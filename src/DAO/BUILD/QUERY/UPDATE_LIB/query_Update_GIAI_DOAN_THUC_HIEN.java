package DAO.BUILD.QUERY.UPDATE_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_THUC_HIEN;
import View.DateTime.MyDateFormat;

public class query_Update_GIAI_DOAN_THUC_HIEN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_Ghichu(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, String text) {
		try {
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET GHI_CHU='" + text + "' WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ get_GIAIDOAN_THUCHIEN.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Thoidiem_Batdau_GiaidoanThuchien(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN,
			Date tHISDAY) {
		try {
			String Date = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET THOI_DIEM_BAT_DAU= " + Date + " WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ get_GIAIDOAN_THUCHIEN.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Thoidiem_Chuyengiao_GiaidoanThuchien(GIAI_DOAN_THUC_HIEN giai_DOAN_THUC_HIEN,
			Date tHISDAY) {
		try {
			String Date = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET THOI_DIEM_CHUYEN_GIAO=" + Date + " WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ giai_DOAN_THUC_HIEN.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Thoidiem_Ketthuc_GiaidoanThuchien(GIAI_DOAN_THUC_HIEN gdth, Date tHISDAY) {
		try {
			String Date = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET THOI_DIEM_HOAN_THANH=" + Date + " WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ gdth.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Dukien_GiaidoanThuchien(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN,
			int dukienThuchien) {
		try {
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET THOI_GIAN_DU_KIEN_HOAN_THANH='" + dukienThuchien
					+ "' WHERE MA_GIAI_DOAN_THUC_HIEN='" + get_GIAIDOAN_THUCHIEN.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_Giaidoan_Thuchien(GIAI_DOAN_THUC_HIEN gdth) {
		try {
			return "UPDATE GIAI_DOAN_THUC_HIEN  SET THOI_DIEM_BAT_DAU="
					+ ((gdth.getTHOI_DIEM_BAT_DAU() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdth.getTHOI_DIEM_BAT_DAU()) + "'")
					+ ", THOI_DIEM_CHUYEN_GIAO="
					+ ((gdth.getTHOI_DIEM_CHUYEN_GIAO() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdth.getTHOI_DIEM_CHUYEN_GIAO()) + "'")
					+ ", THOI_DIEM_HOAN_THANH="
					+ ((gdth.getTHOI_DIEM_HOAN_THANH() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdth.getTHOI_DIEM_HOAN_THANH()) + "'")
					+ ", GHI_CHU = '" + gdth.getGHI_CHU() + "' WHERE MA_GIAI_DOAN_THUC_HIEN='"
					+ gdth.getMA_GIAI_DOAN_THUC_HIEN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
