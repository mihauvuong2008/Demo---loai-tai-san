package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.HOSO_DAGUI;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;
import View.AssetManagers.Taikhoan.Chontaikhoan;
import View.Template.FormTemplate;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GuiTaphoso extends Dialog {

	protected Object result;
	protected Shell shlGiVnBn;
	private Table table;
	Controler controler;
	private NGUOIDUNG user;
	private ArrayList<TAPHOSO> thsl;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 * @param thsl
	 */
	public GuiTaphoso(Shell parent, int style, NGUOIDUNG user, ArrayList<TAPHOSO> thsl) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.user = user;
		this.thsl = thsl;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlGiVnBn.open();
		shlGiVnBn.layout();
		Display display = getParent().getDisplay();
		while (!shlGiVnBn.isDisposed()) {
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
		shlGiVnBn = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlGiVnBn
				.setImage(SWTResourceManager.getImage(GuiTaphoso.class, "/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlGiVnBn.setSize(599, 386);
		shlGiVnBn.setText("Gửi Văn bản");
		shlGiVnBn.setLayout(new GridLayout(3, false));

		new FormTemplate().setCenterScreen(shlGiVnBn);

		Label lblGiTi = new Label(shlGiVnBn, SWT.NONE);
		GridData gd_lblGiTi = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiTi.verticalIndent = 3;
		lblGiTi.setLayoutData(gd_lblGiTi);
		lblGiTi.setText("Gửi tới:");

		List list = new List(shlGiVnBn, SWT.BORDER | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(shlGiVnBn, SWT.NONE);
		new Label(shlGiVnBn, SWT.NONE);

		Button btnChn = new Button(shlGiVnBn, SWT.NONE);
		btnChn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Chontaikhoan ctk = new Chontaikhoan(shlGiVnBn, SWT.DIALOG_TRIM, user);
				try {
					ctk.open();
					ArrayList<NGUOIDUNG> ndl = (ArrayList<NGUOIDUNG>) ctk.getResult();
					if (ndl == null)
						return;
					list.removeAll();
					for (NGUOIDUNG nguoidung : ndl) {
						list.add(nguoidung.getTEN_TAI_KHOAN());
						list.setData(nguoidung.getTEN_TAI_KHOAN(), nguoidung);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		GridData gd_btnChn = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnChn.widthHint = 75;
		btnChn.setLayoutData(gd_btnChn);
		btnChn.setText("Chọn");

		table = new Table(shlGiVnBn, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnTpH = new TableColumn(table, SWT.NONE);
		tblclmnTnTpH.setWidth(200);
		tblclmnTnTpH.setText("TÊN TẬP HỒ SƠ");

		TableColumn tblclmnGiiThiu = new TableColumn(table, SWT.NONE);
		tblclmnGiiThiu.setWidth(250);
		tblclmnGiiThiu.setText("Giới thiệu");

		Button btnXong = new Button(shlGiVnBn, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (table.getItems().length <= 0)
					return;
				if (list.getItems().length <= 0)
					return;
				try {
					for (TableItem ti : table.getItems()) {
						TAPHOSO ths = (TAPHOSO) ti.getData();
						HOSO_DAGUI hsdg = getTaphosoDagui(ths);
						int key = controler.getControl_HOSO_DAGUI().insert_HOSO_DAGUI(hsdg);
						for (String tentaikhoan : list.getItems()) {
							controler.getControl_HOSO_DAGUI().send(key, tentaikhoan);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shlGiVnBn.dispose();
			}

			private HOSO_DAGUI getTaphosoDagui(TAPHOSO ths) {
				HOSO_DAGUI hsdg = new HOSO_DAGUI();
				hsdg.setMA_TAPHOSO(ths.getMA_TAPHOSO());
				hsdg.setNGAY_GUI(new Date());
				hsdg.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
				return hsdg;
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlGiVnBn, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlGiVnBn.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();

	}

	private void init() {
		fillTaphosoTable();
	}

	private void fillTaphosoTable() {
		table.removeAll();
		int i = 1;
		for (TAPHOSO taphoso : thsl) {
			TableItem tbi = new TableItem(table, SWT.NONE);
			tbi.setText(new String[] { (i++) + "", taphoso.getTEN_TAPHOSO(), taphoso.getGIOITHIEU_TAPHOSO() });
			tbi.setData(taphoso);
		}
	}
}
