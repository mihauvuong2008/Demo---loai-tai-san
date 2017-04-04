package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOMTAISAN_CAP_I {

	public String getString_Tatca_NhomTaisanCapI() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_I";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisancapI(int ma_NHOMTAISAN_CAP_I) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_I WHERE MA_NHOMTAISAN_CAP_I='"
					+ ma_NHOMTAISAN_CAP_I + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
