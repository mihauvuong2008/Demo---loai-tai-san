package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InsertTaphoso extends Dialog {

	protected Object result;
	protected Shell shlToTpH;
	private Text text;
	private Text text_1;
	private NGUOIDUNG user;
	private Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public InsertTaphoso(Shell parent, int style, NGUOIDUNG user) {
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
		shlToTpH.setImage(
				SWTResourceManager.getImage(InsertTaphoso.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlToTpH.setSize(455, 244);
		shlToTpH.setText("Tạo tập hồ sơ tải lên");
		shlToTpH.setLayout(new GridLayout(3, false));

		Label lblTnTpH = new Label(shlToTpH, SWT.NONE);
		lblTnTpH.setText("Tên tập hồ sơ: ");

		text = new Text(shlToTpH, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblGiiThiuTp = new Label(shlToTpH, SWT.NONE);
		GridData gd_lblGiiThiuTp = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiuTp.verticalIndent = 3;
		lblGiiThiuTp.setLayoutData(gd_lblGiiThiuTp);
		lblGiiThiuTp.setText("Giới thiệu tập hồ sơ: ");

		text_1 = new Text(shlToTpH, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(shlToTpH, SWT.NONE);

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
				TAPHOSO ths = new TAPHOSO();
				ths.setTEN_TAPHOSO(text.getText());
				ths.setGIOITHIEU_TAPHOSO(text_1.getText());
				controler.getControl_TAPHOSO().Create_TAP_HO_SO(ths);
				// controler.getControl_HOSOCUATOI()
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.BOTTOM, true, true, 1, 1);
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
