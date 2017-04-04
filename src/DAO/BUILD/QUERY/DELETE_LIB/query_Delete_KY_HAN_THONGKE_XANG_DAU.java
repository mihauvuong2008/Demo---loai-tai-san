package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.KY_HAN_THONGKE_XANG_DAU;

public class query_Delete_KY_HAN_THONGKE_XANG_DAU {
	private static Log log = LogFactory.getLog(query_Delete_KY_HAN_THONGKE_XANG_DAU.class);

	public String getString_Xoa_KyhanThongkeXangdau(KY_HAN_THONGKE_XANG_DAU i) {
		try {
			String result = "DELETE FROM KY_HAN_THONGKE_XANG_DAU WHERE MA_KYHAN = '" + i.getMA_KYHAN() + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
