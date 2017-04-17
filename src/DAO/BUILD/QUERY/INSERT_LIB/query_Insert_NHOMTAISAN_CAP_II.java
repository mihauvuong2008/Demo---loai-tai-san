package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Insert_NHOMTAISAN_CAP_II {

	public String getString_ThemNhomTaisanCapII(NHOMTAISAN_CAP_II l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_II() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_II (MA_NHOMTAISAN_CAP_II, TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I ) VALUES('"
						+ l.getMA_NHOMTAISAN_CAP_II() + "', '" + l.getTEN_NHOMTAISAN_CAP_II() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_I() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_II (TEN_NHOMTAISAN_CAP_II, MA_NHOMTAISAN_CAP_I ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_II() + "', '" + l.getMA_NHOMTAISAN_CAP_I() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Vohinh(int NhomCapI) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_II (TEN_NHOMTAISAN_CAP_II, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_I ) VALUES( 'Nhóm tài sản cố định Vô hình', '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "', '" + NhomCapI + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacbiet(int NhomCapI) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_II (TEN_NHOMTAISAN_CAP_II, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_I ) VALUES( 'Nhóm tài sản cố định Đặc biệt', '"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "', '" + NhomCapI + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacthu(int NhomCapI) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_II (TEN_NHOMTAISAN_CAP_II, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_I ) VALUES( 'Nhóm tài sản cố định Đặc thù', '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "', '" + NhomCapI + "' );";
		} catch (Exception e) {
			return null;
		}
	}
}
