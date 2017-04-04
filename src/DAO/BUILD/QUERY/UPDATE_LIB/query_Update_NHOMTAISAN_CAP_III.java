package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOMTAISAN_CAP_III;

public class query_Update_NHOMTAISAN_CAP_III {

	public String getString_CapnhatNhomTaisanCapIII(NHOMTAISAN_CAP_III l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_III SET TEN_NHOMTAISAN_CAP_III='" + l.getTEN_NHOMTAISAN_CAP_III()
					+ "', MA_NHOMTAISAN_CAP_II = '" + l.getMA_NHOMTAISAN_CAP_II() + "' WHERE MA_NHOMTAISAN_CAP_III='"
					+ l.getMA_NHOMTAISAN_CAP_III() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
