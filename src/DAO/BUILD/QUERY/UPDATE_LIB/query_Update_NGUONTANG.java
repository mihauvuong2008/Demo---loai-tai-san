package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUONTANG;

public class query_Update_NGUONTANG {

	public String getString_Capnhat_Nguontang(NGUONTANG nt) {
		try {
			return "UPDATE NGUONTANG SET TEN_NGUONTANG='" + nt.getTEN_NGUONTANG() + "', GIOI_THIEU ='"
					+ nt.getGIOI_THIEU() + "', LIEN_HE='" + nt.getLIEN_HE() + "'" + " where MA_NGUONTANG='"
					+ nt.getMA_NGUONTANG() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
