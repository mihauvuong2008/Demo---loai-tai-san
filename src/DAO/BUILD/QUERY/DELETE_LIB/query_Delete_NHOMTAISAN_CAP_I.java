package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_I;

public class query_Delete_NHOMTAISAN_CAP_I {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_I.class);

	public String getString_Xoa(NHOMTAISAN_CAP_I l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I  WHERE MA_NHOMTAISAN_CAP_I='" + l.getMA_NHOMTAISAN_CAP_I() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
