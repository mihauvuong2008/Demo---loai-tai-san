package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.NHOM_DONVI_THAMGIA_THONGKE;

public class query_Insert_NHOM_DONVI_THAMGIA_THONGKE {

	public String getString_Them_NhomDonvi_Thamgia_Thongke(NHOM_DONVI_THAMGIA_THONGKE r) {
		try {
			String result = "INSERT INTO NHOM_DONVI_THAMGIA_THONGKE (TEN_NHOM_DONVI_THAMGIA_THONGKE, MA_KYHAN) VALUES( '"
					+ r.getTEN_NHOM_DONVI_THAMGIA_THONGKE() + "', '" + r.getMA_KYHAN() + "');";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
