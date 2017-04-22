package View.AssetManagers.excel_XuatDulieu;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.AssetManagers.excel_XuatDulieu.ColumnKeyCollector.PTGT_keyColumn;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.eclipse.wb.swt.SWTResourceManager;

public class ExportExcelData_Phuongtiengiaothong extends Dialog {

	protected Object result;
	protected Shell shlXutDLiu;
	private Table table;
	private ColumnKeyCollector ckc;
	private TAISAN[] items;
	private Controler controler;
	private excel_row er;
	private TableColumn tblclmnStt;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */

	public ExportExcelData_Phuongtiengiaothong(Shell parent, int style, NGUOIDUNG user, TAISAN[] items) {
		super(parent, style);
		setText("SWT Dialog");
		this.items = items;
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
		shlXutDLiu.open();
		shlXutDLiu.layout();
		Display display = getParent().getDisplay();
		while (!shlXutDLiu.isDisposed()) {
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
		shlXutDLiu = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlXutDLiu.setImage(SWTResourceManager.getImage(ExportExcelData_Phuongtiengiaothong.class, "/Excel-icon.png"));
		shlXutDLiu.setSize(650, 400);
		shlXutDLiu.setText("Xuất dữ liệu Phương tiện giao thông");
		shlXutDLiu.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlXutDLiu);

		table = new Table(shlXutDLiu, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnStt = new org.eclipse.swt.widgets.TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		Button btnHonTt = new Button(shlXutDLiu, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (er != null) {
					FileDialog fd = new FileDialog(shlXutDLiu, SWT.OPEN | SWT.MULTI);
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
								MessageBox m = new MessageBox(shlXutDLiu, SWT.ICON_WARNING);
								m.setText("Lỗi ghi dữ liệu");
								m.setText("Kiểm tra File đã chọn");
								m.open();
								e1.printStackTrace();
							}
						}
					}
				}
				shlXutDLiu.dispose();
			}

			private String addExtention(String string) {
				String[] ar = string.split(Pattern.quote("."));
				if (ar.length < 2) {
					string = string + ".xls";
				}
				return string;
			}

		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Hoàn tất");

		Button btnng = new Button(shlXutDLiu, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlXutDLiu.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		ArrayList<LabelColumn> labelColumn = new ArrayList<>();
		labelColumn.add(new LabelColumn("Mã PTGT", "MA_PHUONGTIEN_GIAOTHONG"));
		labelColumn.add(new LabelColumn("Tên PTGT", "TEN_PHUONGTIEN_GIAOTHONG"));
		labelColumn.add(new LabelColumn("Loại PTGT", "LOAI_PHUONGTIEN_GIAOTHONG"));
		labelColumn.add(new LabelColumn("Loại xe", "MA_LOAI_XE"));
		labelColumn.add(new LabelColumn("Biển số", "BIENSO"));
		labelColumn.add(new LabelColumn("Số khung", "SOKHUNG"));
		labelColumn.add(new LabelColumn("Số máy", "SOMAY"));
		labelColumn.add(new LabelColumn("Số km xe", "SO_KM_XE"));
		labelColumn.add(new LabelColumn("Thời gian đăng kiểm", "THOIHAN_DANGKIEM"));
		labelColumn.add(new LabelColumn("Mã tài sản", "MA_TAISAN"));
		SelectColumn sc = new SelectColumn(shlXutDLiu, SWT.DIALOG_TRIM, labelColumn);
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
		for (TAISAN taisan : items) {
			PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
					.get_PHUONGTIEN_GIAOTHONG_FromTaisan(taisan.getMA_TAISAN());
			Column_Collec cc = er.getRow(pg.getMA_PHUONGTIEN_GIAOTHONG());
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
		for (TAISAN taisan : items) {
			Column_Collec cc = new Column_Collec();
			for (int i = 0; i < ckc.getColumnCollector().size(); i++) {
				PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
						.get_PHUONGTIEN_GIAOTHONG_FromTaisan(taisan.getMA_TAISAN());
				// Đưa vào hashmap các trường đã lựa chọn từ ckc
				if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.MA_PHUONGTIEN_GIAOTHONG)) {
					cc.addColumn(PTGT_keyColumn.MA_PHUONGTIEN_GIAOTHONG, pg.getMA_PHUONGTIEN_GIAOTHONG());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.TEN_PHUONGTIEN_GIAOTHONG)) {
					cc.addColumn(PTGT_keyColumn.TEN_PHUONGTIEN_GIAOTHONG, pg.getTEN_PHUONGTIEN_GIAOTHONG());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.LOAI_PHUONGTIEN_GIAOTHONG)) {
					cc.addColumn(PTGT_keyColumn.LOAI_PHUONGTIEN_GIAOTHONG,
							new Fill_ItemData().getStringLOAI_PHUONGTIEN_GIAOTHONG(pg.getLOAI_PHUONGTIEN_GIAOTHONG()));
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.MA_LOAI_XE)) {
					LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(pg.getMA_LOAI_XE());
					cc.addColumn(PTGT_keyColumn.MA_LOAI_XE, lx.getHANG_SAN_XUAT() + " - " + lx.getTEN_DONG_XE());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.BIENSO)) {
					cc.addColumn(PTGT_keyColumn.BIENSO, pg.getBIENSO());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.SOKHUNG)) {
					cc.addColumn(PTGT_keyColumn.SOKHUNG, pg.getSOKHUNG());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.SOMAY)) {
					cc.addColumn(PTGT_keyColumn.SOMAY, pg.getSOMAY());
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.SO_KM_XE)) {
					cc.addColumn(PTGT_keyColumn.SO_KM_XE, String.valueOf(pg.getSO_KM_XE()));
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.THOIHAN_DANGKIEM)) {
					cc.addColumn(PTGT_keyColumn.THOIHAN_DANGKIEM,
							new MyDateFormat().getViewStringDate(pg.getTHOIHAN_DANGKIEM()));
				} else if (ckc.getColumnCollector().get(i).equals(PTGT_keyColumn.MA_TAISAN)) {
					String KtKey = controler.getControl_TAISAN().get_MataisanKetoan(taisan.getMA_TAISAN());
					cc.addColumn(PTGT_keyColumn.MA_TAISAN, KtKey);
				}
				er.addRow(pg.getMA_PHUONGTIEN_GIAOTHONG(), cc);
			}
		}
	}

}
