package View.AssetManagers.LenhDieuXe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import net.sf.jasperreports.engine.JRException;

public class LenhDieuxe extends Shell {
	private Text text_SoKmhientai;
	private Text text_Quangduongdukien;
	private Text text_Noidung;
	private Text text_Diadiemgiodon;
	private Text text_Malenh;
	private static NGUOIDUNG user;
	private Text text_PhieuNhienlieuduoccap;
	private Combo combo_Bienso;
	private Combo combo_canbo;
	private DateTime dateTime_Ngaydi;
	private DateTime dateTime_Ngayve;
	private Button btnHonTt;
	private Text text_Tonxang_Hientai;
	private Text text_xuatphat;
	private Text text_Noiden;
	protected Ldx_Calendar cl;
	protected Ldx_RecentMess rm;
	protected ldx_Nguoilai nl;
	protected Ldx_chonxe dx;
	private final Controler controler;
	private static PHUONGTIEN_GIAOTHONG ptgt;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(LenhDieuxe.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LenhDieuxe shell = new LenhDieuxe(display, user, ptgt);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @param l
	 * @throws SQLException
	 */
	public LenhDieuxe(Display display, NGUOIDUNG user, PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (cl != null)
					cl.shlChnNgy.dispose();
				if (rm != null)
					rm.shell.dispose();
				if (nl != null)
					nl.shlChnNgiL.dispose();
				if (dx != null)
					dx.shlChnNgiL.dispose();
			}
		});
		setImage(SWTResourceManager.getImage(LenhDieuxe.class, "/car (1).png"));
		setLayout(new GridLayout(7, false));
		LenhDieuxe.user = user;
		LenhDieuxe.ptgt = ptgt;
		controler = new Controler(user);

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(LenhDieuxe.class, "/train-icon.png"));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		GridData gd_lblNewLabel = new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1);
		gd_lblNewLabel.horizontalIndent = 10;
		gd_lblNewLabel.verticalIndent = 5;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("Times New Roman", 18, SWT.BOLD));
		lblNewLabel.setText("L\u1EC6NH \u0110I\u1EC0U XE");

		Label lblMLnh = new Label(this, SWT.NONE);
		GridData gd_lblMLnh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_lblMLnh.verticalIndent = 6;
		lblMLnh.setLayoutData(gd_lblMLnh);
		lblMLnh.setText("M\u00E3 l\u1EC7nh:");

		text_Malenh = new Text(this, SWT.BORDER);
		text_Malenh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Malenh.setEditable(false);
		GridData gd_text_Malenh = new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1);
		gd_text_Malenh.verticalIndent = 6;
		text_Malenh.setLayoutData(gd_text_Malenh);

		Label lblXeT = new Label(this, SWT.NONE);
		lblXeT.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblXeT.setText("Xe \u00F4 t\u00F4 bi\u1EC3n s\u1ED1:");

		Button button_10 = new Button(this, SWT.NONE);
		button_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					if ((dx == null || dx.shlChnNgiL.isDisposed())) {
						dx = new Ldx_chonxe(getShell(), SWT.DIALOG_TRIM, user, absolutePos);
						dx.open();
					}
					if (dx.result != null) {
						PHUONGTIEN_GIAOTHONG nd = (PHUONGTIEN_GIAOTHONG) dx.result;
						setCombo_PhuongtienGiaothong(nd);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		GridData gd_button_10 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_10.widthHint = 32;
		button_10.setLayoutData(gd_button_10);
		button_10.setText("S");

		combo_Bienso = new Combo(this, SWT.READ_ONLY);
		combo_Bienso.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
				fillData_PTGT(ptgt);
			}
		});
		combo_Bienso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblNgiLiXe = new Label(this, SWT.NONE);
		lblNgiLiXe.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNgiLiXe.setText("Ng\u01B0\u1EDDi l\u00E1i xe:");

		Button btnS = new Button(this, SWT.NONE);
		btnS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					if ((nl == null || nl.shlChnNgiL.isDisposed())) {
						nl = new ldx_Nguoilai(getShell(), SWT.DIALOG_TRIM, user, absolutePos);
						nl.open();
					}
					if (nl.result != null) {
						NGUOIDUNG nd = (NGUOIDUNG) nl.result;
						for (int i = 0; i < combo_canbo.getItemCount(); i++) {
							if (nd.getTEN_TAI_KHOAN().equals(combo_canbo.getItem(i))) {
								combo_canbo.select(i);
							}
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		GridData gd_btnS = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnS.widthHint = 32;
		btnS.setLayoutData(gd_btnS);
		btnS.setText("U");

		combo_canbo = new Combo(this, SWT.READ_ONLY);
		combo_canbo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblXutPht = new Label(this, SWT.NONE);
		lblXutPht.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblXutPht.setText("N\u01A1i Xu\u1EA5t ph\u00E1t:");

		Button btnR = new Button(this, SWT.NONE);
		btnR.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					ArrayList<String> recent = controler.getControl_LENH_DIEU_XE().get_Recent_DiemXuatphat(20);
					if ((rm == null || rm.shell.isDisposed()) && recent != null) {
						rm = new Ldx_RecentMess(getShell(), SWT.DIALOG_TRIM, absolutePos, "Chọn Điểm xuất phát",
								recent);
						rm.open();
					}
					if (rm.result != null) {
						String text = (String) rm.result;
						text_xuatphat.setText(text);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		GridData gd_btnR = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnR.widthHint = 32;
		btnR.setLayoutData(gd_btnR);
		btnR.setText("R");

		text_xuatphat = new Text(this, SWT.BORDER);
		text_xuatphat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblNin = new Label(this, SWT.NONE);
		lblNin.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNin.setText("N\u01A1i \u0111\u1EBFn:");

		Button button_9 = new Button(this, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					ArrayList<String> recent = controler.getControl_LENH_DIEU_XE().get_Recent_DiemDen(20);
					if ((rm == null || rm.shell.isDisposed()) && recent != null) {
						rm = new Ldx_RecentMess(getShell(), SWT.DIALOG_TRIM, absolutePos, "Chọn Điểm đến", recent);
						rm.open();
					}
					if (rm.result != null) {
						String text = (String) rm.result;
						text_Noiden.setText(text);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		GridData gd_button_9 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_9.widthHint = 32;
		button_9.setLayoutData(gd_button_9);
		button_9.setText("R");

		text_Noiden = new Text(this, SWT.BORDER);
		text_Noiden.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblNgyi = new Label(this, SWT.NONE);
		lblNgyi.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNgyi.setText("Ng\u00E0y \u0111i:");

		Button btnC = new Button(this, SWT.NONE);
		btnC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				if (event.widget instanceof Control) {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					if (cl == null || cl.shlChnNgy.isDisposed()) {
						cl = new Ldx_Calendar(getShell(), SWT.NONE, absolutePos, "Chọn Ngày đi");
						cl.open();
					}
					if (cl.result != null) {
						int[] arr = (int[]) cl.result;
						dateTime_Ngaydi.setDate(arr[0], arr[1], arr[2]);
					}
				}
			}
		});
		GridData gd_btnC = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnC.widthHint = 32;
		btnC.setLayoutData(gd_btnC);
		btnC.setText("C");

		dateTime_Ngaydi = new DateTime(this, SWT.BORDER);
		dateTime_Ngaydi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		dateTime_Ngaydi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblNgyV = new Label(this, SWT.NONE);
		lblNgyV.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNgyV.setText("Ng\u00E0y v\u1EC1:");

		Button button_8 = new Button(this, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				if (event.widget instanceof Control) {
					Point absolutePos = ((Control) event.widget).toDisplay(event.x, event.y);
					if (cl == null || cl.shlChnNgy.isDisposed()) {
						cl = new Ldx_Calendar(getShell(), SWT.NONE, absolutePos, "Chọn Ngày về");
						cl.open();
					}
					if (cl.result != null) {
						int[] arr = (int[]) cl.result;
						Date Ngayve = mdf.date(arr[2], arr[1], arr[0]);
						Date Ngaydi = mdf.getDate(dateTime_Ngaydi);
						if (Ngayve.before(Ngaydi)) {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Lỗi");
							m.setMessage("Xe về sau khi xuất phát!");
							m.open();
						} else {
							dateTime_Ngayve.setDate(arr[0], arr[1], arr[2]);
						}
					}
				}
			}
		});
		GridData gd_button_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_8.widthHint = 32;
		button_8.setLayoutData(gd_button_8);
		button_8.setText("C");

		dateTime_Ngayve = new DateTime(this, SWT.BORDER);
		dateTime_Ngayve.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		dateTime_Ngayve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblSKmXe = new Label(this, SWT.NONE);
		lblSKmXe.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblSKmXe.setText("S\u1ED1 km xe hi\u1EC7n t\u1EA1i:");

		Button button_2 = new Button(this, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int curr = Integer.valueOf(text_SoKmhientai.getText());
				curr += 15;
				text_SoKmhientai.setText(curr + "");
			}
		});
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 32;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("+15");

		Button button_3 = new Button(this, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int curr = Integer.valueOf(text_SoKmhientai.getText());
				if (curr - 10 >= 0)
					curr -= 10;
				text_SoKmhientai.setText(curr + "");
			}
		});
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_3.widthHint = 32;
		button_3.setLayoutData(gd_button_3);
		button_3.setText("-10");

		Button btnA = new Button(this, SWT.NONE);
		btnA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
					int SoKmxe = ptgt.getSO_KM_XE();
					LENH_DIEU_XE LenhGanNhat = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_Gannhat(ptgt);
					int QuangduongDiLantruoc = (LenhGanNhat == null) ? 0 : LenhGanNhat.getQUANG_DUONG_DUKIEN();
					int DukienKmxeHientai = SoKmxe + QuangduongDiLantruoc;
					text_SoKmhientai.setText(DukienKmxeHientai + "");
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnA = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnA.widthHint = 32;
		btnA.setLayoutData(gd_btnA);
		btnA.setText("A");

		Button btnE = new Button(this, SWT.NONE);
		btnE.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
				text_SoKmhientai.setText(ptgt.getSO_KM_XE() + "");
			}
		});
		GridData gd_btnE = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnE.widthHint = 32;
		btnE.setLayoutData(gd_btnE);
		btnE.setText("RS");

		text_SoKmhientai = new Text(this, SWT.BORDER | SWT.RIGHT);
		text_SoKmhientai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_SoKmhientai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_SoKmhientai.selectAll();
			}
		});
		text_SoKmhientai.addVerifyListener(new VerifyListener() {
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
		text_SoKmhientai.setText("0");
		text_SoKmhientai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblQungngD = new Label(this, SWT.NONE);
		lblQungngD.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblQungngD.setText("Qu\u00E3ng \u0111\u01B0\u1EDDng d\u1EF1 ki\u1EBFn:");

		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int curr = Integer.valueOf(text_Quangduongdukien.getText());
				curr += 15;
				text_Quangduongdukien.setText(curr + "");
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 32;
		button.setLayoutData(gd_button);
		button.setText("+15");

		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int curr = Integer.valueOf(text_Quangduongdukien.getText());
				if (curr - 10 >= 0)
					curr -= 10;
				text_Quangduongdukien.setText(curr + "");
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 32;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("-10");

		text_Quangduongdukien = new Text(this, SWT.BORDER | SWT.RIGHT);
		text_Quangduongdukien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Quangduongdukien.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_Quangduongdukien.selectAll();
			}
		});
		text_Quangduongdukien.addVerifyListener(new VerifyListener() {
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
		text_Quangduongdukien.setText("0");
		text_Quangduongdukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblTnXngHin = new Label(this, SWT.NONE);
		lblTnXngHin.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblTnXngHin.setText("Tồn Nhiên liệu hiện tại: ");

		Button button_12 = new Button(this, SWT.NONE);
		button_12.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
					double DinhMucXang_dauOto = controler.getControl_LOAI_XE().get_LOAI_XE(ptgt.getMA_LOAI_XE())
							.getDINH_MUC_XANG_DAU();
					LENH_DIEU_XE LenhGanNhat;
					LenhGanNhat = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_Gannhat(ptgt);

					int TonNhienlieuLantruoc = 0;
					int NhienLieuLantruoc = 0;
					int QuangduongDiLantruoc = 0;
					if (LenhGanNhat != null) {
						TonNhienlieuLantruoc = LenhGanNhat.getTON_NHIENLIEU_HIENTAI();
						NhienLieuLantruoc = LenhGanNhat.getPHIEU_NHIENLIEU_DUOCCAP();
						int kmXeHientai = Integer.valueOf(text_SoKmhientai.getText());
						QuangduongDiLantruoc = kmXeHientai - LenhGanNhat.getSO_KM_HIENTAI();
					}
					int Tong_NhienlieuLantruoc = TonNhienlieuLantruoc + NhienLieuLantruoc;
					double DukienXangTonHientai = Tong_NhienlieuLantruoc
							- QuangduongDiLantruoc * DinhMucXang_dauOto / 100;
					if (DukienXangTonHientai > 0)
						text_Tonxang_Hientai.setText((int) DukienXangTonHientai + "");
					else {
						text_Tonxang_Hientai.setText("0");
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button_12 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_12.widthHint = 32;
		button_12.setLayoutData(gd_button_12);
		button_12.setText("A");

		text_Tonxang_Hientai = new Text(this, SWT.BORDER | SWT.RIGHT);
		text_Tonxang_Hientai.setText("0");
		text_Tonxang_Hientai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tonxang_Hientai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		Label lblPhiuXngc = new Label(this, SWT.NONE);
		lblPhiuXngc.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblPhiuXngc.setText("Nhiên liệu được cấp (lít):");

		Button button_4 = new Button(this, SWT.NONE);
		GridData gd_button_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_4.widthHint = 32;
		button_4.setLayoutData(gd_button_4);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text_PhieuNhienlieuduoccap.getEditable()) {
					int Curr = Integer.valueOf(text_PhieuNhienlieuduoccap.getText());
					Curr += 50;
					text_PhieuNhienlieuduoccap.setText(Curr + "");
				}
			}
		});
		button_4.setText("+50");

		Button button_5 = new Button(this, SWT.NONE);
		GridData gd_button_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_5.widthHint = 32;
		button_5.setLayoutData(gd_button_5);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text_PhieuNhienlieuduoccap.getEditable()) {
					int Curr = Integer.valueOf(text_PhieuNhienlieuduoccap.getText());
					if (Curr - 25 >= 0) {
						Curr -= 25;
						text_PhieuNhienlieuduoccap.setText(Curr + "");
					}
				}
			}
		});
		button_5.setText("-25");

		text_PhieuNhienlieuduoccap = new Text(this, SWT.BORDER | SWT.RIGHT);
		text_PhieuNhienlieuduoccap.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_PhieuNhienlieuduoccap.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_PhieuNhienlieuduoccap.selectAll();
			}
		});
		text_PhieuNhienlieuduoccap.addVerifyListener(new VerifyListener() {
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

		text_PhieuNhienlieuduoccap.setText("0");
		text_PhieuNhienlieuduoccap.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblaimGi = new Label(this, SWT.NONE);
		GridData gd_lblaimGi = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
		gd_lblaimGi.verticalIndent = 3;
		lblaimGi.setLayoutData(gd_lblaimGi);
		lblaimGi.setText("\u0110\u1ECBa \u0111i\u1EC3m, gi\u1EDD \u0111\u00F3n:");

		text_Diadiemgiodon = new Text(this, SWT.BORDER);
		text_Diadiemgiodon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Diadiemgiodon.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 5, 1));

		Label lblNiDungChuyn = new Label(this, SWT.NONE);
		GridData gd_lblNiDungChuyn = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
		gd_lblNiDungChuyn.verticalIndent = 3;
		lblNiDungChuyn.setLayoutData(gd_lblNiDungChuyn);
		lblNiDungChuyn.setText("N\u1ED9i dung chuy\u1EBFn \u0111i:");

		text_Noidung = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Noidung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_text_Noidung = new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1);
		gd_text_Noidung.heightHint = 101;
		text_Noidung.setLayoutData(gd_text_Noidung);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		btnHonTt = new Button(this, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					if (check_Ngaydi_Ngayve() && check_NhienLieu_Daydu()) {
						if (check_Xe_trong_Thoigiandieu()) {
							Relator rl = new Relator();
							ArrayList<BeanRealator> a = new ArrayList<>();
							LENH_DIEU_XE l = getLenhDieuXe();
							PHUONGTIEN_GIAOTHONG p = (PHUONGTIEN_GIAOTHONG) combo_Bienso
									.getData(combo_Bienso.getText());
							if (p != null) {
								String Maptgt = p.getMA_PHUONGTIEN_GIAOTHONG();
								BeanRealator b = new BeanRealator(l, user);
								a.add(b);
								rl.getRelator(a);
								controler.getControl_LENH_DIEU_XE().insert_LENH_DIEU_XE(l);
								controler.getControl_PHUONGTIEN_GIAOTHONG().update_soKmXe(Maptgt,
										Integer.valueOf(text_SoKmhientai.getText()));
							}
							dispose();

						}
					}
				} catch (JRException | NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}

			private boolean check_Xe_trong_Thoigiandieu() throws SQLException {
				PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
				LENH_DIEU_XE Lenh_Ganday = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_Gannhat(ptgt);
				Date Ngaydi = mdf.getDate(dateTime_Ngaydi);
				if (Lenh_Ganday == null)
					return true;
				if (Lenh_Ganday.getNGAY_VE().before(Ngaydi)) {
					return true;
				}
				int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL;
				MessageBox messageBox_Thongbao = new MessageBox(getShell(), style);
				messageBox_Thongbao.setText("Cảnh báo");
				messageBox_Thongbao.setMessage("Xe đang trong giai đoạn thực hiện Lệnh điều xe, xem lệnh?");
				int rc = messageBox_Thongbao.open();

				switch (rc) {
				case SWT.OK:
					break;
				case SWT.CANCEL:
					break;
				case SWT.YES:
					openLenhdieuXe_Gannhat(ptgt);
				case SWT.NO:
					return false;
				case SWT.RETRY:
					break;
				case SWT.ABORT:
					break;
				case SWT.IGNORE:
					break;
				}
				return false;
			}

			private void openLenhdieuXe_Gannhat(PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
				LENH_DIEU_XE LenhGannhat = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_Gannhat(ptgt);
				QuanLy_Lenhdieuxe q = new QuanLy_Lenhdieuxe(getShell(), SWT.DIALOG_TRIM, user, LenhGannhat, false);
				q.open();
			}

			private boolean check_NhienLieu_Daydu() throws SQLException {
				PHUONGTIEN_GIAOTHONG ptgt = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
				if (ptgt != null) {
					int QuangduongDukien = Integer.valueOf(text_Quangduongdukien.getText());
					int Tong_NhienLieu = Integer.valueOf(text_PhieuNhienlieuduoccap.getText())
							+ Integer.valueOf(text_Tonxang_Hientai.getText());
					Double DinhmucXangDau = controler.getControl_LOAI_XE().get_LOAI_XE(ptgt.getMA_LOAI_XE())
							.getDINH_MUC_XANG_DAU();
					if (QuangduongDukien * DinhmucXangDau / 100 > Tong_NhienLieu + 5) {
						int NhienlieuCan = (int) (QuangduongDukien * DinhmucXangDau / 100);
						int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL;
						MessageBox messageBox_Thongbao = new MessageBox(getShell(), style);
						messageBox_Thongbao.setText("Cảnh báo");
						messageBox_Thongbao
								.setMessage("- Nhiên liệu có thể sẽ không đủ cho chuyến đi! \n- cần khoảng: '"
										+ NhienlieuCan + "' (lít)\n- Tiếp tục tạo lệnh?");
						int rc = messageBox_Thongbao.open();

						switch (rc) {
						case SWT.OK:
							break;
						case SWT.CANCEL:
							break;
						case SWT.YES:
							return true;
						case SWT.NO:
							return false;
						case SWT.RETRY:
							break;
						case SWT.ABORT:
							break;
						case SWT.IGNORE:
							break;
						}

						return false;
					}
				}
				return true;

			}

			private boolean check_Ngaydi_Ngayve() {
				Date Ngayve = mdf.getDate(dateTime_Ngayve);
				Date Ngaydi = mdf.getDate(dateTime_Ngaydi);
				if (Ngayve.before(Ngaydi)) {
					MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
					m.setText("Lỗi");
					m.setMessage("Xe về sau khi xuất phát!");
					m.open();
					return false;
				} else {
					return true;
				}
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Ho\u00E0n t\u1EA5t");

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 75;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("\u0110\u00F3ng");
		createContents();
		init();
	}

	protected void setCombo_PhuongtienGiaothong(PHUONGTIEN_GIAOTHONG ptgt) {
		for (int i = 0; i < combo_Bienso.getItemCount(); i++) {
			if (ptgt.getBIENSO().equals(combo_Bienso.getItem(i))) {
				combo_Bienso.select(i);
			}
		}
	}

	protected LENH_DIEU_XE getLenhDieuXe() {
		LENH_DIEU_XE l = new LENH_DIEU_XE();
		PHUONGTIEN_GIAOTHONG p = (PHUONGTIEN_GIAOTHONG) combo_Bienso.getData(combo_Bienso.getText());
		if (p != null) {
			String Maptgt = p.getMA_PHUONGTIEN_GIAOTHONG();
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
			l.setNGAY_DI(mdf.getDate(dateTime_Ngaydi));
			l.setNGAY_VE(mdf.getDate(dateTime_Ngayve));
			l.setSO_KM_HIENTAI(Integer.valueOf(text_SoKmhientai.getText()));
			l.setQUANG_DUONG_DUKIEN(Integer.valueOf(text_Quangduongdukien.getText()));
			l.setTON_NHIENLIEU_HIENTAI(Integer.valueOf(text_Tonxang_Hientai.getText()));
			l.setPHIEU_NHIENLIEU_DUOCCAP(Integer.valueOf(text_PhieuNhienlieuduoccap.getText()));
			l.setDIADIEM_GIODON(text_Diadiemgiodon.getText());
			l.setNOIDUNG_CHUYENDI(text_Noidung.getText());
			l.setNGUOI_TAO_LENH(user.getTEN_TAI_KHOAN());
			return l;
		}
		return null;
	}

	protected void fillData_PTGT(PHUONGTIEN_GIAOTHONG ptgt) {
		text_SoKmhientai.setText(ptgt.getSO_KM_XE() + "");
		text_Tonxang_Hientai.setText("0");
	}

	private void init() throws SQLException {
		text_Malenh.setText(String.valueOf(controler.getControl_LENH_DIEU_XE().getNetxt_Key()));
		Fill_ItemData f = new Fill_ItemData();
		ArrayList<PHUONGTIEN_GIAOTHONG> pl = controler.getControl_PHUONGTIEN_GIAOTHONG().get_All_Oto();
		f.setComboBox_PHUONGTIEN_GIAOTHONG(combo_Bienso, pl);
		ArrayList<NGUOIDUNG> ndl = controler.getControl_NGUOIDUNG().get_All();
		f.setComboBox_NGUOIDUNG(combo_canbo, ndl);
		combo_Bienso.setEnabled(true);
		combo_canbo.setEnabled(true);
		text_xuatphat.setEditable(true);
		text_Noiden.setEditable(true);
		dateTime_Ngaydi.setEnabled(true);
		dateTime_Ngayve.setEnabled(true);
		text_SoKmhientai.setEditable(true);
		text_Quangduongdukien.setEditable(true);
		text_PhieuNhienlieuduoccap.setEditable(true);
		text_Diadiemgiodon.setEditable(true);
		text_Noidung.setEditable(true);
		btnHonTt.setText("Hoàn tất");

		if (ptgt != null) {
			setCombo_PhuongtienGiaothong(ptgt);
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u0110i\u1EC1u xe");
		setSize(386, 530);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
