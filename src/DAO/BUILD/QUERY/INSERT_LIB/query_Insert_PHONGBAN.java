package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.PHONGBAN;

public class query_Insert_PHONGBAN {

	public String getString_ThemPhongban(PHONGBAN p) {
		try {
			return "INSERT INTO PHONGBAN (TEN_PHONGBAN, GIOI_THIEU_PHONGBAN) VALUES( '" + p.getTEN_PHONGBAN() + "', '"
					+ p.getGIOI_THIEU_PHONGBAN() + "');";
		} catch (Exception e) {
			return null;
		}
	}
}
