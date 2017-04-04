package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.TAP_HO_SO;

public class query_Update_NGUOIDUNG_NGHIEMTHU {

	public String getString_CapnhatHoso(NGUOIDUNG_NGHIEMTHU ndnt, TAP_HO_SO ths) {
		try {
			String result = "UPDATE GIAI_DOAN_NGHIEM_THU_CAN_BO SET MA_TAPHOSO='" + ths.getMA_TAPHOSO()
					+ "' where MA_GIAI_DOAN_NGHIEM_THU='" + ndnt.getMA_GIAI_DOAN_NGHIEM_THU() + "' AND TEN_TAI_KHOAN='"
					+ ndnt.getTEN_TAI_KHOAN() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
