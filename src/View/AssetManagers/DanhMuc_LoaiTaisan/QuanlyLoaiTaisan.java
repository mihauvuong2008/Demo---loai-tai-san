package View.AssetManagers.DanhMuc_LoaiTaisan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import View.AssetManagers.MainForm;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_Loaitaisan;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

public class QuanlyLoaiTaisan extends Shell {
	private static NGUOIDUNG user;
	TreeItem Tong_Item;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyLoaiTaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanlyLoaiTaisan shell = new QuanlyLoaiTaisan(display, user);
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

	private int MODE;
	private Text text_Name;
	private Tree tree;

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @throws SQLException
	 */
	public QuanlyLoaiTaisan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/Books-icon.png"));
		setLayout(new GridLayout(7, false));
		QuanlyLoaiTaisan.user = user;
		controler = new Controler(user);

		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));
		Tong_Item = new TreeItem(tree, SWT.NONE);
		Tong_Item.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Tong_Item.setImage(SWTResourceManager.getImage(MainForm.class, "/Books-icon.png"));
		Tong_Item.setText("Tất cả Loại tài sản");
		setItem_LoaiTaisan(Tong_Item);
		Tong_Item.setExpanded(true);
		tree.pack();
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree.getSelection();
				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof LOAITAISAN_CAP_I) {
						text_Name.setText(((LOAITAISAN_CAP_I) o).getTEN_LOAITAISAN_CAP_I());
					} else if (o instanceof LOAITAISAN_CAP_II) {
						text_Name.setText(((LOAITAISAN_CAP_II) o).getTEN_LOAITAISAN_CAP_II());
					} else if (o instanceof LOAITAISAN_CAP_III) {
						text_Name.setText(((LOAITAISAN_CAP_III) o).getTEN_LOAITAISAN_CAP_III());
					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});
		// tsl.setTreeItemHeight(tree, 20);

		text_Name = new Text(this, SWT.BORDER);
		text_Name.setEditable(false);
		text_Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/add-1-icon (1).png"));
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		btnThm.setText("Thêm");

		Button btnSa = new Button(this, SWT.NONE);
		btnSa.setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/edit-validated-icon (1).png"));
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		btnSa.setText("Sửa");

		Button btnXa = new Button(this, SWT.NONE);
		btnXa.setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/delete-1-icon (1).png"));
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						Object o = items[0].getData();
						if (o instanceof LOAITAISAN_CAP_I) {
							LOAITAISAN_CAP_I l = (LOAITAISAN_CAP_I) o;
							controler.getControl_LOAITAISAN_CAP_I().delete_LOAITAISAN_CAP_I(l);
						} else if (o instanceof LOAITAISAN_CAP_II) {
							LOAITAISAN_CAP_II l = (LOAITAISAN_CAP_II) o;
							controler.getControl_LOAITAISAN_CAP_II().delete_LOAITAISAN_CAP_II(l);
						} else if (o instanceof LOAITAISAN_CAP_III) {
							LOAITAISAN_CAP_III l = (LOAITAISAN_CAP_III) o;
							controler.getControl_LOAITAISAN_CAP_III().delete_LOAITAISAN_CAP_III(l);
						}
					}
					setItem_LoaiTaisan(Tong_Item);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXa.setText("Xóa");

		Button btnHonTt = new Button(this, SWT.NONE);
		btnHonTt.setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/Actions-document-save-icon (1).png"));
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						Object o = items[0].getData();
						if (isInsertMode()) {
							if (!text_Name.getText().equals("")) {
								if (o instanceof LOAITAISAN_CAP_I) {
									LOAITAISAN_CAP_II l = new LOAITAISAN_CAP_II();
									l.setTEN_LOAITAISAN_CAP_II(text_Name.getText());
									l.setMA_LOAITAISAN_CAP_I(((LOAITAISAN_CAP_I) o).getMA_LOAITAISAN_CAP_I());
									controler.getControl_LOAITAISAN_CAP_II().insert_LOAITAISAN_CAP_II(l);
								} else if (o instanceof LOAITAISAN_CAP_II) {
									LOAITAISAN_CAP_III l = new LOAITAISAN_CAP_III();
									l.setTEN_LOAITAISAN_CAP_III(text_Name.getText());
									l.setMA_LOAITAISAN_CAP_II(((LOAITAISAN_CAP_II) o).getMA_LOAITAISAN_CAP_II());
									controler.getControl_LOAITAISAN_CAP_III().insert_LOAITAISAN_CAP_III(l);
								} else if (!(o instanceof LOAITAISAN_CAP_III)) {
									LOAITAISAN_CAP_I l = new LOAITAISAN_CAP_I();
									l.setTEN_LOAITAISAN_CAP_I(text_Name.getText());
									controler.getControl_LOAITAISAN_CAP_I().insert_LOAITAISAN_CAP_I(l);
								}
							} else {
								MessageBox m = new MessageBox(getShell(), SWT.NONE);
								m.setMessage("Không để trống Tên nhóm");
								m.open();
							}
							setItem_LoaiTaisan(Tong_Item);
						} else if (isEditMode()) {
							if (!text_Name.getText().equals("")) {
								if (o instanceof LOAITAISAN_CAP_I) {
									LOAITAISAN_CAP_I l = (LOAITAISAN_CAP_I) o;
									l.setTEN_LOAITAISAN_CAP_I(text_Name.getText());
									controler.getControl_LOAITAISAN_CAP_I().update_LOAITAISAN_CAP_I(l);
								} else if (o instanceof LOAITAISAN_CAP_II) {
									LOAITAISAN_CAP_II l = (LOAITAISAN_CAP_II) o;
									l.setTEN_LOAITAISAN_CAP_II(text_Name.getText());
									controler.getControl_LOAITAISAN_CAP_II().update_LOAITAISAN_CAP_II(l);
								} else if (o instanceof LOAITAISAN_CAP_III) {
									LOAITAISAN_CAP_III l = (LOAITAISAN_CAP_III) o;
									l.setTEN_LOAITAISAN_CAP_III(text_Name.getText());
									controler.getControl_LOAITAISAN_CAP_III().update_PHAN_NHOMTAISAN(l);
								}
							} else {
								MessageBox m = new MessageBox(getShell(), SWT.NONE);
								m.setMessage("Không để trống Tên nhóm");
								m.open();
							}
							setItem_LoaiTaisan(Tong_Item);

						}
					}

					setComplete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnHonTt.setText("Hoàn tất");

		Button button = new Button(this, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(QuanlyLoaiTaisan.class, "/import-icon.png"));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ImportExcel_Loaitaisan il = new ImportExcel_Loaitaisan(display, user);
					il.open();
					setItem_LoaiTaisan(Tong_Item);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		button.setText("Tạo mới từ excel");

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

	private void setItem_LoaiTaisan(TreeItem tong_Item) throws SQLException {
		tong_Item.removeAll();
		ArrayList<LOAITAISAN_CAP_I> ll = controler.getControl_LOAITAISAN_CAP_I().getAllData();
		for (LOAITAISAN_CAP_I l_lv1 : ll) {
			TreeItem ti = new TreeItem(tong_Item, SWT.None);
			ti.setText(l_lv1.getTEN_LOAITAISAN_CAP_I());
			ArrayList<LOAITAISAN_CAP_II> nl = controler.getControl_LOAITAISAN_CAP_II().getAllData();
			ti.setData(l_lv1);
			for (LOAITAISAN_CAP_II lv2 : nl) {
				if (lv2.getMA_LOAITAISAN_CAP_I() == l_lv1.getMA_LOAITAISAN_CAP_I()) {
					TreeItem tii = new TreeItem(ti, SWT.None);
					tii.setText(lv2.getTEN_LOAITAISAN_CAP_II());
					tii.setData(lv2);
					ArrayList<LOAITAISAN_CAP_III> pl = controler.getControl_LOAITAISAN_CAP_III().getAllData();
					for (LOAITAISAN_CAP_III l_lv3 : pl) {
						if (l_lv3.getMA_LOAITAISAN_CAP_II() == lv2.getMA_LOAITAISAN_CAP_II()) {
							TreeItem tiii = new TreeItem(tii, SWT.None);
							tiii.setText("(" + l_lv3.getMA_LOAITAISAN_CAP_III() + ") - "
									+ l_lv3.getTEN_LOAITAISAN_CAP_III());
							tiii.setData(l_lv3);
						}
					}
					tii.setExpanded(true);
				}
			}
			ti.setExpanded(true);
		}
		tong_Item.setExpanded(true);
	}

	protected boolean isEditMode() {
		if (MODE == 1)
			return true;
		return false;
	}

	protected boolean isInsertMode() {
		if (MODE == 2)
			return true;
		return false;
	}

	protected void setInsertMode() {
		MODE = 2;
		text_Name.setText("");
		text_Name.setEditable(true);
	}

	protected void setEditMode() {
		MODE = 1;
		text_Name.setEditable(true);
	}

	protected void setComplete() {
		MODE = 0;
		text_Name.setText("");
		text_Name.setEditable(false);
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Quản lý Loại tài sản");
		setSize(800, 500);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
