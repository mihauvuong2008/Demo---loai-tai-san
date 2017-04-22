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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
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
import DAO.TAISAN;
import DAO.TAPHOSO;
import View.AssetManagers.Hoso.Taphoso_View;
import View.AssetManagers.NguonGiam.ChonNguonGiam;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.AssetManagers.NguonTang.ChonNguontang;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

public class Nhatky_Lamviec extends Dialog {

	protected Object result;
	protected Shell shlNhtKCng;
	private Text text_LoaiCongviec;
	private Text text_TenCongviec;
	private Text text_Mota;
	private Text text_Sodexuat;
	private Text text_Dexuat_Tongngay;
	private Text text_Dexuat_Ghichu;
	private Text text_Thuchien_Tongngay;
	private Text text_Thuchien_Ghichu;
	private Text text_Nghiemthu_Ghichu;
	private Text text_Quyettoan_Tongngay;
	private Text text_Quyettoan_Ghichu;
	private Text text_MaDonvi;
	private Text text_Tendonvi;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private Text text_TongngayThuchien;
	private static NGUOIDUNG user;
	private final Controler controler;
	private Object Congviec;
	private final int LOAI_CONGVIEC;
	/*
	 * 1: Dot Sua chua Bao duong- 2: Dot Tang // Taisan - // 3:Dot Giam Taisan
	 */
	private DOT_THUCHIEN_SUACHUA_BAODUONG dsb;
	private DOT_THUCHIEN_TANG_TAISAN dtt;
	private DOT_THUCHIEN_GIAM_TAISAN dgt;
	private DE_XUAT dx;
	private GIAI_DOAN_THUC_HIEN gdth;
	private GIAI_DOAN_NGHIEM_THU gdnt;
	private GIAI_DOAN_QUYET_TOAN gdqt;
	private NGUONSUACHUA_BAODUONG nsbd;
	private NGUONTANG nt;
	private NGUONGIAM ng;
	private DateTime dateTime_Dexuat_Tiepnhan;
	private DateTime dateTime_Dexuat_Chuyengiao;
	private DateTime dateTime_Dexuat_Hoanthanh;
	private DateTime dateTime_Thuchien_Tiepnhan;
	private DateTime dateTime_Thuchien_Chuyengiao;
	private DateTime dateTime_Thuchien_Hoanthanh;
	private DateTime dateTime_Nghiemthu_Tiepnhan;
	private DateTime dateTime_Nghiemthu_Chuyengiao;
	private DateTime dateTime_Nghiemthu_Hoanthanh;
	private DateTime dateTime_Quyettoan_Tiepnhan;
	private DateTime dateTime_Quyettoan_Hoanthanh;
	private Combo combo_DonviDexuat;
	private Tree tree_Danhsach_Ptts;
	private Table table_Dexuat;
	private Table table_Thuchien;
	private Table table_Nghiemthu;
	private Table table_Quyettoan;
	private Text text_Nghiemthu_Tongngay;
	private Text text_Congviec_Batdau;
	private Text text_Congviec_Ketthuc;
	private Composite grpKiemTraNghiemThu;
	private Composite grpQuyetToan;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Nhatky_Lamviec.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public Nhatky_Lamviec(Shell parent, int style, Object Congviec, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		Nhatky_Lamviec.user = user;
		this.Congviec = Congviec;
		if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			LOAI_CONGVIEC = 1;
		} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
			LOAI_CONGVIEC = 2;
		} else if (Congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			LOAI_CONGVIEC = 3;
		} else {
			LOAI_CONGVIEC = 0;
		}
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
		shlNhtKCng.open();
		shlNhtKCng.layout();
		Display display = getParent().getDisplay();
		while (!shlNhtKCng.isDisposed()) {
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
		shlNhtKCng = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlNhtKCng.setImage(user.getIcondata().nhatkyLamviec);
		shlNhtKCng.setSize(850, 525);
		shlNhtKCng.setText("Nh\u1EADt k\u00FD c\u00F4ng vi\u1EC7c");
		shlNhtKCng.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlNhtKCng);

		SashForm sashForm = new SashForm(shlNhtKCng, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite composite = new Composite(sashForm_1, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblLoiCngVic = new Label(composite, SWT.NONE);
		lblLoiCngVic.setText("Lo\u1EA1i c\u00F4ng vi\u1EC7c:");

		text_LoaiCongviec = new Text(composite, SWT.READ_ONLY);
		text_LoaiCongviec.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_LoaiCongviec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnCngVic = new Label(composite, SWT.NONE);
		lblTnCngVic.setText("T\u00EAn c\u00F4ng vi\u1EC7c:");

		text_TenCongviec = new Text(composite, SWT.NONE);
		text_TenCongviec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnV = new Label(composite, SWT.NONE);
		lblnV.setText("\u0110\u01A1n v\u1ECB \u0111\u1EC1 xu\u1EA5t:");

		combo_DonviDexuat = new Combo(composite, SWT.READ_ONLY);
		combo_DonviDexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyBtu = new Label(composite, SWT.NONE);
		lblNgyBtu.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		text_Congviec_Batdau = new Text(composite, SWT.READ_ONLY);
		text_Congviec_Batdau.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Congviec_Batdau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyKtThc = new Label(composite, SWT.NONE);
		lblNgyKtThc.setText("Ng\u00E0y k\u1EBFt th\u00FAc:");

		text_Congviec_Ketthuc = new Text(composite, SWT.READ_ONLY);
		text_Congviec_Ketthuc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Congviec_Ketthuc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTngSNgy = new Label(composite, SWT.NONE);
		GridData gd_lblTngSNgy = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTngSNgy.widthHint = 110;
		lblTngSNgy.setLayoutData(gd_lblTngSNgy);
		lblTngSNgy.setText("Tổng cộng (ngày):");

		text_TongngayThuchien = new Text(composite, SWT.READ_ONLY);
		text_TongngayThuchien.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_TongngayThuchien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Gi\u1EDBi thi\u1EC7u:");

		text_Mota = new Text(composite, SWT.V_SCROLL);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Group grpnVNgoi = new Group(sashForm_1, SWT.NONE);
		grpnVNgoi.setText("Liên hệ Đơn vị ngoài");
		grpnVNgoi.setLayout(new GridLayout(3, false));

		Label lblTnnV = new Label(grpnVNgoi, SWT.NONE);
		GridData gd_lblTnnV = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTnnV.widthHint = 110;
		lblTnnV.setLayoutData(gd_lblTnnV);
		lblTnnV.setText("T\u00EAn \u0111\u01A1n v\u1ECB:");

		text_Tendonvi = new Text(grpnVNgoi, SWT.NONE);
		text_Tendonvi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					switch (LOAI_CONGVIEC) {
					case 1:
						ChonNguonSuachua_Baoduong cb = new ChonNguonSuachua_Baoduong(shlNhtKCng, SWT.DIALOG_TRIM, user);
						cb.open();
						nsbd = cb.getResult();
						if (nsbd != null) {
							text_Tendonvi.setText(nsbd.getTEN_NGUONSUACHUA_BAODUONG());
							text_MaDonvi.setText(nsbd.getMA_NGUONSUACHUA_BAODUONG() + "");
							text_Gioithieu.setText(nsbd.getGIOI_THIEU());
							text_Lienhe.setText(nsbd.getLIEN_HE());
						}
						break;
					case 2:
						ChonNguontang cnt = new ChonNguontang(shlNhtKCng, SWT.DIALOG_TRIM, user, 0);
						cnt.open();
						nt = cnt.getResult();
						if (nt != null) {
							text_Tendonvi.setText(nt.getTEN_NGUONTANG());
							text_MaDonvi.setText(nt.getMA_NGUONTANG() + "");
							text_Gioithieu.setText(nt.getGIOI_THIEU());
							text_Lienhe.setText(nt.getLIEN_HE());
						}
						break;
					case 3:
						ChonNguonGiam cng = new ChonNguonGiam(shlNhtKCng, SWT.DIALOG_TRIM, user);
						cng.open();
						ng = cng.getResult();
						if (ng != null) {
							text_Tendonvi.setText(ng.getTEN_NGUONGIAM());
							text_MaDonvi.setText(ng.getMA_NGUONGIAM() + "");
							text_Gioithieu.setText(ng.getGIOI_THIEU());
							text_Lienhe.setText(ng.getLIEN_HE());
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
		text_Tendonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tendonvi.setEditable(false);
		GridData gd_text_Tendonvi = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_Tendonvi.widthHint = 315;
		text_Tendonvi.setLayoutData(gd_text_Tendonvi);

		text_MaDonvi = new Text(grpnVNgoi, SWT.NONE);
		text_MaDonvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_MaDonvi.setEditable(false);
		text_MaDonvi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu_1 = new Label(grpnVNgoi, SWT.NONE);
		GridData gd_lblGiiThiu_1 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu_1.verticalIndent = 3;
		lblGiiThiu_1.setLayoutData(gd_lblGiiThiu_1);
		lblGiiThiu_1.setText("Gi\u1EDBi thi\u1EC7u:");

		text_Gioithieu = new Text(grpnVNgoi, SWT.NONE);
		text_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Gioithieu.setEditable(false);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Label lblLinH = new Label(grpnVNgoi, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Li\u00EAn h\u1EC7:");

		text_Lienhe = new Text(grpnVNgoi, SWT.NONE);
		text_Lienhe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Lienhe.setEditable(false);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tree_Danhsach_Ptts = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Danhsach_Ptts.setLinesVisible(true);
		tree_Danhsach_Ptts.setHeaderVisible(true);

		TreeColumn trclmnStt = new TreeColumn(tree_Danhsach_Ptts, SWT.NONE);
		trclmnStt.setWidth(45);
		trclmnStt.setText("STT");

		TreeColumn trclmnTnMT = new TreeColumn(tree_Danhsach_Ptts, SWT.NONE);
		trclmnTnMT.setWidth(150);
		trclmnTnMT.setText("TÊN, MÔ TẢ PTTS");

		TreeColumn trclmnModel = new TreeColumn(tree_Danhsach_Ptts, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("MODEL");

		TreeColumn trclmnSeri = new TreeColumn(tree_Danhsach_Ptts, SWT.NONE);
		trclmnSeri.setWidth(100);
		trclmnSeri.setText("SERI");

		TreeColumn trclmnMPtts = new TreeColumn(tree_Danhsach_Ptts, SWT.NONE);
		trclmnMPtts.setWidth(100);
		trclmnMPtts.setText("MÃ PTTS");
		sashForm_1.setWeights(new int[] { 186, 103, 150 });

		ExpandBar expandBar = new ExpandBar(sashForm, SWT.V_SCROLL);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));

		ExpandItem xpndtmXut = new ExpandItem(expandBar, SWT.NONE);
		xpndtmXut.setExpanded(true);
		xpndtmXut.setImage(user.getIcondata().DexuatIcon);
		xpndtmXut.setText("Đề xuất");

		SashForm sashForm_4 = new SashForm(expandBar, SWT.VERTICAL);
		xpndtmXut.setControl(sashForm_4);

		Composite grpXut = new Composite(sashForm_4, SWT.NONE);
		grpXut.setLayout(new GridLayout(2, false));

		Label lblSXut = new Label(grpXut, SWT.NONE);
		lblSXut.setText("S\u1ED1 \u0111\u1EC1 xu\u1EA5t:");

		text_Sodexuat = new Text(grpXut, SWT.NONE);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTipNhn = new Label(grpXut, SWT.NONE);
		lblTipNhn.setText("Ti\u1EBFp nh\u1EADn:");

		dateTime_Dexuat_Tiepnhan = new DateTime(grpXut, SWT.BORDER);
		dateTime_Dexuat_Tiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblChuynGiao = new Label(grpXut, SWT.NONE);
		lblChuynGiao.setText("Chuy\u1EC3n giao:");

		dateTime_Dexuat_Chuyengiao = new DateTime(grpXut, SWT.BORDER);
		dateTime_Dexuat_Chuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHonThnh = new Label(grpXut, SWT.NONE);
		lblHonThnh.setText("Ho\u00E0n th\u00E0nh:");

		dateTime_Dexuat_Hoanthanh = new DateTime(grpXut, SWT.BORDER);
		dateTime_Dexuat_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblTngngy = new Label(grpXut, SWT.NONE);
		lblTngngy.setText("T\u1ED5ng (ng\u00E0y):");

		text_Dexuat_Tongngay = new Text(grpXut, SWT.NONE);
		text_Dexuat_Tongngay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_21 = new Label(grpXut, SWT.NONE);
		GridData gd_label_21 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_21.verticalIndent = 3;
		label_21.setLayoutData(gd_label_21);
		label_21.setText("Ghi ch\u00FA:");

		text_Dexuat_Ghichu = new Text(grpXut, SWT.NONE);
		text_Dexuat_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_Dexuat = new Table(sashForm_4, SWT.BORDER | SWT.FULL_SELECTION);
		table_Dexuat.setHeaderVisible(true);
		table_Dexuat.setLinesVisible(true);

		TableColumn tblclmnTn = new TableColumn(table_Dexuat, SWT.NONE);
		tblclmnTn.setWidth(100);
		tblclmnTn.setText("Tên Cán bộ");

		TableColumn tblclmnTiKhon = new TableColumn(table_Dexuat, SWT.NONE);
		tblclmnTiKhon.setWidth(100);
		tblclmnTiKhon.setText("Tài khoản");

		TableColumn tblclmnNgyThamGia = new TableColumn(table_Dexuat, SWT.NONE);
		tblclmnNgyThamGia.setWidth(100);
		tblclmnNgyThamGia.setText("Ngày tham gia");

		Menu menu = new Menu(table_Dexuat);
		table_Dexuat.setMenu(menu);

		MenuItem mntmThmHS = new MenuItem(menu, SWT.NONE);
		mntmThmHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String TEN_TAI_KHOAN = user.getTEN_CAN_BO();
				DE_XUAT dx;
				try {
					dx = controler.getControl_DEXUAT().get_DEXUAT(Congviec);
					String Nguoidung = dx.getTEN_TAI_KHOAN();
					if (Nguoidung.equals(TEN_TAI_KHOAN)) {
						TAPHOSO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO()));
						if (ths == null) {
							ths = new TAPHOSO();
							ths.setTEN_TAPHOSO("Tập hồ sơ Đề xuất");
							ths.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung - Đề xuất");
							int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(ths);
							if (Ma_NewTapHoso > 0) {
								controler.getControl_DEXUAT().update_TapHoso(dx, Ma_NewTapHoso);
								dx.setMA_TAPHOSO(Ma_NewTapHoso);
							}
							ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
						}
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, false);
						thsV.open();
					} else {
						MessageBox m = new MessageBox(shlNhtKCng);
						m.setMessage("Người Thực hiện Nhập đề xuất này mới có thể thay đổi hồ sơ");
						m.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmThmHS.setText("Thêm hồ sơ");

		MenuItem mntmXemHS = new MenuItem(menu, SWT.NONE);
		mntmXemHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String TEN_TAI_KHOAN = user.getTEN_CAN_BO();
				DE_XUAT dx;
				try {
					dx = controler.getControl_DEXUAT().get_DEXUAT(Congviec);
					String Nguoidung = dx.getTEN_TAI_KHOAN();
					TAPHOSO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(dx.getMA_TAPHOSO()));
					if (Nguoidung.equals(TEN_TAI_KHOAN)) {
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, false);
						thsV.open();
					} else {
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, true);
						thsV.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXemHS.setText("Xem hồ sơ");
		sashForm_4.setWeights(new int[] { 1000, 618 });
		xpndtmXut.setHeight(300);

		ExpandItem xpndtmTChc = new ExpandItem(expandBar, SWT.NONE);
		xpndtmTChc.setImage(user.getIcondata().greyFolder);
		xpndtmTChc.setText("Tổ chức - Thực hiện");

		SashForm sashForm_5 = new SashForm(expandBar, SWT.VERTICAL);
		xpndtmTChc.setControl(sashForm_5);

		Composite grpTChc = new Composite(sashForm_5, SWT.NONE);
		grpTChc.setLayout(new GridLayout(2, false));

		Label lblTipNhn_1 = new Label(grpTChc, SWT.NONE);
		lblTipNhn_1.setText("Ti\u1EBFp nh\u1EADn:");

		dateTime_Thuchien_Tiepnhan = new DateTime(grpTChc, SWT.BORDER);
		dateTime_Thuchien_Tiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblChuynGiao_1 = new Label(grpTChc, SWT.NONE);
		lblChuynGiao_1.setText("Chuy\u1EC3n giao:");

		dateTime_Thuchien_Chuyengiao = new DateTime(grpTChc, SWT.BORDER);
		dateTime_Thuchien_Chuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHonThnh_1 = new Label(grpTChc, SWT.NONE);
		lblHonThnh_1.setText("Ho\u00E0n th\u00E0nh:");

		dateTime_Thuchien_Hoanthanh = new DateTime(grpTChc, SWT.BORDER);
		dateTime_Thuchien_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTngngy_1 = new Label(grpTChc, SWT.NONE);
		lblTngngy_1.setText("T\u1ED5ng (ng\u00E0y):");

		text_Thuchien_Tongngay = new Text(grpTChc, SWT.NONE);
		text_Thuchien_Tongngay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(grpTChc, SWT.NONE);
		GridData gd_label_5 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_5.verticalIndent = 3;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("Ghi ch\u00FA:");

		text_Thuchien_Ghichu = new Text(grpTChc, SWT.NONE);
		text_Thuchien_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_Thuchien = new Table(sashForm_5, SWT.BORDER | SWT.FULL_SELECTION);
		table_Thuchien.setLinesVisible(true);
		table_Thuchien.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table_Thuchien, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Tên Cán bộ");

		TableColumn tableColumn_1 = new TableColumn(table_Thuchien, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Tài khoản");

		TableColumn tableColumn_2 = new TableColumn(table_Thuchien, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("Ngày tham gia");

		Menu menu_1 = new Menu(table_Thuchien);
		table_Thuchien.setMenu(menu_1);

		MenuItem menuItem = new MenuItem(menu_1, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(Congviec);
					if (gdth == null)
						return;
					NGUOIDUNG_THUCHIEN thamgiaCongviec = null;
					ArrayList<NGUOIDUNG_THUCHIEN> ndth = controler.getControl_THUCHIEN_CANBO()
							.get_AllNGUOIDUNG_THUCHIEN(gdth);
					for (NGUOIDUNG_THUCHIEN nguoidung_THUCHIEN : ndth) {
						if (nguoidung_THUCHIEN.getTEN_TAI_KHOAN().equals(user.getTEN_TAI_KHOAN()))
							thamgiaCongviec = nguoidung_THUCHIEN;
					}
					if (thamgiaCongviec != null) {
						TAPHOSO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(thamgiaCongviec.getMA_TAPHOSO()));
						if (ths == null) {
							ths = new TAPHOSO();
							ths.setTEN_TAPHOSO("Tập hồ sơ bổ sung - Thực hiện");
							ths.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung - Thực hiện");
							int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(ths);
							if (Ma_NewTapHoso > 0) {
								controler.getControl_DEXUAT().update_TapHoso(dx, Ma_NewTapHoso);
								dx.setMA_TAPHOSO(Ma_NewTapHoso);
							}
							ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
						}
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, false);
						thsV.open();
					} else {
						MessageBox m = new MessageBox(shlNhtKCng, SWT.ICON_WARNING);
						m.setMessage("Bạn không tham gia công việc này");
						m.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("Thêm hồ sơ");

		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(Congviec);
					if (gdth == null)
						return;
					ViewPartHoso vph = new ViewPartHoso(shlNhtKCng, SWT.DIALOG_TRIM, user, gdth);
					vph.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_1.setText("Xem hồ sơ");
		sashForm_5.setWeights(new int[] { 1000, 618 });
		xpndtmTChc.setHeight(280);

		ExpandItem xpndtmNghimThu = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNghimThu.setImage(user.getIcondata().greyFolder);
		xpndtmNghimThu.setText("Nghiệm thu - Bàn giao");

		SashForm sashForm_6 = new SashForm(expandBar, SWT.VERTICAL);
		xpndtmNghimThu.setControl(sashForm_6);

		grpKiemTraNghiemThu = new Composite(sashForm_6, SWT.NONE);
		grpKiemTraNghiemThu.setLayout(new GridLayout(2, false));

		Label lblTipNhn_2 = new Label(grpKiemTraNghiemThu, SWT.NONE);
		lblTipNhn_2.setText("Ti\u1EBFp nh\u1EADn:");

		dateTime_Nghiemthu_Tiepnhan = new DateTime(grpKiemTraNghiemThu, SWT.BORDER);
		dateTime_Nghiemthu_Tiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblChuynGiao_2 = new Label(grpKiemTraNghiemThu, SWT.NONE);
		lblChuynGiao_2.setText("Chuy\u1EC3n giao:");

		dateTime_Nghiemthu_Chuyengiao = new DateTime(grpKiemTraNghiemThu, SWT.BORDER);
		dateTime_Nghiemthu_Chuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHonThnh_2 = new Label(grpKiemTraNghiemThu, SWT.NONE);
		lblHonThnh_2.setText("Ho\u00E0n th\u00E0nh:");

		dateTime_Nghiemthu_Hoanthanh = new DateTime(grpKiemTraNghiemThu, SWT.BORDER);
		dateTime_Nghiemthu_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTngngy_2 = new Label(grpKiemTraNghiemThu, SWT.NONE);
		lblTngngy_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTngngy_2.setText("T\u1ED5ng (ng\u00E0y):");

		text_Nghiemthu_Tongngay = new Text(grpKiemTraNghiemThu, SWT.NONE);
		text_Nghiemthu_Tongngay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(grpKiemTraNghiemThu, SWT.NONE);
		GridData gd_label_6 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_6.verticalIndent = 3;
		label_6.setLayoutData(gd_label_6);
		label_6.setText("Ghi ch\u00FA:");

		text_Nghiemthu_Ghichu = new Text(grpKiemTraNghiemThu, SWT.NONE);
		text_Nghiemthu_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_Nghiemthu = new Table(sashForm_6, SWT.BORDER | SWT.FULL_SELECTION);
		table_Nghiemthu.setLinesVisible(true);
		table_Nghiemthu.setHeaderVisible(true);

		TableColumn tableColumn_3 = new TableColumn(table_Nghiemthu, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Tên Cán bộ");

		TableColumn tableColumn_4 = new TableColumn(table_Nghiemthu, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("Tài khoản");

		TableColumn tableColumn_5 = new TableColumn(table_Nghiemthu, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("Ngày tham gia");

		Menu menu_2 = new Menu(table_Nghiemthu);
		table_Nghiemthu.setMenu(menu_2);

		MenuItem menuItem_3 = new MenuItem(menu_2, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					GIAI_DOAN_NGHIEM_THU gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(Congviec);
					if (gdth == null)
						return;
					NGUOIDUNG_NGHIEMTHU thamgiaCongviec = null;
					ArrayList<NGUOIDUNG_NGHIEMTHU> ndth = controler.getControl_NGHIEMTHU_CANBO()
							.get_AllNGUOIDUNG_NGHIEMTHU(gdth);
					for (NGUOIDUNG_NGHIEMTHU nguoidung_THUCHIEN : ndth) {
						if (nguoidung_THUCHIEN.getTEN_TAI_KHOAN().equals(user.getTEN_TAI_KHOAN()))
							thamgiaCongviec = nguoidung_THUCHIEN;
					}
					if (thamgiaCongviec != null) {
						TAPHOSO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(thamgiaCongviec.getMA_TAPHOSO()));
						if (ths == null) {
							ths = new TAPHOSO();
							ths.setTEN_TAPHOSO("Tập hồ sơ bổ sung - Nghiệm thu");
							ths.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung - Nghiệm thu");
							int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(ths);
							if (Ma_NewTapHoso > 0) {
								controler.getControl_DEXUAT().update_TapHoso(dx, Ma_NewTapHoso);
								dx.setMA_TAPHOSO(Ma_NewTapHoso);
							}
							ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
						}
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, false);
						thsV.open();
					} else {
						MessageBox m = new MessageBox(shlNhtKCng, SWT.ICON_WARNING);
						m.setMessage("Bạn không tham gia công việc này");
						m.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_3.setText("Thêm hồ sơ");

		MenuItem menuItem_4 = new MenuItem(menu_2, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GIAI_DOAN_NGHIEM_THU gdth;
				try {
					gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(Congviec);
					if (gdth == null)
						return;
					ViewPartHoso vph = new ViewPartHoso(shlNhtKCng, SWT.DIALOG_TRIM, user, gdth);
					vph.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_4.setText("Xem hồ sơ");
		sashForm_6.setWeights(new int[] { 1000, 618 });
		xpndtmNghimThu.setHeight(280);

		ExpandItem xpndtmQuytTon = new ExpandItem(expandBar, SWT.NONE);
		xpndtmQuytTon.setImage(user.getIcondata().greyFolder);
		xpndtmQuytTon.setText("Quyết toán - Thanh lý hợp đồng");

		SashForm sashForm_7 = new SashForm(expandBar, SWT.VERTICAL);
		xpndtmQuytTon.setControl(sashForm_7);

		grpQuyetToan = new Composite(sashForm_7, SWT.NONE);
		grpQuyetToan.setLayout(new GridLayout(2, false));

		Label lblTipNhn_3 = new Label(grpQuyetToan, SWT.NONE);
		lblTipNhn_3.setText("Ti\u1EBFp nh\u1EADn:");

		dateTime_Quyettoan_Tiepnhan = new DateTime(grpQuyetToan, SWT.BORDER);
		dateTime_Quyettoan_Tiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHonThnh_3 = new Label(grpQuyetToan, SWT.NONE);
		lblHonThnh_3.setText("Ho\u00E0n th\u00E0nh:");

		dateTime_Quyettoan_Hoanthanh = new DateTime(grpQuyetToan, SWT.BORDER);
		dateTime_Quyettoan_Hoanthanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTngngy_3 = new Label(grpQuyetToan, SWT.NONE);
		lblTngngy_3.setText("T\u1ED5ng (ng\u00E0y):");

		text_Quyettoan_Tongngay = new Text(grpQuyetToan, SWT.NONE);
		text_Quyettoan_Tongngay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_17 = new Label(grpQuyetToan, SWT.NONE);
		GridData gd_label_17 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_17.verticalIndent = 3;
		label_17.setLayoutData(gd_label_17);
		label_17.setText("Ghi ch\u00FA:");

		text_Quyettoan_Ghichu = new Text(grpQuyetToan, SWT.NONE);
		text_Quyettoan_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table_Quyettoan = new Table(sashForm_7, SWT.BORDER | SWT.FULL_SELECTION);
		table_Quyettoan.setLinesVisible(true);
		table_Quyettoan.setHeaderVisible(true);

		TableColumn tableColumn_6 = new TableColumn(table_Quyettoan, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("Tên Cán bộ");

		TableColumn tableColumn_7 = new TableColumn(table_Quyettoan, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("Tài khoản");

		TableColumn tableColumn_8 = new TableColumn(table_Quyettoan, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("Ngày tham gia");

		Menu menu_3 = new Menu(table_Quyettoan);
		table_Quyettoan.setMenu(menu_3);

		MenuItem menuItem_6 = new MenuItem(menu_3, SWT.NONE);
		menuItem_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(Congviec);
					if (gdth == null)
						return;
					NGUOIDUNG_QUYETTOAN thamgiaCongviec = null;
					ArrayList<NGUOIDUNG_QUYETTOAN> ndth = controler.getControl_QUYETTOAN_CANBO()
							.get_AllNGUOIDUNG_QUYETTOAN(gdth);
					for (NGUOIDUNG_QUYETTOAN nguoidung_THUCHIEN : ndth) {
						if (nguoidung_THUCHIEN.getTEN_TAI_KHOAN().equals(user.getTEN_TAI_KHOAN()))
							thamgiaCongviec = nguoidung_THUCHIEN;
					}
					if (thamgiaCongviec != null) {
						TAPHOSO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(thamgiaCongviec.getMA_TAPHOSO()));
						if (ths == null) {
							ths = new TAPHOSO();
							ths.setTEN_TAPHOSO("Tập hồ sơ bổ sung - Quyết toán");
							ths.setGIOITHIEU_TAPHOSO("Tập hồ sơ bổ sung - Quyết toán");
							int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(ths);
							if (Ma_NewTapHoso > 0) {
								controler.getControl_DEXUAT().update_TapHoso(dx, Ma_NewTapHoso);
								dx.setMA_TAPHOSO(Ma_NewTapHoso);
							}
							ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
						}
						Taphoso_View thsV = new Taphoso_View(shlNhtKCng, SWT.DIALOG_TRIM, user, ths, false);
						thsV.open();
					} else {
						MessageBox m = new MessageBox(shlNhtKCng, SWT.ICON_WARNING);
						m.setMessage("Bạn không tham gia công việc này");
						m.open();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_6.setText("Thêm hồ sơ");

		MenuItem menuItem_7 = new MenuItem(menu_3, SWT.NONE);
		menuItem_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GIAI_DOAN_QUYET_TOAN gdth;
				try {
					gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(Congviec);
					if (gdth == null)
						return;
					ViewPartHoso vph = new ViewPartHoso(shlNhtKCng, SWT.DIALOG_TRIM, user, gdth);
					vph.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_7.setText("Xem hồ sơ");
		sashForm_7.setWeights(new int[] { 1000, 618 });
		xpndtmQuytTon.setHeight(250);
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnng = new Button(shlNhtKCng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Save();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				shlNhtKCng.dispose();
			}
		});
		btnng.setImage(user.getIcondata().saveIcon);
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("L\u01B0u");

		Button button = new Button(shlNhtKCng, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNhtKCng.dispose();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("\u0110\u00F3ng");
		LoadData();
	}

	protected void Save() throws SQLException {
		switch (LOAI_CONGVIEC) {
		case 1:
			dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_TenCongviec.getText());
			dsb.setMO_TA(text_Mota.getText());
			if (nsbd != null)
				dsb.setMA_NGUONSUACHUA_BAODUONG(nsbd.getMA_NGUONSUACHUA_BAODUONG());
			controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().update_DOT_THUCHIEN_SUACHUA_BAODUONG(dsb);

			break;
		case 2:
			dtt.setTEN_DOT_TANG(text_TenCongviec.getText());
			dtt.setMO_TA(text_Mota.getText());
			if (nt != null)
				dtt.setMA_NGUONTANG(nt.getMA_NGUONTANG());
			(controler.getControl_DOT_THUCHIEN_TANG_TAISAN()).update_DOT_TANG_TAISAN(dtt);
			break;
		case 3:
			dgt.setTEN_DOT_GIAM(text_TenCongviec.getText());
			dgt.setMO_TA(text_Mota.getText());
			if (ng != null)
				dgt.setMA_NGUONGIAM(ng.getMA_NGUONGIAM());
			controler.getControl_DOT_THUCHIEN_GIAM_TAISAN().update_DOT_GIAM_TAISAN(dgt);
			break;

		default:
			break;
		}
		if (dx != null) {
			dx.setSODEXUAT(text_Sodexuat.getText());
			if (dx.getTHOI_DIEM_BAT_DAU() != null) {
				dx.setTHOI_DIEM_BAT_DAU(getDate(dateTime_Dexuat_Tiepnhan));
			}
			if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				dx.setTHOI_DIEM_CHUYEN_GIAO(getDate(dateTime_Dexuat_Chuyengiao));
			}
			if (dx.getTHOI_DIEM_HOAN_THANH() != null) {
				dx.setTHOI_DIEM_HOAN_THANH(getDate(dateTime_Dexuat_Hoanthanh));
			}
			dx.setGHI_CHU(text_Dexuat_Ghichu.getText());
			controler.getControl_DEXUAT().update_Dexuat(dx);
		}
		if (gdth != null) {
			if (gdth.getTHOI_DIEM_BAT_DAU() != null) {
				gdth.setTHOI_DIEM_BAT_DAU(getDate(dateTime_Thuchien_Tiepnhan));
			}
			if (gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				gdth.setTHOI_DIEM_CHUYEN_GIAO(getDate(dateTime_Thuchien_Chuyengiao));
			}
			if (gdth.getTHOI_DIEM_HOAN_THANH() != null) {
				gdth.setTHOI_DIEM_HOAN_THANH(getDate(dateTime_Thuchien_Hoanthanh));
			}
			gdth.setGHI_CHU(text_Thuchien_Ghichu.getText());
			controler.getControl_THUCHIEN().Update_Giaidoan_Thuchien(gdth);
		}
		if (gdnt != null) {
			if (gdnt.getTHOI_DIEM_TIEP_NHAN() != null) {
				gdnt.setTHOI_DIEM_TIEP_NHAN(getDate(dateTime_Nghiemthu_Tiepnhan));
			}
			if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				gdnt.setTHOI_DIEM_CHUYEN_GIAO(getDate(dateTime_Nghiemthu_Chuyengiao));
			}
			if (gdnt.getTHOI_DIEM_KET_THUC() != null) {
				gdnt.setTHOI_DIEM_KET_THUC(getDate(dateTime_Nghiemthu_Hoanthanh));
			}
			gdnt.setGHI_CHU(text_Nghiemthu_Ghichu.getText());
			controler.getControl_NGHIEMTHU().Update_Giaidoan_Nghiemthu(gdnt);
		}
		if (gdqt != null) {
			if (gdqt.getTHOI_DIEM_TIEP_NHAN() != null) {
				gdqt.setTHOI_DIEM_TIEP_NHAN(getDate(dateTime_Quyettoan_Tiepnhan));
			}
			if (gdqt.getTHOI_GIAN_KET_THUC() != null) {
				gdqt.setTHOI_GIAN_KET_THUC(getDate(dateTime_Quyettoan_Hoanthanh));
			}
			gdqt.setGHI_CHU(text_Quyettoan_Ghichu.getText());
			controler.getControl_QUYETTOAN().update_Giaidoan_Quyettoan(gdqt);
		}
	}

	private void buildPhongbanList() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		ArrayList<PHONGBAN> pa = controler.getControl_PHONGBAN().getAllDonvi();
		f.setComboBox_DONVI_NOIBO(combo_DonviDexuat, pa);
	}

	private void LoadData() throws SQLException {
		buildPhongbanList();
		// switch (LOAI_CONGVIEC) {
		// case 1:
		// dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec;
		// dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);
		// gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
		// gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
		// gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
		// break;
		// case 2:
		// dtt = (DOT_THUCHIEN_TANG_TAISAN) Congviec;
		// dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
		// gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
		// gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
		// gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
		// break;
		// case 3:
		// dgt = (DOT_THUCHIEN_GIAM_TAISAN) Congviec;
		// dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
		// gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
		// break;
		// default:
		// break;
		// }
		dx = controler.getControl_DEXUAT().get_DEXUAT(Congviec);
		gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(Congviec);
		gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(Congviec);
		gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(Congviec);
		fillCongviec(dx, gdth, gdnt, gdqt);
		fillPhanviec(dx, gdth, gdnt, gdqt);
	}

	private void fillCongviec(DE_XUAT dx, GIAI_DOAN_THUC_HIEN gdth, GIAI_DOAN_NGHIEM_THU gdnt,
			GIAI_DOAN_QUYET_TOAN gdqt) throws SQLException {
		fill_Congviec_SetPHONGBAN(dx);
		// setDate_DateTime(dx.getTHOI_DIEM_BAT_DAU(),
		// dateTime_Congviec_Batdau);
		text_Congviec_Batdau.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
		int TongsoNgay = 0;
		ArrayList<TAISAN> tl = null;
		switch (LOAI_CONGVIEC) {
		case 1:
			dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec;
			text_LoaiCongviec.setText("Sửa chữa - Bảo dưỡng Phương tiện tài sản");
			text_TenCongviec.setText(dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
			// setDate_DateTime(gdqt.getTHOI_GIAN_KET_THUC(),
			// dateTime_Congviec_Ketthuc);
			text_Congviec_Ketthuc.setText(mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()));
			TongsoNgay = mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), gdqt.getTHOI_GIAN_KET_THUC());
			if (dsb.getMO_TA() != null)
				text_Mota.setText(dsb.getMO_TA());
			NGUONSUACHUA_BAODUONG nsbd = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(dsb);
			if (nsbd != null) {
				text_MaDonvi.setText(nsbd.getMA_NGUONSUACHUA_BAODUONG() + "");
				text_Tendonvi.setText(nsbd.getTEN_NGUONSUACHUA_BAODUONG());
				text_Gioithieu.setText(nsbd.getGIOI_THIEU());
				text_Lienhe.setText(nsbd.getLIEN_HE());
			}
			tl = controler.getControl_TAISAN().get_TAISAN(dsb);
			break;
		case 2:
			dtt = (DOT_THUCHIEN_TANG_TAISAN) Congviec;
			text_LoaiCongviec.setText("Mua sắm - Tiếp nhận Phương tiện tài sản");
			text_TenCongviec.setText(dtt.getTEN_DOT_TANG());
			// setDate_DateTime(gdqt.getTHOI_GIAN_KET_THUC(),
			// dateTime_Congviec_Ketthuc);
			text_Congviec_Ketthuc.setText(mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()));
			TongsoNgay = mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), gdqt.getTHOI_GIAN_KET_THUC());
			if (dtt.getMO_TA() != null)
				text_Mota.setText(dtt.getMO_TA());
			NGUONTANG nt = controler.getControl_NGUONTANG().get_NguonTang(dtt);
			if (nt != null) {
				text_MaDonvi.setText(nt.getMA_NGUONTANG() + "");
				text_Tendonvi.setText(nt.getTEN_NGUONTANG());
				text_Gioithieu.setText(nt.getGIOI_THIEU());
				text_Lienhe.setText(nt.getLIEN_HE());
			}
			tl = controler.getControl_TAISAN().get_TAISAN(dtt);
			break;
		case 3:
			dgt = (DOT_THUCHIEN_GIAM_TAISAN) Congviec;
			text_LoaiCongviec.setText("Thanh lý - Bàn giao Phương tiện tài sản");
			text_TenCongviec.setText(dgt.getTEN_DOT_GIAM());
			// setDate_DateTime(gdth.getTHOI_DIEM_HOAN_THANH(),
			// dateTime_Congviec_Ketthuc);
			text_Congviec_Ketthuc.setText(mdf.getViewStringDate(gdth.getTHOI_DIEM_HOAN_THANH()));
			TongsoNgay = mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_HOAN_THANH());
			if (dgt.getMO_TA() != null)
				text_Mota.setText(dgt.getMO_TA());
			NGUONGIAM ng = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
			if (ng != null) {
				text_MaDonvi.setText(ng.getMA_NGUONGIAM() + "");
				text_Tendonvi.setText(ng.getTEN_NGUONGIAM());
				text_Gioithieu.setText(ng.getGIOI_THIEU());
				text_Lienhe.setText(ng.getLIEN_HE());
			}
			tl = controler.getControl_TAISAN().get_TAISAN(dgt);
			grpKiemTraNghiemThu.dispose();
			grpQuyetToan.dispose();
			table_Nghiemthu.dispose();
			table_Quyettoan.dispose();
			break;
		default:
			break;
		}
		text_TongngayThuchien.setText(TongsoNgay + "");
		fill_DanhsachTaisan(tl);
	}

	private void fill_DanhsachTaisan(ArrayList<TAISAN> tl) {
		tree_Danhsach_Ptts.removeAll();
		if (tl != null) {
			int i = 1;
			for (TAISAN ts : tl) {
				TreeItem til = new TreeItem(tree_Danhsach_Ptts, SWT.NONE);
				til.setText(new String[] { i + "", ts.getTEN_TAISAN(), ts.getMODEL(), ts.getSERI(),
						ts.getMA_TAISAN() + "" });
				til.setData(ts);
				i++;
			}
		}

	}

	private void fill_Congviec_SetPHONGBAN(DE_XUAT dx) throws SQLException {
		PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
		combo_DonviDexuat.setText(pb.getTEN_PHONGBAN());
	}

	void fillPhanviec(DE_XUAT dx, GIAI_DOAN_THUC_HIEN gdth, GIAI_DOAN_NGHIEM_THU gdnt, GIAI_DOAN_QUYET_TOAN gdqt)
			throws SQLException {
		if (dx != null) {
			text_Sodexuat.setText(dx.getSODEXUAT());
			setDate_DateTime(dx.getTHOI_DIEM_BAT_DAU(), dateTime_Dexuat_Tiepnhan);
			setDate_DateTime(dx.getTHOI_DIEM_CHUYEN_GIAO(), dateTime_Dexuat_Chuyengiao);
			setDate_DateTime(dx.getTHOI_DIEM_HOAN_THANH(), dateTime_Dexuat_Hoanthanh);
			mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), dx.getTHOI_DIEM_HOAN_THANH());
			text_Dexuat_Tongngay
					.setText(mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), dx.getTHOI_DIEM_HOAN_THANH()) + "");
			if (dx.getGHI_CHU() != null)
				text_Dexuat_Ghichu.setText(dx.getGHI_CHU());
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN());
			if (nd != null) {
				TableItem tbi = new TableItem(table_Dexuat, SWT.NONE);
				tbi.setText(new String[] { nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
						mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()) });
			}
		}
		if (gdth != null) {
			setDate_DateTime(gdth.getTHOI_DIEM_BAT_DAU(), dateTime_Thuchien_Tiepnhan);
			setDate_DateTime(gdth.getTHOI_DIEM_CHUYEN_GIAO(), dateTime_Thuchien_Chuyengiao);
			setDate_DateTime(gdth.getTHOI_DIEM_HOAN_THANH(), dateTime_Thuchien_Hoanthanh);
			mdf.getTongNgaythuchien(gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_HOAN_THANH());
			text_Thuchien_Tongngay
					.setText(mdf.getTongNgaythuchien(gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_HOAN_THANH()) + "");
			if (gdth.getGHI_CHU() != null)
				text_Dexuat_Ghichu.setText(gdth.getGHI_CHU());
			ArrayList<NGUOIDUNG_THUCHIEN> ndthl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(gdth);
			table_Thuchien.removeAll();
			if (ndthl != null) {
				for (NGUOIDUNG_THUCHIEN ndth : ndthl) {
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
					TableItem tbi = new TableItem(table_Thuchien, SWT.NONE);
					tbi.setText(new String[] { nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
							mdf.getViewStringDate(ndth.getNGAY_THAM_GIA()) });
				}
			}
		}
		if (gdnt != null) {
			setDate_DateTime(gdnt.getTHOI_DIEM_TIEP_NHAN(), dateTime_Nghiemthu_Tiepnhan);
			setDate_DateTime(gdnt.getTHOI_DIEM_CHUYEN_GIAO(), dateTime_Nghiemthu_Chuyengiao);
			setDate_DateTime(gdnt.getTHOI_DIEM_KET_THUC(), dateTime_Nghiemthu_Hoanthanh);
			text_Nghiemthu_Tongngay
					.setText(mdf.getTongNgaythuchien(gdnt.getTHOI_DIEM_TIEP_NHAN(), gdnt.getTHOI_DIEM_KET_THUC()) + "");
			if (gdnt.getGHI_CHU() != null)
				text_Dexuat_Ghichu.setText(gdnt.getGHI_CHU());
			ArrayList<NGUOIDUNG_NGHIEMTHU> ndntl = controler.getControl_NGHIEMTHU_CANBO()
					.get_AllNGUOIDUNG_NGHIEMTHU(gdnt);
			table_Nghiemthu.removeAll();
			if (ndntl != null) {
				for (NGUOIDUNG_NGHIEMTHU ndnt : ndntl) {
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndnt.getTEN_TAI_KHOAN());
					TableItem tbi = new TableItem(table_Nghiemthu, SWT.NONE);
					tbi.setText(new String[] { nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
							mdf.getViewStringDate(ndnt.getNGAY_THAM_GIA()) });
				}
			}
		}
		if (gdqt != null) {
			setDate_DateTime(gdqt.getTHOI_DIEM_TIEP_NHAN(), dateTime_Quyettoan_Tiepnhan);
			setDate_DateTime(gdqt.getTHOI_GIAN_KET_THUC(), dateTime_Quyettoan_Hoanthanh);
			text_Quyettoan_Tongngay
					.setText(mdf.getTongNgaythuchien(gdqt.getTHOI_DIEM_TIEP_NHAN(), gdqt.getTHOI_GIAN_KET_THUC()) + "");
			if (gdqt.getGHI_CHU() != null)
				text_Dexuat_Ghichu.setText(gdqt.getGHI_CHU());
			ArrayList<NGUOIDUNG_QUYETTOAN> ndqtl = controler.getControl_QUYETTOAN_CANBO()
					.get_AllNGUOIDUNG_QUYETTOAN(gdqt);
			table_Quyettoan.removeAll();
			if (ndqtl != null) {
				for (NGUOIDUNG_QUYETTOAN ndqt : ndqtl) {
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndqt.getTEN_TAI_KHOAN());
					TableItem tbi = new TableItem(table_Quyettoan, SWT.NONE);
					tbi.setText(new String[] { nd.getTEN_CAN_BO(), nd.getTEN_TAI_KHOAN(),
							mdf.getViewStringDate(ndqt.getNGAY_THAM_GIA()) });
				}
			}
		}
	};

	void setDate_DateTime(Date date, DateTime datetime) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			datetime.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		} else {
			System.out.println("Ngày tháng rỗng");
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
}
