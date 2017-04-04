package Control.THONGBAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import DAO.NGUOIDUNG;
import DAO.THONGBAO;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_THONGBAO;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_THONGBAO;

public class Control_THONGBAO {

	private NGUOIDUNG user;
	private final Connection conn;

	public Control_THONGBAO(NGUOIDUNG user) {
		this.user = user;
		conn = user.getConn();
	}

	public ArrayList<THONGBAO> get_THONGBAO_LenhDieuXe(Date date, Date date2) throws SQLException {
		return (new Select()).get_THONGBAO_LenhDieuXe(date, date2);
	}

	public ArrayList<THONGBAO> get_THONGBAO_ThongBaokhac(Date date, Date date2) throws SQLException {
		return new Select().get_THONGBAO_ThongBaokhac(date, date2);
	}

	public ArrayList<THONGBAO> get_THONGBAO_NguoiDung(Date date, Date date2) throws SQLException {
		return new Select().get_THONGBAO_NguoiDung(date, date2);
	}

	public ArrayList<THONGBAO> get_THONGBAO_MuaSam_ThanhLy(Date date, Date date2) throws SQLException {
		return new Select().get_THONGBAO_MuaSam_ThanhLy(date, date2);
	}

	public ArrayList<THONGBAO> get_THONGBAO_SuaChua_BaoDuong(Date date, Date date2) throws SQLException {
		return new Select().get_THONGBAO_SuaChua_BaoDuong(date, date2);
	}

	public ArrayList<THONGBAO> get_UNREAD_THONGBAO_LenhDieuXe() throws SQLException {
		return (new Select()).get_UNREAD_THONGBAO_LenhDieuXe();
	}

	public ArrayList<THONGBAO> get_UNREAD_THONGBAO_SuaChua_BaoDuong() throws SQLException {
		return new Select().get_UNREAD_THONGBAO_SuaChua_BaoDuong();
	}

	public ArrayList<THONGBAO> get_UNREAD_THONGBAO_MuaSam_ThanhLy() throws SQLException {
		return new Select().get_UNREAD_THONGBAO_MuaSam_ThanhLy();
	}

	public ArrayList<THONGBAO> get_UNREAD_THONGBAO_NguoiDung() throws SQLException {
		return new Select().get_UNREAD_THONGBAO_NguoiDung();
	}

	public ArrayList<THONGBAO> get_UNREAD_THONGBAO_ThongBaokhac() throws SQLException {
		return new Select().get_UNREAD_THONGBAO_ThongBaokhac();
	}

	public boolean get_THONGBAO_Moi() throws SQLException {
		return new Select().get_THONGBAO_Moi();
	}

	public int getNextKey() {
		if (user != null)
			return (new Control_Tool(conn)).nextKey_TABLE("THONGBAO");
		return -1;
	}

	public int insert_THONGBAO(THONGBAO tb) throws SQLException {
		return new Insert().insert_THONGBAO(tb);
	}

	class Insert {
		public int insert_THONGBAO(THONGBAO tb) throws SQLException {
			if (user != null) {
				String query = (new query_Insert_THONGBAO()).getString_Them_Thongbao(tb);
				if (query == null)
					return -1;
				int nextKey = getNextKey();
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextKey;
			}
			return -1;
		}

	}

	class Select {
		public ArrayList<THONGBAO> get_THONGBAO_ThongBaokhac(Date date, Date date2) throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_ThongbaoKhac(user, date, date2);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public boolean get_THONGBAO_Moi() throws SQLException {
			if (user != null) {
				String query = (new query_Select_THONGBAO()).getString_bool_Thongbao_chuadoc(user);
				if (query == null)
					return false;
				boolean result = false;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result = true;
					break;
				}
				rs.close();
				st.close();
				return result;
			}
			return false;
		}

		public ArrayList<THONGBAO> get_THONGBAO_NguoiDung(Date date, Date date2) throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Nguoidung(user, date, date2);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_THONGBAO_MuaSam_ThanhLy(Date date, Date date2) throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Muasam_Thanhly(user, date, date2);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_THONGBAO_SuaChua_BaoDuong(Date date, Date date2) throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_SuachuaBaoduong(user, date, date2);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_THONGBAO_LenhDieuXe(Date date, Date date2) throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Lenhdieuxe(user, date, date2);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_UNREAD_THONGBAO_LenhDieuXe() throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Lenhdieuxe_ChuaDoc(user);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_UNREAD_THONGBAO_SuaChua_BaoDuong() throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_SuachuaBaoduong_ChuaDoc(user);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_UNREAD_THONGBAO_MuaSam_ThanhLy() throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Muasam_Thanhly_ChuaDoc(user);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_UNREAD_THONGBAO_NguoiDung() throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_Nguoidung_ChuaDoc(user);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<THONGBAO> get_UNREAD_THONGBAO_ThongBaokhac() throws SQLException {
			if (user != null) {
				ArrayList<THONGBAO> result = new ArrayList<>();
				String query = (new query_Select_THONGBAO()).getString_Thongbao_ThongbaoKhac_ChuaDoc(user);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					THONGBAO tb = (new Control_DAO_Build()).get_THONGBAO(rs);
					result.add(tb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	class Update {
	}

	class Delete {
	}

}
