package View.AssetManagers.excel_XuatDulieu;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;
import DAO.TAISAN;
import View.AssetManagers.Icondataset;
import View.AssetManagers.excel_XuatDulieu.ColumnKeyCollector.TAISAN_keyColumn;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ExportExcelData_Taisan extends Dialog {

	protected Object result;
	protected Shell shlDanhSachTai;
	private Controler controler;
	private ArrayList<TAISAN> taisan;
	private TAISAN[] MyData;
	private ColumnKeyCollector ckc;
	private Table table;
	private excel_row er;
	private final Icondataset icondata = new Icondataset();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param data
	 * @param user
	 */
	public ExportExcelData_Taisan(Shell parent, int style, NGUOIDUNG user, ArrayList<TAISAN> data) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
		this.taisan = data;
		MyData = new TAISAN[taisan.size()];
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlDanhSachTai.open();
		shlDanhSachTai.layout();
		Display display = getParent().getDisplay();
		while (!shlDanhSachTai.isDisposed()) {
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
		shlDanhSachTai = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlDanhSachTai.setImage(icondata.excelIcon);
		shlDanhSachTai.setSize(650, 400);
		shlDanhSachTai.setText("Danh sách tài sản");
		shlDanhSachTai.setLayout(new GridLayout(5, false));
		new FormTemplate().setCenterScreen(shlDanhSachTai);

		table = new Table(shlDanhSachTai, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 5, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		Button btnTheoNhmTi = new Button(shlDanhSachTai, SWT.RADIO);
		btnTheoNhmTi.setText("Theo Nhóm Tài sản");

		Button btnTheoLoiTi = new Button(shlDanhSachTai, SWT.RADIO);
		btnTheoLoiTi.setText("Theo Loại Tài sản");

		Button btnChXutDanh = new Button(shlDanhSachTai, SWT.RADIO);
		btnChXutDanh.setSelection(true);
		btnChXutDanh.setText("Chỉ xuất danh sách");

		Button button = new Button(shlDanhSachTai, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (er != null) {
					FileDialog fd = new FileDialog(shlDanhSachTai, SWT.OPEN | SWT.MULTI);
					fd.setText("Open");
					fd.setFilterPath("C:/");
					String[] filterExt = { "*.xls" };
					fd.setFilterExtensions(filterExt);
					String selected = fd.open();
					if (selected != null) {
						String[] files = fd.getFileNames();
						for (int i = 0, n = files.length; i < n; i++) {
							// get file
							StringBuffer buf = new StringBuffer();
							buf.append(fd.getFilterPath());
							if (buf.charAt(buf.length() - 1) != File.separatorChar) {
								buf.append(File.separatorChar);
							}
							files[i] = addExtention(files[i]);
							buf.append(files[i]);
							// load image to inpustream
							ExcelWriter_ExportPTGT ee;
							try {
								ee = new ExcelWriter_ExportPTGT(buf.toString());
								ee.setData(ckc, er);
								ee.Write();
								ee.close();
							} catch (BiffException | IOException | WriteException e1) {
								MessageBox m = new MessageBox(shlDanhSachTai, SWT.ICON_WARNING);
								m.setText("Lỗi ghi dữ liệu");
								m.setText("Kiểm tra File đã chọn");
								m.open();
								e1.printStackTrace();
							}
						}
					}
				}
				shlDanhSachTai.dispose();
			}

			private String addExtention(String string) {
				String[] ar = string.split(Pattern.quote("."));
				if (ar.length < 2) {
					string = string + ".xls";
				}
				return string;
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Hoàn tất");

		Button button_1 = new Button(shlDanhSachTai, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDanhSachTai.dispose();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		ArrayList<LabelColumn> label = new ArrayList<>();
		label.add(new LabelColumn("Mã Tài sản", "MA_TAISAN"));
		label.add(new LabelColumn("Tên, Mô tả tài sản", "TEN_TAISAN"));
		label.add(new LabelColumn("Model", "MODEL"));
		label.add(new LabelColumn("Seri", "SERI"));
		label.add(new LabelColumn("Ngày sử dụng", "NGAY_SU_DUNG"));
		label.add(new LabelColumn("Xuất xứ", "XUAT_XU"));
		label.add(new LabelColumn("Bảo hành", "BAO_HANH"));
		label.add(new LabelColumn("Tình trạng", "TINH_TRANG"));
		label.add(new LabelColumn("Trạng thái", "TRANG_THAI"));
		label.add(new LabelColumn("Đơn vị tính", "DON_VI_TINH"));
		label.add(new LabelColumn("Số lượng", "SOLUONG"));
		label.add(new LabelColumn("Nguyên giá", "NGUYEN_GIA"));
		label.add(new LabelColumn("Mã tài sản kế toán", "MA_TANSAN_KETOAN"));
		label.add(new LabelColumn("Nhóm tài sản", "MA_NHOMTAISAN_CAP_V"));
		label.add(new LabelColumn("Loại tài sản", "MA_LOAITAISAN_CAP_III"));
		label.add(new LabelColumn("Đơn vị sử dụng", "MA_DON_VI_SU_DUNG"));
		label.add(new LabelColumn("Đơn vị quản lý", "MA_DON_VI_QUAN_LY"));
		label.add(new LabelColumn("Ghi chú", "GHI_CHU"));
		SelectColumn sc = new SelectColumn(shlDanhSachTai, SWT.DIALOG_TRIM, label);
		sc.open();
		this.ckc = sc.getCkc();
		if (ckc != null) {
			buildColumn();
			FillTable();
		}
	}

	private void FillTable() throws SQLException {
		for (int i = 0; i < ckc.columnCollector.size(); i++) {
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setWidth(180);
			tableColumn.setText(ckc.columnCollector.get(i));
		}
		int x = 0;
		for (TAISAN taisan : MyData) {
			Column_Collec cc = er.getRow(String.valueOf(taisan.getMA_TAISAN()));
			if (cc == null)
				return;
			String[] ar = new String[cc.ColumnCount() + 1];
			ar[0] = x + "";
			int i = 1;
			for (String s : ckc.getColumnCollector()) {
				ar[i] = ((String) cc.getColumn(s));
				i++;
			}
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(ar);
			x++;
		}
	}

	private void buildColumn() throws SQLException {
		er = new excel_row();
		int x = 0;
		for (TAISAN taisan_it : taisan) {
			Column_Collec cc = new Column_Collec();
			MyData[x] = controler.getControl_TAISAN().get_Taisan(taisan_it.getMA_TAISAN());
			TAISAN ts = MyData[x];
			if (ts != null) {
				for (int i = 0; i < ckc.getColumnCollector().size(); i++) {
					// Đưa vào hashmap các trường đã lựa chọn từ ckc
					if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_TAISAN)) {
						cc.addColumn(TAISAN_keyColumn.MA_TAISAN, ts.getMA_TAISAN() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.TEN_TAISAN)) {
						cc.addColumn(TAISAN_keyColumn.TEN_TAISAN, ts.getTEN_TAISAN() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MODEL)) {
						cc.addColumn(TAISAN_keyColumn.MODEL, ts.getMODEL() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.SERI)) {
						cc.addColumn(TAISAN_keyColumn.SERI, ts.getSERI() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.NGAY_SU_DUNG)) {
						cc.addColumn(TAISAN_keyColumn.NGAY_SU_DUNG,
								new MyDateFormat().getViewStringDate(ts.getNGAY_SU_DUNG()) + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.XUAT_XU)) {
						cc.addColumn(TAISAN_keyColumn.XUAT_XU, ts.getXUAT_XU() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.BAO_HANH)) {
						cc.addColumn(TAISAN_keyColumn.BAO_HANH, ts.getBAO_HANH() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.TINH_TRANG)) {
						cc.addColumn(TAISAN_keyColumn.TINH_TRANG,
								new Fill_ItemData().getStringOfTINHTRANG(ts.getTINH_TRANG()) + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.TRANG_THAI)) {
						cc.addColumn(TAISAN_keyColumn.TRANG_THAI,
								new Fill_ItemData().getStringOfTRANGTHAI(ts.getTRANG_THAI()) + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.DON_VI_TINH)) {
						cc.addColumn(TAISAN_keyColumn.DON_VI_TINH, ts.getDON_VI_TINH() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.SOLUONG)) {
						cc.addColumn(TAISAN_keyColumn.SOLUONG, ts.getSOLUONG() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.NGUYEN_GIA)) {
						cc.addColumn(TAISAN_keyColumn.NGUYEN_GIA, ts.getNGUYEN_GIA() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_TANSAN_KETOAN)) {
						cc.addColumn(TAISAN_keyColumn.MA_TANSAN_KETOAN, ts.getMA_TANSAN_KETOAN() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_NHOMTAISAN_CAP_V)) {
						int PHANNHOM = controler.getControl_NHOMTAISAN_CAP_V().getPHANNHOM(ts.getMA_NHOMTAISAN_CAP_V());
						switch (PHANNHOM) {
						case 0:
							NHOMTAISAN_CAP_V codinhHuuHinh = controler.getControl_NHOMTAISAN_CAP_V()
									.getNHOMTAISAN_CAP_V(ts.getMA_NHOMTAISAN_CAP_V());
							cc.addColumn(TAISAN_keyColumn.MA_NHOMTAISAN_CAP_V,
									"NHÓM CỐ ĐỊNH Hữu hình - " + codinhHuuHinh.getTEN_NHOMTAISAN_CAP_V());
							break;
						case 1:
							NHOM_TAISANCODINH_VOHINH codinhVohinh = controler.getControl_NHOM_TAISANCODINH_VOHINH()
									.getNHOM_TAISANCODINH_VOHINH(ts.getMA_NHOMTAISAN_CAP_V());
							cc.addColumn(TAISAN_keyColumn.MA_NHOMTAISAN_CAP_V,
									"NHÓM CỐ ĐỊNH Vô hình - " + codinhVohinh.getTEN_NHOM_TAISANCODINH_VOHINH());
							break;
						case 2:
							NHOM_TAISANCODINH_DACTHU codinhDacthu = controler.getControl_NHOM_TAISANCODINH_DACTHU()
									.getNHOM_TAISANCODINH_DACTHU(ts.getMA_NHOMTAISAN_CAP_V());
							cc.addColumn(TAISAN_keyColumn.MA_NHOMTAISAN_CAP_V,
									"NHÓM CỐ ĐỊNH ĐẶC THÙ - " + codinhDacthu.getTEN_NHOM_TAISANCODINH_DACTHU());
							break;
						case 3:
							NHOM_TAISANCODINH_DACBIET codinhDacbiet = controler.getControl_NHOM_TAISANCODINH_DACBIET()
									.getNHOM_TAISANCODINH_DACBIET(ts.getMA_NHOMTAISAN_CAP_V());
							cc.addColumn(TAISAN_keyColumn.MA_NHOMTAISAN_CAP_V,
									"NHÓM CỐ ĐỊNH ĐẶC BIỆT - " + codinhDacbiet.getTEN_NHOM_TAISANCODINH_DACBIET());
							break;
						default:
							break;
						}
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_LOAITAISAN_CAP_III)) {
						LOAITAISAN_CAP_III lc3 = controler.getControl_LOAITAISAN_CAP_III()
								.get_LOAITAISAN_CAP_III(ts.getMA_LOAITAISAN_CAP_III());
						cc.addColumn(TAISAN_keyColumn.MA_LOAITAISAN_CAP_III, lc3.getTEN_LOAITAISAN_CAP_III() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_DON_VI_SU_DUNG)) {
						cc.addColumn(TAISAN_keyColumn.MA_DON_VI_SU_DUNG, controler.getControl_PHONGBAN()
								.get_PHONGBAN(ts.getMA_DON_VI_SU_DUNG()).getTEN_PHONGBAN() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.MA_DON_VI_QUAN_LY)) {
						cc.addColumn(TAISAN_keyColumn.MA_DON_VI_QUAN_LY, controler.getControl_PHONGBAN()
								.get_PHONGBAN(ts.getMA_DON_VI_QUAN_LY()).getTEN_PHONGBAN() + "");
					} else if (ckc.getColumnCollector().get(i).equals(TAISAN_keyColumn.GHI_CHU)) {
						cc.addColumn(TAISAN_keyColumn.GHI_CHU, ts.getGHI_CHU() + "");
					}
					er.addRow(MyData[x].getMA_TAISAN() + "", cc);
				}
			}
			x++;
		}
	}
}
