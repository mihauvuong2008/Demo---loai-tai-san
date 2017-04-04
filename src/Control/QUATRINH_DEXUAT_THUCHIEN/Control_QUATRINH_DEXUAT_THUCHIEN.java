package Control.QUATRINH_DEXUAT_THUCHIEN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ControlTool.Control_Tool;
import DAO.NGUOIDUNG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_QUATRINH_DEXUAT_THUCHIEN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_QUATRINH_DEXUAT_THUCHIEN;

public class Control_QUATRINH_DEXUAT_THUCHIEN {
	private final Connection conn;

	public Control_QUATRINH_DEXUAT_THUCHIEN(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public int insert_QUATRINH_DEXUAT_THUCHIEN(QUATRINH_DEXUAT_THUCHIEN qdt) throws SQLException {
		return new Insert().insert_QUATRINH_DEXUAT_THUCHIEN(qdt);
	}

	public boolean delete_QUATRINH_DEXUAT_THUCHIEN(int ma_QUATRINH_DEXUAT_THUCHIEN) throws SQLException {
		return new Delete().delete_QUATRINH_DEXUAT_THUCHIEN(ma_QUATRINH_DEXUAT_THUCHIEN);
	}

	class Insert {
		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("QUATRINH_DEXUAT_THUCHIEN");
			return -1;
		}

		public int insert_QUATRINH_DEXUAT_THUCHIEN(QUATRINH_DEXUAT_THUCHIEN qdt) throws SQLException {
			if (conn != null) {
				int nextKey = getNextKey();
				String query = (new query_Insert_QUATRINH_DEXUAT_THUCHIEN()).getString_ThemQuatrinhDexuatThuchien(qdt);
				if (query == null)
					return -1;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextKey;
			}
			return -1;
		}

	}

	class Select {
	}

	class Update {
	}

	class Delete {
		public boolean delete_QUATRINH_DEXUAT_THUCHIEN(int ma_QUATRINH_DEXUAT_THUCHIEN) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_QUATRINH_DEXUAT_THUCHIEN()).getString_Xoa(ma_QUATRINH_DEXUAT_THUCHIEN);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;

		}
	}
}
