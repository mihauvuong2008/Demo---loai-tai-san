package View.AssetManagers.Taikhoan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Taikhoan extends Dialog {

	protected Object result;
	protected Shell shlTiKhon;
	private Text text_Tentaikhoan;
	private Text text_TenNguoidung;
	private Text text_Matkhau;
	private Text text_Gioithieu;
	private Combo combo_Phongban;
	private NGUOIDUNG nd;
	private Label lblTnNgiDng;
	private Label lblMatKhau;
	private final Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Taikhoan(Shell parent, int style, NGUOIDUNG user, NGUOIDUNG nd) {
		super(parent, style);
		setText("SWT Dialog");
		this.nd = nd;
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlTiKhon.open();
		shlTiKhon.layout();
		Display display = getParent().getDisplay();
		while (!shlTiKhon.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @throws SQLException
	 */
	private void createContents() throws SQLException {
		shlTiKhon = new Shell(getParent(), getStyle());
		shlTiKhon.setImage(SWTResourceManager.getImage(Taikhoan.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlTiKhon.setSize(321, 397);
		shlTiKhon.setText("T\u00E0i kho\u1EA3n");
		shlTiKhon.setLayout(new GridLayout(3, false));
		setText("Quản lý tài khoản Người dùng");
		new FormTemplate().setCenterScreen(shlTiKhon);

		lblTnNgiDng = new Label(shlTiKhon, SWT.NONE);
		lblTnNgiDng.setText("T\u00EAn Ng\u01B0\u1EDDi d\u00F9ng:");

		text_TenNguoidung = new Text(shlTiKhon, SWT.BORDER);
		text_TenNguoidung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblMaTaiKhoan = new Label(shlTiKhon, SWT.NONE);
		lblMaTaiKhoan.setText("T\u00EAn T\u00E0i kho\u1EA3n:");

		text_Tentaikhoan = new Text(shlTiKhon, SWT.BORDER);
		text_Tentaikhoan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		lblMatKhau = new Label(shlTiKhon, SWT.NONE);
		lblMatKhau.setText("Mật khẩu:");

		text_Matkhau = new Text(shlTiKhon, SWT.BORDER);
		text_Matkhau.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblnV = new Label(shlTiKhon, SWT.NONE);
		lblnV.setText("\u0110\u01A1n v\u1ECB:");

		combo_Phongban = new Combo(shlTiKhon, SWT.READ_ONLY);
		combo_Phongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblGiiThiu = new Label(shlTiKhon, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu = new Text(shlTiKhon, SWT.BORDER);
		text_Gioithieu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		new Label(shlTiKhon, SWT.NONE);

		Button btnXong = new Button(shlTiKhon, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = (NGUOIDUNG) getNguoidung();
				shlTiKhon.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlTiKhon, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = null;
				shlTiKhon.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		loadData();
	}

	private void loadData() throws SQLException {
		ArrayList<PHONGBAN> pbl = controler.getControl_PHONGBAN().getAllDonvi();
		Fill_ItemData f = new Fill_ItemData();
		f.setComboBox_DONVI_NOIBO(combo_Phongban, pbl);
		if (nd != null) {
			text_Tentaikhoan.setText(nd.getTEN_TAI_KHOAN());
			text_Tentaikhoan.setEditable(false);
			text_TenNguoidung.setText(nd.getTEN_CAN_BO());
			text_Matkhau.setText("---");
			text_Matkhau.setEditable(false);
			text_Gioithieu.setText(nd.getGIOI_THIEU());
			text_Gioithieu.setEditable(false);
			for (int i = 0; i < pbl.size(); i++) {
				if (((PHONGBAN) combo_Phongban.getData(combo_Phongban.getItem(i))).getMA_PHONGBAN() == nd
						.getMA_PHONGBAN()) {
					combo_Phongban.select(i);
				}
			}
		} else {
			lblTnNgiDng.setText("Cấp tài khoản cho:");
			lblMatKhau.setText("Mật khẩu ban đầu: ");
		}
	}

	protected NGUOIDUNG getNguoidung() {
		NGUOIDUNG result = new NGUOIDUNG();
		result.setTEN_TAI_KHOAN(text_Tentaikhoan.getText());
		result.setTEN_CAN_BO(text_TenNguoidung.getText());
		result.setMAT_KHAU(text_Matkhau.getText());
		result.setGIOI_THIEU(text_Gioithieu.getText());
		result.setMA_PHONGBAN(((PHONGBAN) combo_Phongban.getData(combo_Phongban.getText())).getMA_PHONGBAN());
		return result;
	}
}
