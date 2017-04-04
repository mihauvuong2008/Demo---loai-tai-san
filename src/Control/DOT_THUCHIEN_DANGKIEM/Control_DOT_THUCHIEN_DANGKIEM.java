package Control.DOT_THUCHIEN_DANGKIEM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_DANGKIEM;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_THUCHIEN_DANGKIEM;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_THUCHIEN_DANGKIEM;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_DANGKIEM;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_THUCHIEN_DANGKIEM;

public class Control_DOT_THUCHIEN_DANGKIEM {

	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final PrivilegeChecker pvc;
	// private final Control_SYSTEM_LOG cs;

	public final Insert getInserter() {
		if (inserter == null)
			inserter = new Insert();
		return inserter;
	}

	public final Select getSelecter() {
		if (selecter == null)
			selecter = new Select();
		return selecter;
	}

	public final Update getUpdater() {
		if (updater == null)
			updater = new Update();
		return updater;
	}

	public final Delete getDeleter() {
		if (deleter == null)
			deleter = new Delete();
		return deleter;
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		// Lấy khóa tiếp theo của Ba?ng (auto increment)
		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("KY_HAN_DANGKIEM");
			return -1;
		}

		public int insert_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM cd) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextKey = getNextKey();
				String query = (new query_Insert_DOT_THUCHIEN_DANGKIEM()).get_String_InsertDOT_THUCHIEN_DANGKIEM(cd);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextKey;
			}
			return -1;
		}
	}

	private class Select extends REAactivity {

		@SuppressWarnings("unused")
		public ArrayList<DOT_THUCHIEN_DANGKIEM> get_AllDOT_THUCHIEN_DANGKIEM() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_DANGKIEM> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_DANGKIEM()).getString_Select_AllDOT_THUCHIEN_DANGKIEM();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_DANGKIEM dx = (new Control_DAO_Build()).get_DOT_THUCHIEN_DANGKIEM(rs);
					result.add(dx);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_DANGKIEM> get_AllDOT_THUCHIEN_DANGKIEM(String key) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_DANGKIEM> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_DANGKIEM())
						.getString_Select_AllDOT_THUCHIEN_DANGKIEM(key);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_DANGKIEM dx = (new Control_DAO_Build()).get_DOT_THUCHIEN_DANGKIEM(rs);
					result.add(dx);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public DOT_THUCHIEN_DANGKIEM get_DOT_THUCHIEN_DANGKIEM_GANNHAT(PHUONGTIEN_GIAOTHONG pg) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_DANGKIEM result = null;
				String query = (new query_Select_DOT_THUCHIEN_DANGKIEM())
						.getString_Select_DOT_THUCHIEN_DANGKIEM_GANNHAT(pg);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_DOT_THUCHIEN_DANGKIEM(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {

		public boolean update_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_DANGKIEM()).getString_Update_DOT_THUCHIEN_DANGKIEM(r);
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

	private class Delete extends DELactivity {

		public boolean remove_CHUKY_DANGKIEM(DOT_THUCHIEN_DANGKIEM cd) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_DOT_THUCHIEN_DANGKIEM()).getString_XoaDOT_THUCHIEN_DANGKIEM(cd);
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

	public Control_DOT_THUCHIEN_DANGKIEM(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
		// cs = new Control_SYSTEM_LOG(user);
	}

	public boolean remove_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM i) throws SQLException {
		boolean rs = getDeleter().remove_CHUKY_DANGKIEM(i);
		return rs;
	}

	public int insert_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM cd) throws SQLException {
		int key = getInserter().insert_DOT_THUCHIEN_DANGKIEM(cd);
		return key;
	}

	public boolean update_DOT_THUCHIEN_DANGKIEM(DOT_THUCHIEN_DANGKIEM r) throws SQLException {
		boolean rs = getUpdater().update_DOT_THUCHIEN_DANGKIEM(r);
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_DANGKIEM> get_AllDOT_THUCHIEN_DANGKIEM(String key) throws SQLException {
		ArrayList<DOT_THUCHIEN_DANGKIEM> rs = getSelecter().get_AllDOT_THUCHIEN_DANGKIEM(key);
		return rs;
	}

	public DOT_THUCHIEN_DANGKIEM get_DOT_THUCHIEN_DANGKIEM_GANNHAT(PHUONGTIEN_GIAOTHONG pg) throws SQLException {
		DOT_THUCHIEN_DANGKIEM rs = getSelecter().get_DOT_THUCHIEN_DANGKIEM_GANNHAT(pg);
		return rs;
	}
}