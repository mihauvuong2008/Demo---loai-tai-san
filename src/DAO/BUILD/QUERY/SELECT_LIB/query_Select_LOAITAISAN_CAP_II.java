package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_LOAITAISAN_CAP_II {

	public String getString_Tatca_LoaiTaisanCapII() {
		try {
			return "SELECT MA_LOAITAISAN_CAP_II, TEN_LOAITAISAN_CAP_II, MA_LOAITAISAN_CAP_I  FROM LOAITAISAN_CAP_II";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_LoaiTaisanCapII(int ma_NHOMTAISAN) {
		try {
			return "SELECT MA_LOAITAISAN_CAP_II, TEN_LOAITAISAN_CAP_II, MA_LOAITAISAN_CAP_I  FROM NHOMTAISAN WHERE ma_LOAITAISAN_CAP_II='"
					+ ma_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
