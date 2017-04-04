package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.GIAI_DOAN_THUC_HIEN;

public class query_Select_NGUOIDUNG_THUCHIEN {

	public String getString_ThongtinPhanviec_HosoPhanviec_Nguoidung_Thuchien(String ten_TAI_KHOAN,
			GIAI_DOAN_THUC_HIEN th) {
		try {
			return "SELECT * FROM GIAI_DOAN_THUC_HIEN_CAN_BO WHERE TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
					+ "' AND MA_GIAI_DOAN_THUC_HIEN = '" + th.getMA_GIAI_DOAN_THUC_HIEN() + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatCa_Nguoidung_Thamgia_Phanviec_Thuchien(GIAI_DOAN_THUC_HIEN gdth) {
		try {
			return "SELECT * FROM GIAI_DOAN_THUC_HIEN_CAN_BO WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ gdth.getMA_GIAI_DOAN_THUC_HIEN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NGUOIDUNG_Thamgia_GiaidoanThuchien(GIAI_DOAN_THUC_HIEN gdth) {
		try {
			return "SELECT * FROM GIAI_DOAN_THUC_HIEN_CAN_BO INNER JOIN NGUOIDUNG "
					+ " ON NGUOIDUNG.TEN_TAI_KHOAN = GIAI_DOAN_THUC_HIEN_CAN_BO.TEN_TAI_KHOAN WHERE  GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN = '"
					+ gdth.getMA_GIAI_DOAN_THUC_HIEN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}
}
