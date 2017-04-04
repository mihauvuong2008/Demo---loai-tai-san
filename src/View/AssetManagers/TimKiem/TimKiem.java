package View.AssetManagers.TimKiem;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class TimKiem extends Shell {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			TimKiem shell = new TimKiem(display);
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
	public TimKiem(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(TimKiem.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		setLayout(new GridLayout(1, false));

		Label lblChaM = new Label(this, SWT.NONE);
		lblChaM.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		lblChaM.setText("Ch\u01B0a m\u1EDF");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Ch\u1EE9c n\u0103ng ch\u01B0a m\u1EDF");
		setSize(450, 300);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
