package View.AssetManagers.CongViec.Giamtaisan;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
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
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import DAO.PHONGBAN;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import View.AssetManagers.AppMessage.DefaultBoxMessage;
import View.AssetManagers.CongViec.Baoduong.NhapDeXuat;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.Hoso.TapHoso_View;
import View.AssetManagers.NguonGiam.ChonNguonGiam;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class TaoDotGiam extends Dialog {
	private Text text_Tendotgiam;
	private Text text_Mota;
	private Text txtDangThuc;
	private static NGUOIDUNG user;
	private final Controler controler;
	private NGUONGIAM ng;
	private Combo combo_Lydogiam;
	private DOT_THUCHIEN_GIAM_TAISAN dgt;
	private Tree tree;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(TaoDotGiam.class);
	private Text text_Sodexuat;
	private Text text_NgaythangVanban;
	private Text text_Donvibanhanh;
	private Text text_Ngaytiennhan;
	private Text text_Ngaychuyengiao;
	private Text text_Trichyeu;
	private Shell shell;
	Fill_ItemData f = new Fill_ItemData();
	private Object result;
	protected DE_XUAT dexuat;
	private ArrayList<TAISAN> data;
	private Button button;
	private Text text_Tenlienhe;
	private Text text_Gioithieu;
	private Text text_Lienhe;

	/**
	 * @wbp.parser.constructor
	 */
	public TaoDotGiam(Shell parent, int Style, NGUOIDUNG user2, ArrayList<TAISAN> data) {
		super(parent, Style);
		setText("SWT Dialog");
		TaoDotGiam.user = user2;
		controler = new Controler(user);
		this.data = data;
	}

	public TaoDotGiam(Shell parent, int Style, NGUOIDUNG user2, DOT_THUCHIEN_GIAM_TAISAN dgt) {
		super(parent, Style);
		setText("SWT Dialog");
		TaoDotGiam.user = user2;
		controler = new Controler(user);
		this.dgt = dgt;
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
		shell.setImage(SWTResourceManager.getImage(TaoDotGiam.class, "/database_remove.png"));
		shell.setLayout(new GridLayout(4, false));
		shell.setText("Tạo đợt giảm tài sản");
		shell.setSize(777, 480);
		new FormTemplate().setCenterScreen(shell);

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite grpTotGim = new Composite(sashForm_1, SWT.NONE);
		grpTotGim.setLayout(new GridLayout(2, false));
		GridData gd_grpTotGim = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTotGim.heightHint = 182;
		grpTotGim.setLayoutData(gd_grpTotGim);

		Label lblTntGim = new Label(grpTotGim, SWT.NONE);
		GridData gd_lblTntGim = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTntGim.widthHint = 100;
		lblTntGim.setLayoutData(gd_lblTntGim);
		lblTntGim.setText("Tên đợt giảm*:");

		text_Tendotgiam = new Text(grpTotGim, SWT.BORDER);
		text_Tendotgiam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLDoGim = new Label(grpTotGim, SWT.NONE);
		lblLDoGim.setText("Lý do giảm*:");

		combo_Lydogiam = new Combo(grpTotGim, SWT.READ_ONLY);
		combo_Lydogiam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		f.setComboBox_LYDOGIAM(combo_Lydogiam);

		Label lblKinhPhThu = new Label(grpTotGim, SWT.NONE);
		lblKinhPhThu.setText("Kinh phí thu hồi:");

		txtDangThuc = new Text(grpTotGim, SWT.BORDER | SWT.READ_ONLY | SWT.RIGHT);
		txtDangThuc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(grpTotGim, SWT.NONE);
		GridData gd_label_4 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_4.verticalIndent = 3;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("Mô tả:");

		text_Mota = new Text(grpTotGim, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(grpTotGim, SWT.NONE);

		Button btnnVNgoi = new Button(grpTotGim, SWT.NONE);
		GridData gd_btnnVNgoi = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnnVNgoi.widthHint = 85;
		btnnVNgoi.setLayoutData(gd_btnnVNgoi);
		btnnVNgoi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonGiam cng = new ChonNguonGiam(shell, SWT.DIALOG_TRIM, user);
					cng.open();
					ng = cng.getResult();
					if (dgt == null)
						return;
					if (ng == null) {
						MessageBox m = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CLOSE);
						m.setText("Xóa dữ liệu cũ?");
						m.setMessage("Bạn muốn xóa dữ liệu cũ?");
						int rc = m.open();
						switch (rc) {
						case SWT.CANCEL:
							break;
						case SWT.YES:
							dgt.setMA_NGUONGIAM(-1);
							break;
						case SWT.NO:
							break;
						}
					} else {
						dgt.setMA_NGUONGIAM(ng.getMA_NGUONGIAM());
					}
					controler.getControl_DOT_THUCHIEN_GIAM_TAISAN().update_DOT_GIAM_TAISAN(dgt);
					fillNguonGiam(dgt);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		btnnVNgoi.setImage(SWTResourceManager.getImage(TaoDotGiam.class, "/phone-icon.png"));
		btnnVNgoi.setText("Liên hệ");
		tree = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree.getSelection();
				if (items.length > 0) {
				}
			}
		});
		TreeColumn trclmnStt = new TreeColumn(tree, SWT.NONE);
		trclmnStt.setWidth(45);
		trclmnStt.setText("STT");

		TreeColumn trclmnTnTiSn = new TreeColumn(tree, SWT.NONE);
		trclmnTnTiSn.setWidth(150);
		trclmnTnTiSn.setText("TÊN TÀI SẢN");

		TreeColumn trclmnNgySDng = new TreeColumn(tree, SWT.NONE);
		trclmnNgySDng.setWidth(100);
		trclmnNgySDng.setText("NGÀY SỬ DỤNG");

		TreeColumn trclmnModel = new TreeColumn(tree, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("MODEL");

		TreeColumn trclmnSeri = new TreeColumn(tree, SWT.NONE);
		trclmnSeri.setWidth(100);
		trclmnSeri.setText("SERI");

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmThm = new MenuItem(menu, SWT.NONE);
		mntmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					NhapDanhsachGiam ndsg = new NhapDanhsachGiam(shell, SWT.DIALOG_TRIM, getTreeData(), user);
					ndsg.open();
					ArrayList<TAISAN> data = new ArrayList<>();
					ArrayList<TAISAN> tmpData = getTreeData();
					if (tmpData != null)
						data.addAll(tmpData);
					if (ndsg.result != null)
						data.addAll(ndsg.result);
					fillTable(data);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThm.setText("Thêm");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		mntmXa.setText("Xóa");
		sashForm_1.setWeights(new int[] { 183, 214 });

		ExpandBar expandBar = new ExpandBar(sashForm, SWT.NONE);
		expandBar.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));

		ExpandItem xpndtmXut = new ExpandItem(expandBar, SWT.NONE);
		xpndtmXut.setExpanded(true);
		xpndtmXut.setText("Đề xuất");

		Composite composite = new Composite(expandBar, SWT.NONE);
		xpndtmXut.setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Số đề xuất: ");

		text_Sodexuat = new Text(composite, SWT.NONE);
		text_Sodexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Sodexuat.setEditable(false);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Ngày tháng: ");

		text_NgaythangVanban = new Text(composite, SWT.NONE);
		text_NgaythangVanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_NgaythangVanban.setEditable(false);
		text_NgaythangVanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("Đơn vị: ");

		text_Donvibanhanh = new Text(composite, SWT.NONE);
		text_Donvibanhanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Donvibanhanh.setEditable(false);
		text_Donvibanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("Ngày xử lý:");

		text_Ngaytiennhan = new Text(composite, SWT.NONE);
		text_Ngaytiennhan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaytiennhan.setEditable(false);
		text_Ngaytiennhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("Ngày giao:");

		text_Ngaychuyengiao = new Text(composite, SWT.NONE);
		text_Ngaychuyengiao.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ngaychuyengiao.setEditable(false);
		text_Ngaychuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(composite, SWT.NONE);
		GridData gd_label_6 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_6.verticalIndent = 3;
		label_6.setLayoutData(gd_label_6);
		label_6.setText("Ghi chú: ");

		text_Trichyeu = new Text(composite, SWT.NONE);
		text_Trichyeu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Trichyeu.setEditable(false);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(composite, SWT.NONE);

		button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dgt != null && dgt.getMA_DOT_GIAM() > 0) {
						dexuat = controler.getControl_DEXUAT().get_DEXUAT(dgt);
					}
					if (dexuat != null) {
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dexuat.getMA_TAPHOSO());
						TapHoso_View thsv = new TapHoso_View(shell, SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					} else {
						NhapDeXuat ndx = new NhapDeXuat(shell, SWT.DIALOG_TRIM, user);
						ndx.open();
						dexuat = ndx.result;
						if (dexuat == null)
							return;
						if (dgt == null)
							return;
						if (dgt.getMA_DOT_GIAM() <= 0)
							return;
						int Ma_Quatrinh_Dexuat_thuchien = getMaQuatrinhDexuatThuchien(dexuat);
						if (Ma_Quatrinh_Dexuat_thuchien <= 0)
							return;
						boolean ict = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
								.update_DOT_GIAM_TAISAN_Update_QUATRINH_DEXUAT_THUCHIEN(dgt,
										Ma_Quatrinh_Dexuat_thuchien);
						if (ict) {
							MessageBox m = new MessageBox(shell, SWT.ICON_WORKING);
							m.setText("Hoàn tất");
							m.setMessage("Thêm Đề xuất Hoàn tất");
							m.open();
							fillDexuat(dexuat);
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
		button.setImage(SWTResourceManager.getImage(TaoDotGiam.class, "/Mimes-ooo-writer-icon.png"));
		xpndtmXut.setHeight(200);

		ExpandItem xpndtmLinH = new ExpandItem(expandBar, SWT.NONE);
		xpndtmLinH.setText("Liên hệ");

		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		xpndtmLinH.setControl(composite_1);
		composite_1.setLayout(new GridLayout(3, false));

		Label lblTnLinH = new Label(composite_1, SWT.NONE);
		lblTnLinH.setText("Tên liên hệ:");

		text_Tenlienhe = new Text(composite_1, SWT.BORDER);
		text_Tenlienhe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblGiiThiu = new Label(composite_1, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite_1, SWT.BORDER);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Label lblLinH = new Label(composite_1, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Liên hệ:");

		text_Lienhe = new Text(composite_1, SWT.BORDER);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		xpndtmLinH.setHeight(150);
		sashForm.setWeights(new int[] { 1000, 618 });
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnBQua = new Button(shell, SWT.NONE);
		btnBQua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dgt != null) {
						doUpdate();
						shell.dispose();
					} else {
						dgt = getDotGiamtaisan();
						int key = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN().InsertDOT_THUCHIEN_GIAM_TAISAN(dgt,
								null);
						if (key <= 0)
							return;
						dgt.setMA_DOT_GIAM(key);
						int Ma_Quatrinh_Dexuat_thuchien = getMaQuatrinhDexuatThuchien(dexuat);
						if (Ma_Quatrinh_Dexuat_thuchien > 0)
							controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
									.update_DOT_GIAM_TAISAN_Update_QUATRINH_DEXUAT_THUCHIEN(dgt,
											Ma_Quatrinh_Dexuat_thuchien);
						doUpdate();
						showMessage_Succes();
						shell.dispose();
						GiaoViec gv = new GiaoViec(user);
						gv.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnBQua.setImage(SWTResourceManager.getImage(TaoDotGiam.class, "/Actions-document-save-icon (1).png"));
		GridData gd_btnBQua = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnBQua.widthHint = 75;
		btnBQua.setLayoutData(gd_btnBQua);
		btnBQua.setText("Lưu");

		Button btnng = new Button(shell, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void fillNguonGiam(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		text_Tenlienhe.setText("");
		text_Gioithieu.setText("");
		text_Lienhe.setText("");
		NGUONGIAM ng = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
		if (ng == null)
			return;
		text_Tenlienhe.setText(ng.getTEN_NGUONGIAM());
		text_Gioithieu.setText(ng.getGIOI_THIEU());
		text_Lienhe.setText(ng.getLIEN_HE());
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(shell);
		m.setText("Hoàn tất");
		m.setMessage("Tạo công việc hoàn tất");
		m.open();
	}

	protected int getMaQuatrinhDexuatThuchien(DE_XUAT Insert_dx) throws NullPointerException, SQLException {
		int key = controler.getControl_DEXUAT().insert_DEXUAT(Insert_dx);
		if (key <= 0)
			return -1;
		Insert_dx.setMA_DE_XUAT(key);
		QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
		qdt.setMA_DE_XUAT(key);
		qdt.setLOAI_CONGVIEC(f.getInt_LoaiCongviec_Muasam());
		return controler.getControl_QUATRINH_DEXUAT_THUCHIEN().insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
	}

	protected DOT_THUCHIEN_GIAM_TAISAN getDotGiamtaisan() {
		dgt = new DOT_THUCHIEN_GIAM_TAISAN();
		dgt.setTEN_DOT_GIAM(text_Tendotgiam.getText());
		dgt.setLY_DO_GIAM((int) combo_Lydogiam.getData(combo_Lydogiam.getText()));
		dgt.setMO_TA(text_Mota.getText());
		return dgt;
	}

	protected void delete() {
		TreeItem[] til = tree.getSelection();
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

	private void init() throws SQLException {
		if (data != null)
			fillTable(data);
		if (dgt == null)
			return;
		text_Tendotgiam.setText(dgt.getTEN_DOT_GIAM());
		f.setComboBox_LYDOGIAM(combo_Lydogiam);
		combo_Lydogiam.setText(f.getStringOfLYDOGIAM(dgt.getLY_DO_GIAM()));
		txtDangThuc.setText("dang thuc hien");
		text_Mota.setText(dgt.getMO_TA());
		fillTable(controler.getControl_TAISAN().get_TAISAN(dgt));
		DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
		fillDexuat(dx);
		fillNguonGiam(dgt);
	}

	private void fillDexuat(DE_XUAT dx) throws SQLException {
		if (dx == null)
			return;
		button.setText("Xem Hồ sơ");
		text_Sodexuat.setText(dx.getSODEXUAT());
		text_NgaythangVanban.setText(mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
		PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
		text_Donvibanhanh.setText(pb.getTEN_PHONGBAN());
		text_Ngaytiennhan.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
		text_Ngaychuyengiao.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
		text_Trichyeu.setText(dx.getGHI_CHU());
	}

	protected void doUpdate() throws SQLException {
		if (!checkTextNotNULL())
			return;
		if (dgt == null)
			return;
		dgt.setTEN_DOT_GIAM(text_Tendotgiam.getText());
		dgt.setMO_TA(text_Mota.getText());
		dgt.setLY_DO_GIAM((int) combo_Lydogiam.getData(combo_Lydogiam.getText()));
		if (ng != null)
			dgt.setMA_NGUONGIAM(ng.getMA_NGUONGIAM());
		boolean flg = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN().update_DOT_GIAM_TAISAN(dgt);
		if (!flg)
			return;
		ArrayList<TAISAN> oldList = new ArrayList<>();
		ArrayList<TAISAN> tmp = controler.getControl_TAISAN().get_TAISAN(dgt);
		if (tmp != null)
			oldList.addAll(tmp);
		ArrayList<TAISAN> currentList = getTreeData();
		doInsert(oldList, currentList);
		doDelete(oldList, currentList);
	}

	private void doDelete(ArrayList<TAISAN> oldList, ArrayList<TAISAN> currentList) throws SQLException {
		if (oldList != null && currentList != null)
			for (TAISAN t_ : oldList) {
				boolean deleteFlag = true;
				for (TAISAN t : currentList) {
					if (t.getMA_TAISAN() == t_.getMA_TAISAN()) {
						deleteFlag = false;
					}
				}
				if (deleteFlag) {
					controler.getControl_TAISAN_DOT_THUCHIEN_GIAM_TAISAN().delete_TAISAN_DOT_GIAM_TAISAN(t_, dgt);
				}
			}
	}

	private void doInsert(ArrayList<TAISAN> oldList, ArrayList<TAISAN> currentList) throws SQLException {
		if (oldList != null && currentList != null)
			for (TAISAN t_ : currentList) {
				boolean insertFlag = true;
				for (TAISAN t : oldList) {
					if (t.getMA_TAISAN() == t_.getMA_TAISAN()) {
						insertFlag = false;
					}
				}
				if (insertFlag) {
					controler.getControl_TAISAN_DOT_THUCHIEN_GIAM_TAISAN().set_DOTGIAMTAISAN_TAISAN(dgt, t_);
				}
			}
	}

	private boolean checkTextNotNULL() {
		if (text_Tendotgiam.getText().equals("")) {
			DefaultBoxMessage dbm = new DefaultBoxMessage();
			dbm.Notification("Lỗi!", "Không để trống tên đợt Thanh lý, chuyển giao");
			return false;
		}
		return true;
	}

	protected void fillTable(ArrayList<TAISAN> treeData) {
		tree.removeAll();
		int x = 1;
		if (treeData != null)
			for (TAISAN n : treeData) {
				TreeItem Item = new TreeItem(tree, SWT.NONE);
				Item.setText(new String[] { x + "", n.getTEN_TAISAN(), mdf.getViewStringDate(n.getNGAY_SU_DUNG()),
						n.getMODEL(), n.getSERI() });
				Item.setData(n);
				x++;
			}
		if (tree != null)
			for (TreeColumn t : tree.getColumns()) {
				t.pack();
			}
	}

	protected ArrayList<TAISAN> getTreeData() {
		ArrayList<TAISAN> result = new ArrayList<>();
		TreeItem[] til = tree.getItems();
		if (til.length > 0) {
			for (TreeItem ti : til) {
				result.add((TAISAN) ti.getData());
			}
		}
		return result;
	}

	/**
	 * Create contents of the shell.
	 */

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
