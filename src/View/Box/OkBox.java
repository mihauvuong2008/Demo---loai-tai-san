package View.Box;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

public class OkBox extends Shell {
	static String title;
	static String message;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			OkBox shell = new OkBox(display, title, message);
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
	public OkBox(Display display, String title, String Message) {
		super(display, SWT.CLOSE | SWT.TITLE);
		if (title != null)
			setText(title);
		else
			setText("OK");
		setSize(320, 180);
		setLayout(new GridLayout(3, false));
		OkBox.title = title;
		OkBox.message = Message;

		Label label = new Label(this, SWT.CENTER);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 3);
		gd_label.widthHint = 85;
		label.setLayoutData(gd_label);
		label.setImage(SWTResourceManager.getImage(OkBox.class, "/Accept-icon.png"));
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);

		Label lblHoanfTaa = new Label(this, SWT.NONE);
		GridData gd_lblHoanfTaa = new GridData(SWT.LEFT, SWT.CENTER, true, true, 2, 1);
		gd_lblHoanfTaa.horizontalIndent = 15;
		lblHoanfTaa.setLayoutData(gd_lblHoanfTaa);
		lblHoanfTaa.setText("Thành công!");
		lblHoanfTaa.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		new Label(this, SWT.NONE);

		Button btnOk = new Button(this, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dispose();
			}
		});
		GridData gd_btnOk = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnOk.widthHint = 65;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("OK");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
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
