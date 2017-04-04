package Control.QUATRINH_NGHIEMTHU_QUYETTOAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ControlTool.Control_Tool;
import DAO.NGUOIDUNG;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_QUATRINH_NGHIEMTHU_QUYETTOAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_QUATRINH_NGHIEMTHU_QUYETTOAN;

public class Control_QUATRINH_NGHIEMTHU_QUYETTOAN {
	private final Connection conn;

	public Control_QUATRINH_NGHIEMTHU_QUYETTOAN(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public boolean delete_QUATRINH_NGHIEMTHU_QUYETTOAN(int ma_QUATRINH_NGHIEMTHU_QUYETTOAN) throws SQLException {
		return new Delete().delete_QUATRINH_NGHIEMTHU_QUYETTOAN(ma_QUATRINH_NGHIEMTHU_QUYETTOAN);
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("QUATRINH_NGHIEMTHU_QUYETTOAN");
		return -1;
	}

	public int create_QUATRINH_NGHIEMTHU_QUYETTOAN(String string) throws SQLException {
		return new Select().create_QUATRINH_NGHIEMTHU_QUYETTOAN(string);
	}

	class Insert {
	}

	class Select {
		public int create_QUATRINH_NGHIEMTHU_QUYETTOAN(String string) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_QUATRINH_NGHIEMTHU_QUYETTOAN())
						.getString_ThemQuatrinhNghiemthuQuyettoan(string);
				if (query == null)
					return -1;
				int getNextKey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return getNextKey;
			}
			return -1;
		}

	}

	class Update {
	}

	class Delete {
		public boolean delete_QUATRINH_NGHIEMTHU_QUYETTOAN(int ma_QUATRINH_NGHIEMTHU_QUYETTOAN) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_QUATRINH_NGHIEMTHU_QUYETTOAN())
						.getString_Xoa(ma_QUATRINH_NGHIEMTHU_QUYETTOAN);
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
