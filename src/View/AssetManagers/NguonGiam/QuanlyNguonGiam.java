package View.AssetManagers.NguonGiam;

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
import org.eclipse.swt.widgets.Dialog;
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
import DAO.NGUONGIAM;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class QuanlyNguonGiam extends Dialog {

	protected Object result;
	protected Shell shlQunLNgun;
	private Text text;
	private Table table;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyNguonGiam.class);
	private NGUOIDUNG user;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtMadonvi;
	private Text txtGioithieu;
	private Text txtLienhe;
	private Text txtTendonvi;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public QuanlyNguonGiam(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.user = user;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlQunLNgun.open();
		shlQunLNgun.layout();
		Display display = getParent().getDisplay();
		while (!shlQunLNgun.isDisposed()) {
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
		shlQunLNgun = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlQunLNgun.setImage(user.getIcondata().ThanhlyIcon);
		shlQunLNgun.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlQunLNgun);
		shlQunLNgun.setText("Quản lý Nguồn giảm");
		shlQunLNgun.setLayout(new GridLayout(5, false));

		text = new Text(shlQunLNgun, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));

		SashForm sashForm = new SashForm(shlQunLNgun, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table.getSelection();
				if (items.length > 0) {
					NGUONGIAM ng = (NGUONGIAM) items[0].getData();
					fillText(ng);
				}
			}

			private void fillText(NGUONGIAM ng) {
				txtMadonvi.setText(ng.getMA_NGUONGIAM() + "");
				txtTendonvi.setText(ng.getTEN_NGUONGIAM());
				txtGioithieu.setText(ng.getGIOI_THIEU());
				txtLienhe.setText(ng.getLIEN_HE());
			}
		});

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("T\u00CAN NGU\u1ED2N GI\u1EA2M");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("GI\u1EDAI THI\u1EC6U");

		Composite composite_1 = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		{
			TableWrapLayout twl_composite_1 = new TableWrapLayout();
			twl_composite_1.numColumns = 2;
			composite_1.setLayout(twl_composite_1);
		}

		Label lblMnV = formToolkit.createLabel(composite_1, "Mã đơn vị: ", SWT.NONE);
		lblMnV.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 1));
		lblMnV.setBounds(0, 0, 55, 15);

		txtMadonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtMadonvi.setText("");
		txtMadonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		Label lblTnnV = formToolkit.createLabel(composite_1, "Tên đơn vị: ", SWT.NONE);
		lblTnnV.setLayoutData(new TableWrapData(TableWrapData.LEFT, TableWrapData.MIDDLE, 1, 1));

		txtTendonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtTendonvi.setText("");
		txtTendonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblGiiThiu = formToolkit.createLabel(composite_1, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite_1, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtGioithieu.setText("");
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);

		@SuppressWarnings("unused")
		Label lblLinH = formToolkit.createLabel(composite_1, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite_1, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);
		sashForm.setWeights(new int[] { 440, 270 });

		Button button = new Button(shlQunLNgun, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCreate();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Thêm");
		button.setImage(user.getIcondata().addIcon);

		Button button_1 = new Button(shlQunLNgun, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEdit();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Sửa");
		button_1.setImage(user.getIcondata().editIcon);

		Button button_3 = new Button(shlQunLNgun, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (getEdit() || getCreate())
						if ((!txtTendonvi.getText().equals("")) && (!txtLienhe.getText().equals(""))) {
							MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_WORKING);
							if (getEdit()) {
								NGUONGIAM nt = new NGUONGIAM();
								nt.setMA_NGUONGIAM(Integer.valueOf(txtMadonvi.getText()));
								nt.setTEN_NGUONGIAM(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONGIAM().update_NGUONGIAM(nt);
								m.setMessage("Lưu hoàn tất!");
								m.setText("Hoàn tất");
								m.open();
							} else if (getCreate()) {
								NGUONGIAM nt = new NGUONGIAM();
								nt.setTEN_NGUONGIAM(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONGIAM().Insert_NGUONGIAM(nt);
								m.setMessage("Tạo mới hoàn tất!");
								m.setText("Hoàn tất");
								m.open();
							}
						} else {
							MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_ERROR);
							m.setText("lỗi");
							m.setMessage("Tên, Liên hệ không để trống!");
							m.open();
						}
					disableText();
					setComplete();
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_3.widthHint = 75;
		button_3.setLayoutData(gd_button_3);
		button_3.setText("Lưu");
		button_3.setImage(user.getIcondata().saveIcon);

		Button button_2 = new Button(shlQunLNgun, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemarr = table.getSelection();
					if (itemarr != null) {
						for (TableItem ti : itemarr) {
							NGUONGIAM nt = (NGUONGIAM) ti.getData();
							controler.getControl_NGUONGIAM().delete_NGUONGIAM(nt);
						}
						MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_WORKING);
						m.setText("Xóa hoàn tất");
						m.setMessage("Xóa hoàn tất");
						m.open();
						init();

						txtMadonvi.setText("");
						txtTendonvi.setText("");
						txtLienhe.setText("");
						txtGioithieu.setText("");
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
				}
			}
		});
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Xóa");
		button_2.setImage(user.getIcondata().deleteIcon);

		Button button_4 = new Button(shlQunLNgun, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlQunLNgun.dispose();
			}
		});
		GridData gd_button_4 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_4.widthHint = 75;
		button_4.setLayoutData(gd_button_4);
		button_4.setText("Đóng");
		init();
	}

	protected void setCreate() {
		EnableText();
		resetText();
		mode = 1;
	}

	protected void setEdit() {
		EnableText();
		mode = 2;
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

	private void init() throws SQLException {
		table.removeAll();
		ArrayList<NGUONGIAM> ngl = controler.getControl_NGUONGIAM().get_AllNguonGiam(text.getText());
		int i = 0;
		for (NGUONGIAM nguongiam : ngl) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { (i++) + "", nguongiam.getTEN_NGUONGIAM(), nguongiam.getGIOI_THIEU() });
			tableItem.setData(nguongiam);
		}
		new TreeRowStyle().Pack(table);
		disableText();
	}

	protected void setInsertMode() {
		mode = 1;
		enableText();
		clearText();
	}

	private void clearText() {
		txtTendonvi.setText("");
		txtGioithieu.setText("");
		txtLienhe.setText("");
	}

	private void enableText() {
		txtTendonvi.setEditable(true);
		txtGioithieu.setEditable(true);
		txtLienhe.setEditable(true);
	}

	protected void setEditMode() {
		mode = 2;
		enableText();
	}
}
