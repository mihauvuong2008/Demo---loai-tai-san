package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import View.DateTime.MyDateFormat;

public class query_Select_SYSTEM_LOG {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_All_SYSTEM_LOG(Date begin, Date end) {
		try {
			return "SELECT * FROM SYSTEM_LOG WHERE THOIGIAN > '" + mdf.getSQLStringDate(begin) + "' AND  THOIGIAN < '"
					+ mdf.getSQLStringDate(end) + "' ";
		} catch (Exception e) {
			return null;
		}
	}

}
