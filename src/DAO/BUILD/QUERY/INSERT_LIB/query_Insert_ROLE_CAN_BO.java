package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUOIDUNG;
import DAO.ROLE;

public class query_Insert_ROLE_CAN_BO {

	public String getString_ThemNguoiDung_duoc_CapQuyen(NGUOIDUNG nd, ROLE r) {
		try {
			return "INSERT INTO ROLE_CAN_BO ( TEN_TAI_KHOAN, MA_QUYEN) VALUES( '" + nd.getTEN_TAI_KHOAN() + "', '"
					+ r.getMA_QUYEN() + "');";
		} catch (Exception e) {
			return null;
		}
	}
}
