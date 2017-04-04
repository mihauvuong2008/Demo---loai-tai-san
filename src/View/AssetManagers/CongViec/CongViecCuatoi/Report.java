package View.AssetManagers.CongViec.CongViecCuatoi;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import View.Template.FormTemplate;

public class Report extends Dialog {

	protected String result;
	protected Shell shlReport;
	private Text text;
	private String oldString;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param oldString
	 */
	public Report(Shell parent, int style, String oldString) {
		super(parent, style);
		setText("SWT Dialog");
		this.oldString = oldString;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlReport.open();
		shlReport.layout();
		Display display = getParent().getDisplay();
		while (!shlReport.isDisposed()) {
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
		shlReport = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shlReport.setImage(SWTResourceManager.getImage(Report.class, "/Distributor-report-icon.png"));
		shlReport.setSize(485, 300);
		new FormTemplate().setCenterScreen(shlReport);
		shlReport.setText("Vi\u1EBFt ghi ch\u00FA");
		shlReport.setLayout(new GridLayout(2, false));

		text = new Text(shlReport, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
		});
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		text.setText(oldString == null ? "" : oldString);

		Button btnHonTt = new Button(shlReport, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = text.getText();
				shlReport.dispose();
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Ho\u00E0n t\u1EA5t");

		Button btnNewButton = new Button(shlReport, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = null;
				shlReport.dispose();
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 75;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setText("\u0110\u00F3ng");

	}
}
