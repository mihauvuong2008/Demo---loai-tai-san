package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_TANG_TAISAN;

public class query_Delete_DOT_THUCHIEN_TANG_TAISAN {

	private static Log log = LogFactory.getLog(query_Delete_DOT_THUCHIEN_TANG_TAISAN.class);

	public String getString_Delete_DotTangTaisan(DOT_THUCHIEN_TANG_TAISAN dt) {
		try {
			String result = "DELETE FROM DOT_THUCHIEN_TANG_TAISAN  WHERE MA_DOT_TANG='" + dt.getMA_DOT_TANG() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
