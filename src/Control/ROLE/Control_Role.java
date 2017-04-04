package Control.ROLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.NGUOIDUNG;
import DAO.ROLE;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_ROLE;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_ROLE;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_ROLE;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_ROLE;

public class Control_Role {
	private final Connection conn;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	public final PrivilegeChecker pvc;

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

	public Control_Role(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<ROLE> getAll_ROLE() throws SQLException {
		return new Select().getAll_ROLE();
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("ROLE");
		return -1;
	}

	public boolean update_ROLE(ROLE r) throws SQLException {
		return getUpdater().update_ROLE(r);
	}

	public int insert_ROLE(ROLE r) throws SQLException {
		return getInserter().insert_ROLE(r);
	}

	public boolean remove_ROLE(ROLE r) throws SQLException {
		return getDeleter().remove_ROLE(r);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public int insert_ROLE(ROLE r) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_ROLE()).getString_ThemROLE(r);
				int nextkey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}
	}

	private class Select extends REAactivity {
		public ArrayList<ROLE> getAll_ROLE() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_ROLE()).getString_TatcaQuyen();
				ArrayList<ROLE> result = new ArrayList<>();
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				ROLE user = null;
				while (rs.next()) {
					user = (new Control_DAO_Build()).get_ROLE(rs);
					result.add(user);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_ROLE(ROLE r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_ROLE()).getString_Capnhat_ROLE(r);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	private class Delete extends DELactivity {
		public boolean remove_ROLE(ROLE r) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_ROLE()).getString_Xoa(r);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}
}
