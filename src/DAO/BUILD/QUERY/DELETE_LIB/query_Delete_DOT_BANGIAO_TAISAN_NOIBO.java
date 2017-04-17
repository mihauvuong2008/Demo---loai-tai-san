package DAO.BUILD.QUERY.DELETE_LIB;

import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.TAISAN;

public class query_Delete_DOT_BANGIAO_TAISAN_NOIBO {

	public String getString_Delete_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dgt) {
		try {
			String result = "DELETE FROM DOT_BANGIAO_TAISAN_NOIBO  WHERE MA_DOT_BANGIAO_TAISAN_NOIBO='"
					+ dgt.getMA_DOT_BANGIAO_TAISAN_NOIBO() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(TAISAN ts, DOT_BANGIAO_TAISAN_NOIBO data) {
		try {
			String result = "DELETE FROM TAISAN_DOT_BANGIAO_TAISAN_NOIBO  WHERE MA_TAISAN='" + ts.getMA_TAISAN()
					+ "' AND MA_DOT_BANGIAO_TAISAN_NOIBO='" + data.getMA_DOT_BANGIAO_TAISAN_NOIBO() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
