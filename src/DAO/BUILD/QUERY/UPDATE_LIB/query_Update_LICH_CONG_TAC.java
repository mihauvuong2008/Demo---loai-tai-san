package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LICH_CONG_TAC;
import View.DateTime.MyDateFormat;

public class query_Update_LICH_CONG_TAC {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_Update_LICH_CONG_TAC(LICH_CONG_TAC r) {
		try {
			return "UPDATE LICH_CONG_TAC  SET NOIDUNG = '" + r.getNOIDUNG() + "', THOIGIAN='"
					+ mdf.getSQLStringDateTime(r.getTHOIGIAN()) + "' WHERE MA_LICH_CONG_TAC='" + r.getMA_LICH_CONG_TAC()
					+ "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
