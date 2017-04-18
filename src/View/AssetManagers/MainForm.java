package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.codec.language.bm.Rule.Phoneme;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.LENH_DIEU_XE;
import DAO.LICH_CONG_TAC;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import View.AboutUs.AboutUs;
import View.AssetManagers.AppMessage.DefaultBoxMessage;
import View.AssetManagers.CongViec.TangTaiSan.XemDotTangtaisan;
import View.AssetManagers.Hoso.TapHoso_View;
import View.AssetManagers.ThongBao.Library.Thongbao_Lib_Hoso;
import View.AssetManagers.Wait.Wait;
import View.AssetManagers.excel_XuatDulieu.SelectColumn;
import View.Box.ErorrBox;
import View.Box.Exit_Box;
import View.DateTime.MyDateFormat;
import View.Filter.Sortter;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;
import login.login;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

@SuppressWarnings("unused")
public class MainForm {
	protected Shell shell;
	private Text text;
	private Text text_TenTaiSan;
	private Text text_NguonGocTaiSan;
	private Text text_LyDotang;
	private Text text_DonViSuDung;
	private Text text_DonViQuanLy;
	private Text text_MaTaiSan;
	private Text text_Model;
	private Text text_Serial;
	private Text text_NgaySudung;
	private Text text_NguyenGia;
	private Text text_DonViTinh;
	private Text text_TinhTrangTaiSan;
	private Text text_BaoHanh;
	private Text text_MaPTGT;
	private Table table_PhuKien;
	private Table table_muasamganday;
	private final int treeviewHeight = 23;
	private Table table_HosoGanday;
	protected static NGUOIDUNG user;
	private MenuItem menuItem_SEPARATOR1;
	private MenuItem menuItem_SEPARATOR3;
	private ToolItem toolItem;
	private Text text_XuatXu;
	private Tree tree_DanhsachTaisan;
	private Tree Tree_DonViSuDung;
	private Tree tree_LoaiTaisan;
	private Tree tree_NHOMTaisan_Codinh_huuhinh;
	private Text text_1;
	private Table table_lichSu_Suachua;
	protected String Selectindex;
	protected PHONGBAN dv;
	private Table table_Oto;
	private Table table_Xemay;
	private Table table_LichsuBaoduong;
	private Table table_LichSudieuxe;
	private ArrayList<TAISAN> row_taisan_mainForm;
	private final Controler controler;
	private Tree tree_Quyettoan;
	private Tree tree_Nghiemthu;
	private Tree tree_Thuchien;
	private Tree tree_Dexuat;
	private Tree tree_CongviecDangtrienkhai;
	private Text text_2;
	private Text text_5;
	private Fill_MainForm Mainformfiller;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Icondata icondata = new Icondata();
	private Table table_LichCongtac;
	private int tableSelected = 0;
	private Tree tree_NhomTaisan_Codinh_Vohinh;
	private Tree tree_NhomTaisan_Codinh_dacbiet;
	private Tree tree_Nhomtaisan_Codinh_Dacthu;
	private Nhomtaisan_162 N162;
	private Loaitaisan_19BCA L_19BCA;
	private Menu_Handler mh;
	private Tool_Handler th;
	protected int selected_Tree;
	private static Log log = LogFactory.getLog(MainForm.class);
	private MenuItem mntmHienthiNamconlai;
	private MenuItem mntmHienthiGiatriconlai;
	private Composite ThongtinThongkeDanhsachTaisan;
	private TabItem tbtmPhuongtienvantai;
	private TabItem tbtmDanhSachTaisan;
	private TabItem tbtmViecDangTrienKhai;
	private TabItem tbtmHosoGanday;
	private TabItem tbtmThongKeTinhhinhPTTS;
	private Table table_LichsuChuyengiaoNoibo;
	private DateTime dateTime_LichCongtac;

	public MainForm(NGUOIDUNG user) {
		MainForm.user = user;
		controler = new Controler(user);
	}

	/**
	 * Launch the application.
	 * 
	 * @author NgocDong
	 */

	public static void main(String[] args) {
		try {
			MainForm window = new MainForm(user);
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
		new FormTemplate().setCenterScreen(shell);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @throws SQLException
	 */

	protected void createContents() throws SQLException {

		shell = new Shell();
		shell.setImage(icondata.MainIcon);
		shell.setMinimumSize(new Point(500, 300));
		shell.setSize(938, 580);
		shell.setText("Giao diện chính - Tài khoản hiện hành: [" + user.getTEN_TAI_KHOAN() + "]");
		mh = new Menu_Handler(user);
		th = new Tool_Handler(user, shell.getDisplay(), shell);
		PopupMenu_MainView_TreeTaisan_Handler pmth = new PopupMenu_MainView_TreeTaisan_Handler(Display.getDefault(),
				shell, user);
		Wait w = new Wait(shell.getDisplay());
		w.open();
		GridLayout gl_shell = new GridLayout(1, false);
		shell.setLayout(gl_shell);
		TreeRowStyle tsl = new TreeRowStyle();
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("Hệ thống");

		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);

		MenuItem mntmTiKhonHin = new MenuItem(menu_1, SWT.NONE);
		mntmTiKhonHin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_TaiKhoanHienHanh();
				} catch (SQLException e1) {
					int code = 0;
					ErorrBox eb = new ErorrBox(shell, SWT.DIALOG_TRIM, "Lỗi Khi Mở [tài khoản hiện hành]", "message",
							"log", code, user);
					try {
						eb.open();
					} catch (SQLException e2) {
						log.error(e2.getMessage());
						e2.printStackTrace();
					}
					e1.printStackTrace();
				}
			}
		});
		mntmTiKhonHin.setText("Tài khoản hiện hành");

		MenuItem mntmngXut = new MenuItem(menu_1, SWT.NONE);
		mntmngXut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				user = null;
				shell.dispose();
				login l = new login();
				l.open();
			}
		});
		mntmngXut.setText("Đăng xuất");

		MenuItem mntmCit = new MenuItem(menu_1, SWT.NONE);
		mntmCit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				th.OpenForm_setting();
			}
		});
		mntmCit.setText("Cài đặt");

		new MenuItem(menu_1, SWT.SEPARATOR);

		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("Kh\u1EDFi \u0111\u1ED9ng l\u1EA1i");

		MenuItem mntmThot = new MenuItem(menu_1, SWT.NONE);
		mntmThot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.setVisible(false);
				Exit_Box ThoatChuongTrinh = new Exit_Box(null);
				ThoatChuongTrinh.open();
				shell.setVisible(true);
			}
		});
		mntmThot.setText("Tho\u00E1t");

		MenuItem mntmQunTrH = new MenuItem(menu, SWT.CASCADE);
		mntmQunTrH.setText("Quản trị Người dùng");

		Menu menu_7 = new Menu(mntmQunTrH);
		mntmQunTrH.setMenu(menu_7);

		MenuItem mntmTiKhon = new MenuItem(menu_7, SWT.NONE);
		mntmTiKhon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_TaiKhoanCuatoi();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTiKhon.setText("Tài khoản cá nhân");

		MenuItem mntmDanhSchTi = new MenuItem(menu_7, SWT.NONE);
		mntmDanhSchTi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_QuanlyTaikhoanNguoidung(Display.getCurrent());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhSchTi.setText("Danh sách tài khoản - Phân quyền người dùng");

		new MenuItem(menu_7, SWT.SEPARATOR);

		MenuItem mntmHotngNgi = new MenuItem(menu_7, SWT.NONE);
		mntmHotngNgi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_LogUser(Display.getCurrent());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmHotngNgi.setText("Hoạt động người dùng");

		MenuItem mntmQunLNhm = new MenuItem(menu_7, SWT.NONE);
		mntmQunLNhm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_QuanlyQuyenhan(Display.getCurrent());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmQunLNhm.setText("Quản lý quyền hạn");

		new MenuItem(menu, SWT.SEPARATOR);

		MenuItem mntmCngC = new MenuItem(menu, SWT.CASCADE);
		mntmCngC.setText("C\u00F4ng c\u1EE5");

		Menu menu_3 = new Menu(mntmCngC);
		mntmCngC.setMenu(menu_3);

		MenuItem mntmTngTiSn = new MenuItem(menu_3, SWT.NONE);
		mntmTngTiSn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Tool_Excel_NhapPTTS(shell.getDisplay());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTngTiSn.setText("Nhập Danh sách tài sản từ File Excel");

		MenuItem mntmNewItem_6 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Tool_Excel_NhapPhuongtienGiaothong(shell.getDisplay());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmNewItem_6.setText("Nhập Thông tin Phương tiện giao thông");

		MenuItem mntmngBVi = new MenuItem(menu_3, SWT.NONE);
		mntmngBVi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		mntmngBVi.setText("Đồng bộ với dữ liệu kế toán tài sản");

		new MenuItem(menu_3, SWT.SEPARATOR);

		MenuItem mntmXutFileD = new MenuItem(menu_3, SWT.NONE);
		mntmXutFileD.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_Xuat_Dulieu_Tasaisan();
			}
		});
		mntmXutFileD.setText("Xuất dữ liệu PTTS - excel");

		new MenuItem(menu_3, SWT.SEPARATOR);

		MenuItem mntmHTrKim = new MenuItem(menu_3, SWT.NONE);
		mntmHTrKim.setText("Hỗ trợ kiểm tra dữ liệu");

		new MenuItem(menu_3, SWT.SEPARATOR);

		MenuItem mntmGiaoTiepSanner = new MenuItem(menu_3, SWT.NONE);
		mntmGiaoTiepSanner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

			}
		});
		mntmGiaoTiepSanner.setText("Giao tiếp Sanner");

		MenuItem mntmNewItem_3 = new MenuItem(menu_3, SWT.NONE);
		mntmNewItem_3.setText("Biểu mẫu");

		MenuItem mntmNewSubmenu_3 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_3.setText("Thống kê dữ liệu");

		Menu menu_5 = new Menu(mntmNewSubmenu_3);
		mntmNewSubmenu_3.setMenu(menu_5);

		MenuItem mntmTnhChungV = new MenuItem(menu_5, SWT.NONE);
		mntmTnhChungV.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_TinhHinhPhuongTienTaiSan();
			}
		});
		mntmTnhChungV.setText("Tình hình chung Phương tiện tài sản");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmTnhHnhSa = new MenuItem(menu_5, SWT.NONE);
		mntmTnhHnhSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.Openform_TinhHinhSuaChuaBaoDuong();
			}
		});
		mntmTnhHnhSa.setText("Tình hình sửa chữa bảo dưỡng");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmPhnVic = new MenuItem(menu_5, SWT.NONE);
		mntmPhnVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_PhanViecDaGiao();
			}
		});
		mntmPhnVic.setText("Phần việc đã giao");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmGimStHot = new MenuItem(menu_5, SWT.NONE);
		mntmGimStHot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_GiamSatHoatDongNguoiDung();
			}
		});
		mntmGimStHot.setText("Giám sát hoạt động người dùng");

		MenuItem mntmNewItem_2 = new MenuItem(menu_5, SWT.NONE);
		mntmNewItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_DeNghiNghiemVu();
			}
		});
		mntmNewItem_2.setText("Đề nghị nhiệm vụ");

		new MenuItem(menu_5, SWT.SEPARATOR);

		MenuItem mntmTnhHnhCp = new MenuItem(menu_5, SWT.NONE);
		mntmTnhHnhCp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenFrom_TinhHinhCapNhatDuLieu();
			}
		});
		mntmTnhHnhCp.setText("Tình hình cập nhật dữ liệu");

		MenuItem mntmQunLDanh = new MenuItem(menu, SWT.CASCADE);
		mntmQunLDanh.setText("Quản lý danh mục");

		Menu menu_10 = new Menu(mntmQunLDanh);
		mntmQunLDanh.setMenu(menu_10);

		MenuItem mntmDanhMcLoi = new MenuItem(menu_10, SWT.NONE);
		mntmDanhMcLoi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Tool_QuanlyLoaiTaisan(shell.getDisplay());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhMcLoi.setText("Danh mục Loại tài sản");

		MenuItem mntmDanhMcNhm = new MenuItem(menu_10, SWT.NONE);
		mntmDanhMcNhm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Tool_QuanLyNhomTaisan(shell.getDisplay());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhMcNhm.setText("Danh mục Nhóm tài sản");

		MenuItem mntmNhpDanhMc = new MenuItem(menu_10, SWT.NONE);
		mntmNhpDanhMc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_Tool_Excel_Import_LoaiTaisan(shell.getDisplay());
			}
		});
		mntmNhpDanhMc.setText("Nhập danh mục Loại tài sản từ Excel");

		MenuItem mntmNhpDanhMc_1 = new MenuItem(menu_10, SWT.NONE);
		mntmNhpDanhMc_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_Tool_Excel_Import_NhomTaisan(shell.getDisplay());
			}
		});
		mntmNhpDanhMc_1.setText("Nhập danh mục Nhóm tài sản từ Excel");

		MenuItem mntmCcDanhMc_1 = new MenuItem(menu_10, SWT.CASCADE);
		mntmCcDanhMc_1.setText("Các danh mục tài sản");

		Menu menu_13 = new Menu(mntmCcDanhMc_1);
		mntmCcDanhMc_1.setMenu(menu_13);

		MenuItem mntmDanhMcn_1 = new MenuItem(menu_13, SWT.NONE);
		mntmDanhMcn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_QuanLyDanhMuc_Donvitinh(shell.getDisplay());
			}
		});
		mntmDanhMcn_1.setText("Danh mục đơn vị tính");

		MenuItem mntmDanhMcXut = new MenuItem(menu_13, SWT.NONE);
		mntmDanhMcXut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_QuanLyDanhMuc_Xuatxu(shell.getDisplay());
			}
		});
		mntmDanhMcXut.setText("Danh mục xuất xứ");

		MenuItem mntmDanhMcH = new MenuItem(menu_13, SWT.NONE);
		mntmDanhMcH.setText("Danh mục hệ thống Phương tiện kỹ thuật");

		new MenuItem(menu_10, SWT.SEPARATOR);

		MenuItem mntmnVS = new MenuItem(menu_10, SWT.NONE);
		mntmnVS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Tool_QuanLy_DanhMuc_PhongBan(Display.getDefault());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmnVS.setText("Đơn vị sử dụng, quản lý");

		MenuItem mntmDanhMcNgun_2 = new MenuItem(menu_10, SWT.NONE);
		mntmDanhMcNgun_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Danhmuc_Suachua_Baoduong(shell);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhMcNgun_2.setText("Danh mục Nguồn Sửa chữa - Bảo dưỡng");

		MenuItem mntmDanhMcNgun = new MenuItem(menu_10, SWT.NONE);
		mntmDanhMcNgun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpForm_NguonTang();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhMcNgun.setText("Danh mục Nguồn tăng");

		MenuItem mntmDanhMcNgun_1 = new MenuItem(menu_10, SWT.NONE);
		mntmDanhMcNgun_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_Danhmuc_Nguongiam(shell);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDanhMcNgun_1.setText("Danh mục Nguồn giảm");

		new MenuItem(menu, SWT.SEPARATOR);

		MenuItem mntmTiSn_1 = new MenuItem(menu, SWT.CASCADE);
		mntmTiSn_1.setText("Tài sản");

		Menu menu_2 = new Menu(mntmTiSn_1);
		mntmTiSn_1.setMenu(menu_2);

		MenuItem mntmTngTiSn_1 = new MenuItem(menu_2, SWT.NONE);
		mntmTngTiSn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_TangTaiSan();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTngTiSn_1.setText("Tăng tài sản");

		MenuItem mntmGimTiSn = new MenuItem(menu_2, SWT.NONE);
		mntmGimTiSn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_GiamTaiSan();
			}
		});
		mntmGimTiSn.setText("Giảm tài sản");

		MenuItem mntmCpNhpTi = new MenuItem(menu_2, SWT.NONE);
		mntmCpNhpTi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_CapNhatThongTinTaiSan();
			}
		});
		mntmCpNhpTi.setText("Cập nhật thông tin tài sản");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmBnGiaoTi = new MenuItem(menu_2, SWT.NONE);
		mntmBnGiaoTi.setText("Bàn giao tài sản nội bộ");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmNhpMiH = new MenuItem(menu_2, SWT.NONE);
		mntmNhpMiH.setText("Nhập mới hồ sơ tài sản");

		MenuItem mntmCpNhpH = new MenuItem(menu_2, SWT.NONE);
		mntmCpNhpH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_CapNhatHoSoTaiSan();
			}
		});
		mntmCpNhpH.setText("Cập nhập hồ sơ tài sản");

		MenuItem mntmQunLH = new MenuItem(menu_2, SWT.NONE);
		mntmQunLH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_QuanLyHoSoTaiSan();
			}
		});
		mntmQunLH.setText("Quản lý hồ sơ tài sản");

		MenuItem mntmPhnVic_2 = new MenuItem(menu, SWT.CASCADE);
		mntmPhnVic_2.setText("Công việc");

		Menu menu_12 = new Menu(mntmPhnVic_2);
		mntmPhnVic_2.setMenu(menu_12);

		MenuItem mntmPhnVic_1 = new MenuItem(menu_12, SWT.NONE);
		mntmPhnVic_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_ThemPhanViec();
			}
		});
		mntmPhnVic_1.setText("Tạo mới công việc");

		MenuItem mntmDanhSchPhn = new MenuItem(menu_12, SWT.NONE);
		mntmDanhSchPhn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_DanhSachPhanViec();
			}
		});
		mntmDanhSchPhn.setText("Danh sách công việc");

		new MenuItem(menu_12, SWT.SEPARATOR);

		MenuItem mntmangThcHin = new MenuItem(menu_12, SWT.NONE);
		mntmangThcHin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_CongViecDangTrienKhai();
			}
		});
		mntmangThcHin.setText("Công việc đan triển khai");

		MenuItem mntmSaChaBo = new MenuItem(menu_12, SWT.NONE);
		mntmSaChaBo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_LichSuSuaChuaBaoDuong();
			}
		});
		mntmSaChaBo.setText("Lịch sử sữa chữa bảo dưỡng");

		MenuItem mntmThngKPhn = new MenuItem(menu_12, SWT.NONE);
		mntmThngKPhn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_ThongKePhanViec();
			}
		});
		mntmThngKPhn.setText("Thống kê phần việc");

		MenuItem mntmNewSubmenu_2 = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu_2.setText("H\u1ED3 s\u01A1 sao l\u01B0u");

		Menu menu_4 = new Menu(mntmNewSubmenu_2);
		mntmNewSubmenu_2.setMenu(menu_4);

		MenuItem mntmNhpHS = new MenuItem(menu_4, SWT.NONE);
		mntmNhpHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.CapNhatHoSoDulieu();
			}
		});
		mntmNhpHS.setText("Cập nhật hồ sơ");

		new MenuItem(menu_4, SWT.SEPARATOR);

		MenuItem mntmHSTng = new MenuItem(menu_4, SWT.NONE);
		mntmHSTng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoTangTaiSan();
			}
		});
		mntmHSTng.setText("Hồ sơ tăng tài sản");

		MenuItem mntmHSGim = new MenuItem(menu_4, SWT.NONE);
		mntmHSGim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoGiamTaiSan();
			}
		});
		mntmHSGim.setText("Hồ sơ giảm tài sản");

		MenuItem mntmHSChuyn = new MenuItem(menu_4, SWT.NONE);
		mntmHSChuyn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoChuyenGiaoTaiSanNoiBo();
			}
		});
		mntmHSChuyn.setText("Hồi sơ chuyển giao tài sản nội bộ");

		new MenuItem(menu_4, SWT.SEPARATOR);

		MenuItem mntmNewItem_4 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoSuaChua_BaoDuong_NangCapTaiSan();
			}
		});
		mntmNewItem_4.setText("Hồ sơ bảo dưỡng, sữa chữa, nâng cấp PTTS");

		MenuItem mntmNewItem_5 = new MenuItem(menu_4, SWT.NONE);
		mntmNewItem_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoThanhToan();
			}
		});
		mntmNewItem_5.setText("Hồ sơ thanh toán");

		MenuItem mntmGny = new MenuItem(menu, SWT.CASCADE);
		mntmGny.setText("Gần đây");

		Menu menu_6 = new Menu(mntmGny);
		mntmGny.setMenu(menu_6);

		MenuItem mntmTngGny = new MenuItem(menu_6, SWT.NONE);
		mntmTngGny.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_TangGanDay();
			}
		});
		mntmTngGny.setText("Tăng gần đây");

		MenuItem mntmGimGny = new MenuItem(menu_6, SWT.NONE);
		mntmGimGny.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_GiamGanDay();
			}
		});
		mntmGimGny.setText("Giảm gần đây");

		new MenuItem(menu_6, SWT.SEPARATOR);

		MenuItem mntmCngVicang = new MenuItem(menu_6, SWT.NONE);
		mntmCngVicang.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_CongViecDangTrienKhai();
			}
		});
		mntmCngVicang.setText("Công việc đang triển khai");

		new MenuItem(menu_6, SWT.SEPARATOR);

		MenuItem mntmHSCp = new MenuItem(menu_6, SWT.NONE);
		mntmHSCp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mh.OpenForm_HoSoCapNhatGanDay();
			}
		});
		mntmHSCp.setText("Hồ sơ cập nhật gần đây");

		MenuItem mntmTmKim = new MenuItem(menu, SWT.CASCADE);
		mntmTmKim.setText("T\u00ECm ki\u1EBFm");

		Menu menu_8 = new Menu(mntmTmKim);
		mntmTmKim.setMenu(menu_8);

		MenuItem mntmTmKimTi = new MenuItem(menu_8, SWT.NONE);
		mntmTmKimTi.setText("Tìm kiếm tài sản");

		MenuItem mntmTmKimH = new MenuItem(menu_8, SWT.NONE);
		mntmTmKimH.setText("Tìm kiếm hồ sơ");

		MenuItem mntmTrGip = new MenuItem(menu, SWT.CASCADE);
		mntmTrGip.setText("Tr\u1EE3 gi\u00FAp");

		Menu menu_9 = new Menu(mntmTrGip);
		mntmTrGip.setMenu(menu_9);

		MenuItem mntmHngDn = new MenuItem(menu_9, SWT.NONE);
		mntmHngDn.setText("Hướng dẫn");

		MenuItem mntmPhmTt = new MenuItem(menu_9, SWT.NONE);
		mntmPhmTt.setText("Phím tắt");

		MenuItem mntmVChngTi = new MenuItem(menu_9, SWT.NONE);
		mntmVChngTi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AboutUs au = new AboutUs(shell, SWT.DIALOG_TRIM);
				au.open();
			}
		});
		mntmVChngTi.setText("Về chúng tôi");

		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem tltmiuXe = new ToolItem(toolBar, SWT.NONE);
		tltmiuXe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_LenhDieuXe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmiuXe.setImage(icondata.LenhDieuxeIcon);
		tltmiuXe.setText("Lệnh Điều xe");

		ToolItem tltmLichdieuxe = new ToolItem(toolBar, SWT.NONE);
		tltmLichdieuxe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Lichsu_Dieuxe();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLichdieuxe.setImage(icondata.NhatkyDieuxeIcon);
		tltmLichdieuxe.setText("Nhật ký điều xe");

		ToolItem tltmLchBoDng = new ToolItem(toolBar, SWT.NONE);
		tltmLchBoDng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_LichBaoduong();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLchBoDng.setImage(icondata.LichbaoduongIcon);
		tltmLchBoDng.setText("Lịch bảo dưỡng");

		ToolItem tltmLichDangKiem = new ToolItem(toolBar, SWT.NONE);
		tltmLichDangKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Lich_Dang_Kiem();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLichDangKiem.setImage(icondata.LichDangkiemIcon);
		tltmLichDangKiem.setText("Lịch Đăng kiểm");

		ToolItem tltmPhngTinC = new ToolItem(toolBar, SWT.NONE);
		tltmPhngTinC.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_ThongKe_PHUONGTIEN_GIAOTHONG();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmPhngTinC.setImage(icondata.ThongkeLichtrinhIcon);
		tltmPhngTinC.setText("Thống kê lịch trình");

		ToolItem toolItem_3 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmTiuChunBo = new ToolItem(toolBar, SWT.NONE);
		tltmTiuChunBo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_TieuchuanBaoduong();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmTiuChunBo.setImage(icondata.TieuChuanBaoduongIcon);
		tltmTiuChunBo.setText("Tiêu chuẩn bảo dưỡng");

		ToolItem tltmKHnng = new ToolItem(toolBar, SWT.NONE);
		tltmKHnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Ky_han_Dang_kiem();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tltmKHnng.setText("Kỳ hạn Đăng kiểm");
		tltmKHnng.setImage(icondata.KyhanDangkiem);

		ToolItem tltmnhMcXng = new ToolItem(toolBar, SWT.NONE);
		tltmnhMcXng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					mh.OpenForm_DinhmucNhienlieu(shell.getDisplay());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmnhMcXng.setImage(icondata.DinhmucNhienlieu);
		tltmnhMcXng.setText("Định mức Nhiên liệu");

		toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmBoDng = new ToolItem(toolBar, SWT.NONE);
		tltmBoDng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Baoduong_Phuongtien_Giaothong();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmBoDng.setImage(icondata.BaoduongIcon);
		tltmBoDng.setText("Bảo dưỡng");

		ToolItem tltmTangtaisan = new ToolItem(toolBar, SWT.NONE);
		tltmTangtaisan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_TangTaiSan();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmTangtaisan.setImage(icondata.MuasamIcon);
		tltmTangtaisan.setText("Mua sắm");

		ToolItem tltmTaoMoiCongViec = new ToolItem(toolBar, SWT.NONE);
		tltmTaoMoiCongViec.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Suachua();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmTaoMoiCongViec.setImage(icondata.SuachuaIcon);
		tltmTaoMoiCongViec.setText("Sửa chữa");

		ToolItem tltmGiamTaiSan = new ToolItem(toolBar, SWT.NONE);
		tltmGiamTaiSan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_GiamTaiSan();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmGiamTaiSan.setImage(icondata.ThanhlyIcon);
		tltmGiamTaiSan.setText("Thanh lý");

		ToolItem toolItem_4 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmBnGiaoNi = new ToolItem(toolBar, SWT.NONE);
		tltmBnGiaoNi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_XemDot_Bangiao_Taisan_Noibo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tltmBnGiaoNi.setImage(icondata.BangiaoTaisanIcon);
		tltmBnGiaoNi.setText("Bàn giao Nội bộ");

		ToolItem tltmThVin = new ToolItem(toolBar, SWT.NONE);
		tltmThVin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_ThuvienDexuat();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThVin.setImage(icondata.ThuvienDexuatIcon);
		tltmThVin.setText("Thư viện đề xuất");

		ToolItem tltmTiuChunKhu = new ToolItem(toolBar, SWT.NONE);
		tltmTiuChunKhu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				th.OpenForm_Tool_Danhmuc_Lienhe();
			}
		});
		tltmTiuChunKhu.setImage(icondata.LienheDichvuIcon);
		tltmTiuChunKhu.setText("Liên hệ dịch vụ");

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmCongViecCuaToi = new ToolItem(toolBar, SWT.NONE);
		tltmCongViecCuaToi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_CongViecCuaToi();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmCongViecCuaToi.setImage(icondata.CongviecCuatoiIcon);
		tltmCongViecCuaToi.setText("Công việc của tôi");

		ToolItem tltmangThcHin_1 = new ToolItem(toolBar, SWT.NONE);
		tltmangThcHin_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_CongViec_Dangthuchien();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tltmangThcHin_1.setText("Đang thực hiện");
		tltmangThcHin_1.setImage(icondata.DanhthuchienIcon);

		ToolItem tltmDanhSachCongViec = new ToolItem(toolBar, SWT.NONE);
		tltmDanhSachCongViec.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Quanly_CongViec();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmDanhSachCongViec.setImage(icondata.QuanlycongviecIcon);
		tltmDanhSachCongViec.setText("Quản lý công việc");

		ToolItem tltmBiu = new ToolItem(toolBar, SWT.NONE);
		tltmBiu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				th.OpenForm_Tool_Chart();
			}
		});
		tltmBiu.setText("Biểu đồ");
		tltmBiu.setImage(icondata.BieudoIcon);

		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmCapNhatThongTin = new ToolItem(toolBar, SWT.NONE);
		tltmCapNhatThongTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_QuanlyHoso();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmCapNhatThongTin.setImage(icondata.QuanlyHosoIcon);
		tltmCapNhatThongTin.setText("Quản lý hồ sơ");

		ToolItem tltmNghNhim = new ToolItem(toolBar, SWT.NONE);
		setIconThongbaomoi(tltmNghNhim);

		tltmNghNhim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					th.OpenForm_Tool_Thongbao_Chuadoc();
					setIconThongbaomoi(tltmNghNhim);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmNghNhim.setText("Thông báo chưa đọc");

		ToolItem tltmHoatDongNguoidung = new ToolItem(toolBar, SWT.NONE);
		tltmHoatDongNguoidung.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				th.OpenForm_Tool_HopthuLuu();
			}
		});
		tltmHoatDongNguoidung.setImage(icondata.HopthuluIcon);
		tltmHoatDongNguoidung.setText("Hộp thư lưu");

		ToolItem tltmTimKiem = new ToolItem(toolBar, SWT.NONE);
		tltmTimKiem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				th.OpenForm_Tool_TimKiem();
			}
		});
		tltmTimKiem.setImage(icondata.TimkiemIcon);
		tltmTimKiem.setText("T\u00ECm ki\u1EBFm");

		SashForm sashForm_1 = new SashForm(shell, SWT.NONE);
		sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabFolder tabFolder = new TabFolder(sashForm_1, SWT.NONE);

		TabItem tbtmNhmTiSn_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNhmTiSn_1.setText("Phân loại tài sản");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNhmTiSn_1.setControl(composite_1);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);

		SashForm sashForm = new SashForm(composite_1, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Tree_DonViSuDung = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		Tree_DonViSuDung.setLinesVisible(true);
		tsl.setTreeItemHeight(Tree_DonViSuDung, 20);
		Tree_DonViSuDung.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = Tree_DonViSuDung.getSelection();
					if (items.length > 0) {
						dv = null;
						dv = (PHONGBAN) items[0].getData();
						Mainformfiller.fillDataOto(table_Oto, getdata_Oto());
						Mainformfiller.fillDataXemay(table_Xemay, getdata_Xemay());
						switch (selected_Tree) {
						case 0:
							row_taisan_mainForm = getdata_NHOMTaisan_Codinh();
							break;
						case 1:
							row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Vohinh();
							break;
						case 2:
							row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Dacthu();
							break;
						case 3:
							row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Dacbiet();
							break;
						case 5:
							row_taisan_mainForm = getdata_LoaiTaisan();
							break;
						default:
							break;
						}
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		ExpandBar expandBar = new ExpandBar(sashForm, SWT.V_SCROLL);
		expandBar.addExpandListener(new ExpandAdapter() {
			@Override
			public void itemExpanded(ExpandEvent e) {
				// computeSize(SWT.DEFAULT, SWT.DEFAULT).y
				ExpandItem min = null;
				for (ExpandItem it : expandBar.getItems()) {
					if (!it.equals(e.item)) {
						it.setExpanded(false);
						min = it;
					}
				}
				if (min != null) {
					((ExpandItem) e.item).setHeight(expandBar.getSize().y - (min.getHeaderHeight() * 6));
				}
			}

			@Override
			public void itemCollapsed(ExpandEvent e) {
			}
		});
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));

		ExpandItem xpndtmTaiSanCodinh_huuhinh = new ExpandItem(expandBar, SWT.NONE);
		xpndtmTaiSanCodinh_huuhinh
				.setImage(SWTResourceManager.getImage(MainForm.class, "/Places-folder-black-icon.png"));
		xpndtmTaiSanCodinh_huuhinh.setText("Nhóm Tài sản cố định Hữu hình");
		tree_NHOMTaisan_Codinh_huuhinh = new Tree(expandBar, SWT.BORDER | SWT.FULL_SELECTION);
		xpndtmTaiSanCodinh_huuhinh.setControl(tree_NHOMTaisan_Codinh_huuhinh);
		tree_NHOMTaisan_Codinh_huuhinh.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					selected_Tree = 0;
					TreeItem[] items = tree_NHOMTaisan_Codinh_huuhinh.getSelection();
					if (items.length > 0) {
						row_taisan_mainForm = getdata_NHOMTaisan_Codinh();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		tsl.setTreeItemHeight(tree_NHOMTaisan_Codinh_huuhinh, 20);
		tree_NHOMTaisan_Codinh_huuhinh.pack();

		xpndtmTaiSanCodinh_huuhinh.setHeight(280);

		ExpandItem xpndtmTaiSanCodinh_vohinh = new ExpandItem(expandBar, 0);
		xpndtmTaiSanCodinh_vohinh
				.setImage(SWTResourceManager.getImage(MainForm.class, "/Places-folder-black-icon.png"));
		xpndtmTaiSanCodinh_vohinh.setText("Nhóm Tài sản cố định Vô hình");

		tree_NhomTaisan_Codinh_Vohinh = new Tree(expandBar, SWT.BORDER);
		xpndtmTaiSanCodinh_vohinh.setControl(tree_NhomTaisan_Codinh_Vohinh);
		tree_NhomTaisan_Codinh_Vohinh.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					selected_Tree = 1;
					TreeItem[] items = tree_NhomTaisan_Codinh_Vohinh.getSelection();
					if (items.length > 0) {
						row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Vohinh();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		tsl.setTreeItemHeight(tree_NHOMTaisan_Codinh_huuhinh, 20);
		tree_NHOMTaisan_Codinh_huuhinh.pack();
		xpndtmTaiSanCodinh_vohinh.setHeight(280);

		ExpandItem xpndtmTaiSanCodinhDacthu = new ExpandItem(expandBar, 0);
		xpndtmTaiSanCodinhDacthu.setImage(SWTResourceManager.getImage(MainForm.class, "/Places-folder-black-icon.png"));
		xpndtmTaiSanCodinhDacthu.setText("Nhóm Tài sản cố định Đặc thù");

		tree_Nhomtaisan_Codinh_Dacthu = new Tree(expandBar, SWT.BORDER);
		tree_Nhomtaisan_Codinh_Dacthu.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					selected_Tree = 2;
					TreeItem[] items = tree_Nhomtaisan_Codinh_Dacthu.getSelection();
					if (items.length > 0) {
						row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Dacthu();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		xpndtmTaiSanCodinhDacthu.setControl(tree_Nhomtaisan_Codinh_Dacthu);
		xpndtmTaiSanCodinhDacthu.setHeight(280);

		ExpandItem xpndtmTaiSanCodinhDacbiet = new ExpandItem(expandBar, 0);
		xpndtmTaiSanCodinhDacbiet
				.setImage(SWTResourceManager.getImage(MainForm.class, "/Places-folder-black-icon.png"));
		xpndtmTaiSanCodinhDacbiet.setText("Nhóm Tài sản cố định Đặc biệt");

		tree_NhomTaisan_Codinh_dacbiet = new Tree(expandBar, SWT.BORDER);
		tree_NhomTaisan_Codinh_dacbiet.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					selected_Tree = 3;
					TreeItem[] items = tree_NhomTaisan_Codinh_dacbiet.getSelection();
					if (items.length > 0) {
						row_taisan_mainForm = getdata_NHOMTaisan_Codinh_Dacbiet();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		xpndtmTaiSanCodinhDacbiet.setControl(tree_NhomTaisan_Codinh_dacbiet);
		xpndtmTaiSanCodinhDacbiet.setHeight(280);

		ExpandItem xpndtmLoaiTaisan = new ExpandItem(expandBar, 0);
		xpndtmLoaiTaisan.setImage(SWTResourceManager.getImage(MainForm.class, "/Places-folder-green-icon.png"));
		xpndtmLoaiTaisan.setText("Loại Tài sản Đặc biệt - chuyên dùng - ...");
		tree_LoaiTaisan = new Tree(expandBar, SWT.BORDER | SWT.FULL_SELECTION);
		xpndtmLoaiTaisan.setControl(tree_LoaiTaisan);

		tree_LoaiTaisan.pack();
		tree_LoaiTaisan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					selected_Tree = 5;
					TreeItem[] items = tree_LoaiTaisan.getSelection();
					if (items.length > 0) {
						row_taisan_mainForm = getdata_LoaiTaisan();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		tsl.setTreeItemHeight(tree_LoaiTaisan, 20);
		tree_LoaiTaisan.pack();
		xpndtmLoaiTaisan.setHeight(280);

		sashForm.setWeights(new int[] { 382, 1000 });
		TabItem tbtmNhmCnh = new TabItem(tabFolder, SWT.NONE);
		tbtmNhmCnh.setText("Mua sắm gần đây");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNhmCnh.setControl(composite_2);
		GridLayout gl_composite_2 = new GridLayout(1, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);

		TableViewer tableViewer = new TableViewer(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_muasamganday = tableViewer.getTable();
		table_muasamganday.setLinesVisible(true);
		table_muasamganday.setHeaderVisible(true);
		table_muasamganday.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TableColumn tblclmnnVNhn = new TableColumn(table_muasamganday, SWT.NONE);
		tblclmnnVNhn.setWidth(110);
		tblclmnnVNhn.setText("Hình thức");

		TableColumn tblclmnNgyThngBn = new TableColumn(table_muasamganday, SWT.NONE);
		tblclmnNgyThngBn.setWidth(160);
		tblclmnNgyThngBn.setText("Thời điểm");

		TableColumn DonviDexuat = new TableColumn(table_muasamganday, SWT.NONE);
		DonviDexuat.setWidth(120);
		DonviDexuat.setText("Đơn vị đề xuất");

		TableColumn tblclmnTntMua = new TableColumn(table_muasamganday, SWT.NONE);
		tblclmnTntMua.setWidth(200);
		tblclmnTntMua.setText("Tên đợt mua sắm - tiếp nhận");

		TabFolder tabFolder_Main = new TabFolder(sashForm_1, SWT.NONE);

		tbtmPhuongtienvantai = new TabItem(tabFolder_Main, SWT.NONE);
		tbtmPhuongtienvantai.setText("Phương tiện Giao thông");

		SashForm sashForm_6 = new SashForm(tabFolder_Main, SWT.NONE);
		tbtmPhuongtienvantai.setControl(sashForm_6);

		SashForm sashForm_7 = new SashForm(sashForm_6, SWT.VERTICAL);

		Group grpT = new Group(sashForm_7, SWT.NONE);
		grpT.setText("Ô tô");
		GridLayout gl_grpT = new GridLayout(1, false);
		gl_grpT.marginTop = 3;
		gl_grpT.marginWidth = 0;
		gl_grpT.marginHeight = 0;
		grpT.setLayout(gl_grpT);

		table_Oto = new Table(grpT, SWT.FULL_SELECTION);
		table_Oto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableSelected = 1;
				table_LichSudieuxe.removeAll();
				table_LichsuBaoduong.removeAll();
				TableItem tbi[] = table_Oto.getSelection();
				if (tbi.length > 0) {
					TAISAN t = ((TAISAN) tbi[0].getData());
					PHUONGTIEN_GIAOTHONG pg = t.getPhuongtien_Giaothong();
					try {
						ArrayList<LENH_DIEU_XE> ldxl = controler.getControl_LENH_DIEU_XE().get_List_LENH_DIEU_XE(pg,
								mdf.addDate(new Date(), -30), new Date());
						if (ldxl != null)
							fill_ldx(ldxl);
						ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler
								.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(
										mdf.addDate(new Date(), -30), new Date(), t.getMA_TAISAN());
						if (dsbl != null)
							fill_dsb(dsbl);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

			private void fill_dsb(ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl) throws SQLException {
				int i = 0;
				for (DOT_THUCHIEN_SUACHUA_BAODUONG dsb : dsbl) {
					TableItem tbi = new TableItem(table_LichsuBaoduong, SWT.NONE);
					GIAI_DOAN_THUC_HIEN gth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
					tbi.setText(new String[] { i + "", mdf.getViewStringDate(gth.getTHOI_DIEM_BAT_DAU()),
							dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() });
					i++;
				}
			}

			private void fill_ldx(ArrayList<LENH_DIEU_XE> ldxl) {
				int i = 0;
				for (LENH_DIEU_XE ldx : ldxl) {
					TableItem tbi = new TableItem(table_LichSudieuxe, SWT.NONE);
					tbi.setText(new String[] { i + "", mdf.getViewStringDateTime(ldx.getNGAY_DI()),
							ldx.getTEN_TAI_KHOAN() });
					i++;
				}
			}
		});
		table_Oto.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_Oto.setHeaderVisible(true);
		table_Oto.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table_Oto, SWT.NONE);
		tblclmnStt.setWidth(50);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnMT = new TableColumn(table_Oto, SWT.LEFT);
		tblclmnTnMT.setWidth(100);
		tblclmnTnMT.setText("TÊN, MÔ TẢ");

		TableColumn tblclmnBinS = new TableColumn(table_Oto, SWT.NONE);
		tblclmnBinS.setWidth(100);
		tblclmnBinS.setText("BIỂN SỐ");

		TableColumn tblclmnHangsanxuat = new TableColumn(table_Oto, SWT.NONE);
		tblclmnHangsanxuat.setWidth(120);
		tblclmnHangsanxuat.setText("HÃNG SẢN XUẤT");

		TableColumn tblclmnDngXe = new TableColumn(table_Oto, SWT.NONE);
		tblclmnDngXe.setWidth(100);
		tblclmnDngXe.setText("DÒNG XE");

		TableColumn tblclmnSKhung = new TableColumn(table_Oto, SWT.NONE);
		tblclmnSKhung.setWidth(100);
		tblclmnSKhung.setText("SỐ KHUNG");

		TableColumn tblclmnSMy = new TableColumn(table_Oto, SWT.NONE);
		tblclmnSMy.setWidth(100);
		tblclmnSMy.setText("SỐ MÁY");

		Menu menu_15 = new Menu(table_Oto);
		table_Oto.setMenu(menu_15);

		MenuItem mntmiuXe = new MenuItem(menu_15, SWT.NONE);
		mntmiuXe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_Oto.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							PHUONGTIEN_GIAOTHONG ptgt = controler.getControl_PHUONGTIEN_GIAOTHONG()
									.get_PHUONGTIEN_GIAOTHONG_FromTaisan(t.getMA_TAISAN());
							th.OpenForm_Tool_LenhDieuXe(ptgt);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmiuXe.setText("Điều xe");

		MenuItem mntmXemLchBo = new MenuItem(menu_15, SWT.NONE);
		mntmXemLchBo.setText("Xem lịch bảo dưỡng");

		MenuItem mntmXemLchng = new MenuItem(menu_15, SWT.NONE);
		mntmXemLchng.setText("Xem lịch đăng kiểm");

		new MenuItem(menu_15, SWT.SEPARATOR);

		MenuItem mntmSaCha = new MenuItem(menu_15, SWT.NONE);
		mntmSaCha.setText("Sửa chữa - bảo dưỡng");

		MenuItem mntmThanhL = new MenuItem(menu_15, SWT.NONE);
		mntmThanhL.setText("Thanh lý");

		new MenuItem(menu_15, SWT.SEPARATOR);

		MenuItem mntmXutFileExcel = new MenuItem(menu_15, SWT.NONE);
		mntmXutFileExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TAISAN[] data = new TAISAN[table_Oto.getItemCount()];
				for (int i = 0; i < data.length; i++) {
					data[i] = (TAISAN) table_Oto.getItem(i).getData();
				}
				try {
					pmth.OpenForm_Export_PHUONGTIEN_GIAOTHONG(data);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXutFileExcel.setText("Xuất File Excel danh sách thông tin Ô tô");

		MenuItem mntmThayiThng = new MenuItem(menu_15, SWT.NONE);
		mntmThayiThng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_Oto.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							pmth.OpenForm_Edit_PHUONGTIEN_GIAOTHONG(t);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThayiThng.setText("Thay đổi Thông tin Phương tiện giao thông");

		MenuItem mntmXemPtts = new MenuItem(menu_15, SWT.NONE);
		mntmXemPtts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_Oto.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							Integer MA_TAISAN = t.getMA_TAISAN();
							pmth.OpenForm_View_TaiSan(MA_TAISAN);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemPtts.setText("Xem Thông tin Phương tiện tài sản");

		Group grpXeMy = new Group(sashForm_7, SWT.NONE);
		grpXeMy.setText("Xe máy");
		GridLayout gl_grpXeMy = new GridLayout(1, false);
		gl_grpXeMy.marginHeight = 0;
		gl_grpXeMy.marginWidth = 0;
		gl_grpXeMy.marginTop = 3;
		grpXeMy.setLayout(gl_grpXeMy);

		table_Xemay = new Table(grpXeMy, SWT.FULL_SELECTION);
		table_Xemay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableSelected = 2;
				table_LichsuBaoduong.removeAll();
				TableItem tbi[] = table_Xemay.getSelection();
				if (tbi.length > 0) {
					TAISAN t = ((TAISAN) tbi[0].getData());
					try {
						ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler
								.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(
										mdf.addDate(new Date(), -30), new Date(), t.getMA_TAISAN());
						if (dsbl != null)
							fill_dsb(dsbl);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}

			private void fill_dsb(ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl) throws SQLException {
				int i = 0;
				for (DOT_THUCHIEN_SUACHUA_BAODUONG dsb : dsbl) {
					TableItem tbi = new TableItem(table_LichsuBaoduong, SWT.NONE);
					GIAI_DOAN_THUC_HIEN gth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
					tbi.setText(new String[] { i + "", mdf.getViewStringDate(gth.getTHOI_DIEM_BAT_DAU()),
							dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() });
					i++;
				}
			}
		});
		table_Xemay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_Xemay.setHeaderVisible(true);
		table_Xemay.setLinesVisible(true);

		TableColumn tblclmnStt_1 = new TableColumn(table_Xemay, SWT.NONE);
		tblclmnStt_1.setWidth(50);
		tblclmnStt_1.setText("STT");

		TableColumn tblclmnTnMT_1 = new TableColumn(table_Xemay, SWT.NONE);
		tblclmnTnMT_1.setWidth(100);
		tblclmnTnMT_1.setText("TÊN, MÔ TẢ");

		TableColumn tblclmnBinS_1 = new TableColumn(table_Xemay, SWT.NONE);
		tblclmnBinS_1.setWidth(100);
		tblclmnBinS_1.setText("BIỂN SỐ");

		TableColumn tableColumn = new TableColumn(table_Xemay, SWT.NONE);
		tableColumn.setWidth(120);
		tableColumn.setText("HÃNG SẢN XUẤT");

		TableColumn tableColumn_1 = new TableColumn(table_Xemay, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("DÒNG XE");

		TableColumn tblclmnSKhung_1 = new TableColumn(table_Xemay, SWT.NONE);
		tblclmnSKhung_1.setWidth(100);
		tblclmnSKhung_1.setText("SỐ KHUNG");

		TableColumn tblclmnSMy_1 = new TableColumn(table_Xemay, SWT.NONE);
		tblclmnSMy_1.setWidth(100);
		tblclmnSMy_1.setText("SỐ MÁY");

		Menu menu_14 = new Menu(table_Xemay);
		table_Xemay.setMenu(menu_14);

		MenuItem menuItem_1 = new MenuItem(menu_14, SWT.NONE);
		menuItem_1.setText("Xem lịch bảo dưỡng");

		MenuItem menuItem_2 = new MenuItem(menu_14, SWT.NONE);
		menuItem_2.setText("Xem lịch đăng kiểm");

		MenuItem menuItem_3 = new MenuItem(menu_14, SWT.NONE);
		menuItem_3.setText("Sửa chữa - bảo dưỡng");

		MenuItem menuItem_5 = new MenuItem(menu_14, SWT.NONE);
		menuItem_5.setText("Thanh lý");

		MenuItem menuItem_6 = new MenuItem(menu_14, SWT.SEPARATOR);

		MenuItem mntmXutFileExcel_1 = new MenuItem(menu_14, SWT.NONE);
		mntmXutFileExcel_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TAISAN[] data = new TAISAN[table_Xemay.getItemCount()];
				for (int i = 0; i < data.length; i++) {
					data[i] = (TAISAN) table_Xemay.getItem(i).getData();
				}
				try {
					pmth.OpenForm_Export_PHUONGTIEN_GIAOTHONG(data);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXutFileExcel_1.setText("Xuất File Excel danh sách thông tin xe máy");

		MenuItem mntmThayiThng_1 = new MenuItem(menu_14, SWT.NONE);
		mntmThayiThng_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_Xemay.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							pmth.OpenForm_Edit_PHUONGTIEN_GIAOTHONG(t);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThayiThng_1.setText("Thay đổi Thông tin Phương tiện giao thông");

		MenuItem mntmXemThngTin = new MenuItem(menu_14, SWT.NONE);
		mntmXemThngTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] items = table_Xemay.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							Integer MA_TAISAN = t.getMA_TAISAN();
							pmth.OpenForm_View_TaiSan(MA_TAISAN);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemThngTin.setText("Xem Thông tin Phương tiện tài sản");
		sashForm_7.setWeights(new int[] { 1, 1 });

		TabFolder tabFolder_2 = new TabFolder(sashForm_6, SWT.NONE);

		TabItem tbtmHTr = new TabItem(tabFolder_2, SWT.NONE);
		tbtmHTr.setText("Hỗ trợ");

		Composite composite_6 = new Composite(tabFolder_2, SWT.NONE);
		tbtmHTr.setControl(composite_6);
		composite_6.setLayout(new GridLayout(3, false));

		Label lblTmKim = new Label(composite_6, SWT.NONE);
		lblTmKim.setText("Tìm kiếm:");

		Button btnTenPhuongTien = new Button(composite_6, SWT.CHECK);
		btnTenPhuongTien.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnTenPhuongTien.setText("Tên phương tiện");
		new Label(composite_6, SWT.NONE);

		Button btnBienSo = new Button(composite_6, SWT.CHECK);
		btnBienSo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnBienSo.setText("Biển số");
		new Label(composite_6, SWT.NONE);

		Button btnDongXe = new Button(composite_6, SWT.CHECK);
		btnDongXe.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnDongXe.setText("Dòng xe");
		new Label(composite_6, SWT.NONE);

		Button btnSoKhung = new Button(composite_6, SWT.CHECK);
		btnSoKhung.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnSoKhung.setText("Số khung");
		new Label(composite_6, SWT.NONE);

		Button btnSoMay = new Button(composite_6, SWT.CHECK);
		btnSoMay.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnSoMay.setText("Số máy");
		new Label(composite_6, SWT.NONE);

		Label lblTKha = new Label(composite_6, SWT.NONE);
		lblTKha.setText("Từ khóa: ");

		Combo combo = new Combo(composite_6, SWT.NONE);
		combo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					Sortter s = new Sortter();
					try {
						switch (tableSelected) {
						case 1:
							Mainformfiller.fillDataOto(table_Oto,
									s.SortPTGT(getTaisanPTGT(table_Oto), btnTenPhuongTien.getSelection(),
											btnBienSo.getSelection(), btnDongXe.getSelection(),
											btnSoKhung.getSelection(), btnSoMay.getSelection(), combo.getText(), user));
							break;
						case 2:
							Mainformfiller.fillDataXemay(table_Xemay,
									s.SortPTGT(getTaisanPTGT(table_Xemay), btnTenPhuongTien.getSelection(),
											btnBienSo.getSelection(), btnDongXe.getSelection(),
											btnSoKhung.getSelection(), btnSoMay.getSelection(), combo.getText(), user));
							break;

						default:
							break;
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}

			private ArrayList<TAISAN> getTaisanPTGT(Table table_Oto) {
				ArrayList<TAISAN> rs = new ArrayList<>();
				for (TableItem ti : table_Oto.getItems()) {
					rs.add((TAISAN) ti.getData());
				}
				return rs;
			}
		});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite_6, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label lblLch = new Label(composite_6, SWT.NONE);
		lblLch.setText("Lịch:");

		dateTime_LichCongtac = new DateTime(composite_6, SWT.CALENDAR | SWT.LONG);
		dateTime_LichCongtac.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ArrayList<LICH_CONG_TAC> lctl = controler.getControl_LICH_CONG_TAC()
							.get_LICH_CONG_TAC_list(mdf.getDate(dateTime_LichCongtac));
					fillLichCongtac(lctl);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		dateTime_LichCongtac.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 2, 2));
		new Label(composite_6, SWT.NONE);

		Label lblGhiCh = new Label(composite_6, SWT.NONE);
		lblGhiCh.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblGhiCh.setText("Công việc:");

		table_LichCongtac = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table_LichCongtac.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_LichCongtac.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table_LichCongtac, SWT.NONE);
		tblclmnNewColumn.setWidth(120);
		tblclmnNewColumn.setText("Nội dung công việc");

		Menu menu_16 = new Menu(table_LichCongtac);
		table_LichCongtac.setMenu(menu_16);

		MenuItem mntmXem = new MenuItem(menu_16, SWT.NONE);
		mntmXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table_LichCongtac.getSelection();
				if (ti.length > 0) {
					LICH_CONG_TAC lct = (LICH_CONG_TAC) ti[0].getData();
					XemLichCongtac xlct = new XemLichCongtac(shell, SWT.DIALOG_TRIM, user, lct);
					xlct.open();
				}
				try {
					LoadCongviec();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXem.setText("Xem");

		MenuItem mntmThemCongVic = new MenuItem(menu_16, SWT.NONE);
		mntmThemCongVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				XemLichCongtac xlct = new XemLichCongtac(shell, SWT.DIALOG_TRIM, user, new LICH_CONG_TAC());
				xlct.open();
				try {
					LoadCongviec();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmThemCongVic.setText("Thêm công việc");

		MenuItem mntmXa = new MenuItem(menu_16, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table_LichCongtac.getSelection();
				if (ti.length > 0) {
					LICH_CONG_TAC lct = (LICH_CONG_TAC) ti[0].getData();
					try {
						controler.getControl_LICH_CONG_TAC().delete_LICH_CONG_TAC(lct);
						LoadCongviec();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXa.setText("Xóa");

		TableColumn tblclmnNiDung = new TableColumn(table_LichCongtac, SWT.NONE);
		tblclmnNiDung.setWidth(200);
		tblclmnNiDung.setText("NỘI DUNG");

		TabItem tbtmSaCha = new TabItem(tabFolder_2, SWT.NONE);
		tbtmSaCha.setText("Sửa chữa - bảo dưỡng");

		table_LichsuBaoduong = new Table(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmSaCha.setControl(table_LichsuBaoduong);
		table_LichsuBaoduong.setHeaderVisible(true);
		table_LichsuBaoduong.setLinesVisible(true);

		TableColumn tblclmnStt_3 = new TableColumn(table_LichsuBaoduong, SWT.NONE);
		tblclmnStt_3.setWidth(45);
		tblclmnStt_3.setText("STT");

		TableColumn tblclmnThiGian = new TableColumn(table_LichsuBaoduong, SWT.NONE);
		tblclmnThiGian.setWidth(100);
		tblclmnThiGian.setText("THỜI GIAN");

		TableColumn tblclmnTntBo = new TableColumn(table_LichsuBaoduong, SWT.NONE);
		tblclmnTntBo.setWidth(150);
		tblclmnTntBo.setText("TÊN ĐỢT BẢO DƯỠNG");

		TabItem tbtmLniuXe = new TabItem(tabFolder_2, SWT.NONE);
		tbtmLniuXe.setText("Lện điều xe");

		table_LichSudieuxe = new Table(tabFolder_2, SWT.BORDER | SWT.FULL_SELECTION);
		tbtmLniuXe.setControl(table_LichSudieuxe);
		table_LichSudieuxe.setLinesVisible(true);
		table_LichSudieuxe.setHeaderVisible(true);

		TableColumn tblclmnStt_2 = new TableColumn(table_LichSudieuxe, SWT.NONE);
		tblclmnStt_2.setWidth(45);
		tblclmnStt_2.setText("STT");

		TableColumn tblclmnNgyGi = new TableColumn(table_LichSudieuxe, SWT.NONE);
		tblclmnNgyGi.setWidth(150);
		tblclmnNgyGi.setText("NGÀY GIỜ");

		TableColumn tblclmnNgiLi = new TableColumn(table_LichSudieuxe, SWT.NONE);
		tblclmnNgiLi.setWidth(150);
		tblclmnNgiLi.setText("NGƯỜI LÁI");
		sashForm_6.setWeights(new int[] { 1000, 618 });

		tbtmDanhSachTaisan = new TabItem(tabFolder_Main, SWT.NONE);
		tbtmDanhSachTaisan.setText("Danh sách tài sản");

		SashForm sashForm_5 = new SashForm(tabFolder_Main, SWT.NONE);
		tbtmDanhSachTaisan.setControl(sashForm_5);

		Composite composite_3 = new Composite(sashForm_5, SWT.NONE);
		composite_3.setLayout(new GridLayout(1, false));

		text = new Text(composite_3, SWT.RIGHT | SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					Sortter s = new Sortter();
					Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan,
							s.SortTaisan(row_taisan_mainForm, text.getText()), Selectindex,
							mntmHienthiNamconlai.getSelection(), mntmHienthiGiatriconlai.getSelection());
				}
			}
		});
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 275;
		text.setLayoutData(gd_text);
		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				text.setText("");
			}
		});
		text.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (text.getText().equals("")) {
					text.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
				}
			}
		});
		text.setMessage("Tìm kiếm tài sản");
		tree_DanhsachTaisan = new Tree(composite_3, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
		tree_DanhsachTaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// tree_DanhsachTaisan.setSize(337, 397);
		tree_DanhsachTaisan.setSortDirection(SWT.DOWN);
		tree_DanhsachTaisan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		tree_DanhsachTaisan.setHeaderVisible(true);
		tree_DanhsachTaisan.setLinesVisible(true);
		tree_DanhsachTaisan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_DanhsachTaisan.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							Selectindex = items[0].getText(0);
							Mainformfiller.getData_viewMainForm_Lichsu_Suachua(t.getMA_TAISAN(), table_lichSu_Suachua);
							Mainformfiller.getData_viewMainForm_Lichsu_Chuyengiao_Noibo(t.getMA_TAISAN(),
									table_LichsuChuyengiaoNoibo);
							setData_viewMainForm_InfoTaiSan(t.getMA_TAISAN());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		tsl.setTreeItemHeight(tree_DanhsachTaisan, treeviewHeight);

		Menu menu_11 = new Menu(tree_DanhsachTaisan);
		tree_DanhsachTaisan.setMenu(menu_11);

		MenuItem mntmXemChiTit = new MenuItem(menu_11, SWT.NONE);
		mntmXemChiTit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree_DanhsachTaisan.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							Integer MA_TAISAN = t.getMA_TAISAN();
							pmth.OpenForm_View_TaiSan(MA_TAISAN);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemChiTit.setText("Xem chi tiết");

		MenuItem mntmCpNhtThng = new MenuItem(menu_11, SWT.NONE);
		mntmCpNhtThng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree_DanhsachTaisan.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							Integer MA_TAISAN = t.getMA_TAISAN();
							pmth.OpenForm_Edit_TaiSan(MA_TAISAN);
						}

					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmCpNhtThng.setText("Sửa đổi thông tin");

		MenuItem mntmCapNhatPhuong = new MenuItem(menu_11, SWT.NONE);
		mntmCapNhatPhuong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree_DanhsachTaisan.getSelection();
					if (items.length > 0) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							PHUONGTIEN_GIAOTHONG pg;
							pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
									.get_PHUONGTIEN_GIAOTHONG_FromTaisan(t.getMA_TAISAN());
							if (pg == null)
								pmth.OpenForm_Insert_PHUONGTIEN_GIAOTHONG(t);
							else
								pmth.OpenForm_Edit_PHUONGTIEN_GIAOTHONG(t);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmCapNhatPhuong.setText("Cập nhật Phương tiện giao thông");

		MenuItem mntmXoa = new MenuItem(menu_11, SWT.NONE);
		mntmXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox messageBox_Thongbao = new MessageBox(shell,
						SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CLOSE);
				messageBox_Thongbao.setText("Xóa");
				messageBox_Thongbao.setMessage("Xóa Thông tin Phương tiện tài sản đã chọn?");
				int rc = messageBox_Thongbao.open();
				switch (rc) {
				case SWT.OK:
					break;
				case SWT.CANCEL:
					break;
				case SWT.YES:
					try {
						TreeItem[] select = tree_DanhsachTaisan.getSelection();
						for (TreeItem tri : select) {
							TAISAN t = (TAISAN) tri.getData();
							controler.getControl_TAISAN().delete_TAISAN(t);
						}
						row_taisan_mainForm = getdata_LoaiTaisan();
						Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm,
								Selectindex, mntmHienthiNamconlai.getSelection(),
								mntmHienthiGiatriconlai.getSelection());
					} catch (SQLException e1) {
						log.error(e1.getMessage());
						e1.printStackTrace();
					}
					break;
				case SWT.NO:
					break;
				case SWT.RETRY:
					break;
				case SWT.ABORT:
					break;
				case SWT.IGNORE:
					break;
				}
			}
		});
		mntmXoa.setText("Xóa thông tin tài sản");

		menuItem_SEPARATOR1 = new MenuItem(menu_11, SWT.SEPARATOR);

		mntmHienthiNamconlai = new MenuItem(menu_11, SWT.CHECK);
		mntmHienthiNamconlai.setText("Hiển thị Năm sử dụng còn lại");

		mntmHienthiGiatriconlai = new MenuItem(menu_11, SWT.CHECK);
		mntmHienthiGiatriconlai.setText("Hiển thị giá trị còn lại");

		new MenuItem(menu_11, SWT.SEPARATOR);

		MenuItem mntmHSMua = new MenuItem(menu_11, SWT.NONE);
		mntmHSMua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] select = tree_DanhsachTaisan.getSelection();
				if (select.length > 0) {
					TAISAN t = (TAISAN) select[0].getData();
					DOT_THUCHIEN_TANG_TAISAN dtt_last;
					try {
						dtt_last = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
								.get_DotTangTaisan_Gannhat(t.getMA_TAISAN());
						if (dtt_last != null) {
							pmth.OpenForm_HosoMuasam(dtt_last);
						} else {
							DefaultBoxMessage dbm = new DefaultBoxMessage();
							dbm.Notification("Dữ liệu trống",
									"Không có dữ liệu mua sắm, tiếp nhận PTTS trong hệ thống!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		mntmHSMua.setText("Hồ sơ mua sắm");

		MenuItem mntmHSChuyn_1 = new MenuItem(menu_11, SWT.NONE);
		mntmHSChuyn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TreeItem[] select = tree_DanhsachTaisan.getSelection();
				if (select.length > 0) {
					TAISAN t = (TAISAN) select[0].getData();
					pmth.OpenForm_View_Hoso_Chuyengiao_TaiSan_Noibo(t);
				}
			}
		});
		mntmHSChuyn_1.setText("Hồ sơ  chuyển giao Nội bộ");

		MenuItem mntmChuynTiSn = new MenuItem(menu_11, SWT.NONE);
		mntmChuynTiSn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree_DanhsachTaisan.getSelection();

				if (items.length > 0) {
					ArrayList<TAISAN> taisan_list = new ArrayList<>();
					for (TreeItem o : items) {
						TAISAN t = (TAISAN) o.getData();
						taisan_list.add(t);
					}
					if (taisan_list != null) {
						// pmth.OpenForm_Delete_TaiSan_list(shell.getDisplay(),
						// taisan_list);
					}

				}
			}
		});
		mntmChuynTiSn.setText("Thanh lý tài sản");

		menuItem_SEPARATOR3 = new MenuItem(menu_11, SWT.SEPARATOR);

		MenuItem mntmXuata = new MenuItem(menu_11, SWT.NONE);
		mntmXuata.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					pmth.OpenForm_Export_Taisan(row_taisan_mainForm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmXuata.setText("Xuất dữ liệu Excel");

		ExpandBar expandBar_2 = new ExpandBar(sashForm_5, SWT.V_SCROLL);
		expandBar_2.addExpandListener(new ExpandAdapter() {
			@Override
			public void itemExpanded(ExpandEvent e) {
				ExpandItem min = null;
				for (ExpandItem it : expandBar_2.getItems()) {
					if (!it.equals(e.item)) {
						it.setExpanded(false);
						min = it;
					}
				}
				if (min != null) {
					((ExpandItem) e.item).setHeight((int) (expandBar_2.getSize().y - (min.getHeaderHeight() * 4.8)));
				}
			}
		});
		expandBar_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));

		ExpandItem xpndtmThongKe = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmThongKe.setExpanded(true);
		xpndtmThongKe.setText("Thống kê");

		ThongtinThongkeDanhsachTaisan = new Composite(expandBar_2, SWT.NONE);
		xpndtmThongKe.setControl(ThongtinThongkeDanhsachTaisan);
		xpndtmThongKe.setHeight(340);

		ExpandItem xpndtmLchSSa = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmLchSSa.setText("Lịch sử sửa chữa - bảo dưỡng");

		Composite composite_5 = new Composite(expandBar_2, SWT.NONE);
		xpndtmLchSSa.setControl(composite_5);
		GridLayout gl_composite_5 = new GridLayout(2, false);
		gl_composite_5.marginWidth = 0;
		gl_composite_5.marginTop = 1;
		gl_composite_5.verticalSpacing = 0;
		gl_composite_5.marginHeight = 0;
		gl_composite_5.horizontalSpacing = 0;
		composite_5.setLayout(gl_composite_5);

		text_1 = new Text(composite_5, SWT.BORDER | SWT.RIGHT);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		text_1.setSize(329, 21);
		text_1.setMessage("Lịch sử sữa chữa nâng cấp");
		text_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));

		TableViewer tableViewer_1 = new TableViewer(composite_5, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table_lichSu_Suachua = tableViewer_1.getTable();
		table_lichSu_Suachua.setLinesVisible(true);
		table_lichSu_Suachua.setHeaderVisible(true);
		GridData gd_table_lichSu_Congviec = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_table_lichSu_Congviec.verticalIndent = 5;
		gd_table_lichSu_Congviec.heightHint = 134;
		table_lichSu_Suachua.setLayoutData(gd_table_lichSu_Congviec);
		table_lichSu_Suachua.setSize(421, 197);

		TableColumn tblclmnSTT = new TableColumn(table_lichSu_Suachua, SWT.CENTER);
		tblclmnSTT.setWidth(45);
		tblclmnSTT.setText("Stt");

		TableColumn tblclmnNgyBtu = new TableColumn(table_lichSu_Suachua, SWT.LEFT);
		tblclmnNgyBtu.setWidth(180);
		tblclmnNgyBtu.setText("Tên Đợt Sửa chữa - Bảo dưỡng");

		TableColumn tblclmnTnhTrng = new TableColumn(table_lichSu_Suachua, SWT.CENTER);
		tblclmnTnhTrng.setWidth(120);
		tblclmnTnhTrng.setText("Ngày bắt đầu");

		TableColumn tblclmnNgyKtThc = new TableColumn(table_lichSu_Suachua, SWT.CENTER);
		tblclmnNgyKtThc.setWidth(120);
		tblclmnNgyKtThc.setText("Ngày kết thúc");
		xpndtmLchSSa.setHeight(xpndtmLchSSa.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		ExpandItem xpndtmLchSChuyn = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmLchSChuyn.setText("Lịch sử Chuyển giao Nội bộ");

		table_LichsuChuyengiaoNoibo = new Table(expandBar_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		xpndtmLchSChuyn.setControl(table_LichsuChuyengiaoNoibo);
		table_LichsuChuyengiaoNoibo.setHeaderVisible(true);
		table_LichsuChuyengiaoNoibo.setLinesVisible(true);
		xpndtmLchSChuyn.setHeight(100);

		TableColumn tblclmnNgyThng = new TableColumn(table_LichsuChuyengiaoNoibo, SWT.NONE);
		tblclmnNgyThng.setWidth(100);
		tblclmnNgyThng.setText("Ngày tháng");

		TableColumn tblclmnBnGiao = new TableColumn(table_LichsuChuyengiaoNoibo, SWT.NONE);
		tblclmnBnGiao.setWidth(100);
		tblclmnBnGiao.setText("Bên giao");

		TableColumn tblclmnBnNhn = new TableColumn(table_LichsuChuyengiaoNoibo, SWT.NONE);
		tblclmnBnNhn.setWidth(100);
		tblclmnBnNhn.setText("Bên nhận");

		Menu menu_17 = new Menu(table_LichsuChuyengiaoNoibo);
		table_LichsuChuyengiaoNoibo.setMenu(menu_17);

		MenuItem mntmXemHS = new MenuItem(menu_17, SWT.NONE);
		mntmXemHS.setText("Xem hồ sơ");
		mntmXemHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table_LichsuChuyengiaoNoibo.getSelection();
				if (ti.length > 0) {
					DOT_BANGIAO_TAISAN_NOIBO dbtn = (DOT_BANGIAO_TAISAN_NOIBO) ti[0].getData();
					if (dbtn == null)
						return;
					TAP_HO_SO ths = null;
					try {
						ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dbtn.getMA_TAPHOSO());
						if (ths == null)
							return;
						TapHoso_View thsv = new TapHoso_View(shell, SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		ExpandItem xpndtmChiTitThng = new ExpandItem(expandBar_2, SWT.NONE);
		xpndtmChiTitThng.setText("Chi tiết thông tin tài sản");

		Composite composite = new Composite(expandBar_2, SWT.NONE);
		xpndtmChiTitThng.setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		Label lblnVQun = new Label(composite, SWT.NONE);
		GridData gd_lblnVQun = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblnVQun.verticalIndent = 5;
		lblnVQun.setLayoutData(gd_lblnVQun);
		lblnVQun.setText("Nguồn gốc:");

		text_NguonGocTaiSan = new Text(composite, SWT.NONE);
		text_NguonGocTaiSan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NguonGocTaiSan.setEditable(false);
		GridData gd_text_NguonGocTaiSan = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_NguonGocTaiSan.verticalIndent = 5;
		gd_text_NguonGocTaiSan.widthHint = 120;
		text_NguonGocTaiSan.setLayoutData(gd_text_NguonGocTaiSan);

		Label lblTrngThi = new Label(composite, SWT.NONE);
		lblTrngThi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTrngThi.setText("Lý do tăng:");

		text_LyDotang = new Text(composite, SWT.NONE);
		text_LyDotang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_LyDotang.setEditable(false);
		text_LyDotang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVS_1 = new Label(composite, SWT.NONE);
		lblnVS_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblnVS_1.setText("Đơn vị sử dụng:");

		text_DonViSuDung = new Text(composite, SWT.NONE);
		text_DonViSuDung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_DonViSuDung.setEditable(false);
		text_DonViSuDung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVQun_1 = new Label(composite, SWT.NONE);
		lblnVQun_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblnVQun_1.setText("Đơn vị quản lý:");

		text_DonViQuanLy = new Text(composite, SWT.NONE);
		text_DonViQuanLy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_DonViQuanLy.setEditable(false);
		text_DonViQuanLy.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label lblTnTiSn = new Label(composite, SWT.NONE);
		lblTnTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnTiSn.setText("Tên tài sản:");

		text_TenTaiSan = new Text(composite, SWT.NONE);
		text_TenTaiSan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_TenTaiSan.setEditable(false);
		text_TenTaiSan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMTiSn = new Label(composite, SWT.NONE);
		lblMTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMTiSn.setText("Mã tài sản:");

		text_MaTaiSan = new Text(composite, SWT.NONE);
		text_MaTaiSan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_MaTaiSan.setEditable(false);
		text_MaTaiSan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblModel = new Label(composite, SWT.NONE);
		lblModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModel.setText("Model:");

		text_Model = new Text(composite, SWT.NONE);
		text_Model.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_Model.setEditable(false);
		text_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSerial = new Label(composite, SWT.NONE);
		lblSerial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSerial.setText("Serial:");

		text_Serial = new Text(composite, SWT.NONE);
		text_Serial.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_Serial.setEditable(false);
		text_Serial.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgySDng = new Label(composite, SWT.NONE);
		lblNgySDng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgySDng.setText("Ngày sử dụng:");

		text_NgaySudung = new Text(composite, SWT.NONE);
		text_NgaySudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NgaySudung.setEditable(false);
		text_NgaySudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblXutX = new Label(composite, SWT.NONE);
		lblXutX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblXutX.setText("Xuất xứ:");

		text_XuatXu = new Text(composite, SWT.NONE);
		text_XuatXu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_XuatXu.setEditable(false);
		text_XuatXu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNguynGi = new Label(composite, SWT.NONE);
		lblNguynGi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNguynGi.setText("Nguyên giá:");

		text_NguyenGia = new Text(composite, SWT.NONE);
		text_NguyenGia.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_NguyenGia.setEditable(false);
		text_NguyenGia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVTnh = new Label(composite, SWT.NONE);
		lblnVTnh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblnVTnh.setText("Đơn vị tính:");

		text_DonViTinh = new Text(composite, SWT.NONE);
		text_DonViTinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_DonViTinh.setEditable(false);
		text_DonViTinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnhTrng = new Label(composite, SWT.NONE);
		lblTnhTrng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnhTrng.setText("Tình trạng:");

		text_TinhTrangTaiSan = new Text(composite, SWT.NONE);
		text_TinhTrangTaiSan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_TinhTrangTaiSan.setEditable(false);
		text_TinhTrangTaiSan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblBoHnh = new Label(composite, SWT.NONE);
		lblBoHnh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBoHnh.setText("Bảo hành:");

		text_BaoHanh = new Text(composite, SWT.NONE);
		text_BaoHanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_BaoHanh.setEditable(false);
		text_BaoHanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		Label lblNhmCnh = new Label(composite, SWT.NONE);
		lblNhmCnh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNhmCnh.setText("Mã PTGT:");

		text_MaPTGT = new Text(composite, SWT.NONE);
		text_MaPTGT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_MaPTGT.setEditable(false);
		text_MaPTGT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Phụ kiện:");

		table_PhuKien = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_PhuKien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_PhuKien.setHeaderVisible(true);
		table_PhuKien.setLinesVisible(true);

		TableColumn t1 = new TableColumn(table_PhuKien, SWT.NONE);
		t1.setText("Mã phụ kiện");
		t1.setWidth(100);
		TableColumn t2 = new TableColumn(table_PhuKien, SWT.NONE);
		t2.setText("Tên phụ kiện");
		t2.setWidth(120);
		TableColumn t3 = new TableColumn(table_PhuKien, SWT.NONE);
		t3.setText("Model");
		t3.setWidth(100);
		TableColumn t4 = new TableColumn(table_PhuKien, SWT.NONE);
		t4.setText("Seri");
		t4.setWidth(100);
		TableColumn t5 = new TableColumn(table_PhuKien, SWT.NONE);
		t5.setText("Nguyên giá");
		t5.setWidth(100);

		new Label(composite, SWT.NONE);

		Button btnHS = new Button(composite, SWT.NONE);
		btnHS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnHS.setText("Hồ sơ");
		xpndtmChiTitThng.setHeight(xpndtmChiTitThng.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		sashForm_5.setWeights(new int[] { 2, 1 });

		tbtmViecDangTrienKhai = new TabItem(tabFolder_Main, SWT.NONE);
		tbtmViecDangTrienKhai.setText("Công việc đang triển khai");

		SashForm sashForm_2 = new SashForm(tabFolder_Main, SWT.NONE);
		tbtmViecDangTrienKhai.setControl(sashForm_2);

		SashForm sashForm_4 = new SashForm(sashForm_2, SWT.VERTICAL);

		tree_CongviecDangtrienkhai = new Tree(sashForm_4, SWT.BORDER | SWT.FULL_SELECTION);
		tree_CongviecDangtrienkhai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem ti[] = tree_CongviecDangtrienkhai.getSelection();
				if (ti.length > 0) {
					Object o = ti[0].getData();
					try {
						Mainformfiller.fillPartCongviec(o, tree_Dexuat, tree_Thuchien, tree_Nghiemthu, tree_Quyettoan);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		tree_CongviecDangtrienkhai.setHeaderVisible(true);
		tree_CongviecDangtrienkhai.setLinesVisible(true);

		TreeColumn trclmnStt = new TreeColumn(tree_CongviecDangtrienkhai, SWT.CENTER);
		trclmnStt.setWidth(55);
		trclmnStt.setText("STT");

		TreeColumn trclmnLoaicongviec = new TreeColumn(tree_CongviecDangtrienkhai, SWT.CENTER);
		trclmnLoaicongviec.setWidth(250);
		trclmnLoaicongviec.setText("LOẠI CÔNG VIỆC");

		TreeColumn trclmnNgayBatdau = new TreeColumn(tree_CongviecDangtrienkhai, SWT.NONE);
		trclmnNgayBatdau.setWidth(150);
		trclmnNgayBatdau.setText("NGÀY BẮT ĐẦU");
		sashForm_4.setWeights(new int[] { 160 });

		SashForm sashForm_3 = new SashForm(sashForm_2, SWT.VERTICAL);

		tree_Dexuat = new Tree(sashForm_3, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Dexuat.setHeaderVisible(true);

		TreeColumn trclmnTnhTrng = new TreeColumn(tree_Dexuat, SWT.NONE);
		trclmnTnhTrng.setWidth(100);
		trclmnTnhTrng.setText("Đề xuất");

		TreeColumn trclmnNgiThcHin = new TreeColumn(tree_Dexuat, SWT.NONE);
		trclmnNgiThcHin.setWidth(100);
		trclmnNgiThcHin.setText("Người thực hiện");

		TreeColumn trclmnNgyBtu = new TreeColumn(tree_Dexuat, SWT.NONE);
		trclmnNgyBtu.setWidth(100);
		trclmnNgyBtu.setText("Ngày bắt đầu");

		TreeColumn trclmnDKin = new TreeColumn(tree_Dexuat, SWT.NONE);
		trclmnDKin.setWidth(100);
		trclmnDKin.setText("Ngày giao");

		TreeColumn trclmnNgyKtThc = new TreeColumn(tree_Dexuat, SWT.NONE);
		trclmnNgyKtThc.setWidth(100);
		trclmnNgyKtThc.setText("Ngày kết thúc");

		tree_Thuchien = new Tree(sashForm_3, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Thuchien.setHeaderVisible(true);

		TreeColumn trclmnThcHin = new TreeColumn(tree_Thuchien, SWT.NONE);
		trclmnThcHin.setWidth(100);
		trclmnThcHin.setText("Thực hiện");

		TreeColumn treeColumn_1 = new TreeColumn(tree_Thuchien, SWT.NONE);
		treeColumn_1.setWidth(100);
		treeColumn_1.setText("Người thực hiện");

		TreeColumn treeColumn_2 = new TreeColumn(tree_Thuchien, SWT.NONE);
		treeColumn_2.setWidth(100);
		treeColumn_2.setText("Ngày bắt đầu");

		TreeColumn treeColumn_3 = new TreeColumn(tree_Thuchien, SWT.NONE);
		treeColumn_3.setWidth(100);
		treeColumn_3.setText("Dự kiến");

		TreeColumn treeColumn_4 = new TreeColumn(tree_Thuchien, SWT.NONE);
		treeColumn_4.setWidth(100);
		treeColumn_4.setText("Ngày kết thúc");

		tree_Nghiemthu = new Tree(sashForm_3, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Nghiemthu.setHeaderVisible(true);

		TreeColumn trclmnNghimThu = new TreeColumn(tree_Nghiemthu, SWT.NONE);
		trclmnNghimThu.setWidth(100);
		trclmnNghimThu.setText("Nghiệm thu");

		TreeColumn treeColumn_6 = new TreeColumn(tree_Nghiemthu, SWT.NONE);
		treeColumn_6.setWidth(100);
		treeColumn_6.setText("Người thực hiện");

		TreeColumn treeColumn_7 = new TreeColumn(tree_Nghiemthu, SWT.NONE);
		treeColumn_7.setWidth(100);
		treeColumn_7.setText("Ngày bắt đầu");

		TreeColumn treeColumn_8 = new TreeColumn(tree_Nghiemthu, SWT.NONE);
		treeColumn_8.setWidth(100);
		treeColumn_8.setText("Dự kiến");

		TreeColumn treeColumn_9 = new TreeColumn(tree_Nghiemthu, SWT.NONE);
		treeColumn_9.setWidth(100);
		treeColumn_9.setText("Ngày kết thúc");

		tree_Quyettoan = new Tree(sashForm_3, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Quyettoan.setHeaderVisible(true);

		TreeColumn trclmnQuytTon = new TreeColumn(tree_Quyettoan, SWT.NONE);
		trclmnQuytTon.setWidth(100);
		trclmnQuytTon.setText("Quyết toán");

		TreeColumn treeColumn_11 = new TreeColumn(tree_Quyettoan, SWT.NONE);
		treeColumn_11.setWidth(100);
		treeColumn_11.setText("Người thực hiện");

		TreeColumn treeColumn_12 = new TreeColumn(tree_Quyettoan, SWT.NONE);
		treeColumn_12.setWidth(100);
		treeColumn_12.setText("Ngày bắt đầu");

		TreeColumn treeColumn_13 = new TreeColumn(tree_Quyettoan, SWT.NONE);
		treeColumn_13.setWidth(100);
		treeColumn_13.setText("Dự kiến");

		TreeColumn treeColumn_14 = new TreeColumn(tree_Quyettoan, SWT.NONE);
		treeColumn_14.setWidth(100);
		treeColumn_14.setText("Ngày kết thúc");
		sashForm_3.setWeights(new int[] { 1, 1, 1, 1 });
		sashForm_2.setWeights(new int[] { 330, 348 });

		tbtmHosoGanday = new TabItem(tabFolder_Main, SWT.NONE);
		tbtmHosoGanday.setText("Hồ sơ gần đây");

		TableViewer tableViewer_2 = new TableViewer(tabFolder_Main, SWT.BORDER | SWT.FULL_SELECTION);
		table_HosoGanday = tableViewer_2.getTable();
		table_HosoGanday.setLinesVisible(true);
		tbtmHosoGanday.setControl(table_HosoGanday);
		table_HosoGanday.setHeaderVisible(true);

		TableColumn tblclmnStt_4 = new TableColumn(table_HosoGanday, SWT.NONE);
		tblclmnStt_4.setWidth(45);
		tblclmnStt_4.setText("STT");

		TableColumn tblclmnNgyTo = new TableColumn(table_HosoGanday, SWT.NONE);
		tblclmnNgyTo.setWidth(150);
		tblclmnNgyTo.setText("NGÀY TẠO");

		TableColumn tblclmnTnHS = new TableColumn(table_HosoGanday, SWT.NONE);
		tblclmnTnHS.setWidth(300);
		tblclmnTnHS.setText("TÊN HỒ SƠ");

		TableColumn tblclmnNgiThcHin = new TableColumn(table_HosoGanday, SWT.NONE);
		tblclmnNgiThcHin.setWidth(300);
		tblclmnNgiThcHin.setText("GIỚI THIỆU");

		Menu menu_18 = new Menu(table_HosoGanday);
		table_HosoGanday.setMenu(menu_18);

		MenuItem mntmXemHS_1 = new MenuItem(menu_18, SWT.NONE);
		mntmXemHS_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table_HosoGanday.getSelection();
				if (ti.length > 0) {
					TAP_HO_SO tap_HO_SO = (TAP_HO_SO) ti[0].getData();
					if (tap_HO_SO == null)
						return;
					TapHoso_View thsv = new TapHoso_View(shell, SWT.DIALOG_TRIM, user, tap_HO_SO, true);
					try {
						thsv.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXemHS_1.setText("Xem Hồ sơ");

		tbtmThongKeTinhhinhPTTS = new TabItem(tabFolder_Main, SWT.NONE);
		tbtmThongKeTinhhinhPTTS.setText("Thống kê tình hình PTTS");

		SashForm sashForm_8 = new SashForm(tabFolder_Main, SWT.VERTICAL);
		tbtmThongKeTinhhinhPTTS.setControl(sashForm_8);

		ExpandBar expandBar_1 = new ExpandBar(sashForm_8, SWT.NONE);
		expandBar_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		expandBar_1.addExpandListener(new ExpandAdapter() {
			@Override
			public void itemExpanded(ExpandEvent e) {
				// computeSize(SWT.DEFAULT, SWT.DEFAULT).y
				ExpandItem min = null;
				for (ExpandItem it : expandBar_1.getItems()) {
					if (!it.equals(e.item)) {
						it.setExpanded(false);
						min = it;
					}
				}
				if (min != null) {
					((ExpandItem) e.item).setHeight(expandBar_1.getSize().y - (min.getHeaderHeight() * 5));
				}
			}

			@Override
			public void itemCollapsed(ExpandEvent e) {
			}
		});
		ExpandItem xpndtmMuaSm = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmMuaSm.setText("Mua sắm");

		Composite grpMuaSm = new Composite(expandBar_1, SWT.NONE);
		xpndtmMuaSm.setControl(grpMuaSm);
		grpMuaSm.setLayout(new GridLayout(4, false));

		Label lblTNgy = new Label(grpMuaSm, SWT.NONE);
		lblTNgy.setText("Từ ngày:");

		DateTime dateTime = new DateTime(grpMuaSm, SWT.BORDER);

		Label lblnNgy = new Label(grpMuaSm, SWT.NONE);
		lblnNgy.setBounds(0, 0, 55, 15);
		lblnNgy.setText("Đến ngày: ");

		DateTime dateTime_1 = new DateTime(grpMuaSm, SWT.BORDER);

		Label lblStMua = new Label(grpMuaSm, SWT.NONE);
		lblStMua.setText("Số đợt mua sắm:");

		text_2 = new Text(grpMuaSm, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		Label lblTngSPtts = new Label(grpMuaSm, SWT.NONE);
		lblTngSPtts.setText("Tổng số Ptts tham gia: ");

		text_5 = new Text(grpMuaSm, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		xpndtmMuaSm.setHeight(xpndtmMuaSm.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		ExpandItem xpndtmBoDng = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmBoDng.setText("Bảo dưỡng");

		Composite grpBoDng = new Composite(expandBar_1, SWT.NONE);
		xpndtmBoDng.setControl(grpBoDng);
		xpndtmBoDng.setHeight(xpndtmBoDng.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		ExpandItem xpndtmSaCha = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmSaCha.setText("Sửa chữa");

		Composite grpSaCha = new Composite(expandBar_1, SWT.NONE);
		xpndtmSaCha.setControl(grpSaCha);
		xpndtmSaCha.setHeight(xpndtmSaCha.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		ExpandItem xpndtmThanhL = new ExpandItem(expandBar_1, SWT.NONE);
		xpndtmThanhL.setText("Thanh lý");

		Composite grpThanhL = new Composite(expandBar_1, SWT.NONE);
		xpndtmThanhL.setControl(grpThanhL);
		xpndtmThanhL.setHeight(xpndtmThanhL.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		sashForm_8.setWeights(new int[] { 100 });

		Menu menu_ShowMainTab = new Menu(tabFolder_Main);
		tabFolder_Main.setMenu(menu_ShowMainTab);

		MenuItem mntmPhuongtienGiaothong = new MenuItem(menu_ShowMainTab, SWT.CHECK);
		mntmPhuongtienGiaothong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmPhuongtienGiaothong.getSelection()) {
					tbtmPhuongtienvantai = new TabItem(tabFolder_Main, SWT.NONE, 0);
					tbtmPhuongtienvantai.setText("Phương tiện Giao thông");
					tbtmPhuongtienvantai.setControl(sashForm_6);
					tabFolder_Main.setSelection(0);
				} else {
					tbtmPhuongtienvantai.dispose();
				}
			}
		});
		mntmPhuongtienGiaothong.setSelection(true);
		mntmPhuongtienGiaothong.setText("Phương tiện giao thông");

		MenuItem mntmDanhsachTaisan = new MenuItem(menu_ShowMainTab, SWT.CHECK);
		mntmDanhsachTaisan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmDanhsachTaisan.getSelection()) {
					int count = tabFolder_Main.getItemCount();
					if (count == 0) {
						tbtmDanhSachTaisan = new TabItem(tabFolder_Main, SWT.NONE, 0);
						tabFolder_Main.setSelection(0);
					} else if (count >= 1) {
						tbtmDanhSachTaisan = new TabItem(tabFolder_Main, SWT.NONE, 1);
						tabFolder_Main.setSelection(1);
					} else {
						tbtmDanhSachTaisan = new TabItem(tabFolder_Main, SWT.NONE, count - 1);
						tabFolder_Main.setSelection(count - 1);
					}
					tbtmDanhSachTaisan.setText("Danh sách tài sản");
					tbtmDanhSachTaisan.setControl(sashForm_5);
				} else {
					tbtmDanhSachTaisan.dispose();
				}
			}
		});
		mntmDanhsachTaisan.setSelection(true);
		mntmDanhsachTaisan.setText("Danh sách tài sản");

		MenuItem mntmCongviecDangtrienkhai = new MenuItem(menu_ShowMainTab, SWT.CHECK);
		mntmCongviecDangtrienkhai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmCongviecDangtrienkhai.getSelection()) {
					int count = tabFolder_Main.getItemCount();
					if (count == 0) {
						tbtmViecDangTrienKhai = new TabItem(tabFolder_Main, SWT.NONE, 0);
						tabFolder_Main.setSelection(0);
					} else if (count >= 2) {
						tbtmViecDangTrienKhai = new TabItem(tabFolder_Main, SWT.NONE, 2);
						tabFolder_Main.setSelection(2);
					} else {
						tbtmViecDangTrienKhai = new TabItem(tabFolder_Main, SWT.NONE, count - 1);
						tabFolder_Main.setSelection(count - 1);
					}
					tbtmViecDangTrienKhai.setText("Công việc đang triển khai");
					tbtmViecDangTrienKhai.setControl(sashForm_2);
				} else {
					tbtmViecDangTrienKhai.dispose();
				}
			}
		});
		mntmCongviecDangtrienkhai.setSelection(true);
		mntmCongviecDangtrienkhai.setText("Công việc đang triển khai");

		MenuItem mntmHosoGanday = new MenuItem(menu_ShowMainTab, SWT.CHECK);
		mntmHosoGanday.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmHosoGanday.getSelection()) {
					int count = tabFolder_Main.getItemCount();
					if (count == 0) {
						tbtmHosoGanday = new TabItem(tabFolder_Main, SWT.NONE, 0);
						tabFolder_Main.setSelection(0);
					} else if (count >= 3) {
						tbtmHosoGanday = new TabItem(tabFolder_Main, SWT.NONE, 3);
						tabFolder_Main.setSelection(3);
					} else {
						tbtmHosoGanday = new TabItem(tabFolder_Main, SWT.NONE, count - 1);
						tabFolder_Main.setSelection(count - 1);
					}
					tbtmHosoGanday.setText("Hồ sơ gần đây");
					tbtmHosoGanday.setControl(table_HosoGanday);
				} else {
					tbtmHosoGanday.dispose();
				}
			}
		});
		mntmHosoGanday.setSelection(true);
		mntmHosoGanday.setText("Hồ sơ gần đây");

		MenuItem mntmThongkeTinhhinhPTTS = new MenuItem(menu_ShowMainTab, SWT.CHECK);
		mntmThongkeTinhhinhPTTS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (mntmThongkeTinhhinhPTTS.getSelection()) {
					int count = tabFolder_Main.getItemCount();
					if (count == 0) {
						tbtmThongKeTinhhinhPTTS = new TabItem(tabFolder_Main, SWT.NONE, 0);
						tabFolder_Main.setSelection(0);
					} else if (count >= 4) {
						tbtmThongKeTinhhinhPTTS = new TabItem(tabFolder_Main, SWT.NONE, 4);
						tabFolder_Main.setSelection(4);
					} else {
						tbtmThongKeTinhhinhPTTS = new TabItem(tabFolder_Main, SWT.NONE, count - 1);
						tabFolder_Main.setSelection(count - 1);
					}
					tbtmThongKeTinhhinhPTTS.setText("Thống kê tình hình PTTS");
					tbtmThongKeTinhhinhPTTS.setControl(sashForm_8);
				} else {
					tbtmThongKeTinhhinhPTTS.dispose();
				}
			}
		});
		mntmThongkeTinhhinhPTTS.setSelection(true);
		mntmThongkeTinhhinhPTTS.setText("Thống kê Tình hình PTTS");

		TableColumn[] columns = table_muasamganday.getColumns();
		for (int i = 0, n = columns.length; i < n; i++) {
			columns[i].pack();
		}
		sashForm_1.setWeights(new int[] { 202, 689 });
		init(w);
	}

	private void init(Wait w) throws SQLException {
		new TreeTemplate(user).getTreePHONGBAN(Tree_DonViSuDung);
		N162 = new Nhomtaisan_162(controler, icondata);
		N162.setTree_NHOMTaisan_Codinh(tree_NHOMTaisan_Codinh_huuhinh);
		N162.setTree_NhomTaisan_Codinh_dacbiet(tree_NhomTaisan_Codinh_dacbiet);
		N162.setTree_Nhomtaisan_Codinh_Dacthu(tree_Nhomtaisan_Codinh_Dacthu);
		N162.setTree_NhomTaisan_Codinh_Vohinh(tree_NhomTaisan_Codinh_Vohinh);
		N162.initTongitem();
		N162.initItem_NhomTaisan_Codinh(true);
		N162.setItem_NhomTaisan_Codinh_Vohinh();
		N162.setItem_NhomTaisan_Codinh_Dacthu();
		N162.setItem_NhomTaisan_Codinh_Dacbiet();
		L_19BCA = new Loaitaisan_19BCA(tree_LoaiTaisan, controler, icondata);
		L_19BCA.setItem_LoaiTaisan();
		Mainformfiller = new Fill_MainForm(user, shell);
		Mainformfiller.fillDataXemay(table_Xemay, getdata_Xemay());
		Mainformfiller.fillDataOto(table_Oto, getdata_Oto());
		Mainformfiller.fillDataHosoGanday(table_HosoGanday);
		Mainformfiller.fillDataCongviecDangThuchien(tree_CongviecDangtrienkhai);
		// Show Data TaiSan MainForm
		row_taisan_mainForm = getdata_LoaiTaisan();
		Mainformfiller.loadData_ViewTaiSan_MainForm(tree_DanhsachTaisan, row_taisan_mainForm, Selectindex,
				mntmHienthiNamconlai.getSelection(), mntmHienthiGiatriconlai.getSelection());
		if (w != null) {
			w.dispose();
			Drager dg = new Drager(tree_DanhsachTaisan);
			MainTableBuilder tcs = new MainTableBuilder(tree_DanhsachTaisan, Mainformfiller, mntmHienthiNamconlai,
					mntmHienthiGiatriconlai);
			Droper NhomTaisan = new Droper(tree_NHOMTaisan_Codinh_huuhinh, mh);
			ThongkeDanhsachPhuongtienTaisan tkdsptts = new ThongkeDanhsachPhuongtienTaisan(
					ThongtinThongkeDanhsachTaisan, controler);
			Mainformfiller.setThongkeDanhsach(tkdsptts);
			NhomTaisan.setListennerNhomTaisan_Codinh_Huuhinh();
			Droper NhomTaisan_CodinhVohinh = new Droper(tree_NhomTaisan_Codinh_Vohinh, mh);
			NhomTaisan_CodinhVohinh.setListennerNhomTaisan_Codinh_Vohinh();
			Droper NhomTaisan_CodinhDacthu = new Droper(tree_Nhomtaisan_Codinh_Dacthu, mh);
			NhomTaisan_CodinhDacthu.setListennerNhomTaisan_Codinh_Dacthu();
			Droper NhomTaisan_CodinhDacbiet = new Droper(tree_NhomTaisan_Codinh_dacbiet, mh);
			NhomTaisan_CodinhDacbiet.setListennerNhomTaisan_Codinh_Dacbiet();
			Droper LoaiTaisan = new Droper(tree_LoaiTaisan, mh);
			LoaiTaisan.setListennerLoaiTaisan();
			Droper Phongban = new Droper(Tree_DonViSuDung, mh);
			Phongban.SetListennerPhongban();
			dg.setTranfer(NhomTaisan.getTypes_Data());
			dg.setTranfer(NhomTaisan_CodinhVohinh.getTypes_Data());
			dg.setTranfer(NhomTaisan_CodinhDacthu.getTypes_Data());
			dg.setTranfer(NhomTaisan_CodinhDacbiet.getTypes_Data());
			dg.setTranfer(LoaiTaisan.getTypes_Data());
			dg.setTranfer(Phongban.getTypes_Data());
			LoadCongviec();
		}
	}

	private void LoadCongviec() throws SQLException {
		ArrayList<LICH_CONG_TAC> lctl = controler.getControl_LICH_CONG_TAC()
				.get_LICH_CONG_TAC_list(mdf.getDate(dateTime_LichCongtac));
		fillLichCongtac(lctl);
	}

	private void fillLichCongtac(ArrayList<LICH_CONG_TAC> lctl) {
		table_LichCongtac.removeAll();
		if (lctl == null)
			return;
		for (LICH_CONG_TAC lich_CONG_TAC : lctl) {
			TableItem ti = new TableItem(table_LichCongtac, SWT.NONE);
			ti.setText(new String[] { mdf.getViewStringDateTime_hm_dmy(lich_CONG_TAC.getTHOIGIAN()),
					lich_CONG_TAC.getNOIDUNG() });
			ti.setData(lich_CONG_TAC);
		}
	}

	private void fill_MuasamGanday() throws SQLException {
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_AndFind_DOT_THUCHIEN_TANG_TAISAN_list(mdf.addDate(new Date(), -180), new Date(), "");
		for (DOT_THUCHIEN_TANG_TAISAN dtt : dttl) {
			GIAI_DOAN_THUC_HIEN gth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
			GIAI_DOAN_QUYET_TOAN gqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
			if (gth != null) {
				TableItem tbi = new TableItem(table_muasamganday, SWT.NONE);

				tbi.setText(new String[] { new Fill_ItemData().getStringHinhthucMuasam(dtt.getLY_DO_TANG()),
						mdf.getViewStringDate(gth.getTHOI_DIEM_BAT_DAU()),
						gqt == null ? "Đang thực hiện"
								: (gqt.getTHOI_GIAN_KET_THUC() == null ? "Đang thực hiện Quyết toán"
										: mdf.getViewStringDate(gqt.getTHOI_GIAN_KET_THUC())) });
			}
		}
	}

	private void setIconThongbaomoi(ToolItem tltmNghNhim) throws SQLException {
		if (getThongbaomoi()) {
			tltmNghNhim.setImage(icondata.ThongbaoChuadocIcon);
		} else {
			tltmNghNhim.setImage(icondata.ThongbaoIcon);
		}
	}

	private boolean getThongbaomoi() throws SQLException {
		return controler.getControl_THONGBAO().get_THONGBAO_Moi();
	}

	Object[] getDataLoaiTaisan(Tree tree) {
		Object[] rs = new Object[3];
		TreeItem items[] = tree.getSelection();
		if (items.length > 0) {
			Object o = items[0].getData();
			if (o instanceof LOAITAISAN_CAP_I) {
				rs[0] = o;
			} else if (o instanceof LOAITAISAN_CAP_II) {
				rs[1] = o;
			} else if (o instanceof LOAITAISAN_CAP_III) {
				rs[2] = o;
			}
		}
		return rs;
	}

	protected ArrayList<TAISAN> getdata_LoaiTaisan() throws SQLException {
		Object[] LoaiSelected = getDataLoaiTaisan(tree_LoaiTaisan);
		return controler.getControl_TAISAN().Data_TaiSan_Mainform_LoaiTaisan(dv, (LOAITAISAN_CAP_III) LoaiSelected[2],
				(LOAITAISAN_CAP_II) LoaiSelected[1], (LOAITAISAN_CAP_I) LoaiSelected[0]);
	}

	Object[] getDataNhomTaisan(Tree tree) {
		Object[] rs = new Object[5];
		TreeItem items[] = tree.getSelection();
		if (items.length > 0) {
			Object o = items[0].getData();
			if (o instanceof NHOMTAISAN_CAP_I) {
				rs[0] = o;
			} else if (o instanceof NHOMTAISAN_CAP_II) {
				rs[1] = o;
			} else if (o instanceof NHOMTAISAN_CAP_III) {
				rs[2] = o;
			} else if (o instanceof NHOMTAISAN_CAP_IV) {
				rs[3] = o;
			} else if (o instanceof NHOMTAISAN_CAP_V) {
				rs[4] = o;
			}
		}
		return rs;
	}

	protected ArrayList<TAISAN> getdata_NHOMTaisan_Codinh_Dacbiet() throws SQLException {
		TreeItem items[] = tree_NhomTaisan_Codinh_dacbiet.getSelection();
		if (items.length > 0) {
			Object it = items[0].getData();
			if (it != null) {
				NHOM_TAISANCODINH_DACBIET ntv = (NHOM_TAISANCODINH_DACBIET) it;
				NHOMTAISAN_CAP_V temp = new NHOMTAISAN_CAP_V();
				temp.setMA_NHOMTAISAN_CAP_V(ntv.getMA_NHOM_TAISANCODINH_DACBIET());
				temp.setMA_NHOMTAISAN_CAP_IV(ntv.getMA_NHOMTAISAN_CAP_IV());
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, temp, null, null, null, null);
			} else {
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, null, null, null, null, null);
			}
		}
		return new ArrayList<>();
	}

	protected ArrayList<TAISAN> getdata_NHOMTaisan_Codinh_Dacthu() throws SQLException {
		TreeItem items[] = tree_Nhomtaisan_Codinh_Dacthu.getSelection();
		if (items.length > 0) {
			Object it = items[0].getData();
			if (it != null) {
				NHOM_TAISANCODINH_DACTHU ntv = (NHOM_TAISANCODINH_DACTHU) it;
				NHOMTAISAN_CAP_V temp = new NHOMTAISAN_CAP_V();
				temp.setMA_NHOMTAISAN_CAP_V(ntv.getMA_NHOM_TAISANCODINH_DACTHU());
				temp.setMA_NHOMTAISAN_CAP_IV(ntv.getMA_NHOMTAISAN_CAP_IV());
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, temp, null, null, null, null);
			} else {
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, null, null, null, null, null);
			}
		}
		return new ArrayList<>();
	}

	protected ArrayList<TAISAN> getdata_NHOMTaisan_Codinh_Vohinh() throws SQLException {
		TreeItem items[] = tree_NhomTaisan_Codinh_Vohinh.getSelection();
		if (items.length > 0) {
			Object it = items[0].getData();
			if (it != null) {
				NHOM_TAISANCODINH_VOHINH ntv = (NHOM_TAISANCODINH_VOHINH) it;
				NHOMTAISAN_CAP_V temp = new NHOMTAISAN_CAP_V();
				temp.setMA_NHOMTAISAN_CAP_V(ntv.getMA_NHOM_TAISANCODINH_VOHINH());
				temp.setMA_NHOMTAISAN_CAP_IV(ntv.getMA_NHOMTAISAN_CAP_IV());
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, temp, null, null, null, null);
			} else {
				return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, null, null, null, null, null);
			}
		}
		return new ArrayList<>();
	}

	protected ArrayList<TAISAN> getdata_NHOMTaisan_Codinh() throws SQLException {
		Object[] NhomtsSelected = getDataNhomTaisan(tree_NHOMTaisan_Codinh_huuhinh);
		return controler.getControl_TAISAN().Data_TaiSan_Mainform_NhomTaisan(dv, (NHOMTAISAN_CAP_V) NhomtsSelected[4],
				(NHOMTAISAN_CAP_IV) NhomtsSelected[3], (NHOMTAISAN_CAP_III) NhomtsSelected[2],
				(NHOMTAISAN_CAP_II) NhomtsSelected[1], (NHOMTAISAN_CAP_I) NhomtsSelected[0]);
	}

	protected ArrayList<TAISAN> getdata_Oto() throws SQLException {
		return controler.getControl_TAISAN().get_Taisan_Oto(dv);
	}

	protected ArrayList<TAISAN> getdata_Xemay() throws SQLException {
		return controler.getControl_TAISAN().get_Taisan_Xemay(dv);
	}

	protected void setData_viewMainForm_InfoTaiSan(Integer MA_TAISAN) throws SQLException {
		TAISAN t = controler.getControl_TAISAN().get_Taisan(MA_TAISAN);
		if (t != null) {
			Fill_ItemData fi = new Fill_ItemData();
			NGUONTANG a = controler.getControl_NGUONTANG().get_NguonTangTaisan_FromTaiSan(t.getMA_TAISAN());
			if (a == null) {
				text_NguonGocTaiSan.setText("Không có dữ liệu");
			} else {
				text_NguonGocTaiSan.setText(a.getTEN_NGUONTANG());
				// linkNguongoc.setText("<A>" + a.getTEN_NGUONTANG() + "</A>");
			}
			DOT_THUCHIEN_TANG_TAISAN ctt = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
					.get_DotTangTaisan_Gannhat(MA_TAISAN);

			if (ctt == null) {
				text_LyDotang.setText("Không có dữ liệu");
			} else {
				text_LyDotang.setText(fi.getStringOfLYDOTANG(ctt.getLY_DO_TANG()));
			}
			text_DonViSuDung.setText(t.getDonvi_Sudung().getTEN_PHONGBAN());
			text_DonViQuanLy.setText(t.getDonvi_Quanly().getTEN_PHONGBAN());
			text_TenTaiSan.setText(t.getTEN_TAISAN());
			text_MaTaiSan.setText(String.valueOf(t.getMA_TAISAN()));
			text_Model.setText(String.valueOf(t.getMODEL()).equals("null") ? "--" : t.getMODEL());
			text_Serial.setText(String.valueOf(t.getSERI()).equals("null") ? "--" : t.getSERI());
			text_NgaySudung.setText(new MyDateFormat().getViewStringDate(t.getNGAY_SU_DUNG()));
			text_XuatXu.setText(String.valueOf(t.getXUAT_XU()).equals("null") ? "--" : t.getXUAT_XU());
			text_NguyenGia.setText(String.valueOf(t.getNGUYEN_GIA()));
			text_DonViTinh.setText(
					String.valueOf(String.valueOf(t.getDON_VI_TINH()).equals("null") ? "--" : t.getDON_VI_TINH()));
			text_TinhTrangTaiSan.setText(fi.getStringOfTINHTRANG(t.getTINH_TRANG()));
			text_BaoHanh.setText(String.valueOf(t.getBAO_HANH()));
			ArrayList<PHUKIEN> list_phukien = t.getPhukienList();
			text_MaPTGT.setText("--");
			PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
					.get_PHUONGTIEN_GIAOTHONG_FromTaisan(t.getMA_TAISAN());
			if (pg != null)
				text_MaPTGT.setText(pg.getMA_PHUONGTIEN_GIAOTHONG());
			if ((list_phukien != null) && (list_phukien.size() > 0)) {
				table_PhuKien.removeAll();
				for (PHUKIEN p : list_phukien) {
					TableItem ti = new TableItem(table_PhuKien, SWT.NONE);
					ti.setText(0, String.valueOf(p.getMA_PHUKIEN()));
					ti.setText(1, p.getTEN_PHUKIEN());
					ti.setText(2, String.valueOf(p.getMODEL()).equals("null") ? "--" : p.getMODEL());
					ti.setText(3, String.valueOf(p.getSERI()).equals("null") ? "--" : p.getSERI());
					ti.setText(4, String.valueOf(p.getNGUYEN_GIA()));
				}
			} else {
				table_PhuKien.removeAll();
			}
		}
	}
}
