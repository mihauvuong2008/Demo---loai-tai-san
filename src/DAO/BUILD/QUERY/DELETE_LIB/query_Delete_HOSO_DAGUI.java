package DAO.BUILD.QUERY.DELETE_LIB;

import DAO.HOSO_DAGUI;

public class query_Delete_HOSO_DAGUI {

	public String getString_remove_HOSO_DAGUI(HOSO_DAGUI i) {
		return "DELETE FROM HOSO_DAGUI WHERE MA_HOSO_DAGUI='" + i.getMA_HOSO_DAGUI() + "'";
	}

}
