package View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChonTaisan extends Dialog {

	protected Object result;
	protected Shell shlDanhSchTi;
	private Table table;
	private PHONGBAN pb;
	private Controler controler;
	MyDateFormat mdf = new MyDateFormat();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ChonTaisan(Shell parent, int style, NGUOIDUNG user, PHONGBAN pb) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.pb = pb;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlDanhSchTi.open();
		shlDanhSchTi.layout();
		Display display = getParent().getDisplay();
		while (!shlDanhSchTi.isDisposed()) {
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
		shlDanhSchTi = new Shell(getParent(), getStyle());
		shlDanhSchTi.setSize(641, 379);
		shlDanhSchTi.setText("Danh sách tài sản: ");
		shlDanhSchTi.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlDanhSchTi);

		table = new Table(shlDanhSchTi, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnTiSan = new TableColumn(table, SWT.NONE);
		tblclmnTnTiSan.setWidth(200);
		tblclmnTnTiSan.setText("TÊN TÀI SẢN");

		TableColumn tblclmnNgySDng = new TableColumn(table, SWT.NONE);
		tblclmnNgySDng.setWidth(100);
		tblclmnNgySDng.setText("NGÀY SỬ DỤNG");

		TableColumn tblclmnMQunL = new TableColumn(table, SWT.NONE);
		tblclmnMQunL.setWidth(100);
		tblclmnMQunL.setText("MÃ QUẢN LÝ");

		TableColumn tblclmnnVQun = new TableColumn(table, SWT.NONE);
		tblclmnnVQun.setWidth(120);
		tblclmnnVQun.setText("ĐƠN VỊ QUẢN LÝ");

		Button btnXong = new Button(shlDanhSchTi, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<TAISAN> rs = new ArrayList<>();
				for (TableItem t : table.getItems()) {
					if (t.getChecked()) {
						rs.add((TAISAN) t.getData());
					}
				}
				result = rs;
				shlDanhSchTi.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlDanhSchTi, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDanhSchTi.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		table.removeAll();
		ArrayList<TAISAN> tsl = controler.getControl_TAISAN().get_Taisan_Timkiem("", pb);
		PHONGBAN quanly = controler.getControl_PHONGBAN().get_PHONGBAN(tsl.get(0).getMA_DON_VI_SU_DUNG());
		shlDanhSchTi.setText(shlDanhSchTi.getText() + quanly.getTEN_PHONGBAN());
		int i = 1;
		for (TAISAN taisan : tsl) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(
					new String[] { (i++) + "", taisan.getTEN_TAISAN(), mdf.getViewStringDate(taisan.getNGAY_SU_DUNG()),
							taisan.getMA_TANSAN_KETOAN(), quanly.getTEN_PHONGBAN() });
			ti.setData(taisan);
		}
	}
}
