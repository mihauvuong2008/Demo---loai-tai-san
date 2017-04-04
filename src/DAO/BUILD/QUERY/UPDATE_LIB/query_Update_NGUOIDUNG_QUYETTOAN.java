package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.TAP_HO_SO;

public class query_Update_NGUOIDUNG_QUYETTOAN {

	public String getString_Capnhat_Taphoso(NGUOIDUNG_QUYETTOAN ndqt, TAP_HO_SO ths) {
		try {
			return "UPDATE GIAI_DOAN_QUYET_TOAN_CAN_BO SET MA_TAPHOSO='" + ths.getMA_TAPHOSO()
					+ "' where MA_GIAI_DOAN_QUYET_TOAN='" + ndqt.getMA_GIAI_DOAN_QUYET_TOAN() + "' AND TEN_TAI_KHOAN='"
					+ ndqt.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
