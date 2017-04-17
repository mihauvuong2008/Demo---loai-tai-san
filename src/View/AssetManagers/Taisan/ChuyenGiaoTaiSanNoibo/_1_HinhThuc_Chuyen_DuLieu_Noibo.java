package View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import Control.TAISAN.Control_TAISAN;
import DAO.NGUOIDUNG;
import View.Box.OkBox;
import View.Template.FormTemplate;
import org.eclipse.swt.widgets.Label;

public class _1_HinhThuc_Chuyen_DuLieu_Noibo extends Shell {

	private static NGUOIDUNG user;
	private static Integer mAPHONGBAN;
	private static String[] dS_MA_TAISAN;
	private Button btnXong;
	private Button btnng;
	private Button btnChiChuyenDuLieu;
	private Button btnTaotDotBanGiao;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			_1_HinhThuc_Chuyen_DuLieu_Noibo shell = new _1_HinhThuc_Chuyen_DuLieu_Noibo(display, user, mAPHONGBAN,
					dS_MA_TAISAN);
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
	 * Open the window.
	 */

	/**
	 * Create contents of the window.
	 */
	public _1_HinhThuc_Chuyen_DuLieu_Noibo(Display display, NGUOIDUNG user, Integer mAPHONGBAN, String[] dS_MA_TAISAN) {
		super(display, SWT.CLOSE | SWT.MIN | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(_1_HinhThuc_Chuyen_DuLieu_Noibo.class, "/Import-export-icon.png"));
		_1_HinhThuc_Chuyen_DuLieu_Noibo.user = user;
		_1_HinhThuc_Chuyen_DuLieu_Noibo.dS_MA_TAISAN = dS_MA_TAISAN;
		_1_HinhThuc_Chuyen_DuLieu_Noibo.dS_MA_TAISAN = dS_MA_TAISAN;

		SelectionListener selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Button button = ((Button) event.widget);
				if (button.getSelection()) {
					btnXong.setText("Tiếp >>");
				} else {
					btnXong.setText("Hoàn tất");
				}
			};
		};
		setLayout(new GridLayout(3, false));
		btnTaotDotBanGiao = new Button(this, SWT.RADIO);
		GridData gd_btnTaotDotBanGiao = new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 3, 1);
		gd_btnTaotDotBanGiao.horizontalIndent = 150;
		btnTaotDotBanGiao.setLayoutData(gd_btnTaotDotBanGiao);
		btnTaotDotBanGiao.addSelectionListener(selectionListener);

		btnTaotDotBanGiao
				.setImage(SWTResourceManager.getImage(_1_HinhThuc_Chuyen_DuLieu_Noibo.class, "/Transfer-icon.png"));
		btnTaotDotBanGiao.setText("T\u1EA1o \u0111\u1EE3t B\u00E0n giao t\u00E0i s\u1EA3n");

		btnChiChuyenDuLieu = new Button(this, SWT.RADIO);
		GridData gd_btnChiChuyenDuLieu = new GridData(SWT.LEFT, SWT.TOP, false, true, 3, 1);
		gd_btnChiChuyenDuLieu.horizontalIndent = 150;
		btnChiChuyenDuLieu.setLayoutData(gd_btnChiChuyenDuLieu);

		btnChiChuyenDuLieu
				.setImage(SWTResourceManager.getImage(_1_HinhThuc_Chuyen_DuLieu_Noibo.class, "/TransferData.png"));
		btnChiChuyenDuLieu.setText("Ch\u1EC9 chuy\u1EC3n d\u1EEF li\u1EC7u");
		new Label(this, SWT.NONE);

		btnXong = new Button(this, SWT.NONE);
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnChiChuyenDuLieu.getSelection()) {
					if (btnChiChuyenDuLieu.getSelection()) {
						OkBox m = new OkBox(null, "Chuyển dữ liệu hoàn tất!", "Chuyển dữ liệu");
						// m.Text("Chuyển dữ liệu");
						try {
							for (String key : dS_MA_TAISAN) {
								Control_TAISAN cdt = new Control_TAISAN(user);
								cdt.Update_Donvi_Sudung(key, mAPHONGBAN);
							}
						} catch (Exception e2) {
							// m.("Chuyển dữ liệu thất bại!");
						}
						dispose();
						m.open();
					} else {

					}
				} else if (btnTaotDotBanGiao.getSelection()) {
					TaoDot_ChuyenGiao_Taisan_Noibo tctn = new TaoDot_ChuyenGiao_Taisan_Noibo(getShell(),
							SWT.DIALOG_TRIM, dS_MA_TAISAN, mAPHONGBAN, user);
					try {
						tctn.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
		});
		btnXong.setText("Ti\u1EBFp >>");

		btnng = new Button(this, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		// center screen
		setSize(496, 287);
		new FormTemplate().setCenterScreen(getShell());
		setText("Chuy\u1EC3n t\u00E0i s\u1EA3n");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
