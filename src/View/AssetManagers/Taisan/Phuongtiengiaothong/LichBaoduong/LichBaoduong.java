package View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.LichBaoduong_ROW;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import View.Template.TreeTemplate;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class LichBaoduong extends Dialog {

	protected Object result;
	protected Shell shlLchBoDng;
	private Table table_LoaiHinhbaoduong;
	private Table table_DanhsachBaoduong_Oto;
	private Table table_DanhsachBaoduong_Xemay;
	private DateTime dateTime_End;
	protected PHONGBAN phongban_Selected;
	protected int LoaiHinhbaoduong;
	private final NGUOIDUNG user;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private Text text;
	String ERROR_EXCEPTION_oldText = "";
	private static Log log = LogFactory.getLog(LichBaoduong.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public LichBaoduong(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
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
		shlLchBoDng.open();
		shlLchBoDng.layout();
		Display display = getParent().getDisplay();
		while (!shlLchBoDng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlLchBoDng = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlLchBoDng.setImage(
				SWTResourceManager.getImage(LichBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlLchBoDng.setSize(728, 450);
		shlLchBoDng.setText("L\u1ECBch B\u1EA3o d\u01B0\u1EE1ng Ph\u01B0\u01A1ng ti\u1EC7n giao th\u00F4ng");
		shlLchBoDng.setLayout(new GridLayout(6, false));
		new FormTemplate().setCenterScreen(shlLchBoDng);
		SashForm sashForm = new SashForm(shlLchBoDng, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		table_LoaiHinhbaoduong = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_LoaiHinhbaoduong.setHeaderVisible(true);
		table_LoaiHinhbaoduong.setLinesVisible(true);
		table_LoaiHinhbaoduong.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TableItem[] items = table_LoaiHinhbaoduong.getSelection();
					if (items.length > 0) {
						LoaiHinhbaoduong = (int) items[0].getData();
						fillDanhsachBaoduong(phongban_Selected, LoaiHinhbaoduong, dateTime_End);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		TableColumn tblclmnStt = new TableColumn(table_LoaiHinhbaoduong, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnLoiHnhBo = new TableColumn(table_LoaiHinhbaoduong, SWT.NONE);
		tblclmnLoiHnhBo.setWidth(200);
		tblclmnLoiHnhBo.setText("LO\u1EA0I H\u00CCNH B\u1EA2O D\u01AF\u1EE0NG");

		Tree tree = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		(new TreeTemplate(user)).getTreePHONGBAN(tree);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						phongban_Selected = (PHONGBAN) items[0].getData();
						fillDanhsachBaoduong(phongban_Selected, LoaiHinhbaoduong, dateTime_End);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		sashForm_1.setWeights(new int[] { 1000, 618 });

		TabFolder tabFolder_1 = new TabFolder(sashForm, SWT.NONE);

		TabItem tbtmt = new TabItem(tabFolder_1, SWT.NONE);
		tbtmt.setText("\u00D4 t\u00F4");

		table_DanhsachBaoduong_Oto = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmt.setControl(table_DanhsachBaoduong_Oto);
		table_DanhsachBaoduong_Oto.setHeaderVisible(true);
		table_DanhsachBaoduong_Oto.setLinesVisible(true);

		TableColumn tblclmnStt_1 = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnStt_1.setWidth(45);
		tblclmnStt_1.setText("STT");

		TableColumn tblclmnBinS = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnBinS.setWidth(100);
		tblclmnBinS.setText("BI\u1EC2N S\u1ED0");

		TableColumn tblclmnLoiXe = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnLoiXe.setWidth(100);
		tblclmnLoiXe.setText("LOẠI XE");

		TableColumn tblclmnKHn_1 = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnKHn_1.setWidth(100);
		tblclmnKHn_1.setText("K\u1EF2 H\u1EA0N");

		TableColumn tblclmnLnBoDng = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnLnBoDng.setWidth(200);
		tblclmnLnBoDng.setText("L\u1EA6N B\u1EA2O D\u01AF\u1EE0NG TR\u01AF\u1EDAC");

		TableColumn tblclmnNgayKetThuc = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnNgayKetThuc.setWidth(120);
		tblclmnNgayKetThuc.setText("NGÀY HOÀN THÀNH");

		TableColumn tblclmnTnPtts = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnTnPtts.setWidth(100);
		tblclmnTnPtts.setText("T\u00CAN PTTS");

		TableColumn tblclmnMPtgt = new TableColumn(table_DanhsachBaoduong_Oto, SWT.NONE);
		tblclmnMPtgt.setWidth(100);
		tblclmnMPtgt.setText("M\u00C3 PTGT");

		Menu menu = new Menu(table_DanhsachBaoduong_Oto);
		table_DanhsachBaoduong_Oto.setMenu(menu);

		MenuItem mntmXemTiuChun = new MenuItem(menu, SWT.NONE);
		mntmXemTiuChun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TieuchuanBaoduong tcbd = new TieuchuanBaoduong(shlLchBoDng, SWT.DIALOG_TRIM, user);
					tcbd.open();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemTiuChun.setText("Xem tiêu chuẩn bảo dưỡng");

		TabItem tbtmXeMy = new TabItem(tabFolder_1, SWT.NONE);
		tbtmXeMy.setText("Xe m\u00E1y");

		table_DanhsachBaoduong_Xemay = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_DanhsachBaoduong_Xemay.setLinesVisible(true);
		table_DanhsachBaoduong_Xemay.setHeaderVisible(true);
		tbtmXeMy.setControl(table_DanhsachBaoduong_Xemay);

		TableColumn tableColumn = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("BI\u1EC2N S\u1ED0");

		TableColumn tblclmnLoiXe_1 = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tblclmnLoiXe_1.setWidth(100);
		tblclmnLoiXe_1.setText("LOẠI XE");

		TableColumn tblclmnKHn = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tblclmnKHn.setWidth(100);
		tblclmnKHn.setText("KỲ HẠN");

		TableColumn tableColumn_2 = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tableColumn_2.setWidth(180);
		tableColumn_2.setText("L\u1EA6N B\u1EA2O D\u01AF\u1EE0NG TR\u01AF\u1EDAC");

		TableColumn tblclmnNewColumn = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("NGÀY HOÀN THÀNH");

		TableColumn tableColumn_3 = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("T\u00CAN PTTS");

		TableColumn tableColumn_4 = new TableColumn(table_DanhsachBaoduong_Xemay, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("M\u00C3 PTGT");

		Menu menu_1 = new Menu(table_DanhsachBaoduong_Xemay);
		table_DanhsachBaoduong_Xemay.setMenu(menu_1);

		MenuItem menuItem = new MenuItem(menu_1, SWT.NONE);
		menuItem.setText("Xem tiêu chuẩn bảo dưỡng");
		sashForm.setWeights(new int[] { 618, 1000 });

		Label lblThiHn = new Label(shlLchBoDng, SWT.NONE);
		lblThiHn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblThiHn.setText("Thời gian tính:");

		text = new Text(shlLchBoDng, SWT.BORDER | SWT.RIGHT);
		text.setText("120");
		text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				ERROR_EXCEPTION_oldText = text.getText();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					updateDate();
				} catch (Exception e2) {
					text.setText(ERROR_EXCEPTION_oldText);
				}
			}
		});
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 100;
		text.setLayoutData(gd_text);

		Label lblNgay = new Label(shlLchBoDng, SWT.NONE);
		lblNgay.setText("ngày ");

		dateTime_End = new DateTime(shlLchBoDng, SWT.BORDER);
		dateTime_End.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));

		Button btnXemDanhSch = new Button(shlLchBoDng, SWT.NONE);
		btnXemDanhSch.setImage(
				SWTResourceManager.getImage(LichBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnXemDanhSch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table_LoaiHinhbaoduong.getSelection();
				if (ti.length > 0) {
					int select = (int) ti[0].getData();
					DanhSachDenghiBaoduong dsdn = new DanhSachDenghiBaoduong(shlLchBoDng, SWT.DIALOG_TRIM,
							phongban_Selected, select, table_DanhsachBaoduong_Oto.getItems(), user);
					try {
						dsdn.open();
					} catch (SQLException e1) {
						log.error(e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
		btnXemDanhSch.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnXemDanhSch.setText("Xem danh sách đề nghị");

		Button btnng = new Button(shlLchBoDng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLchBoDng.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected void updateDate() {
		int date = Integer.valueOf(text.getText());
		Date d = mdf.addDate(new Date(), -date);
		dateTime_End.setDate(mdf.getYear(d), mdf.getMonth(d), mdf.getDay(d));
	}

	protected void fillDanhsachBaoduong(PHONGBAN phongban_Selected, int i, DateTime dateTime_End) throws SQLException {

		ArrayList<LichBaoduong_ROW> lbdr = null;
		switch (i) {
		case 1:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_Thaynhot(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 2:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayLocnhot(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 3:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayLocgio(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 4:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayLocNhienlieu(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 5:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayDauphanh_Daulyhop(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 6:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayDauhopso(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 7:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayDauvisai(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 8:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayLocgioGianlanh(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 9:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayDauTroluclai(phongban_Selected,
					getDate(dateTime_End));
			break;
		case 10:
			lbdr = controler.getControl_PHUONGTIEN_GIAOTHONG().getLichbaoduong_ThayBaoduongkhac(phongban_Selected,
					getDate(dateTime_End));
			break;

		default:
			break;
		}
		FillTable(lbdr, i);
	}

	private void FillTable(ArrayList<LichBaoduong_ROW> lbdr, int i) throws SQLException {
		table_DanhsachBaoduong_Oto.removeAll();
		table_DanhsachBaoduong_Xemay.removeAll();
		if (lbdr != null) {
			int idx = 1;
			for (LichBaoduong_ROW lichBaoduong_ROW : lbdr) {
				TableItem tbi = new TableItem((lichBaoduong_ROW.getLoaixe().getOTO_XEMAY() == 1)
						? table_DanhsachBaoduong_Oto : table_DanhsachBaoduong_Xemay, SWT.NULL);
				GIAI_DOAN_NGHIEM_THU gnt = null;
				if (lichBaoduong_ROW.getDotBaoduong() != null)
					gnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(lichBaoduong_ROW.getDotBaoduong());
				switch (i) {
				case 1:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-" : lichBaoduong_ROW.getHtbd().getThayNhot() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 2:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-" : lichBaoduong_ROW.getHtbd().getThayLocNhot() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 3:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-" : lichBaoduong_ROW.getHtbd().getThayLocgio() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 4:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayLocnhienlieu() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 5:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayDauphanh_Daulyhop() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 6:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayDauhopso() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 7:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayDauVisai() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 8:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayLocgioGianlanh() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 9:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getThayDautroluclai() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;
				case 10:
					tbi.setText(new String[] { idx + "", lichBaoduong_ROW.getPhuongtienGiaothong().getBIENSO(),
							lichBaoduong_ROW.getLoaixe().getTEN_DONG_XE(),
							lichBaoduong_ROW.getHtbd() == null ? "-"
									: lichBaoduong_ROW.getHtbd().getBaoduongkhac() + "",
							lichBaoduong_ROW.getDotBaoduong() == null ? "Chưa thực hiện bảo dưỡng"
									: lichBaoduong_ROW.getDotBaoduong().getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(),
							gnt == null ? "-" : mdf.getViewStringDate(gnt.getTHOI_DIEM_KET_THUC()),
							lichBaoduong_ROW.getTaisan().getTEN_TAISAN(),
							lichBaoduong_ROW.getPhuongtienGiaothong().getMA_PHUONGTIEN_GIAOTHONG() + "" });
					break;

				default:
					break;
				}
				tbi.setData(lichBaoduong_ROW);
				idx++;
			}
		}
	}

	private void init() {
		table_LoaiHinhbaoduong.removeAll();
		@SuppressWarnings("unused")
		TableItem ti;
		ti = CreateItem(1, "THAY_NHOT", "Thay nhớt");
		ti = CreateItem(2, "THAY_LOC_NHOT", "Thay Lọc nhớt");
		ti = CreateItem(3, "THAY_LOC_GIO", "Thay Lọc gió");
		ti = CreateItem(4, "THAY_LOC_NHIEN_LIEU", "Thay Lọc nhiên liệu");
		ti = CreateItem(5, "THAY_DAU_PHANH_DAU_LY_HOP", "Thay Dầu phanh - Dầu ly hợp");
		ti = CreateItem(6, "THAY_DAU_HOP_SO", "Thay Dầu hộp số");
		ti = CreateItem(7, "THAY_DAU_VI_SAI", "Thay Dầu vi sai");
		ti = CreateItem(8, "THAY_LOC_GIO_GIAN_LANH", "Thay Lọc gió giàn lạnh");
		ti = CreateItem(9, "THAY_DAU_TRO_LUC_LAI", "Thay Dầu Trợ lực lái");
		ti = CreateItem(10, "BAO_DUONG_KHAC", "Thay Bảo dưỡng khác");
		updateDate();
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

	private TableItem CreateItem(int index, String SQL_NAME, String Name) {
		TableItem ti = new TableItem(table_LoaiHinhbaoduong, SWT.NONE);
		ti.setText(new String[] { index + "", Name });
		HinhthucBaoduong htbd = new HinhthucBaoduong(index, Name);
		htbd.setSQL_NAME(SQL_NAME);
		ti.setData(index);

		return ti;
	}
}
