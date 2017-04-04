package DAO.BUILD.QUERY.UPDATE_LIB;

import java.util.Date;

import DAO.NGUOIDUNG;
import DAO.THONGBAO;
import View.DateTime.MyDateFormat;

public class query_Update_NGUOIDUNG_NHAN_THONGBAO {
	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_NgaydocThongbao(THONGBAO tb, NGUOIDUNG user2, Date date) {
		try {
			String Date = (date == null) ? "null" : "'" + mdf.getSQLStringDate(date) + "'";
			return "UPDATE NGUOIDUNG_NHAN_THONGBAO SET NGAY_DOC_THONGBAO=" + Date + " WHERE MA_THONGBAO = '"
					+ tb.getMA_THONGBAO() + "' AND TEN_TAI_KHOAN='" + user2.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
