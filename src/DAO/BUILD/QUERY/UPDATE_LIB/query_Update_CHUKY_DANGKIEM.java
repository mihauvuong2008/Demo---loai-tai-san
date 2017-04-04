package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.CHUKY_DANGKIEM;

public class query_Update_CHUKY_DANGKIEM {

	public String getString_Update_CHUKY_DANGKIEM(CHUKY_DANGKIEM r) {
		try {
			return "UPDATE CHUKY_DANGKIEM  SET TEN_KYHAN = " + r.getTEN_KYHAN() + ", CHU_KY_DAU='" + r.getCHU_KY_DAU()
					+ "', CHU_KY = '" + r.getCHU_KY() + "' WHERE MA_KYHAN_DANGKIEM='" + r.getMA_KYHAN_DANGKIEM() + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
