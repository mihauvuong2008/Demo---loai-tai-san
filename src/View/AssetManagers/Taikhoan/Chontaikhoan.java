package View.AssetManagers.Taikhoan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.Template.FormTemplate;
import View.Template.TreeTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Chontaikhoan extends Dialog {

	private ArrayList<NGUOIDUNG> result;
	protected Shell shlChnTiKhon;
	private Table table;
	private Tree tree_Phongban;
	private NGUOIDUNG user;
	Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Chontaikhoan(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
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
		shlChnTiKhon.open();
		shlChnTiKhon.layout();
		Display display = getParent().getDisplay();
		while (!shlChnTiKhon.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return getResult();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlChnTiKhon = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlChnTiKhon.setImage(
				SWTResourceManager.getImage(Chontaikhoan.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlChnTiKhon.setSize(599, 382);
		shlChnTiKhon.setText("Chọn tài khoản");
		shlChnTiKhon.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlChnTiKhon);

		SashForm sashForm = new SashForm(shlChnTiKhon, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tree_Phongban = new Tree(sashForm, SWT.BORDER);
		tree_Phongban.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree_Phongban.getSelection();
				if (til.length <= 0)
					return;
				PHONGBAN pb = (PHONGBAN) til[0].getData();
				try {
					ArrayList<NGUOIDUNG> ndl;
					if (pb == null) {
						ndl = controler.getControl_NGUOIDUNG().get_All();
					} else {
						ndl = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(pb);

					}
					fillTable(ndl);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(100);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnTiKhon = new TableColumn(table, SWT.NONE);
		tblclmnTnTiKhon.setWidth(100);
		tblclmnTnTiKhon.setText("TÊN TÀI KHOẢN");

		TableColumn tblclmnTnNgiDng = new TableColumn(table, SWT.NONE);
		tblclmnTnNgiDng.setWidth(100);
		tblclmnTnNgiDng.setText("TÊN NGƯỜI DÙNG");

		TableColumn tblclmnGiiThiu = new TableColumn(table, SWT.NONE);
		tblclmnGiiThiu.setWidth(100);
		tblclmnGiiThiu.setText("GIỚI THIỆU");
		sashForm.setWeights(new int[] { 216, 315 });

		Button btnXong = new Button(shlChnTiKhon, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length <= 0)
					return;
				if (result == null)
					result = new ArrayList<>();
				for (TableItem tableItem : til) {
					NGUOIDUNG nd = (NGUOIDUNG) tableItem.getData();
					result.add(nd);
				}
				shlChnTiKhon.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlChnTiKhon, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnTiKhon.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	protected void fillTable(ArrayList<NGUOIDUNG> ndl) {
		table.removeAll();
		if (ndl == null)
			return;
		int i = 1;
		for (NGUOIDUNG nguoidung : ndl) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(new String[] { (i++) + "", nguoidung.getTEN_TAI_KHOAN(), nguoidung.getTEN_CAN_BO(),
					nguoidung.getGIOI_THIEU() });
			ti.setData(nguoidung);
		}
	}

	private void init() throws SQLException {
		new TreeTemplate(user).getTreePHONGBAN(tree_Phongban);
	}

	public ArrayList<NGUOIDUNG> getResult() {
		return result;
	}

	public void setResult(ArrayList<NGUOIDUNG> result) {
		this.result = result;
	}
}
