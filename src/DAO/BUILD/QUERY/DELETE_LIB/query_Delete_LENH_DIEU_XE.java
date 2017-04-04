package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LENH_DIEU_XE;

public class query_Delete_LENH_DIEU_XE {
	private static Log log = LogFactory.getLog(query_Delete_LENH_DIEU_XE.class);

	public String getString_Xoa_LenhDieuxe(LENH_DIEU_XE l) {
		try {
			String result = "DELETE FROM LENH_DIEU_XE  WHERE MA_LENH_DIEU_XE='" + l.getMA_LENH_DIEU_XE() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
