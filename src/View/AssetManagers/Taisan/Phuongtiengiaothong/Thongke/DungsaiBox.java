package View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class DungsaiBox extends Dialog {

	protected Object result;
	protected Shell shlDungSai;
	private Text text;
	private Button btnng;
	private Button btnXong;
	private int dungsai;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param dungsai
	 */
	public DungsaiBox(Shell parent, int style, int dungsai) {
		super(parent, style);
		setText("SWT Dialog");
		this.dungsai = dungsai;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDungSai.open();
		shlDungSai.layout();
		Display display = getParent().getDisplay();
		while (!shlDungSai.isDisposed()) {
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
		shlDungSai = new Shell(getParent(), getStyle());
		shlDungSai.setSize(328, 91);
		new FormTemplate().setCenterScreen(shlDungSai);
		shlDungSai.setText("Dung sai");
		shlDungSai.setLayout(new GridLayout(3, false));

		Label lblNhpDungSai = new Label(shlDungSai, SWT.NONE);
		lblNhpDungSai.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, true, 1, 1));
		lblNhpDungSai.setText("Nh\u1EADp dung sai nhi\u00EAn li\u1EC7u: ");

		text = new Text(shlDungSai, SWT.BORDER | SWT.RIGHT);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.selectAll();
			}
		});
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		text.setText(dungsai + "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(shlDungSai, SWT.NONE);

		btnXong = new Button(shlDungSai, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = Integer.valueOf(text.getText());
				shlDungSai.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		btnng = new Button(shlDungSai, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDungSai.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

}
