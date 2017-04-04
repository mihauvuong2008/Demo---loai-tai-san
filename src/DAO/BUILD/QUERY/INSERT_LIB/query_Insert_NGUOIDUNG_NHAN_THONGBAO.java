package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NGUOIDUNG_NHAN_THONGBAO;
import View.DateTime.MyDateFormat;

public class query_Insert_NGUOIDUNG_NHAN_THONGBAO {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Them_Nguoidung_Nhan_Thongbao(NGUOIDUNG_NHAN_THONGBAO nntb) {
		try {
			String Date = nntb.getNGAY_DOC_THONGBAO() == null ? "null"
					: "'" + mdf.getSQLStringDateTime(nntb.getNGAY_DOC_THONGBAO()) + "'";
			return "INSERT INTO NGUOIDUNG_NHAN_THONGBAO " + "( MA_THONGBAO ,TEN_TAI_KHOAN, NGAY_DOC_THONGBAO) VALUES"
					+ "( '" + nntb.getMA_THONGBAO() + "', '" + nntb.getTEN_TAI_KHOAN() + "', " + Date + " );";
		} catch (Exception e) {
			return null;
		}
	}

}
