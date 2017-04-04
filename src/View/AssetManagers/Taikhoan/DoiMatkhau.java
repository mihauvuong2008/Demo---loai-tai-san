package View.AssetManagers.Taikhoan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DoiMatkhau extends Dialog {

	protected Object result;
	protected Shell shlThayiMt;
	private Text text_Matkhaucu;
	private Text text_Matkhaumoi;
	private Text text_Nhaplai;
	private static NGUOIDUNG user;
	private Group grpTnNgiDng;
	private final Controler controler;
	private static Log log = LogFactory.getLog(DoiMatkhau.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public DoiMatkhau(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		DoiMatkhau.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlThayiMt.open();
		shlThayiMt.layout();
		Display display = getParent().getDisplay();
		while (!shlThayiMt.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlThayiMt = new Shell(getParent(), getStyle());
		shlThayiMt.setImage(SWTResourceManager.getImage(DoiMatkhau.class, "/Login-icon.png"));
		shlThayiMt.setSize(400, 207);
		new FormTemplate().setCenterScreen(shlThayiMt);
		shlThayiMt.setText("Thay \u0111\u1ED5i M\u1EADt kh\u1EA9u");
		shlThayiMt.setLayout(new GridLayout(4, false));

		Label lblPic = new Label(shlThayiMt, SWT.CENTER);
		GridData gd_lblPic = new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1);
		gd_lblPic.widthHint = 80;
		lblPic.setLayoutData(gd_lblPic);
		lblPic.setImage(SWTResourceManager.getImage(DoiMatkhau.class, "/secrecy-icon.png"));

		grpTnNgiDng = new Group(shlThayiMt, SWT.NONE);
		grpTnNgiDng.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		grpTnNgiDng.setText("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng");
		grpTnNgiDng.setLayout(new GridLayout(2, false));
		grpTnNgiDng.setText("Người dùng: " + user.getTEN_CAN_BO());
		Label lblMtKhuC = new Label(grpTnNgiDng, SWT.NONE);
		lblMtKhuC.setText("M\u1EADt kh\u1EA9u c\u0169:");

		text_Matkhaucu = new Text(grpTnNgiDng, SWT.BORDER | SWT.PASSWORD);
		text_Matkhaucu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMtKhuMi = new Label(grpTnNgiDng, SWT.NONE);
		lblMtKhuMi.setText("M\u1EADt kh\u1EA9u M\u1EDBi:");

		text_Matkhaumoi = new Text(grpTnNgiDng, SWT.BORDER | SWT.PASSWORD);
		text_Matkhaumoi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNhpLi = new Label(grpTnNgiDng, SWT.NONE);
		lblNhpLi.setText("Nh\u1EADp l\u1EA1i:");

		text_Nhaplai = new Text(grpTnNgiDng, SWT.BORDER | SWT.PASSWORD);
		text_Nhaplai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlThayiMt, SWT.NONE);

		Button button = new Button(shlThayiMt, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				// text_Matkhaumoi.
			}

			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		button.setImage(SWTResourceManager.getImage(DoiMatkhau.class, "/find-icon.png"));

		Button btnHonTt = new Button(shlThayiMt, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					setupChangePassword();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void setupChangePassword() throws SQLException {
				if (text_Matkhaumoi.getText().equals(text_Nhaplai.getText())) {
					if (text_Matkhaucu.getText().equals(user.getMAT_KHAU())) {
						controler.getControl_NGUOIDUNG().update_MATKHAU(text_Matkhaumoi.getText());
						MessageBox m = new MessageBox(shlThayiMt, SWT.ICON_WORKING);
						m.setText("Hoàn tất");
						m.setMessage("Đổi mật khẩu thành công!");
						m.open();
						shlThayiMt.dispose();
					} else {
						MessageBox m = new MessageBox(shlThayiMt, SWT.ICON_ERROR);
						m.setText("Thất bại");
						m.setMessage("Mật khẩu cũ không chính xác!");
						m.open();
					}

				} else {
					MessageBox m = new MessageBox(shlThayiMt, SWT.ICON_WARNING);
					m.setText("Lỗi");
					m.setMessage("Xác nhận mật khẩu không khớp!");
					m.open();
				}
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.BOTTOM, false, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Ho\u00E0n t\u1EA5t");

		Button btnng = new Button(shlThayiMt, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlThayiMt.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

}
