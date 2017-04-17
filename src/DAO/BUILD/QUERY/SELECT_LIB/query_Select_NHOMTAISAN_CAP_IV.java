package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

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

	public String getString_NhomVohinh() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacbiet() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN ='"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacthu() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
