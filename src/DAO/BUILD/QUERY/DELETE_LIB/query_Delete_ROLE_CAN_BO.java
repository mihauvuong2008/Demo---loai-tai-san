package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NGUOIDUNG;
import DAO.ROLE;

public class query_Delete_ROLE_CAN_BO {

	private static Log log = LogFactory.getLog(query_Delete_ROLE_CAN_BO.class);

	public String getString_Xoa_Quyen_Daphan_ChoNguoidung(NGUOIDUNG nd, ROLE r) {
		try {
			return "DELETE FROM ROLE_CAN_BO WHERE TEN_TAI_KHOAN ='" + nd.getTEN_TAI_KHOAN() + "' AND MA_QUYEN='"
					+ r.getMA_QUYEN() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
