package View.AssetManagers.CongViec.CongviecDahoanthanh;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Controler.Controler;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAPHOSO;
import View.AssetManagers.Hoso.Taphoso_View;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ViewPartHoso extends Dialog {

	protected Object result;
	protected Shell shlHSThc;
	private Table table;
	private NGUOIDUNG user;
	private Controler controler;
	private Object phanviec;
	MyDateFormat mdf = new MyDateFormat();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ViewPartHoso(Shell parent, int style, NGUOIDUNG user, Object phanviec) {
		super(parent, style);
		setText("SWT Dialog");
		this.user = user;
		this.phanviec = phanviec;
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
		shlHSThc.open();
		shlHSThc.layout();
		Display display = getParent().getDisplay();
		while (!shlHSThc.isDisposed()) {
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
		shlHSThc = new Shell(getParent(), getStyle());
		shlHSThc.setSize(810, 500);
		shlHSThc.setText("Hồ sơ thực hiện công việc");
		shlHSThc.setSize(728, 450);
		shlHSThc.setLayout(new GridLayout(1, false));
		new FormTemplate().setCenterScreen(shlHSThc);

		table = new Table(shlHSThc, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("MÃ TẬP HỒ SƠ");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("TÊN TẬP HỒ SƠ");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("NGÀY TẠO");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("GIỚI THIỆU");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("NGƯỜI TẠO");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(120);
		tableColumn_6.setText("NGÀY THAM GIA");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(150);
		tableColumn_7.setText("HÌNH THỨC NHẬN VIỆC");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table.getSelection();
				if (ti.length <= 0)
					return;
				TAPHOSO ths = (TAPHOSO) ti[0].getData();
				NGUOIDUNG nd = (NGUOIDUNG) ti[0].getData("nguoidung");
				boolean view_mode = true;
				if (nd.getTEN_TAI_KHOAN().equals(user.getTEN_TAI_KHOAN()))
					view_mode = false;
				Taphoso_View thsv = new Taphoso_View(shlHSThc, SWT.DIALOG_TRIM, user, ths, view_mode);
				try {
					thsv.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("Xem Tập hồ sơ");

		Button button = new Button(shlHSThc, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlHSThc.dispose();
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		if (phanviec == null)
			return;
		table.removeAll();
		int i = 1;
		Fill_ItemData f = new Fill_ItemData();
		if (phanviec instanceof GIAI_DOAN_THUC_HIEN) {
			GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) phanviec;
			ArrayList<NGUOIDUNG_THUCHIEN> ndthl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(gdth);
			for (NGUOIDUNG_THUCHIEN ndth : ndthl) {
				TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
				if (ths == null)
					return;
				TableItem tix = new TableItem(table, SWT.NONE);
				tix.setText(
						new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
								(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
										: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
								ths.getGIOITHIEU_TAPHOSO(), nd.getTEN_TAI_KHOAN(),
								(ndth.getNGAY_THAM_GIA() == null ? "-"
										: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
								f.getString_GIAO_NHANVIEC(ndth) });
				tix.setData(ths);
				tix.setData("nguoidung", nd);
			}
		} else if (phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
			GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) phanviec;
			ArrayList<NGUOIDUNG_NGHIEMTHU> ndthl = controler.getControl_NGHIEMTHU_CANBO()
					.get_AllNGUOIDUNG_NGHIEMTHU(gdth);
			for (NGUOIDUNG_NGHIEMTHU ndth : ndthl) {
				TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
				if (ths == null)
					return;
				TableItem tix = new TableItem(table, SWT.NONE);
				tix.setText(
						new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
								(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
										: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
								ths.getGIOITHIEU_TAPHOSO(), nd.getTEN_TAI_KHOAN(),
								(ndth.getNGAY_THAM_GIA() == null ? "-"
										: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
								f.getString_GIAO_NHANVIEC(ndth) });
				tix.setData(ths);
				tix.setData("nguoidung", nd);
			}
		} else if (phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
			GIAI_DOAN_QUYET_TOAN gdth = (GIAI_DOAN_QUYET_TOAN) phanviec;
			ArrayList<NGUOIDUNG_QUYETTOAN> ndthl = controler.getControl_QUYETTOAN_CANBO()
					.get_AllNGUOIDUNG_QUYETTOAN(gdth);
			for (NGUOIDUNG_QUYETTOAN ndth : ndthl) {
				TAPHOSO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ndth);
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(ndth.getTEN_TAI_KHOAN());
				if (ths == null)
					return;
				TableItem tix = new TableItem(table, SWT.NONE);
				tix.setText(
						new String[] { (i++) + "", ths.getMA_TAPHOSO() + "", ths.getTEN_TAPHOSO(),
								(ths.getNGAY_TAO_TAPHOSO() == null ? "-"
										: mdf.getViewStringDate(ths.getNGAY_TAO_TAPHOSO())),
								ths.getGIOITHIEU_TAPHOSO(), nd.getTEN_TAI_KHOAN(),
								(ndth.getNGAY_THAM_GIA() == null ? "-"
										: mdf.getViewStringDate(ndth.getNGAY_THAM_GIA())),
								f.getString_GIAO_NHANVIEC(ndth) });
				tix.setData(ths);
				tix.setData("nguoidung", nd);
			}
		}
	}

}
