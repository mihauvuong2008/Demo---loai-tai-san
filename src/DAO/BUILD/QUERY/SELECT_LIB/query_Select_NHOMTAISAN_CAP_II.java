package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

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

	public String getString_NhomVohinh() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_II WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacbiet() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_II WHERE PHAN_NHOMTAISAN ='"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacthu() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_II WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}
}
