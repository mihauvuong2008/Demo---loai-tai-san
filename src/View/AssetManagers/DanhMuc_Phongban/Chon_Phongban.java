package View.AssetManagers.DanhMuc_Phongban;

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
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import View.Template.TreeTemplate;

public class Chon_Phongban extends Dialog {

	protected ArrayList<PHONGBAN> result;
	protected Shell shlChnPhngBan;
	private static NGUOIDUNG user;
	private Text text_Tenphongban;
	private Text text_GioithieuPhongban;
	private Label label_Maphongban;
	private Tree tree;
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(Chon_Phongban.class);

	public ArrayList<PHONGBAN> getResult() {
		return result;
	}

	public void setResult(ArrayList<PHONGBAN> result) {
		this.result = result;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Chon_Phongban(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		Chon_Phongban.user = user;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlChnPhngBan.open();
		shlChnPhngBan.layout();
		Display display = getParent().getDisplay();
		while (!shlChnPhngBan.isDisposed()) {
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
		shlChnPhngBan = new Shell(getParent(), getStyle());
		shlChnPhngBan.setImage(SWTResourceManager.getImage(QuanLy_PhongBan.class, "/city-icon.png"));
		shlChnPhngBan.setSize(450, 300);
		shlChnPhngBan.setText("Chọn Phòng ban");
		shlChnPhngBan.setLayout(new GridLayout(2, false));
		shlChnPhngBan.setSize(695, 430);
		new FormTemplate().setCenterScreen(shlChnPhngBan);
		SashForm sashForm = new SashForm(shlChnPhngBan, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		TreeRowStyle tsl = new TreeRowStyle();
		tree = new Tree(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		new TreeTemplate(user).getTreePHONGBAN(tree);
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
		text_Tenphongban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tenphongban.setEditable(false);
		text_Tenphongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiuPhng = new Label(grpThngTinPhng, SWT.NONE);
		GridData gd_lblGiiThiuPhng = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiuPhng.verticalIndent = 3;
		lblGiiThiuPhng.setLayoutData(gd_lblGiiThiuPhng);
		lblGiiThiuPhng.setText("Giới thiệu:");

		text_GioithieuPhongban = new Text(grpThngTinPhng, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		text_GioithieuPhongban.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_GioithieuPhongban.setEditable(false);
		text_GioithieuPhongban.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 392, 284 });

		Button btnLu = new Button(shlChnPhngBan, SWT.NONE);
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] til = tree.getSelection();
				result = new ArrayList<>();
				for (TreeItem ti : til) {
					result.add((PHONGBAN) ti.getData());
				}
				shlChnPhngBan.dispose();
			}
		});
		btnLu.setImage(SWTResourceManager.getImage(Chon_Phongban.class, "/Select-icon.png"));
		GridData gd_btnLu = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLu.setLayoutData(gd_btnLu);
		btnLu.setText("Chọn");

		Button btnng = new Button(shlChnPhngBan, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnPhngBan.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
	}

}
