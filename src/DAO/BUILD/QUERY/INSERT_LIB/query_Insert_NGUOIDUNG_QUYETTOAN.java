package DAO.BUILD.QUERY.INSERT_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_QUYET_TOAN;
import View.DateTime.MyDateFormat;

public class query_Insert_NGUOIDUNG_QUYETTOAN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Them_Nguoidun_Quyettoan(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN,
			int value_HinhThucNhanCongviec_NguoiDungNhanViec, Date nGAY_THAM_GIA) {
		try {
			return "INSERT INTO GIAI_DOAN_QUYET_TOAN_CAN_BO (MA_GIAI_DOAN_QUYET_TOAN, TEN_TAI_KHOAN, GIAO_NHANVIEC, NGAY_THAM_GIA) VALUES( '"
					+ get_GIAIDOAN_QUYETTOAN.getMA_GIAI_DOAN_QUYET_TOAN() + "', '" + ten_TAI_KHOAN + "', '"
					+ value_HinhThucNhanCongviec_NguoiDungNhanViec + "', '" + mdf.getSQLStringDate(nGAY_THAM_GIA)
					+ "');";
		} catch (Exception e) {
			return null;
		}
	}

}
