package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import Controler.Controler;
import DAO.FILESCAN;
import DAO.HOSO_TAILEN;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

public class TailenTaphoso extends Dialog {

	protected Object result;
	protected Shell shlToTpH;
	private Text text;
	private Text text_1;
	private NGUOIDUNG user;
	private Controler controler;
	TAPHOSO ths;
	ArrayList<VANBAN> vbl;
	protected ArrayList<ArrayList<FILESCAN>> fscll;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public TailenTaphoso(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlToTpH.open();
		shlToTpH.layout();
		Display display = getParent().getDisplay();
		while (!shlToTpH.isDisposed()) {
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
		shlToTpH = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlToTpH.setImage(user.getIcondata().upload16);
		shlToTpH.setSize(565, 369);
		shlToTpH.setText("Tạo tập hồ sơ tải lên");
		shlToTpH.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shlToTpH);

		SashForm sashForm = new SashForm(shlToTpH, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblTnTpH = new Label(composite_1, SWT.NONE);
		lblTnTpH.setText("Tên tập hồ sơ: ");

		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblGiiThiuTp = new Label(composite_1, SWT.NONE);
		GridData gd_lblGiiThiuTp = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiuTp.verticalIndent = 3;
		lblGiiThiuTp.setLayoutData(gd_lblGiiThiuTp);
		lblGiiThiuTp.setText("Giới thiệu tập hồ sơ: ");

		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		List list = new List(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] { 134, 73 });

		Button btnThmVnBn = new Button(shlToTpH, SWT.NONE);
		btnThmVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TailenVanban tlv = new TailenVanban(shlToTpH, SWT.DIALOG_TRIM, user);
				tlv.open();
				if (vbl == null)
					vbl = new ArrayList<>();
				if (fscll == null)
					fscll = new ArrayList<>();

				VANBAN vb = (VANBAN) tlv.result;
				if (vb == null)
					return;
				vbl.add(vb);
				fscll.add(tlv.getFscl());
				list.removeAll();
				for (VANBAN vanban : vbl) {
					list.add(vanban.getSO_VANBAN() + " - "
							+ new MyDateFormat().getViewStringDate(vanban.getNGAY_BAN_HANH()) + " - "
							+ vanban.getTRICH_YEU());
					list.setData(vanban.getSO_VANBAN() + " - "
							+ new MyDateFormat().getViewStringDate(vanban.getNGAY_BAN_HANH()) + " - "
							+ vanban.getTRICH_YEU(), vanban);
				}
			}
		});
		btnThmVnBn.setImage(user.getIcondata().addIcon);
		btnThmVnBn.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		btnThmVnBn.setText("Thêm");

		Button btnHonTt = new Button(shlToTpH, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insertTaphoso();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shlToTpH.dispose();
			}

			private void insertTaphoso() throws SQLException {
				ths = new TAPHOSO();
				ths.setTEN_TAPHOSO(text.getText());
				ths.setGIOITHIEU_TAPHOSO(text_1.getText());
				int key = controler.getControl_TAPHOSO().Create_TAP_HO_SO(ths);
				if (vbl != null) {
					int i = 0;
					for (VANBAN vanban : vbl) {
						vanban.setMA_TAPHOSO(key);
						int key2 = controler.getControl_VANBAN().insert_VANBAN(vanban);
						if (fscll.size() > 0)
							if (fscll.get(i) != null) {
								ArrayList<FILESCAN> fsl = fscll.get(i);
								for (FILESCAN f : fsl) {
									f.setMA_VANBAN(key2);
									controler.getControl_FILESCAN().insert_IMGAGE(f);
								}
							}
						i++;
					}
				}
				HOSO_TAILEN hsct = getHOSO_CUATOI(key);
				if (key <= 0)
					return;
				controler.getControl_HOSO_CUATOI().insert_HOSO_CUATOI(hsct);

			}

			private HOSO_TAILEN getHOSO_CUATOI(int key) {
				HOSO_TAILEN rs = new HOSO_TAILEN();
				rs.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
				rs.setMA_TAPHOSO(key);
				return rs;
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Hoàn tất");

		Button btnng = new Button(shlToTpH, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlToTpH.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");

	}

}
