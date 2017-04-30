package DAO.BUILD.QUERY.DELETE_LIB;

import DAO.HOSO_DANHAN;

public class query_Delete_HOSO_DANHAN {

	public String getString_remove_HOSO_DANHAN(HOSO_DANHAN i) {
		return "DELETE FROM HOSO_DANHAN WHERE MA_HOSO_DANHAN = '" + i.getMA_HOSO_DANHAN() + "'";
	}

}
