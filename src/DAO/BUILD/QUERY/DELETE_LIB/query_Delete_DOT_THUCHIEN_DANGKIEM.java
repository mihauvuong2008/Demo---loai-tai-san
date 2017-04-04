package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_DANGKIEM;

public class query_Delete_DOT_THUCHIEN_DANGKIEM {

	private static final Log log = LogFactory.getLog(query_Delete_DOT_THUCHIEN_DANGKIEM.class);

	public String getString_XoaDOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM cd) {
		try {
			String result = "DELETE FROM DOT_THUCHIEN_DANGKIEM  WHERE MA_DOT_THUCHIEN_DANGKIEM='"
					+ cd.getMA_DOT_THUCHIEN_DANGKIEM() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
