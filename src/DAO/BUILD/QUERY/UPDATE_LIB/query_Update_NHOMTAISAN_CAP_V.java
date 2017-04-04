package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOMTAISAN_CAP_V;

public class query_Update_NHOMTAISAN_CAP_V {

	public String getString_Capnhat_NHomTaisanCapV(NHOMTAISAN_CAP_V l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_V SET TEN_NHOMTAISAN_CAP_V='" + l.getTEN_NHOMTAISAN_CAP_V()
					+ "', MA_NHOMTAISAN_CAP_IV='" + l.getMA_NHOMTAISAN_CAP_IV() + "' WHERE MA_NHOMTAISAN_CAP_V='"
					+ l.getMA_NHOMTAISAN_CAP_V() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
