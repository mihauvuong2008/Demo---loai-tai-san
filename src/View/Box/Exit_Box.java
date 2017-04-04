package View.Box;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class Exit_Box extends Shell {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Exit_Box shell = new Exit_Box(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public Exit_Box(Display display) {
		super(display, SWT.CLOSE | SWT.TITLE);
		setLayout(new GridLayout(1, false));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblNewLabel = new Label(composite, SWT.CENTER);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 3);
		gd_lblNewLabel.widthHint = 108;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setImage(SWTResourceManager.getImage(Exit_Box.class, "/question_mark.png"));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		GridData gd_lblNewLabel_1 = new GridData(SWT.LEFT, SWT.CENTER, true, true, 2, 1);
		gd_lblNewLabel_1.horizontalIndent = 15;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		lblNewLabel_1.setText("B\u1EA1n mu\u1ED1n tho\u00E1t ch\u01B0\u01A1ng tr\u00ECnh?");

		Button btnNewButton = new Button(composite, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnNewButton.widthHint = 85;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setText("Tho\u00E1t");

		Button btnng = new Button(composite, SWT.NONE);
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 85;
		btnng.setLayoutData(gd_btnng);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		btnng.setText("\u0110\u00F3ng");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tho\u00E1t ch\u01B0\u01A1ng tr\u00ECnh");
		setSize(380, 200);
		Monitor primary = this.getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		setLocation(x, y);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
