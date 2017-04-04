package View.AssetManagers.Taikhoan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.ROLE.PrivilegeChecker;
import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.ROLE;
import View.Template.FormTemplate;

public class TaikhoanHienhanh extends Shell {
	private Text text_tentaikhoan;
	private Text text_tennguoidung;
	private Table table;
	private static NGUOIDUNG user;
	private Text text_Gioithieu;
	protected int Mode;
	private static boolean view_Mode; // true: view
	private Text txtHotngCa;
	private final Controler controler;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TaikhoanHienhanh shell = new TaikhoanHienhanh(display, user, view_Mode);
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
	 * @param b
	 * @throws SQLException
	 */
	public TaikhoanHienhanh(Display display, NGUOIDUNG user_, boolean view_Mode) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		controler = new Controler(user_);

		setImage(SWTResourceManager.getImage(TaikhoanHienhanh.class, "/Office-Customer-Male-Light-icon.png"));
		setLayout(new GridLayout(1, false));
		refreshUser(user_);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.horizontalSpacing = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);

		Label label = new Label(composite_1, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 105;
		label.setLayoutData(gd_label);
		label.setText("T\u00EAn t\u00E0i kho\u1EA3n:");

		text_tentaikhoan = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_tentaikhoan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_tentaikhoan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_tentaikhoan.setText(getUser().getTEN_TAI_KHOAN());

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng:");

		text_tennguoidung = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_tennguoidung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite_1, SWT.NONE);
		lblGiiThiu.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text_Gioithieu.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		text_Gioithieu.setEditable(false);

		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.horizontalSpacing = 0;
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		composite_2.setLayout(gl_composite_2);

		Label label_2 = new Label(composite_2, SWT.NONE);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_2.widthHint = 105;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("Danh s\u00E1ch quy\u1EC1n:");

		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(75);
		tableColumn.setText("M\u00C3 QUY\u1EC0N");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("T\u00CAN QUY\u1EC0N");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("M\u00D4 T\u1EA2");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(120);
		tableColumn_3.setText("Thêm người dùng");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("Phân quyền");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(150);
		tableColumn_5.setText("Xem T/Tin người dùng");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("Xóa người dùng");

		TableColumn tableColumn_61 = new TableColumn(table, SWT.CENTER);
		tableColumn_61.setWidth(100);
		tableColumn_61.setText("Thêm tài sản");

		TableColumn tableColumn_62 = new TableColumn(table, SWT.CENTER);
		tableColumn_62.setWidth(150);
		tableColumn_62.setText("Xem T/Tin tài sản");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(150);
		tableColumn_7.setText("Sửa T/Tin tài sản");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(150);
		tableColumn_8.setText("Xóa T/Tin tài sản");

		TableColumn tableColumn_9 = new TableColumn(table, SWT.CENTER);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("Thêm công việc");

		TableColumn tableColumn_10 = new TableColumn(table, SWT.CENTER);
		tableColumn_10.setWidth(150);
		tableColumn_10.setText("Xem T/Tin công việc");

		TableColumn tableColumn_11 = new TableColumn(table, SWT.CENTER);
		tableColumn_11.setWidth(150);
		tableColumn_11.setText("Sửa T/Tin công việc");

		TableColumn tableColumn_12 = new TableColumn(table, SWT.CENTER);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("Xóa công việc");

		TableColumn tableColumn_13 = new TableColumn(table, SWT.CENTER);
		tableColumn_13.setWidth(100);
		tableColumn_13.setText("Thêm hồ sơ");

		TableColumn tableColumn_14 = new TableColumn(table, SWT.CENTER);
		tableColumn_14.setWidth(100);
		tableColumn_14.setText("Xem T/Tin hồ sơ");

		TableColumn tableColumn_15 = new TableColumn(table, SWT.CENTER);
		tableColumn_15.setWidth(150);
		tableColumn_15.setText("Sửa T/Tin hồ sơ");

		TableColumn tableColumn_16 = new TableColumn(table, SWT.CENTER);
		tableColumn_16.setWidth(150);
		tableColumn_16.setText("Xóa T/Tin hồ sơ");

		viewDataRole(getUser().getRolelist(), table);
		sashForm_1.setWeights(new int[] { 618, 1000 });

		txtHotngCa = new Text(sashForm, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtHotngCa.setText("hoạt động của tôi");
		if (view_Mode)
			text_tennguoidung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		if (view_Mode)
			text_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));

		sashForm.setWeights(new int[] { 1000, 618 });

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.verticalSpacing = 0;
		composite.setLayout(gl_composite);

		Button btndoiMatKhau = new Button(composite, SWT.NONE);
		btndoiMatKhau.setImage(SWTResourceManager.getImage(TaikhoanHienhanh.class, "/secrecy-icon (1).png"));
		btndoiMatKhau.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DoiMatkhau d = new DoiMatkhau(getShell(), SWT.DIALOG_TRIM, user);
				d.open();
			}
		});
		btndoiMatKhau.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btndoiMatKhau.setText("Đổi mật khẩu");

		Button btnThaydoi = new Button(composite, SWT.NONE);
		btnThaydoi.setImage(SWTResourceManager.getImage(TaikhoanHienhanh.class, "/edit-validated-icon (1).png"));
		btnThaydoi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}

			private void setEditMode() {
				Mode = 1;
				EnableText();
			}

			private void EnableText() {
				text_tennguoidung.setEditable(true);
				text_Gioithieu.setEditable(true);
			}
		});
		btnThaydoi.setText("Thay đổi");

		Button btnLu = new Button(composite, SWT.NONE);
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setImage(SWTResourceManager.getImage(TaikhoanHienhanh.class, "/Actions-document-save-icon (1).png"));
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isEditMode()) {
					try {
						// do update information
						UpdateInfo_User();
						refreshUser(user);
						loadData();
						ThongBao_DoiThongtinThanhCong();
					} catch (Exception ex) {
						ex.printStackTrace();
						ThongBao_DoiThongtinKHONGThanhCong("");
					}
				}
				setComplete();
			}

			private void UpdateInfo_User() throws SQLException {
				controler.getControl_NGUOIDUNG().do_update_tenNguoidung(text_tennguoidung.getText());
				controler.getControl_NGUOIDUNG().do_update_gioiThieu(text_Gioithieu.getText());
			}

			private void ThongBao_DoiThongtinKHONGThanhCong(String string) {
				MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WORKING | SWT.OK);
				messageBox.setText("Không Thành công!");
				messageBox.setMessage("Đổi thông tin tài KHÔNG khoản thành công: " + string);
				messageBox.open();
			}

			private void ThongBao_DoiThongtinThanhCong() {
				MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WORKING | SWT.OK);
				messageBox.setText("Thành công!");
				messageBox.setMessage("Đổi thông tin tài khoản thành công");
				messageBox.open();
			}

			private boolean isEditMode() {
				if (Mode == 1)
					return true;
				return false;
			}

			private void setComplete() {
				text_tennguoidung.setEditable(false);
				text_Gioithieu.setEditable(false);
				Mode = 0;
			}
		});
		btnLu.setText("Lưu");

		Button btnng = new Button(composite, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		if (view_Mode)
			btndoiMatKhau.setVisible(false);
		if (view_Mode)
			btnThaydoi.setVisible(false);
		if (view_Mode)
			btnLu.setVisible(false);
		createContents();
		loadData();
	}

	private NGUOIDUNG getUser() {
		return user;
	}

	private void refreshUser(NGUOIDUNG user_) throws SQLException {
		if (controler.getControl_NGUOIDUNG() != null) {
			user = controler.getControl_NGUOIDUNG().get_info_user(user_.getTEN_TAI_KHOAN());
			user.setConn(controler.getControl_NGUOIDUNG().connect());
			user.setPrivilegeChecker(new PrivilegeChecker(user));
		}
	}

	private void loadData() {
		text_tennguoidung.setText(user.getTEN_CAN_BO());
		text_Gioithieu.setText(user.getGIOI_THIEU());
	}

	private void viewDataRole(ArrayList<ROLE> quyen, Table table) {
		if (quyen != null)
			for (ROLE r : quyen) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0, String.valueOf(r.getMA_QUYEN()));
				item.setText(1, r.getTEN_QUYEN());
				item.setText(2, r.getMO_TA());
				item.setText(3, String.valueOf(r.getTHEM_NGUOIDUNG()));
				item.setText(4, String.valueOf(r.getPHAN_QUYEN_NGUOIDUNG()));
				item.setText(5, String.valueOf(r.getXEM_THONGTIN_NGUOIDUNG()));
				item.setText(6, String.valueOf(r.getXOA_NGUOIDUNG()));
				item.setText(7, String.valueOf(r.getTHEM_THONGTIN_TAISAN()));
				item.setText(8, String.valueOf(r.getXEM_THONGTIN_TAISAN()));
				item.setText(9, String.valueOf(r.getSUA_THONGTIN_TAISAN()));
				item.setText(10, String.valueOf(r.getXOA_THONGTIN_TAISAN()));
				item.setText(11, String.valueOf(r.getTHEM_CONGVIEC()));
				item.setText(12, String.valueOf(r.getXEM_THONGTIN_CONGVIEC()));
				item.setText(13, String.valueOf(r.getSUA_THONGTIN_CONGVIEC()));
				item.setText(14, String.valueOf(r.getXOA_CONGVIEC()));
				item.setText(15, String.valueOf(r.getTHEM_HOSO()));
				item.setText(16, String.valueOf(r.getXEM_THONGTIN_HOSO()));
				item.setText(17, String.valueOf(r.getSUA_THONGTIN_HOSO()));
				item.setText(18, String.valueOf(r.getXOA_HOSO()));
			}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setSize(745, 460);
		new FormTemplate().setCenterScreen(getShell());
		setText("Tài khoản");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
