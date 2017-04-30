package View.AssetManagers.Hoso;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;

import Controler.Controler;
import DAO.HOSO_DAGUI;
import DAO.HOSO_DANHAN;
import DAO.HOSO_USER;
import DAO.NGUOIDUNG;
import DAO.NGUOINHAN_HOSO_DAGUI;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NhanTaphoso extends Dialog {

	protected Object result;
	protected Shell shlNhnHS;
	private Table table;
	private NGUOIDUNG user;
	private ArrayList<HOSO_USER> hsrl;
	private Controler controler;
	MyDateFormat mdf = new MyDateFormat();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 */
	public NhanTaphoso(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
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
		shlNhnHS.open();
		shlNhnHS.layout();
		Display display = getParent().getDisplay();
		while (!shlNhnHS.isDisposed()) {
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
		shlNhnHS = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlNhnHS.setImage(user.getIcondata().ThongbaoChuadocIcon);
		shlNhnHS.setSize(557, 353);
		shlNhnHS.setText("Nhận hồ sơ");
		shlNhnHS.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlNhnHS);

		table = new Table(shlNhnHS, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnNgiGi = new TableColumn(table, SWT.NONE);
		tblclmnNgiGi.setWidth(100);
		tblclmnNgiGi.setText("NGƯỜI GỬI");

		TableColumn tblclmnNgyGi = new TableColumn(table, SWT.NONE);
		tblclmnNgyGi.setWidth(100);
		tblclmnNgyGi.setText("NGÀY GỬI");

		TableColumn tblclmnTnTpH = new TableColumn(table, SWT.NONE);
		tblclmnTnTpH.setWidth(150);
		tblclmnTnTpH.setText("TÊN TẬP HỒ SƠ");

		TableColumn tblclmnGiiThiu = new TableColumn(table, SWT.NONE);
		tblclmnGiiThiu.setWidth(250);
		tblclmnGiiThiu.setText("GIỚI THIỆU");

		TableColumn tblclmnNgiTo = new TableColumn(table, SWT.NONE);
		tblclmnNgiTo.setWidth(100);
		tblclmnNgiTo.setText("NGƯỜI TẠO");

		Button btnNhn = new Button(shlNhnHS, SWT.NONE);
		btnNhn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for (TableItem ti : table.getItems()) {
					if (ti.getChecked()) {
						try {
							HOSO_USER hsr = (HOSO_USER) ti.getData();
							HOSO_DANHAN hdn = getHoso_Danhan(hsr.getHoso_Dagui(), hsr.getNguoiNhanHosoDagui());
							hsr.getNguoiNhanHosoDagui().setDA_DOC(true);
							controler.getControl_HOSO_DAGUI().update_NGUOINHAN_HOSO_DAGUI(hsr.getNguoiNhanHosoDagui());
							controler.getControl_HOSO_DANHAN().insert_HOSO_DANHAN(hdn);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				shlNhnHS.dispose();
			}

			private HOSO_DANHAN getHoso_Danhan(HOSO_DAGUI hoso_Hagui, NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui) {
				HOSO_DANHAN rs = new HOSO_DANHAN();
				rs.setNGAY_NHAN(new Date());
				rs.setTAIKHOAN_NHAN(nguoiNhanHosoDagui.getTEN_TAI_KHOAN());
				rs.setTAIKHOAN_GUI(hoso_Hagui.getTEN_TAI_KHOAN());
				rs.setMA_TAPHOSO(hoso_Hagui.getMA_TAPHOSO());
				return rs;
			}
		});
		GridData gd_btnNhn = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnNhn.widthHint = 75;
		btnNhn.setLayoutData(gd_btnNhn);
		btnNhn.setText("Nhận");

		Button btnng = new Button(shlNhnHS, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNhnHS.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		hsrl = controler.getControl_Hoso_Row().get_VanbanguidenData(user.getTEN_TAI_KHOAN());
		fillTree(hsrl);
	}

	private void fillTree(ArrayList<HOSO_USER> hsrl) {
		int i = 1;
		if (hsrl != null)
			for (HOSO_USER hsr : hsrl) {
				String date = hsr.getHoso_Dagui().getNGAY_GUI() == null ? "-"
						: mdf.getViewStringDate(hsr.getHoso_Dagui().getNGAY_GUI());
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { "" + i, hsr.getNguoiNhanHosoDagui().getTEN_TAI_KHOAN(), date,
						hsr.getTaphoso().getTEN_TAPHOSO(), hsr.getTaphoso().getGIOITHIEU_TAPHOSO(),
						hsr.getTEN_TAI_KHOAN() == null ? "--" : hsr.getTEN_TAI_KHOAN() });
				ti.setData(hsr);
				i++;
			}
	}
}
