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
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class QuanLy_NguonTang extends Shell {
	private Text text_TenNguontang;
	private Text text_Lienlac;
	private Text text_Gioithieu;
	private Table table;
	private String ItemIndex;
	private Text text_MaNguonTang;
	private int ItemHeight = 22;
	private static NGUOIDUNG user;
	private Text text_1;
	private Button btnThmNgunTng;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanLy_NguonTang.class);

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
		setImage(SWTResourceManager.getImage(QuanLy_NguonTang.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(6, false));
		QuanLy_NguonTang.user = user;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE | SWT.ON_TOP);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1));
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
						text_MaNguonTang.setText(String.valueOf(t.getMA_NGUONTANG()));
						text_TenNguontang.setText(t.getTEN_NGUONTANG());
						text_Lienlac.setText(t.getLIEN_HE());
						text_Gioithieu.setText(t.getGIOI_THIEU());

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
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblMNgunTng = new Label(composite, SWT.NONE);
		lblMNgunTng.setText("Mã:");

		text_MaNguonTang = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_MaNguonTang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnNgunTng = new Label(composite, SWT.NONE);
		lblTnNgunTng.setText("Tên:");

		text_TenNguontang = new Text(composite, SWT.BORDER);
		text_TenNguontang.setEditable(false);
		text_TenNguontang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblLinLc = new Label(composite, SWT.NONE);
		lblLinLc.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblLinLc.setText("Liên lạc:");

		text_Lienlac = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Lienlac.setEditable(false);
		GridData gd_text_Lienlac = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Lienlac.heightHint = 68;
		text_Lienlac.setLayoutData(gd_text_Lienlac);

		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_3.setText("Giới thiệu:");

		text_Gioithieu = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Gioithieu.setEditable(false);
		GridData gd_text_Gioithieu = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text_Gioithieu.heightHint = 146;
		text_Gioithieu.setLayoutData(gd_text_Gioithieu);
		sashForm.setWeights(new int[] { 1000, 618 });
		new TreeRowStyle().setTableItemHeight(table, ItemHeight);
		Filltable();

		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnThmNgunTng = new Button(this, SWT.NONE);
		btnThmNgunTng.setImage(
				SWTResourceManager.getImage(QuanLy_NguonTang.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnThmNgunTng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCreate();
			}
		});
		btnThmNgunTng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnThmNgunTng.setText("Thêm mới");

		Button btnSa_1 = new Button(this, SWT.NONE);
		btnSa_1.setImage(
				SWTResourceManager.getImage(QuanLy_NguonTang.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
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
		btnXoa.setImage(
				SWTResourceManager.getImage(QuanLy_NguonTang.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
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

						text_MaNguonTang.setText("");
						text_TenNguontang.setText("");
						text_Lienlac.setText("");
						text_Gioithieu.setText("");
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
		btnSa.setImage(
				SWTResourceManager.getImage(QuanLy_NguonTang.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// flag2 = true: quá trình sửa thông tin hồ sơ
				try {
					if ((!text_TenNguontang.getText().equals("")) && (!text_Lienlac.getText().equals(""))) {

						MessageBox m = new MessageBox(getShell(), SWT.ICON_WORKING);
						if (getEdit()) {
							NGUONTANG nt = new NGUONTANG();
							nt.setMA_NGUONTANG(Integer.valueOf(text_MaNguonTang.getText()));
							nt.setTEN_NGUONTANG(text_TenNguontang.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienlac.getText());
							controler.getControl_NGUONTANG().update_NGUONTANG(nt);
							m.setMessage("Lưu hoàn tất!");
						}

						if (getCreate()) {
							NGUONTANG nt = new NGUONTANG();
							nt.setTEN_NGUONTANG(text_TenNguontang.getText());
							nt.setGIOI_THIEU(text_Gioithieu.getText());
							nt.setLIEN_HE(text_Lienlac.getText());
							controler.getControl_NGUONTANG().Insert_NGUONTANG(nt);
							m.setMessage("Tạo mới hoàn tất!");
						}
						m.setText("Hoàn tất");
						m.open();
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
		GridData gd_btnSa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
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
		text_MaNguonTang.setText("");
		text_TenNguontang.setText("");
		text_Lienlac.setText("");
		text_Gioithieu.setText("");
	}

	protected void disableText() {
		text_MaNguonTang.setEditable(false);
		text_TenNguontang.setEditable(false);
		text_Lienlac.setEditable(false);
		text_Gioithieu.setEditable(false);
	}

	private void EnableText() {
		text_MaNguonTang.setEditable(true);
		text_TenNguontang.setEditable(true);
		text_Lienlac.setEditable(true);
		text_Gioithieu.setEditable(true);

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
