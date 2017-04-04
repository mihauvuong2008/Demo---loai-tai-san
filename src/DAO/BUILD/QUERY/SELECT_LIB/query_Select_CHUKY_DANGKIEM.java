package DAO.BUILD.QUERY.SELECT_LIB;

public class query_Select_CHUKY_DANGKIEM {

	public String getString_Select_AllCHUKY_DANGKIEM() {
		try {
			return "SELECT * " + "FROM CHUKY_DANGKIEM ; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_CHUKY_DANGKIEM(int ma_KYHAN_DANGKIEM) {
		try {
			return "SELECT * " + "FROM CHUKY_DANGKIEM  WHERE MA_KYHAN_DANGKIEM = " + ma_KYHAN_DANGKIEM + " ;";
		} catch (Exception e) {
			return null;
		}
	}

}
