package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.LENH_DIEU_XE;
import View.DateTime.MyDateFormat;

public class query_Insert_LENH_DIEU_XE {

	private MyDateFormat mdf = new MyDateFormat();

	public String getString_ThemLenhDieuxe(LENH_DIEU_XE l) {
		try {
			String result = "INSERT INTO LENH_DIEU_XE (" + " MA_PHUONGTIEN_GIAOTHONG, TEN_TAI_KHOAN, DIEM_XUATPHAT, "
					+ "DIEM_DEN, NGAY_DI, NGAY_VE, SO_KM_HIENTAI, TON_NHIENLIEU_HIENTAI, QUANG_DUONG_DUKIEN, "
					+ "PHIEU_NHIENLIEU_DUOCCAP, DIADIEM_GIODON, NOIDUNG_CHUYENDI, NGUOI_TAO_LENH) VALUES('"
					+ l.getMA_PHUONGTIEN_GIAOTHONG() + "','" + l.getTEN_TAI_KHOAN() + "','" + l.getDIEM_XUATPHAT()
					+ "','" + l.getDIEM_DEN() + "','" + mdf.getSQLStringDateTime(l.getNGAY_DI()) + "','"
					+ mdf.getSQLStringDateTime(l.getNGAY_VE()) + "', '" + l.getSO_KM_HIENTAI() + "','"
					+ l.getTON_NHIENLIEU_HIENTAI() + "','" + l.getQUANG_DUONG_DUKIEN() + "', '"
					+ l.getPHIEU_NHIENLIEU_DUOCCAP() + "','" + l.getDIADIEM_GIODON() + "','" + l.getNOIDUNG_CHUYENDI()
					+ "', '" + l.getNGUOI_TAO_LENH() + "')";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
