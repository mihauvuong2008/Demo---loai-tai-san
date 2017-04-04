package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NGUONGIAM;

public class query_Delete_NGUONGIAM {

	private static Log log = LogFactory.getLog(query_Delete_NGUONGIAM.class);

	public String getString_XoaNguongiam(NGUONGIAM ng) {
		try {
			return "DELETE FROM NGUONGIAM WHERE MA_NGUONGIAM ='" + ng.getMA_NGUONGIAM() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
