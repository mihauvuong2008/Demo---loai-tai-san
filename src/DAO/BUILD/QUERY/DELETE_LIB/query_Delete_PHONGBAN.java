package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.PHONGBAN;

public class query_Delete_PHONGBAN {

	private static Log log = LogFactory.getLog(query_Delete_PHONGBAN.class);

	public String getString_Xoa(PHONGBAN p) {
		try {
			return "DELETE FROM PHONGBAN WHERE MA_PHONGBAN='" + p.getMA_PHONGBAN() + "'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
