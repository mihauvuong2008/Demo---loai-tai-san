package View.AssetManagers.NhapDulieu_Excel;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;

import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import Controler.Controler;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;

public class Import_LOAIXE extends Dialog {

	protected Object result;
	protected Shell shlDanhSchDng;
	private Table table;
	private TableColumn tblclmnStt;
	private TableColumn tblclmnHngSnXut;
	private TableColumn tblclmnDngXe;
	private Text text;
	private Button btnChnFile;
	private Label lblFileExcel;
	Controler controler;
	protected ArrayList<LOAI_XE> danhsachLoaixe;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Import_LOAIXE(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		controler = new Controler(user);
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDanhSchDng.open();
		shlDanhSchDng.layout();
		Display display = getParent().getDisplay();
		while (!shlDanhSchDng.isDisposed()) {
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
		shlDanhSchDng = new Shell(getParent(), getStyle());
		shlDanhSchDng.setSize(345, 450);
		shlDanhSchDng.setText("Danh sách Dòng xe");
		new FormTemplate().setCenterScreen(shlDanhSchDng);
		shlDanhSchDng.setLayout(new GridLayout(3, false));

		lblFileExcel = new Label(shlDanhSchDng, SWT.NONE);
		lblFileExcel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFileExcel.setText("File Excel: ");

		text = new Text(shlDanhSchDng, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnChnFile = new Button(shlDanhSchDng, SWT.NONE);
		btnChnFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shlDanhSchDng, SWT.OPEN | SWT.SINGLE | SWT.CENTER);
				fd.setText("Chọn File Excel Dữ liệu tài sản (theo phòng)");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.xls", "*.xlsx" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				if (selected != null) {
					String[] files = fd.getFileNames();
					for (int i = 0, n = files.length; i < n; i++) {
						StringBuffer buf = new StringBuffer();
						buf.append(fd.getFilterPath());
						if (buf.charAt(buf.length() - 1) != File.separatorChar) {
							buf.append(File.separatorChar);
						}
						buf.append(files[i]);// get file
						try {
							ExcelReader_ImportLOAIXE eil = new ExcelReader_ImportLOAIXE(buf.toString());
							ArrayList<LOAI_XE> DanhsachLOAIXE = eil.getData();
							MessageBox m = new MessageBox(shlDanhSchDng);
							m.setText("Hoàn tất");
							m.setMessage("Đã nhận " + DanhsachLOAIXE.size() + " dòng dữ liệu");
							m.open();
							setResult(DanhsachLOAIXE);
							fillTree(DanhsachLOAIXE);
						} catch (BiffException | IOException | SQLException e1) {
							if (e1.getMessage().equals("Unable to recognize OLE stream")) {
								MessageBox m = new MessageBox(shlDanhSchDng, SWT.ERROR);
								m.setMessage("Hỗ trợ File Excel cho phiên bản Excel 2000 về trước");
								m.open();
							}
						}
					}
				}
			}

			private void fillTree(ArrayList<LOAI_XE> danhsachPHUONGTIEN_GIAOTHONG) throws SQLException {
				table.removeAll();
				int stt = 1;
				Fill_ItemData fid = new Fill_ItemData();
				for (LOAI_XE t : danhsachPHUONGTIEN_GIAOTHONG) {
					TableItem tableitem = new TableItem(table, SWT.NONE);
					tableitem.setText(new String[] { "" + stt, t.getHANG_SAN_XUAT(), t.getTEN_DONG_XE(),
							fid.getStringLOAI_PHUONGTIEN_GIAOTHONG(t.getOTO_XEMAY()), t.getDINH_MUC_XANG_DAU() + "" });
					tableitem.setData(t);
					stt++;
				}
			}
		});
		btnChnFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnChnFile.setText("Chọn File");

		table = new Table(shlDanhSchDng, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		tblclmnHngSnXut = new TableColumn(table, SWT.NONE);
		tblclmnHngSnXut.setWidth(130);
		tblclmnHngSnXut.setText("HÃNG SẢN XUẤT");

		tblclmnDngXe = new TableColumn(table, SWT.NONE);
		tblclmnDngXe.setWidth(125);
		tblclmnDngXe.setText("DÒNG XE");
		new Label(shlDanhSchDng, SWT.NONE);

		Button btnXong = new Button(shlDanhSchDng, SWT.NONE);
		btnXong.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				for (LOAI_XE lx : danhsachLoaixe) {
					try {
						controler.getControl_LOAI_XE().insert_LOAI_XE(lx);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				shlDanhSchDng.dispose();
			}
		});
		GridData gd_btnXong = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnXong.widthHint = 75;
		btnXong.setLayoutData(gd_btnXong);
		btnXong.setText("Xong");

		Button btnng = new Button(shlDanhSchDng, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDanhSchDng.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");

	}

	protected void setResult(ArrayList<LOAI_XE> danhsachLOAIXE) {
		danhsachLoaixe = danhsachLOAIXE;
	}

}
