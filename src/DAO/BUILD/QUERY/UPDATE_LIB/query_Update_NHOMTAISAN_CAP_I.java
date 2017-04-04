package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOMTAISAN_CAP_I;

public class query_Update_NHOMTAISAN_CAP_I {

	public String getString_Capnhat_NhomTaisanCapI(NHOMTAISAN_CAP_I l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_I SET TEN_NHOMTAISAN_CAP_I='" + l.getTEN_NHOMTAISAN_CAP_I()
					+ "' WHERE MA_NHOMTAISAN_CAP_I='" + l.getMA_NHOMTAISAN_CAP_I() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
