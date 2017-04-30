package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.HOSO_DAGUI;

public class query_Select_HOSO_DAGUI {

	public String getString_Tatca_HOSO_DAGUI() {
		return "SELECT * FROM HOSO_DAGUI";
	}

	public String getString_getNguoiNhan(HOSO_DAGUI hoso_Hagui) {
		return "SELECT * FROM NGUOINHAN_HOSO_DAGUI as nhd INNER JOIN HOSO_DAGUI as hd"
				+ " ON nhd.MA_HOSO_DAGUI = hd.MA_HOSO_DAGUI WHERE  hd.MA_HOSO_DAGUI = '" + hoso_Hagui.getMA_HOSO_DAGUI()
				+ "'";
	}

}
