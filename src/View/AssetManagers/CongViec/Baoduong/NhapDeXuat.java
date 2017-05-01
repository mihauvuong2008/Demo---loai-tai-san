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
import DAO.TAPHOSO;
import DAO.VANBAN;
import View.AssetManagers.Hoso.TailenTaphoso;
import View.AssetManagers.Hoso.TapHoso_Creator;
import View.AssetManagers.Hoso.Taphoso_View;
import View.AssetManagers.Hoso.Vanban_View;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class NhapDeXuat extends Dialog {
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
	private static Log log = LogFactory.getLog(NhapDeXuat.class);
	public DE_XUAT result;
	private Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public NhapDeXuat(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		NhapDeXuat.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @throws SQLException
	 */
	public void createContents() throws SQLException {
		shell = new Shell(getParent(), SWT.SHELL_TRIM);
		shell.setImage(user.getIcondata().DexuatIcon);
		shell.setText("Nhập Đề xuất - Hồ sơ chủ trương phê duyệt");
		shell.setSize(720, 445);
		shell.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shell);

		SashForm sashForm = new SashForm(shell, SWT.NONE);
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
						Vanban_View vbv = new Vanban_View(shell, SWT.DIALOG_TRIM, user, null, vb, false);
						vbv.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemVnBn.setText("Xem Văn bản");

		MenuItem mntmTpHS = new MenuItem(menu, SWT.NONE);
		mntmTpHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					add_Edit_Hoso_Dexuat();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmTpHS.setText("Tập hồ sơ");
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setImage(user.getIcondata().buleFolderIcon);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					add_Edit_Hoso_Dexuat();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnNewButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnNewButton.setText("Hồ sơ - Đề xuất");

		Button btnTip = new Button(shell, SWT.NONE);
		btnTip.setImage(user.getIcondata().saveIcon);
		btnTip.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!checkTextNotNULL()) {
					showMessage_FillText();
					return;
				}
				// dx.setMA_DE_XUAT(controler.getControl_DEXUAT().getNextKey());
				dx.setSODEXUAT(text_Sodexuat.getText());
				dx.setNGAYTHANG_VANBAN(mdf.getDate(dateTime_NgaythangVanban));
				dx.setMA_PHONGBAN(((PHONGBAN) combo.getData(combo.getText())).getMA_PHONGBAN());
				dx.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
				dx.setGHI_CHU(text_Ghichu.getText());
				dx.setTHOI_DIEM_BAT_DAU(mdf.getDate(dateTime_NgayNhanVanban));
				dx.setTHOI_DIEM_CHUYEN_GIAO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
				result = dx;
				shell.dispose();
			}
		});
		GridData gd_btnTip = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnTip.widthHint = 75;
		btnTip.setLayoutData(gd_btnTip);
		btnTip.setText("Xong");

		Button btnng = new Button(shell, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected void add_Edit_Hoso_Dexuat() throws SQLException {
		if (dx.getMA_TAPHOSO() <= 0) {
			TAPHOSO ths_tmp = new TapHoso_Creator(user).getTaphoso("Tập hồ sơ - Đề xuất",
					"Tập hồ sơ - Đề xuất, phê duyệt chủ trương");
			TailenTaphoso tlt = new TailenTaphoso(shell, SWT.DIALOG_TRIM, user, ths_tmp);
			tlt.open();
			ths_tmp = (TAPHOSO) tlt.result;
			if (ths_tmp == null)
				return;
			dx.setMA_TAPHOSO(ths_tmp.getMA_TAPHOSO());
		}
		TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO());
		Taphoso_View b = new Taphoso_View(shell, SWT.DIALOG_TRIM, user, ths, false);
		b.open();
		fillTaphoso(ths);
	}

	private void fillTaphoso(TAPHOSO ths) throws SQLException {
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
		MessageBox m = new MessageBox(shell, SWT.ICON_WARNING);
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
