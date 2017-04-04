package Control.LOAITAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LOAITAISAN_CAP_II;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_LOAITAISAN_CAP_II;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LOAITAISAN_CAP_II;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_II;

public class Control_LOAITAISAN_CAP_II {
	private final Connection conn;

	public Control_LOAITAISAN_CAP_II(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public ArrayList<LOAITAISAN_CAP_II> getAllData() throws SQLException {
		return new Select().getAllData();
	}

	public LOAITAISAN_CAP_II getNHOMTAISAN(int ma_NHOMTAISAN) throws SQLException {
		return new Select().getNHOMTAISAN(ma_NHOMTAISAN);
	}

	public void insert_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
		new Insert().insert_LOAITAISAN_CAP_II(n);
	}

	public boolean update_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
		return new Update().update_LOAITAISAN_CAP_II(n);
	}

	public boolean delete_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
		return new Delete().delete_LOAITAISAN_CAP_II(n);
	}

	class Insert {
		public boolean insert_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_LOAITAISAN_CAP_II()).getString_Them_LoaiTaisanCapII(n);
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
		public ArrayList<LOAITAISAN_CAP_II> getAllData() throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_II()).getString_Tatca_LoaiTaisanCapII();
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				ArrayList<LOAITAISAN_CAP_II> result = new ArrayList<>();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					LOAITAISAN_CAP_II lt = (new Control_DAO_Build()).get_LOAITAISAN_CAP_II(rs);
					result.add(lt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public LOAITAISAN_CAP_II getNHOMTAISAN(int ma_NHOMTAISAN) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_LOAITAISAN_CAP_II()).getString_LoaiTaisanCapII(ma_NHOMTAISAN);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				LOAITAISAN_CAP_II lt = new LOAITAISAN_CAP_II();
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					lt.setMA_LOAITAISAN_CAP_II(Integer.valueOf(rs.getInt("MA_LOAITAISAN_CAP_II")));
					lt.setTEN_LOAITAISAN_CAP_II(rs.getString("TEN_LOAITAISAN_CAP_II"));
					lt.setMA_LOAITAISAN_CAP_I(Integer.valueOf(rs.getInt("MA_LOAITAISAN_CAP_I")));
				}
				rs.close();
				st.close();
				return lt;
			}
			return null;
		}

	}

	class Update {
		public boolean update_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_LOAITAISAN_CAP_II()).getString_Capnhat_LoaiTaisanCapII(n);
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
		public boolean delete_LOAITAISAN_CAP_II(LOAITAISAN_CAP_II n) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_LOAITAISAN_CAP_II()).getString_Xoa(n);
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
