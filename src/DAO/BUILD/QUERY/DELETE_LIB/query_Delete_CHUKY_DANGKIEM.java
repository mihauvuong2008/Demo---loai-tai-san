package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.CHUKY_DANGKIEM;

public class query_Delete_CHUKY_DANGKIEM {

	private static final Log log = LogFactory.getLog(query_Delete_CHUKY_DANGKIEM.class);

	public String getString_XoaCHUKY_DANGKIEM(CHUKY_DANGKIEM cd) {
		try {
			String result = "DELETE FROM CHUKY_DANGKIEM  WHERE MA_KYHAN_DANGKIEM='" + cd.getMA_KYHAN_DANGKIEM() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
