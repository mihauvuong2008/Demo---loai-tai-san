package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Insert_NHOMTAISAN_CAP_I {

	public String getString_ThemNhomTaisan(NHOMTAISAN_CAP_I l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_I() != 0)
				return "INSERT INTO NHOMTAISAN_CAP_I (MA_NHOMTAISAN_CAP_I, TEN_NHOMTAISAN_CAP_I ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_I() + "', '" + l.getTEN_NHOMTAISAN_CAP_I() + "' );";
			else {
				return "INSERT INTO NHOMTAISAN_CAP_I (TEN_NHOMTAISAN_CAP_I ) VALUES( '" + l.getTEN_NHOMTAISAN_CAP_I()
						+ "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Vohinh() {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_I (TEN_NHOMTAISAN_CAP_I, PHAN_NHOMTAISAN) VALUES( 'Nhóm tài sản cố định Vô hình', '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacbiet() {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_I (TEN_NHOMTAISAN_CAP_I, PHAN_NHOMTAISAN) VALUES( 'Nhóm tài sản cố định Đặc biệt', '"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacthu() {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_I (TEN_NHOMTAISAN_CAP_I, PHAN_NHOMTAISAN) VALUES( 'Nhóm tài sản cố định Đặc thù', '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "' );";
		} catch (Exception e) {
			return null;
		}
	}
}
