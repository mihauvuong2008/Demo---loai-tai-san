package View.MarkItem;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Combo;

import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.NHOMTAISAN_CAP_V;
import DAO.LOAITAISAN_CAP_III;
import DAO.LOAI_XE;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;

public class Fill_ItemData {

	public void setComboBox_LYDOTANG(Combo combo_Lydotang) {
		combo_Lydotang.setItems(new String[] { "Mua mới", "Nhận bàn giao", "Biếu tặng", "Nguồn gốc khác" });
		combo_Lydotang.setData("Mua mới", 1);
		combo_Lydotang.setData("Nhận bàn giao", 2);
		combo_Lydotang.setData("Biếu tặng", 3);
		combo_Lydotang.setData("Nguồn gốc khác", 4);
		combo_Lydotang.select(0);
	}

	public void setComboBox_TRANGTHAI_TAISAN(Combo combo_Trangthai) {
		combo_Trangthai.setItems(new String[] { "Đang thực hiện tăng", "Sử dụng", "Kho", "Đã thực hiện giảm" });
		combo_Trangthai.setData("Đang thực hiện tăng", 0);
		combo_Trangthai.setData("Sử dụng", 1);
		combo_Trangthai.setData("Kho", 2);
		combo_Trangthai.setData("Đã thực hiện giảm", 3);

	}

	public void setComboBox_TINHTRANH_TAISAN(Combo combo_Tinhtrang) {
		combo_Tinhtrang.setItems(new String[] { "Mới", "Tốt", "Trung bình", "Kém", "Không sử dụng được" });
		combo_Tinhtrang.setData("Mới", 1);
		combo_Tinhtrang.setData("Tốt", 2);
		combo_Tinhtrang.setData("Trung bình", 3);
		combo_Tinhtrang.setData("Kém", 4);
		combo_Tinhtrang.setData("Không sử dụng được", 5);

	}

	public void setComboBox_MUCKINHPHI(Combo combo) {
		combo.setItems(new String[] { "Dưới 200.000", "Từ 200.000 - 1000.000", "Từ 1000.000 - 5000.000",
				"Từ 5000.0000 - 20.000.000", "Từ 20.000.000-50.000.000", "Trên 50.000.000" });
		combo.setData("Dưới 200.000", 1);
		combo.setData("Từ 200.000 - 1000.000", 2);
		combo.setData("Từ 1000.000 - 5000.000", 3);
		combo.setData("Từ 5000.0000-20.000.000", 4);
		combo.setData("Từ 20.000.000-50.000.000", 5);
		combo.setData("Trên 50.000.000", 6);
		combo.select(0);
	}

	public String getStringOfLYDOTANG(int ly_DO_TANG) {
		switch (ly_DO_TANG) {
		case 1:
			return "Mua mới";
		case 2:
			return "Nhận bàn giao";
		case 3:
			return "Biếu tặng";
		case 4:
			return "Nguồn gốc khác";
		default:
			return "Nguồn gốc khác";
		}
	}

	public String getStringOfLYDOGIAM(int ly_DO_GIAM) {
		switch (ly_DO_GIAM) {
		case 1:
			return "Tiêu hủy";
		case 2:
			return "Chuyển giao";
		case 3:
			return "Biếu tặng";
		case 4:
			return "Lý do gốc khác";
		default:
			return "Lý do gốc khác";
		}
	}

	public String getStringOfTINHTRANG(int tinh_TRANG) {
		switch (tinh_TRANG) {
		case 1:
			return "Mới";
		case 2:
			return "tốt";
		case 3:
			return "Trung bình";
		case 4:
			return "Kém";
		default:
			return "Không sử dụng được";
		}
	}

	public String getStringOfSUACHUA_BAODUONG(int SUACHUA_BAODUONG) {
		switch (SUACHUA_BAODUONG) {
		case 1:
			return "Bảo dưỡng";
		case 2:
			return "Sửa chữa";
		default:
			return "Bảo dưỡng";
		}
	}

	public int getIntOfSUACHUA_BAODUONG(String SUACHUA_BAODUONG) {
		switch (SUACHUA_BAODUONG) {
		case "Bảo dưỡng":
			return 1;
		case "Sửa chữa":
			return 2;
		default:
			return 1;
		}
	}

	public void setComboBox_DONVI_NOIBO(Combo combo, ArrayList<PHONGBAN> pa) {
		for (PHONGBAN p : pa) {
			combo.add(p.getTEN_PHONGBAN());
			combo.setData(p.getTEN_PHONGBAN(), p);
		}
		combo.select(0);
	}

	public void setComboBox_LOAITAISAN(Combo combo, ArrayList<LOAITAISAN_CAP_III> pna) {
		for (LOAITAISAN_CAP_III p : pna) {
			combo.add(p.getTEN_LOAITAISAN_CAP_III());
			combo.setData(p.getTEN_LOAITAISAN_CAP_III(), p);
		}
		combo.select(0);
	}

	public void setComboBox_NHOMTAISAN(Combo combo, ArrayList<NHOMTAISAN_CAP_V> pna) {
		for (NHOMTAISAN_CAP_V p : pna) {
			combo.add(p.getTEN_NHOMTAISAN_CAP_V());
			combo.setData(p.getTEN_NHOMTAISAN_CAP_V(), p);
		}
		combo.select(0);
	}

	public void setComboBox_LYDOGIAM(Combo combo_Lydogiam) {
		combo_Lydogiam.setItems(new String[] { "Tiêu hủy", "Chuyển giao", "Biếu tặng", "Lý do gốc khác" });
		combo_Lydogiam.setData("Tiêu hủy", 1);
		combo_Lydogiam.setData("Chuyển giao", 2);
		combo_Lydogiam.setData("Biếu tặng", 3);
		combo_Lydogiam.setData("Lý do gốc khác", 4);
		combo_Lydogiam.select(0);
	}

	public void setComboBox_PHUONGTIEN_GIAOTHONG(Combo combo_Bienso, ArrayList<PHUONGTIEN_GIAOTHONG> pl) {
		if (pl != null)
			for (PHUONGTIEN_GIAOTHONG p : pl) {
				combo_Bienso.add(p.getBIENSO());
				combo_Bienso.setData(p.getBIENSO(), p);
			}
		combo_Bienso.select(0);
	}

	public void setComboBox_NGUOIDUNG(Combo combo_canbo, ArrayList<NGUOIDUNG> ndl) {
		if (ndl != null)
			for (NGUOIDUNG p : ndl) {
				combo_canbo.add(p.getTEN_TAI_KHOAN());
				combo_canbo.setData(p.getTEN_TAI_KHOAN(), p);
			}
		combo_canbo.select(0);

	}

	public void setComboBox_LOAIPHUONGTIEN_Phuongtien_Giaothong(Combo combo_Loaiphuongtien, int loaiPTTG) {
		combo_Loaiphuongtien.removeAll();
		combo_Loaiphuongtien.setItems(new String[] { "Ô tô", "Xe máy" });
		combo_Loaiphuongtien.setData("Ô tô", 1);
		combo_Loaiphuongtien.setData("Xe máy", 2);
		for (String e : combo_Loaiphuongtien.getItems()) {
			int x = (int) combo_Loaiphuongtien.getData(e);
			if (x == loaiPTTG) {
				combo_Loaiphuongtien.setText(e);
			}
		}
	}

	public int getInt_HinhThucNhanCongviec_NguoiDungNhanViec() {
		return 1;
	}

	public int getInt_HinhThucNhanCongviec_GiaoviecChoNguoiDung() {
		return 0;
	}

	public String getString_GIAO_NHANVIEC(NGUOIDUNG_THUCHIEN ndth) {
		switch (ndth.getGIAO_NHANVIEC()) {
		case 1:
			return "Cán bộ Nhận việc";
		default:
			return "Cán bộ được giao việc";
		}
	}

	public String getString_GIAO_NHANVIEC(NGUOIDUNG_NGHIEMTHU ndngth) {
		switch (ndngth.getGIAO_NHANVIEC()) {
		case 1:

			return "Cán bộ Nhận việc";

		default:
			return "Cán bộ được giao việc";
		}
	}

	public String getString_GIAO_NHANVIEC(NGUOIDUNG_QUYETTOAN ndngth) {
		switch (ndngth.getGIAO_NHANVIEC()) {
		case 1:

			return "Cán bộ Nhận việc";

		default:
			return "Cán bộ được giao việc";
		}
	}

	public String getString_LoaiCongviec(int loai_CONGVIEC) {
		switch (loai_CONGVIEC) {
		case 1:
			return "Sửa chữa - bảo dưỡng";
		case 2:
			return "Mua sắm PTTS";
		case 3:
			return "Thanh lý PTTS";
		default:
			return "--";
		}
	}

	public String getString_LOAI_PHANVIEC(int loai_PHANVIEC) {
		switch (loai_PHANVIEC) {
		case 1:
			return "Tổ chức - thực hiện";
		case 2:
			return "Nghiệm thu - bàn giao";
		case 3:
			return "quyết toán - thanh lý hợp đồng";
		default:
			return "--";
		}
	}

	public String getInt_ThayNhot(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayLocNhot(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayLocgio(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayLocnhienlieu(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayDauphanh_Daulyhop(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayDauhopso(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayDauVisai(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayLocgioGianlanh(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_ThayDautroluclai(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public String getInt_Baoduongkhac(boolean thayNhot) {
		if (thayNhot)
			return "1";
		return "0";
	}

	public int getInt_Xemay() {
		return 2;
	}

	public int getInt_Oto() {
		return 1;
	}

	public String getString_LOAI_THONGBAO(int input) {
		switch (input) {
		case 1:
			return "Lệnh điều xe";
		case 2:
			return "Sửa chữa";
		case 3:
			return "Bảo dưỡng";
		case 4:
			return "Mua sắm";
		case 5:
			return "Thanh lý";
		case 6:
			return "Người dùng";
		default:
			return "";
		}
	}

	public int getInt_LOAI_THONGBAO_LenhDieuxe() {
		return 1;
	}

	public int getInt_LOAI_THONGBAO_Suachua() {
		return 2;
	}

	public int getInt_LOAI_THONGBAO_Baoduong() {
		return 3;
	}

	public int getInt_LOAI_THONGBAO_Muasam() {
		return 4;
	}

	public int getInt_LOAI_THONGBAO_Thanhly() {
		return 5;
	}

	public int getInt_LOAI_THONGBAO_Nguoidung() {
		return 6;
	}

	public int getInt_LOAI_THONGBAO_ThongbaoKhac() {
		return 7;
	}

	public String getStringLOAI_PHUONGTIEN_GIAOTHONG(int loai_PHUONGTIEN_GIAOTHONG) {
		switch (loai_PHUONGTIEN_GIAOTHONG) {
		case 1:
			return "Ô tô";
		case 2:
			return "Xe máy";

		default:
			return "-";
		}
	}

	public String getStringOfTRANGTHAI(int trang_THAI) {
		switch (trang_THAI) {
		case 1:
			return "Đang sử dụng";
		case 2:
			return "Kho";
		case 3:
			return "Ngoài danh sách quản lý";
		default:
			return "";
		}
	}

	public void setComboBox_Dongxe(Combo combo_Dongxe, ArrayList<LOAI_XE> pa) {
		combo_Dongxe.removeAll();
		for (LOAI_XE p : pa) {
			combo_Dongxe.add(p.getTEN_DONG_XE());
			combo_Dongxe.setData(p.getTEN_DONG_XE(), p);
		}
		combo_Dongxe.select(0);
	}

	public String get_String_LOAI_NHIENLIEU(int loaiNhienlieu) {
		switch (loaiNhienlieu) {
		case 1:
			return "XĂNG";
		case 2:
			return "DẦU";
		default:
			return "";
		}
	}

	public int getIntLOAI_NHIENLIEU_XANG() {
		return 1;
	}

	public int getIntLOAI_NHIENLIEU_DAU() {
		return 2;
	}

	public void setComboBox_LOAI_NHIENLIEU(Combo combo_LOAI_NHIENLIEU) {
		combo_LOAI_NHIENLIEU.removeAll();
		combo_LOAI_NHIENLIEU.add("XĂNG");
		combo_LOAI_NHIENLIEU.add("DẦU");
		combo_LOAI_NHIENLIEU.setData("XĂNG", 1);
		combo_LOAI_NHIENLIEU.setData("DẦU", 2);

	}

	public String getStringHinhthucMuasam(int ly_DO_TANG) {
		switch (ly_DO_TANG) {
		case 1:
			return "Mua mới";
		case 2:

			return "Nhận bàn giao";
		case 3:

			return "Biếu tặng";
		case 4:

			return "Nguồn gốc khác";

		default:
			break;
		}
		return "";
	}

}
