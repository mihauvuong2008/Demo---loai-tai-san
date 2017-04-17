package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.LICH_CONG_TAC;

public class query_Delete_LICH_CONG_TAC {

	private static final Log log = LogFactory.getLog(query_Delete_LICH_CONG_TAC.class);

	public String getString_XoaLICH_CONG_TAC(LICH_CONG_TAC cd) {
		try {
			String result = "DELETE FROM LICH_CONG_TAC  WHERE MA_LICH_CONG_TAC='" + cd.getMA_LICH_CONG_TAC() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
