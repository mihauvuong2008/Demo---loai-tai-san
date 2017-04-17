package View.AssetManagers.Taisan.SuaThongTinTaisan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import DAO.PHUKIEN;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class Box_Phukien extends Dialog {

	protected Object result;
	protected Shell shlPhKin;
	private Text text_Nguyengia;
	private Text text_Tenphukien;
	private Text text_Model;
	private Text text_Seri;
	private PHUKIEN data;
	private Integer mA_TAISAN;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param mA_TAISAN
	 */
	public Box_Phukien(Shell parent, int style, Integer mA_TAISAN) {
		super(parent, style);
		setText("SWT Dialog");
		this.mA_TAISAN = mA_TAISAN;
	}

	public Box_Phukien(Shell parent, int style, PHUKIEN data) {
		super(parent, style);
		setText("SWT Dialog");
		this.data = data;
		mA_TAISAN = data.getMA_TAISAN();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlPhKin.open();
		shlPhKin.layout();
		Display display = getParent().getDisplay();
		while (!shlPhKin.isDisposed()) {
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
		shlPhKin = new Shell(getParent(), getStyle());
		shlPhKin.setImage(
				SWTResourceManager.getImage(Box_Phukien.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlPhKin.setSize(324, 167);
		shlPhKin.setText("Phụ kiện");
		shlPhKin.setLayout(new GridLayout(3, false));
		new FormTemplate().setCenterScreen(shlPhKin);

		Label lblTenPhuKien = new Label(shlPhKin, SWT.NONE);
		lblTenPhuKien.setText("Tên phụ kiện: ");

		text_Tenphukien = new Text(shlPhKin, SWT.BORDER);
		text_Tenphukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblModel = new Label(shlPhKin, SWT.NONE);
		lblModel.setText("Model:");

		text_Model = new Text(shlPhKin, SWT.BORDER);
		text_Model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblSeri = new Label(shlPhKin, SWT.NONE);
		lblSeri.setText("Seri:");

		text_Seri = new Text(shlPhKin, SWT.BORDER);
		text_Seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblNguynGi = new Label(shlPhKin, SWT.NONE);
		lblNguynGi.setText("Nguyên giá: ");

		text_Nguyengia = new Text(shlPhKin, SWT.BORDER);
		text_Nguyengia.addVerifyListener(new VerifyListener() {
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
		text_Nguyengia.setText("0");
		text_Nguyengia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(shlPhKin, SWT.NONE);

		Button btnHonTt = new Button(shlPhKin, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PHUKIEN pk = getPhukien();
				result = pk;
				shlPhKin.dispose();
			}

			private PHUKIEN getPhukien() {
				PHUKIEN rs = new PHUKIEN();
				if (data != null)
					rs.setMA_PHUKIEN(data.getMA_PHUKIEN());
				rs.setTEN_PHUKIEN(text_Tenphukien.getText());
				rs.setMODEL(text_Model.getText());
				rs.setSERI(text_Seri.getText());
				rs.setNGUYEN_GIA(Integer.valueOf(text_Nguyengia.getText()));
				rs.setMA_TAISAN(mA_TAISAN);
				return rs;
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Hoàn tất");

		Button btnng = new Button(shlPhKin, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlPhKin.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() {
		if (data != null) {
			text_Tenphukien.setText(data.getTEN_PHUKIEN());
			text_Model.setText(data.getMODEL());
			text_Seri.setText(data.getSERI());
			text_Nguyengia.setText(data.getNGUYEN_GIA() + "");
		}
	}

}
