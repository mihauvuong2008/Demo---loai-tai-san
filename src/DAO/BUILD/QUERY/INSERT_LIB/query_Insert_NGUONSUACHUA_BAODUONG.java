package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUONSUACHUA_BAODUONG;

public class query_Insert_NGUONSUACHUA_BAODUONG {

	public String getString_ThemNguonSuachuaBaoduong(NGUONSUACHUA_BAODUONG nt) {
		try {
			return "INSERT INTO NGUONSUACHUA_BAODUONG (TEN_NGUONSUACHUA_BAODUONG, GIOI_THIEU, LIEN_HE) VALUES( '"
					+ nt.getTEN_NGUONSUACHUA_BAODUONG() + "', '" + nt.getGIOI_THIEU() + "', '" + nt.getLIEN_HE()
					+ "');";
		} catch (Exception e) {
			return null;
		}
	}

}
