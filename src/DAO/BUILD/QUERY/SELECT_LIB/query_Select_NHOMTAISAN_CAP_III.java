package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOMTAISAN_CAP_III {

	public String getString_TatcaNhomTaisanCapIII() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_III, TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II  FROM NHOMTAISAN_CAP_III";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisanCapIII(int ma_NHOMTAISAN_CAP_III) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_III, TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II  FROM NHOMTAISAN_CAP_III WHERE MA_NHOMTAISAN_CAP_III='"
					+ ma_NHOMTAISAN_CAP_III + "'";
		} catch (Exception e) {
			return null;
		}

	}

}
