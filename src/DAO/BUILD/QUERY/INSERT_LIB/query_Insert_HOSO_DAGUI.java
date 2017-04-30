package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.HOSO_DAGUI;
import View.DateTime.MyDateFormat;

public class query_Insert_HOSO_DAGUI {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_insert_HOSO_DAGUI(HOSO_DAGUI r) {
		return "INSERT INTO HOSO_DAGUI (NGAY_GUI, TEN_TAI_KHOAN, MA_TAPHOSO ) VALUES ('"
				+ mdf.getSQLStringDate(r.getNGAY_GUI()) + "','" + r.getTEN_TAI_KHOAN() + "','" + r.getMA_TAPHOSO()
				+ "')";
	}

	public String getString_GuiHoso(int key, String tentaikhoan) {
		return "INSERT INTO NGUOINHAN_HOSO_DAGUI (MA_HOSO_DAGUI, TEN_TAI_KHOAN ) VALUES ('" + key + "','" + tentaikhoan
				+ "')";
	}

}
