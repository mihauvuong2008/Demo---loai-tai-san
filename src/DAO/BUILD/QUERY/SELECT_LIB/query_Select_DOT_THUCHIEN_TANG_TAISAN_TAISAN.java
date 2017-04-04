package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.TAISAN;

public class query_Select_DOT_THUCHIEN_TANG_TAISAN_TAISAN {

	public String getString_set_DOTTANGTAISAN_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt, TAISAN t) {
		try {
			return "INSERT INTO TAISAN_DOT_THUCHIEN_TANG_TAISAN ( MA_TAISAN, MA_DOT_TANG) VALUES( '" + t.getMA_TAISAN()
					+ "','" + dt.getMA_DOT_TANG() + "'  );";
		} catch (Exception e) {
			return null;
		}
	}

}
