package View.AssetManagers.CongViec.CongviecDahoanthanh;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.CONGVIEC_PHANVIEC;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAP_HO_SO;
import View.AssetManagers.Hoso.TapHoso_View;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.swt.widgets.Text;

public class QuanLyCongviec extends Shell {

	private static NGUOIDUNG user;
	private final Controler controler;
	private final int treeHeight = 21;
	private Tree tree_DanhsachCongviec;
	private Tree tree_Hoso;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(QuanLyCongviec.class);
	private Text text_Sodexuat;
	private Text text_Tongngay;
	private Text text_Ghichu;
	private Text text_Timkiems;
	private Text text_Ngaytiepnhan;
	private Text text_Ngaychuyengiao;
	private Text text_Ngayhoanthanh;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanLyCongviec shell = new QuanLyCongviec(display, user);
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
	public QuanLyCongviec(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanLyCongviec.class, "/Actions-view-list-details-icon.png"));
		setLayout(new GridLayout(9, false));
		QuanLyCongviec.user = user;
		controler = new Controler(user);
		TreeRowStyle ts = new TreeRowStyle();
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 9, 1));
		tree_DanhsachCongviec = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tree_DanhsachCongviec.setLinesVisible(true);
		tree_DanhsachCongviec.setHeaderVisible(true);
		tree_DanhsachCongviec.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TreeItem[] til = tree_DanhsachCongviec.getSelection();
					if (til.length > 0) {
						fillDexuat(til[0]);
						fillHoso_Thuchien(til[0]);
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		TreeColumn trclmnStt_7 = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnStt_7.setWidth(50);
		trclmnStt_7.setText("STT");

		TreeColumn trclmnLoiCngVic_2 = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnLoiCngVic_2.setWidth(120);
		trclmnLoiCngVic_2.setText("LOẠI CÔNG VIỆC");

		TreeColumn trclmnTin_2 = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnTin_2.setWidth(150);
		trclmnTin_2.setText("TÊN CÔNG VIỆC");

		TreeColumn trclmnMT = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnMT.setWidth(200);
		trclmnMT.setText("MÔ TẢ");

		TreeColumn trclmnNgaybatdau = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnNgaybatdau.setWidth(120);
		trclmnNgaybatdau.setText("NGÀY BẮT ĐẦU");

		TreeColumn trclmnNgyKtThc = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnNgyKtThc.setWidth(120);
		trclmnNgyKtThc.setText("NGÀY KẾT THÚC");

		TreeColumn trclmnSNgyThc = new TreeColumn(tree_DanhsachCongviec, SWT.NONE);
		trclmnSNgyThc.setWidth(100);
		trclmnSNgyThc.setText("ID");
		ts.setTreeItemHeight(tree_DanhsachCongviec, treeHeight);

		Menu menu = new Menu(tree_DanhsachCongviec);
		tree_DanhsachCongviec.setMenu(menu);

		MenuItem mntmXemNhtK = new MenuItem(menu, SWT.NONE);
		mntmXemNhtK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_DanhsachCongviec.getSelection();
					if (til.length > 0) {
						Object o = til[0].getData();
						if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG || o instanceof DOT_THUCHIEN_TANG_TAISAN
								|| o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							Nhatky_Lamviec nk = new Nhatky_Lamviec(getShell(), SWT.DIALOG_TRIM, o, user);
							nk.open();
						} else {
							TreeItem Parent = til[0].getParentItem();
							Nhatky_Lamviec nk = new Nhatky_Lamviec(getShell(), SWT.DIALOG_TRIM, Parent.getData(), user);

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

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_DanhsachCongviec.getSelection();
					if (til.length > 0) {
						Object o = til[0].getData();
						if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
									.delete_DOT_THUCHIEN_SUACHUA_BAODUONG((DOT_THUCHIEN_SUACHUA_BAODUONG) o);
						} else if (o instanceof DOT_THUCHIEN_TANG_TAISAN) {
							controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
									.delete_DOT_THUCHIEN_TANG_TAISAN((DOT_THUCHIEN_TANG_TAISAN) o);
						} else if (o instanceof DOT_THUCHIEN_GIAM_TAISAN) {

							controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
									.delete_DOT_THUCHIEN_GIAM_TAISAN((DOT_THUCHIEN_GIAM_TAISAN) o);
						}
					} else {
						CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) til[0].getData();
						if (p != null)
							if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
								controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
										.delete_DOT_THUCHIEN_SUACHUA_BAODUONG(
												(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC());
							} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
								controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
										.delete_DOT_THUCHIEN_TANG_TAISAN((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
							} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
								controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
										.delete_DOT_THUCHIEN_GIAM_TAISAN((DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC());
							}
					}
					fillDanhsachCongviec(text_Timkiems.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmXa.setText("Xóa Công việc");

		SashForm sashForm_4 = new SashForm(sashForm, SWT.VERTICAL);

		Composite group = new Composite(sashForm_4, SWT.NONE);
		group.setLayout(new GridLayout(2, false));

		Label label = new Label(group, SWT.NONE);
		label.setText("Số đề xuất:");

		text_Sodexuat = new Text(group, SWT.NONE);
		text_Sodexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Sodexuat.setEditable(false);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("Tiếp nhận:");

		text_Ngaytiepnhan = new Text(group, SWT.NONE);
		text_Ngaytiepnhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaytiepnhan.setEditable(false);
		text_Ngaytiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("Chuyển giao:");

		text_Ngaychuyengiao = new Text(group, SWT.NONE);
		text_Ngaychuyengiao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaychuyengiao.setEditable(false);
		text_Ngaychuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("Hoàn thành:");

		text_Ngayhoanthanh = new Text(group, SWT.NONE);
		text_Ngayhoanthanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngayhoanthanh.setEditable(false);
		text_Ngayhoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("Tổng (ngày):");

		text_Tongngay = new Text(group, SWT.NONE);
		text_Tongngay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tongngay.setEditable(false);
		text_Tongngay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_5 = new Label(group, SWT.NONE);
		GridData gd_label_5 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_5.verticalIndent = 3;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("Ghi chú:");

		text_Ghichu = new Text(group, SWT.NONE);
		text_Ghichu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ghichu.setEditable(false);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tree_Hoso = new Tree(sashForm_4, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Hoso.setLinesVisible(true);
		tree_Hoso.setHeaderVisible(true);

		TreeColumn trclmnStt_8 = new TreeColumn(tree_Hoso, SWT.NONE);
		trclmnStt_8.setWidth(55);
		trclmnStt_8.setText("STT");

		TreeColumn trclmnTnTiSn = new TreeColumn(tree_Hoso, SWT.NONE);
		trclmnTnTiSn.setWidth(100);
		trclmnTnTiSn.setText("TÊN HỒ SƠ");

		TreeColumn trclmnnV = new TreeColumn(tree_Hoso, SWT.NONE);
		trclmnnV.setWidth(100);
		trclmnnV.setText("GIỚI THIỆU");

		TreeColumn trclmnMTiSn = new TreeColumn(tree_Hoso, SWT.NONE);
		trclmnMTiSn.setWidth(100);
		trclmnMTiSn.setText("NGƯỜI TẠO");

		Menu menu_1 = new Menu(tree_Hoso);
		tree_Hoso.setMenu(menu_1);

		MenuItem mntmXem = new MenuItem(menu_1, SWT.NONE);
		mntmXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] tbi = tree_Hoso.getSelection();
				if (tbi.length > 0) {
					TAP_HO_SO ths = (TAP_HO_SO) tbi[0].getData();
					TapHoso_View ths_V = new TapHoso_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
					try {
						ths_V.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXem.setText("Xem Tập hồ sơ");
		sashForm_4.setWeights(new int[] { 618, 1000 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Label lblTNgy = new Label(this, SWT.NONE);
		lblTNgy.setText("Từ ngày: ");

		dateTime_Begin = new DateTime(this, SWT.BORDER);

		Label lblnNgy = new Label(this, SWT.NONE);
		lblnNgy.setText("Đến ngày: ");

		dateTime_End = new DateTime(this, SWT.BORDER);
		new Label(this, SWT.NONE);

		Label lblTn = new Label(this, SWT.NONE);
		lblTn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTn.setText("Tên: ");

		text_Timkiems = new Text(this, SWT.BORDER);
		text_Timkiems.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnXem = new Button(this, SWT.NONE);
		btnXem.setImage(SWTResourceManager.getImage(QuanLyCongviec.class, "/Accept-icon (1).png"));
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					fillDanhsachCongviec(text_Timkiems.getText());
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

	protected void fillDexuat(TreeItem treeItem) throws SQLException {
		Object Congviec = treeItem.getData();
		DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(Congviec);
		refreshField();
		if (dx == null)
			return;
		try {
			text_Sodexuat.setText(dx.getSODEXUAT());
			text_Ngaytiepnhan.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
			text_Ngaychuyengiao.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
			text_Ngayhoanthanh.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_HOAN_THANH()));
			text_Tongngay.setText(mdf.daysBetween(dx.getTHOI_DIEM_BAT_DAU(), dx.getTHOI_DIEM_HOAN_THANH()) + "");
			text_Ghichu.setText(dx.getGHI_CHU());
		} catch (Exception e) {
			return;
		}
	}

	private void refreshField() {
		text_Sodexuat.setText("");
		text_Ngaytiepnhan.setText("");
		text_Ngaychuyengiao.setText("");
		text_Ngayhoanthanh.setText("");
		text_Tongngay.setText("");
		text_Ghichu.setText("");
	}

	private void init() throws SQLException {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mdf.addDate(thisDay, -365));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);
		fillDanhsachCongviec(text_Timkiems.getText());
	}

	protected void fillHoso_Thuchien(TreeItem ti) throws SQLException {
		tree_Hoso.removeAll();
		Object o = ti.getData();
		Integer i = new Integer(1);/* index */
		if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG || o instanceof DOT_THUCHIEN_TANG_TAISAN
				|| o instanceof DOT_THUCHIEN_GIAM_TAISAN) {/* ti: [Congviec] */
			TreeItem[] child = ti.getItems();
			i = Hoso_Phanviec(child[0], i);
			i = Hoso_Phanviec(child[1], i);
			if (child.length > 2) {/* [congviec] toi thieu 02 bo ho so */
				i = Hoso_Phanviec(child[2], i);
				i = Hoso_Phanviec(child[3], i);
			}
		} else {
			i = Hoso_Phanviec(ti, i);
		}
	}

	/**
	 * 
	 * 
	 * 
	 */

	private Integer Hoso_Phanviec(TreeItem treeItem, Integer i) throws SQLException {
		Object o = treeItem.getData();
		if (o instanceof DE_XUAT) {
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(((DE_XUAT) o).getMA_TAPHOSO());
			NGUOIDUNG nd_Dexuat = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(((DE_XUAT) o).getTEN_TAI_KHOAN());
			if (ths != null) {
				TreeItem titem = new TreeItem(tree_Hoso, SWT.NONE);
				titem.setText(new String[] { i + "", ths.getTEN_TAPHOSO(), ths.getGIOITHIEU_TAPHOSO(),
						nd_Dexuat.getTEN_CAN_BO() });
				titem.setData(ths);
				i++;
			}

		} else if (o instanceof GIAI_DOAN_THUC_HIEN) {
			GIAI_DOAN_THUC_HIEN th = (GIAI_DOAN_THUC_HIEN) o;
			ArrayList<NGUOIDUNG_THUCHIEN> ndthl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(th);
			if (ndthl != null)
				for (NGUOIDUNG_THUCHIEN ndth : ndthl) {
					TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth.getMA_TAPHOSO());
					if (ths != null) {
						TreeItem titem = new TreeItem(tree_Hoso, SWT.NONE);
						NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
						titem.setText(new String[] { i + "", ths.getTEN_TAPHOSO(), ths.getGIOITHIEU_TAPHOSO(),
								nd.getTEN_CAN_BO() });
						titem.setData(ths);
						i++;
					}
				}
		} else if (o instanceof GIAI_DOAN_NGHIEM_THU) {
			GIAI_DOAN_NGHIEM_THU th = (GIAI_DOAN_NGHIEM_THU) o;
			ArrayList<NGUOIDUNG_NGHIEMTHU> ndthl = controler.getControl_NGHIEMTHU_CANBO()
					.get_AllNGUOIDUNG_NGHIEMTHU(th);
			if (ndthl != null)
				for (NGUOIDUNG_NGHIEMTHU ndth : ndthl) {
					TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth.getMA_TAPHOSO());
					if (ths != null) {
						TreeItem titem = new TreeItem(tree_Hoso, SWT.NONE);
						NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
						titem.setText(new String[] { i + "", ths.getTEN_TAPHOSO(), ths.getGIOITHIEU_TAPHOSO(),
								nd.getTEN_CAN_BO() });
						titem.setData(ths);
						i++;
					}
				}
		} else if (o instanceof GIAI_DOAN_QUYET_TOAN) {
			GIAI_DOAN_QUYET_TOAN th = (GIAI_DOAN_QUYET_TOAN) o;
			ArrayList<NGUOIDUNG_QUYETTOAN> ndthl = controler.getControl_QUYETTOAN_CANBO()
					.get_AllNGUOIDUNG_QUYETTOAN(th);
			if (ndthl != null)
				for (NGUOIDUNG_QUYETTOAN ndth : ndthl) {
					TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth.getMA_TAPHOSO());
					if (ths != null) {
						TreeItem titem = new TreeItem(tree_Hoso, SWT.NONE);
						NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
						titem.setText(new String[] { i + "", ths.getTEN_TAPHOSO(), ths.getGIOITHIEU_TAPHOSO(),
								nd.getTEN_CAN_BO() });
						titem.setData(ths);
						i++;
					}
				}
		}
		return i;
	}

	/**
	 * Get All data [Cong viec Da Hoan thanh] and build item Congviec
	 * 
	 * @author NgocDong
	 * @param FindString
	 * @throws SQLException
	 */
	private void fillDanhsachCongviec(String FindString) throws SQLException {
		tree_DanhsachCongviec.removeAll();
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_AndFind_DOT_THUCHIEN_SUACHUA_BAODUONG_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End),
						FindString);
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_AndFind_DOT_THUCHIEN_TANG_TAISAN_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End),
						FindString);
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
				.get_AndFind_DOT_THUCHIEN_GIAM_TAISAN_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End),
						FindString);
		int i = 1;
		if (dsbl != null)
			for (DOT_THUCHIEN_SUACHUA_BAODUONG dsb : dsbl) {
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
				GIAI_DOAN_NGHIEM_THU gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
				GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);

				TreeItem ti = new TreeItem(tree_DanhsachCongviec, SWT.NONE);
				ti.setText(new String[] { i + "", "Công việc Sửa chữa - Bảo dưỡng Ptts",
						dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(), dsb.getMO_TA(),
						getTextDate(dx.getTHOI_DIEM_BAT_DAU()), getTextDate(gdqt.getTHOI_GIAN_KET_THUC()),
						dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "" });
				ti.setData(dsb);
				fillChildItemCongviec(ti, dx, gdth, gdnt, gdqt);
				i++;
			}
		if (dttl != null)
			for (DOT_THUCHIEN_TANG_TAISAN dtt : dttl) {
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
				GIAI_DOAN_NGHIEM_THU gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
				GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);

				TreeItem ti = new TreeItem(tree_DanhsachCongviec, SWT.NONE);
				ti.setText(new String[] { i + "", "Công việc Mua sắm - Tiếp nhận Ptts", dtt.getTEN_DOT_TANG(),
						dtt.getMO_TA(), getTextDate(dx.getTHOI_DIEM_BAT_DAU()),
						getTextDate(gdqt.getTHOI_GIAN_KET_THUC()), dtt.getMA_DOT_TANG() + "" });
				ti.setData(dtt);
				fillChildItemCongviec(ti, dx, gdth, gdnt, gdqt);
				i++;
			}
		if (dgtl != null)
			for (DOT_THUCHIEN_GIAM_TAISAN dgt : dgtl) {
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);

				TreeItem ti = new TreeItem(tree_DanhsachCongviec, SWT.NONE);
				ti.setText(new String[] { i + "", "Công việc Thanh lý - Bàn giao Ptts", dgt.getTEN_DOT_GIAM(),
						dgt.getMO_TA(), getTextDate(dx.getTHOI_DIEM_BAT_DAU()),
						getTextDate(gdth.getTHOI_DIEM_HOAN_THANH()), dgt.getMA_DOT_GIAM() + "" });
				ti.setData(dgt);
				fillChildItemCongviec(ti, dx, gdth, null, null);
				i++;
			}
	}

	/**
	 * get Phan viec and show Child Item in table Congviec
	 * 
	 * @author NgocDong
	 */
	void fillChildItemCongviec(TreeItem parent, DE_XUAT dx, GIAI_DOAN_THUC_HIEN gdth, GIAI_DOAN_NGHIEM_THU gdnt,
			GIAI_DOAN_QUYET_TOAN gdqt) {
		TreeItem child1 = new TreeItem(parent, SWT.NONE);
		if (dx != null) {
			child1.setText(new String[] { 1 + "", "Phê duyệt chủ trương đề xuất", dx.getSODEXUAT(), dx.getGHI_CHU(),
					getTextDate(dx.getTHOI_DIEM_BAT_DAU()), getTextDate(dx.getTHOI_DIEM_HOAN_THANH()),
					dx.getMA_DE_XUAT() + "" });
			child1.setData(dx);
		}
		if (gdth != null) {
			TreeItem child2 = new TreeItem(parent, SWT.NONE);
			child2.setText(new String[] { 2 + "", "Tổ chức thực hiện Công việc", "-", gdth.getGHI_CHU(),
					getTextDate(gdth.getTHOI_DIEM_BAT_DAU()), getTextDate(gdth.getTHOI_DIEM_HOAN_THANH()),
					gdth.getMA_GIAI_DOAN_THUC_HIEN() + "" });
			child2.setData(gdth);
		}
		if (gdnt != null) {
			TreeItem child3 = new TreeItem(parent, SWT.NONE);
			child3.setText(new String[] { 3 + "", "Kiểm tra, Nghiệm thu, Bàn giao", "-", gdnt.getGHI_CHU(),
					getTextDate(gdnt.getTHOI_DIEM_TIEP_NHAN()), getTextDate(gdnt.getTHOI_DIEM_KET_THUC()),
					gdnt.getMA_GIAI_DOAN_NGHIEM_THU() + "" });
			child3.setData(gdnt);
		}
		if (gdqt != null) {
			TreeItem child4 = new TreeItem(parent, SWT.NONE);
			child4.setText(new String[] { 4 + "", "Quyết toán thanh lý hợp đồng", "-", gdqt.getGHI_CHU(),
					getTextDate(gdqt.getTHOI_DIEM_TIEP_NHAN()), getTextDate(gdqt.getTHOI_GIAN_KET_THUC()),
					gdqt.getMA_GIAI_DOAN_QUYET_TOAN() + "" });
			child4.setData(gdqt);
		}
	}

	String getTextDate(Date d) {
		if (d == null)
			return "#";
		return mdf.getViewStringDate(d);
	}

	void treePack(Tree tree) {
		for (TreeColumn tc : tree.getColumns()) {
			tc.pack();
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Công việc đã hoàn thành");
		this.setSize(875, 540);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
