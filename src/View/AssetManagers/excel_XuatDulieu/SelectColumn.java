package View.AssetManagers.excel_XuatDulieu;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import View.Template.FormTemplate;

public class SelectColumn extends Dialog {

	protected Object result;
	protected Shell shlChnDLiu;
	private Table table_src;
	private Table table_dest;
	protected ColumnKeyCollector ckc;
	private ArrayList<LabelColumn> ListLabel;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public SelectColumn(Shell parent, int style, ArrayList<LabelColumn> ListLabel) {
		super(parent, style);
		setText("SWT Dialog");
		this.ListLabel = ListLabel;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlChnDLiu.open();
		shlChnDLiu.layout();
		Display display = getParent().getDisplay();
		while (!shlChnDLiu.isDisposed()) {
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
		shlChnDLiu = new Shell(getParent(), getStyle());
		shlChnDLiu.setSize(433, 389);
		shlChnDLiu.setText("Chọn dữ liệu xuất");
		shlChnDLiu.setLayout(new GridLayout(4, false));
		new FormTemplate().setCenterScreen(shlChnDLiu);

		Composite composite = new Composite(shlChnDLiu, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		table_src = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table_src.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		table_src.setHeaderVisible(true);
		table_src.setLinesVisible(true);

		TableColumn tblclmnTnCt = new TableColumn(table_src, SWT.NONE);
		tblclmnTnCt.setWidth(180);
		tblclmnTnCt.setText("TÊN CỘT");

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tbi[] = table_src.getSelection();
				if (tbi.length > 0) {

					for (TableItem ti : tbi) {
						int idx = table_src.indexOf(ti);
						TableItem ti2 = new TableItem(table_dest, SWT.NONE);
						ti2.setText(ti.getText());
						ti2.setData(ti.getData());
						table_src.remove(idx);
					}
				}
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1));
		btnNewButton.setText(">>");

		table_dest = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table_dest.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		table_dest.setHeaderVisible(true);
		table_dest.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table_dest, SWT.NONE);
		tableColumn.setWidth(180);
		tableColumn.setText("TÊN CỘT");

		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tbi[] = table_dest.getSelection();
				if (tbi.length > 0) {
					for (TableItem ti : tbi) {
						int idx = table_dest.indexOf(ti);
						TableItem ti2 = new TableItem(table_src, SWT.NONE);
						ti2.setText(ti.getText());
						ti2.setData(ti.getData());
						table_dest.remove(idx);
					}
				}
			}
		});
		button.setText("<<");

		Button btnXong = new Button(shlChnDLiu, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ckc = new ColumnKeyCollector();
				for (TableItem ti : table_dest.getItems()) {
					ckc.addColumn((String) ti.getData());
				}
				shlChnDLiu.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 3, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnDdongs = new Button(shlChnDLiu, SWT.NONE);
		btnDdongs.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnDLiu.dispose();
			}
		});
		GridData gd_btnDdongs = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnDdongs.widthHint = 75;
		btnDdongs.setLayoutData(gd_btnDdongs);
		btnDdongs.setText("Đóng");
		init();
	}

	private void init() {
		for (LabelColumn labelColumn : ListLabel) {
			creatLabel(labelColumn.getName(), labelColumn.getData());
		}
	}

	void creatLabel(String name, String Data) {
		TableItem tbi = new TableItem(table_src, SWT.NONE);
		tbi.setText(new String[] { name });
		tbi.setData(Data);
	}

	public final ColumnKeyCollector getCkc() {
		return ckc;
	}

}
