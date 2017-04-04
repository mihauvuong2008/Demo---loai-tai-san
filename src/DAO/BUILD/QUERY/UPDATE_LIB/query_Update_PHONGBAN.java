package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.PHONGBAN;

public class query_Update_PHONGBAN {

	public String getString_CapnhatPhongban(PHONGBAN p) {

		try {
			return "UPDATE PHONGBAN SET TEN_PHONGBAN  ='" + p.getTEN_PHONGBAN() + "', GIOI_THIEU_PHONGBAN='"
					+ p.getGIOI_THIEU_PHONGBAN() + "' where MA_PHONGBAN='" + p.getMA_PHONGBAN() + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
