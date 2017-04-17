package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

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

	public String getString_NhomVohinh() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_III WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacbiet() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_III WHERE PHAN_NHOMTAISAN ='"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacthu() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_III WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}
}
