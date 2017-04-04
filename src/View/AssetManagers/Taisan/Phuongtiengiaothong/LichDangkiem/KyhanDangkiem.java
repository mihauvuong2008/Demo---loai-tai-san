package View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem;

import java.io.File;
import java.io.IOException;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.CHUKY_DANGKIEM;
import DAO.NGUOIDUNG;
import View.AssetManagers.NhapDulieu_Excel.ExcelReader_ImportCHUKY_DANGKIEM;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;

public class KyhanDangkiem extends Dialog {

	protected Object result;
	protected Shell shlKHnng;
	private Table table;
	protected int Mode;
	@SuppressWarnings("unused")
	private static NGUOIDUNG user;
	protected ArrayList<CHUKY_DANGKIEM> insert = new ArrayList<>();
	protected ArrayList<CHUKY_DANGKIEM> delete;
	protected Controler controler;
	protected Log log = LogFactory.getLog(KyhanDangkiem.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public KyhanDangkiem(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		KyhanDangkiem.user = user;
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
		shlKHnng.open();
		shlKHnng.layout();
		Display display = getParent().getDisplay();
		while (!shlKHnng.isDisposed()) {
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
		shlKHnng = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlKHnng.setImage(
				SWTResourceManager.getImage(KyhanDangkiem.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlKHnng.setSize(728, 450);
		new FormTemplate().setCenterScreen(shlKHnng);
		shlKHnng.setText("K\u1EF3 h\u1EA1n \u0110\u0103ng ki\u1EC3m");
		shlKHnng.setLayout(new GridLayout(2, false));

		ToolBar toolBar = new ToolBar(shlKHnng, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

		ToolItem tltmThmMi = new ToolItem(toolBar, SWT.NONE);
		tltmThmMi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isInsertMode()) {
					TableItem newItem = new TableItem(table, SWT.NONE);
					newItem.setText(new String[] { "-", "tạo mới", "180", "180" });
					CHUKY_DANGKIEM r = KhoiTaoCHUKY_DANGKIEM();
					newItem.setData(r);
					AddInsert(r);
				}
				setInsertMode();
			}

			private CHUKY_DANGKIEM KhoiTaoCHUKY_DANGKIEM() {
				CHUKY_DANGKIEM r = new CHUKY_DANGKIEM();
				r.setTEN_KYHAN("tạo mới");
				r.setCHU_KY_DAU(180);
				r.setCHU_KY(180);
				return r;
			}

			private void setInsertMode() {
				Mode = 1;
			}
		});
		tltmThmMi.setText("Thêm mới");

		ToolItem tltmThayi = new ToolItem(toolBar, SWT.NONE);
		tltmThayi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				setEditMode();
			}

			private void setEditMode() {
				Mode = 2;
			}
		});
		tltmThayi.setText("Thay đổi");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((CHUKY_DANGKIEM) til[0].getData()).getMA_KYHAN_DANGKIEM() == 0)
							ResetInsert();
					}
					if (isEditMode()) {
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				CHUKY_DANGKIEM r = (CHUKY_DANGKIEM) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table.remove(table.indexOf(til[0]));

			}
		});
		tltmXa.setText("Xóa");

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
					CHUKY_DANGKIEM r = (CHUKY_DANGKIEM) i.getData();
					controler.getControl_CHUKY_DANGKIEM().update_CHUKY_DANGKIEM(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table.getItems()) {
					if (i.getData() != null)
						if (((CHUKY_DANGKIEM) i.getData()).getMA_KYHAN_DANGKIEM() != 0)
							result.add(i);
				}
				return result;
			}

			private void insertItem() throws SQLException {
				if (getInsert() != null) {
					for (CHUKY_DANGKIEM i : getInsert()) {
						(controler.getControl_CHUKY_DANGKIEM()).insert_CHUKY_DANGKIEM(i);
					}
				}
				ResetInsert();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (CHUKY_DANGKIEM i : delete) {
						controler.getControl_CHUKY_DANGKIEM().remove_CHUKY_DANGKIEM(i);
					}
				delete = new ArrayList<>();
			}
		});
		tltmLu.setText("Lưu");

		table = new Table(shlKHnng, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (cursor.getColumn() >= 1) {
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
				if (cursor.getColumn() > 0) {// from 1
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
									CHUKY_DANGKIEM r = (CHUKY_DANGKIEM) items[0].getData();
									if (r != null) {
										r.setTEN_KYHAN(items[0].getText(1));
										try {
											r.setCHU_KY_DAU(Integer.valueOf(items[0].getText(2)));
											r.setCHU_KY(Integer.valueOf(items[0].getText(3)));
										} catch (Exception e) {
											MessageBox m = new MessageBox(shlKHnng);
											m.setMessage("Lỗi định dạng số!");
											m.open();
											items[0].setText(0, "0");
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

		TableColumn tblclmnLoiXe = new TableColumn(table, SWT.NONE);
		tblclmnLoiXe.setWidth(300);
		tblclmnLoiXe.setText("TÊN CHU KỲ KIỂM ĐỊNH");

		TableColumn tblclmnHngSnXut = new TableColumn(table, SWT.NONE);
		tblclmnHngSnXut.setWidth(150);
		tblclmnHngSnXut.setText("CHU KỲ ĐẦU (ngày)");

		TableColumn tblclmnKHnng = new TableColumn(table, SWT.NONE);
		tblclmnKHnng.setWidth(120);
		tblclmnKHnng.setText("CHU KỲ (ngày)");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.setText("Xóa");

		Button btnNhpTExcel = new Button(shlKHnng, SWT.NONE);
		btnNhpTExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlKHnng, SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Dữ liệu Chu kỳ bảo dưỡng");
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
						buf.append(files[i]);// get file
						try {
							ExcelReader_ImportCHUKY_DANGKIEM eil = new ExcelReader_ImportCHUKY_DANGKIEM(buf.toString());
							ArrayList<CHUKY_DANGKIEM> DanhsachCHUKY_DANGKIEM = eil.getData();
							MessageBox m = new MessageBox(shlKHnng);
							m.setText("Hoàn tất");
							m.setMessage("Đã nhận " + DanhsachCHUKY_DANGKIEM.size() + " dòng dữ liệu");
							m.open();
							for (CHUKY_DANGKIEM ckd : DanhsachCHUKY_DANGKIEM) {
								controler.getControl_CHUKY_DANGKIEM().insert_CHUKY_DANGKIEM(ckd);
							}
							init();
						} catch (BiffException | IOException | SQLException e1) {
							if (e1.getMessage().equals("Unable to recognize OLE stream")) {
								MessageBox m = new MessageBox(shlKHnng, SWT.ERROR);
								m.setMessage("Hỗ trợ File Excel cho phiên bản Excel 2000 về trước");
								m.open();
							}
						}
					}
				}
			}
		});
		btnNhpTExcel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnNhpTExcel.setText("Nhập từ Excel");

		Button btnng = new Button(shlKHnng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shlKHnng.dispose();
			}
		});

		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

		init();

	}

	public ArrayList<CHUKY_DANGKIEM> getInsert() {
		return insert;
	}

	private void init() throws SQLException {
		loadData();
	}

	private void loadData() throws SQLException {
		table.removeAll();
		ArrayList<CHUKY_DANGKIEM> lxl = controler.getControl_CHUKY_DANGKIEM().get_AllCHUKY_DANGKIEM();
		int i = 1;
		if (lxl != null)
			for (CHUKY_DANGKIEM l : lxl) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { i + "", l.getTEN_KYHAN() + "", l.getCHU_KY_DAU() + "", l.getCHU_KY() + "" });
				ti.setData(l);
				i++;
			}
	}

	public void ResetInsert() {
		insert = new ArrayList<>();
	}

	protected void AddInsert(CHUKY_DANGKIEM r) {
		this.insert.add(r);
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

}
