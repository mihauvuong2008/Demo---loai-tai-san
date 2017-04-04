package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUONGIAM;

public class query_Insert_NGUONGIAM {

	public String getString_ThemNguongiam(NGUONGIAM ng) {
		try {
			return "INSERT INTO NGUONGIAM (TEN_NGUONGIAM, GIOI_THIEU, LIEN_HE) VALUES( '" + ng.getTEN_NGUONGIAM()
					+ "', '" + ng.getGIOI_THIEU() + "', '" + ng.getLIEN_HE() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
