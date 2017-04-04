package View.AssetManagers.DanhMuc_Phongban;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class QuanLy_PhongBan extends Shell {
	private Text text_Tenphongban;
	private Text text_GioithieuPhongban;
	private static NGUOIDUNG user;
	private Label label_Maphongban;
	private Tree tree;
	private int mode;
	private final Controler controler;
	private static Log log = LogFactory.getLog(QuanLy_PhongBan.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			QuanLy_PhongBan shell = new QuanLy_PhongBan(display, user);
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
	 * @param user
	 * @throws SQLException
	 */
	public QuanLy_PhongBan(Display display, NGUOIDUNG user) throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/city-icon.png"));
		setLayout(new GridLayout(5, false));
		QuanLy_PhongBan.user = user;
		controler = new Controler(user);

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		TreeRowStyle tsl = new TreeRowStyle();
		tree = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		new TreeTemplate(user).getTreePHONGBAN(tree);
		tree.pack();
		tsl.setTreeItemHeight(tree, 20);
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree.getSelection();

				if (items.length > 0) {
					Object o = items[0].getData();
					if (o instanceof PHONGBAN) {
						PHONGBAN p = (PHONGBAN) o;
						label_Maphongban.setText(p.getMA_PHONGBAN() + "");
						text_Tenphongban.setText(p.getTEN_PHONGBAN());
						text_GioithieuPhongban.setText(p.getGIOI_THIEU_PHONGBAN());
					}
				}
			}
		});

		Group grpThngTinPhng = new Group(sashForm, SWT.NONE);
		grpThngTinPhng.setText("Th\u00F4ng tin Ph\u00F2ng ban");
		grpThngTinPhng.setLayout(new GridLayout(2, false));

		Label lblMPhngBan = new Label(grpThngTinPhng, SWT.NONE);
		lblMPhngBan.setText("M\u00E3 Ph\u00F2ng Ban:");

		label_Maphongban = new Label(grpThngTinPhng, SWT.BORDER);
		label_Maphongban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Maphongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnPhngBan = new Label(grpThngTinPhng, SWT.NONE);
		lblTnPhngBan.setText("T\u00EAn Ph\u00F2ng ban:");

		text_Tenphongban = new Text(grpThngTinPhng, SWT.BORDER);
		text_Tenphongban.setEditable(false);
		text_Tenphongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiuPhng = new Label(grpThngTinPhng, SWT.NONE);
		GridData gd_lblGiiThiuPhng = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiuPhng.verticalIndent = 3;
		lblGiiThiuPhng.setLayoutData(gd_lblGiiThiuPhng);
		lblGiiThiuPhng.setText("Gi\u1EDBi thi\u1EC7u ph\u00F2ng ban:");

		text_GioithieuPhongban = new Text(grpThngTinPhng, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text_GioithieuPhongban.setEditable(false);
		text_GioithieuPhongban.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1, 1 });

		Button btnThm = new Button(this, SWT.NONE);
		btnThm.setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/add-1-icon (1).png"));
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setInsertMode();
			}
		});
		btnThm.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnThm.setText("Th\u00EAm");

		Button btnSa = new Button(this, SWT.NONE);
		btnSa.setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/edit-validated-icon (1).png"));
		btnSa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setEditMode();
			}
		});
		btnSa.setText("S\u1EEDa");

		Button btnXa = new Button(this, SWT.NONE);
		btnXa.setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/delete-1-icon (1).png"));
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem ti[] = tree.getSelection();
					if (ti.length > 0) {
						PHONGBAN p = (PHONGBAN) ti[0].getData();
						controler.getControl_PHONGBAN().delete_PHONGBAN(p);
					}
					new TreeTemplate(user).getTreePHONGBAN(tree);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXa.setText("X\u00F3a");

		Button btnLu = new Button(this, SWT.NONE);
		btnLu.setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/Actions-document-save-icon (1).png"));
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (isInsertMode()) {
						PHONGBAN p = new PHONGBAN();
						p.setTEN_PHONGBAN(text_Tenphongban.getText());
						p.setGIOI_THIEU_PHONGBAN(text_GioithieuPhongban.getText());
						controler.getControl_PHONGBAN().insert_PHONBAN(p);
					} else if (isEditMode()) {
						TreeItem ti[] = tree.getSelection();
						if (ti.length > 0) {
							PHONGBAN p = (PHONGBAN) ti[0].getData();
							p.setTEN_PHONGBAN(text_Tenphongban.getText());
							p.setGIOI_THIEU_PHONGBAN(text_GioithieuPhongban.getText());
							controler.getControl_PHONGBAN().update_PHONGBAN(p);
						}
					}
					new TreeTemplate(user).getTreePHONGBAN(tree);
					setCompleteMode();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLu.setText("L\u01B0u");

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
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	protected boolean isEditMode() {
		if (mode == 2)
			return true;
		return false;
	}

	protected boolean isInsertMode() {
		if (mode == 1)
			return true;
		return false;
	}

	protected void setInsertMode() {
		mode = 1;
		text_Tenphongban.setEditable(true);
		text_GioithieuPhongban.setEditable(true);
		clearText();
	}

	private void clearText() {
		label_Maphongban.setText("");
		text_Tenphongban.setText("");
		text_GioithieuPhongban.setText("");
	}

	protected void setEditMode() {
		mode = 2;
		text_Tenphongban.setEditable(true);
		text_GioithieuPhongban.setEditable(true);

	}

	protected void setCompleteMode() {
		text_Tenphongban.setEditable(false);
		text_GioithieuPhongban.setEditable(false);

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Quản lý Đơn vị nội bộ");
		setSize(695, 430);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
