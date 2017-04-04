package Control.LOAITAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.LOAITAISAN_CAP_I;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LOAITAISAN_CAP_I;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LOAITAISAN_CAP_I;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LOAITAISAN_CAP_I;

public class Control_LOAITAISAN_CAP_I {
	private final Connection conn;

	public Control_LOAITAISAN_CAP_I(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<LOAITAISAN_CAP_I> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public boolean insert_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
		return new Insert().insert_LOAITAISAN_CAP_I(l);
	}

	public boolean update_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
		return new Update().update_LOAITAISAN_CAP_I(l);
	}

	public boolean delete_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
		return new Delete().delete_LOAITAISAN_CAP_I(l);
	}

	class Insert {
		public boolean insert_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_I()).getString_LoaiTaisanCapI(l);
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

	class Select {
		public ArrayList<LOAITAISAN_CAP_I> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_I()).getString_Tatca_LoaiTaisanCapI();
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				ArrayList<LOAITAISAN_CAP_I> result = new ArrayList<>();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					LOAITAISAN_CAP_I lt = (new Control_DAO_Build()).get_LOAITAISAN_CAP_I(rs);
					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	class Update {
		public boolean update_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_LOAITAISAN_CAP_I()).getString_Capnhat_LoaiTaisanCapI(l);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}

	class Delete {
		public boolean delete_LOAITAISAN_CAP_I(LOAITAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_LOAITAISAN_CAP_I()).getString_Delete_LoaiTaisanCapI(l);
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
