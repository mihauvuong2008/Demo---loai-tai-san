package Control.NHOMTAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_III;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NHOMTAISAN_CAP_III;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NHOMTAISAN_CAP_III;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NHOMTAISAN_CAP_III;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NHOMTAISAN_CAP_III;

public class Control_NHOMTAISAN_CAP_III {

	private final Connection conn;

	public Control_NHOMTAISAN_CAP_III(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<NHOMTAISAN_CAP_III> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public boolean insert_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
		return new Insert().insert_NHOMTAISAN_CAP_III(l);
	}

	public boolean update_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
		return new Update().update_NHOMTAISAN_CAP_III(l);
	}

	public boolean delete_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
		return new Delete().delete_NHOMTAISAN_CAP_III(l);
	}

	public NHOMTAISAN_CAP_III getNHOMTAISAN_CAP_III(int ma_NHOMTAISAN_CAP_III) throws SQLException {
		return new Select().getNHOMTAISAN_CAP_III(ma_NHOMTAISAN_CAP_III);
	}

	public boolean delete_All() throws SQLException {
		return new Delete().delete_All();
	}

	class Insert {
		public boolean insert_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NHOMTAISAN_CAP_III()).getString_ThemNhomTaisanCapIII(l);
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
		public ArrayList<NHOMTAISAN_CAP_III> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOMTAISAN_CAP_III()).getString_TatcaNhomTaisanCapIII();
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				ArrayList<NHOMTAISAN_CAP_III> result = new ArrayList<>();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					NHOMTAISAN_CAP_III lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_III(rs);

					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NHOMTAISAN_CAP_III getNHOMTAISAN_CAP_III(int ma_NHOMTAISAN_CAP_III) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOMTAISAN_CAP_III())
						.getString_NhomTaisanCapIII(ma_NHOMTAISAN_CAP_III);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				NHOMTAISAN_CAP_III lt = new NHOMTAISAN_CAP_III();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_III(rs);
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}
	}

	class Update {
		public boolean update_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NHOMTAISAN_CAP_III()).getString_CapnhatNhomTaisanCapIII(l);
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
		public boolean delete_NHOMTAISAN_CAP_III(NHOMTAISAN_CAP_III l) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOMTAISAN_CAP_III()).getString_Xoa(l);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean delete_All() throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOMTAISAN_CAP_III()).getDelete_All();
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
