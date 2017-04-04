package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class query_Delete_QUATRINH_NGHIEMTHU_QUYETTOAN {

	private static Log log = LogFactory.getLog(query_Delete_QUATRINH_NGHIEMTHU_QUYETTOAN.class);

	public String getString_Xoa(int ma_QUATRINH_NGHIEMTHU_QUYETTOAN) {
		try {
			return "DELETE FROM QUATRINH_NGHIEMTHU_QUYETTOAN  WHERE ma_QUATRINH_NGHIEMTHU_QUYETTOAN='"
					+ ma_QUATRINH_NGHIEMTHU_QUYETTOAN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
