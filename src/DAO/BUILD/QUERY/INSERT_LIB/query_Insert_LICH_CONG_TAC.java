package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.LICH_CONG_TAC;
import View.DateTime.MyDateFormat;

public class query_Insert_LICH_CONG_TAC {
	MyDateFormat mdf = new MyDateFormat();

	public String get_String_InsertLICH_CONG_TAC(LICH_CONG_TAC cd) {
		try {
			String result = "INSERT INTO LICH_CONG_TAC " + "(NOIDUNG, THOIGIAN )VALUES( '" + cd.getNOIDUNG() + "', '"
					+ mdf.getSQLStringDateTime(cd.getTHOIGIAN()) + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
