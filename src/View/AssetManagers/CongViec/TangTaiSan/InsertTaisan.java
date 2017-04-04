package View.AssetManagers.CongViec.TangTaiSan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.LOAITAISAN_CAP_III;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class InsertTaisan extends Dialog {

	protected ArrayList<TAISAN> result;
	protected Shell shlNhpPtts;
	private Text text_Seri;
	private Text text_Nguyengia;
	private Text text_Tentaisan;
	private Text text_Baohanh;
	private Text text_Ghichu;
	private int model;
	private Tree tree_PTTS;
	private Combo combo_Donviquanly;
	private Combo combo_Donvisudung;
	private Combo combo_Hethong;
	private Combo combo_Tinhtrang;
	private Combo combo_MODEL;
	private Combo combo_Xuatxu;
	private DateTime dateTime;
	private Combo combo_LoaiTaisan;
	private Combo combo_Donvitinh;
	private ArrayList<TAISAN> rowl;
	private int selectIndex;
	private Combo combo_NhomTaisan;
	private Text text_Soluong;
	private Text text_MaPhanmemKetoan;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param rowl
	 * @param selectIndex
	 */
	public InsertTaisan(Shell parent, int style, NGUOIDUNG user, ArrayList<TAISAN> rowl, int selectIndex) {
		super(parent, style);
		setText("SWT Dialog");
		this.rowl = rowl;
		this.selectIndex = selectIndex;
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
		shlNhpPtts.open();
		shlNhpPtts.layout();
		Display display = getParent().getDisplay();
		while (!shlNhpPtts.isDisposed()) {
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
		shlNhpPtts = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlNhpPtts.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/add-icon (1).png"));
		shlNhpPtts.setSize(809, 500);
		new FormTemplate().setCenterScreen(shlNhpPtts);
		shlNhpPtts.setText("Nhập Thông tin Phương tiện tài sản");
		shlNhpPtts.setLayout(new GridLayout(6, false));
		Fill_ItemData f = new Fill_ItemData();

		SashForm sashForm_2 = new SashForm(shlNhpPtts, SWT.VERTICAL);
		sashForm_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

		SashForm sashForm_1 = new SashForm(sashForm_2, SWT.NONE);

		Group group = new Group(sashForm_1, SWT.NONE);
		group.setLayout(new GridLayout(4, false));
		group.setText("Định danh tài sản");
		group.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_6.setText("Tên, mô tả*:");

		text_Tentaisan = new Text(group, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("Model*:");

		combo_MODEL = new Combo(group, SWT.NONE);
		combo_MODEL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("Seri*:");

		text_Seri = new Text(group, SWT.BORDER);
		text_Seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("Nguyên giá:");

		text_Nguyengia = new Text(group, SWT.BORDER | SWT.RIGHT);
		text_Nguyengia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_Nguyengia.setText("0");
		text_Nguyengia.addVerifyListener(new VerifyListener() {
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

		Label label_16 = new Label(group, SWT.NONE);
		label_16.setText("Đơn vị tính:");

		combo_Donvitinh = new Combo(group, SWT.NONE);
		combo_Donvitinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("Ngày sử dụng*:");

		dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("Xuất xứ:");

		combo_Xuatxu = new Combo(group, SWT.NONE);
		combo_Xuatxu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("Đơn vị quản lý:");

		combo_Donviquanly = new Combo(group, SWT.READ_ONLY);
		GridData gd_combo_Donviquanly = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Donviquanly.widthHint = 200;
		combo_Donviquanly.setLayoutData(gd_combo_Donviquanly);

		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("Đơn vị sử dụng:");

		combo_Donvisudung = new Combo(group, SWT.READ_ONLY);
		GridData gd_combo_Donvisudung = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_Donvisudung.widthHint = 200;
		combo_Donvisudung.setLayoutData(gd_combo_Donvisudung);

		Label lblSLng = new Label(group, SWT.NONE);
		lblSLng.setText("Số lượng:");

		text_Soluong = new Text(group, SWT.BORDER | SWT.RIGHT);
		text_Soluong.setText("0");
		text_Soluong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblMLinKt = new Label(group, SWT.NONE);
		lblMLinKt.setText("Mã liên kết:");

		text_MaPhanmemKetoan = new Text(group, SWT.BORDER);
		text_MaPhanmemKetoan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		SashForm sashForm_3 = new SashForm(sashForm_1, SWT.VERTICAL);

		Group grpNhmTiSn = new Group(sashForm_3, SWT.NONE);
		grpNhmTiSn.setSize(425, 74);
		grpNhmTiSn.setText("Nhóm tài sản");
		grpNhmTiSn.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		grpNhmTiSn.setLayout(new GridLayout(2, false));

		Label lblLaiTiSn = new Label(grpNhmTiSn, SWT.NONE);
		lblLaiTiSn.setText("Lọai tài sản*:");

		combo_LoaiTaisan = new Combo(grpNhmTiSn, SWT.NONE);
		combo_LoaiTaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_LoaiTaisan.select(0);

		Label label = new Label(grpNhmTiSn, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Nhóm tài sản*:");

		combo_NhomTaisan = new Combo(grpNhmTiSn, SWT.NONE);
		combo_NhomTaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo_NhomTaisan.select(0);

		Label label_12 = new Label(grpNhmTiSn, SWT.NONE);
		label_12.setText("Hệ thống:");

		combo_Hethong = new Combo(grpNhmTiSn, SWT.READ_ONLY);
		combo_Hethong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group group_4 = new Group(sashForm_3, SWT.NONE);
		group_4.setText("Thông tin khác");
		group_4.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		group_4.setLayout(new GridLayout(2, false));

		Label label_15 = new Label(group_4, SWT.NONE);
		label_15.setText("Tình trạng:");

		combo_Tinhtrang = new Combo(group_4, SWT.READ_ONLY);
		combo_Tinhtrang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		f.setComboBox_TINHTRANH_TAISAN(combo_Tinhtrang);
		combo_Tinhtrang.select(0);

		Label label_17 = new Label(group_4, SWT.NONE);
		GridData gd_label_17 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_17.verticalIndent = 3;
		label_17.setLayoutData(gd_label_17);
		label_17.setText("Bảo hành:");

		text_Baohanh = new Text(group_4, SWT.BORDER);
		text_Baohanh.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblGhiChu = new Label(group_4, SWT.NONE);
		lblGhiChu.setText("Ghi chú:");

		text_Ghichu = new Text(group_4, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		text_Ghichu.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		sashForm_3.setWeights(new int[] { 108, 163 });
		sashForm_1.setWeights(new int[] { 1000, 618 });

		SashForm sashForm = new SashForm(sashForm_2, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		tree_PTTS = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tree_PTTS.setHeaderVisible(true);
		tree_PTTS.setLinesVisible(true);
		tree_PTTS.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TreeItem[] items = tree_PTTS.getSelection();
					if (items.length > 0) {
						if (items[0].getData() instanceof TAISAN) {
							TAISAN t = (TAISAN) items[0].getData();
							if (t != null) {
								fillTaisan(t);
							}
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		TreeColumn trclmnStt = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("Stt");

		TreeColumn trclmnTnTiSn = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnTnTiSn.setWidth(200);
		trclmnTnTiSn.setText("Tên tài sản");

		TreeColumn trclmnModel = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("Model");

		TreeColumn trclmnNgySDng = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnNgySDng.setWidth(100);
		trclmnNgySDng.setText("Ngày sử dụng");

		TreeColumn trclmnSerial = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnSerial.setWidth(100);
		trclmnSerial.setText("Serial");

		TreeColumn trclmnXutX = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnXutX.setWidth(100);
		trclmnXutX.setText("Xuất xứ");

		TreeColumn trclmnNguynGi = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnNguynGi.setWidth(100);
		trclmnNguynGi.setText("Nguyên giá");
		sashForm.setWeights(new int[] { 618 });
		sashForm_2.setWeights(new int[] { 1000, 518 });

		Button btnNhp = new Button(shlNhpPtts, SWT.NONE);
		btnNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ((!combo_MODEL.getText().equals("")) && (!text_Seri.getText().equals(""))
						&& (!text_Tentaisan.equals(""))) {
					TAISAN t = getTAISAN_FromField();
					insertToTable(t);
				} else {
					MessageBox m = new MessageBox(shlNhpPtts);
					m.setText("Lỗi");
					m.setMessage("TÊN PTTS\nMODEL\nSERI không để trống");
					m.open();
				}
			}
		});
		GridData gd_btnNhp = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnNhp.widthHint = 80;
		btnNhp.setLayoutData(gd_btnNhp);
		btnNhp.setText("Nhập");
		btnNhp.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/add-1-icon (1).png"));

		Button btnSa = new Button(shlNhpPtts, SWT.NONE);
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_PTTS.getSelection();
				if (til.length > 0) {
					Object o = til[0].getData();
					if (o instanceof TAISAN) {
						ArrayList<TAISAN> Row = getDataFromTree();
						for (TAISAN t : Row) {
							int i = Row.indexOf(t);
							if (i == tree_PTTS.indexOf(til[0])) {
								TAISAN taisan = (TAISAN) o;
								PHUKIEN phukien = null;
								InsertPhukien ip = new InsertPhukien(shlNhpPtts, SWT.DIALOG_TRIM, taisan, phukien);
								ip.open();
								PHUKIEN result = (PHUKIEN) ip.getResult();
								ArrayList<PHUKIEN> pk = taisan.getPhukienList();
								if (pk == null)
									pk = new ArrayList<>();
								pk.add(result);
								taisan.setPhukienList(pk);
								Row.set(i, taisan);
								break;
							}
						}
						fillTree(Row);
					} else if (o instanceof PHUKIEN) {
						ArrayList<TAISAN> Row = getDataFromTree();
						for (TAISAN t : Row) {
							int i = Row.indexOf(t);
							if (i == tree_PTTS.indexOf(til[0].getParentItem())) {
								int ii = til[0].getParentItem().indexOf(til[0]);
								ArrayList<PHUKIEN> pkl = new ArrayList<>();
								TAISAN ts = (TAISAN) til[0].getParentItem().getData();
								pkl.addAll(ts.getPhukienList());
								InsertPhukien ip = new InsertPhukien(shlNhpPtts, SWT.DIALOG_TRIM, t, pkl.get(ii));
								ip.open();
								PHUKIEN result = (PHUKIEN) ip.getResult();
								pkl.set(ii, result);
								ts.setPhukienList(pkl);
								til[0].getParentItem().setData(ts);
								break;
							}
						}
						fillTree(Row);
					}
				}
			}
		});
		GridData gd_btnSa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSa.widthHint = 80;
		btnSa.setLayoutData(gd_btnSa);
		btnSa.setText("Phụ kiện");
		btnSa.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/plugin-add-icon.png"));

		Button btnLu = new Button(shlNhpPtts, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Save();
			}
		});
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLu.widthHint = 80;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Lưu");
		btnLu.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/Actions-document-save-icon (1).png"));

		Button btnXa = new Button(shlNhpPtts, SWT.NONE);
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_PTTS.getSelection();
				if (til.length > 0) {
					ArrayList<TAISAN> data = getDataFromTree();
					if (til[0].getData() instanceof PHUKIEN) {
						for (TAISAN t : data) {
							for (int i = 0; i < t.getPhukienList().size(); i++) {
								if (t.getPhukienList().get(i).getSERI()
										.equals(((PHUKIEN) til[0].getData()).getSERI())) {
									t.getPhukienList().remove(i);
									break;
								}
							}
						}
					} else if (til[0].getData() instanceof TAISAN) {
						for (int i = 0; i < data.size(); i++) {
							if (data.get(i).getSERI().equals(((TAISAN) til[0].getData()).getSERI())) {
								data.remove(i);
								break;
							}
						}
					}
					fillTree(data);
				}
			}
		});
		GridData gd_btnXa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXa.widthHint = 80;
		btnXa.setLayoutData(gd_btnXa);
		btnXa.setText("Xóa");
		btnXa.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/delete-1-icon (1).png"));

		Button button_8 = new Button(shlNhpPtts, SWT.NONE);
		button_8.setImage(SWTResourceManager.getImage(InsertTaisan.class, "/success-icon.png"));
		GridData gd_button_8 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button_8.widthHint = 80;
		button_8.setLayoutData(gd_button_8);
		button_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Save();
				result = getDataFromTree();
				shlNhpPtts.dispose();
			}
		});
		button_8.setText("Hoàn tất");

		Button btnng = new Button(shlNhpPtts, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNhpPtts.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
		init_EditMode();
	}

	protected void Save() {
		TreeItem[] til = tree_PTTS.getSelection();
		if (til.length > 0) {
			Object o = til[0].getData();
			if (o instanceof TAISAN) {
				ArrayList<TAISAN> Row = getDataFromTree();
				for (TAISAN t : Row) {
					int i = Row.indexOf(t);
					if (i == tree_PTTS.indexOf(til[0])) {
						TAISAN tx = getTAISAN_FromField();
						ArrayList<PHUKIEN> pkl = new ArrayList<>();
						for (TreeItem c : til[0].getItems()) {
							pkl.add((PHUKIEN) c.getData());
						}
						tx.setPhukienList(pkl);
						tx.setMA_TAISAN(((TAISAN) o).getMA_TAISAN());
						Row.set(i, tx);
						break;
					}
				}
				fillTree(Row);
			} else if (o instanceof PHUKIEN) {
				ArrayList<TAISAN> Row = getDataFromTree();
				for (TAISAN t : Row) {
					int i = Row.indexOf(t);
					if (i == tree_PTTS.indexOf(til[0].getParentItem())) {
						int ii = til[0].getParentItem().indexOf(til[0]);
						ArrayList<PHUKIEN> pkl = new ArrayList<>();
						TAISAN ts = (TAISAN) til[0].getParentItem().getData();
						pkl.addAll(ts.getPhukienList());
						PHUKIEN pk = new PHUKIEN();
						pk.setMA_PHUKIEN(((PHUKIEN) o).getMA_PHUKIEN());
						pk.setTEN_PHUKIEN(til[0].getText(1));
						pk.setMODEL(til[0].getText(2));
						pk.setSERI(til[0].getText(3));
						pk.setNGUYEN_GIA(Integer.valueOf(til[0].getText(4)));
						pk.setMA_TAISAN(((PHUKIEN) o).getMA_TAISAN());
						pkl.set(ii, pk);
						ts.setPhukienList(pkl);
						til[0].getParentItem().setData(ts);
						break;
					}
				}
				fillTree(Row);
			}
		}

	}

	private void init() throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		ArrayList<LOAITAISAN_CAP_III> pna = controler.getControl_LOAITAISAN_CAP_III().getAllData();
		ArrayList<NHOMTAISAN_CAP_V> nts = controler.getControl_NHOMTAISAN_CAP_V().getAllData();
		ArrayList<PHONGBAN> pa = controler.getControl_PHONGBAN().getAllDonvi();
		f.setComboBox_DONVI_NOIBO(combo_Donviquanly, pa);
		f.setComboBox_DONVI_NOIBO(combo_Donvisudung, pa);
		f.setComboBox_LOAITAISAN(combo_LoaiTaisan, pna);
		f.setComboBox_NHOMTAISAN(combo_NhomTaisan, nts);
	}

	private void init_EditMode() throws SQLException {
		if (rowl != null) {
			fillTree(rowl);
			result = new ArrayList<>();
			result.addAll(rowl);
			if (selectIndex >= 0) {
				tree_PTTS.select(tree_PTTS.getItem(selectIndex));
				TreeItem[] items = tree_PTTS.getSelection();
				if (items.length > 0) {
					if (items[0].getData() instanceof TAISAN) {
						TAISAN t = (TAISAN) items[0].getData();
						if (t != null) {
							fillTaisan(t);
						}
					}
				}
			}
		}
	}

	private void fillTaisan(TAISAN t) throws SQLException {
		Fill_ItemData f = new Fill_ItemData();
		text_Tentaisan.setText(t.getTEN_TAISAN());
		combo_MODEL.setText(t.getMODEL());
		text_Seri.setText(t.getSERI());
		text_Nguyengia.setText(String.valueOf(t.getNGUYEN_GIA()));
		String[] d = (mdf.getViewStringDate(t.getNGAY_SU_DUNG())).split("-");
		dateTime.setDate(Integer.valueOf(d[2]), Integer.valueOf(d[1]) - 1, Integer.valueOf(d[0]));
		combo_Xuatxu.setText(t.getXUAT_XU());
		text_MaPhanmemKetoan.setText(t.getMA_TANSAN_KETOAN());
		LOAITAISAN_CAP_III pn = controler.getControl_LOAITAISAN_CAP_III()
				.get_LOAITAISAN_CAP_III(t.getMA_LOAITAISAN_CAP_III());
		NHOMTAISAN_CAP_V ntscV = controler.getControl_NHOMTAISAN_CAP_V()
				.getNHOMTAISAN_CAP_V(t.getMA_NHOMTAISAN_CAP_V());
		combo_LoaiTaisan.select(combo_LoaiTaisan.indexOf(pn.getTEN_LOAITAISAN_CAP_III()));
		combo_NhomTaisan.select(combo_NhomTaisan.indexOf(ntscV.getTEN_NHOMTAISAN_CAP_V()));
		combo_Donviquanly.select(combo_Donviquanly
				.indexOf(controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_QUAN_LY()).getTEN_PHONGBAN()));
		combo_Donvisudung.select(combo_Donvisudung
				.indexOf(controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_SU_DUNG()).getTEN_PHONGBAN()));
		combo_Tinhtrang.select(combo_Tinhtrang.indexOf(f.getStringOfTINHTRANG(t.getTINH_TRANG())));
		combo_Donvitinh.setText(t.getDON_VI_TINH());
		text_Baohanh.setText(t.getBAO_HANH());
		text_Ghichu.setText(t.getGHI_CHU());
	}

	protected TAISAN getTAISAN_FromField() {
		TAISAN t = new TAISAN();
		t.setTEN_TAISAN(text_Tentaisan.getText());
		t.setMODEL(combo_MODEL.getText());
		t.setSERI(text_Seri.getText());
		Date date = mdf.getDate(dateTime);
		t.setNGAY_SU_DUNG(date);
		t.setNGUYEN_GIA(Integer.valueOf(text_Nguyengia.getText()));
		t.setXUAT_XU(combo_Xuatxu.getText());
		t.setMA_DON_VI_QUAN_LY(((PHONGBAN) combo_Donviquanly.getData(combo_Donviquanly.getText())).getMA_PHONGBAN());
		t.setMA_DON_VI_SU_DUNG(((PHONGBAN) combo_Donvisudung.getData(combo_Donvisudung.getText())).getMA_PHONGBAN());
		t.setMA_LOAITAISAN_CAP_III(
				((LOAITAISAN_CAP_III) combo_LoaiTaisan.getData(combo_LoaiTaisan.getText())).getMA_LOAITAISAN_CAP_III());
		t.setMA_NHOMTAISAN_CAP_V(
				((NHOMTAISAN_CAP_V) combo_NhomTaisan.getData(combo_NhomTaisan.getText())).getMA_NHOMTAISAN_CAP_V());
		t.setTINH_TRANG((int) combo_Tinhtrang.getData(combo_Tinhtrang.getText()));
		t.setDON_VI_TINH(combo_Donvitinh.getText());
		t.setBAO_HANH(text_Baohanh.getText());
		t.setGHI_CHU(text_Ghichu.getText());
		t.setSOLUONG(Integer.valueOf(text_Soluong.getText()));
		t.setMA_TANSAN_KETOAN(text_MaPhanmemKetoan.getText());
		return t;
	}

	void fillTree(ArrayList<TAISAN> Data) {
		if (tree_PTTS.getItems().length > 0)
			tree_PTTS.removeAll();
		if (Data != null)
			for (TAISAN t : Data) {
				TreeItem Parent = new TreeItem(tree_PTTS, SWT.NONE);
				Parent.setText(
						new String[] { "", t.getTEN_TAISAN(), t.getMODEL(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
								t.getSERI(), t.getXUAT_XU(), String.valueOf(t.getNGUYEN_GIA()) });
				Parent.setData(t);
				int ii = 1;
				if (t.getPhukienList() != null)
					for (PHUKIEN p : t.getPhukienList()) {
						TreeItem child = new TreeItem(Parent, SWT.NONE);
						child.setText(new String[] { "" + ii, p.getTEN_PHUKIEN(), p.getMODEL(), "-", p.getSERI(), "-",
								String.valueOf(p.getNGUYEN_GIA()) });
						child.setData(p);
						ii++;
					}
				Parent.setExpanded(true);
			}
	}

	protected ArrayList<TAISAN> getDataFromTree() {
		ArrayList<TAISAN> result = new ArrayList<>();
		TreeItem[] til = tree_PTTS.getItems();
		for (TreeItem ti : til) {
			if (ti.getData() instanceof TAISAN)
				result.add((TAISAN) ti.getData());
			if (ti.getData() instanceof PHUKIEN) {
				PHUKIEN pk = (PHUKIEN) ti.getData();
				result.get(result.size() - 1).getPhukienList().add(pk);
			}
		}
		return result;
	}

	protected void insertToTable(TAISAN t) {
		TreeItem treeItem = new TreeItem(tree_PTTS, SWT.NONE);
		treeItem.setText(new String[] { "", t.getTEN_TAISAN(), t.getMODEL(), mdf.getViewStringDate(t.getNGAY_SU_DUNG()),
				t.getSERI(), t.getXUAT_XU(), String.valueOf(t.getNGUYEN_GIA()) });
		treeItem.setData(t);
	}

	protected void setCreate() {
		model = 1;
	}

	boolean getCreate() {
		return model == 1 ? true : false;
	}

	boolean getEdit() {
		return model == 2 ? true : false;
	}

}
