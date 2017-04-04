package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.LOAITAISAN_CAP_I;

public class query_Select_LOAITAISAN_CAP_I {

	public String getString_Tatca_LoaiTaisanCapI() {
		try {
			String result = "SELECT MA_LOAITAISAN_CAP_I, TEN_LOAITAISAN_CAP_I  FROM LOAITAISAN_CAP_I";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_LoaiTaisanCapI(LOAITAISAN_CAP_I l) {
		try {
			String result = null;
			if (l.getMA_LOAITAISAN_CAP_I() != 0) {
				result = "INSERT INTO LOAITAISAN_CAP_I (MA_LOAITAISAN_CAP_I, TEN_LOAITAISAN_CAP_I ) VALUES('"
						+ l.getMA_LOAITAISAN_CAP_I() + "', '" + l.getTEN_LOAITAISAN_CAP_I() + "' );";
			} else {
				result = "INSERT INTO LOAITAISAN_CAP_I (TEN_LOAITAISAN_CAP_I ) VALUES( '" + l.getTEN_LOAITAISAN_CAP_I()
						+ "' );";
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
