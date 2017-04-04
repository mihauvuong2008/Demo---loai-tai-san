package View.AssetManagers.ThongBao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.THONGBAO;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.DateTime;

public class ThongBao extends Shell {
	private Table table;
	private static NGUOIDUNG user;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private final int lenThongbao = 160;
	private Tree tree;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static Log log = LogFactory.getLog(ThongBao.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ThongBao shell = new ThongBao(display, user);
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
	public ThongBao(Display display, NGUOIDUNG user) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(ThongBao.class, "/Actions-help-about-icon (1).png"));
		setLayout(new GridLayout(6, false));
		ThongBao.user = user;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		tree = new Tree(sashForm, SWT.BORDER);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					viewThongbao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		TreeColumn treeColumn = new TreeColumn(tree, SWT.LEFT);
		treeColumn.setWidth(301);
		treeColumn.setText("DANH SÁCH THÔNG BÁO");

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("Đọc Thông báo");

		TreeItem treeItem = new TreeItem(tree, 0);
		treeItem.setText("Lệnh Điều xe");
		treeItem.setImage(SWTResourceManager.getImage(ThongBao.class, "/Household-Garage-icon.png"));
		treeItem.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		treeItem.setData("LENHDIEUXE_MESSAGE");

		TreeItem treeItem_1 = new TreeItem(tree, 0);
		treeItem_1.setText("Sửa chữa - Bảo dưỡng");
		treeItem_1.setImage(SWTResourceManager.getImage(ThongBao.class, "/maintenance-icon.png"));
		treeItem_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		treeItem_1.setData("SUACHUA_BAODUONG_MESSAGE");

		TreeItem treeItem_2 = new TreeItem(tree, 0);
		treeItem_2.setText("Mua sắm - Thanh lý");
		treeItem_2.setImage(SWTResourceManager.getImage(ThongBao.class, "/add-icon.png"));
		treeItem_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		treeItem_2.setData("MUASAM_THANHLY_MESSAGE");

		TreeItem treeItem_3 = new TreeItem(tree, 0);
		treeItem_3.setText("Tài khoản người dùng");
		treeItem_3.setImage(SWTResourceManager.getImage(ThongBao.class, "/Office-Customer-Male-Light-icon (1).png"));
		treeItem_3.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		treeItem_3.setData("TAIKHOANNGUOIDUNG_MESSAGE");

		TreeItem treeItem_4 = new TreeItem(tree, 0);
		treeItem_4.setText("Thông báo khác");
		treeItem_4.setImage(SWTResourceManager.getImage(ThongBao.class, "/table-relationship-icon.png"));
		treeItem_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		treeItem_4.setData("THONGBAOKHAC_MESSAGE");

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(47);
		tblclmnStt.setText("STT");

		TableColumn tblclmnNgyThngBo = new TableColumn(table, SWT.NONE);
		tblclmnNgyThngBo.setWidth(150);
		tblclmnNgyThngBo.setText("NGÀY THÔNG BÁO");

		TableColumn tblclmnNiDung = new TableColumn(table, SWT.NONE);
		tblclmnNiDung.setWidth(344);
		tblclmnNiDung.setText("NỘI DUNG");

		Menu menu_1 = new Menu(table);
		table.setMenu(menu_1);

		MenuItem mntmXemThngBo = new MenuItem(menu_1, SWT.NONE);
		mntmXemThngBo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (til[0].getData() instanceof THONGBAO) {
						THONGBAO tb = (THONGBAO) til[0].getData();
						ViewTHONGBAO ctb = new ViewTHONGBAO(getShell(), SWT.DIALOG_TRIM, tb, user);
						ctb.open();
						try {
							viewThongbao();
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					}

				}
			}
		});
		mntmXemThngBo.setText("Xem Thông báo");
		sashForm.setWeights(new int[] { 228, 413 });

		Label lblTNgy = new Label(this, SWT.NONE);
		lblTNgy.setText("Từ ngày:");

		dateTime_Begin = new DateTime(this, SWT.BORDER);

		Label lblnNgy = new Label(this, SWT.NONE);
		lblnNgy.setText("Đến ngày: ");

		dateTime_End = new DateTime(this, SWT.BORDER);

		Button btnXem = new Button(this, SWT.NONE);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					viewThongbao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.setText("Xem");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		createContents();
		init();
	}

	private void init() {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDay);
		cal.add(Calendar.DATE, -365);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);
	}

	protected void viewThongbao() throws SQLException {
		TreeItem[] til = tree.getSelection();
		if (til.length > 0) {
			String item = (String) til[0].getData();
			if (item.equals("LENHDIEUXE_MESSAGE")) {
				Fill_LenhDieuXe(getDate(dateTime_Begin), getDate(dateTime_End));
			}
			if (item.equals("SUACHUA_BAODUONG_MESSAGE")) {
				Fill_SuaChua_BaoDuong(getDate(dateTime_Begin), getDate(dateTime_End));
			}
			if (item.equals("MUASAM_THANHLY_MESSAGE")) {
				Fill_MuaSam_ThanhLy(getDate(dateTime_Begin), getDate(dateTime_End));
			}
			if (item.equals("TAIKHOANNGUOIDUNG_MESSAGE")) {
				Fill_NguoiDung(getDate(dateTime_Begin), getDate(dateTime_End));
			}
			if (item.equals("THONGBAOKHAC_MESSAGE")) {
				Fill_ThongBaokhac(getDate(dateTime_Begin), getDate(dateTime_End));
			}
		}

	}

	private void Fill_ThongBaokhac(Date date, Date date2) throws SQLException {
		table.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_THONGBAO_ThongBaokhac(date, date2);
		int i = 1;
		for (THONGBAO tb : tbl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "",
					"[" + tb.getNGAY_THONGBAO() == null ? "-"
							: mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()),
					tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > lenThongbao ? lenThongbao
							: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setData(tb);
			i++;
		}
	}

	private void Fill_NguoiDung(Date date, Date date2) throws SQLException {
		table.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_THONGBAO_NguoiDung(date, date2);
		int i = 1;
		for (THONGBAO tb : tbl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "",
					tb.getNGAY_THONGBAO() == null ? "-" : mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()),
					tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > lenThongbao ? lenThongbao
							: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setData(tb);
			i++;
		}
	}

	private void Fill_MuaSam_ThanhLy(Date date, Date date2) throws SQLException {
		table.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_THONGBAO_MuaSam_ThanhLy(date, date2);
		int i = 1;
		for (THONGBAO tb : tbl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "",
					tb.getNGAY_THONGBAO() == null ? "-" : mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()),
					tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > lenThongbao ? lenThongbao
							: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setData(tb);
			i++;
		}
	}

	private void Fill_SuaChua_BaoDuong(Date date, Date date2) throws SQLException {
		table.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_THONGBAO_SuaChua_BaoDuong(date, date2);
		int i = 1;
		for (THONGBAO tb : tbl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "",
					tb.getNGAY_THONGBAO() == null ? "-" : mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()),
					tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > lenThongbao ? lenThongbao
							: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setData(tb);
			i++;
		}
	}

	private void Fill_LenhDieuXe(Date date, Date date2) throws SQLException {
		table.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_THONGBAO_LenhDieuXe(date, date2);
		int i = 1;
		for (THONGBAO tb : tbl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "",
					tb.getNGAY_THONGBAO() == null ? "-" : mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()),
					tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > lenThongbao ? lenThongbao
							: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setData(tb);
			i++;
		}
	}

	private Date getDate(DateTime dateTime) {
		if (dateTime != null) {
			return date(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear());
		}
		return null;
	}

	private static Date date(final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Hoạt động người dùng");
		setSize(670, 415);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
