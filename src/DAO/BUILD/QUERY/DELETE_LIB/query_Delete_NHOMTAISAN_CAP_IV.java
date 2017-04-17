package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Delete_NHOMTAISAN_CAP_IV {

	private static Log log = LogFactory.getLog(query_Delete_NHOMTAISAN_CAP_I.class);

	public String getString_Xoa(NHOMTAISAN_CAP_IV l) {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_IV  WHERE MA_NHOMTAISAN_CAP_IV='" + l.getMA_NHOMTAISAN_CAP_IV()
					+ "' AND PHAN_NHOMTAISAN ='" + NHOMTAISAN_CAP_IV.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_All() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN ='" + NHOMTAISAN_CAP_IV.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Vohinh() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Dacthu() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Delete_Dacbiet() {
		try {
			return "DELETE FROM NHOMTAISAN_CAP_IV WHERE PHAN_NHOMTAISAN ='" + NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN
					+ "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
