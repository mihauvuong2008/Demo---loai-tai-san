package View.AssetManagers.CongViec.Chart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import View.AssetManagers.CongViec.Chart.GRANTTChart.GranttChart;
import View.AssetManagers.CongViec.Chart.PIEChart.PieChart;
import View.AssetManagers.CongViec.Chart.XYLINEChart.XYLineChart;
import View.AssetManagers.CongViec.CongviecDahoanthanh.Nhatky_Lamviec;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Chart extends Dialog {

	protected Object result;
	protected Shell shlBi;
	private Composite composite_BieuDo_Grantt;
	private static NGUOIDUNG user;
	private Composite composite_PieChart;
	private Composite composite_Xychart;
	private SashForm sashForm_3;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private Composite composite_KhoiluongCongviec;
	private Button btnTatCa;
	private Button btnThanhLy;
	private Button btnMuaSam;
	private Button btnSuaChua;
	protected GranttChart granttChart;
	protected XYLineChart xyLineChart;
	private DateTime dateTime_End_Pie;
	private Text text_Search_pie;
	protected PieChart pieChart;
	private DateTime dateTime_Start_Pie;
	protected final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Chart.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Chart(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		Chart.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlBi.open();
		shlBi.layout();
		Display display = getParent().getDisplay();
		while (!shlBi.isDisposed()) {
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
		shlBi = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlBi.setImage(SWTResourceManager.getImage(Chart.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlBi.setSize(875, 540);
		new FormTemplate().setCenterScreen(shlBi);
		shlBi.setText("Biểu đồ");
		shlBi.setLayout(new GridLayout(1, false));

		TabFolder tabFolder = new TabFolder(shlBi, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabItem tbtmTinCng = new TabItem(tabFolder, SWT.NONE);
		tbtmTinCng.setText("Tiến độ Công việc đang thực hiện");

		SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
		tbtmTinCng.setControl(sashForm);

		TabItem tbtmChiTitCng = new TabItem(tabFolder, SWT.NONE);
		tbtmChiTitCng.setText("Khối lượng công việc");

		sashForm_3 = new SashForm(tabFolder, SWT.NONE);
		tbtmChiTitCng.setControl(sashForm_3);

		composite_KhoiluongCongviec = new Composite(sashForm_3, SWT.NONE);
		composite_KhoiluongCongviec.setLayout(new GridLayout(5, false));

		composite_Xychart = new Composite(composite_KhoiluongCongviec, SWT.EMBEDDED);
		composite_Xychart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		Label lblTNgy = new Label(composite_KhoiluongCongviec, SWT.NONE);
		lblTNgy.setText("Từ ngày: ");

		dateTime_Begin = new DateTime(composite_KhoiluongCongviec, SWT.BORDER);

		Label lblnNgy = new Label(composite_KhoiluongCongviec, SWT.NONE);
		lblnNgy.setText("Đến ngày:");

		dateTime_End = new DateTime(composite_KhoiluongCongviec, SWT.BORDER);

		Button btnXem = new Button(composite_KhoiluongCongviec, SWT.NONE);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (xyLineChart == null)
					xyLineChart = new XYLineChart(user);
				try {
					xyLineChart.Draw(composite_Xychart, mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End));
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				composite_Xychart.redraw();
			}
		});
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.setText("Xem");

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));

		composite_BieuDo_Grantt = new Composite(composite, SWT.EMBEDDED);
		composite_BieuDo_Grantt.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		composite_BieuDo_Grantt.setLayout(new GridLayout(1, false));

		btnSuaChua = new Button(composite, SWT.CHECK);
		btnSuaChua.setText("Sửa chữa - Bảo dưỡng");

		btnMuaSam = new Button(composite, SWT.CHECK);
		btnMuaSam.setText("Mua sắm");

		btnThanhLy = new Button(composite, SWT.CHECK);
		btnThanhLy.setText("Thanh lý");

		btnTatCa = new Button(composite, SWT.CHECK);
		btnTatCa.setText("Tất cả");

		Button btnXem_1 = new Button(composite, SWT.NONE);
		btnXem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					final int OPTION;
					int suachua = 0;
					if (btnSuaChua.getSelection())
						suachua = 1;
					int muasam = 0;
					if (btnMuaSam.getSelection())
						muasam = 2;
					int thanhly = 0;
					if (btnThanhLy.getSelection())
						thanhly = 4;
					if (btnTatCa.getSelection())
						OPTION = 7;
					else
						OPTION = muasam + suachua + thanhly;
					if (granttChart == null)
						granttChart = new GranttChart(user);
					granttChart.Draw(composite_BieuDo_Grantt, OPTION);
					granttChart.reDraw();
					composite_BieuDo_Grantt.redraw();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXem_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem_1.widthHint = 75;
		btnXem_1.setLayoutData(gd_btnXem_1);
		btnXem_1.setText("Xem");
		sashForm.setWeights(new int[] { 528 });

		TabItem tbtmChiTit = new TabItem(tabFolder, SWT.NONE);
		tbtmChiTit.setText("Chi tiết công việc đã thực hiện");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmChiTit.setControl(composite_1);
		composite_1.setLayout(new GridLayout(6, false));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		SashForm sashForm_2 = new SashForm(sashForm_1, SWT.VERTICAL);

		Tree tree_DanhsachCongviec_Pie = new Tree(sashForm_2, SWT.BORDER | SWT.FULL_SELECTION);
		tree_DanhsachCongviec_Pie.setLinesVisible(true);
		tree_DanhsachCongviec_Pie.setHeaderVisible(true);
		tree_DanhsachCongviec_Pie.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				TreeItem[] til = tree_DanhsachCongviec_Pie.getSelection();
				if (til.length > 0) {
					Object congviec = til[0].getData();
					if (pieChart == null)
						pieChart = new PieChart(user);
					try {
						pieChart.Draw(composite_PieChart, congviec);
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});

		TreeColumn treeColumn = new TreeColumn(tree_DanhsachCongviec_Pie, SWT.CENTER);
		treeColumn.setWidth(60);
		treeColumn.setText("STT");

		TreeColumn trclmnMCngVic = new TreeColumn(tree_DanhsachCongviec_Pie, SWT.LEFT);
		trclmnMCngVic.setWidth(100);
		trclmnMCngVic.setText("MÃ CÔNG VIỆC");

		TreeColumn trclmnTnCngVic = new TreeColumn(tree_DanhsachCongviec_Pie, SWT.LEFT);
		trclmnTnCngVic.setWidth(129);
		trclmnTnCngVic.setText("TÊN CÔNG VIỆC");

		TreeColumn trclmnNgyBtu = new TreeColumn(tree_DanhsachCongviec_Pie, SWT.CENTER);
		trclmnNgyBtu.setWidth(100);
		trclmnNgyBtu.setText("NGÀY BẮT ĐẦU");

		TreeColumn trclmnNgyHonThnh = new TreeColumn(tree_DanhsachCongviec_Pie, SWT.NONE);
		trclmnNgyHonThnh.setWidth(100);
		trclmnNgyHonThnh.setText("NGÀY HOÀN THÀNH");

		Menu menu = new Menu(tree_DanhsachCongviec_Pie);
		tree_DanhsachCongviec_Pie.setMenu(menu);

		MenuItem mntmXemNhtK = new MenuItem(menu, SWT.NONE);
		mntmXemNhtK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_DanhsachCongviec_Pie.getSelection();
					if (til.length > 0) {
						Object o = til[0].getData();
						if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG || o instanceof DOT_THUCHIEN_TANG_TAISAN
								|| o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							Nhatky_Lamviec nk = new Nhatky_Lamviec(shlBi, SWT.DIALOG_TRIM, o, user);
							nk.open();
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemNhtK.setText("Xem Nhật ký");
		sashForm_2.setWeights(new int[] { 85 });
		composite_PieChart = new Composite(sashForm_1, SWT.EMBEDDED);
		sashForm_1.setWeights(new int[] { 320, 420 });

		Label lblTNgy_1 = new Label(composite_1, SWT.NONE);
		lblTNgy_1.setText("Từ ngày: ");

		dateTime_Start_Pie = new DateTime(composite_1, SWT.BORDER);

		Label lblnNgy_1 = new Label(composite_1, SWT.NONE);
		lblnNgy_1.setText("Đến ngày: ");

		dateTime_End_Pie = new DateTime(composite_1, SWT.BORDER);

		text_Search_pie = new Text(composite_1, SWT.BORDER | SWT.RIGHT);
		text_Search_pie.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Search_pie.setMessage("Tên công việc, mã công việc");
		Button btnXem_2 = new Button(composite_1, SWT.NONE);
		btnXem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					viewDanhsachCongviec(dateTime_Start_Pie, dateTime_End_Pie, text_Search_pie.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void viewDanhsachCongviec(DateTime dateTime_Start_Pie, DateTime dateTime_End_Pie, String text)
					throws SQLException {
				tree_DanhsachCongviec_Pie.removeAll();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
						.get_All_DotTangTaisan(mdf.getDate(dateTime_Start_Pie), mdf.getDate(dateTime_End_Pie), text);
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
						.get_All_DotGiamTaisan(mdf.getDate(dateTime_Start_Pie), mdf.getDate(dateTime_End_Pie), text);
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
						.get_All_DotSuachua_BaoduongTaisan(mdf.getDate(dateTime_Start_Pie),
								mdf.getDate(dateTime_End_Pie), text);
				ArrayList<Object> data = new ArrayList<>();
				if (dttl != null)
					data.addAll(dttl);
				if (dgtl != null) {
					data.addAll(dgtl);
				}
				if (dsbl != null) {
					data.addAll(dsbl);
				}
				int i = 1;
				for (Object object : data) {
					if (object instanceof DOT_THUCHIEN_TANG_TAISAN) {
						DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) object;

						DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
						GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
						TreeItem ti = new TreeItem(tree_DanhsachCongviec_Pie, SWT.NONE);
						ti.setText(new String[] { (i++) + "", dtt.getMA_DOT_TANG() + "", dtt.getTEN_DOT_TANG(),
								mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()),
								mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()) });
						ti.setData(dtt);
					} else if (object instanceof DOT_THUCHIEN_GIAM_TAISAN) {
						DOT_THUCHIEN_GIAM_TAISAN dtt = (DOT_THUCHIEN_GIAM_TAISAN) object;

						DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
						GIAI_DOAN_THUC_HIEN gdqt = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
						TreeItem ti = new TreeItem(tree_DanhsachCongviec_Pie, SWT.NONE);
						ti.setText(new String[] { (i++) + "", dtt.getMA_DOT_GIAM() + "", dtt.getTEN_DOT_GIAM(),
								mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()),
								mdf.getViewStringDate(gdqt.getTHOI_DIEM_HOAN_THANH()) });
						ti.setData(dtt);
					} else {
						DOT_THUCHIEN_SUACHUA_BAODUONG dtt = (DOT_THUCHIEN_SUACHUA_BAODUONG) object;
						if (dtt != null) {
							DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
							GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);

							TreeItem ti = new TreeItem(tree_DanhsachCongviec_Pie, SWT.NONE);
							ti.setText(new String[] { (i++) + "", dtt.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "",
									dtt.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
									mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()),
									mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()) });
							ti.setData(dtt);
						}
					}
				}
			}
		});
		GridData gd_btnXem_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem_2.widthHint = 75;
		btnXem_2.setLayoutData(gd_btnXem_2);
		btnXem_2.setText("Xem");
		init();
	}

	private void init() {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mdf.addDate(thisDay, -365));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);
		dateTime_Start_Pie.setDate(year, month, day);
	}
}
