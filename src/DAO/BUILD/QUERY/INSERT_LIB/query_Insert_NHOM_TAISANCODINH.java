package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Insert_NHOM_TAISANCODINH {

	public String getString_ThemNHOM_TAISANCODINH_DACBIET(NHOM_TAISANCODINH_DACBIET l) {
		try {
			String result = "INSERT INTO NHOMTAISAN_CAP_V "
					+ "(TEN_NHOMTAISAN_CAP_V, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_IV, THOIGIAN_SUDUNG, TILE_HAOMON  )VALUES( '"
					+ l.getTEN_NHOM_TAISANCODINH_DACBIET() + "', '" + l.getMaPhannhom() + "', '" + l.getMaPhannhom()
					+ "', '" + l.getTHOIGIAN_SUDUNG() + "', '" + l.getGIA_QUYUOC() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) {
		try {
			String result = "INSERT INTO NHOMTAISAN_CAP_V "
					+ "(TEN_NHOMTAISAN_CAP_V, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_IV, THOIGIAN_SUDUNG, TILE_HAOMON )VALUES( '"
					+ l.getTEN_NHOM_TAISANCODINH_DACTHU() + "', '" + l.getMaPhannhom() + "', '" + l.getMaPhannhom()
					+ "', '" + l.getTHOIGIAN_SUDUNG() + "', '" + l.getTILE_HAOMON() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemNHOM_TAISANCODINH_VOHINH(NHOM_TAISANCODINH_VOHINH l) {
		try {
			String result = "INSERT INTO NHOMTAISAN_CAP_V "
					+ "(TEN_NHOMTAISAN_CAP_V, PHAN_NHOMTAISAN, MA_NHOMTAISAN_CAP_IV, THOIGIAN_SUDUNG, TILE_HAOMON )VALUES( '"
					+ l.getTEN_NHOM_TAISANCODINH_VOHINH() + "', '" + l.getMaPhannhom() + "', '" + l.getMaPhannhom()
					+ "', '" + l.getTHOIGIAN_SUDUNG() + "', '" + l.getTILE_HAOMON() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
