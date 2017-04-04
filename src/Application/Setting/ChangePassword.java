package Application.Setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import CONFIG.keyy;
import View.Template.FormTemplate;

public class ChangePassword extends Dialog {

	protected HostSetting result;
	protected Shell shlMtKhuD;
	private Text text_newPassword;
	private Text text_denewPassword;
	protected String Plaintext;
	protected String CipherText;
	protected String key = new keyy().getKey();// 128 bit
	// initVector; // 16 bytes IV
	private Text text_cipherText;
	HostSetting hs;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param hs
	 */
	public ChangePassword(Shell parent, int style, HostSetting hs) {
		super(parent, style);
		setText("SWT Dialog");
		this.hs = hs;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlMtKhuD.open();
		shlMtKhuD.layout();
		Display display = getParent().getDisplay();
		while (!shlMtKhuD.isDisposed()) {
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
		shlMtKhuD = new Shell(getParent(), getStyle());
		shlMtKhuD.setSize(419, 148);
		shlMtKhuD.setText("Mật khẩu dữ liệu");
		shlMtKhuD.setLayout(new GridLayout(4, false));
		new FormTemplate().setCenterScreen(shlMtKhuD);

		Label lblMtKhuC = new Label(shlMtKhuD, SWT.NONE);
		lblMtKhuC.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMtKhuC.setText("Mật khẩu cũ:");

		text_cipherText = new Text(shlMtKhuD, SWT.BORDER | SWT.READ_ONLY);
		text_cipherText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Button btnGiiM = new Button(shlMtKhuD, SWT.NONE);
		btnGiiM.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					viewPassword();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			private void viewPassword() {
				String key = "";
				keyinput ki = new keyinput(shlMtKhuD, SWT.DIALOG_TRIM);
				ki.open();
				if (ki.result != null) {
					key = (String) ki.result;
					CipherPassword cp = new CipherPassword();
					MessageBox m = new MessageBox(shlMtKhuD);
					System.out.println(hs.getInitVector());
					m.setMessage(cp.decrypt(key, hs.getInitVector(), text_cipherText.getText()));
					m.open();
				}
			}
		});
		btnGiiM.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnGiiM.setText("Giải mã");

		Label lblMtKhuMi = new Label(shlMtKhuD, SWT.NONE);
		lblMtKhuMi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMtKhuMi.setText("Mật khẩu mới: ");

		text_newPassword = new Text(shlMtKhuD, SWT.BORDER);
		text_newPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblXcThc = new Label(shlMtKhuD, SWT.NONE);
		lblXcThc.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblXcThc.setText("Xác thực: ");

		text_denewPassword = new Text(shlMtKhuD, SWT.BORDER);
		text_denewPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Button btnLoadKeyfile = new Button(shlMtKhuD, SWT.NONE);
		btnLoadKeyfile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				File keyfile = getKeyFile();
				loadKeyFile(keyfile);
			}
		});
		btnLoadKeyfile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnLoadKeyfile.setText("Load KeyFile");

		Button btnToMtKhu = new Button(shlMtKhuD, SWT.NONE);
		btnToMtKhu.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnToMtKhu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Plaintext = text_newPassword.getText();
				if (text_newPassword.getText().equals(text_denewPassword.getText())) {
					CipherPassword cp = new CipherPassword();
					// System.out.println(cp.decrypt(key, initVector,
					// cp.encrypt(key, initVector, "Hello World")));

					File keyfile = null;
					if (key == null) {
						keyfile = getKeyFile();
						loadKeyFile(keyfile);
					}
					if (keyfile != null || key != null) {
						SecureRandom random = new SecureRandom();
						String initVector = nextSessionId(random).substring(0, 16);
						String CipherText = cp.encrypt(key, initVector, Plaintext);
						MessageBox m = new MessageBox(shlMtKhuD);
						m.setMessage(CipherText + " - initVector: " + initVector);
						m.open();
					}
				}
			}

			public String nextSessionId(SecureRandom random) {
				return new BigInteger(128, random).toString(32);
			}
		});
		btnToMtKhu.setText("Tạo mật khẩu client");

		Button btnHonTt = new Button(shlMtKhuD, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Plaintext = text_newPassword.getText();
				if (text_newPassword.getText().equals(text_denewPassword.getText())) {
					CipherPassword cp = new CipherPassword();
					// System.out.println(cp.decrypt(key, initVector,
					// cp.encrypt(key, initVector, "Hello World")));

					File keyfile = null;
					if (key == null) {
						keyfile = getKeyFile();
						loadKeyFile(keyfile);
					}
					if (keyfile != null || key != null) {
						SecureRandom random = new SecureRandom();
						String initVector = nextSessionId(random).substring(0, 16);
						String CipherText = cp.encrypt(key, initVector, Plaintext);
						MessageBox m = new MessageBox(shlMtKhuD);
						m.setMessage(CipherText + " - initVector: " + initVector);
						m.open();
						hs.setPassword(CipherText);
						hs.setInitVector(initVector);
						result = hs;
						// try {
						// FileDialog dialog = new FileDialog(shlMtKhuD,
						// SWT.SAVE);
						// dialog.setFilterNames(new String[] { "text file",
						// "All Files (*.*)" });
						// dialog.setFilterExtensions(new String[] { "*.txt",
						// "*.*" });
						// dialog.setFilterPath("c:\\"); // Windows path
						// dialog.setFileName("key.txt");
						// String Path = dialog.open();
						// if (Path != null) {
						// PrintWriter out = new PrintWriter(Path);
						// out.println(key);
						// out.close();
						// }
						// } catch (FileNotFoundException e1) {
						// e1.printStackTrace();
						// }
						shlMtKhuD.dispose();
					}
				}
			}

			public String nextSessionId(SecureRandom random) {
				return new BigInteger(128, random).toString(32);
			}
		});
		btnHonTt.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true, 1, 1));
		btnHonTt.setText("Đổi mật khẩu");

		Button btnng = new Button(shlMtKhuD, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMtKhuD.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_btnng.widthHint = -1;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");

		init();

	}

	protected void loadKeyFile(File file) {
		if (file != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				int x = 0;
				loop: while ((line = br.readLine()) != null) {
					switch (x) {
					case 0:
						key = line.substring(0, 16);
						break;

					default:
						break loop;
					}
					x++;
				}
			} catch (IOException e1) {
				MessageBox m = new MessageBox(shlMtKhuD);
				m.setMessage("Lỗi đọc Secrect Key");
				e1.printStackTrace();
			}
		}
	}

	private void init() {
		text_cipherText.setText(hs.getPassword());
	}

	@SuppressWarnings("unused")
	public File getKeyFile() {
		FileDialog fd = new FileDialog(shlMtKhuD, SWT.OPEN | SWT.MULTI);
		fd.setText("Open");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.txt" };
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		if (selected != null) {
			String[] files = fd.getFileNames();
			for (int i = 0, n = files.length; i < n; i++) {
				// get file
				StringBuffer buf = new StringBuffer();
				buf.append(fd.getFilterPath());
				if (buf.charAt(buf.length() - 1) != File.separatorChar) {
					buf.append(File.separatorChar);
				}
				buf.append(files[i]);
				// load image to inpustream
				return new File(buf.toString());
			}
		}
		return null;
	}
}
