package DAO.BUILD.QUERY.INSERT_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_NGHIEM_THU;
import View.DateTime.MyDateFormat;

public class query_Insert_NGUOIDUNG_NGHIEMTHU {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_ThemNguoidungThamgiaNghiemthu(String ten_TAI_KHOAN,
			GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, int value_HinhThucNhanCongviec_NguoiDungNhanViec,
			Date nGAY_THAM_GIA) {
		try {
			String result = "INSERT INTO GIAI_DOAN_NGHIEM_THU_CAN_BO (MA_GIAI_DOAN_NGHIEM_THU, TEN_TAI_KHOAN, GIAO_NHANVIEC, NGAY_THAM_GIA) VALUES( '"
					+ get_GIAIDOAN_NGHIEMTHU.getMA_GIAI_DOAN_NGHIEM_THU() + "', '" + ten_TAI_KHOAN + "', '"
					+ value_HinhThucNhanCongviec_NguoiDungNhanViec + "','" + mdf.getSQLStringDate(nGAY_THAM_GIA)
					+ "');";
			return result;
		} catch (Exception e) {
			return null;
		}

	}

}
