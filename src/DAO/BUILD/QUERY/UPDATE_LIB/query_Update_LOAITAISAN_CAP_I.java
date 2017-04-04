package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LOAITAISAN_CAP_I;

public class query_Update_LOAITAISAN_CAP_I {

	public String getString_Capnhat_LoaiTaisanCapI(LOAITAISAN_CAP_I l) {
		try {
			return "UPDATE LOAITAISAN_CAP_I SET TEN_LOAITAISAN_CAP_I='" + l.getTEN_LOAITAISAN_CAP_I()
					+ "' WHERE MA_LOAITAISAN_CAP_I='" + l.getMA_LOAITAISAN_CAP_I() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
