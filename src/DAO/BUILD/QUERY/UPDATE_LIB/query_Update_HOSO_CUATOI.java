package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.HOSO_TAILEN;

public class query_Update_HOSO_CUATOI {

	public String getString_Update_HOSO_CUATOI(HOSO_TAILEN r) {
		if (r == null)
			return null;
		return "UPDATE HOSO_CUATOI SET TEN_TAI_KHOAN = '" + r.getTEN_TAI_KHOAN() + "' ";
	}

}
