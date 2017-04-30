package myControler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.HOSO_DAGUI;
import DAO.NGUOIDUNG;
import DAO.NGUOINHAN_HOSO_DAGUI;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_HOSO_DAGUI;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_HOSO_DAGUI;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_HOSO_DAGUI;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_HOSO_DAGUI;

public class Control_HOSO_DAGUI {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
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

	public Control_HOSO_DAGUI(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public int insert_HOSO_DAGUI(HOSO_DAGUI r) throws SQLException {
			if (r.getMA_TAPHOSO() <= 0)
				return -1;
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_HOSO_DAGUI()).getString_insert_HOSO_DAGUI(r);
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

		public int insert_HOSO_DAGUI(int key, String tentaikhoan) throws SQLException {
			if (key <= 0)
				return -1;
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_HOSO_DAGUI()).getString_GuiHoso(key, tentaikhoan);
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
		public ArrayList<HOSO_DAGUI> getAllData() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_HOSO_DAGUI()).getString_Tatca_HOSO_DAGUI();
				if (query == null)
					return null;
				ArrayList<HOSO_DAGUI> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				HOSO_DAGUI khxd = null;
				while (rs.next()) {
					khxd = (new Control_DAO_Build()).get_HOSO_DAGUI(rs);
					result.add(khxd);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<NGUOINHAN_HOSO_DAGUI> getNguoiNhan(HOSO_DAGUI hoso_Hagui) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_HOSO_DAGUI()).getString_getNguoiNhan(hoso_Hagui);
				if (query == null)
					return null;
				ArrayList<NGUOINHAN_HOSO_DAGUI> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				NGUOINHAN_HOSO_DAGUI khxd = null;
				while (rs.next()) {
					khxd = (new Control_DAO_Build()).getNGUOINHAN_HOSO_DAGUI(rs);
					result.add(khxd);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_HOSO_DAGUI(HOSO_DAGUI r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_HOSO_DAGUI()).getString_Update_HOSO_DAGUI(r);
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

		public boolean update_NGUOINHAN_HOSO_DAGUI(NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_HOSO_DAGUI())
						.getString_Update_NGUOINHAN_HOSO_DAGUI(nguoiNhanHosoDagui);
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
		public boolean remove_HOSO_DAGUI(HOSO_DAGUI i) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_HOSO_DAGUI()).getString_remove_HOSO_DAGUI(i);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return false;
			}
			return false;
		}
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("HOSO_DAGUI");
		return -1;
	}

	public int insert_HOSO_DAGUI(HOSO_DAGUI r) throws SQLException {
		return getInserter().insert_HOSO_DAGUI(r);
	}

	public ArrayList<HOSO_DAGUI> getAllData() throws SQLException {
		return getSelecter().getAllData();
	}

	public boolean remove_HOSO_DAGUI(HOSO_DAGUI i) throws SQLException {
		return getDeleter().remove_HOSO_DAGUI(i);
	}

	public boolean update_HOSO_DAGUI(HOSO_DAGUI r) throws SQLException {
		return getUpdater().update_HOSO_DAGUI(r);
	}

	public int send(int key, String tentaikhoan) throws SQLException {
		return getInserter().insert_HOSO_DAGUI(key, tentaikhoan);
	}

	public boolean update_NGUOINHAN_HOSO_DAGUI(NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui) throws SQLException {
		return getUpdater().update_NGUOINHAN_HOSO_DAGUI(nguoiNhanHosoDagui);
	}

	public ArrayList<NGUOINHAN_HOSO_DAGUI> getNguoiNhan(HOSO_DAGUI hoso_Hagui) throws SQLException {
		return getSelecter().getNguoiNhan(hoso_Hagui);
	}

}
