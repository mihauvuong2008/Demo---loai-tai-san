package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.KY_HAN_THONGKE_XANG_DAU;
import View.DateTime.MyDateFormat;

public class query_Insert_KY_HAN_THONGKE_XANG_DAU {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Them_KhyhanThongkeXangdau(KY_HAN_THONGKE_XANG_DAU r) {
		try {
			String result = "INSERT INTO KY_HAN_THONGKE_XANG_DAU (TEN_KYHAN, NGAY_BAT_DAU, NGAY_KET_THUC,  GHI_CHU  ) VALUES( '"
					+ r.getTEN_KYHAN() + "', '" + mdf.getSQLStringDate(r.getNGAY_BAT_DAU()) + "', '"
					+ mdf.getSQLStringDate(r.getNGAY_KET_THUC()) + "', '" + r.getGHI_CHU() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
