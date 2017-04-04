package View.AssetManagers.CongViec.Baoduong;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class _1_NhapDeXuat extends Shell {
	private DE_XUAT dx;
	private static NGUOIDUNG user;
	private Table table;
	private Text text_Ghichu;
	private Text text_Sodexuat;
	private DateTime dateTime_NgaythangVanban;
	private DateTime dateTime_NgayNhanVanban;
	private Combo combo;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(_1_NhapDeXuat.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_1_NhapDeXuat shell = new _1_NhapDeXuat(display, user);
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
	public _1_NhapDeXuat(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(_1_NhapDeXuat.class, "/maintenance-icon (1).png"));
		setLayout(new GridLayout(3, false));
		_1_NhapDeXuat.user = user;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("Số đề xuất:");

		text_Sodexuat = new Text(composite, SWT.BORDER);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("Đơn vị ban hành:");

		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.select(0);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("Ngày tháng văn bản:");

		dateTime_NgaythangVanban = new DateTime(composite, SWT.BORDER | SWT.LONG);
		dateTime_NgaythangVanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_2.setText("Ngày nhận văn bản:");

		dateTime_NgayNhanVanban = new DateTime(composite, SWT.CALENDAR | SWT.LONG);
		dateTime_NgayNhanVanban.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_1.setText("Thông tin đề xuất:");

		text_Ghichu = new Text(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnSVnBn = new TableColumn(table, SWT.NONE);
		tblclmnSVnBn.setWidth(100);
		tblclmnSVnBn.setText("SỐ VĂN BẢN");

		TableColumn tblclmnTrchYu = new TableColumn(table, SWT.NONE);
		tblclmnTrchYu.setWidth(150);
		tblclmnTrchYu.setText("TRÍCH YẾU");

		TableColumn tblclmnCQuanBan = new TableColumn(table, SWT.NONE);
		tblclmnCQuanBan.setWidth(120);
		tblclmnCQuanBan.setText("CƠ QUAN BAN HÀNH");

		TableColumn tblclmnNgyBanHnh = new TableColumn(table, SWT.NONE);
		tblclmnNgyBanHnh.setWidth(100);
		tblclmnNgyBanHnh.setText("NGÀY BAN HÀNH");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmXemVnBn = new MenuItem(menu, SWT.NONE);
		mntmXemVnBn.addSelectionListener(new SelectionAdapter() {
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
		mntmXemVnBn.setText("Xem Văn bản");
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setImage(
				SWTResourceManager.getImage(_1_NhapDeXuat.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dx.getMA_TAPHOSO() <= 0) {
						TAP_HO_SO t = new TAP_HO_SO();
						t.setTEN_TAPHOSO("Tập hồ sơ - Đề xuất Bảo dưỡng");
						t.setGIOITHIEU_TAPHOSO("Tập hồ sơ - Đề xuất Bảo dưỡng");
						t.setNGAY_TAO_TAPHOSO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
						dx.setMA_TAPHOSO(controler.getControl_TAPHOSO().Create_TAP_HO_SO(t));
					}
					TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO());
					TAPHOSO_View b = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
					b.open();
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
		btnNewButton.setText("Hồ sơ - văn bản");

		Button btnTip = new Button(this, SWT.NONE);
		btnTip.setImage(
				SWTResourceManager.getImage(_1_NhapDeXuat.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnTip.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (checkTextNotNULL()) {
						dx.setMA_DE_XUAT(controler.getControl_DEXUAT().getNextKey());
						dx.setSODEXUAT(text_Sodexuat.getText());
						dx.setNGAYTHANG_VANBAN(mdf.getDate(dateTime_NgaythangVanban));
						dx.setMA_PHONGBAN(((PHONGBAN) combo.getData(combo.getText())).getMA_PHONGBAN());
						dx.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
						dx.setGHI_CHU(text_Ghichu.getText());
						dx.setTHOI_DIEM_BAT_DAU(mdf.getDate(dateTime_NgayNhanVanban));
						dx.setTHOI_DIEM_CHUYEN_GIAO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
						setVisible(false);
						_2_Taodot_Baoduong sb = new _2_Taodot_Baoduong(display, user, getShell(),
								/* De xuat: */ dx, /* MODE_NEW_VIEW: */1);

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
		GridData gd_btnng = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
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
		setText("Nhận đề xuất Bảo dưỡng Phương tiện giao thông");
		setSize(615, 380);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
