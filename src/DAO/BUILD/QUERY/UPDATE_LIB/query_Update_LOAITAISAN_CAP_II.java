package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LOAITAISAN_CAP_II;

public class query_Update_LOAITAISAN_CAP_II {

	public String getString_Capnhat_LoaiTaisanCapII(LOAITAISAN_CAP_II n) {
		// TODO Auto-generated method stub
		try {
			return "UPDATE LOAITAISAN_CAP_II SET TEN_LOAITAISAN_CAP_II='" + n.getTEN_LOAITAISAN_CAP_II()
					+ "', MA_LOAITAISAN_CAP_I='" + n.getMA_LOAITAISAN_CAP_I() + "'  WHERE MA_LOAITAISAN_CAP_II='"
					+ n.getMA_LOAITAISAN_CAP_II() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
