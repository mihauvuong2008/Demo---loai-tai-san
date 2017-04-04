package View.AssetManagers.Hoso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Dialog;

public class TAPHOSO_View extends Dialog {
	protected Object result;
	protected Shell shlTaphosoShow;
	private TAP_HO_SO ths;
	private static NGUOIDUNG user;
	private Text text_Tentaphoso;
	private Text text_GioithieuTaphoso;
	private Text text_Mavanban;
	private Text text_Sovanban;
	private Text text_Donvibanhanh;
	private Text text_Trichyeu;
	private Text text_Soluongvanban;
	private Tree tree_taphoso;
	private VANBAN vb = new VANBAN();
	private DateTime dateTime;
	private boolean view_mode = false;// false: EditModenew
	private final Controler controler;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(TAPHOSO_View.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public TAPHOSO_View(Shell parent, int style, NGUOIDUNG user, TAP_HO_SO ths, boolean view_mode) {
		super(parent, style);
		TAPHOSO_View.user = user;
		this.ths = ths;
		this.view_mode = view_mode;
		controler = new Controler(user);
	}

	public Object open() throws SQLException {
		createContents();
		shlTaphosoShow.open();
		shlTaphosoShow.layout();
		Display display = getParent().getDisplay();
		while (!shlTaphosoShow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @param user
	 * @param ths
	 * @param view_mode
	 * @return
	 * @throws SQLException
	 */

	private void createContents() throws SQLException {
		shlTaphosoShow = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlTaphosoShow.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Documents-Folder-Graphite-icon.png"));
		shlTaphosoShow.setSize(780, 480);
		new FormTemplate().setCenterScreen(shlTaphosoShow);
		shlTaphosoShow.setText("T\u1EADp h\u1ED3 s\u01A1");
		if (view_mode) {
			shlTaphosoShow.setText("T\u1EADp h\u1ED3 s\u01A1 (Xem)");
		} else {
			shlTaphosoShow.setText("T\u1EADp h\u1ED3 s\u01A1 (Quản lý)");
		}
		shlTaphosoShow.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(shlTaphosoShow, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		ToolItem tltmThmVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmThmVnBn.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/document-add-icon (1).png"));
		tltmThmVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!view_mode) {
						updateTaphoso();
						Vanban_View vbv = new Vanban_View(shlTaphosoShow, SWT.NONE, user, ths, null, view_mode);
						vbv.open();
					}
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmThmVnBn.setText("Th\u00EAm v\u0103n b\u1EA3n");

		ToolItem tltmNhiuVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmNhiuVnBn.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Document-Add-icon-Mul.png"));
		tltmNhiuVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!view_mode) {
				}
			}
		});
		tltmNhiuVnBn.setText("Nhi\u1EC1u v\u0103n b\u1EA3n");

		ToolItem tltmXemVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmXemVnBn.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Mimetypes-Blank-Document-icon.png"));
		tltmXemVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateTaphoso();
					Vanban_View vbv = new Vanban_View(shlTaphosoShow, SWT.NONE, user, ths, vb, view_mode);
					vbv.open();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXemVnBn.setText("Xem v\u0103n b\u1EA3n");

		ToolItem tltmXaVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmXaVnBn.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Document-Delete-icon (1).png"));
		tltmXaVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!view_mode) {
						TreeItem[] til = tree_taphoso.getSelection();
						if (til.length > 0) {
							for (TreeItem ti : til) {
								VANBAN vb = (VANBAN) ti.getData();
								controler.getControl_VANBAN().delete_VANBAN(vb);

							}
							init();
						}
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXaVnBn.setText("X\u00F3a v\u0103n b\u1EA3n");

		ToolItem tltmLu = new ToolItem(toolBar, SWT.NONE);
		tltmLu.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Actions-document-save-icon (1).png"));
		tltmLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (!view_mode) {
						updateTaphoso();
						controler.getControl_TAPHOSO().update_TAPHOSO(ths);

						updateVanban();
						controler.getControl_VANBAN().update_VANBAN(vb);
						fillTaphoso();
					}
					init();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmLu.setText("L\u01B0u");

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmInVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmInVnBn.setImage(SWTResourceManager.getImage(TAPHOSO_View.class, "/Actions-document-print-icon (1).png"));
		tltmInVnBn.setText("Xem và In văn bản");

		SashForm sashForm = new SashForm(shlTaphosoShow, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		tree_taphoso = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_taphoso.setLinesVisible(true);
		tree_taphoso.setHeaderVisible(true);
		tree_taphoso.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TreeItem[] items = tree_taphoso.getSelection();
				if (items.length > 0) {
					vb = (VANBAN) items[0].getData();
					text_Mavanban.setText(String.valueOf(vb.getMA_VANBAN()));
					text_Sovanban.setText(vb.getSO_VANBAN());
					text_Donvibanhanh.setText(vb.getCO_QUAN_BAN_HANH());
					String[] d = mdf.getViewStringDate(vb.getNGAY_BAN_HANH()).split("-");
					dateTime.setDate(Integer.valueOf(d[0]), (Integer.valueOf(d[1]) - 1), Integer.valueOf(d[2]));
					text_Trichyeu.setText(vb.getTRICH_YEU() == null ? "" : vb.getTRICH_YEU());
				}
			}
		});

		TreeColumn trclmnStt = new TreeColumn(tree_taphoso, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("STT");

		TreeColumn trclmnSVnBn = new TreeColumn(tree_taphoso, SWT.NONE);
		trclmnSVnBn.setWidth(100);
		trclmnSVnBn.setText("S\u1ED0 V\u0102N B\u1EA2N");

		TreeColumn trclmnNgyBanHnh = new TreeColumn(tree_taphoso, SWT.NONE);
		trclmnNgyBanHnh.setWidth(120);
		trclmnNgyBanHnh.setText("NG\u00C0Y BAN H\u00C0NH");

		TreeColumn trclmnCQuanBan = new TreeColumn(tree_taphoso, SWT.NONE);
		trclmnCQuanBan.setWidth(140);
		trclmnCQuanBan.setText("C\u01A0 QUAN BAN H\u00C0NH");

		Menu menu = new Menu(tree_taphoso);
		tree_taphoso.setMenu(menu);

		MenuItem mntmXemVnBn = new MenuItem(menu, SWT.NONE);
		mntmXemVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Vanban_View vbv = new Vanban_View(shlTaphosoShow, SWT.NONE, user, ths, vb, view_mode);
					vbv.open();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemVnBn.setText("Xem V\u0103n b\u1EA3n");

		TreeColumn trclmnTrchYuNi = new TreeColumn(tree_taphoso, SWT.NONE);
		trclmnTrchYuNi.setWidth(200);
		trclmnTrchYuNi.setText("TR\u00CDCH Y\u1EBEU N\u1ED8I DUNG V\u0102N B\u1EA2N");

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		Group grpThngTinTp = new Group(sashForm_1, SWT.NONE);
		grpThngTinTp.setFont(SWTResourceManager.getFont("Consolas", 9, SWT.BOLD));
		grpThngTinTp.setText("Th\u00F4ng tin T\u1EADp h\u1ED3 s\u01A1");
		grpThngTinTp.setLayout(new GridLayout(2, false));

		Label lblTnTpH = new Label(grpThngTinTp, SWT.NONE);
		lblTnTpH.setText("T\u00EAn t\u1EADp h\u1ED3 s\u01A1:");

		text_Tentaphoso = new Text(grpThngTinTp, SWT.BORDER);
		text_Tentaphoso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiuTp = new Label(grpThngTinTp, SWT.NONE);
		lblGiiThiuTp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblGiiThiuTp.setText("Gi\u1EDBi thi\u1EC7u t\u1EADp h\u1ED3 s\u01A1:");

		text_GioithieuTaphoso = new Text(grpThngTinTp, SWT.BORDER | SWT.WRAP);
		text_GioithieuTaphoso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblSLngVn = new Label(grpThngTinTp, SWT.NONE);
		lblSLngVn.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblSLngVn.setText("S\u1ED1 l\u01B0\u1EE3ng v\u0103n b\u1EA3n:");

		text_Soluongvanban = new Text(grpThngTinTp, SWT.BORDER);
		text_Soluongvanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Soluongvanban.setEditable(false);
		text_Soluongvanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group grpThngTinVn = new Group(sashForm_1, SWT.NONE);
		grpThngTinVn.setText("Th\u00F4ng tin v\u0103n b\u1EA3n");
		grpThngTinVn.setFont(SWTResourceManager.getFont("Consolas", 9, SWT.BOLD));
		grpThngTinVn.setLayout(new GridLayout(2, false));

		Label lblMVnBn = new Label(grpThngTinVn, SWT.NONE);
		lblMVnBn.setText("M\u00E3 v\u0103n b\u1EA3n:");

		text_Mavanban = new Text(grpThngTinVn, SWT.BORDER | SWT.READ_ONLY);
		text_Mavanban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Mavanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSVnBn = new Label(grpThngTinVn, SWT.NONE);
		lblSVnBn.setText("S\u1ED1 v\u0103n b\u1EA3n:");

		text_Sovanban = new Text(grpThngTinVn, SWT.BORDER);
		text_Sovanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyBanHnh = new Label(grpThngTinVn, SWT.NONE);
		lblNgyBanHnh.setText("Ng\u00E0y ban h\u00E0nh:");

		dateTime = new DateTime(grpThngTinVn, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblnVBan = new Label(grpThngTinVn, SWT.NONE);
		lblnVBan.setText("\u0110\u01A1n v\u1ECB ban h\u00E0nh:");

		text_Donvibanhanh = new Text(grpThngTinVn, SWT.BORDER);
		text_Donvibanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTrchYu = new Label(grpThngTinVn, SWT.NONE);
		GridData gd_lblTrchYu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTrchYu.verticalIndent = 3;
		lblTrchYu.setLayoutData(gd_lblTrchYu);
		lblTrchYu.setText("Tr\u00EDch y\u1EBFu:");

		text_Trichyeu = new Text(grpThngTinVn, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_1.setWeights(new int[] { 618, 1000 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnng = new Button(shlTaphosoShow, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTaphosoShow.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected void updateVanban() {
		vb.setSO_VANBAN(text_Sovanban.getText());
		vb.setCO_QUAN_BAN_HANH(text_Donvibanhanh.getText());
		Date d = mdf.getDate(dateTime);
		vb.setNGAY_BAN_HANH(d);
		vb.setTRICH_YEU(text_Trichyeu.getText());
	}

	protected void updateTaphoso() {
		ths.setTEN_TAPHOSO(text_Tentaphoso.getText());
		ths.setGIOITHIEU_TAPHOSO(text_GioithieuTaphoso.getText());
	}

	protected void fillTaphoso() {
		text_Tentaphoso.setText(ths.getTEN_TAPHOSO() == null ? "" : ths.getTEN_TAPHOSO());
		text_GioithieuTaphoso.setText(ths.getGIOITHIEU_TAPHOSO() == null ? "" : ths.getGIOITHIEU_TAPHOSO());
	}

	private void init() throws SQLException {
		tree_taphoso.removeAll();
		if (ths != null) {
			ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
			int i = 1;
			if (vbl != null)
				for (VANBAN vb : vbl) {
					TreeItem ti = new TreeItem(tree_taphoso, SWT.NONE);
					int max = 50;
					String ty = vb.getTRICH_YEU();
					if (ty.length() < 50)
						max = ty.length();
					ti.setText(new String[] { "" + i, vb.getSO_VANBAN(), mdf.getViewStringDate(vb.getNGAY_BAN_HANH()),
							vb.getCO_QUAN_BAN_HANH(), ty.substring(0, max) });
					ti.setData(vb);
					i++;
				}
			text_Soluongvanban.setText(String.valueOf(i - 1));
			fillTaphoso();
		}
	}

	/**
	 * Create contents of the shell.
	 */
}
