package View.AssetManagers.CongViec.TangTaiSan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import DAO.PHUKIEN;
import DAO.TAISAN;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class InsertPhukien extends Dialog {

	protected Object result;
	protected Shell shlThngTinPh;
	private Text text_Tentaisan;
	private Text text_ModelTaisan;
	private Text text_NguyengiaTaisan;
	private Text text_Tenphukien;
	private Text text_ModelPhukien;
	private Text text_SeriPhukien;
	private Text text_Nguyengia;
	private TAISAN taisan;
	private PHUKIEN phukien;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public InsertPhukien(Shell parent, int style, TAISAN taisan, PHUKIEN phukien) {
		super(parent, style);
		setText("SWT Dialog");
		this.taisan = taisan;
		this.phukien = phukien;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlThngTinPh.open();
		shlThngTinPh.layout();
		Display display = getParent().getDisplay();
		while (!shlThngTinPh.isDisposed()) {
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
		shlThngTinPh = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN);
		shlThngTinPh.setImage(SWTResourceManager.getImage(InsertPhukien.class, "/plugin-add-icon.png"));
		shlThngTinPh.setSize(453, 280);
		new FormTemplate().setCenterScreen(shlThngTinPh);
		shlThngTinPh.setText("Th\u00F4ng tin ph\u1EE5 ki\u1EC7n");
		shlThngTinPh.setLayout(new GridLayout(2, false));

		SashForm sashForm = new SashForm(shlThngTinPh, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Group grpThngTinTi = new Group(sashForm, SWT.NONE);
		grpThngTinTi.setText("Th\u00F4ng tin t\u00E0i s\u1EA3n");
		grpThngTinTi.setLayout(new GridLayout(2, false));

		Label lblTnTiSn = new Label(grpThngTinTi, SWT.NONE);
		lblTnTiSn.setText("T\u00EAn t\u00E0i s\u1EA3n:");

		text_Tentaisan = new Text(grpThngTinTi, SWT.NONE);
		text_Tentaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tentaisan.setEditable(false);
		text_Tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblModel = new Label(grpThngTinTi, SWT.NONE);
		lblModel.setText("Model:");

		text_ModelTaisan = new Text(grpThngTinTi, SWT.NONE);
		text_ModelTaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_ModelTaisan.setEditable(false);
		text_ModelTaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNguynGi = new Label(grpThngTinTi, SWT.NONE);
		GridData gd_lblNguynGi = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNguynGi.widthHint = 80;
		lblNguynGi.setLayoutData(gd_lblNguynGi);
		lblNguynGi.setText("Nguy\u00EAn gi\u00E1:");

		text_NguyengiaTaisan = new Text(grpThngTinTi, SWT.NONE);
		text_NguyengiaTaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_NguyengiaTaisan.setEditable(false);
		text_NguyengiaTaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblTnPhKin = new Label(composite, SWT.NONE);
		GridData gd_lblTnPhKin = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTnPhKin.verticalIndent = 3;
		gd_lblTnPhKin.widthHint = 80;
		lblTnPhKin.setLayoutData(gd_lblTnPhKin);
		lblTnPhKin.setText("T\u00EAn ph\u1EE5 ki\u1EC7n: ");

		text_Tenphukien = new Text(composite, SWT.BORDER);
		text_Tenphukien.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label label = new Label(composite, SWT.NONE);
		label.setText("Model:");

		text_ModelPhukien = new Text(composite, SWT.BORDER);
		text_ModelPhukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSeri = new Label(composite, SWT.NONE);
		lblSeri.setText("Seri:");

		text_SeriPhukien = new Text(composite, SWT.BORDER);
		text_SeriPhukien.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNguynGi_1 = new Label(composite, SWT.NONE);
		lblNguynGi_1.setText("Nguy\u00EAn gi\u00E1:");

		text_Nguyengia = new Text(composite, SWT.BORDER | SWT.RIGHT);
		text_Nguyengia.setText("0");
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
		text_Nguyengia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		sashForm.setWeights(new int[] { 88, 120 });

		Button btnHonTt = new Button(shlThngTinPh, SWT.NONE);
		btnHonTt.setImage(SWTResourceManager.getImage(InsertPhukien.class, "/success-icon.png"));
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadPhukien();
				result = phukien;
				shlThngTinPh.dispose();
			}

			private void loadPhukien() {
				if (phukien == null)
					phukien = new PHUKIEN();
				phukien.setTEN_PHUKIEN(text_Tenphukien.getText());
				phukien.setMODEL(text_ModelPhukien.getText());
				phukien.setSERI(text_SeriPhukien.getText());
				phukien.setNGUYEN_GIA(Integer.valueOf(text_Nguyengia.getText()));
				phukien.setMA_TAISAN(taisan.getMA_TAISAN());

			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Xong");

		Button btnng = new Button(shlThngTinPh, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = phukien;
				shlThngTinPh.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		loadData();
	}

	private void loadData() {
		if (taisan != null) {
			text_Tentaisan.setText(taisan.getTEN_TAISAN());
			text_ModelTaisan.setText(taisan.getMODEL());
			text_NguyengiaTaisan.setText(taisan.getNGUYEN_GIA() + "");
		}
		if (phukien != null) {
			text_Tenphukien.setText(phukien.getTEN_PHUKIEN());
			text_ModelPhukien.setText(phukien.getMODEL());
			text_SeriPhukien.setText(phukien.getSERI());
			text_Nguyengia.setText(phukien.getNGUYEN_GIA() + "");
		}
	}
}
