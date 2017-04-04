package Application.Setting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import View.Template.FormTemplate;

public class keyinput extends Dialog {

	protected Object result;
	protected Shell shlNhpKha;
	private Text text;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public keyinput(Shell parent, int style) {
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
		shlNhpKha.open();
		shlNhpKha.layout();
		Display display = getParent().getDisplay();
		while (!shlNhpKha.isDisposed()) {
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
		shlNhpKha = new Shell(getParent(), getStyle());
		shlNhpKha.setSize(450, 63);
		shlNhpKha.setText("Nhập khóa");
		shlNhpKha.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlNhpKha);

		text = new Text(shlNhpKha, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Button btnNhp = new Button(shlNhpKha, SWT.NONE);
		btnNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = text.getText();
				shlNhpKha.dispose();
			}
		});
		btnNhp.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		btnNhp.setText("xong");

	}

}
