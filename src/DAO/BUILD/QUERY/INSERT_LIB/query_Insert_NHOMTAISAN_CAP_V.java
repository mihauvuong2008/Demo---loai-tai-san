package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_V;

public class query_Insert_NHOMTAISAN_CAP_V {

	public String getString_ThemNhomTaisanCapV(NHOMTAISAN_CAP_V l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_V() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_V (MA_NHOMTAISAN_CAP_V, TEN_NHOMTAISAN_CAP_V, THOIGIAN_SUDUNG, TILE_HAOMON, MA_NHOMTAISAN_CAP_IV ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_V() + "', '" + l.getTEN_NHOMTAISAN_CAP_V() + "','"
						+ l.getTHOIGIAN_SUDUNG() + "', '" + l.getTILE_HAOMON() + "', '" + l.getMA_NHOMTAISAN_CAP_IV()
						+ "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_V (TEN_NHOMTAISAN_CAP_V, MA_NHOMTAISAN_CAP_IV ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_V() + "', '" + l.getMA_NHOMTAISAN_CAP_IV() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

}
