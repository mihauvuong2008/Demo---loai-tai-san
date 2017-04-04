package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_IV;

public class query_Insert_NHOMTAISAN_CAP_IV {

	public String getString_ThemNhomTaisanCapIV(NHOMTAISAN_CAP_IV l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_IV() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_IV (MA_NHOMTAISAN_CAP_IV, TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  ) VALUES( '"
						+ l.getMA_NHOMTAISAN_CAP_IV() + "', '" + l.getTEN_NHOMTAISAN_CAP_IV() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_III() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_IV (TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_IV() + "', '" + l.getMA_NHOMTAISAN_CAP_III() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

}
