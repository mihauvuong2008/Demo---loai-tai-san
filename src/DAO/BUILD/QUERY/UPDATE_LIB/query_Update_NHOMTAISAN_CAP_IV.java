package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOMTAISAN_CAP_IV;

public class query_Update_NHOMTAISAN_CAP_IV {

	public String getString_Capnhat_NhomTaisanCapIV(NHOMTAISAN_CAP_IV l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_IV SET TEN_NHOMTAISAN_CAP_IV='" + l.getTEN_NHOMTAISAN_CAP_IV()
					+ "' WHERE MA_NHOMTAISAN_CAP_III='" + l.getMA_NHOMTAISAN_CAP_III() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
