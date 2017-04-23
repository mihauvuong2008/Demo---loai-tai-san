package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import View.DateTime.MyDateFormat;

public class query_Insert_DOT_BANGIAO_TAISAN_NOIBO {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_Insert_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dbtn) {
		try {
			String result = "INSERT INTO DOT_BANGIAO_TAISAN_NOIBO "
					+ "(TEN_DOT_BANGIAO_TAISAN_NOIBO , NGAY_THUCHIEN, BEN_GIAO, BEN_NHAN , MA_TAPHOSO, TEN_TAI_KHOAN  )VALUES( '"
					+ dbtn.getTEN_DOT_BANGIAO_TAISAN_NOIBO() + "', '" + mdf.getSQLStringDate(dbtn.getNGAY_THUCHIEN())
					+ "', '" + dbtn.getBEN_GIAO() + "', '" + dbtn.getBEN_NHAN() + "', '" + dbtn.getMA_TAPHOSO() + "', '"
					+ dbtn.getTEN_TAI_KHOAN() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(String Mataisan, DOT_BANGIAO_TAISAN_NOIBO dbtn) {
		try {
			String result = "INSERT INTO TAISAN_DOT_BANGIAO_TAISAN_NOIBO "
					+ "(MA_DOT_BANGIAO_TAISAN_NOIBO , MA_TAISAN )VALUES( '" + dbtn.getMA_DOT_BANGIAO_TAISAN_NOIBO()
					+ "', '" + Mataisan + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
