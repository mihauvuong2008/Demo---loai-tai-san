package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.ROLE;

public class query_Delete_ROLE {

	private static Log log = LogFactory.getLog(query_Delete_ROLE.class);

	public String getString_Xoa(ROLE r) {
		try {
			return "DELETE FROM ROLE where MA_QUYEN='" + r.getMA_QUYEN() + "'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
