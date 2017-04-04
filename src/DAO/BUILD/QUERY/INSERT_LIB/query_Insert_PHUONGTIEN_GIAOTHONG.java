package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.PHUONGTIEN_GIAOTHONG;
import View.DateTime.MyDateFormat;

public class query_Insert_PHUONGTIEN_GIAOTHONG {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_ThemPhuongtienGiaothong(PHUONGTIEN_GIAOTHONG p) {
		try {
			return "INSERT INTO PHUONGTIEN_GIAOTHONG (MA_PHUONGTIEN_GIAOTHONG, "
					+ " TEN_PHUONGTIEN_GIAOTHONG, LOAI_PHUONGTIEN_GIAOTHONG, MA_LOAI_XE, BIENSO, SOKHUNG, "
					+ "SOMAY, SO_KM_XE, XANG_DAU, THOIHAN_DANGKIEM, MA_TAISAN ) VALUES( '"
					+ p.getMA_PHUONGTIEN_GIAOTHONG() + "', '" + p.getTEN_PHUONGTIEN_GIAOTHONG() + "', '"
					+ p.getLOAI_PHUONGTIEN_GIAOTHONG() + "','" + p.getMA_LOAI_XE() + "','" + p.getBIENSO() + "','"
					+ p.getSOKHUNG() + "','" + p.getSOMAY() + "','" + p.getSO_KM_XE() + "' , '" + p.getXANG_DAU()
					+ "', '" + mdf.getSQLStringDate(p.getTHOIHAN_DANGKIEM()) + "','" + p.getMA_TAISAN() + "');";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
