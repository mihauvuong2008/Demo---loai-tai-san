package View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke;

import java.sql.SQLException;
import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.KY_HAN_THONGKE_XANG_DAU;
import DAO.LENH_DIEU_XE;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.AssetManagers.LenhDieuXe.QuanLy_Lenhdieuxe;
import View.AssetManagers.Taisan.Phuongtiengiaothong.EditPhuongtienGiaothong;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Thongke_PhuongtienGiaothong extends Shell {
	private static NGUOIDUNG user;
	private Date Date_Batdau;
	private Date Date_Ketthuc;
	private Table table_Quyettoantonghop;
	private Table table_1;
	private Table table_2;
	private Table table_Lichtrinh;
	private Table Oto_DanhsachXe;
	private Table table_Kyhan;
	private Tree tree;
	private Text Text_TongKmhoatdong;
	private Text Text_Tongnhienlieucap;
	private Text Text_TongNhienlieumuangoai;
	private Text Text_Tongnhienlieutieuthu;
	private Text text_Thongbao;
	private Label lblNhinLiuTiu;
	private Label btnTngNhinLiuMuangoai;
	protected int Dungsai = 3;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static Log log = LogFactory.getLog(Thongke_PhuongtienGiaothong.class);
	private Text text_NhienLieuTronglichtrinh;
	private Text text_TongQuangduongDukien;
	private Text text_ToncuoiKy;
	private Text text_TieuthuThucte;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Thongke_PhuongtienGiaothong shell = new Thongke_PhuongtienGiaothong(display, user);
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
	 * @param cuc_Item
	 * @throws SQLException
	 */

	public Thongke_PhuongtienGiaothong(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.CLOSE | SWT.MAX | SWT.TITLE);
		setImage(SWTResourceManager.getImage(Thongke_PhuongtienGiaothong.class,
				"/Actions-view-statistics-icon (1).png"));
		setLayout(new GridLayout(4, false));
		Thongke_PhuongtienGiaothong.user = user;
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));

		ToolItem tltmToKHn = new ToolItem(toolBar, SWT.NONE);
		tltmToKHn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Kyhan_Thongke kt = new Kyhan_Thongke(getShell(), SWT.DIALOG_TRIM, user);
					kt.open();
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmToKHn.setImage(SWTResourceManager.getImage(Thongke_PhuongtienGiaothong.class, "/view-preview.png"));
		tltmToKHn.setText("Quản lý Kỳ hạn Thống kê");

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmCpNhtD = new ToolItem(toolBar, SWT.NONE);
		tltmCpNhtD.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DungsaiBox dsb = new DungsaiBox(getShell(), SWT.DIALOG_TRIM, Dungsai);
				dsb.open();
				if (dsb.result != null)
					Dungsai = (int) dsb.result;
			}
		});
		tltmCpNhtD.setText("Dung sai nhiên liệu");
		tltmCpNhtD.setImage(SWTResourceManager.getImage(Thongke_PhuongtienGiaothong.class, "/Dungsai.png"));

		ToolItem tltmLuKtQu = new ToolItem(toolBar, SWT.NONE);
		tltmLuKtQu.setImage(
				SWTResourceManager.getImage(Thongke_PhuongtienGiaothong.class, "/Actions-document-save-icon (1).png"));
		tltmLuKtQu.setText("Lưu kết quả");

		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		SashForm sashForm_2 = new SashForm(sashForm_1, SWT.NONE);
		TreeRowStyle tsl = new TreeRowStyle();
		tree = new Tree(sashForm_2, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		new TreeTemplate(user).getTreePHONGBAN(tree);
		tree.pack();
		tsl.setTreeItemHeight(tree, 20);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TableItem[] items = table_Kyhan.getSelection();
					if (items.length > 0) {
						KY_HAN_THONGKE_XANG_DAU kh = (KY_HAN_THONGKE_XANG_DAU) items[0].getData();
						Date_Batdau = kh.getNGAY_BAT_DAU();
						Date_Ketthuc = kh.getNGAY_KET_THUC();
						if (Date_Batdau.before(Date_Ketthuc) || Date_Batdau.compareTo(Date_Ketthuc) == 0) {
							fillDanhsachOtoSudung(get_PHONGBAN_Selected(), Date_Batdau, Date_Ketthuc);
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		table_Kyhan = new Table(sashForm_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_Kyhan.setHeaderVisible(true);
		table_Kyhan.setLinesVisible(true);
		table_Kyhan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TableItem[] items = table_Kyhan.getSelection();
					if (items.length > 0) {
						KY_HAN_THONGKE_XANG_DAU kh = (KY_HAN_THONGKE_XANG_DAU) items[0].getData();
						Date_Batdau = kh.getNGAY_BAT_DAU();
						Date_Ketthuc = kh.getNGAY_KET_THUC();
						if (Date_Batdau.before(Date_Ketthuc) || Date_Batdau.compareTo(Date_Ketthuc) == 0) {
							fillDanhsachOtoSudung(get_PHONGBAN_Selected(), Date_Batdau, Date_Ketthuc);
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}

		});

		TableColumn tblclmnStt_1 = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnStt_1.setWidth(45);
		tblclmnStt_1.setText("STT");

		TableColumn tblclmnMKHn = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnMKHn.setWidth(100);
		tblclmnMKHn.setText("MÃ KỲ HẠN");

		TableColumn tblclmnTnKHn = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnTnKHn.setWidth(150);
		tblclmnTnKHn.setText("TÊN KỲ HẠN");

		TableColumn tblclmnTNgy = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnTNgy.setWidth(120);
		tblclmnTNgy.setText("TỪ NGÀY");

		TableColumn tblclmnnNgy = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnnNgy.setWidth(120);
		tblclmnnNgy.setText("ĐẾN NGÀY");

		TableColumn tblclmnGhiCh_1 = new TableColumn(table_Kyhan, SWT.NONE);
		tblclmnGhiCh_1.setWidth(200);
		tblclmnGhiCh_1.setText("GHI CHÚ");
		sashForm_2.setWeights(new int[] { 334, 537 });
		sashForm_1.setWeights(new int[] { 809 });

		TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);

		TabItem tbtmT = new TabItem(tabFolder, SWT.NONE);
		tbtmT.setText("Lịch trình Ô tô");

		TabFolder tabFolder_1 = new TabFolder(tabFolder, SWT.NONE);
		tbtmT.setControl(tabFolder_1);

		TabItem tbtmTngXe = new TabItem(tabFolder_1, SWT.NONE);
		tbtmTngXe.setText("Lịch trình từng xe");

		SashForm sashForm_3 = new SashForm(tabFolder_1, SWT.NONE);
		tbtmTngXe.setControl(sashForm_3);

		Oto_DanhsachXe = new Table(sashForm_3, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		Oto_DanhsachXe.setLinesVisible(true);
		Oto_DanhsachXe.setHeaderVisible(true);
		Oto_DanhsachXe.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TableItem[] items = table_Kyhan.getSelection();
					if (items.length > 0) {
						KY_HAN_THONGKE_XANG_DAU kh = (KY_HAN_THONGKE_XANG_DAU) items[0].getData();
						Date_Batdau = kh.getNGAY_BAT_DAU();
						Date_Ketthuc = kh.getNGAY_KET_THUC();
						if (Date_Batdau.before(Date_Ketthuc) || Date_Batdau.compareTo(Date_Ketthuc) == 0) {
							TableItem[] til = Oto_DanhsachXe.getSelection();
							if (til.length > 0) {
								for (TableItem tbi : til) {
									PHUONGTIEN_GIAOTHONG pt = (PHUONGTIEN_GIAOTHONG) tbi.getData();
									fillLichTrinhOto(pt, Date_Batdau, Date_Ketthuc);
								}
							}
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}

		});

		TableColumn tblclmnStt = new TableColumn(Oto_DanhsachXe, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTS = new TableColumn(Oto_DanhsachXe, SWT.NONE);
		tblclmnTS.setText("Ô TÔ SỬ DỤNG");
		tblclmnTS.setWidth(100);

		TableColumn tblclmnXngTnu = new TableColumn(Oto_DanhsachXe, SWT.NONE);
		tblclmnXngTnu.setWidth(100);
		tblclmnXngTnu.setText("TỒN ĐẦU KỲ");

		TableColumn tblclmnXngTnCui = new TableColumn(Oto_DanhsachXe, SWT.NONE);
		tblclmnXngTnCui.setWidth(100);
		tblclmnXngTnCui.setText("TỒN CUỐI KỲ");

		Menu menu_3 = new Menu(Oto_DanhsachXe);
		Oto_DanhsachXe.setMenu(menu_3);

		MenuItem mntmThngTinPhng = new MenuItem(menu_3, SWT.NONE);
		mntmThngTinPhng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] ti = Oto_DanhsachXe.getSelection();
					if (ti.length > 0) {
						PHUONGTIEN_GIAOTHONG pg = (PHUONGTIEN_GIAOTHONG) ti[0].getData();
						EditPhuongtienGiaothong epg = new EditPhuongtienGiaothong(getShell(), SWT.DIALOG_TRIM, user,
								controler.getControl_TAISAN().get_Taisan(pg));
						epg.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThngTinPhng.setText("Thông tin Phương tiện giao thông");

		SashForm sashForm_4 = new SashForm(sashForm_3, SWT.VERTICAL);

		table_Lichtrinh = new Table(sashForm_4, SWT.BORDER | SWT.FULL_SELECTION);
		table_Lichtrinh.setLinesVisible(true);
		table_Lichtrinh.setHeaderVisible(true);

		TableColumn tblclmnStt_3 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnStt_3.setWidth(45);
		tblclmnStt_3.setText("STT");

		TableColumn tblclmnLoiNhinLiu = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnLoiNhinLiu.setWidth(120);
		tblclmnLoiNhinLiu.setText("LOẠI NHIÊN LIỆU");

		TableColumn tblclmnPhiuXng = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnPhiuXng.setWidth(150);
		tblclmnPhiuXng.setText("PHIẾU NHIÊN LIỆU CẤP");

		TableColumn tableColumn_8 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("NGÀY ĐI");

		TableColumn tableColumn_10 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("NGÀY VỀ");

		TableColumn tableColumn_11 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tableColumn_11.setWidth(114);
		tableColumn_11.setText("NƠI XUẤT PHÁT");

		TableColumn tableColumn_12 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("ĐIỂM ĐẾN");

		TableColumn tableColumn_13 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tableColumn_13.setWidth(90);
		tableColumn_13.setText("KM ĐI");

		TableColumn tblclmnKmnd = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnKmnd.setWidth(92);
		tblclmnKmnd.setText("KM ĐẾN (DỰ KIẾN)");

		TableColumn tblclmnKmHotng_1 = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnKmHotng_1.setWidth(100);
		tblclmnKmHotng_1.setText("KM HOẠT ĐỘNG (THEO DỰ KIẾN TẠI LỆNH ĐIỀU XE)");

		TableColumn tblclmnNgiLi = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnNgiLi.setWidth(100);
		tblclmnNgiLi.setText("NGƯỜI LÁI");

		TableColumn tblclmnNgiToLnh = new TableColumn(table_Lichtrinh, SWT.NONE);
		tblclmnNgiToLnh.setWidth(100);
		tblclmnNgiToLnh.setText("NGƯỜI TẠO LỆNH");

		Menu menu_2 = new Menu(table_Lichtrinh);
		table_Lichtrinh.setMenu(menu_2);

		MenuItem menuItem_8 = new MenuItem(menu_2, SWT.NONE);
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] tableItem = table_Lichtrinh.getSelection();
					if (tableItem.length > 0) {
						LENH_DIEU_XE ldx = (LENH_DIEU_XE) tableItem[0].getData();
						QuanLy_Lenhdieuxe qlldx = new QuanLy_Lenhdieuxe(getShell(), SWT.DIALOG_TRIM, user, ldx, true);
						qlldx.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		menuItem_8.setText("Xem Lệnh điều xe");

		MenuItem menuItem_9 = new MenuItem(menu_2, SWT.NONE);
		menuItem_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] ti = table_Lichtrinh.getSelection();
					if (ti.length > 0) {
						LENH_DIEU_XE l = (LENH_DIEU_XE) ti[0].getData();
						if (l.getHUY_LENH() == 1) {
							controler.getControl_LENH_DIEU_XE().set_Huylenh(l, true);

						} else {
							controler.getControl_LENH_DIEU_XE().set_Huylenh(l, false);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		menuItem_9.setText("Hủy lệnh");

		MenuItem menuItem_10 = new MenuItem(menu_2, SWT.NONE);
		menuItem_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] tableItem = table_Lichtrinh.getSelection();
					if (tableItem.length > 0) {
						LENH_DIEU_XE ldx = (LENH_DIEU_XE) tableItem[0].getData();
						QuanLy_Lenhdieuxe qlldx = new QuanLy_Lenhdieuxe(getShell(), SWT.DIALOG_TRIM, user, ldx, false);
						qlldx.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		menuItem_10.setText("Sửa thông tin");

		MenuItem menuItem_11 = new MenuItem(menu_2, SWT.NONE);
		menuItem_11.setText("Xóa");

		Composite composite = new Composite(sashForm_4, SWT.NONE);
		composite.setLayout(new GridLayout(5, false));

		Label lblTngQungng = new Label(composite, SWT.NONE);
		lblTngQungng.setText("Tổng Quãng đường dự kiến:");

		text_TongQuangduongDukien = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_TongQuangduongDukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);

		Label lblTong = new Label(composite, SWT.NONE);
		lblTong.setText("Tổng Km hoạt động:");

		Text_TongKmhoatdong = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		Text_TongKmhoatdong.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		Label lblTngNhinLiu_1 = new Label(composite, SWT.NONE);
		lblTngNhinLiu_1.setText("Tổng nhiên liệu cấp (lít):");

		Text_Tongnhienlieucap = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		Text_Tongnhienlieucap.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		new Label(composite, SWT.NONE);

		btnTngNhinLiuMuangoai = new Label(composite, SWT.NONE);
		btnTngNhinLiuMuangoai.setText("Nhiên liệu mua ngoài (lít):");

		Text_TongNhienlieumuangoai = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		Text_TongNhienlieumuangoai.setText("0");
		Text_TongNhienlieumuangoai.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

		Label lblTngNhinLiu = new Label(composite, SWT.NONE);
		lblTngNhinLiu.setText("Tổng nhiên liệu trong lịch trình:");

		text_NhienLieuTronglichtrinh = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_NhienLieuTronglichtrinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);

		Label lblNhinLiuTn = new Label(composite, SWT.NONE);
		lblNhinLiuTn.setText("Nhiên liệu Tồn cuối kỳ:");

		text_ToncuoiKy = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_ToncuoiKy.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 16777296 || e.keyCode == 13) {
					double TonCuoiky = Double.valueOf(text_ToncuoiKy.getText());
					TableItem[] til = Oto_DanhsachXe.getSelection();
					ThongSoHoatdong tshd = (ThongSoHoatdong) til[0].getData("tshd");
					tshd.setTonCuoiky(TonCuoiky);
					til[0].setText(3, TonCuoiky + "");
					fillResult(tshd);
				}
			}
		});
		text_ToncuoiKy.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							double TonCuoiky = Double.valueOf(text_ToncuoiKy.getText());
							TableItem[] til = Oto_DanhsachXe.getSelection();
							ThongSoHoatdong tshd = (ThongSoHoatdong) til[0].getData("tshd");
							tshd.setTonCuoiky(TonCuoiky);
							til[0].setText(3, TonCuoiky + "");
						} catch (Exception e2) {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Lỗi");
							m.setMessage("Chỉ nhập số vào khu vực tính!");
						}
					}
				});
			}
		});
		text_ToncuoiKy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_ToncuoiKy.addVerifyListener(new VerifyListener() {
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
		lblNhinLiuTiu = new Label(composite, SWT.NONE);
		lblNhinLiuTiu.setText("Tổng tiêu thụ  tính theo Định mức (lít):");

		Text_Tongnhienlieutieuthu = new Text(composite, SWT.BORDER);
		Text_Tongnhienlieutieuthu.setEditable(false);
		Text_Tongnhienlieutieuthu.setText("0");
		Text_Tongnhienlieutieuthu.addVerifyListener(new VerifyListener() {
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
		Text_Tongnhienlieutieuthu.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		new Label(composite, SWT.NONE);

		Label lblTngTiuTh = new Label(composite, SWT.NONE);
		lblTngTiuTh.setText("Tổng tiêu thụ thực tế tính được (lít):");

		text_TieuthuThucte = new Text(composite, SWT.BORDER);
		text_TieuthuThucte.setText("0");
		text_TieuthuThucte.setEditable(false);
		text_TieuthuThucte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		text_Thongbao = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Thongbao.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		sashForm_4.setWeights(new int[] { 125, 135 });
		sashForm_3.setWeights(new int[] { 618, 1000 });

		TabItem tbtmTngHp_1 = new TabItem(tabFolder_1, SWT.NONE);
		tbtmTngHp_1.setText("Quyết toán Tổng hợp");

		table_Quyettoantonghop = new Table(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_Quyettoantonghop.setLinesVisible(true);
		table_Quyettoantonghop.setHeaderVisible(true);
		tbtmTngHp_1.setControl(table_Quyettoantonghop);

		TableColumn tableColumn_7 = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tableColumn_7.setWidth(50);
		tableColumn_7.setText("STT");

		TableColumn tblclmnLoiXe = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnLoiXe.setWidth(100);
		tblclmnLoiXe.setText("LOẠI XE");

		TableColumn tableColumn_9 = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("XE - BIỂN SỐ");

		TableColumn tblclmnSKmC = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnSKmC.setWidth(114);
		tblclmnSKmC.setText("SỐ KM CŨ");

		TableColumn tblclmnSKmMi = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnSKmMi.setWidth(100);
		tblclmnSKmMi.setText("SỐ KM MỚI");

		TableColumn tblclmnKmHotng = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnKmHotng.setWidth(124);
		tblclmnKmHotng.setText("KM HOẠT ĐỘNG");

		Menu menu = new Menu(table_Quyettoantonghop);
		table_Quyettoantonghop.setMenu(menu);

		MenuItem menuItem_4 = new MenuItem(menu, SWT.NONE);
		menuItem_4.setText("Xem Lệnh điều xe");

		MenuItem menuItem_5 = new MenuItem(menu, SWT.NONE);
		menuItem_5.setText("Hủy lệnh");

		MenuItem menuItem_6 = new MenuItem(menu, SWT.NONE);
		menuItem_6.setText("Sửa thông tin");

		MenuItem menuItem_7 = new MenuItem(menu, SWT.NONE);
		menuItem_7.setText("Xóa");

		TableColumn tblclmnnhMcXng = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnnhMcXng.setWidth(120);
		tblclmnnhMcXng.setText("ĐỊNH MỨC XĂNG");

		TableColumn tblclmnTnuThng = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnTnuThng.setWidth(110);
		tblclmnTnuThng.setText("TỒN ĐẦU KỲ");

		TableColumn tblclmnMuaTrongThng = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnMuaTrongThng.setWidth(110);
		tblclmnMuaTrongThng.setText("MUA TRONG KỲ");

		TableColumn tblclmnSXngDu = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnSXngDu.setWidth(140);
		tblclmnSXngDu.setText("XĂNG DẦU SỬ DỤNG");

		TableColumn tblclmnTnCuithangs = new TableColumn(table_Quyettoantonghop, SWT.NONE);
		tblclmnTnCuithangs.setWidth(100);
		tblclmnTnCuithangs.setText("TỒN CUỐI KỲ");

		TabItem tbtmXeMy = new TabItem(tabFolder, SWT.NONE);
		tbtmXeMy.setText("Lịch trình Xe máy");

		TabFolder tabFolder_2 = new TabFolder(tabFolder, SWT.NONE);
		tbtmXeMy.setControl(tabFolder_2);

		TabItem tbtmTngHp_2 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmTngHp_2.setText("Quyết toán tổng hợp");

		table_1 = new Table(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		tbtmTngHp_2.setControl(table_1);

		Menu menu_1 = new Menu(table_1);
		table_1.setMenu(menu_1);

		MenuItem menuItem = new MenuItem(menu_1, SWT.NONE);
		menuItem.setText("Xem Lệnh điều xe");

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("Hủy lệnh");

		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.setText("Sửa thông tin");

		MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
		menuItem_3.setText("Xóa");

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(130);
		tableColumn_1.setText("NGÀY ĐIỀU XE");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("XE - BIỂN SỐ");

		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(114);
		tableColumn_3.setText("NGÀY KHỞI HÀNH");

		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("CÁN BỘ LÁI");

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(124);
		tableColumn_5.setText("SỐ KM ĐÃ ĐI");

		TabItem tbtmTngXe_1 = new TabItem(tabFolder_2, SWT.NONE);
		tbtmTngXe_1.setText("Lịch trình từng xe");

		TabItem tbtmTngHp = new TabItem(tabFolder, SWT.NONE);
		tbtmTngHp.setText("Tổng hợp tình hình sử dụng xăng dầu");

		table_2 = new Table(tabFolder, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmTngHp.setControl(table_2);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TableColumn tblclmnNhinLiu = new TableColumn(table_2, SWT.NONE);
		tblclmnNhinLiu.setWidth(100);
		tblclmnNhinLiu.setText("Nhiên liệu");

		TableColumn tblclmnTngSTiu = new TableColumn(table_2, SWT.NONE);
		tblclmnTngSTiu.setWidth(100);
		tblclmnTngSTiu.setText("Tổng số tiêu thụ");

		TableColumn tblclmnMuaNgoi = new TableColumn(table_2, SWT.NONE);
		tblclmnMuaNgoi.setWidth(100);
		tblclmnMuaNgoi.setText("Mua ngoài");

		TableColumn tblclmnKho = new TableColumn(table_2, SWT.NONE);
		tblclmnKho.setWidth(100);
		tblclmnKho.setText("Kho");

		TableColumn tblclmnTinMuaNgoi = new TableColumn(table_2, SWT.NONE);
		tblclmnTinMuaNgoi.setWidth(120);
		tblclmnTinMuaNgoi.setText("Kinh phí Mua ngoài");

		TableColumn tblclmnKinhPhKho = new TableColumn(table_2, SWT.NONE);
		tblclmnKinhPhKho.setWidth(100);
		tblclmnKinhPhKho.setText("Kinh phí Kho");

		TableColumn tblclmnTngKinhPh = new TableColumn(table_2, SWT.NONE);
		tblclmnTngKinhPh.setWidth(100);
		tblclmnTngKinhPh.setText("Tổng kinh phí");

		TableColumn tblclmnGhiCh = new TableColumn(table_2, SWT.NONE);
		tblclmnGhiCh.setWidth(100);
		tblclmnGhiCh.setText("Ghi chú");

		TableItem tableItem = new TableItem(table_2, SWT.NONE);
		tableItem.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		tableItem.setText(new String[] { "Xăng-dầu Ô tô" });
		tableItem.setText("Xăng - dầu Ô tô");

		TableItem tableItem_1 = new TableItem(table_2, 0);
		tableItem_1.setText(new String[] { "Xăng-dầu Ô tô" });
		tableItem_1.setText("Xăng xe máy");
		tableItem_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));

		TableItem tableItem_2 = new TableItem(table_2, 0);
		tableItem_2.setText(new String[] { "Xăng-dầu Ô tô" });
		tableItem_2.setText("Tổng cộng");
		tableItem_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		sashForm.setWeights(new int[] { 418, 1000 });
		new Label(this, SWT.NONE);

		Button btnXutBoCo = new Button(this, SWT.NONE);
		btnXutBoCo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnXutBoCo.setText("Xuất báo cáo Excel");

		Button btnQuytTonTng = new Button(this, SWT.NONE);
		btnQuytTonTng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] til = Oto_DanhsachXe.getItems();
					table_Quyettoantonghop.removeAll();
					if (til.length > 0) {
						ArrayList<ThongSoHoatdong> tshd = new ArrayList<>();
						for (TableItem ti : til) {
							if (ti.getChecked())
								tshd.add((ThongSoHoatdong) ti.getData("tshd"));
						}
						fill_Quyettoan_Tonghop(tshd);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void fill_Quyettoan_Tonghop(ArrayList<ThongSoHoatdong> tshd_list) throws SQLException {
				int i = 1;
				for (ThongSoHoatdong tshd : tshd_list) {
					addRow_Quyettoan_Tonghop(i, tshd, Date_Batdau, Date_Ketthuc);
					i++;
				}
			}

			private void addRow_Quyettoan_Tonghop(int i, ThongSoHoatdong tshd, Date date_Batdau, Date date_Ketthuc)
					throws SQLException {
				TableItem ti = new TableItem(table_Quyettoantonghop, SWT.NONE);
				PHUONGTIEN_GIAOTHONG pg = tshd.getPHUONGTIEN_GIAOTHONG();
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(pg.getMA_LOAI_XE());
				ti.setText(new String[] { i + "", lx.getTEN_DONG_XE(), tshd.getPHUONGTIEN_GIAOTHONG().getBIENSO(),
						tshd.getKmCu() + "", tshd.getKmMoi() + "", tshd.getTongKmHoatdong() + "",
						lx.getDINH_MUC_XANG_DAU() + " "
								+ (new Fill_ItemData().get_String_LOAI_NHIENLIEU(pg.getXANG_DAU())),
						tshd.getTonDauky() + "", tshd.getTongXang_DauMuangoai() + "", tshd.getTongXang_DauSudung() + "",
						tshd.getTonCuoiky() + "" });
			}
		});
		btnQuytTonTng.setText("Quyết toán tổng hợp");
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

	protected ArrayList<PHONGBAN> get_PHONGBAN_Selected() {
		ArrayList<PHONGBAN> result = new ArrayList<>();
		TreeItem[] items = tree.getItems()[0].getItems();
		if (items != null)
			for (TreeItem ti : items) {
				Object o = ti.getData();
				if (o instanceof PHONGBAN) {
					if (ti.getChecked()) {
						PHONGBAN p = (PHONGBAN) o;
						result.add(p);
					}

				}
			}
		return result;
	}

	private void init() throws SQLException {
		table_Kyhan.removeAll();
		ArrayList<KY_HAN_THONGKE_XANG_DAU> khl = controler.getControl_KY_HAN_THONGKE_XANG_DAU().getAllData();
		int i = 1;
		for (KY_HAN_THONGKE_XANG_DAU k : khl) {
			TableItem t = new TableItem(table_Kyhan, SWT.NONE);
			t.setText(new String[] { i + "", k.getMA_KYHAN() + "", k.getTEN_KYHAN(),
					mdf.getViewStringDate(k.getNGAY_BAT_DAU()), mdf.getViewStringDate(k.getNGAY_KET_THUC()),
					k.getGHI_CHU() });
			t.setData(k);
			i++;
		}
	}

	private void fillLichTrinhOto(PHUONGTIEN_GIAOTHONG pt, Date Date_Batdau, Date Date_Ketthuc) throws SQLException {
		table_Lichtrinh.removeAll();
		ArrayList<LENH_DIEU_XE> ldxl = controler.getControl_LENH_DIEU_XE().get_List_LENH_DIEU_XE(pt, Date_Batdau,
				Date_Ketthuc);
		int i = 1;
		if (ldxl != null) {
			for (LENH_DIEU_XE l : ldxl) {
				TableItem t = new TableItem(table_Lichtrinh, SWT.NONE);
				NGUOIDUNG canbolai = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(l.getTEN_TAI_KHOAN());
				NGUOIDUNG Nguoitaolenh = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(l.getNGUOI_TAO_LENH());
				String NGAYKHOIHANH = mdf.getViewStringDate(l.getNGAY_DI());
				String NGAYVE = mdf.getViewStringDate(l.getNGAY_VE());
				int LoaiNhienlieu = controler.getControl_PHUONGTIEN_GIAOTHONG()
						.get_LoaiNhienlieu(pt.getMA_PHUONGTIEN_GIAOTHONG());
				Fill_ItemData f = new Fill_ItemData();

				t.setText(new String[] { "" + i, f.get_String_LOAI_NHIENLIEU(LoaiNhienlieu),
						l.getPHIEU_NHIENLIEU_DUOCCAP() + "", NGAYKHOIHANH, NGAYVE, l.getDIEM_XUATPHAT(),
						l.getDIEM_DEN(), l.getSO_KM_HIENTAI() + "",
						(l.getQUANG_DUONG_DUKIEN() + l.getSO_KM_HIENTAI()) + "", l.getQUANG_DUONG_DUKIEN() + "",
						canbolai.getTEN_CAN_BO(), Nguoitaolenh.getTEN_CAN_BO() });
				t.setData(l);
				i++;
			}
		}
		FillKetqua_Lythuyet(pt, ldxl);
	}

	private void FillKetqua_Lythuyet(PHUONGTIEN_GIAOTHONG pg, ArrayList<LENH_DIEU_XE> ldxl) throws SQLException {
		ThongSoHoatdong thd = getThongsoLythuyet(pg, ldxl);
		fillResult(thd);
	}

	private ThongSoHoatdong getThongsoLythuyet(PHUONGTIEN_GIAOTHONG pg, ArrayList<LENH_DIEU_XE> ldxl)
			throws SQLException {

		LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(pg.getMA_LOAI_XE());
		ThongSoHoatdong result = new ThongSoHoatdong(pg);
		LENH_DIEU_XE first = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_DAUKY(pg, Date_Batdau);
		LENH_DIEU_XE last = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_CUOIKY(pg, Date_Ketthuc);

		LENH_DIEU_XE after_last = controler.getControl_LENH_DIEU_XE().get_LENH_DIEU_XE_AFTER(last);

		result.setTonDauky(first.getTON_NHIENLIEU_HIENTAI());
		int tmpQuangDuongdichuyen_last = last.getQUANG_DUONG_DUKIEN();
		if (after_last == null) {
			double NhienlieuTon_Last = (last.getTON_NHIENLIEU_HIENTAI() + last.getPHIEU_NHIENLIEU_DUOCCAP()
					+ last.getPHIEU_NHIENLIEU_MUANGOAI()
					- (tmpQuangDuongdichuyen_last * lx.getDINH_MUC_XANG_DAU() / 100));
			result.setTonCuoiky(NhienlieuTon_Last);
			text_ToncuoiKy.setEditable(true);
		} else {
			text_ToncuoiKy.setEditable(false);
			result.setTonCuoiky(after_last.getTON_NHIENLIEU_HIENTAI());
		}
		result.setKmCu(first.getSO_KM_HIENTAI());
		result.setKmMoi(last.getSO_KM_HIENTAI() + tmpQuangDuongdichuyen_last);
		result.setTongKmHoatdong(result.getKmMoi() - result.getKmCu());

		if (ldxl != null) {
			result.setTongXang_DauCap(result.getTonDauky());
			for (LENH_DIEU_XE l : ldxl) {
				result.setTongKmDukien(result.getTongKmDukien() + l.getQUANG_DUONG_DUKIEN());
				result.setTongXang_DauCap(result.getTongXang_DauCap() + l.getPHIEU_NHIENLIEU_DUOCCAP());
				result.setTongXang_DauMuangoai(result.getTongXang_DauMuangoai() + l.getPHIEU_NHIENLIEU_MUANGOAI());
			}
			/* Tong xang dau su dung ly thuyet: */
			result.setTongXang_DauSudung_TheoDinhmuc((result.getTongKmHoatdong() * lx.getDINH_MUC_XANG_DAU() / 100));
			result.setTongXang_TrongKyhan(result.getTongXang_DauCap() + result.getTongXang_DauMuangoai());
		}
		return result;
	}

	public void Phuongtrinh_Nhienlieutieuthu(double Tongtieuthu_Lythuyet, double TonDauky, double TonCuoiky,
			double Tongcap, double Tongmua, int Dungsai) {
		if (TonCuoiky > 0) {
			// Tongcap = Phieu cap + ton dau ky
			double Tongtieuthu_Thucte = Tongcap + Tongmua - TonCuoiky;
			if (Tongtieuthu_Thucte < Tongtieuthu_Lythuyet - Dungsai) {
				text_Thongbao.setText("Dữ liệu không khớp. \nTổng Nhiên liệu tiêu thụ thực tế thấp hơn lý thuyết: "
						+ (Tongtieuthu_Lythuyet - Tongtieuthu_Thucte)
						+ " lít,\n(Tồn nhiên liệu thấp, hoặc mua ngoài thấp). Kiểm tra lại Lệnh điều xe");
			} else if (Tongtieuthu_Thucte > Tongtieuthu_Lythuyet + Dungsai) {
				text_Thongbao.setText("Dữ liệu không khớp. \nTổng Nhiên liệu thực tế cao hơn lý thuyết: "
						+ (Tongtieuthu_Thucte - Tongtieuthu_Lythuyet)
						+ " lít, \n(Tồn đầu kỳ cao - Hoặc mua ngoài cao). Kiểm tra lại Lệnh điều xe");
			} else {
				text_Thongbao.setText("Dữ liệu khớp, sai số: " + Dungsai + "(lít)");
			}
		} else {
			text_Thongbao.setText("Nhiên liệu ghi nhận cho chuyến đi cuối cùng không đủ, \nthiếu tối thiểu: "
					+ TonCuoiky * -1
					+ " (lít nhiên liệu cho hành trình), không thể tính tồn cuối kỳ. kiểm tra lại lệnh điều xe");
		}
		return;
	}

	private void fillResult(ThongSoHoatdong thd) {
		text_TongQuangduongDukien.setText(thd.getTongKmDukien() + "");
		Text_TongKmhoatdong.setText(thd.getTongKmHoatdong() + "");
		Text_Tongnhienlieucap.setText(thd.getTongXang_DauCap() + "");
		text_NhienLieuTronglichtrinh.setText(thd.getTongXang_TrongKyhan() + "");
		Text_Tongnhienlieutieuthu.setText(thd.getTongXang_DauSudung() + "");
		Text_TongNhienlieumuangoai.setText(thd.getTongXang_DauMuangoai() + "");
		text_ToncuoiKy.setText(thd.TonCuoiky + "");
		text_TieuthuThucte.setText((thd.TongXang_DauCap + thd.TongXang_DauMuangoai - thd.TonCuoiky) + "");
		Phuongtrinh_Nhienlieutieuthu(thd.getTongXang_DauSudung(), thd.getTonDauky(), thd.getTonCuoiky(),
				thd.getTongXang_DauCap(), thd.getTongXang_DauMuangoai(), Dungsai);
		TableItem[] tbil = Oto_DanhsachXe.getItems();
		if (tbil.length > 0) {
			for (TableItem tbi : tbil) {
				PHUONGTIEN_GIAOTHONG pgx = (PHUONGTIEN_GIAOTHONG) tbi.getData();
				if (pgx.getMA_PHUONGTIEN_GIAOTHONG() == thd.getPHUONGTIEN_GIAOTHONG().getMA_PHUONGTIEN_GIAOTHONG()) {
					tbil[0].setData("tshd", thd);
				}
			}
		}
	}

	private void fillDanhsachOtoSudung(ArrayList<PHONGBAN> dvl, Date Date_Batdau, Date Date_Ketthuc)
			throws SQLException {
		Oto_DanhsachXe.removeAll();
		table_Lichtrinh.removeAll();
		ArrayList<PHUONGTIEN_GIAOTHONG> ptgt = new ArrayList<>();
		for (PHONGBAN dv : dvl) {
			ptgt.addAll(controler.getControl_LENH_DIEU_XE().get_Oto_Dasudung(dv, Date_Batdau, Date_Ketthuc));
		}
		int i = 1;
		if (ptgt != null)
			for (PHUONGTIEN_GIAOTHONG l : ptgt) {
				LENH_DIEU_XE first = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_DAUKY(l, Date_Batdau);
				LENH_DIEU_XE last = controler.getControl_LENH_DIEU_XE().get_LENHDIEUXE_CUOIKY(l, Date_Ketthuc);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(l.getMA_LOAI_XE());

				LENH_DIEU_XE after_last = controler.getControl_LENH_DIEU_XE().get_LENH_DIEU_XE_AFTER(last);
				double NhienlieuTon_Last = 0;
				if (after_last == null) {
					int tmpQuangDuongdichuyen_last = last.getQUANG_DUONG_DUKIEN();
					NhienlieuTon_Last = (last.getTON_NHIENLIEU_HIENTAI() + +last.getPHIEU_NHIENLIEU_DUOCCAP()
							+ last.getPHIEU_NHIENLIEU_MUANGOAI()
							- (tmpQuangDuongdichuyen_last * lx.getDINH_MUC_XANG_DAU() / 100));
				} else {
					NhienlieuTon_Last = after_last.getTON_NHIENLIEU_HIENTAI();
				}

				TableItem t = new TableItem(Oto_DanhsachXe, SWT.NONE);
				t.setText(new String[] { "" + i, String.valueOf(l.getBIENSO()), first.getTON_NHIENLIEU_HIENTAI() + "",
						NhienlieuTon_Last >= 0 ? (NhienlieuTon_Last + "") : "Không xác định" + "" });
				t.setData(l);
				i++;
			}
	}

	/**
	 * Create contents of the shell.
	 */

	protected void createContents() {
		setText("Thống kê Chung Tình hình hoạt động của Phương tiện giao thông vận tải");
		setSize(890, 550);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	class ThongSoHoatdong {
		private int KmCu = 0;
		private int KmMoi = 0;
		private int TongKmDukien = 0;
		private int TongKmHoatdong = 0;
		private double TongXang_DauCap = 0;
		private double TongXang_TrongKyhan = 0;
		private double TongXang_DauMuangoai = 0;
		private double TongXang_DauSudung = 0;
		private double TonDauky = 0;
		private double TonCuoiky = 0;
		private PHUONGTIEN_GIAOTHONG pg;

		public ThongSoHoatdong(PHUONGTIEN_GIAOTHONG pg) {
			this.pg = pg;
		}

		public final PHUONGTIEN_GIAOTHONG getPHUONGTIEN_GIAOTHONG() {
			return pg;
		}

		public final void setTongXang_DauSudung(double tongXang_DauSudung) {
			TongXang_DauSudung = tongXang_DauSudung;
		}

		public final double getTongXang_TrongKyhan() {
			return TongXang_TrongKyhan;
		}

		public final void setTongXang_TrongKyhan(double tongXang_TrongKyhan) {
			TongXang_TrongKyhan = tongXang_TrongKyhan;
		}

		public final int getTongKmDukien() {
			return TongKmDukien;
		}

		public final void setTongKmDukien(int tongKmDukien) {
			TongKmDukien = tongKmDukien;
		}

		public final int getKmCu() {
			return KmCu;
		}

		public final void setKmCu(int kmCu) {
			KmCu = kmCu;
		}

		public final int getKmMoi() {
			return KmMoi;
		}

		public final void setKmMoi(int kmMoi) {
			KmMoi = kmMoi;
		}

		public final int getTongKmHoatdong() {
			return TongKmHoatdong;
		}

		public final void setTongKmHoatdong(int tongKmHoatdong) {
			TongKmHoatdong = tongKmHoatdong;
		}

		public final double getTongXang_DauCap() {
			return TongXang_DauCap;
		}

		public final void setTongXang_DauCap(double tongXang_DauCap) {
			TongXang_DauCap = tongXang_DauCap;
		}

		public final double getTongXang_DauMuangoai() {
			return TongXang_DauMuangoai;
		}

		public final void setTongXang_DauMuangoai(double tongXang_DauMuangoai) {
			TongXang_DauMuangoai = tongXang_DauMuangoai;
		}

		public final double getTongXang_DauSudung() {
			return TongXang_DauSudung;
		}

		public final void setTongXang_DauSudung_TheoDinhmuc(double tongXang_DauSudung) {
			TongXang_DauSudung = tongXang_DauSudung;
		}

		public final double getTonDauky() {
			return TonDauky;
		}

		public final void setTonDauky(double tonDauky) {
			TonDauky = tonDauky;
		}

		public final double getTonCuoiky() {
			return TonCuoiky;
		}

		public final void setTonCuoiky(double tonCuoiky) {
			TonCuoiky = tonCuoiky;
		}

	}
}
