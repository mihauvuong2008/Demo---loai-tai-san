package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.CHUKY_DANGKIEM;

public class query_Insert_CHUKY_DANGKIEM {

	public String get_String_InsertCHUKY_DANGKIEM(CHUKY_DANGKIEM cd) {
		try {
			String result = "INSERT INTO CHUKY_DANGKIEM " + "(TEN_KYHAN, CHU_KY_DAU, CHU_KY )VALUES( '"
					+ cd.getTEN_KYHAN() + "', " + cd.getCHU_KY_DAU() + ", '" + cd.getCHU_KY() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
