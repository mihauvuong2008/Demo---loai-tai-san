package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DE_XUAT;
import View.DateTime.MyDateFormat;

public class query_Insert_DEXUAT {

	private final MyDateFormat mdf = new MyDateFormat();

	public String get_String_InsertDexuat(DE_XUAT dx) throws NullPointerException {

		try {
			String result = "INSERT INTO DE_XUAT " + "(SODEXUAT, NGAYTHANG_VANBAN, MA_PHONGBAN , THOI_DIEM_BAT_DAU, "
					+ "THOI_DIEM_CHUYEN_GIAO, THOI_DIEM_HOAN_THANH," + "GHI_CHU,TEN_TAI_KHOAN, MA_TAPHOSO)VALUES( '"
					+ dx.getSODEXUAT() + "', "
					+ (dx.getNGAYTHANG_VANBAN() == null ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getNGAYTHANG_VANBAN()) + "'"))
					+ ", '" + dx.getMA_PHONGBAN() + "', "
					+ (dx.getTHOI_DIEM_BAT_DAU() == null ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getTHOI_DIEM_BAT_DAU()) + "'"))
					+ ","
					+ (dx.getTHOI_DIEM_CHUYEN_GIAO() == null ? "null"
							: ("'" + mdf.getSQLStringDate(dx.getTHOI_DIEM_CHUYEN_GIAO()) + "'"))
					+ ",null,'" + dx.getGHI_CHU() + "','" + dx.getTEN_TAI_KHOAN() + "', '" + dx.getMA_TAPHOSO() + "');";

			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
