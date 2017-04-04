package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LOAITAISAN_CAP_II;

public class query_Delete_LOAITAISAN_CAP_II {

	private static Log log = LogFactory.getLog(query_Delete_LOAITAISAN_CAP_II.class);

	public String getString_Xoa(LOAITAISAN_CAP_II n) {
		try {
			return "DELETE FROM LOAITAISAN_CAP_II  WHERE MA_LOAITAISAN_CAP_II='" + n.getMA_LOAITAISAN_CAP_II() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
