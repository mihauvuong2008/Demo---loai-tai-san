package View.AssetManagers.Role;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.ROLE;
import View.Template.FormTemplate;

public class QuanlyQuyenhan extends Shell {
	protected static int Mode = 0;
	private Table table;
	private ArrayList<ROLE> delete;
	private ArrayList<ROLE> insert;
	private static NGUOIDUNG user;
	private Button btnThmNgiDng;
	private Button btnXemThngTinND;
	private Button btnCpQuyn;
	private Button btnXaNgiDng;
	private Button btnThmThngTinPTTS;
	private Button btnXemThngTin_PTTS;
	private Button btnCpNhtThng_PTTS;
	private Button btnXaPtts;
	private Button btnThmCngVic;
	private Button btnXemThngTin_CV;
	private Button btnCpNhtThng_CV;
	private Button btnXaCngVic;
	private Button btnTaoHS;
	private Button btnXemHS;
	private Button btnCpNhtThng_HS;
	private Button btnXaHS;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyQuyenhan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanlyQuyenhan shell = new QuanlyQuyenhan(display, user);
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
	public QuanlyQuyenhan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanlyQuyenhan.class, "/rules-icon.png"));
		setLayout(new GridLayout(1, false));
		QuanlyQuyenhan.user = user;
		controler = new Controler(user);

		ToolBar toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThm = new ToolItem(toolBar, SWT.NONE);
		tltmThm.setImage(SWTResourceManager.getImage(QuanlyQuyenhan.class, "/add-1-icon (1).png"));
		tltmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isInsertMode()) {
					setInsertMode();
					TableItem newItem = new TableItem(table, SWT.NONE);
					newItem.setText(new String[] { "", "tạo mới", "tạo mới" });
					ROLE r = new ROLE();
					r.setTEN_QUYEN("tạo mới");
					r.setMO_TA("tạo mới");
					newItem.setData(r);
					if (insert == null)
						insert = new ArrayList<>();
					insert.add(r);

				}
			}

			private void setInsertMode() {
				Mode = 1;
				enableCheckbox();
			}
		});
		tltmThm.setText("Th\u00EAm");

		ToolItem tltmThayi = new ToolItem(toolBar, SWT.NONE);
		tltmThayi.setImage(SWTResourceManager.getImage(QuanlyQuyenhan.class, "/edit-validated-icon (1).png"));
		tltmThayi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}

			private void setEditMode() {
				Mode = 2;
				enableCheckbox();
			}

		});
		tltmThayi.setText("Thay \u0111\u1ED5i");

		ToolItem tltmXa = new ToolItem(toolBar, SWT.NONE);
		tltmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					if (isInsertMode()) {
						if (((ROLE) til[0].getData()).getMA_QUYEN() == 0)
							insert = new ArrayList<>();
					}
					if (isEditMode()) {
						disableCheckBox();
						Mode = 0;
					}
					removeTableItem(til);
				}
			}

			private void removeTableItem(TableItem[] til) {
				ROLE r = (ROLE) til[0].getData();
				if (delete == null)
					delete = new ArrayList<>();
				delete.add(r);
				table.remove(table.indexOf(til[0]));

			}
		});
		tltmXa.setImage(SWTResourceManager.getImage(QuanlyQuyenhan.class, "/delete-1-icon (1).png"));
		tltmXa.setText("X\u00F3a");

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.setImage(SWTResourceManager.getImage(QuanlyQuyenhan.class, "/Actions-document-save-icon (1).png"));
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
					ROLE r = (ROLE) i.getData();
					controler.getControl_Role().update_ROLE(r);
				}
			}

			private ArrayList<TableItem> getItemUpdate() {
				ArrayList<TableItem> result = new ArrayList<>();
				for (TableItem i : table.getItems()) {
					if (i.getData() != null)
						if (((ROLE) i.getData()).getMA_QUYEN() != 0)
							result.add(i);
				}
				return result;
			}

			private void insertItem() throws SQLException {
				if (insert != null) {
					for (ROLE i : insert) {
						controler.getControl_Role().insert_ROLE(i);
					}
				}
				insert = new ArrayList<>();
			}

			private void removeItem() throws SQLException {
				if (delete != null)
					for (ROLE i : delete) {
						controler.getControl_Role().remove_ROLE(i);
					}
				delete = new ArrayList<>();
			}

		});
		tltmLu.setText("L\u01B0u");

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(4, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpNgiDng = new Group(composite, SWT.NONE);
		grpNgiDng.setLayout(new GridLayout(1, false));
		grpNgiDng.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpNgiDng.setText("D\u1EEF li\u1EC7u Ng\u01B0\u1EDDi d\u00F9ng");

		btnThmNgiDng = new Button(grpNgiDng, SWT.CHECK);
		btnThmNgiDng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnThmNgiDng.setEnabled(false);
		btnThmNgiDng.setText("Th\u00EAm ng\u01B0\u1EDDi d\u00F9ng");

		btnXemThngTinND = new Button(grpNgiDng, SWT.CHECK);
		btnXemThngTinND.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXemThngTinND.setEnabled(false);
		btnXemThngTinND.setText("Xem Th\u00F4ng tin ng\u01B0\u1EDDi d\u00F9ng");

		btnCpQuyn = new Button(grpNgiDng, SWT.CHECK);
		btnCpQuyn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnCpQuyn.setEnabled(false);
		btnCpQuyn.setText("C\u1EA5p quy\u1EC1n");

		btnXaNgiDng = new Button(grpNgiDng, SWT.CHECK);
		btnXaNgiDng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXaNgiDng.setEnabled(false);
		btnXaNgiDng.setText("X\u00F3a ng\u01B0\u1EDDi d\u00F9ng");

		Group grpDLiuTi = new Group(composite, SWT.NONE);
		grpDLiuTi.setLayout(new GridLayout(1, false));
		grpDLiuTi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpDLiuTi.setText("D\u1EEF li\u1EC7u T\u00E0i s\u1EA3n");

		btnThmThngTinPTTS = new Button(grpDLiuTi, SWT.CHECK);
		btnThmThngTinPTTS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnThmThngTinPTTS.setEnabled(false);
		btnThmThngTinPTTS.setText("Th\u00EAm PTTS");

		btnXemThngTin_PTTS = new Button(grpDLiuTi, SWT.CHECK);
		btnXemThngTin_PTTS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXemThngTin_PTTS.setEnabled(false);
		btnXemThngTin_PTTS.setText("Xem th\u00F4ng tin PTTS");

		btnCpNhtThng_PTTS = new Button(grpDLiuTi, SWT.CHECK);
		btnCpNhtThng_PTTS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnCpNhtThng_PTTS.setEnabled(false);
		btnCpNhtThng_PTTS.setText("Cập nhật thông tin PTTS");

		btnXaPtts = new Button(grpDLiuTi, SWT.CHECK);
		btnXaPtts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXaPtts.setEnabled(false);
		btnXaPtts.setText("X\u00F3a PTTS");

		Group grpDLiuCng = new Group(composite, SWT.NONE);
		grpDLiuCng.setLayout(new GridLayout(1, false));
		grpDLiuCng.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpDLiuCng.setText("D\u1EEF li\u1EC7u C\u00F4ng vi\u1EC7c");

		btnThmCngVic = new Button(grpDLiuCng, SWT.CHECK);
		btnThmCngVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnThmCngVic.setEnabled(false);
		btnThmCngVic.setText("Th\u00EAm C\u00F4ng vi\u1EC7c");

		btnXemThngTin_CV = new Button(grpDLiuCng, SWT.CHECK);
		btnXemThngTin_CV.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXemThngTin_CV.setEnabled(false);
		btnXemThngTin_CV.setText("Xem Th\u00F4ng tin C\u00F4ng vi\u1EC7c");

		btnCpNhtThng_CV = new Button(grpDLiuCng, SWT.CHECK);
		btnCpNhtThng_CV.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnCpNhtThng_CV.setEnabled(false);
		btnCpNhtThng_CV.setText("C\u1EADp nh\u1EADt th\u00F4ng tin C\u00F4ng vi\u1EC7c");

		btnXaCngVic = new Button(grpDLiuCng, SWT.CHECK);
		btnXaCngVic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXaCngVic.setEnabled(false);
		btnXaCngVic.setText("X\u00F3a C\u00F4ng vi\u1EC7c");

		Group grpDLiuH = new Group(composite, SWT.NONE);
		grpDLiuH.setLayout(new GridLayout(1, false));
		grpDLiuH.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpDLiuH.setText("D\u1EEF li\u1EC7u H\u1ED3 s\u01A1");

		btnTaoHS = new Button(grpDLiuH, SWT.CHECK);
		btnTaoHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnTaoHS.setEnabled(false);
		btnTaoHS.setText("T\u1EA1o h\u1ED3 s\u01A1");

		btnXemHS = new Button(grpDLiuH, SWT.CHECK);
		btnXemHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXemHS.setEnabled(false);
		btnXemHS.setText("Xem th\u00F4ng tin H\u1ED3 s\u01A1");

		btnCpNhtThng_HS = new Button(grpDLiuH, SWT.CHECK);
		btnCpNhtThng_HS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnCpNhtThng_HS.setEnabled(false);
		btnCpNhtThng_HS.setText("C\u1EADp nh\u1EADt th\u00F4ng tin H\u1ED3 s\u01A1");

		btnXaHS = new Button(grpDLiuH, SWT.CHECK);
		btnXaHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SetDataRow_CheckBox();
			}
		});
		btnXaHS.setEnabled(false);
		btnXaHS.setText("X\u00F3a H\u1ED3 s\u01A1");

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table.getSelection();

				if (items.length > 0) {
					ROLE r = (ROLE) items[0].getData();
					if (r != null)
						getDataCheckBox(r);
				}
			}
		});

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnQuynHn = new TableColumn(table, SWT.NONE);
		tblclmnTnQuynHn.setWidth(150);
		tblclmnTnQuynHn.setText("T\u00CAN QUY\u1EC0N H\u1EA0N");

		TableColumn tblclmnMT = new TableColumn(table, SWT.NONE);
		tblclmnMT.setWidth(300);
		tblclmnMT.setText("M\u00D4 T\u1EA2");
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
									ROLE r = (ROLE) items[0].getData();
									if (r != null) {
										r.setTEN_QUYEN(items[0].getText(1));
										r.setMO_TA(items[0].getText(2));
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

		Button btnng = new Button(this, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

		createContents();
		loadData();
	}

	private void setComplete() throws SQLException {
		Mode = 0;
		disableCheckBox();
		loadData();
	}

	private void disableCheckBox() {
		btnThmNgiDng.setEnabled(false);
		btnXemThngTinND.setEnabled(false);
		btnCpQuyn.setEnabled(false);
		btnXaNgiDng.setEnabled(false);
		btnThmThngTinPTTS.setEnabled(false);
		btnXemThngTin_PTTS.setEnabled(false);
		btnCpNhtThng_PTTS.setEnabled(false);
		btnXaPtts.setEnabled(false);
		btnThmCngVic.setEnabled(false);
		btnXemThngTin_CV.setEnabled(false);
		btnCpNhtThng_CV.setEnabled(false);
		btnXaCngVic.setEnabled(false);
		btnTaoHS.setEnabled(false);
		btnXemHS.setEnabled(false);
		btnCpNhtThng_HS.setEnabled(false);
		btnXaHS.setEnabled(false);
	}

	private void enableCheckbox() {
		btnThmNgiDng.setEnabled(true);
		btnXemThngTinND.setEnabled(true);
		btnCpQuyn.setEnabled(true);
		btnXaNgiDng.setEnabled(true);
		btnThmThngTinPTTS.setEnabled(true);
		btnXemThngTin_PTTS.setEnabled(true);
		btnCpNhtThng_PTTS.setEnabled(true);
		btnXaPtts.setEnabled(true);
		btnThmCngVic.setEnabled(true);
		btnXemThngTin_CV.setEnabled(true);
		btnCpNhtThng_CV.setEnabled(true);
		btnXaCngVic.setEnabled(true);
		btnTaoHS.setEnabled(true);
		btnXemHS.setEnabled(true);
		btnCpNhtThng_HS.setEnabled(true);
		btnXaHS.setEnabled(true);
	}

	protected void SetDataRow_CheckBox() {
		TableItem[] til = table.getSelection();
		if (til.length > 0) {
			ROLE r = (ROLE) til[0].getData();
			setDataCheckbox(r);
			til[0].setData(r);
		}
	}

	protected void getDataCheckBox(ROLE r) {

		if (r.getTHEM_NGUOIDUNG() == 1) {
			btnThmNgiDng.setSelection(true);
		} else {
			btnThmNgiDng.setSelection(false);
		}
		if (r.getXEM_THONGTIN_NGUOIDUNG() == 1) {
			btnXemThngTinND.setSelection(true);
		} else {
			btnXemThngTinND.setSelection(false);
		}
		if (r.getPHAN_QUYEN_NGUOIDUNG() == 1) {
			btnCpQuyn.setSelection(true);
		} else {
			btnCpQuyn.setSelection(false);
		}
		if (r.getXOA_NGUOIDUNG() == 1) {
			btnXaNgiDng.setSelection(true);
		} else {
			btnXaNgiDng.setSelection(false);
		}
		if (r.getTHEM_THONGTIN_TAISAN() == 1) {
			btnThmThngTinPTTS.setSelection(true);
		} else {
			btnThmThngTinPTTS.setSelection(false);
		}
		if (r.getXEM_THONGTIN_TAISAN() == 1) {
			btnXemThngTin_PTTS.setSelection(true);
		} else {
			btnXemThngTin_PTTS.setSelection(false);
		}
		if (r.getSUA_THONGTIN_TAISAN() == 1) {
			btnCpNhtThng_PTTS.setSelection(true);
		} else {
			btnCpNhtThng_PTTS.setSelection(false);
		}
		if (r.getXOA_THONGTIN_TAISAN() == 1) {
			btnXaPtts.setSelection(true);
		} else {
			btnXaPtts.setSelection(false);
		}
		if (r.getTHEM_CONGVIEC() == 1) {
			btnThmCngVic.setSelection(true);
		} else {
			btnThmCngVic.setSelection(false);
		}
		if (r.getXEM_THONGTIN_CONGVIEC() == 1) {
			btnXemThngTin_CV.setSelection(true);
		} else {
			btnXemThngTin_CV.setSelection(false);
		}
		if (r.getSUA_THONGTIN_CONGVIEC() == 1) {
			btnCpNhtThng_CV.setSelection(true);
		} else {
			btnCpNhtThng_CV.setSelection(false);
		}
		if (r.getXOA_CONGVIEC() == 1) {
			btnXaCngVic.setSelection(true);
		} else {
			btnXaCngVic.setSelection(false);
		}
		if (r.getTHEM_HOSO() == 1) {
			btnTaoHS.setSelection(true);
		} else {
			btnTaoHS.setSelection(false);
		}
		if (r.getXEM_THONGTIN_HOSO() == 1) {
			btnXemHS.setSelection(true);
		} else {
			btnXemHS.setSelection(false);
		}
		if (r.getSUA_THONGTIN_HOSO() == 1) {
			btnCpNhtThng_HS.setSelection(true);
		} else {
			btnCpNhtThng_HS.setSelection(false);
		}
		if (r.getXOA_HOSO() == 1) {
			btnXaHS.setSelection(true);
		} else {
			btnXaHS.setSelection(false);
		}
	}

	protected void setDataCheckbox(ROLE r) {
		r.setTHEM_NGUOIDUNG(btnThmNgiDng.getSelection() == true ? 1 : 0);
		r.setXEM_THONGTIN_NGUOIDUNG(btnXemThngTinND.getSelection() == true ? 1 : 0);
		r.setPHAN_QUYEN_NGUOIDUNG(btnCpQuyn.getSelection() == true ? 1 : 0);
		r.setXOA_NGUOIDUNG(btnXaNgiDng.getSelection() == true ? 1 : 0);
		r.setTHEM_THONGTIN_TAISAN(btnThmThngTinPTTS.getSelection() == true ? 1 : 0);
		r.setXEM_THONGTIN_TAISAN(btnXemThngTin_PTTS.getSelection() == true ? 1 : 0);
		r.setSUA_THONGTIN_TAISAN(btnCpNhtThng_PTTS.getSelection() == true ? 1 : 0);
		r.setXOA_THONGTIN_TAISAN(btnXaPtts.getSelection() == true ? 1 : 0);
		r.setTHEM_CONGVIEC(btnThmCngVic.getSelection() == true ? 1 : 0);
		r.setXEM_THONGTIN_CONGVIEC(btnXemThngTin_CV.getSelection() == true ? 1 : 0);
		r.setSUA_THONGTIN_CONGVIEC(btnCpNhtThng_CV.getSelection() == true ? 1 : 0);
		r.setXOA_CONGVIEC(btnXaCngVic.getSelection() == true ? 1 : 0);
		r.setTHEM_HOSO(btnTaoHS.getSelection() == true ? 1 : 0);
		r.setXEM_THONGTIN_HOSO(btnXemHS.getSelection() == true ? 1 : 0);
		r.setSUA_THONGTIN_HOSO(btnCpNhtThng_HS.getSelection() == true ? 1 : 0);
		r.setXOA_HOSO(btnXaHS.getSelection() == true ? 1 : 0);
	}

	private boolean isInsertMode() {
		if (Mode == 1)
			return true;
		return false;
	}

	private boolean isEditMode() {
		if (Mode == 2)
			return true;
		return false;
	}

	private void loadData() throws SQLException {
		table.removeAll();
		ArrayList<ROLE> rl = controler.getControl_Role().getAll_ROLE();
		int i = 1;
		if (rl != null)
			for (ROLE r : rl) {
				TableItem tb = new TableItem(table, SWT.NONE);
				tb.setText(new String[] { i + "", r.getTEN_QUYEN(), r.getMO_TA() });
				tb.setData(r);
				i++;
			}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Qu\u1EA3n l\u00FD quy\u1EC1n h\u1EA1n");
		setSize(728, 450);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
