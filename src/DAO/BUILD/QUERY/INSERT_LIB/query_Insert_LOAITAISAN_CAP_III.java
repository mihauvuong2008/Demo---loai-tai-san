package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.LOAITAISAN_CAP_III;

public class query_Insert_LOAITAISAN_CAP_III {

	public String getString_Them_LoaiTaisanCapIII(LOAITAISAN_CAP_III n) {
		try {
			if (n.getMA_LOAITAISAN_CAP_III() != 0) {
				return "INSERT INTO LOAITAISAN_CAP_III (MA_LOAITAISAN_CAP_III, TEN_LOAITAISAN_CAP_III , MA_LOAITAISAN_CAP_II) VALUES('"
						+ n.getMA_LOAITAISAN_CAP_III() + "', '" + n.getTEN_LOAITAISAN_CAP_III() + "','"
						+ n.getMA_LOAITAISAN_CAP_II() + "' );";
			} else {
				return "INSERT INTO LOAITAISAN_CAP_III (TEN_LOAITAISAN_CAP_III , MA_LOAITAISAN_CAP_II) VALUES( '"
						+ n.getTEN_LOAITAISAN_CAP_III() + "','" + n.getMA_LOAITAISAN_CAP_II() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}
}
