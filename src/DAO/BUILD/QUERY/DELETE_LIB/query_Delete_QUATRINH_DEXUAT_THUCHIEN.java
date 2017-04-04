package DAO.BUILD.QUERY.DELETE_LIB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class query_Delete_QUATRINH_DEXUAT_THUCHIEN {

	private static Log log = LogFactory.getLog(query_Delete_QUATRINH_DEXUAT_THUCHIEN.class);

	public String getString_Xoa(int ma_QUATRINH_DEXUAT_THUCHIEN) {
		try {
			return "DELETE FROM QUATRINH_DEXUAT_THUCHIEN  WHERE ma_QUATRINH_DEXUAT_THUCHIEN='"
					+ ma_QUATRINH_DEXUAT_THUCHIEN + "';";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
