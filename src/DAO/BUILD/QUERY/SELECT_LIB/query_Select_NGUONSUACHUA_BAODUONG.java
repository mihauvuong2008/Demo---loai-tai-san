package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;

public class query_Select_NGUONSUACHUA_BAODUONG {

	public String getString_NguonSuachuaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			return "SELECT * " + " FROM NGUONSUACHUA_BAODUONG "
					+ " INNER JOIN (SELECT DOT_THUCHIEN_SUACHUA_BAODUONG.MA_NGUONSUACHUA_BAODUONG "
					+ " FROM DOT_THUCHIEN_SUACHUA_BAODUONG "
					+ " WHERE DOT_THUCHIEN_SUACHUA_BAODUONG.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = '"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "')as t1 "
					+ " ON NGUONSUACHUA_BAODUONG.MA_NGUONSUACHUA_BAODUONG = t1.MA_NGUONSUACHUA_BAODUONG;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Tatca_NguonSuachuaBaoduong(String pattern) {
		try {
			return "SELECT * " + " FROM NGUONSUACHUA_BAODUONG WHERE TEN_NGUONSUACHUA_BAODUONG LIKE '%" + pattern
					+ "%' OR LIEN_HE LIKE '%" + pattern + "%' OR GIOI_THIEU LIKE '%" + pattern + "%'";
		} catch (Exception e) {
			return null;
		}
	}
}
