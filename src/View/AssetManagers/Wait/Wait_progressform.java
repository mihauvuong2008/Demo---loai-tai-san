package View.AssetManagers.Wait;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Rectangle;

public class Wait_progressform extends Shell {
	private static ProgressBar progressBar;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Wait_progressform shell = new Wait_progressform(display);
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
	public Wait_progressform(Display display) {
		super(display, SWT.NONE | SWT.ON_TOP);
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 3;
		gridLayout.marginWidth = 3;
		gridLayout.marginHeight = 3;
		gridLayout.horizontalSpacing = 3;
		setLayout(gridLayout);
		progressBar = new ProgressBar(this, SWT.SMOOTH);
		GridData gd_progressBar = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_progressBar.heightHint = 23;
		progressBar.setLayoutData(gd_progressBar);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		createContents();
	}

	public static void setProgressbar(int INCREMENT, Display display) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				display.asyncExec(new Runnable() {
					public void run() {
						progressBar.setSelection(INCREMENT);
					}
				});
			}
			// This happens inside a different Thread
		}).start();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Load dữ liệu");
		setSize(577, 24);
		Monitor primary = Display.getDefault().getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = this.getBounds();
		int x = bounds.x + (bounds.width - rect.width - 20);
		int y = bounds.y + (bounds.height - rect.height - 40);
		setLocation(x, y);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
