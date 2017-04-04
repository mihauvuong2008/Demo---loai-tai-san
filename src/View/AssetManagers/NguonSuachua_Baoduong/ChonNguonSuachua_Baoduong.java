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
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import View.Template.FormTemplate;

public class ChonNguonSuachua_Baoduong extends Dialog {

	private NGUONSUACHUA_BAODUONG result;
	protected Shell shlChnNgunSa;
	private Text text;
	private Table table;
	private static NGUOIDUNG user;
	private Text lblMaNguonScbd;
	private Text lblTenNguonScbd;
	private Text label_Gioithieu;
	private Text label_Lienhe;
	private final Controler controler;

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
		shlChnNgunSa = new Shell(getParent(), getStyle());
		shlChnNgunSa.setImage(SWTResourceManager.getImage(ChonNguonSuachua_Baoduong.class, "/phone-icon.png"));
		shlChnNgunSa.setSize(650, 400);
		new FormTemplate().setCenterScreen(shlChnNgunSa);
		shlChnNgunSa.setText("Chọn Nguồn Sửa chữa - bảo dưỡng");
		shlChnNgunSa.setLayout(new GridLayout(4, false));

		SashForm sashForm = new SashForm(shlChnNgunSa, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

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
						lblMaNguonScbd.setText(String.valueOf(t.getMA_NGUONSUACHUA_BAODUONG()));
						lblTenNguonScbd.setText(t.getTEN_NGUONSUACHUA_BAODUONG());
						label_Lienhe.setText(t.getLIEN_HE());
						label_Gioithieu.setText(t.getGIOI_THIEU());

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

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblMNgunScbd = new Label(composite_1, SWT.NONE);
		lblMNgunScbd.setText("Mã Nguồn:");

		lblMaNguonScbd = new Text(composite_1, SWT.BORDER);
		lblMaNguonScbd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblMaNguonScbd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblTnNgunScbd = new Label(composite_1, SWT.NONE);
		lblTnNgunScbd.setText("Tên nguồn:");

		lblTenNguonScbd = new Text(composite_1, SWT.BORDER);
		lblTenNguonScbd.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblTenNguonScbd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_ = new Label(composite_1, SWT.NONE);
		label_.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		label_.setText("Giới thiệu:");

		label_Gioithieu = new Text(composite_1, SWT.BORDER | SWT.WRAP);
		label_Gioithieu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(composite_1, SWT.NONE);
		lblLinH.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblLinH.setText("Liên hệ:");

		label_Lienhe = new Text(composite_1, SWT.BORDER | SWT.WRAP);
		label_Lienhe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label_Lienhe.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1000, 618 });

		text = new Text(shlChnNgunSa, SWT.BORDER);
		text.setMessage("Tìm kiếm");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button button = new Button(shlChnNgunSa, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(ChonNguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
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
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Chọn");

		Button button_1 = new Button(shlChnNgunSa, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(ChonNguonSuachua_Baoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
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

		Button button_2 = new Button(shlChnNgunSa, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnNgunSa.dispose();
			}
		});
		GridData gd_button_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_2.widthHint = 75;
		button_2.setLayoutData(gd_button_2);
		button_2.setText("Đóng");
		init(text.getText());
	}

	private void init(String pattern) throws SQLException {
		ArrayList<NGUONSUACHUA_BAODUONG> sbdl = controler.getControl_NGUONSUACHUA_BAODUONG().getAllData(pattern);
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
