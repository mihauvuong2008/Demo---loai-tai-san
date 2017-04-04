package DAO.BUILD.QUERY.UPDATE_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_NGHIEM_THU;
import View.DateTime.MyDateFormat;

public class query_Update_GIAI_DOAN_NGHIEM_THU {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_Ghichu(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, String text) {
		try {
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET GHI_CHU='" + text + "' WHERE MA_GIAI_DOAN_NGHIEM_THU='"
					+ get_GIAIDOAN_NGHIEMTHU.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_ThoidiemBatdauCongviec(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY) {
		try {
			String THISDAY = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET THOI_DIEM_TIEP_NHAN=" + THISDAY
					+ " WHERE MA_GIAI_DOAN_NGHIEM_THU='" + get_GIAIDOAN_NGHIEMTHU.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_NgayChuyengiaoPhanviec(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY) {
		try {
			String THISDAY = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET THOI_DIEM_CHUYEN_GIAO=" + THISDAY
					+ " WHERE MA_GIAI_DOAN_NGHIEM_THU='" + get_GIAIDOAN_NGHIEMTHU.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_ThoidiemKetthucPhanviec(GIAI_DOAN_NGHIEM_THU gdnt, Date tHISDAY) {
		try {
			String THISDAY = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET THOI_DIEM_KET_THUC=" + THISDAY + " WHERE MA_GIAI_DOAN_NGHIEM_THU='"
					+ gdnt.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_DukienThoiganHoanthanh(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU,
			int dukienThuchien) {
		try {
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET THOI_GIAN_DU_KIEN_HOAN_THANH='" + dukienThuchien
					+ "' WHERE MA_GIAI_DOAN_NGHIEM_THU='" + get_GIAIDOAN_NGHIEMTHU.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_Giaidoan_Nghiemthu(GIAI_DOAN_NGHIEM_THU gdnt) {
		try {
			return "UPDATE GIAI_DOAN_NGHIEM_THU  SET THOI_DIEM_TIEP_NHAN= "
					+ ((gdnt.getTHOI_DIEM_TIEP_NHAN() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdnt.getTHOI_DIEM_TIEP_NHAN()) + "'")
					+ ",THOI_DIEM_CHUYEN_GIAO= "
					+ ((gdnt.getTHOI_DIEM_CHUYEN_GIAO() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdnt.getTHOI_DIEM_CHUYEN_GIAO()) + "'")
					+ ",THOI_DIEM_KET_THUC= "
					+ ((gdnt.getTHOI_DIEM_KET_THUC() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdnt.getTHOI_DIEM_KET_THUC()) + "'")
					+ ", GHI_CHU='" + gdnt.getGHI_CHU() + "' WHERE MA_GIAI_DOAN_NGHIEM_THU='"
					+ gdnt.getMA_GIAI_DOAN_NGHIEM_THU() + "';";
		} catch (Exception e) {
			return null;
		}
	}
}
