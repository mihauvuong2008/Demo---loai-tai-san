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
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.Row_PTTSthamgia_Baoduong;
import DAO.TAISAN;
import DAO.TAP_HO_SO;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.Hoso.TapHoso_View;
import View.AssetManagers.NguonSuachua_Baoduong.ChonNguonSuachua_Baoduong;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

public class Taodot_Baoduong extends Dialog {
	private static NGUOIDUNG user;
	private final Controler controler;
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
	private Button btnDong;
	private DOT_THUCHIEN_SUACHUA_BAODUONG ViewAndEdit_MODE_dsb;
	protected NGUONSUACHUA_BAODUONG nsb;
	private Button btnNgunSaCha;
	private Button btnLuu;
	private final MyDateFormat mdf = new MyDateFormat();
	private static Log log = LogFactory.getLog(Taodot_Baoduong.class);
	private Text text_Sodexuat;
	private Text text_NgaythangVanban;
	private Text text_Donvi;
	private Text text_Trichyeu;
	private Text text_Ngaytiepnhan;
	private Text text_Ngaygiao;
	private Shell shell;
	private Object result;
	private ArrayList<TAISAN> dataCreate;
	private Button btnThemDexuat;
	protected DE_XUAT Insert_dx;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @wbp.parser.constructor
	 */
	public Taodot_Baoduong(Shell parent, int Style, NGUOIDUNG user, ArrayList<TAISAN> dataCreate) throws SQLException {
		super(parent, Style);
		setText("SWT Dialog");
		Taodot_Baoduong.user = user;
		controler = new Controler(user);
		this.dataCreate = dataCreate;
	}

	public Taodot_Baoduong(Shell parent, int Style, NGUOIDUNG user2, DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		super(parent, Style);
		setText("SWT Dialog");
		Taodot_Baoduong.user = user2;
		controler = new Controler(user);
		ViewAndEdit_MODE_dsb = dsb;
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM);
		shell.setImage(SWTResourceManager.getImage(Taodot_Baoduong.class, "/maintenance-icon (1).png"));
		shell.setLayout(new GridLayout(2, false));
		setText("Tạo Công việc (Đợt Bảo dưỡng)");
		shell.setSize(777, 480);
		new FormTemplate().setCenterScreen(shell);

		Fill_ItemData fi = new Fill_ItemData();

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		SashForm sashForm_3 = new SashForm(sashForm, SWT.VERTICAL);

		SashForm sashForm_2 = new SashForm(sashForm_3, SWT.NONE);
		Composite composite = new Composite(sashForm_2, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("Tên đợt Bảo dưỡng*:");

		text_Tendot_Baoduong = new Text(composite, SWT.BORDER);
		text_Tendot_Baoduong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label label_3 = new Label(composite, SWT.NONE);
		GridData gd_label_3 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_label_3.verticalIndent = 3;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("Loại phương tiện:");

		combo_Loaiphuongtien = new Combo(composite, SWT.READ_ONLY);
		combo_Loaiphuongtien.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ViewAndEdit_MODE_dsb != null) {
					Fill_ItemData f = new Fill_ItemData();
					if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Xemay()) {
						ViewAndEdit_MODE_dsb.setLOAI_PHUONG_TIEN(f.getInt_Xemay());
						tree_PTTS.removeAll();
					} else if ((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()) == f.getInt_Oto()) {
						ViewAndEdit_MODE_dsb.setLOAI_PHUONG_TIEN(f.getInt_Oto());
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
		new Label(composite, SWT.NONE);

		btnNgunSaCha = new Button(composite, SWT.NONE);
		GridData gd_btnNgunSaCha = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnNgunSaCha.widthHint = 85;
		btnNgunSaCha.setLayoutData(gd_btnNgunSaCha);
		btnNgunSaCha.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ChonNguonSuachua_Baoduong cnsb = new ChonNguonSuachua_Baoduong(shell, SWT.DIALOG_TRIM, user);
					cnsb.open();
					nsb = cnsb.getResult();
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNgunSaCha.setText("Liên hệ");
		btnNgunSaCha.setImage(SWTResourceManager.getImage(Taodot_Baoduong.class, "/phone-icon.png"));

		Composite composite_1 = new Composite(sashForm_2, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));

		Label lblSXut = new Label(composite_1, SWT.NONE);
		lblSXut.setText("Số đề xuất: ");

		text_Sodexuat = new Text(composite_1, SWT.NONE);
		text_Sodexuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyThng = new Label(composite_1, SWT.NONE);
		lblNgyThng.setText("Ngày tháng: ");

		text_NgaythangVanban = new Text(composite_1, SWT.NONE);
		text_NgaythangVanban.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblnV = new Label(composite_1, SWT.NONE);
		lblnV.setText("Đơn vị: ");

		text_Donvi = new Text(composite_1, SWT.NONE);
		text_Donvi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyXL = new Label(composite_1, SWT.NONE);
		lblNgyXL.setText("Ngày xử lý:");

		text_Ngaytiepnhan = new Text(composite_1, SWT.NONE);
		text_Ngaytiepnhan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNgyGiao = new Label(composite_1, SWT.NONE);
		lblNgyGiao.setText("Ngày giao:");

		text_Ngaygiao = new Text(composite_1, SWT.NONE);
		text_Ngaygiao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTrchYu = new Label(composite_1, SWT.NONE);
		GridData gd_lblTrchYu = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblTrchYu.verticalIndent = 3;
		lblTrchYu.setLayoutData(gd_lblTrchYu);
		lblTrchYu.setText("Ghi chú: ");

		text_Trichyeu = new Text(composite_1, SWT.NONE);
		text_Trichyeu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		btnThemDexuat = new Button(composite_1, SWT.NONE);
		btnThemDexuat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Insert_dx = null;
					if (ViewAndEdit_MODE_dsb != null
							&& ViewAndEdit_MODE_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() > 0) {
						Insert_dx = controler.getControl_DEXUAT().get_DEXUAT(ViewAndEdit_MODE_dsb);
					}
					if (Insert_dx != null) {
						TAP_HO_SO ths = controler.getControl_TAPHOSO().get_TAP_HO_SO(Insert_dx.getMA_TAPHOSO());
						TapHoso_View thsv = new TapHoso_View(shell, SWT.DIALOG_TRIM, user, ths, false);
						thsv.open();
					} else {
						NhapDeXuat ndx = new NhapDeXuat(shell, SWT.DIALOG_TRIM, user);
						ndx.open();
						Insert_dx = ndx.result;
						if (Insert_dx == null)
							return;
						if (ViewAndEdit_MODE_dsb == null)
							return;
						if (ViewAndEdit_MODE_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() <= 0)
							return;
						int Ma_Quatrinh_Dexuat_thuchien = getMaQuantrinhDexuatThuchien(Insert_dx);
						if (Ma_Quatrinh_Dexuat_thuchien <= 0)
							return;
						boolean ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
								.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_DEXUAT_THUCHIEN(
										ViewAndEdit_MODE_dsb, Ma_Quatrinh_Dexuat_thuchien);
						if (ict) {
							MessageBox m = new MessageBox(shell, SWT.ICON_WORKING);
							m.setText("Hoàn tất");
							m.setMessage("Thêm Đề xuất Hoàn tất");
							m.open();
						}
					}
				} catch (NullPointerException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThemDexuat.setImage(SWTResourceManager.getImage(Taodot_Baoduong.class, "/Mimes-ooo-writer-icon.png"));
		btnThemDexuat.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		btnThemDexuat.setText("Thêm Hồ sơ");
		sashForm_2.setWeights(new int[] { 1000, 618 });

		SashForm sashForm_1 = new SashForm(sashForm_3, SWT.NONE);
		tree_PTTS = new Tree(sashForm_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tree_PTTS.setLinesVisible(true);
		tree_PTTS.setHeaderVisible(true);
		tree_PTTS.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				TreeItem[] til = tree_PTTS.getSelection();
				if (til.length > 0) {
					Row_PTTSthamgia_Baoduong pttg = (Row_PTTSthamgia_Baoduong) til[0].getData();
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

		Composite grpHnhThcBo = new Composite(sashForm_1, SWT.NONE);
		grpHnhThcBo.setLayout(new GridLayout(1, false));

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
		sashForm_1.setWeights(new int[] { 585, 173 });
		sashForm_3.setWeights(new int[] { 180, 227 });
		sashForm.setWeights(new int[] { 501 });

		btnLuu = new Button(shell, SWT.NONE);
		btnLuu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (dataCreate != null) {
						try {
							TaoMoi_DotSuachua_Baoduong();
						} catch (SQLException e1) {
							log.error(e1.getMessage());
							e1.printStackTrace();
						}
					} else {
						updateField();
						GiaoViec.FillTableSuachua();
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
			}

			private void TaoMoi_DotSuachua_Baoduong() throws SQLException {
				if (checkTextNotNULL()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = getDOT_SUACHUA_BAODUONG();
					int ict = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
							.InsertDOT_THUCHIEN_SUACHUA_BAODUONG(dsb, null, null);
					if (ict >= 0) {
						dsb.setMA_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
						int Ma_Quatrinh_Dexuat_thuchien = getMaQuantrinhDexuatThuchien(Insert_dx);
						if (Ma_Quatrinh_Dexuat_thuchien > 0)
							controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
									.update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_DEXUAT_THUCHIEN(dsb,
											Ma_Quatrinh_Dexuat_thuchien);
						TreeItem[] til = tree_PTTS.getItems();
						if (til.length > 0) {
							for (TreeItem ti : til) {
								dsb.setMA_DOT_THUCHIEN_SUACHUA_BAODUONG(ict);
								Row_PTTSthamgia_Baoduong rp = (Row_PTTSthamgia_Baoduong) ti.getData();
								controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
										.set_DOT_THUCHIEN_SUACHUA_TAISAN(dsb, rp);
							}
						}
						showMessage_Succes();
						shell.dispose();
						GiaoViec gv = new GiaoViec(user);
						gv.open();
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

		});
		GridData gd_btnLu = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_btnLu.widthHint = 75;
		btnLuu.setLayoutData(gd_btnLu);
		btnLuu.setText("Xong");

		btnDong = new Button(shell, SWT.NONE);
		btnDong.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if (ViewAndEdit_MODE_dsb != null) {
						updateField();
						GiaoViec.FillTableSuachua();
					} else {
						// if (Insert_dx != null)
						// controler.getControl_DEXUAT().delete_DEXUAT(Insert_dx);
					}
				} catch (SQLException e1) {
					log.error(e1.getMessage());
					e1.printStackTrace();
				}
				shell.dispose();
			}
		});
		GridData gd_btnDong = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDong.widthHint = 75;
		btnDong.setLayoutData(gd_btnDong);
		btnDong.setText("Đóng");
		init_loadMODE();
		init_CreateMode();
	}

	protected int getMaQuantrinhDexuatThuchien(DE_XUAT Insert_dx) throws NullPointerException, SQLException {
		int key = controler.getControl_DEXUAT().insert_DEXUAT(Insert_dx);
		if (key <= 0)
			return -1;
		Insert_dx.setMA_DE_XUAT(key);
		QUATRINH_DEXUAT_THUCHIEN qdt = new QUATRINH_DEXUAT_THUCHIEN();
		qdt.setMA_DE_XUAT(key);
		qdt.setLOAI_CONGVIEC(new Fill_ItemData().getInt_LoaiCongviec_Suachua_Baoduong());
		return controler.getControl_QUATRINH_DEXUAT_THUCHIEN().insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
	}

	private void init_CreateMode() throws SQLException {
		if (dataCreate == null)
			return;
		ArrayList<PHUONGTIEN_GIAOTHONG> pgl = new ArrayList<>();
		for (TAISAN taisan : dataCreate) {
			pgl.add(taisan.getPhuongtien_Giaothong());
		}
		if (pgl.size() <= 0)
			return;
		int Oto_Xemay = pgl.get(0).getLOAI_PHUONGTIEN_GIAOTHONG();
		combo_Loaiphuongtien.setText(new Fill_ItemData().getStringLOAI_PHUONGTIEN_GIAOTHONG(Oto_Xemay));
		ArrayList<Row_PTTSthamgia_Baoduong> data = danhsachPTGT_To_Row_PTTSthamgia(pgl);
		fillTable_ROW(data);
	}

	protected void Them_PTGT() throws SQLException {
		NhapdanhsachTaisan nds = new NhapdanhsachTaisan(shell, SWT.DIALOG_TRIM, user, getTreeData(),
				ViewAndEdit_MODE_dsb, (int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText()));
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
			Row_PTTSthamgia_Baoduong r = (Row_PTTSthamgia_Baoduong) ti.getData();
			result.add(r.getPtgt());
		}
		return result;
	}

	protected void updateField() throws SQLException {
		if (checkTextNotNULL()) {
			ViewAndEdit_MODE_dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(text_Tendot_Baoduong.getText());
			ViewAndEdit_MODE_dsb.setLOAI_PHUONG_TIEN(
					Integer.valueOf((int) combo_Loaiphuongtien.getData(combo_Loaiphuongtien.getText())));
			ViewAndEdit_MODE_dsb.setSUACHUA_BAODUONG(1);
			ViewAndEdit_MODE_dsb.setMO_TA(text_Mota.getText());
			if (nsb != null)
				ViewAndEdit_MODE_dsb.setMA_NGUONSUACHUA_BAODUONG(nsb.getMA_NGUONSUACHUA_BAODUONG());
			boolean flg = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.update_DOT_THUCHIEN_SUACHUA_BAODUONG(ViewAndEdit_MODE_dsb);
			if (flg) {
				controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN().remove_All(ViewAndEdit_MODE_dsb);
				TreeItem[] til = tree_PTTS.getItems();
				if (til.length > 0) {
					for (TreeItem ti : til) {
						Row_PTTSthamgia_Baoduong rp = (Row_PTTSthamgia_Baoduong) ti.getData();
						controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
								.set_DOT_THUCHIEN_SUACHUA_TAISAN(ViewAndEdit_MODE_dsb, rp);
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
		setupMODE_Layout();
		if (ViewAndEdit_MODE_dsb == null)
			return;
		text_Tendot_Baoduong.setText(ViewAndEdit_MODE_dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG());
		if (ViewAndEdit_MODE_dsb.getLOAI_PHUONG_TIEN() == 1/* ô tô */)
			combo_Loaiphuongtien.select(0);
		if (ViewAndEdit_MODE_dsb.getLOAI_PHUONG_TIEN() == 2/* xe máy */)
			combo_Loaiphuongtien.select(1);
		text_Mota.setText(ViewAndEdit_MODE_dsb.getMO_TA());
		ArrayList<Row_PTTSthamgia_Baoduong> rpl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN()
				.getPTTS_BAODUONG(ViewAndEdit_MODE_dsb);
		fillTable_ROW(rpl);
		nsb = controler.getControl_NGUONSUACHUA_BAODUONG().get_NguonSuachua_Baoduong(ViewAndEdit_MODE_dsb);
		DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(ViewAndEdit_MODE_dsb);
		if (dx == null)
			return;
		fillDexuat(dx);
	}

	private void fillDexuat(DE_XUAT dx) throws SQLException {
		text_Sodexuat.setText(dx.getSODEXUAT());
		text_NgaythangVanban.setText(mdf.getViewStringDate(dx.getNGAYTHANG_VANBAN()));
		PHONGBAN pb = controler.getControl_PHONGBAN().get_PHONGBAN(dx.getMA_PHONGBAN());
		text_Donvi.setText(pb.getTEN_PHONGBAN());
		text_Ngaytiepnhan.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_BAT_DAU()));
		text_Ngaygiao.setText(mdf.getViewStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()));
		text_Trichyeu.setText(dx.getGHI_CHU());
	}

	private void setupMODE_Layout() throws SQLException {
		if (ViewAndEdit_MODE_dsb != null) {
			shell.setText("Đợt thực hiện bảo dưỡng phương tiện tài sản");
			btnDong.setText("Hoàn tất");
			btnLuu.setImage(SWTResourceManager.getImage(Taodot_Baoduong.class, "/Actions-document-save-icon (1).png"));
			DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(ViewAndEdit_MODE_dsb);
			if (dx != null)
				btnThemDexuat.setText("Xem Đề xuất");
		} else {
			shell.setText("Tạo mới Đợt thực hiện bảo dưỡng phương tiện tài sản");
			combo_Loaiphuongtien.select(0);
			btnLuu.setText("Xong");
		}
	}

	private void fillTable_ROW(ArrayList<Row_PTTSthamgia_Baoduong> rpl) throws SQLException {
		tree_PTTS.removeAll();
		int i = 1;
		for (Row_PTTSthamgia_Baoduong p : rpl) {
			TreeItem ti = new TreeItem(tree_PTTS, SWT.NONE);
			Fill_ItemData f = new Fill_ItemData();
			setText(ti, p, i, f);
			i++;
		}
		treePack(tree_PTTS);
	}

	void setText(TreeItem ti, Row_PTTSthamgia_Baoduong p, int i, Fill_ItemData f) throws SQLException {
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
				Row_PTTSthamgia_Baoduong p = (Row_PTTSthamgia_Baoduong) ti.getData();
				p.setHtbd(getHinhthuc_Baoduong());
				setText(ti, p, i, f);
				i++;
			}
		}

	}

	private ArrayList<Row_PTTSthamgia_Baoduong> danhsachPTGT_To_Row_PTTSthamgia(
			ArrayList<PHUONGTIEN_GIAOTHONG> danhsachPTGT) throws SQLException {
		if (danhsachPTGT == null)
			return null;
		ArrayList<Row_PTTSthamgia_Baoduong> rowTree = new ArrayList<>();
		ArrayList<TAISAN> t = new ArrayList<>();
		for (PHUONGTIEN_GIAOTHONG p : danhsachPTGT) {
			t.add(controler.getControl_TAISAN().get_Taisan(p));
		}
		for (TAISAN p : t) {
			Row_PTTSthamgia_Baoduong r = new Row_PTTSthamgia_Baoduong();
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
		MessageBox m = new MessageBox(shell);
		m.setText("Thất bại");
		m.setMessage("Tạo công việc thất bại");
		m.open();
	}

	protected void showMessage_Succes() {
		MessageBox m = new MessageBox(shell);
		m.setText("Hoàn tất");
		if (dataCreate == null) {
			m.setMessage("Sửa thông tin công việc hoàn tất");
		} else {
			m.setMessage("Tạo công việc hoàn tất");
		}
		m.open();
	}

	protected void showMessage_FillText() {
		MessageBox m = new MessageBox(shell);
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
