package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Insert_NHOMTAISAN_CAP_III {

	public String getString_ThemNhomTaisanCapIII(NHOMTAISAN_CAP_III l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_III() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_III (MA_NHOMTAISAN_CAP_III, TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_III() + "', '" + l.getTEN_NHOMTAISAN_CAP_III() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_II() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_III (TEN_NHOMTAISAN_CAP_III, MA_NHOMTAISAN_CAP_II ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_III() + "', '" + l.getMA_NHOMTAISAN_CAP_II() + "' );";
			}

		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Vohinh(int NhomCapII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_III (TEN_NHOMTAISAN_CAP_III, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_II ) VALUES( 'Nhóm tài sản cố định Vô hình', '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "', '" + NhomCapII + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacbiet(int NhomCapII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_III (TEN_NHOMTAISAN_CAP_III, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_II ) VALUES( 'Nhóm tài sản cố định Đặc biệt', '"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "', '" + NhomCapII + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacthu(int NhomCapII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_III (TEN_NHOMTAISAN_CAP_III, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_II ) VALUES( 'Nhóm tài sản cố định Đặc thù', '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "', '" + NhomCapII + "' );";
		} catch (Exception e) {
			return null;
		}
	}
}
