package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.QUATRINH_DEXUAT_THUCHIEN;

public class query_Insert_QUATRINH_DEXUAT_THUCHIEN {

	public String getString_ThemQuatrinhDexuatThuchien(QUATRINH_DEXUAT_THUCHIEN qdt) {
		try {
			return "INSERT INTO QUATRINH_DEXUAT_THUCHIEN " + "( TEN_QUA_TRINH," + " MA_DE_XUAT ) VALUES" + "( '"
					+ qdt.getTEN_QUA_TRINH() + "','" + qdt.getMA_DE_XUAT() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
