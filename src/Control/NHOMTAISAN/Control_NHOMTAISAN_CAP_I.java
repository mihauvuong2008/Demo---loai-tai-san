package Control.NHOMTAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NHOMTAISAN_CAP_I;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NHOMTAISAN_CAP_I;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NHOMTAISAN_CAP_I;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NHOMTAISAN_CAP_I;

public class Control_NHOMTAISAN_CAP_I {

	private final Connection conn;

	public Control_NHOMTAISAN_CAP_I(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<NHOMTAISAN_CAP_I> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public boolean insert_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
		return new Insert().insert_NHOMTAISAN_CAP_I(l);
	}

	public boolean update_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
		return new Update().update_NHOMTAISAN_CAP_I(l);
	}

	public boolean delete_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
		return new Delete().delete_NHOMTAISAN_CAP_I(l);
	}

	public NHOMTAISAN_CAP_I getNHOMTAISAN_CAP_I(int ma_NHOMTAISAN_CAP_I) throws SQLException {
		return new Select().getNHOMTAISAN_CAP_I(ma_NHOMTAISAN_CAP_I);
	}

	public boolean delete_All() throws SQLException {
		return new Delete().delete_All();
	}

	class Insert {
		public boolean insert_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NHOMTAISAN_CAP_I()).getString_ThemNhomTaisan(l);
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
		public ArrayList<NHOMTAISAN_CAP_I> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOMTAISAN_CAP_I()).getString_Tatca_NhomTaisanCapI();
				if (query == null)
					return null;
				ArrayList<NHOMTAISAN_CAP_I> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NHOMTAISAN_CAP_I lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_I(rs);
					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NHOMTAISAN_CAP_I getNHOMTAISAN_CAP_I(int ma_NHOMTAISAN_CAP_I) throws SQLException {
			if (conn != null) {
				String query = new query_Select_NHOMTAISAN_CAP_I().getString_NhomTaisancapI(ma_NHOMTAISAN_CAP_I);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				NHOMTAISAN_CAP_I lt = null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_I(rs);
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}
	}

	class Update {
		public boolean update_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NHOMTAISAN_CAP_I()).getString_Capnhat_NhomTaisanCapI(l);
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
		public boolean delete_NHOMTAISAN_CAP_I(NHOMTAISAN_CAP_I l) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOMTAISAN_CAP_I()).getString_Xoa(l);
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
				String query = (new query_Delete_NHOMTAISAN_CAP_I()).getDelete_All();
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
