package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Select_NHOM_TAISANCODINH {

	public String getString_Tatca_NHOM_TAISANCODINH_DACBIET() {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN='"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NHOM_TAISANCODINH_DACBIET(int ma_NHOM_TAISANCODINH_DACBIET) {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V= " + ma_NHOM_TAISANCODINH_DACBIET
					+ " AND PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NHOM_TAISANCODINH_DACTHU(int ma_NHOM_TAISANCODINH_DACTHU) {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V  WHERE MA_NHOMTAISAN_CAP_V= " + ma_NHOM_TAISANCODINH_DACTHU
					+ " AND PHAN_NHOMTAISAN = '" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Tatca_NHOM_TAISANCODINH_DACTHU() {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NHOM_TAISANCODINH_VOHINH(int ma_NHOM_TAISANCODINH_VOHINH) {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V  WHERE MA_NHOMTAISAN_CAP_V = " + ma_NHOM_TAISANCODINH_VOHINH
					+ " AND PHAN_NHOMTAISAN = '" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Tatca_NHOM_TAISANCODINH_VOHINH() {
		try {
			return "SELECT * " + "FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN = '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
