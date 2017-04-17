package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Delete_NHOMTAISAN_CAP_I {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_I.class);

	public String getString_XoaNHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I  WHERE MA_NHOMTAISAN_CAP_I='" + l.getMA_NHOMTAISAN_CAP_I() + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getDelete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN = '" + NHOMTAISAN_CAP_I.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Vohinh() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Dacthu() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Dacbiet() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_I WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
