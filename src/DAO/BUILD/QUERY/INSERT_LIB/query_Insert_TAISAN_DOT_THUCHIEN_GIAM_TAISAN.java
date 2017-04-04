package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.TAISAN;

public class query_Insert_TAISAN_DOT_THUCHIEN_GIAM_TAISAN {

	public String getString_Them_TAISAN_GIAM_TAISAN(TAISAN t, DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "INSERT INTO TAISAN_DOT_THUCHIEN_GIAM_TAISAN ( MA_TAISAN, MA_DOT_GIAM) VALUES( '" + t.getMA_TAISAN()
					+ "','" + dgt.getMA_DOT_GIAM() + "'  );";
		} catch (Exception e) {
			return null;
		}
	}

}
