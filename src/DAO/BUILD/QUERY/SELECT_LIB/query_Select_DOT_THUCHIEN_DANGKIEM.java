package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.PHUONGTIEN_GIAOTHONG;

public class query_Select_DOT_THUCHIEN_DANGKIEM {

	public String getString_Select_AllDOT_THUCHIEN_DANGKIEM() {
		try {
			return "SELECT * " + "FROM DOT_THUCHIEN_DANGKIEM  ORDER BY DATE(NGAY_THUCHIEN) DESC;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_AllDOT_THUCHIEN_DANGKIEM(String key) {
		try {
			return "SELECT * " + "FROM DOT_THUCHIEN_DANGKIEM WHERE MA_PHUONGTIEN_GIAOTHONG ='" + key
					+ "' ORDER BY DATE(NGAY_THUCHIEN) DESC;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_DOT_THUCHIEN_DANGKIEM_GANNHAT(PHUONGTIEN_GIAOTHONG pg) {
		try {
			return "SELECT * " + "FROM DOT_THUCHIEN_DANGKIEM WHERE MA_PHUONGTIEN_GIAOTHONG ='"
					+ pg.getMA_PHUONGTIEN_GIAOTHONG() + "' ORDER BY   DATE(NGAY_THUCHIEN) DESC  LIMIT 1;";

		} catch (Exception e) {
			return null;
		}
	}

}
