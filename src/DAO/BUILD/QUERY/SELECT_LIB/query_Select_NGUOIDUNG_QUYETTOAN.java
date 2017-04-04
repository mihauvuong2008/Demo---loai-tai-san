package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.GIAI_DOAN_QUYET_TOAN;

public class query_Select_NGUOIDUNG_QUYETTOAN {

	public String getString_NguoiDung_Quyettoan(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN qt) {
		try {
			return "SELECT * FROM GIAI_DOAN_QUYET_TOAN_CAN_BO WHERE TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
					+ "' AND MA_GIAI_DOAN_QUYET_TOAN = '" + qt.getMA_GIAI_DOAN_QUYET_TOAN() + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatCaNguoidung_Quyettoan(GIAI_DOAN_QUYET_TOAN phanviec) {
		try {
			return "SELECT * FROM GIAI_DOAN_QUYET_TOAN_CAN_BO WHERE MA_GIAI_DOAN_QUYET_TOAN='"
					+ phanviec.getMA_GIAI_DOAN_QUYET_TOAN() + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatCaNguoidungFull_Quyettoan(GIAI_DOAN_QUYET_TOAN result) {
		try {
			return "SELECT NGUOIDUNG.TEN_TAI_KHOAN, TEN_CAN_BO, GIOI_THIEU,  MA_PHONGBAN "
					+ " FROM NGUOIDUNG, GIAI_DOAN_QUYET_TOAN_CAN_BO "
					+ " WHERE NGUOIDUNG.TEN_TAI_KHOAN = GIAI_DOAN_QUYET_TOAN_CAN_BO.TEN_TAI_KHOAN AND  GIAI_DOAN_QUYET_TOAN_CAN_BO.MA_GIAI_DOAN_QUYET_TOAN= '"
					+ result.getMA_GIAI_DOAN_QUYET_TOAN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}
}
