package View.AssetManagers.CongViec.CongViecCuatoi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Text;
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
import DAO.NGUONGIAM;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.NGUONTANG;
import DAO.PHUKIEN;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.AssetManagers.CongViec.Baoduong._2_Taodot_Baoduong;
import View.AssetManagers.CongViec.Suachua._2_Taodot_Suachua;
import View.AssetManagers.CongViec.TangTaiSan.XemDotTangtaisan;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.AssetManagers.Hoso.Vanban_View;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.AssetManagers.ThongBao.Library.ThongBao_Lib_Congviec;
import View.AssetManagers.ThongBao.Library.Thongbao_Lib_Hoso;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class CongViecCuaToi extends Shell {
	private Tree tree_Dangthuchien;
	private Tree tree_1;
	private Tree tree_LuocSuSuachua;
	private static NGUOIDUNG user;
	private Tree tree_CongviecDathuchien;
	private Text text_Nguon_Dathuchien;
	private Text text_GioiThieu_Dathuchien;
	private Text text_Lienhe_Dathuchien;
	private Text text_Nguon;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private Tree tree_Tiepnhan;
	private Text text_6;
	private Table table;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Table table_1;
	private Text text_12;
	private Text text_13;
	private Table table_2;
	private Text text_11;
	private Text text_14;
	private Text text_15;
	private Table table_3;
	private Text text_16;
	private Text text_17;
	private TabItem tbtmNguonsuachua;
	private Text text_Congviectruoc_TenPhanviec;
	private Text text_Congviectruoc_Ngaybatdau;
	private Text text_Congviectruoc_Ngaychuyengiao;
	private Text text_Congviectruoc_Tongsongay;
	private Text text_Congviectruoc_Ghichu;
	private Text text_Ngaythamgia;
	private Text text_Ngaythuchien;
	private Text text_Ngaychuyengiao;
	private Text text_Ghichu_Phanviec;
	private Text text_Sodexuat;
	private Text text_Donvidexuat;
	private Text text_Ghichudexuat;
	private Text text_ngayHoanthanhxulydexuat;
	private Text text_CanboXuly;
	private Text text_NgaynhanVB;
	private Text text_Ngaythangvanban;
	private Tree tree_HosoPhanviec;
	private Tree tree_Hoso_PhanviecTuoc;
	private Text text_DukienThuchien;
	private Group grpNgunThamGia;
	private Tree tree_Hoso_Dathuchien;
	private TabFolder tabFolder;
	private Button btnTiepNhan;
	private Button btnTraLai;
	private Button btnChuyengiao;
	private Button btnNgungChuyenGiao;
	private final int ItemHeight = 21;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(CongViecCuaToi.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			CongViecCuaToi shell = new CongViecCuaToi(display, user);
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
	 * Open the window.
	 * 
	 * @throws SQLException
	 */
	public CongViecCuaToi(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Actions-view-calendar-tasks-icon (1).png"));
		CongViecCuaToi.user = user;
		controler = new Controler(user);
		this.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);

		ToolItem tltmHSLu = new ToolItem(toolBar, SWT.NONE);
		tltmHSLu.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Places-folder-documents-icon (1).png"));
		tltmHSLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tree_Dangthuchien.isVisible()) {
						TreeItem[] ti = tree_Dangthuchien.getSelection();
						if (ti.length > 0) {
							CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
							TAP_HO_SO ths = null;
							if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
								GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC();
								NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
										.getNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(), gdth);
								ths = Creat_TapHoso_if_Null(ndth.getMA_TAPHOSO());
								if (ths != null && ths.getMA_TAPHOSO() > 0)
									controler.getControl_THUCHIEN_CANBO().update_TAPHOSO(ndth, ths);
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
								GIAI_DOAN_NGHIEM_THU gdnt = (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC();
								NGUOIDUNG_NGHIEMTHU ndnt = controler.getControl_NGHIEMTHU_CANBO()
										.getNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(), gdnt);
								ths = Creat_TapHoso_if_Null(ndnt.getMA_TAPHOSO());
								if (ths != null)
									controler.getControl_NGHIEMTHU_CANBO().update_TAPHOSO(ndnt, ths);
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
								GIAI_DOAN_QUYET_TOAN gdqt = (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC();
								NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
										.getNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(), gdqt);
								ths = Creat_TapHoso_if_Null(ndqt.getMA_TAPHOSO());
								controler.getControl_QUYETTOAN_CANBO().update_TAPHOSO(ndqt, ths);
							}
							if (ths != null) {
								TAPHOSO_View b = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, false);
								b.open();

								int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL;
								MessageBox messageBox_Thongbao = new MessageBox(getShell(), style);
								messageBox_Thongbao.setText("Gửi thông điệp đến  Những người cùng tham gia");
								messageBox_Thongbao.setMessage(
										"Bạn muốn gửi lời nhắn [Cập nhật hồ sơ] đến Những  người cùng tham gia phần việc?");
								int rc = messageBox_Thongbao.open();

								switch (rc) {
								case SWT.OK:
									break;
								case SWT.CANCEL:
									break;
								case SWT.YES:
									Thongbao_Lib_Hoso tb = new Thongbao_Lib_Hoso(user);
									Gui_Thongbao_Congviec(p.getCONGVIEC(), p.getPHANVIEC(), tb);
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
						}
					} else if (tree_Hoso_Dathuchien.isVisible()) {
						TreeItem[] ti = tree_Hoso_Dathuchien.getSelection();
						if (ti.length > 0) {
							Object o = ti[0].getData();
							TAP_HO_SO ths = null;
							if (o instanceof NGUOIDUNG_THUCHIEN) {
								NGUOIDUNG_THUCHIEN ndth = (NGUOIDUNG_THUCHIEN) o;
								ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth.getMA_TAPHOSO());
							} else if (o instanceof NGUOIDUNG_NGHIEMTHU) {
								NGUOIDUNG_NGHIEMTHU ndnt = (NGUOIDUNG_NGHIEMTHU) o;
								ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndnt.getMA_TAPHOSO());
							} else if (o instanceof NGUOIDUNG_QUYETTOAN) {
								NGUOIDUNG_QUYETTOAN ndqt = (NGUOIDUNG_QUYETTOAN) o;
								ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndqt.getMA_TAPHOSO());
							}
							if (ths != null) {
								TAPHOSO_View b = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, true);
								b.open();
							}
						}
					} else if (tree_Hoso_PhanviecTuoc.isVisible()) {
						TreeItem[] til = tree_Hoso_PhanviecTuoc.getSelection();
						if (til.length > 0) {
							TAP_HO_SO ths = (TAP_HO_SO) til[0].getData();
							boolean view_mode = true;/* Xem */
							TAPHOSO_View thss = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, view_mode);
							thss.open();
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private TAP_HO_SO Creat_TapHoso_if_Null(int ma_TAPHOSO) throws SQLException {
				TAP_HO_SO ths = null;
				ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ma_TAPHOSO);
				if (ths == null) {
					ths = new TAP_HO_SO();
					int MA_TAPHOSO = controler.getControl_TAPHOSO().Create_TAP_HO_SO(ths);
					if (MA_TAPHOSO > 0) {
						ths.setMA_TAPHOSO(MA_TAPHOSO);
					}
				}
				return ths;
			}

			private void Gui_Thongbao_Congviec(Object congviec, Object phanviec, Thongbao_Lib_Hoso tb)
					throws SQLException {
				if (congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
					Gui_Thongbao_Phanviec_Congviec_Suachua_Baoduong((DOT_THUCHIEN_SUACHUA_BAODUONG) congviec, phanviec,
							tb);
				} else if (congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
					Gui_Thongbao_Phanviec_Congviec_Muasam((DOT_THUCHIEN_TANG_TAISAN) congviec, phanviec, tb);
				} else if (congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
					Gui_Thongbao_Phanviec_Congviec_Muasam((DOT_THUCHIEN_GIAM_TAISAN) congviec, phanviec, tb);
				}
			}

			private void Gui_Thongbao_Phanviec_Congviec_Muasam(DOT_THUCHIEN_GIAM_TAISAN congviec, Object phanviec,
					Thongbao_Lib_Hoso tb) throws SQLException {
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_GIAMTAISAN(congviec,
							(GIAI_DOAN_THUC_HIEN) phanviec);
				}
			}

			private void Gui_Thongbao_Phanviec_Congviec_Muasam(DOT_THUCHIEN_TANG_TAISAN congviec, Object phanviec,
					Thongbao_Lib_Hoso tb) throws SQLException {
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_TANGTAISAN(congviec,
							(GIAI_DOAN_THUC_HIEN) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_TANGTAISAN(congviec,
							(GIAI_DOAN_NGHIEM_THU) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_TANGTAISAN(congviec,
							(GIAI_DOAN_QUYET_TOAN) phanviec);
				}
			}

			private void Gui_Thongbao_Phanviec_Congviec_Suachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
					Object phanviec, Thongbao_Lib_Hoso tb) throws SQLException {
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					if (congviec.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_BAODUONG(congviec,
								(GIAI_DOAN_THUC_HIEN) phanviec);
					} else if (congviec.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_SUACHUA(congviec,
								(GIAI_DOAN_THUC_HIEN) phanviec);
					}
				} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
					if (congviec.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_BAODUONG(congviec,
								(GIAI_DOAN_NGHIEM_THU) phanviec);
					} else if (congviec.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_SUACHUA(congviec,
								(GIAI_DOAN_NGHIEM_THU) phanviec);
					}
				} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
					if (congviec.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_BAODUONG(congviec,
								(GIAI_DOAN_QUYET_TOAN) phanviec);
					} else if (congviec.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_SUACHUA(congviec,
								(GIAI_DOAN_QUYET_TOAN) phanviec);
					}

				}

			}
		});
		tltmHSLu.setText("Cập nhật Hồ sơ Công việc");

		ToolItem tltmBoCo = new ToolItem(toolBar, SWT.NONE);
		tltmBoCo.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/distributor-report-icon (1).png"));
		tltmBoCo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tree_Dangthuchien.isVisible()) {
						TreeItem[] ti = tree_Dangthuchien.getSelection();
						if (ti.length > 0) {
							CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
							if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
								String oldString = controler.getControl_THUCHIEN()
										.get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()).getGHI_CHU();
								String message = getDialogMessage(oldString);
								if (message != null)
									if (controler.getControl_THUCHIEN().update_Ghichu(
											controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()),
											message)) {
										text_Ghichu_Phanviec.setText(message);
									}
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
								String oldString = controler.getControl_NGHIEMTHU()
										.get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()).getGHI_CHU();
								String message = getDialogMessage(oldString);
								if (message != null)
									if (controler.getControl_NGHIEMTHU().update_Ghichu(
											controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()),
											message)) {
										text_Ghichu_Phanviec.setText(message);
									}
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
								String oldString = controler.getControl_QUYETTOAN()
										.get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()).getGHI_CHU();
								String message = getDialogMessage(oldString);
								if (message != null)
									if (controler.getControl_QUYETTOAN().update_Ghichu(
											controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()),
											message)) {
										text_Ghichu_Phanviec.setText(message);
									}
							}
							fillCongviecDangThuchien();
							int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL;
							MessageBox messageBox_Thongbao = new MessageBox(getShell(), style);
							messageBox_Thongbao.setText("Gửi thông điệp đến  Những người cùng tham gia");
							messageBox_Thongbao.setMessage(
									"Bạn muốn gửi lời nhắn [Cập nhật Ghi chú] đến Những  người cùng tham gia phần việc?");
							int rc = messageBox_Thongbao.open();

							switch (rc) {
							case SWT.OK:
								break;
							case SWT.CANCEL:
								break;
							case SWT.YES:
								Gui_Thongbao_CapnhatGhichu(p.getCONGVIEC(), p.getPHANVIEC());

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
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void Gui_Thongbao_CapnhatGhichu(Object congviec, Object phanviec) throws SQLException {
				Thongbao_Lib_Hoso tb = new Thongbao_Lib_Hoso(user);
				if (congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
					DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) congviec;
					Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong(d, phanviec, tb);
				} else if (congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
					Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong((DOT_THUCHIEN_TANG_TAISAN) congviec, phanviec,
							tb);
				} else if (congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
					if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
						Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong((DOT_THUCHIEN_GIAM_TAISAN) congviec,
								phanviec, tb);
					}
				}
			}

			private void Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong(DOT_THUCHIEN_GIAM_TAISAN congviec,
					Object phanviec, Thongbao_Lib_Hoso tb) throws SQLException {
				tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_GIAMTAISAN((DOT_THUCHIEN_GIAM_TAISAN) congviec,
						(GIAI_DOAN_THUC_HIEN) phanviec);
			}

			private void Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong(DOT_THUCHIEN_TANG_TAISAN congviec,
					Object phanviec, Thongbao_Lib_Hoso tb) throws SQLException {
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_TANGTAISAN(
							(DOT_THUCHIEN_TANG_TAISAN) congviec, (GIAI_DOAN_THUC_HIEN) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_TANGTAISAN(
							(DOT_THUCHIEN_TANG_TAISAN) congviec, (GIAI_DOAN_NGHIEM_THU) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
					tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_TANGTAISAN(
							(DOT_THUCHIEN_TANG_TAISAN) congviec, (GIAI_DOAN_QUYET_TOAN) phanviec);
				}
			}

			private void Gui_Thongbao_CapnhatGhichu_Congviec_Suachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG d,
					Object phanviec, Thongbao_Lib_Hoso tb) throws SQLException {
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					if (d.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_BAODUONG(d,
								(GIAI_DOAN_THUC_HIEN) phanviec);
					} else if (d.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_SUACHUA(d,
								(GIAI_DOAN_THUC_HIEN) phanviec);
					}
				} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
					if (d.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_BAODUONG(d,
								(GIAI_DOAN_NGHIEM_THU) phanviec);
					} else if (d.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_SUACHUA(d,
								(GIAI_DOAN_NGHIEM_THU) phanviec);
					}
				} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
					if (d.getSUACHUA_BAODUONG() == 1) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_BAODUONG(d,
								(GIAI_DOAN_QUYET_TOAN) phanviec);
					} else if (d.getSUACHUA_BAODUONG() == 2) {
						tb.create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_SUACHUA(d,
								(GIAI_DOAN_QUYET_TOAN) phanviec);
					}
				}
			}

			private String getDialogMessage(String oldString) {
				Report rp = new Report(getShell(), SWT.DIALOG_TRIM, oldString);
				rp.open();
				return rp.result;
			}
		});
		tltmBoCo.setText("Ghi chú phần việc");

		ToolItem tltmNgunSaCha = new ToolItem(toolBar, SWT.NONE);
		tltmNgunSaCha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] ti = tree_Dangthuchien.getSelection();
					if (ti.length > 0) {
						CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
						if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
							ChonNguonSuachua_Baoduong cnsb = new ChonNguonSuachua_Baoduong(getShell(), SWT.DIALOG_TRIM,
									user);
							cnsb.open();
							NGUONSUACHUA_BAODUONG nsb = cnsb.getResult();
							if (nsb != null && dsb != null)
								controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().update_Nguon_Suachua_Baoduong(nsb,
										dsb);
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {

						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {

						}
						fillCongviecDangThuchien();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmNgunSaCha.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/phone-icon.png"));
		tltmNgunSaCha.setText("Dịch vụ sử dụng");

		ToolItem tltmDKin = new ToolItem(toolBar, SWT.NONE);
		tltmDKin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (tree_Dangthuchien.isVisible()) {
						TreeItem[] ti = tree_Dangthuchien.getSelection();
						if (ti.length > 0) {
							CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
							if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
								GIAI_DOAN_THUC_HIEN gth = (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC();
								Date batdau = gth.getTHOI_DIEM_BAT_DAU();
								DukienNgayHoanthanh dk = new DukienNgayHoanthanh(getShell(), SWT.NONE, batdau);
								dk.open();
								int DukienThuchien = dk.result;
								if (DukienThuchien > 0) {
									controler.getControl_THUCHIEN().update_DukienThuchien(
											controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()),
											DukienThuchien);
								}
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
								GIAI_DOAN_NGHIEM_THU gth = (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC();
								Date batdau = gth.getTHOI_DIEM_TIEP_NHAN();
								DukienNgayHoanthanh dk = new DukienNgayHoanthanh(getShell(), SWT.NONE, batdau);
								dk.open();
								int DukienThuchien = dk.result;
								if (DukienThuchien > 0) {
									controler.getControl_NGHIEMTHU().update_DukienThuchien(
											controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()),
											DukienThuchien);
								}
							} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
								GIAI_DOAN_QUYET_TOAN gth = (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC();
								Date batdau = gth.getTHOI_DIEM_TIEP_NHAN();
								DukienNgayHoanthanh dk = new DukienNgayHoanthanh(getShell(), SWT.NONE, batdau);
								dk.open();
								int DukienThuchien = dk.result;
								if (DukienThuchien > 0) {
									controler.getControl_QUYETTOAN().update_DukienThuchien(
											controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()),
											DukienThuchien);
								}
							}
							fillCongviecDangThuchien();

						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmDKin.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Calendar-icon (1).png"));
		tltmDKin.setText("D\u1EF1 ki\u1EBFn th\u1EDDi gian tri\u1EC3n khai");
		tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int i = tabFolder.getSelectionIndex();
				switch (i) {
				case 0:
					DisableControlDangthuchien();
					EnableControlTiepnhan();
					break;
				case 1:
					DisableControlTiepnhan();
					EnableControlDangthuchien();
					break;
				case 2:
					break;
				default:
					break;
				}
			}

			private void DisableControlTiepnhan() {
				if (btnTiepNhan != null)
					btnTiepNhan.setEnabled(false);
			}

			private void EnableControlTiepnhan() {
				if (btnTiepNhan != null)
					btnTiepNhan.setEnabled(true);
			}

			private void EnableControlDangthuchien() {
				if (btnTraLai != null)
					btnTraLai.setEnabled(true);
				if (btnChuyengiao != null)
					btnChuyengiao.setEnabled(true);
				if (btnNgungChuyenGiao != null)
					btnNgungChuyenGiao.setEnabled(true);
			}

			private void DisableControlDangthuchien() {
				if (btnTraLai != null)
					btnTraLai.setEnabled(false);
				if (btnChuyengiao != null)
					btnChuyengiao.setEnabled(false);
				if (btnNgungChuyenGiao != null)
					btnNgungChuyenGiao.setEnabled(false);
			}
		});
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		CongViecCuaToi.user = user;

		TreeRowStyle ts = new TreeRowStyle();

		TabItem tbtmTipNhnPhn_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmTipNhnPhn_1.setText("Phần việc chờ tiếp nhận ");

		SashForm sashForm_7 = new SashForm(tabFolder, SWT.VERTICAL);
		tbtmTipNhnPhn_1.setControl(sashForm_7);

		tree_Tiepnhan = new Tree(sashForm_7, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Tiepnhan.setLinesVisible(true);
		tree_Tiepnhan.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_Tiepnhan, ItemHeight);
		tree_Tiepnhan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_Tiepnhan.getSelection();
					if (items.length > 0) {
						clearText_ThongTin_Congviec_Truoc();
						CONGVIEC_PHANVIEC e = (CONGVIEC_PHANVIEC) items[0].getData();
						Object CONGVIEC = e.getCONGVIEC();
						Object PHANVIEC = e.getPHANVIEC();
						if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC;
							if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
								DE_XUAT d = null;
								if (dsb != null) {
									d = controler.getControl_DEXUAT().get_DEXUAT(dsb);
								}
								String Header = "Đề xuất - Sửa chữa, Bảo dưỡng";
								Fill_Dexuat(Header, d);
							} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
								GIAI_DOAN_THUC_HIEN gdth = null;
								if (dsb != null) {
									gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
								}
								String Header = "Thực hiện - Sửa chữa, Bảo dưỡng";
								Fill_Hoso_THUCHIEN(Header, gdth);
							} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
								GIAI_DOAN_NGHIEM_THU gdnt = null;
								if (dsb != null) {
									gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
								}
								String Header = "Thực hiện - Sửa chữa, Bảo dưỡng";
								Fill_Hoso_NGHIEMTHU(Header, gdnt);
							}
						} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) CONGVIEC;
							if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
								DE_XUAT d = null;
								if (dtt != null) {
									d = controler.getControl_DEXUAT().get_DEXUAT(dtt);
								}
								String Header = "Đề xuất - Mua sắm, Tiếp nhận";
								Fill_Dexuat(Header, d);
							} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
								GIAI_DOAN_THUC_HIEN gdth = null;
								if (dtt != null) {
									gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
								}
								String Header = "Thực hiện - Mua sắm, Tiếp nhận";
								Fill_Hoso_THUCHIEN(Header, gdth);
							} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
								GIAI_DOAN_NGHIEM_THU gdnt = null;
								if (dtt != null) {
									gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
								}
								String Header = "Nghiệm thu - Mua sắm, Tiếp nhận";
								Fill_Hoso_NGHIEMTHU(Header, gdnt);
							}
						} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							if (e.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
								DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC;
								DE_XUAT d = null;
								if (dgt != null) {
									d = controler.getControl_DEXUAT().get_DEXUAT(dgt);

								}
								String Header = "Đề xuất - Thanh lý, Bàn giao";
								Fill_Dexuat(Header, d);
							}
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void Fill_Hoso_NGHIEMTHU(String header, GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
				if (gdnt != null) {
					text_Congviectruoc_TenPhanviec.setText(header);
					String Date2 = gdnt.getTHOI_DIEM_TIEP_NHAN() == null ? "Chưa bắt đầu"
							: mdf.getViewStringDate(gdnt.getTHOI_DIEM_TIEP_NHAN());
					text_Congviectruoc_Ngaybatdau.setText(Date2);
					String Date = gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa chuyển giao"
							: mdf.getViewStringDate(gdnt.getTHOI_DIEM_CHUYEN_GIAO());
					text_Congviectruoc_Ngaychuyengiao.setText(Date);
					boolean flag = true;
					if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null || gdnt.getTHOI_DIEM_TIEP_NHAN() == null)
						flag = false;
					if (flag) {
						int t = gdnt.getTHOI_DIEM_CHUYEN_GIAO().compareTo(gdnt.getTHOI_DIEM_TIEP_NHAN());
						text_Congviectruoc_Tongsongay.setText(String.valueOf(t));
					}
					text_Congviectruoc_Ghichu.setText(gdnt.getGHI_CHU() == null ? "" : gdnt.getGHI_CHU());
					loadHosoPhanviecTruoc(gdnt);
				}
			}

			private void Fill_Hoso_THUCHIEN(String header, GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
				if (gdth != null) {
					text_Congviectruoc_TenPhanviec.setText(header);
					String Date2 = gdth.getTHOI_DIEM_BAT_DAU() == null ? "Chưa bắt đầu"
							: mdf.getViewStringDate(gdth.getTHOI_DIEM_BAT_DAU());
					text_Congviectruoc_Ngaybatdau.setText(Date2);
					String Date = gdth.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa chuyển giao"
							: mdf.getViewStringDate(gdth.getTHOI_DIEM_CHUYEN_GIAO());
					text_Congviectruoc_Ngaychuyengiao.setText(Date);
					boolean flag = true;
					if (gdth.getTHOI_DIEM_CHUYEN_GIAO() == null || gdth.getTHOI_DIEM_BAT_DAU() == null)
						flag = false;
					if (flag) {
						int t = gdth.getTHOI_DIEM_CHUYEN_GIAO().compareTo(gdth.getTHOI_DIEM_BAT_DAU());
						text_Congviectruoc_Tongsongay.setText(String.valueOf(t));
					}
					text_Congviectruoc_Ghichu.setText(gdth.getGHI_CHU() == null ? "" : gdth.getGHI_CHU());
					loadHosoPhanviecTruoc(gdth);
				}
			}

			private void Fill_Dexuat(String header, DE_XUAT d) throws SQLException {
				if (d != null) {
					text_Congviectruoc_TenPhanviec.setText(header);
					String Date2 = d.getTHOI_DIEM_BAT_DAU() == null ? "Chưa bắt đầu"
							: mdf.getViewStringDate(d.getTHOI_DIEM_BAT_DAU());
					text_Congviectruoc_Ngaybatdau.setText(Date2);
					String Date = d.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa chuyển giao"
							: mdf.getViewStringDate(d.getTHOI_DIEM_CHUYEN_GIAO());
					text_Congviectruoc_Ngaychuyengiao.setText(Date);
					boolean flag = true;
					if (d.getTHOI_DIEM_CHUYEN_GIAO() == null || d.getTHOI_DIEM_BAT_DAU() == null)
						flag = false;
					if (flag) {
						int t = d.getTHOI_DIEM_CHUYEN_GIAO().compareTo(d.getTHOI_DIEM_BAT_DAU());
						text_Congviectruoc_Tongsongay.setText(String.valueOf(t));
					}
					text_Congviectruoc_Ghichu.setText(d.getGHI_CHU() == null ? "" : d.getGHI_CHU());
					loadHosoPhanviecTruoc(d);
				}
			}
		});
		TreeColumn trclmnStt_3 = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnStt_3.setWidth(50);
		trclmnStt_3.setText("STT");

		TreeColumn trclmnPhnVic_2 = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnPhnVic_2.setWidth(100);
		trclmnPhnVic_2.setText("PH\u1EA6N VI\u1EC6C");

		TreeColumn trclmnLoiCngVic_2 = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnLoiCngVic_2.setWidth(120);
		trclmnLoiCngVic_2.setText("LO\u1EA0I C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnTnCngVic_2 = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnTnCngVic_2.setWidth(100);
		trclmnTnCngVic_2.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnIdCngVic_2 = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnIdCngVic_2.setWidth(100);
		trclmnIdCngVic_2.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnNgyThamGia = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnNgyThamGia.setWidth(120);
		trclmnNgyThamGia.setText("NG\u00C0Y THAM GIA");

		Menu menu_2 = new Menu(tree_Tiepnhan);
		tree_Tiepnhan.setMenu(menu_2);

		MenuItem mntmTipNhn = new MenuItem(menu_2, SWT.NONE);
		mntmTipNhn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TiepNhan();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		mntmTipNhn.setText("Ti\u1EBFp nh\u1EADn");

		MenuItem mntmNewItem = new MenuItem(menu_2, SWT.NONE);
		mntmNewItem.setText("Xem hồ sơ phần việc trước");

		TreeColumn trclmnNewColumn = new TreeColumn(tree_Tiepnhan, SWT.NONE);
		trclmnNewColumn.setWidth(100);
		trclmnNewColumn.setText("TRẠNG THÁI");

		SashForm sashForm_8 = new SashForm(sashForm_7, SWT.NONE);

		TabFolder tabFolder_2 = new TabFolder(sashForm_8, SWT.NONE);

		TabItem tbtmPhngTinTi = new TabItem(tabFolder_2, SWT.NONE);
		tbtmPhngTinTi.setText("Thông tin phần việc trước");

		SashForm sashForm_10 = new SashForm(tabFolder_2, SWT.NONE);
		tbtmPhngTinTi.setControl(sashForm_10);

		Composite composite_4 = new Composite(sashForm_10, SWT.NONE);
		composite_4.setLayout(new GridLayout(2, false));

		Label lblTnPhnVic = new Label(composite_4, SWT.NONE);
		lblTnPhnVic.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnPhnVic.setText("Tên phần việc:");

		text_Congviectruoc_TenPhanviec = new Text(composite_4, SWT.NONE);
		text_Congviectruoc_TenPhanviec.setEditable(false);
		text_Congviectruoc_TenPhanviec.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyBtu_1 = new Label(composite_4, SWT.NONE);
		lblNgyBtu_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyBtu_1.setText("Ngày bắt đầu:");

		text_Congviectruoc_Ngaybatdau = new Text(composite_4, SWT.NONE);
		text_Congviectruoc_Ngaybatdau.setEditable(false);
		text_Congviectruoc_Ngaybatdau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyChuynGiao = new Label(composite_4, SWT.NONE);
		lblNgyChuynGiao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyChuynGiao.setText("Ngày chuyển giao:");

		text_Congviectruoc_Ngaychuyengiao = new Text(composite_4, SWT.NONE);
		text_Congviectruoc_Ngaychuyengiao.setEditable(false);
		text_Congviectruoc_Ngaychuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTongsongay = new Label(composite_4, SWT.NONE);
		lblTongsongay.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTongsongay.setText("Tổng số ngày:");

		text_Congviectruoc_Tongsongay = new Text(composite_4, SWT.NONE);
		text_Congviectruoc_Tongsongay.setEditable(false);
		text_Congviectruoc_Tongsongay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGhiCh_2 = new Label(composite_4, SWT.NONE);
		lblGhiCh_2.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblGhiCh_2.setText("Ghi chú:");

		text_Congviectruoc_Ghichu = new Text(composite_4, SWT.WRAP);
		text_Congviectruoc_Ghichu.setEditable(false);
		text_Congviectruoc_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tree_Hoso_PhanviecTuoc = new Tree(sashForm_10, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tree_Hoso_PhanviecTuoc.setLinesVisible(true);
		tree_Hoso_PhanviecTuoc.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_Hoso_PhanviecTuoc, ItemHeight);

		TreeColumn treeColumn = new TreeColumn(tree_Hoso_PhanviecTuoc, SWT.NONE);
		treeColumn.setWidth(55);
		treeColumn.setText("STT");

		TreeColumn trclmnTnHS = new TreeColumn(tree_Hoso_PhanviecTuoc, SWT.NONE);
		trclmnTnHS.setWidth(100);
		trclmnTnHS.setText("TÊN HỒ SƠ");

		TreeColumn trclmnSLngTh = new TreeColumn(tree_Hoso_PhanviecTuoc, SWT.NONE);
		trclmnSLngTh.setWidth(143);
		trclmnSLngTh.setText("MÔ TẢ");

		TreeColumn trclmnNgyTo = new TreeColumn(tree_Hoso_PhanviecTuoc, SWT.NONE);
		trclmnNgyTo.setWidth(100);
		trclmnNgyTo.setText("NGÀY TẠO");

		TreeColumn trclmnNgiTo = new TreeColumn(tree_Hoso_PhanviecTuoc, SWT.NONE);
		trclmnNgiTo.setWidth(100);
		trclmnNgiTo.setText("NGƯỜI TẠO");

		Menu menu_1 = new Menu(tree_Hoso_PhanviecTuoc);
		tree_Hoso_PhanviecTuoc.setMenu(menu_1);

		MenuItem mntmXem = new MenuItem(menu_1, SWT.NONE);
		mntmXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree_Hoso_PhanviecTuoc.getSelection();
					if (til.length > 0) {
						TAP_HO_SO ths = (TAP_HO_SO) til[0].getData();
						boolean view_mode = true;
						TAPHOSO_View thss = new TAPHOSO_View(getShell(), SWT.DIALOG_TRIM, user, ths, view_mode);
						thss.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXem.setText("Xem Tập hồ sơ");
		sashForm_10.setWeights(new int[] { 300, 479 });
		sashForm_8.setWeights(new int[] { 1 });
		sashForm_7.setWeights(new int[] { 1000, 618 });
		TabItem tbtmCngVicang = new TabItem(tabFolder, SWT.NONE);
		tbtmCngVicang.setText("C\u00F4ng vi\u1EC7c \u0111ang th\u1EF1c hi\u1EC7n");

		SashForm sashForm = new SashForm(tabFolder, SWT.VERTICAL);
		tbtmCngVicang.setControl(sashForm);

		SashForm sashForm_4 = new SashForm(sashForm, SWT.NONE);

		SashForm sashForm_1 = new SashForm(sashForm_4, SWT.VERTICAL);

		tree_Dangthuchien = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Dangthuchien.setLinesVisible(true);
		tree_Dangthuchien.setHeaderVisible(true);
		tree_Dangthuchien.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_Dangthuchien.getSelection();
					if (items.length > 0) {
						clearText_ThongTin_Congviec();
						CONGVIEC_PHANVIEC e = (CONGVIEC_PHANVIEC) items[0].getData();
						if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
							if (dsb != null) {
								setField_ThongTin_DE_XUAT(dsb);
								setField_ThongTin_NGUONSUACHUA_BAODUONG(dsb);
								setTree_Danhsach_TAISAN(dsb);
								setField_ThongTin_Congviec(e);
							}
						} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
							if (dtt != null) {
								setField_ThongTin_DE_XUAT(dtt);
								setField_ThongTin_NGUON_TANG(dtt);
								setTree_Danhsach_TAISAN(dtt);
								setField_ThongTin_Congviec(e);
							}
						} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) e.getCONGVIEC();
							if (dgt != null) {
								setField_ThongTin_DE_XUAT(dgt);

								setField_ThongTin_NGUON_GIAM(dgt);
								setTree_Danhsach_TAISAN(dgt);
								setField_ThongTin_Congviec(e);

							}
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		ts.setTreeItemHeight(tree_Dangthuchien, ItemHeight);
		TreeColumn trclmnStt = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnStt.setWidth(55);
		trclmnStt.setText("STT");

		TreeColumn trclmnPhnVic = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnPhnVic.setWidth(100);
		trclmnPhnVic.setText("PH\u1EA6N VI\u1EC6C");

		TreeColumn trclmnLoiCngVic = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnLoiCngVic.setWidth(120);
		trclmnLoiCngVic.setText("LO\u1EA0I C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnTnCngVic = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnTnCngVic.setWidth(100);
		trclmnTnCngVic.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnIdCngVic = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnIdCngVic.setWidth(100);
		trclmnIdCngVic.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnnV = new TreeColumn(tree_Dangthuchien, SWT.NONE);
		trclmnnV.setWidth(125);
		trclmnnV.setText("\u0110\u01A0N V\u1ECA \u0110\u1EC0 XU\u1EA4T");

		Menu menu_4 = new Menu(tree_Dangthuchien);
		tree_Dangthuchien.setMenu(menu_4);

		MenuItem mntmChuynGiao = new MenuItem(menu_4, SWT.NONE);
		mntmChuynGiao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChuyenGiao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmChuynGiao.setText("Chuyển giao");

		MenuItem mntmDungChuyenGiao = new MenuItem(menu_4, SWT.NONE);
		mntmDungChuyenGiao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DungChuyengiao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmDungChuyenGiao.setText("Dừng chuyển giao");

		MenuItem mntmTrLi = new MenuItem(menu_4, SWT.NONE);
		mntmTrLi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TraLai();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmTrLi.setText("Trả lại");
		sashForm_1.setWeights(new int[] { 223 });

		TabFolder tabFolder_4 = new TabFolder(sashForm_4, SWT.NONE);

		TabItem tbtmPhnVicang = new TabItem(tabFolder_4, SWT.NONE);
		tbtmPhnVicang.setText("Phần việc đang thực hiện");

		Composite composite_5 = new Composite(tabFolder_4, SWT.NONE);
		tbtmPhnVicang.setControl(composite_5);
		composite_5.setLayout(new GridLayout(2, false));

		Label label = new Label(composite_5, SWT.NONE);
		label.setText("Ngày Tham gia:");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_Ngaythamgia = new Text(composite_5, SWT.NONE);
		text_Ngaythamgia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Ngaythamgia.setEditable(false);

		Label label_9 = new Label(composite_5, SWT.NONE);
		label_9.setText("Ngày Bắt đầu:");
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_Ngaythuchien = new Text(composite_5, SWT.NONE);
		text_Ngaythuchien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_Ngaythuchien.setEditable(false);

		Label lblDKinngy = new Label(composite_5, SWT.NONE);
		lblDKinngy.setText("Dự kiến (ngày):");
		lblDKinngy.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_DukienThuchien = new Text(composite_5, SWT.NONE);
		text_DukienThuchien.setEditable(false);
		text_DukienThuchien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_10 = new Label(composite_5, SWT.NONE);
		label_10.setText("Ngày Chuyển giao:");
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_Ngaychuyengiao = new Text(composite_5, SWT.NONE);
		text_Ngaychuyengiao.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		text_Ngaychuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_Ngaychuyengiao.setEditable(false);

		Label label_11 = new Label(composite_5, SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_11.setText("Ghi chú: ");
		label_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_Ghichu_Phanviec = new Text(composite_5, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu_Phanviec.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		text_Ghichu_Phanviec.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));

		Group grpHSnh = new Group(composite_5, SWT.NONE);
		grpHSnh.setLayout(new GridLayout(1, false));
		grpHSnh.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		grpHSnh.setText("Hồ sơ đính kèm");

		tree_HosoPhanviec = new Tree(grpHSnh, SWT.BORDER | SWT.FULL_SELECTION);
		tree_HosoPhanviec.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree_HosoPhanviec.setLinesVisible(true);
		tree_HosoPhanviec.setHeaderVisible(true);

		TreeColumn trclmnStt_4 = new TreeColumn(tree_HosoPhanviec, SWT.NONE);
		trclmnStt_4.setWidth(50);
		trclmnStt_4.setText("STT");

		TreeColumn trclmnSVnBn = new TreeColumn(tree_HosoPhanviec, SWT.NONE);
		trclmnSVnBn.setWidth(100);
		trclmnSVnBn.setText("SỐ VĂN BẢN");

		TreeColumn trclmnNgyBanHnh = new TreeColumn(tree_HosoPhanviec, SWT.NONE);
		trclmnNgyBanHnh.setWidth(100);
		trclmnNgyBanHnh.setText("NGÀY BAN HÀNH");

		TreeColumn trclmnCQuanBan = new TreeColumn(tree_HosoPhanviec, SWT.NONE);
		trclmnCQuanBan.setWidth(130);
		trclmnCQuanBan.setText("CƠ QUAN BAN HÀNH");

		TreeColumn trclmnTrchYu = new TreeColumn(tree_HosoPhanviec, SWT.NONE);
		trclmnTrchYu.setWidth(160);
		trclmnTrchYu.setText("TRÍCH YẾU");

		Menu menu_3 = new Menu(tree_HosoPhanviec);
		tree_HosoPhanviec.setMenu(menu_3);

		MenuItem mntmXemVnBn = new MenuItem(menu_3, SWT.NONE);
		mntmXemVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] ti = tree_HosoPhanviec.getSelection();
					if (ti.length > 0) {
						VANBAN vb = (VANBAN) ti[0].getData();
						Vanban_View vbv = new Vanban_View(getShell(), SWT.NONE, user, null, vb, false);
						vbv.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemVnBn.setText("Xem văn bản");

		MenuItem mntmThmVnBn = new MenuItem(menu_3, SWT.NONE);
		mntmThmVnBn.setText("Thêm văn bản");

		TabItem tbtmThngTin_1 = new TabItem(tabFolder_4, SWT.NONE);
		tbtmThngTin_1.setText("Thông tin đề xuất");

		Composite composite_6 = new Composite(tabFolder_4, SWT.NONE);
		tbtmThngTin_1.setControl(composite_6);
		composite_6.setLayout(new GridLayout(2, false));

		Label label_13 = new Label(composite_6, SWT.NONE);
		label_13.setText("Số đề xuất:");

		text_Sodexuat = new Text(composite_6, SWT.NONE);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Sodexuat.setEditable(false);

		Label label_16 = new Label(composite_6, SWT.NONE);
		label_16.setText("Ngày tháng VB:");

		text_Ngaythangvanban = new Text(composite_6, SWT.NONE);
		text_Ngaythangvanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_Ngaythangvanban.setEditable(false);

		Label label_14 = new Label(composite_6, SWT.NONE);
		label_14.setText("Đơn vị ĐX:");

		text_Donvidexuat = new Text(composite_6, SWT.NONE);
		text_Donvidexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_Donvidexuat.setEditable(false);

		Label label_17 = new Label(composite_6, SWT.NONE);
		label_17.setText("Ngày nhận VB:");

		text_NgaynhanVB = new Text(composite_6, SWT.NONE);
		text_NgaynhanVB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_NgaynhanVB.setEditable(false);

		Label label_18 = new Label(composite_6, SWT.NONE);
		label_18.setText("Cán bộ xử lý:");

		text_CanboXuly = new Text(composite_6, SWT.NONE);
		text_CanboXuly.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_CanboXuly.setEditable(false);

		Label lblNgyChuynVb = new Label(composite_6, SWT.NONE);
		lblNgyChuynVb.setText("Ngày chuyển VB:");

		text_ngayHoanthanhxulydexuat = new Text(composite_6, SWT.NONE);
		text_ngayHoanthanhxulydexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text_ngayHoanthanhxulydexuat.setEditable(false);

		Label label_15 = new Label(composite_6, SWT.NONE);
		label_15.setText("Ghi chú:");

		text_Ghichudexuat = new Text(composite_6, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_Ghichudexuat = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 3);
		gd_text_Ghichudexuat.heightHint = 6;
		text_Ghichudexuat.setLayoutData(gd_text_Ghichudexuat);
		text_Ghichudexuat.setEditable(false);
		new Label(composite_6, SWT.NONE);
		new Label(composite_6, SWT.NONE);

		Label lblHS = new Label(composite_6, SWT.NONE);
		lblHS.setText("Hồ sơ:");

		Tree tree_3 = new Tree(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		tree_3.setLinesVisible(true);
		tree_3.setHeaderVisible(true);
		tree_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_4.setWeights(new int[] { 1000, 618 });

		SashForm sashForm_3 = new SashForm(sashForm, SWT.NONE);

		TabFolder tabFolder_1 = new TabFolder(sashForm_3, SWT.NONE);

		TabItem tbtmDanhSchTi = new TabItem(tabFolder_1, SWT.NONE);
		tbtmDanhSchTi.setText("Danh s\u00E1ch t\u00E0i s\u1EA3n");

		tree_1 = new Tree(tabFolder_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		tree_1.setLinesVisible(true);
		tree_1.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_1, ItemHeight);
		tbtmDanhSchTi.setControl(tree_1);

		TreeColumn trclmnStt_1 = new TreeColumn(tree_1, SWT.NONE);
		trclmnStt_1.setWidth(55);
		trclmnStt_1.setText("STT");

		TreeColumn trclmnTnPtts = new TreeColumn(tree_1, SWT.NONE);
		trclmnTnPtts.setWidth(100);
		trclmnTnPtts.setText("T\u00CAN PTTS");

		TreeColumn trclmnModel = new TreeColumn(tree_1, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("MODEL");

		TreeColumn trclmnNgySDng = new TreeColumn(tree_1, SWT.NONE);
		trclmnNgySDng.setWidth(100);
		trclmnNgySDng.setText("NG\u00C0Y S\u1EEC D\u1EE4NG");

		TreeColumn trclmnnV_1 = new TreeColumn(tree_1, SWT.NONE);
		trclmnnV_1.setWidth(100);
		trclmnnV_1.setText("\u0110\u01A0N V\u1ECA");

		TreeColumn trclmnSSri = new TreeColumn(tree_1, SWT.NONE);
		trclmnSSri.setWidth(100);
		trclmnSSri.setText("S\u1ED0 S\u00CA-RI");

		Menu menu = new Menu(tree_1);
		tree_1.setMenu(menu);

		MenuItem mntmCpNhtDanh = new MenuItem(menu, SWT.NONE);
		mntmCpNhtDanh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					TreeItem[] items = tree_Dangthuchien.getSelection();
					if (items.length > 0) {
						clearText_ThongTin_Congviec();
						CONGVIEC_PHANVIEC e = (CONGVIEC_PHANVIEC) items[0].getData();
						if (e.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
							if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
								DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
								DE_XUAT dx;
								dx = controler.getControl_DEXUAT().get_DEXUAT(d);

								if (d.getSUACHUA_BAODUONG() == 1) {
									_2_Taodot_Baoduong tdbd = new _2_Taodot_Baoduong(getDisplay(), user, getShell(), dx,
											2);
									tdbd.open();
								} else if (d.getSUACHUA_BAODUONG() == 2) {
									_2_Taodot_Suachua tdbd = new _2_Taodot_Suachua(getDisplay(), user, getShell(), dx,
											2);
									tdbd.open();
								}
							} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
								DOT_THUCHIEN_TANG_TAISAN dt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
								XemDotTangtaisan xdt = new XemDotTangtaisan(display, user, dt);
								xdt.open();
							} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							}
						} else {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Hạn chế");
							m.setMessage(
									"Khi thực hiện phần việc [Tổ chức - Thực Hiện] hoặc [tạo Đề xuất] mới có thể cập nhật PTTS tham gia công việc");
							m.open();
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmCpNhtDanh.setText("C\u1EADp nh\u1EADt Danh s\u00E1ch t\u00E0i s\u1EA3n tham gia");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.setText("X\u00F3a");

		tbtmNguonsuachua = new TabItem(tabFolder_1, SWT.NONE);
		tbtmNguonsuachua.setText("Nguồn sửa chữa - bảo dưỡng");

		Composite composite_2 = new Composite(tabFolder_1, SWT.NONE);
		tbtmNguonsuachua.setControl(composite_2);
		composite_2.setLayout(new GridLayout(2, false));

		Label lblTnNgun_1 = new Label(composite_2, SWT.NONE);
		lblTnNgun_1.setText("T\u00EAn ngu\u1ED3n:");

		text_Nguon = new Text(composite_2, SWT.NONE);
		text_Nguon.setEditable(false);
		text_Nguon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu_1 = new Label(composite_2, SWT.NONE);
		GridData gd_lblGiiThiu_1 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu_1.verticalIndent = 3;
		lblGiiThiu_1.setLayoutData(gd_lblGiiThiu_1);
		lblGiiThiu_1.setText("Gi\u1EDBi thi\u1EC7u:");

		text_Gioithieu = new Text(composite_2, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Gioithieu.setEditable(false);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH_1 = new Label(composite_2, SWT.NONE);
		GridData gd_lblLinH_1 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH_1.verticalIndent = 3;
		lblLinH_1.setLayoutData(gd_lblLinH_1);
		lblLinH_1.setText("Li\u00EAn h\u1EC7:");

		text_Lienhe = new Text(composite_2, SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Lienhe.setEditable(false);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TabFolder tabFolder_3 = new TabFolder(sashForm_3, SWT.NONE);

		TabItem tbtmLcSSa_1 = new TabItem(tabFolder_3, SWT.NONE);
		tbtmLcSSa_1.setText("L\u01B0\u1EE3c s\u1EED s\u1EEDa ch\u1EEFa t\u00E0i s\u1EA3n theo th\u00E1ng");

		tree_LuocSuSuachua = new Tree(tabFolder_3, SWT.BORDER);
		tbtmLcSSa_1.setControl(tree_LuocSuSuachua);
		tree_LuocSuSuachua.setLinesVisible(true);
		tree_LuocSuSuachua.setHeaderVisible(true);
		ts.setTreeItemHeight(tree_LuocSuSuachua, ItemHeight);

		TreeColumn trclmntSaCha = new TreeColumn(tree_LuocSuSuachua, SWT.NONE);
		trclmntSaCha.setWidth(125);
		trclmntSaCha.setText("\u0110\u1EE2T S\u1EECA CH\u1EEEA");

		TreeColumn trclmnSXut = new TreeColumn(tree_LuocSuSuachua, SWT.NONE);
		trclmnSXut.setWidth(100);
		trclmnSXut.setText("S\u1ED0 \u0110\u1EC0 XU\u1EA4T");

		TabItem tbtmHSPhn = new TabItem(tabFolder_3, SWT.NONE);
		tbtmHSPhn.setText("Hồ sơ phần việc trước");
		sashForm.setWeights(new int[] { 1000, 618 });

		TabItem tbtmCngVic_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmCngVic_1.setText("C\u00F4ng vi\u1EC7c \u0111\u00E3 th\u1EF1c hi\u1EC7n");

		SashForm sashForm_5 = new SashForm(tabFolder, SWT.NONE);
		tbtmCngVic_1.setControl(sashForm_5);

		tree_CongviecDathuchien = new Tree(sashForm_5, SWT.BORDER | SWT.FULL_SELECTION);
		tree_CongviecDathuchien.setHeaderVisible(true);
		tree_CongviecDathuchien.setLinesVisible(true);
		tree_CongviecDathuchien.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_CongviecDathuchien.getSelection();
					if (items.length > 0) {
						clearText_ThongTin_Congviec();
						CONGVIEC_PHANVIEC e = (CONGVIEC_PHANVIEC) items[0].getData();
						if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
							if (dsb != null) {
								setField_ThongTin_NGUONSUACHUA_BAODUONG_Dathuchien(dsb);
								set_TatcaHosoThuchien(e.getPHANVIEC());
							}
						} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
							DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
							if (dtt != null) {
								setField_ThongTin_NGUON_TANG_Dathuchien(dtt);
								set_TatcaHosoThuchien(e.getPHANVIEC());
							}
						} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) e.getCONGVIEC();
							if (dgt != null) {
								setField_ThongTin_NGUON_GIAM_Dathuchien(dgt);
								set_TatcaHosoThuchien(e.getPHANVIEC());
							}
						}
						setField_HosoCongviec_DaThuchien(e);

					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void set_TatcaHosoThuchien(Object phanviec) throws SQLException {
				tree_Hoso_Dathuchien.removeAll();
				if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
					setTree_Hoso_Canbo_DaThuchien((GIAI_DOAN_THUC_HIEN) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
					setTree_Hoso_Canbo_DaThuchien((GIAI_DOAN_NGHIEM_THU) phanviec);
				} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
					setTree_Hoso_Canbo_DaThuchien((GIAI_DOAN_QUYET_TOAN) phanviec);
				}
			}
		});
		ts.setTreeItemHeight(tree_CongviecDathuchien, ItemHeight);

		TreeColumn trclmnStt_2 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnStt_2.setWidth(50);
		trclmnStt_2.setText("STT");

		TreeColumn trclmnPhnVic_1 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnPhnVic_1.setWidth(100);
		trclmnPhnVic_1.setText("PH\u1EA6N VI\u1EC6C");

		TreeColumn trclmnLoiCngVic_1 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnLoiCngVic_1.setWidth(120);
		trclmnLoiCngVic_1.setText("LO\u1EA0I C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnTnCngVic_1 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnTnCngVic_1.setWidth(120);
		trclmnTnCngVic_1.setText("T\u00CAN C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnIdCngVic_1 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnIdCngVic_1.setWidth(100);
		trclmnIdCngVic_1.setText("ID C\u00D4NG VI\u1EC6C");

		TreeColumn trclmnNgyBtu_1 = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnNgyBtu_1.setWidth(100);
		trclmnNgyBtu_1.setText("NG\u00C0Y B\u1EAET \u0110\u1EA6U");

		TreeColumn trclmnNgyKtThc = new TreeColumn(tree_CongviecDathuchien, SWT.NONE);
		trclmnNgyKtThc.setWidth(110);
		trclmnNgyKtThc.setText("NG\u00C0Y K\u1EBET TH\u00DAC");

		TabFolder tabFolder_5 = new TabFolder(sashForm_5, SWT.NONE);

		TabItem tbtmThngTin = new TabItem(tabFolder_5, SWT.NONE);
		tbtmThngTin.setText("Th\u00F4ng tin");

		SashForm sashForm_6 = new SashForm(tabFolder_5, SWT.VERTICAL);
		tbtmThngTin.setControl(sashForm_6);

		Group grpHSLu = new Group(sashForm_6, SWT.NONE);
		grpHSLu.setText("H\u1ED3 s\u01A1 l\u01B0u tr\u1EEF");
		grpHSLu.setLayout(new GridLayout(1, false));

		tree_Hoso_Dathuchien = new Tree(grpHSLu, SWT.BORDER | SWT.FULL_SELECTION);
		tree_Hoso_Dathuchien.setLinesVisible(true);
		tree_Hoso_Dathuchien.setHeaderVisible(true);
		tree_Hoso_Dathuchien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		TreeColumn trclmnStt_5 = new TreeColumn(tree_Hoso_Dathuchien, SWT.CENTER);
		trclmnStt_5.setWidth(40);
		trclmnStt_5.setText("STT");

		TreeColumn trclmnMax = new TreeColumn(tree_Hoso_Dathuchien, SWT.CENTER);
		trclmnMax.setWidth(80);
		trclmnMax.setText("MÃ HỒ SƠ");

		TreeColumn trclmnTnTpH = new TreeColumn(tree_Hoso_Dathuchien, SWT.LEFT);
		trclmnTnTpH.setWidth(120);
		trclmnTnTpH.setText("TÊN TẬP HỒ SƠ");

		TreeColumn trclmnNgyToH = new TreeColumn(tree_Hoso_Dathuchien, SWT.CENTER);
		trclmnNgyToH.setWidth(120);
		trclmnNgyToH.setText("NGÀY TẠO HỒ SƠ");

		TreeColumn trclmnMTH = new TreeColumn(tree_Hoso_Dathuchien, SWT.LEFT);
		trclmnMTH.setWidth(150);
		trclmnMTH.setText("MÔ TẢ HỒ SƠ");

		TreeColumn trclmnNewColumn_1 = new TreeColumn(tree_Hoso_Dathuchien, SWT.NONE);
		trclmnNewColumn_1.setWidth(100);
		trclmnNewColumn_1.setText("NGƯỜI TẠO");

		Composite composite_1 = new Composite(sashForm_6, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		grpNgunThamGia = new Group(composite_1, SWT.NONE);
		grpNgunThamGia.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpNgunThamGia.setText("Ngu\u1ED3n tham gia");
		grpNgunThamGia.setLayout(new GridLayout(2, false));

		Label lblTnNgun = new Label(grpNgunThamGia, SWT.NONE);
		lblTnNgun.setText("T\u00EAn Ngu\u1ED3n:");

		text_Nguon_Dathuchien = new Text(grpNgunThamGia, SWT.NONE);
		text_Nguon_Dathuchien.setEditable(false);
		text_Nguon_Dathuchien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(grpNgunThamGia, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Gi\u1EDBi thi\u1EC7u:");

		text_GioiThieu_Dathuchien = new Text(grpNgunThamGia, SWT.WRAP | SWT.V_SCROLL);
		text_GioiThieu_Dathuchien.setEditable(false);
		text_GioiThieu_Dathuchien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(grpNgunThamGia, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Li\u00EAn h\u1EC7:");

		text_Lienhe_Dathuchien = new Text(grpNgunThamGia, SWT.WRAP | SWT.V_SCROLL);
		text_Lienhe_Dathuchien.setEditable(false);
		text_Lienhe_Dathuchien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_6.setWeights(new int[] { 1000, 618 });

		TabItem tbtmTin = new TabItem(tabFolder_5, SWT.NONE);
		tbtmTin.setText("Ti\u1EBFn \u0111\u1ED9");

		SashForm sashForm_9 = new SashForm(tabFolder_5, SWT.VERTICAL);
		tbtmTin.setControl(sashForm_9);

		Group grpXut = new Group(sashForm_9, SWT.NONE);
		grpXut.setText("\u0110\u1EC1 xu\u1EA5t - Ch\u1EE7 tr\u01B0\u01A1ng");
		grpXut.setLayout(new GridLayout(3, false));

		Label lblNgyBtu = new Label(grpXut, SWT.NONE);
		lblNgyBtu.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNgyBtu.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		text_6 = new Text(grpXut, SWT.NONE);
		text_6.setEditable(false);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		table = new Table(grpXut, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));
		table.setLinesVisible(true);

		Label lblTngThiGian = new Label(grpXut, SWT.NONE);
		lblTngThiGian.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTngThiGian.setText("Th\u1EDDi gian th\u1EF1c hi\u1EC7n:");

		text_7 = new Text(grpXut, SWT.NONE);
		text_7.setEditable(false);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGhiCh = new Label(grpXut, SWT.NONE);
		lblGhiCh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGhiCh.setText("Ghi ch\u00FA:");

		text_8 = new Text(grpXut, SWT.WRAP | SWT.MULTI);
		text_8.setEditable(false);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Group grpTChc = new Group(sashForm_9, SWT.NONE);
		grpTChc.setText("T\u1ED5 ch\u1EE9c - th\u1EF1c hi\u1EC7n");
		grpTChc.setLayout(new GridLayout(3, false));

		Label label_1 = new Label(grpTChc, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		text_9 = new Text(grpTChc, SWT.NONE);
		text_9.setEditable(false);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		table_1 = new Table(grpTChc, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));

		Label lblThiGianThc = new Label(grpTChc, SWT.NONE);
		lblThiGianThc.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblThiGianThc.setText("Th\u1EDDi gian th\u1EF1c hi\u1EC7n:");

		text_10 = new Text(grpTChc, SWT.NONE);
		text_10.setEditable(false);
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(grpTChc, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("Ghi ch\u00FA:");

		text_11 = new Text(grpTChc, SWT.WRAP | SWT.MULTI);
		text_11.setEditable(false);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Group grpNghimThu = new Group(sashForm_9, SWT.NONE);
		grpNghimThu.setText("Nghi\u1EC7m thu - B\u00E0n giao");
		grpNghimThu.setLayout(new GridLayout(3, false));

		Label label_2 = new Label(grpNghimThu, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		text_12 = new Text(grpNghimThu, SWT.NONE);
		text_12.setEditable(false);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		table_2 = new Table(grpNghimThu, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));

		Label label_4 = new Label(grpNghimThu, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("Th\u1EDDi gian th\u1EF1c hi\u1EC7n:");

		text_13 = new Text(grpNghimThu, SWT.NONE);
		text_13.setEditable(false);
		text_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(grpNghimThu, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("Ghi ch\u00FA:");

		text_14 = new Text(grpNghimThu, SWT.WRAP | SWT.MULTI);
		text_14.setEditable(false);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Group group = new Group(sashForm_9, SWT.NONE);
		group.setText("Nghi\u1EC7m thu - B\u00E0n giao");
		group.setLayout(new GridLayout(3, false));

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_6.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		text_15 = new Text(group, SWT.NONE);
		text_15.setEditable(false);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		table_3 = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table_3.setLinesVisible(true);
		table_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3));

		Label label_7 = new Label(group, SWT.NONE);
		label_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_7.setText("Th\u1EDDi gian th\u1EF1c hi\u1EC7n:");

		text_16 = new Text(group, SWT.NONE);
		text_16.setEditable(false);
		text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_8 = new Label(group, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("Ghi ch\u00FA:");

		text_17 = new Text(group, SWT.WRAP | SWT.MULTI);
		text_17.setEditable(false);
		text_17.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		sashForm_9.setWeights(new int[] { 1, 1, 1, 1 });
		sashForm_5.setWeights(new int[] { 480, 307 });

		TabItem tbtmNhtKThc = new TabItem(tabFolder, SWT.NONE);
		tbtmNhtKThc.setText("Nhật ký Làm việc");

		Label lblNewLabel = new Label(tabFolder, SWT.WRAP);
		tbtmNhtKThc.setControl(lblNewLabel);
		lblNewLabel.setText("Nh\u1EADt k\u00FD th\u1EF1c hi\u1EC7n");

		TabItem tbtmBiuCng = new TabItem(tabFolder, SWT.NONE);
		tbtmBiuCng.setText("Bi\u1EC3u \u0111\u1ED3 c\u00F4ng vi\u1EC7c");

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(6, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);

		btnTiepNhan = new Button(composite, SWT.NONE);
		btnTiepNhan
				.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Mailbox-receive-message-2-icon (1).png"));
		btnTiepNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TiepNhan();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnTiepNhan.setText("Tiếp nhận");

		btnTraLai = new Button(composite, SWT.NONE);
		GridData gd_btnTraLai = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTraLai.widthHint = 75;
		btnTraLai.setLayoutData(gd_btnTraLai);
		btnTraLai.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Actions-blue-arrow-undo-icon (1).png"));
		btnTraLai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TraLai();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		btnTraLai.setText("Trả lại");

		btnChuyengiao = new Button(composite, SWT.NONE);
		btnChuyengiao.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/Transfer-icon (1).png"));
		btnChuyengiao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChuyenGiao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnChuyengiao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnChuyengiao.setText("Chuy\u1EC3n giao");

		btnNgungChuyenGiao = new Button(composite, SWT.NONE);
		btnNgungChuyenGiao.setImage(SWTResourceManager.getImage(CongViecCuaToi.class, "/stop-red-icon (1).png"));
		btnNgungChuyenGiao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DungChuyengiao();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNgungChuyenGiao.setText("Ngừng chuyển giao");
		new Label(composite, SWT.NONE);

		Button btnng = new Button(composite, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.BOTTOM, false, true, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
		fillCongviecDangThuchien();
		fillCongviecDaThuchien();
		fillCongviecChotiepnhan();
	}

	protected void setField_HosoCongviec_DaThuchien(CONGVIEC_PHANVIEC e) throws SQLException {
		ArrayList<Object> hsl = new ArrayList<>();
		if (e.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			ArrayList<NGUOIDUNG_THUCHIEN> cdth = controler.getControl_THUCHIEN_CANBO()
					.get_AllNGUOIDUNG_THUCHIEN((GIAI_DOAN_THUC_HIEN) e.getPHANVIEC());
			if (cdth != null)
				hsl.addAll(cdth);
		} else if (e.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			ArrayList<NGUOIDUNG_NGHIEMTHU> cdth = controler.getControl_NGHIEMTHU_CANBO()
					.get_AllNGUOIDUNG_NGHIEMTHU((GIAI_DOAN_NGHIEM_THU) e.getPHANVIEC());
			if (cdth != null)
				hsl.addAll(cdth);
		} else if (e.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			ArrayList<NGUOIDUNG_QUYETTOAN> cdth = controler.getControl_QUYETTOAN_CANBO()
					.get_AllNGUOIDUNG_QUYETTOAN((GIAI_DOAN_QUYET_TOAN) e.getPHANVIEC());
			if (cdth != null)
				hsl.addAll(cdth);
		}
		tree_Hoso_Dathuchien.removeAll();
		int i = 1;
		for (Object ths : hsl) {
			TreeItem ti = new TreeItem(tree_Hoso_Dathuchien, SWT.NONE);
			if (ths instanceof NGUOIDUNG_THUCHIEN) {
				NGUOIDUNG_THUCHIEN o = (NGUOIDUNG_THUCHIEN) ths;
				TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(o.getMA_TAPHOSO());
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(o.getTEN_TAI_KHOAN());
				if (t != null) {
					ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
							t.getNGAY_TAO_TAPHOSO() == null ? "-" : (mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO())),
							t.getGIOITHIEU_TAPHOSO(), nd.getTEN_CAN_BO() });
					ti.setData(ths);
				}
			} else if (ths instanceof NGUOIDUNG_NGHIEMTHU) {
				NGUOIDUNG_NGHIEMTHU o = (NGUOIDUNG_NGHIEMTHU) ths;
				TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(o.getMA_TAPHOSO());
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(o.getTEN_TAI_KHOAN());
				if (t != null) {
					ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
							t.getNGAY_TAO_TAPHOSO() == null ? "-" : (mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO())),
							t.getGIOITHIEU_TAPHOSO(), nd.getTEN_CAN_BO() });
					ti.setData(ths);
				}
			} else if (ths instanceof NGUOIDUNG_QUYETTOAN) {
				NGUOIDUNG_QUYETTOAN o = (NGUOIDUNG_QUYETTOAN) ths;
				TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(o.getMA_TAPHOSO());
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(o.getTEN_TAI_KHOAN());
				if (t != null) {
					ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
							t.getNGAY_TAO_TAPHOSO() == null ? "-" : (mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO())),
							t.getGIOITHIEU_TAPHOSO(), nd.getTEN_CAN_BO() });
					ti.setData(ths);
				}
			}
			i++;
		}
		treePack(tree_Hoso_Dathuchien);
	}

	protected void setField_ThongTin_NGUON_GIAM_Dathuchien(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		grpNgunThamGia.setText("Nguồn giảm PTTS");
		NGUONGIAM nt = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
		if (nt != null) {
			text_Nguon_Dathuchien.setText(nt.getTEN_NGUONGIAM());
			text_GioiThieu_Dathuchien.setText(nt.getGIOI_THIEU());
			text_Lienhe_Dathuchien.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setField_ThongTin_NGUON_TANG_Dathuchien(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		grpNgunThamGia.setText("Nguồn tăng PTTS");
		NGUONTANG nt = controler.getControl_NGUONTANG().get_NguonTang(dtt);
		if (nt != null) {
			text_Nguon_Dathuchien.setText(nt.getTEN_NGUONTANG());
			text_GioiThieu_Dathuchien.setText(nt.getGIOI_THIEU());
			text_Lienhe_Dathuchien.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setField_ThongTin_NGUONSUACHUA_BAODUONG_Dathuchien(DOT_THUCHIEN_SUACHUA_BAODUONG dsb)
			throws SQLException {
		grpNgunThamGia.setText("Nguồn Sửa chữa - Bảo dưỡng");
		NGUONSUACHUA_BAODUONG nsb = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(dsb);
		if (nsb != null) {
			text_Nguon_Dathuchien.setText(nsb.getTEN_NGUONSUACHUA_BAODUONG());
			text_GioiThieu_Dathuchien.setText(nsb.getGIOI_THIEU());
			text_Lienhe_Dathuchien.setText(nsb.getLIEN_HE());
		} else {
			clearText_NGUONTANG_Dathuchien();
		}
	}

	private void clearText_NGUONTANG_Dathuchien() {
		text_Nguon_Dathuchien.setText("");
		text_GioiThieu_Dathuchien.setText("");
		text_Lienhe_Dathuchien.setText("");
	}

	protected void setTree_Hoso_Canbo_DaThuchien(GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_QUYETTOAN> nql = controler.getControl_QUYETTOAN_CANBO()
				.get_AllNGUOIDUNG_QUYETTOAN(phanviec);
		int i = 1;
		for (NGUOIDUNG_QUYETTOAN nq : nql) {
			TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(nq.getMA_TAPHOSO());
			if (t != null) {
				TreeItem ti = new TreeItem(tree_Hoso_Dathuchien, SWT.NONE);
				ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
						t.getNGAY_TAO_TAPHOSO() == null ? "" : mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO()),
						nq.getTEN_TAI_KHOAN() });
				ti.setData(t);
				i++;
			}
		}
	}

	protected void setTree_Hoso_Canbo_DaThuchien(GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_NGHIEMTHU> nql = controler.getControl_NGHIEMTHU_CANBO()
				.get_AllNGUOIDUNG_NGHIEMTHU(phanviec);
		int i = 1;
		for (NGUOIDUNG_NGHIEMTHU nq : nql) {
			TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(nq.getMA_TAPHOSO());
			if (t != null) {

				TreeItem ti = new TreeItem(tree_Hoso_Dathuchien, SWT.NONE);
				ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
						t.getNGAY_TAO_TAPHOSO() == null ? "" : mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO()),
						nq.getTEN_TAI_KHOAN() });
				ti.setData(t);
				i++;
			}
		}
	}

	protected void setTree_Hoso_Canbo_DaThuchien(GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_THUCHIEN> nql = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(phanviec);
		int i = 1;
		for (NGUOIDUNG_THUCHIEN nq : nql) {
			TAP_HO_SO t = controler.getControl_TAPHOSO().get_TAP_HO_SO(nq.getMA_TAPHOSO());
			if (t != null) {
				TreeItem ti = new TreeItem(tree_Hoso_Dathuchien, SWT.NONE);
				ti.setText(new String[] { i + "", t.getMA_TAPHOSO() + "", t.getTEN_TAPHOSO(),
						t.getNGAY_TAO_TAPHOSO() == null ? "" : mdf.getViewStringDate(t.getNGAY_TAO_TAPHOSO()),
						nq.getTEN_TAI_KHOAN() });
				ti.setData(t);
				i++;
			}
		}
	}

	protected void DungChuyengiao() throws SQLException {
		if (tree_Dangthuchien.isVisible()) {
			TreeItem[] ti = tree_Dangthuchien.getSelection();
			if (ti.length > 0) {
				CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();

				if (Check_PHANVIEC_Start(p)) {
					SetupChuaHoanthanhPhanViec_Chuyengiao(p);
				}
			}
		}
	}

	private void SetupChuaHoanthanhPhanViec_Chuyengiao(CONGVIEC_PHANVIEC p) throws SQLException {
		Date THISDAY = null;
		ThongBao_Lib_Congviec tb = new ThongBao_Lib_Congviec(user);
		if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			controler.getControl_THUCHIEN().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(), THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				// tb.create_THONGBAO_Congvieccuatoi_DUNGHoanthanh_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
				// (DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC(),
				// (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				controler.getControl_THUCHIEN().update_ThoiDiemKetthucCongviec((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(),
						THISDAY/* =null */);
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			controler.getControl_NGHIEMTHU().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC(),
					THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			controler.getControl_QUYETTOAN()
					.set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC((GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC(), THISDAY);
		}
		fillCongviecDangThuchien();
		MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
		m.setText("Hoàn tất");
		m.setMessage("Đã dừng Chuyển giao phần việc và thông báo đến những người cùng tham gia!");
		m.open();
	}

	protected void TraLai() throws SQLException {
		if (tree_Dangthuchien.isVisible()) {
			TreeItem[] ti = tree_Dangthuchien.getSelection();
			if (ti.length > 0) {
				CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
				SetupTraLaiPhanViec_TraLai(p);
			}
		}
	}

	private void SetupTraLaiPhanViec_TraLai(CONGVIEC_PHANVIEC p) throws SQLException {

		Date THISDAY = null;
		final int DukienNgayThuchien_0 = 0;
		ThongBao_Lib_Congviec tb = new ThongBao_Lib_Congviec(user);
		if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			controler.getControl_THUCHIEN().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(), THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				controler.getControl_THUCHIEN().update_ThoiDiemKetthucCongviec((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(),
						THISDAY);
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			controler.getControl_NGHIEMTHU().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC(),
					THISDAY);
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			controler.getControl_QUYETTOAN()
					.set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC((GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC(), THISDAY);
		}

		if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			DE_XUAT dx = null;
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				dx = controler.getControl_DEXUAT().get_DEXUAT(d);
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				dx = controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
				tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				dx = controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC());
				tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
						(DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
			}
			if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				controler.getControl_DEXUAT().update_ThoiDiemKetthucCongviec(dx, THISDAY);
				controler.getControl_THUCHIEN().update_ThoiDiemBatdauCongviec(
						controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()), THISDAY);
				controler.getControl_THUCHIEN().update_DukienThuchien(
						controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()),
						DukienNgayThuchien_0);
			} else {
				MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
				m.setText("Công việc trước chưa giao");
				m.setMessage("Đề xuất chưa chuyển giao");
				m.open();
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			GIAI_DOAN_THUC_HIEN gdth = null;
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(d);
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				gdth = controler.getControl_THUCHIEN()
						.get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
				tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				gdth = controler.getControl_THUCHIEN()
						.get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC());
			}
			if (gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				controler.getControl_THUCHIEN().update_ThoiDiemKetthucCongviec(gdth, THISDAY);
				controler.getControl_NGHIEMTHU().update_ThoiDiemBatdauCongviec(
						controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()), THISDAY);
				controler.getControl_NGHIEMTHU().update_DukienThuchien(
						controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()),
						DukienNgayThuchien_0);
			} else {
				MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
				m.setText("Công việc trước chưa giao");
				m.setMessage("Phần việc Tổ chức - Thực hiện chưa chuyển giao");
				m.open();
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			GIAI_DOAN_NGHIEM_THU gdnt = null;
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(d);
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				gdnt = controler.getControl_NGHIEMTHU()
						.get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
				tb.create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			}
			if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() != null) {
				controler.getControl_NGHIEMTHU().update_ThoiDiemKetthucPhanviec(gdnt, THISDAY);
				controler.getControl_QUYETTOAN().update_ThoiDiemBatdauCongviec(
						controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()), THISDAY);
				controler.getControl_QUYETTOAN().update_DukienThuchien(
						controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()),
						DukienNgayThuchien_0);
			} else {
				MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
				m.setText("Công việc trước chưa giao");
				m.setMessage("Phần việc Nghiệm thu - Bàn giao chưa chuyển giao");
				m.open();
			}
		}
		fillCongviecChotiepnhan();
		fillCongviecDangThuchien();
		MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
		m.setText("Hoàn thành");
		m.setMessage("Đã Trả lại phần việc và Thông báo cho những người cùng tham gia");
		m.open();
	}

	protected void ChuyenGiao() throws SQLException {
		if (tree_Dangthuchien.isVisible()) {

			TreeItem[] ti = tree_Dangthuchien.getSelection();
			if (ti.length > 0) {
				CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();

				if (Check_PHANVIEC_Start(p)) {
					SetupHoanthanhPhanViec_Chuyengiao(p);
				}
			}
		}
	}

	private void SetupHoanthanhPhanViec_Chuyengiao(CONGVIEC_PHANVIEC p) throws SQLException {
		Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
		ThongBao_Lib_Congviec tb = new ThongBao_Lib_Congviec(user);
		if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			controler.getControl_THUCHIEN().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(), THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_Hoanthanh_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
						(DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
				controler.getControl_THUCHIEN().update_ThoiDiemKetthucCongviec((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC(),
						THISDAY);
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			controler.getControl_NGHIEMTHU().set_NGAYCHUYENGIAO_PHANVIEC((GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC(),
					THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			}
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			controler.getControl_QUYETTOAN()
					.set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC((GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC(), THISDAY);
			if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
				if (d.getSUACHUA_BAODUONG() == 1) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_BAODUONG(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
				} else if (d.getSUACHUA_BAODUONG() == 2) {
					tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_SUACHUA(
							(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
				}
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
				tb.create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_TANG_TAISAN(
						(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(), (GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
			} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			}
		}
		fillCongviecDangThuchien();
		MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
		m.setText("Hoàn tất");
		m.setMessage("Chuyển giao phần việc hoàn tất, Đã Thực hiện gửi thông báo đến những người cùng tham gia!");
		m.open();
	}

	protected void TiepNhan() throws SQLException {
		if (tree_Tiepnhan.isVisible()) {
			TreeItem[] ti = tree_Tiepnhan.getSelection();
			if (ti.length > 0) {
				boolean flag = true;
				CONGVIEC_PHANVIEC p = (CONGVIEC_PHANVIEC) ti[0].getData();
				Date THISDAY = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
				DukienNgayHoanthanh dk = new DukienNgayHoanthanh(getShell(), SWT.NONE, THISDAY);
				dk.open();
				int DukienThuchien = dk.result;
				if (DukienThuchien > 0) {
					ThongBao_Lib_Congviec tb = new ThongBao_Lib_Congviec(user);
					if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
						DE_XUAT dx = null;
						if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
							dx = controler.getControl_DEXUAT().get_DEXUAT(d);
							if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null)
								if (d.getSUACHUA_BAODUONG() == 1) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
								} else if (d.getSUACHUA_BAODUONG() == 2) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
								}
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
							dx = controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
							if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null)
								tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
										(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(),
										(GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							dx = controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC());
							if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null)
								tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
										(DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC(),
										(GIAI_DOAN_THUC_HIEN) p.getPHANVIEC());
						}
						if (dx.getTHOI_DIEM_CHUYEN_GIAO() != null) {
							controler.getControl_DEXUAT().update_ThoiDiemKetthucCongviec(dx, THISDAY);
							controler.getControl_THUCHIEN().update_ThoiDiemBatdauCongviec(
									controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()), THISDAY);
							controler.getControl_THUCHIEN().update_DukienThuchien(
									controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(p.getMA_PHANVIEC()),
									DukienThuchien);
						} else {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Công việc trước chưa giao");
							m.setMessage("Đề xuất chưa chuyển giao");
							m.open();
							flag = false;
						}
					} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
						GIAI_DOAN_THUC_HIEN gdth = null;
						if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
							gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(d);
							if (gdth.getTHOI_DIEM_CHUYEN_GIAO() != null)
								if (d.getSUACHUA_BAODUONG() == 1) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
								} else if (d.getSUACHUA_BAODUONG() == 2) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());

								}
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
							gdth = controler.getControl_THUCHIEN()
									.get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
							if (gdth.getTHOI_DIEM_CHUYEN_GIAO() != null)
								tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
										(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(),
										(GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC());
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
							gdth = controler.getControl_THUCHIEN()
									.get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_GIAM_TAISAN) p.getCONGVIEC());
						}
						if (gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
							controler.getControl_THUCHIEN().update_ThoiDiemKetthucCongviec(gdth, THISDAY);
							controler.getControl_NGHIEMTHU().update_ThoiDiemBatdauCongviec(
									controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()),
									THISDAY);
							controler.getControl_NGHIEMTHU().update_DukienThuchien(
									controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(p.getMA_PHANVIEC()),
									DukienThuchien);
						} else {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Công việc trước chưa giao");
							m.setMessage("Phần việc Tổ chức - Thực hiện chưa chuyển giao");
							m.open();
							flag = false;
						}
					} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
						GIAI_DOAN_NGHIEM_THU gdnt = null;
						if (p.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
							DOT_THUCHIEN_SUACHUA_BAODUONG d = (DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC();
							gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(d);
							if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() != null)
								if (d.getSUACHUA_BAODUONG() == 1) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_BAODUONG(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
								} else if (d.getSUACHUA_BAODUONG() == 2) {
									tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_SUACHUA(
											(DOT_THUCHIEN_SUACHUA_BAODUONG) p.getCONGVIEC(),
											(GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());

								}
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
							gdnt = controler.getControl_NGHIEMTHU()
									.get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC());
							if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() != null)
								tb.create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
										(DOT_THUCHIEN_TANG_TAISAN) p.getCONGVIEC(),
										(GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC());
						} else if (p.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
						}
						if (gdnt.getTHOI_DIEM_CHUYEN_GIAO() != null) {

							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
							m.setText("Chú ý");
							m.setMessage("Kiểm tra Hồ sơ trước khi nhận việc!");
							m.open();
							controler.getControl_NGHIEMTHU().update_ThoiDiemKetthucPhanviec(gdnt, THISDAY);
							controler.getControl_QUYETTOAN().update_ThoiDiemBatdauCongviec(
									controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()),
									THISDAY);
							controler.getControl_QUYETTOAN().update_DukienThuchien(
									controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(p.getMA_PHANVIEC()),
									DukienThuchien);
						} else {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WARNING);
							m.setText("Công việc trước chưa giao");
							m.setMessage("Phần việc Nghiệm thu - Bàn giao chưa chuyển giao");
							m.open();
							flag = false;
						}
					}
				}
				fillCongviecChotiepnhan();
				fillCongviecDangThuchien();
				fillCongviecDaThuchien();
				if (flag) {
					MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
					m.setText("Hoàn tất");
					m.setMessage("Tiếp nhận công việc thành công");
					m.open();
				}
			}
		}
	}

	private boolean Check_PHANVIEC_Start(CONGVIEC_PHANVIEC p) {
		if (p.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			if (((GIAI_DOAN_THUC_HIEN) p.getPHANVIEC()).getTHOI_DIEM_BAT_DAU() == null)
				return false;
			return true;
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			if (((GIAI_DOAN_NGHIEM_THU) p.getPHANVIEC()).getTHOI_DIEM_TIEP_NHAN() == null)
				return false;
			return true;
		} else if (p.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			if (((GIAI_DOAN_QUYET_TOAN) p.getPHANVIEC()).getTHOI_DIEM_TIEP_NHAN() == null)
				return false;
			return true;
		}
		return false;
	}

	protected void loadHosoPhanviecTruoc(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
		tree_Hoso_PhanviecTuoc.removeAll();
		ArrayList<NGUOIDUNG_NGHIEMTHU> thsl = controler.getControl_NGHIEMTHU_CANBO().get_AllNGUOIDUNG_NGHIEMTHU(gdnt);
		int i = 1;
		for (NGUOIDUNG_NGHIEMTHU ndgt : thsl) {
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndgt.getMA_TAPHOSO());
			if (ths != null) {
				TreeItem ti = new TreeItem(tree_Hoso_PhanviecTuoc, SWT.NONE);
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndgt.getTEN_TAI_KHOAN());
				ti.setText(new String[] { "" + i, ths.getTEN_TAPHOSO().equals("null") ? "-" : ths.getTEN_TAPHOSO(),
						ths.getGIOITHIEU_TAPHOSO().equals("null") ? "-" : ths.getGIOITHIEU_TAPHOSO(),
						ths.getNGAY_TAO_TAPHOSO() == null ? "-" : mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO()),
						nd.getTEN_CAN_BO() });
				ti.setData(ths);
			}
			i++;
		}
		treePack(tree_Hoso_PhanviecTuoc);
	}

	protected void loadHosoPhanviecTruoc(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
		tree_Hoso_PhanviecTuoc.removeAll();
		ArrayList<NGUOIDUNG_THUCHIEN> thsl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(gdth);
		int i = 1;
		for (NGUOIDUNG_THUCHIEN ndth : thsl) {
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth.getMA_TAPHOSO());
			if (ths != null) {
				TreeItem ti = new TreeItem(tree_Hoso_PhanviecTuoc, SWT.NONE);
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
				ti.setText(new String[] { "" + i, ths.getTEN_TAPHOSO().equals("null") ? "-" : ths.getTEN_TAPHOSO(),
						ths.getGIOITHIEU_TAPHOSO().equals("null") ? "-" : ths.getGIOITHIEU_TAPHOSO(),
						ths.getNGAY_TAO_TAPHOSO() == null ? "-" : mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO()),
						nd.getTEN_CAN_BO() });
				ti.setData(ths);
			}
			i++;
		}
		treePack(tree_Hoso_PhanviecTuoc);
	}

	protected void loadHosoPhanviecTruoc(DE_XUAT d) throws SQLException {
		tree_Hoso_PhanviecTuoc.removeAll();
		TAP_HO_SO thsl = controler.getControl_TAPHOSO().get_TAP_HO_SO(d.getMA_TAPHOSO());
		if (thsl != null) {
			TreeItem ti = new TreeItem(tree_Hoso_PhanviecTuoc, SWT.NONE);
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(d.getTEN_TAI_KHOAN());
			ti.setText(new String[] { "" + 1, thsl.getTEN_TAPHOSO().equals("null") ? "-" : thsl.getTEN_TAPHOSO(),
					thsl.getGIOITHIEU_TAPHOSO().equals("null") ? "-" : thsl.getGIOITHIEU_TAPHOSO(),
					thsl.getNGAY_TAO_TAPHOSO() == null ? "-" : mdf.getViewStringDate(thsl.getNGAY_TAO_TAPHOSO()),
					nd.getTEN_CAN_BO() });
			ti.setData(thsl);
		}
		treePack(tree_Hoso_PhanviecTuoc);
	}

	protected void clearText_ThongTin_Congviec_Truoc() {
		text_Congviectruoc_TenPhanviec.setText(" ");
		text_Congviectruoc_Ngaybatdau.setText("");
		text_Congviectruoc_Tongsongay.setText("");
		text_Congviectruoc_Ghichu.setText("");
	}

	protected void setField_ThongTin_Congviec(CONGVIEC_PHANVIEC e) throws SQLException {
		clearText_ThongTin_Congviec();
		if (e.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
			GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) e.getPHANVIEC();
			NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
					.getNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(), gdth);
			if (ndth != null) {
				text_Ngaythamgia.setText(mdf.getViewStringDate(ndth.getNGAY_THAM_GIA()));
				text_Ngaythuchien.setText(gdth.getTHOI_DIEM_BAT_DAU() == null ? "Chưa bắt đầu"
						: mdf.getViewStringDate(gdth.getTHOI_DIEM_BAT_DAU()));
				text_DukienThuchien.setText("" + gdth.getTHOI_GIAN_DU_KIEN_HOAN_THANH());
				text_Ngaychuyengiao.setText(gdth.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa chuyển giao"
						: mdf.getViewStringDate(gdth.getTHOI_DIEM_CHUYEN_GIAO()));
				text_Ghichu_Phanviec.setText(gdth.getGHI_CHU() == null ? "" : gdth.getGHI_CHU());
				loadHosoPhanviec(gdth);
			}
		} else if (e.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
			GIAI_DOAN_NGHIEM_THU gdnt = (GIAI_DOAN_NGHIEM_THU) e.getPHANVIEC();
			NGUOIDUNG_NGHIEMTHU ndnt = controler.getControl_NGHIEMTHU_CANBO()
					.getNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(), gdnt);
			if (ndnt != null) {
				text_Ngaythamgia.setText(mdf.getViewStringDate(ndnt.getNGAY_THAM_GIA()));
				text_Ngaythuchien.setText(gdnt.getTHOI_DIEM_TIEP_NHAN() == null ? "Chưa bắt đầu"
						: mdf.getViewStringDate(gdnt.getTHOI_DIEM_TIEP_NHAN()));
				text_DukienThuchien.setText("" + gdnt.getTHOI_GIAN_DU_KIEN_HOAN_THANH());
				text_Ngaychuyengiao.setText(gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa chuyển giao"
						: mdf.getViewStringDate(gdnt.getTHOI_DIEM_CHUYEN_GIAO()));
				text_Ghichu_Phanviec.setText(gdnt.getGHI_CHU() == null ? "" : gdnt.getGHI_CHU());
				loadHosoPhanviec(gdnt);
			}
		} else if (e.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
			GIAI_DOAN_QUYET_TOAN gdqt = (GIAI_DOAN_QUYET_TOAN) e.getPHANVIEC();
			NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
					.getNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(), gdqt);
			if (ndqt != null) {
				text_Ngaythamgia.setText(mdf.getViewStringDate(ndqt.getNGAY_THAM_GIA()));
				text_Ngaythuchien.setText(gdqt.getTHOI_DIEM_TIEP_NHAN() == null ? "Chưa bắt đầu"
						: mdf.getViewStringDate(gdqt.getTHOI_DIEM_TIEP_NHAN()));
				text_DukienThuchien.setText("" + gdqt.getTHOI_GIAN_DU_KIEN_HOAN_THANH());
				text_Ngaychuyengiao.setText(gdqt.getTHOI_GIAN_KET_THUC() == null ? "Chưa chuyển giao"
						: mdf.getViewStringDate(gdqt.getTHOI_GIAN_KET_THUC()));
				text_Ghichu_Phanviec.setText(gdqt.getGHI_CHU() == null ? "" : gdqt.getGHI_CHU());
				loadHosoPhanviec(gdqt);
			}
		}
	}

	private void loadHosoPhanviec(GIAI_DOAN_QUYET_TOAN gdqt) throws SQLException {
		tree_HosoPhanviec.removeAll();
		NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
				.getNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(), gdqt);
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndqt);
		if (ths != null) {
			ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
			int i = 1;
			for (VANBAN vb : vbl) {
				TreeItem ti = new TreeItem(tree_HosoPhanviec, SWT.NONE);
				ti.setText(new String[] { "" + i, vb.getSO_VANBAN(), mdf.getViewStringDate(vb.getNGAY_BAN_HANH()),
						vb.getCO_QUAN_BAN_HANH(), vb.getTRICH_YEU() });
				ti.setData(vb);
				i++;
			}
		}
		treePack(tree_HosoPhanviec);
	}

	private void loadHosoPhanviec(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
		tree_HosoPhanviec.removeAll();
		NGUOIDUNG_NGHIEMTHU ndnt = controler.getControl_NGHIEMTHU_CANBO()
				.getNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(), gdnt);
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndnt);
		if (ths != null) {
			ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
			int i = 1;
			for (VANBAN vb : vbl) {
				TreeItem ti = new TreeItem(tree_HosoPhanviec, SWT.NONE);
				ti.setText(new String[] { "" + i, vb.getSO_VANBAN(), mdf.getViewStringDate(vb.getNGAY_BAN_HANH()),
						vb.getCO_QUAN_BAN_HANH(), vb.getTRICH_YEU() });
				ti.setData(vb);
				i++;
			}
		}
		treePack(tree_HosoPhanviec);
	}

	private void loadHosoPhanviec(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {

		tree_HosoPhanviec.removeAll();
		NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO().getNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(),
				gdth);
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
		if (ths != null) {
			ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
			int i = 1;
			for (VANBAN vb : vbl) {
				TreeItem ti = new TreeItem(tree_HosoPhanviec, SWT.NONE);
				ti.setText(new String[] { "" + i, vb.getSO_VANBAN(), mdf.getViewStringDate(vb.getNGAY_BAN_HANH()),
						vb.getCO_QUAN_BAN_HANH(), vb.getTRICH_YEU() });
				ti.setData(vb);
				i++;
			}
		}
		treePack(tree_HosoPhanviec);
	}

	private void clearText_ThongTin_Congviec() {
		text_Ngaythamgia.setText("");
		text_Ngaythuchien.setText("");
		text_DukienThuchien.setText("");
		text_Ngaychuyengiao.setText("");
		text_Ghichu_Phanviec.setText("");
		tree_HosoPhanviec.removeAll();
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		if (tree_1.isVisible()) {
			if (tree_1.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_1.getListeners(SWT.SetData)) {
					tree_1.removeListener(SWT.SetData, lst);
				}

			tree_1.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dgt);

			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_1.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						String[] ParentRow = { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) };
						parent.setText(ParentRow);
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								String[] childrow = { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(), "", "",
										pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) };
								child.setText(childrow);
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_1.setItemCount(COUNT);

			treePack(tree_1);
		}
	}

	protected void setField_ThongTin_NGUON_GIAM(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		tbtmNguonsuachua.setText("Nguồn giảm PTTS");
		NGUONGIAM nt = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
		if (nt != null) {
			text_Nguon.setText(nt.getTEN_NGUONGIAM());
			text_Gioithieu.setText(nt.getGIOI_THIEU());
			text_Lienhe.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		DE_XUAT d = controler.getControl_DEXUAT().get_DEXUAT(dgt);
		if (d != null) {
			text_Sodexuat.setText(d.getSODEXUAT());
			text_Ngaythangvanban.setText(d.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(d.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(d.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_Donvidexuat.setText(ten);
			text_NgaynhanVB.setText(d.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(d.getTHOI_DIEM_CHUYEN_GIAO()));
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_ngayHoanthanhxulydexuat.setText(d.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(d.getTHOI_DIEM_HOAN_THANH()));
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(d.getTEN_TAI_KHOAN());
			text_CanboXuly.setText(nd == null ? "" : nd.getTEN_CAN_BO());
			text_Ghichudexuat.setText(d.getGHI_CHU() == null ? "" : d.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		if (tree_1.isVisible()) {
			if (tree_1.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_1.getListeners(SWT.SetData)) {
					tree_1.removeListener(SWT.SetData, lst);
				}

			tree_1.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dtt);

			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_1.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						String[] ParentRow = { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) };
						parent.setText(ParentRow);
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								String[] childrow = { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(), "", "",
										pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) };
								child.setText(childrow);
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_1.setItemCount(COUNT);

			treePack(tree_1);
		}
	}

	protected void setField_ThongTin_NGUON_TANG(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		tbtmNguonsuachua.setText("Nguồn tăng PTTS");
		NGUONTANG nt = controler.getControl_NGUONTANG().get_NguonTang(dtt);
		if (nt != null) {
			text_Nguon.setText(nt.getTEN_NGUONTANG());
			text_Gioithieu.setText(nt.getGIOI_THIEU());
			text_Lienhe.setText(nt.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		DE_XUAT d = controler.getControl_DEXUAT().get_DEXUAT(dtt);
		if (d != null) {
			text_Sodexuat.setText(d.getSODEXUAT());
			text_Ngaythangvanban.setText(d.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(d.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(d.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_Donvidexuat.setText(ten);
			text_NgaynhanVB.setText(d.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(d.getTHOI_DIEM_CHUYEN_GIAO()));
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_ngayHoanthanhxulydexuat.setText(d.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(d.getTHOI_DIEM_HOAN_THANH()));
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(d.getTEN_TAI_KHOAN());
			text_CanboXuly.setText(nd == null ? "" : nd.getTEN_CAN_BO());
			text_Ghichudexuat.setText(d.getGHI_CHU() == null ? "" : d.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	protected void setField_ThongTin_NGUONSUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		tbtmNguonsuachua.setText("Nguồn Sửa chữa - Bảo dưỡng");
		NGUONSUACHUA_BAODUONG nsb = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(dsb);
		if (nsb != null) {
			text_Nguon.setText(nsb.getTEN_NGUONSUACHUA_BAODUONG());
			text_Gioithieu.setText(nsb.getGIOI_THIEU());
			text_Lienhe.setText(nsb.getLIEN_HE());
		} else {
			clearText_NGUONTANG();
		}
	}

	private void clearText_NGUONTANG() {
		text_Nguon.setText("");
		text_Gioithieu.setText("");
		text_Lienhe.setText("");
	}

	protected void setField_ThongTin_DE_XUAT(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		DE_XUAT d = controler.getControl_DEXUAT().get_DEXUAT(dsb);
		if (d != null) {
			text_Sodexuat.setText(d.getSODEXUAT());
			text_Ngaythangvanban.setText(d.getNGAYTHANG_VANBAN() == null ? " không có dữ liệu"
					: mdf.getViewStringDate(d.getNGAYTHANG_VANBAN()));
			String ten = (controler.getControl_PHONGBAN().get_PHONGBAN(d.getMA_PHONGBAN()).getTEN_PHONGBAN());
			text_Donvidexuat.setText(ten);
			text_NgaynhanVB.setText(d.getTHOI_DIEM_CHUYEN_GIAO() == null ? "Chưa hoàn thành, chuyển giao phần việc"
					: mdf.getViewStringDate(d.getTHOI_DIEM_CHUYEN_GIAO()));
			// chuyen giao cho nguoi khac tuc la hoan thanh
			text_ngayHoanthanhxulydexuat.setText(d.getTHOI_DIEM_HOAN_THANH() == null ? "Chưa hoàn tất"
					: mdf.getViewStringDate(d.getTHOI_DIEM_HOAN_THANH()));
			NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(d.getTEN_TAI_KHOAN());
			text_CanboXuly.setText(nd == null ? "" : nd.getTEN_CAN_BO());
			text_Ghichudexuat.setText(d.getGHI_CHU() == null ? "" : d.getGHI_CHU());
		} else {
			clearText_DEXUAT();
		}
	}

	private void clearText_DEXUAT() {
		// TODO Auto-generated method stub

	}

	private void fillCongviecDaThuchien() throws SQLException {
		tree_CongviecDathuchien.removeAll();
		ArrayList<CONGVIEC_PHANVIEC> th_SCBD_l = controler.getControl_THUCHIEN().get_DaThucHien_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> th_MUASAM_l = controler.getControl_THUCHIEN().get_DaThucHien_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> th_THANHLY_l = controler.getControl_THUCHIEN().get_DaThucHien_GIAM_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_SCBD_l = controler.getControl_NGHIEMTHU()
				.get_DaNghiemthu_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_MUASAM_l = controler.getControl_NGHIEMTHU().get_DaNghiemthu_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_SCBD_l = controler.getControl_QUYETTOAN()
				.get_DaQuyettoan_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_MUASAM_l = controler.getControl_QUYETTOAN().get_DaQuyettoan_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> data = new ArrayList<>();
		if (th_SCBD_l != null)
			data.addAll(th_SCBD_l);
		if (th_MUASAM_l != null)
			data.addAll(th_MUASAM_l);
		if (th_THANHLY_l != null)
			data.addAll(th_THANHLY_l);
		if (nt_SCBD_l != null)
			data.addAll(nt_SCBD_l);
		if (nt_MUASAM_l != null)
			data.addAll(nt_MUASAM_l);
		if (qt_SCBD_l != null)
			data.addAll(qt_SCBD_l);
		if (qt_MUASAM_l != null)
			data.addAll(qt_MUASAM_l);
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getMA_CONGVIEC(), p1.getMA_CONGVIEC());
			}
		});
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getLOAI_CONGVIEC(), p1.getLOAI_CONGVIEC());
			}
		});

		int i = 1;
		for (CONGVIEC_PHANVIEC e : data) {
			Fill_ItemData f = new Fill_ItemData();
			TreeItem ti = new TreeItem(tree_CongviecDathuchien, SWT.NONE);
			ti.setText(new String[] { "" + i, f.getString_LOAI_PHANVIEC(e.getLOAI_PHANVIEC()),
					f.getString_LoaiCongviec(e.getLOAI_CONGVIEC()), e.getTEN_CONGVIEC(),
					String.valueOf(e.getMA_CONGVIEC()),
					e.getTHOI_DIEM_BAT_DAU() == null ? "" : mdf.getViewStringDate(e.getTHOI_DIEM_BAT_DAU()) });
			ti.setData(e);
			i++;
		}
		treePack(tree_CongviecDathuchien);
	}

	private void fillCongviecDangThuchien() throws SQLException {
		tree_Dangthuchien.removeAll();
		ArrayList<CONGVIEC_PHANVIEC> th_SCBD_l = controler.getControl_THUCHIEN()
				.get_DangThucHien_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> th_MUASAM_l = controler.getControl_THUCHIEN().get_DangThucHien_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> th_THANHLY_l = controler.getControl_THUCHIEN().get_DangThucHien_GIAM_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_SCBD_l = controler.getControl_NGHIEMTHU()
				.get_DangNghiemthu_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_MUASAM_l = controler.getControl_NGHIEMTHU().get_DangNghiemthu_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_SCBD_l = controler.getControl_QUYETTOAN()
				.get_DangQuyettoan_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_MUASAM_l = controler.getControl_QUYETTOAN().get_DangQuyettoan_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> data = new ArrayList<>();
		if (th_SCBD_l != null)
			data.addAll(th_SCBD_l);
		if (th_MUASAM_l != null)
			data.addAll(th_MUASAM_l);
		if (th_THANHLY_l != null)
			data.addAll(th_THANHLY_l);
		if (nt_SCBD_l != null)
			data.addAll(nt_SCBD_l);
		if (nt_MUASAM_l != null)
			data.addAll(nt_MUASAM_l);
		if (qt_SCBD_l != null)
			data.addAll(qt_SCBD_l);
		if (qt_MUASAM_l != null)
			data.addAll(qt_MUASAM_l);
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getMA_CONGVIEC(), p1.getMA_CONGVIEC());
			}
		});
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getLOAI_CONGVIEC(), p1.getLOAI_CONGVIEC());
			}
		});

		int i = 1;
		for (CONGVIEC_PHANVIEC e : data) {
			if (e.getTHOI_DIEM_BAT_DAU() != null) {
				Fill_ItemData f = new Fill_ItemData();
				TreeItem ti = new TreeItem(tree_Dangthuchien, SWT.NONE);
				ti.setText(new String[] { "" + i, f.getString_LOAI_PHANVIEC(e.getLOAI_PHANVIEC()),
						f.getString_LoaiCongviec(e.getLOAI_CONGVIEC()), e.getTEN_CONGVIEC(),
						String.valueOf(e.getMA_CONGVIEC()), mdf.getViewStringDate(e.getTHOI_DIEM_BAT_DAU()) });
				ti.setData(e);
				i++;
			}
		}
		treePack(tree_Dangthuchien);
	}

	private void fillCongviecChotiepnhan() throws SQLException {
		tree_Tiepnhan.removeAll();
		ArrayList<CONGVIEC_PHANVIEC> th_SCBD_l = controler.getControl_THUCHIEN()
				.get_DangThucHien_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> th_MUASAM_l = controler.getControl_THUCHIEN().get_DangThucHien_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> th_THANHLY_l = controler.getControl_THUCHIEN().get_DangThucHien_GIAM_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_SCBD_l = controler.getControl_NGHIEMTHU()
				.get_DangNghiemthu_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> nt_MUASAM_l = controler.getControl_NGHIEMTHU().get_DangNghiemthu_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_SCBD_l = controler.getControl_QUYETTOAN()
				.get_DangQuyettoan_SUACHUA_BAODUONG(user);
		ArrayList<CONGVIEC_PHANVIEC> qt_MUASAM_l = controler.getControl_QUYETTOAN().get_DangQuyettoan_TANG_TAISAN(user);
		ArrayList<CONGVIEC_PHANVIEC> data = new ArrayList<>();
		if (th_SCBD_l != null)
			data.addAll(th_SCBD_l);
		if (th_MUASAM_l != null)
			data.addAll(th_MUASAM_l);
		if (th_THANHLY_l != null)
			data.addAll(th_THANHLY_l);
		if (nt_SCBD_l != null)
			data.addAll(nt_SCBD_l);
		if (nt_MUASAM_l != null)
			data.addAll(nt_MUASAM_l);
		if (qt_SCBD_l != null)
			data.addAll(qt_SCBD_l);
		if (qt_MUASAM_l != null)
			data.addAll(qt_MUASAM_l);
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getMA_CONGVIEC(), p1.getMA_CONGVIEC());
			}
		});
		Collections.sort(data, new Comparator<CONGVIEC_PHANVIEC>() {
			@Override
			public int compare(CONGVIEC_PHANVIEC p2, CONGVIEC_PHANVIEC p1) {
				return Integer.compare(p2.getLOAI_CONGVIEC(), p1.getLOAI_CONGVIEC());
			}
		});

		int i = 1;
		for (CONGVIEC_PHANVIEC e : data) {
			if (e.getTHOI_DIEM_BAT_DAU() == null) {
				Fill_ItemData f = new Fill_ItemData();
				TreeItem ti = new TreeItem(tree_Tiepnhan, SWT.NONE);
				String ngaythamgia = null;
				boolean tiepnhan = false;
				if (e.getPHANVIEC() instanceof GIAI_DOAN_THUC_HIEN) {
					NGUOIDUNG_THUCHIEN ndth = controler.getControl_THUCHIEN_CANBO()
							.getNGUOIDUNG_THUCHIEN(user.getTEN_TAI_KHOAN(), (GIAI_DOAN_THUC_HIEN) e.getPHANVIEC());
					ngaythamgia = mdf.getViewStringDate(ndth.getNGAY_THAM_GIA());
					if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
						DE_XUAT d = null;
						if (dsb != null) {
							d = controler.getControl_DEXUAT().get_DEXUAT(dsb);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
						DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
						DE_XUAT d = null;
						if (dtt != null) {
							d = controler.getControl_DEXUAT().get_DEXUAT(dtt);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
						DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) e.getCONGVIEC();
						DE_XUAT d = null;
						if (dgt != null) {
							d = controler.getControl_DEXUAT().get_DEXUAT(dgt);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					}
				} else if (e.getPHANVIEC() instanceof GIAI_DOAN_NGHIEM_THU) {
					NGUOIDUNG_NGHIEMTHU ndngth = controler.getControl_NGHIEMTHU_CANBO()
							.getNGUOIDUNG_NGHIEMTHU(user.getTEN_TAI_KHOAN(), (GIAI_DOAN_NGHIEM_THU) e.getPHANVIEC());
					ngaythamgia = mdf.getViewStringDate(ndngth.getNGAY_THAM_GIA());
					if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
						GIAI_DOAN_THUC_HIEN d = null;
						if (dsb != null) {
							d = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
						DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
						GIAI_DOAN_THUC_HIEN d = null;
						if (dtt != null) {
							d = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
						DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) e.getCONGVIEC();
						GIAI_DOAN_THUC_HIEN d = null;
						if (dgt != null) {
							d = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					}
				} else if (e.getPHANVIEC() instanceof GIAI_DOAN_QUYET_TOAN) {
					NGUOIDUNG_QUYETTOAN ndqt = controler.getControl_QUYETTOAN_CANBO()
							.getNGUOIDUNG_QUYETTOAN(user.getTEN_TAI_KHOAN(), (GIAI_DOAN_QUYET_TOAN) e.getPHANVIEC());
					ngaythamgia = mdf.getViewStringDate(ndqt.getNGAY_THAM_GIA());
					if (e.getCONGVIEC() instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) e.getCONGVIEC();
						GIAI_DOAN_NGHIEM_THU d = null;
						if (dsb != null) {
							d = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_TANG_TAISAN) {
						DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) e.getCONGVIEC();
						GIAI_DOAN_NGHIEM_THU d = null;
						if (dtt != null) {
							d = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
						}
						if (d.getTHOI_DIEM_CHUYEN_GIAO() != null)
							tiepnhan = true;
					} else if (e.getCONGVIEC() instanceof DOT_THUCHIEN_GIAM_TAISAN) {
						// do notthing
					}
				}

				ti.setText(new String[] { "" + i, f.getString_LOAI_PHANVIEC(e.getLOAI_PHANVIEC()),
						f.getString_LoaiCongviec(e.getLOAI_CONGVIEC()), e.getTEN_CONGVIEC(),
						String.valueOf(e.getMA_CONGVIEC()), ngaythamgia,
						tiepnhan == false ? "Chưa chuyển giao" : "Có thể tiếp nhận" });
				if (tiepnhan == true) {
					Color gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN);
					ti.setForeground(gray);
				}
				ti.setData(e);
				i++;
			}
		}
		treePack(tree_Tiepnhan);
	}

	void treePack(Tree tree) {
		for (TreeColumn tc : tree.getColumns()) {
			tc.pack();
		}
	}

	protected void setTree_Danhsach_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		if (tree_1.isVisible()) {
			if (tree_1.getListeners(SWT.SetData) != null)
				for (Listener lst : tree_1.getListeners(SWT.SetData)) {
					tree_1.removeListener(SWT.SetData, lst);
				}

			tree_1.removeAll();
			ArrayList<TAISAN> tl = controler.getControl_TAISAN().get_TAISAN(dsb);

			int COUNT = tl.size();
			if (COUNT > 0) {
				tree_1.addListener(SWT.SetData, new Listener() {
					public void handleEvent(Event event) {
						TreeItem parent = (TreeItem) event.item;
						int index = event.index;
						TAISAN t = tl.get(index);
						String[] ParentRow = { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
								t.getDonvi_Sudung().getTEN_PHONGBAN(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), String.valueOf(t.getMA_TAISAN()) };
						parent.setText(ParentRow);
						parent.setData(t);
						ArrayList<PHUKIEN> Childrow = t.getPhukienList();
						if (Childrow != null) {
							for (int i = 0; i < Childrow.size(); i++) {
								// Create a child item and add data to the
								// columns
								PHUKIEN pk = Childrow.get(i);
								TreeItem child = new TreeItem(parent, SWT.NONE);
								String[] childrow = { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(), "", "",
										pk.getSERI(), String.valueOf(pk.getMA_PHUKIEN()) };
								child.setText(childrow);
								child.setData("PrimaryKey_Phukien", pk.getMA_PHUKIEN());
							}
						}
						parent.setExpanded(true);
					}
				});
			}
			tree_1.setItemCount(COUNT);

			treePack(tree_1);
		}

	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		// center screen
		setSize(825, 510);
		new FormTemplate().setCenterScreen(getShell());
		setText("C\u00F4ng vi\u1EC7c c\u1EE7a t\u00F4i");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
