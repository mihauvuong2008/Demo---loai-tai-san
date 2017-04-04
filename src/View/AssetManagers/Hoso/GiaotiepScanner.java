package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GiaotiepScanner extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public GiaotiepScanner(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(514, 300);
		shell.setText(getText());
		shell.setLayout(new GridLayout(1, false));

		Group grpCom = new Group(shell, SWT.NONE);
		grpCom.setLayout(new GridLayout(3, false));
		grpCom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpCom.setText("Config");

		Label lblComport = new Label(grpCom, SWT.NONE);
		lblComport.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComport.setText("Comport:");

		Combo combo = new Combo(grpCom, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnKtNi = new Button(grpCom, SWT.NONE);
		btnKtNi.setText("K\u1EBFt n\u1ED1i");

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

	}
}
