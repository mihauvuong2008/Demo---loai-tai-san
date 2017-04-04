package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.LOAITAISAN_CAP_II;

public class query_Insert_LOAITAISAN_CAP_II {

	public String getString_Them_LoaiTaisanCapII(LOAITAISAN_CAP_II n) {
		try {
			if (n.getMA_LOAITAISAN_CAP_II() != 0) {
				return "INSERT INTO LOAITAISAN_CAP_II (MA_LOAITAISAN_CAP_II, TEN_LOAITAISAN_CAP_II , MA_LOAITAISAN_CAP_I) VALUES('"
						+ n.getMA_LOAITAISAN_CAP_II() + "', '" + n.getTEN_LOAITAISAN_CAP_II() + "','"
						+ n.getMA_LOAITAISAN_CAP_I() + "' );";
			} else {
				return "INSERT INTO LOAITAISAN_CAP_II (TEN_LOAITAISAN_CAP_II , MA_LOAITAISAN_CAP_I) VALUES( '"
						+ n.getTEN_LOAITAISAN_CAP_II() + "','" + n.getMA_LOAITAISAN_CAP_I() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

}
