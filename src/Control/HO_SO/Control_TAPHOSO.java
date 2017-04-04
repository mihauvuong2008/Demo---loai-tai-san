package Control.HO_SO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAP_HO_SO;
import View.DateTime.MyDateFormat;

public class Control_TAPHOSO {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final PrivilegeChecker pvc;
	private final MyDateFormat mdf = new MyDateFormat();

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

	public Control_TAPHOSO(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public TAP_HO_SO get_TAP_HO_SO(int MA_TAPHOSO) throws SQLException {
		return getSelecter().get_TAP_HO_SO(MA_TAPHOSO);
	}

	public int Create_TAP_HO_SO(TAP_HO_SO t) throws SQLException {
		return getInserter().Create_TAP_HO_SO(t);
	}

	public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_THUCHIEN ndth) throws SQLException {
		return getSelecter().get_TAP_HO_SO(ndth);
	}

	public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_NGHIEMTHU ndnt) throws SQLException {
		return getSelecter().get_TAP_HO_SO(ndnt);
	}

	public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_QUYETTOAN ndqt) throws SQLException {
		return getSelecter().get_TAP_HO_SO(ndqt);
	}

	public int update_TAPHOSO(TAP_HO_SO ths) throws SQLException {
		return getUpdater().update_TAPHOSO(ths);
	}

	public boolean delete_TAPHOSO(TAP_HO_SO ths) throws SQLException {
		return getDeleter().delete_TAPHOSO(ths);
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
				return new Control_Tool(conn).nextKey_TABLE("TAPHOSO");
			}
			return -1;
		}

		public int Create_TAP_HO_SO(TAP_HO_SO t) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = "INSERT INTO TAPHOSO (TEN_TAPHOSO, GIOITHIEU_TAPHOSO, NGAY_TAO_TAPHOSO) VALUES('"
						+ t.getTEN_TAPHOSO() + "', '" + t.getGIOITHIEU_TAPHOSO() + "', "
						+ (t.getNGAY_TAO_TAPHOSO() == null ? "'" + mdf.getSQLStringDate(new Date()) + "'"
								: "'" + mdf.getSQLStringDate(t.getNGAY_TAO_TAPHOSO()) + "' ")
						+ ");";
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
		public TAP_HO_SO get_TAP_HO_SO(int MA_TAPHOSO) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				TAP_HO_SO ths = null;
				String query = "SELECT * FROM TAPHOSO WHERE MA_TAPHOSO ='" + MA_TAPHOSO + "' ";
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					ths = new TAP_HO_SO();
					ths.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
					ths.setTEN_TAPHOSO(rs.getString("TEN_TAPHOSO"));
					ths.setNGAY_TAO_TAPHOSO(rs.getTimestamp("NGAY_TAO_TAPHOSO"));
					ths.setGIOITHIEU_TAPHOSO(rs.getString("GIOITHIEU_TAPHOSO"));
				}
				rs.close();
				st.close();
				return ths;
			}
			return null;
		}

		public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_THUCHIEN ndth) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				TAP_HO_SO ths = null;
				String query = "SELECT * FROM TAPHOSO INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO ON (MA_GIAI_DOAN_THUC_HIEN='"
						+ ndth.getMA_GIAI_DOAN_THUC_HIEN() + "' AND TEN_TAI_KHOAN='" + ndth.getTEN_TAI_KHOAN()
						+ "' AND TAPHOSO.MA_TAPHOSO = GIAI_DOAN_THUC_HIEN_CAN_BO.MA_TAPHOSO ) ";
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					ths = new TAP_HO_SO();
					ths.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
					ths.setTEN_TAPHOSO(rs.getString("TEN_TAPHOSO"));
					ths.setNGAY_TAO_TAPHOSO(rs.getTimestamp("NGAY_TAO_TAPHOSO"));
					ths.setGIOITHIEU_TAPHOSO(rs.getString("GIOITHIEU_TAPHOSO"));
				}
				rs.close();
				st.close();
				return ths;
			}
			return null;
		}

		public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_NGHIEMTHU ndnt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				TAP_HO_SO ths = null;
				String query = "SELECT * FROM TAPHOSO INNER JOIN GIAI_DOAN_NGHIEM_THU_CAN_BO ON (MA_GIAI_DOAN_NGHIEM_THU='"
						+ ndnt.getMA_GIAI_DOAN_NGHIEM_THU() + "' AND TEN_TAI_KHOAN='" + ndnt.getTEN_TAI_KHOAN()
						+ "' AND TAPHOSO.MA_TAPHOSO = GIAI_DOAN_NGHIEM_THU_CAN_BO.MA_TAPHOSO ) ";
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					ths = new TAP_HO_SO();
					ths.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
					ths.setTEN_TAPHOSO(rs.getString("TEN_TAPHOSO"));
					ths.setNGAY_TAO_TAPHOSO(rs.getTimestamp("NGAY_TAO_TAPHOSO"));
					ths.setGIOITHIEU_TAPHOSO(rs.getString("GIOITHIEU_TAPHOSO"));
				}
				rs.close();
				st.close();
				return ths;
			}
			return null;
		}

		public TAP_HO_SO get_TAP_HO_SO(NGUOIDUNG_QUYETTOAN ndqt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				TAP_HO_SO ths = null;
				String query = "SELECT * FROM TAPHOSO INNER JOIN GIAI_DOAN_QUYET_TOAN_CAN_BO ON (MA_GIAI_DOAN_QUYET_TOAN='"
						+ ndqt.getMA_GIAI_DOAN_QUYET_TOAN() + "' AND TEN_TAI_KHOAN='" + ndqt.getTEN_TAI_KHOAN()
						+ "' AND TAPHOSO.MA_TAPHOSO = GIAI_DOAN_QUYET_TOAN_CAN_BO.MA_TAPHOSO ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ths = new TAP_HO_SO();
					ths.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
					ths.setTEN_TAPHOSO(rs.getString("TEN_TAPHOSO"));
					ths.setNGAY_TAO_TAPHOSO(rs.getTimestamp("NGAY_TAO_TAPHOSO"));
					ths.setGIOITHIEU_TAPHOSO(rs.getString("GIOITHIEU_TAPHOSO"));
				}
				rs.close();
				st.close();
				return ths;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public int update_TAPHOSO(TAP_HO_SO ths) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String date = ths.getNGAY_TAO_TAPHOSO() == null ? new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						: new SimpleDateFormat("yyyy-MM-dd").format(ths.getNGAY_TAO_TAPHOSO());
				String query = "UPDATE TAPHOSO SET TEN_TAPHOSO='" + ths.getTEN_TAPHOSO() + "', NGAY_TAO_TAPHOSO = '"
						+ date + "' ,GIOITHIEU_TAPHOSO ='" + ths.getGIOITHIEU_TAPHOSO() + "' where MA_TAPHOSO='"
						+ ths.getMA_TAPHOSO() + "';";
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return ths.getMA_TAPHOSO();
			}
			return -1;
		}
	}

	private class Delete extends DELactivity {
		public boolean delete_TAPHOSO(TAP_HO_SO ths) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				PreparedStatement prs;
				String query = "DELETE FROM TAPHOSO WHERE MA_TAPHOSO = '" + ths.getMA_TAPHOSO() + "' ;";
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}
}
