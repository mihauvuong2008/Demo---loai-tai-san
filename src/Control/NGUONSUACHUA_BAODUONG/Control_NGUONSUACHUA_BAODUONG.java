package Control.NGUONSUACHUA_BAODUONG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NGUONSUACHUA_BAODUONG;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUONSUACHUA_BAODUONG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUONSUACHUA_BAODUONG;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUONSUACHUA_BAODUONG;

public class Control_NGUONSUACHUA_BAODUONG {
	private final Connection conn;

	public Control_NGUONSUACHUA_BAODUONG(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public int getNextKey() {
		if (conn != null)
			return new Control_Tool(conn).nextKey_TABLE("NGUONSUACHUA_BAODUONG");
		return -1;
	}

	public NGUONSUACHUA_BAODUONG get_NguonSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return new Select().get_NguonSuachua_Baoduong(dsb);
	}

	public ArrayList<NGUONSUACHUA_BAODUONG> getAllData(String pattern) throws SQLException {
		return new Select().getAllData(pattern);
	}

	public int update_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG nt) throws SQLException {
		return new Update().update_NGUONSUACHUA_BAODUONG(nt);
	}

	public int Insert_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG nt) throws SQLException {
		return new Insert().Insert_NGUONSUACHUA_BAODUONG(nt);
	}

	public boolean delete_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG ng) throws SQLException {
		return new Delete().delete_NGUONSUACHUA_BAODUONG(ng);
	}

	class Insert {

		public int Insert_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NGUONSUACHUA_BAODUONG()).getString_ThemNguonSuachuaBaoduong(nt);
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
		public ArrayList<NGUONSUACHUA_BAODUONG> getAllData(String pattern) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NGUONSUACHUA_BAODUONG()).getString_Tatca_NguonSuachuaBaoduong(pattern);
				if (query == null)
					return null;
				ArrayList<NGUONSUACHUA_BAODUONG> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NGUONSUACHUA_BAODUONG nt = (new Control_DAO_Build()).get_NGUONSUACHUA_BAODUONG(rs);
					result.add(nt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NGUONSUACHUA_BAODUONG get_NguonSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NGUONSUACHUA_BAODUONG()).getString_NguonSuachuaBaoduong(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				NGUONSUACHUA_BAODUONG nt = null;
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONSUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}
	}

	class Update {

		public int update_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NGUONSUACHUA_BAODUONG()).getString_Capnhat_NguonSuachua_Baoduong(nt);
				if (query == null)
					return -1;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nt.getMA_NGUONSUACHUA_BAODUONG();
			}
			return -1;
		}
	}

	class Delete {
		public boolean delete_NGUONSUACHUA_BAODUONG(NGUONSUACHUA_BAODUONG ng) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NGUONSUACHUA_BAODUONG()).getString_XoaNguonSuachua_Baoduong(ng);
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
