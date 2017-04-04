package View.AssetManagers.CongViec.TangTaiSan;

import java.sql.SQLException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import DAO.PHUKIEN;
import DAO.TAISAN;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.NguonTang.ChonNguontang;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

import org.eclipse.swt.widgets.Combo;

public class XemDotTangtaisan extends Shell {
	private Text text_Donvisudung;
	private Text text_Donviquanly;
	private Text text_Tentaisan;
	private Text text_Model;
	private Text text_Serial;
	private Text text_Ngaysudung;
	private Text text_Xuatxu;
	private Text text_Nguyengia;
	private Text text_Donvitinh;
	private Text text_Tinhtrang;
	private Text text_Baohanh;
	private Text text_Hethong;
	private static NGUOIDUNG user;
	private Tree tree;
	private static DOT_THUCHIEN_TANG_TAISAN dtt;

	private Text text_Tendottang;
	private Text text_Mota;
	private Combo combo;
	private Text text_TenNguonTang;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private Text text_Soluong;
	private Text text_MaphanmemKetoan;
	protected NGUONTANG nt;
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(XemDotTangtaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			XemDotTangtaisan shell = new XemDotTangtaisan(display, user, dtt);
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
	 * @param nt
	 * @param dtt
	 * @throws SQLException
	 */
	public XemDotTangtaisan(Display display, NGUOIDUNG user, DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(XemDotTangtaisan.class, "/add-icon (1).png"));
		setLayout(new GridLayout(5, false));
		XemDotTangtaisan.user = user;
		XemDotTangtaisan.dtt = dtt;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		SashForm sashForm_2 = new SashForm(sashForm_1, SWT.NONE);

		Composite composite_2 = new Composite(sashForm_2, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));

		Label lblTnNgunTng = new Label(composite_2, SWT.NONE);
		lblTnNgunTng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTnNgunTng.setText("Tên đợt tăng:");

		text_Tendottang = new Text(composite_2, SWT.BORDER);
		text_Tendottang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLinH = new Label(composite_2, SWT.NONE);
		lblLinH.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLinH.setText("Lý do tăng:");

		combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite_2, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Mô tả:");

		text_Mota = new Text(composite_2, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_2.setWeights(new int[] { 1 });

		tree = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						if (items[0].getData() instanceof TAISAN) {
							TAISAN t = (TAISAN) items[0].getData();
							if (t != null) {
								fillTaisan(t);
							}
						}
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}

			private void fillTaisan(TAISAN t) throws SQLException {
				text_Donvisudung.setText(
						controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_SU_DUNG()).getTEN_PHONGBAN());
				text_Donviquanly.setText(
						controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_QUAN_LY()).getTEN_PHONGBAN());
				text_Tentaisan.setText(t.getTEN_TAISAN());
				text_Model.setText(t.getMODEL());
				text_Serial.setText(t.getSERI());
				text_Ngaysudung.setText(mdf.getViewStringDate(t.getNGAY_SU_DUNG()));
				text_Xuatxu.setText(t.getXUAT_XU());
				text_Nguyengia.setText("" + t.getNGUYEN_GIA());
				text_Donvitinh.setText(t.getDON_VI_TINH());
				text_Baohanh.setText(t.getBAO_HANH());
				text_Soluong.setText(t.getSOLUONG() + "");
				text_MaphanmemKetoan.setText(t.getMA_TANSAN_KETOAN() == null ? "null" : t.getMA_TANSAN_KETOAN());
			}
		});

		TreeColumn trclmnStt = new TreeColumn(tree, SWT.NONE);
		trclmnStt.setWidth(55);
		trclmnStt.setText("STT");

		TreeColumn trclmnTnMT = new TreeColumn(tree, SWT.NONE);
		trclmnTnMT.setWidth(131);
		trclmnTnMT.setText("TÊN, MÔ TẢ TÀI SẢN");

		TreeColumn trclmnModel = new TreeColumn(tree, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("MODEL");

		TreeColumn trclmnNmSDng = new TreeColumn(tree, SWT.NONE);
		trclmnNmSDng.setWidth(100);
		trclmnNmSDng.setText("NĂM SỬ DỤNG");

		TreeColumn trclmnSSri = new TreeColumn(tree, SWT.NONE);
		trclmnSSri.setWidth(100);
		trclmnSSri.setText("SỐ SÊ-RI");

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("Đơn vị sử dụng:");

		text_Donvisudung = new Text(composite_1, SWT.NONE);
		text_Donvisudung.setEditable(false);
		text_Donvisudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Donvisudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("Đơn vị quản lý:");

		text_Donviquanly = new Text(composite_1, SWT.NONE);
		text_Donviquanly.setEditable(false);
		text_Donviquanly.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Donviquanly.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_17 = new Label(composite_1, SWT.NONE);
		label_17.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_17.setText("Tên tài sản:");

		text_Tentaisan = new Text(composite_1, SWT.NONE);
		text_Tentaisan.setEditable(false);
		text_Tentaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_8.setText("Model:");

		text_Model = new Text(composite_1, SWT.NONE);
		text_Model.setEditable(false);
		text_Model.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_9 = new Label(composite_1, SWT.NONE);
		label_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_9.setText("Serial:");

		text_Serial = new Text(composite_1, SWT.NONE);
		text_Serial.setEditable(false);
		text_Serial.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Serial.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_10.setText("Ngày sử dụng:");

		text_Ngaysudung = new Text(composite_1, SWT.NONE);
		text_Ngaysudung.setEditable(false);
		text_Ngaysudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaysudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_11 = new Label(composite_1, SWT.NONE);
		label_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_11.setText("Xuất xứ:");

		text_Xuatxu = new Text(composite_1, SWT.NONE);
		text_Xuatxu.setEditable(false);
		text_Xuatxu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Xuatxu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_12 = new Label(composite_1, SWT.NONE);
		label_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_12.setText("Nguyên giá:");

		text_Nguyengia = new Text(composite_1, SWT.NONE);
		text_Nguyengia.setEditable(false);
		text_Nguyengia.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Nguyengia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_13 = new Label(composite_1, SWT.NONE);
		label_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_13.setText("Đơn vị tính:");

		text_Donvitinh = new Text(composite_1, SWT.NONE);
		text_Donvitinh.setEditable(false);
		text_Donvitinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Donvitinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_14 = new Label(composite_1, SWT.NONE);
		label_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_14.setText("Tình trạng:");

		text_Tinhtrang = new Text(composite_1, SWT.NONE);
		text_Tinhtrang.setEditable(false);
		text_Tinhtrang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_15 = new Label(composite_1, SWT.NONE);
		label_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_15.setText("Bảo hành:");

		text_Baohanh = new Text(composite_1, SWT.NONE);
		text_Baohanh.setEditable(false);
		text_Baohanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Baohanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSLng = new Label(composite_1, SWT.NONE);
		lblSLng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSLng.setText("Số lượng:");

		text_Soluong = new Text(composite_1, SWT.NONE);
		text_Soluong.setEditable(false);
		text_Soluong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Soluong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMLinKt = new Label(composite_1, SWT.NONE);
		lblMLinKt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMLinKt.setText("Mã liên kết:");

		text_MaphanmemKetoan = new Text(composite_1, SWT.NONE);
		text_MaphanmemKetoan.setEditable(false);
		text_MaphanmemKetoan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_MaphanmemKetoan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_20 = new Label(composite_1, SWT.NONE);
		label_20.setText("Thuộc về hệ thống");

		text_Hethong = new Text(composite_1, SWT.NONE);
		text_Hethong.setEditable(false);
		text_Hethong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Hethong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Tên nguồn tăng:");
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

		text_TenNguonTang = new Text(composite_1, SWT.BORDER);
		text_TenNguonTang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("Giới thiệu:");
		GridData gd_label_1 = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_label_1.verticalIndent = 3;
		label_1.setLayoutData(gd_label_1);

		text_Gioithieu = new Text(composite_1, SWT.BORDER);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("Liên hệ:");
		GridData gd_label_4 = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_label_4.verticalIndent = 3;
		label_4.setLayoutData(gd_label_4);

		text_Lienhe = new Text(composite_1, SWT.BORDER | SWT.MULTI);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnNgunMuaSm = new Button(this, SWT.NONE);
		btnNgunMuaSm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguontang cnt = new ChonNguontang(getShell(), SWT.DIALOG_TRIM, user, 0);
					cnt.open();
					nt = cnt.getResult();
					if (nt != null) {
						text_TenNguonTang.setText(nt.getTEN_NGUONTANG());
						text_Gioithieu.setText(nt.getGIOI_THIEU());
						text_Lienhe.setText(nt.getLIEN_HE());
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNgunMuaSm.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnNgunMuaSm.setText("Nguồn Mua sắm - Tiếp nhận");
		btnNgunMuaSm.setImage(SWTResourceManager.getImage(XemDotTangtaisan.class, "/phone-icon.png"));

		Button btnThn = new Button(this, SWT.NONE);
		btnThn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ArrayList<TAISAN> row = new ArrayList<>();
					InsertTaisan it = new InsertTaisan(getShell(), SWT.DIALOG_TRIM, user, null, 0);
					it.open();

					if (it.result != null) {
						row.addAll(it.result);
					}
					Inserttable(row);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnThn.setImage(SWTResourceManager.getImage(XemDotTangtaisan.class, "/add-1-icon (1).png"));
		GridData gd_btnThn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnThn.widthHint = 80;
		btnThn.setLayoutData(gd_btnThn);
		btnThn.setText("Thêm");

		Button btnThayi = new Button(this, SWT.NONE);
		btnThayi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] til = tree.getSelection();
					if (til.length > 0) {
						ArrayList<TAISAN> tl = getDataFromTree();
						int SelectedIndex = tree.indexOf(til[0]);
						InsertTaisan it = new InsertTaisan(getShell(), SWT.DIALOG_TRIM, user, tl, SelectedIndex);
						it.open();
						tree.removeAll();
						if (it.result != null) {
							Inserttable(it.result);
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnThayi.setImage(SWTResourceManager.getImage(XemDotTangtaisan.class, "/edit-validated-icon (1).png"));
		GridData gd_btnThayi = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThayi.widthHint = 80;
		btnThayi.setLayoutData(gd_btnThayi);
		btnThayi.setText("Thay đổi");

		Button btnLu = new Button(this, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateThongtinDottang();
					updateThongtinTaisan();
					GiaoViec.FillTableMuasam();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void updateThongtinDottang() throws SQLException {
				DOT_THUCHIEN_TANG_TAISAN dtt = new DOT_THUCHIEN_TANG_TAISAN();
				dtt.setMA_DOT_TANG(XemDotTangtaisan.dtt.getMA_DOT_TANG());
				dtt.setTEN_DOT_TANG(text_Tendottang.getText());
				dtt.setLY_DO_TANG((int) combo.getData(combo.getText()));
				dtt.setMO_TA(text_Mota.getText());
				controler.getControl_DOT_THUCHIEN_TANG_TAISAN().update_DOT_TANG_TAISAN(dtt);
			}

			private void updateThongtinTaisan() throws SQLException {
				XoaTaisan_KhongtrongDanhsachMoi();
				if (tree.getItems().length > 0) {
					NhapTaisan_TrongDanhsachMoi();
				}
			}

			private void NhapTaisan_TrongDanhsachMoi() throws SQLException {
				for (TreeItem ti : tree.getItems()) {
					if (ti.getData() instanceof TAISAN) {
						TAISAN t = (TAISAN) ti.getData();
						if (t.getMA_TAISAN() <= 0) {

							int Key = controler.getControl_TAISAN().insert_TAISAN(t);
							if (Key > 0) {
								t.setMA_TAISAN(Key);
								controler.getControl_TAISAN_DOT_THUCHIEN_TANG_TAISAN().set_DOTTANGTAISAN_TAISAN(dtt, t);
							}
						} else {
							controler.getControl_TAISAN().Update_Taisan(t);
							XoaPhukien_KhongtrongDanhsachMoi(t);
							NhapPhukien_TrongDanhsachMoi(t);
						}
					}
				}
			}

			private void NhapPhukien_TrongDanhsachMoi(TAISAN t) throws SQLException {
				for (PHUKIEN pk : t.getPhukienList()) {
					if (pk.getMA_PHUKIEN() <= 0) {
						controler.getControl_TAISAN().insert_PHUKIEN(pk);
					} else {
						controler.getControl_TAISAN().Update_Phukien(pk);
					}
				}
			}

			private void XoaPhukien_KhongtrongDanhsachMoi(TAISAN t) throws SQLException {
				ArrayList<PHUKIEN> tdtt = controler.getControl_TAISAN().get_DataPhuKien(t);
				for (PHUKIEN pk : tdtt) {
					boolean flag = true;
					for (TreeItem ti : tree.getItems()) {
						if (((TAISAN) ti.getData()).getMA_TAISAN() == t.getMA_TAISAN()) {
							for (TreeItem ti2 : ti.getItems()) {
								if (ti2.getData() instanceof PHUKIEN)
									if (((PHUKIEN) ti2.getData()).getMA_PHUKIEN() == pk.getMA_PHUKIEN()) {
										flag = false;
									}
							}
						}
					}
					if (flag) {
						controler.getControl_TAISAN().delete_PHUKIEN(pk);
					}
				}
			}

			private void XoaTaisan_KhongtrongDanhsachMoi() throws SQLException {
				ArrayList<TAISAN> tdtt = controler.getControl_TAISAN().get_TAINSAN_FULL_INFO(dtt);
				for (TAISAN t : tdtt) {
					boolean flag = true;
					for (TreeItem ti : tree.getItems()) {
						if (ti.getData() instanceof TAISAN)
							if (((TAISAN) ti.getData()).getMA_TAISAN() == t.getMA_TAISAN()) {
								flag = false;
							}
					}
					if (flag) {
						controler.getControl_TAISAN().delete_TAISAN(t);
					}
				}
			}
		});
		btnLu.setImage(SWTResourceManager.getImage(XemDotTangtaisan.class, "/Actions-document-save-icon (1).png"));
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLu.widthHint = 80;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Lưu");

		Button btnHonTt = new Button(this, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnHonTt.widthHint = 80;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Đóng");

		TreeRowStyle ts = new TreeRowStyle();
		ts.setTreeItemHeight(tree, 20);

		TreeColumn trclmnMaTaiSan = new TreeColumn(tree, SWT.NONE);
		trclmnMaTaiSan.setWidth(100);
		trclmnMaTaiSan.setText("MÃ TÀI SẢN");
		sashForm_1.setWeights(new int[] { 618, 1000 });

		ArrayList<TAISAN> row = controler.getControl_TAISAN().get_TAINSAN_FULL_INFO(dtt);
		Filltable(row);
		createContents();
		init_Field();
	}

	private void Inserttable(ArrayList<TAISAN> row) {
		for (TAISAN t : row) {
			TreeItem ti = new TreeItem(tree, SWT.NONE);
			ti.setText(new String[] { "", t.getTEN_TAISAN(), t.getMODEL(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
					t.getSERI(), String.valueOf(t.getMA_TAISAN()) });
			ti.setData(t);
			int i = 1;
			if (t.getPhukienList() != null)
				for (PHUKIEN pk : t.getPhukienList()) {
					TreeItem child = new TreeItem(ti, SWT.NONE);
					child.setText(new String[] { "" + i, pk.getTEN_PHUKIEN(), pk.getMODEL(), "-", pk.getSERI() });
					child.setData(pk);
					i++;
				}
		}
	}

	private ArrayList<TAISAN> getDataFromTree() {
		ArrayList<TAISAN> row = new ArrayList<>();
		for (TreeItem ti : tree.getItems()) {
			if (ti.getData() instanceof TAISAN) {
				row.add((TAISAN) ti.getData());
			}
		}
		return row;
	}

	private void init_Field() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		f.setComboBox_LYDOTANG(combo);
		if (dtt != null) {
			text_Tendottang.setText(dtt.getTEN_DOT_TANG());
			combo.select(combo.indexOf(f.getStringOfLYDOTANG(dtt.getLY_DO_TANG())));
			text_Mota.setText(dtt.getMO_TA());
			NGUONTANG ngtg = controler.getControl_NGUONTANG().get_NguonTang(dtt);
			if (ngtg != null) {
				text_TenNguonTang.setText(ngtg.getTEN_NGUONTANG());
				text_Gioithieu.setText(ngtg.getGIOI_THIEU());
				text_Lienhe.setText(ngtg.getLIEN_HE());
			}
		}
	}

	private void Filltable(ArrayList<TAISAN> row) {
		tree.removeAll();
		int index = 1;
		if (row != null)
			for (TAISAN t : row) {
				TreeItem parent = new TreeItem(tree, SWT.NONE);
				parent.setText(new String[] { String.valueOf(index), t.getTEN_TAISAN(), t.getMODEL(),
						t.getNGAY_SU_DUNG() == null ? "-" : mdf.getViewStringDate(t.getNGAY_SU_DUNG()), t.getSERI(),
						"" + t.getMA_TAISAN() });
				parent.setData(t);
				ArrayList<PHUKIEN> Childrow = t.getPhukienList();
				if (Childrow != null) {
					for (int i = 0; i < Childrow.size(); i++) {
						PHUKIEN pk = Childrow.get(i);
						TreeItem child = new TreeItem(parent, SWT.NONE);
						child.setText(new String[] { String.valueOf(i + 1), pk.getTEN_PHUKIEN(), pk.getMODEL(), "-",
								pk.getSERI() });
						child.setData(pk);
					}
				}
				parent.setExpanded(true);
				index++;
			}
		for (TreeColumn tc : tree.getColumns())
			tc.pack();

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Đợt thực hiện Mua sắm - tiếp nhận tài sản");
		setSize(840, 520);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
