package View.Box;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import DAO.NGUOIDUNG;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

public class ErorrBox extends Dialog {

	static String title;
	static String message;
	private Shell shlErorrBox;
	private Object result;
	@SuppressWarnings("unused")
	private String log;
	@SuppressWarnings("unused")
	private int ErrorCode;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param code
	 * @param log
	 */
	public ErorrBox(Shell parent, int style, String title, String Message, String log, int code, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		OkBox.title = title;
		OkBox.message = Message;
		this.log = log;
		this.ErrorCode = code;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlErorrBox.open();
		shlErorrBox.layout();
		Display display = getParent().getDisplay();
		while (!shlErorrBox.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	private void createContents() {
		shlErorrBox = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.MAX);
		shlErorrBox
				.setImage(SWTResourceManager.getImage(ErorrBox.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlErorrBox.setSize(354, 200);
		if (title != null)
			setText(title);
		else
			setText("Th\u1EA5t b\u1EA1i");
		new FormTemplate().setCenterScreen(shlErorrBox);
		shlErorrBox.setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(shlErorrBox, SWT.CENTER);
		lblNewLabel.setImage(SWTResourceManager.getImage(ErorrBox.class, "/attention-icon.png"));
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 2));

		Label lblNewLabel_1 = new Label(shlErorrBox, SWT.NONE);
		GridData gd_lblNewLabel_1 = new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 2);
		gd_lblNewLabel_1.widthHint = 174;
		gd_lblNewLabel_1.horizontalIndent = 15;
		lblNewLabel_1.setLayoutData(gd_lblNewLabel_1);
		if (message != null)
			lblNewLabel_1.setText(message);
		else {
			lblNewLabel_1.setText("Hoạt động thất bại!");
		}
		GridData gd_btnNewButton_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_1.widthHint = 65;

		ExpandBar expandBar = new ExpandBar(shlErorrBox, SWT.V_SCROLL);
		expandBar.setSpacing(1);
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));

		ExpandItem xpndtmThmThngTin = new ExpandItem(expandBar, SWT.NONE);
		xpndtmThmThngTin.setImage(
				SWTResourceManager.getImage(ErorrBox.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
		xpndtmThmThngTin.setText("Thông tin lỗi");
		new Label(shlErorrBox, SWT.NONE);
		new Label(shlErorrBox, SWT.NONE);

		Button btnNewButton = new Button(shlErorrBox, SWT.NONE);
		GridData gd_btnNewButton_11 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton_11.widthHint = 75;
		btnNewButton.setLayoutData(gd_btnNewButton_11);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				shlErorrBox.dispose();
			}
		});
		btnNewButton.setLayoutData(gd_btnNewButton_11);
		btnNewButton.setText("Ok");
	}

}
