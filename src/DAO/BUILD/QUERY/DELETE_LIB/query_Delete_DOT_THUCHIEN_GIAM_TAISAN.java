package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;

public class query_Delete_DOT_THUCHIEN_GIAM_TAISAN {

	private static Log log = LogFactory.getLog(query_Delete_DOT_THUCHIEN_GIAM_TAISAN.class);

	public String getString_Delete_Dot_GiamTaisan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			String result = "DELETE FROM DOT_THUCHIEN_GIAM_TAISAN  WHERE MA_DOT_GIAM='" + dgt.getMA_DOT_GIAM() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
