package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_III;

public class query_Delete_NHOMTAISAN_CAP_III {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_III.class);

	public String getString_Xoa(NHOMTAISAN_CAP_III l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_III  WHERE MA_NHOMTAISAN_CAP_III='" + l.getMA_NHOMTAISAN_CAP_III()
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_III;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
