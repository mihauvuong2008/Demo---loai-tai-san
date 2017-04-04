package View.AssetManagers.LenhDieuXe;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class QuanLy_Lenhdieuxe extends Dialog {

	protected Object result;
	protected Shell shlQunLLnh;
	private Text text_Malenh;
	private Text text_SoKmhientai;
	private Text text_Quangduongdukien;
	private Text text_PhieuNhienlieuDuoccap;
	private Text text_Diadiemgiodon;
	private Text text_Noidung;
	private static NGUOIDUNG user;
	private Combo combo_canbo;
	private Combo combo_Bienso;
	private DateTime dateTime_Ngaydi;
	private DateTime dateTime_Ngayve;
	private LENH_DIEU_XE l;
	boolean ViewMode = false;// Edit
	private ToolBar toolBar;
	private ToolItem tltmLu;
	private Text text_Tonxang_Hientai;
	private Label lblTnNhinLiu;
	private Text text_xuatphat;
	private Text text_Noiden;
	private ToolItem tltmHyLnh;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(QuanLy_Lenhdieuxe.class);
	private Label lblNhinLiuMua;
	private Text text_PhieuNhienLieuMuangoai;
	private DateTime dateTime_time1;
	private DateTime dateTime_time2;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param V
	 */
	public QuanLy_Lenhdieuxe(Shell parent, int style, NGUOIDUNG user, LENH_DIEU_XE l, boolean ViewMode) {
		super(parent, style);
		setText("SWT Dialog");
		QuanLy_Lenhdieuxe.user = user;
		this.l = l;
		this.ViewMode = ViewMode;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlQunLLnh.open();
		shlQunLLnh.layout();
		Display display = getParent().getDisplay();
		while (!shlQunLLnh.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlQunLLnh = new Shell(getParent(), getStyle());
		shlQunLLnh.setImage(SWTResourceManager.getImage(QuanLy_Lenhdieuxe.class, "/Files-Clipboard-icon.png"));
		shlQunLLnh.setSize(386, 530);
		new FormTemplate().setCenterScreen(shlQunLLnh);
		shlQunLLnh.setText("Quản lý lệnh điều xe");
		shlQunLLnh.setLayout(new GridLayout(3, false));

		toolBar = new ToolBar(shlQunLLnh, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Date Ngaydi = mdf.getDate(dateTime_Ngaydi);
					Date Ngayve = mdf.getDate(dateTime_Ngayve);
					if (Ngayve.before(Ngaydi)) {
						MessageBox m = new MessageBox(shlQunLLnh, SWT.ICON_WARNING);
						m.setText("Lỗi");
						m.setMessage("Xe về sau khi xuất phát!");
						m.open();
					} else {
						LENH_DIEU_XE ldx = getLenhDieuXe();
						ldx.setMA_LENH_DIEU_XE(l.getMA_LENH_DIEU_XE());
						controler.getControl_LENH_DIEU_XE().update_LENH_DIEU_XE(ldx);
						l = controler.getControl_LENH_DIEU_XE().get_LENH_DIEU_XE(l.getMA_LENH_DIEU_XE());

					}
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLu.setImage(SWTResourceManager.getImage(QuanLy_Lenhdieuxe.class, "/Actions-document-save-icon (1).png"));
		tltmLu.setText("Lưu");

		tltmHyLnh = new ToolItem(toolBar, SWT.NONE);
		tltmHyLnh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (l.getHUY_LENH() == 1) {
						Date Ngaydi = mdf.getDate(dateTime_Ngaydi);
						MessageBox m = new MessageBox(shlQunLLnh, SWT.ICON_WARNING);
						m.setText("Hủy lệnh?");
						m.setMessage("Bạn có chắc muốn hủy Lệnh điều xe " + combo_Bienso.getText() + " vào ngày: "
								+ mdf.getViewStringDate(Ngaydi));
						controler.getControl_LENH_DIEU_XE().set_Huylenh(l, true);
					} else {
						controler.getControl_LENH_DIEU_XE().set_Huylenh(l, false);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmHyLnh.setText("Hủy - Lập lại lệnh");
		tltmHyLnh.setImage(SWTResourceManager.getImage(QuanLy_Lenhdieuxe.class, "/Button-warning-icon.png"));

		ToolItem tltmXemVIn = new ToolItem(toolBar, SWT.NONE);
		tltmXemVIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Relator rl = new Relator();
				try {
					ArrayList<BeanRealator> a = new ArrayList<>();
					BeanRealator b = new BeanRealator(l, user);
					a.add(b);
					rl.getRelator(a);
				} catch (JRException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tltmXemVIn.setText("Xem và in");
		tltmXemVIn
				.setImage(SWTResourceManager.getImage(QuanLy_Lenhdieuxe.class, "/Actions-document-print-icon (1).png"));

		Label label_1 = new Label(shlQunLLnh, SWT.NONE);
		label_1.setText("Mã lệnh:");

		text_Malenh = new Text(shlQunLLnh, SWT.BORDER);
		text_Malenh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_Malenh.setText("0");
		text_Malenh.setEditable(false);
		text_Malenh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_2 = new Label(shlQunLLnh, SWT.NONE);
		label_2.setText("Xe ô tô biển số:");

		combo_Bienso = new Combo(shlQunLLnh, SWT.READ_ONLY);
		combo_Bienso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label label_3 = new Label(shlQunLLnh, SWT.NONE);
		label_3.setText("Người lái xe:");

		combo_canbo = new Combo(shlQunLLnh, SWT.READ_ONLY);
		combo_canbo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label label_4 = new Label(shlQunLLnh, SWT.NONE);
		label_4.setText("Nơi Xuất phát:");

		text_xuatphat = new Text(shlQunLLnh, SWT.BORDER);
		text_xuatphat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_5 = new Label(shlQunLLnh, SWT.NONE);
		label_5.setText("Nơi đến:");

		text_Noiden = new Text(shlQunLLnh, SWT.BORDER);
		text_Noiden.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_6 = new Label(shlQunLLnh, SWT.NONE);
		label_6.setText("Ngày đi:");

		dateTime_time1 = new DateTime(shlQunLLnh, SWT.BORDER | SWT.TIME);

		dateTime_Ngaydi = new DateTime(shlQunLLnh, SWT.BORDER);
		dateTime_Ngaydi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		dateTime_Ngaydi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_7 = new Label(shlQunLLnh, SWT.NONE);
		label_7.setText("Ngày về:");

		dateTime_time2 = new DateTime(shlQunLLnh, SWT.BORDER | SWT.TIME);

		dateTime_Ngayve = new DateTime(shlQunLLnh, SWT.BORDER);
		dateTime_Ngayve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		dateTime_Ngayve.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_8 = new Label(shlQunLLnh, SWT.NONE);
		label_8.setText("Số km xe hiện tại:");

		text_SoKmhientai = new Text(shlQunLLnh, SWT.BORDER | SWT.RIGHT);
		text_SoKmhientai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_SoKmhientai.setText("0");
		text_SoKmhientai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_9 = new Label(shlQunLLnh, SWT.NONE);
		label_9.setText("Quãng đường dự kiến:");

		text_Quangduongdukien = new Text(shlQunLLnh, SWT.BORDER | SWT.RIGHT);
		text_Quangduongdukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_Quangduongdukien.setText("0");
		text_Quangduongdukien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		lblTnNhinLiu = new Label(shlQunLLnh, SWT.NONE);
		lblTnNhinLiu.setText("Tồn Nhiên liệu hiện tại: ");

		text_Tonxang_Hientai = new Text(shlQunLLnh, SWT.BORDER | SWT.RIGHT);
		text_Tonxang_Hientai.setText("0");
		text_Tonxang_Hientai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tonxang_Hientai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblNhinLiuc = new Label(shlQunLLnh, SWT.NONE);
		lblNhinLiuc.setText("Nhiên liệu được cấp:");

		text_PhieuNhienlieuDuoccap = new Text(shlQunLLnh, SWT.BORDER | SWT.RIGHT);
		text_PhieuNhienlieuDuoccap.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_PhieuNhienlieuDuoccap.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_PhieuNhienlieuDuoccap.selectAll();
			}
		});
		text_PhieuNhienlieuDuoccap.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		text_PhieuNhienlieuDuoccap.setText("0");
		text_PhieuNhienlieuDuoccap.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		lblNhinLiuMua = new Label(shlQunLLnh, SWT.NONE);
		lblNhinLiuMua.setText("Nhiên liệu mua ngoài:");

		text_PhieuNhienLieuMuangoai = new Text(shlQunLLnh, SWT.BORDER | SWT.RIGHT);
		text_PhieuNhienLieuMuangoai.setText("0");
		text_PhieuNhienLieuMuangoai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_PhieuNhienLieuMuangoai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_12 = new Label(shlQunLLnh, SWT.NONE);
		label_12.setText("Địa điểm, giờ đón:");

		text_Diadiemgiodon = new Text(shlQunLLnh, SWT.BORDER);
		text_Diadiemgiodon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		text_Diadiemgiodon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Label label_13 = new Label(shlQunLLnh, SWT.NONE);
		GridData gd_label_13 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_13.verticalIndent = 3;
		label_13.setLayoutData(gd_label_13);
		label_13.setText("Nội dung chuyến đi:");

		text_Noidung = new Text(shlQunLLnh, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Noidung.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		text_Noidung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		Button btnHonTt = new Button(shlQunLLnh, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlQunLLnh.dispose();
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 3, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Đóng");
		init();
	}

	protected LENH_DIEU_XE getLenhDieuXe() {
		LENH_DIEU_XE l = new LENH_DIEU_XE();
		String Maptgt = ((PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText()))
				.getMA_PHUONGTIEN_GIAOTHONG();
		if (combo_Bienso.getItemCount() > 0) {
			l.setMA_PHUONGTIEN_GIAOTHONG(Maptgt);
		} else {
			l.setMA_PHUONGTIEN_GIAOTHONG("Trống");
		}
		if (combo_canbo.getItemCount() > 0) {
			l.setTEN_TAI_KHOAN(((NGUOIDUNG) combo_canbo.getData(combo_canbo.getText())).getTEN_TAI_KHOAN());
		} else {
			l.setTEN_TAI_KHOAN("Trống");
		}
		l.setDIEM_XUATPHAT(text_xuatphat.getText());
		l.setDIEM_DEN(text_Noiden.getText());
		l.setNGAY_DI(mdf.getDate(dateTime_time1, dateTime_Ngaydi));
		l.setNGAY_VE(mdf.getDate(dateTime_time2, dateTime_Ngayve));
		l.setSO_KM_HIENTAI(Integer.valueOf(text_SoKmhientai.getText()));
		l.setTON_NHIENLIEU_HIENTAI(Integer.valueOf(text_Tonxang_Hientai.getText()));
		l.setQUANG_DUONG_DUKIEN(Integer.valueOf(text_Quangduongdukien.getText()));
		l.setPHIEU_NHIENLIEU_DUOCCAP(Integer.valueOf(text_PhieuNhienlieuDuoccap.getText()));
		l.setPHIEU_NHIENLIEU_MUANGOAI(Integer.valueOf(text_PhieuNhienLieuMuangoai.getText()));
		l.setDIADIEM_GIODON(text_Diadiemgiodon.getText());
		l.setNOIDUNG_CHUYENDI(text_Noidung.getText());
		return l;
	}

	private void init() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		ArrayList<PHUONGTIEN_GIAOTHONG> pl = controler.getControl_PHUONGTIEN_GIAOTHONG().get_All_Oto();
		f.setComboBox_PHUONGTIEN_GIAOTHONG(combo_Bienso, pl);
		ArrayList<NGUOIDUNG> ndl = controler.getControl_NGUOIDUNG().get_All();
		f.setComboBox_NGUOIDUNG(combo_canbo, ndl);
		if (l != null) {// xem
			text_Malenh.setText(String.valueOf(l.getMA_LENH_DIEU_XE()));
			combo_Bienso.setText(controler.getControl_PHUONGTIEN_GIAOTHONG()
					.get_PHUONGTIEN_GIAOTHONG(l.getMA_PHUONGTIEN_GIAOTHONG()).getBIENSO());
			combo_canbo
					.setText(controler.getControl_NGUOIDUNG().get_NGUOIDUNG(l.getTEN_TAI_KHOAN()).getTEN_TAI_KHOAN());
			text_xuatphat.setText(l.getDIEM_XUATPHAT());
			text_Noiden.setText(l.getDIEM_DEN());
			Calendar cal1 = mdf.getCalendar(l.getNGAY_DI());
			Calendar cal2 = mdf.getCalendar(l.getNGAY_VE());
			dateTime_time1.setHours(cal1.get(Calendar.HOUR_OF_DAY));
			dateTime_time1.setMinutes(cal1.get(Calendar.MINUTE));
			dateTime_Ngaydi.setDate(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
			dateTime_time2.setHours(cal2.get(Calendar.HOUR_OF_DAY));
			dateTime_time2.setMinutes(cal2.get(Calendar.MINUTE));
			dateTime_Ngayve.setDate(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH));
			text_SoKmhientai.setText(l.getSO_KM_HIENTAI() + "");
			text_Tonxang_Hientai.setText(l.getTON_NHIENLIEU_HIENTAI() + "");
			text_Quangduongdukien.setText(l.getQUANG_DUONG_DUKIEN() + "");
			text_PhieuNhienlieuDuoccap.setText(l.getPHIEU_NHIENLIEU_DUOCCAP() + "");
			text_PhieuNhienLieuMuangoai.setText(l.getPHIEU_NHIENLIEU_MUANGOAI() + "");
			text_Diadiemgiodon.setText(l.getDIADIEM_GIODON());
			text_Noidung.setText(l.getNOIDUNG_CHUYENDI());
			if (l.getHUY_LENH() == 1) {
				tltmHyLnh.setText("Hủy lệnh");
			} else {
				tltmHyLnh.setText("Lập lại lệnh");
			}
		}
		if (ViewMode) {
			tltmLu.dispose();
			tltmHyLnh.dispose();
		}
	}
}
