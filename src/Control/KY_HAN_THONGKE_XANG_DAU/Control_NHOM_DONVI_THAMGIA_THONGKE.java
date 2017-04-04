package Control.KY_HAN_THONGKE_XANG_DAU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.NGUOIDUNG;
import DAO.NHOM_DONVI_THAMGIA_THONGKE;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NHOM_DONVI_THAMGIA_THONGKE;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NHOM_DONVI_THAMGIA_THONGKE;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NHOM_DONVI_THAMGIA_THONGKE;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NHOM_DONVI_THAMGIA_THONGKE;

public class Control_NHOM_DONVI_THAMGIA_THONGKE {
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

	public Control_NHOM_DONVI_THAMGIA_THONGKE(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<NHOM_DONVI_THAMGIA_THONGKE> getAll_NHOM_DONVI_THAMGIA_THONGKE(int ma_Kyhan) throws SQLException {
		return getSelecter().getAll_NHOM_DONVI_THAMGIA_THONGKE(ma_Kyhan);
	}

	public boolean update_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
		return getUpdater().update_NHOM_DONVI_THAMGIA_THONGKE(r);
	}

	public int insert_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
		return getInserter().insert_NHOM_DONVI_THAMGIA_THONGKE(r);
	}

	public boolean remove_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
		return getDeleter().remove_NHOM_DONVI_THAMGIA_THONGKE(r);
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

		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("NHOM_DONVI_THAMGIA_THONGKE");
			return -1;
		}

		public int insert_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_NHOM_DONVI_THAMGIA_THONGKE())
						.getString_Them_NhomDonvi_Thamgia_Thongke(r);
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
		public ArrayList<NHOM_DONVI_THAMGIA_THONGKE> getAll_NHOM_DONVI_THAMGIA_THONGKE(int ma_Kyhan)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_NHOM_DONVI_THAMGIA_THONGKE())
						.getString_Tatca_NhomDonvi_Thamgia_Thongke(ma_Kyhan);
				if (query == null)
					return null;
				ArrayList<NHOM_DONVI_THAMGIA_THONGKE> result = new ArrayList<>();
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				NHOM_DONVI_THAMGIA_THONGKE nhom = null;
				while (rs.next()) {
					nhom = (new Control_DAO_Build()).get_NHOM_DONVI_THAMGIA_THONGKE(rs);
					result.add(nhom);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NHOM_DONVI_THAMGIA_THONGKE())
						.getString_Capnhat_NhomDonviThamgiaThongKe(r);
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
		public boolean remove_NHOM_DONVI_THAMGIA_THONGKE(NHOM_DONVI_THAMGIA_THONGKE r) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_NHOM_DONVI_THAMGIA_THONGKE())
						.getString_Delete_NhomDonvi_Thamgia_Thongke(r);
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
}
