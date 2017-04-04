package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class query_Delete_NGUOIDUNG_NGHIEMTHU {

	private static Log log = LogFactory.getLog(query_Delete_NGUOIDUNG_NGHIEMTHU.class);

	public String getString_XoaNguoidungNghiemthu(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC) {
		try {
			return "DELETE FROM  GIAI_DOAN_NGHIEM_THU_CAN_BO  WHERE MA_GIAI_DOAN_NGHIEM_THU='" + ma_GIAI_DOAN_CONG_VIEC
					+ "' AND TEN_TAI_KHOAN='" + ten_TAI_KHOAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
