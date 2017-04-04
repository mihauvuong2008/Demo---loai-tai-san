package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.PHUKIEN;
import DAO.TAISAN;

public class query_Delete_TAISAN {

	private static Log log = LogFactory.getLog(query_Delete_TAISAN.class);

	public String getString_Xoa_Phukien(TAISAN t) {
		try {
			return "delete from PHUKIEN where MA_TAISAN='" + t.getMA_TAISAN() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Xoa_Phukien(PHUKIEN pk) {
		try {
			return " delete from PHUKIEN WHERE  MA_PHUKIEN='" + pk.getMA_PHUKIEN() + "'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Xoa_Taisan(TAISAN t) {
		try {
			return " delete from taisan where MA_TAISAN='" + t.getMA_TAISAN() + "'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Xoa_Taisan_Muasam(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			return " delete from taisan WHERE MA_TAISAN IN ( SELECT MA_TAISAN FROM TAISAN_DOT_THUCHIEN_TANG_TAISAN WHERE TAISAN_DOT_THUCHIEN_TANG_TAISAN.MA_DOT_TANG='"
					+ dtt.getMA_DOT_TANG() + "' )";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

}
