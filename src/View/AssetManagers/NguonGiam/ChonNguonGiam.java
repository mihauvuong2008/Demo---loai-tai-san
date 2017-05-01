package View.AssetManagers.NguonGiam;

import java.sql.SQLException;
import java.util.ArrayList;

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

public class ChonNguonGiam extends Dialog {

	protected Object result;
	protected Shell shlChnNgunTthanh;
	private Table table;
	private final Controler controler;
	private Text text_Search;
	private NGUOIDUNG user;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtManguon;
	private Text txtTendonvi;
	private Text txtGioithieu;
	private Text txtLienhe;

	public NGUONGIAM getResult() {
		return (NGUONGIAM) result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public ChonNguonGiam(Shell parent, int style, NGUOIDUNG user) {
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
		shlChnNgunTthanh.open();
		shlChnNgunTthanh.layout();
		Display display = getParent().getDisplay();
		while (!shlChnNgunTthanh.isDisposed()) {
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
		shlChnNgunTthanh = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlChnNgunTthanh.setImage(user.getIcondata().PhoneIcon);
		shlChnNgunTthanh.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlChnNgunTthanh);
		shlChnNgunTthanh.setText("Ch\u1ECDn ngu\u1ED3n Tthanh l\u00FD - B\u00E0n giao T\u00E0i s\u1EA3n");
		shlChnNgunTthanh.setLayout(new GridLayout(3, false));

		text_Search = new Text(shlChnNgunTthanh, SWT.BORDER);
		text_Search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		SashForm sashForm = new SashForm(shlChnNgunTthanh, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
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
				txtManguon.setText(ng.getMA_NGUONGIAM() + "");
				txtTendonvi.setText(ng.getTEN_NGUONGIAM());
				txtGioithieu.setText(ng.getGIOI_THIEU());
				txtLienhe.setText(ng.getLIEN_HE());
			}
		});
		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnNgunGim = new TableColumn(table, SWT.NONE);
		tblclmnTnNgunGim.setWidth(150);
		tblclmnTnNgunGim.setText("T\u00CAN NGU\u1ED2N GI\u1EA2M");

		TableColumn tblclmnGiiThiu = new TableColumn(table, SWT.NONE);
		tblclmnGiiThiu.setWidth(200);
		tblclmnGiiThiu.setText("GI\u1EDAI THI\u1EC6U");

		Composite composite_1 = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		{
			TableWrapLayout twl_composite_1 = new TableWrapLayout();
			twl_composite_1.numColumns = 2;
			composite_1.setLayout(twl_composite_1);
		}

		@SuppressWarnings("unused")
		Label lblM_1 = formToolkit.createLabel(composite_1, "Mã: ", SWT.NONE);

		txtManguon = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtManguon.setText("");
		txtManguon.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblTnnV = formToolkit.createLabel(composite_1, "Tên đơn vị: ", SWT.NONE);

		txtTendonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtTendonvi.setText("");
		txtTendonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblGiiThiu_1 = formToolkit.createLabel(composite_1, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtGioithieu.setText("");
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);

		@SuppressWarnings("unused")
		Label lblLinH_1 = formToolkit.createLabel(composite_1, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);
		sashForm.setWeights(new int[] { 225, 146 });

		Button btnThm = new Button(shlChnNgunTthanh, SWT.NONE);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QuanlyNguonGiam qng = new QuanlyNguonGiam(shlChnNgunTthanh, SWT.DIALOG_TRIM, user);
				try {
					qng.open();
					init();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnThm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThm.widthHint = 75;
		btnThm.setLayoutData(gd_btnThm);
		btnThm.setText("Thêm");

		Button btnChn = new Button(shlChnNgunTthanh, SWT.NONE);
		btnChn.setImage(user.getIcondata().successIcon);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelection().length > 0) {
					result = table.getSelection()[0].getData();
					shlChnNgunTthanh.dispose();
				}
			}
		});
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Ch\u1ECDn");

		Button btnng = new Button(shlChnNgunTthanh, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgunTthanh.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	private void init() throws SQLException {
		ArrayList<NGUONGIAM> ngl = controler.getControl_NGUONGIAM().get_AllNguonGiam(text_Search.getText());
		fillData(ngl);
	}

	void fillData(ArrayList<NGUONGIAM> ngl) {
		table.removeAll();
		int i = 1;
		if (ngl != null)
			for (NGUONGIAM ng : ngl) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { i + "", ng.getTEN_NGUONGIAM(), ng.getGIOI_THIEU() });
				ti.setData(ng);
			}
		new TreeRowStyle().Pack(table);
	}
}
