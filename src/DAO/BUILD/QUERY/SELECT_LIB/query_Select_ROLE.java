package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NGUOIDUNG;

public class query_Select_ROLE {

	public String getString_TatcaQuyencuaUser(NGUOIDUNG user) {
		try {
			return "select  ROLE.MA_QUYEN, TEN_QUYEN, MO_TA, THEM_NGUOIDUNG,"
					+ "PHAN_QUYEN_NGUOIDUNG, XEM_THONGTIN_NGUOIDUNG, XOA_NGUOIDUNG, THEM_THONGTIN_TAISAN,"
					+ " XEM_THONGTIN_TAISAN, SUA_THONGTIN_TAISAN, XOA_THONGTIN_TAISAN, "
					+ "THEM_CONGVIEC,XEM_THONGTIN_CONGVIEC, SUA_THONGTIN_CONGVIEC, XOA_CONGVIEC,"
					+ " THEM_HOSO,XEM_THONGTIN_HOSO, SUA_THONGTIN_HOSO,  XOA_HOSO "
					+ "from ROLE, ROLE_CAN_BO, NGUOIDUNG where ROLE_CAN_BO.Ten_Tai_khoan = NGUOIDUNG.Ten_Tai_khoan "
					+ "AND ROLE_CAN_BO.MA_QUYEN=ROLE.MA_QUYEN AND NGUOIDUNG.Ten_Tai_khoan = '" + user.getTEN_TAI_KHOAN()
					+ "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaQuyen() {
		try {
			return " select * from ROLE;";
		} catch (Exception e) {
			return null;
		}
	}

}
