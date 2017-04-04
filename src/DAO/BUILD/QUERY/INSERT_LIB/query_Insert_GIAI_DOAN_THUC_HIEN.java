package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;

public class query_Insert_GIAI_DOAN_THUC_HIEN {

	public String getString_Taomoi_GiaidoanThuchien_SuachuaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			return "INSERT INTO GIAI_DOAN_THUC_HIEN (MA_QUATRINH_DEXUAT_THUCHIEN) VALUES( '"
					+ dsb.getMA_QUATRINH_DEXUAT_THUCHIEN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Taomoi_GiaidoanThuchien_TangTaisan(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			return "INSERT INTO GIAI_DOAN_THUC_HIEN (MA_QUATRINH_DEXUAT_THUCHIEN) VALUES( '"
					+ dtt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Taomoi_GiaidoanThuchien_GiamTaisan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "INSERT INTO GIAI_DOAN_THUC_HIEN (MA_QUATRINH_DEXUAT_THUCHIEN) VALUES( '"
					+ dgt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
