package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.HOSO_DAGUI;
import DAO.NGUOINHAN_HOSO_DAGUI;
import View.DateTime.MyDateFormat;

public class query_Update_HOSO_DAGUI {

	public String getString_Update_HOSO_DAGUI(HOSO_DAGUI r) {
		// TODO Auto-generated method stub
		String date = new MyDateFormat().getSQLStringDate(r.getNGAY_GUI());
		return "UPDATE HOSO_DAGUI SET NGAY_GUI = '" + date + "'";
	}

	public String getString_Update_NGUOINHAN_HOSO_DAGUI(NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui) {
		// TODO Auto-generated method stub
		return "UPDATE NGUOINHAN_HOSO_DAGUI SET DA_DOC = '" + (nguoiNhanHosoDagui.getDA_DOC() == true ? "1" : "0")
				+ "' WHERE MA_HOSO_DAGUI = '" + nguoiNhanHosoDagui.getMA_HOSO_DAGUI() + "' AND TEN_TAI_KHOAN = '"
				+ nguoiNhanHosoDagui.getTEN_TAI_KHOAN() + "'";
	}

}
