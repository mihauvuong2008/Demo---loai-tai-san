package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import View.DateTime.MyDateFormat;

public class query_Update_DOT_BANGIAO_TAISAN_NOIBO {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dgt) {
		try {
			MyDateFormat mdf = new MyDateFormat();
			return "UPDATE DOT_BANGIAO_TAISAN_NOIBO  SET  TEN_DOT_BANGIAO_TAISAN_NOIBO = '"
					+ dgt.getTEN_DOT_BANGIAO_TAISAN_NOIBO() + "',  NGAY_THUCHIEN='"
					+ mdf.getSQLStringDate(dgt.getNGAY_THUCHIEN()) + "', BEN_GIAO = '" + dgt.getBEN_GIAO()
					+ "', BEN_NHAN = '" + dgt.getBEN_NHAN() + "', MA_TAPHOSO = '" + dgt.getMA_TAPHOSO()
					+ "'  WHERE MA_DOT_BANGIAO_TAISAN_NOIBO='" + dgt.getMA_DOT_BANGIAO_TAISAN_NOIBO() + "';";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
