package Control.QUYETTOAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ROLE.PrivilegeChecker;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.TAP_HO_SO;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_GIAI_DOAN_QUYET_TOAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUOIDUNG_QUYETTOAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUOIDUNG_QUYETTOAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUOIDUNG_QUYETTOAN;

public class Control_QUYETTOAN_CANBO {
	private final Connection conn;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
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

	public Control_QUYETTOAN_CANBO(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public boolean setNGUOIDUNG_QUYETTOAN(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN,
			int value_HinhThucNhanCongviec_NguoiDungNhanViec, Date NGAY_THAM_GIA) throws SQLException {
		return getUpdater().setNGUOIDUNG_QUYETTOAN(ten_TAI_KHOAN, get_GIAIDOAN_QUYETTOAN,
				value_HinhThucNhanCongviec_NguoiDungNhanViec, NGAY_THAM_GIA);
	}

	public NGUOIDUNG_QUYETTOAN getNGUOIDUNG_QUYETTOAN(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN qt)
			throws SQLException {
		return getSelecter().getNGUOIDUNG_QUYETTOAN(ten_TAI_KHOAN, qt);
	}

	public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_QUYET_TOAN gdth) throws SQLException {
		return getSelecter().get_NGUOIDUNG_Thamgia_Phanviec(gdth);
	}

	public int update_TAPHOSO(NGUOIDUNG_QUYETTOAN ndqt, TAP_HO_SO ths) throws SQLException {
		return getUpdater().update_TAPHOSO(ndqt, ths);
	}

	public ArrayList<NGUOIDUNG_QUYETTOAN> get_AllNGUOIDUNG_QUYETTOAN(GIAI_DOAN_QUYET_TOAN phanviec)
			throws SQLException {
		return getSelecter().get_AllNGUOIDUNG_QUYETTOAN(phanviec);
	}

	public boolean deleteNGUOIDUNG_GIAI_DOAN_QUYET_TOAN(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
			throws SQLException {
		return getDeleter().deleteNGUOIDUNG_GIAI_DOAN_QUYET_TOAN(ten_TAI_KHOAN, ma_GIAI_DOAN_CONG_VIEC);
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
	}

	private class Select extends REAactivity {
		public NGUOIDUNG_QUYETTOAN getNGUOIDUNG_QUYETTOAN(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN qt)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				NGUOIDUNG_QUYETTOAN ndth = null;
				String query = (new query_Select_NGUOIDUNG_QUYETTOAN()).getString_NguoiDung_Quyettoan(ten_TAI_KHOAN,
						qt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ndth = (new Control_DAO_Build()).get_NGUOIDUNG_QUYETTOAN(rs);
				}
				rs.close();
				st.close();
				return ndth;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_QUYET_TOAN gdth) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_QUYETTOAN()).getString_TatCaNguoidungFull_Quyettoan(gdth);
				if (query == null)
					return null;
				Statement st2 = conn.createStatement();
				ResultSet rs2 = st2.executeQuery(query);
				while (rs2.next()) {
					NGUOIDUNG nd = (new Control_DAO_Build()).get_NGUOIDUNG(rs2);
					result.add(nd);
				}
				rs2.close();
				st2.close();
				return result;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG_QUYETTOAN> get_AllNGUOIDUNG_QUYETTOAN(GIAI_DOAN_QUYET_TOAN phanviec)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG_QUYETTOAN> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_QUYETTOAN()).getString_TatCaNguoidung_Quyettoan(phanviec);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					NGUOIDUNG_QUYETTOAN ndth = (new Control_DAO_Build()).get_NGUOIDUNG_QUYETTOAN(rs);
					result.add(ndth);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean setNGUOIDUNG_QUYETTOAN(String ten_TAI_KHOAN, GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN,
				int value_HinhThucNhanCongviec_NguoiDungNhanViec, Date NGAY_THAM_GIA) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Insert_NGUOIDUNG_QUYETTOAN()).getString_Them_Nguoidun_Quyettoan(ten_TAI_KHOAN,
						get_GIAIDOAN_QUYETTOAN, value_HinhThucNhanCongviec_NguoiDungNhanViec, NGAY_THAM_GIA);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public int update_TAPHOSO(NGUOIDUNG_QUYETTOAN ndqt, TAP_HO_SO ths) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG_QUYETTOAN()).getString_Capnhat_Taphoso(ndqt, ths);
				if (query == null)
					return -1;
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
		public boolean deleteNGUOIDUNG_GIAI_DOAN_QUYET_TOAN(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
				throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_GIAI_DOAN_QUYET_TOAN()).getString_XoaNguoidung_Quyettoan(ten_TAI_KHOAN,
						ma_GIAI_DOAN_CONG_VIEC);
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

}
