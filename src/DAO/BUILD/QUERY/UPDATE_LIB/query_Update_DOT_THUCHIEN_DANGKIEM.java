package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_THUCHIEN_DANGKIEM;
import View.DateTime.MyDateFormat;

public class query_Update_DOT_THUCHIEN_DANGKIEM {

	public String getString_Update_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM r) {
		try {
			MyDateFormat mdf = new MyDateFormat();
			return "UPDATE DOT_THUCHIEN_DANGKIEM  SET MA_DOT_THUCHIEN_DANGKIEM= " + r.getMA_DOT_THUCHIEN_DANGKIEM()
					+ ", NGAY_THUCHIEN='" + mdf.getSQLStringDate(r.getNGAY_THUCHIEN()) + "', MA_TAPHOSO = '"
					+ r.getMA_TAPHOSO() + "', MA_PHUONGTIEN_GIAOTHONG = '" + r.getMA_PHUONGTIEN_GIAOTHONG()
					+ "', GHI_CHU = '" + r.getGHI_CHU() + "' WHERE MA_DOT_THUCHIEN_DANGKIEM='"
					+ r.getMA_DOT_THUCHIEN_DANGKIEM() + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
