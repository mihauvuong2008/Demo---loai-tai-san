package Control.ROLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DAO.NGUOIDUNG;
import DAO.ROLE;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_ROLE_CAN_BO;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_ROLE_CAN_BO;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_ROLE_CAN_BO;

public class Control_Role_User {
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

	public Control_Role_User(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public boolean Add_Role(NGUOIDUNG nd, ROLE r) throws SQLException {
		return new Insert().Add_Role(nd, r);
	}

	public ROLE getROLE_User(NGUOIDUNG user) throws SQLException {
		/**
		 * Only System access
		 */
		return new SystemAccess().getROLE_User(user);
	}

	public boolean Remove_Role(NGUOIDUNG nd, ROLE r) throws SQLException {
		return getDeleter().Remove_Role(nd, r);
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

		public boolean Add_Role(NGUOIDUNG nd, ROLE r) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_ROLE_CAN_BO()).getString_ThemNguoiDung_duoc_CapQuyen(nd, r);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	private class SystemAccess {
		/**
		 * System always allow access data to get information role - user
		 * 
		 * @throws SQLException
		 */
		public ROLE getROLE_User(NGUOIDUNG user) throws SQLException {

			if (user != null /* && isPrivilegeREA() */) {
				ROLE result = null;
				String query = (new query_Select_ROLE_CAN_BO()).getString_getRole(user);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_ROLE(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Select extends REAactivity {

	}

	private class Update extends EDIactivity {
	}

	class Delete extends DELactivity {
		public boolean Remove_Role(NGUOIDUNG nd, ROLE r) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_ROLE_CAN_BO()).getString_Xoa_Quyen_Daphan_ChoNguoidung(nd, r);
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
