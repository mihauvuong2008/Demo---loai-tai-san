package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Controler.Controler;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;

public class ThongkeDanhsachPhuongtienTaisan {
	private Text text_TongsoPTTS;
	private Text text_Tinhtrang_Moi;
	private Text text_Tinhtrang_Tot;
	private Text text_Tinhtrang_Trungbinh;
	private Text text_Tinhtrang_Kem;
	private Text text_Tinhtrang_Khongsudungduoc;
	private Text text_Giatri_80100;
	private Text text_Giatri_6080;
	private Text text_Giatri_4060;
	private Text text_Giatri_2040;
	private Text text_Giatri_020;
	private Text text_DangBaoduong;

	private Composite ThongtinThongkeDanhsachTaisan;
	private Controler controler;

	public ThongkeDanhsachPhuongtienTaisan(Composite ThongtinThongkeDanhsachTaisan, Controler controler) {
		this.ThongtinThongkeDanhsachTaisan = ThongtinThongkeDanhsachTaisan;
		this.controler = controler;
		buildField();
	}

	public void setField(ArrayList<TAISAN> data) throws SQLException {
		int gt_80100 = 0, gt_6080 = 0, gt_4060 = 0, gt_2040 = 0, gt_020 = 0;
		int Moi = 0, Tot = 0, Trungbinh = 0, Kem = 0, Khongdungduoc = 0;
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsb = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DangThucHien_Suachua_Baoduong();
		ArrayList<TAISAN> tstotal = new ArrayList<>();
		for (DOT_THUCHIEN_SUACHUA_BAODUONG dot_THUCHIEN_SUACHUA_BAODUONG : dsb) {
			ArrayList<TAISAN> tsl = controler.getControl_TAISAN().get_TAISAN(dot_THUCHIEN_SUACHUA_BAODUONG);
			tstotal.addAll(tsl);
		}
		int PTTSSuachua = 0;
		for (TAISAN t : data) {
			double TileHaomon = 0;
			int PHANNHOM = controler.getControl_NHOMTAISAN_CAP_V().getPHANNHOM(t.getMA_NHOMTAISAN_CAP_V());
			switch (PHANNHOM) {
			case 0:
				NHOMTAISAN_CAP_V nc5 = controler.getControl_NHOMTAISAN_CAP_V()
						.getNHOMTAISAN_CAP_V(t.getMA_NHOMTAISAN_CAP_V());
				TileHaomon = nc5.getTILE_HAOMON();
				break;
			case 1:
				NHOM_TAISANCODINH_VOHINH ctv = controler.getControl_NHOM_TAISANCODINH_VOHINH()
						.getNHOM_TAISANCODINH_VOHINH(t.getMA_NHOMTAISAN_CAP_V());
				TileHaomon = ctv.getTILE_HAOMON();
				break;
			case 2:
				NHOM_TAISANCODINH_DACTHU ctt = controler.getControl_NHOM_TAISANCODINH_DACTHU()
						.getNHOM_TAISANCODINH_DACTHU(t.getMA_NHOMTAISAN_CAP_V());
				TileHaomon = ctt.getTILE_HAOMON();
				break;
			case 3:
				NHOM_TAISANCODINH_DACBIET ctb = controler.getControl_NHOM_TAISANCODINH_DACBIET()
						.getNHOM_TAISANCODINH_DACBIET(t.getMA_NHOMTAISAN_CAP_V());
				TileHaomon = ctb.getGIA_QUYUOC();
				break;
			default:
				break;
			}
			int Nam = new MyDateFormat().daysBetween(t.getNGAY_SU_DUNG(), new Date());
			double PhanHaomon = (Nam / 365) * TileHaomon;
			double conlai = 100 - PhanHaomon;
			if (conlai <= 20) {
				gt_020++;
			} else if (conlai <= 40) {
				gt_2040++;
			} else if (conlai <= 60) {
				gt_4060++;
			} else if (conlai <= 80) {
				gt_6080++;
			} else if (conlai <= 100) {
				gt_80100++;
			}
			switch (t.getTINH_TRANG()) {
			case 1:
				Moi++;
				break;
			case 2:
				Tot++;
				break;
			case 3:
				Trungbinh++;
				break;
			case 4:
				Kem++;
				break;
			case 5:
				Khongdungduoc++;
				break;
			default:
				Moi++;
				break;
			}
			boolean flag = false;
			for (TAISAN taisan : tstotal) {
				if (t.getMA_TAISAN() == taisan.getMA_TAISAN()) {
					flag = true;
				}
			}
			if (flag)
				PTTSSuachua++;
		}
		text_Giatri_80100.setText(gt_80100 + "");
		text_Giatri_6080.setText(gt_6080 + "");
		text_Giatri_4060.setText(gt_4060 + "");
		text_Giatri_2040.setText(gt_2040 + "");
		text_Giatri_020.setText(gt_020 + "");
		int Tongso = data.size();
		text_TongsoPTTS.setText(Tongso + "");
		text_Tinhtrang_Moi.setText(Moi + "");
		text_Tinhtrang_Tot.setText(Tot + "");
		text_Tinhtrang_Trungbinh.setText(Trungbinh + "");
		text_Tinhtrang_Kem.setText(Kem + "");
		text_Tinhtrang_Khongsudungduoc.setText(Khongdungduoc + "");
		text_DangBaoduong.setText(PTTSSuachua + "");
	}

	private void buildField() {
		ThongtinThongkeDanhsachTaisan.setLayout(new GridLayout(2, false));

		Label lblTngPtts = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblTngPtts.setText("Tổng số PTTS:");

		text_TongsoPTTS = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_TongsoPTTS.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_TongsoPTTS.setEditable(false);
		text_TongsoPTTS.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Label sepa1 = new Label(ThongtinThongkeDanhsachTaisan, SWT.SEPARATOR | SWT.HORIZONTAL);
		sepa1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblMi = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblMi.setText("Mới:");

		text_Tinhtrang_Moi = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Tinhtrang_Moi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang_Moi.setEditable(false);
		text_Tinhtrang_Moi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTt = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblTt.setText("Tốt");

		text_Tinhtrang_Tot = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Tinhtrang_Tot.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang_Tot.setEditable(false);
		text_Tinhtrang_Tot.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblTrungBnh = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblTrungBnh.setText("Trung bình:");

		text_Tinhtrang_Trungbinh = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Tinhtrang_Trungbinh.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang_Trungbinh.setEditable(false);
		text_Tinhtrang_Trungbinh.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblKm = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblKm.setText("Kém:");

		text_Tinhtrang_Kem = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Tinhtrang_Kem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang_Kem.setEditable(false);
		text_Tinhtrang_Kem.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblKhngSDng = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblKhngSDng.setText("Không sử dụng được:");

		text_Tinhtrang_Khongsudungduoc = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Tinhtrang_Khongsudungduoc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Tinhtrang_Khongsudungduoc.setEditable(false);
		text_Tinhtrang_Khongsudungduoc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label sepa2 = new Label(ThongtinThongkeDanhsachTaisan, SWT.SEPARATOR | SWT.HORIZONTAL);
		sepa2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label label_3 = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		label_3.setText("80%-100%: ");

		text_Giatri_80100 = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Giatri_80100.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Giatri_80100.setEditable(false);
		text_Giatri_80100.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_4 = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		label_4.setText("60%-80%: ");

		text_Giatri_6080 = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Giatri_6080.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Giatri_6080.setEditable(false);
		text_Giatri_6080.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_5 = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		label_5.setText("40%-60%: ");

		text_Giatri_4060 = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Giatri_4060.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Giatri_4060.setEditable(false);
		text_Giatri_4060.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_6 = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		label_6.setText("20%-40%: ");

		text_Giatri_2040 = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Giatri_2040.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Giatri_2040.setEditable(false);
		text_Giatri_2040.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label label_7 = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		label_7.setText("0%-20%:");

		text_Giatri_020 = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_Giatri_020.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_Giatri_020.setEditable(false);
		text_Giatri_020.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label sepa3 = new Label(ThongtinThongkeDanhsachTaisan, SWT.SEPARATOR | SWT.HORIZONTAL);
		sepa3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblDangBaoDuong = new Label(ThongtinThongkeDanhsachTaisan, SWT.NONE);
		lblDangBaoDuong.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDangBaoDuong.setText("Bảo dưỡng - Sửa chữa:");

		text_DangBaoduong = new Text(ThongtinThongkeDanhsachTaisan, SWT.BORDER);
		text_DangBaoduong.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		text_DangBaoduong.setEditable(false);
		text_DangBaoduong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
