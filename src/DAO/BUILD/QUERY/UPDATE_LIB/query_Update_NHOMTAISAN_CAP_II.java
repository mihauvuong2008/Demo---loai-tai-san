package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOMTAISAN_CAP_II;

public class query_Update_NHOMTAISAN_CAP_II {

	public String getString_CapnhatNhomTaisanCapII(NHOMTAISAN_CAP_II l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_II SET TEN_NHOMTAISAN_CAP_II='" + l.getTEN_NHOMTAISAN_CAP_II()
					+ "', MA_NHOMTAISAN_CAP_I='" + l.getMA_NHOMTAISAN_CAP_I() + "'  WHERE MA_NHOMTAISAN_CAP_II='"
					+ l.getMA_NHOMTAISAN_CAP_II() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
