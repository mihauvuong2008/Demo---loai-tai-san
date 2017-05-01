package View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;

import com.ibm.icu.util.Calendar;

import Controler.Controler;
import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAISAN;
import DAO.TAPHOSO;
import DAO.VANBAN;
import View.AssetManagers.Hoso.TailenTaphoso;
import View.AssetManagers.Hoso.TapHoso_Creator;
import View.AssetManagers.Hoso.Vanban_View;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class TaoDot_ChuyenGiao_Taisan_Noibo extends Dialog {

	protected Object result;
	protected Shell shlChuynGiaoTi;
	private Text text_TenDotChuyengiao;
	private Table table;
	private Controler controler;
	private NGUOIDUNG user;
	private Integer mAPHONGBAN;
	private String[] dS_MA_TAISAN;
	private Combo combo_Phongban1;
	private Combo combo_Phongban2;
	private TAPHOSO ths;
	private DOT_BANGIAO_TAISAN_NOIBO data;
	private Table table_1;
	private DateTime dateTime;
	private MyDateFormat mdf = new MyDateFormat();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param user
	 * @param mAPHONGBAN
	 * @param dS_MA_TAISAN
	 * @wbp.parser.constructor
	 */
	public TaoDot_ChuyenGiao_Taisan_Noibo(Shell parent, int style, String[] dS_MA_TAISAN, Integer mAPHONGBAN,
			NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		this.dS_MA_TAISAN = dS_MA_TAISAN;
		this.mAPHONGBAN = mAPHONGBAN;
		this.user = user;
		controler = new Controler(user);
	}

	public TaoDot_ChuyenGiao_Taisan_Noibo(Shell shlXemCct, int dialogTrim, DOT_BANGIAO_TAISAN_NOIBO data,
			NGUOIDUNG user2) {
		super(shlXemCct, dialogTrim);
		this.user = user2;
		controler = new Controler(user2);
		this.data = data;
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 * @throws SQLException
	 */
	public Object open() throws SQLException {
		createContents();
		shlChuynGiaoTi.open();
		shlChuynGiaoTi.layout();
		Display display = getParent().getDisplay();
		while (!shlChuynGiaoTi.isDisposed()) {
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
		shlChuynGiaoTi = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlChuynGiaoTi.setImage(user.getIcondata().export_importIcon);
		shlChuynGiaoTi.setSize(679, 420);
		shlChuynGiaoTi.setText("Chuyển giao tài sản nội bộ");
		shlChuynGiaoTi.setLayout(new GridLayout(2, false));
		new FormTemplate().setCenterScreen(shlChuynGiaoTi);

		SashForm sashForm = new SashForm(shlChuynGiaoTi, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblTntChuyn = new Label(composite, SWT.NONE);
		lblTntChuyn.setText("Tên: ");

		text_TenDotChuyengiao = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_text_TenDotChuyengiao = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_TenDotChuyengiao.heightHint = 35;
		text_TenDotChuyengiao.setLayoutData(gd_text_TenDotChuyengiao);

		Label lblNgyThcHin = new Label(composite, SWT.NONE);
		lblNgyThcHin.setText("Ngày thực hiện: ");

		dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVChuyn = new Label(composite, SWT.NONE);
		lblnVChuyn.setText("Đơn vị chuyển: ");

		combo_Phongban1 = new Combo(composite, SWT.READ_ONLY);
		combo_Phongban1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVNhn = new Label(composite, SWT.NONE);
		lblnVNhn.setText("Đơn vị nhận: ");

		combo_Phongban2 = new Combo(composite, SWT.READ_ONLY);
		combo_Phongban2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblHS = new Label(composite, SWT.NONE);
		GridData gd_lblHS = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblHS.verticalIndent = 3;
		lblHS.setLayoutData(gd_lblHS);
		lblHS.setText("Hồ sơ: ");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnVnBn = new TableColumn(table, SWT.NONE);
		tblclmnTnVnBn.setWidth(100);
		tblclmnTnVnBn.setText("TÊN VĂN BẢN");

		TableColumn tblclmnNgyThng = new TableColumn(table, SWT.NONE);
		tblclmnNgyThng.setWidth(150);
		tblclmnNgyThng.setText("NGÀY THÁNG BAN HÀNH");

		TableColumn tblclmnnVBan = new TableColumn(table, SWT.NONE);
		tblclmnnVBan.setWidth(120);
		tblclmnnVBan.setText("ĐƠN VỊ BAN HÀNH");

		TableColumn tblclmnTrchYu = new TableColumn(table, SWT.NONE);
		tblclmnTrchYu.setWidth(150);
		tblclmnTrchYu.setText("TRÍCH YẾU");

		Menu menu = new Menu(table);
		table.setMenu(menu);

		MenuItem mntmThmVnBn = new MenuItem(menu, SWT.NONE);
		mntmThmVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					creatTaphoso();
					Vanban_View vv = new Vanban_View(shlChuynGiaoTi, SWT.DIALOG_TRIM, user, ths, null, false);
					vv.open();
					refreshTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void refreshTable() throws SQLException {
				ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
				fillVanban(vbl);
			}
		});
		mntmThmVnBn.setText("Thêm Văn bản");

		MenuItem mntmXamVnBn = new MenuItem(menu, SWT.NONE);
		mntmXamVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (ti.length > 0) {
					VANBAN vb = (VANBAN) ti[0].getData();
					Vanban_View vv = new Vanban_View(shlChuynGiaoTi, SWT.NONE, user, ths, vb, false);
					try {
						vv.open();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXamVnBn.setText("Xem Văn bản");

		MenuItem mntmXa = new MenuItem(menu, SWT.NONE);
		mntmXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table.getSelection();
				if (ti.length > 0) {
					VANBAN vb = (VANBAN) ti[0].getData();
					try {
						controler.getControl_VANBAN().delete_VANBAN(vb);
						ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
						fillVanban(vbl);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mntmXa.setText("Xóa");

		table_1 = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(45);
		tableColumn.setText("STT");

		TableColumn tblclmnTnTiSn = new TableColumn(table_1, SWT.NONE);
		tblclmnTnTiSn.setWidth(167);
		tblclmnTnTiSn.setText("TÊN TÀI SẢN");

		TableColumn tblclmnNgySDng = new TableColumn(table_1, SWT.NONE);
		tblclmnNgySDng.setWidth(98);
		tblclmnNgySDng.setText("NGÀY SỬ DỤNG");

		TableColumn tblclmnMQunL = new TableColumn(table_1, SWT.NONE);
		tblclmnMQunL.setWidth(100);
		tblclmnMQunL.setText("MÃ QUẢN LÝ");

		TableColumn tblclmnnVQun = new TableColumn(table_1, SWT.NONE);
		tblclmnnVQun.setWidth(120);
		tblclmnnVQun.setText("ĐƠN VỊ QUẢN LÝ");

		Menu menu_1 = new Menu(table_1);
		table_1.setMenu(menu_1);

		MenuItem mntmThm = new MenuItem(menu_1, SWT.NONE);
		mntmThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChonTaisan c = new ChonTaisan(shlChuynGiaoTi, SWT.DIALOG_TRIM, user,
						(PHONGBAN) combo_Phongban1.getData(combo_Phongban1.getText()));
				try {
					c.open();
					if (c.result != null) {// get result
						@SuppressWarnings("unchecked")
						ArrayList<TAISAN> rs = (ArrayList<TAISAN>) c.result;
						if (data != null) {
							if (data.getMA_DOT_BANGIAO_TAISAN_NOIBO() != 0) {
								// Thêm danh sách tài sản nếu đã có dữ liệu sẵn
								InsertTaisanAndRefreshTable(rs);
							}
						} else {
							// Tạo danh sách dự kiến thêm mới
							dS_MA_TAISAN = getFinalDanhsachTaisan(rs);
							refreshTable();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			private void refreshTable() throws NumberFormatException, SQLException {
				ArrayList<TAISAN> taisanlist = new ArrayList<>();
				for (String mts : dS_MA_TAISAN) {
					TAISAN t = controler.getControl_TAISAN().get_Taisan(Integer.valueOf(mts));
					taisanlist.add(t);
				}
				fillTaisan(taisanlist);
			}

			private void InsertTaisanAndRefreshTable(ArrayList<TAISAN> rs) throws SQLException {
				for (TAISAN taisan : rs) {
					controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
							.InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(String.valueOf(taisan.getMA_TAISAN()), data);
				}
				ArrayList<TAISAN> ts = controler.getControl_TAISAN().get_TAINSAN_DOT_BANGIAO_TAISAN_NOIBO(data);
				fillTaisan(ts);
			}

			private String[] getFinalDanhsachTaisan(ArrayList<TAISAN> rs) {

				removeDuplicateTaisan(rs);
				if (rs.size() > 0) {
					return collectData(dS_MA_TAISAN, rs);
				}
				// xóa hết -> ko làm gì:
				return dS_MA_TAISAN;
			}

			private String[] collectData(String[] dS_MA_TAISAN, ArrayList<TAISAN> rs) {
				String[] tmp = new String[dS_MA_TAISAN.length + rs.size()];
				int i = 0;
				for (String string : dS_MA_TAISAN) {
					tmp[i] = string;
					i++;
				}
				for (TAISAN t : rs) {
					String key = String.valueOf(t.getMA_TAISAN());
					tmp[i] = key;
					i++;
				}
				return tmp;
			}

			private void removeDuplicateTaisan(ArrayList<TAISAN> rs) {
				Iterator<TAISAN> iterator = rs.iterator();
				while (iterator.hasNext()) {
					TAISAN t = iterator.next();
					String key = String.valueOf(t.getMA_TAISAN());
					boolean f = false;
					for (String taisan : dS_MA_TAISAN) {
						if (key.equals(taisan))
							f = true;
					}
					if (f)
						iterator.remove();
				}
			}
		});
		mntmThm.setText("Thêm Phương tiện tài sản");

		MenuItem mntmXa_1 = new MenuItem(menu_1, SWT.NONE);
		mntmXa_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] ti = table_1.getSelection();
				try {
					if (ti.length > 0) {
						if (data != null) {
							if (data.getMA_DOT_BANGIAO_TAISAN_NOIBO() != 0) {
								TAISAN ts = (TAISAN) ti[0].getData();
								controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
										.delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(ts, data);
								init();
							}
						} else {
							TAISAN ts = (TAISAN) ti[0].getData();
							String[] tmp = new String[dS_MA_TAISAN.length - 1];
							int i = 0;
							for (String s : dS_MA_TAISAN) {
								if (!s.equals(String.valueOf(ts.getMA_TAISAN()))) {
									tmp[i] = s;
									i++;
								}
							}
							dS_MA_TAISAN = tmp;
							ArrayList<TAISAN> taisanlist = new ArrayList<>();
							if (dS_MA_TAISAN != null) {
								for (String mts : dS_MA_TAISAN) {
									TAISAN t = controler.getControl_TAISAN().get_Taisan(Integer.valueOf(mts));
									taisanlist.add(t);
								}
								fillTaisan(taisanlist);
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmXa_1.setText("Xóa");
		sashForm.setWeights(new int[] { 250, 113 });

		Button btnHonTt = new Button(shlChuynGiaoTi, SWT.NONE);
		btnHonTt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					creatTaphoso();// tạo tập hồ sơ
					if (ths.getMA_TAPHOSO() > 0) {
						if (data == null)// tạo mới nếu thêm mới
							data = getDotBangiaoTaisan();
						if (data.getMA_DOT_BANGIAO_TAISAN_NOIBO() <= 0) {
							// thêm mới
							int Key = controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
									.InsertDOT_BANGIAO_TAISAN_NOIBO(data);
							data.setMA_DOT_BANGIAO_TAISAN_NOIBO(Key);
						} else {// cập nhật thông tin cơ bản
							controler.getControl_DOT_BANGIAO_TAISAN_NOIBO().update_DOT_BANGIAO_TAISAN_NOIBO(data);
						}
						if (dS_MA_TAISAN != null)
							for (String string : dS_MA_TAISAN) {
								// cập nhật danh sách tài sản
								TAISAN t = controler.getControl_TAISAN().get_Taisan(Integer.valueOf(string));
								if (t.getMA_DON_VI_SU_DUNG() == data.getBEN_GIAO()) {
									controler.getControl_DOT_BANGIAO_TAISAN_NOIBO()
											.InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(string, data);
									// Thực hiện chuyển tài sản:
									controler.getControl_TAISAN().Update_Donvi_Sudung(string, mAPHONGBAN);
								}
							}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				shlChuynGiaoTi.dispose();
			}
		});
		GridData gd_btnHonTt = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnHonTt.widthHint = 75;
		btnHonTt.setLayoutData(gd_btnHonTt);
		btnHonTt.setText("Hoàn tất");

		Button btnng = new Button(shlChuynGiaoTi, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DeleteTaphosoIfnullData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				shlChuynGiaoTi.dispose();
			}

			private void DeleteTaphosoIfnullData() throws SQLException {
				if (data == null) {
					if (ths != null)
						controler.getControl_TAPHOSO().delete_TAPHOSO(ths);
				}
			}
		});
		GridData gd_btnng = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("Đóng");
		init();
	}

	protected DOT_BANGIAO_TAISAN_NOIBO getDotBangiaoTaisan() {
		DOT_BANGIAO_TAISAN_NOIBO data = new DOT_BANGIAO_TAISAN_NOIBO();
		data.setTEN_DOT_BANGIAO_TAISAN_NOIBO(text_TenDotChuyengiao.getText());
		data.setBEN_GIAO(((PHONGBAN) combo_Phongban1.getData(combo_Phongban1.getText())).getMA_PHONGBAN());
		data.setBEN_NHAN(((PHONGBAN) combo_Phongban2.getData(combo_Phongban2.getText())).getMA_PHONGBAN());
		data.setNGAY_THUCHIEN(mdf.getDate(dateTime));
		data.setMA_TAPHOSO(ths.getMA_TAPHOSO());
		data.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
		return data;
	}

	private void init() throws SQLException {
		Fill_ItemData fi = new Fill_ItemData();
		ArrayList<PHONGBAN> pa = controler.getControl_PHONGBAN().getAllDonvi();
		fi.setComboBox_DONVI_NOIBO(combo_Phongban1, pa);
		fi.setComboBox_DONVI_NOIBO(combo_Phongban2, pa);
		PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(mAPHONGBAN);
		if (pb != null) {
			combo_Phongban2.setText(pb.getTEN_PHONGBAN());
		}
		if (dS_MA_TAISAN != null) {
			TAISAN ts0 = controler.getControl_TAISAN().get_Taisan(Integer.valueOf(dS_MA_TAISAN[0]));
			PHONGBAN pb1 = controler.getControl_PHONGBAN().get_PHONGBAN(ts0.getMA_DON_VI_SU_DUNG());
			if (pb1 != null) {
				combo_Phongban1.setText(pb1.getTEN_PHONGBAN());
			}
		}
		ArrayList<TAISAN> taisanlist = new ArrayList<>();
		if (dS_MA_TAISAN != null) {
			for (String mts : dS_MA_TAISAN) {
				TAISAN ts = controler.getControl_TAISAN().get_Taisan(Integer.valueOf(mts));
				taisanlist.add(ts);
			}
			fillTaisan(taisanlist);
		}
		if (data != null) {
			text_TenDotChuyengiao.setText(data.getTEN_DOT_BANGIAO_TAISAN_NOIBO());
			dateTime.setDate(mdf.getCalendar(data.getNGAY_THUCHIEN()).get(Calendar.YEAR),
					mdf.getCalendar(data.getNGAY_THUCHIEN()).get(Calendar.MONTH),
					mdf.getCalendar(data.getNGAY_THUCHIEN()).get(Calendar.DAY_OF_MONTH));
			PHONGBAN Bengiao = controler.getControl_PHONGBAN().get_PHONGBAN(data.getBEN_GIAO());
			PHONGBAN Bennhan = controler.getControl_PHONGBAN().get_PHONGBAN(data.getBEN_NHAN());
			combo_Phongban1.setText(Bengiao.getTEN_PHONGBAN());
			combo_Phongban2.setText(Bennhan.getTEN_PHONGBAN());
			ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(data.getMA_TAPHOSO());
			if (ths != null) {
				ArrayList<VANBAN> vbl = controler.getControl_VANBAN().get_AllVanban(ths);
				fillVanban(vbl);
			}
			ArrayList<TAISAN> ts = controler.getControl_TAISAN().get_TAINSAN_DOT_BANGIAO_TAISAN_NOIBO(data);
			fillTaisan(ts);
		}
	}

	private void creatTaphoso() throws SQLException {
		if (ths == null)
			ths = new TAPHOSO();
		if (ths.getMA_TAPHOSO() <= 0) {
			TapHoso_Creator tc = new TapHoso_Creator(user);
			ths = tc.getTaphoso(
					"Hồ sơ bàn giao tài sản giữa " + combo_Phongban1.getText() + " và " + combo_Phongban2.getText(),
					"Hồ sơ Bàn giao tài sản giữa " + combo_Phongban1.getText() + " và " + combo_Phongban2.getText()
							+ " (" + user.getTEN_CAN_BO() + ")");
			TailenTaphoso tlt = new TailenTaphoso(shlChuynGiaoTi, SWT.DIALOG_TRIM, user, ths);
			tlt.open();
			ths = (TAPHOSO) tlt.result;
		}
	}

	private void fillVanban(ArrayList<VANBAN> vbl) {
		table.removeAll();
		int i = 1;
		for (VANBAN ts : vbl) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(new String[] { (i++) + "", ts.getSO_VANBAN(), mdf.getViewStringDate(ts.getNGAY_BAN_HANH()),
					ts.getCO_QUAN_BAN_HANH(), ts.getTRICH_YEU() });
			ti.setData(ts);
		}
	}

	private void fillTaisan(ArrayList<TAISAN> taisanlist) throws SQLException {
		table_1.removeAll();
		int i = 1;
		for (TAISAN ts : taisanlist) {
			PHONGBAN phongban = controler.getControl_PHONGBAN().get_PHONGBAN(ts.getMA_DON_VI_SU_DUNG());
			TableItem ti = new TableItem(table_1, SWT.NONE);
			ti.setText(new String[] { (i++) + "", ts.getTEN_TAISAN(), mdf.getViewStringDate(ts.getNGAY_SU_DUNG()),
					ts.getMA_TANSAN_KETOAN(), phongban.getTEN_PHONGBAN() });
			ti.setData(ts);
		}
	}
}
