package View.AboutUs;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AboutUs extends Dialog {

	protected Object result;
	protected Shell shlVChngTi;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public AboutUs(Shell parent, int style) {
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
		shlVChngTi.open();
		shlVChngTi.layout();
		Display display = getParent().getDisplay();
		while (!shlVChngTi.isDisposed()) {
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
		shlVChngTi = new Shell(getParent(), getStyle());
		shlVChngTi.setImage(SWTResourceManager.getImage(AboutUs.class, "/thanhnienvietnam16.png"));
		shlVChngTi.setSize(420, 223);
		shlVChngTi.setText("Về chúng tôi");
		shlVChngTi.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlVChngTi);

		Label label = new Label(shlVChngTi, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 4);
		gd_label.horizontalIndent = 5;
		label.setLayoutData(gd_label);
		label.setImage(SWTResourceManager.getImage(AboutUs.class, "/thanhnienvietnam.png"));

		Label lblChion = new Label(shlVChngTi, SWT.NONE);
		GridData gd_lblChion = new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1);
		gd_lblChion.horizontalIndent = 10;
		lblChion.setLayoutData(gd_lblChion);
		lblChion.setText("Đội ngũ phát triển: Chi đoàn 3");

		Label lblCngTrnhThanh = new Label(shlVChngTi, SWT.NONE);
		GridData gd_lblCngTrnhThanh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblCngTrnhThanh.horizontalIndent = 10;
		lblCngTrnhThanh.setLayoutData(gd_lblCngTrnhThanh);
		lblCngTrnhThanh.setText("Project: Công trình thanh niên - năm 2017 (P1101)");

		Label lblLinHChi = new Label(shlVChngTi, SWT.NONE);
		GridData gd_lblLinHChi = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinHChi.horizontalIndent = 10;
		lblLinHChi.setLayoutData(gd_lblLinHChi);
		lblLinHChi.setText("Liên hệ: Chi đoàn 3");

		Label lblEmail = new Label(shlVChngTi, SWT.NONE);
		GridData gd_lblEmail = new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1);
		gd_lblEmail.horizontalIndent = 10;
		lblEmail.setLayoutData(gd_lblEmail);
		lblEmail.setText("Email: ");
		new Label(shlVChngTi, SWT.NONE);

		Button btnng = new Button(shlVChngTi, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVChngTi.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");

	}

}
