package View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.CHUKY_DANGKIEM;
import DAO.DOT_THUCHIEN_DANGKIEM;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

public class LichDangkiem extends Dialog {

	protected Object result;
	protected Shell shlngKim;
	private Table table;
	private Table table_DotthuchienDangkiem;
	private Text text_Search;
	private Combo combo_Phongban;
	protected PHONGBAN phongbanSelected;
	private final Controler controler;
	protected NGUOIDUNG user;
	private static Log log = LogFactory.getLog(LichDangkiem.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public LichDangkiem(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.user = user;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlngKim.open();
		shlngKim.layout();
		Display display = getParent().getDisplay();
		while (!shlngKim.isDisposed()) {
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
		shlngKim = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlngKim.setImage(
				SWTResourceManager.getImage(LichDangkiem.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlngKim.setSize(728, 450);
		new FormTemplate().setCenterScreen(shlngKim);
		shlngKim.setText("\u0110\u0103ng ki\u1EC3m \u00F4 t\u00F4");
		shlngKim.setLayout(new GridLayout(5, false));

		SashForm sashForm = new SashForm(shlngKim, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table_DotthuchienDangkiem.removeAll();
				TableItem til[] = table.getSelection();
				if (til.length > 0) {
					PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) til[0].getData();
					try {
						fillLichsuDangkiem(pg);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private void fillLichsuDangkiem(PHUONGTIEN_GIAOTHONG pg) throws SQLException {
				table_DotthuchienDangkiem.removeAll();
				ArrayList<DOT_THUCHIEN_DANGKIEM> dtdl = controler.getControl_DOT_THUCHIEN_DANGKIEM()
						.get_AllDOT_THUCHIEN_DANGKIEM(pg.getMA_PHUONGTIEN_GIAOTHONG());
				for (DOT_THUCHIEN_DANGKIEM dtd : dtdl) {
					TableItem ti = new TableItem(table_DotthuchienDangkiem, SWT.NONE);
					boolean hoanthanh = getHoanthanhCongviec(dtd);
					ti.setText(new String[] { new MyDateFormat().getViewStringDate(dtd.getNGAY_THUCHIEN()),
							!hoanthanh ? "Đang thực hiện" : "Hoàn thành" });
					ti.setData(dtd);
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnBinS = new TableColumn(table, SWT.NONE);
		tblclmnBinS.setWidth(100);
		tblclmnBinS.setText("BI\u1EC2N S\u1ED0");

		TableColumn tblclmnLoiXe = new TableColumn(table, SWT.NONE);
		tblclmnLoiXe.setWidth(100);
		tblclmnLoiXe.setText("LO\u1EA0I XE");

		TableColumn tblclmnMPtgt = new TableColumn(table, SWT.NONE);
		tblclmnMPtgt.setWidth(100);
		tblclmnMPtgt.setText("M\u00C3 PTGT");

		TableColumn tblclmnLnGnNht = new TableColumn(table, SWT.NONE);
		tblclmnLnGnNht.setWidth(120);
		tblclmnLnGnNht.setText("LẦN GẦN NHẤT");

		TableColumn tblclmnKHnng = new TableColumn(table, SWT.NONE);
		tblclmnKHnng.setWidth(150);
		tblclmnKHnng.setText("K\u1EF2 H\u1EA0N \u0110\u0102NG KI\u1EC2M");

		Menu menu_1 = new Menu(table);
		table.setMenu(menu_1);

		MenuItem mntmThcHinng = new MenuItem(menu_1, SWT.NONE);
		mntmThcHinng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tbi[] = table.getSelection();
				if (tbi.length > 0) {
					PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) tbi[0].getData();
					if (pg != null) {
						boolean hoanthanh = false;
						try {
							DOT_THUCHIEN_DANGKIEM dtd_ = controler.getControl_DOT_THUCHIEN_DANGKIEM()
									.get_DOT_THUCHIEN_DANGKIEM_GANNHAT(pg);
							hoanthanh = getHoanthanhCongviec(dtd_);
							if (hoanthanh) {
								CongViec_Dangkiem cd = new CongViec_Dangkiem(shlngKim, SWT.DIALOG_TRIM, user, pg);
								cd.open();
							} else {
								MessageBox m = new MessageBox(shlngKim, SWT.ICON_WARNING);
								m.setText("Không hợp lệ");
								m.setMessage("Chưa hoàn thành lần đăng kiểm gần nhất");
								m.open();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		mntmThcHinng.setText("Thực hiện Đăng kiểm");

		MenuItem mntmChnChuK = new MenuItem(menu_1, SWT.NONE);
		mntmChnChuK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem til[] = table.getSelection();
				if (til.length > 0) {
					Select_KYHAN_DANGKIEM skd = new Select_KYHAN_DANGKIEM(shlngKim, SWT.DIALOG_TRIM, user);
					try {
						skd.open();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if (skd.result != null) {
						PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) til[0].getData();
						if (pg != null) {
							pg.setMA_KYHAN_DANGKIEM(((CHUKY_DANGKIEM) skd.result).getMA_KYHAN_DANGKIEM());
							try {
								controler.getControl_PHUONGTIEN_GIAOTHONG().update_PHUONGTIEN_GIAOTHONG(pg);
								FillTable(phongbanSelected, text_Search.getText());
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		mntmChnChuK.setText("Chọn Chu kỳ bảo dưỡng");

		table_DotthuchienDangkiem = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_DotthuchienDangkiem.setHeaderVisible(true);
		table_DotthuchienDangkiem.setLinesVisible(true);

		TableColumn tblclmnLnngKim = new TableColumn(table_DotthuchienDangkiem, SWT.NONE);
		tblclmnLnngKim.setWidth(150);
		tblclmnLnngKim.setText("LỊCH SỬ ĐĂNG KIỂM");

		TableColumn tblclmnTnhTrng = new TableColumn(table_DotthuchienDangkiem, SWT.NONE);
		tblclmnTnhTrng.setWidth(100);
		tblclmnTnhTrng.setText("T\u00CCNH TR\u1EA0NG");

		Menu menu = new Menu(table_DotthuchienDangkiem);
		table_DotthuchienDangkiem.setMenu(menu);

		MenuItem mntmXemCngVic = new MenuItem(menu, SWT.NONE);
		mntmXemCngVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table_DotthuchienDangkiem.getSelection();
				if (til.length > 0) {
					DOT_THUCHIEN_DANGKIEM dtd = (DOT_THUCHIEN_DANGKIEM) til[0].getData();
					CongViec_Dangkiem cd = new CongViec_Dangkiem(shlngKim, SWT.DIALOG_TRIM, user, dtd);
					try {
						cd.open();
						init();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXemCngVic.setText("Xem Công việc");

		MenuItem mntmXemHS = new MenuItem(menu, SWT.NONE);
		mntmXemHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem til[] = table_DotthuchienDangkiem.getSelection();
				if (til.length > 0) {
					DOT_THUCHIEN_DANGKIEM dtd = (DOT_THUCHIEN_DANGKIEM) til[0].getData();
					TAP_HO_SO ths = null;
					try {
						ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
						if (ths != null) {
							TAPHOSO_View thsv = new TAPHOSO_View(shlngKim, SWT.DIALOG_TRIM, user, ths, true);
							thsv.open();
						} else {
							MessageBox m = new MessageBox(shlngKim, SWT.ICON_WARNING);
							m.setMessage("Hồ sơ trống, mở giao diện công việc và hoàn tất hồ sơ đăng kiểm");
							m.open();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXemHS.setText("Xem Hồ sơ");
		sashForm.setWeights(new int[] { 1000, 618 });

		Label lblPhngBan = new Label(shlngKim, SWT.NONE);
		lblPhngBan.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPhngBan.setText("Ph\u00F2ng ban: ");

		combo_Phongban = new Combo(shlngKim, SWT.READ_ONLY);

		GridData gd_combo_Phongban = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_Phongban.widthHint = 180;
		combo_Phongban.setLayoutData(gd_combo_Phongban);

		text_Search = new Text(shlngKim, SWT.BORDER);
		text_Search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Search.setMessage("Biển số, Mã Ptgt");
		Button btnTmKim = new Button(shlngKim, SWT.NONE);
		btnTmKim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					phongbanSelected = (PHONGBAN) combo_Phongban.getData(combo_Phongban.getText());
					FillTable(phongbanSelected, text_Search.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnTmKim.setImage(
				SWTResourceManager.getImage(LichDangkiem.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnTmKim.setText("T\u00ECm ki\u1EBFm");

		Button btnng = new Button(shlngKim, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected boolean getHoanthanhCongviec(DOT_THUCHIEN_DANGKIEM dtd) throws SQLException {
		boolean hoanthanh = true;
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dtd.getMA_TAPHOSO());
		if (ths == null)
			hoanthanh = false;
		else {
			ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
			if (vbl == null) {
			} else if (vbl.size() == 0) {
				hoanthanh = false;
			}
		}
		return hoanthanh;
	}

	private void init() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		ArrayList<PHONGBAN> pa = controler.getControl_PHONGBAN().getAllDonvi();
		f.setComboBox_DONVI_NOIBO(combo_Phongban, pa);
		combo_Phongban.add("TẤT CẢ", 0);
	}

	protected void FillTable(PHONGBAN phongbanSelected2, String text) throws SQLException {
		table.removeAll();
		ArrayList<PHUONGTIEN_GIAOTHONG> pgl = controler.getControl_PHUONGTIEN_GIAOTHONG().get_All_Oto(phongbanSelected2,
				text);
		int i = 1;
		for (PHUONGTIEN_GIAOTHONG phuongtien_GIAOTHONG : pgl) {
			CHUKY_DANGKIEM cd = controler.getControl_CHUKY_DANGKIEM()
					.get_CHUKY_DANGKIEM(phuongtien_GIAOTHONG.getMA_KYHAN_DANGKIEM());
			LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(phuongtien_GIAOTHONG.getMA_LOAI_XE());
			TableItem tbi = new TableItem(table, SWT.NONE);
			tbi.setText(new String[] { (i++) + "", phuongtien_GIAOTHONG.getBIENSO(), lx.getTEN_DONG_XE(),
					phuongtien_GIAOTHONG.getMA_PHUONGTIEN_GIAOTHONG() + "", "", cd == null ? "-" : cd.getTEN_KYHAN() });
			tbi.setData(phuongtien_GIAOTHONG);
			tbi.setData("lx", lx);
		}
	}
}
