package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_III;

public class query_Insert_NHOMTAISAN_CAP_III {

	public String getString_ThemNhomTaisanCapIII(NHOMTAISAN_CAP_III l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_III() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_III (MA_NHOMTAISAN_CAP_III, TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_III() + "', '" + l.getTEN_NHOMTAISAN_CAP_III() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_II() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_III (TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_III() + "', '" + l.getMA_NHOMTAISAN_CAP_II() + "' );";
			}

		} catch (Exception e) {
			return null;
		}
	}

}
