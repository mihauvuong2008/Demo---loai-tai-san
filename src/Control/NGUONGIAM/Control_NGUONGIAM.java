package Control.NGUONGIAM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NGUONGIAM;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUONGIAM;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUONGIAM;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUONGIAM;

public class Control_NGUONGIAM {
	private final Connection conn;

	public Control_NGUONGIAM(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<NGUONGIAM> get_AllNguonGiam(String pattern) throws SQLException {
		return new Select().get_AllNguonGiam(pattern);
	}

	public NGUONGIAM get_NguonGiamtaisan(int ma_NGUONGIAM) throws SQLException {
		new Select().get_NguonGiamtaisan(ma_NGUONGIAM);
		return null;
	}

	public int Insert_NGUONGIAM(NGUONGIAM ng) throws SQLException {
		return new Insert().Insert_NGUONGIAM(ng);
	}

	public int getNextKey() {
		if (conn != null)
			return new Control_Tool(conn).nextKey_TABLE("NGUONGIAM");
		return -1;
	}

	public NGUONGIAM get_NguonGiam(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		return new Select().get_NguonGiam(dgt);
	}

	public boolean delete_NGUONGIAM(NGUONGIAM ng) throws SQLException {
		return new Delete().delete_NGUONGIAM(ng);
	}

	public int update_NGUONGIAM(NGUONGIAM nt) throws SQLException {
		return new Update().update_NGUONGIAM(nt);
	}

	class Insert {
		public int Insert_NGUONGIAM(NGUONGIAM ng) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NGUONGIAM()).getString_ThemNguongiam(ng);
				if (query == null)
					return -1;
				int nextkey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}
	}

	class Select {
		public ArrayList<NGUONGIAM> get_AllNguonGiam(String pattern) throws SQLException {
			if (conn != null) {
				ArrayList<NGUONGIAM> result = new ArrayList<>();
				String query = (new query_Select_NGUONGIAM()).getString_AllNguongiam(pattern);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					NGUONGIAM nt = (new Control_DAO_Build()).get_NGUONGIAM(rs);
					result.add(nt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NGUONGIAM get_NguonGiamtaisan(int ma_NGUONGIAM) throws SQLException {
			if (conn != null) {
				NGUONGIAM nt = null;
				String query = (new query_Select_NGUONGIAM()).getString_Nguongiam(ma_NGUONGIAM);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONGIAM(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}

		public NGUONGIAM get_NguonGiam(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NGUONGIAM()).getString_Nguongiam(dgt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				NGUONGIAM nt = null;
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONGIAM(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}
	}

	class Update {
		public int update_NGUONGIAM(NGUONGIAM nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NGUONGIAM()).getString_Capnhat_Nguongiam(nt);
				if (query == null)
					return -1;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nt.getMA_NGUONGIAM();
			}
			return -1;
		}
	}

	class Delete {
		public boolean delete_NGUONGIAM(NGUONGIAM ng) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NGUONGIAM()).getString_XoaNguongiam(ng);
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
