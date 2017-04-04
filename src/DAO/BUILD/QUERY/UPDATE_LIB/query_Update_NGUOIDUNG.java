package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUOIDUNG;

public class query_Update_NGUOIDUNG {

	public String getString_CapnhatTENNguoigdung(String tenNguoidung, NGUOIDUNG user) {
		try {
			return "UPDATE NGUOIDUNG SET TEN_CAN_BO = '" + tenNguoidung + "' WHERE TEN_TAI_KHOAN='"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_CapnhatGioithieuNguoigdung(String GioiThieu, NGUOIDUNG user) {
		try {
			return "UPDATE NGUOIDUNG SET Gioi_thieu='" + GioiThieu + "' where Ten_tai_khoan='" + user.getTEN_TAI_KHOAN()
					+ "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_TrangthaiDangnhap(int b, NGUOIDUNG user) {
		try {
			return "UPDATE NGUOIDUNG SET TRANG_THAI_DANG_NHAP='" + b + "' where Ten_tai_khoan= '"
					+ user.getTEN_TAI_KHOAN() + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Matkhau(String text, NGUOIDUNG user) {
		try {
			return "UPDATE nguoidung SET MAT_KHAU='" + text + "' where Ten_tai_khoan='" + user.getTEN_TAI_KHOAN()
					+ "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Nguoidung(NGUOIDUNG r) {
		try {
			return "UPDATE NGUOIDUNG SET TEN_CAN_BO='" + r.getTEN_CAN_BO() + "' , GIOI_THIEU='" + r.getGIOI_THIEU()
					+ "' , MA_PHONGBAN='" + r.getMA_PHONGBAN() + "' where Ten_tai_khoan='" + r.getTEN_TAI_KHOAN()
					+ "' AND NOT (Ten_tai_khoan='SYSTEM');";
		} catch (Exception e) {
			return null;
		}
	}

}
