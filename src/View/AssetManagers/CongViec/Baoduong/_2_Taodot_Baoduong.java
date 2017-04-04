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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.Hinhthuc_Baoduong;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.Row_PTTSthamgia;
import DAO.TAISAN;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

public class _2_Taodot_Baoduong extends Shell {
	private static NGUOIDUNG user;
	private final Controler controler;
	private static Shell ParentShell;
	private static DE_XUAT dx;
	private static int MODE_NEW_VIEW;// 2 = xem, sửa thông tin
	private Text text_Mota;
	private Text text_Tendot_Baoduong;
	private Combo combo_Loaiphuongtien;
	private Tree tree_PTTS;
	private Button btnDaudongco;
	private Button btnLocgio;
	private Button btnDauphanh_lyhop;
	private Button btnDauvisai;
	private Button btnDautroluclai;
	private Button btnLocdaudongco;
	private Button btnLocnhienlieu;
	private Button btnDauhopso;
	private Button btnLocgiogianlanh;
	private Button btnBaoduongkhac;
	private Button btnTroLai;
	private Button btnDong;
	private DOT_THUCHIEN_SUACHUA_BAODUONG VIEW_MODE_dsb;
	private Text text_TenNguonsuachua_baoduong;
	private Text text_Gioithieu_NguonSuachuaBaoduong;
	private Text text_Lienhe_NguonSuachuaBaoduong;
	protected NGUONSUACHUA_BAODUONG nsb;
	private Button btnNgunSaCha;
	private Button btnThm;
	private Button btnXa;
	private Button btnLuu;
	private Text Phongban;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(_2_Taodot_Baoduong.class);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			_2_Taodot_Baoduong shell = new _2_Taodot_Baoduong(display, user, ParentShell, dx, MODE_NEW_VIEW);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @param dx
	 * @param shlChnNgiL
	 * @param user
	 * @param i
	 * @throws SQLException
	 */

	public _2_Taodot_Baoduong(Display display, NGUOIDUNG user, Shell ParentShell, DE_XUAT dx, int MODE_NEW_VIEW)
			throws SQLException {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(_2_Taodot_Baoduong.class, "/maintenance-icon (1).png"));
		_2_Taodot_Baoduong.user = user;
		controler = new Controler(user);
		_2_Taodot_Baoduong.ParentShell = ParentShell;
		_2_Taodot_Baoduong.dx = dx;
		_2_Taodot_Baoduong.MODE_NEW_VIEW = MODE_NEW_VIEW;
		setLayout(new GridLayout(7, false));

		Fill_ItemData fi = new Fill_ItemData();

		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 7, 1));

		SashForm sashForm_2 = new SashForm(sashForm, SWT.NONE);
		Composite composite = new Composite(sashForm_2, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("Tên đợt Bảo dưỡng*:");

		text_Tendot_Baoduong = new Text(composite, SWT.BORDER);
		text_Tendot_Baoduong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Đơn vị:");

		Phongban = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		Phongban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Phongban.setText(controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN()).getTEN_PHONGBAN());

		Label label_3 = new Label(composite, SWT.NONE);
		GridData gd_label_3 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_3.verticalIndent = 3;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("Loại phương tiện:");

		combo_Loaiphuongtien = new Combo(composite, SWT.READ_ONLY);
		combo_Loaiphuongtien.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (VIEW_MODE_dsb != null) {
					Fill_ItemData f = new Fill_ItemData();
					if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Xemay()) {
						VIEW_MODE_dsb.setLOAI_PHUONG_TIEN(f.getInt_Xemay());
						tree_PTTS.removeAll();
					} else if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Oto()) {
						VIEW_MODE_dsb.setLOAI_PHUONG_TIEN(f.getInt_Oto());
						tree_PTTS.removeAll();
					}
				}
			}
		});
		combo_Loaiphuongtien.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		fi.setComboBox_LOAIPHUONGTIEN_Phuongtien_Giaothong(combo_Loaiphuongtien, 0);

		Label label_4 = new Label(composite, SWT.NONE);
		GridData gd_label_4 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_4.verticalIndent = 3;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("Mô tả:");

		text_Mota = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_Mota.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));

		Group grpNgunBoDng = new Group(sashForm_2, SWT.NONE);
		grpNgunBoDng.setText("Đơn vị Bảo dưỡng");
		grpNgunBoDng.setLayout(new GridLayout(2, false));

		Label lblTnnV = new Label(grpNgunBoDng, SWT.NONE);
		lblTnnV.setText("Tên đơn vị:");

		text_TenNguonsuachua_baoduong = new Text(grpNgunBoDng, SWT.BORDER);
		text_TenNguonsuachua_baoduong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblGiiThiu = new Label(grpNgunBoDng, SWT.NONE);
		GridData gd_lblGiiThiu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblGiiThiu.verticalIndent = 3;
		lblGiiThiu.setLayoutData(gd_lblGiiThiu);
		lblGiiThiu.setText("Giới thiệu:");

		text_Gioithieu_NguonSuachuaBaoduong = new Text(grpNgunBoDng, SWT.BORDER);
		text_Gioithieu_NguonSuachuaBaoduong.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblLinH = new Label(grpNgunBoDng, SWT.NONE);
		GridData gd_lblLinH = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblLinH.verticalIndent = 3;
		lblLinH.setLayoutData(gd_lblLinH);
		lblLinH.setText("Liên hệ:");

		text_Lienhe_NguonSuachuaBaoduong = new Text(grpNgunBoDng, SWT.BORDER);
		text_Lienhe_NguonSuachuaBaoduong.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm_2.setWeights(new int[] { 1000, 618 });

		SashForm sashForm_1 = new SashForm(sashForm, SWT.NONE);
		tree_PTTS = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_PTTS.setLinesVisible(true);
		tree_PTTS.setHeaderVisible(true);
		tree_PTTS.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				TreeItem[] til = tree_PTTS.getSelection();
				if (til.length > 0) {
					Row_PTTSthamgia pttg = (Row_PTTSthamgia) til[0].getData();
					setHinhthuc_Baoduong(pttg.getHtbd());
				}
			}
		});
		tree_PTTS.addListener(SWT.SetData, new Listener() {
			public void handleEvent(Event event) {
				if (tree_PTTS.getItems().length > 0) {
					combo_Loaiphuongtien.setEnabled(false);
				} else {
					combo_Loaiphuongtien.setEnabled(true);
				}
			}
		});
		TreeColumn trclmnStt = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnStt.setWidth(50);
		trclmnStt.setText("Stt");

		TreeColumn trclmnTnMT = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnTnMT.setWidth(100);
		trclmnTnMT.setText("Tên, mô tả");

		TreeColumn trclmnHngSnXut = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnHngSnXut.setWidth(100);
		trclmnHngSnXut.setText("Hãng sản xuất");

		TreeColumn trclmnDngXe = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnDngXe.setWidth(100);
		trclmnDngXe.setText("Dòng xe");

		TreeColumn trclmnBinS = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnBinS.setWidth(100);
		trclmnBinS.setText("Biển số");

		TreeColumn trclmnNgySDng = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnNgySDng.setWidth(100);
		trclmnNgySDng.setText("Ngày sử dụng");

		TreeColumn trclmnMPhngTin = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnMPhngTin.setWidth(90);
		trclmnMPhngTin.setText("Mã PTTS");

		Menu menu = new Menu(tree_PTTS);
		tree_PTTS.setMenu(menu);

		MenuItem mntmThmPhngTin = new MenuItem(menu, SWT.NONE);
		mntmThmPhngTin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Them_PTGT();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		mntmThmPhngTin.setText("Thêm phương tiện tài sản");

		MenuItem mntmXoa = new MenuItem(menu, SWT.NONE);
		mntmXoa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					delete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

		});
		mntmXoa.setText("Xóa");

		TreeColumn trclmnThayNht = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayNht.setWidth(70);
		trclmnThayNht.setText("Thay nhớt");

		TreeColumn trclmnThayLcNht = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayLcNht.setWidth(100);
		trclmnThayLcNht.setText("Thay lọc nhớt");

		TreeColumn trclmnThayLcGi = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayLcGi.setWidth(100);
		trclmnThayLcGi.setText("Thay lọc gió");

		TreeColumn trclmnThayLcNhin = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayLcNhin.setWidth(100);
		trclmnThayLcNhin.setText("Thay lọc nhiên liệu");

		TreeColumn trclmnThayDuPhanh = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayDuPhanh.setWidth(100);
		trclmnThayDuPhanh.setText("Thay Dầu phanh - ly hợp");

		TreeColumn trclmnThayDuHp = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayDuHp.setWidth(100);
		trclmnThayDuHp.setText("Thay Dầu hộp số");

		TreeColumn trclmnThayDuVi = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayDuVi.setWidth(100);
		trclmnThayDuVi.setText("Thay Dầu vi sai");

		TreeColumn trclmnLcGiGin = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnLcGiGin.setWidth(100);
		trclmnLcGiGin.setText("Lọc gió giàn lạnh");

		TreeColumn trclmnThayDuTr = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnThayDuTr.setWidth(100);
		trclmnThayDuTr.setText("Thay dầu trợ lực lái");

		TreeColumn trclmnBoDngKhcs = new TreeColumn(tree_PTTS, SWT.NONE);
		trclmnBoDngKhcs.setWidth(100);
		trclmnBoDngKhcs.setText("Bảo dưỡng khác");

		Group grpHnhThcBo = new Group(sashForm_1, SWT.NONE);
		grpHnhThcBo.setLayout(new GridLayout(1, false));
		grpHnhThcBo.setText("Hình thức bảo dưỡng");

		btnDaudongco = new Button(grpHnhThcBo, SWT.CHECK);
		btnDaudongco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDaudongco.setText("Dầu động cơ");

		btnLocdaudongco = new Button(grpHnhThcBo, SWT.CHECK);
		btnLocdaudongco.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLocdaudongco.setText("Lọc dầu động cơ");

		btnLocgio = new Button(grpHnhThcBo, SWT.CHECK);
		btnLocgio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLocgio.setText("Lọc gió");

		btnLocnhienlieu = new Button(grpHnhThcBo, SWT.CHECK);
		btnLocnhienlieu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLocnhienlieu.setText("Lọc nhiên liệu");

		btnDauphanh_lyhop = new Button(grpHnhThcBo, SWT.CHECK);
		btnDauphanh_lyhop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDauphanh_lyhop.setText("Dầu phanh và dầu ly hợp");

		btnDauhopso = new Button(grpHnhThcBo, SWT.CHECK);
		btnDauhopso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDauhopso.setText("Dầu hộp số");

		btnDauvisai = new Button(grpHnhThcBo, SWT.CHECK);
		btnDauvisai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDauvisai.setText("Dầu vi sai");

		btnLocgiogianlanh = new Button(grpHnhThcBo, SWT.CHECK);
		btnLocgiogianlanh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnLocgiogianlanh.setText("Lọc gió giàn lạnh");

		btnDautroluclai = new Button(grpHnhThcBo, SWT.CHECK);
		btnDautroluclai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnDautroluclai.setText("Dầu trợ lực lái");

		btnBaoduongkhac = new Button(grpHnhThcBo, SWT.CHECK);
		btnBaoduongkhac.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					updateSelectedList();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnBaoduongkhac.setText("Bảo dưỡng khác");
		sashForm_1.setWeights(new int[] { 599, 182 });
		sashForm.setWeights(new int[] { 618, 1000 });
		new Label(this, SWT.NONE);

		btnTroLai = new Button(this, SWT.NONE);
		btnTroLai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParentShell.setVisible(true);
				dispose();
			}
		});
		GridData gd_btnTroLai = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnTroLai.widthHint = 75;
		btnTroLai.setLayoutData(gd_btnTroLai);
		btnTroLai.setText("Trở lại <<");

		btnNgunSaCha = new Button(this, SWT.NONE);
		btnNgunSaCha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonSuachua_Baoduong cnsb = new ChonNguonSuachua_Baoduong(getShell(), SWT.DIALOG_TRIM, user);
					cnsb.open();
					nsb = cnsb.getResult();
					fillNguonSuachuaBaoduong(nsb);
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNgunSaCha.setText("Nguồn sửa chữa - Bảo dưỡng");
		btnNgunSaCha.setImage(SWTResourceManager.getImage(_2_Taodot_Baoduong.class, "/phone-icon.png"));

		btnThm = new Button(this, SWT.NONE);
		btnThm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Them_PTGT();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnThm = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnThm.widthHint = 75;
		btnThm.setLayoutData(gd_btnThm);
		btnThm.setText("Thêm");
		btnThm.setImage(SWTResourceManager.getImage(_2_Taodot_Baoduong.class, "/add-1-icon (1).png"));

		btnXa = new Button(this, SWT.NONE);
		btnXa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					delete();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		GridData gd_btnXa = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnXa.widthHint = 75;
		btnXa.setLayoutData(gd_btnXa);
		btnXa.setText("Xóa");
		btnXa.setImage(SWTResourceManager.getImage(_2_Taodot_Baoduong.class, "/delete-1-icon (1).png"));

		btnLuu = new Button(this, SWT.NONE);
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (MODE_NEW_VIEW == 1) {
						try {
							TaoMoi_DotSuachua_Baoduong();
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					} else {
						updateField();
					}
					GiaoViec.FillTableSuachua();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void TaoMoi_DotSuachua_Baoduong() throws SQLException {
				if (checkTextNotNULL()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = getDOT_SUACHUA_BAODUONG();
					if (nsb == null)
						nsb = getNGUONSUACHUABAODUONG();
					if (nsb != null)
						if (nsb.getMA_NGUONSUACHUA_BAODUONG() >= 0)
							dsb.setMA_NGUONSUACHUA_BAODUONG(nsb.getMA_NGUONSUACHUA_BAODUONG());
					int i = controler.getControl_DEXUAT().insert_DEXUAT(dx);
					if (i >= 0) {
						QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
						qdt.setMA_DE_XUAT(dx.getMA_DE_XUAT());
						qdt.setTEN_QUA_TRINH("Đề xuất - thực hiện theo công văn: " + dx.getSODEXUAT());
						int i2 = controler.getControl_QUATRINH_DEXUAT_THUCHIEN().insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
						if (i2 >= 0) {
							dsb.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
							qdt.setMA_QUATRINH_DEXUAT_THUCHIEN(i2);
							int ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
									.InsertDOT_THUCHIEN_SUACHUA_BAODUONG(dsb, qdt, null);
							if (ict >= 0) {
								TreeItem[] til = tree_PTTS.getItems();
								if (til.length > 0) {
									for (TreeItem ti : til) {
										dsb.setMA_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
										Row_PTTSthamgia rp = (Row_PTTSthamgia) ti.getData();
										controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
												.set_DOT_THUCHIEN_SUACHUA_TAISAN(dsb, rp);
									}
								}
								showMessage_Succes();
								ParentShell.dispose();
								dispose();
								GiaoViec gv = new GiaoViec(user);
								gv.open();
							} else {
								showMessage_Fail();
							}
						} else {
							showMessage_Fail();
						}
					} else {
						showMessage_Fail();
					}
				} else {
					showMessage_FillText();
				}
			}

			private DOT_THUCHIEN_SUACHUA_BAODUONG getDOT_SUACHUA_BAODUONG() {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = new DOT_THUCHIEN_SUACHUA_BAODUONG();
				dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Baoduong.getText());
				dsb.setLOAI_PHUONG_TIEN(
						Integer.valueOf((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText())));
				dsb.setSUACHUA_BAODUONG(1);
				dsb.setMO_TA(text_Mota.getText());
				return dsb;
			}

			private NGUONSUACHUA_BAODUONG getNGUONSUACHUABAODUONG() throws SQLException {
				NGUONSUACHUA_BAODUONG nsbd = null;
				if (!text_TenNguonsuachua_baoduong.getText().equals("")
						&& !text_Gioithieu_NguonSuachuaBaoduong.getText().equals("")) {
					nsbd = new NGUONSUACHUA_BAODUONG();
					nsbd.setTEN_NGUONSUACHUA_BAODUONG(text_TenNguonsuachua_baoduong.getText());
					nsbd.setGIOI_THIEU(text_Gioithieu_NguonSuachuaBaoduong.getText());
					nsbd.setLIEN_HE(text_Lienhe_NguonSuachuaBaoduong.getText());
					int key = controler.getControl_NGUONSUACHUA_BAODUONG().Insert_NGUONSUACHUA_BAODUONG(nsbd);
					if (key > 0) {
						nsbd.setMA_NGUONSUACHUA_BAODUONG(key);
					}
				}
				return nsbd;
			}
		});
		GridData gd_btnLu = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLuu.setLayoutData(gd_btnLu);
		btnLuu.setText("Xong");

		btnDong = new Button(this, SWT.NONE);
		btnDong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (_2_Taodot_Baoduong.MODE_NEW_VIEW == 2) {
						updateField();
						GiaoViec.FillTableSuachua();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				dispose();
			}
		});
		GridData gd_btnDong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDong.widthHint = 75;
		btnDong.setLayoutData(gd_btnDong);
		btnDong.setText("Đóng");
		createContents();
		init_loadMODE();
	}

	protected void fillNguonSuachuaBaoduong(NGUONSUACHUA_BAODUONG nsb2) {
		if (nsb2 != null) {
			text_TenNguonsuachua_baoduong.setText(nsb2.getTEN_NGUONSUACHUA_BAODUONG());
			text_Gioithieu_NguonSuachuaBaoduong.setText(nsb2.getGIOI_THIEU());
			text_Lienhe_NguonSuachuaBaoduong.setText(nsb2.getLIEN_HE());
		}
	}

	protected void Them_PTGT() throws SQLException {
		_3_Nhapdanhsach nds = new _3_Nhapdanhsach(getShell(), SWT.DIALOG_TRIM, user, getTreeData(), dx,
				(int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()));
		nds.open();
		ArrayList<PHUONGTIEN_GIAOTHONG> tmp = new ArrayList<>();
		if (getTreeData() != null)
			tmp.addAll(getTreeData());
		if (nds.getResult_danhsachPTTS() != null) {
			tmp.addAll(nds.getResult_danhsachPTTS());
		}
		fillTable_ROW(danhsachPTGT_To_Row_PTTSthamgia(tmp));
	}

	protected void delete() throws SQLException {
		TreeItem[] til = tree_PTTS.getSelection();
		if (til.length > 0) {
			removeTreeItem(til);
		}
		fillTable_ROW(danhsachPTGT_To_Row_PTTSthamgia(getTreeData()));
	}

	private void removeTreeItem(TreeItem[] til) {
		for (TreeItem item : til) {
			item.dispose();
		}
	}

	protected ArrayList<PHUONGTIEN_GIAOTHONG> getTreeData() {
		ArrayList<PHUONGTIEN_GIAOTHONG> result = new ArrayList<>();
		TreeItem[] til = tree_PTTS.getItems();
		for (TreeItem ti : til) {
			Row_PTTSthamgia r = (Row_PTTSthamgia) ti.getData();
			result.add(r.getPtgt());
		}
		return result;
	}

	protected void updateField() throws SQLException {
		if (checkTextNotNULL()) {
			VIEW_MODE_dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Baoduong.getText());
			VIEW_MODE_dsb.setLOAI_PHUONG_TIEN(
					Integer.valueOf((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText())));
			VIEW_MODE_dsb.setSUACHUA_BAODUONG(1);
			VIEW_MODE_dsb.setMO_TA(text_Mota.getText());
			if (nsb != null)
				VIEW_MODE_dsb.setMA_NGUONSUACHUA_BAODUONG(nsb.getMA_NGUONSUACHUA_BAODUONG());
			boolean flg = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.update_DOT_THUCHIEN_SUACHUA_BAODUONG(VIEW_MODE_dsb);
			if (flg) {
				controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().remove_All(VIEW_MODE_dsb);
				TreeItem[] til = tree_PTTS.getItems();
				if (til.length > 0) {
					for (TreeItem ti : til) {
						Row_PTTSthamgia rp = (Row_PTTSthamgia) ti.getData();
						controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
								.set_DOT_THUCHIEN_SUACHUA_TAISAN(VIEW_MODE_dsb, rp);
					}
				}
			} else {
				showMessage_Fail();
			}
		} else {
			showMessage_FillText();
		}
	}

	private void init_loadMODE() throws SQLException {
		setupMODE_Layout(MODE_NEW_VIEW);
		if (MODE_NEW_VIEW == 2 && dx != null) {
			VIEW_MODE_dsb = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG().get_DOT_THUCHIEN_SUACHUA_BAODUONG(dx);
			if (VIEW_MODE_dsb != null) {
				text_Tendot_Baoduong.setText(VIEW_MODE_dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
				if (VIEW_MODE_dsb.getLOAI_PHUONG_TIEN() == 1/* ô tô */)
					combo_Loaiphuongtien.select(0);
				if (VIEW_MODE_dsb.getLOAI_PHUONG_TIEN() == 2/* xe máy */)
					combo_Loaiphuongtien.select(1);
				text_Mota.setText(VIEW_MODE_dsb.getMO_TA());
				ArrayList<Row_PTTSthamgia> rpl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
						.getPTTS_BAODUONG(VIEW_MODE_dsb);
				fillTable_ROW(rpl);
				nsb = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(VIEW_MODE_dsb);
				fillNguonSuachuaBaoduong(nsb);
			}
		}
	}

	private void setupMODE_Layout(int mODE) {
		if (MODE_NEW_VIEW == 2) {
			btnTroLai.setVisible(false);
			this.setText("Đợt thực hiện bảo dưỡng phương tiện tài sản");
			btnDong.setText("Hoàn tất");
			btnLuu.setImage(
					SWTResourceManager.getImage(_2_Taodot_Baoduong.class, "/Actions-document-save-icon (1).png"));
		} else {
			combo_Loaiphuongtien.select(0);
			btnLuu.setText("Tiếp >>");
		}
	}

	private void fillTable_ROW(ArrayList<Row_PTTSthamgia> rpl) throws SQLException {
		tree_PTTS.removeAll();
		int i = 1;
		for (Row_PTTSthamgia p : rpl) {
			TreeItem ti = new TreeItem(tree_PTTS, SWT.NONE);
			Fill_ItemData f = new Fill_ItemData();
			setText(ti, p, i, f);
			i++;
		}
		treePack(tree_PTTS);
	}

	void setText(TreeItem ti, Row_PTTSthamgia p, int i, Fill_ItemData f) throws SQLException {
		LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(p.getPtgt().getMA_LOAI_XE());
		ti.setText(new String[] { "" + i, p.getTEN_TAI_SAN(), lx.getHANG_SAN_XUAT(), lx.getTEN_DONG_XE(),
				p.getPtgt().getBIENSO(), p.getNGAY_SU_DUNG(), p.getMA_TAI_SAN(),
				f.getInt_ThayNhot(p.getHtbd().isThayNhot()).equals("1") ? "1" : "",
				f.getInt_ThayLocNhot(p.getHtbd().isThayLocNhot()).equals("1") ? "1" : "",
				f.getInt_ThayLocgio(p.getHtbd().isThayLocgio()).equals("1") ? "1" : "",
				f.getInt_ThayLocnhienlieu(p.getHtbd().isThayLocnhienlieu()).equals("1") ? "1" : "",
				f.getInt_ThayDauphanh_Daulyhop(p.getHtbd().isThayDauphanh_Daulyhop()).equals("1") ? "1" : "",
				f.getInt_ThayDauhopso(p.getHtbd().isThayDauhopso()).equals("1") ? "1" : "",
				f.getInt_ThayDauVisai(p.getHtbd().isThayDauVisai()).equals("1") ? "1" : "",
				f.getInt_ThayLocgioGianlanh(p.getHtbd().isThayLocgioGianlanh()).equals("1") ? "1" : "",
				f.getInt_ThayDautroluclai(p.getHtbd().isThayDautroluclai()).equals("1") ? "1" : "",
				f.getInt_Baoduongkhac(p.getHtbd().isBaoduongkhac()).equals("1") ? "1" : "" });
		ti.setData(p);
	}

	Hinhthuc_Baoduong getHinhthuc_Baoduong() {
		Hinhthuc_Baoduong htbd = new Hinhthuc_Baoduong();
		htbd.setThayNhot(btnDaudongco.getSelection());
		htbd.setThayLocNhot(btnLocdaudongco.getSelection());
		htbd.setThayLocgio(btnLocgio.getSelection());
		htbd.setThayLocnhienlieu(btnLocnhienlieu.getSelection());
		htbd.setThayDauphanh_Daulyhop(btnDauphanh_lyhop.getSelection());
		htbd.setThayDauhopso(btnDauhopso.getSelection());
		htbd.setThayDauVisai(btnDauvisai.getSelection());
		htbd.setThayLocgioGianlanh(btnLocgiogianlanh.getSelection());
		htbd.setThayDautroluclai(btnDautroluclai.getSelection());
		htbd.setBaoduongkhac(btnBaoduongkhac.getSelection());
		return htbd;
	}

	void setHinhthuc_Baoduong(Hinhthuc_Baoduong htbd) {
		btnDaudongco.setSelection(htbd.isThayNhot());
		btnLocdaudongco.setSelection(htbd.isThayLocNhot());
		btnLocgio.setSelection(htbd.isThayLocgio());
		btnLocnhienlieu.setSelection(htbd.isThayLocnhienlieu());
		btnDauphanh_lyhop.setSelection(htbd.isThayDauphanh_Daulyhop());
		btnDauhopso.setSelection(htbd.isThayDauhopso());
		btnDauvisai.setSelection(htbd.isThayDauVisai());
		btnLocgiogianlanh.setSelection(htbd.isThayLocgioGianlanh());
		btnDautroluclai.setSelection(htbd.isThayDautroluclai());
		btnBaoduongkhac.setSelection(htbd.isBaoduongkhac());
	}

	protected void updateSelectedList() throws SQLException {
		TreeItem[] til = tree_PTTS.getSelection();
		if (til.length > 0) {
			int i = 1;
			Fill_ItemData f = new Fill_ItemData();
			for (TreeItem ti : til) {
				Row_PTTSthamgia p = (Row_PTTSthamgia) ti.getData();
				p.setHtbd(getHinhthuc_Baoduong());
				setText(ti, p, i, f);
				i++;
			}
		}

	}

	private ArrayList<Row_PTTSthamgia> danhsachPTGT_To_Row_PTTSthamgia(ArrayList<PHUONGTIEN_GIAOTHONG> danhsachPTGT)
			throws SQLException {
		if (danhsachPTGT == null)
			return null;
		ArrayList<Row_PTTSthamgia> rowTree = new ArrayList<>();
		ArrayList<TAISAN> t = new ArrayList<>();
		for (PHUONGTIEN_GIAOTHONG p : danhsachPTGT) {
			t.add(controler.getControl_TAISAN().get_Taisan(p));
		}
		for (TAISAN p : t) {
			Row_PTTSthamgia r = new Row_PTTSthamgia();
			r.setTEN_TAI_SAN(p.getTEN_TAISAN());
			r.setNGAY_SU_DUNG(mdf.getViewStringDate(p.getNGAY_SU_DUNG()));
			r.setMA_TAI_SAN(String.valueOf(p.getMA_TAISAN()));
			r.setPtgt(p.getPhuongtien_Giaothong());
			r.setHtbd(getHinhthuc_Baoduong());
			rowTree.add(r);
		}
		return rowTree;
	}

	void treePack(Tree tree) {
		for (TreeColumn t : tree.getColumns()) {
			t.pack();
		}
	}

	protected void showMessage_Fail() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Thất bại");
		m.setMessage("Tạo công việc thất bại");
		m.open();
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Hoàn tất");
		if (MODE_NEW_VIEW == 2) {
			m.setMessage("Sửa thông tin công việc hoàn tất");
		} else {
			m.setMessage("Tạo công việc hoàn tất");
		}
		m.open();
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(getShell());
		m.setText("Lỗi");
		m.setMessage("Tên đợt tăng, Môt tả không để trống!");
		m.open();
	}

	protected boolean checkTextNotNULL() {
		if (text_Tendot_Baoduong.getText().equals("")) {
			return false;
		}
		if (text_Mota.getText().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Tạo Công việc (Đợt Bảo dưỡng)");
		setSize(777, 480);
		new FormTemplate().setCenterScreen(getShell());
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
