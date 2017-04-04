package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.TAISAN;

public class query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN {

	private static Log log = LogFactory.getLog(query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN.class);

	public String getString_Delete_Danhsach_Taisan_Suachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb) {
		try {
			String result = "DELETE FROM DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG ='"
					+ vIEW_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "' ;";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Taisan_DotSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, TAISAN t) {
		try {
			return "DELETE FROM DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG ='"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "' AND MA_TAISAN='" + t.getMA_TAISAN() + "' ;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
