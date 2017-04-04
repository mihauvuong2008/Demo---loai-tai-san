package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.KY_HAN_THONGKE_XANG_DAU;
import View.DateTime.MyDateFormat;

public class query_Update_KY_HAN_THONGKE_XANG_DAU {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_KyhanthongkeXangdau(KY_HAN_THONGKE_XANG_DAU r) {
		try {
			String result = "UPDATE KY_HAN_THONGKE_XANG_DAU SET TEN_KYHAN  ='" + r.getTEN_KYHAN() + "', NGAY_BAT_DAU='"
					+ mdf.getSQLStringDate(r.getNGAY_BAT_DAU()) + "', NGAY_KET_THUC='"
					+ mdf.getSQLStringDate(r.getNGAY_KET_THUC()) + "', GHI_CHU='" + r.getGHI_CHU()
					+ "' WHERE MA_KYHAN = '" + r.getMA_KYHAN() + "'";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
