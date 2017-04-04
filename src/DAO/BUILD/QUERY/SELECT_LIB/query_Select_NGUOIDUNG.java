package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.PHONGBAN;

public class query_Select_NGUOIDUNG {

	public String getString_Tatca_Taikhoan_Matkhau() {
		try {
			return "select TEN_TAI_KHOAN, MAT_KHAU from NGUOIDUNG";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Nguoidung_Full(String taikhoan) {
		try {
			return " select * from NGUOIDUNG where TEN_TAI_KHOAN='" + taikhoan + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Nguoidung_Normal(String taikhoan) {
		try {
			return " select TEN_TAI_KHOAN , TEN_CAN_BO, GIOI_THIEU , MA_PHONGBAN from NGUOIDUNG where TEN_TAI_KHOAN='"
					+ taikhoan + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaNguoidung() {
		try {
			return "select TEN_TAI_KHOAN , MAT_KHAU, TEN_CAN_BO, GIOI_THIEU, MA_PHONGBAN from NGUOIDUNG ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Nguoidung_Phongban(PHONGBAN p) {
		try {
			return " select TEN_TAI_KHOAN ,  TEN_CAN_BO, GIOI_THIEU , MA_PHONGBAN from NGUOIDUNG WHERE MA_PHONGBAN ='"
					+ p.getMA_PHONGBAN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}

}
