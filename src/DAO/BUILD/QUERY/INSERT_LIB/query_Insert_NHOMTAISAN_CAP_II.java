package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_II;

public class query_Insert_NHOMTAISAN_CAP_II {

	public String getString_ThemNhomTaisanCapII(NHOMTAISAN_CAP_II l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_II() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_II (MA_NHOMTAISAN_CAP_II, TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_II() + "', '" + l.getTEN_NHOMTAISAN_CAP_II() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_I() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_II (TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_II() + "', '" + l.getMA_NHOMTAISAN_CAP_I() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

}
