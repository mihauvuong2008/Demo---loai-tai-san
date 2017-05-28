package View.AssetManagers.CongViec.Suachua;

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
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.PHONGBAN;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.TAISAN;
import DAO.TAPHOSO;
import View.AssetManagers.CongViec.Baoduong.NhapDeXuat;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.Hoso.Taphoso_View;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class Taodot_Suachua extends Dialog {
	private final Controler controler;
	private Text text_Mota;
	private Text text_Tendot_Suachua;
	private Tree tree_PTTS;
	private Button btnDong;
	private final int itemHeight = 21;
	protected NGUONSUACHUA_BAODUONG nsbd;
	private Text text_DonviDexuat;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Taodot_Suachua.class);
	private static NGUOIDUNG user;
	private Shell shell;
	private Object result;
	private Text text_Sodexuat;
	private Text text_NgaythangVanban;
	private Text text_Donvi;
	private Text text_Ngaytiepnhan;
	private Text text_Ngaygiao;
	private Text text_Trichyeu;
	private ArrayList<TAISAN> dataCreate;
	protected DOT_THUCHIEN_SUACHUA_BAODUONG ViewAndEdit_MODE_dsb;
	protected DE_XUAT Insert_dx;
	private Button button;
	private Text text_Tenlienhe;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private Fill_ItemData f = new Fill_ItemData();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @wbp.parser.constructor
	 */

	public Taodot_Suachua(Shell parent, int Style, NGUOIDUNG user, ArrayList<TAISAN> dataCreate) {
		super(parent, Style);
		setText("SWT Dialog");
		Taodot_Suachua.user = user;
		controler = new Controler(user);
		this.dataCreate = dataCreate;
	}

	public Taodot_Suachua(Shell parent, int Style, NGUOIDUNG user, DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		super(parent, Style);
		setText("SWT Dialog");
		Taodot_Suachua.user = user;
		controler = new Controler(user);
		this.ViewAndEdit_MODE_dsb = dsb;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */

	public Object open() throws SQLException {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	public void createContents() throws SQLException {
		shell = new Shell(getParent(), SWT.SHELL_TRIM);
		shell.setImage(user.getIcondata().SuachuaIcon);
		shell.setText("Tạo Đợt Sửa chữa Phương tiện Tài sản");
		shell.setSize(777, 480);
		new FormTemplate().setCenterScreen(shell);

		TreeRowStyle ts = new TreeRowStyle();
		shell.setLayout(new GridLayout(3, false));

		@SuppressWarnings("unused")
		Fill_ItemData fi = new Fill_ItemData();

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);
		Composite composite = new Composite(sashForm_1, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblTntSa = new Label(composite, SWT.NONE);
		lblTntSa.setText("Tên đợt Sửa chữa*:");

		text_Tendot_Suachua = new Text(composite, SWT.BORDER);
		text_Tendot_Suachua.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Đơn vị:");

		text_DonviDexuat = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_DonviDexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_DonviDexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(composite, SWT.NONE);
		GridData gd_label_4 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_4.verticalIndent = 3;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("Mô tả:");

		text_Mota = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		new Label(composite, SWT.NONE);

		Button btnNgunSaCha = new Button(composite, SWT.NONE);
		GridData gd_btnNgunSaCha = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNgunSaCha.widthHint = 85;
		btnNgunSaCha.setLayoutData(gd_btnNgunSaCha);
		btnNgunSaCha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonSuachua_Baoduong cnsb = new ChonNguonSuachua_Baoduong(shell, SWT.DIALOG_TRIM, user);
					cnsb.open();
					nsbd = cnsb.getResult();
					if (ViewAndEdit_MODE_dsb == null) {
						fillNguonSuachuaBaoduong(nsbd);
						return;
					}
					if (nsbd == null) {
						MessageBox m = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CLOSE);
						m.setText("Xóa dữ liệu cũ?");
						m.setMessage("Bạn muốn xóa dữ liệu cũ?");
						int rc = m.open();
						switch (rc) {
						case SWT.CANCEL:
							break;
						case SWT.YES:
							ViewAndEdit_MODE_dsb.setMA_NGUONSUACHUA_BAODUONG(-1);
							break;
						case SWT.NO:
							break;
						}
					} else {
						ViewAndEdit_MODE_dsb.setMA_NGUONSUACHUA_BAODUONG(nsbd.getMA_NGUONSUACHUA_BAODUONG());
					}
					controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
							.update_DOT_THUCHIEN_SUACHUA_BAODUONG(ViewAndEdit_MODE_dsb);
					fillNguonSuachuaBaoduong(ViewAndEdit_MODE_dsb);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		btnNgunSaCha.setImage(user.getIcondata().PhoneIcon);
		btnNgunSaCha.setText("Liên hệ");

		tree_PTTS = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_PTTS.setLinesVisible(true);
		tree_PTTS.setHeaderVisible(true);
		TreeColumn trclmnStt = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("Stt");

		TreeColumn trclmnTnMT = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnTnMT.setWidth(100);
		trclmnTnMT.setText("Tên, mô tả");

		TreeColumn trclmnHngSnXut = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnHngSnXut.setWidth(100);
		trclmnHngSnXut.setText("Model");

		TreeColumn trclmnNgySDng = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnNgySDng.setWidth(100);
		trclmnNgySDng.setText("Ngày sử dụng");

		TreeColumn trclmnDngXe = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnDngXe.setWidth(100);
		trclmnDngXe.setText("Serial");

		TreeColumn trclmnMPhngTin = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnMPhngTin.setWidth(90);
		trclmnMPhngTin.setText("Mã PTTS");

		Menu menu = new Menu(tree_PTTS);
		tree_PTTS.setMenu(menu);

		MenuItem mntmThmPhngTin = new MenuItem(menu, SWT.NONE);
		mntmThmPhngTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Nhapdanhsach nds = new Nhapdanhsach(shell, SWT.DIALOG_TRIM, user, getTreeData());
					nds.open();
					if (nds.isAccept()) {
						ArrayList<TAISAN> tsl = new ArrayList<>();
						tsl.addAll(getTreeData());
						tsl.addAll(nds.getResult_danhsachPTTS());
						fillTable(tsl);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThmPhngTin.setText("Thêm Phương tiện tài sản");

		MenuItem mntmXoa = new MenuItem(menu, SWT.NONE);
		mntmXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		mntmXoa.setText("Xóa");
		ts.setTreeItemHeight(tree_PTTS, itemHeight);
		sashForm_1.setWeights(new int[] { 618, 1000 });

		ExpandBar expandBar = new ExpandBar(sashForm, SWT.NONE);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));

		ExpandItem xpndtmXut = new ExpandItem(expandBar, SWT.NONE);
		xpndtmXut.setExpanded(true);
		xpndtmXut.setText("Đề xuất");

		Composite composite_2 = new Composite(expandBar, SWT.NONE);
		xpndtmXut.setControl(composite_2);
		composite_2.setLayout(new GridLayout(2, false));

		Label label = new Label(composite_2, SWT.NONE);
		label.setText("Số đề xuất: ");

		text_Sodexuat = new Text(composite_2, SWT.NONE);
		text_Sodexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Sodexuat.setEditable(false);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("Ngày tháng: ");

		text_NgaythangVanban = new Text(composite_2, SWT.NONE);
		text_NgaythangVanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_NgaythangVanban.setEditable(false);
		text_NgaythangVanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("Đơn vị: ");

		text_Donvi = new Text(composite_2, SWT.NONE);
		text_Donvi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Donvi.setEditable(false);
		text_Donvi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite_2, SWT.NONE);
		label_5.setText("Ngày xử lý:");

		text_Ngaytiepnhan = new Text(composite_2, SWT.NONE);
		text_Ngaytiepnhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaytiepnhan.setEditable(false);
		text_Ngaytiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(composite_2, SWT.NONE);
		label_6.setText("Ngày giao:");

		text_Ngaygiao = new Text(composite_2, SWT.NONE);
		text_Ngaygiao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaygiao.setEditable(false);
		text_Ngaygiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_7 = new Label(composite_2, SWT.NONE);
		GridData gd_label_7 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_7.verticalIndent = 3;
		label_7.setLayoutData(gd_label_7);
		label_7.setText("Ghi chú: ");

		text_Trichyeu = new Text(composite_2, SWT.NONE);
		text_Trichyeu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Trichyeu.setEditable(false);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(composite_2, SWT.NONE);

		button = new Button(composite_2, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Insert_dx = null;
					if (ViewAndEdit_MODE_dsb != null
							&& ViewAndEdit_MODE_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() > 0) {
						Insert_dx = controler.getControl_DEXUAT().get_DEXUAT(ViewAndEdit_MODE_dsb);
					}
					if (Insert_dx != null) {
						TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(Insert_dx.getMA_TAPHOSO());
						Taphoso_View thsv = new Taphoso_View(shell, SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					} else {
						NhapDeXuat ndx = new NhapDeXuat(shell, SWT.DIALOG_TRIM, user);
						ndx.open();
						Insert_dx = ndx.result;
						if (Insert_dx == null) {
							return;
						} else {
							fillDexuat(Insert_dx);
						}
						if (ViewAndEdit_MODE_dsb == null)
							return;
						if (ViewAndEdit_MODE_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() <= 0)
							return;
						int Ma_Quatrinh_Dexuat_thuchien = getMaQuantrinhDexuatThuchien(Insert_dx);
						if (Ma_Quatrinh_Dexuat_thuchien <= 0)
							return;
						boolean ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
								.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_DEXUAT_THUCHIEN(
										ViewAndEdit_MODE_dsb, Ma_Quatrinh_Dexuat_thuchien);
						if (ict) {
							MessageBox m = new MessageBox(shell, SWT.ICON_WORKING);
							m.setText("Hoàn tất");
							m.setMessage("Thêm Đề xuất Hoàn tất");
							m.open();
							fillDexuat(Insert_dx);
						}
					}
				} catch (NullPointerException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		button.setText("Thêm Hồ sơ");
		button.setImage(user.getIcondata().DexuatIcon);
		xpndtmXut.setHeight(180);

		ExpandItem xpndtmLinH = new ExpandItem(expandBar, SWT.NONE);
		xpndtmLinH.setText("Liên hệ");

		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmLinH.setControl(composite_1);
		xpndtmLinH.setHeight(120);
		composite_1.setLayout(new GridLayout(2, false));

		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setText("Tên liên hệ: ");

		text_Tenlienhe = new Text(composite_1, SWT.BORDER);
		text_Tenlienhe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_9 = new Label(composite_1, SWT.NONE);
		GridData gd_label_9 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_9.verticalIndent = 3;
		label_9.setLayoutData(gd_label_9);
		label_9.setText("Giới thiệu: ");

		text_Gioithieu = new Text(composite_1, SWT.BORDER);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_10 = new Label(composite_1, SWT.NONE);
		GridData gd_label_10 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_10.verticalIndent = 3;
		label_10.setLayoutData(gd_label_10);
		label_10.setText("Liên hệ: ");

		text_Lienhe = new Text(composite_1, SWT.BORDER);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1000, 500 });
		new Label(shell, SWT.NONE);

		Button btnXong = new Button(shell, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dataCreate == null) {
						doUpdate();
					} else {
						TaoMoidotSuachua();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Lưu");
		btnXong.setImage(user.getIcondata().saveIcon);

		btnDong = new Button(shell, SWT.NONE);
		btnDong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnDong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDong.widthHint = 75;
		btnDong.setLayoutData(gd_btnDong);
		btnDong.setText("Đóng");
		init_loadMODE();
	}

	protected void fillNguonSuachuaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG viewAndEdit_MODE_dsb2) throws SQLException {
		text_Tenlienhe.setText("");
		text_Gioithieu.setText("");
		text_Lienhe.setText("");
		if (viewAndEdit_MODE_dsb2 == null)
			return;
		NGUONSUACHUA_BAODUONG nsb = controler.getControl_NGUONSUACHUA_BAODUONG()
				.get_NguonSuachua_Baoduong(viewAndEdit_MODE_dsb2);
		if (nsb == null)
			return;
		text_Tenlienhe.setText(nsb.getTEN_NGUONSUACHUA_BAODUONG());
		text_Gioithieu.setText(nsb.getGIOI_THIEU());
		text_Lienhe.setText(nsb.getLIEN_HE());
	}

	void fillNguonSuachuaBaoduong(NGUONSUACHUA_BAODUONG nsb) {
		if (nsb == null)
			return;
		text_Tenlienhe.setText(nsb.getTEN_NGUONSUACHUA_BAODUONG());
		text_Gioithieu.setText(nsb.getGIOI_THIEU());
		text_Lienhe.setText(nsb.getLIEN_HE());
	}

	protected int getMaQuantrinhDexuatThuchien(DE_XUAT insert_dx2) throws NullPointerException, SQLException {
		int key = controler.getControl_DEXUAT().insert_DEXUAT(Insert_dx);
		if (key <= 0)
			return -1;
		Insert_dx.setMA_DE_XUAT(key);
		QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
		qdt.setMA_DE_XUAT(key);
		qdt.setLOAI_CONGVIEC(new Fill_ItemData().getInt_LoaiCongviec_Suachua_Baoduong());
		return controler.getControl_QUATRINH_DEXUAT_THUCHIEN().insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
	}

	protected void TaoMoidotSuachua() {
		try {
			if (!checkTextNotNULL()) {
				showMessage_FillText();
				return;
			}
			DOT_THUCHIEN_SUACHUA_BAODUONG dsb = new DOT_THUCHIEN_SUACHUA_BAODUONG();
			dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Suachua.getText());
			dsb.setSUACHUA_BAODUONG(f.getInt_Suachua());
			dsb.setMO_TA(text_Mota.getText());

			int ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().InsertDOT_THUCHIEN_SUACHUA_BAODUONG(dsb,
					null, null);
			dsb.setMA_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
			int Ma_Quatrinh_Dexuat_thuchien = getMaQuantrinhDexuatThuchien(Insert_dx);
			if (Ma_Quatrinh_Dexuat_thuchien > 0)
				controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
						.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_DEXUAT_THUCHIEN(dsb,
								Ma_Quatrinh_Dexuat_thuchien);
			DOT_THUCHIEN_SUACHUA_BAODUONG dsb1 = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.get_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
			insertDanhsachTaisan(dsb1);
			if (ict >= 0) {
				showMessage_Succes();
				shell.dispose();
				GiaoViec gv = new GiaoViec(user);
				gv.open();
			} else {
				showMessage_Fail();
			}
		} catch (SQLException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
	}

	private void insertDanhsachTaisan(DOT_THUCHIEN_SUACHUA_BAODUONG dsb1) throws SQLException {
		TreeItem[] til = tree_PTTS.getItems();
		for (TreeItem ti : til) {
			TAISAN ts = (TAISAN) ti.getData();
			controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().set_DOT_THUCHIEN_SUACHUA_TAISAN(dsb1, ts);
		}
	}

	protected void delete() {
		TreeItem[] til = tree_PTTS.getSelection();
		if (til.length > 0) {
			removeTreeItem(til);
		}
		fillTable(getTreeData());
	}

	private void removeTreeItem(TreeItem[] til) {
		for (TreeItem item : til) {
			item.dispose();
		}
	}

	protected void doUpdate() throws SQLException {
		if (checkTextNotNULL()) {
			ViewAndEdit_MODE_dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Suachua.getText());
			ViewAndEdit_MODE_dsb.setSUACHUA_BAODUONG(2);
			ViewAndEdit_MODE_dsb.setMO_TA(text_Mota.getText());
			if (nsbd != null)
				ViewAndEdit_MODE_dsb.setMA_NGUONSUACHUA_BAODUONG(nsbd.getMA_NGUONSUACHUA_BAODUONG());
			boolean flg = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.update_DOT_THUCHIEN_SUACHUA_BAODUONG(ViewAndEdit_MODE_dsb);
			if (flg) {
				ArrayList<TAISAN> oldList = controler.getControl_TAISAN().get_TAISAN(ViewAndEdit_MODE_dsb);
				ArrayList<TAISAN> currentList = getTreeData();
				doInsert(oldList, currentList);
				doDelete(oldList, currentList);
			}
		} else {
			showMessage_FillText();
		}
	}

	protected ArrayList<TAISAN> getTreeData() {
		ArrayList<TAISAN> result = new ArrayList<>();
		TreeItem[] til = tree_PTTS.getItems();
		if (til.length > 0) {
			for (TreeItem ti : til) {
				TAISAN t = (TAISAN) ti.getData();
				result.add(t);
			}
			return result;
		}
		return new ArrayList<>();
	}

	private void doDelete(ArrayList<TAISAN> oldList, ArrayList<TAISAN> currentList) throws SQLException {
		if (currentList != null && oldList != null)
			for (TAISAN t : oldList) {
				boolean deleteFlag = true;
				for (TAISAN dt : currentList) {
					if (dt.getMA_TAISAN() == t.getMA_TAISAN()) {
						deleteFlag = false;
					}
				}
				if (deleteFlag) {
					controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().remove(ViewAndEdit_MODE_dsb, t);
				}
			}
	}

	private void doInsert(ArrayList<TAISAN> oldList, ArrayList<TAISAN> currentList) throws SQLException {
		if (currentList != null && oldList != null)
			for (TAISAN t : currentList) {
				boolean insertFlag = true;
				for (TAISAN dt : oldList) {
					if (dt.getMA_TAISAN() == t.getMA_TAISAN()) {
						insertFlag = false;
					}
				}
				if (insertFlag) {
					controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
							.set_DOT_THUCHIEN_SUACHUA_TAISAN(ViewAndEdit_MODE_dsb, t);
				}
			}
	}

	private void init_loadMODE() throws SQLException {
		if (ViewAndEdit_MODE_dsb != null) {
			setupMODE_Layout();
			if (ViewAndEdit_MODE_dsb != null) {
				text_Tendot_Suachua.setText(ViewAndEdit_MODE_dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
				text_Mota.setText(ViewAndEdit_MODE_dsb.getMO_TA());
				ArrayList<TAISAN> tsl = controler.getControl_TAISAN().get_TAISAN(ViewAndEdit_MODE_dsb);
				fillTable(tsl);
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(ViewAndEdit_MODE_dsb);
				if (dx == null)
					return;
				button.setText("Xem Hồ sơ");
				PHONGBAN p = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
				text_DonviDexuat.setText(p.getTEN_PHONGBAN());
				fillNguonSuachuaBaoduong(ViewAndEdit_MODE_dsb);
				fillDexuat(dx);
			}

		} else if (dataCreate != null) {
			fillTable(dataCreate);
		}
	}

	private void fillDexuat(DE_XUAT dx) throws SQLException {
		text_Sodexuat.setText(dx.getSODEXUAT());
		text_NgaythangVanban.setText(mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
		PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
		text_Donvi.setText(pb.getTEN_PHONGBAN());
		text_Ngaytiepnhan.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
		text_Ngaygiao.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
		text_Trichyeu.setText(dx.getGHI_CHU());
	}

	private void setupMODE_Layout() {
		if (ViewAndEdit_MODE_dsb != null) {
			shell.setText("Đợt thực hiện Sửa chữa phương tiện tài sản");
			btnDong.setText("Hoàn tất");
		}
	}

	protected void fillTable(ArrayList<TAISAN> danhsachTaisan) {
		tree_PTTS.removeAll();
		int i = 1;
		for (TAISAN p : danhsachTaisan) {
			TreeItem ti = new TreeItem(tree_PTTS, SWT.NONE);
			ti.setText(new String[] { "" + i, p.getTEN_TAISAN(), p.getMODEL(),
					mdf.getViewStringDate(p.getNGAY_SU_DUNG()), p.getSERI(), String.valueOf(p.getMA_TAISAN()) });
			ti.setData(p);
			i++;
		}
	}

	protected void showMessage_Fail() {
		MessageBox m = new MessageBox(shell);
		m.setText("Thất bại");
		m.setMessage("Tạo công việc thất bại");
		m.open();
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(shell);
		m.setText("Hoàn tất");
		m.setMessage("Tạo công việc hoàn tất");
		m.open();
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(shell);
		m.setText("Lỗi");
		m.setMessage("Tên đợt tăng, Môt tả không để trống!");
		m.open();
	}

	protected boolean checkTextNotNULL() {
		if (text_Tendot_Suachua.getText().equals("")) {
			return false;
		}
		if (text_Mota.getText().equals("")) {
			return false;
		}
		return true;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
