package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;

public class query_Insert_GIAI_DOAN_QUYET_TOAN {

	public String getString_ThemGiaidoanQuyettoan_TangTaisan(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			return "INSERT INTO GIAI_DOAN_QUYET_TOAN (MA_QUATRINH_NGHIEMTHU_QUYETTOAN) VALUES( '"
					+ dtt.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_ThemGiaidoanQuyettoan_SuachuaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			return "INSERT INTO GIAI_DOAN_QUYET_TOAN (MA_QUATRINH_NGHIEMTHU_QUYETTOAN) VALUES( '"
					+ dsb.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
