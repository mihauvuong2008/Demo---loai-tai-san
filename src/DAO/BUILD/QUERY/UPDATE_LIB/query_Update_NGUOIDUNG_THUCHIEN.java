package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAP_HO_SO;

public class query_Update_NGUOIDUNG_THUCHIEN {

	public String getString_Capnhat_TapHoso(NGUOIDUNG_THUCHIEN ndth, TAP_HO_SO ths) {
		try {
			return "UPDATE GIAI_DOAN_THUC_HIEN_CAN_BO SET MA_TAPHOSO='" + ths.getMA_TAPHOSO()
					+ "' where MA_GIAI_DOAN_THUC_HIEN='" + ndth.getMA_GIAI_DOAN_THUC_HIEN() + "' AND TEN_TAI_KHOAN='"
					+ ndth.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
