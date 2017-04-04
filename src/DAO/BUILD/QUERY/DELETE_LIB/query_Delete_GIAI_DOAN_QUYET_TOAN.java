package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class query_Delete_GIAI_DOAN_QUYET_TOAN {

	private static Log log = LogFactory.getLog(query_Delete_GIAI_DOAN_QUYET_TOAN.class);

	public String getString_XoaNguoidung_Quyettoan(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC) {
		try {
			return "DELETE FROM  GIAI_DOAN_QUYET_TOAN_CAN_BO  WHERE MA_GIAI_DOAN_QUYET_TOAN='" + ma_GIAI_DOAN_CONG_VIEC
					+ "' AND TEN_TAI_KHOAN='" + ten_TAI_KHOAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
