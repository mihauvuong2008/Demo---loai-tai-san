package DAO.BUILD.QUERY.DELETE_LIB;

import DAO.HOSO_TAILEN;

public class query_Delete_HOSO_CUATOI {

	public String getString_remove_HOSO_CUATOI(HOSO_TAILEN i) {
		if (i == null)
			return null;
		return "DELETE FROM HOSO_CUATOI WHERE MA_HOSO_CUATOI= '" + i.getMA_HOSO_TAILEN() + "'";
	}

}
