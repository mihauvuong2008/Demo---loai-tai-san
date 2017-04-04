package View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
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
import DAO.Kyhan_Baoduong;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class TieuchuanBaoduong extends Dialog {

	protected Object result;
	protected Shell shlTiuChunBo;
	private Table table;
	private Table table_ListHinhthucBaoduong;
	private TableViewer viewer;
	private final Controler controler;
	private boolean EditMode = false;
	private static Log log = LogFactory.getLog(TieuchuanBaoduong.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */

	public TieuchuanBaoduong(Shell parent, int style, NGUOIDUNG user) {
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
		shlTiuChunBo.open();
		shlTiuChunBo.layout();
		Display display = getParent().getDisplay();
		while (!shlTiuChunBo.isDisposed()) {
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
		shlTiuChunBo = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shlTiuChunBo.setImage(
				SWTResourceManager.getImage(TieuchuanBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlTiuChunBo.setSize(728, 450);
		shlTiuChunBo.setText("Ti\u00EAu chu\u1EA9n b\u1EA3o d\u01B0\u1EE1ng");
		shlTiuChunBo.setLayout(new GridLayout(1, false));
		new FormTemplate().setCenterScreen(shlTiuChunBo);
		ToolBar toolBar = new ToolBar(shlTiuChunBo, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		ToolItem tltmSaiK = new ToolItem(toolBar, SWT.NONE);
		tltmSaiK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EditMode = true;
			}
		});
		tltmSaiK.setText("Sửa đổi kỳ hạn");
		tltmSaiK.setImage(
				SWTResourceManager.getImage(TieuchuanBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] til = table.getSelection();
					if (til.length > 0) {
						LOAI_XE r = (LOAI_XE) til[0].getData();
						setKyhanBaoduong(r, getKyhanBaoduong(r.getOTO_XEMAY()));
						controler.getControl_LOAI_XE().update_TieuchuanBaoduong_LOAI_XE(r);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private Kyhan_Baoduong getKyhanBaoduong(int Oto_Xemay) {
				Kyhan_Baoduong result = new Kyhan_Baoduong();
				result.setThayNhot(getThaynhot());
				result.setThayLocNhot(getLocnhot());
				result.setThayLocnhienlieu(getLocnhienlieu());
				result.setThayLocgio(getLocgio());
				result.setThayDauphanh_Daulyhop(getDauphanh_Daulyhop());
				result.setThayDauhopso(getThayDauhopso());
				if (Oto_Xemay == new Fill_ItemData().getInt_Oto()) {
					result.setThayDauVisai(getDauVisai());
					result.setThayDautroluclai(getDautroluclai());
					result.setThayLocgioGianlanh(getLocgioGianlanh());
					result.setBaoduongkhac(getBaoduongkhac());
				}
				return result;
			}

			private int getThaynhot() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(0).getData();
				return htbd.getData();
			}

			private int getLocnhot() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(1).getData();
				return htbd.getData();
			}

			private int getLocgio() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(2).getData();
				return htbd.getData();
			}

			private int getLocnhienlieu() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(3).getData();
				return htbd.getData();
			}

			private int getDauphanh_Daulyhop() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(4).getData();
				return htbd.getData();
			}

			private int getThayDauhopso() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(5).getData();
				return htbd.getData();
			}

			private int getDauVisai() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(6).getData();
				return htbd.getData();
			}

			private int getLocgioGianlanh() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(7).getData();
				return htbd.getData();
			}

			private int getDautroluclai() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(8).getData();
				return htbd.getData();
			}

			private int getBaoduongkhac() {
				HinhthucBaoduong htbd = (HinhthucBaoduong) table_ListHinhthucBaoduong.getItem(9).getData();
				return htbd.getData();
			}
		});
		tltmLu.setText("Lưu");
		tltmLu.setImage(
				SWTResourceManager.getImage(TieuchuanBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

		ToolItem toolItem_2 = new ToolItem(toolBar, SWT.NONE);
		toolItem_2.setText("X\u00F3a");
		toolItem_2.setImage(
				SWTResourceManager.getImage(TieuchuanBaoduong.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

		SashForm sashForm = new SashForm(shlTiuChunBo, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					LOAI_XE lx = (LOAI_XE) til[0].getData();
					fillHinhthucbaoduong(lx);
				}
			}
		});

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("LO\u1EA0I XE");

		TableColumn tblclmnHngSnXut = new TableColumn(table, SWT.NONE);
		tblclmnHngSnXut.setWidth(120);
		tblclmnHngSnXut.setText("H\u00C3NG S\u1EA2N XU\u1EA4T");

		TableColumn tblclmnLoiPhngTin = new TableColumn(table, SWT.NONE);
		tblclmnLoiPhngTin.setWidth(120);
		tblclmnLoiPhngTin.setText("LO\u1EA0I PH\u01AF\u01A0NG TI\u1EC6N");

		viewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(new ArrayContentProvider());

		table_ListHinhthucBaoduong = viewer.getTable();
		table_ListHinhthucBaoduong.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		table_ListHinhthucBaoduong.setHeaderVisible(true);
		table_ListHinhthucBaoduong.setLinesVisible(true);

		final TableCursor cursor = new TableCursor(table_ListHinhthucBaoduong, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		cursor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (cursor.getColumn() == 1) {
					table_ListHinhthucBaoduong.setSelection(new TableItem[] { cursor.getRow() });
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent event) {
				if (isEditMode()) {
					EditAction();
				}
			}

			void EditAction() {
				if (cursor.getColumn() == 1) {
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
								System.out.println(cursor.getColumn());
								cursor.getRow().setText(cursor.getColumn(), text.getText());
								TableItem[] items = table_ListHinhthucBaoduong.getSelection();
								if (items.length > 0) {
									try {
										((HinhthucBaoduong) items[0].getData())
												.setData(Integer.valueOf(text.getText()));
									} catch (Exception e) {
										MessageBox m = new MessageBox(shlTiuChunBo);
										m.setMessage("Lỗi định dạng số");
										m.setText("Lỗi");
										m.open();
										items[0].setText(1, "0");
									}
									updateRow();
								}
							case SWT.ESC:
								text.dispose();
								break;
							}
						}

						private void updateRow() {
							for (TableItem ti : table_ListHinhthucBaoduong.getItems()) {
								HinhthucBaoduong htbd = (HinhthucBaoduong) ti.getData();
								String Name = ti.getText(0);
								ti.setText(new String[] { Name, (htbd.getData() + " ngày") });
							}
						}
					});
					text.addVerifyListener(new VerifyListener() {
						public void verifyText(VerifyEvent e) {
							Text text = (Text) e.getSource();
							final String oldS = text.getText();
							String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

							boolean isFloat = true;
							try {
								Float.parseFloat(newS);
							} catch (NumberFormatException ex) {
								isFloat = false;
							}
							if (!isFloat)
								e.doit = false;
						}
					});
					editor.setEditor(text);
				}
			}
		});

		TableViewerColumn tableViewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		TableColumn tblclmnHinhThucBao = tableViewerColumn.getColumn();
		tblclmnHinhThucBao.setWidth(150);
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				HinhthucBaoduong item = (HinhthucBaoduong) element;
				return item.getTitle();
			}
		});
		tblclmnHinhThucBao.setText("H\u00CCNH TH\u1EE8C B\u1EA2O D\u01AF\u1EE0NG");

		@SuppressWarnings("unused")
		TableViewerColumn tblclmnKyHan = createTableViewerColumn("KỲ HẠN", 100, 1);
		// tblclmnKyHan.setEditingSupport(new KYHANEditingSupport(viewer));
		// tblclmnKyHan.setLabelProvider(new ColumnLabelProvider() {
		// @Override
		// public String getText(Object element) {
		// HinhthucBaoduong htbd = (HinhthucBaoduong) element;
		// return (htbd.getData() + " ngày");
		// }
		// });
		sashForm.setWeights(new int[] { 618, 618 });

		Button btnng = new Button(shlTiuChunBo, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTiuChunBo.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected boolean isEditMode() {
		return EditMode;
	}

	protected void fillHinhthucbaoduong(LOAI_XE lx) {
		@SuppressWarnings("unused")
		TableItem ti;
		int oto_XEMAY = lx.getOTO_XEMAY();
		switch (oto_XEMAY) {
		case 1:// (Oto)
			table_ListHinhthucBaoduong.removeAll();
			ti = CreateItem(1, "Thay nhớt", lx.getTHAY_NHOT());
			ti = CreateItem(2, "Thay Lọc nhớt", lx.getTHAY_LOC_NHOT());
			ti = CreateItem(3, "Thay Lọc gió", lx.getTHAY_LOC_GIO());
			ti = CreateItem(4, "Thay Lọc nhiên liệu", lx.getTHAY_LOC_NHIEN_LIEU());
			ti = CreateItem(5, "Thay Dầu phanh - Dầu ly hợp", lx.getTHAY_DAU_PHANH_DAU_LY_HOP());
			ti = CreateItem(6, "Thay Dầu hộp số", lx.getTHAY_DAU_HOP_SO());
			ti = CreateItem(7, "Thay Dầu vi sai", lx.getTHAY_DAU_VI_SAI());
			ti = CreateItem(8, "Thay Lọc gió giàn lạnh", lx.getTHAY_LOC_GIO_GIAN_LANH());
			ti = CreateItem(9, "Thay Dầu Trợ lực lái", lx.getTHAY_DAU_TRO_LUC_LAI());
			ti = CreateItem(10, "Thay Bảo dưỡng khác", lx.getBAO_DUONG_KHAC());
			break;
		default:
			table_ListHinhthucBaoduong.removeAll();
			ti = CreateItem(11, "Thay nhớt", lx.getTHAY_NHOT());
			ti = CreateItem(12, "Thay Lọc nhớt", lx.getTHAY_LOC_NHOT());
			ti = CreateItem(13, "Thay Lọc gió", lx.getTHAY_LOC_GIO());
			ti = CreateItem(14, "Thay Lọc nhiên liệu", lx.getTHAY_LOC_NHIEN_LIEU());
			ti = CreateItem(15, "Thay Dầu phanh - Dầu ly hợp", lx.getTHAY_DAU_PHANH_DAU_LY_HOP());
			ti = CreateItem(16, "Thay Dầu hộp số", lx.getTHAY_DAU_HOP_SO());
			break;
		}
	}

	private TableItem CreateItem(int index, String Name, int Data) {
		HinhthucBaoduong htbd = new HinhthucBaoduong(index, Name);
		htbd.setData(Data);
		TableItem ti = new TableItem(table_ListHinhthucBaoduong, SWT.NONE);
		ti.setText(new String[] { Name, (Data + " ngày") });
		ti.setData(htbd);
		return ti;
	}

	private void init() throws SQLException {
		loadLoaixe();
	}

	private void loadLoaixe() throws SQLException {
		ArrayList<LOAI_XE> lxl = controler.getControl_LOAI_XE().get_AllLOAI_XE();
		int i = 0;
		for (LOAI_XE lx : lxl) {
			TableItem tbi = new TableItem(table, SWT.NONE);
			Kyhan_Baoduong khbd = controler.getControl_LOAI_XE().getKyhanBaoduong(lx);
			setKyhanBaoduong(lx, khbd);
			tbi.setText(new String[] { i++ + "", lx.getTEN_DONG_XE(), lx.getHANG_SAN_XUAT(),
					new Fill_ItemData().getStringLOAI_PHUONGTIEN_GIAOTHONG(lx.getOTO_XEMAY()) });
			tbi.setData(lx);
		}
	}

	private void setKyhanBaoduong(LOAI_XE r, Kyhan_Baoduong khbd) {
		if (khbd != null) {
			r.setTHAY_NHOT(khbd.getThayNhot());
			r.setTHAY_LOC_NHOT(khbd.getThayLocNhot());
			r.setTHAY_LOC_NHIEN_LIEU(khbd.getThayLocnhienlieu());
			r.setTHAY_LOC_GIO_GIAN_LANH(khbd.getThayLocgioGianlanh());
			r.setTHAY_LOC_GIO(khbd.getThayLocgio());
			r.setTHAY_DAU_VI_SAI(khbd.getThayDauVisai());
			r.setTHAY_DAU_TRO_LUC_LAI(khbd.getThayDautroluclai());
			r.setTHAY_DAU_PHANH_DAU_LY_HOP(khbd.getThayDauphanh_Daulyhop());
			r.setTHAY_DAU_HOP_SO(khbd.getThayDauhopso());
			r.setBAO_DUONG_KHAC(khbd.getBaoduongkhac());
		}
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				HinhthucBaoduong item = (HinhthucBaoduong) element;
				return String.valueOf(item.getData());
			}
		});
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				viewer.getTable().setSortDirection(index);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	public class KYHANEditingSupport extends EditingSupport {

		private ComboBoxCellEditor cellEditor = null;
		int cellData = 0;
		TableViewer viewer;
		ArrayList<Integer> DateValue;

		public KYHANEditingSupport(TableViewer viewer) throws SQLException {
			super(viewer);
			this.viewer = viewer;
			DateValue = controler.getControl_LOAI_XE().get_DateList();
			String[] dataCell = getDataCell(DateValue);
			Text text = new Text(table, SWT.NONE);
			text.setText("Text");
			cellEditor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), dataCell);
		}

		private String[] getDataCell(ArrayList<Integer> DateValue) {
			String[] result = new String[DateValue.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = (DateValue.get(i) + " ngày");
			}
			return result;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return cellEditor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			HinhthucBaoduong Htbd = (HinhthucBaoduong) element;
			return Htbd.getData();
		}

		@Override
		protected void saveCellEditorValue(CellEditor cellEdi, org.eclipse.jface.viewers.ViewerCell cell) {
			System.out.println(cell.getText() + " " + cellEdi.getValue());
			System.out.println(cell.getText());
			cellData = Integer.valueOf(0);
			addDateValue(cellData);
			String[] dataCell = getDataCell(DateValue);
			cellEditor = new ComboBoxCellEditor(((TableViewer) viewer).getTable(), dataCell);
		};

		private void addDateValue(Integer valueOf) {
			boolean fg = true;
			for (Integer integer : DateValue) {
				if (integer == valueOf)
					fg = false;
			}
			if (fg)
				DateValue.add(valueOf);
		}

		@Override
		protected void setValue(Object element, Object value) {
			HinhthucBaoduong pers = (HinhthucBaoduong) element;
			System.out.println(value + " data " + pers.getData());
			if (value instanceof Integer)
				if (((int) value) >= 0) {
					pers.setData(DateValue.get((int) value));
				} else {
					pers.setData(cellData);

				}
			viewer.update(element, null);
		}
	}
}
