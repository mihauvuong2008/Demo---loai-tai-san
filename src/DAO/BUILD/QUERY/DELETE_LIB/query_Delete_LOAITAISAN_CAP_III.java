package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LOAITAISAN_CAP_III;

public class query_Delete_LOAITAISAN_CAP_III {

	private static Log log = LogFactory.getLog(query_Delete_LOAITAISAN_CAP_III.class);

	public String getString_Xoa(LOAITAISAN_CAP_III n) {
		try {
			return "DELETE FROM LOAITAISAN_CAP_III  WHERE MA_LOAITAISAN_CAP_III='" + n.getMA_LOAITAISAN_CAP_III()
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
