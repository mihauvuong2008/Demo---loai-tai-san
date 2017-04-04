package View.AssetManagers.CongViec.TangTaiSan;

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

import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;

public class _2_TaoCongviec_Tangtaisan extends Shell {
	private Text text_Tendottang;
	private Text text_Mota;
	private Combo combo_Lydotang;
	private static DE_XUAT dx;
	private static NGUOIDUNG user;
	private static Shell ParentShell;
	private final Controler controler;
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(_2_TaoCongviec_Tangtaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_2_TaoCongviec_Tangtaisan shell = new _2_TaoCongviec_Tangtaisan(display, user, ParentShell, dx);
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
	public _2_TaoCongviec_Tangtaisan(Display display, NGUOIDUNG user, Shell ParentShell, DE_XUAT dx) {
		super(display, SWT.CLOSE | SWT.MIN | SWT.TITLE);
		setImage(SWTResourceManager.getImage(_2_TaoCongviec_Tangtaisan.class, "/add-icon (1).png"));
		setLayout(new GridLayout(4, false));
		_2_TaoCongviec_Tangtaisan.dx = dx;
		_2_TaoCongviec_Tangtaisan.user = user;
		_2_TaoCongviec_Tangtaisan.ParentShell = ParentShell;
		controler = new Controler(user);

		Label label = new Label(this, SWT.NONE);
		label.setText("Tên đợt tăng*:");

		text_Tendottang = new Text(this, SWT.BORDER);
		text_Tendottang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblHnhThcGim = new Label(this, SWT.NONE);
		lblHnhThcGim.setText("Hình thức Tăng*:");

		combo_Lydotang = new Combo(this, SWT.READ_ONLY);
		combo_Lydotang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label label_3 = new Label(this, SWT.NONE);
		GridData gd_label_3 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_3.verticalIndent = 2;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("Mô tả:");

		text_Mota = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		new Label(this, SWT.NONE);

		Button btnTrLi = new Button(this, SWT.NONE);
		btnTrLi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParentShell.setVisible(true);
				dispose();
			}
		});
		GridData gd_btnTrLi = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnTrLi.widthHint = 75;
		btnTrLi.setLayoutData(gd_btnTrLi);
		btnTrLi.setText("Trở lại <<");

		Button btnTip = new Button(this, SWT.NONE);
		btnTip.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (checkTextNotNULL()) {
						DOT_THUCHIEN_TANG_TAISAN dt = new DOT_THUCHIEN_TANG_TAISAN();
						dt.setTEN_DOT_TANG(text_Tendottang.getText());
						dt.setLY_DO_TANG((int) combo_Lydotang.getData(combo_Lydotang.getText()));
						dt.setMO_TA(text_Mota.getText());

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
								dt.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								qdt.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								int ict = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
										.InsertDOT_THUCHIEN_TANG_TAISAN(dt, qdt, null, null);
								if (ict >= 0) {
									showMessage_Succes();
									ParentShell.dispose();
									dispose();
									GiaoViec gv = new GiaoViec(user);
									gv.open();
								} else {
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
					showMessage_Fail();
				}
			}
		});
		GridData gd_btnTip = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnTip.widthHint = 75;
		btnTip.setLayoutData(gd_btnTip);
		btnTip.setText("Tiếp >>");

		Button btnng = new Button(this, SWT.NONE);
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

		Fill_ItemData fi = new Fill_ItemData();
		fi.setComboBox_LYDOTANG(combo_Lydotang);
		createContents();
	}

	protected void showMessage_Fail() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Thất bại");
		m.setMessage("Tạo công việc thất bại");
		m.open();
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Hoàn tất");
		m.setMessage("Tạo công việc hoàn tất");
		m.open();
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Lỗi");
		m.setMessage("Tên đợt tăng, Mô tả không để trống!");
		m.open();
	}

	protected boolean checkTextNotNULL() {
		if (text_Tendottang.getText().equals("")) {
			return false;
		}
		if (text_Mota.getText().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tạo công việc (Tăng tài sản)");
		setSize(592, 366);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
