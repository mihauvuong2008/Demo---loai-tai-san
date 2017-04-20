package View.AssetManagers.CongViec.Suachua;

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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAISAN;
import View.AssetManagers.MainForm;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;
import View.Template.TreeRowStyle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

public class Nhapdanhsach extends Dialog {

	protected Object result;
	protected Shell shlChnPhngTin;
	private Text text;
	private Table table_DanhsachTaisan;
	private final Controler controler;
	private final ArrayList<TAISAN> Data_From_Parent_list_Taisan;
	private ArrayList<TAISAN> Result_danhsachPTTS;
	protected boolean Accept = false;
	protected LOAITAISAN_CAP_III p;
	protected LOAITAISAN_CAP_II n;
	protected LOAITAISAN_CAP_I l;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Nhapdanhsach.class);
	private Combo combo;

	public ArrayList<TAISAN> getResult_danhsachPTTS() {
		return Result_danhsachPTTS;
	}

	public void setResult_danhsachPTTS(ArrayList<TAISAN> result_danhsachPTTS) {
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
	public Nhapdanhsach(Shell parent, int style, NGUOIDUNG user, ArrayList<TAISAN> Selected) {
		super(parent, style);
		controler = new Controler(user);
		this.Data_From_Parent_list_Taisan = Selected;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlChnPhngTin.open();
		shlChnPhngTin.layout();
		Display display = getParent().getDisplay();
		while (!shlChnPhngTin.isDisposed()) {
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
		shlChnPhngTin = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlChnPhngTin.setImage(SWTResourceManager.getImage(Nhapdanhsach.class, "/maintenance-icon (1).png"));
		shlChnPhngTin.setSize(728, 450);
		shlChnPhngTin.setText("Chọn Phương tiện tài sản");
		new FormTemplate().setCenterScreen(shlChnPhngTin);
		TreeRowStyle tsl = new TreeRowStyle();
		shlChnPhngTin.setLayout(new GridLayout(4, false));

		Label lblPhngBan = new Label(shlChnPhngTin, SWT.NONE);
		lblPhngBan.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPhngBan.setText("Phòng ban: ");

		combo = new Combo(shlChnPhngTin, SWT.READ_ONLY);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 100;
		combo.setLayoutData(gd_combo);

		text = new Text(shlChnPhngTin, SWT.BORDER | SWT.RIGHT);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 250;
		text.setLayoutData(gd_text);
		text.setMessage("Tìm theo mã tài sản");

		Button btnTm = new Button(shlChnPhngTin, SWT.NONE);
		GridData gd_btnTm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTm.widthHint = 75;
		btnTm.setLayoutData(gd_btnTm);
		btnTm.setText("Tìm");

		SashForm sashForm = new SashForm(shlChnPhngTin, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		Tree tree_NhomTaisan = new Tree(sashForm, SWT.BORDER);
		TreeItem Tong_Item = new TreeItem(tree_NhomTaisan, SWT.NONE);
		Tong_Item.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		Tong_Item.setImage(SWTResourceManager.getImage(MainForm.class, "/Books-2-icon.png"));
		Tong_Item.setText("Tất cả tài sản");
		setItem_LoaiTaisan(tree_NhomTaisan, Tong_Item);
		Tong_Item.setExpanded(true);
		tree_NhomTaisan.pack();
		tree_NhomTaisan.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					/* get selection */
					TreeItem[] items = tree_NhomTaisan.getSelection();
					if (items.length > 0) {
						l = null;
						n = null;
						p = null;
						l = (LOAITAISAN_CAP_I) items[0].getData("lts");
						n = (LOAITAISAN_CAP_II) items[0].getData("nts");
						p = (LOAITAISAN_CAP_III) items[0].getData("pnts");
						PHONGBAN dv = (PHONGBAN) combo.getData(combo.getText());
						ViewTaiSan(controler.getControl_TAISAN().Data_TaiSan_Mainform_LoaiTaisan(dv, p, n, l));
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		tsl.setTreeItemHeight(tree_NhomTaisan, 20);
		tree_NhomTaisan.pack();

		table_DanhsachTaisan = new Table(sashForm, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_DanhsachTaisan.setLinesVisible(true);
		table_DanhsachTaisan.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("STT");

		TableColumn tblclmnTnMT = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tblclmnTnMT.setWidth(100);
		tblclmnTnMT.setText("Tên mô tả");

		TableColumn tblclmnModel = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tblclmnModel.setWidth(100);
		tblclmnModel.setText("Model");

		TableColumn tblclmnNgySDng = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tblclmnNgySDng.setWidth(100);
		tblclmnNgySDng.setText("Ngày sử dụng");

		TableColumn tblclmnSerial = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tblclmnSerial.setWidth(100);
		tblclmnSerial.setText("Serial");

		TableColumn tblclmnMPtts = new TableColumn(table_DanhsachTaisan, SWT.NONE);
		tblclmnMPtts.setWidth(100);
		tblclmnMPtts.setText("Mã PTTS");
		sashForm.setWeights(new int[] { 618, 1000 });

		Button button = new Button(shlChnPhngTin, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Result_danhsachPTTS = getChecked();
				Accept = true;
				shlChnPhngTin.dispose();
			}

			private ArrayList<TAISAN> getChecked() {
				ArrayList<TAISAN> result = new ArrayList<>();
				for (TableItem t : table_DanhsachTaisan.getItems()) {
					if (t.getChecked()) {
						result.add((TAISAN) t.getData());
					}
				}
				return result;
			}
		});
		GridData gd_button = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 3, 1);
		gd_button.widthHint = 75;
		button.setLayoutData(gd_button);
		button.setText("Xong");

		Button button_1 = new Button(shlChnPhngTin, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Accept = false;
				shlChnPhngTin.dispose();
			}
		});
		GridData gd_button_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button_1.widthHint = 75;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("Đóng");
		init();
	}

	void init() throws SQLException {
		String tmpText = shlChnPhngTin.getText();
		ArrayList<PHONGBAN> pbl = controler.getControl_PHONGBAN().getAllDonvi();
		new Fill_ItemData().setComboBox_DONVI_NOIBO(combo, pbl);
		PHONGBAN dv = (PHONGBAN) combo.getData(combo.getText());
		shlChnPhngTin.setText(tmpText + " [" + dv.getTEN_PHONGBAN() + "]");
		ViewTaiSan(controler.getControl_TAISAN().Data_TaiSan_Mainform_LoaiTaisan(dv, p, n, l));
	};

	protected void ViewTaiSan(ArrayList<TAISAN> row_taisan) {
		table_DanhsachTaisan.removeAll();
		int i = 1;
		ArrayList<TAISAN> view = new ArrayList<>();
		for (TAISAN tm : row_taisan) {
			boolean flag = true;
			for (TAISAN t : Data_From_Parent_list_Taisan) {
				if (t.getMA_TAISAN() == tm.getMA_TAISAN())
					flag = false;
			}
			if (flag)
				view.add(tm);
		}
		for (TAISAN t : view) {
			TableItem tb = new TableItem(table_DanhsachTaisan, SWT.NONE);
			tb.setText(new String[] { "" + i, t.getTEN_TAISAN(), t.getMODEL(),
					mdf.getViewStringDate(t.getNGAY_SU_DUNG()), t.getSERI(), String.valueOf(t.getMA_TAISAN()) });
			tb.setData(t);
			i++;
		}
	}

	private void setItem_LoaiTaisan(Tree tree_NhomTaisan, TreeItem tong_Item) throws SQLException {
		ArrayList<LOAITAISAN_CAP_I> ll = controler.getControl_LOAITAISAN_CAP_I().getAllData();
		for (LOAITAISAN_CAP_I l : ll) {
			TreeItem ti = new TreeItem(tong_Item, SWT.None);
			ti.setText(l.getTEN_LOAITAISAN_CAP_I());
			ArrayList<LOAITAISAN_CAP_II> nl = controler.getControl_LOAITAISAN_CAP_II().getAllData();
			ti.setData("lts", l);
			for (LOAITAISAN_CAP_II n : nl) {
				if (n.getMA_LOAITAISAN_CAP_I() == l.getMA_LOAITAISAN_CAP_I()) {
					TreeItem tii = new TreeItem(ti, SWT.None);
					tii.setText(n.getTEN_LOAITAISAN_CAP_II());
					tii.setData("nts", n);
					ArrayList<LOAITAISAN_CAP_III> pl = controler.getControl_LOAITAISAN_CAP_III().getAllData();
					for (LOAITAISAN_CAP_III p : pl) {
						if (p.getMA_LOAITAISAN_CAP_II() == n.getMA_LOAITAISAN_CAP_II()) {
							TreeItem tiii = new TreeItem(tii, SWT.None);
							tiii.setText(p.getTEN_LOAITAISAN_CAP_III());
							tiii.setData("pnts", p);
						}
					}
				}
			}
		}
	}
}
