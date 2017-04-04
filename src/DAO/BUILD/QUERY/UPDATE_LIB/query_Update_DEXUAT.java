package DAO.BUILD.QUERY.UPDATE_LIB;

import java.util.Date;

import DAO.DE_XUAT;
import View.DateTime.MyDateFormat;

public class query_Update_DEXUAT {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Update_ThoidiemKetthucCongviecDexuat(DE_XUAT dx, Date thoiDiemKetthuc) {
		try {
			String THOIDIEMKETTHUC = (thoiDiemKetthuc == null) ? "null"
					: "'" + mdf.getSQLStringDate(thoiDiemKetthuc) + "'";
			String result = "UPDATE DE_XUAT  SET THOI_DIEM_HOAN_THANH=" + THOIDIEMKETTHUC + " WHERE MA_DE_XUAT='"
					+ dx.getMA_DE_XUAT() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_TapHoso(DE_XUAT selected, int ma_NewTapHoso) {
		try {
			return "UPDATE DE_XUAT  SET MA_TAPHOSO=" + ma_NewTapHoso + " WHERE MA_DE_XUAT='" + selected.getMA_DE_XUAT()
					+ "' AND MA_TAPHOSO IS NULL;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Update_Dexuat(DE_XUAT dx) {
		try {
			return "UPDATE DE_XUAT  SET SODEXUAT='" + dx.getSODEXUAT() + "' ,NGAYTHANG_VANBAN='"
					+ mdf.getSQLStringDate(dx.getNGAYTHANG_VANBAN()) + "', MA_PHONGBAN='" + dx.getMA_PHONGBAN()
					+ "', THOI_DIEM_BAT_DAU="
					+ ((dx.getTHOI_DIEM_BAT_DAU() == null) ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getTHOI_DIEM_BAT_DAU()) + "'"))
					+ ", THOI_DIEM_CHUYEN_GIAO="
					+ ((dx.getTHOI_DIEM_CHUYEN_GIAO() == null) ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()) + "'"))
					+ ", THOI_DIEM_HOAN_THANH="
					+ ((dx.getTHOI_DIEM_HOAN_THANH() == null) ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getTHOI_DIEM_HOAN_THANH()) + "'"))
					+ ", GHI_CHU='" + dx.getGHI_CHU() + "', TEN_TAI_KHOAN='" + dx.getTEN_TAI_KHOAN() + "', MA_TAPHOSO='"
					+ dx.getMA_TAPHOSO() + "' WHERE MA_DE_XUAT='" + dx.getMA_DE_XUAT() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
