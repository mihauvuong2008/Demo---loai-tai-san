package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.TAISAN;

public class query_Delete_TAISAN_DOT_THUCHIEN_GIAM_TAISAN {

	private static Log log = LogFactory.getLog(query_Delete_TAISAN_DOT_THUCHIEN_GIAM_TAISAN.class);

	public String getString_Xoa_TAISAN_GIAM_TAISAN(TAISAN t, DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "DELETE FROM TAISAN_DOT_THUCHIEN_GIAM_TAISAN WHERE MA_TAISAN='" + t.getMA_TAISAN()
					+ "' AND MA_DOT_GIAM='" + dgt.getMA_DOT_GIAM() + "'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
