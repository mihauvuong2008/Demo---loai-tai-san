package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOM_DONVI_THAMGIA_THONGKE;

public class query_Delete_NHOM_DONVI_THAMGIA_THONGKE {

	private static Log log = LogFactory.getLog(query_Delete_NHOM_DONVI_THAMGIA_THONGKE.class);

	public String getString_Delete_NhomDonvi_Thamgia_Thongke(NHOM_DONVI_THAMGIA_THONGKE r) {
		try {
			String result = "DELETE FROM NHOM_DONVI_THAMGIA_THONGKE where MA_NHOM_DONVI_THAMGIA_THONGKE='"
					+ r.getMA_NHOM_DONVI_THAMGIA_THONGKE() + "'";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
