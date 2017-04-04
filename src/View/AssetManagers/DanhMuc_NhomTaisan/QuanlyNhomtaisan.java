package View.AssetManagers.DanhMuc_NhomTaisan;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import View.AssetManagers.MainForm;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_NhomTaisan;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class QuanlyNhomtaisan extends Shell {
	private Tree tree;
	private static NGUOIDUNG user;
	TreeItem TongItem_NhomTaisan;
	private Text text_Name;
	private int MODE;
	private Button btnCheckButton;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyNhomtaisan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanlyNhomtaisan shell = new QuanlyNhomtaisan(display, user);
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
	public QuanlyNhomtaisan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/Books-2-icon.png"));
		setLayout(new GridLayout(8, false));
		QuanlyNhomtaisan.user = user;
		controler = new Controler(user);

		tree = new Tree(this, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 8, 1));
		TongItem_NhomTaisan = new TreeItem(tree, SWT.NONE);
		TongItem_NhomTaisan.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		TongItem_NhomTaisan.setImage(SWTResourceManager.getImage(MainForm.class, "/Books-2-icon.png"));
		TongItem_NhomTaisan.setText("Tất cả Loại tài sản");
		setItem_NhomTaisan(TongItem_NhomTaisan, true);
		TongItem_NhomTaisan.setExpanded(true);
		tree.pack();
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof NHOMTAISAN_CAP_I) {
						text_Name.setText(((NHOMTAISAN_CAP_I) o).getTEN_NHOMTAISAN_CAP_I());
					} else if (o instanceof NHOMTAISAN_CAP_II) {
						text_Name.setText(((NHOMTAISAN_CAP_II) o).getTEN_NHOMTAISAN_CAP_II());

					} else if (o instanceof NHOMTAISAN_CAP_III) {
						text_Name.setText(((NHOMTAISAN_CAP_III) o).getTEN_NHOMTAISAN_CAP_III());

					} else if (o instanceof NHOMTAISAN_CAP_IV) {
						text_Name.setText(((NHOMTAISAN_CAP_IV) o).getTEN_NHOMTAISAN_CAP_IV());

					} else if (o instanceof NHOMTAISAN_CAP_V) {
						text_Name.setText(((NHOMTAISAN_CAP_V) o).getTEN_NHOMTAISAN_CAP_V());

					} else {
						text_Name.setText("Tất cả");
					}
				}
			}
		});

		btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setSelection(true);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flg = btnCheckButton.getSelection();
				try {
					setItem_NhomTaisan(TongItem_NhomTaisan, flg);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnCheckButton.setText("Mở rộng");

		text_Name = new Text(this, SWT.BORDER);
		text_Name.setEditable(false);
		text_Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/add-1-icon (1).png"));
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		btnThm.setText("Thêm");

		Button btnSa = new Button(this, SWT.NONE);
		btnSa.setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/edit-validated-icon (1).png"));
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		btnSa.setText("Sửa");

		Button btnXa = new Button(this, SWT.NONE);
		btnXa.setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/delete-1-icon (1).png"));
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						Object o = items[0].getData();
						if (o instanceof NHOMTAISAN_CAP_I) {
							NHOMTAISAN_CAP_I l = (NHOMTAISAN_CAP_I) o;
							controler.getControl_NHOMTAISAN_CAP_I().delete_NHOMTAISAN_CAP_I(l);
						} else if (o instanceof NHOMTAISAN_CAP_II) {
							NHOMTAISAN_CAP_II l = (NHOMTAISAN_CAP_II) o;
							controler.getControl_NHOMTAISAN_CAP_II().delete_NHOMTAISAN_CAP_II(l);
						} else if (o instanceof NHOMTAISAN_CAP_III) {
							NHOMTAISAN_CAP_III l = (NHOMTAISAN_CAP_III) o;
							controler.getControl_NHOMTAISAN_CAP_III().delete_NHOMTAISAN_CAP_III(l);
						} else if (o instanceof NHOMTAISAN_CAP_IV) {
							NHOMTAISAN_CAP_IV l = (NHOMTAISAN_CAP_IV) o;
							controler.getControl_NHOMTAISAN_CAP_IV().delete_NHOMTAISAN_CAP_IV(l);
						} else if (o instanceof NHOMTAISAN_CAP_V) {
							NHOMTAISAN_CAP_V l = (NHOMTAISAN_CAP_V) o;
							controler.getControl_NHOMTAISAN_CAP_V().delete_NHOMTAISAN_CAP_V(l);
						}
					}
					setItem_NhomTaisan(TongItem_NhomTaisan, true);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXa.setText("Xóa");

		Button btnLu = new Button(this, SWT.NONE);
		btnLu.setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/Actions-document-save-icon (1).png"));
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (items.length > 0) {
						Object o = items[0].getData();
						if (isInsertMode()) {
							if (!text_Name.getText().equals("")) {
								if (o instanceof NHOMTAISAN_CAP_I) {
									NHOMTAISAN_CAP_II l = new NHOMTAISAN_CAP_II();
									l.setTEN_NHOMTAISAN_CAP_II(text_Name.getText());
									l.setMA_NHOMTAISAN_CAP_I(((NHOMTAISAN_CAP_I) o).getMA_NHOMTAISAN_CAP_I());
									controler.getControl_NHOMTAISAN_CAP_II().insert_NHOMTAISAN_CAP_II(l);
								} else if (o instanceof NHOMTAISAN_CAP_II) {
									NHOMTAISAN_CAP_III l = new NHOMTAISAN_CAP_III();
									l.setTEN_NHOMTAISAN_CAP_III(text_Name.getText());
									l.setMA_NHOMTAISAN_CAP_II(((NHOMTAISAN_CAP_II) o).getMA_NHOMTAISAN_CAP_II());
									controler.getControl_NHOMTAISAN_CAP_III().insert_NHOMTAISAN_CAP_III(l);
								} else if (o instanceof NHOMTAISAN_CAP_III) {
									NHOMTAISAN_CAP_IV l = new NHOMTAISAN_CAP_IV();
									l.setTEN_NHOMTAISAN_CAP_IV(text_Name.getText());
									l.setMA_NHOMTAISAN_CAP_III(((NHOMTAISAN_CAP_III) o).getMA_NHOMTAISAN_CAP_III());
									controler.getControl_NHOMTAISAN_CAP_IV().insert_NHOMTAISAN_CAP_IV(l);
								} else if (o instanceof NHOMTAISAN_CAP_IV) {
									NHOMTAISAN_CAP_V l = new NHOMTAISAN_CAP_V();
									l.setTEN_NHOMTAISAN_CAP_V(text_Name.getText());
									l.setMA_NHOMTAISAN_CAP_IV(((NHOMTAISAN_CAP_IV) o).getMA_NHOMTAISAN_CAP_IV());
									controler.getControl_NHOMTAISAN_CAP_V().insert_NHOMTAISAN_CAP_V(l);
								} else {
									NHOMTAISAN_CAP_I l = new NHOMTAISAN_CAP_I();
									l.setTEN_NHOMTAISAN_CAP_I(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_I().insert_NHOMTAISAN_CAP_I(l);
								}
							} else {
								MessageBox m = new MessageBox(getShell(), SWT.NONE);
								m.setMessage("Không để trống Tên nhóm");
								m.open();
							}
							setItem_NhomTaisan(TongItem_NhomTaisan, true);
						} else if (isEditMode()) {
							if (!text_Name.getText().equals("")) {
								if (o instanceof NHOMTAISAN_CAP_I) {
									NHOMTAISAN_CAP_I l = (NHOMTAISAN_CAP_I) o;
									l.setTEN_NHOMTAISAN_CAP_I(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_I().update_NHOMTAISAN_CAP_I(l);
								} else if (o instanceof NHOMTAISAN_CAP_II) {
									NHOMTAISAN_CAP_II l = (NHOMTAISAN_CAP_II) o;
									l.setTEN_NHOMTAISAN_CAP_II(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_II().update_NHOMTAISAN_CAP_II(l);
								} else if (o instanceof NHOMTAISAN_CAP_III) {
									NHOMTAISAN_CAP_III l = (NHOMTAISAN_CAP_III) o;
									l.setTEN_NHOMTAISAN_CAP_III(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_III().update_NHOMTAISAN_CAP_III(l);
								} else if (o instanceof NHOMTAISAN_CAP_IV) {
									NHOMTAISAN_CAP_IV l = (NHOMTAISAN_CAP_IV) o;
									l.setTEN_NHOMTAISAN_CAP_IV(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_IV().update_NHOMTAISAN_CAP_IV(l);
								} else if (o instanceof NHOMTAISAN_CAP_V) {
									NHOMTAISAN_CAP_V l = (NHOMTAISAN_CAP_V) o;
									l.setTEN_NHOMTAISAN_CAP_V(text_Name.getText());
									controler.getControl_NHOMTAISAN_CAP_V().update_NHOMTAISAN_CAP_V(l);
								}
							} else {
								MessageBox m = new MessageBox(getShell(), SWT.NONE);
								m.setMessage("Không để trống Tên nhóm");
								m.open();
							}
							setItem_NhomTaisan(TongItem_NhomTaisan, true);
						}

					}
					setComplete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLu.setText("Hoàn tất");

		Button btnToMiT = new Button(this, SWT.NONE);
		btnToMiT.setImage(SWTResourceManager.getImage(QuanlyNhomtaisan.class, "/import-icon.png"));
		btnToMiT.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ImportExcel_NhomTaisan in = new ImportExcel_NhomTaisan(display, user);
					in.open();
					setItem_NhomTaisan(TongItem_NhomTaisan, true);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnToMiT.setText("Tạo mới từ excel");

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

	protected void setComplete() {
		MODE = 0;
		text_Name.setText("");
		text_Name.setEditable(false);
	}

	protected void setEditMode() {
		MODE = 1;
		text_Name.setEditable(true);
	}

	protected void setInsertMode() {
		MODE = 2;
		text_Name.setText("");
		text_Name.setEditable(true);
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

	private void setItem_NhomTaisan(TreeItem tongItem_NhomTaisan, Boolean expand) throws SQLException {
		tongItem_NhomTaisan.removeAll();
		ArrayList<NHOMTAISAN_CAP_I> lv1_l = controler.getControl_NHOMTAISAN_CAP_I().getAllData();
		if (lv1_l != null)
			for (NHOMTAISAN_CAP_I l_lv1 : lv1_l) {
				TreeItem ti1 = new TreeItem(tongItem_NhomTaisan, SWT.None);
				ti1.setText(l_lv1.getTEN_NHOMTAISAN_CAP_I());
				ti1.setData(l_lv1);
				ArrayList<NHOMTAISAN_CAP_II> lv2_l = controler.getControl_NHOMTAISAN_CAP_II().getAllData();
				if (lv2_l != null)
					for (NHOMTAISAN_CAP_II l_lv2 : lv2_l) {
						if (l_lv1.getMA_NHOMTAISAN_CAP_I() == l_lv2.getMA_NHOMTAISAN_CAP_I()) {
							TreeItem ti2 = new TreeItem(ti1, SWT.None);
							ti2.setText(l_lv2.getTEN_NHOMTAISAN_CAP_II());
							ti2.setData(l_lv2);
							ArrayList<NHOMTAISAN_CAP_III> lv3_l = controler.getControl_NHOMTAISAN_CAP_III()
									.getAllData();
							if (lv3_l != null)
								for (NHOMTAISAN_CAP_III l_lv3 : lv3_l) {
									if (l_lv2.getMA_NHOMTAISAN_CAP_II() == l_lv3.getMA_NHOMTAISAN_CAP_II()) {
										TreeItem ti3 = new TreeItem(ti2, SWT.None);
										ti3.setText(l_lv3.getTEN_NHOMTAISAN_CAP_III());
										ti3.setData(l_lv3);
										ArrayList<NHOMTAISAN_CAP_IV> lv4_l = controler.getControl_NHOMTAISAN_CAP_IV()
												.getAllData();
										if (lv4_l != null)
											for (NHOMTAISAN_CAP_IV l_lv4 : lv4_l) {
												if (l_lv3.getMA_NHOMTAISAN_CAP_III() == l_lv4
														.getMA_NHOMTAISAN_CAP_III()) {
													TreeItem ti4 = new TreeItem(ti3, SWT.None);
													ti4.setText(l_lv4.getTEN_NHOMTAISAN_CAP_IV());
													ti4.setData(l_lv4);
													ArrayList<NHOMTAISAN_CAP_V> lv5_l = controler
															.getControl_NHOMTAISAN_CAP_V().getAllData();
													if (lv5_l != null)
														for (NHOMTAISAN_CAP_V l_lv5 : lv5_l) {
															if (l_lv4.getMA_NHOMTAISAN_CAP_IV() == l_lv5
																	.getMA_NHOMTAISAN_CAP_IV()) {
																TreeItem ti5 = new TreeItem(ti4, SWT.None);
																ti5.setText("(" + l_lv5.getMA_NHOMTAISAN_CAP_V()
																		+ ") - " + l_lv5.getTEN_NHOMTAISAN_CAP_V());
																ti5.setData(l_lv5);
															}
														}
													if (expand)
														ti4.setExpanded(true);
												}
											}
										if (expand)
											ti3.setExpanded(true);
									}
								}
							if (expand)
								ti2.setExpanded(true);
						}
					}
				if (expand)
					ti1.setExpanded(true);
			}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Quản lý Nhóm tài sản");
		setSize(800, 500);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
