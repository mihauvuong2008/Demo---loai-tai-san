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
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class _2_Taodot_Suachua extends Shell {
	private static NGUOIDUNG user;
	private final Controler controler;
	private static Shell ParentShell;
	private static DE_XUAT dx;
	private DOT_THUCHIEN_SUACHUA_BAODUONG VIEW_dsb;
	private static int MODE_NEW_VIEW;
	private Text text_Mota;
	private Text text_Tendot_Suachua;
	private Tree tree_PTTS;
	private Button btnTiep;
	private Button btnTroLai;
	private Button btnDong;
	private final int itemHeight = 21;
	private Text text_TenLienhe;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	protected NGUONSUACHUA_BAODUONG nsbd;
	private Text text_DonviDexuat;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(_2_Taodot_Suachua.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_2_Taodot_Suachua shell = new _2_Taodot_Suachua(display, user, ParentShell, dx, MODE_NEW_VIEW);
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
	 * @param dx
	 * @param shlChnNgiL
	 * @param user
	 * @param MODE_NEW_VIEW
	 * @throws SQLException
	 */
	public _2_Taodot_Suachua(Display display, NGUOIDUNG user, Shell ParentShell, DE_XUAT dx, int MODE_NEW_VIEW)
			throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(_2_Taodot_Suachua.class, "/maintenance-icon (1).png"));
		_2_Taodot_Suachua.user = user;
		controler = new Controler(user);
		_2_Taodot_Suachua.ParentShell = ParentShell;
		_2_Taodot_Suachua.dx = dx;
		_2_Taodot_Suachua.MODE_NEW_VIEW = MODE_NEW_VIEW;
		TreeRowStyle ts = new TreeRowStyle();
		setLayout(new GridLayout(8, false));

		@SuppressWarnings("unused")
		Fill_ItemData fi = new Fill_ItemData();

		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));

		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
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

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblTnNgunTng = new Label(composite_1, SWT.NONE);
		lblTnNgunTng.setText("Tên Liên hệ:");

		text_TenLienhe = new Text(composite_1, SWT.BORDER);
		text_TenLienhe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite_1, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite_1, SWT.BORDER);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(composite_1, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Liên hệ:");

		text_Lienhe = new Text(composite_1, SWT.BORDER);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_1.setWeights(new int[] { 1000, 618 });

		tree_PTTS = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
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

		MenuItem mntmXoa = new MenuItem(menu, SWT.NONE);
		mntmXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		mntmXoa.setText("Xóa");
		ts.setTreeItemHeight(tree_PTTS, itemHeight);
		sashForm.setWeights(new int[] { 618, 1000 });
		new Label(this, SWT.NONE);

		btnTiep = new Button(this, SWT.NONE);
		btnTiep.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (checkTextNotNULL()) {
						DOT_THUCHIEN_SUACHUA_BAODUONG dsb = new DOT_THUCHIEN_SUACHUA_BAODUONG();
						dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Suachua.getText());
						dsb.setSUACHUA_BAODUONG(2);
						dsb.setMO_TA(text_Mota.getText());

						int i;
						i = controler.getControl_DEXUAT().insert_DEXUAT(dx);

						if (i >= 0) {
							QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
							qdt.setMA_DE_XUAT(dx.getMA_DE_XUAT());
							qdt.setTEN_QUA_TRINH("Đề xuất - thực hiện Sửa chữa theo công văn: " + dx.getSODEXUAT());
							int i2 = controler.getControl_QUATRINH_DEXUAT_THUCHIEN()
									.insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
							if (i2 >= 0) {
								dsb.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								qdt.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
								int ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
										.InsertDOT_THUCHIEN_SUACHUA_BAODUONG(dsb, qdt, null);
								DOT_THUCHIEN_SUACHUA_BAODUONG dsb1 = controler
										.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
										.get_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
								insertDanhsachTaisan(dsb1);
								if (ict >= 0) {
									showMessage_Succes();
									ParentShell.dispose();
									dispose();
									GiaoViec gv = new GiaoViec(user);
									gv.open();
								} else {
									showMessage_Fail();
								}
							} else {
								showMessage_Fail();
							}
						} else {
							showMessage_Fail();
						}
					} else {
						showMessage_FillText();
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
					controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().set_DOT_THUCHIEN_SUACHUA_TAISAN(dsb1,
							ts);
				}
			}
		});
		GridData gd_btnTiep = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnTiep.widthHint = 75;
		btnTiep.setLayoutData(gd_btnTiep);
		btnTiep.setText("Tiếp >>");

		btnTroLai = new Button(this, SWT.NONE);
		btnTroLai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParentShell.setVisible(true);
				dispose();
			}
		});
		GridData gd_btnTroLai = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnTroLai.widthHint = 75;
		btnTroLai.setLayoutData(gd_btnTroLai);
		btnTroLai.setText("Trở lại <<");

		Button btnNgunSaCha = new Button(this, SWT.NONE);
		btnNgunSaCha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonSuachua_Baoduong cnsb = new ChonNguonSuachua_Baoduong(getShell(), SWT.DIALOG_TRIM, user);
					cnsb.open();
					nsbd = cnsb.getResult();
					fillData(nsbd);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void fillData(NGUONSUACHUA_BAODUONG nsbd) {
				text_TenLienhe.setText(nsbd.getTEN_NGUONSUACHUA_BAODUONG());
				text_Gioithieu.setText(nsbd.getGIOI_THIEU());
				text_Lienhe.setText(nsbd.getLIEN_HE());
			}
		});
		btnNgunSaCha.setImage(SWTResourceManager.getImage(_2_Taodot_Suachua.class, "/phone-icon.png"));
		btnNgunSaCha.setText("Nguồn Sửa chữa - Bảo dưỡng");

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.setImage(SWTResourceManager.getImage(_2_Taodot_Suachua.class, "/add-1-icon (1).png"));
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PHONGBAN dv = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
					_3_Nhapdanhsach nds = new _3_Nhapdanhsach(getShell(), SWT.DIALOG_TRIM, user, getTreeData(), dv);
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
		GridData gd_btnThm = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnThm.widthHint = 75;
		btnThm.setLayoutData(gd_btnThm);
		btnThm.setText("Thêm");

		Button btnXa = new Button(this, SWT.NONE);
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}

		});
		GridData gd_btnXa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXa.widthHint = 75;
		btnXa.setLayoutData(gd_btnXa);
		btnXa.setText("Xóa");
		btnXa.setImage(SWTResourceManager.getImage(_2_Taodot_Suachua.class, "/delete-1-icon (1).png"));

		Button btnXong = new Button(this, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (MODE_NEW_VIEW == 2) {
						doUpdate();
						GiaoViec.FillTableSuachua();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Lưu");
		btnXong.setImage(SWTResourceManager.getImage(_2_Taodot_Suachua.class, "/Actions-document-save-icon (1).png"));

		btnDong = new Button(this, SWT.NONE);
		btnDong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnDong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDong.widthHint = 75;
		btnDong.setLayoutData(gd_btnDong);
		btnDong.setText("Đóng");
		createContents();
		init_loadMODE();
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
			VIEW_dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Suachua.getText());
			VIEW_dsb.setSUACHUA_BAODUONG(2);
			VIEW_dsb.setMO_TA(text_Mota.getText());
			if (nsbd != null)
				VIEW_dsb.setMA_NGUONSUACHUA_BAODUONG(nsbd.getMA_NGUONSUACHUA_BAODUONG());
			boolean flg = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.update_DOT_THUCHIEN_SUACHUA_BAODUONG(VIEW_dsb);
			if (flg) {
				ArrayList<TAISAN> oldList = controler.getControl_TAISAN().get_TAISAN(VIEW_dsb);
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
					controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().remove(VIEW_dsb, t);
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
							.set_DOT_THUCHIEN_SUACHUA_TAISAN(VIEW_dsb, t);
				}
			}
	}

	private void init_loadMODE() throws SQLException {
		if (MODE_NEW_VIEW == 2/* xem */ && dx != null) {
			setupMODE_Layout(MODE_NEW_VIEW);
			VIEW_dsb = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().get_DOT_THUCHIEN_SUACHUA_BAODUONG(dx);
			if (VIEW_dsb != null) {
				text_Tendot_Suachua.setText(VIEW_dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
				PHONGBAN p = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
				text_DonviDexuat.setText(p.getTEN_PHONGBAN());
				text_Mota.setText(VIEW_dsb.getMO_TA());
				ArrayList<TAISAN> tsl = controler.getControl_TAISAN().get_TAISAN(VIEW_dsb);
				fillTable(tsl);
				NGUONSUACHUA_BAODUONG nsbd = controler.getControl_NGUONSUACHUA_BAODUONG()
						.get_NguonSuachua_Baoduong(VIEW_dsb);
				if (nsbd != null) {
					text_TenLienhe.setText(nsbd.getTEN_NGUONSUACHUA_BAODUONG());
					text_Gioithieu.setText(nsbd.getGIOI_THIEU());
					text_Lienhe.setText(nsbd.getLIEN_HE());
				}
			}
		}
	}

	private void setupMODE_Layout(int MODE_NEW_VIEW) {
		if (MODE_NEW_VIEW == 2) {
			btnTiep.setVisible(false);
			btnTroLai.setVisible(false);
			this.setText("Đợt thực hiện Sửa chữa phương tiện tài sản");
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
		MessageBox m = new MessageBox(getShell());
		m.setText("Thất bại");
		m.setMessage("Tạo công việc thất bại");
		m.open();
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Hoàn tất");
		m.setMessage("Tạo công việc hoàn tất");
		m.open();
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(getShell());
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

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tạo Đợt Sửa chữa Phương tiện Tài sản");
		setSize(825, 510);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
