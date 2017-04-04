package DAO.BUILD.QUERY.UPDATE_LIB;

import java.util.Date;

import DAO.GIAI_DOAN_QUYET_TOAN;
import View.DateTime.MyDateFormat;

public class query_Update_GIAI_DOAN_QUYET_TOAN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_CapnhatGhichu(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, String text) {
		try {
			return "UPDATE GIAI_DOAN_QUYET_TOAN  SET GHI_CHU='" + text + "' WHERE MA_GIAI_DOAN_QUYET_TOAN='"
					+ get_GIAIDOAN_QUYETTOAN.getMA_GIAI_DOAN_QUYET_TOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_ThoidiembatdauPhanviec(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, Date tHISDAY) {
		try {
			String THISDAY = tHISDAY == null ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_QUYET_TOAN  SET THOI_DIEM_TIEP_NHAN=" + THISDAY
					+ " WHERE MA_GIAI_DOAN_QUYET_TOAN='" + get_GIAIDOAN_QUYETTOAN.getMA_GIAI_DOAN_QUYET_TOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_NgaychuyengiaoPhanviec_KetthucCongviec(GIAI_DOAN_QUYET_TOAN giai_DOAN_QUYET_TOAN,
			Date tHISDAY) {
		try {
			String THISDAY = (tHISDAY == null) ? "null" : "'" + mdf.getSQLStringDate(tHISDAY) + "'";
			return "UPDATE GIAI_DOAN_QUYET_TOAN  SET THOI_GIAN_KET_THUC=" + THISDAY + " WHERE MA_GIAI_DOAN_QUYET_TOAN='"
					+ giai_DOAN_QUYET_TOAN.getMA_GIAI_DOAN_QUYET_TOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_DukienThuchienPhanviec(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN,
			int dukienThuchien) {
		try {
			return "UPDATE GIAI_DOAN_QUYET_TOAN  SET THOI_GIAN_DU_KIEN_HOAN_THANH=" + dukienThuchien
					+ " WHERE MA_GIAI_DOAN_QUYET_TOAN='" + get_GIAIDOAN_QUYETTOAN.getMA_GIAI_DOAN_QUYET_TOAN() + "';";

		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_Giaidoan_Quyettoan(GIAI_DOAN_QUYET_TOAN gdqt) {
		try {
			return "UPDATE GIAI_DOAN_QUYET_TOAN  SET THOI_DIEM_TIEP_NHAN = "
					+ ((gdqt.getTHOI_DIEM_TIEP_NHAN() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdqt.getTHOI_DIEM_TIEP_NHAN()) + "'")
					+ ",THOI_GIAN_KET_THUC = "
					+ ((gdqt.getTHOI_GIAN_KET_THUC() == null) ? "'null'"
							: "'" + mdf.getSQLStringDate(gdqt.getTHOI_GIAN_KET_THUC()) + "'")
					+ ", GHI_CHU = '" + gdqt.getGHI_CHU() + "'  WHERE MA_GIAI_DOAN_QUYET_TOAN='"
					+ gdqt.getMA_GIAI_DOAN_QUYET_TOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
