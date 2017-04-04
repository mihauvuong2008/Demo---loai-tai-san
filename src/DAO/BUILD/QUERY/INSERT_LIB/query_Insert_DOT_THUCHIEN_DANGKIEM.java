package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_DANGKIEM;
import View.DateTime.MyDateFormat;

public class query_Insert_DOT_THUCHIEN_DANGKIEM {

	public String get_String_InsertDOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM cd) {
		try {
			MyDateFormat mdf = new MyDateFormat();
			String result = "INSERT INTO DOT_THUCHIEN_DANGKIEM "
					+ "(NGAY_THUCHIEN, MA_TAPHOSO, MA_PHUONGTIEN_GIAOTHONG, GHI_CHU)VALUES( '"
					+ mdf.getSQLStringDate(cd.getNGAY_THUCHIEN()) + "', '" + cd.getMA_TAPHOSO() + "', '"
					+ cd.getMA_PHUONGTIEN_GIAOTHONG() + "', '" + cd.getGHI_CHU() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
