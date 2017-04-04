package View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.KY_HAN_THONGKE_XANG_DAU;
import DAO.NGUOIDUNG;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Kyhan_Thongke extends Dialog {

	protected Object result;
	protected Shell shlKHnThng;
	private Table table;
	private int Mode;
	protected ArrayList<KY_HAN_THONGKE_XANG_DAU> insert;
	protected ArrayList<KY_HAN_THONGKE_XANG_DAU> delete;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Kyhan_Thongke(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
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
		shlKHnThng.open();
		shlKHnThng.layout();
		Display display = getParent().getDisplay();
		while (!shlKHnThng.isDisposed()) {
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
		shlKHnThng = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shlKHnThng.setImage(
				SWTResourceManager.getImage(Kyhan_Thongke.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlKHnThng.setSize(665, 410);
		new FormTemplate().setCenterScreen(shlKHnThng);
		shlKHnThng.setText("K\u1EF3 h\u1EA1n th\u1ED1ng k\u00EA ph\u01B0\u01A1ng ti\u1EC7n t\u00E0i s\u1EA3n");
		shlKHnThng.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(shlKHnThng, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThmKHn = new ToolItem(toolBar, SWT.NONE);
		tltmThmKHn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewItem();
				setInsertMode();
			}

			private void AddNewItem() {
				if (!isInsertMode()) {
					setInsertMode();
					TableItem newItem = new TableItem(table, SWT.NONE);
					Date d = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
					String thisday = mdf.getViewStringDate(d);
					newItem.setText(new String[] { "", "tạo mới", "tạo mới", thisday, thisday, "tạo mới" });
					KY_HAN_THONGKE_XANG_DAU r = new KY_HAN_THONGKE_XANG_DAU();
					r.setTEN_KYHAN("tạo mới");
					r.setNGAY_BAT_DAU(d);
					r.setNGAY_KET_THUC(d);
					r.setGHI_CHU("tạo mới");
					newItem.setData(r);
					if (insert == null)
						insert = new ArrayList<>();
					insert.add(r);

				}
			}
		});
		tltmThmKHn.setImage(SWTResourceManager.getImage(Kyhan_Thongke.class, "/add-1-icon (1).png"));
		tltmThmKHn.setText("Th\u00EAm k\u1EF3 h\u1EA1n");

		ToolItem tltmThayiThng = new ToolItem(toolBar, SWT.NONE);
		tltmThayiThng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		tltmThayiThng.setText("Thay \u0111\u1ED5i th\u00F4ng tin");
		tltmThayiThng.setImage(SWTResourceManager.getImage(Kyhan_Thongke.class, "/edit-validated-icon (1).png"));

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((KY_HAN_THONGKE_XANG_DAU) til[0].getData()).getMA_KYHAN() == 0)
							insert = new ArrayList<>();
					}
					if (isEditMode()) {
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				KY_HAN_THONGKE_XANG_DAU r = (KY_HAN_THONGKE_XANG_DAU) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table.remove(table.indexOf(til[0]));
			}
		});
		tltmXa.setText("X\u00F3a");
		tltmXa.setImage(SWTResourceManager.getImage(Kyhan_Thongke.class, "/delete-1-icon (1).png"));

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateItem(getItemUpdate());
					removeItem();
					insertItem();
					setComleteEvent();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void insertItem() throws SQLException {
				if (insert != null) {
					for (KY_HAN_THONGKE_XANG_DAU i : insert) {
						controler.getControl_KY_HAN_THONGKE_XANG_DAU().insert_KY_HAN_THONGKE_XANG_DAU(i);
					}
				}
				insert = new ArrayList<>();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (KY_HAN_THONGKE_XANG_DAU i : delete) {
						controler.getControl_KY_HAN_THONGKE_XANG_DAU().remove_KY_HAN_THONGKE_XANG_DAU(i);
					}
				delete = new ArrayList<>();
			}

			private void updateItem(ArrayList<TableItem> itemUpdate) throws SQLException {
				for (TableItem i : itemUpdate) {
					KY_HAN_THONGKE_XANG_DAU r = (KY_HAN_THONGKE_XANG_DAU) i.getData();
					controler.getControl_KY_HAN_THONGKE_XANG_DAU().update_KY_HAN_THONGKE_XANG_DAU(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table.getItems()) {
					if (i.getData() != null)
						if (((KY_HAN_THONGKE_XANG_DAU) i.getData()).getMA_KYHAN() != 0)
							result.add(i);
				}
				return result;
			}
		});
		tltmLu.setText("L\u01B0u");
		tltmLu.setImage(SWTResourceManager.getImage(Kyhan_Thongke.class, "/Actions-document-save-icon (1).png"));

		table = new Table(shlKHnThng, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});
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
				// thang moi them vao:
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
								try {
									TableItem[] items = table.getSelection();

									Date Ngaybatdau = mdf.getDateFromVIEWString(items[0].getText(3));
									if ((cursor.getColumn() == 3))
										Ngaybatdau = mdf.getDateFromVIEWString(text.getText());
									Date NgayKetthuc = mdf.getDateFromVIEWString(items[0].getText(4));
									if ((cursor.getColumn() == 4))
										NgayKetthuc = mdf.getDateFromVIEWString(text.getText());
									cursor.getRow().setText(cursor.getColumn(), text.getText());
									if (items.length > 0) {
										KY_HAN_THONGKE_XANG_DAU r = (KY_HAN_THONGKE_XANG_DAU) items[0].getData();
										if (r != null) {
											r.setTEN_KYHAN(items[0].getText(2));
											r.setNGAY_BAT_DAU(Ngaybatdau);
											r.setNGAY_KET_THUC(NgayKetthuc);
											r.setGHI_CHU(items[0].getText(5));
											items[0].setData(r);
										}
									}
								} catch (ParseException e) {
									MessageBox m = new MessageBox(shlKHnThng, SWT.ICON_ERROR);
									m.setText("Lỗi dữ liệu nhập");
									m.setMessage("Lỗi định dạng ngày tháng");
									m.open();
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

		TableColumn tblclmnMKHn = new TableColumn(table, SWT.NONE);
		tblclmnMKHn.setWidth(100);
		tblclmnMKHn.setText("M\u00C3 K\u1EF2 H\u1EA0N");

		TableColumn tblclmnTnKHn = new TableColumn(table, SWT.NONE);
		tblclmnTnKHn.setWidth(150);
		tblclmnTnKHn.setText("T\u00CAN K\u1EF2 H\u1EA0N");

		TableColumn tblclmnNgyu = new TableColumn(table, SWT.NONE);
		tblclmnNgyu.setWidth(120);
		tblclmnNgyu.setText("NG\u00C0Y B\u1EAET \u0110\u1EA6U");

		TableColumn tblclmnNgyCuiCng = new TableColumn(table, SWT.NONE);
		tblclmnNgyCuiCng.setWidth(120);
		tblclmnNgyCuiCng.setText("NG\u00C0Y K\u1EBET TH\u00DAC");

		TableColumn tblclmnGhiCh = new TableColumn(table, SWT.NONE);
		tblclmnGhiCh.setWidth(200);
		tblclmnGhiCh.setText("GHI CH\u00DA");

		Button btnng = new Button(shlKHnThng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlKHnThng.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	private void init() throws SQLException {
		table.removeAll();
		ArrayList<KY_HAN_THONGKE_XANG_DAU> khl = controler.getControl_KY_HAN_THONGKE_XANG_DAU().getAllData();
		int i = 1;
		for (KY_HAN_THONGKE_XANG_DAU k : khl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(new String[] { i + "", k.getMA_KYHAN() + "", k.getTEN_KYHAN(),
					mdf.getViewStringDate(k.getNGAY_BAT_DAU()), mdf.getViewStringDate(k.getNGAY_KET_THUC()),
					k.getGHI_CHU() });
			t.setData(k);
			i++;
		}
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

	protected void setComleteEvent() throws SQLException {
		init();
		Mode = 0;
	}

	protected void setEditMode() {
		Mode = 2;
	}

	protected void setInsertMode() {
		Mode = 1;

	}
}
