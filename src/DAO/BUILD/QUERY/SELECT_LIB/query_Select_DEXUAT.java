package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import View.DateTime.MyDateFormat;

public class query_Select_DEXUAT {
	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Select_Dexuat_MuasamTaisan(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			String result = "SELECT * " + "FROM DE_XUAT "
					+ " INNER JOIN 	(SELECT QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT " + "FROM QUATRINH_DEXUAT_THUCHIEN "
					+ " INNER JOIN 	(select DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " FROM DOT_THUCHIEN_TANG_TAISAN WHERE DOT_THUCHIEN_TANG_TAISAN.MA_DOT_TANG = '"
					+ dtt.getMA_DOT_TANG() + "') as t "
					+ " ON QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN = t.MA_QUATRINH_DEXUAT_THUCHIEN) as t2 "
					+ " ON DE_XUAT.MA_DE_XUAT = t2.MA_DE_XUAT; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dexuat_Suachua_Baoduong_PTTS(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			String result = "SELECT * " + "FROM DE_XUAT " + " INNER JOIN (SELECT QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT "
					+ "FROM QUATRINH_DEXUAT_THUCHIEN "
					+ " INNER JOIN 	(select DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " FROM DOT_THUCHIEN_SUACHUA_BAODUONG WHERE DOT_THUCHIEN_SUACHUA_BAODUONG.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = '"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "') as t "
					+ " ON QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN = t.MA_QUATRINH_DEXUAT_THUCHIEN) as t2 "
					+ " ON DE_XUAT.MA_DE_XUAT = t2.MA_DE_XUAT; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dexuat_ThanhlyTaisan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			String result = "SELECT * " + "FROM DE_XUAT "
					+ " INNER JOIN 	(SELECT QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT "
					+ "FROM QUATRINH_DEXUAT_THUCHIEN INNER JOIN 	(select DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " FROM DOT_THUCHIEN_GIAM_TAISAN WHERE DOT_THUCHIEN_GIAM_TAISAN.MA_DOT_GIAM = '"
					+ dgt.getMA_DOT_GIAM() + "') as t "
					+ " ON QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN = t.MA_QUATRINH_DEXUAT_THUCHIEN) as t2 "
					+ " ON DE_XUAT.MA_DE_XUAT = t2.MA_DE_XUAT; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_AllDexuat_Suachua_Baoduong(Date begin, Date end, String text_pattern) {
		try {
			return "SELECT * FROM DE_XUAT as dx" + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON qtdxth.MA_DE_XUAT = dx.MA_DE_XUAT " + " INNER JOIN 	DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN WHERE NGAYTHANG_VANBAN > '"
					+ mdf.getSQLStringDate(begin) + "' AND NGAYTHANG_VANBAN < '" + mdf.getSQLStringDate(end) + "'"
					+ ((text_pattern.equals("")) ? "" : " AND dx.SODEXUAT LIKE '%" + text_pattern + "%'");
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_AllDexuat_Muasam(Date begin, Date end, String text_pattern) {
		try {
			return "SELECT * FROM DE_XUAT as dx" + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON qtdxth.MA_DE_XUAT = dx.MA_DE_XUAT " + " INNER JOIN 	DOT_THUCHIEN_TANG_TAISAN as dtt "
					+ " ON dtt.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN WHERE NGAYTHANG_VANBAN > '"
					+ mdf.getSQLStringDate(end) + "' AND NGAYTHANG_VANBAN < '" + mdf.getSQLStringDate(end) + "'"
					+ ((text_pattern.equals("")) ? "" : " AND dx.SODEXUAT LIKE '%" + text_pattern + "%'");
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_AllDexuat_Thanhly(Date begin, Date end, String text_pattern) {
		try {
			return "SELECT * FROM DE_XUAT as dx" + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON qtdxth.MA_DE_XUAT = dx.MA_DE_XUAT " + " INNER JOIN 	DOT_THUCHIEN_GIAM_TAISAN as dgt "
					+ " ON dgt.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN WHERE NGAYTHANG_VANBAN > '"
					+ mdf.getSQLStringDate(begin) + "' AND NGAYTHANG_VANBAN < '" + mdf.getSQLStringDate(end) + "' "
					+ ((text_pattern.equals("")) ? "" : " AND dx.SODEXUAT LIKE '%" + text_pattern + "%'");
		} catch (Exception e) {
			return null;
		}
	}

}
