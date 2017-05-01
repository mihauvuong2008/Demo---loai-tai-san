package View.AssetManagers.NguonSuachua_Baoduong;

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
import DAO.NGUONSUACHUA_BAODUONG;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class Quanly_NguonSuachua_Baoduong extends Dialog {

	protected Object result;
	protected Shell shlQunLNgun;
	private Text text;
	private Table table;
	private int mode;
	private final Controler controler;
	private NGUOIDUNG user;
	private static Log log = LogFactory.getLog(Quanly_NguonSuachua_Baoduong.class);
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtMadonvi;
	private Text txtTendonvi;
	private Text txtGioithieu;
	private Text txtLienhe;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public Quanly_NguonSuachua_Baoduong(Shell parent, int style, NGUOIDUNG user) {
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
		shlQunLNgun.setImage(user.getIcondata().PhoneIcon);
		shlQunLNgun.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlQunLNgun);
		shlQunLNgun.setText("Quản lý nguồn Sửa chữa - Bảo dưỡng");
		shlQunLNgun.setLayout(new GridLayout(5, false));

		text = new Text(shlQunLNgun, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					try {
						init();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		text.setMessage("Tìm kiếm");
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
					NGUONSUACHUA_BAODUONG ng = (NGUONSUACHUA_BAODUONG) items[0].getData();
					fillText(ng);
				}
			}

			private void fillText(NGUONSUACHUA_BAODUONG ng) {
				txtMadonvi.setText(ng.getMA_NGUONSUACHUA_BAODUONG() + "");
				txtTendonvi.setText(ng.getTEN_NGUONSUACHUA_BAODUONG());
				txtGioithieu.setText(ng.getGIOI_THIEU());
				txtLienhe.setText(ng.getLIEN_HE());
			}
		});

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tblclmnMNgunScbd = new TableColumn(table, SWT.NONE);
		tblclmnMNgunScbd.setWidth(120);
		tblclmnMNgunScbd.setText("MÃ NGUỒN SC-BD");

		TableColumn tblclmnTnNgunScbd = new TableColumn(table, SWT.NONE);
		tblclmnTnNgunScbd.setWidth(120);
		tblclmnTnNgunScbd.setText("TÊN NGUỒN SC-BD");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("LIÊN HỆ");

		Composite composite = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite);
		{
			TableWrapLayout twl_composite = new TableWrapLayout();
			twl_composite.numColumns = 2;
			composite.setLayout(twl_composite);
		}

		@SuppressWarnings("unused")
		Label lblMnV = formToolkit.createLabel(composite, "Mã đơn vị: ", SWT.NONE);

		txtMadonvi = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtMadonvi.setLayoutData(new TableWrapData(TableWrapData.FILL, TableWrapData.TOP, 1, 1));
		txtMadonvi.setText("");

		@SuppressWarnings("unused")
		Label lblTnnV = formToolkit.createLabel(composite, "Tên đơn vị: ", SWT.NONE);

		txtTendonvi = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtTendonvi.setText("");
		txtTendonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblGiiThiu = formToolkit.createLabel(composite, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtGioithieu.setText("");
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);

		@SuppressWarnings("unused")
		Label lblLinH = formToolkit.createLabel(composite, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite, "New Text", SWT.WRAP | SWT.V_SCROLL);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnThm = new Button(shlQunLNgun, SWT.NONE);
		btnThm.setImage(user.getIcondata().addIcon);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		GridData gd_btnThm = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnThm.widthHint = 75;
		btnThm.setLayoutData(gd_btnThm);
		btnThm.setText("Thêm");

		Button button_1 = new Button(shlQunLNgun, SWT.NONE);
		button_1.setImage(user.getIcondata().editIcon);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Sửa");

		Button button_2 = new Button(shlQunLNgun, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] itemarr = table.getSelection();
					if (itemarr != null) {
						for (TableItem ti : itemarr) {
							NGUONSUACHUA_BAODUONG nt = (NGUONSUACHUA_BAODUONG) ti.getData();
							controler.getControl_NGUONSUACHUA_BAODUONG().delete_NGUONSUACHUA_BAODUONG(nt);
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
					e1.printStackTrace();
				}
			}
		});
		button_2.setImage(user.getIcondata().deleteIcon);
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Xóa");

		Button button_3 = new Button(shlQunLNgun, SWT.NONE);
		button_3.setImage(user.getIcondata().saveIcon);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (getEdit() || getCreate())
						if ((!txtTendonvi.getText().equals("")) && (!txtLienhe.getText().equals(""))) {
							MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_WORKING);
							if (getEdit()) {
								NGUONSUACHUA_BAODUONG nt = new NGUONSUACHUA_BAODUONG();
								nt.setMA_NGUONSUACHUA_BAODUONG(Integer.valueOf(txtMadonvi.getText()));
								nt.setTEN_NGUONSUACHUA_BAODUONG(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONSUACHUA_BAODUONG().update_NGUONSUACHUA_BAODUONG(nt);
								m.setMessage("Lưu hoàn tất!");
								m.setText("Hoàn tất");
								m.open();
							} else if (getCreate()) {
								NGUONSUACHUA_BAODUONG nt = new NGUONSUACHUA_BAODUONG();
								nt.setTEN_NGUONSUACHUA_BAODUONG(txtTendonvi.getText());
								nt.setGIOI_THIEU(txtGioithieu.getText());
								nt.setLIEN_HE(txtLienhe.getText());
								controler.getControl_NGUONSUACHUA_BAODUONG().Insert_NGUONSUACHUA_BAODUONG(nt);
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
					setCompleteAction();
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_button_3.widthHint = 75;
		button_3.setLayoutData(gd_button_3);
		button_3.setText("Lưu");

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

	protected boolean isInsertMode() {
		if (mode == 1)
			return true;
		return false;
	}

	protected boolean isEditMode() {
		if (mode == 2)
			return true;
		return false;
	}

	protected void setCompleteAction() {
		mode = 0;
		disableText();
	}

	private void disableText() {
		txtMadonvi.setEditable(false);
		txtTendonvi.setEditable(false);
		txtGioithieu.setEditable(false);
		txtLienhe.setEditable(false);
	}

	protected void setEditMode() {
		mode = 2;
		enableText();
	}

	private void enableText() {
		txtTendonvi.setEditable(true);
		txtGioithieu.setEditable(true);
		txtLienhe.setEditable(true);
	}

	protected void setInsertMode() {
		mode = 1;
		enableText();
		clearText();
	}

	private void clearText() {
		txtMadonvi.setText("");
		txtTendonvi.setText("");
		txtGioithieu.setText("");
		txtLienhe.setText("");
	}

	private void init() throws SQLException {
		table.removeAll();
		ArrayList<NGUONSUACHUA_BAODUONG> sbdl = controler.getControl_NGUONSUACHUA_BAODUONG().getAllData(text.getText());
		int i = 1;
		for (NGUONSUACHUA_BAODUONG nsb : sbdl) {
			TableItem tbi = new TableItem(table, SWT.NONE);
			tbi.setText(new String[] { "" + i, nsb.getMA_NGUONSUACHUA_BAODUONG() + "",
					nsb.getTEN_NGUONSUACHUA_BAODUONG(), nsb.getLIEN_HE() });
			tbi.setData(nsb);
			i++;
		}
		new TreeRowStyle().Pack(table);
		disableText();
	}
}
