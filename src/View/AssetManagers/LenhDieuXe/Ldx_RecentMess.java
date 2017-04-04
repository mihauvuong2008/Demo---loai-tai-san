package View.AssetManagers.LenhDieuXe;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Ldx_RecentMess extends Dialog {

	protected Object result;
	protected Shell shell;
	private Table table;
	private TableColumn tblclmnGny;
	private Point p;
	private String title;
	private ArrayList<String> Message;
	private TableColumn tblclmnStt;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Ldx_RecentMess(Shell parent, int style, Point p, String title, ArrayList<String> Message) {
		super(parent, style);
		setText("SWT Dialog");
		this.title = title;
		this.Message = Message;
		this.p = p;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.CLOSE | SWT.TITLE);
		shell.setImage(
				SWTResourceManager.getImage(Ldx_RecentMess.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
		shell.setSize(485, 300);
		shell.setText(title);
		shell.setLayout(new GridLayout(1, false));

		Rectangle rect = shell.getBounds();
		int x1 = p.x + 180;
		int y1 = p.y - rect.height / 2;
		shell.setLocation(x1, y1);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				TableItem[] til = table.getSelection();
				if (til.length > 0) {
					result = til[0].getText(1);
					shell.dispose();
				}
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);

		tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		tblclmnGny = new TableColumn(table, SWT.NONE);
		tblclmnGny.setWidth(420);
		tblclmnGny.setText("G\u1EA7n \u0111\u00E2y");

		Button btnng = new Button(shell, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		loadData();
	}

	private void loadData() {
		table.removeAll();
		int i = 1;
		for (String t : Message) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] { i + "", t });
			i++;
		}

	}
}
