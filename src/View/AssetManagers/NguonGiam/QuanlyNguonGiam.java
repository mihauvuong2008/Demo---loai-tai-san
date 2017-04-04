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
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class QuanlyNguonGiam extends Dialog {

	protected Object result;
	protected Shell shlQunLNgun;
	private Text text;
	private Table table;
	private Text text_Ma;
	private Text text_Ten;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanlyNguonGiam.class);

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
		shlQunLNgun.setImage(
				SWTResourceManager.getImage(QuanlyNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlQunLNgun.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlQunLNgun);
		shlQunLNgun.setText("Quản lý Nguồn giảm");
		shlQunLNgun.setLayout(new GridLayout(6, false));

		SashForm sashForm = new SashForm(shlQunLNgun, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));

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
				text_Ma.setText(ng.getMA_NGUONGIAM() + "");
				text_Ten.setText(ng.getTEN_NGUONGIAM());
				text_Gioithieu.setText(ng.getGIOI_THIEU());
				text_Lienhe.setText(ng.getLIEN_HE());
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

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label = new Label(composite, SWT.NONE);
		label.setText("M\u00E3:");

		text_Ma = new Text(composite, SWT.BORDER);
		text_Ma.setEditable(false);
		text_Ma.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("T\u00EAn:");

		text_Ten = new Text(composite, SWT.BORDER);
		text_Ten.setEditable(false);
		text_Ten.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ten.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite, SWT.NONE);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_2.verticalIndent = 3;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("Gi\u1EDBi thi\u1EC7u:");

		text_Gioithieu = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_Gioithieu.setEditable(false);
		text_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label_3 = new Label(composite, SWT.NONE);
		GridData gd_label_3 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_3.verticalIndent = 3;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("Li\u00EAn h\u1EC7:");

		text_Lienhe = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_Lienhe.setEditable(false);
		text_Lienhe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1000, 618 });

		text = new Text(shlQunLNgun, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
		button.setImage(
				SWTResourceManager.getImage(QuanlyNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

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
		button_1.setImage(
				SWTResourceManager.getImage(QuanlyNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

		Button button_3 = new Button(shlQunLNgun, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if ((!text_Ten.getText().equals("")) && (!text_Lienhe.getText().equals(""))) {

						MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_WORKING);
						if (getEdit()) {
							NGUONGIAM nt = new NGUONGIAM();
							nt.setMA_NGUONGIAM(Integer.valueOf(text_Ma.getText()));
							nt.setTEN_NGUONGIAM(text_Ten.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienhe.getText());
							controler.getControl_NGUONGIAM().update_NGUONGIAM(nt);
							m.setMessage("Lưu hoàn tất!");
						}

						if (getCreate()) {
							NGUONGIAM nt = new NGUONGIAM();
							nt.setTEN_NGUONGIAM(text_Ten.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienhe.getText());
							controler.getControl_NGUONGIAM().Insert_NGUONGIAM(nt);
							m.setMessage("Tạo mới hoàn tất!");
						}
						m.setText("Hoàn tất");
						m.open();
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
		button_3.setImage(
				SWTResourceManager.getImage(QuanlyNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

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

						text_Ma.setText("");
						text_Ten.setText("");
						text_Lienhe.setText("");
						text_Gioithieu.setText("");
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button_2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Xóa");
		button_2.setImage(
				SWTResourceManager.getImage(QuanlyNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));

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
		text_Ma.setText("");
		text_Ten.setText("");
		text_Lienhe.setText("");
		text_Gioithieu.setText("");
	}

	protected void disableText() {
		text_Ma.setEditable(false);
		text_Ten.setEditable(false);
		text_Lienhe.setEditable(false);
		text_Gioithieu.setEditable(false);
	}

	private void EnableText() {
		text_Ma.setEditable(true);
		text_Ten.setEditable(true);
		text_Lienhe.setEditable(true);
		text_Gioithieu.setEditable(true);

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
		text_Ten.setText("");
		text_Gioithieu.setText("");
		text_Lienhe.setText("");
	}

	private void enableText() {
		text_Ten.setEditable(true);
		text_Gioithieu.setEditable(true);
		text_Lienhe.setEditable(true);
	}

	protected void setEditMode() {
		mode = 2;
		enableText();
	}
}
