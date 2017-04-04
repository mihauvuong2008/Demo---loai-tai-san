package View.AssetManagers.CongViec.CongviecDangthuchien;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
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
import DAO.NGUONGIAM;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.NGUONTANG;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import DAO.user_congviec;
import View.AssetManagers.CongViec.Baoduong._2_Taodot_Baoduong;
import View.AssetManagers.CongViec.CongviecDahoanthanh.Nhatky_Lamviec;
import View.AssetManagers.CongViec.Giamtaisan.XemDotGiam;
import View.AssetManagers.CongViec.Suachua._2_Taodot_Suachua;
import View.AssetManagers.CongViec.TangTaiSan.XemDotTangtaisan;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.AssetManagers.Taisan.XemTaiSan.View_Taisan;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class GiaoViec {

	protected Shell shlQunLCng;
	private static NGUOIDUNG user;
	private final Controler controler;
	private final int treeHeight = 21;
	private static Tree tree_CongviecSuachua;
	private static Tree tree_CongViecMuasam;
	private static Tree tree_CongviecThanhly;
	private Tree tree_DanhsachCanbo;
	private Tree tree_PhanViecDangThucHien;
	private Tree tree_THUCHIEN;
	private Tree tree_NGHIEMTHU;
	private Tree tree_PhanViecDaThucHien;
	private Text text_Sodexuat;
	private Text text_NgaythangVanban;
	private Text text_DonviDexuat;
	private Text text_Ngaythuchien;
	private Text text_Ghichu;
	private Text text_Nguontang;
	private Text text_Gioithieu;
	private Text text_LienHe;
	private Tree tree_TaiSanXuly;
	private Text text_THUCHIEN_Batdau;
	private Text text_THUCHIEN_Dukien;
	private Text text_THUCHIEN_Hoanthanh;
	private Text text_NGHIEMTHU_Batdau;
	private Text text_NGHIEMTHU_dukien;
	private Text text_NGHIEMTHU_Hoanthanh;
	private Text text_QUYETTOAN_Batdau;
	private Text text_QUYETTOAN_Dukien;
	private Text text_QUYETTOAN_Hoanthanh;
	private Tree tree_QUYETTOAN;
	private Text text_Ngay_Hoantat_GiaoViec;
	private TabFolder tabFolder_Phanviec;
	private Text text_TenCB;
	private TabFolder tabFolder_2;
	private Text text;
	private Text text_Ghichu_Thuchien;
	private Text text_Ghichu_Nghiemthu;
	private Text text_Ghichu_Quyettoan;
	private Text text_THUCHIEN_Chuyengiao;
	private Text text_NGHIEMTHU_Chuyengiao;
	private DE_XUAT dx;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Nhatky_Lamviec.class);

	public GiaoViec(NGUOIDUNG user) {
		GiaoViec.user = user;
		controler = new Controler(user);
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GiaoViec window = new GiaoViec(user);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		createContents();
		// center screen
		new FormTemplate().setCenterScreen(shlQunLCng);
		shlQunLCng.setLayout(new GridLayout(1, false));
		TreeRowStyle ts = new TreeRowStyle();
		// Drag
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };

		SashForm sashForm_13 = new SashForm(shlQunLCng, SWT.NONE);
		sashForm_13.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		SashForm sashForm = new SashForm(sashForm_13, SWT.VERTICAL);

		TabFolder tabFolder_Congviec = new TabFolder(sashForm, SWT.NONE);

		TabItem tbtmCngVic = new TabItem(tabFolder_Congviec, SWT.NONE);
		tbtmCngVic.setImage(SWTResourceManager.getImage(GiaoViec.class, "/table-sum-icon.png"));
		tbtmCngVic.setText("Tổng hợp Công việc");

		SashForm sashForm_9 = new SashForm(tabFolder_Congviec, SWT.NONE);
		tbtmCngVic.setControl(sashForm_9);

		tabFolder_2 = new TabFolder(sashForm_9, SWT.NONE);
		tabFolder_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = tabFolder_2.getSelectionIndex();
				switch (i) {
				case 0:
					if (tabFolder_Phanviec != null)
						tabFolder_Phanviec.getItems()[2].setText("Nguồn Sửa chữa");
					break;
				case 1:
					if (tabFolder_Phanviec != null)
						tabFolder_Phanviec.getItems()[2].setText("Nguồn Tăng");
					break;
				case 2:
					if (tabFolder_Phanviec != null)
						tabFolder_Phanviec.getItems()[2].setText("Nguồn Giảm");
					break;
				default:
					if (tabFolder_Phanviec != null)
						tabFolder_Phanviec.getItems()[2].setText("Nguồn Sửa chữa");
					break;
				}
			}
		});

		TabItem tbtmBoDng_1 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmBoDng_1.setImage(SWTResourceManager.getImage(GiaoViec.class, "/maintenance-icon (1).png"));
		tbtmBoDng_1.setText("Bảo dưỡng - Sửa chữa PTTS");

		tree_CongviecSuachua = new Tree(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmBoDng_1.setControl(tree_CongviecSuachua);
		tree_CongviecSuachua.setLinesVisible(true);
		tree_CongviecSuachua.setHeaderVisible(true);
		tree_CongviecSuachua.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_CongviecSuachua.getSelection();
					if (items.length > 0) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) items[0].getData();
						if (dsb != null) {
							text.setText(dsb.getMO_TA());
							setField_ThongTin_DE_XUAT(dsb);

							setField_ThongTin_NGUONSUACHUA_BAODUONG(dsb);
							setTree_Danhsach_TAISAN(dsb);
							TinhTrang_Thuchien(dsb);
							TinhTrang_Nghiemthu(dsb);
							TinhTrang_Quyettoan(dsb);
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		ts.setTreeItemHeight(tree_CongviecSuachua, treeHeight);
		TreeColumn treeColumn = new TreeColumn(tree_CongviecSuachua, SWT.CENTER);
		treeColumn.setWidth(55);
		treeColumn.setText("STT");

		TreeColumn treeColumn_1 = new TreeColumn(tree_CongviecSuachua, SWT.CENTER);
		treeColumn_1.setWidth(90);
		treeColumn_1.setText("ID CÔNG VIỆC");

		TreeColumn treeColumn_2 = new TreeColumn(tree_CongviecSuachua, SWT.LEFT);
		treeColumn_2.setWidth(100);
		treeColumn_2.setText("TÊN CÔNG VIỆC");

		TreeColumn trclmnLoiCngVic = new TreeColumn(tree_CongviecSuachua, SWT.NONE);
		trclmnLoiCngVic.setWidth(125);
		trclmnLoiCngVic.setText("LOẠI CÔNG VIỆC");

		TreeColumn trclmnnV = new TreeColumn(tree_CongviecSuachua, SWT.CENTER);
		trclmnnV.setWidth(110);
		trclmnnV.setText("ĐƠN VỊ ĐỀ XUẤT");
		Menu menu_1 = new Menu(tree_CongviecSuachua);
		tree_CongviecSuachua.setMenu(menu_1);

		MenuItem mntmCpNhtTin = new MenuItem(menu_1, SWT.NONE);
		mntmCpNhtTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem ti[] = tree_CongviecSuachua.getSelection();
					if (ti.length > 0) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) ti[0].getData();
						DE_XUAT dx;
						dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);

						switch (dsb.getSUACHUA_BAODUONG()) {
						case 1:
							_2_Taodot_Baoduong vbd = new _2_Taodot_Baoduong(display, user, null, dx,
									/* MODE_NEW_VIEW: */2);
							vbd.open();
							break;
						case 2:
							_2_Taodot_Suachua vsc = new _2_Taodot_Suachua(display, user, null, dx,
									/* MODE_NEW_VIEW: */2);
							vsc.open();
							break;

						default:
							break;
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmCpNhtTin.setText("Xem công việc");

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("Hoàn tất nhanh");

		MenuItem mntmXa = new MenuItem(menu_1, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_CongviecSuachua.getSelection();
					if (til.length > 0) {
						controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
								.delete_DOT_THUCHIEN_SUACHUA_BAODUONG((DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData());
					}
					FillTableSuachua();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXa.setText("Xóa");

		TabItem tbtmMuaSm_1 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmMuaSm_1.setImage(SWTResourceManager.getImage(GiaoViec.class, "/add-icon (1).png"));
		tbtmMuaSm_1.setText("Mua sắm - Tiếp nhận PTTS");

		SashForm sashForm_10 = new SashForm(tabFolder_2, SWT.VERTICAL);
		tbtmMuaSm_1.setControl(sashForm_10);

		tree_CongViecMuasam = new Tree(sashForm_10, SWT.BORDER | SWT.FULL_SELECTION);
		tree_CongViecMuasam.setLinesVisible(true);
		tree_CongViecMuasam.setHeaderVisible(true);
		tree_CongViecMuasam.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_CongViecMuasam.getSelection();
					if (items.length > 0) {
						DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) items[0].getData();
						if (dtt != null) {
							text.setText(dtt.getMO_TA());
							setField_ThongTin_DE_XUAT(dtt);

							setField_ThongTin_NGUONTANG(dtt);
							setTree_Danhsach_TAISAN(dtt);
							TinhTrang_Thuchien(dtt);
							TinhTrang_Nghiemthu(dtt);
							TinhTrang_Quyettoan(dtt);
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		ts.setTreeItemHeight(tree_CongViecMuasam, treeHeight);

		TreeColumn trclmnStt = new TreeColumn(tree_CongViecMuasam, SWT.CENTER);
		trclmnStt.setWidth(55);
		trclmnStt.setText("STT");

		TreeColumn trclmnIdcongviec = new TreeColumn(tree_CongViecMuasam, SWT.CENTER);
		trclmnIdcongviec.setWidth(90);
		trclmnIdcongviec.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnTenCongViec = new TreeColumn(tree_CongViecMuasam, SWT.LEFT);
		trclmnTenCongViec.setWidth(100);
		trclmnTenCongViec.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnDonvidexuat = new TreeColumn(tree_CongViecMuasam, SWT.CENTER);
		trclmnDonvidexuat.setWidth(110);
		trclmnDonvidexuat.setText("\u0110\u01A0N V\u1ECA \u0110\u1EC0 XU\u1EA4T");
		Menu menu = new Menu(tree_CongViecMuasam);
		tree_CongViecMuasam.setMenu(menu);

		MenuItem mntmXemCngVic = new MenuItem(menu, SWT.NONE);
		mntmXemCngVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem til[] = tree_CongViecMuasam.getSelection();
				try {
					if (til.length > 0) {
						DOT_THUCHIEN_TANG_TAISAN dt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
						XemDotTangtaisan xdt = new XemDotTangtaisan(display, user, dt);
						xdt.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemCngVic.setText("Xem công việc");
		MenuItem mntmHonTtNhanh = new MenuItem(menu, SWT.NONE);
		mntmHonTtNhanh.setText("Hoàn tất nhanh");

		MenuItem mntmXa_1 = new MenuItem(menu, SWT.NONE);
		mntmXa_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_CongViecMuasam.getSelection();
					if (til.length > 0) {
						controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
								.delete_DOT_THUCHIEN_TANG_TAISAN((DOT_THUCHIEN_TANG_TAISAN) til[0].getData());

						FillTableMuasam();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmXa_1.setText("Xóa");
		sashForm_10.setWeights(new int[] { 1 });

		TabItem tbtmThanhL_1 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmThanhL_1.setImage(SWTResourceManager.getImage(GiaoViec.class, "/database_remove.png"));
		tbtmThanhL_1.setText("Thanh lý - Bàn giao PTTS");

		SashForm sashForm_6 = new SashForm(tabFolder_2, SWT.VERTICAL);
		tbtmThanhL_1.setControl(sashForm_6);

		tree_CongviecThanhly = new Tree(sashForm_6, SWT.BORDER | SWT.FULL_SELECTION);
		tree_CongviecThanhly.setLinesVisible(true);
		tree_CongviecThanhly.setHeaderVisible(true);
		tree_CongviecThanhly.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_CongviecThanhly.getSelection();
					if (items.length > 0) {
						DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) items[0].getData();
						if (dgt != null) {
							text.setText(dgt.getMO_TA());
							setField_ThongTin_DE_XUAT(dgt);

							setField_ThongTin_NGUONGIAM(dgt);
							setTree_Danhsach_TAISAN(dgt);
							TinhTrang_Thuchien(dgt);
							TinhTrang_Nghiemthu(dgt);
							TinhTrang_Quyettoan(dgt);
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		ts.setTreeItemHeight(tree_CongviecThanhly, treeHeight);
		TreeColumn treeColumn_6 = new TreeColumn(tree_CongviecThanhly, SWT.CENTER);
		treeColumn_6.setWidth(55);
		treeColumn_6.setText("STT");

		TreeColumn treeColumn_7 = new TreeColumn(tree_CongviecThanhly, SWT.CENTER);
		treeColumn_7.setWidth(90);
		treeColumn_7.setText("ID CÔNG VIỆC");

		TreeColumn treeColumn_8 = new TreeColumn(tree_CongviecThanhly, SWT.LEFT);
		treeColumn_8.setWidth(100);
		treeColumn_8.setText("TÊN CÔNG VIỆC");

		TreeColumn trclmnnV_1 = new TreeColumn(tree_CongviecThanhly, SWT.CENTER);
		trclmnnV_1.setWidth(110);
		trclmnnV_1.setText("ĐƠN VỊ ĐỀ XUẤT");

		Menu menu_2 = new Menu(tree_CongviecThanhly);
		tree_CongviecThanhly.setMenu(menu_2);

		MenuItem menuItem_2 = new MenuItem(menu_2, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem til[] = tree_CongviecThanhly.getSelection();
					if (til.length > 0) {
						DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
						XemDotGiam xdg = new XemDotGiam(display, user, dgt);
						xdg.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		menuItem_2.setText("Xem công việc");

		MenuItem menuItem_3 = new MenuItem(menu_2, SWT.NONE);
		menuItem_3.setText("Hoàn tất nhanh");

		MenuItem mntmXoa = new MenuItem(menu_2, SWT.NONE);
		mntmXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_CongviecThanhly.getSelection();
					if (til.length > 0) {
						controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
								.delete_DOT_THUCHIEN_GIAM_TAISAN((DOT_THUCHIEN_GIAM_TAISAN) til[0].getData());
					}
					FillTableThanhly();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXoa.setText("Xóa");
		sashForm_6.setWeights(new int[] { 1 });

		Group grpThngTin = new Group(sashForm_9, SWT.NONE);
		grpThngTin.setText("Thông tin đề xuất");
		grpThngTin.setLayout(new GridLayout(2, false));

		Label lblSXut = new Label(grpThngTin, SWT.NONE);
		lblSXut.setText("Số đề xuất:");

		text_Sodexuat = new Text(grpThngTin, SWT.NONE);
		text_Sodexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Sodexuat.setEditable(false);

		Label lblNgyThngVb = new Label(grpThngTin, SWT.NONE);
		lblNgyThngVb.setText("Ngày tháng VB:");

		text_NgaythangVanban = new Text(grpThngTin, SWT.NONE);
		text_NgaythangVanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_NgaythangVanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_NgaythangVanban.setEditable(false);

		Label lblnV = new Label(grpThngTin, SWT.NONE);
		lblnV.setText("Đơn vị đề xuất:");

		text_DonviDexuat = new Text(grpThngTin, SWT.NONE);
		text_DonviDexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_DonviDexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_DonviDexuat.setEditable(false);

		Label lblCnBX = new Label(grpThngTin, SWT.NONE);
		lblCnBX.setText("Cán bộ xử lý ĐX:");

		text_TenCB = new Text(grpThngTin, SWT.NONE);
		text_TenCB.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_TenCB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_TenCB.setEditable(false);

		Label lblNgyThcHin = new Label(grpThngTin, SWT.NONE);
		lblNgyThcHin.setText("Ngày Chuyển VB:");

		text_Ngaythuchien = new Text(grpThngTin, SWT.NONE);
		text_Ngaythuchien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaythuchien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_Ngaythuchien.setEditable(false);

		Label lblNgyGiaoVic = new Label(grpThngTin, SWT.NONE);
		lblNgyGiaoVic.setText("Ngày giao việc:");

		text_Ngay_Hoantat_GiaoViec = new Text(grpThngTin, SWT.NONE);
		text_Ngay_Hoantat_GiaoViec.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngay_Hoantat_GiaoViec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Ngay_Hoantat_GiaoViec.setEditable(false);

		Label lblGhiCh = new Label(grpThngTin, SWT.NONE);
		lblGhiCh.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblGhiCh.setText("Ghi chú:");

		text_Ghichu = new Text(grpThngTin, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		text_Ghichu.setEditable(false);
		new Label(grpThngTin, SWT.NONE);

		Button btnHSLu = new Button(grpThngTin, SWT.NONE);
		btnHSLu.setImage(SWTResourceManager.getImage(GiaoViec.class, "/folder-documents-icon(1).png"));
		btnHSLu.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		btnHSLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dx != null) {
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO());
						if (ths != null) {
							TAPHOSO_View thsv = new TAPHOSO_View(shlQunLCng, SWT.DIALOG_TRIM, user, ths, false);
							thsv.open();
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnHSLu.setText("Hồ sơ Đề xuất");
		sashForm_9.setWeights(new int[] { 2, 1 });

		TabItem tbtmNhnLc = new TabItem(tabFolder_Congviec, SWT.NONE);
		tbtmNhnLc.setImage(SWTResourceManager.getImage(GiaoViec.class, "/Group-Black-Folder-icon.png"));
		tbtmNhnLc.setText("Hiện trạng Nhân lực");

		SashForm sashForm_1 = new SashForm(tabFolder_Congviec, SWT.NONE);
		tbtmNhnLc.setControl(sashForm_1);

		tree_DanhsachCanbo = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree_DanhsachCanbo.setLinesVisible(true);
		tree_DanhsachCanbo.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_DanhsachCanbo, treeHeight);
		tree_DanhsachCanbo.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_DanhsachCanbo.getSelection();
					if (items.length > 0) {
						NGUOIDUNG nd = (NGUOIDUNG) items[0].getData();
						if (nd != null) {
							view_Congviec_Dang_Thuchien(nd);
							view_Congviec_Da_Thuchien(nd);
						}
						// Show data on Cong viec}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		TreeColumn trclmnStt_2 = new TreeColumn(tree_DanhsachCanbo, SWT.CENTER);
		trclmnStt_2.setWidth(50);
		trclmnStt_2.setText("STT");

		TreeColumn trclmnTnCnB_1 = new TreeColumn(tree_DanhsachCanbo, SWT.CENTER);
		trclmnTnCnB_1.setWidth(100);
		trclmnTnCnB_1.setText("T\u00CAN C\u00C1N B\u1ED8");

		TreeColumn trclmnPhngBan = new TreeColumn(tree_DanhsachCanbo, SWT.CENTER);
		trclmnPhngBan.setWidth(100);
		trclmnPhngBan.setText("PH\u00D2NG BAN");

		TreeColumn trclmnTnTiKhon_1 = new TreeColumn(tree_DanhsachCanbo, SWT.CENTER);
		trclmnTnTiKhon_1.setWidth(100);
		trclmnTnTiKhon_1.setText("T\u00CAN T\u00C0I KHO\u1EA2N");

		DragSource dragSource = new DragSource(tree_DanhsachCanbo, SWT.DRAG);

		dragSource.setTransfer(types);
		dragSource.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {

				// Get the selected items in the drag source
				DragSource ds = (DragSource) event.widget;
				Tree tree = (Tree) ds.getControl();
				TreeItem[] selection = tree.getSelection();
				StringBuffer buff = new StringBuffer();
				for (int i = 0, n = selection.length; i < n; i++) {
					// TreeItem t = selection[i];
					if (selection[i].getData() != null)
						buff.append(((NGUOIDUNG) selection[i].getData()).getTEN_TAI_KHOAN() + " ");
				}
				event.data = buff.toString();
				// System.out.println((String) event.data);
			}
		});

		TabFolder tabFolder = new TabFolder(sashForm_1, SWT.NONE);

		TabItem tbtmPhnVicang = new TabItem(tabFolder, SWT.NONE);
		tbtmPhnVicang.setText("Công việc đang thực hiện");

		tree_PhanViecDangThucHien = new Tree(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tree_PhanViecDangThucHien.setLinesVisible(true);
		tree_PhanViecDangThucHien.setHeaderVisible(true);
		tbtmPhnVicang.setControl(tree_PhanViecDangThucHien);
		ts.setTreeItemHeight(tree_PhanViecDangThucHien, treeHeight);

		TreeColumn trclmnStt_3 = new TreeColumn(tree_PhanViecDangThucHien, SWT.CENTER);
		trclmnStt_3.setWidth(50);
		trclmnStt_3.setText("STT");

		TreeColumn trclmnLoaijCongViec = new TreeColumn(tree_PhanViecDangThucHien, SWT.CENTER);
		trclmnLoaijCongViec.setWidth(120);
		trclmnLoaijCongViec.setText("LO\u1EA0I C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnPhnVic = new TreeColumn(tree_PhanViecDangThucHien, SWT.NONE);
		trclmnPhnVic.setWidth(100);
		trclmnPhnVic.setText("PHẦN VIỆC");

		TreeColumn trclmnTencongviec = new TreeColumn(tree_PhanViecDangThucHien, SWT.NONE);
		trclmnTencongviec.setWidth(100);
		trclmnTencongviec.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnIdCngVic = new TreeColumn(tree_PhanViecDangThucHien, SWT.CENTER);

		trclmnIdCngVic.setWidth(100);
		trclmnIdCngVic.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnNgyBtu_1 = new TreeColumn(tree_PhanViecDangThucHien, SWT.CENTER);
		trclmnNgyBtu_1.setWidth(100);
		trclmnNgyBtu_1.setText("NG\u00C0Y B\u1EAET \u0110\u1EA6U");

		TreeColumn trclmnTin = new TreeColumn(tree_PhanViecDangThucHien, SWT.CENTER);
		trclmnTin.setWidth(100);
		trclmnTin.setText("TI\u1EBEN \u0110\u1ED8");

		TabItem tbtmLchSPhn = new TabItem(tabFolder, SWT.NONE);
		tbtmLchSPhn.setText("Công việc đã thực hiện");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmLchSPhn.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		tree_PhanViecDaThucHien = new Tree(composite_1, SWT.BORDER);
		tree_PhanViecDaThucHien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree_PhanViecDaThucHien.setSize(493, 172);
		tree_PhanViecDaThucHien.setLinesVisible(true);
		tree_PhanViecDaThucHien.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_PhanViecDaThucHien, treeHeight);

		TreeColumn trclmnStt_5 = new TreeColumn(tree_PhanViecDaThucHien, SWT.CENTER);
		trclmnStt_5.setWidth(52);
		trclmnStt_5.setText("STT");

		TreeColumn trclmnIdCngVic_1 = new TreeColumn(tree_PhanViecDaThucHien, SWT.NONE);
		trclmnIdCngVic_1.setWidth(89);
		trclmnIdCngVic_1.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnLoiCngVic_1 = new TreeColumn(tree_PhanViecDaThucHien, SWT.CENTER);
		trclmnLoiCngVic_1.setWidth(120);
		trclmnLoiCngVic_1.setText("LO\u1EA0I C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnPhnVic_1 = new TreeColumn(tree_PhanViecDaThucHien, SWT.NONE);
		trclmnPhnVic_1.setWidth(100);
		trclmnPhnVic_1.setText("PHẦN VIỆC");

		TreeColumn trclmnTencongviec_1 = new TreeColumn(tree_PhanViecDaThucHien, SWT.NONE);
		trclmnTencongviec_1.setWidth(129);
		trclmnTencongviec_1.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnNgyBtu_2 = new TreeColumn(tree_PhanViecDaThucHien, SWT.CENTER);
		trclmnNgyBtu_2.setWidth(100);
		trclmnNgyBtu_2.setText("NG\u00C0Y B\u1EAET \u0110\u1EA6U");

		TreeColumn trclmnNgyKtThc = new TreeColumn(tree_PhanViecDaThucHien, SWT.CENTER);
		trclmnNgyKtThc.setWidth(120);
		trclmnNgyKtThc.setText("NG\u00C0Y K\u1EBET TH\u00DAC");

		TreeColumn trclmnTngSNgy = new TreeColumn(tree_PhanViecDaThucHien, SWT.CENTER);
		trclmnTngSNgy.setWidth(170);
		trclmnTngSNgy.setText("T\u1ED4NG S\u1ED0 NG\u00C0Y TH\u1EF0C HI\u1EC6N");
		sashForm_1.setWeights(new int[] { 467, 511 });

		tabFolder_Phanviec = new TabFolder(sashForm, SWT.NONE);

		TabItem tbtmPhnVic = new TabItem(tabFolder_Phanviec, SWT.NONE);
		tbtmPhnVic.setText("Phần việc đang triển khai");

		SashForm sashForm_7 = new SashForm(tabFolder_Phanviec, SWT.NONE);
		tbtmPhnVic.setControl(sashForm_7);

		Group grpTChcThc = new Group(sashForm_7, SWT.NONE);
		grpTChcThc.setText("Tổ chức - thực hiện");
		grpTChcThc.setFont(new Font(display, "Consolas", 9, SWT.BOLD));
		grpTChcThc.setForeground(new Color(null, 255, 1, 51));
		GridLayout gl_grpTChcThc = new GridLayout(2, false);
		grpTChcThc.setLayout(gl_grpTChcThc);

		Label lblNgyBtu = new Label(grpTChcThc, SWT.NONE);
		lblNgyBtu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyBtu.setText("Ngày bắt đầu:");

		text_THUCHIEN_Batdau = new Text(grpTChcThc, SWT.NONE);
		text_THUCHIEN_Batdau.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_THUCHIEN_Batdau.setEditable(false);
		text_THUCHIEN_Batdau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblDKin = new Label(grpTChcThc, SWT.NONE);
		lblDKin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDKin.setText("Dự kiến:");

		text_THUCHIEN_Dukien = new Text(grpTChcThc, SWT.NONE);
		text_THUCHIEN_Dukien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_THUCHIEN_Dukien.setEditable(false);
		text_THUCHIEN_Dukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyChuynGiao = new Label(grpTChcThc, SWT.NONE);
		lblNgyChuynGiao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyChuynGiao.setText("Ngày chuyển giao:");

		text_THUCHIEN_Chuyengiao = new Text(grpTChcThc, SWT.NONE);
		text_THUCHIEN_Chuyengiao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_THUCHIEN_Chuyengiao.setEditable(false);
		text_THUCHIEN_Chuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyHonThnh = new Label(grpTChcThc, SWT.NONE);
		lblNgyHonThnh.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblNgyHonThnh.setText("Ngày hoàn thành:");

		text_THUCHIEN_Hoanthanh = new Text(grpTChcThc, SWT.NONE);
		text_THUCHIEN_Hoanthanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_THUCHIEN_Hoanthanh.setEditable(false);
		text_THUCHIEN_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		Label lblGhiCh_1 = new Label(grpTChcThc, SWT.NONE);
		lblGhiCh_1.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblGhiCh_1.setText("Ghi chú:");
		text_Ghichu_Thuchien = new Text(grpTChcThc, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu_Thuchien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		GridData gd_text_Ghichu_Thuchien = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Ghichu_Thuchien.heightHint = 50;
		text_Ghichu_Thuchien.setLayoutData(gd_text_Ghichu_Thuchien);
		text_Ghichu_Thuchien.setEditable(false);

		tree_THUCHIEN = new Tree(grpTChcThc, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tree_THUCHIEN = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_tree_THUCHIEN.heightHint = 100;
		tree_THUCHIEN.setLayoutData(gd_tree_THUCHIEN);
		tree_THUCHIEN.setHeaderVisible(true);

		TreeColumn trclmnTnCnB = new TreeColumn(tree_THUCHIEN, SWT.NONE);
		trclmnTnCnB.setWidth(110);
		trclmnTnCnB.setText("Cán bộ thực hiện");

		TreeColumn trclmnTiKhon = new TreeColumn(tree_THUCHIEN, SWT.NONE);
		trclmnTiKhon.setWidth(120);
		trclmnTiKhon.setText("Tài khoản");

		Menu menu_3 = new Menu(tree_THUCHIEN);
		tree_THUCHIEN.setMenu(menu_3);

		MenuItem mntmNhnVic = new MenuItem(menu_3, SWT.NONE);
		mntmNhnVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til;
					Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					switch (tabFolder_2.getSelectionIndex()) {
					case 0:// sua chua
						til = tree_CongviecSuachua.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
							Fill_ItemData f = new Fill_ItemData();
							GIAI_DOAN_THUC_HIEN dgth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
							if (dgth == null) {
								int MAGDTH = controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dsb);
								dgth = new GIAI_DOAN_THUC_HIEN();
								dgth.setMA_GIAI_DOAN_THUC_HIEN(MAGDTH);
							}
							controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(), dgth,
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Thuchien(dsb);
						}
						break;
					case 1:// mua sam
						til = tree_CongViecMuasam.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
							Fill_ItemData f = new Fill_ItemData();
							if (controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt) == null) {
								controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dtt);
							}
							controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(),
									controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt),
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Thuchien(dtt);
						}
						break;
					case 2:// thanh ly
						til = tree_CongviecThanhly.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
							Fill_ItemData f = new Fill_ItemData();
							if (controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt) == null) {
								controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dgt);
							}
							controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(),
									controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt),
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Thuchien(dgt);
						}
						break;

					default:
						break;
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmNhnVic.setText("Nhận việc");

		MenuItem mntmTubo = new MenuItem(menu_3, SWT.NONE);
		mntmTubo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] tmp = tree_THUCHIEN.getSelection();
					if (tmp.length > 0) {
						user_congviec uc = (user_congviec) tmp[0].getData();
						if (controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(uc.getMA_GIAI_DOAN_CONG_VIEC())
								.getTHOI_DIEM_BAT_DAU() == null) {
							controler.getControl_THUCHIEN_CANBO().deleteNGUOIDUNG_GIAI_DOAN_THUC_HIEN(
									uc.getTEN_TAI_KHOAN(), uc.getMA_GIAI_DOAN_CONG_VIEC());
						} else {
							MessageBox m = new MessageBox(shlQunLCng, SWT.ICON_WARNING);
							m.setText("Thao tác sai");
							m.setMessage(
									"Không thể từ bỏ công việc sau khi đã bắt đầu thực hiện, hãy trả lại phần việc!");
							m.open();
						}
						TreeItem[] til;
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								TinhTrang_Thuchien(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								TinhTrang_Thuchien(dtt);
							}
							break;
						case 2:
							til = tree_CongviecThanhly.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
								TinhTrang_Thuchien(dgt);
							}
							break;
						default:
							break;
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTubo.setText("Từ bỏ");

		TreeColumn trclmnHnhThcNhn = new TreeColumn(tree_THUCHIEN, SWT.NONE);
		trclmnHnhThcNhn.setWidth(100);
		trclmnHnhThcNhn.setText("Hình thức nhận việc");

		DropTarget dropTarget = new DropTarget(tree_THUCHIEN, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);

		dropTarget.setTransfer(types);

		TreeColumn trclmnNgyThamGia = new TreeColumn(tree_THUCHIEN, SWT.NONE);
		trclmnNgyThamGia.setWidth(100);
		trclmnNgyThamGia.setText("Ngày tham gia");
		dropTarget.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						// files should only be copied
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					// NOTE: on unsupported platforms this will return null
					// Object o =
					// TextTransfer.getInstance().nativeToJava(event.currentDataType);
					// String t = (String) o;
					// if (t != null)
					// System.out.println(t);
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				try {
					if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
						String TEN_TAI_KHOAN = event.data.toString();
						System.out.println(TEN_TAI_KHOAN);
						// System.out.println(((TreeItem)event.item).getText());
						// if (event.item != null)
						// a
						TreeItem[] til;
						Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								Fill_ItemData f = new Fill_ItemData();
								GIAI_DOAN_THUC_HIEN dgth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
								if (dgth == null) {
									int MAGDTH = controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dsb);
									dgth = new GIAI_DOAN_THUC_HIEN();
									dgth.setMA_GIAI_DOAN_THUC_HIEN(MAGDTH);
								}
								controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(TEN_TAI_KHOAN, dgth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);
								TinhTrang_Thuchien(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
								Fill_ItemData f = new Fill_ItemData();
								if (gdth == null) {
									int MAGDTH = controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dtt);
									gdth = new GIAI_DOAN_THUC_HIEN();
									gdth.setMA_GIAI_DOAN_THUC_HIEN(MAGDTH);
								}

								controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(TEN_TAI_KHOAN, gdth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);
								TinhTrang_Thuchien(dtt);
							}
							break;
						case 2:
							til = tree_CongviecThanhly.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
								GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
								Fill_ItemData f = new Fill_ItemData();
								if (gdth == null) {
									int MAGDTH = controler.getControl_THUCHIEN().create_GIAI_DOAN_THUCHIEN(dgt);
									gdth = new GIAI_DOAN_THUC_HIEN();
									gdth.setMA_GIAI_DOAN_THUC_HIEN(MAGDTH);
								}

								controler.getControl_THUCHIEN_CANBO().setNGUOIDUNG_THUCHIEN(TEN_TAI_KHOAN, gdth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);
								TinhTrang_Thuchien(dgt);
							}
							break;

						default:
							break;
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
				new Label(grpTChcThc, SWT.NONE);
		
				Button btnHSThc = new Button(grpTChcThc, SWT.NONE);
				btnHSThc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				btnHSThc.setImage(SWTResourceManager.getImage(GiaoViec.class, "/folder-documents-icon(1).png"));
				btnHSThc.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							int s = tabFolder_2.getSelectionIndex();
							switch (s) {
							case 0:
								TreeItem[] ti = tree_CongviecSuachua.getSelection();
								if (ti.length > 0) {
									DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) ti[0].getData();
									GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_THUCHIEN, gdth);
									vph.open();
									//
								}
								break;
							case 1:
								TreeItem[] ti2 = tree_CongViecMuasam.getSelection();
								if (ti2.length > 0) {
									DOT_THUCHIEN_TANG_TAISAN dsb = (DOT_THUCHIEN_TANG_TAISAN) ti2[0].getData();
									GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_THUCHIEN, gdth);
									vph.open();
									//
								}
								break;
							case 2:
								TreeItem[] ti3 = tree_CongViecMuasam.getSelection();
								if (ti3.length > 0) {
									DOT_THUCHIEN_GIAM_TAISAN dsb = (DOT_THUCHIEN_GIAM_TAISAN) ti3[0].getData();
									GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_THUCHIEN, gdth);
									vph.open();
									//
								}
								break;

							default:
								break;
							}
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					}
				});
				btnHSThc.setText("Hồ sơ thực hiện");

		Group grpNghimThu = new Group(sashForm_7, SWT.NONE);
		grpNghimThu.setFont(new Font(display, "Consolas", 9, SWT.BOLD));
		grpNghimThu.setForeground(new Color(null, 255, 1, 51));
		grpNghimThu.setText("Nghiệm thu - bàn giao");
		grpNghimThu.setLayout(new GridLayout(2, false));

		Label label = new Label(grpNghimThu, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Ngày bắt đầu:");

		text_NGHIEMTHU_Batdau = new Text(grpNghimThu, SWT.NONE);
		text_NGHIEMTHU_Batdau.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NGHIEMTHU_Batdau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_NGHIEMTHU_Batdau.setEditable(false);

		Label label_1 = new Label(grpNghimThu, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("Dự kiến:");

		text_NGHIEMTHU_dukien = new Text(grpNghimThu, SWT.NONE);
		text_NGHIEMTHU_dukien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NGHIEMTHU_dukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_NGHIEMTHU_dukien.setEditable(false);

		Label label_7 = new Label(grpNghimThu, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("Ngày chuyển giao:");

		text_NGHIEMTHU_Chuyengiao = new Text(grpNghimThu, SWT.NONE);
		text_NGHIEMTHU_Chuyengiao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NGHIEMTHU_Chuyengiao.setEditable(false);
		text_NGHIEMTHU_Chuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyHonThnh_1 = new Label(grpNghimThu, SWT.NONE);
		lblNgyHonThnh_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyHonThnh_1.setText("Ngày hoàn thành:");

		text_NGHIEMTHU_Hoanthanh = new Text(grpNghimThu, SWT.NONE);
		text_NGHIEMTHU_Hoanthanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NGHIEMTHU_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_NGHIEMTHU_Hoanthanh.setEditable(false);

		Label label_5 = new Label(grpNghimThu, SWT.NONE);
		label_5.setText("Ghi chú:");
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));

		text_Ghichu_Nghiemthu = new Text(grpNghimThu, SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu_Nghiemthu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_text_Ghichu_Nghiemthu = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Ghichu_Nghiemthu.heightHint = 50;
		text_Ghichu_Nghiemthu.setLayoutData(gd_text_Ghichu_Nghiemthu);
		text_Ghichu_Nghiemthu.setEditable(false);

		tree_NGHIEMTHU = new Tree(grpNghimThu, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tree_NGHIEMTHU = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_tree_NGHIEMTHU.heightHint = 100;
		tree_NGHIEMTHU.setLayoutData(gd_tree_NGHIEMTHU);
		tree_NGHIEMTHU.setHeaderVisible(true);

		TreeColumn trclmnNghimThu = new TreeColumn(tree_NGHIEMTHU, SWT.NONE);
		trclmnNghimThu.setWidth(110);
		trclmnNghimThu.setText("Cán bộ thực hiện");

		TreeColumn trclmnTiKhon_1 = new TreeColumn(tree_NGHIEMTHU, SWT.NONE);
		trclmnTiKhon_1.setWidth(120);
		trclmnTiKhon_1.setText("Tài khoản");

		Menu menu_4 = new Menu(tree_NGHIEMTHU);
		tree_NGHIEMTHU.setMenu(menu_4);

		MenuItem mntmNhnVic_1 = new MenuItem(menu_4, SWT.NONE);
		mntmNhnVic_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til;
					Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					switch (tabFolder_2.getSelectionIndex()) {
					case 0:
						til = tree_CongviecSuachua.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
							GIAI_DOAN_NGHIEM_THU gdngth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
							Fill_ItemData f = new Fill_ItemData();
							if (gdngth == null) {
								// tạo dot nghiemthu - quyet toan truoc
								int MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
								// kiem tra xem qua trinh ntqt co hay chua:
								if (MA_QUATRINH_NGHIEMTHU_QUYETTOAN == 0) {
									MA_QUATRINH_NGHIEMTHU_QUYETTOAN = controler
											.getControl_QUATRINH_NGHIEMTHU_QUYETTOAN()
											.create_QUATRINH_NGHIEMTHU_QUYETTOAN("ĐỢT NGHIỆM THU - QUYẾT TOÁN");
									dsb.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(MA_QUATRINH_NGHIEMTHU_QUYETTOAN);
									controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
											.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(
													dsb);
								}
								int MAGDNGTH = controler.getControl_NGHIEMTHU().create_GIAI_DOAN_NGHIEMTHU(dsb);
								gdngth = new GIAI_DOAN_NGHIEM_THU();
								gdngth.setMA_GIAI_DOAN_NGHIEM_THU(MAGDNGTH);

							}
							controler.getControl_NGHIEMTHU_CANBO().setNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(),
									controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb),
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);

							TinhTrang_Nghiemthu(dsb);
						}
						break;
					case 1:
						til = tree_CongViecMuasam.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
							GIAI_DOAN_NGHIEM_THU gdngth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
							Fill_ItemData f = new Fill_ItemData();
							if (gdngth == null) {
								// tạo dot nghiemthu - quyet toan truoc
								int MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
								// kiem tra xem qua trinh ntqt co hay chua:
								if (MA_QUATRINH_NGHIEMTHU_QUYETTOAN == 0) {
									MA_QUATRINH_NGHIEMTHU_QUYETTOAN = controler
											.getControl_QUATRINH_NGHIEMTHU_QUYETTOAN()
											.create_QUATRINH_NGHIEMTHU_QUYETTOAN("ĐỢT NGHIỆM THU - QUYẾT TOÁN");

									controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
											.update_DOT_TANG_TAISAN_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(dtt,
													MA_QUATRINH_NGHIEMTHU_QUYETTOAN);

									dtt.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(MA_QUATRINH_NGHIEMTHU_QUYETTOAN);
								}
								int MAGDNGTH = controler.getControl_NGHIEMTHU().create_GIAI_DOAN_NGHIEMTHU(dtt);
								gdngth = new GIAI_DOAN_NGHIEM_THU();
								gdngth.setMA_GIAI_DOAN_NGHIEM_THU(MAGDNGTH);

							}
							controler.getControl_NGHIEMTHU_CANBO().setNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(),
									gdngth, f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Nghiemthu(dtt);
						}
						break;
					case 2:
						til = tree_CongviecThanhly.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_GIAM_TAISAN dsb = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
							TinhTrang_Nghiemthu(dsb);
						}
						break;

					default:
						break;
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmNhnVic_1.setText("Nhận việc");

		MenuItem mntmTubo1 = new MenuItem(menu_4, SWT.NONE);
		mntmTubo1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] tmp = tree_NGHIEMTHU.getSelection();
					if (tmp.length > 0) {
						user_congviec uc = (user_congviec) tmp[0].getData();
						if (controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(uc.getMA_GIAI_DOAN_CONG_VIEC())
								.getTHOI_DIEM_TIEP_NHAN() == null) {
							controler.getControl_NGHIEMTHU_CANBO().deleteNGUOIDUNG_GIAI_DOAN_NGHIEM_THU(
									uc.getTEN_TAI_KHOAN(), uc.getMA_GIAI_DOAN_CONG_VIEC());
						} else {
							MessageBox m = new MessageBox(shlQunLCng, SWT.ICON_WARNING);
							m.setText("Thao tác sai");
							m.setMessage(
									"Không thể từ bỏ công việc sau khi đã bắt đầu thực hiện, hãy trả lại phần việc!");
							m.open();
						}
						TreeItem[] til;
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								TinhTrang_Nghiemthu(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								TinhTrang_Nghiemthu(dtt);
							}
							break;
						case 2:
							til = tree_CongviecThanhly.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
								TinhTrang_Nghiemthu(dgt);
							}
							break;

						default:
							break;
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTubo1.setText("Từ bỏ");

		TreeColumn trclmnNewColumn_2 = new TreeColumn(tree_NGHIEMTHU, SWT.NONE);
		trclmnNewColumn_2.setWidth(100);
		trclmnNewColumn_2.setText("Hình thức nhận việc");

		DropTarget dropTarget_1 = new DropTarget(tree_NGHIEMTHU, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);

		dropTarget_1.setTransfer(types);

		TreeColumn trclmnNgyThamGia_1 = new TreeColumn(tree_NGHIEMTHU, SWT.NONE);
		trclmnNgyThamGia_1.setWidth(100);
		trclmnNgyThamGia_1.setText("Ngày tham gia");
		dropTarget_1.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						// files should only be copied
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					// NOTE: on unsupported platforms this will return null
					// Object o =
					// TextTransfer.getInstance().nativeToJava(event.currentDataType);
					// String t = (String) o;
					// if (t != null)
					// System.out.println(t);
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				try {
					if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
						String TEN_TAI_KHOAN = event.data.toString();
						System.out.println(TEN_TAI_KHOAN);
						// System.out.println(((TreeItem)event.item).getText());
						// if (event.item != null)
						// a
						TreeItem[] til;
						Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								GIAI_DOAN_NGHIEM_THU gdngth = controler.getControl_NGHIEMTHU()
										.get_GIAIDOAN_NGHIEMTHU(dsb);
								Fill_ItemData f = new Fill_ItemData();
								if (gdngth == null) {
									// tạo dot nghiemthu - quyet toan truoc
									int MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
									// kiem tra xem qua trinh ntqt co hay chua:
									if (MA_QUATRINH_NGHIEMTHU_QUYETTOAN == 0) {
										MA_QUATRINH_NGHIEMTHU_QUYETTOAN = controler
												.getControl_QUATRINH_NGHIEMTHU_QUYETTOAN()
												.create_QUATRINH_NGHIEMTHU_QUYETTOAN("ĐỢT NGHIỆM THU - QUYẾT TOÁN");
										dsb.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(MA_QUATRINH_NGHIEMTHU_QUYETTOAN);
										controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
												.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(
														dsb);
									}
									int MAGDNGTH = controler.getControl_NGHIEMTHU().create_GIAI_DOAN_NGHIEMTHU(dsb);
									gdngth = new GIAI_DOAN_NGHIEM_THU();
									gdngth.setMA_GIAI_DOAN_NGHIEM_THU(MAGDNGTH);

								}
								controler.getControl_NGHIEMTHU_CANBO().setNGUOIDUNG_NGHIEMTHU(TEN_TAI_KHOAN,
										controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb),
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);

								TinhTrang_Nghiemthu(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								GIAI_DOAN_NGHIEM_THU gdngth = controler.getControl_NGHIEMTHU()
										.get_GIAIDOAN_NGHIEMTHU(dtt);
								Fill_ItemData f = new Fill_ItemData();
								if (gdngth == null) {
									// tạo dot nghiemthu - quyet toan truoc
									int MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
									// kiem tra xem qua trinh ntqt co hay chua:
									if (MA_QUATRINH_NGHIEMTHU_QUYETTOAN == 0) {
										MA_QUATRINH_NGHIEMTHU_QUYETTOAN = controler
												.getControl_QUATRINH_NGHIEMTHU_QUYETTOAN()
												.create_QUATRINH_NGHIEMTHU_QUYETTOAN("ĐỢT NGHIỆM THU - QUYẾT TOÁN");

										controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
												.update_DOT_TANG_TAISAN_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(dtt,
														MA_QUATRINH_NGHIEMTHU_QUYETTOAN);

										dtt.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(MA_QUATRINH_NGHIEMTHU_QUYETTOAN);
									}
									int MAGDNGTH = controler.getControl_NGHIEMTHU().create_GIAI_DOAN_NGHIEMTHU(dtt);
									gdngth = new GIAI_DOAN_NGHIEM_THU();
									gdngth.setMA_GIAI_DOAN_NGHIEM_THU(MAGDNGTH);

								}
								controler.getControl_NGHIEMTHU_CANBO().setNGUOIDUNG_NGHIEMTHU(TEN_TAI_KHOAN, gdngth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);
								TinhTrang_Nghiemthu(dtt);
							}
							break;
						case 2:
							System.out.println("Thanh ly-");
							break;

						default:
							break;
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
				new Label(grpNghimThu, SWT.NONE);
		
				Button button = new Button(grpNghimThu, SWT.NONE);
				button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				button.setImage(SWTResourceManager.getImage(GiaoViec.class, "/folder-documents-icon(1).png"));
				button.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							int s = tabFolder_2.getSelectionIndex();
							switch (s) {
							case 0:
								TreeItem[] ti = tree_CongviecSuachua.getSelection();
								if (ti.length > 0) {
									DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) ti[0].getData();
									GIAI_DOAN_NGHIEM_THU gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_NGHIEMTHU,
											gdth);

									vph.open();

									//
								}
								break;
							case 1:
								TreeItem[] ti2 = tree_CongViecMuasam.getSelection();
								if (ti2.length > 0) {
									DOT_THUCHIEN_TANG_TAISAN dsb = (DOT_THUCHIEN_TANG_TAISAN) ti2[0].getData();
									GIAI_DOAN_NGHIEM_THU gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_NGHIEMTHU,
											gdth);
									vph.open();
									//
								}
								break;
							case 2:
								// do nothing
								break;

							default:
								break;
							}
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					}
				});
				button.setText("Hồ sơ thực hiện");

		Group grpQuytTon = new Group(sashForm_7, SWT.NONE);
		grpQuytTon.setFont(new Font(display, "Consolas", 9, SWT.BOLD));
		grpQuytTon.setForeground(new Color(null, 255, 1, 51));
		grpQuytTon.setText("Quyết toán - Thanh lý hợp đồng");
		grpQuytTon.setLayout(new GridLayout(2, false));

		Label label_2 = new Label(grpQuytTon, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Ngày bắt đầu:");

		text_QUYETTOAN_Batdau = new Text(grpQuytTon, SWT.NONE);
		text_QUYETTOAN_Batdau.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_QUYETTOAN_Batdau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_QUYETTOAN_Batdau.setEditable(false);

		Label label_3 = new Label(grpQuytTon, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("Dự kiến:");

		text_QUYETTOAN_Dukien = new Text(grpQuytTon, SWT.NONE);
		text_QUYETTOAN_Dukien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_QUYETTOAN_Dukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_QUYETTOAN_Dukien.setEditable(false);

		Label label_4 = new Label(grpQuytTon, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("Ngày hoàn thành:");

		text_QUYETTOAN_Hoanthanh = new Text(grpQuytTon, SWT.NONE);
		text_QUYETTOAN_Hoanthanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_QUYETTOAN_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_QUYETTOAN_Hoanthanh.setEditable(false);

		Label label_6 = new Label(grpQuytTon, SWT.NONE);
		label_6.setText("Ghi chú:");
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));

		text_Ghichu_Quyettoan = new Text(grpQuytTon, SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu_Quyettoan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_text_Ghichu_Quyettoan = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Ghichu_Quyettoan.heightHint = 50;
		text_Ghichu_Quyettoan.setLayoutData(gd_text_Ghichu_Quyettoan);
		text_Ghichu_Quyettoan.setEditable(false);

		tree_QUYETTOAN = new Tree(grpQuytTon, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tree_QUYETTOAN = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_tree_QUYETTOAN.heightHint = 100;
		tree_QUYETTOAN.setLayoutData(gd_tree_QUYETTOAN);
		tree_QUYETTOAN.setHeaderVisible(true);

		TreeColumn trclmnTnCnB_2 = new TreeColumn(tree_QUYETTOAN, SWT.NONE);
		trclmnTnCnB_2.setWidth(110);
		trclmnTnCnB_2.setText("Cán bộ thực hiện");

		TreeColumn trclmnTiKhon_2 = new TreeColumn(tree_QUYETTOAN, SWT.NONE);
		trclmnTiKhon_2.setWidth(120);
		trclmnTiKhon_2.setText("Tài khoản");

		Menu menu_5 = new Menu(tree_QUYETTOAN);
		tree_QUYETTOAN.setMenu(menu_5);

		MenuItem mntmNhnVic_2 = new MenuItem(menu_5, SWT.NONE);
		mntmNhnVic_2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til;
					Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					switch (tabFolder_2.getSelectionIndex()) {
					case 0:
						til = tree_CongviecSuachua.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
							GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
							Fill_ItemData f = new Fill_ItemData();
							if (gdqt == null) {
								int MAGDNGTH = controler.getControl_QUYETTOAN().create_GIAI_DOAN_QUYETTOAN(dsb);
								gdqt = new GIAI_DOAN_QUYET_TOAN();
								gdqt.setMA_GIAI_DOAN_QUYET_TOAN(MAGDNGTH);
							}
							controler.getControl_QUYETTOAN_CANBO().setNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(), gdqt,
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Quyettoan(dsb);
						}
						break;
					case 1:
						til = tree_CongViecMuasam.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
							GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
							Fill_ItemData f = new Fill_ItemData();
							if (gdqt == null) {
								int MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
								if (MA_QUATRINH_NGHIEMTHU_QUYETTOAN > 0) {
									int MAGDTH = controler.getControl_QUYETTOAN().create_GIAI_DOAN_QUYETTOAN(dtt);
									gdqt = new GIAI_DOAN_QUYET_TOAN();
									gdqt.setMA_GIAI_DOAN_QUYET_TOAN(MAGDTH);
								}
							}
							controler.getControl_QUYETTOAN_CANBO().setNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(),
									controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt),
									f.getInt_HinhThucNhanCongviec_NguoiDungNhanViec(), THISDAY);
							TinhTrang_Quyettoan(dtt);
						}
						break;
					case 2:
						til = tree_CongviecThanhly.getSelection();
						if (til.length > 0) {
							DOT_THUCHIEN_GIAM_TAISAN dsb = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
							TinhTrang_Quyettoan(dsb);
						}
						break;
					default:
						break;
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		mntmNhnVic_2.setText("Nhận việc");

		MenuItem mntmTubo2 = new MenuItem(menu_5, SWT.NONE);
		mntmTubo2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] tmp = tree_QUYETTOAN.getSelection();
					if (tmp.length > 0) {
						user_congviec uc = (user_congviec) tmp[0].getData();
						if (uc != null) {
							if (controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(uc.getMA_GIAI_DOAN_CONG_VIEC())
									.getTHOI_DIEM_TIEP_NHAN() == null) {

								controler.getControl_QUYETTOAN_CANBO().deleteNGUOIDUNG_GIAI_DOAN_QUYET_TOAN(
										uc.getTEN_TAI_KHOAN(), uc.getMA_GIAI_DOAN_CONG_VIEC());
							} else {
								MessageBox m = new MessageBox(shlQunLCng, SWT.ICON_WARNING);
								m.setText("Thao tác sai");
								m.setMessage(
										"Không thể từ bỏ công việc sau khi đã bắt đầu thực hiện, hãy trả lại phần việc!");
								m.open();
							}
						}

						TreeItem[] til;
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								TinhTrang_Quyettoan(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								TinhTrang_Quyettoan(dtt);
							}
							break;
						case 2:
							til = tree_CongviecThanhly.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) til[0].getData();
								TinhTrang_Quyettoan(dgt);
							}
							break;

						default:
							break;
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTubo2.setText("Từ bỏ");

		TreeColumn trclmnHnhThcNhn_1 = new TreeColumn(tree_QUYETTOAN, SWT.NONE);
		trclmnHnhThcNhn_1.setWidth(100);
		trclmnHnhThcNhn_1.setText("Hình thức nhận việc");

		DropTarget dropTarget_2 = new DropTarget(tree_QUYETTOAN, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);

		dropTarget_2.setTransfer(types);

		TreeColumn trclmnNgaythamGia = new TreeColumn(tree_QUYETTOAN, SWT.NONE);
		trclmnNgaythamGia.setWidth(100);
		trclmnNgaythamGia.setText("Ngày tham gia");
		dropTarget_2.addDropListener(new DropTargetAdapter() {
			public void dragEnter(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// will accept text but prefer to have files dropped
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
						event.currentDataType = event.dataTypes[i];
						// files should only be copied
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					// NOTE: on unsupported platforms this will return null
					// Object o =
					// TextTransfer.getInstance().nativeToJava(event.currentDataType);
					// String t = (String) o;
					// if (t != null)
					// System.out.println(t);
				}
			}

			public void dragOperationChanged(DropTargetEvent event) {
				if (event.detail == DND.DROP_DEFAULT) {
					if ((event.operations & DND.DROP_COPY) != 0) {
						event.detail = DND.DROP_COPY;
					} else {
						event.detail = DND.DROP_NONE;
					}
				}
				// allow text to be moved but files should only be copied
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			public void dragLeave(DropTargetEvent event) {
			}

			public void dropAccept(DropTargetEvent event) {
			}

			public void drop(DropTargetEvent event) {
				try {
					if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
						String TEN_TAI_KHOAN = event.data.toString();
						System.out.println(TEN_TAI_KHOAN);
						// System.out.println(((TreeItem)event.item).getText());
						// if (event.item != null)
						// a
						TreeItem[] til;
						Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
						switch (tabFolder_2.getSelectionIndex()) {
						case 0:
							til = tree_CongviecSuachua.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) til[0].getData();
								GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN()
										.get_GIAIDOAN_QUYETTOAN(dsb);
								if (gdth == null) {
									int MAGDTH = controler.getControl_QUYETTOAN().create_GIAI_DOAN_QUYETTOAN(dsb);
									gdth = new GIAI_DOAN_QUYET_TOAN();
									gdth.setMA_GIAI_DOAN_QUYET_TOAN(MAGDTH);
								}
								Fill_ItemData f = new Fill_ItemData();
								controler.getControl_QUYETTOAN_CANBO().setNGUOIDUNG_QUYETTOAN(TEN_TAI_KHOAN, gdth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);

								TinhTrang_Quyettoan(dsb);
							}
							break;
						case 1:
							til = tree_CongViecMuasam.getSelection();
							if (til.length > 0) {
								DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) til[0].getData();
								GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN()
										.get_GIAIDOAN_QUYETTOAN(dtt);
								Fill_ItemData f = new Fill_ItemData();
								if (gdth == null) {
									int MAGDTH = controler.getControl_QUYETTOAN().create_GIAI_DOAN_QUYETTOAN(dtt);
									gdth = new GIAI_DOAN_QUYET_TOAN();
									gdth.setMA_GIAI_DOAN_QUYET_TOAN(MAGDTH);

								}
								controler.getControl_QUYETTOAN_CANBO().setNGUOIDUNG_QUYETTOAN(TEN_TAI_KHOAN, gdth,
										f.getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung(), THISDAY);

								TinhTrang_Quyettoan(dtt);
							}
							break;
						case 2:
							System.out.println("Thanh ly-");
							break;

						default:
							break;
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
				new Label(grpQuytTon, SWT.NONE);
		
				Button button_1 = new Button(grpQuytTon, SWT.NONE);
				button_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				button_1.setImage(SWTResourceManager.getImage(GiaoViec.class, "/folder-documents-icon(1).png"));
				button_1.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							int s = tabFolder_2.getSelectionIndex();
							switch (s) {
							case 0:
								TreeItem[] ti = tree_CongviecSuachua.getSelection();
								if (ti.length > 0) {
									DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) ti[0].getData();
									GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_QUYETTOAN,
											gdth);

									vph.open();

									//
								}
								break;
							case 1:
								TreeItem[] ti2 = tree_CongViecMuasam.getSelection();
								if (ti2.length > 0) {
									DOT_THUCHIEN_TANG_TAISAN dsb = (DOT_THUCHIEN_TANG_TAISAN) ti2[0].getData();
									GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
									ViewPartHoso vph = new ViewPartHoso(shlQunLCng, SWT.DIALOG_TRIM, user, tree_QUYETTOAN,
											gdth);
									vph.open();
									//
								}
								break;
							case 2:
								// do nothing
								break;

							default:
								break;
							}
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					}
				});
				button_1.setText("Hồ sơ thực hiện");
		sashForm_7.setWeights(new int[] { 1, 1, 1 });

		TabItem tbtmPhngTinTi = new TabItem(tabFolder_Phanviec, SWT.NONE);
		tbtmPhngTinTi.setText("Phương tiện tài sản tham gia");

		tree_TaiSanXuly = new Tree(tabFolder_Phanviec, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tbtmPhngTinTi.setControl(tree_TaiSanXuly);
		tree_TaiSanXuly.setLinesVisible(true);
		tree_TaiSanXuly.setHeaderVisible(true);

		ts.setTreeItemHeight(tree_TaiSanXuly, treeHeight);

		TreeColumn trclmnStt_6 = new TreeColumn(tree_TaiSanXuly, SWT.CENTER);
		trclmnStt_6.setWidth(55);
		trclmnStt_6.setText("STT");

		TreeColumn trclmnTenPtts = new TreeColumn(tree_TaiSanXuly, SWT.CENTER);
		trclmnTenPtts.setWidth(160);
		trclmnTenPtts.setText("T\u00CAN PTTS");

		TreeColumn trclmnLoaiPtts = new TreeColumn(tree_TaiSanXuly, SWT.CENTER);
		trclmnLoaiPtts.setWidth(100);
		trclmnLoaiPtts.setText("MODEL");

		TreeColumn trclmnNewColumn = new TreeColumn(tree_TaiSanXuly, SWT.NONE);
		trclmnNewColumn.setWidth(100);
		trclmnNewColumn.setText("NGÀY SỬ DỤNG");

		TreeColumn trclmnMaPtts = new TreeColumn(tree_TaiSanXuly, SWT.CENTER);
		trclmnMaPtts.setWidth(100);
		trclmnMaPtts.setText("ĐƠN VỊ SỬ DỤNG");

		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree_TaiSanXuly, SWT.NONE);
		trclmnNewColumn_1.setWidth(100);
		trclmnNewColumn_1.setText("SỐ SÊ-RI");

		Menu menu_6 = new Menu(tree_TaiSanXuly);
		tree_TaiSanXuly.setMenu(menu_6);

		MenuItem mntmXemThngTin = new MenuItem(menu_6, SWT.NONE);
		mntmXemThngTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_TaiSanXuly.getSelection();
					if (til.length > 0) {
						TAISAN ts = (TAISAN) til[0].getData();
						View_Taisan vt = new View_Taisan(display.getShells()[0], SWT.DIALOG_TRIM, user,
								ts.getMA_TAISAN());
						vt.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemThngTin.setText("Xem thông tin Phương tiện tài sản");

		TabItem tbtmNgunTng = new TabItem(tabFolder_Phanviec, SWT.NONE);
		tbtmNgunTng.setText("Nguồn Sửa chữa");

		Composite composite_4 = new Composite(tabFolder_Phanviec, SWT.NONE);
		tbtmNgunTng.setControl(composite_4);
		composite_4.setLayout(new GridLayout(2, false));

		Label lblTnNgunTng = new Label(composite_4, SWT.NONE);
		lblTnNgunTng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnNgunTng.setText("Tên nguồn tăng:");

		text_Nguontang = new Text(composite_4, SWT.NONE);
		text_Nguontang.setEditable(false);
		text_Nguontang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite_4, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite_4, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Gioithieu.setEditable(false);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(composite_4, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Liên hệ:");

		text_LienHe = new Text(composite_4, SWT.WRAP | SWT.MULTI);
		text_LienHe.setEditable(false);
		text_LienHe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group grpGhiCh = new Group(sashForm, SWT.NONE);
		grpGhiCh.setText("Mô tả");
		grpGhiCh.setLayout(new GridLayout(1, false));

		text = new Text(grpGhiCh, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		sashForm.setWeights(new int[] {163, 260, 62});
		sashForm_13.setWeights(new int[] { 1000 });

		fill_DanhsachCanbo();
		FillTableSuachua();
		FillTableMuasam();
		FillTableThanhly();

		shlQunLCng.open();
		shlQunLCng.layout();
		while (!shlQunLCng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void fill_DanhsachCanbo() throws SQLException {
		tree_DanhsachCanbo.removeAll();
		ArrayList<NGUOIDUNG> ndl = controler.getControl_NGUOIDUNG().get_All();
		int i = 1;
		for (NGUOIDUNG nd : ndl) {
			PHONGBAN p = controler.getControl_PHONGBAN().get_PHONGBAN(nd.getMA_PHONGBAN());
			if (p != null) {
				TreeItem ti = new TreeItem(tree_DanhsachCanbo, SWT.NONE);
				ti.setText(new String[] { "" + i, nd.getTEN_CAN_BO(), p.getTEN_PHONGBAN(), nd.getTEN_TAI_KHOAN() });
				ti.setData(nd);
				i++;
			}
		}
		treePack(tree_DanhsachCanbo);
	}

	protected void view_Congviec_Da_Thuchien(NGUOIDUNG nd) throws SQLException {
		tree_PhanViecDaThucHien.removeAll();
		ArrayList<CONGVIEC_PHANVIEC> th_SCBD_l = controler.getControl_THUCHIEN().get_DaThucHien_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> th_MUASAM_l = controler.getControl_THUCHIEN().get_DaThucHien_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> th_THANHLY_l = controler.getControl_THUCHIEN().get_DaThucHien_GIAM_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> nt_SCBD_l = controler.getControl_NGHIEMTHU().get_DaNghiemthu_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> nt_MUASAM_l = controler.getControl_NGHIEMTHU().get_DaNghiemthu_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> qt_SCBD_l = controler.getControl_QUYETTOAN().get_DaQuyettoan_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> qt_MUASAM_l = controler.getControl_QUYETTOAN().get_DaQuyettoan_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> data = new ArrayList<>();
		data.addAll(th_SCBD_l);
		data.addAll(th_MUASAM_l);
		data.addAll(th_THANHLY_l);
		data.addAll(nt_SCBD_l);
		data.addAll(nt_MUASAM_l);
		data.addAll(qt_SCBD_l);
		data.addAll(qt_MUASAM_l);
		int i = 1;
		for (CONGVIEC_PHANVIEC e : data) {
			Fill_ItemData f = new Fill_ItemData();
			TreeItem ti = new TreeItem(tree_PhanViecDaThucHien, SWT.NONE);
			Date begin = e.getTHOI_DIEM_BAT_DAU();
			Date end = e.getTHOI_DIEM_HOAN_THANH();
			long t = end.getTime() - begin.getTime();
			long difDate = t / (1000 * 60 * 60 * 24);
			int value = (int) difDate;
			ti.setText(new String[] { "" + i, String.valueOf(e.getMA_CONGVIEC()),
					f.getString_LoaiCongviec(e.getLOAI_CONGVIEC()), f.getString_LOAI_PHANVIEC(e.getLOAI_PHANVIEC()),
					e.getTEN_CONGVIEC(),

					e.getTHOI_DIEM_BAT_DAU() == null ? "" : mdf.getViewStringDate(e.getTHOI_DIEM_BAT_DAU()),
					mdf.getViewStringDate(e.getTHOI_DIEM_HOAN_THANH()), value + "" });
			ti.setData(e);
			i++;
		}
		treePack(tree_PhanViecDaThucHien);
	}

	protected void view_Congviec_Dang_Thuchien(NGUOIDUNG nd) throws SQLException {
		tree_PhanViecDangThucHien.removeAll();
		ArrayList<CONGVIEC_PHANVIEC> th_SCBD_l = controler.getControl_THUCHIEN().get_DangThucHien_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> th_MUASAM_l = controler.getControl_THUCHIEN().get_DangThucHien_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> th_THANHLY_l = controler.getControl_THUCHIEN().get_DangThucHien_GIAM_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> nt_SCBD_l = controler.getControl_NGHIEMTHU()
				.get_DangNghiemthu_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> nt_MUASAM_l = controler.getControl_NGHIEMTHU().get_DangNghiemthu_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> qt_SCBD_l = controler.getControl_QUYETTOAN()
				.get_DangQuyettoan_SUACHUA_BAODUONG(nd);
		ArrayList<CONGVIEC_PHANVIEC> qt_MUASAM_l = controler.getControl_QUYETTOAN().get_DangQuyettoan_TANG_TAISAN(nd);
		ArrayList<CONGVIEC_PHANVIEC> data = new ArrayList<>();
		data.addAll(th_SCBD_l);
		data.addAll(th_MUASAM_l);
		data.addAll(th_THANHLY_l);
		data.addAll(nt_SCBD_l);
		data.addAll(nt_MUASAM_l);
		data.addAll(qt_SCBD_l);
		data.addAll(qt_MUASAM_l);
		int i = 1;
		for (CONGVIEC_PHANVIEC e : data) {
			Fill_ItemData f = new Fill_ItemData();
			TreeItem ti = new TreeItem(tree_PhanViecDangThucHien, SWT.NONE);
			ti.setText(new String[] { "" + i, f.getString_LoaiCongviec(e.getLOAI_CONGVIEC()),
					f.getString_LOAI_PHANVIEC(e.getLOAI_PHANVIEC()), e.getTEN_CONGVIEC(),
					String.valueOf(e.getMA_CONGVIEC()),
					e.getTHOI_DIEM_BAT_DAU() == null ? "" : mdf.getViewStringDate(e.getTHOI_DIEM_BAT_DAU()),
					e.getTHOI_GIAN_DU_KIEN_HOAN_THANH() + "" });
			ti.setData(e);
			i++;
		}
		treePack(tree_PhanViecDangThucHien);

	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
		if (dx != null) {
			text_Sodexuat.setText(dx.getSODEXUAT());
			text_NgaythangVanban.setText(dx.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_DonviDexuat.setText(ten);
			text_Ngaythuchien.setText(dx.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
			text_TenCB.setText(controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN()).getTEN_CAN_BO());
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_Ngay_Hoantat_GiaoViec.setText(dx.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_HOAN_THANH()));
			text_Ghichu.setText(dx.getGHI_CHU() == null ? "" : dx.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	protected void setField_ThongTin_NGUONGIAM(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		NGUONGIAM nt = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
		if (nt != null) {
			text_Nguontang.setText(nt.getTEN_NGUONGIAM());
			text_Gioithieu.setText(nt.getGIOI_THIEU());
			text_LienHe.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		if (tree_TaiSanXuly.isVisible()) {
			if (tree_TaiSanXuly.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_TaiSanXuly.getListeners(SWT.SetData)) {
					tree_TaiSanXuly.removeListener(SWT.SetData, lst);
				}
			tree_TaiSanXuly.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dgt);

			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_TaiSanXuly.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						parent.setText(new String[] { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) });
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(),
										"", "", pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) });
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_TaiSanXuly.setItemCount(COUNT);

			treePack(tree_TaiSanXuly);
		}
	}

	protected void TinhTrang_Thuchien(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		tree_THUCHIEN.removeAll();
		GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
		if (th != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_THUCHIEN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(th);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_THUCHIEN, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				Fill_ItemData f = new Fill_ItemData();
				NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
						.getNGUOIDUNG_THUCHIEN(nd.getTEN_TAI_KHOAN(), th);
				if (ndth != null) {
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndth));
					t.setText(3, "" + mdf.getViewStringDate(ndth.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(th.getMA_GIAI_DOAN_THUC_HIEN());
					t.setData(uc);
				}
				// uc.setGIAO_NHANVIEC(gIAO_NHANVIEC);
			}
			treePack(tree_THUCHIEN);
			text_Ghichu_Thuchien.setText(th.getGHI_CHU() == null ? "" : th.getGHI_CHU());
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

			text_THUCHIEN_Batdau.setText(th.getTHOI_DIEM_BAT_DAU() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(th.getTHOI_DIEM_BAT_DAU()));
			if (th.getTHOI_DIEM_BAT_DAU() == null) {
				text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Chuyengiao.setText(th.getTHOI_DIEM_CHUYEN_GIAO() == null ? "chưa chuyển giao"
					: mdf.getViewStringDate(th.getTHOI_DIEM_CHUYEN_GIAO()));
			if (th.getTHOI_DIEM_CHUYEN_GIAO() == null) {
				text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Dukien.setText(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Hoanthanh.setText(th.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(th.getTHOI_DIEM_HOAN_THANH()));
			if (th.getTHOI_DIEM_HOAN_THANH() == null) {
				text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Thuchien.setText("");
			text_THUCHIEN_Batdau.setText("chưa bắt đầu");
			text_THUCHIEN_Chuyengiao.setText("chưa Chuyển giao");
			text_THUCHIEN_Dukien.setText("chưa thực hiện");
			text_THUCHIEN_Hoanthanh.setText("Chưa hoàn thành");
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	protected void TinhTrang_Nghiemthu(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		tree_NGHIEMTHU.removeAll();
		text_Ghichu_Nghiemthu.setText("");
		text_NGHIEMTHU_Batdau.setText("Không thực hiện");
		text_NGHIEMTHU_Chuyengiao.setText("Không thực hiện");
		text_NGHIEMTHU_dukien.setText("Không thực hiện");
		text_NGHIEMTHU_Hoanthanh.setText("Không thực hiện");
		text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	}

	protected void TinhTrang_Quyettoan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		tree_QUYETTOAN.removeAll();
		text_Ghichu_Quyettoan.setText("");
		text_QUYETTOAN_Batdau.setText("Không thực hiện");
		text_QUYETTOAN_Dukien.setText("Không thực hiện");
		text_QUYETTOAN_Hoanthanh.setText("Không thực hiện");
		text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	}

	protected void TinhTrang_Quyettoan(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		tree_QUYETTOAN.removeAll();
		GIAI_DOAN_QUYET_TOAN qt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
		if (qt != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_QUYETTOAN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(qt);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_QUYETTOAN, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
						.getNGUOIDUNG_QUYETTOAN(nd.getTEN_TAI_KHOAN(), qt);
				if (ndqt != null) {
					Fill_ItemData f = new Fill_ItemData();
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndqt));
					t.setText(3, "" + mdf.getViewStringDate(ndqt.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(ndqt.getMA_GIAI_DOAN_QUYET_TOAN());
					t.setData(uc);
				}
			}
			treePack(tree_QUYETTOAN);
			text_Ghichu_Quyettoan.setText(qt.getGHI_CHU() == null ? "" : qt.getGHI_CHU());
			text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Batdau.setText(qt.getTHOI_DIEM_TIEP_NHAN() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(qt.getTHOI_DIEM_TIEP_NHAN()));
			if (qt.getTHOI_DIEM_TIEP_NHAN() == null) {
				text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_QUYETTOAN_Dukien.setText(qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_QUYETTOAN_Hoanthanh.setText(qt.getTHOI_GIAN_KET_THUC() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(qt.getTHOI_GIAN_KET_THUC()));
			if (qt.getTHOI_GIAN_KET_THUC() == null) {
				text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Quyettoan.setText("");
			text_QUYETTOAN_Batdau.setText("chưa bắt đầu");
			text_QUYETTOAN_Dukien.setText("chưa thực hiện");
			text_QUYETTOAN_Hoanthanh.setText("Chưa hoàn thành");
			text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	protected void TinhTrang_Nghiemthu(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		tree_NGHIEMTHU.removeAll();
		GIAI_DOAN_NGHIEM_THU ngth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
		if (ngth != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_NGHIEMTHU_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(ngth);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_NGHIEMTHU, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				Fill_ItemData f = new Fill_ItemData();
				NGUOIDUNG_NGHIEMTHU ndngth = controler.getControl_NGHIEMTHU_CANBO()
						.getNGUOIDUNG_NGHIEMTHU(nd.getTEN_TAI_KHOAN(), ngth);
				if (ndngth != null) {
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndngth));
					t.setText(3, "" + mdf.getViewStringDate(ndngth.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(ndngth.getMA_GIAI_DOAN_NGHIEM_THU());
					t.setData(uc);
				}
			}
			treePack(tree_NGHIEMTHU);
			text_Ghichu_Nghiemthu.setText(ngth.getGHI_CHU() == null ? "" : ngth.getGHI_CHU());
			text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Batdau.setText(ngth.getTHOI_DIEM_TIEP_NHAN() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_TIEP_NHAN()));
			if (ngth.getTHOI_DIEM_TIEP_NHAN() == null) {
				text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_Chuyengiao.setText(ngth.getTHOI_DIEM_CHUYEN_GIAO() == null ? "chưa chuyển giao"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_CHUYEN_GIAO()));
			if (ngth.getTHOI_DIEM_CHUYEN_GIAO() == null) {
				text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_dukien.setText(ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_Hoanthanh.setText(ngth.getTHOI_DIEM_KET_THUC() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_KET_THUC()));
			if (ngth.getTHOI_DIEM_KET_THUC() == null) {
				text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Nghiemthu.setText("");
			text_NGHIEMTHU_Batdau.setText("chưa bắt đầu");
			text_NGHIEMTHU_Chuyengiao.setText("chưa thực hiện");
			text_NGHIEMTHU_dukien.setText("chưa thực hiện");
			text_NGHIEMTHU_Hoanthanh.setText("Chưa hoàn thành");
			text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}

	}

	protected void TinhTrang_Thuchien(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		tree_THUCHIEN.removeAll();
		GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
		if (th != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_THUCHIEN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(th);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_THUCHIEN, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				Fill_ItemData f = new Fill_ItemData();
				NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
						.getNGUOIDUNG_THUCHIEN(nd.getTEN_TAI_KHOAN(), th);
				if (ndth != null) {
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndth));
					t.setText(3, "" + mdf.getViewStringDate(ndth.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(th.getMA_GIAI_DOAN_THUC_HIEN());
					t.setData(uc);
				}
				// uc.setGIAO_NHANVIEC(gIAO_NHANVIEC);
			}
			treePack(tree_THUCHIEN);
			treePack(tree_THUCHIEN);
			text_Ghichu_Thuchien.setText(th.getGHI_CHU() == null ? "" : th.getGHI_CHU());
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

			text_THUCHIEN_Batdau.setText(th.getTHOI_DIEM_BAT_DAU() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(th.getTHOI_DIEM_BAT_DAU()));
			if (th.getTHOI_DIEM_BAT_DAU() == null) {
				text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Chuyengiao.setText(th.getTHOI_DIEM_CHUYEN_GIAO() == null ? "chưa chuyển giao"
					: mdf.getViewStringDate(th.getTHOI_DIEM_CHUYEN_GIAO()));
			if (th.getTHOI_DIEM_CHUYEN_GIAO() == null) {
				text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Dukien.setText(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Hoanthanh.setText(th.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(th.getTHOI_DIEM_HOAN_THANH()));
			if (th.getTHOI_DIEM_HOAN_THANH() == null) {
				text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Thuchien.setText("");
			text_THUCHIEN_Batdau.setText("chưa bắt đầu");
			text_THUCHIEN_Chuyengiao.setText("chưa Chuyển giao");
			text_THUCHIEN_Dukien.setText("chưa thực hiện");
			text_THUCHIEN_Hoanthanh.setText("Chưa hoàn thành");
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		if (tree_TaiSanXuly.isVisible()) {
			if (tree_TaiSanXuly.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_TaiSanXuly.getListeners(SWT.SetData)) {
					tree_TaiSanXuly.removeListener(SWT.SetData, lst);
				}

			tree_TaiSanXuly.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dsb);

			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_TaiSanXuly.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						parent.setText(new String[] { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) });
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(),
										"", "", pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) });
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_TaiSanXuly.setItemCount(COUNT);
		}
		treePack(tree_TaiSanXuly);

	}

	protected void setField_ThongTin_NGUONSUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		NGUONSUACHUA_BAODUONG nsb = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(dsb);
		if (nsb != null) {
			text_Nguontang.setText(nsb.getTEN_NGUONSUACHUA_BAODUONG());
			text_Gioithieu.setText(nsb.getGIOI_THIEU());
			text_LienHe.setText(nsb.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);
		if (dx != null) {
			text_Sodexuat.setText(dx.getSODEXUAT());
			text_NgaythangVanban.setText(dx.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_DonviDexuat.setText(ten);
			text_Ngaythuchien.setText(dx.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
			text_TenCB.setText(controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN()).getTEN_CAN_BO());
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_Ngay_Hoantat_GiaoViec.setText(dx.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_HOAN_THANH()));
			if (dx.getTHOI_DIEM_HOAN_THANH() == null) {
				text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			} else {
				text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			}
			text_Ghichu.setText(dx.getGHI_CHU() == null ? "" : dx.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	protected void TinhTrang_Quyettoan(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		tree_QUYETTOAN.removeAll();
		GIAI_DOAN_QUYET_TOAN qt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
		if (qt != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_QUYETTOAN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(qt);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_QUYETTOAN, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
						.getNGUOIDUNG_QUYETTOAN(nd.getTEN_TAI_KHOAN(), qt);
				if (ndqt != null) {
					Fill_ItemData f = new Fill_ItemData();
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndqt));
					t.setText(3, "" + mdf.getViewStringDate(ndqt.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(ndqt.getMA_GIAI_DOAN_QUYET_TOAN());
					t.setData(uc);
				}
			}
			treePack(tree_QUYETTOAN);
			text_Ghichu_Quyettoan.setText(qt.getGHI_CHU() == null ? "" : qt.getGHI_CHU());
			text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_QUYETTOAN_Batdau.setText(qt.getTHOI_DIEM_TIEP_NHAN() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(qt.getTHOI_DIEM_TIEP_NHAN()));
			if (qt.getTHOI_DIEM_TIEP_NHAN() == null) {
				text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_QUYETTOAN_Dukien.setText(qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (qt.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_QUYETTOAN_Hoanthanh.setText(qt.getTHOI_GIAN_KET_THUC() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(qt.getTHOI_GIAN_KET_THUC()));
			if (qt.getTHOI_GIAN_KET_THUC() == null) {
				text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Quyettoan.setText("");
			text_QUYETTOAN_Batdau.setText("chưa bắt đầu");
			text_QUYETTOAN_Dukien.setText("chưa thực hiện");
			text_QUYETTOAN_Hoanthanh.setText("Chưa hoàn thành");
			text_QUYETTOAN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_QUYETTOAN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_QUYETTOAN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	protected void TinhTrang_Nghiemthu(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		tree_NGHIEMTHU.removeAll();
		GIAI_DOAN_NGHIEM_THU ngth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
		if (ngth != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_NGHIEMTHU_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(ngth);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_NGHIEMTHU, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				Fill_ItemData f = new Fill_ItemData();
				NGUOIDUNG_NGHIEMTHU ndngth = controler.getControl_NGHIEMTHU_CANBO()
						.getNGUOIDUNG_NGHIEMTHU(nd.getTEN_TAI_KHOAN(), ngth);
				if (ndngth != null) {
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndngth));
					t.setText(3, "" + mdf.getViewStringDate(ndngth.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(ndngth.getMA_GIAI_DOAN_NGHIEM_THU());
					t.setData(uc);
				}
			}
			treePack(tree_NGHIEMTHU);
			text_Ghichu_Nghiemthu.setText(ngth.getGHI_CHU() == null ? "" : ngth.getGHI_CHU());
			text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_NGHIEMTHU_Batdau.setText(ngth.getTHOI_DIEM_TIEP_NHAN() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_TIEP_NHAN()));
			if (ngth.getTHOI_DIEM_TIEP_NHAN() == null) {
				text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_dukien.setText(ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (ngth.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_Chuyengiao.setText(ngth.getTHOI_DIEM_CHUYEN_GIAO() == null ? "chưa Chuyển giao"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_CHUYEN_GIAO()));
			if (ngth.getTHOI_DIEM_CHUYEN_GIAO() == null) {
				text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_NGHIEMTHU_Hoanthanh.setText(ngth.getTHOI_DIEM_KET_THUC() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(ngth.getTHOI_DIEM_KET_THUC()));
			if (ngth.getTHOI_DIEM_KET_THUC() == null) {
				text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Nghiemthu.setText("");
			text_NGHIEMTHU_Batdau.setText("chưa bắt đầu");
			text_NGHIEMTHU_Chuyengiao.setText("chưa thực hiện");
			text_NGHIEMTHU_dukien.setText("chưa thực hiện");
			text_NGHIEMTHU_Hoanthanh.setText("Chưa hoàn thành");
			text_NGHIEMTHU_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_NGHIEMTHU_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}

	}

	protected void TinhTrang_Thuchien(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		tree_THUCHIEN.removeAll();
		GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
		if (th != null) {
			ArrayList<NGUOIDUNG> ndl = controler.getControl_THUCHIEN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(th);
			for (NGUOIDUNG nd : ndl) {
				TreeItem t = new TreeItem(tree_THUCHIEN, SWT.NONE);
				t.setText(0, "" + nd.getTEN_CAN_BO());
				t.setText(1, "" + nd.getTEN_TAI_KHOAN());
				Fill_ItemData f = new Fill_ItemData();
				NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
						.getNGUOIDUNG_THUCHIEN(nd.getTEN_TAI_KHOAN(), th);
				if (ndth != null) {
					t.setText(2, "" + f.getString_GIAO_NHANVIEC(ndth));
					t.setText(3, "" + mdf.getViewStringDate(ndth.getNGAY_THAM_GIA()));
					user_congviec uc = new user_congviec();
					uc.setTEN_TAI_KHOAN(nd.getTEN_TAI_KHOAN());
					uc.setMA_GIAI_DOAN_CONG_VIEC(th.getMA_GIAI_DOAN_THUC_HIEN());
					t.setData(uc);
				}
				// uc.setGIAO_NHANVIEC(gIAO_NHANVIEC);
			}
			treePack(tree_THUCHIEN);

			text_Ghichu_Thuchien.setText(th.getGHI_CHU() == null ? "" : th.getGHI_CHU());
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));

			text_THUCHIEN_Batdau.setText(th.getTHOI_DIEM_BAT_DAU() == null ? "chưa bắt đầu"
					: mdf.getViewStringDate(th.getTHOI_DIEM_BAT_DAU()));
			if (th.getTHOI_DIEM_BAT_DAU() == null) {
				text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Chuyengiao.setText(th.getTHOI_DIEM_CHUYEN_GIAO() == null ? "chưa chuyển giao"
					: mdf.getViewStringDate(th.getTHOI_DIEM_CHUYEN_GIAO()));
			if (th.getTHOI_DIEM_CHUYEN_GIAO() == null) {
				text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Dukien.setText(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0 ? "chưa thực hiện"
					: String.valueOf(th.getTHOI_GIAN_DU_KIEN_HOAN_THANH()));
			if (th.getTHOI_GIAN_DU_KIEN_HOAN_THANH() == 0) {
				text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
			text_THUCHIEN_Hoanthanh.setText(th.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn thành"
					: mdf.getViewStringDate(th.getTHOI_DIEM_HOAN_THANH()));
			if (th.getTHOI_DIEM_HOAN_THANH() == null) {
				text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			}
		} else {
			text_Ghichu_Thuchien.setText("");
			text_THUCHIEN_Batdau.setText("chưa bắt đầu");
			text_THUCHIEN_Chuyengiao.setText("chưa Chuyển giao");
			text_THUCHIEN_Dukien.setText("chưa thực hiện");
			text_THUCHIEN_Hoanthanh.setText("Chưa hoàn thành");
			text_THUCHIEN_Chuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Batdau.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Dukien.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_THUCHIEN_Hoanthanh.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		if (tree_TaiSanXuly.isVisible()) {
			if (tree_TaiSanXuly.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_TaiSanXuly.getListeners(SWT.SetData)) {
					tree_TaiSanXuly.removeListener(SWT.SetData, lst);
				}
			tree_TaiSanXuly.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dtt);
			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_TaiSanXuly.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						parent.setText(new String[] { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(),
								t.getNGAY_SU_DUNG() == null ? "-" : mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) });
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(),
										"", "", pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) });
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_TaiSanXuly.setItemCount(COUNT);
			treePack(tree_TaiSanXuly);
		}
	}

	protected void setField_ThongTin_NGUONTANG(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		NGUONTANG nt = controler.getControl_NGUONTANG().get_NguonTang(dtt);
		if (nt != null) {
			text_Nguontang.setText(nt.getTEN_NGUONTANG());
			text_Gioithieu.setText(nt.getGIOI_THIEU());
			text_LienHe.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	private void clearText_NGUONTANG() {
		text_Nguontang.setText("");
		text_Gioithieu.setText("");
		text_LienHe.setText("");

	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
		if (dx != null) {
			text_Sodexuat.setText(dx.getSODEXUAT());
			text_NgaythangVanban.setText(dx.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_DonviDexuat.setText(ten);
			text_Ngaythuchien.setText(dx.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
			text_TenCB.setText(controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN()).getTEN_CAN_BO());
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_Ngay_Hoantat_GiaoViec.setText(dx.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(dx.getTHOI_DIEM_HOAN_THANH()));
			text_Ghichu.setText(dx.getGHI_CHU() == null ? "" : dx.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	private void clearText_DEXUAT() {
		text_Sodexuat.setText("");
		text_NgaythangVanban.setText("");
		text_DonviDexuat.setText("");
		text_Ngaythuchien.setText("");
		text_TenCB.setText("");
		text_Ghichu.setText("");

	}

	public static void FillTableThanhly() throws SQLException {
		final Controler controler = new Controler(user);
		if (tree_CongviecThanhly != null && !tree_CongviecThanhly.isDisposed()) {
			tree_CongviecThanhly.removeAll();
			ArrayList<DOT_THUCHIEN_GIAM_TAISAN> nt = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
					.get_DangThucHien_DotGiamTaisan();
			int x = 1;
			if (nt != null)
				for (DOT_THUCHIEN_GIAM_TAISAN n : nt) {
					TreeItem Item = new TreeItem(tree_CongviecThanhly, SWT.NONE);
					Item.setText(0, "" + x);
					Item.setText(1, String.valueOf(n.getMA_DOT_GIAM()));
					Item.setText(2, "" + n.getTEN_DOT_GIAM());
					Item.setText(3, "" + n.getLY_DO_GIAM());
					Item.setData(n);
					x++;
				}
			treePack(tree_CongviecThanhly);
		}
	}

	static public void FillTableMuasam() throws SQLException {
		final Controler controler = new Controler(user);
		if (tree_CongViecMuasam != null && !tree_CongViecMuasam.isDisposed()) {
			tree_CongViecMuasam.removeAll();
			ArrayList<DOT_THUCHIEN_TANG_TAISAN> nt = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
					.get_DangThucHien_DotTangTaisan();
			int x = 1;
			if (nt != null)
				for (DOT_THUCHIEN_TANG_TAISAN n : nt) {
					TreeItem Item = new TreeItem(tree_CongViecMuasam, SWT.NONE);
					Item.setText(new String[] { x + "", n.getMA_DOT_TANG() + "", n.getTEN_DOT_TANG(),
							n.getLY_DO_TANG() + "" });
					Item.setData(n);
					x++;
				}
			treePack(tree_CongViecMuasam);
		}
	}

	static public void FillTableSuachua() throws SQLException {
		final Controler controler = new Controler(user);
		if (tree_CongviecSuachua != null && !tree_CongviecSuachua.isDisposed()) {
			tree_CongviecSuachua.removeAll();
			ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> nt = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.get_DangThucHien_Suachua_Baoduong();
			int x = 1;
			if (nt != null)
				for (DOT_THUCHIEN_SUACHUA_BAODUONG n : nt) {
					TreeItem Item = new TreeItem(tree_CongviecSuachua, SWT.NONE);
					Item.setText(0, "" + x);
					Item.setText(1, String.valueOf(n.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()));
					Item.setText(2, "" + n.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
					Fill_ItemData f = new Fill_ItemData();
					Item.setText(3, f.getStringOfSUACHUA_BAODUONG(n.getSUACHUA_BAODUONG()));
					DE_XUAT d = new DE_XUAT();
					d = controler.getControl_DEXUAT().get_DEXUAT(n);
					if (d != null) {
						Item.setText(4,
								controler.getControl_PHONGBAN().get_PHONGBAN(d.getMA_PHONGBAN()).getTEN_PHONGBAN());
						Item.setData(n);
						x++;
					}
				}
			treePack(tree_CongviecSuachua);
		}
	}

	static void treePack(Tree tree) {
		for (TreeColumn tc : tree.getColumns()) {
			tc.pack();
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlQunLCng = new Shell();
		shlQunLCng.setImage(SWTResourceManager.getImage(GiaoViec.class, "/reunion.png"));
		shlQunLCng.setSize(875, 540);
		shlQunLCng.setText("Công việc đang thực hiện");

	}

}
