package DAO.BUILD.QUERY.INSERT_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_THUC_HIEN;
import View.DateTime.MyDateFormat;

public class query_Insert_NGUOIDUNG_THUCHIEN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_ThemNguoidung_Thuchien(String tEN_TAI_KHOAN, GIAI_DOAN_THUC_HIEN gdth, int gIAO_NHANVIEC,
			Date nGAY_THAM_GIA) {
		try {
			return "INSERT INTO GIAI_DOAN_THUC_HIEN_CAN_BO (MA_GIAI_DOAN_THUC_HIEN, TEN_TAI_KHOAN, GIAO_NHANVIEC, NGAY_THAM_GIA) VALUES( '"
					+ gdth.getMA_GIAI_DOAN_THUC_HIEN() + "', '" + tEN_TAI_KHOAN + "', '" + gIAO_NHANVIEC + "', '"
					+ mdf.getSQLStringDate(nGAY_THAM_GIA) + "');";
		} catch (Exception e) {
			return null;
		}
	}
}
