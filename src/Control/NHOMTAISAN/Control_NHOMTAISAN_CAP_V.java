package Control.NHOMTAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_V;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NHOMTAISAN_CAP_V;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NHOMTAISAN_CAP_V;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NHOMTAISAN_CAP_V;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NHOMTAISAN_CAP_V;

public class Control_NHOMTAISAN_CAP_V {

	private final Connection conn;

	public Control_NHOMTAISAN_CAP_V(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<NHOMTAISAN_CAP_V> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public boolean insert_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
		return new Insert().insert_NHOMTAISAN_CAP_V(l);
	}

	public boolean update_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
		return new Update().update_NHOMTAISAN_CAP_V(l);
	}

	public boolean delete_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
		return new Delete().delete_NHOMTAISAN_CAP_V(l);
	}

	public NHOMTAISAN_CAP_V getNHOMTAISAN_CAP_V(int ma_NHOMTAISAN_CAP_V) throws SQLException {
		return new Select().getNHOMTAISAN_CAP_V(ma_NHOMTAISAN_CAP_V);
	}

	public boolean delete_All() throws SQLException {
		return new Delete().delete_All();
	}

	class Insert {
		public boolean insert_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NHOMTAISAN_CAP_V()).getString_ThemNhomTaisanCapV(l);
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
		public NHOMTAISAN_CAP_V getNHOMTAISAN_CAP_V(int ma_NHOMTAISAN_CAP_V) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOMTAISAN_CAP_V()).getString_NhomTaisanCapV(ma_NHOMTAISAN_CAP_V);
				if (query == null)
					return null;
				NHOMTAISAN_CAP_V lt = new NHOMTAISAN_CAP_V();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_V(rs);
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}

		public ArrayList<NHOMTAISAN_CAP_V> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOMTAISAN_CAP_V()).getString_TatcaNhomTaisanCapV();
				if (query == null)
					return null;
				ArrayList<NHOMTAISAN_CAP_V> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NHOMTAISAN_CAP_V lt = (new Control_DAO_Build()).get_NHOMTAISAN_CAP_V(rs);
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
		public boolean update_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NHOMTAISAN_CAP_V()).getString_Capnhat_NHomTaisanCapV(l);
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
		public boolean delete_NHOMTAISAN_CAP_V(NHOMTAISAN_CAP_V l) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOMTAISAN_CAP_V()).getString_Xoa(l);
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
				String query = (new query_Delete_NHOMTAISAN_CAP_V()).getString_Delete_All();
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
