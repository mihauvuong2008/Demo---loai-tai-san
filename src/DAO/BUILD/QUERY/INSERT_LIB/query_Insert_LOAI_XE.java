package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.LOAI_XE;

public class query_Insert_LOAI_XE {

	public String getString_ThemLoaixe(LOAI_XE i) {
		try {
			return "INSERT INTO LOAI_XE (TEN_DONG_XE, HANG_SAN_XUAT, OTO_XEMAY, DINH_MUC_XANG_DAU) VALUES( '"
					+ i.getTEN_DONG_XE() + "', '" + i.getHANG_SAN_XUAT() + "', '" + i.getOTO_XEMAY() + "', '"
					+ i.getDINH_MUC_XANG_DAU() + "');";
		} catch (Exception e) {
			return null;
		}
	}
}
