package Control.SYSTEM_LOG;

import java.util.Date;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.LENH_DIEU_XE;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;

public class Log_Library {

	public String getString_XemToanboLenhDieuxe() {
		return "Xem Toàn bộ Lệnh điều xe";
	}

	public String getString_Xem_Danhsach_LenhDieuxe__Phongban_Batdau_Ketthuc(PHONGBAN dv, Date date_Batdau,
			Date date_Ketthuc) {
		return "Xem Danh sách Lệnh điều xe theo Đơn vị, ngày bắt đầu, ngày kết thúc ("
				+ (dv == null ? "null" : String.valueOf(dv.getTEN_PHONGBAN())) + " - "
				+ (date_Batdau == null ? "null" : date_Batdau) + "-" + (date_Ketthuc == null ? "null" : date_Ketthuc)
				+ ")";
	}

	public String getString_Xem_Danhsach_LenhDieuxe__PhuongtienGiaothong_Batdau_Ketthuc(PHUONGTIEN_GIAOTHONG pt,
			Date date_Batdau, Date date_Ketthuc) {
		return "Xem Danh sách Lệnh điều xe theo Phương tiện giao thông, ngày bắt đầu, ngày kết thúc ("
				+ (pt == null ? "null" : pt.getBIENSO().toString()) + " - "
				+ (date_Batdau == null ? "null" : date_Batdau) + "-" + (date_Ketthuc == null ? "null" : date_Ketthuc)
				+ ")";
	}

	public String getString_get_NgayDautien_DIEUXE() {
		return "Xem ngày Đầu tiên điều xe trong dữ liệu";
	}

	public String getString_get_NgayCuoicung_DIEUXE() {
		return "Xem ngày Cuối cùng điều xe trong dữ liệu";
	}

	public String getString_get_Oto_Dasudung__Phongban_Batdau_Ketthuc(PHONGBAN dv, Date date_Batdau,
			Date date_Ketthuc) {
		return "Xem danh sách Ô tô đã sử dụng của Đơn vị ("
				+ (dv == null ? "null" : String.valueOf(dv.getTEN_PHONGBAN())) + "-"
				+ (date_Batdau == null ? "null" : date_Batdau) + "-" + (date_Ketthuc == null ? "null" : date_Ketthuc)
				+ ")";
	}

	public String getString_get_LENHDIEUXE_DAUKY__PhuongtienGiaothong_Date(PHUONGTIEN_GIAOTHONG pt, Date date_Batdau) {
		return "Xem Lệnh điều xe đầu kỳ (" + (pt == null ? "null" : String.valueOf(pt.getBIENSO())) + "-"
				+ (date_Batdau == null ? "null" : date_Batdau) + ")";
	}

	public String getString_get_LENHDIEUXE_CUOIKY__PhuongtienGiaothong_Date(PHUONGTIEN_GIAOTHONG pt,
			Date date_Ketthuc) {
		return "Xem Lệnh điều xe cuối kỳ (" + (pt == null ? "null" : String.valueOf(pt.getBIENSO())) + "-"
				+ (date_Ketthuc == null ? "null" : date_Ketthuc) + ")";
	}

	public String getString_get_DiemxuatphatGanday(int i) {
		return "Xem Điểm xuất phát gần đây (" + i + ")";
	}

	public String getString_get_DiemdenGanday(int i) {
		return "Xem Điểm đến gần đây (" + i + ")";
	}

	public String getString_get_LENHDIEUXE_Gannhat(PHUONGTIEN_GIAOTHONG ptgt) {
		return "Xem Lệnh điều xe gần nhất (" + (ptgt == null ? "null" : ptgt.getBIENSO()) + ")";
	}

	public String getString_get_LENHDIEUXE(int ma_LENH_DIEU_XE) {
		return "Xem Lệnh điều xe (" + ma_LENH_DIEU_XE + ")";
	}

	public String getString_Them_LENHDIEUXE(String ma_PHUONGTIEN_GIAOTHONG) {
		return "Thêm Lệnh điều xe (mã ptgt:" + ma_PHUONGTIEN_GIAOTHONG + ")";
	}

	public String getString_Capnhat_LENHDIEUXE(int ma_LENH_DIEU_XE) {
		return "Cập nhật Lệnh điều xe (mã LĐX:" + ma_LENH_DIEU_XE + ")";
	}

	public String getString_Xoa_LENHDIEUXE(int ma_LENH_DIEU_XE) {
		return "Xóa Lệnh điều xe (mã LĐX:" + ma_LENH_DIEU_XE + ")";
	}

	public String getString_Huy_LENHDIEUXE(int ma_LENH_DIEU_XE) {
		return "Hủy Lệnh điều xe (mã LĐX:" + ma_LENH_DIEU_XE + ")";
	}

	public String getString_ThemDexuat(int key) {
		return "Thêm Đề xuất (mã ĐX:" + key + ")";
	}

	public String getString_Xem_Dexuat(int ma_DE_XUAT) {
		return "Xem Đề xuất (mã ĐX:" + ma_DE_XUAT + ")";
	}

	public String getString_Xem_Danhsach_Dexuat_Suachua_Baoduong(Date begin, Date end) {
		return "Xem Danh sách Đề xuất sửa chữa - bảo dưỡng(" + (begin == null ? "null" : begin) + " - "
				+ (end == null ? "null" : end) + ")";
	}

	public String getString_Xem_Danhsach_Dexuat_Muasam(Date begin, Date end) {
		return "Xem Danh sách Đề xuất Mua sắm(" + (begin == null ? "null" : begin) + " - "
				+ (end == null ? "null" : end) + ")";
	}

	public String getString_Xem_Danhsach_Dexuat_Thanhly(Date begin, Date end) {
		return "Xem Danh sách Đề xuất Thanh lý(" + (begin == null ? "null" : begin) + " - "
				+ (end == null ? "null" : end) + ")";
	}

	public String getString_Xoa_Dexuat(int ma_DE_XUAT) {
		return "Xóa Đề xuất (mã ĐX:" + ma_DE_XUAT + ")";
	}

	public String getString_Capnhat_Hoso_Dexuat(int ma_DE_XUAT, int ma_NewTapHoso) {
		return "Cập nhật Hồ sơ Đề xuất (mã ĐX:" + ma_DE_XUAT + " - " + ma_NewTapHoso + ")";
	}

	public String getString_Capnhat_Dexuat(int ma_DE_XUAT) {
		return "Cập nhật Đề xuất (mã ĐX:" + ma_DE_XUAT + ")";
	}

	public String getString_Capnhat_ThoidiemKetthuc_Dexuat(int ma_DE_XUAT, Date tHISDAY) {
		return "Cập nhật Thời điểm kết thúc Đề xuất (mã ĐX:" + ma_DE_XUAT + " - " + (tHISDAY == null ? "null" : tHISDAY)
				+ ")";
	}

	public String getString_Xem_NgaythuchienThanhlyTaisan(DOT_THUCHIEN_GIAM_TAISAN dg) {
		return "Xem ngày thực hiện giảm tài sản (mã ĐGT:" + dg.getMA_DOT_GIAM() + ")";
	}

	public String getString_Xem_Danhsach_DotThanhlyTaisan() {
		return "Xem Danh sách đợt thanh lý - bàn giao tài sản";
	}

	public String getString_Them_DotThanhlyTaisan(DOT_THUCHIEN_GIAM_TAISAN dg) {
		return "Thêm Đợt thanh lý - bàn giao tài sản (Mã ĐGT:" + dg.getMA_DOT_GIAM() + ")";
	}

	public String getString_Danhsach_DotThanhlyTaisan_Dangthuchien() {
		return "Xem Đợt thanh lý - bàn giao tài sản đang thực hiện";
	}

	public String getString_DotThanhlyTaisan(int ma_DOTGIAM) {
		return "Xem Đợt thanh lý - bàn giao tài sản (Mã ĐGT:" + ma_DOTGIAM + ")";
	}

	public String getString_DOT_THUCHIEN_GIAM_TAISAN_ChuaHoanthanh() {
		return "Xem Đợt thanh lý - bàn giao tài sản [Đồ thị]";
	}

	public String getString_XoaDOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		return "Xem Đợt thanh lý - bàn giao tài sản (Mã ĐGT:" + dgt.getMA_DOT_GIAM() + ")";
	}

	public String getString_Xem_Danhsach_DOT_THUCHIEN_GIAM_TAISAN(Date begin, Date end) {
		return "Xem Danh sách Đợt thanh lý - bàn giao tài sản ( " + (begin == null ? "null" : begin) + " - "
				+ (end == null ? "null" : end) + ")";
	}

	public String getString_Capnhat_DOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		return "Xem Cập nhật Đợt thanh lý - bàn giao tài sản ( " + dgt.getMA_DOT_GIAM() + ")";
	}

	public String getString_CapnhatTaisan_DotThanhlyTaisan(DOT_THUCHIEN_GIAM_TAISAN dg, TAISAN t) {
		return "Cập nhật Tài sản tham gia Đợt thanh lý - bàn giao tài sản ( " + dg.getMA_DOT_GIAM() + ", "
				+ t.getMA_TAISAN() + ")";
	}

	public String getString_get_LENHDIEUXE_AFTER(LENH_DIEU_XE last) {
		return "Xem Danh sách Lệnh điều xe sau lệnh:" + last.getMA_LENH_DIEU_XE();
	}

}
