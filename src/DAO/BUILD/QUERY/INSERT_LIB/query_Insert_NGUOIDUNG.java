package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUOIDUNG;

public class query_Insert_NGUOIDUNG {

	public String getString_ThemNguoidung(NGUOIDUNG i) {
		try {
			return "INSERT INTO NGUOIDUNG (TEN_TAI_KHOAN, MAT_KHAU, TEN_CAN_BO,  GIOI_THIEU, MA_PHONGBAN) VALUES( '"
					+ i.getTEN_TAI_KHOAN() + "', '" + i.getMAT_KHAU() + "', '" + i.getTEN_CAN_BO() + "', '"
					+ i.getGIOI_THIEU() + "', '" + i.getMA_PHONGBAN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
