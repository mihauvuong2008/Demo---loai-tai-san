package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_NHOM_DONVI_THAMGIA_THONGKE {

	public String getString_Tatca_NhomDonvi_Thamgia_Thongke(int ma_Kyhan) {
		try {
			String result = "select * from NHOM_DONVI_THAMGIA_THONGKE;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
