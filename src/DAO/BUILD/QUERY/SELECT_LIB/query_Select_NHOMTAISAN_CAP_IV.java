package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOMTAISAN_CAP_IV {

	public String getString_TatcaNhomTaisanCapIV() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_IV, TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  FROM NHOMTAISAN_CAP_IV";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisanCapIV(int ma_NHOMTAISAN_CAP_IV) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_IV, TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  FROM NHOMTAISAN_CAP_IV WHERE MA_NHOMTAISAN_CAP_IV='"
					+ ma_NHOMTAISAN_CAP_IV + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
