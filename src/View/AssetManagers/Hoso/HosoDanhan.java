package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;

import Controler.Controler;
import DAO.HOSO_USER;
import DAO.NGUOIDUNG;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class HosoDanhan extends Dialog {

	protected Object result;
	protected Shell shlHS;
	private ArrayList<HOSO_USER> hsrl;
	MyDateFormat mdf = new MyDateFormat();
	private Tree tree_TuHoso;
	private NGUOIDUNG user;
	private Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 * @param hsrl
	 */
	public HosoDanhan(Shell parent, int style, NGUOIDUNG user, ArrayList<HOSO_USER> hsrl) {
		super(parent, style);
		setText("SWT Dialog");
		this.hsrl = hsrl;
		this.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlHS.open();
		shlHS.layout();
		Display display = getParent().getDisplay();
		while (!shlHS.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlHS = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlHS.setImage(user.getIcondata().receiveIcon16);
		shlHS.setSize(615, 381);
		shlHS.setText("Hồ sơ đã nhận");
		new FormTemplate().setCenterScreen(shlHS);
		shlHS.setLayout(new GridLayout(2, false));

		tree_TuHoso = new Tree(shlHS, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_TuHoso.setLinesVisible(true);
		tree_TuHoso.setHeaderVisible(true);
		tree_TuHoso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		TreeColumn treeColumn = new TreeColumn(tree_TuHoso, SWT.NONE);
		treeColumn.setWidth(50);
		treeColumn.setText("Stt");

		TreeColumn treeColumn_1 = new TreeColumn(tree_TuHoso, SWT.NONE);
		treeColumn_1.setWidth(140);
		treeColumn_1.setText("Tên Hồ sơ");

		TreeColumn trclmnNgyNhn = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnNgyNhn.setWidth(100);
		trclmnNgyNhn.setText("Ngày nhận");

		TreeColumn treeColumn_3 = new TreeColumn(tree_TuHoso, SWT.NONE);
		treeColumn_3.setWidth(100);
		treeColumn_3.setText("Ngày tạo");

		TreeColumn treeColumn_4 = new TreeColumn(tree_TuHoso, SWT.NONE);
		treeColumn_4.setWidth(160);
		treeColumn_4.setText("Mô tả");

		Menu menu = new Menu(tree_TuHoso);
		tree_TuHoso.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_TuHoso.getSelection();
				if (til.length <= 0)
					return;
				HOSO_USER hsr = (HOSO_USER) til[0].getData();
				Taphoso_View thsv = new Taphoso_View(shlHS, SWT.DIALOG_TRIM, user, hsr.getTaphoso(), true);
				try {
					thsv.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("Xem");

		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_TuHoso.getSelection();
				if (til.length <= 0)
					return;
				for (TreeItem treeItem : til) {
					HOSO_USER hsr = (HOSO_USER) treeItem.getData();
					try {
						controler.getControl_HOSO_DANHAN().remove_HOSO_DANHAN(hsr.getHoso_Danhan());
						treeItem.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		menuItem_1.setText("Xóa nhãn");

		Button btnXong = new Button(shlHS, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlHS.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlHS, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlHS.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() {
		if (hsrl == null)
			return;
		fillTree(hsrl);
	}

	private void fillTree(ArrayList<HOSO_USER> hsrl) {
		int i = 1;
		if (hsrl != null)
			for (HOSO_USER hsr : hsrl) {
				String Ngaynhan = hsr.getHoso_Danhan().getNGAY_NHAN() == null ? "-"
						: mdf.getViewStringDate(hsr.getHoso_Danhan().getNGAY_NHAN());

				String date = hsr.getTaphoso().getNGAY_TAO_TAPHOSO() == null ? "-"
						: mdf.getViewStringDate(hsr.getTaphoso().getNGAY_TAO_TAPHOSO());
				TreeItem ti = new TreeItem(tree_TuHoso, SWT.NONE);
				ti.setText(new String[] { "" + i, hsr.getTaphoso().getTEN_TAPHOSO(), Ngaynhan, date,
						hsr.getTaphoso().getGIOITHIEU_TAPHOSO(),
						hsr.getTEN_TAI_KHOAN() == null ? "--" : hsr.getTEN_TAI_KHOAN() });
				ti.setData(hsr);
				i++;
				ti.setExpanded(false);
			}
	}

}
