package View.AssetManagers.Taisan.SuaThongTinTaisan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.TAISAN;
import View.AssetManagers.AppMessage.DefaultBoxMessage;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class Edit_TaiSan extends Shell {
	private Text text_Seri;
	private Text text_Nguyengia;
	private Text text_Tentaisan;
	private Text text_Ghichu;
	private Text text_Baohanh;
	private Table table_Phukien;
	private static NGUOIDUNG user;
	private static Integer MA_TAISAN;
	private Combo combo_Donvisudung;
	private Combo combo_Donviquanly;
	private Combo combo_Model;
	private Combo combo_Xuatxu;
	private Combo combo_Tinhtrang;
	private DateTime dateTime;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private Text text_Malienket;
	private Text text_Soluong;
	private Text text_Donvitinh;
	private Combo combo_Trangthai;
	protected ArrayList<PHUKIEN> deleteList;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Edit_TaiSan shell = new Edit_TaiSan(display, user, MA_TAISAN);
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
	 * Create contents of the window.
	 * 
	 * @throws SQLException
	 */
	public Edit_TaiSan(Display display, NGUOIDUNG user, Integer MA_TAISAN) throws SQLException {
		deleteList = new ArrayList<>();
		setText("Thông tin phương tiện tài sản: " + MA_TAISAN);
		setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/edit-validated-icon.png"));
		Edit_TaiSan.user = user;
		Edit_TaiSan.MA_TAISAN = MA_TAISAN;
		controler = new Controler(user);
		setLayout(new GridLayout(1, false));

		Group group_DinhdanhTaiSan = new Group(this, SWT.NONE);
		GridData gd_group_DinhdanhTaiSan = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_group_DinhdanhTaiSan.heightHint = 190;
		group_DinhdanhTaiSan.setLayoutData(gd_group_DinhdanhTaiSan);
		group_DinhdanhTaiSan.setText("\u0110\u1ECBnh danh t\u00E0i s\u1EA3n");
		group_DinhdanhTaiSan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		group_DinhdanhTaiSan.setLayout(new GridLayout(2, false));

		Label label_12 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_12.setText("Model*:");

		combo_Model = new Combo(group_DinhdanhTaiSan, SWT.NONE);
		combo_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_13 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_13.setText("Seri*:");

		text_Seri = new Text(group_DinhdanhTaiSan, SWT.BORDER);
		text_Seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_14 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_14.setText("Ng\u00E0y s\u1EED d\u1EE5ng*:");

		dateTime = new DateTime(group_DinhdanhTaiSan, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_15 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_15.setText("Nguy\u00EAn gi\u00E1:");

		text_Nguyengia = new Text(group_DinhdanhTaiSan, SWT.BORDER);
		text_Nguyengia.addVerifyListener(new VerifyListener() {
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
		GridData gd_text_Nguyengia = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_Nguyengia.widthHint = 150;
		text_Nguyengia.setLayoutData(gd_text_Nguyengia);

		Label label_16 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_16.setText("Xu\u1EA5t x\u1EE9:");

		combo_Xuatxu = new Combo(group_DinhdanhTaiSan, SWT.NONE);
		GridData gd_combo_Xuatxu = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_Xuatxu.widthHint = 150;
		combo_Xuatxu.setLayoutData(gd_combo_Xuatxu);

		Label lblTnMT = new Label(group_DinhdanhTaiSan, SWT.NONE);
		GridData gd_lblTnMT = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTnMT.verticalIndent = 3;
		lblTnMT.setLayoutData(gd_lblTnMT);
		lblTnMT.setText("T\u00EAn, m\u00F4 t\u1EA3*:");

		text_Tentaisan = new Text(group_DinhdanhTaiSan, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group grpcimTi = new Group(this, SWT.NONE);
		GridData gd_grpcimTi = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpcimTi.heightHint = 260;
		grpcimTi.setLayoutData(gd_grpcimTi);
		grpcimTi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpcimTi.setLayout(new GridLayout(2, false));
		grpcimTi.setText("\u0110\u1EB7c \u0111i\u1EC3m t\u00E0i s\u1EA3n");

		Label label_5 = new Label(grpcimTi, SWT.NONE);
		GridData gd_label_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_5.widthHint = 85;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("\u0110\u01A1n v\u1ECB s\u1EED d\u1EE5ng:");

		combo_Donvisudung = new Combo(grpcimTi, SWT.READ_ONLY);
		GridData gd_combo_Donvisudung = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Donvisudung.widthHint = 180;
		combo_Donvisudung.setLayoutData(gd_combo_Donvisudung);

		Label label_6 = new Label(grpcimTi, SWT.NONE);
		label_6.setText("\u0110\u01A1n v\u1ECB qu\u1EA3n l\u00FD:");

		combo_Donviquanly = new Combo(grpcimTi, SWT.READ_ONLY);
		GridData gd_combo_Donviquanly = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Donviquanly.widthHint = 180;
		combo_Donviquanly.setLayoutData(gd_combo_Donviquanly);

		Label lblTnhTrng = new Label(grpcimTi, SWT.NONE);
		lblTnhTrng.setText("T\u00ECnh tr\u1EA1ng:");

		combo_Tinhtrang = new Combo(grpcimTi, SWT.READ_ONLY);
		combo_Tinhtrang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTrngThi = new Label(grpcimTi, SWT.NONE);
		lblTrngThi.setText("Trạng thái:");

		combo_Trangthai = new Combo(grpcimTi, SWT.READ_ONLY);
		combo_Trangthai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_Trangthai.select(-1);

		Label label_19 = new Label(grpcimTi, SWT.NONE);
		label_19.setText("\u0110\u01A1n v\u1ECB t\u00EDnh:");

		text_Donvitinh = new Text(grpcimTi, SWT.BORDER);
		text_Donvitinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_20 = new Label(grpcimTi, SWT.NONE);
		label_20.setText("B\u1EA3o h\u00E0nh:");

		text_Baohanh = new Text(grpcimTi, SWT.BORDER);
		text_Baohanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMLinKt = new Label(grpcimTi, SWT.NONE);
		lblMLinKt.setText("Mã liên kết:");

		text_Malienket = new Text(grpcimTi, SWT.BORDER);
		text_Malienket.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSLng = new Label(grpcimTi, SWT.NONE);
		lblSLng.setText("Số lượng:");

		text_Soluong = new Text(grpcimTi, SWT.BORDER);
		text_Soluong.addVerifyListener(new VerifyListener() {
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
		text_Soluong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGhiCh = new Label(grpcimTi, SWT.NONE);
		GridData gd_lblGhiCh = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGhiCh.verticalIndent = 3;
		lblGhiCh.setLayoutData(gd_lblGhiCh);
		lblGhiCh.setText("Ghi chú: ");

		text_Ghichu = new Text(grpcimTi, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text_Ghichu.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));

		table_Phukien = new Table(grpcimTi, SWT.BORDER | SWT.FULL_SELECTION);
		table_Phukien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_Phukien.setHeaderVisible(true);
		table_Phukien.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table_Phukien, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTe = new TableColumn(table_Phukien, SWT.NONE);
		tblclmnTe.setWidth(100);
		tblclmnTe.setText("TÊN PHỤ KIỆN");

		TableColumn tblclmnModel = new TableColumn(table_Phukien, SWT.NONE);
		tblclmnModel.setWidth(100);
		tblclmnModel.setText("MODEL");

		TableColumn tblclmnSeri = new TableColumn(table_Phukien, SWT.NONE);
		tblclmnSeri.setWidth(100);
		tblclmnSeri.setText("SERI");

		TableColumn tblclmnNguynGi = new TableColumn(table_Phukien, SWT.NONE);
		tblclmnNguynGi.setWidth(100);
		tblclmnNguynGi.setText("NGUYÊN GIÁ");

		Menu menu = new Menu(table_Phukien);
		table_Phukien.setMenu(menu);

		MenuItem mntmThm = new MenuItem(menu, SWT.NONE);
		mntmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Box_Phukien bp = new Box_Phukien(getShell(), SWT.DIALOG_TRIM, MA_TAISAN);
				bp.open();
				PHUKIEN rs = (PHUKIEN) bp.result;
				if (rs != null) {
					TableItem ti = new TableItem(table_Phukien, SWT.NONE);
					ti.setText(new String[] { "", rs.getTEN_PHUKIEN(), rs.getMODEL(), rs.getSERI(),
							rs.getNGUYEN_GIA() + "" });
					ti.setData(rs);
				}
			}
		});
		mntmThm.setText("Thêm");

		MenuItem mntmS = new MenuItem(menu, SWT.NONE);
		mntmS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table_Phukien.getSelection();
				if (ti.length > 0) {
					PHUKIEN data = (PHUKIEN) ti[0].getData();
					Box_Phukien bp = new Box_Phukien(getShell(), SWT.DIALOG_TRIM, data);
					bp.open();
					ti[0].setData(bp.result);
				}
			}
		});
		mntmS.setText("Thay đổi thông tin");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table_Phukien.getSelection();
				if (ti.length > 0) {
					PHUKIEN data = (PHUKIEN) ti[0].getData();
					deleteList.add(data);
					ti[0].dispose();
				}
			}
		});
		mntmXa.setText("Xóa");

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_3 = new GridLayout(3, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);

		Button btnLu = new Button(composite_3, SWT.NONE);
		btnLu.setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/Accept-icon (1).png"));
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					update_Taisan();
					updatePhukien();
					init();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void updatePhukien() throws SQLException {
				for (TableItem ti : table_Phukien.getItems()) {
					PHUKIEN phukien = (PHUKIEN) ti.getData();
					if (phukien.getMA_PHUKIEN() == 0) {
						controler.getControl_TAISAN().insert_PHUKIEN(phukien);
					} else {
						controler.getControl_TAISAN().Update_Phukien(phukien);
					}
				}
				for (PHUKIEN phukien : deleteList) {
					controler.getControl_TAISAN().delete_PHUKIEN(phukien);
				}
			}
		});
		btnLu.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		btnLu.setText("Hoàn tất");

		Button button_6 = new Button(composite_3, SWT.NONE);
		button_6.setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/refresh-icon.png"));
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					init();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_6.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		button_6.setText("Nh\u1EADp l\u1EA1i");

		Button button_7 = new Button(composite_3, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_button_7 = new GridData(SWT.RIGHT, SWT.FILL, true, true, 1, 1);
		gd_button_7.widthHint = 75;
		button_7.setLayoutData(gd_button_7);
		button_7.setText("\u0110\u00F3ng");
		createContents();
	}

	protected void createContents() throws SQLException {
		setSize(456, 658);
		new FormTemplate().setCenterScreen(getShell());
		init();
	}

	private void init() throws SQLException {
		fillData_Taisan();
	}

	protected void update_Taisan() throws SQLException {
		TAISAN t = getTaisan();
		controler.getControl_TAISAN().Update_Taisan(t);
	}

	private TAISAN getTaisan() throws SQLException {
		TAISAN t = controler.getControl_TAISAN().get_Taisan(MA_TAISAN);
		try {
			t.setMA_TAISAN(MA_TAISAN);
			t.setTEN_TAISAN(text_Tentaisan.getText());
			t.setMODEL(combo_Model.getText());
			t.setSERI(text_Seri.getText());
			t.setNGAY_SU_DUNG(new MyDateFormat().getDate(dateTime));
			t.setXUAT_XU(combo_Xuatxu.getText());
			t.setBAO_HANH(text_Baohanh.getText());
			t.setTINH_TRANG((int) combo_Tinhtrang.getData(combo_Tinhtrang.getText()));
			t.setTRANG_THAI((int) combo_Trangthai.getData(combo_Trangthai.getText()));
			t.setDON_VI_TINH(text_Donvitinh.getText());
			t.setSOLUONG(Integer.valueOf(text_Soluong.getText()));
			t.setNGUYEN_GIA(Integer.valueOf(text_Nguyengia.getText()));
			t.setGHI_CHU(text_Ghichu.getText());
			t.setMA_TANSAN_KETOAN(text_Malienket.getText());
			t.setMA_DON_VI_QUAN_LY(
					((PHONGBAN) combo_Donviquanly.getData(combo_Donviquanly.getText())).getMA_PHONGBAN());
			t.setMA_DON_VI_SU_DUNG(
					((PHONGBAN) combo_Donvisudung.getData(combo_Donvisudung.getText())).getMA_PHONGBAN());
		} catch (Exception e) {
			DefaultBoxMessage bd = new DefaultBoxMessage();
			bd.Notification("Lỗi", "Kiểm tra lại thông tin cập nhật");
		}
		return t;
	}

	private void fillData_Taisan() throws SQLException {
		TAISAN t = controler.getControl_TAISAN().get_Taisan(MA_TAISAN);
		String Model_item[] = controler.getControl_TAISAN().get_RandomTaisan("Model", 100);
		for (String s : Model_item) {
			if (s != null) {
				combo_Model.add(s);
			}
		}
		combo_Model.setText(t.getMODEL());
		text_Seri.setText(t.getSERI());
		dateTime.setDay(mdf.getDay(t.getNGAY_SU_DUNG()));
		dateTime.setMonth(mdf.getMonth(t.getNGAY_SU_DUNG()));
		dateTime.setYear(mdf.getYear(t.getNGAY_SU_DUNG()));
		text_Nguyengia.setText(String.valueOf(t.getNGUYEN_GIA()));
		String Xuatxu_item[] = controler.getControl_TAISAN().get_RandomTaisan("Xuat_xu", 100);
		for (String s : Xuatxu_item) {
			if (s != null) {
				combo_Xuatxu.add(s);
			}
		}
		combo_Xuatxu.setText(t.getXUAT_XU().equals("null") ? "" : t.getXUAT_XU());
		text_Tentaisan.setText(t.getTEN_TAISAN());
		// String Donvisudung_item[] = cdt.get_RandomTaisan("Xuat_xu", 100);
		// combo_Donvisudung.setItems(items);
		ArrayList<PHONGBAN> dv = controler.getControl_PHONGBAN().getAllDonvi();

		Fill_ItemData fid = new Fill_ItemData();
		fid.setComboBox_DONVI_NOIBO(combo_Donviquanly, dv);
		fid.setComboBox_DONVI_NOIBO(combo_Donvisudung, dv);
		fid.setComboBox_TINHTRANH_TAISAN(combo_Tinhtrang);
		combo_Tinhtrang.select(t.getTINH_TRANG() - 1);
		fid.setComboBox_TRANGTHAI_TAISAN(combo_Trangthai);

		combo_Trangthai.select(t.getTRANG_THAI() - 1);
		text_Donvitinh.setText(t.getDON_VI_TINH());
		text_Baohanh.setText(t.getBAO_HANH());
		text_Malienket.setText(t.getMA_TANSAN_KETOAN() == null ? "" : t.getMA_TANSAN_KETOAN());
		text_Soluong.setText(t.getSOLUONG() + "");
		text_Ghichu.setText(t.getGHI_CHU());
		// DOT_THUCHIEN_TANG_TAISAN dtt_last =
		// controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
		// .get_DotTangTaisan_Gannhat(MA_TAISAN);
		// NGUONTANG nt = null;
		// if (dtt_last != null) {
		// nt =
		// controler.getControl_NGUONTANG().get_NguonTangTaisan(dtt_last.getMA_NGUONTANG());
		// }
		table_Phukien.removeAll();
		ArrayList<PHUKIEN> pkl = controler.getControl_TAISAN().get_DataPhuKien(t);
		int i = 1;
		for (PHUKIEN phukien : pkl) {
			TableItem ti = new TableItem(table_Phukien, SWT.NONE);
			ti.setText(new String[] { (i++) + "", phukien.getTEN_PHUKIEN(), phukien.getMODEL(), phukien.getSERI(),
					phukien.getNGUYEN_GIA() + "" });
			ti.setData(phukien);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
