package View.AssetManagers.CongViec.Giamtaisan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import View.Template.TreeTemplate;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NhapDanhsachGiam extends Dialog {

	protected ArrayList<TAISAN> result;
	protected Shell shlChnPhngTin;
	private Text text_Pettern;
	private Tree tree_Phongban;
	protected PHONGBAN p;
	private NGUOIDUNG user;
	ArrayList<TAISAN> list;
	private final Controler controler;
	private Tree tree;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(NhapDanhsachGiam.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public NhapDanhsachGiam(Shell parent, int style, ArrayList<TAISAN> list, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		this.list = list;
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
		shlChnPhngTin.open();
		shlChnPhngTin.layout();
		Display display = getParent().getDisplay();
		while (!shlChnPhngTin.isDisposed()) {
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
		shlChnPhngTin = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shlChnPhngTin.setImage(
				SWTResourceManager.getImage(NhapDanhsachGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlChnPhngTin.setSize(809, 500);
		new FormTemplate().setCenterScreen(shlChnPhngTin);
		shlChnPhngTin.setText("Chọn Phương tiện tài sản");
		shlChnPhngTin.setLayout(new GridLayout(2, false));

		text_Pettern = new Text(shlChnPhngTin, SWT.BORDER | SWT.RIGHT);
		text_Pettern.setMessage("Nhập tên phương tiện tài sản");
		text_Pettern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnTm = new Button(shlChnPhngTin, SWT.NONE);
		btnTm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					fillTree(controler.getControl_TAISAN().get_Taisan_Timkiem(text_Pettern.getText(), p));
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnTm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTm.widthHint = 75;
		btnTm.setLayoutData(gd_btnTm);
		btnTm.setText("T\u00ECm");

		SashForm sashForm = new SashForm(shlChnPhngTin, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		tree_Phongban = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		new TreeTemplate(user).getTreePHONGBAN(tree_Phongban);
		tree_Phongban.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree_Phongban.getSelection();
				if (items.length > 0) {
					p = (PHONGBAN) items[0].getData();
				}
			}
		});
		tree = new Tree(sashForm, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);

		TreeColumn trclmnStt = new TreeColumn(tree, SWT.NONE);
		trclmnStt.setWidth(45);
		trclmnStt.setText("STT");

		TreeColumn trclmnMPtts = new TreeColumn(tree, SWT.NONE);
		trclmnMPtts.setWidth(100);
		trclmnMPtts.setText("MÃ PTTS");

		TreeColumn trclmnTnPtts = new TreeColumn(tree, SWT.NONE);
		trclmnTnPtts.setWidth(150);
		trclmnTnPtts.setText("TÊN PTTS");

		TreeColumn trclmnNmSDng = new TreeColumn(tree, SWT.NONE);
		trclmnNmSDng.setWidth(100);
		trclmnNmSDng.setText("NĂM SỬ DỤNG");

		TreeColumn trclmnModel = new TreeColumn(tree, SWT.NONE);
		trclmnModel.setWidth(100);
		trclmnModel.setText("MODEL");

		TreeColumn trclmnNguynGi = new TreeColumn(tree, SWT.NONE);
		trclmnNguynGi.setWidth(100);
		trclmnNguynGi.setText("NGUYÊN GIÁ");

		TreeColumn trclmnSeri = new TreeColumn(tree, SWT.NONE);
		trclmnSeri.setWidth(100);
		trclmnSeri.setText("SERI");
		sashForm.setWeights(new int[] { 618, 1000 });

		Button btnChn = new Button(shlChnPhngTin, SWT.NONE);
		btnChn.setImage(
				SWTResourceManager.getImage(NhapDanhsachGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getItems();
				for (TreeItem ti : til) {
					if (ti.getChecked()) {
						TAISAN ts = (TAISAN) ti.getData();
						if (result == null)
							result = new ArrayList<>();
						if (ts != null)
							result.add(ts);
					}
				}
				shlChnPhngTin.dispose();
			}
		});
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Chọn");

		Button btnng = new Button(shlChnPhngTin, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnPhngTin.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

	protected void fillTree(ArrayList<TAISAN> get_Taisan_Timkiem) {
		tree.removeAll();
		if (get_Taisan_Timkiem != null) {
			int i = 1;
			for (TAISAN t : get_Taisan_Timkiem) {
				boolean showFlag = true;
				if (list != null)
					for (TAISAN t_ : list) {
						if (t_.getMA_TAISAN() == t.getMA_TAISAN()) {
							showFlag = false;
						}
					}
				if (showFlag) {
					TreeItem ti = new TreeItem(tree, SWT.NONE);
					ti.setText(new String[] { i + "", t.getMA_TAISAN() + "", t.getTEN_TAISAN(),
							mdf.getViewStringDate(t.getNGAY_SU_DUNG()), t.getMODEL(), t.getSERI(),
							t.getNGUYEN_GIA() + "" });
					ti.setData(t);
					if (t.getPhukienList() != null) {
						int j = 1;
						for (PHUKIEN pk : t.getPhukienList()) {
							TreeItem child = new TreeItem(ti, SWT.NONE);
							child.setText(new String[] { j + "", pk.getMA_PHUKIEN() + "", pk.getTEN_PHUKIEN(), "-",
									pk.getMODEL(), pk.getSERI(), pk.getNGUYEN_GIA() + "" });
							j++;
						}
					}
					i++;
				}
			}
		}
		if (tree != null) {
			for (TreeColumn tc : tree.getColumns()) {
				tc.pack();
			}
		}
	}

}
