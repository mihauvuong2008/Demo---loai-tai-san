package View.AssetManagers.Taisan.XemTaiSan;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import Controler.Controler;
import DAO.LOAITAISAN_CAP_III;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;
import View.Template.FormTemplate;

import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class View_Taisan extends Dialog {

	protected Object result;
	protected Shell shlThngTinTi;
	private Text text_mataisan;
	private Text text_tentaisan;
	private Text text_model;
	private Text text_seri;
	private Text text_ngaysudung;
	private Text text_loaitaisan;
	private Text text_nhomtaisan;
	private Text text_soluong;
	private Text text_nguyengia;
	private Text text_xuatxu;
	private Text text_donvitinh;
	private Text text_baohanh;
	private Text text_tinhtrang;
	private Text text_trangthai;
	private Text text_mamorong;
	private Text text_donviquanly;
	private Text text_donvisudung;
	private Label lblModel;
	private Label lblSeri;
	private Label label;
	private Label lblLoiTiSn;
	private Label lblNhmTiSn;
	private Label lblSLng;
	private Label lblnVTnh;
	private Label lblXutX;
	private Label lblnVTnh_1;
	private Label lblTnhTrng;
	private Label label_1;
	private Label lblTnhTrng_1;
	private Label lblMMRng;
	private Label lblnVQun;
	private Label lblnVS;
	private Label label_2;
	private Label lblMPhngTin;
	private Text text_MaPTGT;
	private Label lblLoiPtgt;
	private Label lblHngSnXut;
	private Label lblDngXe;
	private Label lblBinS;
	private Label lblSKhung;
	private Label lblSMy;
	private Label lblSKmXe;
	private Label lblThiHnng;
	private Text text_loaiPTGT;
	private Text text_hangsanxuat;
	private Text text_dongxe;
	private Text text_bienso;
	private Text text_sokhung;
	private Text text_somay;
	private Text text_sokmxe;
	private Text text_thoihandangkiem;
	private Label lblGhiCh;
	private Text text_ghichu;
	private int mA_TAISAN;
	private Button btnng;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;
	private Label lblTnPtgt;
	private Text text_tenPTGT;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 * @param mA_TAISAN
	 */
	public View_Taisan(Shell parent, int style, NGUOIDUNG user, Integer mA_TAISAN) {
		super(parent, style);
		setText("SWT Dialog");
		this.mA_TAISAN = mA_TAISAN;
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
		shlThngTinTi.open();
		shlThngTinTi.layout();
		Display display = getParent().getDisplay();
		while (!shlThngTinTi.isDisposed()) {
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
		shlThngTinTi = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shlThngTinTi.setImage(
				SWTResourceManager.getImage(View_Taisan.class, "/org/eclipse/jface/dialogs/images/message_info.png"));
		shlThngTinTi.setSize(370, 637);
		new FormTemplate().setCenterScreen(shlThngTinTi);
		shlThngTinTi.setText("Th\u00F4ng tin t\u00E0i s\u1EA3n");
		shlThngTinTi.setLayout(new GridLayout(2, false));
		TAISAN t = controler.getControl_TAISAN().get_Taisan(mA_TAISAN);
		if (t != null) {
			Label lblMTiSn = new Label(shlThngTinTi, SWT.NONE);
			lblMTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblMTiSn.setText("M\u00E3 t\u00E0i s\u1EA3n:");

			text_mataisan = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_mataisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_mataisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_mataisan.setText(t.getMA_TAISAN() + "");

			Label lblTnTiSn = new Label(shlThngTinTi, SWT.NONE);
			lblTnTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblTnTiSn.setText("T\u00EAn t\u00E0i s\u1EA3n:");

			text_tentaisan = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_tentaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_tentaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_tentaisan.setText(t.getTEN_TAISAN());

			lblModel = new Label(shlThngTinTi, SWT.NONE);
			lblModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblModel.setText("Model:");

			text_model = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_model.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_model.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_model.setText(t.getMODEL().equals("null") ? "--" : t.getMODEL());

			lblSeri = new Label(shlThngTinTi, SWT.NONE);
			lblSeri.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblSeri.setText("Seri:");

			text_seri = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_seri.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_seri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_seri.setText(t.getSERI().equals("null") ? "--" : t.getSERI());

			label = new Label(shlThngTinTi, SWT.NONE);
			label.setText("Ng\u00E0y s\u1EED d\u1EE5ng:");
			label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_ngaysudung = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_ngaysudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_ngaysudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			String NgaysuDung = t.getNGAY_SU_DUNG() == null ? "--" : mdf.getViewStringDate(t.getNGAY_SU_DUNG());
			text_ngaysudung.setText(NgaysuDung);

			lblLoiTiSn = new Label(shlThngTinTi, SWT.NONE);
			lblLoiTiSn.setText("Lo\u1EA1i T\u00E0i s\u1EA3n:");
			lblLoiTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_loaitaisan = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_loaitaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_loaitaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			LOAITAISAN_CAP_III ltc3 = controler.getControl_LOAITAISAN_CAP_III()
					.get_LOAITAISAN_CAP_III(t.getMA_LOAITAISAN_CAP_III());
			text_loaitaisan.setText(ltc3.getTEN_LOAITAISAN_CAP_III());

			lblNhmTiSn = new Label(shlThngTinTi, SWT.NONE);
			lblNhmTiSn.setText("Nh\u00F3m T\u00E0i s\u1EA3n:");
			lblNhmTiSn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_nhomtaisan = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_nhomtaisan.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_nhomtaisan.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			NHOMTAISAN_CAP_V nc5 = controler.getControl_NHOMTAISAN_CAP_V()
					.getNHOMTAISAN_CAP_V(t.getMA_NHOMTAISAN_CAP_V());
			text_nhomtaisan.setText(nc5.getTEN_NHOMTAISAN_CAP_V());

			lblSLng = new Label(shlThngTinTi, SWT.NONE);
			lblSLng.setText("S\u1ED1 l\u01B0\u1EE3ng:");
			lblSLng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_soluong = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_soluong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_soluong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_soluong.setText(t.getSOLUONG() + "");

			lblnVTnh = new Label(shlThngTinTi, SWT.NONE);
			lblnVTnh.setText("Nguy\u00EAn gi\u00E1:");
			lblnVTnh.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_nguyengia = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_nguyengia.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_nguyengia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_nguyengia.setText(t.getNGUYEN_GIA() + "");

			lblXutX = new Label(shlThngTinTi, SWT.NONE);
			lblXutX.setText("Xu\u1EA5t x\u1EE9:");
			lblXutX.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_xuatxu = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_xuatxu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_xuatxu.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_xuatxu.setText(t.getXUAT_XU().equals("null") ? "--" : t.getXUAT_XU());

			lblnVTnh_1 = new Label(shlThngTinTi, SWT.NONE);
			lblnVTnh_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblnVTnh_1.setText("\u0110\u01A1n v\u1ECB t\u00EDnh:");

			text_donvitinh = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_donvitinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_donvitinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_donvitinh.setText(t.getDON_VI_TINH().equals("null") ? "--" : t.getDON_VI_TINH());

			lblTnhTrng = new Label(shlThngTinTi, SWT.NONE);
			lblTnhTrng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblTnhTrng.setText("B\u1EA3o h\u00E0nh:");

			text_baohanh = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_baohanh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_baohanh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_baohanh.setText(t.getBAO_HANH().equals("null") ? "--" : t.getBAO_HANH());

			lblTnhTrng_1 = new Label(shlThngTinTi, SWT.NONE);
			lblTnhTrng_1.setText("T\u00ECnh tr\u1EA1ng:");
			lblTnhTrng_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			Fill_ItemData f = new Fill_ItemData();
			text_tinhtrang = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_tinhtrang.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_tinhtrang.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_tinhtrang.setText(f.getStringOfTINHTRANG(t.getTINH_TRANG()));

			label_1 = new Label(shlThngTinTi, SWT.NONE);
			label_1.setText("Tr\u1EA1ng th\u00E1i:");
			label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_trangthai = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_trangthai.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_trangthai.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_trangthai.setText(f.getStringOfTRANGTHAI(t.getTRANG_THAI()));

			lblMMRng = new Label(shlThngTinTi, SWT.NONE);
			lblMMRng.setText("M\u00E3 m\u1EDF r\u1ED9ng:");
			lblMMRng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_mamorong = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_mamorong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_mamorong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			text_mamorong.setText(t.getMA_TANSAN_KETOAN() == null ? "--" : t.getMA_TANSAN_KETOAN());

			lblnVQun = new Label(shlThngTinTi, SWT.NONE);
			lblnVQun.setText("\u0110\u01A1n v\u1ECB qu\u1EA3n l\u00FD:");
			lblnVQun.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));

			text_donviquanly = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_donviquanly.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_donviquanly.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			PHONGBAN p = controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_QUAN_LY());
			text_donviquanly.setText(p.getTEN_PHONGBAN());

			lblnVS = new Label(shlThngTinTi, SWT.NONE);
			lblnVS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblnVS.setText("\u0110\u01A1n v\u1ECB s\u1EED d\u1EE5ng:");

			text_donvisudung = new Text(shlThngTinTi, SWT.READ_ONLY);
			text_donvisudung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_donvisudung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			PHONGBAN p2 = controler.getControl_PHONGBAN().get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
			text_donvisudung.setText(p2.getTEN_PHONGBAN());

			lblGhiCh = new Label(shlThngTinTi, SWT.NONE);
			lblGhiCh.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
			lblGhiCh.setText("Ghi ch\u00FA:");

			text_ghichu = new Text(shlThngTinTi, SWT.READ_ONLY | SWT.WRAP);
			text_ghichu.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
			text_ghichu.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			text_ghichu.setText(t.getGHI_CHU().equals("null") ? "--" : t.getGHI_CHU());

			label_2 = new Label(shlThngTinTi, SWT.SEPARATOR | SWT.HORIZONTAL);
			label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

			PHUONGTIEN_GIAOTHONG pg = controler.getControl_PHUONGTIEN_GIAOTHONG()
					.get_PHUONGTIEN_GIAOTHONG_FromTaisan(t.getMA_TAISAN());
			if (pg != null) {

				lblMPhngTin = new Label(shlThngTinTi, SWT.NONE);
				lblMPhngTin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblMPhngTin.setText("M\u00E3 PTGT:");

				text_MaPTGT = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_MaPTGT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_MaPTGT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_MaPTGT.setText(pg.getMA_PHUONGTIEN_GIAOTHONG());

				lblTnPtgt = new Label(shlThngTinTi, SWT.NONE);
				lblTnPtgt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblTnPtgt.setText("Tên PTGT:");

				text_tenPTGT = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_tenPTGT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_tenPTGT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_tenPTGT.setText(pg.getTEN_PHUONGTIEN_GIAOTHONG());

				lblLoiPtgt = new Label(shlThngTinTi, SWT.NONE);
				lblLoiPtgt.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblLoiPtgt.setText("Lo\u1EA1i PTGT:");

				text_loaiPTGT = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_loaiPTGT.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_loaiPTGT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_loaiPTGT.setText(f.getStringLOAI_PHUONGTIEN_GIAOTHONG(pg.getLOAI_PHUONGTIEN_GIAOTHONG()));
				LOAI_XE lx = controler.getControl_LOAI_XE().get_LOAI_XE(pg.getMA_LOAI_XE());
				lblHngSnXut = new Label(shlThngTinTi, SWT.NONE);
				lblHngSnXut.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblHngSnXut.setText("H\u00E3ng s\u1EA3n xu\u1EA5t:");

				text_hangsanxuat = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_hangsanxuat.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_hangsanxuat.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_hangsanxuat.setText(lx.getHANG_SAN_XUAT());

				lblDngXe = new Label(shlThngTinTi, SWT.NONE);
				lblDngXe.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblDngXe.setText("D\u00F2ng xe:");

				text_dongxe = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_dongxe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_dongxe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_dongxe.setText(lx.getTEN_DONG_XE());

				lblBinS = new Label(shlThngTinTi, SWT.NONE);
				lblBinS.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblBinS.setText("Bi\u1EC3n s\u1ED1:");

				text_bienso = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_bienso.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_bienso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_bienso.setText(pg.getBIENSO());

				lblSKhung = new Label(shlThngTinTi, SWT.NONE);
				lblSKhung.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblSKhung.setText("S\u1ED1 khung:");

				text_sokhung = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_sokhung.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_sokhung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_sokhung.setText(pg.getSOKHUNG());

				lblSMy = new Label(shlThngTinTi, SWT.NONE);
				lblSMy.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblSMy.setText("S\u1ED1 m\u00E1y:");

				text_somay = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_somay.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_somay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_somay.setText(pg.getSOMAY());

				lblSKmXe = new Label(shlThngTinTi, SWT.NONE);
				lblSKmXe.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblSKmXe.setText("S\u1ED1 Km xe:");

				text_sokmxe = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_sokmxe.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_sokmxe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				text_sokmxe.setText(pg.getSO_KM_XE() + "");

				lblThiHnng = new Label(shlThngTinTi, SWT.NONE);
				lblThiHnng.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
				lblThiHnng.setText("Th\u1EDDi h\u1EA1n \u0111\u0103ng ki\u1EC3m:");

				text_thoihandangkiem = new Text(shlThngTinTi, SWT.READ_ONLY);
				text_thoihandangkiem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
				text_thoihandangkiem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				String d = mdf.getViewStringDate(pg.getTHOIHAN_DANGKIEM());
				text_thoihandangkiem.setText(d);
			}
		}
		new Label(shlThngTinTi, SWT.NONE);

		btnng = new Button(shlThngTinTi, SWT.NONE);
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlThngTinTi.dispose();
			}
		});
		GridData gd_btnng = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnng.widthHint = 75;
		btnng.setLayoutData(gd_btnng);
		btnng.setText("\u0110\u00F3ng");

	}

}
