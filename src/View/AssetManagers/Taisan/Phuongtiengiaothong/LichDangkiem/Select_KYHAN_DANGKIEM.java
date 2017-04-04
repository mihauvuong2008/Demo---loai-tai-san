package View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Controler.Controler;
import DAO.CHUKY_DANGKIEM;
import DAO.NGUOIDUNG;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Select_KYHAN_DANGKIEM extends Dialog {

	protected Object result;
	protected Shell shlChnChuK;
	private Table table;
	Controler controler;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Select_KYHAN_DANGKIEM(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
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
		shlChnChuK.open();
		shlChnChuK.layout();
		Display display = getParent().getDisplay();
		while (!shlChnChuK.isDisposed()) {
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
		shlChnChuK = new Shell(getParent(), getStyle());
		shlChnChuK.setSize(536, 374);
		shlChnChuK.setText("Chọn Chu kỳ Đăng kiểm");
		shlChnChuK.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlChnChuK);

		table = new Table(shlChnChuK, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(45);
		tblclmnNewColumn.setText("STT");

		TableColumn tblclmnTnChuK = new TableColumn(table, SWT.NONE);
		tblclmnTnChuK.setWidth(180);
		tblclmnTnChuK.setText("TÊN CHU KỲ");

		TableColumn tblclmnKHn = new TableColumn(table, SWT.NONE);
		tblclmnKHn.setWidth(120);
		tblclmnKHn.setText("KỲ HẠN");

		Button btnOk = new Button(shlChnChuK, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem til[] = table.getSelection();
				if (til.length > 0) {
					result = (CHUKY_DANGKIEM) til[0].getData();
				}
				shlChnChuK.dispose();
			}
		});
		GridData gd_btnOk = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnOk.widthHint = 75;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("Ok");

		Button btnng = new Button(shlChnChuK, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnChuK.dispose();
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
		ArrayList<CHUKY_DANGKIEM> lxl = controler.getControl_CHUKY_DANGKIEM().get_AllCHUKY_DANGKIEM();
		int i = 1;
		if (lxl != null)
			for (CHUKY_DANGKIEM l : lxl) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { i + "", l.getTEN_KYHAN() + "", l.getCHU_KY() + "" });
				ti.setData(l);
				i++;
			}
	}

}
