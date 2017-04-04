package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.PHUKIEN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;

public class query_Update_TAISAN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Capnhat_Donvi_Sudung(String key, Integer mAPHONGBAN) {
		try {
			return "UPDATE Taisan SET MA_DON_VI_SU_DUNG ='" + mAPHONGBAN + "' where MA_TAISAN='" + key + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Thongtin_Taisan(TAISAN t) {
		try {
			String d = t.getNGAY_SU_DUNG() == null ? "null" : "'" + mdf.getSQLStringDate(t.getNGAY_SU_DUNG()) + "'";
			return "UPDATE TAISAN  SET TEN_TAISAN='" + t.getTEN_TAISAN() + "', MODEL='" + t.getMODEL() + "', SERI = '"
					+ t.getSERI() + "', NGAY_SU_DUNG = " + d + ", XUAT_XU = '" + t.getXUAT_XU() + "', BAO_HANH = '"
					+ t.getBAO_HANH() + "', TINH_TRANG = '" + t.getTINH_TRANG() + "', TRANG_THAI = '"
					+ t.getTRANG_THAI() + "', DON_VI_TINH = '" + t.getDON_VI_TINH() + "', GHI_CHU = '" + t.getGHI_CHU()
					+ "', MA_LOAITAISAN_CAP_III = '" + t.getMA_LOAITAISAN_CAP_III() + "', MA_NHOMTAISAN_CAP_V ='"
					+ t.getMA_NHOMTAISAN_CAP_V() + "', MA_DON_VI_SU_DUNG = '" + t.getMA_DON_VI_SU_DUNG()
					+ "', MA_DON_VI_QUAN_LY = '" + t.getMA_DON_VI_QUAN_LY() + "', MA_TANSAN_KETOAN='"
					+ t.getMA_TANSAN_KETOAN() + "' WHERE MA_TAISAN= '" + t.getMA_TAISAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Phukien(PHUKIEN pk) {
		try {
			return "UPDATE PHUKIEN SET TEN_PHUKIEN ='" + pk.getTEN_PHUKIEN() + "',MODEL ='" + pk.getMODEL()
					+ "',SERI ='" + pk.getSERI() + "', NGUYEN_GIA ='" + pk.getNGUYEN_GIA() + "'  where MA_PHUKIEN='"
					+ pk.getMA_PHUKIEN() + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_Taisan_LoaiTaisan(int key, int mA_NhomTaisan) {
		try {
			return "UPDATE Taisan SET MA_LOAITAISAN_CAP_III ='" + mA_NhomTaisan + "' where MA_TAISAN='" + key + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Taisan_NhomTaisan(int key, int mA_NhomTaisan) {
		try {
			return "UPDATE Taisan SET MA_NHOMTAISAN_CAP_V ='" + mA_NhomTaisan + "' where MA_TAISAN='" + key + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
