package View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.util.Calendar;

import Controler.Controler;
import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;

public class Xem_Dot_Chuyengiao_Noibo extends Dialog {

	protected Object result;
	protected Shell shlXemCct;
	private Table table;
	private DateTime dateTime;
	private DateTime dateTime_1;
	Controler controler;
	private Text text_search;
	MyDateFormat mdf = new MyDateFormat();
	private NGUOIDUNG user;
	private Text text_TenDotBangiao;
	private Text text_Bengiao;
	private Text text_Bennhan;
	private Table table_1;
	private Table table_2;
	private DateTime dateTime_2;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Xem_Dot_Chuyengiao_Noibo(Shell parent, int style, NGUOIDUNG user) {
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
		shlXemCct.open();
		shlXemCct.layout();
		Display display = getParent().getDisplay();
		while (!shlXemCct.isDisposed()) {
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
		shlXemCct = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlXemCct.setImage(SWTResourceManager.getImage(Xem_Dot_Chuyengiao_Noibo.class, "/Import-export-icon.png"));
		shlXemCct.setSize(734, 454);
		shlXemCct.setText("Xem Các Đợt bàn giao Tài sản");
		shlXemCct.setLayout(new GridLayout(7, false));
		new FormTemplate().setCenterScreen(shlXemCct);

		SashForm sashForm = new SashForm(shlXemCct, SWT.NONE);
		GridData gd_sashForm = new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1);
		gd_sashForm.widthHint = 676;
		sashForm.setLayoutData(gd_sashForm);

		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table.getSelection();
				if (ti.length > 0) {
					DOT_BANGIAO_TAISAN_NOIBO dbtn = (DOT_BANGIAO_TAISAN_NOIBO) ti[0].getData();
					text_TenDotBangiao.setText(dbtn.getTEN_DOT_BANGIAO_TAISAN_NOIBO());
					dateTime_2.setDate(mdf.getCalendar(dbtn.getNGAY_THUCHIEN()).get(Calendar.YEAR),
							mdf.getCalendar(dbtn.getNGAY_THUCHIEN()).get(Calendar.MONTH),
							mdf.getCalendar(dbtn.getNGAY_THUCHIEN()).get(Calendar.DAY_OF_MONTH));
					try {
						PHONGBAN bengiao = controler.getControl_PHONGBAN().get_PHONGBAN(dbtn.getBEN_GIAO());
						PHONGBAN bennhan = controler.getControl_PHONGBAN().get_PHONGBAN(dbtn.getBEN_NHAN());
						text_Bengiao.setText(bengiao.getTEN_PHONGBAN());
						text_Bennhan.setText(bennhan.getTEN_PHONGBAN());
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dbtn.getMA_TAPHOSO());
						ArrayList<VANBAN> dataVB = controler.getControl_VANBAN().get_AllVanban(ths);
						fillVanban(dataVB);
						ArrayList<TAISAN> dataTS = controler.getControl_TAISAN()
								.get_TAINSAN_DOT_BANGIAO_TAISAN_NOIBO(dbtn);
						fillTaisan(dataTS);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private void fillTaisan(ArrayList<TAISAN> dataTS) throws SQLException {
				table_2.removeAll();
				int i = 1;
				for (TAISAN ts : dataTS) {
					PHONGBAN phongban = controler.getControl_PHONGBAN().get_PHONGBAN(ts.getMA_DON_VI_SU_DUNG());
					TableItem ti = new TableItem(table_2, SWT.NONE);
					ti.setText(
							new String[] { (i++) + "", ts.getTEN_TAISAN(), mdf.getViewStringDate(ts.getNGAY_SU_DUNG()),
									ts.getMA_TANSAN_KETOAN(), phongban.getTEN_PHONGBAN() });
					ti.setData(ts);
				}
			}

			private void fillVanban(ArrayList<VANBAN> dataVB) {
				table_1.removeAll();
				int i = 1;
				for (VANBAN ts : dataVB) {
					TableItem ti = new TableItem(table_1, SWT.NONE);
					ti.setText(
							new String[] { (i++) + "", ts.getSO_VANBAN(), mdf.getViewStringDate(ts.getNGAY_BAN_HANH()),
									ts.getCO_QUAN_BAN_HANH(), ts.getTRICH_YEU() });
					ti.setData(ts);
				}
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTntBn = new TableColumn(table, SWT.NONE);
		tblclmnTntBn.setWidth(180);
		tblclmnTntBn.setText("TÊN ĐỢT BÀN GIAO");

		TableColumn tblclmnNgyThcHin = new TableColumn(table, SWT.NONE);
		tblclmnNgyThcHin.setWidth(120);
		tblclmnNgyThcHin.setText("NGÀY THỰC HIỆN");

		TableColumn tblclmnBnGiao = new TableColumn(table, SWT.NONE);
		tblclmnBnGiao.setWidth(100);
		tblclmnBnGiao.setText("BÊN GIAO");

		TableColumn tblclmnBnNhn = new TableColumn(table, SWT.NONE);
		tblclmnBnNhn.setWidth(100);
		tblclmnBnNhn.setText("BÊN NHẬN");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmXemtBn = new MenuItem(menu, SWT.NONE);
		mntmXemtBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table.getSelection();
				if (ti.length > 0) {
					TaoDot_ChuyenGiao_Taisan_Noibo tctn = new TaoDot_ChuyenGiao_Taisan_Noibo(shlXemCct, SWT.DIALOG_TRIM,
							(DOT_BANGIAO_TAISAN_NOIBO) ti[0].getData(), user);
					try {
						tctn.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXemtBn.setText("Xem Đợt bàn giao");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem ti[] = table.getSelection();
				if (ti.length > 0) {
					try {
						controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
								.delete_DOT_BANGIAO_TAISAN_NOIBO((DOT_BANGIAO_TAISAN_NOIBO) ti[0].getData());
						init();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXa.setText("Xóa");

		TableColumn tblclmnTnTpH = new TableColumn(table, SWT.NONE);
		tblclmnTnTpH.setWidth(120);
		tblclmnTnTpH.setText("TÊN TẬP HỒ SƠ");

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblTn = new Label(composite, SWT.NONE);
		lblTn.setText("Tên: ");

		text_TenDotBangiao = new Text(composite, SWT.BORDER);
		text_TenDotBangiao.setText("");
		text_TenDotBangiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyThcHin = new Label(composite, SWT.NONE);
		lblNgyThcHin.setText("Ngày thực hiện:");

		dateTime_2 = new DateTime(composite, SWT.BORDER);
		dateTime_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblBnGiao = new Label(composite, SWT.NONE);
		lblBnGiao.setText("Bên giao: ");

		text_Bengiao = new Text(composite, SWT.BORDER);
		text_Bengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblBnNhn = new Label(composite, SWT.NONE);
		lblBnNhn.setText("Bên nhận: ");

		text_Bennhan = new Text(composite, SWT.BORDER);
		text_Bennhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		SashForm sashForm_1 = new SashForm(composite, SWT.VERTICAL);
		sashForm_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		table_1 = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("TÊN VĂN BẢN");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("NGÀY THÁNG BAN HÀNH");

		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(120);
		tableColumn_3.setText("ĐƠN VỊ BAN HÀNH");

		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(150);
		tableColumn_4.setText("TRÍCH YẾU");

		table_2 = new Table(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		TableColumn tableColumn_5 = new TableColumn(table_2, SWT.NONE);
		tableColumn_5.setWidth(45);
		tableColumn_5.setText("STT");

		TableColumn tableColumn_6 = new TableColumn(table_2, SWT.NONE);
		tableColumn_6.setWidth(167);
		tableColumn_6.setText("TÊN TÀI SẢN");

		TableColumn tableColumn_7 = new TableColumn(table_2, SWT.NONE);
		tableColumn_7.setWidth(98);
		tableColumn_7.setText("NGÀY SỬ DỤNG");

		TableColumn tableColumn_8 = new TableColumn(table_2, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("MÃ QUẢN LÝ");

		TableColumn tableColumn_9 = new TableColumn(table_2, SWT.NONE);
		tableColumn_9.setWidth(120);
		tableColumn_9.setText("ĐƠN VỊ QUẢN LÝ");
		sashForm_1.setWeights(new int[] { 1, 1 });
		sashForm.setWeights(new int[] { 1000, 618 });

		Label lblTNgy = new Label(shlXemCct, SWT.NONE);
		lblTNgy.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTNgy.setText("Từ ngày: ");

		dateTime = new DateTime(shlXemCct, SWT.BORDER);

		Label lbln = new Label(shlXemCct, SWT.NONE);
		lbln.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lbln.setText("Đến: ");

		dateTime_1 = new DateTime(shlXemCct, SWT.BORDER);

		text_search = new Text(shlXemCct, SWT.BORDER);
		text_search.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnXem = new Button(shlXemCct, SWT.NONE);
		btnXem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<DOT_BANGIAO_TAISAN_NOIBO> data;
				try {
					data = controler.getControl_DOT_BANGIAO_TAISAN_NOIBO().get_DOT_BANGIAO_TAISAN_NOIBO_list(
							mdf.getDate(dateTime), mdf.getDate(dateTime_1), text_search.getText());
					FillData(data);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXem = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXem.widthHint = 75;
		btnXem.setLayoutData(gd_btnXem);
		btnXem.setText("Xem");

		Button btnng = new Button(shlXemCct, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlXemCct.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	private void init() throws SQLException {
		ArrayList<DOT_BANGIAO_TAISAN_NOIBO> data = controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
				.get_All_DOT_BANGIAO_TAISAN_NOIBO();
		FillData(data);
	}

	private void FillData(ArrayList<DOT_BANGIAO_TAISAN_NOIBO> data) throws SQLException {
		table.removeAll();
		int i = 1;
		for (DOT_BANGIAO_TAISAN_NOIBO dot_BANGIAO_TAISAN_NOIBO : data) {
			PHONGBAN Bengiao = controler.getControl_PHONGBAN().get_PHONGBAN(dot_BANGIAO_TAISAN_NOIBO.getBEN_GIAO());
			PHONGBAN Bennhan = controler.getControl_PHONGBAN().get_PHONGBAN(dot_BANGIAO_TAISAN_NOIBO.getBEN_NHAN());
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(dot_BANGIAO_TAISAN_NOIBO.getMA_TAPHOSO());
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(new String[] { (i++) + "", dot_BANGIAO_TAISAN_NOIBO.getTEN_DOT_BANGIAO_TAISAN_NOIBO(),
					new MyDateFormat().getViewStringDate(dot_BANGIAO_TAISAN_NOIBO.getNGAY_THUCHIEN()),
					Bengiao.getTEN_PHONGBAN(), Bennhan.getTEN_PHONGBAN(), ths == null ? "--" : ths.getTEN_TAPHOSO() });
			ti.setData(dot_BANGIAO_TAISAN_NOIBO);
		}
	}

}
