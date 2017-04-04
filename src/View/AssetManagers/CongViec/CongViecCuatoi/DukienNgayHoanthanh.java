package View.AssetManagers.CongViec.CongViecCuatoi;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

public class DukienNgayHoanthanh extends Dialog {

	protected int result = 0;
	protected Shell shlDKinNgy;
	private Text text;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Date tHISDAY;
	private final MyDateFormat mdf = new MyDateFormat();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param tHISDAY
	 * @param user
	 */
	public DukienNgayHoanthanh(Shell parent, int style, Date tHISDAY) {
		super(parent, style);
		setText("SWT Dialog");
		this.tHISDAY = tHISDAY;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDKinNgy.open();
		shlDKinNgy.layout();
		Display display = getParent().getDisplay();
		while (!shlDKinNgy.isDisposed()) {
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
		shlDKinNgy = new Shell(getParent(), SWT.CLOSE | SWT.TITLE);
		shlDKinNgy.setImage(SWTResourceManager.getImage(DukienNgayHoanthanh.class, "/Calendar-icon (1).png"));
		shlDKinNgy.setSize(321, 393);
		shlDKinNgy.setText("D\u1EF1 ki\u1EBFn ng\u00E0y ho\u00E0n th\u00E0nh");
		shlDKinNgy.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shlDKinNgy);
		Label lblNgayBatDau = new Label(shlDKinNgy, SWT.NONE);
		GridData gd_lblNgayBatDau = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblNgayBatDau.verticalIndent = 3;
		lblNgayBatDau.setLayoutData(gd_lblNgayBatDau);
		lblNgayBatDau.setText("Ng\u00E0y b\u1EAFt \u0111\u1EA7u:");

		dateTime = new DateTime(shlDKinNgy, SWT.CALENDAR);
		dateTime.setEnabled(false);
		dateTime.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 2, 1));
		String date = mdf.getViewStringDate(tHISDAY);
		String[] par = date.split("-");
		dateTime.setDay(Integer.valueOf(par[0]));
		dateTime.setMonth(Integer.valueOf(par[1]) - 1);
		dateTime.setYear(Integer.valueOf(par[2]));

		Label lblNgyKtThc = new Label(shlDKinNgy, SWT.NONE);
		GridData gd_lblNgyKtThc = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblNgyKtThc.verticalIndent = 3;
		lblNgyKtThc.setLayoutData(gd_lblNgyKtThc);
		lblNgyKtThc.setText("Ng\u00E0y k\u1EBFt th\u00FAc:");

		dateTime_1 = new DateTime(shlDKinNgy, SWT.CALENDAR);
		dateTime_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Date begin = mdf.getDate(dateTime);
				Date end = mdf.getDate(dateTime_1);
				long t = end.getTime() - begin.getTime();
				long difDate = t / (1000 * 60 * 60 * 24);
				int value = (int) difDate;
				text.setText(value + "");
			}
		});
		dateTime_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 2, 1));

		Label lblTngSNgy = new Label(shlDKinNgy, SWT.NONE);
		lblTngSNgy.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTngSNgy.setText("T\u1ED5ng s\u1ED1 ng\u00E0y:");

		text = new Text(shlDKinNgy, SWT.BORDER | SWT.RIGHT);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
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
		text.setEditable(false);
		text.setText("0");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(shlDKinNgy, SWT.NONE);

		Button btnHonTt = new Button(shlDKinNgy, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = Integer.parseInt(text.getText());
				if (result == 0) {
					MessageBox m = new MessageBox(shlDKinNgy, SWT.ICON_WARNING);
					m.setText("Dự kiến ngày hoàn thành");
					m.setMessage("Ít nhất 01 ngày!");
					m.open();
				} else {
					shlDKinNgy.dispose();
				}
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Ho\u00E0n t\u1EA5t");

		Button btnng = new Button(shlDKinNgy, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDKinNgy.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

}
