package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;

public class query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG {

	private static Log log = LogFactory.getLog(query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG.class);

	public String getString_delete_DotSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			String result = "DELETE FROM DOT_THUCHIEN_SUACHUA_BAODUONG  WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG='"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
