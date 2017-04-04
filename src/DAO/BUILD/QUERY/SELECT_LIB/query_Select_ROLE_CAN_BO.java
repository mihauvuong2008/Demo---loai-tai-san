package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NGUOIDUNG;

public class query_Select_ROLE_CAN_BO {

	public String getString_getRole(NGUOIDUNG user) {
		try {
			return "SELECT * FROM ROLE as r " + " INNER JOIN ROLE_CAN_BO as rc " + " ON r.MA_QUYEN = rc.MA_QUYEN "
					+ " WHERE TEN_TAI_KHOAN = '" + user.getTEN_TAI_KHOAN() + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
