package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.PHUONGTIEN_GIAOTHONG;

public class query_Update_PHUONGTIEN_GIAOTHONG {

	public String getString_Capnhat_SoKm(String maptgt, Integer valueOf) {

		try {
			return "UPDATE PHUONGTIEN_GIAOTHONG SET SO_KM_XE='" + valueOf + "'" + " where MA_PHUONGTIEN_GIAOTHONG='"
					+ maptgt + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_LoaiNhienlieu(String ma_PHUONGTIEN_GIAOTHONG) {
		try {
			return "SELECT XANG_DAU FROM PHUONGTIEN_GIAOTHONG WHERE  MA_PHUONGTIEN_GIAOTHONG ='"
					+ ma_PHUONGTIEN_GIAOTHONG + "';";
		} catch (Exception e) {
			return null;
		}

	}

	public String getString_update_PHUONGTIEN_GIAOTHONG(PHUONGTIEN_GIAOTHONG phuongtien_Giaothong) {
		try {
			return "UPDATE PHUONGTIEN_GIAOTHONG SET TEN_PHUONGTIEN_GIAOTHONG = '"
					+ phuongtien_Giaothong.getTEN_PHUONGTIEN_GIAOTHONG() + "', LOAI_PHUONGTIEN_GIAOTHONG = '"
					+ phuongtien_Giaothong.getLOAI_PHUONGTIEN_GIAOTHONG() + "',  MA_LOAI_XE ='"
					+ phuongtien_Giaothong.getMA_LOAI_XE() + "', BIENSO = '" + phuongtien_Giaothong.getBIENSO()
					+ "', SOKHUNG = '" + phuongtien_Giaothong.getSOKHUNG() + "', SOMAY = '"
					+ phuongtien_Giaothong.getSOMAY() + "', SO_KM_XE = '" + phuongtien_Giaothong.getSO_KM_XE()
					+ "', THOIHAN_DANGKIEM='" + phuongtien_Giaothong.getTHOIHAN_DANGKIEM() + "', MA_TAISAN = '"
					+ phuongtien_Giaothong.getMA_TAISAN() + "', XANG_DAU = '" + phuongtien_Giaothong.getXANG_DAU()
					+ "',  MA_KYHAN_DANGKIEM ='" + phuongtien_Giaothong.getMA_KYHAN_DANGKIEM()
					+ "'  where MA_PHUONGTIEN_GIAOTHONG='" + phuongtien_Giaothong.getMA_PHUONGTIEN_GIAOTHONG() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
