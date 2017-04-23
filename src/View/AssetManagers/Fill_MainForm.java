package View.AssetManagers;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.TAISAN;
import DAO.TAPHOSO;
import View.AssetManagers.Wait.Wait;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

public class Fill_MainForm {
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private Shell shell;
	private Wait w;
	private ThongkeDanhsachPhuongtienTaisan tkdsptts;
	private static Log log = LogFactory.getLog(Fill_MainForm.class);

	public Fill_MainForm(NGUOIDUNG user, Shell shell) {
		controler = new Controler(user);
		this.shell = shell;
	}

	public void getData_viewMainForm_Lichsu_Suachua(Integer MaTaiSan, Table table) throws Exception {
		table.clearAll();
		table.removeAll();
		if (table.getListeners(SWT.SetData) != null)
			for (Listener l : table.getListeners(SWT.SetData)) {
				table.removeListener(SWT.SetData, l);
			}
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> cviec_list = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(MaTaiSan);
		if (cviec_list != null) {
			int COUNT = cviec_list.size();
			table.addListener(SWT.SetData, new Listener() {
				private DOT_THUCHIEN_SUACHUA_BAODUONG c;

				public void handleEvent(Event event) {
					try {
						TableItem parent = (TableItem) event.item;
						int index = event.index;
						c = cviec_list.get(index);
						DE_XUAT dx;
						dx = controler.getControl_DEXUAT().get_DEXUAT(c);
						GIAI_DOAN_QUYET_TOAN q = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(c);
						parent.setText(0, "" + (index + 1));
						parent.setText(1, ((c == null) ? "-" : c.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG()));
						parent.setText(2, dx == null ? "-" : mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
						if (q != null)
							parent.setText(3, q.getTHOI_GIAN_KET_THUC() == null ? "chưa hoàn thành"
									: mdf.getViewStringDate(q.getTHOI_GIAN_KET_THUC()));
						else
							parent.setText(3, "-");
						parent.setData(c);
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			});

			table.setItemCount(COUNT);
		}
		for (TableColumn tc : table.getColumns())
			tc.pack();
	}

	public void loadData_ViewTaiSan_MainForm(Tree tree, ArrayList<TAISAN> row, String ItemIndex, boolean Namconlai,
			boolean Giatriconlai) {
		if (tree.isVisible() && row != null) {
			if (row.size() > 1000) {
				if (w != null)
					w.dispose();
				w = new Wait(shell.getDisplay());
				new FormTemplate().setRightScreen(w.getShell());
				w.open();
			}
			if (tree != null) {
				if (tkdsptts != null) {
					try {
						tkdsptts.setField(row);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (tree.getListeners(SWT.SetData) != null)
					for (Listener lst : tree.getListeners(SWT.SetData)) {
						tree.removeListener(SWT.SetData, lst);
					}
				tree.removeAll();
				int COUNT = row.size();
				if (COUNT > 0) {
					tree.addListener(SWT.SetData, new Listener() {
						public void handleEvent(Event event) {
							TreeItem parent = (TreeItem) event.item;
							int index = event.index;
							TAISAN t = row.get(index);
							String Namconlai_Str = "";
							String Giatriconlai_Str = "";
							if (Namconlai || Giatriconlai) {
								try {
									int Thoigiansudung = 0;
									double TileHaomon = 0;
									int PHANNHOM = controler.getControl_NHOMTAISAN_CAP_V()
											.getPHANNHOM(t.getMA_NHOMTAISAN_CAP_V());
									switch (PHANNHOM) {
									case 0:
										NHOMTAISAN_CAP_V nc5 = controler.getControl_NHOMTAISAN_CAP_V()
												.getNHOMTAISAN_CAP_V(t.getMA_NHOMTAISAN_CAP_V());
										Thoigiansudung = nc5.getTHOIGIAN_SUDUNG();
										TileHaomon = nc5.getTILE_HAOMON();
										break;
									case 1:
										NHOM_TAISANCODINH_VOHINH ctv = controler.getControl_NHOM_TAISANCODINH_VOHINH()
												.getNHOM_TAISANCODINH_VOHINH(t.getMA_NHOMTAISAN_CAP_V());
										Thoigiansudung = ctv.getTHOIGIAN_SUDUNG();
										TileHaomon = ctv.getTILE_HAOMON();
										break;
									case 2:
										NHOM_TAISANCODINH_DACTHU ctt = controler.getControl_NHOM_TAISANCODINH_DACTHU()
												.getNHOM_TAISANCODINH_DACTHU(t.getMA_NHOMTAISAN_CAP_V());
										Thoigiansudung = ctt.getTHOIGIAN_SUDUNG();
										TileHaomon = ctt.getTILE_HAOMON();
										break;
									case 3:
										NHOM_TAISANCODINH_DACBIET ctb = controler.getControl_NHOM_TAISANCODINH_DACBIET()
												.getNHOM_TAISANCODINH_DACBIET(t.getMA_NHOMTAISAN_CAP_V());
										Thoigiansudung = ctb.getTHOIGIAN_SUDUNG();
										TileHaomon = ctb.getGIA_QUYUOC();
										break;
									default:
										break;
									}
									int Nam = mdf.daysBetween(t.getNGAY_SU_DUNG(), new Date());
									if (Namconlai) {
										Namconlai_Str = (new DecimalFormat("##.#")
												.format((double) ((double) ((Thoigiansudung * 365) - Nam) / 365)))
												+ " năm";
									}
									if (Giatriconlai) {
										double PhanHaomon = (Nam / 365) * TileHaomon;
										double conlai = 100 - PhanHaomon;
										Giatriconlai_Str = conlai >= 0
												? (new DecimalFormat("##.##").format(conlai) + "%") : "0%";
										Color gray = null;
										if (conlai < 10) {
											gray = new Color(tree.getDisplay(), 255, 128, 128);
										} else if (conlai < 20) {
											gray = new Color(tree.getDisplay(), 255, 153, 153);
										} else if (conlai < 30) {
											gray = new Color(tree.getDisplay(), 255, 179, 179);
										} else if (conlai < 40) {
											gray = new Color(tree.getDisplay(), 255, 204, 204);
										} else if (conlai < 50) {
											gray = new Color(tree.getDisplay(), 153, 204, 255);
										} else if (conlai < 60) {
											gray = new Color(tree.getDisplay(), 179, 217, 255);
										} else if (conlai < 70) {
											gray = new Color(tree.getDisplay(), 153, 255, 102);
										} else if (conlai < 80) {
											gray = new Color(tree.getDisplay(), 170, 255, 128);
										} else if (conlai < 90) {
											gray = new Color(tree.getDisplay(), 187, 255, 153);
										} else if (conlai < 100) {
											gray = new Color(tree.getDisplay(), 204, 255, 179);
										}
										parent.setBackground(gray);
									}

								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							parent.setText(new String[] { (index + 1) + "", t.getTEN_TAISAN(),
									t.getMODEL().equals("null") ? "--" : t.getMODEL(),
									t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
									t.getSERI().equals("null") ? "--" : t.getSERI(), t.getMA_TAISAN() + "",
									Namconlai_Str, Giatriconlai_Str });
							parent.setData(t);
							ArrayList<PHUKIEN> Childrow = t.getPhukienList();
							if (Childrow != null) {
								for (int i = 0; i < Childrow.size(); i++) {
									PHUKIEN pk = Childrow.get(i);
									TreeItem child = new TreeItem(parent, SWT.NONE);
									child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(),
											pk.getMODEL().equals("null") ? "--" : pk.getMODEL(), "", "",
											pk.getSERI().equals("null") ? "--" : pk.getSERI(),
											String.valueOf(pk.getMA_PHUKIEN()) });
									child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
								}
							}
							parent.setExpanded(true);
						}
					});
				}
				tree.setItemCount(COUNT);
				if (COUNT < 1000)
					for (TreeColumn tc : tree.getColumns()) {
						if (tc.getWidth() > 0)
							tc.pack();
					}

				if (ItemIndex != null) {
					int index = Integer.valueOf(ItemIndex);
					if (index > 0 && index < tree.getItemCount()) {
						TreeItem treeItem = tree.getItem(index);
						tree.setSelection(treeItem);
					}
				}
			}

		} else {
			tree.removeAll();
		}
		if (w != null)
			w.dispose();
	}

	public void fillDataXemay(Table table_Xemay, ArrayList<TAISAN> tl) throws SQLException {
		table_Xemay.removeAll();
		if (tl != null) {
			int i = 1;
			for (TAISAN t : tl) {
				TableItem ti = new TableItem(table_Xemay, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(t.getPhuongtien_Giaothong().getMA_LOAI_XE());
				ti.setText(new String[] { "" + i, t.getTEN_TAISAN(), t.getPhuongtien_Giaothong().getBIENSO(),
						lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), t.getPhuongtien_Giaothong().getSOKHUNG(),
						t.getPhuongtien_Giaothong().getSOMAY() });
				ti.setData(t);
				i++;
			}
		}
	}

	public void fillDataOto(Table table_Oto, ArrayList<TAISAN> tl) throws SQLException {
		table_Oto.removeAll();
		if (tl != null) {
			int i = 1;
			for (TAISAN t : tl) {
				TableItem ti = new TableItem(table_Oto, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(t.getPhuongtien_Giaothong().getMA_LOAI_XE());
				ti.setText(new String[] { "" + i, t.getTEN_TAISAN(), t.getPhuongtien_Giaothong().getBIENSO(),
						lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), t.getPhuongtien_Giaothong().getSOKHUNG(),
						t.getPhuongtien_Giaothong().getSOMAY() });
				ti.setData(t);
				i++;
			}
		}
	}

	public void fillDataHosoGanday(Table table_Hoatdongganday) throws SQLException {
		table_Hoatdongganday.removeAll();
		Date thiss = new Date();
		Date last = new MyDateFormat().addDate(thiss, -60);
		ArrayList<TAPHOSO> thss = controler.getControl_TAPHOSO().get_All_TAPHOSO(last, thiss, "");
		int i = 0;
		for (TAPHOSO tap_HO_SO : thss) {
			TableItem ti = new TableItem(table_Hoatdongganday, SWT.NONE);
			ti.setText(new String[] { i + "", mdf.getViewStringDate(tap_HO_SO.getNGAY_TAO_TAPHOSO()),
					tap_HO_SO.getTEN_TAPHOSO(), tap_HO_SO.getGIOITHIEU_TAPHOSO() });
			i++;
			ti.setData(tap_HO_SO);
		}
	}

	public void fillDataCongviecDangThuchien(Tree tree_CongviecDangtrienkhai) throws SQLException {
		tree_CongviecDangtrienkhai.removeAll();
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_DangThucHien_DotTangTaisan();
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DangThucHien_Suachua_Baoduong();
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
				.get_DangThucHien_DotGiamTaisan();
		int i = 1;
		for (DOT_THUCHIEN_TANG_TAISAN dtt : dttl) {
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
			TreeItem ti = new TreeItem(tree_CongviecDangtrienkhai, SWT.NONE);
			ti.setText(new String[] { i + "", "Mua sắm, tiếp nhận tài sản",
					dx != null ? mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()) : "--" });
			i++;
			ti.setData(dtt);
		}

		for (DOT_THUCHIEN_SUACHUA_BAODUONG dsb : dsbl) {
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);
			TreeItem ti = new TreeItem(tree_CongviecDangtrienkhai, SWT.NONE);
			ti.setText(new String[] { i + "", "Sửa chữa, bảo dưỡng tài sản",
					dx != null ? mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()) : "--" });
			i++;
			ti.setData(dsb);
		}

		for (DOT_THUCHIEN_GIAM_TAISAN dgt : dgtl) {
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
			TreeItem ti = new TreeItem(tree_CongviecDangtrienkhai, SWT.NONE);
			ti.setText(new String[] { i + "", "Thanh lý tài sản",
					dx != null ? mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()) : "--" });
			i++;
			ti.setData(dgt);
		}
	}

	public void fillPartCongviec(Object o, Tree tree_Dexuat, Tree tree_Thuchien, Tree tree_Nghiemthu,
			Tree tree_Quyettoan) throws SQLException {
		tree_Dexuat.removeAll();
		tree_Thuchien.removeAll();
		tree_Nghiemthu.removeAll();
		tree_Quyettoan.removeAll();
		DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(o);
		if (dx != null) {
			TreeItem ti = new TreeItem(tree_Dexuat, SWT.NONE);
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN());
			ti.setText(new String[] { dx.getSODEXUAT(), nd.getTEN_CAN_BO(),
					dx.getTHOI_DIEM_BAT_DAU() == null ? "--" : mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()),
					dx.getTHOI_DIEM_CHUYEN_GIAO() == null ? "--" : mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()),
					dx.getTHOI_DIEM_HOAN_THANH() == null ? "--"
							: mdf.getViewStringDate(dx.getTHOI_DIEM_HOAN_THANH()) });
			ti.setData(dx);
		}
		GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(o);
		if (gdth != null) {
			ArrayList<NGUOIDUNG_THUCHIEN> ndl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(gdth);
			if (ndl != null)
				for (NGUOIDUNG_THUCHIEN nguoidung_THUCHIEN : ndl) {
					TreeItem ti = new TreeItem(tree_Thuchien, SWT.NONE);
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG()
							.get_NGUOIDUNG(nguoidung_THUCHIEN.getTEN_TAI_KHOAN());
					ti.setText(new String[] { gdth.getMA_GIAI_DOAN_THUC_HIEN() + "", nd.getTEN_CAN_BO(),
							gdth.getTHOI_DIEM_BAT_DAU() == null ? "--"
									: mdf.getViewStringDate(gdth.getTHOI_DIEM_BAT_DAU()),
							gdth.getTHOI_DIEM_CHUYEN_GIAO() == null ? "--"
									: mdf.getViewStringDate(gdth.getTHOI_DIEM_CHUYEN_GIAO()),
							gdth.getTHOI_DIEM_HOAN_THANH() == null ? "--"
									: mdf.getViewStringDate(gdth.getTHOI_DIEM_HOAN_THANH()) });
					ti.setData(dx);
				}
		}
		GIAI_DOAN_NGHIEM_THU gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(o);
		if (gdnt != null) {
			ArrayList<NGUOIDUNG_NGHIEMTHU> ndl = controler.getControl_NGHIEMTHU_CANBO()
					.get_AllNGUOIDUNG_NGHIEMTHU(gdnt);
			if (ndl != null)
				for (NGUOIDUNG_NGHIEMTHU nguoidung_NGHIEMTHU : ndl) {
					TreeItem ti = new TreeItem(tree_Nghiemthu, SWT.NONE);
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG()
							.get_NGUOIDUNG(nguoidung_NGHIEMTHU.getTEN_TAI_KHOAN());
					ti.setText(new String[] { gdnt.getMA_GIAI_DOAN_NGHIEM_THU() + "", nd.getTEN_CAN_BO(),
							gdnt.getTHOI_DIEM_TIEP_NHAN() == null ? "--"
									: mdf.getViewStringDate(gdnt.getTHOI_DIEM_TIEP_NHAN()),
							gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null ? "--"
									: mdf.getViewStringDate(gdnt.getTHOI_DIEM_CHUYEN_GIAO()),
							gdnt.getTHOI_DIEM_KET_THUC() == null ? "--"
									: mdf.getViewStringDate(gdnt.getTHOI_DIEM_KET_THUC()) });
					ti.setData(dx);
				}
		}
		GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(o);
		if (gdth != null) {
			ArrayList<NGUOIDUNG_QUYETTOAN> ndl = controler.getControl_QUYETTOAN_CANBO()
					.get_AllNGUOIDUNG_QUYETTOAN(gdqt);
			if (ndl != null)
				for (NGUOIDUNG_QUYETTOAN nguoidung_QUYETTOAN : ndl) {
					TreeItem ti = new TreeItem(tree_Quyettoan, SWT.NONE);
					NGUOIDUNG nd = controler.getControl_NGUOIDUNG()
							.get_NGUOIDUNG(nguoidung_QUYETTOAN.getTEN_TAI_KHOAN());
					ti.setText(new String[] { gdqt.getMA_GIAI_DOAN_QUYET_TOAN() + "", nd.getTEN_CAN_BO(),
							gdqt.getTHOI_DIEM_TIEP_NHAN() == null ? "--"
									: mdf.getViewStringDate(gdqt.getTHOI_DIEM_TIEP_NHAN()),
							gdqt.getTHOI_GIAN_KET_THUC() == null ? "--"
									: mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()) });
					ti.setData(dx);
				}
		}
	}

	public void setThongkeDanhsach(ThongkeDanhsachPhuongtienTaisan tkdsptts) {
		this.tkdsptts = tkdsptts;
	}

	public void getData_viewMainForm_Lichsu_Chuyengiao_Noibo(int ma_TAISAN, Table table_LichsuChuyengiaoNoibo)
			throws SQLException {
		table_LichsuChuyengiaoNoibo.clearAll();
		table_LichsuChuyengiaoNoibo.removeAll();
		if (table_LichsuChuyengiaoNoibo.getListeners(SWT.SetData) != null)
			for (Listener l : table_LichsuChuyengiaoNoibo.getListeners(SWT.SetData)) {
				table_LichsuChuyengiaoNoibo.removeListener(SWT.SetData, l);
			}
		ArrayList<DOT_BANGIAO_TAISAN_NOIBO> cviec_list = controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
				.get_DOT_BANGIAO_TAISAN_NOIBO_list(ma_TAISAN);
		if (cviec_list != null) {
			int COUNT = cviec_list.size();
			table_LichsuChuyengiaoNoibo.addListener(SWT.SetData, new Listener() {
				private DOT_BANGIAO_TAISAN_NOIBO c;

				public void handleEvent(Event event) {
					try {
						TableItem ti = (TableItem) event.item;
						int index = event.index;
						c = cviec_list.get(index);
						PHONGBAN bengiao = controler.getControl_PHONGBAN().get_PHONGBAN(c.getBEN_GIAO());
						PHONGBAN bennhan = controler.getControl_PHONGBAN().get_PHONGBAN(c.getBEN_NHAN());
						ti.setText(0, "" + (mdf.getViewStringDate(c.getNGAY_THUCHIEN())));
						ti.setText(1, ((c == null) ? "-" : bengiao.getTEN_PHONGBAN()));
						ti.setText(2, ((c == null) ? "-" : bennhan.getTEN_PHONGBAN()));
						ti.setData(c);
					} catch (SQLException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			});

			table_LichsuChuyengiaoNoibo.setItemCount(COUNT);
		}
		// for (TableColumn tc : table_LichsuChuyengiaoNoibo.getColumns())
		// tc.pack();
	}

	public void fillMuasamGanday(Table table_muasamganday) throws SQLException {
		table_muasamganday.removeAll();
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_AndFind_DOT_THUCHIEN_TANG_TAISAN_list(mdf.addDate(new Date(), -360), new Date(), "");
		for (DOT_THUCHIEN_TANG_TAISAN dtt : dttl) {
			TableItem ti = new TableItem(table_muasamganday, SWT.NONE);
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
			PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
			GIAI_DOAN_QUYET_TOAN gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
			String time = gdqt.getTHOI_GIAN_KET_THUC() == null ? "--"
					: mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC());
			ti.setText(new String[] { new Fill_ItemData().getStringHinhthucMuasam(dtt.getLY_DO_TANG()), time,
					pb.getTEN_PHONGBAN(), dtt.getTEN_DOT_TANG() });
			ti.setData(dtt);
		}

	}

}
