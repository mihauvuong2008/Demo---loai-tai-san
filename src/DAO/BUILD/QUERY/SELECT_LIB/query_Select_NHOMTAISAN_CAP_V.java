package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOMTAISAN_CAP_V;

public class query_Select_NHOMTAISAN_CAP_V {

	public String getString_TatcaNhomTaisanCapV() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN = '" + NHOMTAISAN_CAP_V.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisanCapV(int ma_NHOMTAISAN_CAP_V) {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='" + ma_NHOMTAISAN_CAP_V
					+ "' AND PHAN_NHOMTAISAN='" + NHOMTAISAN_CAP_V.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}

	}

	public String getString_PHANNhomTaisanCapV(int mA_NHOMTAISAN_CAP_V) {
		return "SELECT * FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='" + mA_NHOMTAISAN_CAP_V + "'";
	}

}
