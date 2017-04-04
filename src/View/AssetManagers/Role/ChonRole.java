package View.AssetManagers.Role;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import org.eclipse.swt.widgets.Composite;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.ROLE;
import View.Template.FormTemplate;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChonRole extends Dialog {
	protected Object result;
	protected Shell shlChnQuyn;
	private Table table_1;
	private Button btnThmNgiDng;
	private Button btnXemThngTinND;
	private Button btnCpQuyn;
	private Button btnXaNgiDng;
	private Button btnThmThngTinPTTS;
	private Button btnXemThngTin_PTTS;
	private Button btnCpNhtThng_PTTS;
	private Button btnXaPtts;
	private Button btnThmCngVic;
	private Button btnXemThngTin_CV;
	private Button btnCpNhtThng_CV;
	private Button btnXaCngVic;
	private Button btnTaoHS;
	private Button btnXemHS;
	private Button btnCpNhtThng_HS;
	private Button btnXaHS;
	private final Controler controler;

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
	public ChonRole(Shell parent, int style, NGUOIDUNG user) {
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
		shlChnQuyn.open();
		shlChnQuyn.layout();
		Display display = getParent().getDisplay();
		while (!shlChnQuyn.isDisposed()) {
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
		shlChnQuyn = new Shell(getParent(), getStyle());
		shlChnQuyn.setSize(767, 456);
		new FormTemplate().setCenterScreen(shlChnQuyn);
		shlChnQuyn.setText("Chọn Quyền");
		shlChnQuyn.setLayout(new GridLayout(2, false));

		Composite composite = new Composite(shlChnQuyn, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		composite.setLayout(new GridLayout(4, false));

		Group group = new Group(composite, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("Dữ liệu Người dùng");
		group.setLayout(new GridLayout(1, false));

		btnThmNgiDng = new Button(group, SWT.CHECK);
		btnThmNgiDng.setText("Thêm người dùng");

		btnXemThngTinND = new Button(group, SWT.CHECK);
		btnXemThngTinND.setText("Xem Thông tin người dùng");

		btnCpQuyn = new Button(group, SWT.CHECK);
		btnCpQuyn.setText("Cấp quyền");

		btnXaNgiDng = new Button(group, SWT.CHECK);
		btnXaNgiDng.setText("Xóa người dùng");

		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_1.setText("Dữ liệu Tài sản");
		group_1.setLayout(new GridLayout(1, false));

		btnThmThngTinPTTS = new Button(group_1, SWT.CHECK);
		btnThmThngTinPTTS.setText("Thêm PTTS");

		btnXemThngTin_PTTS = new Button(group_1, SWT.CHECK);
		btnXemThngTin_PTTS.setText("Xem thông tin PTTS");

		btnCpNhtThng_PTTS = new Button(group_1, SWT.CHECK);
		btnCpNhtThng_PTTS.setText("Cập nhật thông tin PTTS");

		btnXaPtts = new Button(group_1, SWT.CHECK);
		btnXaPtts.setText("Xóa PTTS");

		Group group_2 = new Group(composite, SWT.NONE);
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_2.setText("Dữ liệu Công việc");
		group_2.setLayout(new GridLayout(1, false));

		btnThmCngVic = new Button(group_2, SWT.CHECK);
		btnThmCngVic.setText("Thêm Công việc");

		btnXemThngTin_CV = new Button(group_2, SWT.CHECK);
		btnXemThngTin_CV.setText("Xem Thông tin Công việc");

		btnCpNhtThng_CV = new Button(group_2, SWT.CHECK);
		btnCpNhtThng_CV.setText("Cập nhật thông tin Công việc");

		btnXaCngVic = new Button(group_2, SWT.CHECK);
		btnXaCngVic.setText("Xóa Công việc");

		Group group_3 = new Group(composite, SWT.NONE);
		group_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group_3.setText("Dữ liệu Hồ sơ");
		group_3.setLayout(new GridLayout(1, false));

		btnTaoHS = new Button(group_3, SWT.CHECK);
		btnTaoHS.setText("Tạo hồ sơ");

		btnXemHS = new Button(group_3, SWT.CHECK);
		btnXemHS.setText("Xem thông tin Hồ sơ");

		btnCpNhtThng_HS = new Button(group_3, SWT.CHECK);
		btnCpNhtThng_HS.setText("Cập nhật thông tin Hồ sơ");

		btnXaHS = new Button(group_3, SWT.CHECK);
		btnXaHS.setText("Xóa Hồ sơ");

		table_1 = new Table(shlChnQuyn, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				/* get selection */
				TableItem[] items = table_1.getSelection();

				if (items.length > 0) {
					ROLE r = (ROLE) items[0].getData();
					if (r != null)
						getDataCheckBox(r);
				}
			}

			protected void getDataCheckBox(ROLE r) {

				if (r.getTHEM_NGUOIDUNG() == 1) {
					btnThmNgiDng.setSelection(true);
				} else {
					btnThmNgiDng.setSelection(false);
				}
				if (r.getXEM_THONGTIN_NGUOIDUNG() == 1) {
					btnXemThngTinND.setSelection(true);
				} else {
					btnXemThngTinND.setSelection(false);
				}
				if (r.getPHAN_QUYEN_NGUOIDUNG() == 1) {
					btnCpQuyn.setSelection(true);
				} else {
					btnCpQuyn.setSelection(false);
				}
				if (r.getXOA_NGUOIDUNG() == 1) {
					btnXaNgiDng.setSelection(true);
				} else {
					btnXaNgiDng.setSelection(false);
				}
				if (r.getTHEM_THONGTIN_TAISAN() == 1) {
					btnThmThngTinPTTS.setSelection(true);
				} else {
					btnThmThngTinPTTS.setSelection(false);
				}
				if (r.getXEM_THONGTIN_TAISAN() == 1) {
					btnXemThngTin_PTTS.setSelection(true);
				} else {
					btnXemThngTin_PTTS.setSelection(false);
				}
				if (r.getSUA_THONGTIN_TAISAN() == 1) {
					btnCpNhtThng_PTTS.setSelection(true);
				} else {
					btnCpNhtThng_PTTS.setSelection(false);
				}
				if (r.getXOA_THONGTIN_TAISAN() == 1) {
					btnXaPtts.setSelection(true);
				} else {
					btnXaPtts.setSelection(false);
				}
				if (r.getTHEM_CONGVIEC() == 1) {
					btnThmCngVic.setSelection(true);
				} else {
					btnThmCngVic.setSelection(false);
				}
				if (r.getXEM_THONGTIN_CONGVIEC() == 1) {
					btnXemThngTin_CV.setSelection(true);
				} else {
					btnXemThngTin_CV.setSelection(false);
				}
				if (r.getSUA_THONGTIN_CONGVIEC() == 1) {
					btnCpNhtThng_CV.setSelection(true);
				} else {
					btnCpNhtThng_CV.setSelection(false);
				}
				if (r.getXEM_THONGTIN_CONGVIEC() == 1) {
					btnXaCngVic.setSelection(true);
				} else {
					btnXaCngVic.setSelection(false);
				}
				if (r.getTHEM_NGUOIDUNG() == 1) {
					btnTaoHS.setSelection(true);
				} else {
					btnTaoHS.setSelection(false);
				}
				if (r.getXEM_THONGTIN_HOSO() == 1) {
					btnXemHS.setSelection(true);
				} else {
					btnXemHS.setSelection(false);
				}
				if (r.getSUA_THONGTIN_HOSO() == 1) {
					btnCpNhtThng_HS.setSelection(true);
				} else {
					btnCpNhtThng_HS.setSelection(false);
				}
				if (r.getXOA_HOSO() == 1) {
					btnXaHS.setSelection(true);
				} else {
					btnXaHS.setSelection(false);
				}
			}
		});

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("TÊN QUYỀN HẠN");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(300);
		tableColumn_2.setText("MÔ TẢ");

		Button btnChon = new Button(shlChnQuyn, SWT.NONE);
		btnChon.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tl = table_1.getSelection();
				if (tl.length > 0) {
					result = tl[0].getData();
				}
				shlChnQuyn.dispose();
			}
		});
		GridData gd_btnChon = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnChon.widthHint = 75;
		btnChon.setLayoutData(gd_btnChon);
		btnChon.setText("Chọn");

		Button btnng = new Button(shlChnQuyn, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChnQuyn.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		loadData();
	}

	private void loadData() throws SQLException {
		table_1.removeAll();
		ArrayList<ROLE> rl = controler.getControl_Role().getAll_ROLE();
		int i = 1;
		if (rl != null)
			for (ROLE r : rl) {
				TableItem tb = new TableItem(table_1, SWT.NONE);
				tb.setText(new String[] { i + "", r.getTEN_QUYEN(), r.getMO_TA() });
				tb.setData(r);
				i++;
			}
	}
}
