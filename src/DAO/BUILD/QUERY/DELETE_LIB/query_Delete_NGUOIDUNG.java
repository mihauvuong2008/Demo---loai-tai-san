package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NGUOIDUNG;

public class query_Delete_NGUOIDUNG {

	private static Log log = LogFactory.getLog(query_Delete_NGUOIDUNG.class);

	public String getString_XoaNguoidung(NGUOIDUNG i) {
		try {
			return "DELETE FROM NGUOIDUNG  where Ten_tai_khoan='" + i.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
