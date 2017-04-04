package Control.THUCHIEN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ROLE.PrivilegeChecker;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.TAP_HO_SO;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_GIAI_DOAN_THUC_HIEN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUOIDUNG_THUCHIEN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUOIDUNG_THUCHIEN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUOIDUNG_THUCHIEN;

public class Control_THUCHIEN_CANBO {
	private final Connection conn;

	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	public PrivilegeChecker pvc;

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

	public Control_THUCHIEN_CANBO(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public void setNGUOIDUNG_THUCHIEN(String tEN_TAI_KHOAN, GIAI_DOAN_THUC_HIEN gdth, int GIAO_NHANVIEC,
			Date NGAY_THAM_GIA) throws SQLException {
		getInserter().setNGUOIDUNG_THUCHIEN(tEN_TAI_KHOAN, gdth, GIAO_NHANVIEC, NGAY_THAM_GIA);
	}

	public NGUOIDUNG_THUCHIEN getNGUOIDUNG_THUCHIEN(String ten_TAI_KHOAN, GIAI_DOAN_THUC_HIEN th) throws SQLException {
		return getSelecter().getNGUOIDUNG_THUCHIEN(ten_TAI_KHOAN, th);
	}

	public ArrayList<NGUOIDUNG_THUCHIEN> get_AllNGUOIDUNG_THUCHIEN(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
		return getSelecter().get_AllNGUOIDUNG_THUCHIEN(gdth);
	}

	public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
		return getSelecter().get_NGUOIDUNG_Thamgia_Phanviec(gdth);
	}

	public int update_TAPHOSO(NGUOIDUNG_THUCHIEN ndth, TAP_HO_SO ths) throws SQLException {
		return getUpdater().update_TAPHOSO(ndth, ths);
	}

	public Boolean deleteNGUOIDUNG_GIAI_DOAN_THUC_HIEN(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
			throws SQLException {
		return getDeleter().deleteNGUOIDUNG_GIAI_DOAN_THUC_HIEN(ten_TAI_KHOAN, ma_GIAI_DOAN_CONG_VIEC);
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
		public void setNGUOIDUNG_THUCHIEN(String tEN_TAI_KHOAN, GIAI_DOAN_THUC_HIEN gdth, int GIAO_NHANVIEC,
				Date NGAY_THAM_GIA) throws SQLException {
			if (isPrivilegeADD()) {
				String query = (new query_Insert_NGUOIDUNG_THUCHIEN()).getString_ThemNguoidung_Thuchien(tEN_TAI_KHOAN,
						gdth, GIAO_NHANVIEC, NGAY_THAM_GIA);
				if (query == null)
					return;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
			}
		}
	}

	private class Select extends REAactivity {
		public NGUOIDUNG_THUCHIEN getNGUOIDUNG_THUCHIEN(String ten_TAI_KHOAN, GIAI_DOAN_THUC_HIEN th)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				NGUOIDUNG_THUCHIEN ndth = null;
				String query = (new query_Select_NGUOIDUNG_THUCHIEN())
						.getString_ThongtinPhanviec_HosoPhanviec_Nguoidung_Thuchien(ten_TAI_KHOAN, th);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ndth = (new Control_DAO_Build()).get_NGUOIDUNG_THUCHIEN(rs);
				}
				rs.close();
				st.close();
				return ndth;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG_THUCHIEN> get_AllNGUOIDUNG_THUCHIEN(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG_THUCHIEN> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_THUCHIEN())
						.getString_TatCa_Nguoidung_Thamgia_Phanviec_Thuchien(gdth);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NGUOIDUNG_THUCHIEN ndth = (new Control_DAO_Build()).get_NGUOIDUNG_THUCHIEN(rs);
					result.add(ndth);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<NGUOIDUNG> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_THUCHIEN())
						.getString_NGUOIDUNG_Thamgia_GiaidoanThuchien(gdth);
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
	}

	private class Update extends EDIactivity {

		public int update_TAPHOSO(NGUOIDUNG_THUCHIEN ndth, TAP_HO_SO ths) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG_THUCHIEN()).getString_Capnhat_TapHoso(ndth, ths);
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

		public Boolean deleteNGUOIDUNG_GIAI_DOAN_THUC_HIEN(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
				throws SQLException {
			if (isPrivilegeDEL()) {
				String query = (new query_Delete_GIAI_DOAN_THUC_HIEN())
						.getString_Xoa_GiaidoanThuchien_Canbo(ten_TAI_KHOAN, ma_GIAI_DOAN_CONG_VIEC);
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
