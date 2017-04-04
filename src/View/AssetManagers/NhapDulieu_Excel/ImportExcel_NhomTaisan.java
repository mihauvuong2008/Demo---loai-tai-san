package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import jxl.read.biff.BiffException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ImportExcel_NhomTaisan extends Shell {
	private Text text;
	private static NGUOIDUNG user;
	private Tree tree;
	private int itemHeight = 20;
	private final Controler controler;
	private static Log log = LogFactory.getLog(ImportExcel_NhomTaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ImportExcel_NhomTaisan shell = new ImportExcel_NhomTaisan(display, user);
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
	 * @param user
	 */
	public ImportExcel_NhomTaisan(Display display, NGUOIDUNG user) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(ImportExcel_NhomTaisan.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(4, false));
		ImportExcel_NhomTaisan.user = user;
		controler = new Controler(user);

		Label lblngDn = new Label(this, SWT.NONE);
		lblngDn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblngDn.setText("\u0110\u01B0\u1EDDng d\u1EABn:");

		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Button btnChnFile = new Button(this, SWT.NONE);
		btnChnFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Mẫu Nhóm tài sản");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xls", "*.xlsx" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				if (selected != null) {
					String[] files = fd.getFileNames();
					for (int i = 0, n = files.length; i < n; i++) {
						StringBuffer buf = new StringBuffer();
						buf.append(fd.getFilterPath());
						if (buf.charAt(buf.length() - 1) != File.separatorChar) {
							buf.append(File.separatorChar);
						}
						buf.append(files[i]);
						try {
							ExcelReader_NhomTaisan en = new ExcelReader_NhomTaisan(buf.toString());
							ArrayList<Object> DataNhomTaisan = en.getData();
							fillTree(DataNhomTaisan);
						} catch (BiffException | IOException e1) {
							if (e1.getMessage().equals("Unable to recognize OLE stream")) {
								MessageBox m = new MessageBox(getShell(), SWT.ERROR);
								m.setMessage("Hỗ trợ File Excel cho phiên bản Excel 2000 về trước");
								m.open();
							}
						}
					}
				}
			}
		});
		GridData gd_btnChnFile = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnChnFile.widthHint = 75;
		btnChnFile.setLayoutData(gd_btnChnFile);
		btnChnFile.setText("Ch\u1ECDn file");

		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		TreeRowStyle ts = new TreeRowStyle();
		ts.setTreeItemHeight(tree, itemHeight);
		TreeColumn trclmnNewColumn = new TreeColumn(tree, SWT.NONE);
		trclmnNewColumn.setWidth(100);
		trclmnNewColumn.setText("Key");

		TreeColumn trclmnName = new TreeColumn(tree, SWT.NONE);
		trclmnName.setWidth(500);
		trclmnName.setText("Name");

		TreeColumn trclmnNm = new TreeColumn(tree, SWT.NONE);
		trclmnNm.setWidth(100);
		trclmnNm.setText("Năm");

		TreeColumn trclmnTL = new TreeColumn(tree, SWT.NONE);
		trclmnTL.setWidth(100);
		trclmnTL.setText("Tỉ lệ");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Button btnLu = new Button(this, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					controler.getControl_NHOMTAISAN_CAP_V().delete_All();
					controler.getControl_NHOMTAISAN_CAP_IV().delete_All();
					controler.getControl_NHOMTAISAN_CAP_III().delete_All();
					controler.getControl_NHOMTAISAN_CAP_II().delete_All();
					controler.getControl_NHOMTAISAN_CAP_I().delete_All();
					for (TreeItem ti1 : tree.getItems()) {
						Object o1 = ti1.getData();
						if (o1 instanceof NHOMTAISAN_CAP_I) {
							NHOMTAISAN_CAP_I o_ = (NHOMTAISAN_CAP_I) o1;
							controler.getControl_NHOMTAISAN_CAP_I().insert_NHOMTAISAN_CAP_I(o_);
						}
						for (TreeItem ti2 : ti1.getItems()) {
							Object o2 = ti2.getData();
							if (o2 instanceof NHOMTAISAN_CAP_II) {
								NHOMTAISAN_CAP_II o_ = (NHOMTAISAN_CAP_II) o2;
								controler.getControl_NHOMTAISAN_CAP_II().insert_NHOMTAISAN_CAP_II(o_);
							}
							for (TreeItem ti3 : ti2.getItems()) {
								Object o3 = ti3.getData();
								if (o3 instanceof NHOMTAISAN_CAP_III) {
									NHOMTAISAN_CAP_III o_ = (NHOMTAISAN_CAP_III) o3;
									controler.getControl_NHOMTAISAN_CAP_III().insert_NHOMTAISAN_CAP_III(o_);
								}
								for (TreeItem ti4 : ti3.getItems()) {
									Object o4 = ti4.getData();
									if (o4 instanceof NHOMTAISAN_CAP_IV) {
										NHOMTAISAN_CAP_IV o_ = (NHOMTAISAN_CAP_IV) o4;
										controler.getControl_NHOMTAISAN_CAP_IV().insert_NHOMTAISAN_CAP_IV(o_);
									}
									for (TreeItem ti5 : ti4.getItems()) {
										Object o5 = ti5.getData();
										if (o5 instanceof NHOMTAISAN_CAP_V) {
											NHOMTAISAN_CAP_V o_ = (NHOMTAISAN_CAP_V) o5;
											controler.getControl_NHOMTAISAN_CAP_V().insert_NHOMTAISAN_CAP_V(o_);
										}
									}
								}
							}
						}
					}
					dispose();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnLu = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Ho\u00E0n t\u1EA5t");

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
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	protected void fillTree(ArrayList<Object> dataNhomTaisan) {
		TreeItem parent_lv1 = null;
		TreeItem parent_lv2 = null;
		TreeItem parent_lv3 = null;
		TreeItem parent_lv4 = null;
		for (Object o : dataNhomTaisan) {
			TreeItem child = null;
			if (o instanceof NHOMTAISAN_CAP_I) {
				NHOMTAISAN_CAP_I t = (NHOMTAISAN_CAP_I) o;
				parent_lv1 = new TreeItem(tree, SWT.NONE);
				parent_lv1.setText(
						new String[] { String.valueOf(t.getMA_NHOMTAISAN_CAP_I()), t.getTEN_NHOMTAISAN_CAP_I() });
				parent_lv1.setData(t);
			} else if (o instanceof NHOMTAISAN_CAP_II) {
				NHOMTAISAN_CAP_II t = (NHOMTAISAN_CAP_II) o;
				parent_lv2 = new TreeItem(parent_lv1, SWT.NONE);
				parent_lv2.setText(
						new String[] { String.valueOf(t.getMA_NHOMTAISAN_CAP_II()), t.getTEN_NHOMTAISAN_CAP_II() });
				parent_lv2.setData(t);
			} else if (o instanceof NHOMTAISAN_CAP_III) {
				NHOMTAISAN_CAP_III t = (NHOMTAISAN_CAP_III) o;
				parent_lv3 = new TreeItem(parent_lv2, SWT.NONE);
				parent_lv3.setText(
						new String[] { String.valueOf(t.getMA_NHOMTAISAN_CAP_III()), t.getTEN_NHOMTAISAN_CAP_III() });
				parent_lv3.setData(t);
			} else if (o instanceof NHOMTAISAN_CAP_IV) {
				NHOMTAISAN_CAP_IV t = (NHOMTAISAN_CAP_IV) o;
				parent_lv4 = new TreeItem(parent_lv3, SWT.NONE);
				parent_lv4.setText(
						new String[] { String.valueOf(t.getMA_NHOMTAISAN_CAP_IV()), t.getTEN_NHOMTAISAN_CAP_IV() });
				parent_lv4.setData(t);
			} else if (o instanceof NHOMTAISAN_CAP_V) {
				NHOMTAISAN_CAP_V t = (NHOMTAISAN_CAP_V) o;
				child = new TreeItem(parent_lv4, SWT.NONE);
				child.setText(new String[] { String.valueOf(t.getMA_NHOMTAISAN_CAP_V()), t.getTEN_NHOMTAISAN_CAP_V(),
						String.valueOf(t.getTHOIGIAN_SUDUNG()), String.valueOf(t.getTILE_HAOMON()) });
				child.setData(t);
			}
			if (parent_lv1 != null)
				parent_lv1.setExpanded(true);
			if (parent_lv2 != null)
				parent_lv2.setExpanded(true);
			if (parent_lv3 != null)
				parent_lv3.setExpanded(true);
			if (parent_lv4 != null)
				parent_lv4.setExpanded(true);
			if (child != null)
				child.setExpanded(true);
		}
		for (TreeColumn tc : tree.getColumns()) {
			tc.pack();
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(800, 500);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
