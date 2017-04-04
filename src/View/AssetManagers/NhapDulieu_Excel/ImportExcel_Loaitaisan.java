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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;

import jxl.read.biff.BiffException;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import View.Template.FormTemplate;

public class ImportExcel_Loaitaisan extends Shell {
	private Text text;
	private Tree tree;
	private static NGUOIDUNG user;
	private final Controler controler;
	private static Log log = LogFactory.getLog(ImportExcel_Loaitaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ImportExcel_Loaitaisan shell = new ImportExcel_Loaitaisan(display, user);
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
	 */
	public ImportExcel_Loaitaisan(Display display, NGUOIDUNG user) {
		super(display, SWT.SHELL_TRIM);
		setLayout(new GridLayout(3, false));
		ImportExcel_Loaitaisan.user = user;
		controler = new Controler(user);

		Label lblChnFile = new Label(this, SWT.NONE);
		lblChnFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChnFile.setText("\u0110\u01B0\u1EDDng d\u1EABn:");

		text = new Text(this, SWT.BORDER);
		text.setText("");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChn = new Button(this, SWT.NONE);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Mẫu Loại tài sản");
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
							ExcelReader_LoaiTaisan en = new ExcelReader_LoaiTaisan(buf.toString());
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

			private void fillTree(ArrayList<Object> dataNhomTaisan) {
				TreeItem parent_lv1 = null;
				TreeItem parent_lv2 = null;
				for (Object o : dataNhomTaisan) {
					TreeItem child = null;
					if (o instanceof LOAITAISAN_CAP_I) {
						LOAITAISAN_CAP_I t = (LOAITAISAN_CAP_I) o;
						parent_lv1 = new TreeItem(tree, SWT.NONE);
						parent_lv1.setText(new String[] { String.valueOf(t.getMA_LOAITAISAN_CAP_I()),
								t.getTEN_LOAITAISAN_CAP_I() });
						parent_lv1.setData(t);
					} else if (o instanceof LOAITAISAN_CAP_II) {
						LOAITAISAN_CAP_II t = (LOAITAISAN_CAP_II) o;
						parent_lv2 = new TreeItem(parent_lv1, SWT.NONE);
						parent_lv2.setText(new String[] { String.valueOf(t.getMA_LOAITAISAN_CAP_II()),
								t.getTEN_LOAITAISAN_CAP_II() });
						parent_lv2.setData(t);
					} else if (o instanceof LOAITAISAN_CAP_III) {
						LOAITAISAN_CAP_III t = (LOAITAISAN_CAP_III) o;
						child = new TreeItem(parent_lv2, SWT.NONE);
						child.setText(new String[] { String.valueOf(t.getMA_LOAITAISAN_CAP_III()),
								t.getTEN_LOAITAISAN_CAP_III() });
						child.setData(t);
					}
					if (parent_lv1 != null)
						parent_lv1.setExpanded(true);
					if (parent_lv2 != null)
						parent_lv2.setExpanded(true);
					if (child != null)
						child.setExpanded(true);
				}
				for (TreeColumn tc : tree.getColumns()) {
					tc.pack();
				}
			}
		});
		GridData gd_btnChn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Ch\u1ECDn");

		tree = new Tree(this, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		TreeColumn trclmnKey = new TreeColumn(tree, SWT.NONE);
		trclmnKey.setWidth(100);
		trclmnKey.setText("Key");

		TreeColumn trclmnContent = new TreeColumn(tree, SWT.NONE);
		trclmnContent.setWidth(300);
		trclmnContent.setText("Content");
		new Label(this, SWT.NONE);

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					for (TreeItem ti1 : tree.getItems()) {
						Object o1 = ti1.getData();
						if (o1 instanceof LOAITAISAN_CAP_I) {
							LOAITAISAN_CAP_I o_ = (LOAITAISAN_CAP_I) o1;
							controler.getControl_LOAITAISAN_CAP_I().insert_LOAITAISAN_CAP_I(o_);
						}
						for (TreeItem ti2 : ti1.getItems()) {
							Object o2 = ti2.getData();
							if (o2 instanceof LOAITAISAN_CAP_II) {
								LOAITAISAN_CAP_II o_ = (LOAITAISAN_CAP_II) o2;
								controler.getControl_LOAITAISAN_CAP_II().insert_LOAITAISAN_CAP_II(o_);
							}
							for (TreeItem ti3 : ti2.getItems()) {
								Object o3 = ti3.getData();
								if (o3 instanceof LOAITAISAN_CAP_III) {
									LOAITAISAN_CAP_III o_ = (LOAITAISAN_CAP_III) o3;
									controler.getControl_LOAITAISAN_CAP_III().insert_LOAITAISAN_CAP_III(o_);
								}
							}
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				dispose();
			}
		});
		GridData gd_btnThm = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnThm.widthHint = 75;
		btnThm.setLayoutData(gd_btnThm);
		btnThm.setText("Ho\u00E0n t\u1EA5t");

		Button btnng = new Button(this, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
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
