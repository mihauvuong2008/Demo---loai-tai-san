package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUOIDUNG;

public class query_Insert_SYSTEM_LOG {

	public String getString_INSERT_INTO_SYSTEM_LOG(NGUOIDUNG user, String message, String date) {
		try {
			return "INSERT INTO SYSTEM_LOG " + "(TEN_TAI_KHOAN, NOIDUNG, THOIGIAN )VALUES( '" + user.getTEN_TAI_KHOAN()
					+ "','" + message + "', '" + date + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
