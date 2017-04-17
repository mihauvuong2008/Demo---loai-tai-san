package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOM_TAISANCODINH_DACBIET;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.NHOM_TAISANCODINH_VOHINH;

public class query_Update_NHOM_TAISANCODINH {

	public String getString_Capnhat_NHOM_TAISANCODINH_DACBIET(NHOM_TAISANCODINH_DACBIET l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_V  SET TEN_NHOMTAISAN_CAP_V= '" + l.getTEN_NHOM_TAISANCODINH_DACBIET()
					+ "', THOIGIAN_SUDUNG = '" + l.getTHOIGIAN_SUDUNG() + "', TILE_HAOMON = '" + l.getGIA_QUYUOC()
					+ "' WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_DACBIET() + "' AND PHAN_NHOMTAISAN='"
					+ NHOM_TAISANCODINH_DACBIET.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getString_Capnhat_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_V  SET TEN_NHOMTAISAN_CAP_V = '" + l.getTEN_NHOM_TAISANCODINH_DACTHU()
					+ "', THOIGIAN_SUDUNG = '" + l.getTHOIGIAN_SUDUNG() + "', TILE_HAOMON = '" + l.getTILE_HAOMON()
					+ "' WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_DACTHU() + "' AND PHAN_NHOMTAISAN='"
					+ NHOM_TAISANCODINH_DACTHU.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getString_Capnhat_NHOM_TAISANCODINH_VOHINH(NHOM_TAISANCODINH_VOHINH l) {
		try {
			return "UPDATE NHOMTAISAN_CAP_V  SET TEN_NHOMTAISAN_CAP_V = '" + l.getTEN_NHOM_TAISANCODINH_VOHINH()
					+ "', THOIGIAN_SUDUNG = '" + l.getTHOIGIAN_SUDUNG() + "', TILE_HAOMON = '" + l.getTILE_HAOMON()
					+ "' WHERE MA_NHOMTAISAN_CAP_V='" + l.getMA_NHOM_TAISANCODINH_VOHINH() + "' AND PHAN_NHOMTAISAN='"
					+ NHOM_TAISANCODINH_VOHINH.PHAN_NHOMTAISAN + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
