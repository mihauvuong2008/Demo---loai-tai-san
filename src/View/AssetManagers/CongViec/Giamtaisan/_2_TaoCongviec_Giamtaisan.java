package View.AssetManagers.CongViec.Giamtaisan;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import org.eclipse.wb.swt.SWTResourceManager;

public class _2_TaoCongviec_Giamtaisan extends Shell {

	private static NGUOIDUNG user;
	private final Controler controler;
	private static DE_XUAT dx;
	private static Shell ParentShell;
	private Text text_Tendotgiam;
	private Text text_Mota;
	private Combo combo_Lydogiam;
	private static Log log = LogFactory.getLog(_2_TaoCongviec_Giamtaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_2_TaoCongviec_Giamtaisan shell = new _2_TaoCongviec_Giamtaisan(display, user, ParentShell, dx);
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
	 * @param dx
	 * @param shlChnNgiL
	 * @param user
	 */
	public _2_TaoCongviec_Giamtaisan(Display display, NGUOIDUNG user, Shell Parent, DE_XUAT dx) {
		super(display, SWT.CLOSE | SWT.MIN | SWT.TITLE);
		setImage(SWTResourceManager.getImage(_2_TaoCongviec_Giamtaisan.class, "/database_remove.png"));
		setLayout(new GridLayout(4, false));
		_2_TaoCongviec_Giamtaisan.user = user;
		controler = new Controler(user);

		Label label = new Label(this, SWT.NONE);
		label.setText("Tên đợt tăng*:");

		text_Tendotgiam = new Text(this, SWT.BORDER);
		text_Tendotgiam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("Lý do tăng*:");

		combo_Lydogiam = new Combo(this, SWT.READ_ONLY);
		combo_Lydogiam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label label_2 = new Label(this, SWT.NONE);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_2.verticalIndent = 3;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("Mô tả:");

		text_Mota = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1));
		new Label(this, SWT.NONE);

		Button button = new Button(this, SWT.NONE);
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Trở lại <<");

		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (checkTextNotNULL()) {
						DOT_THUCHIEN_GIAM_TAISAN dg = new DOT_THUCHIEN_GIAM_TAISAN();
						dg.setTEN_DOT_GIAM(text_Tendotgiam.getText());
						dg.setLY_DO_GIAM((int) combo_Lydogiam.getData(combo_Lydogiam.getText()));
						dg.setMO_TA(text_Mota.getText());

						int i;
						i = controler.getControl_DEXUAT().insert_DEXUAT(dx);

						System.out.println(i);
						if (i >= 0) {
							QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
							qdt.setMA_DE_XUAT(dx.getMA_DE_XUAT());
							qdt.setTEN_QUA_TRINH("Đề xuất - thực hiện theo công văn: " + dx.getSODEXUAT());
							int i2 = controler.getControl_QUATRINH_DEXUAT_THUCHIEN()
									.insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
							if (i2 >= 0) {
								dg.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								qdt.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								int ict = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
										.InsertDOT_THUCHIEN_GIAM_TAISAN(dg, null);
								if (ict >= 0) {
									showMessage_Succes();
									ParentShell.dispose();
									dispose();
									GiaoViec gv = new GiaoViec(user);
									gv.open();
								} else {
									showMessage_Fail();
									System.out.println("1");
								}
							} else {
								showMessage_Fail();
								System.out.println("2");
							}
						} else {
							showMessage_Fail();
							System.out.println("3");
						}
					} else {
						showMessage_FillText();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void showMessage_FillText() {
				MessageBox m = new MessageBox(getShell());
				m.setText("Lỗi");
				m.setMessage("Tên đợt giảm, Mô tả không để trống!");
				m.open();
			}

			private void showMessage_Fail() {
				MessageBox m = new MessageBox(getShell());
				m.setText("Thất bại");
				m.setMessage("Tạo công việc thất bại");
				m.open();
			}

			private void showMessage_Succes() {
				MessageBox m = new MessageBox(getShell());
				m.setText("Hoàn tất");
				m.setMessage("Tạo công việc hoàn tất");
				m.open();
			}

			private boolean checkTextNotNULL() {
				if (text_Tendotgiam.getText().equals("")) {
					return false;
				}
				if (text_Mota.getText().equals("")) {
					return false;
				}
				return true;
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Tiếp >>");

		Button button_2 = new Button(this, SWT.NONE);
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Đóng");
		_2_TaoCongviec_Giamtaisan.user = user;
		_2_TaoCongviec_Giamtaisan.dx = dx;
		_2_TaoCongviec_Giamtaisan.ParentShell = Parent;
		Fill_ItemData fi = new Fill_ItemData();
		fi.setComboBox_LYDOGIAM(combo_Lydogiam);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tạo công việc (Giảm tài sản)");
		setSize(592, 366);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
