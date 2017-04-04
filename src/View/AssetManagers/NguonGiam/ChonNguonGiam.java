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
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;

public class ChonNguonGiam extends Dialog {

	protected Object result;
	protected Shell shlChnNgunTthanh;
	private Table table;
	private Text text_Ten;
	private Text text_Gioithieu;
	private Text text_Lienhe;
	private final Controler controler;
	private Text text_Search;
	private Text text_Ma;

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
		shlChnNgunTthanh = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shlChnNgunTthanh.setImage(
				SWTResourceManager.getImage(ChonNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlChnNgunTthanh.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlChnNgunTthanh);
		shlChnNgunTthanh.setText("Ch\u1ECDn ngu\u1ED3n Tthanh l\u00FD - B\u00E0n giao T\u00E0i s\u1EA3n");
		shlChnNgunTthanh.setLayout(new GridLayout(3, false));

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
				text_Ma.setText(ng.getMA_NGUONGIAM() + "");
				text_Ten.setText(ng.getTEN_NGUONGIAM());
				text_Gioithieu.setText(ng.getGIOI_THIEU());
				text_Lienhe.setText(ng.getLIEN_HE());
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

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblM = new Label(composite, SWT.NONE);
		lblM.setText("M\u00E3:");

		text_Ma = new Text(composite, SWT.BORDER);
		text_Ma.setEditable(false);
		text_Ma.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTnNgunGim = new Label(composite, SWT.NONE);
		lblTnNgunGim.setText("T\u00EAn:");

		text_Ten = new Text(composite, SWT.BORDER);
		text_Ten.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Ten.setEditable(false);
		text_Ten.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(composite, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Gi\u1EDBi thi\u1EC7u:");

		text_Gioithieu = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Gioithieu.setEditable(false);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(composite, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Li\u00EAn h\u1EC7:");

		text_Lienhe = new Text(composite, SWT.BORDER | SWT.MULTI);
		text_Lienhe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Lienhe.setEditable(false);
		text_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1000, 618 });

		text_Search = new Text(shlChnNgunTthanh, SWT.BORDER);
		text_Search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnChn = new Button(shlChnNgunTthanh, SWT.NONE);
		btnChn.setImage(
				SWTResourceManager.getImage(ChonNguonGiam.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getSelection().length > 0) {
					result = table.getSelection()[0].getData();
					shlChnNgunTthanh.dispose();
				}
			}
		});
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
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
