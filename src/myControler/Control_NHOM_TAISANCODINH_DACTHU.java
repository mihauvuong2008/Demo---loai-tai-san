package myControler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.NHOM_TAISANCODINH_DACTHU;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NHOM_TAISANCODINH;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NHOM_TAISANCODINH;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NHOM_TAISANCODINH;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NHOM_TAISANCODINH;

public class Control_NHOM_TAISANCODINH_DACTHU {

	private final Connection conn;

	public Control_NHOM_TAISANCODINH_DACTHU(NGUOIDUNG user) {
		conn = user.getConn();
	}

	class Insert {
		public boolean insert_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NHOM_TAISANCODINH()).getString_ThemNHOM_TAISANCODINH_DACTHU(l);
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
		public ArrayList<NHOM_TAISANCODINH_DACTHU> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NHOM_TAISANCODINH()).getString_Tatca_NHOM_TAISANCODINH_DACTHU();
				if (query == null)
					return null;
				ArrayList<NHOM_TAISANCODINH_DACTHU> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NHOM_TAISANCODINH_DACTHU lt = (new Control_DAO_Build()).get_NHOM_TAISANCODINH_DACTHU(rs);
					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NHOM_TAISANCODINH_DACTHU getNHOM_TAISANCODINH_DACTHU(int ma_NHOM_TAISANCODINH_DACTHU)
				throws SQLException {
			if (conn != null) {
				String query = new query_Select_NHOM_TAISANCODINH()
						.getString_NHOM_TAISANCODINH_DACTHU(ma_NHOM_TAISANCODINH_DACTHU);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				NHOM_TAISANCODINH_DACTHU lt = null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					lt = (new Control_DAO_Build()).get_NHOM_TAISANCODINH_DACTHU(rs);
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}
	}

	class Update {
		public boolean update_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NHOM_TAISANCODINH()).getString_Capnhat_NHOM_TAISANCODINH_DACTHU(l);
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
		public boolean delete_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOM_TAISANCODINH()).getString_XoaNHOM_TAISANCODINH_DACTHU(l);
				if (query == null)
					return false;
				System.out.println(query);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean delete_All_NHOM_TAISANCODINH_DACTHU() throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NHOM_TAISANCODINH()).getDelete_All_NHOM_TAISANCODINH_DACTHU();
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

	public ArrayList<NHOM_TAISANCODINH_DACTHU> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public boolean insert_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
		return new Insert().insert_NHOM_TAISANCODINH_DACTHU(l);
	}

	public boolean update_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
		return new Update().update_NHOM_TAISANCODINH_DACTHU(l);
	}

	public boolean delete_NHOM_TAISANCODINH_DACTHU(NHOM_TAISANCODINH_DACTHU l) throws SQLException {
		return new Delete().delete_NHOM_TAISANCODINH_DACTHU(l);
	}

	public NHOM_TAISANCODINH_DACTHU getNHOM_TAISANCODINH_DACTHU(int ma_NHOMTAISAN_CAP_I) throws SQLException {
		return new Select().getNHOM_TAISANCODINH_DACTHU(ma_NHOMTAISAN_CAP_I);
	}

	public boolean delete_All_NHOM_TAISANCODINH_DACTHU() throws SQLException {
		return new Delete().delete_All_NHOM_TAISANCODINH_DACTHU();
	}
}
