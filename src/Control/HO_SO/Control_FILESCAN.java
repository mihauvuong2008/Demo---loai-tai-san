package Control.HO_SO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Control.ROLE.PrivilegeChecker;
import DAO.FILESCAN;
import DAO.NGUOIDUNG;
import DAO.VANBAN;

public class Control_FILESCAN {
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

	public Control_FILESCAN(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public int CountRow(int i) {
		return new Select().CountRow(i);
	}

	public int insert_IMGAGE(FILESCAN f) throws SQLException {
		return getInserter().insert_IMGAGE(f);
	}

	public ArrayList<FILESCAN> get_IMAGE_l(VANBAN vb) throws SQLException {
		return getSelecter().get_IMAGE_l(vb);
	}

	public boolean delete_FILESCAN(FILESCAN f) throws SQLException {
		return getDeleter().delete_FILESCAN(f);
	}

	public int update_IMG(FILESCAN f) throws SQLException {
		return getUpdater().update_IMG(f);
	}

	public int update_STT(FILESCAN f, int i) throws SQLException {
		return getUpdater().update_STT(f, i);
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
		public int insert_IMGAGE(FILESCAN f) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(f.getNgaytao());
				int stt = CountRow(f.getMA_VANBAN());
				String query = "INSERT INTO FILESCAN (image, image_size, image_name, Ngaytao, Stt, MA_VANBAN) VALUES(?, '"
						+ f.getImage_size() + "', '" + f.getImage_name() + "', '" + date + "' ,'" + stt + "' ,'"
						+ f.getMA_VANBAN() + "');";
				PreparedStatement prs = conn.prepareStatement(query);
				prs.setBlob(1, f.getImage());
				prs.execute();
				prs.close();
				return 1;
			}
			return -1;
		}

	}

	private class Select extends REAactivity {
		public ArrayList<FILESCAN> get_IMAGE_l(VANBAN vb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<FILESCAN> fscl = null;
				String query = "SELECT * FROM FILESCAN  WHERE MA_VANBAN = '" + vb.getMA_VANBAN() + "' ORDER BY Stt; ";
				fscl = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					FILESCAN fsc = new FILESCAN();
					fsc.setImage_id(rs.getInt("Image_id"));
					fsc.setImage(rs.getBinaryStream("image"));
					fsc.setImage_name(rs.getString("image_name"));
					fsc.setNgaytao(rs.getTimestamp("Ngaytao"));
					fsc.setStt(rs.getInt("Stt"));
					fsc.setMA_VANBAN(rs.getInt("MA_VANBAN"));
					fscl.add(fsc);
				}
				rs.close();
				st.close();
				return fscl;
			}
			return null;
		}

		public int CountRow(int i) {
			if (conn != null) {
				int result = -1;

				String query = "SELECT 1 FROM FILESCAN WHERE MA_VANBAN ='" + i + "'; ";
				Statement st;
				ResultSet rs;
				System.out.println(query);
				try {
					st = conn.createStatement();
					rs = st.executeQuery(query);
					rs.last();
					result = rs.getRow();
					rs.close();
					st.close();
					return result;
				} catch (Exception e) {
					e.printStackTrace();
					return -1;
				}
			}
			return -1;
		}
	}

	private class Update extends EDIactivity {
		public int update_IMG(FILESCAN f) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = "UPDATE FILESCAN SET image_name='" + f.getImage_name() + "', image_name ='"
						+ f.getImage_name() + "', Stt = '" + f.getStt() + "' where image_id='" + f.getImage_id() + "';";
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return f.getImage_id();
			}
			return -1;
		}

		public int update_STT(FILESCAN f, int i) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = "UPDATE FILESCAN SET  Stt = '" + i + "' where image_id='" + f.getImage_id() + "';";
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return f.getImage_id();
			}
			return -1;
		}
	}

	private class Delete extends DELactivity {
		public boolean delete_FILESCAN(FILESCAN f) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = "DELETE FROM FILESCAN WHERE image_id = '" + f.getImage_id() + "';";
				// System.out.println(query);
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
