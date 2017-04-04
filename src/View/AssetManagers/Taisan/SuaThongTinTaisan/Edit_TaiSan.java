package View.AssetManagers.Taisan.SuaThongTinTaisan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import DAO.PHONGBAN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.DateTime;

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
	private Combo combo_Donvitinh;
	private Combo combo_Tinhtrang;
	private DateTime dateTime;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;

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
		setText("Thông tin phương tiện tài sản: " + MA_TAISAN);
		setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/icons/full/message_warning.png"));
		setLayout(new GridLayout(1, false));
		Edit_TaiSan.user = user;
		Edit_TaiSan.MA_TAISAN = MA_TAISAN;
		controler = new Controler(user);

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.horizontalSpacing = 0;
		composite_2.setLayout(gl_composite_2);

		Group group_DinhdanhTaiSan = new Group(composite_2, SWT.NONE);
		group_DinhdanhTaiSan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		group_DinhdanhTaiSan.setText("\u0110\u1ECBnh danh t\u00E0i s\u1EA3n");
		group_DinhdanhTaiSan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		group_DinhdanhTaiSan.setLayout(new GridLayout(3, false));

		Label label_12 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_12.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		label_12.setText("Model*:");

		combo_Model = new Combo(group_DinhdanhTaiSan, SWT.NONE);
		combo_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_13 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_13.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		label_13.setText("Seri*:");

		text_Seri = new Text(group_DinhdanhTaiSan, SWT.BORDER);
		text_Seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_14 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_14.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		label_14.setText("Ng\u00E0y s\u1EED d\u1EE5ng*:");

		dateTime = new DateTime(group_DinhdanhTaiSan, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label label_15 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_15.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		label_15.setText("Nguy\u00EAn gi\u00E1:");

		text_Nguyengia = new Text(group_DinhdanhTaiSan, SWT.BORDER);
		GridData gd_text_Nguyengia = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_Nguyengia.widthHint = 150;
		text_Nguyengia.setLayoutData(gd_text_Nguyengia);

		Label label_16 = new Label(group_DinhdanhTaiSan, SWT.NONE);
		label_16.setText("Xu\u1EA5t x\u1EE9:");

		combo_Xuatxu = new Combo(group_DinhdanhTaiSan, SWT.NONE);
		GridData gd_combo_Xuatxu = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_combo_Xuatxu.widthHint = 150;
		combo_Xuatxu.setLayoutData(gd_combo_Xuatxu);

		Label lblLoiTiSn = new Label(group_DinhdanhTaiSan, SWT.NONE);
		lblLoiTiSn.setText("Nhóm tài sản*:");

		Combo combo = new Combo(group_DinhdanhTaiSan, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 185;
		combo.setLayoutData(gd_combo);

		Button button_2 = new Button(group_DinhdanhTaiSan, SWT.NONE);
		button_2.setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/Actions-tab-new-icon.png"));

		Group grpcimTi = new Group(composite_2, SWT.NONE);
		grpcimTi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpcimTi.setLayout(new GridLayout(2, false));
		grpcimTi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpcimTi.setText("\u0110\u1EB7c \u0111i\u1EC3m t\u00E0i s\u1EA3n");

		Label lblTnMT = new Label(grpcimTi, SWT.NONE);
		GridData gd_lblTnMT = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTnMT.verticalIndent = 3;
		lblTnMT.setLayoutData(gd_lblTnMT);
		lblTnMT.setText("T\u00EAn, m\u00F4 t\u1EA3*:");

		text_Tentaisan = new Text(grpcimTi, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

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
		combo_Tinhtrang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		Group grpLinKt = new Group(composite_1, SWT.NONE);
		grpLinKt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpLinKt.setText("Phụ kiện");
		grpLinKt.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpLinKt.setLayout(new GridLayout(1, false));

		table_Phukien = new Table(grpLinKt, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table_Phukien = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table_Phukien.heightHint = 132;
		table_Phukien.setLayoutData(gd_table_Phukien);
		table_Phukien.setHeaderVisible(true);
		table_Phukien.setLinesVisible(true);

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

		Group grpKhc = new Group(composite_1, SWT.NONE);
		grpKhc.setLayout(new GridLayout(3, false));
		grpKhc.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		grpKhc.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpKhc.setText("Thông tin khác");

		Label lblHThngTi = new Label(grpKhc, SWT.NONE);
		lblHThngTi.setText("H\u1EC7 th\u1ED1ng:");

		Combo combo_TenHethong = new Combo(grpKhc, SWT.READ_ONLY);
		GridData gd_combo_TenHethong = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_TenHethong.widthHint = 383;
		combo_TenHethong.setLayoutData(gd_combo_TenHethong);

		Button btnQuanlyHethong = new Button(grpKhc, SWT.NONE);
		btnQuanlyHethong.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnQuanlyHethong.setImage(SWTResourceManager.getImage(Edit_TaiSan.class, "/Actions-tab-new-icon.png"));

		Composite composite_4 = new Composite(grpKhc, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(4, false);
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label label_19 = new Label(composite_4, SWT.NONE);
		label_19.setText("\u0110\u01A1n v\u1ECB t\u00EDnh:");

		combo_Donvitinh = new Combo(composite_4, SWT.NONE);
		combo_Donvitinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_20 = new Label(composite_4, SWT.NONE);
		label_20.setText("B\u1EA3o h\u00E0nh:");

		text_Baohanh = new Text(composite_4, SWT.BORDER);
		text_Baohanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpThngTinTi = new Group(composite_1, SWT.NONE);
		grpThngTinTi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpThngTinTi.setText("Ghi chú");
		grpThngTinTi.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpThngTinTi.setLayout(new GridLayout(1, false));

		text_Ghichu = new Text(grpThngTinTi, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Ghichu.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		GridData gd_text_Ghichu = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2);
		gd_text_Ghichu.heightHint = 70;
		text_Ghichu.setLayoutData(gd_text_Ghichu);

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_3.heightHint = 45;
		composite_3.setLayoutData(gd_composite_3);
		composite_3.setLayout(new GridLayout(4, false));

		Button btnLu = new Button(composite_3, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					update_Taisan();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_btnLu.widthHint = 65;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Cập nhật");

		Button button_6 = new Button(composite_3, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_button_6 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		gd_button_6.widthHint = 65;
		button_6.setLayoutData(gd_button_6);
		button_6.setText("Nh\u1EADp l\u1EA1i");

		Button btnDanhMc = new Button(composite_3, SWT.NONE);
		btnDanhMc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnDanhMc = new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1);
		gd_btnDanhMc.widthHint = 65;
		btnDanhMc.setLayoutData(gd_btnDanhMc);
		btnDanhMc.setText("Danh m\u1EE5c");

		Button button_7 = new Button(composite_3, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_button_7 = new GridData(SWT.RIGHT, SWT.FILL, true, true, 1, 1);
		gd_button_7.widthHint = 65;
		button_7.setLayoutData(gd_button_7);
		button_7.setText("\u0110\u00F3ng");
		sashForm.setWeights(new int[] { 6, 8 });
		createContents();
		init();
	}

	private void init() throws SQLException {
		fillData_Taisan();
	}

	protected void createContents() {
		setSize(810, 500);
		new FormTemplate().setCenterScreen(getShell());
	}

	protected void update_Taisan() throws SQLException {
		// String Model = combo_Model.getText();
		// String Seri = text_Seri.getText();
		// String[] date = text_datetime.getText().split("/");
		// String Ngay_Su_Dung = date[2] + "-" + date[1] + "-" + date[0];
		// String Xuat_xu = combo_Xuatxu.getText();
		// String Bao_hanh = text_Baohanh.getText();
		// int Tinh_Trang = (int) combo_Tinhtrang.getData();
		// String Don_Vi_Tinh = combo_Donvitinh.getText();
		// String Nguyen_Gia = text_Nguyengia.getText();
		// String Ghi_Chu = text_Ghichu.getText();
		// String Ma_PhanNhom_Tai_San = "001";
		// int Ma_Dvi_Sudung = ((PHONGBAN)
		// combo_Donvisudung.getData(combo_Donvisudung.getText())).getMA_PHONGBAN();
		// int Ma_Dvi_Quanly = ((PHONGBAN)
		// combo_Donviquanly.getData(combo_Donviquanly.getText())).getMA_PHONGBAN();
		TAISAN t = new TAISAN();
		// t.setMA_TAISAN(MA_TAISAN);
		t.setTEN_TAISAN(text_Tentaisan.getText());

		controler.getControl_TAISAN().Update_Taisan(t);
	}

	private void fillData_Taisan() throws SQLException {
		TAISAN t = controler.getControl_TAISAN().get_Taisan(MA_TAISAN);
		String Model_item[] = controler.getControl_TAISAN().get_RandomTaisan("Model", 100);
		for (String string : Model_item) {
			System.out.println(string);
		}
		combo_Model.setItems(Model_item);
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
		combo_Xuatxu.setText(t.getXUAT_XU());
		text_Tentaisan.setText(t.getTEN_TAISAN());

		// String Donvisudung_item[] = cdt.get_RandomTaisan("Xuat_xu", 100);
		// combo_Donvisudung.setItems(items);
		ArrayList<PHONGBAN> dv = controler.getControl_PHONGBAN().getAllDonvi();

		combo_Donvisudung.add(t.getDonvi_Sudung().getTEN_PHONGBAN());
		combo_Donvisudung.setData(t.getDonvi_Sudung().getTEN_PHONGBAN(), t.getDonvi_Sudung());
		combo_Donvisudung.setText(t.getDonvi_Sudung().getTEN_PHONGBAN());
		for (PHONGBAN d_ : dv) {
			combo_Donvisudung.add(d_.getTEN_PHONGBAN());
			combo_Donvisudung.setData(d_.getTEN_PHONGBAN(), d_);
		}

		combo_Donviquanly.add(t.getDonvi_Quanly().getTEN_PHONGBAN());
		combo_Donviquanly.setData(t.getDonvi_Quanly().getTEN_PHONGBAN(), t.getDonvi_Quanly());
		combo_Donviquanly.setText(t.getDonvi_Quanly().getTEN_PHONGBAN());
		for (PHONGBAN d_ : dv) {
			combo_Donviquanly.add(d_.getTEN_PHONGBAN());
			combo_Donviquanly.setData(d_.getTEN_PHONGBAN(), d_);
		}

		combo_Donvitinh.setText(t.getDON_VI_TINH());
		text_Baohanh.setText(t.getBAO_HANH());
		text_Ghichu.setText(t.getGHI_CHU());

		DOT_THUCHIEN_TANG_TAISAN dtt_last = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_DotTangTaisan_Gannhat(MA_TAISAN);

		@SuppressWarnings("unused")
		NGUONTANG nt = null;
		if (dtt_last != null) {
			nt = controler.getControl_NGUONTANG().get_NguonTangTaisan(dtt_last.getMA_NGUONTANG());
		}

		Fill_ItemData i = new Fill_ItemData();
		i.setComboBox_TINHTRANH_TAISAN(combo_Tinhtrang);
		combo_Tinhtrang.select(t.getTINH_TRANG() - 1);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
