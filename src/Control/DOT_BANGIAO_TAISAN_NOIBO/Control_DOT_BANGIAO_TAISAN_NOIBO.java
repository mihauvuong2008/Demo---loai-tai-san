package Control.DOT_BANGIAO_TAISAN_NOIBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import Control.SYSTEM_LOG.Control_SYSTEM_LOG;
import Control.SYSTEM_LOG.Log_Library;
import DAO.DOT_BANGIAO_TAISAN_NOIBO;
import DAO.NGUOIDUNG;
import DAO.TAISAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_BANGIAO_TAISAN_NOIBO;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_BANGIAO_TAISAN_NOIBO;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_BANGIAO_TAISAN_NOIBO;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_BANGIAO_TAISAN_NOIBO;

public class Control_DOT_BANGIAO_TAISAN_NOIBO {
	private Control_SYSTEM_LOG cs;
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
				return (new Control_Tool(conn)).nextKey_TABLE("DOT_BANGIAO_TAISAN_NOIBO");
			return -1;
		}

		public int InsertDOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dg) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_Insert_DOT_BANGIAO_TAISAN_NOIBO(dg);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}

		public int InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(String Mataisan, DOT_BANGIAO_TAISAN_NOIBO dbtn)
				throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_DOT_BANGIAO_TAISAN_NOIBO())
						.InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(Mataisan, dbtn);
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

		public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_All_DOT_BANGIAO_TAISAN_NOIBO() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_BANGIAO_TAISAN_NOIBO()).getString_Tatca_DOT_BANGIAO_TAISAN_NOIBO();
				if (query == null)
					return null;
				ArrayList<DOT_BANGIAO_TAISAN_NOIBO> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_BANGIAO_TAISAN_NOIBO dts = (new Control_DAO_Build()).get_DOT_BANGIAO_TAISAN_NOIBO(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public DOT_BANGIAO_TAISAN_NOIBO get_DOT_BANGIAO_TAISAN_NOIBO(int ma_DOT_BANGIAO_TAISAN_NOIBO)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_get_DOT_BANGIAO_TAISAN_NOIBO(ma_DOT_BANGIAO_TAISAN_NOIBO);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				DOT_BANGIAO_TAISAN_NOIBO dgt = null;
				while (rs.next()) {
					dgt = (new Control_DAO_Build()).get_DOT_BANGIAO_TAISAN_NOIBO(rs);
				}
				rs.close();
				st.close();
				return dgt;
			}
			return null;
		}

		public DOT_BANGIAO_TAISAN_NOIBO get_DOT_BANGIAO_TAISAN_NOIBO_Gannhat(int ma_TAISAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_get_DOT_BANGIAO_TAISAN_NOIBO_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				DOT_BANGIAO_TAISAN_NOIBO dgt = null;
				while (rs.next()) {
					dgt = (new Control_DAO_Build()).get_DOT_BANGIAO_TAISAN_NOIBO(rs);
				}
				rs.close();
				st.close();
				return dgt;
			}
			return null;
		}

		public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_DOT_BANGIAO_TAISAN_NOIBO_list(Date begin, Date end,
				String string) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_Danhsach_DOT_BANGIAO_TAISAN_NOIBO(begin, end, string);
				if (query == null)
					return null;
				ArrayList<DOT_BANGIAO_TAISAN_NOIBO> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_BANGIAO_TAISAN_NOIBO dts = (new Control_DAO_Build()).get_DOT_BANGIAO_TAISAN_NOIBO(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_DOT_BANGIAO_TAISAN_NOIBO_list(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_BANGIAO_TAISAN_NOIBO())
						.get_DOT_BANGIAO_TAISAN_NOIBO_list(ma_TAISAN);
				if (query == null)
					return null;
				ArrayList<DOT_BANGIAO_TAISAN_NOIBO> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_BANGIAO_TAISAN_NOIBO dts = (new Control_DAO_Build()).get_DOT_BANGIAO_TAISAN_NOIBO(rs);
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
		public boolean update_DOT_GIAM_TAISAN(DOT_BANGIAO_TAISAN_NOIBO dgt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_Capnhat_DOT_BANGIAO_TAISAN_NOIBO(dgt);
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
		public boolean delete_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dgt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (dgt == null)
					return false;
				String query = (new query_Delete_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_Delete_DOT_BANGIAO_TAISAN_NOIBO(dgt);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(TAISAN ts, DOT_BANGIAO_TAISAN_NOIBO data)
				throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (data == null)
					return false;
				String query = (new query_Delete_DOT_BANGIAO_TAISAN_NOIBO())
						.getString_delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(ts, data);
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

	public Control_DOT_BANGIAO_TAISAN_NOIBO(NGUOIDUNG user) {
		conn = user.getConn();
		cs = new Control_SYSTEM_LOG(user);
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_All_DOT_BANGIAO_TAISAN_NOIBO() throws SQLException {
		ArrayList<DOT_BANGIAO_TAISAN_NOIBO> rs = getSelecter().get_All_DOT_BANGIAO_TAISAN_NOIBO();
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_DotThanhlyTaisan());
		return rs;
	}

	public int InsertDOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dg) throws SQLException {
		int rs = getInserter().InsertDOT_BANGIAO_TAISAN_NOIBO(dg);
		return rs;
	}

	public DOT_BANGIAO_TAISAN_NOIBO get_DOT_BANGIAO_TAISAN_NOIBO(int ma_DOTGIAM) throws SQLException {
		DOT_BANGIAO_TAISAN_NOIBO rs = getSelecter().get_DOT_BANGIAO_TAISAN_NOIBO(ma_DOTGIAM);
		return rs;
	}

	public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_DOT_BANGIAO_TAISAN_NOIBO_list(Date begin, Date end, String string)
			throws SQLException {
		ArrayList<DOT_BANGIAO_TAISAN_NOIBO> rs = getSelecter().get_DOT_BANGIAO_TAISAN_NOIBO_list(begin, end, string);
		return rs;
	}

	public boolean update_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dgt) throws SQLException {
		boolean rs = getUpdater().update_DOT_GIAM_TAISAN(dgt);
		return rs;
	}

	public boolean delete_DOT_BANGIAO_TAISAN_NOIBO(DOT_BANGIAO_TAISAN_NOIBO dgt) throws SQLException {
		boolean rs = getDeleter().delete_DOT_BANGIAO_TAISAN_NOIBO(dgt);
		return rs;
	}

	public void InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(String string, DOT_BANGIAO_TAISAN_NOIBO dbtn)
			throws SQLException {
		getInserter().InsertTAISAN_DOT_BANGIAO_TAISAN_NOIBO(string, dbtn);
	}

	public boolean delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(TAISAN ts, DOT_BANGIAO_TAISAN_NOIBO data)
			throws SQLException {
		boolean rs = getDeleter().delete_TAISAN_DOT_BANGIAO_TAISAN_NOIBO(ts, data);
		return rs;
	}

	public ArrayList<DOT_BANGIAO_TAISAN_NOIBO> get_DOT_BANGIAO_TAISAN_NOIBO_list(int ma_TAISAN) throws SQLException {
		ArrayList<DOT_BANGIAO_TAISAN_NOIBO> rs = getSelecter().get_DOT_BANGIAO_TAISAN_NOIBO_list(ma_TAISAN);
		return rs;
	}

	public DOT_BANGIAO_TAISAN_NOIBO get_DOT_BANGIAO_TAISAN_NOIBO_Gannhat(int ma_TAISAN) throws SQLException {
		DOT_BANGIAO_TAISAN_NOIBO rs = getSelecter().get_DOT_BANGIAO_TAISAN_NOIBO_Gannhat(ma_TAISAN);
		return rs;
	}

}
