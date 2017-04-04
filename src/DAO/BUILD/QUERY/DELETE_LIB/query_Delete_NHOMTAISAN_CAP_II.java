package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_II;

public class query_Delete_NHOMTAISAN_CAP_II {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_II.class);

	public String getString_Xoa(NHOMTAISAN_CAP_II l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_II  WHERE MA_NHOMTAISAN_CAP_II='" + l.getMA_NHOMTAISAN_CAP_II() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_II;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
