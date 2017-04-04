package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUONSUACHUA_BAODUONG;

public class query_Update_NGUONSUACHUA_BAODUONG {

	public String getString_Capnhat_NguonSuachua_Baoduong(NGUONSUACHUA_BAODUONG nt) {
		try {
			return "UPDATE NGUONSUACHUA_BAODUONG SET TEN_NGUONSUACHUA_BAODUONG='" + nt.getTEN_NGUONSUACHUA_BAODUONG()
					+ "', GIOI_THIEU ='" + nt.getGIOI_THIEU() + "', LIEN_HE='" + nt.getLIEN_HE() + "'"
					+ " where MA_NGUONSUACHUA_BAODUONG='" + nt.getMA_NGUONSUACHUA_BAODUONG() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
