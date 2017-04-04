package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LOAI_XE;

public class query_Delete_LOAI_XE {

	private static Log log = LogFactory.getLog(query_Delete_LOAI_XE.class);

	public String getString_Xoa(LOAI_XE i) {
		try {
			return "DELETE FROM LOAI_XE  WHERE MA_LOAI_XE='" + i.getMA_LOAI_XE() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
