package View.AssetManagers.CongViec.Suachua;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.AssetManagers.Hoso.Vanban_View;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class _1_NhapDeXuat_Suachua extends Shell {
	private DE_XUAT dx;
	private static NGUOIDUNG user;
	private final Controler controler;
	private Text text_Ghichu;
	private Text text_Sodexuat;
	private Combo combo;
	private Table table;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(_1_NhapDeXuat_Suachua.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_1_NhapDeXuat_Suachua shell = new _1_NhapDeXuat_Suachua(display, user);
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
	 * @throws SQLException
	 */
	public _1_NhapDeXuat_Suachua(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(_1_NhapDeXuat_Suachua.class, "/document-icon.png"));
		setLayout(new GridLayout(4, false));
		_1_NhapDeXuat_Suachua.user = user;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Số đề xuất:");

		text_Sodexuat = new Text(composite, SWT.BORDER);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Đơn vị ban hành:");

		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("Ngày tháng văn bản:");

		dateTime_1 = new DateTime(composite, SWT.BORDER | SWT.LONG);
		dateTime_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_3.setText("Ngày nhận văn bản:");

		dateTime = new DateTime(composite, SWT.CALENDAR | SWT.LONG);
		dateTime.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_4.setText("Thông tin đề xuất:");

		text_Ghichu = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("SỐ VĂN BẢN");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("TRÍCH YẾU");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(120);
		tableColumn_3.setText("CƠ QUAN BAN HÀNH");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("NGÀY BAN HÀNH");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] til = table.getSelection();
					if (til.length > 0) {
						VANBAN vb = (VANBAN) til[0].getData();
						Vanban_View vbv = new Vanban_View(getShell(), SWT.DIALOG_TRIM, user, null, vb, false);
						vbv.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("Xem Văn bản");
		sashForm.setWeights(new int[] { 1000, 618 });
		new Label(this, SWT.NONE);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(_1_NhapDeXuat_Suachua.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dx.getMA_TAPHOSO() <= 0) {
						TAP_HO_SO t = new TAP_HO_SO();
						t.setTEN_TAPHOSO("Tập hồ sơ - Đề xuất Sửa chữa");
						t.setGIOITHIEU_TAPHOSO("Tập hồ sơ - Đề xuất Sửa chữa");
						t.setNGAY_TAO_TAPHOSO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
						dx.setMA_TAPHOSO(controler.getControl_TAPHOSO().Create_TAP_HO_SO(t));
					}
					TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO());

					TAPHOSO_View view = new TAPHOSO_View(getShell(), SWT.NONE, user, ths, false);
					view.open();
					fillTaphoso(ths);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void fillTaphoso(TAP_HO_SO ths) throws SQLException {
				table.removeAll();
				ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
				int i = 1;
				if (vbl != null)
					for (VANBAN vanban : vbl) {
						TableItem tableItem = new TableItem(table, SWT.NONE);
						tableItem.setText(new String[] { (i++) + "", vanban.getSO_VANBAN(), vanban.getTRICH_YEU(),
								vanban.getCO_QUAN_BAN_HANH(), ((vanban.getNGAY_BAN_HANH() == null) ? "-"
										: mdf.getViewStringDate(vanban.getNGAY_BAN_HANH())) });
						tableItem.setData(vanban);
					}
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("File H\u1ED3 s\u01A1");

		Button btnTip = new Button(this, SWT.NONE);
		btnTip.setImage(SWTResourceManager.getImage(_1_NhapDeXuat_Suachua.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnTip.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (checkTextNotNULL()) {
						dx.setMA_DE_XUAT(controler.getControl_DEXUAT().getNextKey());
						dx.setSODEXUAT(text_Sodexuat.getText());
						dx.setNGAYTHANG_VANBAN(mdf.getDate(dateTime_1));
						dx.setMA_PHONGBAN(((PHONGBAN) combo.getData(combo.getText())).getMA_PHONGBAN());
						dx.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
						dx.setGHI_CHU(text_Ghichu.getText());
						dx.setTHOI_DIEM_BAT_DAU(mdf.getDate(dateTime));
						dx.setTHOI_DIEM_CHUYEN_GIAO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
						setVisible(false);
						_2_Taodot_Suachua sb = new _2_Taodot_Suachua(display, user, getShell(), dx,
								/* INSERT DSB */1);
						sb.open();
					} else {
						showMessage_FillText();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnTip = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnTip.widthHint = 75;
		btnTip.setLayoutData(gd_btnTip);
		btnTip.setText("Ti\u1EBFp >>");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});

		GridData gd_btnng = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

		createContents();

		init();

	}

	private void init() throws SQLException {
		dx = new DE_XUAT();
		setCoquanbanhanh(combo);
	}

	private void setCoquanbanhanh(Combo combo2) throws SQLException {
		ArrayList<PHONGBAN> pl = controler.getControl_PHONGBAN().getAllDonvi();
		for (PHONGBAN p : pl) {
			combo2.add(p.getTEN_PHONGBAN());
			combo2.setData(p.getTEN_PHONGBAN(), p);
		}
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
		m.setText("Lỗi");
		m.setMessage("Số đề xuất, đơn vị ban hành không để trống!");
		m.open();
	}

	protected boolean checkTextNotNULL() {
		if (text_Sodexuat.getText().equals("")) {
			return false;
		}
		if (combo.getText().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Nhận đề xuất Sửa chữa Phương tiện tài sản");
		setSize(615, 380);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
