package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_I;

public class query_Insert_NHOMTAISAN_CAP_I {

	public String getString_ThemNhomTaisan(NHOMTAISAN_CAP_I l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_I() != 0)
				return "INSERT INTO NHOMTAISAN_CAP_I (MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_I() + "', '" + l.getTEN_NHOMTAISAN_CAP_I() + "' );";
			else {
				return "INSERT INTO NHOMTAISAN_CAP_I (TEN_NHOMTAISAN_CAP_I ) VALUES( '" + l.getTEN_NHOMTAISAN_CAP_I()
						+ "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

}
