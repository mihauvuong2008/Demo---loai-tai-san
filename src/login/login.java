package login;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Control.NGUOIDUNG.Control_NGUOIDUNG;
import Control.ROLE.PrivilegeChecker;
import DAO.NGUOIDUNG;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class login {

	protected Shell shlDangNhap;
	private Text Text_User;
	private Text text_Password;

	public login() {
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			login window = new login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();

		// center screen
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlDangNhap.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlDangNhap.setLocation(x, y);

		shlDangNhap.open();
		shlDangNhap.layout();
		while (!shlDangNhap.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDangNhap = new Shell(shlDangNhap, (SWT.SHELL_TRIM & (~SWT.RESIZE) & (SWT.TITLE | SWT.CLOSE | SWT.MIN)));
		shlDangNhap.setImage(SWTResourceManager.getImage(login.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlDangNhap.setSize(426, 210);
		shlDangNhap.setText("\u0110\u0103ng nh\u1EADp");
		shlDangNhap.setLayout(new GridLayout(3, false));

		Label lblTiKhon = new Label(shlDangNhap, SWT.NONE);
		GridData gd_lblTiKhon = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTiKhon.verticalIndent = 35;
		gd_lblTiKhon.horizontalIndent = 75;
		lblTiKhon.setLayoutData(gd_lblTiKhon);
		lblTiKhon.setText("T\u00E0i kho\u1EA3n:");

		Text_User = new Text(shlDangNhap, SWT.BORDER);
		GridData gd_Text_User = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_Text_User.verticalIndent = 35;
		Text_User.setLayoutData(gd_Text_User);

		Label lblMtKhu = new Label(shlDangNhap, SWT.NONE);
		GridData gd_lblMtKhu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblMtKhu.horizontalIndent = 75;
		lblMtKhu.setLayoutData(gd_lblMtKhu);
		lblMtKhu.setText("M\u1EADt kh\u1EA9u:");

		text_Password = new Text(shlDangNhap, SWT.BORDER | SWT.PASSWORD);
		text_Password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				switch (event.keyCode) {
				case SWT.CR:
					try {
						DoLogin();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case SWT.ESC:
					break;
				}
			}
		});
		text_Password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		new Label(shlDangNhap, SWT.NONE);

		Button btnGhiNh = new Button(shlDangNhap, SWT.CHECK);
		btnGhiNh.setText("Ghi nh\u1EDB");
		new Label(shlDangNhap, SWT.NONE);
		new Label(shlDangNhap, SWT.NONE);

		Button btnngNhp = new Button(shlDangNhap, SWT.NONE);
		GridData gd_btnDangNhap = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDangNhap.widthHint = 80;
		btnngNhp.setLayoutData(gd_btnDangNhap);
		btnngNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DoLogin();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnngNhp.setText("\u0110\u0103ng nh\u1EADp");

		Button btnThot = new Button(shlDangNhap, SWT.NONE);
		GridData gd_btnThot = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThot.horizontalIndent = 30;
		gd_btnThot.widthHint = 80;
		btnThot.setLayoutData(gd_btnThot);
		btnThot.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnThot.setText("Tho\u00E1t");

	}

	protected void DoLogin() throws SQLException {
		NGUOIDUNG u = new NGUOIDUNG();
		u.setTEN_TAI_KHOAN(Text_User.getText());
		u.setMAT_KHAU(text_Password.getText());

		Control_NGUOIDUNG nd = new Control_NGUOIDUNG(u);
		Connection Connection = nd.connect();
		if (Connection != null) {
			// dang nhap thanh cong
			u.setConn(Connection);
			nd = new Control_NGUOIDUNG(u);
			u = nd.get_Login_userData(u.getTEN_TAI_KHOAN());
			u.setConn(Connection);
			u.setPrivilegeChecker(getPrivilegeChecker(u));
			shlDangNhap.dispose();
			circumstance c = new circumstance(u);
			c.ViewFormAfterLogin();
		} else {
			// mật khẩu không khớp
			MessageBox m = new MessageBox(shlDangNhap, SWT.ICON_INFORMATION);
			m.setText("Đăng nhập thất bại!");
			m.setMessage("Lỗi tài khoản hoặc mật khẩu");
			m.open();
		}
	}

	private PrivilegeChecker getPrivilegeChecker(NGUOIDUNG u) {
		return new PrivilegeChecker(u);
	}
}
