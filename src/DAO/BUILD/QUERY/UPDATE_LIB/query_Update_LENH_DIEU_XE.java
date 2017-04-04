package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LENH_DIEU_XE;
import View.DateTime.MyDateFormat;

public class query_Update_LENH_DIEU_XE {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_Lenhdieuxe(LENH_DIEU_XE l) {
		try {
			String result = "UPDATE LENH_DIEU_XE SET  MA_PHUONGTIEN_GIAOTHONG='" + l.getMA_PHUONGTIEN_GIAOTHONG()
					+ "', " + " TEN_TAI_KHOAN='" + l.getTEN_TAI_KHOAN() + "', " + " DIEM_XUATPHAT='"
					+ l.getDIEM_XUATPHAT() + "', " + " DIEM_DEN='" + l.getDIEM_DEN() + "', " + " NGAY_DI= '"
					+ mdf.getSQLStringDateTime(l.getNGAY_DI()) + "', " + " NGAY_VE='" + mdf.getSQLStringDateTime(l.getNGAY_VE())
					+ "', " + " SO_KM_HIENTAI='" + l.getSO_KM_HIENTAI() + "', TON_NHIENLIEU_HIENTAI = '"
					+ l.getTON_NHIENLIEU_HIENTAI() + "',  QUANG_DUONG_DUKIEN='" + l.getQUANG_DUONG_DUKIEN() + "', "
					+ " PHIEU_NHIENLIEU_DUOCCAP='" + l.getPHIEU_NHIENLIEU_DUOCCAP() + "', "
					+ " PHIEU_NHIENLIEU_MUANGOAI='" + l.getPHIEU_NHIENLIEU_MUANGOAI() + "', " + " DIADIEM_GIODON='"
					+ l.getDIADIEM_GIODON() + "', " + " NOIDUNG_CHUYENDI='" + l.getNOIDUNG_CHUYENDI()
					+ "' WHERE MA_LENH_DIEU_XE='" + l.getMA_LENH_DIEU_XE() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Huylenh_Laplailenh(LENH_DIEU_XE l, Boolean huy) {
		try {
			String result;
			if (huy) {
				result = "UPDATE LENH_DIEU_XE SET HUY_LENH='0' WHERE MA_LENH_DIEU_XE='" + l.getMA_LENH_DIEU_XE() + "';";
			} else {
				result = "UPDATE LENH_DIEU_XE SET HUY_LENH='1' WHERE MA_LENH_DIEU_XE='" + l.getMA_LENH_DIEU_XE() + "';";
			}
			return result;
		} catch (Exception e) {
			return null;
		}

	}

}
