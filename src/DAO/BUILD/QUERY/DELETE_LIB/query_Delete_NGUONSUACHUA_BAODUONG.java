package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NGUONSUACHUA_BAODUONG;

public class query_Delete_NGUONSUACHUA_BAODUONG {

	private static Log log = LogFactory.getLog(query_Delete_NGUONSUACHUA_BAODUONG.class);

	public String getString_XoaNguonSuachua_Baoduong(NGUONSUACHUA_BAODUONG ng) {
		try {
			return "DELETE FROM NGUONSUACHUA_BAODUONG  WHERE MA_NGUONSUACHUA_BAODUONG='"
					+ ng.getMA_NGUONSUACHUA_BAODUONG() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
