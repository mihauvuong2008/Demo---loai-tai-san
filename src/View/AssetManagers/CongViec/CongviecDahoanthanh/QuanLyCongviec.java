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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class QuanLyCongviec extends Shell {

	private static NGUOIDUNG user;
	private final Controler controler;
	private final int treeHeight = 21;
	private Tree tree_DanhsachCongviec;
	private Tree tree_Hoso;
	private Tree tree_Nguoithuchien;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(QuanLyCongviec.class);

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
		setImage(SWTResourceManager.getImage(QuanLyCongviec.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(6, false));
		QuanLyCongviec.user = user;
		controler = new Controler(user);
		TreeRowStyle ts = new TreeRowStyle();

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));

		ToolItem tltmXemCongViec = new ToolItem(toolBar, SWT.NONE);
		tltmXemCongViec.setImage(
				SWTResourceManager.getImage(QuanLyCongviec.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		tltmXemCongViec.setText("Xem công việc");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.setImage(
				SWTResourceManager.getImage(QuanLyCongviec.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		tltmXa.setText("Xóa");

		ToolItem tltmXemHS = new ToolItem(toolBar, SWT.NONE);
		tltmXemHS.setImage(
				SWTResourceManager.getImage(QuanLyCongviec.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		tltmXemHS.setText("Quản lý Hồ sơ");
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
		tree_DanhsachCongviec = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tree_DanhsachCongviec.setLinesVisible(true);
		tree_DanhsachCongviec.setHeaderVisible(true);
		tree_DanhsachCongviec.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TreeItem[] til = tree_DanhsachCongviec.getSelection();
					if (til.length > 0) {
						fillNguoidung_Thuchien(til[0]);
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

		MenuItem mntmThmTpH = new MenuItem(menu, SWT.NONE);
		mntmThmTpH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String TEN_TAI_KHOAN = user.getTEN_CAN_BO();
				TreeItem[] til = tree_DanhsachCongviec.getSelection();
				try {
					if (til.length > 0) {
						Object o = til[0].getData();
						if (o instanceof DE_XUAT) {
							DE_XUAT dx = ((DE_XUAT) o);
							String Nguoidung = dx.getTEN_TAI_KHOAN();
							if (Nguoidung.equals(TEN_TAI_KHOAN)) {
								TAP_HO_SO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO()));
								if (ths == null) {
									TAP_HO_SO thsx = new TAP_HO_SO();
									thsx.setTEN_TAPHOSO("Tập hồ sơ bổ sung Công việc");
									thsx.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung Đề xuất");
									int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(thsx);
									if (Ma_NewTapHoso > 0) {
										controler.getControl_DEXUAT().update_TapHoso(dx, Ma_NewTapHoso);
										dx.setMA_TAPHOSO(Ma_NewTapHoso);
									}
									ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
								}
								TAPHOSO_View thsV = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
								thsV.open();
							}
						} else if (o instanceof GIAI_DOAN_THUC_HIEN) {
							GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) o;
							ArrayList<NGUOIDUNG> ndl = controler.getControl_THUCHIEN_CANBO()
									.get_NGUOIDUNG_Thamgia_Phanviec(gdth);
							if (ndl != null) {
								boolean flg = false;
								for (NGUOIDUNG nd : ndl) {
									if (nd.getTEN_TAI_KHOAN().equals(TEN_TAI_KHOAN))
										flg = true;
								}
								if (flg) {
									NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
											.getNGUOIDUNG_THUCHIEN(TEN_TAI_KHOAN, gdth);
									TAP_HO_SO ths = (controler.getControl_TAPHOSO()
											.get_TAP_HO_SO(ndth.getMA_TAPHOSO()));
									if (ths == null) {
										TAP_HO_SO thsx = new TAP_HO_SO();
										thsx.setTEN_TAPHOSO("Tập hồ sơ bổ sung Công việc");
										thsx.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung Công việc - Thực hiện");
										int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(thsx);
										if (Ma_NewTapHoso > 0) {
											thsx.setMA_TAPHOSO(Ma_NewTapHoso);
											controler.getControl_THUCHIEN_CANBO().update_TAPHOSO(ndth, thsx);
										}
										ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
									}
									TAPHOSO_View thsV = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
									thsV.open();
								}
							}

						} else if (o instanceof GIAI_DOAN_NGHIEM_THU) {
							GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) o;
							ArrayList<NGUOIDUNG> ndl = controler.getControl_NGHIEMTHU_CANBO()
									.get_NGUOIDUNG_Thamgia_Phanviec(gdth);
							if (ndl != null) {
								boolean flg = false;
								for (NGUOIDUNG nd : ndl) {
									if (nd.getTEN_TAI_KHOAN().equals(TEN_TAI_KHOAN))
										flg = true;
								}
								if (flg) {
									NGUOIDUNG_NGHIEMTHU ndth = controler.getControl_NGHIEMTHU_CANBO()
											.getNGUOIDUNG_NGHIEMTHU(TEN_TAI_KHOAN, gdth);
									TAP_HO_SO ths = (controler.getControl_TAPHOSO()
											.get_TAP_HO_SO(ndth.getMA_TAPHOSO()));
									if (ths == null) {
										TAP_HO_SO thsx = new TAP_HO_SO();
										thsx.setTEN_TAPHOSO("Tập hồ sơ bổ sung Công việc");
										thsx.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung Công việc - Nghiệm thu");
										int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(thsx);
										if (Ma_NewTapHoso > 0) {
											thsx.setMA_TAPHOSO(Ma_NewTapHoso);
											controler.getControl_NGHIEMTHU_CANBO().update_TAPHOSO(ndth, thsx);
										}
										ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
									}
									TAPHOSO_View thsV = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
									thsV.open();
								}
							}
						} else if (o instanceof GIAI_DOAN_QUYET_TOAN) {
							GIAI_DOAN_QUYET_TOAN gdth = (GIAI_DOAN_QUYET_TOAN) o;
							ArrayList<NGUOIDUNG> ndl = controler.getControl_QUYETTOAN_CANBO()
									.get_NGUOIDUNG_Thamgia_Phanviec(gdth);
							if (ndl != null) {
								boolean flg = false;
								for (NGUOIDUNG nd : ndl) {
									if (nd.getTEN_TAI_KHOAN().equals(TEN_TAI_KHOAN))
										flg = true;
								}
								if (flg) {
									NGUOIDUNG_QUYETTOAN ndth = controler.getControl_QUYETTOAN_CANBO()
											.getNGUOIDUNG_QUYETTOAN(TEN_TAI_KHOAN, gdth);
									TAP_HO_SO ths = (controler.getControl_TAPHOSO()
											.get_TAP_HO_SO(ndth.getMA_TAPHOSO()));
									if (ths == null) {
										TAP_HO_SO thsx = new TAP_HO_SO();
										thsx.setTEN_TAPHOSO("Tập hồ sơ bổ sung Công việc");
										thsx.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung Công việc - Quyết toán");
										int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(thsx);
										if (Ma_NewTapHoso > 0) {
											thsx.setMA_TAPHOSO(Ma_NewTapHoso);
											controler.getControl_QUYETTOAN_CANBO().update_TAPHOSO(ndth, thsx);
										}
										ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
									}
									TAPHOSO_View thsV = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
									thsV.open();
								}
							}

						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThmTpH.setText("Thêm tập hồ sơ");

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
					fillDanhsachCongviec();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmXa.setText("Xóa");

		SashForm sashForm_4 = new SashForm(sashForm, SWT.VERTICAL);

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
					TAPHOSO_View ths_V = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
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

		tree_Nguoithuchien = new Tree(sashForm_4, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Nguoithuchien.setLinesVisible(true);
		tree_Nguoithuchien.setHeaderVisible(true);

		TreeColumn trclmnStt = new TreeColumn(tree_Nguoithuchien, SWT.NONE);
		trclmnStt.setWidth(45);
		trclmnStt.setText("STT");

		TreeColumn trclmnNgiThcHin = new TreeColumn(tree_Nguoithuchien, SWT.NONE);
		trclmnNgiThcHin.setWidth(151);
		trclmnNgiThcHin.setText("NGƯỜI THỰC HIỆN");

		TreeColumn trclmnTnTiKhon_2 = new TreeColumn(tree_Nguoithuchien, SWT.NONE);
		trclmnTnTiKhon_2.setWidth(100);
		trclmnTnTiKhon_2.setText("TÊN TÀI KHOẢN");

		TreeColumn trclmnPhnVic = new TreeColumn(tree_Nguoithuchien, SWT.NONE);
		trclmnPhnVic.setWidth(100);
		trclmnPhnVic.setText("Phần việc");
		sashForm_4.setWeights(new int[] { 1000, 618 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Label lblTNgy = new Label(this, SWT.NONE);
		lblTNgy.setText("Từ ngày: ");

		dateTime_Begin = new DateTime(this, SWT.BORDER);

		Label lblnNgy = new Label(this, SWT.NONE);
		lblnNgy.setText("Đến ngày: ");

		dateTime_End = new DateTime(this, SWT.BORDER);

		Button btnXem = new Button(this, SWT.NONE);
		btnXem.setImage(
				SWTResourceManager.getImage(QuanLyCongviec.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					fillDanhsachCongviec();
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

	private void init() throws SQLException {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mdf.addDate(thisDay, -365));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);
		fillDanhsachCongviec();
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
	 * Get item [Congviec] or [Phanviec] and call method build list
	 * [Nguoithuchien]
	 * 
	 * @author NgocDong
	 * @throws SQLException
	 */
	protected void fillNguoidung_Thuchien(TreeItem ti) throws SQLException {
		tree_Nguoithuchien.removeAll();
		Object o = ti.getData();
		Integer i = new Integer(1);
		if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG || o instanceof DOT_THUCHIEN_TANG_TAISAN
				|| o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			TreeItem[] child = ti.getItems();
			i = fillCanbo_Thamgia_Phanviec(child[0], i);
			i = fillCanbo_Thamgia_Phanviec(child[1], i);
			if (child.length > 2) {
				i = fillCanbo_Thamgia_Phanviec(child[2], i);
				i = fillCanbo_Thamgia_Phanviec(child[3], i);
			}
		} else {
			i = fillCanbo_Thamgia_Phanviec(ti, i);
		}
	}

	/**
	 * Get information [Nguoi thuc hien cac phan viec] from [Congviec] or
	 * [Phanviec] and build Item in tree [Nguoithuchien]
	 * 
	 * @author NgocDong
	 * @throws SQLException
	 */
	int fillCanbo_Thamgia_Phanviec(TreeItem ti, int i) throws SQLException {
		Object o = ti.getData();
		if (o instanceof DE_XUAT) {
			NGUOIDUNG nd_Dexuat = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(((DE_XUAT) o).getTEN_TAI_KHOAN());
			if (nd_Dexuat != null) {
				TreeItem titem = new TreeItem(tree_Nguoithuchien, SWT.NONE);
				titem.setText(new String[] { i + "", nd_Dexuat.getTEN_CAN_BO(), nd_Dexuat.getTEN_TAI_KHOAN(),
						"Đề xuất, Xin Phê duyệt chủ trương" });
				titem.setData(nd_Dexuat);
				i++;
			}
		} else if (o instanceof GIAI_DOAN_THUC_HIEN) {
			GIAI_DOAN_THUC_HIEN th = (GIAI_DOAN_THUC_HIEN) o;
			ArrayList<NGUOIDUNG> ndl_th = controler.getControl_THUCHIEN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(th);
			if (ndl_th != null)
				for (NGUOIDUNG nd : ndl_th) {
					TreeItem titem = new TreeItem(tree_Nguoithuchien, SWT.NONE);
					titem.setText(
							new String[] { i + "", nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(), "Tổ chức thực hiện" });
					titem.setData(nd);
					i++;
				}
		} else if (o instanceof GIAI_DOAN_NGHIEM_THU) {
			GIAI_DOAN_NGHIEM_THU nt = (GIAI_DOAN_NGHIEM_THU) o;
			ArrayList<NGUOIDUNG> ndl_nt = controler.getControl_NGHIEMTHU_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(nt);
			if (ndl_nt != null)
				for (NGUOIDUNG nd : ndl_nt) {
					TreeItem titem = new TreeItem(tree_Nguoithuchien, SWT.NONE);
					titem.setText(new String[] { i + "", nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
							"Kiểm tra, Nghiệm thu, Bàn giao" });
					titem.setData(nd);
					i++;
				}
		} else if (o instanceof GIAI_DOAN_QUYET_TOAN) {
			GIAI_DOAN_QUYET_TOAN qt = (GIAI_DOAN_QUYET_TOAN) o;
			ArrayList<NGUOIDUNG> ndl_qt = controler.getControl_QUYETTOAN_CANBO().get_NGUOIDUNG_Thamgia_Phanviec(qt);
			if (ndl_qt != null)
				for (NGUOIDUNG nd : ndl_qt) {
					TreeItem titem = new TreeItem(tree_Nguoithuchien, SWT.NONE);
					titem.setText(new String[] { i + "", nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
							"Quyết toán thanh lý hợp đồng" });
					titem.setData(nd);
					i++;
				}
		}
		return i;
	}

	/**
	 * Get All data [Cong viec Da Hoan thanh] and build item Congviec
	 * 
	 * @author NgocDong
	 * @throws SQLException
	 */
	private void fillDanhsachCongviec() throws SQLException {
		tree_DanhsachCongviec.removeAll();
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End));
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_DOT_THUCHIEN_TANG_TAISAN_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End));
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
				.get_DOT_THUCHIEN_GIAM_TAISAN_list(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End));
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
