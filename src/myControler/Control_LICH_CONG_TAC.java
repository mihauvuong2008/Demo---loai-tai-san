package myControler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import DAO.LICH_CONG_TAC;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LICH_CONG_TAC;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_LICH_CONG_TAC;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LICH_CONG_TAC;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LICH_CONG_TAC;

public class Control_LICH_CONG_TAC {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final PrivilegeChecker pvc;

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

	public Control_LICH_CONG_TAC(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("DOT_THUCHIEN_GIAM_TAISAN");
			return -1;
		}

		public int InsertLICH_CONG_TAC(LICH_CONG_TAC cd) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_LICH_CONG_TAC()).get_String_InsertLICH_CONG_TAC(cd);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}

	}

	private class Select extends REAactivity {

		public ArrayList<LICH_CONG_TAC> get_All_LICH_CONG_TAC() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LICH_CONG_TAC()).getString_Select_AllLICH_CONG_TAC();
				if (query == null)
					return null;
				ArrayList<LICH_CONG_TAC> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LICH_CONG_TAC dts = (new Control_DAO_Build()).get_LICH_CONG_TAC(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public LICH_CONG_TAC get_LICH_CONG_TAC(int ma_LICH_CONG_TAC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LICH_CONG_TAC()).getString_Select_LICH_CONG_TAC(ma_LICH_CONG_TAC);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				LICH_CONG_TAC dgt = null;
				while (rs.next()) {
					dgt = (new Control_DAO_Build()).get_LICH_CONG_TAC(rs);
				}
				rs.close();
				st.close();
				return dgt;
			}
			return null;
		}

		public ArrayList<LICH_CONG_TAC> get_LICH_CONG_TAC_list(Date begin) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LICH_CONG_TAC()).getString_Danhsach_LICH_CONG_TAC(begin);
				if (query == null)
					return null;
				ArrayList<LICH_CONG_TAC> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LICH_CONG_TAC dts = (new Control_DAO_Build()).get_LICH_CONG_TAC(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_LICH_CONG_TAC(LICH_CONG_TAC dgt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_LICH_CONG_TAC()).getString_Update_LICH_CONG_TAC(dgt);
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
		public boolean delete_LICH_CONG_TAC(LICH_CONG_TAC dgt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (dgt == null)
					return false;
				String query = (new query_Delete_LICH_CONG_TAC()).getString_XoaLICH_CONG_TAC(dgt);
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

	public int InsertLICH_CONG_TAC(LICH_CONG_TAC cd) throws SQLException {
		int rs = getInserter().InsertLICH_CONG_TAC(cd);
		return rs;
	}

	public ArrayList<LICH_CONG_TAC> get_All_LICH_CONG_TAC() throws SQLException {
		ArrayList<LICH_CONG_TAC> rs = getSelecter().get_All_LICH_CONG_TAC();
		return rs;
	}

	public ArrayList<LICH_CONG_TAC> get_LICH_CONG_TAC_list(Date d) throws SQLException {
		ArrayList<LICH_CONG_TAC> rs = getSelecter().get_LICH_CONG_TAC_list(d);
		return rs;
	}

	public LICH_CONG_TAC get_DOT_THUCHIEN_GIAM_TAISAN(int ma_LICH_CONG_TAC) throws SQLException {
		LICH_CONG_TAC rs = getSelecter().get_LICH_CONG_TAC(ma_LICH_CONG_TAC);
		return rs;
	}

	public boolean delete_LICH_CONG_TAC(LICH_CONG_TAC dgt) throws SQLException {
		boolean rs = getDeleter().delete_LICH_CONG_TAC(dgt);
		return rs;
	}

	public boolean update_LICH_CONG_TAC(LICH_CONG_TAC dgt) throws SQLException {
		boolean rs = getUpdater().update_LICH_CONG_TAC(dgt);
		return rs;
	}

}
