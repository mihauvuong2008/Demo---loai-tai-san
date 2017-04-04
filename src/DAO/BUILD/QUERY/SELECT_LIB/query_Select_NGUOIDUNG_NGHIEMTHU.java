package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.GIAI_DOAN_NGHIEM_THU;

public class query_Select_NGUOIDUNG_NGHIEMTHU {

	public String getString_NguoidungNghiemthuCongviec(String ten_TAI_KHOAN, GIAI_DOAN_NGHIEM_THU ngth) {
		try {
			String result = "SELECT * FROM GIAI_DOAN_NGHIEM_THU_CAN_BO WHERE TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
					+ "' AND MA_GIAI_DOAN_NGHIEM_THU = '" + ngth.getMA_GIAI_DOAN_NGHIEM_THU() + "'; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Tatca_NguoidungNghiemthu(GIAI_DOAN_NGHIEM_THU gdnt) {
		try {
			String result = "SELECT * FROM GIAI_DOAN_NGHIEM_THU_CAN_BO WHERE MA_GIAI_DOAN_NGHIEM_THU='"
					+ gdnt.getMA_GIAI_DOAN_NGHIEM_THU() + "'; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NguoidungThamgiaNghiemthu(GIAI_DOAN_NGHIEM_THU result) {
		try {
			return "SELECT * " + " FROM GIAI_DOAN_NGHIEM_THU_CAN_BO INNER JOIN NGUOIDUNG ON  "
					+ " NGUOIDUNG.TEN_TAI_KHOAN = GIAI_DOAN_NGHIEM_THU_CAN_BO.TEN_TAI_KHOAN WHERE GIAI_DOAN_NGHIEM_THU_CAN_BO.MA_GIAI_DOAN_NGHIEM_THU= '"
					+ result.getMA_GIAI_DOAN_NGHIEM_THU() + "' ";
		} catch (Exception e) {
			return null;
		}
	}

}
