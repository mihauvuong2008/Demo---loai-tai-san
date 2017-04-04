package View.AssetManagers.ThongBao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.THONGBAO;
import View.AssetManagers.MainForm;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ThongbaoChuadoc extends Dialog {
	protected Object result;
	protected Shell shlThongbaoChuadoc;
	private Tree tree;
	private static NGUOIDUNG user;
	private TreeItem Suachua_Baoduong;
	private TreeItem Muasam_Thanhly;
	private TreeItem DieuXe;
	private TreeItem NguoiDung;
	private TreeItem ThongbaoKhac;
	private TreeColumn trclmnNewColumn;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static Log log = LogFactory.getLog(ThongbaoChuadoc.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public ThongbaoChuadoc(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		ThongbaoChuadoc.user = user;
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
		shlThongbaoChuadoc.open();
		shlThongbaoChuadoc.layout();
		Display display = getParent().getDisplay();
		while (!shlThongbaoChuadoc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlThongbaoChuadoc = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlThongbaoChuadoc
				.setImage(SWTResourceManager.getImage(ThongbaoChuadoc.class, "/Places-mail-message-icon (1).png"));
		shlThongbaoChuadoc.setSize(330, 534);
		new FormTemplate().setRightScreen(shlThongbaoChuadoc);
		shlThongbaoChuadoc.setLayout(new GridLayout(1, false));
		shlThongbaoChuadoc.setText("Bạn có thông báo mới");

		tree = new Tree(shlThongbaoChuadoc, SWT.BORDER);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					showMessage();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		// Table_Style ts = new Table_Style();
		// ts.setTreeItemHeight(tree, 44);
		DieuXe = new TreeItem(tree, SWT.NONE);
		DieuXe.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		DieuXe.setImage(SWTResourceManager.getImage(MainForm.class, "/Household-Garage-icon.png"));
		DieuXe.setText("Lệnh Điều xe");
		Suachua_Baoduong = new TreeItem(tree, SWT.NONE);
		Suachua_Baoduong.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Suachua_Baoduong.setImage(SWTResourceManager.getImage(MainForm.class, "/maintenance-icon.png"));
		Suachua_Baoduong.setText("Sửa chữa - Bảo dưỡng");
		Muasam_Thanhly = new TreeItem(tree, SWT.NONE);
		Muasam_Thanhly.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Muasam_Thanhly.setImage(SWTResourceManager.getImage(MainForm.class, "/add-icon.png"));
		Muasam_Thanhly.setText("Mua sắm - Thanh lý");
		NguoiDung = new TreeItem(tree, SWT.NONE);
		NguoiDung.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		NguoiDung.setImage(SWTResourceManager.getImage(MainForm.class, "/Office-Customer-Male-Light-icon (1).png"));
		NguoiDung.setText("Tài khoản người dùng");
		ThongbaoKhac = new TreeItem(tree, SWT.NONE);
		ThongbaoKhac.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		ThongbaoKhac.setImage(SWTResourceManager.getImage(MainForm.class, "/table-relationship-icon.png"));
		ThongbaoKhac.setText("Thông báo khác");

		trclmnNewColumn = new TreeColumn(tree, SWT.LEFT);
		trclmnNewColumn.setWidth(301);
		trclmnNewColumn.setText("DANH SÁCH THÔNG BÁO");

		Menu menu = new Menu(tree);
		tree.setMenu(menu);

		MenuItem mntmc = new MenuItem(menu, SWT.NONE);
		mntmc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					showMessage();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmc.setText("Đọc Thông báo");
		init();
	}

	protected void showMessage() throws SQLException {
		TreeItem[] til = tree.getSelection();
		if (til.length > 0) {
			if (til[0].getData() instanceof THONGBAO) {
				THONGBAO tb = (THONGBAO) til[0].getData();
				ViewTHONGBAO ctb = new ViewTHONGBAO(shlThongbaoChuadoc, SWT.DIALOG_TRIM, tb, user);
				ctb.open();
				init();
			}

		}
	}

	private void init() throws SQLException {
		Fill_LenhDieuXe();
		Fill_SuaChua_BaoDuong();
		Fill_MuaSam_ThanhLy();
		Fill_NguoiDung();
		Fill_ThongBaokhac();
		// treePack(tree);
		treeExpand(tree);
	}

	@SuppressWarnings("unused")
	private void treePack(Tree tree2) {
		for (TreeColumn c : tree2.getColumns()) {
			c.pack();
		}
	}

	private void treeExpand(Tree tree2) {
		for (TreeItem c : tree2.getItems()) {
			c.setExpanded(true);
		}
	}

	private void Fill_ThongBaokhac() throws SQLException {
		ThongbaoKhac.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_UNREAD_THONGBAO_ThongBaokhac();
		for (THONGBAO tb : tbl) {
			TreeItem t = new TreeItem(ThongbaoKhac, SWT.NONE);
			t.setText(new String[] { "[" + tb.getNGAY_THONGBAO() == null ? "-"
					: "[" + mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()) + "] "
							+ tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > 25 ? 25
									: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setImage(SWTResourceManager.getImage(MainForm.class, "/messages-icon (1).png"));
			t.setData(tb);
		}
	}

	private void Fill_NguoiDung() throws SQLException {
		NguoiDung.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_UNREAD_THONGBAO_NguoiDung();
		for (THONGBAO tb : tbl) {
			TreeItem t = new TreeItem(NguoiDung, SWT.NONE);
			t.setText(new String[] { "[" + tb.getNGAY_THONGBAO() == null ? "-"
					: mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()) + "] "
							+ tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > 25 ? 25
									: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setImage(SWTResourceManager.getImage(MainForm.class, "/messages-icon (1).png"));
			t.setData(tb);
		}
	}

	private void Fill_MuaSam_ThanhLy() throws SQLException {
		Muasam_Thanhly.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_UNREAD_THONGBAO_MuaSam_ThanhLy();
		for (THONGBAO tb : tbl) {
			TreeItem t = new TreeItem(Muasam_Thanhly, SWT.NONE);
			t.setText(new String[] { tb.getNGAY_THONGBAO() == null ? "-"
					: "[" + mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()) + "] "
							+ tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > 25 ? 25
									: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setImage(SWTResourceManager.getImage(MainForm.class, "/messages-icon (1).png"));
			t.setData(tb);
		}
	}

	private void Fill_SuaChua_BaoDuong() throws SQLException {
		Suachua_Baoduong.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_UNREAD_THONGBAO_SuaChua_BaoDuong();
		for (THONGBAO tb : tbl) {
			TreeItem t = new TreeItem(Suachua_Baoduong, SWT.NONE);
			t.setText(new String[] { tb.getNGAY_THONGBAO() == null ? "-"
					: "[" + mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()) + "] "
							+ tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > 25 ? 25
									: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setImage(SWTResourceManager.getImage(MainForm.class, "/messages-icon (1).png"));
			t.setData(tb);
		}
	}

	private void Fill_LenhDieuXe() throws SQLException {
		DieuXe.removeAll();
		ArrayList<THONGBAO> tbl = controler.getControl_THONGBAO().get_UNREAD_THONGBAO_LenhDieuXe();
		for (THONGBAO tb : tbl) {
			TreeItem t = new TreeItem(DieuXe, SWT.NONE);
			t.setText(tb.getNGAY_THONGBAO() == null ? "-"
					: "[" + mdf.getViewStringDateTime_hma_dmy(tb.getNGAY_THONGBAO()) + "] "
							+ new String[] {
									tb.getNOIDUNG_THONGBAO().substring(0, tb.getNOIDUNG_THONGBAO().length() > 25 ? 25
											: tb.getNOIDUNG_THONGBAO().length() - 1) });
			t.setImage(SWTResourceManager.getImage(MainForm.class, "/messages-icon (1).png"));
			t.setData(tb);
		}
	}

}
