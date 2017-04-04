package Control.NGUOIDUNG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ROLE.PrivilegeChecker;
import DAO.ROLE;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NGUOIDUNG;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUOIDUNG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUOIDUNG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_ROLE;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUOIDUNG;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import MODEL.DBConnection;

public class Control_NGUOIDUNG {
	private static NGUOIDUNG user;
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

	public Control_NGUOIDUNG(NGUOIDUNG user) {
		Control_NGUOIDUNG.user = user;
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public Connection connect() {
		return new getConnection().connect();
	}

	public NGUOIDUNG get_Login_userData(String taikhoan) throws SQLException {
		/**
		 * Only System access
		 */
		return new SystemAccess().get_Login_userData(taikhoan);
	}

	public NGUOIDUNG get_info_user(String taikhoan) throws SQLException {
		return getSelecter().get_info_user(taikhoan);
	}

	public NGUOIDUNG get_NGUOIDUNG(String taikhoan) throws SQLException {
		return getSelecter().get_NGUOIDUNG(taikhoan);
	}

	public ArrayList<NGUOIDUNG> get_All() throws SQLException {
		return getSelecter().get_All();
	}

	public ArrayList<NGUOIDUNG> get_NGUOIDUNG(PHONGBAN p) throws SQLException {
		return getSelecter().get_NGUOIDUNG(p);
	}

	public boolean do_update_tenNguoidung(String tenNguoidung) throws SQLException {
		return getUpdater().do_update_tenNguoidung(tenNguoidung);
	}

	public boolean do_update_gioiThieu(String GioiThieu) throws SQLException {
		return getUpdater().do_update_gioiThieu(GioiThieu);
	}

	public boolean set_Trangthai_Dangnhap(int b) throws SQLException {
		return getUpdater().set_Trangthai_Dangnhap(b);
	}

	public boolean update_MATKHAU(String text) throws SQLException {
		return getUpdater().update_MATKHAU(text);
	}

	public boolean update_NGUOIDUNG(NGUOIDUNG r) throws SQLException {
		return getUpdater().update_NGUOIDUNG(r);
	}

	public String insert_NGUOIDUNG(NGUOIDUNG i) throws SQLException {
		return getInserter().insert_NGUOIDUNG(i);
	}

	public boolean remove_NGUOIDUNG(NGUOIDUNG i) throws SQLException {
		return getDeleter().remove_NGUOIDUNG(i);
	}

	class getConnection {
		public Connection connect() {
			DBConnection db = new DBConnection();
			Connection conn = db.getConn();
			if (conn != null) {
				String query = (new query_Select_NGUOIDUNG()).getString_Tatca_Taikhoan_Matkhau();
				NGUOIDUNG user = null;
				try {
					Statement st;
					ResultSet rs;
					st = conn.createStatement();
					rs = st.executeQuery(query);
					boolean flag = false;
					while (rs.next()) {
						user = (new Control_DAO_Build()).get_NGUOIDUNG_TINY(rs);
						if (user.getTEN_TAI_KHOAN().equals(Control_NGUOIDUNG.user.getTEN_TAI_KHOAN())) {
							if (user.getMAT_KHAU().equals(Control_NGUOIDUNG.user.getMAT_KHAU())) {
								flag = true;
							}
						}
					}
					rs.close();
					st.close();
					if (flag)
						return conn;
					return null;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
			return null;
		}
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getUPDATE_Privilege();
		}
	}

	class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_NGUOIDUNG().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public String insert_NGUOIDUNG(NGUOIDUNG i) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_NGUOIDUNG()).getString_ThemNguoidung(i);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return i.getTEN_TAI_KHOAN();
			}
			return null;
		}
	}

	private class SystemAccess {
		/**
		 * System alway allow access data to get information conn
		 * 
		 * @throws SQLException
		 */
		public NGUOIDUNG get_Login_userData(String taikhoan) throws SQLException {
			if (conn != null /* && System access */) {
				String query = (new query_Select_NGUOIDUNG()).getString_Nguoidung_Full(taikhoan);
				NGUOIDUNG user = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					user = (new Control_DAO_Build()).get_NGUOIDUNG_With_PASSWORD(rs);
					String SubQuery_quyen = (new query_Select_ROLE()).getString_TatcaQuyencuaUser(user);
					ArrayList<ROLE> rolelist = new ArrayList<>();
					Statement st_ = conn.createStatement();
					ResultSet rs_ = st_.executeQuery(SubQuery_quyen);
					while (rs_.next()) {
						ROLE quyen = (new Control_DAO_Build()).get_ROLE(rs_);
						rolelist.add(quyen);
					}
					rs_.close();
					st_.close();
					user.setRolelist(rolelist);
				}
				rs.close();
				st.close();
				return user;

			}
			return null;
		};
	}

	private class Select extends REAactivity {
		public NGUOIDUNG get_info_user(String taikhoan) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_NGUOIDUNG()).getString_Nguoidung_Full(taikhoan);
				NGUOIDUNG user = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					user = (new Control_DAO_Build()).get_NGUOIDUNG_With_PASSWORD(rs);
					String SubQuery_quyen = (new query_Select_ROLE()).getString_TatcaQuyencuaUser(user);
					ArrayList<ROLE> rolelist = new ArrayList<>();
					Statement st_ = conn.createStatement();
					ResultSet rs_ = st_.executeQuery(SubQuery_quyen);
					while (rs_.next()) {
						ROLE quyen = (new Control_DAO_Build()).get_ROLE(rs_);
						rolelist.add(quyen);
					}
					rs_.close();
					st_.close();
					user.setRolelist(rolelist);
				}
				rs.close();
				st.close();
				return user;
			}
			return null;
		};

		public NGUOIDUNG get_NGUOIDUNG(String taikhoan) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_NGUOIDUNG()).getString_Nguoidung_Normal(taikhoan);
				NGUOIDUNG user = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					user = (new Control_DAO_Build()).get_NGUOIDUNG(rs);
					String SubQuery_quyen = (new query_Select_ROLE()).getString_TatcaQuyencuaUser(user);
					ArrayList<ROLE> rolelist = new ArrayList<>();

					Statement st_ = conn.createStatement();
					ResultSet rs_ = st_.executeQuery(SubQuery_quyen);
					while (rs_.next()) {
						ROLE quyen = (new Control_DAO_Build()).get_ROLE(rs_);
						rolelist.add(quyen);
					}
					rs_.close();
					st_.close();
					user.setRolelist(rolelist);
					rs.close();
					st.close();
					return user;
				}

			}
			return null;
		};

		public ArrayList<NGUOIDUNG> get_All() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG> result;
				String query = (new query_Select_NGUOIDUNG()).getString_TatcaNguoidung();
				result = new ArrayList<>();
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					NGUOIDUNG user = (new Control_DAO_Build()).get_NGUOIDUNG_With_PASSWORD(rs);

					String SubQuery_quyen = (new query_Select_ROLE()).getString_TatcaQuyencuaUser(user);
					ArrayList<ROLE> rolelist = new ArrayList<>();
					Statement st_ = conn.createStatement();
					ResultSet rs_ = st_.executeQuery(SubQuery_quyen);
					while (rs_.next()) {
						ROLE quyen = (new Control_DAO_Build()).get_ROLE(rs_);
						rolelist.add(quyen);
					}

					rs_.close();
					st_.close();

					user.setRolelist(rolelist);
					result.add(user);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG> get_NGUOIDUNG(PHONGBAN p) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG> result;
				String query = (new query_Select_NGUOIDUNG()).getString_Nguoidung_Phongban(p);
				result = new ArrayList<>();
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					NGUOIDUNG user = (new Control_DAO_Build()).get_NGUOIDUNG(rs);
					result.add(user);
				}
				rs.close();
				st.close();
				return result;

			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean do_update_tenNguoidung(String tenNguoidung) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG()).getString_CapnhatTENNguoigdung(tenNguoidung, user);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean do_update_gioiThieu(String GioiThieu) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG()).getString_CapnhatGioithieuNguoigdung(GioiThieu, user);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean set_Trangthai_Dangnhap(int b) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG()).getString_Update_TrangthaiDangnhap(b, user);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_MATKHAU(String text) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG()).getString_Capnhat_Matkhau(text, user);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_NGUOIDUNG(NGUOIDUNG r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG()).getString_Capnhat_Nguoidung(r);
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
		public boolean remove_NGUOIDUNG(NGUOIDUNG i) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_NGUOIDUNG()).getString_XoaNguoidung(i);
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
