package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Select_NHOMTAISAN_CAP_I {

	public String getString_Tatca_NhomTaisanCapI() {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN='"
					+ NHOMTAISAN_CAP_I.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomTaisancapI(int ma_NHOMTAISAN_CAP_I) {
		try {
			return "SELECT MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I  FROM NHOMTAISAN_CAP_I WHERE MA_NHOMTAISAN_CAP_I='"
					+ ma_NHOMTAISAN_CAP_I + "' AND PHAN_NHOMTAISAN ='" + NHOMTAISAN_CAP_I.PHAN_NHOMTAISAN + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomVohinh() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN = '" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN
					+ "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacbiet() {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN
					+ "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NhomDacthu(int ma_NHOMTAISAN_CAP_IV) {
		try {
			return "SELECT * FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN = '" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN
					+ "'";
		} catch (Exception e) {
			return null;
		}
	}
}
