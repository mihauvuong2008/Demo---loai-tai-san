package View.AssetManagers.Taisan.Phuongtiengiaothong.Loaixe_DinhmucNhienlieu;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

public class Loaixe_DinhmucNhienlieu extends Shell {
	private static NGUOIDUNG user;
	private Table table;
	private int Mode;
	protected ArrayList<LOAI_XE> insert = new ArrayList<>();
	protected ArrayList<LOAI_XE> delete;
	private Button btnOto;
	private Button btnXeMay;
	private final Controler controler;
	private static Log log = LogFactory.getLog(Loaixe_DinhmucNhienlieu.class);

	public ArrayList<LOAI_XE> getInsert() {
		return insert;
	}

	public void setInsert(ArrayList<LOAI_XE> insert) {
		this.insert = insert;
	}

	public void ResetInsert() {
		this.insert = new ArrayList<>();
	}

	public void AppendInsert(ArrayList<LOAI_XE> el) {
		this.insert.addAll(el);
	}

	public void AddInsert(LOAI_XE e) {
		this.insert.add(e);
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Loaixe_DinhmucNhienlieu shell = new Loaixe_DinhmucNhienlieu(display, user);
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
	public Loaixe_DinhmucNhienlieu(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(Loaixe_DinhmucNhienlieu.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(4, false));
		Loaixe_DinhmucNhienlieu.user = user;
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThm = new ToolItem(toolBar, SWT.NONE);
		tltmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isInsertMode()) {
					TableItem newItem = new TableItem(table, SWT.NONE);
					newItem.setText(new String[] { "", "-", "tạo mới", "tạo mới", "10.5" });
					LOAI_XE r = KhoiTaoLoaiXe();
					newItem.setData(r);
					AddInsert(r);

				}
				setInsertMode();
			}

			private LOAI_XE KhoiTaoLoaiXe() {
				LOAI_XE r = new LOAI_XE();
				r.setTEN_DONG_XE("tạo mới");
				r.setHANG_SAN_XUAT("tạo mới");
				Fill_ItemData f = new Fill_ItemData();
				if (btnOto.getSelection()) {
					r.setOTO_XEMAY(f.getInt_Oto());
					r.setDINH_MUC_XANG_DAU(10.5);
				} else if (btnXeMay.getSelection()) {
					r.setOTO_XEMAY(f.getInt_Xemay());
					r.setDINH_MUC_XANG_DAU(3);
				}
				return r;
			}

			private void setInsertMode() {
				Mode = 1;
			}
		});
		tltmThm.setImage(SWTResourceManager.getImage(Loaixe_DinhmucNhienlieu.class, "/add-1-icon (1).png"));
		tltmThm.setText("Th\u00EAm");

		ToolItem tltmSa = new ToolItem(toolBar, SWT.NONE);
		tltmSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}

			private void setEditMode() {
				Mode = 2;
			}
		});
		tltmSa.setText("S\u1EEDa");
		tltmSa.setImage(SWTResourceManager.getImage(Loaixe_DinhmucNhienlieu.class, "/edit-validated-icon (1).png"));

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((LOAI_XE) til[0].getData()).getMA_LOAI_XE() == 0)
							ResetInsert();
					}
					if (isEditMode()) {
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				LOAI_XE r = (LOAI_XE) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table.remove(table.indexOf(til[0]));

			}
		});
		tltmXa.setText("X\u00F3a");
		tltmXa.setImage(SWTResourceManager.getImage(Loaixe_DinhmucNhienlieu.class, "/delete-1-icon (1).png"));

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateItem(getItemUpdate());
					removeItem();
					insertItem();
					setComplete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}

			}

			private void updateItem(ArrayList<TableItem> itemUpdate) throws SQLException {
				for (TableItem i : itemUpdate) {
					LOAI_XE r = (LOAI_XE) i.getData();
					controler.getControl_LOAI_XE().update_LOAI_XE(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table.getItems()) {
					if (i.getData() != null)
						if (((LOAI_XE) i.getData()).getMA_LOAI_XE() != 0)
							result.add(i);
				}
				return result;
			}

			private void insertItem() throws SQLException {
				if (getInsert() != null) {
					for (LOAI_XE i : getInsert()) {
						controler.getControl_LOAI_XE().insert_LOAI_XE(i);
					}
				}
				ResetInsert();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (LOAI_XE i : delete) {
						controler.getControl_LOAI_XE().remove_LOAI_XE(i);
					}
				delete = new ArrayList<>();
			}
		});
		tltmLu.setText("L\u01B0u");
		tltmLu.setImage(
				SWTResourceManager.getImage(Loaixe_DinhmucNhienlieu.class, "/Actions-document-save-icon (1).png"));

		Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		label.setLayoutData(gd_label);

		btnOto = new Button(this, SWT.RADIO);
		btnOto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (isEditMode()) {
						TableItem[] items = table.getSelection();
						if (items.length > 0) {
							LOAI_XE r = (LOAI_XE) items[0].getData();
							if (r != null) {
								Fill_ItemData f = new Fill_ItemData();
								r.setOTO_XEMAY(f.getInt_Oto());
								items[0].setData(r);
							}
						}
					} else {
						init();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnOto.setSelection(true);
		GridData gd_btnOto = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnOto.horizontalIndent = 10;
		btnOto.setLayoutData(gd_btnOto);
		btnOto.setText("Ô tô");

		btnXeMay = new Button(this, SWT.RADIO);
		GridData gd_btnXeMay = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXeMay.horizontalIndent = 10;
		btnXeMay.setLayoutData(gd_btnXeMay);
		btnXeMay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (isEditMode()) {
						TableItem[] items = table.getSelection();
						if (items.length > 0) {
							LOAI_XE r = (LOAI_XE) items[0].getData();
							if (r != null) {
								Fill_ItemData f = new Fill_ItemData();
								r.setOTO_XEMAY(f.getInt_Xemay());
								items[0].setData(r);
							}
						}
					} else {
						init();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXeMay.setText("Xe máy");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);

		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (cursor.getColumn() > 1) {
					table.setSelection(new TableItem[] { cursor.getRow() });
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (isEditMode()) {
					EditAction();
				} else if (isInsertMode()) {
					if (checkCursor_in_NewItem()) {
						EditAction();
					}
				}
			}

			private boolean checkCursor_in_NewItem() {
				if (table.indexOf(cursor.getRow()) == (table.getItemCount() - 1))
					return true;
				return false;
			}

			void EditAction() {
				if (cursor.getColumn() > 1) {
					final Text text = new Text(cursor, SWT.NONE);
					text.setFocus();
					// Copy the text from the cell to the Text control
					text.setText(cursor.getRow().getText(cursor.getColumn()));
					text.setFocus();
					text.selectAll();
					// Add a handler to detect key presses
					text.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent event) {
							switch (event.keyCode) {
							case SWT.CR:
								cursor.getRow().setText(cursor.getColumn(), text.getText());
								TableItem[] items = table.getSelection();
								if (items.length > 0) {
									LOAI_XE r = (LOAI_XE) items[0].getData();
									if (r != null) {
										r.setTEN_DONG_XE(items[0].getText(2));
										r.setHANG_SAN_XUAT(items[0].getText(3));
										try {
											r.setDINH_MUC_XANG_DAU(Double.valueOf(items[0].getText(4)));
										} catch (Exception e) {
											MessageBox m = new MessageBox(getShell());
											m.setMessage("Lỗi định dạng số!");
											m.open();
											items[0].setText(0, "0");
										}
										Fill_ItemData f = new Fill_ItemData();
										if (btnXeMay.getSelection()) {
											r.setOTO_XEMAY(f.getInt_Xemay());
										} else if (btnOto.getSelection()) {
											r.setOTO_XEMAY(f.getInt_Oto());
										}
										items[0].setData(r);
									}
								}
							case SWT.ESC:
								text.dispose();
								break;
							}
						}
					});
					editor.setEditor(text);
				}
			}
		});

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnMLoiXe = new TableColumn(table, SWT.NONE);
		tblclmnMLoiXe.setWidth(100);
		tblclmnMLoiXe.setText("M\u00C3 LO\u1EA0I XE");

		TableColumn tblclmnTnDngXe = new TableColumn(table, SWT.NONE);
		tblclmnTnDngXe.setWidth(100);
		tblclmnTnDngXe.setText("T\u00CAN D\u00D2NG XE");

		TableColumn tblclmnHngXe = new TableColumn(table, SWT.NONE);
		tblclmnHngXe.setWidth(100);
		tblclmnHngXe.setText("H\u00C3NG XE");

		TableColumn tblclmnnhMcNhin = new TableColumn(table, SWT.NONE);
		tblclmnnhMcNhin.setWidth(150);
		tblclmnnhMcNhin.setText("\u0110\u1ECANH M\u1EE8C NHI\u00CAN LI\u1EC6U");
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 4, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		createContents();
		init();
	}

	protected void setComplete() throws SQLException {
		Mode = 0;
		init();
	}

	protected boolean isInsertMode() {
		if (Mode == 1)
			return true;
		return false;
	}

	protected boolean isEditMode() {
		if (Mode == 2)
			return true;
		return false;
	}

	private void init() throws SQLException {
		table.removeAll();
		if (btnOto.getSelection()) {
			loadDataOto();
		} else if (btnXeMay.getSelection()) {
			loadDataXemay();
		}

	}

	private void loadDataXemay() throws SQLException {
		table.removeAll();
		ArrayList<LOAI_XE> lxl = controler.getControl_LOAI_XE().get_AllLOAI_XE_XEMAY();
		int i = 1;
		for (LOAI_XE l : lxl) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(new String[] { i + "", l.getMA_LOAI_XE() + "", l.getTEN_DONG_XE(), l.getHANG_SAN_XUAT(),
					l.getDINH_MUC_XANG_DAU() + "" });
			ti.setData(l);
			i++;
		}
	}

	private void loadDataOto() throws SQLException {
		table.removeAll();
		ArrayList<LOAI_XE> lxl = controler.getControl_LOAI_XE().get_AllLOAI_XE_OTO();
		int i = 1;
		if (lxl != null)
			for (LOAI_XE l : lxl) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { i + "", l.getMA_LOAI_XE() + "", l.getTEN_DONG_XE(), l.getHANG_SAN_XUAT(),
						l.getDINH_MUC_XANG_DAU() + "" });
				ti.setData(l);
				i++;
			}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u0110\u1ECBnh m\u1EE9c x\u0103ng d\u1EA7u");
		setSize(728, 450);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
