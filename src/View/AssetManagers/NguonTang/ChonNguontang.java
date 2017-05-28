package View.AssetManagers.NguonTang;

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
import DAO.NGUONTANG;
import View.Template.FormTemplate;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class ChonNguontang extends Dialog {

	protected Object result;
	protected Shell shlChnNgunTng;
	private Table table;
	private Text text_4;
	private static NGUOIDUNG user;
	public NGUONTANG nt = null;
	private int Ma_NGUONTANG;
	private final Controler controler;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtMadonvi;
	private Text txtTendonvi;
	private Text txtGioithieu;
	private Text txtLienhe;

	public NGUONTANG getResult() {
		return nt;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param M
	 */
	public ChonNguontang(Shell parent, int style, NGUOIDUNG user, int Ma_NGUONTANG) {
		super(parent, style);
		setText("SWT Dialog");
		ChonNguontang.user = user;
		this.Ma_NGUONTANG = Ma_NGUONTANG;
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
		shlChnNgunTng.open();
		shlChnNgunTng.layout();
		Display display = getParent().getDisplay();
		while (!shlChnNgunTng.isDisposed()) {
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
		shlChnNgunTng = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlChnNgunTng.setImage(user.getIcondata().PhoneIcon);
		shlChnNgunTng.setSize(650, 400);
		// center position
		new FormTemplate().setCenterScreen(shlChnNgunTng);
		shlChnNgunTng.setText("Chọn Nguồn tăng");
		shlChnNgunTng.setLayout(new GridLayout(3, false));

		text_4 = new Text(shlChnNgunTng, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		SashForm sashForm = new SashForm(shlChnNgunTng, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table.getSelection();
				if (items.length > 0) {
					NGUONTANG t = (NGUONTANG) items[0].getData();
					if (t != null) {

						txtMadonvi.setText(String.valueOf(t.getMA_NGUONTANG()));
						txtTendonvi.setText(t.getTEN_NGUONTANG());
						txtLienhe.setText(t.getLIEN_HE());
						txtGioithieu.setText(t.getGIOI_THIEU());

					}
				}
			}
		});
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(111);
		tableColumn_1.setText("MÃ NGUỒN TĂNG");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(120);
		tableColumn_2.setText("TÊN NGUỒN TĂNG");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("LIÊN HỆ");

		Composite composite_1 = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite_1);
		{
			TableWrapLayout twl_composite_1 = new TableWrapLayout();
			twl_composite_1.numColumns = 2;
			composite_1.setLayout(twl_composite_1);
		}

		@SuppressWarnings("unused")
		Label lblMnV = formToolkit.createLabel(composite_1, "Mã đơn vị:", SWT.NONE);

		txtMadonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtMadonvi.setText("");
		txtMadonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblTnnV = formToolkit.createLabel(composite_1, "Tên đơn vị: ", SWT.NONE);

		txtTendonvi = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtTendonvi.setText("");
		txtTendonvi.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblGiiThiu = formToolkit.createLabel(composite_1, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtGioithieu.setText("");
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);

		@SuppressWarnings("unused")
		Label lblLinH = formToolkit.createLabel(composite_1, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite_1, "New Text", SWT.NONE);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);
		sashForm.setWeights(new int[] { 1000, 618 });

		Button btnMRng = new Button(shlChnNgunTng, SWT.NONE);
		btnMRng.setImage(user.getIcondata().addIcon);
		btnMRng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					QuanLy_NguonTang q = new QuanLy_NguonTang(shlChnNgunTng.getDisplay(), user);
					q.open();
					fillTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnMRng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnMRng.widthHint = 75;
		btnMRng.setLayoutData(gd_btnMRng);
		btnMRng.setText("Thêm");

		Button btnChn = new Button(shlChnNgunTng, SWT.NONE);
		btnChn.setImage(user.getIcondata().successIcon);
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getSelection();
				if (item != null) {
					if (item.length > 0) {
						nt = (NGUONTANG) item[0].getData();
						shlChnNgunTng.dispose();
					}
				}
			}
		});
		btnChn.setText("Chọn");

		Button btnng = new Button(shlChnNgunTng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgunTng.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		fillTable();
	}

	private void fillTable() throws SQLException {
		table.removeAll();

		if (Ma_NGUONTANG <= 0) {
			ArrayList<NGUONTANG> ntl = controler.getControl_NGUONTANG().get_All_NguonTangTaisan(text_4.getText());
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
		} else {
			NGUONTANG nt = controler.getControl_NGUONTANG().get_NguonTangTaisan(Ma_NGUONTANG);
			TableItem t = new TableItem(table, SWT.NONE);
			t.setText(0, "" + 1);
			t.setText(1, String.valueOf(nt.getMA_NGUONTANG()));
			t.setText(2, nt.getTEN_NGUONTANG());
			t.setText(3, nt.getLIEN_HE());
			t.setData(nt);
		}

		for (TableColumn t : table.getColumns()) {
			t.pack();
		}

	}

}
