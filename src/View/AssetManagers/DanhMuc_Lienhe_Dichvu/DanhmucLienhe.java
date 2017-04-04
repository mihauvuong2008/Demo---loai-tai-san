package View.AssetManagers.DanhMuc_Lienhe_Dichvu;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.NGUONTANG;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Button;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Label;

public class DanhmucLienhe extends Dialog {

	protected Object result;
	protected Shell shlLinHDch;
	private Text text;
	private Table table;
	private Button btnMuaSamTiepnhan;
	private Button btnSuaChua;
	private Button btnThanhLy;
	private Label lblKtQu;
	private Button btnTmKim;
	private final Controler controler;
	private static Log log = LogFactory.getLog(DanhmucLienhe.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public DanhmucLienhe(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLinHDch.open();
		shlLinHDch.layout();
		Display display = getParent().getDisplay();
		while (!shlLinHDch.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlLinHDch = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlLinHDch.setImage(
				SWTResourceManager.getImage(DanhmucLienhe.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlLinHDch.setSize(728, 450);
		shlLinHDch.setText("Li\u00EAn h\u1EC7 d\u1ECBch v\u1EE5");
		shlLinHDch.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlLinHDch);

		btnMuaSamTiepnhan = new Button(shlLinHDch, SWT.RADIO);
		btnMuaSamTiepnhan.setSelection(true);
		btnMuaSamTiepnhan.setText("Mua s\u1EAFm, ti\u1EBFp nh\u1EADn Ph\u01B0\u01A1ng ti\u1EC7n t\u00E0i s\u1EA3n");
		new Label(shlLinHDch, SWT.NONE);

		btnSuaChua = new Button(shlLinHDch, SWT.RADIO);
		btnSuaChua.setText("S\u1EEDa ch\u1EEFa - B\u1EA3o d\u01B0\u1EE1ng");
		new Label(shlLinHDch, SWT.NONE);

		btnThanhLy = new Button(shlLinHDch, SWT.RADIO);
		btnThanhLy.setText("Thanh l\u00FD");
		new Label(shlLinHDch, SWT.NONE);

		text = new Text(shlLinHDch, SWT.BORDER | SWT.RIGHT);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				try {
					switch (event.keyCode) {
					case SWT.CR:
						FillTable();
						break;
					case SWT.ESC:
						break;
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnTmKim = new Button(shlLinHDch, SWT.NONE);
		btnTmKim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					FillTable();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnTmKim.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnTmKim.setText("T\u00ECm ki\u1EBFm");

		table = new Table(shlLinHDch, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnNgunDch = new TableColumn(table, SWT.NONE);
		tblclmnTnNgunDch.setWidth(150);
		tblclmnTnNgunDch.setText("T\u00CAN NGU\u1ED2N D\u1ECACH V\u1EE4");

		TableColumn tblclmnGiiThiu = new TableColumn(table, SWT.NONE);
		tblclmnGiiThiu.setWidth(200);
		tblclmnGiiThiu.setText("GI\u1EDAI THI\u1EC6U");

		TableColumn tblclmnLinH = new TableColumn(table, SWT.NONE);
		tblclmnLinH.setWidth(150);
		tblclmnLinH.setText("LI\u00CAN H\u1EC6");

		lblKtQu = new Label(shlLinHDch, SWT.NONE);
		lblKtQu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblKtQu.setText("0 k\u1EBFt qu\u1EA3");

		Button btnng = new Button(shlLinHDch, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLinHDch.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

	protected void FillTable() throws SQLException {
		table.removeAll();
		lblKtQu.setText("0");
		if (btnMuaSamTiepnhan.getSelection()) {
			ArrayList<NGUONTANG> ntl = controler.getControl_NGUONTANG().get_All_NguonTangTaisan(text.getText());
			int i = 0;
			for (NGUONTANG nguontang : ntl) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { (i++) + "", nguontang.getTEN_NGUONTANG(), nguontang.getGIOI_THIEU(),
						nguontang.getLIEN_HE() });
			}
			lblKtQu.setText(i + " Kết quả");
		} else if (btnSuaChua.getSelection()) {
			ArrayList<NGUONSUACHUA_BAODUONG> nsbl = controler.getControl_NGUONSUACHUA_BAODUONG()
					.getAllData(text.getText());
			int i = 0;
			for (NGUONSUACHUA_BAODUONG nguontang : nsbl) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { (i++) + "", nguontang.getTEN_NGUONSUACHUA_BAODUONG(),
						nguontang.getGIOI_THIEU(), nguontang.getLIEN_HE() });
			}
			lblKtQu.setText(i + " Kết quả");

		} else if (btnThanhLy.getSelection()) {
			ArrayList<NGUONGIAM> ngl = controler.getControl_NGUONGIAM().get_AllNguonGiam(text.getText());
			int i = 0;
			for (NGUONGIAM nguontang : ngl) {
				TableItem tableItem = new TableItem(table, SWT.NONE);
				tableItem.setText(new String[] { (i++) + "", nguontang.getTEN_NGUONGIAM(), nguontang.getGIOI_THIEU(),
						nguontang.getLIEN_HE() });
			}
			lblKtQu.setText(i + " Kết quả");
		}
	}
}
