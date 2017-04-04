package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.THONGBAO;
import View.DateTime.MyDateFormat;

public class query_Insert_THONGBAO {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Them_Thongbao(THONGBAO tb) {
		try {
			return "INSERT INTO THONGBAO "
					+ "( LOAI_THONGBAO ,TEN_TAI_KHOAN_GUI, NGUON_TACDONG, NGUON_BITACDONG, NOIDUNG_THONGBAO,"
					+ " NGAY_THONGBAO ) VALUES" + "( '" + tb.getLOAI_THONGBAO() + "', '" + tb.getTEN_TAI_KHOAN_GUI()
					+ "', '" + tb.getNGUON_TACDONG() + "' , '" + tb.getNGUON_BITACDONG() + "', '"
					+ tb.getNOIDUNG_THONGBAO() + "', '" + mdf.getSQLStringDateTime(tb.getNGAY_THONGBAO()) + "' );";
		} catch (Exception e) {
			return null;
		}

	}

}
