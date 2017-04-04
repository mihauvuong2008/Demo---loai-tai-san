package View.AssetManagers.ThuvienDexuat;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.TAP_HO_SO;
import DAO.VANBAN;
import View.AssetManagers.Hoso.TAPHOSO_View;
import View.AssetManagers.Hoso.Vanban_View;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.custom.SashForm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ThuvienDexuat extends Dialog {

	protected Object result;
	protected Shell shlThVin;
	private Text text_pattern;
	private Text text_Madexuat;
	private Table table_Taphoso;
	private Text text_Sodexuat;
	private Text text_Trichyeu;
	private Text text_Canboxuly;
	private DateTime dateTime_Ngaybanhanh;
	private DateTime dateTime_Ngaytiepnhan;
	private DateTime dateTime_Ngaychuyengiao;
	private Button btnDSB;
	private Button btnDTT;
	private Button btnDGT;
	private Tree tree;
	private Combo combo_Donvi;
	private DE_XUAT Selected;
	private DateTime dateTime_Begin;
	private DateTime dateTime_End;
	private static NGUOIDUNG user;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private static final Log log = LogFactory.getLog(ThuvienDexuat.class);

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ThuvienDexuat(Shell parent, int style, NGUOIDUNG user) {
		super(parent, style);
		setText("SWT Dialog");
		ThuvienDexuat.user = user;
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
		shlThVin.open();
		shlThVin.layout();
		Display display = getParent().getDisplay();
		while (!shlThVin.isDisposed()) {
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
		shlThVin = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shlThVin.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/library-icon_2.png"));
		shlThVin.setSize(810, 500);
		new FormTemplate().setCenterScreen(shlThVin);
		shlThVin.setText("Th\u01B0 vi\u1EC7n \u0110\u1EC1 xu\u1EA5t");
		shlThVin.setLayout(new GridLayout(7, false));

		ToolBar toolBar = new ToolBar(shlThVin, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 7, 1));

		ToolItem tltmXemVnBn = new ToolItem(toolBar, SWT.NONE);
		tltmXemVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] tbil = table_Taphoso.getSelection();
					if (tbil.length > 0) {
						VANBAN vb = (VANBAN) tbil[0].getData();
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(vb.getMA_TAPHOSO());
						Vanban_View vbv = new Vanban_View(shlThVin, SWT.DIALOG_TRIM, user, ths, vb, true);
						vbv.open();
					}
				} catch (SQLException e1) {
					System.out.println(e1.getErrorCode());
					e1.printStackTrace();
				}
			}
		});
		tltmXemVnBn.setText("Xem văn bản");
		tltmXemVnBn.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/document-icon.png"));

		ToolItem tltmCpNhpH = new ToolItem(toolBar, SWT.NONE);
		tltmCpNhpH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					CapnhatHoso();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmCpNhpH.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/Actions-document-edit-icon (1).png"));
		tltmCpNhpH.setText("C\u1EADp nh\u1EADp H\u1ED3 s\u01A1");

		ToolItem tltmXaXut = new ToolItem(toolBar, SWT.NONE);
		tltmXaXut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					XoaHoso();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		tltmXaXut.setText("X\u00F3a H\u1ED3 s\u01A1");
		tltmXaXut.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/Document-Delete-icon (1).png"));

		@SuppressWarnings("unused")
		ToolItem toolItem = new ToolItem(toolBar, SWT.SEPARATOR);

		ToolItem tltmLuThayi = new ToolItem(toolBar, SWT.NONE);
		tltmLuThayi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					DE_XUAT dx = getDexuat();
					if (dx != null) {
						controler.getControl_DEXUAT().update_Dexuat(dx);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private DE_XUAT getDexuat() {
				if (Selected == null)
					return null;
				DE_XUAT result = Selected;
				result.setSODEXUAT(text_Sodexuat.getText());
				result.setNGAYTHANG_VANBAN(mdf.getDate(dateTime_Ngaybanhanh));
				PHONGBAN pb = (PHONGBAN) combo_Donvi.getData(combo_Donvi.getText());
				result.setMA_PHONGBAN(pb.getMA_PHONGBAN());
				result.setGHI_CHU(text_Trichyeu.getText());
				if (result.getTHOI_DIEM_CHUYEN_GIAO() != null)
					result.setTHOI_DIEM_CHUYEN_GIAO(mdf.getDate(dateTime_Ngaychuyengiao));
				if (result.getTHOI_DIEM_BAT_DAU() != null)
					result.setTHOI_DIEM_BAT_DAU(mdf.getDate(dateTime_Ngaytiepnhan));
				return result;
			}
		});
		tltmLuThayi.setText("L\u01B0u \u0110\u1EC1 xu\u1EA5t");
		tltmLuThayi.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/Actions-document-save-icon (1).png"));

		SashForm sashForm = new SashForm(shlThVin, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new GridLayout(3, false));

		tree = new Tree(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		tree.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				try {
					TreeItem[] til = tree.getSelection();
					if (til.length > 0) {
						DE_XUAT dx = (DE_XUAT) til[0].getData();
						if (dx != null) {
							viewDexuat(dx);

							Selected = dx;
						}
					} else {
						Selected = null;
					}
				} catch (SQLException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}

			private void viewDexuat(DE_XUAT dx) throws SQLException {
				text_Madexuat.setText(dx.getMA_DE_XUAT() + "");
				text_Sodexuat.setText(dx.getSODEXUAT());
				text_Trichyeu.setText(dx.getGHI_CHU());
				NGUOIDUNG nd = controler.getControl_NGUOIDUNG().get_NGUOIDUNG(dx.getTEN_TAI_KHOAN());
				PHONGBAN pb = (controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()));
				if (pb != null)
					combo_Donvi.setText(pb.getTEN_PHONGBAN());
				if (nd != null)
					text_Canboxuly.setText(nd.getTEN_CAN_BO());
				setDate(dateTime_Ngaybanhanh, dx.getNGAYTHANG_VANBAN());
				setDate(dateTime_Ngaytiepnhan, dx.getTHOI_DIEM_BAT_DAU());
				setDate(dateTime_Ngaychuyengiao, dx.getTHOI_DIEM_CHUYEN_GIAO());
				fillTapHoso(dx.getMA_TAPHOSO());
			}
		});

		TreeColumn trclmnStt = new TreeColumn(tree, SWT.NONE);
		trclmnStt.setWidth(45);
		trclmnStt.setText("STT");

		TreeColumn trclmnSoDeXuat = new TreeColumn(tree, SWT.NONE);
		trclmnSoDeXuat.setWidth(150);
		trclmnSoDeXuat.setText("S\u1ED0 \u0110\u1EC0 XU\u1EA4T");

		TreeColumn trclmnnV = new TreeColumn(tree, SWT.NONE);
		trclmnnV.setWidth(100);
		trclmnnV.setText("\u0110\u01A0N V\u1ECA");

		TreeColumn trclmnNgyBanHnh = new TreeColumn(tree, SWT.NONE);
		trclmnNgyBanHnh.setWidth(120);
		trclmnNgyBanHnh.setText("NG\u00C0Y BAN H\u00C0NH");

		TreeColumn trclmnTrchYu = new TreeColumn(tree, SWT.NONE);
		trclmnTrchYu.setWidth(100);
		trclmnTrchYu.setText("TR\u00CDCH Y\u1EBEU");

		TreeColumn trclmnLoiCngVic = new TreeColumn(tree, SWT.NONE);
		trclmnLoiCngVic.setWidth(120);
		trclmnLoiCngVic.setText("LOẠI CÔNG VIỆC");

		Menu menu_1 = new Menu(tree);
		tree.setMenu(menu_1);

		MenuItem mntmCpNhtH = new MenuItem(menu_1, SWT.NONE);
		mntmCpNhtH.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					CapnhatHoso();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmCpNhtH.setText("Cập nhật Hồ sơ");

		MenuItem mntmXaHS = new MenuItem(menu_1, SWT.NONE);
		mntmXaHS.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					XoaHoso();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXaHS.setText("Xóa Hồ sơ");

		btnDSB = new Button(composite_1, SWT.CHECK);
		btnDSB.setSelection(true);
		btnDSB.setText("S\u1EEDa ch\u1EEFa - B\u1EA3o d\u01B0\u1EE1ng");

		btnDTT = new Button(composite_1, SWT.CHECK);
		btnDTT.setSelection(true);
		btnDTT.setText("Mua s\u1EAFm - ti\u1EBFp nh\u1EADn");

		btnDGT = new Button(composite_1, SWT.CHECK);
		btnDGT.setSelection(true);
		btnDGT.setText("Thanh l\u00FD");

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblMXut = new Label(composite, SWT.NONE);
		lblMXut.setText("M\u00E3 \u0110\u1EC1 xu\u1EA5t:");

		text_Madexuat = new Text(composite, SWT.READ_ONLY);
		text_Madexuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Madexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblSXut = new Label(composite, SWT.NONE);
		lblSXut.setText("S\u1ED1 \u0110\u1EC1 xu\u1EA5t:");

		text_Sodexuat = new Text(composite, SWT.BORDER);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnVBan = new Label(composite, SWT.NONE);
		lblnVBan.setText("\u0110\u01A1n v\u1ECB ban h\u00E0nh:");

		combo_Donvi = new Combo(composite, SWT.READ_ONLY);
		combo_Donvi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Label lblNgyThngBan = new Label(composite, SWT.NONE);
		lblNgyThngBan.setText("Ng\u00E0y ban h\u00E0nh:");

		dateTime_Ngaybanhanh = new DateTime(composite, SWT.BORDER);
		dateTime_Ngaybanhanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblTrchYu = new Label(composite, SWT.NONE);
		GridData gd_lblTrchYu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTrchYu.verticalIndent = 3;
		lblTrchYu.setLayoutData(gd_lblTrchYu);
		lblTrchYu.setText("Giới thiệu:");

		text_Trichyeu = new Text(composite, SWT.BORDER);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblCnBX = new Label(composite, SWT.NONE);
		lblCnBX.setText("C\u00E1n b\u1ED9 x\u1EED l\u00FD:");

		text_Canboxuly = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		text_Canboxuly.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyTipNhn = new Label(composite, SWT.NONE);
		lblNgyTipNhn.setText("Ng\u00E0y ti\u1EBFp nh\u1EADn:");

		dateTime_Ngaytiepnhan = new DateTime(composite, SWT.BORDER);
		dateTime_Ngaytiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label lblNgyChuynGiao = new Label(composite, SWT.NONE);
		lblNgyChuynGiao.setText("Ng\u00E0y chuy\u1EC3n giao:");

		dateTime_Ngaychuyengiao = new DateTime(composite, SWT.BORDER);
		dateTime_Ngaychuyengiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		table_Taphoso = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_Taphoso.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table_Taphoso.setHeaderVisible(true);
		table_Taphoso.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table_Taphoso, SWT.NONE);
		tblclmnStt.setWidth(45);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnFile = new TableColumn(table_Taphoso, SWT.NONE);
		tblclmnTnFile.setWidth(100);
		tblclmnTnFile.setText("T\u00CAN V\u0102N B\u1EA2N");

		TableColumn tblclmnCQuanBan = new TableColumn(table_Taphoso, SWT.NONE);
		tblclmnCQuanBan.setWidth(150);
		tblclmnCQuanBan.setText("C\u01A0 QUAN BAN H\u00C0NH");

		TableColumn tblclmnTrchYu = new TableColumn(table_Taphoso, SWT.NONE);
		tblclmnTrchYu.setWidth(100);
		tblclmnTrchYu.setText("TR\u00CDCH Y\u1EBEU");

		Menu menu = new Menu(table_Taphoso);
		table_Taphoso.setMenu(menu);

		MenuItem mntmXemVnBn = new MenuItem(menu, SWT.NONE);
		mntmXemVnBn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TableItem[] tbil = table_Taphoso.getSelection();
					if (tbil.length > 0) {
						VANBAN vb = (VANBAN) tbil[0].getData();
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(vb.getMA_TAPHOSO());
						Vanban_View vbv = new Vanban_View(shlThVin, SWT.DIALOG_TRIM, user, ths, vb, true);
						vbv.open();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmXemVnBn.setText("Xem Văn bản");
		sashForm.setWeights(new int[] { 1000, 618 });

		Label lblTNgy = new Label(shlThVin, SWT.NONE);
		lblTNgy.setText("T\u1EEB ng\u00E0y:");

		dateTime_Begin = new DateTime(shlThVin, SWT.BORDER);

		Label lblnNgy = new Label(shlThVin, SWT.NONE);
		lblnNgy.setText("\u0110\u1EBFn ng\u00E0y:");

		dateTime_End = new DateTime(shlThVin, SWT.BORDER);

		text_pattern = new Text(shlThVin, SWT.BORDER);
		text_pattern.setMessage("Nhập số đề xuất");
		GridData gd_text_pattern = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_pattern.widthHint = 250;
		text_pattern.setLayoutData(gd_text_pattern);

		Button btnTm = new Button(shlThVin, SWT.NONE);
		btnTm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					LoadData(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End), text_pattern.getText());
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnTm.setImage(SWTResourceManager.getImage(ThuvienDexuat.class, "/Accept-icon (1).png"));
		GridData gd_btnTm = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_btnTm.widthHint = 75;
		btnTm.setLayoutData(gd_btnTm);
		btnTm.setText("Xem");

		Button btnng = new Button(shlThVin, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlThVin.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");
		init();
	}

	protected void CapnhatHoso() throws SQLException {
		if (Selected != null) {
			TAP_HO_SO ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Selected.getMA_TAPHOSO()));
			if (ths == null) {
				int Ma_NewTapHoso = (controler.getControl_TAPHOSO()).Create_TAP_HO_SO(new TAP_HO_SO());
				if (Ma_NewTapHoso > 0) {
					controler.getControl_DEXUAT().update_TapHoso(Selected, Ma_NewTapHoso);
					Selected.setMA_TAPHOSO(Ma_NewTapHoso);
				}
				ths = (controler.getControl_TAPHOSO().get_TAP_HO_SO(Ma_NewTapHoso));
			}
			TAPHOSO_View thsV = new TAPHOSO_View(shlThVin, SWT.DIALOG_TRIM, user, ths, false);
			thsV.open();
			LoadData(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End), text_pattern.getText());
		}
	}

	protected void XoaHoso() throws SQLException {
		if (Selected != null) {
			TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(Selected.getMA_TAPHOSO());
			if (ths != null) {
				controler.getControl_TAPHOSO().delete_TAPHOSO(ths);
				fillTapHoso(ths.getMA_TAPHOSO());
			}
		}
	}

	private void init() throws SQLException {
		Date thisDay = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDay);
		cal.add(Calendar.DATE, -365);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		dateTime_Begin.setDate(year, month, day);

		ArrayList<PHONGBAN> pb = (controler.getControl_PHONGBAN()).getAllDonvi();
		if (pb != null)
			(new Fill_ItemData()).setComboBox_DONVI_NOIBO(combo_Donvi, pb);
		LoadData(mdf.getDate(dateTime_Begin), mdf.getDate(dateTime_End), text_pattern.getText());
	}

	protected void fillTapHoso(int ma_TAPHOSO) throws SQLException {
		table_Taphoso.removeAll();
		TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(ma_TAPHOSO);
		if (ths != null) {
			ArrayList<VANBAN> vbl = (controler.getControl_VANBAN()).get_AllVanban(ths);
			int i = 1;
			for (VANBAN vb : vbl) {
				TableItem item = new TableItem(table_Taphoso, SWT.NONE);
				item.setText(new String[] { i + "", vb.getSO_VANBAN(), vb.getCO_QUAN_BAN_HANH(), vb.getTRICH_YEU() });
				item.setData(vb);
				i++;
			}
		}
	}

	private void LoadData(Date Begin, Date End, String text_pattern) throws SQLException {
		tree.removeAll();
		int i = 1;
		if (btnDSB.getSelection()) {
			ArrayList<DE_XUAT> dxl = controler.getControl_DEXUAT().get_All_Dexuat_Suachua_Baoduong(Begin, End,
					text_pattern);
			if (dxl != null)
				for (DE_XUAT dx : dxl) {
					TreeItem ti = new TreeItem(tree, SWT.NONE);
					PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
					if (pb != null)
						ti.setText(new String[] { i + "", dx.getSODEXUAT(), pb.getTEN_PHONGBAN(),
								mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()), dx.getGHI_CHU(),
								"Sửa chữa - Bảo dưỡng" });
					ti.setData(dx);
					i++;
				}
		}
		if (btnDTT.getSelection()) {
			ArrayList<DE_XUAT> dxl = controler.getControl_DEXUAT().get_All_Dexuat_Muasam(Begin, End, text_pattern);
			if (dxl != null)
				for (DE_XUAT dx : dxl) {
					TreeItem ti = new TreeItem(tree, SWT.NONE);
					PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
					if (pb != null)
						ti.setText(new String[] { i + "", dx.getSODEXUAT(), pb.getTEN_PHONGBAN(),
								mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()), dx.getGHI_CHU(),
								"Mua sắm - Tiếp nhận" });
					ti.setData(dx);
					i++;
				}
		}
		if (btnDGT.getSelection()) {
			ArrayList<DE_XUAT> dxl = controler.getControl_DEXUAT().get_All_Dexuat_Thanhly(Begin, End, text_pattern);
			if (dxl != null)
				for (DE_XUAT dx : dxl) {
					TreeItem ti = new TreeItem(tree, SWT.NONE);
					PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
					if (pb != null)
						ti.setText(new String[] { i + "", dx.getSODEXUAT(), pb.getTEN_PHONGBAN(),
								mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()), dx.getGHI_CHU(),
								"Thanh lý - Bàn giao" });
					ti.setData(dx);
					i++;
				}
		}
		if (Selected != null)
			for (TreeItem ti : tree.getItems()) {
				DE_XUAT dx = (DE_XUAT) ti.getData();
				if (dx.getMA_DE_XUAT() == Selected.getMA_DE_XUAT()) {
					tree.select(ti);
					break;
				}
			}
	}

	private void setDate(DateTime dateTime, Date date) {
		if (date == null) {
			dateTime.setDate(0, 0, 0);
		} else {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			dateTime.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));
		}
	}

}
