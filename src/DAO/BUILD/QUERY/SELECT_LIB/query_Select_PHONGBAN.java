package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_PHONGBAN {

	public String getString_TatcaPhongban() {
		try {
			return "select  * from PHONGBAN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Phongban(Integer ma_Dvi_Quanly) {
		try {
			return "select  * from PHONGBAN where MA_PHONGBAN = '" + ma_Dvi_Quanly + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
