package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.DE_XUAT;

public class query_Delete_DEXUAT {

	private static final Log log = LogFactory.getLog(query_Delete_DEXUAT.class);

	public String getString_XoaDexuat(DE_XUAT d) {
		try {
			String result = "DELETE FROM DE_XUAT  WHERE MA_DE_XUAT='" + d.getMA_DE_XUAT() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
