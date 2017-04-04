package Control.HO_SO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.NGUOIDUNG;
import DAO.TAP_HO_SO;
import DAO.VANBAN;

public class Control_VANBAN {
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

	public Control_VANBAN(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<VANBAN> get_AllVanban(TAP_HO_SO ths) throws SQLException {
		return getSelecter().get_AllVanban(ths);
	}

	public VANBAN get_Vanban(int ma_VANBAN) throws SQLException {
		return getSelecter().get_Vanban(ma_VANBAN);
	}

	public int insert_VANBAN(VANBAN vb) throws SQLException {
		return getInserter().insert_VANBAN(vb);
	}

	public int update_VANBAN(VANBAN vb) throws SQLException {
		return getUpdater().update_VANBAN(vb);
	}

	public boolean delete_VANBAN(VANBAN vb) throws SQLException {
		return getDeleter().delete_VANBAN(vb);
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
		public int getNextKey() {
			if (conn != null) {
				return new Control_Tool(conn).nextKey_TABLE("VANBAN");
			}
			return -1;
		}

		public int insert_VANBAN(VANBAN vb) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(vb.getNGAY_BAN_HANH());
				String query = "INSERT INTO VANBAN ( SO_VANBAN, NGAY_BAN_HANH, CO_QUAN_BAN_HANH, TRICH_YEU, MA_TAPHOSO) VALUES('"
						+ vb.getSO_VANBAN() + "', '" + date + "','" + vb.getCO_QUAN_BAN_HANH() + "','"
						+ vb.getTRICH_YEU() + "','" + vb.getMA_TAPHOSO() + "');";
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
		public ArrayList<VANBAN> get_AllVanban(TAP_HO_SO ths) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<VANBAN> vbl = new ArrayList<>();
				String query = "SELECT * FROM VANBAN  WHERE MA_TAPHOSO = '" + ths.getMA_TAPHOSO() + "'; ";
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					VANBAN vb = new VANBAN();
					vb.setMA_VANBAN(rs.getInt("MA_VANBAN"));
					vb.setSO_VANBAN(rs.getString("SO_VANBAN"));
					vb.setNGAY_BAN_HANH(rs.getTimestamp("NGAY_BAN_HANH"));
					vb.setCO_QUAN_BAN_HANH(rs.getString("CO_QUAN_BAN_HANH"));
					vb.setTRICH_YEU(rs.getString("TRICH_YEU"));
					vbl.add(vb);
				}
				rs.close();
				st.close();
				return vbl;
			}
			return null;
		}

		public VANBAN get_Vanban(int ma_VANBAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				VANBAN vb = null;
				String query = "SELECT * FROM VANBAN  WHERE MA_VANBAN = '" + ma_VANBAN + "'; ";
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					vb = new VANBAN();
					vb.setMA_VANBAN(rs.getInt("MA_VANBAN"));
					vb.setSO_VANBAN(rs.getString("SO_VANBAN"));
					vb.setNGAY_BAN_HANH(rs.getTimestamp("NGAY_BAN_HANH"));
					vb.setCO_QUAN_BAN_HANH(rs.getString("CO_QUAN_BAN_HANH"));
					vb.setTRICH_YEU(rs.getString("TRICH_YEU"));
				}
				rs.close();
				st.close();
				return vb;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public int update_VANBAN(VANBAN vb) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String date;
				if (vb.getNGAY_BAN_HANH() != null)
					date = "'" + new SimpleDateFormat("yyyy-MM-dd").format(vb.getNGAY_BAN_HANH()) + "'";
				else
					date = null;
				String query = "UPDATE VANBAN SET SO_VANBAN='" + vb.getSO_VANBAN() + "', NGAY_BAN_HANH =" + date
						+ ", CO_QUAN_BAN_HANH='" + vb.getCO_QUAN_BAN_HANH() + "', TRICH_YEU='" + vb.getTRICH_YEU() + "'"
						+ " where MA_VANBAN='" + vb.getMA_VANBAN() + "';";
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return vb.getMA_VANBAN();
			}
			return -1;
		}
	}

	private class Delete extends DELactivity {

		public boolean delete_VANBAN(VANBAN vb) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = "DELETE FROM VANBAN  where MA_VANBAN='" + vb.getMA_VANBAN() + "';";
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
