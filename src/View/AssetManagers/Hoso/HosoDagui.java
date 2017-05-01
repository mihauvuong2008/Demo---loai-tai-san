package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;

import DAO.HOSO_USER;
import DAO.NGUOIDUNG;
import DAO.NGUOINHAN_HOSO_DAGUI;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import Controler.Controler;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.List;

public class HosoDagui extends Dialog {

	protected Object result;
	protected Shell shlHS;
	private Controler controler;
	private NGUOIDUNG user;
	private ArrayList<HOSO_USER> hsrl;
	private Tree tree_TuHoso;
	MyDateFormat mdf = new MyDateFormat();
	private List list;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param hsrl
	 */
	public HosoDagui(Shell parent, int style, NGUOIDUNG user, ArrayList<HOSO_USER> hsrl) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		controler = new Controler(user);
		this.hsrl = hsrl;
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
		shlHS.setImage(user.getIcondata().mailSent16);
		shlHS.setSize(597, 369);
		shlHS.setText("Hồ sơ đã gửi");
		shlHS.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlHS);

		SashForm sashForm = new SashForm(shlHS, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tree_TuHoso = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_TuHoso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_TuHoso.getSelection();
				if (til.length <= 0)
					return;
				HOSO_USER hsr = (HOSO_USER) til[0].getData();
				try {
					list.removeAll();
					ArrayList<NGUOINHAN_HOSO_DAGUI> nhgl = controler.getControl_HOSO_DAGUI()
							.getNguoiNhan(hsr.getHoso_Dagui());
					for (NGUOINHAN_HOSO_DAGUI nguoinhan_HOSO_DAGUI : nhgl) {
						list.add(nguoinhan_HOSO_DAGUI.getTEN_TAI_KHOAN());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tree_TuHoso.setHeaderVisible(true);
		tree_TuHoso.setLinesVisible(true);

		TreeColumn trclmnStt = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("Stt");

		TreeColumn trclmnTnTpH = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnTnTpH.setWidth(140);
		trclmnTnTpH.setText("T\u00EAn H\u1ED3 s\u01A1");

		TreeColumn trclmnSLngVn = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnSLngVn.setWidth(100);
		trclmnSLngVn.setText("Ngày gửi");

		TreeColumn trclmnNgyTo = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnNgyTo.setWidth(100);
		trclmnNgyTo.setText("Ng\u00E0y t\u1EA1o");

		TreeColumn trclmnMT = new TreeColumn(tree_TuHoso, SWT.NONE);
		trclmnMT.setWidth(160);
		trclmnMT.setText("M\u00F4 t\u1EA3");

		Menu menu = new Menu(tree_TuHoso);
		tree_TuHoso.setMenu(menu);

		MenuItem mntmXem = new MenuItem(menu, SWT.NONE);
		mntmXem.addSelectionListener(new SelectionAdapter() {
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
		mntmXem.setText("Xem");

		MenuItem mntmXaNhn = new MenuItem(menu, SWT.NONE);
		mntmXaNhn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_TuHoso.getSelection();
				if (til.length <= 0)
					return;
				for (TreeItem treeItem : til) {
					HOSO_USER hsr = (HOSO_USER) treeItem.getData();
					try {
						controler.getControl_HOSO_DAGUI().remove_HOSO_DAGUI(hsr.getHoso_Dagui());
						treeItem.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXaNhn.setText("Xóa nhãn");

		list = new List(sashForm, SWT.BORDER | SWT.V_SCROLL);
		sashForm.setWeights(new int[] { 423, 172 });

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
				String date = hsr.getTaphoso().getNGAY_TAO_TAPHOSO() == null ? "-"
						: mdf.getViewStringDate(hsr.getTaphoso().getNGAY_TAO_TAPHOSO());
				TreeItem ti = new TreeItem(tree_TuHoso, SWT.NONE);
				ti.setText(new String[] { "" + i, hsr.getTaphoso().getTEN_TAPHOSO(),
						mdf.getViewStringDate(hsr.getHoso_Dagui().getNGAY_GUI()), date,
						hsr.getTaphoso().getGIOITHIEU_TAPHOSO(),
						hsr.getTEN_TAI_KHOAN() == null ? "--" : hsr.getTEN_TAI_KHOAN() });
				ti.setData(hsr);
				i++;
				ti.setExpanded(false);
			}
	}
}
