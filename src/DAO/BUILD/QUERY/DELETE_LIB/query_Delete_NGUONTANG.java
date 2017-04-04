package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NGUONTANG;

public class query_Delete_NGUONTANG {

	private static Log log = LogFactory.getLog(query_Delete_NGUONTANG.class);

	public String getString_XoaNguontang(NGUONTANG nt) {
		try {
			return "DELETE FROM NGUONTANG  WHERE MA_NGUONTANG='" + nt.getMA_NGUONTANG() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
