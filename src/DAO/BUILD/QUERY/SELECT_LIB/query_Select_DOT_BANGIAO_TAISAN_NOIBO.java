package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import View.DateTime.MyDateFormat;

public class query_Select_DOT_BANGIAO_TAISAN_NOIBO {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_Tatca_DOT_BANGIAO_TAISAN_NOIBO() {
		return "SELECT * " + "FROM DOT_BANGIAO_TAISAN_NOIBO";
	}

	public String getString_Danhsach_DOT_BANGIAO_TAISAN_NOIBO(Date begin, Date end, String string) {
		return "SELECT * " + "FROM DOT_BANGIAO_TAISAN_NOIBO WHERE NGAY_THUCHIEN>='" + mdf.getSQLStringDate(begin)
				+ "' AND NGAY_THUCHIEN<='" + mdf.getSQLStringDate(end) + "' AND TEN_DOT_BANGIAO_TAISAN_NOIBO LIKE '%"
				+ string + "%'";
	}

	public String getString_get_DOT_BANGIAO_TAISAN_NOIBO(int ma_DOT_BANGIAO_TAISAN_NOIBO) {
		return "SELECT * " + "FROM DOT_BANGIAO_TAISAN_NOIBO WHERE MA_DOT_BANGIAO_TAISAN_NOIBO ='"
				+ ma_DOT_BANGIAO_TAISAN_NOIBO + "'";
	}

	public String get_DOT_BANGIAO_TAISAN_NOIBO_list(int ma_TAISAN) {
		return "SELECT * "
				+ "FROM DOT_BANGIAO_TAISAN_NOIBO as dbn INNER JOIN TAISAN_DOT_BANGIAO_TAISAN_NOIBO as tdbn ON dbn.MA_DOT_BANGIAO_TAISAN_NOIBO = tdbn.MA_DOT_BANGIAO_TAISAN_NOIBO WHERE tdbn.MA_TAISAN ='"
				+ ma_TAISAN + "'";
	}

	public String getString_get_DOT_BANGIAO_TAISAN_NOIBO_Gannhat(int ma_TAISAN) {
		return "SELECT * " + "FROM DOT_BANGIAO_TAISAN_NOIBO as dbtn "
				+ " INNER JOIN TAISAN_DOT_BANGIAO_TAISAN_NOIBO as tdbtn "
				+ " ON tdbtn.MA_DOT_BANGIAO_TAISAN_NOIBO = dbtn.MA_DOT_BANGIAO_TAISAN_NOIBO "
				+ " INNER JOIN TAISAN as ts " + " ON ts.MA_TAISAN = tdbtn.MA_TAISAN " + " WHERE ts.MA_TAISAN = '"
				+ ma_TAISAN + "' ORDER BY dbtn.NGAY_THUCHIEN DESC  LIMIT 1";
	}

}
