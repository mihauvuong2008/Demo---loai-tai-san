package View.AssetManagers.NguonTang;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class QuanLy_NguonTang extends Shell {
	private Table table;
	private String ItemIndex;
	private int ItemHeight = 22;
	private static NGUOIDUNG user;
	private Text text_1;
	private Button btnThmNgunTng;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanLy_NguonTang.class);
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtMadonvi;
	private Text txtTendonvi;
	private Text txtLienhe;
	private Text txtGioithieu;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanLy_NguonTang shell = new QuanLy_NguonTang(display, user);
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
	public QuanLy_NguonTang(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM | SWT.BORDER);
		setImage(user.getIcondata().phukienIcon);
		setLayout(new GridLayout(5, false));
		QuanLy_NguonTang.user = user;
		controler = new Controler(user);

		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table.getSelection();
				if (items.length > 0) {
					NGUONTANG t = (NGUONTANG) items[0].getData();
					if (t != null) {

						ItemIndex = items[0].getText(0);
						txtMadonvi.setText(String.valueOf(t.getMA_NGUONTANG()));
						txtTendonvi.setText(t.getTEN_NGUONTANG());
						txtLienhe.setText(t.getLIEN_HE());
						txtGioithieu.setText(t.getGIOI_THIEU());

					}
				}
			}
		});
		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(50);
		tblclmnStt.setText("STT");

		TableColumn tblclmnMNgunTng = new TableColumn(table, SWT.NONE);
		tblclmnMNgunTng.setWidth(111);
		tblclmnMNgunTng.setText("MÃ NGUỒN TĂNG");

		TableColumn tblclmnTnNgunTng = new TableColumn(table, SWT.NONE);
		tblclmnTnNgunTng.setWidth(120);
		tblclmnTnNgunTng.setText("TÊN NGUỒN TĂNG");

		TableColumn tblclmnLinH = new TableColumn(table, SWT.NONE);
		tblclmnLinH.setWidth(100);
		tblclmnLinH.setText("LIÊN HỆ");
		new TreeRowStyle().setTableItemHeight(table, ItemHeight);

		Composite composite_1 = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		{
			TableWrapLayout twl_composite_1 = new TableWrapLayout();
			twl_composite_1.numColumns = 2;
			composite_1.setLayout(twl_composite_1);
		}

		Label lblMnV = formToolkit.createLabel(composite_1, "Mã đơn vị: ", SWT.NONE);
		lblMnV.setBounds(0, 0, 55, 15);

		txtMadonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtMadonvi.setText("");
		txtMadonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblTnnV = formToolkit.createLabel(composite_1, "Tên đơn vị: ", SWT.NONE);

		txtTendonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtTendonvi.setText("");
		txtTendonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblLinH = formToolkit.createLabel(composite_1, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite_1, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);

		@SuppressWarnings("unused")
		Label lblGiiThiu = formToolkit.createLabel(composite_1, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite_1, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtGioithieu.setText("");
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);
		sashForm.setWeights(new int[] { 225, 146 });
		Filltable();

		btnThmNgunTng = new Button(this, SWT.NONE);
		btnThmNgunTng.setImage(user.getIcondata().addIcon);
		btnThmNgunTng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCreate();
			}
		});
		btnThmNgunTng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnThmNgunTng.setText("Thêm mới");

		Button btnSa_1 = new Button(this, SWT.NONE);
		btnSa_1.setImage(user.getIcondata().editIcon);
		btnSa_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEdit();
			}
		});
		GridData gd_btnSa_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSa_1.widthHint = 75;
		btnSa_1.setLayoutData(gd_btnSa_1);
		btnSa_1.setText("Sửa");

		Button btnXoa = new Button(this, SWT.NONE);
		btnXoa.setImage(user.getIcondata().deleteIcon);
		btnXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemarr = table.getSelection();
					if (itemarr != null) {
						for (TableItem ti : itemarr) {
							NGUONTANG nt = (NGUONTANG) ti.getData();
							controler.getControl_NGUONTANG().deteleNGUONTANG(nt);
						}
						MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
						m.setText("Xóa hoàn tất");
						m.setMessage("Xóa hoàn tất");
						m.open();
						Filltable();

						txtMadonvi.setText("");
						txtTendonvi.setText("");
						txtLienhe.setText("");
						txtGioithieu.setText("");
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXoa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXoa.widthHint = 75;
		btnXoa.setLayoutData(gd_btnXoa);
		btnXoa.setText("Xóa");

		Button btnSa = new Button(this, SWT.NONE);
		btnSa.setImage(user.getIcondata().saveIcon);
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// flag2 = true: quá trình sửa thông tin hồ sơ
				try {
					if (getCreate() || getEdit())
						if ((!txtTendonvi.getText().equals("")) && (!txtLienhe.getText().equals(""))) {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
							if (getEdit()) {
								NGUONTANG nt = new NGUONTANG();
								nt.setMA_NGUONTANG(Integer.valueOf(txtMadonvi.getText()));
								nt.setTEN_NGUONTANG(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONTANG().update_NGUONTANG(nt);
								m.setMessage("Lưu hoàn tất!");
								m.setText("Hoàn tất");
								m.open();
							} else if (getCreate()) {
								NGUONTANG nt = new NGUONTANG();
								nt.setTEN_NGUONTANG(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONTANG().Insert_NGUONTANG(nt);
								m.setMessage("Tạo mới hoàn tất!");
								m.setText("Hoàn tất");
								m.open();
							}
						} else {
							MessageBox m = new MessageBox(getShell(), SWT.ICON_ERROR);
							m.setText("lỗi");
							m.setMessage("Tên, Liên hệ không để trống!");
							m.open();
						}
					disableText();
					setComplete();
					Filltable();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnSa = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_btnSa.widthHint = 75;
		btnSa.setLayoutData(gd_btnSa);
		btnSa.setText("Lưu");

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
		btnng.setText("Đóng");
		createContents();
		disableText();
	}

	protected void setComplete() {
		mode = 0;
	}

	protected boolean getCreate() {
		if (mode == 1)
			return true;
		return false;
	}

	protected boolean getEdit() {
		if (mode == 2)
			return true;
		return false;
	}

	protected void setEdit() {
		EnableText();
		mode = 2;
	}

	protected void setCreate() {
		EnableText();
		resetText();
		mode = 1;

	}

	private void resetText() {
		txtMadonvi.setText("");
		txtTendonvi.setText("");
		txtLienhe.setText("");
		txtGioithieu.setText("");
	}

	protected void disableText() {
		txtMadonvi.setEditable(false);
		txtTendonvi.setEditable(false);
		txtLienhe.setEditable(false);
		txtGioithieu.setEditable(false);
	}

	private void EnableText() {
		txtMadonvi.setEditable(true);
		txtTendonvi.setEditable(true);
		txtLienhe.setEditable(true);
		txtGioithieu.setEditable(true);

	}

	private void Filltable() throws SQLException {
		table.removeAll();
		ArrayList<NGUONTANG> ntl = controler.getControl_NGUONTANG().get_All_NguonTangTaisan(text_1.getText());
		int x = 1;
		for (NGUONTANG nt : ntl) {
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(0, "" + x);
			t.setText(1, String.valueOf(nt.getMA_NGUONTANG()));
			t.setText(2, nt.getTEN_NGUONTANG());
			t.setText(3, nt.getLIEN_HE());
			t.setData(nt);
			x++;
		}
		for (TableColumn t : table.getColumns()) {
			t.pack();
		}
		if (ItemIndex != null) {
			int index = Integer.valueOf(ItemIndex);
			if (index > 0 && index < table.getItemCount()) {
				table.setSelection(index - 1);
			}
		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Quản lý nguồn tăng");
		setSize(650, 400);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
