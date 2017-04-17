package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Insert_NHOMTAISAN_CAP_IV {

	public String getString_ThemNhomTaisanCapIV(NHOMTAISAN_CAP_IV l) {
		try {
			if (l.getMA_NHOMTAISAN_CAP_IV() != 0) {
				return "INSERT INTO NHOMTAISAN_CAP_IV (MA_NHOMTAISAN_CAP_IV, TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  ) VALUES( '"
						+ l.getMA_NHOMTAISAN_CAP_IV() + "', '" + l.getTEN_NHOMTAISAN_CAP_IV() + "', '"
						+ l.getMA_NHOMTAISAN_CAP_III() + "' );";
			} else {
				return "INSERT INTO NHOMTAISAN_CAP_IV (TEN_NHOMTAISAN_CAP_IV, MA_NHOMTAISAN_CAP_III  ) VALUES( '"
						+ l.getTEN_NHOMTAISAN_CAP_IV() + "', '" + l.getMA_NHOMTAISAN_CAP_III() + "' );";
			}
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Vohinh(int NhomCapIII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_IV (TEN_NHOMTAISAN_CAP_IV, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_III ) VALUES( 'Nhóm tài sản cố định Vô hình', '"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "', '" + NhomCapIII + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacbiet(int NhomCapIII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_IV (TEN_NHOMTAISAN_CAP_IV, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_III ) VALUES( 'Nhóm tài sản cố định Đặc biệt', '"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "', '" + NhomCapIII + "' );";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNhom_Dacthu(int NhomCapIII) {
		try {
			return "INSERT INTO NHOMTAISAN_CAP_IV (TEN_NHOMTAISAN_CAP_IV, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_III ) VALUES( 'Nhóm tài sản cố định Đặc thù', '"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "', '" + NhomCapIII + "' );";
		} catch (Exception e) {
			return null;
		}
	}

}
