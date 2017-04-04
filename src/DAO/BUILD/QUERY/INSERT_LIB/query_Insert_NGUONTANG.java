package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUONTANG;

public class query_Insert_NGUONTANG {

	public String getString_ThemNguontang(NGUONTANG nt) {
		try {
			return "INSERT INTO NGUONTANG (TEN_NGUONTANG, GIOI_THIEU, LIEN_HE) VALUES( '" + nt.getTEN_NGUONTANG()
					+ "', '" + nt.getGIOI_THIEU() + "', '" + nt.getLIEN_HE() + "');";
		} catch (Exception e) {
			return null;
		}
	}
}
