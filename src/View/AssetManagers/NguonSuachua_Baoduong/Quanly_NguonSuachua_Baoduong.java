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
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class Quanly_NguonSuachua_Baoduong extends Dialog {

	protected Object result;
	protected Shell shlQunLNgun;
	private Text text;
	private Table table;
	private Text text_MaNguon;
	private Text text_Tennguon;
	private Text text_Lienlac;
	private Text text_Gioithieu;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(Quanly_NguonSuachua_Baoduong.class);

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
		shlQunLNgun.setImage(SWTResourceManager.getImage(Quanly_NguonSuachua_Baoduong.class, "/phone-icon.png"));
		shlQunLNgun.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlQunLNgun);
		shlQunLNgun.setText("Quản lý nguồn Sửa chữa - Bảo dưỡng");
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
					NGUONSUACHUA_BAODUONG ng = (NGUONSUACHUA_BAODUONG) items[0].getData();
					fillText(ng);
				}
			}

			private void fillText(NGUONSUACHUA_BAODUONG ng) {
				text_MaNguon.setText(ng.getMA_NGUONSUACHUA_BAODUONG() + "");
				text_Tennguon.setText(ng.getTEN_NGUONSUACHUA_BAODUONG());
				text_Gioithieu.setText(ng.getGIOI_THIEU());
				text_Lienlac.setText(ng.getLIEN_HE());
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

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblMNgunScbd = new Label(composite_1, SWT.NONE);
		lblMNgunScbd.setText("Mã Đơn vị:");

		text_MaNguon = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_MaNguon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_MaNguon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnNgunScbd = new Label(composite_1, SWT.NONE);
		lblTnNgunScbd.setText("Tên đơn vị");

		text_Tennguon = new Text(composite_1, SWT.BORDER);
		text_Tennguon.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tennguon.setEditable(false);
		text_Tennguon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_2.setText("Liên lạc:");

		text_Lienlac = new Text(composite_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Lienlac.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Lienlac.setEditable(false);
		GridData gd_text_Lienlac = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Lienlac.heightHint = 68;
		text_Lienlac.setLayoutData(gd_text_Lienlac);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_3.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Gioithieu.setEditable(false);
		GridData gd_text_Gioithieu = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Gioithieu.heightHint = 146;
		text_Gioithieu.setLayoutData(gd_text_Gioithieu);
		sashForm.setWeights(new int[] { 1000, 618 });

		text = new Text(shlQunLNgun, SWT.BORDER);
		text.setMessage("Tìm kiếm");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnThm = new Button(shlQunLNgun, SWT.NONE);
		btnThm.setImage(SWTResourceManager.getImage(Quanly_NguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		btnThm.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnThm.setText("Thêm");

		Button button_1 = new Button(shlQunLNgun, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(Quanly_NguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
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

						text_MaNguon.setText("");
						text_Tennguon.setText("");
						text_Lienlac.setText("");
						text_Gioithieu.setText("");
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		button_2.setImage(SWTResourceManager.getImage(Quanly_NguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Xóa");

		Button button_3 = new Button(shlQunLNgun, SWT.NONE);
		button_3.setImage(SWTResourceManager.getImage(Quanly_NguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if ((!text_Tennguon.getText().equals("")) && (!text_Lienlac.getText().equals(""))) {
						MessageBox m = new MessageBox(shlQunLNgun, SWT.ICON_WORKING);
						if (getEdit()) {
							NGUONSUACHUA_BAODUONG nt = new NGUONSUACHUA_BAODUONG();
							nt.setMA_NGUONSUACHUA_BAODUONG(Integer.valueOf(text_MaNguon.getText()));
							nt.setTEN_NGUONSUACHUA_BAODUONG(text_Tennguon.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienlac.getText());
							controler.getControl_NGUONSUACHUA_BAODUONG().update_NGUONSUACHUA_BAODUONG(nt);
							m.setMessage("Lưu hoàn tất!");
						}
						if (getCreate()) {
							NGUONSUACHUA_BAODUONG nt = new NGUONSUACHUA_BAODUONG();
							nt.setTEN_NGUONSUACHUA_BAODUONG(text_Tennguon.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienlac.getText());
							controler.getControl_NGUONSUACHUA_BAODUONG().Insert_NGUONSUACHUA_BAODUONG(nt);
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
					setCompleteAction();
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
		clearText();
	}

	private void disableText() {
		text_Tennguon.setEditable(false);
		text_Gioithieu.setEditable(false);
		text_Lienlac.setEditable(false);
	}

	protected void setEditMode() {
		mode = 2;
		enableText();
	}

	private void enableText() {
		text_Tennguon.setEditable(true);
		text_Gioithieu.setEditable(true);
		text_Lienlac.setEditable(true);
	}

	protected void setInsertMode() {
		mode = 1;
		enableText();
		clearText();
	}

	private void clearText() {
		text_MaNguon.setText("");
		text_Tennguon.setText("");
		text_Gioithieu.setText("");
		text_Lienlac.setText("");
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
