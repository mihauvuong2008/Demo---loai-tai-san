package Control.LOAITAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LOAITAISAN_CAP_III;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_LOAITAISAN_CAP_III;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LOAITAISAN_CAP_III;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LOAITAISAN_CAP_III;
import DAO.LOAITAISAN_CAP_III;

public class Control_LOAITAISAN_CAP_III {
	private final Connection conn;

	public Control_LOAITAISAN_CAP_III(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<LOAITAISAN_CAP_III> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public LOAITAISAN_CAP_III get_LOAITAISAN_CAP_III(int MA_LOAITAISAN_CAP_III) throws SQLException {
		return new Select().get_LOAITAISAN_CAP_III(MA_LOAITAISAN_CAP_III);
	}

	public boolean insert_LOAITAISAN_CAP_III(LOAITAISAN_CAP_III n) throws SQLException {
		return new Insert().insert_LOAITAISAN_CAP_III(n);
	}

	public boolean update_PHAN_NHOMTAISAN(LOAITAISAN_CAP_III n) throws SQLException {
		return new Update().update_PHAN_NHOMTAISAN(n);
	}

	public boolean delete_LOAITAISAN_CAP_III(LOAITAISAN_CAP_III n) throws SQLException {
		return new Delete().delete_LOAITAISAN_CAP_III(n);
	}

	class Insert {
		public boolean insert_LOAITAISAN_CAP_III(LOAITAISAN_CAP_III n) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_LOAITAISAN_CAP_III()).getString_Them_LoaiTaisanCapIII(n);
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
		public ArrayList<LOAITAISAN_CAP_III> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_III()).getString_Tatca_LoaiTaisanCapIII();
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				ArrayList<LOAITAISAN_CAP_III> result = new ArrayList<>();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					LOAITAISAN_CAP_III lt = (new Control_DAO_Build()).get_LOAITAISAN_CAP_III(rs);
					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public LOAITAISAN_CAP_III get_LOAITAISAN_CAP_III(int MA_LOAITAISAN_CAP_III) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_III())
						.getString_LoaiTaisanCapIII(MA_LOAITAISAN_CAP_III);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				LOAITAISAN_CAP_III lt = null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					lt = (new Control_DAO_Build()).get_LOAITAISAN_CAP_III(rs);
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}
	}

	class Update {
		public boolean update_PHAN_NHOMTAISAN(LOAITAISAN_CAP_III n) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_LOAITAISAN_CAP_III()).getString_Capnhat_LoaiTaisanCapIII(n);
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

		public boolean delete_LOAITAISAN_CAP_III(LOAITAISAN_CAP_III n) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_LOAITAISAN_CAP_III()).getString_Xoa(n);
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
