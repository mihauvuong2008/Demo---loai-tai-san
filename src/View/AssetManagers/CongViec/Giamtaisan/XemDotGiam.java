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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import DAO.TAISAN;
import View.AssetManagers.AppMessage.DefaultBoxMessage;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.NguonGiam.ChonNguonGiam;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class XemDotGiam extends Shell {
	private Text text_Tendotgiam;
	private Text text_Mota;
	private Text text_Kinhphithuhoi;
	private static NGUOIDUNG user;
	private final Controler controler;
	private NGUONGIAM ng;
	private Combo combo_Lydogiam;
	private Text label_Tennguongiam;
	private Text label_Gioithieu;
	private Text label_Lienhe;
	private static DOT_THUCHIEN_GIAM_TAISAN dgt;
	private Tree tree;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(XemDotGiam.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			XemDotGiam shell = new XemDotGiam(display, user, dgt);
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
	public XemDotGiam(Display display, NGUOIDUNG user, DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(XemDotGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(5, false));
		XemDotGiam.user = user;
		XemDotGiam.dgt = dgt;
		controler = new Controler(user);
		Fill_ItemData f = new Fill_ItemData();

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		tree = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
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

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		mntmXa.setText("Xóa");
		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Composite composite = new Composite(sashForm_1, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Group grpTotGim = new Group(composite, SWT.NONE);
		grpTotGim.setLayout(new GridLayout(2, false));
		GridData gd_grpTotGim = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpTotGim.heightHint = 182;
		grpTotGim.setLayoutData(gd_grpTotGim);
		grpTotGim.setText("Th\u00F4ng tin c\u00F4ng vi\u1EC7c");

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

		text_Kinhphithuhoi = new Text(grpTotGim, SWT.BORDER | SWT.RIGHT);
		text_Kinhphithuhoi.setText("0");
		text_Kinhphithuhoi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(grpTotGim, SWT.NONE);
		label_4.setText("Mô tả:");

		text_Mota = new Text(grpTotGim, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));

		Group grpChnNgunGim = new Group(composite_1, SWT.NONE);
		grpChnNgunGim.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpChnNgunGim.setText("Ch\u1ECDn ngu\u1ED3n gi\u1EA3m");
		grpChnNgunGim.setLayout(new GridLayout(2, false));

		Label lblTnNgunGim = new Label(grpChnNgunGim, SWT.NONE);
		GridData gd_lblTnNgunGim = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblTnNgunGim.widthHint = 100;
		lblTnNgunGim.setLayoutData(gd_lblTnNgunGim);
		lblTnNgunGim.setText("Tên Nguồn giảm*: ");

		label_Tennguongiam = new Text(grpChnNgunGim, SWT.BORDER);
		label_Tennguongiam.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Tennguongiam.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_8 = new Label(grpChnNgunGim, SWT.NONE);
		GridData gd_label_8 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_8.verticalIndent = 3;
		label_8.setLayoutData(gd_label_8);
		label_8.setText("Liên hệ*:");

		label_Lienhe = new Text(grpChnNgunGim, SWT.BORDER);
		label_Lienhe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_9 = new Label(grpChnNgunGim, SWT.NONE);
		GridData gd_label_9 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_9.verticalIndent = 3;
		label_9.setLayoutData(gd_label_9);
		label_9.setText("Giới thiệu:");

		label_Gioithieu = new Text(grpChnNgunGim, SWT.BORDER | SWT.WRAP);
		label_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_1.setWeights(new int[] { 280, 188 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnnVNgoi = new Button(this, SWT.NONE);
		btnnVNgoi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonGiam cng = new ChonNguonGiam(getShell(), SWT.DIALOG_TRIM, user);
					cng.open();
					ng = cng.getResult();
					fillText(ng);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnnVNgoi
				.setImage(SWTResourceManager.getImage(XemDotGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnnVNgoi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnnVNgoi.setText("Đơn vị ngoài tham gia");

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					NhapDanhsachGiam ndsg = new NhapDanhsachGiam(getShell(), SWT.DIALOG_TRIM, getTreeData(), user);
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
		btnThm.setImage(SWTResourceManager.getImage(XemDotGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_btnThm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
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
		btnXa.setImage(SWTResourceManager.getImage(XemDotGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_btnXa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXa.widthHint = 75;
		btnXa.setLayoutData(gd_btnXa);
		btnXa.setText("Xóa");

		Button btnBQua = new Button(this, SWT.NONE);
		btnBQua.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					doUpdate();
					GiaoViec.FillTableThanhly();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnBQua.setImage(SWTResourceManager.getImage(XemDotGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_btnBQua = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnBQua.widthHint = 75;
		btnBQua.setLayoutData(gd_btnBQua);
		btnBQua.setText("Lưu");

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		createContents();
		init();
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
		text_Tendotgiam.setText(dgt.getTEN_DOT_GIAM());
		Fill_ItemData fi = new Fill_ItemData();
		fi.setComboBox_LYDOGIAM(combo_Lydogiam);
		combo_Lydogiam.setText(fi.getStringOfLYDOGIAM(dgt.getLY_DO_GIAM()));
		text_Kinhphithuhoi.setText("0");
		text_Mota.setText(dgt.getMO_TA());
		fillTable(controler.getControl_TAISAN().get_TAISAN(dgt));
		ng = controler.getControl_NGUONGIAM().get_NguonGiam(dgt);
		fillText(ng);
	}

	private void fillText(NGUONGIAM ng2) {
		label_Tennguongiam.setText(ng.getTEN_NGUONGIAM());
		label_Gioithieu.setText(ng.getGIOI_THIEU());
		label_Lienhe.setText(ng.getLIEN_HE());
	}

	protected void doUpdate() throws SQLException {
		if (checkTextNotNULL()) {
			dgt.setTEN_DOT_GIAM(text_Tendotgiam.getText());
			dgt.setMO_TA(text_Mota.getText());
			dgt.setLY_DO_GIAM((int) combo_Lydogiam.getData(combo_Lydogiam.getText()));
			if (ng != null)
				dgt.setMA_NGUONGIAM(ng.getMA_NGUONGIAM());
			boolean flg = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN().update_DOT_GIAM_TAISAN(dgt);
			System.out.println(flg);
			if (flg) {
				ArrayList<TAISAN> oldList = new ArrayList<>();
				ArrayList<TAISAN> tmp = controler.getControl_TAISAN().get_TAISAN(dgt);
				if (tmp != null)
					oldList.addAll(tmp);
				ArrayList<TAISAN> currentList = getTreeData();
				doInsert(oldList, currentList);
				doDelete(oldList, currentList);
			}
		} else {
		}
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
	protected void createContents() {
		setText("Tạo đợt giảm tài sản");
		setSize(825, 510);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
