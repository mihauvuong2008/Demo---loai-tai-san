package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

import DAO.FILESCAN;
import DAO.NGUOIDUNG;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TailenVanban extends Dialog {

	protected Object result;
	protected Shell shlVnBn;
	private Text text_Sovanban;
	private Text text_Donvibanhanh;
	private Text text_Trichyeu;
	private DateTime dateTime;
	private ArrayList<FILESCAN> fscl;
	private MyDateFormat mdf = new MyDateFormat();
	private NGUOIDUNG user;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public TailenVanban(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlVnBn.open();
		shlVnBn.layout();
		Display display = getParent().getDisplay();
		while (!shlVnBn.isDisposed()) {
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
		shlVnBn = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlVnBn.setImage(user.getIcondata().xemVanbanIcon);
		shlVnBn.setSize(485, 300);
		shlVnBn.setText("Văn bản");
		shlVnBn.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shlVnBn);

		Label lblSVnBn = new Label(shlVnBn, SWT.NONE);
		lblSVnBn.setText("Số văn bản: ");

		text_Sovanban = new Text(shlVnBn, SWT.BORDER);
		text_Sovanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblnVBan = new Label(shlVnBn, SWT.NONE);
		lblnVBan.setText("Đơn vị ban hành: ");

		text_Donvibanhanh = new Text(shlVnBn, SWT.BORDER);
		text_Donvibanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblNgyBanHnh = new Label(shlVnBn, SWT.NONE);
		lblNgyBanHnh.setText("Ngày ban hành: ");

		dateTime = new DateTime(shlVnBn, SWT.BORDER);
		new Label(shlVnBn, SWT.NONE);

		Label lblTrchYu = new Label(shlVnBn, SWT.NONE);
		GridData gd_lblTrchYu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTrchYu.verticalIndent = 3;
		lblTrchYu.setLayoutData(gd_lblTrchYu);
		lblTrchYu.setText("Trích yếu: ");

		text_Trichyeu = new Text(shlVnBn, SWT.BORDER);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(shlVnBn, SWT.NONE);

		Button btnXong = new Button(shlVnBn, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VANBAN vb = new VANBAN();
				vb.setSO_VANBAN(text_Sovanban.getText());
				vb.setNGAY_BAN_HANH(mdf.getDate(dateTime));
				vb.setCO_QUAN_BAN_HANH(text_Donvibanhanh.getText());
				vb.setTRICH_YEU(text_Trichyeu.getText());
				result = vb;
				shlVnBn.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlVnBn, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlVnBn.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() {
		fscl = chooseFile();
	}

	private ArrayList<FILESCAN> chooseFile() {
		FileDialog fd = new FileDialog(shlVnBn, SWT.OPEN | SWT.MULTI);
		fd.setText("Open");
		fd.setFilterPath("C:/");
		String[] filterExt = { "*.jpg" };
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		ArrayList<FILESCAN> FILESCAN_l = new ArrayList<>();
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
				// System.out.println(files[i]);
				// load image to inpustream
				BufferedImage image = null;
				ByteArrayOutputStream os = new ByteArrayOutputStream() {
					@Override
					public synchronized byte[] toByteArray() {
						return this.buf;
					}
				};
				File fileImage = new File(buf.toString());
				try {
					image = ImageIO.read(fileImage);
					ImageIO.write(image, "jpg", os);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// input stream to file scan
				InputStream fis = new ByteArrayInputStream(os.toByteArray());
				// System.out.println(fis.read());
				FILESCAN f = new FILESCAN();
				f.setImage_name(files[i]);
				f.setImage(fis);
				f.setImage_size(fileImage.length());
				f.setNgaytao(new Date());
				FILESCAN_l.add(f);
			}
		}
		return FILESCAN_l;
	}

	public final ArrayList<FILESCAN> getFscl() {
		return fscl;
	}

	public final void setFscl(ArrayList<FILESCAN> fscl) {
		this.fscl = fscl;
	}

}
