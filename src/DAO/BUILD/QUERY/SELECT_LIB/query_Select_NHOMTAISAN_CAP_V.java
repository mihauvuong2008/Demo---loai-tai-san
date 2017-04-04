package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOMTAISAN_CAP_V {

	public String getString_TatcaNhomTaisanCapV() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_V, TEN_NHOMTAISAN_CAP_V, MA_NHOMTAISAN_CAP_IV  FROM NHOMTAISAN_CAP_V";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisanCapV(int ma_NHOMTAISAN_CAP_V) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_V, TEN_NHOMTAISAN_CAP_V, MA_NHOMTAISAN_CAP_IV  FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='"
					+ ma_NHOMTAISAN_CAP_V + "'";
		} catch (Exception e) {
			return null;
		}

	}

}
