package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUONGIAM;

public class query_Update_NGUONGIAM {

	public String getString_Capnhat_Nguongiam(NGUONGIAM nt) {
		try {
			return "UPDATE NGUONGIAM SET TEN_NGUONGIAM='" + nt.getTEN_NGUONGIAM() + "', GIOI_THIEU ='"
					+ nt.getGIOI_THIEU() + "', LIEN_HE='" + nt.getLIEN_HE() + "'" + " where MA_NGUONGIAM='"
					+ nt.getMA_NGUONGIAM() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
