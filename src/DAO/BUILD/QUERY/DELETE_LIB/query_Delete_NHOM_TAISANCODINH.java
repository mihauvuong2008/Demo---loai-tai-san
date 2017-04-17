package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Delete_NHOM_TAISANCODINH {

	private static final Log log = LogFactory.getLog(query_Delete_NHOM_TAISANCODINH.class);

	public String getDelete_All_NHOMTAISAN_CODINH_DACBIET() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN='" + NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN
					+ "' ;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_XoaNHOM_TAISANCODINH_DACBIET(NHOM_TAISANCODINH_DACBIET l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_DACBIET()
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All_NHOM_TAISANCODINH_DACTHU() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN='" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_XoaNHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_DACTHU()
					+ "'AND PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "' ;";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All_NHOM_TAISANCODINH_VOHINH() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE PHAN_NHOMTAISAN='" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_XoaNHOM_TAISANCODINH_VOHINH(NHOM_TAISANCODINH_VOHINH l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_V WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_VOHINH()
					+ "' AND PHAN_NHOMTAISAN='" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
