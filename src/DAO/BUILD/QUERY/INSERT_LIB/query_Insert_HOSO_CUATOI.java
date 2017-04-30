package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.HOSO_TAILEN;
import View.DateTime.MyDateFormat;

public class query_Insert_HOSO_CUATOI {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_insert_HOSO_TAILEN(HOSO_TAILEN r) {
		return "INSERT INTO HOSO_TAILEN (  TEN_TAI_KHOAN, MA_TAPHOSO )VALUES( '" + r.getTEN_TAI_KHOAN() + "', '"
				+ r.getMA_TAPHOSO() + "')";
	}

}
