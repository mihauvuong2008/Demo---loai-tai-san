package View.AssetManagers.Wait;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

public class Wait extends Shell {
	private ProgressBar progressBar;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Wait shell = new Wait(display);
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
	public Wait(Display display) {
		super(display, SWT.CLOSE | SWT.TITLE | SWT.ON_TOP);
		setImage(SWTResourceManager.getImage(Wait.class, "/Wait-icon.png"));
		setLayout(new GridLayout(1, false));

		progressBar = new ProgressBar(this, SWT.NONE);
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Ch\u1EDD");
		setSize(575, 60);
		Monitor primary = getDisplay().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		setLocation(x, y);
		LongRunningOperation lr = new LongRunningOperation(getShell().getDisplay(), progressBar);
		lr.start();
		System.out.println("thread start");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
