package View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.LichBaoduong_ROW;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

public class DanhSachDenghiBaoduong extends Dialog {

	protected Object result;
	protected Shell shlDanhSch;
	private Table table;
	private Text text;
	private TableItem[] tableItems;
	private final Controler controler;
	private static Log log = LogFactory.getLog(DanhSachDenghiBaoduong.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param select
	 * @param phongban_Selected
	 * @param tableItems
	 */
	public DanhSachDenghiBaoduong(Shell parent, int style, PHONGBAN phongban_Selected, int select,
			TableItem[] tableItems, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.tableItems = tableItems;
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
		shlDanhSch.open();
		shlDanhSch.layout();
		Display display = getParent().getDisplay();
		while (!shlDanhSch.isDisposed()) {
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
		shlDanhSch = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX);
		shlDanhSch.setImage(SWTResourceManager.getImage(DanhSachDenghiBaoduong.class,
				"/javax/swing/plaf/basic/icons/JavaCup16.png"));
		shlDanhSch.setSize(647, 400);
		new FormTemplate().setCenterScreen(shlDanhSch);
		shlDanhSch.setText(
				"Danh s\u00E1ch \u0111\u1EC3 ngh\u1ECB b\u1EA3o d\u01B0\u1EE1ng ph\u01B0\u01A1ng ti\u1EC7n giao th\u00F4ng- H\u00ECnh th\u1EE9c B\u1EA3o d\u01B0\u1EE1ng:");
		shlDanhSch.setLayout(new GridLayout(4, false));

		Label lblThiHnTnh = new Label(shlDanhSch, SWT.NONE);
		lblThiHnTnh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblThiHnTnh.setText("Th\u1EDDi h\u1EA1n t\u00EDnh (ng\u00E0y):");

		text = new Text(shlDanhSch, SWT.BORDER | SWT.RIGHT);
		text.setText("90");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();
				final String oldS = text.getText();
				String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				boolean isFloat = true;
				try {
					Float.parseFloat(newS);
				} catch (NumberFormatException ex) {
					isFloat = false;
				}
				if (!isFloat)
					e.doit = false;
			}
		});
		Button btnXem = new Button(shlDanhSch, SWT.NONE);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					FillItem(Integer.valueOf(text.getText()));
				} catch (NumberFormatException | SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnXem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnXem.setText("Xem");

		SashForm sashForm = new SashForm(shlDanhSch, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnLoiPttgt = new TableColumn(table, SWT.NONE);
		tblclmnLoiPttgt.setWidth(100);
		tblclmnLoiPttgt.setText("LO\u1EA0I PTGT");

		TableColumn tblclmnBinS = new TableColumn(table, SWT.NONE);
		tblclmnBinS.setWidth(100);
		tblclmnBinS.setText("BI\u1EC2N S\u1ED0");

		TableColumn tblclmnLnBoDng = new TableColumn(table, SWT.NONE);
		tblclmnLnBoDng.setWidth(150);
		tblclmnLnBoDng.setText("L\u1EA6N B\u1EA2O D\u01AF\u1EE0NG TR\u01AF\u1EDAC");

		TableColumn tblclmnKHn = new TableColumn(table, SWT.NONE);
		tblclmnKHn.setWidth(100);
		tblclmnKHn.setText("K\u1EF2 H\u1EA0N");

		TableColumn tblclmnThiGianCn = new TableColumn(table, SWT.NONE);
		tblclmnThiGianCn.setWidth(150);
		tblclmnThiGianCn.setText("TH\u1EDCI GIAN C\u00D2N L\u1EA0I");
		sashForm.setWeights(new int[] { 1 });
		new Label(shlDanhSch, SWT.NONE);
		new Label(shlDanhSch, SWT.NONE);

		Button btnInBoCo = new Button(shlDanhSch, SWT.NONE);
		btnInBoCo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		btnInBoCo.setText("In B\u00E1o c\u00E1o");

		Button btnng = new Button(shlDanhSch, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDanhSch.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected void FillItem(Integer valueOf) throws SQLException {
		table.removeAll();
		for (TableItem tableItem : tableItems) {
			Date thisdate = new Date();
			LichBaoduong_ROW Lr = (LichBaoduong_ROW) (LichBaoduong_ROW) tableItem.getData();
			GIAI_DOAN_NGHIEM_THU gnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU((Lr).getDotBaoduong());
			MyDateFormat mdf = new MyDateFormat();
			int e = 0;
			String Message;
			if (gnt != null) {
				if (gnt.getTHOI_DIEM_KET_THUC() != null) {
					int day = mdf.getTongNgaythuchien(gnt.getTHOI_DIEM_KET_THUC(), thisdate);
					int kyhan = Integer.valueOf(tableItem.getText(3));
					e = kyhan - day;
				}
				if (e >= 0) {
					Message = "Thời hạn bảo dưỡng còn: " + e + " ngày";
				} else {
					Message = "Đã quá hạn bảo dưỡng: " + -1 * e + " ngày";
				}
			} else {
				Message = "Không có dữ liệu Bảo dưỡng từ ngày sử dụng ("
						+ mdf.getViewStringDate(Lr.getTaisan().getNGAY_SU_DUNG()) + ")";
			}
			if (e < valueOf) {
				TableItem ti = new TableItem(table, SWT.NONE);
				ti.setText(new String[] { tableItem.getText(0), tableItem.getText(2), tableItem.getText(1),
						tableItem.getText(5), tableItem.getText(3), Message });
			}
		}
	}

	private void init() throws SQLException {
		FillItem(90);
	}
}
