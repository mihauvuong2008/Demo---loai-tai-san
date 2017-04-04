package View.AssetManagers.DanhMuc_NhomTaisan;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.TAISAN.Control_TAISAN;
import DAO.NGUOIDUNG;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class YesNoBox_ChuyenNhomTaisan extends Shell {
	private static String[] maTaisan;
	private static int mA_LoaiTaisan;
	private static NGUOIDUNG user;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			YesNoBox_ChuyenNhomTaisan shell = new YesNoBox_ChuyenNhomTaisan(display, maTaisan, mA_LoaiTaisan, user);
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
	 * @param mA_NhomTaisan
	 * @param maTaisan
	 * 
	 * @param display
	 */
	public YesNoBox_ChuyenNhomTaisan(Display display, String[] maTaisan, int mA_NhomTaisan, NGUOIDUNG user) {
		super(display, SWT.CLOSE | SWT.TITLE);
		setLayout(new GridLayout(3, false));
		YesNoBox_ChuyenNhomTaisan.maTaisan = maTaisan;
		YesNoBox_ChuyenNhomTaisan.mA_LoaiTaisan = mA_NhomTaisan;

		Label label = new Label(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.verticalIndent = 5;
		gd_label.horizontalIndent = 10;
		label.setLayoutData(gd_label);
		label.setImage(SWTResourceManager.getImage(YesNoBox_ChuyenNhomTaisan.class, "/Button-help-icon.png"));

		Label lblChuynNhmTi = new Label(this, SWT.NONE);
		GridData gd_lblChuynNhmTi = new GridData(SWT.LEFT, SWT.CENTER, false, true, 2, 1);
		gd_lblChuynNhmTi.verticalIndent = 8;
		gd_lblChuynNhmTi.horizontalIndent = 10;
		lblChuynNhmTi.setLayoutData(gd_lblChuynNhmTi);
		lblChuynNhmTi.setText("Chuy\u1EC3n Nh\u00F3m t\u00E0i s\u1EA3n?");
		new Label(this, SWT.NONE);

		Button btnDdoong = new Button(this, SWT.NONE);
		btnDdoong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Control_TAISAN ct = new Control_TAISAN(user);
				for (String mataisan : maTaisan) {
					int key = Integer.valueOf(mataisan);
					try {
						ct.Update_NhomTaisan(key, mA_NhomTaisan);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
		});
		GridData gd_btnDdoong = new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1);
		gd_btnDdoong.widthHint = 75;
		btnDdoong.setLayoutData(gd_btnDdoong);
		btnDdoong.setText("\u0110\u1ED3ng \u00FD");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Thay \u0111\u1ED5i nh\u00F3m t\u00E0i s\u1EA3n");
		setSize(320, 180);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
