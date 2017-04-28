package myControler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.HOSO_CUATOI;
import DAO.KY_HAN_THONGKE_XANG_DAU;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_HOSO_CUATOI;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_HOSO_CUATOI;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_HOSO_CUATOI;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_HOSO_CUATOI;

public class Control_HOSO_CUATOI {
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

	public Control_HOSO_CUATOI(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
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
		public int insert_HOSO_CUATOI(HOSO_CUATOI r) throws SQLException {
			if (r.getMA_TAPHOSO() <= 0)
				return -1;
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_HOSO_CUATOI()).getString_insert_HOSO_CUATOI(r);
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
		public ArrayList<HOSO_CUATOI> getAllData() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_HOSO_CUATOI()).getString_Tatca_HOSO_CUATOI();
				if (query == null)
					return null;
				ArrayList<HOSO_CUATOI> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				HOSO_CUATOI khxd = null;
				while (rs.next()) {
					khxd = (new Control_DAO_Build()).get_HOSO_CUATOI(rs);
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
		public boolean update_HOSO_CUATOI(HOSO_CUATOI r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_HOSO_CUATOI()).getString_Update_HOSO_CUATOI(r);
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
		public boolean remove_HOSO_CUATOI(HOSO_CUATOI i) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_HOSO_CUATOI()).getString_remove_HOSO_CUATOI(i);
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
			return (new Control_Tool(conn)).nextKey_TABLE("HOSO_CUATOI");
		return -1;
	}

	public int insert_HOSO_CUATOI(HOSO_CUATOI r) throws SQLException {
		return getInserter().insert_HOSO_CUATOI(r);
	}

	public ArrayList<HOSO_CUATOI> getAllData() throws SQLException {
		return getSelecter().getAllData();
	}

	public boolean remove_HOSO_CUATOI(HOSO_CUATOI i) throws SQLException {
		return getDeleter().remove_HOSO_CUATOI(i);
	}

	public boolean update_HOSO_CUATOI(HOSO_CUATOI r) throws SQLException {
		return getUpdater().update_HOSO_CUATOI(r);
	}

}
