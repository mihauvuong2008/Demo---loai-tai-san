package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;

public class query_Select_NGUONGIAM {

	public String getString_Nguongiam(int ma_NGUONGIAM) {
		try {
			return "SELECT * FROM NGUONGIAM WHERE MA_NGUONGIAM = \"" + ma_NGUONGIAM + "\"; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Nguongiam(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "SELECT * " + " FROM NGUONGIAM " + " INNER JOIN (SELECT DOT_THUCHIEN_GIAM_TAISAN.MA_NGUONGIAM "
					+ " FROM DOT_THUCHIEN_GIAM_TAISAN " + " WHERE DOT_THUCHIEN_GIAM_TAISAN.MA_DOT_GIAM = '"
					+ dgt.getMA_DOT_GIAM() + "')as t1 " + " ON NGUONGIAM.MA_NGUONGIAM = t1.MA_NGUONGIAM;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_AllNguongiam(String pattern) {
		try {
			return "SELECT * " + " FROM NGUONGIAM " + (pattern.equals("") ? ""
					: "WHERE TEN_NGUONGIAM LIKE '%" + pattern + "%' OR LIEN_HE LIKE '%" + pattern
							+ "%' OR GIOI_THIEU LIKE '%" + pattern + "%'");
		} catch (Exception e) {
			return null;
		}
	}

}
