package Application.Setting;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.nio.file.Paths;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import CONFIG.ConfigReader;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class Setting extends Dialog {

	protected Object result;
	protected Shell shlSetting;
	private Text text_IP;
	private Text text_PORT;
	private Text text_TimeDelay;
	private Combo combo_SSL;
	private HostSetting hs;
	ConfigReader CR;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Setting(Shell parent, int style) {
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
		shlSetting.open();
		shlSetting.layout();
		Display display = getParent().getDisplay();
		while (!shlSetting.isDisposed()) {
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
		shlSetting = new Shell(getParent(), getStyle());
		shlSetting.setSize(354, 177);
		shlSetting.setText("Setting");
		shlSetting.setLayout(new GridLayout(4, false));
		new FormTemplate().setCenterScreen(shlSetting);

		Label lblHostIp = new Label(shlSetting, SWT.NONE);
		lblHostIp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHostIp.setText("host IP: ");

		text_IP = new Text(shlSetting, SWT.BORDER);
		text_IP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblServerPort = new Label(shlSetting, SWT.NONE);
		lblServerPort.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblServerPort.setText("Server Port:");

		text_PORT = new Text(shlSetting, SWT.BORDER);
		text_PORT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		text_PORT.addVerifyListener(new VerifyListener() {
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
		Label lblSsl = new Label(shlSetting, SWT.NONE);
		lblSsl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSsl.setText("SSL: ");

		combo_SSL = new Combo(shlSetting, SWT.READ_ONLY);
		combo_SSL.setItems(new String[] { "true", "false" });
		combo_SSL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		combo_SSL.select(0);

		Label lblTimeDelay = new Label(shlSetting, SWT.NONE);
		lblTimeDelay.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTimeDelay.setText("Time Delay: ");

		text_TimeDelay = new Text(shlSetting, SWT.BORDER);
		text_TimeDelay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		text_TimeDelay.addVerifyListener(new VerifyListener() {
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
		new Label(shlSetting, SWT.NONE);

		Button btnLuCit = new Button(shlSetting, SWT.NONE);
		btnLuCit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hs.setIp(text_IP.getText());
				hs.setPort(Integer.valueOf(text_PORT.getText()));
				if (combo_SSL.getSelectionIndex() == 0) {
					hs.setSsl(true);
				} else {
					hs.setSsl(false);
				}
				hs.setTimeDelay(Integer.valueOf(text_TimeDelay.getText()));
				CR.SaveHostSetting(hs);
			}
		});
		btnLuCit.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, true, 1, 1));
		btnLuCit.setText("Lưu cài đặt");

		Button btnMtKhuD = new Button(shlSetting, SWT.NONE);
		btnMtKhuD.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangePassword cp = new ChangePassword(shlSetting, SWT.DIALOG_TRIM, hs);
				cp.open();
				if (cp.result != null) {
					hs = cp.result;
				}
			}
		});
		btnMtKhuD.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true, 1, 1));
		btnMtKhuD.setText("Mật khẩu Dữ liệu");

		Button btnng = new Button(shlSetting, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlSetting.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.FILL, false, true, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() {
		CR = new ConfigReader(Paths.get("./config/user/user.xml").toString(),
				Paths.get("./config/host/host.xml").toString());
		hs = CR.getHostSetting();
		text_IP.setText(hs.getIp());
		text_PORT.setText(hs.getPort() + "");
		if (hs.isSsl()) {
			combo_SSL.select(0);
		} else {
			combo_SSL.select(1);
		}
		text_TimeDelay.setText(hs.getTimeDelay() + "");
		System.out.println(hs.getPassword());
	}

}
