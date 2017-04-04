package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_LOAITAISAN_CAP_III {

	public String getString_Tatca_LoaiTaisanCapIII() {
		try {
			return "SELECT MA_LOAITAISAN_CAP_III, TEN_LOAITAISAN_CAP_III, MA_LOAITAISAN_CAP_II  FROM LOAITAISAN_CAP_III";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_LoaiTaisanCapIII(int mA_LOAITAISAN_CAP_III) {
		try {
			return "SELECT MA_LOAITAISAN_CAP_III, TEN_LOAITAISAN_CAP_III, MA_LOAITAISAN_CAP_II FROM LOAITAISAN_CAP_III WHERE MA_LOAITAISAN_CAP_III = '"
					+ mA_LOAITAISAN_CAP_III + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
