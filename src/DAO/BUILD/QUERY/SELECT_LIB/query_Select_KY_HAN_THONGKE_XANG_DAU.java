package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_KY_HAN_THONGKE_XANG_DAU {

	public String getString_Tatca_KyhanThongke() {
		try {
			String result = "select * from KY_HAN_THONGKE_XANG_DAU;";
			return result;
		} catch (Exception e) {
			return null;
		}

	}

}
