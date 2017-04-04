package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOMTAISAN_CAP_II {

	public String getString_TatcaNhomTaisancapII() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_II, TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_II";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisanCapII(int ma_NHOMTAISAN_CAP_II) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_II, TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_II WHERE MA_NHOMTAISAN_CAP_II='"
					+ ma_NHOMTAISAN_CAP_II + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
