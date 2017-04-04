package View.AssetManagers.CongViec.Baoduong;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Label;

public class _3_Nhapdanhsach extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Table table;
	private Table table_Lanbaoduongganday;
	private final Controler controler;
	final ArrayList<PHUONGTIEN_GIAOTHONG> Data_from_Parent_list_PTGT;
	private ArrayList<PHUONGTIEN_GIAOTHONG> Result_danhsachPTTS;
	protected boolean Accept = false;
	private DE_XUAT dx;
	private int loaiPTGT = 0;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(_3_Nhapdanhsach.class);

	public ArrayList<PHUONGTIEN_GIAOTHONG> getResult_danhsachPTTS() {
		return Result_danhsachPTTS;
	}

	public void setResult_danhsachPTTS(ArrayList<PHUONGTIEN_GIAOTHONG> result_danhsachPTTS) {
		Result_danhsachPTTS = result_danhsachPTTS;
	}

	public boolean isAccept() {
		return Accept;
	}

	public void setAccept(boolean accept) {
		Accept = accept;
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public _3_Nhapdanhsach(Shell parent, int style, NGUOIDUNG user,
			ArrayList<PHUONGTIEN_GIAOTHONG> Data_from_Parent_list_PTGT, DE_XUAT dx, int loaiPTGT) {
		super(parent, style);
		controler = new Controler(user);
		this.Data_from_Parent_list_PTGT = Data_from_Parent_list_PTGT;
		this.dx = dx;
		this.loaiPTGT = loaiPTGT;
		Result_danhsachPTTS = new ArrayList<>();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shell.setImage(SWTResourceManager.getImage(_3_Nhapdanhsach.class, "/maintenance-icon (1).png"));
		shell.setSize(728, 450);
		shell.setText("Chọn phương tiện cơ giới ["
				+ controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()).getTEN_PHONGBAN() + "]");
		new FormTemplate().setCenterScreen(shell);
		shell.setLayout(new GridLayout(3, false));
		Button button_2 = new Button(shell, SWT.CHECK);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (button_2.getSelection()) {
						viewAll();
					} else {
						viewDsGanday();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		button_2.setText("Toàn bộ");

		text = new Text(shell, SWT.BORDER | SWT.RIGHT);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		text.setMessage("Tìm theo mã tài sản");

		Button btnTm = new Button(shell, SWT.NONE);
		btnTm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnTm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTm.widthHint = 75;
		btnTm.setLayoutData(gd_btnTm);
		btnTm.setText("Tìm");

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		table_Lanbaoduongganday = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_Lanbaoduongganday.setLinesVisible(true);
		table_Lanbaoduongganday.setHeaderVisible(true);
		table_Lanbaoduongganday.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					viewDsGanday();
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		TableColumn tblclmnXut = new TableColumn(table_Lanbaoduongganday, SWT.NONE);
		tblclmnXut.setWidth(100);
		tblclmnXut.setText("SỐ ĐỀ XUẤT");

		TableColumn tableColumn_8 = new TableColumn(table_Lanbaoduongganday, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("NGÀY ĐỀ XUẤT");

		TableColumn tableColumn_9 = new TableColumn(table_Lanbaoduongganday, SWT.NONE);
		tableColumn_9.setWidth(161);
		tableColumn_9.setText("NGÀY HOÀN TẤT NGHIỆM THU");

		table = new Table(sashForm, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("HÃNG XE");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("DÒNG XE");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("BIỂN SỐ XE");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("SỐ KM ĐÃ ĐI");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("SỐ KHUNG");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("SỐ MÁY");
		sashForm.setWeights(new int[] { 618, 1000 });

		new Label(shell, SWT.NONE);

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Result_danhsachPTTS = new ArrayList<>();
				for (TableItem t : table.getItems()) {
					if (t.getChecked()) {
						Result_danhsachPTTS.add((PHUONGTIEN_GIAOTHONG) t.getData());
					}
				}
				Accept = true;
				shell.dispose();
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Xong");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Accept = false;
				shell.dispose();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Đóng");
		try {
			init();
		} catch (SQLException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
	}

	private void init() throws SQLException {
		fillLanbaoduong();
	}

	protected void viewDsGanday() throws SQLException {
		TableItem[] items = table_Lanbaoduongganday.getSelection();
		if (items.length > 0) {
			DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) items[0].getData();
			if (dsb != null) {
				PHONGBAN dv = new PHONGBAN();
				dv.setMA_PHONGBAN(dx.getMA_PHONGBAN());
				viewData_DotSuachua_Baoduong(dsb, dv);
			}
		}
	}

	protected void viewAll() throws SQLException {
		table.removeAll();
		PHONGBAN dv = new PHONGBAN();
		dv.setMA_PHONGBAN(dx.getMA_PHONGBAN());
		ArrayList<PHUONGTIEN_GIAOTHONG> pl = controler.getControl_PHUONGTIEN_GIAOTHONG()
				.get_PHUONGTIEN_GIAOTHONG(loaiPTGT, dv);

		int i = 1;
		for (PHUONGTIEN_GIAOTHONG e : pl) {
			boolean flag = true;
			for (PHUONGTIEN_GIAOTHONG e2 : Data_from_Parent_list_PTGT) {
				if (e2.getMA_PHUONGTIEN_GIAOTHONG().equals(e.getMA_PHUONGTIEN_GIAOTHONG())) {
					flag = false;
					break;
				}
			}
			if (flag) {
				TableItem t = new TableItem(table, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(e.getMA_LOAI_XE());
				t.setText(new String[] { "" + i, lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), e.getBIENSO(),
						String.valueOf(e.getSO_KM_XE()), e.getSOKHUNG(), e.getSOMAY() });
				t.setData(e);
				i++;
			}
		}
		for (TableColumn c : table.getColumns()) {
			c.pack();
		}
	}

	protected void viewData_DotSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, PHONGBAN dv) throws SQLException {
		table.removeAll();
		ArrayList<PHUONGTIEN_GIAOTHONG> pl = controler.getControl_PHUONGTIEN_GIAOTHONG().get_PHUONGTIEN_GIAOTHONG(dsb,
				dv);
		int i = 1;
		for (PHUONGTIEN_GIAOTHONG e : pl) {
			boolean flag = true;
			for (PHUONGTIEN_GIAOTHONG e2 : Data_from_Parent_list_PTGT) {
				if (e2.getMA_PHUONGTIEN_GIAOTHONG().equals(e.getMA_PHUONGTIEN_GIAOTHONG())) {
					flag = false;
					break;
				}
			}
			if (flag) {
				TableItem t = new TableItem(table, SWT.NONE);
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(e.getMA_LOAI_XE());
				t.setText(new String[] { "" + i, lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(), e.getBIENSO(),
						String.valueOf(e.getSO_KM_XE()), e.getSOKHUNG(), e.getSOMAY() });
				t.setData(e);
				i++;
			}
		}
		for (TableColumn c : table.getColumns()) {
			c.pack();
		}
	}

	private void fillLanbaoduong() throws SQLException {
		table_Lanbaoduongganday.removeAll();
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> ptl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(dx.getMA_PHONGBAN(), loaiPTGT);
		for (DOT_THUCHIEN_SUACHUA_BAODUONG d : ptl) {
			TableItem t = new TableItem(table_Lanbaoduongganday, SWT.NONE);
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(d);
			GIAI_DOAN_NGHIEM_THU gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(d);
			t.setText(new String[] { dx.getSODEXUAT(), mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()),
					gdnt == null ? "--"
							: gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null ? "-"
									: mdf.getViewStringDate(gdnt.getTHOI_DIEM_CHUYEN_GIAO()) });
			t.setData(d);
		}
	}
}
