package Control.PHONGBAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.PHONGBAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_PHONGBAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_PHONGBAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_PHONGBAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_PHONGBAN;
import DAO.NGUOIDUNG;

public class Control_PHONGBAN {
	private final NGUOIDUNG user;
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

	public Control_PHONGBAN(NGUOIDUNG user) {
		this.user = user;
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<PHONGBAN> getAllDonvi() throws SQLException {
		return getSelecter().getAllDonvi();
	}

	public PHONGBAN get_PHONGBAN(Integer Ma_PHONGBAN) throws SQLException {
		return getSelecter().get_PHONGBAN(Ma_PHONGBAN);
	}

	public boolean update_PHONGBAN(PHONGBAN p) throws SQLException {
		return getUpdater().update_PHONGBAN(p);
	}

	public int getNextKey() {
		if (user != null)
			return new Control_Tool(conn).nextKey_TABLE("PHONGBAN");
		return -1;
	}

	public int insert_PHONBAN(PHONGBAN p) throws SQLException {
		return getInserter().insert_PHONBAN(p);
	}

	public boolean delete_PHONGBAN(PHONGBAN p) throws SQLException {
		return getDeleter().delete_PHONGBAN(p);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public int insert_PHONBAN(PHONGBAN p) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_PHONGBAN()).getString_ThemPhongban(p);
				if (query == null)
					return -1;
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
		public ArrayList<PHONGBAN> getAllDonvi() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHONGBAN> result = new ArrayList<>();
				String query = (new query_Select_PHONGBAN()).getString_TatcaPhongban();
				if (query == null)
					return null;
				Statement st;
				st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					PHONGBAN donvi_Quanly = (new Control_DAO_Build()).get_PHONGBAN(rs);
					result.add(donvi_Quanly);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public PHONGBAN get_PHONGBAN(Integer Ma_PHONGBAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_PHONGBAN()).getString_Phongban(Ma_PHONGBAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				PHONGBAN donvi_Sudung = new PHONGBAN();
				while (rs.next()) {
					donvi_Sudung = (new Control_DAO_Build()).get_PHONGBAN(rs);
					break;
				}
				rs.close();
				st.close();
				return donvi_Sudung;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_PHONGBAN(PHONGBAN p) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_PHONGBAN()).getString_CapnhatPhongban(p);
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

	private class Delete extends DELactivity {
		public boolean delete_PHONGBAN(PHONGBAN p) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_PHONGBAN()).getString_Xoa(p);
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
