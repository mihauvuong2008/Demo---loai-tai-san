package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.PHUKIEN;
import DAO.TAISAN;
import View.DateTime.MyDateFormat;

public class query_Insert_TAISAN {

	private final MyDateFormat mdf = new MyDateFormat();

	String addSeparator(String pattern) {
		StringBuilder sb = new StringBuilder();
		for (char e : pattern.toCharArray()) {
			if (e == '\'') {
				sb.append("\\");
			}
			sb.append(String.valueOf(e));
		}
		return sb.toString();
	}

	public String getString_Them_Taisan(TAISAN t) {
		try {
			String DateString = t.getNGAY_SU_DUNG() == null ? "null"
					: "'" + mdf.getSQLStringDate(t.getNGAY_SU_DUNG()) + "'";
			return "INSERT INTO TAISAN ( TEN_TAISAN , MODEL ,SERI ,NGAY_SU_DUNG,XUAT_XU,BAO_HANH ,TINH_TRANG ,"
					+ "TRANG_THAI, DON_VI_TINH, NGUYEN_GIA , GHI_CHU, MA_TANSAN_KETOAN, MA_NHOMTAISAN_CAP_V, MA_LOAITAISAN_CAP_III , MA_DON_VI_SU_DUNG , MA_DON_VI_QUAN_LY) "
					+ "VALUES ( '" + addSeparator(t.getTEN_TAISAN()) + "', '" + t.getMODEL() + "', '" + t.getSERI()
					+ "', " + DateString + ", '" + t.getXUAT_XU() + "', '" + t.getBAO_HANH() + "','" + t.getTINH_TRANG()
					+ "','" + t.getTRANG_THAI() + "', '" + t.getDON_VI_TINH() + "', '" + t.getNGUYEN_GIA() + "','"
					+ t.getGHI_CHU() + "','" + t.getMA_TANSAN_KETOAN() + "' ,'" + t.getMA_NHOMTAISAN_CAP_V() + "', '"
					+ t.getMA_LOAITAISAN_CAP_III() + "' , '" + t.getMA_DON_VI_SU_DUNG() + "','"
					+ t.getMA_DON_VI_QUAN_LY() + "'  );";
		} catch (Exception e) {
			return null;
		}

	}

	public String getString_Them_Phukien(PHUKIEN pk) {
		try {
			return " INSERT INTO PHUKIEN (TEN_PHUKIEN, MODEL, SERI, NGUYEN_GIA, MA_TAISAN) VALUES ('"
					+ pk.getTEN_PHUKIEN() + "',  '" + pk.getMODEL() + "', '" + pk.getSERI() + "', '"
					+ pk.getNGUYEN_GIA() + "','" + pk.getMA_TAISAN() + "') ;";
		} catch (Exception e) {
			return null;
		}
	}

}
