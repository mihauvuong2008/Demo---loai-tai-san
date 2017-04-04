package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LOAITAISAN_CAP_III;

public class query_Update_LOAITAISAN_CAP_III {

	public String getString_Capnhat_LoaiTaisanCapIII(LOAITAISAN_CAP_III n) {
		try {
			return "UPDATE LOAITAISAN_CAP_III SET TEN_LOAITAISAN_CAP_III='" + n.getTEN_LOAITAISAN_CAP_III()
					+ "', MA_LOAITAISAN_CAP_II='" + n.getMA_LOAITAISAN_CAP_II() + "'  WHERE MA_LOAITAISAN_CAP_III='"
					+ n.getMA_LOAITAISAN_CAP_III() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
