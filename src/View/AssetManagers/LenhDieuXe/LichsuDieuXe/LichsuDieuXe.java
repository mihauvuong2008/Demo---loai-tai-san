package View.AssetManagers.LenhDieuXe.LichsuDieuXe;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.AssetManagers.LenhDieuXe.QuanLy_Lenhdieuxe;
import View.DateTime.MyDateFormat;
import View.DateTime.startDate_endDate;
import View.Template.FormTemplate;
import View.Template.TreeTemplate;

public class LichsuDieuXe extends Shell {
	private Table table;
	private static NGUOIDUNG user;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Button btnLnhHy;
	private Tree tree_Phongban;
	private Tree tree_Quy;
	private Date Date_Batdau;
	private Date Date_Ketthuc;
	private PHONGBAN p;
	protected boolean HuyLenh;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(LichsuDieuXe.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LichsuDieuXe shell = new LichsuDieuXe(display, user);
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
	 * @param user
	 * @throws SQLException
	 */
	public LichsuDieuXe(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.CLOSE | SWT.MAX | SWT.TITLE);
		setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/History.png"));
		setLayout(new GridLayout(7, false));
		LichsuDieuXe.user = user;
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 7, 1));

		ToolItem tltmXemLnhiu = new ToolItem(toolBar, SWT.NONE);
		tltmXemLnhiu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Xem_Lenh_DieuXe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXemLnhiu.setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/car (1).png"));
		tltmXemLnhiu.setText("Xem Lệnh điều xe");

		ToolItem tltmThayiThng = new ToolItem(toolBar, SWT.NONE);
		tltmThayiThng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SuaThongtinLenh();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThayiThng.setText("Thay đổi thông tin ");
		tltmThayiThng.setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/edit-validated-icon (1).png"));

		ToolItem tltmHyLnh = new ToolItem(toolBar, SWT.NONE);
		tltmHyLnh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Huy_LaplaiLenh();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmHyLnh.setText("Hủy lệnh");
		tltmHyLnh.setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/Button-warning-icon.png"));

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Xoa_LenhDieuxe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXa.setText("Xóa");
		tltmXa.setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/delete-1-icon (1).png"));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		tree_Phongban = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		new TreeTemplate(user).getTreePHONGBAN(tree_Phongban);
		tree_Phongban.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_Phongban.getSelection();
					if (items.length > 0) {
						p = (PHONGBAN) items[0].getData();
						fillTable(p, Date_Batdau, Date_Ketthuc);
						if (p != null)
							System.out.println(p.getMA_PHONGBAN());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		Composite composite = new Composite(sashForm_1, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		btnLnhHy = new Button(composite, SWT.CHECK);
		btnLnhHy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnLnhHy.getSelection()) {
					HuyLenh = true;
				} else {
					HuyLenh = false;
				}
			}
		});
		btnLnhHy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnLnhHy.setText("Xem Lệnh đã hủy");

		tree_Quy = new Tree(composite, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tree_Quy = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tree_Quy.widthHint = 242;
		tree_Quy.setLayoutData(gd_tree_Quy);
		TreeItem Tatca = new TreeItem(tree_Quy, SWT.NONE);
		Tatca.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Tatca.setText("Tất cả");
		// Tatca_Quy.setImage(SWTResourceManager.getImage(MainForm.class,
		// "/Home-icon.png"));
		setItem_Quy(Tatca);
		tree_Quy.pack();
		sashForm_1.setWeights(new int[] { 140, 287 });

		tree_Quy.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_Quy.getSelection();
					if (items.length > 0) {

						startDate_endDate dv = (startDate_endDate) items[0].getData();
						if (dv != null) {

							try {
								Date_Batdau = mdf.getDateFromSQLString(dv.getStartDate());
								Date_Ketthuc = mdf.getDateFromSQLString(dv.getEndDate());
							} catch (ParseException e) {
								e.printStackTrace();
							}
						} else {
							Date_Batdau = null;
							Date_Ketthuc = null;
						}
						fillTable(p, Date_Batdau, Date_Ketthuc);

					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmXemLnhiu = new MenuItem(menu, SWT.NONE);
		mntmXemLnhiu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Xem_Lenh_DieuXe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemLnhiu.setText("Xem L\u1EC7nh \u0111i\u1EC1u xe");

		MenuItem mntmHyLnh = new MenuItem(menu, SWT.NONE);
		mntmHyLnh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Huy_LaplaiLenh();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmHyLnh.setText("Lập lại - Hủy lệnh");

		MenuItem mntmSaThngTin = new MenuItem(menu, SWT.NONE);
		mntmSaThngTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					SuaThongtinLenh();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmSaThngTin.setText("S\u1EEDa th\u00F4ng tin");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Xoa_LenhDieuxe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXa.setText("X\u00F3a");

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(50);
		tblclmnStt.setText("STT");

		TableColumn tblclmnMLnhiu = new TableColumn(table, SWT.NONE);
		tblclmnMLnhiu.setWidth(130);
		tblclmnMLnhiu.setText("M\u00C3 L\u1EC6NH \u0110I\u1EC0U XE");

		TableColumn tblclmnXeBin = new TableColumn(table, SWT.NONE);
		tblclmnXeBin.setWidth(100);
		tblclmnXeBin.setText("XE - BI\u1EC2N S\u1ED0");

		TableColumn tblclmnThiGiana = new TableColumn(table, SWT.NONE);
		tblclmnThiGiana.setWidth(135);
		tblclmnThiGiana.setText("TH\u1EDCI GIAN, \u0110\u1ECAA \u0110I\u1EC2M");

		TableColumn tblclmnNgyKhiHnh = new TableColumn(table, SWT.NONE);
		tblclmnNgyKhiHnh.setWidth(114);
		tblclmnNgyKhiHnh.setText("NG\u00C0Y KH\u1EDEI H\u00C0NH");

		TableColumn tblclmnCnBLi = new TableColumn(table, SWT.NONE);
		tblclmnCnBLi.setWidth(100);
		tblclmnCnBLi.setText("C\u00E1n b\u1ED9 l\u00E1i");

		TableColumn tblclmnNgiToLnh = new TableColumn(table, SWT.NONE);
		tblclmnNgiToLnh.setWidth(124);
		tblclmnNgiToLnh.setText("NG\u01AF\u1EDCI T\u1EA0O L\u1EC6NH");
		sashForm.setWeights(new int[] { 252, 502 });
		fillTable(null, null, null);

		Label lblTNgy = new Label(this, SWT.NONE);
		lblTNgy.setText("T\u1EEB ng\u00E0y:");

		dateTime = new DateTime(this, SWT.BORDER);

		Label lblnNgy = new Label(this, SWT.NONE);
		lblnNgy.setText("\u0110\u1EBFn ng\u00E0y:");

		dateTime_1 = new DateTime(this, SWT.BORDER);

		Button btnXem = new Button(this, SWT.NONE);
		btnXem.setImage(SWTResourceManager.getImage(LichsuDieuXe.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Date_Batdau = mdf.getDate(dateTime);
					Date_Ketthuc = mdf.getDate(dateTime_1);
					if (Date_Batdau.before(Date_Ketthuc) || Date_Batdau.compareTo(Date_Ketthuc) == 0)
						fillTable(p, Date_Batdau, Date_Ketthuc);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXem.setText("Xem");
		
		Button btnChaHin = new Button(this, SWT.CHECK);
		btnChaHin.setText("- chưa hiện thực - Xe chuẩn bị điều");
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 75;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("\u0110\u00F3ng");
		createContents();
		init();
	}

	private void init() {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mdf.addDate(thisDay, -365));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime.setDate(year, month, day);
		cal.setTime(mdf.addDate(thisDay, +1));
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_1.setDate(year, month, day);
	}

	protected void Xoa_LenhDieuxe() throws SQLException {
		TableItem[] ti = table.getSelection();
		if (ti.length > 0) {
			LENH_DIEU_XE l = (LENH_DIEU_XE) ti[0].getData();
			controler.getControl_LENH_DIEU_XE().detele_LENH_DIEU_XE(l);
			fillTable(p, Date_Batdau, Date_Ketthuc);
		}
	}

	protected void SuaThongtinLenh() throws SQLException {
		TableItem[] ti = table.getSelection();
		if (ti.length > 0) {
			LENH_DIEU_XE l = (LENH_DIEU_XE) ti[0].getData();
			QuanLy_Lenhdieuxe ldx = new QuanLy_Lenhdieuxe(getShell(), SWT.DIALOG_TRIM, user, l, false);
			ldx.open();
			fillTable(p, Date_Batdau, Date_Ketthuc);
		}
	}

	protected void Huy_LaplaiLenh() throws SQLException {
		TableItem[] ti = table.getSelection();
		if (ti.length > 0) {
			LENH_DIEU_XE l = (LENH_DIEU_XE) ti[0].getData();
			if (l.getHUY_LENH() == 1) {
				controler.getControl_LENH_DIEU_XE().set_Huylenh(l, true);
			} else {
				controler.getControl_LENH_DIEU_XE().set_Huylenh(l, false);
			}
		}
		fillTable(p, Date_Batdau, Date_Ketthuc);
	}

	protected void Xem_Lenh_DieuXe() throws SQLException {
		TableItem[] ti = table.getSelection();
		if (ti.length > 0) {
			LENH_DIEU_XE l = (LENH_DIEU_XE) ti[0].getData();
			QuanLy_Lenhdieuxe ldx = new QuanLy_Lenhdieuxe(getShell(), SWT.DIALOG_TRIM, user, l, true);
			ldx.open();
		}
	}

	private void setItem_Quy(TreeItem tatca_Nam) throws SQLException {
		Date Date_Ketthuc = controler.getControl_LENH_DIEU_XE().get_NgayCuoicung_DIEUXE();
		Date Date_Batdau = controler.getControl_LENH_DIEU_XE().get_NgayDautien_DIEUXE();
		ArrayList<String> Nam_list = getListyear(Date_Batdau, Date_Ketthuc);
		ArrayList<String> Quy_list = new ArrayList<>();
		Quy_list.add("Quý I");
		Quy_list.add("Quý II");
		Quy_list.add("Quý III");
		Quy_list.add("Quý IV");
		ArrayList<String> Thang_list = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				Thang_list.add("0" + i);
			} else {
				Thang_list.add("" + i);
			}
		}
		for (String N : Nam_list) {
			TreeItem t = new TreeItem(tatca_Nam, SWT.NONE);
			t.setText("Năm " + N);
			startDate_endDate sen = new startDate_endDate();
			sen.setStartDate(N + "-01-01");
			sen.setEndDate(N + "-12-31");
			t.setData(sen);
			int quy = 0;
			for (String Q : Quy_list) {
				TreeItem q = new TreeItem(t, SWT.NONE);
				q.setText(Q);
				startDate_endDate seq = new startDate_endDate();
				int startThang = quy * 3 + 1;
				if (startThang < 10) {
					seq.setStartDate(N + "-0" + startThang + "-" + "01");
				} else {
					seq.setStartDate(N + "-" + startThang + "-" + "01");
				}
				int endThang = quy * 3 + 3;
				GregorianCalendar gcQuy = new GregorianCalendar(Integer.valueOf(N), Integer.valueOf(endThang), -1);
				Date monthEndDate_quy = new Date(gcQuy.getTime().getTime());
				seq.setEndDate(mdf.getSQLStringDate(monthEndDate_quy));
				q.setData(seq);
				for (int i = quy * 3; i < quy * 3 + 3; i++) {
					TreeItem th = new TreeItem(q, SWT.None);
					th.setText("Tháng " + Thang_list.get(i));
					startDate_endDate se = new startDate_endDate();
					if (startThang < 10) {
						se.setStartDate(N + "-" + Thang_list.get(i) + "-" + "01");
					} else {
						se.setStartDate(N + "-" + Thang_list.get(i) + "-" + "01");
					}
					GregorianCalendar gc = new GregorianCalendar(Integer.valueOf(N), Integer.valueOf(Thang_list.get(i)),
							-1);
					java.util.Date monthEndDate = new java.util.Date(gc.getTime().getTime());
					se.setEndDate(mdf.getSQLStringDate(monthEndDate));
					th.setData(se);
				}
				quy++;
				q.setExpanded(true);
			}
			tatca_Nam.setExpanded(true);
			t.setExpanded(true);
		}
	}

	private ArrayList<String> getListyear(Date date_Batdau, Date date_Ketthuc) {
		ArrayList<String> result = new ArrayList<>();
		if (date_Batdau != null && date_Ketthuc != null) {

			int nambatdau = mdf.getYear(date_Batdau);
			int namketthuc = mdf.getYear(date_Ketthuc);
			for (int i = nambatdau; i <= namketthuc; i++) {
				result.add(String.valueOf(i));
			}
		}
		return result;
	}

	private void fillTable(PHONGBAN dv, Date Date_Batdau, Date Date_Ketthuc) throws SQLException {
		table.removeAll();
		ArrayList<LENH_DIEU_XE> ldxl = controler.getControl_LENH_DIEU_XE().get_List_LENH_DIEU_XE(dv, Date_Batdau,
				Date_Ketthuc, HuyLenh);
		int i = 1;
		if (ldxl != null)
			for (LENH_DIEU_XE l : ldxl) {
				TableItem t = new TableItem(table, SWT.NONE);
				PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
						.get_PHUONGTIEN_GIAOTHONG(l.getMA_PHUONGTIEN_GIAOTHONG());
				NGUOIDUNG canbolai = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(l.getTEN_TAI_KHOAN());
				NGUOIDUNG canbotaolenh = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(l.getNGUOI_TAO_LENH());
				String NGAYKHOIHANH = mdf.getViewStringDate(l.getNGAY_DI());
				t.setText(new String[] { "" + i, String.valueOf(l.getMA_LENH_DIEU_XE()), pg.getBIENSO(),
						l.getDIADIEM_GIODON(), NGAYKHOIHANH, canbolai.getTEN_CAN_BO(), canbotaolenh.getTEN_CAN_BO() });
				t.setData(l);
				i++;
			}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("L\u1ECBch s\u1EED \u0111i\u1EC1u xe");
		setSize(728, 450);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
