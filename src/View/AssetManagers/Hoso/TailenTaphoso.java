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

	public Object result;
	protected Shell shlTailenTapHoso;
	private Text text;
	private Text text_1;
	private NGUOIDUNG user;
	private Controler controler;
	ArrayList<VANBAN> vbl;
	protected ArrayList<ArrayList<FILESCAN>> fscll;
	private TAPHOSO ths_tmp;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @wbp.parser.constructor
	 */
	public TailenTaphoso(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		controler = new Controler(user);
	}

	public TailenTaphoso(Shell parent, int style, NGUOIDUNG user, TAPHOSO ths_tmp) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		controler = new Controler(user);
		this.ths_tmp = ths_tmp;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlTailenTapHoso.open();
		shlTailenTapHoso.layout();
		Display display = getParent().getDisplay();
		while (!shlTailenTapHoso.isDisposed()) {
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
		shlTailenTapHoso = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlTailenTapHoso.setImage(user.getIcondata().upload16);
		shlTailenTapHoso.setSize(597, 369);
		shlTailenTapHoso.setText("Tạo tập hồ sơ tải lên");
		shlTailenTapHoso.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shlTailenTapHoso);

		SashForm sashForm = new SashForm(shlTailenTapHoso, SWT.VERTICAL);
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

		Button btnThmVnBn = new Button(shlTailenTapHoso, SWT.NONE);
		btnThmVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TailenVanban tlv = new TailenVanban(shlTailenTapHoso, SWT.DIALOG_TRIM, user);
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

		Button btnHonTt = new Button(shlTailenTapHoso, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					insertTaphoso();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shlTailenTapHoso.dispose();
			}

			private void insertTaphoso() throws SQLException {
				TAPHOSO ths = new TAPHOSO();
				ths.setTEN_TAPHOSO(text.getText());
				ths.setGIOITHIEU_TAPHOSO(text_1.getText());
				ths.setNGAY_TAO_TAPHOSO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
				int key = controler.getControl_TAPHOSO().Create_TAP_HO_SO(ths);
				if (key <= 0)
					return;
				result = ths;
				ths.setMA_TAPHOSO(key);
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

			}

		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.BOTTOM, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Hoàn tất");

		Button btnng = new Button(shlTailenTapHoso, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlTailenTapHoso.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.BOTTOM, false, true, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() {
		if (ths_tmp == null)
			return;
		text.setText(ths_tmp.getTEN_TAPHOSO());
		text_1.setText(ths_tmp.getGIOITHIEU_TAPHOSO());
	}

}
