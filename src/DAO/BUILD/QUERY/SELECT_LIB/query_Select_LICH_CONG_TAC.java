package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import View.DateTime.MyDateFormat;

public class query_Select_LICH_CONG_TAC {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_Select_AllLICH_CONG_TAC() {
		try {
			return "SELECT * " + "FROM LICH_CONG_TAC ; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_LICH_CONG_TAC(int ma_LICH_CONG_TAC) {
		try {
			return "SELECT * " + "FROM LICH_CONG_TAC  WHERE MA_LICH_CONG_TAC = " + ma_LICH_CONG_TAC + " ;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_LICH_CONG_TAC(Date date) {
		try {
			return "SELECT * " + "FROM LICH_CONG_TAC  WHERE THOIGIAN LIKE '" + mdf.getSQLStringDate(date) + "%' ;";
		} catch (Exception e) {
			return null;
		}
	}

}
