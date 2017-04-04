package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LOAITAISAN_CAP_I;

public class query_Delete_LOAITAISAN_CAP_I {

	private static Log log = LogFactory.getLog(query_Delete_LOAITAISAN_CAP_I.class);

	public String getString_Delete_LoaiTaisanCapI(LOAITAISAN_CAP_I l) {
		try {
			return "DELETE FROM LOAITAISAN_CAP_I  WHERE MA_LOAITAISAN_CAP_I='" + l.getMA_LOAITAISAN_CAP_I() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
