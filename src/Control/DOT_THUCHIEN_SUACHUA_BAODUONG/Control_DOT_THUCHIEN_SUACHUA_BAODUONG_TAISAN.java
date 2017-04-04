package Control.DOT_THUCHIEN_SUACHUA_BAODUONG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.Hinhthuc_Baoduong;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.Row_PTTSthamgia;
import DAO.TAISAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;

public class Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN {
	private Connection conn;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
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

	public Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(NGUOIDUNG user) {
		this.conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<Row_PTTSthamgia> getPTTS_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getSelecter().getPTTS_BAODUONG(dsb);
	}

	public boolean set_DOT_THUCHIEN_SUACHUA_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, Row_PTTSthamgia rp)
			throws SQLException {
		return getInserter().set_DOT_THUCHIEN_SUACHUA_TAISAN(dsb, rp);
	}

	public boolean set_DOT_THUCHIEN_SUACHUA_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb, TAISAN ts)
			throws SQLException {
		return getInserter().set_DOT_THUCHIEN_SUACHUA_TAISAN(vIEW_dsb, ts);
	}

	public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, Row_PTTSthamgia rp)
			throws SQLException {
		return getUpdater().update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(dsb, rp);
	}

	public boolean remove_All(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb) throws SQLException {
		return getDeleter().remove_All(vIEW_dsb);
	}

	public boolean remove(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, TAISAN t) throws SQLException {
		return getDeleter().remove(dsb, t);
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
		public boolean set_DOT_THUCHIEN_SUACHUA_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, Row_PTTSthamgia rp)
				throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Insert_Phuongtien_Thamgia_Baoduong(dsb, rp);
				if (query == null)
					return false;
				try {
					PreparedStatement prs = conn.prepareStatement(query);
					prs.executeUpdate();
					prs.close();
					return true;
				} catch (SQLException e) {
					if (e.getErrorCode() == 1062/* duplicate key */) {
						if (update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(dsb, rp))
							return true;
						else
							return false;
					} else {
						e.printStackTrace();
						System.out.println(e.getErrorCode());
					}
					return false;
				}
			}
			return false;
		}

		public boolean set_DOT_THUCHIEN_SUACHUA_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb, TAISAN ts)
				throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Insert_Taisan_Thamgia_Suachua(vIEW_dsb, ts);
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

	private class Select extends REAactivity {
		public ArrayList<Row_PTTSthamgia> getPTTS_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<Row_PTTSthamgia> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Select_PhuongtienGiaothong_Thamgia_Baoduong(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Row_PTTSthamgia rp = new Row_PTTSthamgia();
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					Hinhthuc_Baoduong htbd = (new Control_DAO_Build()).get_Hinhthuc_Baoduong(rs);
					rp = (new Control_DAO_Build()).get_Row_PTTSthamgia(rs, p, htbd);
					result.add(rp);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb,
				Row_PTTSthamgia rp) throws SQLException {
			if (conn != null & isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Update_HinhthucBaoduong_PhuongtienThamgiaBaoduong(dsb, rp);
				if (query == null) {
					return false;
				}
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
		public boolean remove_All(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Delete_Danhsach_Taisan_Suachua_Baoduong(dsb);
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

		public boolean remove(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN())
						.getString_Delete_Taisan_DotSuachua_Baoduong(dsb, t);
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
