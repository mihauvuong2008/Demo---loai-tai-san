package Control.HO_SO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ROLE.PrivilegeChecker;
import DAO.HOSO_ROW;
import DAO.NGUOIDUNG;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;

public class Control_Hoso_Row {

	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final MyDateFormat mdf = new MyDateFormat();
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

	public Control_Hoso_Row(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<HOSO_ROW> get_AllData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_AllData(start, end, searchString);
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
	}

	private class Select extends REAactivity {
		public ArrayList<HOSO_ROW> get_AllData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT MA_TAPHOSO, TEN_TAPHOSO, NGAY_TAO_TAPHOSO, GIOITHIEU_TAPHOSO  FROM TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					hsr.setMA_TAPHOSO(rs.getInt(1));
					hsr.setTEN_TAPHOSO(rs.getString(2));
					hsr.setNGAY_TAO_TAPHOSO(rs.getTimestamp(3));
					hsr.setGIOITHIEU_TAPHOSO(rs.getString(4));
					String query2 = "SELECT MA_VANBAN, SO_VANBAN, NGAY_BAN_HANH, CO_QUAN_BAN_HANH, TRICH_YEU, MA_TAPHOSO  FROM VANBAN WHERE  MA_TAPHOSO='"
							+ hsr.getMA_TAPHOSO() + "'";

					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = new VANBAN();
						vb.setMA_VANBAN(rs2.getInt(1));
						vb.setSO_VANBAN(rs2.getString(2));
						vb.setNGAY_BAN_HANH(rs2.getTimestamp(3));
						vb.setCO_QUAN_BAN_HANH(rs2.getString(4));
						vb.setTRICH_YEU(rs2.getString(5));
						vb.setMA_TAPHOSO(rs2.getInt(6));
						vbl.add(vb);
					}
					hsr.setVbl(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
	}

	private class Delete extends DELactivity {
	}
}
