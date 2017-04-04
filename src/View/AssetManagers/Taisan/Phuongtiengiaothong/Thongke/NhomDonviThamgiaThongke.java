package View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NHOM_DONVI_THAMGIA_THONGKE;
import View.Template.FormTemplate;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NhomDonviThamgiaThongke extends Dialog {

	protected Object result;
	protected Shell shlQunLNhm;
	private Table table;
	protected int Mode;
	protected int Ma_Kyhan;
	protected ArrayList<NHOM_DONVI_THAMGIA_THONGKE> insert;
	protected ArrayList<NHOM_DONVI_THAMGIA_THONGKE> delete;
	private final Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public NhomDonviThamgiaThongke(Shell parent, int style, int Ma_Kyhan, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.Ma_Kyhan = Ma_Kyhan;
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
		shlQunLNhm.open();
		shlQunLNhm.layout();
		Display display = getParent().getDisplay();
		while (!shlQunLNhm.isDisposed()) {
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
		shlQunLNhm = new Shell(getParent(), getStyle());
		shlQunLNhm.setSize(520, 343);
		shlQunLNhm.setText(
				"Qu\u1EA3n l\u00FD Nh\u00F3m \u0110\u01A1n v\u1ECB tham gia Tham gia th\u1ED1ng k\u00EA Ph\u01B0\u01A1ng ti\u1EC7n giao th\u00F4ng");
		shlQunLNhm.setLayout(new GridLayout(1, false));
		shlQunLNhm.setSize(767, 456);
		new FormTemplate().setCenterScreen(shlQunLNhm);
		ToolBar toolBar = new ToolBar(shlQunLNhm, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThm = new ToolItem(toolBar, SWT.NONE);
		tltmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isInsertMode()) {
					setInsertMode();
					TableItem newItem = new TableItem(table, SWT.NONE);
					newItem.setText(new String[] { "", "tạo mới", "tạo mới" });
					NHOM_DONVI_THAMGIA_THONGKE r = new NHOM_DONVI_THAMGIA_THONGKE();
					r.setTEN_NHOM_DONVI_THAMGIA_THONGKE("tạo mới");
					r.setMA_KYHAN(Ma_Kyhan);
					newItem.setData(r);
					if (insert == null)
						insert = new ArrayList<>();
					insert.add(r);

				}
			}

			private void setInsertMode() {
				Mode = 1;
			}
		});
		tltmThm.setImage(SWTResourceManager.getImage(NhomDonviThamgiaThongke.class, "/add-1-icon (1).png"));
		tltmThm.setText("Th\u00EAm Nh\u00F3m");

		ToolItem tltmSaTn = new ToolItem(toolBar, SWT.NONE);
		tltmSaTn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}

			private void setEditMode() {
				Mode = 2;
			}

		});
		tltmSaTn.setText("S\u1EEDa t\u00EAn");
		tltmSaTn.setImage(SWTResourceManager.getImage(NhomDonviThamgiaThongke.class, "/edit-validated-icon (1).png"));

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((NHOM_DONVI_THAMGIA_THONGKE) til[0].getData()).getMA_NHOM_DONVI_THAMGIA_THONGKE() == 0)
							insert = new ArrayList<>();
					}
					if (isEditMode()) {
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				NHOM_DONVI_THAMGIA_THONGKE r = (NHOM_DONVI_THAMGIA_THONGKE) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table.remove(table.indexOf(til[0]));

			}
		});
		tltmXa.setText("X\u00F3a");
		tltmXa.setImage(SWTResourceManager.getImage(NhomDonviThamgiaThongke.class, "/delete-1-icon (1).png"));

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void updateItem(ArrayList<TableItem> itemUpdate) throws SQLException {
				for (TableItem i : itemUpdate) {
					NHOM_DONVI_THAMGIA_THONGKE r = (NHOM_DONVI_THAMGIA_THONGKE) i.getData();
					controler.getControl_NHOM_DONVI_THAMGIA_THONGKE().update_NHOM_DONVI_THAMGIA_THONGKE(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table.getItems()) {
					if (i.getData() != null)
						if (((NHOM_DONVI_THAMGIA_THONGKE) i.getData()).getMA_NHOM_DONVI_THAMGIA_THONGKE() != 0)
							result.add(i);
				}
				return result;
			}

			private void insertItem() throws SQLException {
				if (insert != null) {
					for (NHOM_DONVI_THAMGIA_THONGKE i : insert) {
						controler.getControl_NHOM_DONVI_THAMGIA_THONGKE().insert_NHOM_DONVI_THAMGIA_THONGKE(i);
					}
				}
				insert = new ArrayList<>();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (NHOM_DONVI_THAMGIA_THONGKE i : delete) {
						controler.getControl_NHOM_DONVI_THAMGIA_THONGKE().remove_NHOM_DONVI_THAMGIA_THONGKE(i);
					}
				delete = new ArrayList<>();
			}
		});
		tltmLu.setText("L\u01B0u");
		tltmLu.setImage(SWTResourceManager.getImage(NhomDonviThamgiaThongke.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));

		table = new Table(shlQunLNhm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				table.setSelection(new TableItem[] { cursor.getRow() });
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
				if (cursor.getColumn() > 0) {
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
									NHOM_DONVI_THAMGIA_THONGKE r = (NHOM_DONVI_THAMGIA_THONGKE) items[0].getData();
									if (r != null) {
										r.setTEN_NHOM_DONVI_THAMGIA_THONGKE(items[0].getText(1));
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

		TableColumn tblclmnTnNhmn = new TableColumn(table, SWT.NONE);
		tblclmnTnNhmn.setWidth(400);
		tblclmnTnNhmn.setText("T\u00CAN NH\u00D3M \u0110\u01A0N V\u1ECA THAM GIA TH\u1ED0NG K\u00CA");

		Button btnng = new Button(shlQunLNhm, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	private void init() throws SQLException {
		table.removeAll();
		ArrayList<NHOM_DONVI_THAMGIA_THONGKE> rl = controler.getControl_NHOM_DONVI_THAMGIA_THONGKE()
				.getAll_NHOM_DONVI_THAMGIA_THONGKE(Ma_Kyhan);
		int i = 1;
		if (rl != null)
			for (NHOM_DONVI_THAMGIA_THONGKE r : rl) {
				TableItem tb = new TableItem(table, SWT.NONE);
				tb.setText(new String[] { i + "", r.getTEN_NHOM_DONVI_THAMGIA_THONGKE() });
				tb.setData(r);
				i++;
			}
	}

	protected void setComplete() throws SQLException {
		Mode = 0;
		init();
	}

	protected boolean isEditMode() {
		if (Mode == 2)
			return true;
		return false;
	}

	protected boolean isInsertMode() {
		if (Mode == 1)
			return true;
		return false;
	}

}
