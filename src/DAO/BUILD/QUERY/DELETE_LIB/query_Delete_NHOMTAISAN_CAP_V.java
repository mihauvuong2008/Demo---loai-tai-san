package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_V;

public class query_Delete_NHOMTAISAN_CAP_V {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_V.class);

	public String getString_Xoa(NHOMTAISAN_CAP_V l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V  WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOMTAISAN_CAP_V() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
