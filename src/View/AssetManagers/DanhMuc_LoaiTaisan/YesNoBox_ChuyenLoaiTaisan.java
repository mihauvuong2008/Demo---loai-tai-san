package View.AssetManagers.DanhMuc_LoaiTaisan;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.TAISAN.Control_TAISAN;
import DAO.NGUOIDUNG;
import View.AssetManagers.DanhMuc_NhomTaisan.YesNoBox_ChuyenNhomTaisan;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;

public class YesNoBox_ChuyenLoaiTaisan extends Shell {

	private static String[] maTaisan;
	private static int mA_NhomTaisan;
	private static NGUOIDUNG user;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			YesNoBox_ChuyenLoaiTaisan shell = new YesNoBox_ChuyenLoaiTaisan(display, maTaisan, mA_NhomTaisan, user);
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
	 */
	public YesNoBox_ChuyenLoaiTaisan(Display display, String[] maTaisan, int mA_NhomTaisan, NGUOIDUNG user) {
		super(display, SWT.SHELL_TRIM);
		YesNoBox_ChuyenLoaiTaisan.maTaisan = maTaisan;
		YesNoBox_ChuyenLoaiTaisan.mA_NhomTaisan = mA_NhomTaisan;
		setLayout(new GridLayout(4, false));

		Label label = new Label(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.verticalIndent = 5;
		gd_label.horizontalIndent = 10;
		label.setLayoutData(gd_label);
		label.setImage(SWTResourceManager.getImage(YesNoBox_ChuyenNhomTaisan.class, "/Button-help-icon.png"));
		GridData gd_lblChuynNhmTi = new GridData(SWT.LEFT, SWT.CENTER, false, true, 2, 1);
		gd_lblChuynNhmTi.verticalIndent = 8;
		gd_lblChuynNhmTi.horizontalIndent = 10;
		GridData gd_btnDdoong = new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1);
		gd_btnDdoong.widthHint = 75;

		Label lblChuynLoiTi = new Label(this, SWT.NONE);
		GridData gd_lblChuynLoiTi = new GridData(SWT.LEFT, SWT.CENTER, true, true, 2, 1);
		gd_lblChuynLoiTi.verticalIndent = 8;
		gd_lblChuynLoiTi.horizontalIndent = 10;
		lblChuynLoiTi.setLayoutData(gd_lblChuynLoiTi);
		lblChuynLoiTi.setText("Chuy\u1EC3n Lo\u1EA1i t\u00E0i s\u1EA3n?");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Button btnDdoong = new Button(this, SWT.NONE);
		btnDdoong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Control_TAISAN ct = new Control_TAISAN(user);
					for (String mataisan : maTaisan) {
						int key = Integer.valueOf(mataisan);
						ct.Update_LoaiTaisan(key, mA_NhomTaisan);
					}
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDdoong.setLayoutData(gd_btnDdoong);
		btnDdoong.setText("\u0110\u1ED3ng \u00FD");

		Button btnng = new Button(this, SWT.NONE);
		GridData gd_btnng_1 = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng_1.widthHint = 75;
		btnng.setLayoutData(gd_btnng_1);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		btnng.setLayoutData(gd_btnng_1);
		btnng.setText("\u0110\u00F3ng");
		gd_btnng_1.widthHint = 75;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Thay \u0111\u1ED5i lo\u1EA1i t\u00E0i s\u1EA3n");
		setSize(320, 180);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
