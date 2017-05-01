package View.AssetManagers.NguonSuachua_Baoduong;

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
import DAO.NGUONSUACHUA_BAODUONG;
import View.Template.FormTemplate;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class ChonNguonSuachua_Baoduong extends Dialog {

	private NGUONSUACHUA_BAODUONG result;
	protected Shell shlChnNgunSa;
	private Text text;
	private Table table;
	private static NGUOIDUNG user;
	private final Controler controler;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtManguon;
	private Text txtTennguon;
	private Text txtGioithieu;
	private Text txtLienhe;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ChonNguonSuachua_Baoduong(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		ChonNguonSuachua_Baoduong.user = user;
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
		shlChnNgunSa.open();
		shlChnNgunSa.layout();
		Display display = getParent().getDisplay();
		while (!shlChnNgunSa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return getResult();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlChnNgunSa = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlChnNgunSa.setImage(user.getIcondata().PhoneIcon);
		shlChnNgunSa.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlChnNgunSa);
		shlChnNgunSa.setText("Chọn Nguồn Sửa chữa - bảo dưỡng");
		shlChnNgunSa.setLayout(new GridLayout(3, false));

		text = new Text(shlChnNgunSa, SWT.BORDER);
		text.setMessage("Tìm kiếm");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		SashForm sashForm = new SashForm(shlChnNgunSa, SWT.NONE);
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
					NGUONSUACHUA_BAODUONG t = (NGUONSUACHUA_BAODUONG) items[0].getData();
					if (t != null) {
						txtManguon.setText(String.valueOf(t.getMA_NGUONSUACHUA_BAODUONG()));
						txtTennguon.setText(t.getTEN_NGUONSUACHUA_BAODUONG());
						txtLienhe.setText(t.getLIEN_HE());
						txtGioithieu.setText(t.getGIOI_THIEU());

					}
				}
			}
		});

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tblclmnMNgunSa = new TableColumn(table, SWT.NONE);
		tblclmnMNgunSa.setWidth(111);
		tblclmnMNgunSa.setText("MÃ NGUỒN SC-BD");

		TableColumn tblclmnTnNgunScbd = new TableColumn(table, SWT.NONE);
		tblclmnTnNgunScbd.setWidth(120);
		tblclmnTnNgunScbd.setText("TÊN NGUỒN SC-BD");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("LIÊN HỆ");

		Composite composite = formToolkit.createComposite(sashForm, SWT.NONE);
		formToolkit.paintBordersFor(composite);
		{
			TableWrapLayout twl_composite = new TableWrapLayout();
			twl_composite.numColumns = 2;
			composite.setLayout(twl_composite);
		}

		@SuppressWarnings("unused")
		Label lblNewLable = formToolkit.createLabel(composite, "Mã nguồn: ", SWT.NONE);

		txtManguon = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtManguon.setText("");
		txtManguon.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblLabel = formToolkit.createLabel(composite, "Tên nguồn: ", SWT.NONE);

		txtTennguon = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtTennguon.setText("");
		txtTennguon.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.TOP, 1, 1));

		@SuppressWarnings("unused")
		Label lblGioiThieu = formToolkit.createLabel(composite, "Giới thiệu: ", SWT.NONE);

		txtGioithieu = formToolkit.createText(composite, "New Text", SWT.NONE);
		TableWrapData twd_txtGioithieu = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP, 1, 1);
		twd_txtGioithieu.heightHint = 120;
		txtGioithieu.setLayoutData(twd_txtGioithieu);
		txtGioithieu.setText("");

		@SuppressWarnings("unused")
		Label lblLienHe = formToolkit.createLabel(composite, "Liên hệ: ", SWT.NONE);

		txtLienhe = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtLienhe.setText("");
		TableWrapData twd_txtLienhe = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB, 1, 1);
		twd_txtLienhe.heightHint = 120;
		txtLienhe.setLayoutData(twd_txtLienhe);
		sashForm.setWeights(new int[] { 1000, 618 });

		Button button_1 = new Button(shlChnNgunSa, SWT.NONE);
		button_1.setImage(user.getIcondata().addIcon);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Quanly_NguonSuachua_Baoduong qnb = new Quanly_NguonSuachua_Baoduong(shlChnNgunSa, SWT.DIALOG_TRIM,
							user);
					qnb.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Thêm");

		Button button = new Button(shlChnNgunSa, SWT.NONE);
		button.setImage(user.getIcondata().successIcon);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getSelection();
				if (item != null) {
					if (item.length > 0) {
						setResult((NGUONSUACHUA_BAODUONG) item[0].getData());
						shlChnNgunSa.dispose();
					}
				}
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Chọn");

		Button button_2 = new Button(shlChnNgunSa, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgunSa.dispose();
			}
		});
		GridData gd_button_2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		ArrayList<NGUONSUACHUA_BAODUONG> sbdl = controler.getControl_NGUONSUACHUA_BAODUONG().getAllData(text.getText());
		int i = 1;
		for (NGUONSUACHUA_BAODUONG nsb : sbdl) {
			TableItem tbi = new TableItem(table, SWT.NONE);
			tbi.setText(new String[] { "" + i, nsb.getMA_NGUONSUACHUA_BAODUONG() + "",
					nsb.getTEN_NGUONSUACHUA_BAODUONG(), nsb.getLIEN_HE() });
			tbi.setData(nsb);
			i++;
		}
	}

	public NGUONSUACHUA_BAODUONG getResult() {
		return result;
	}

	public void setResult(NGUONSUACHUA_BAODUONG result) {
		this.result = result;
	}
}
