package Control.NGHIEMTHU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ROLE.PrivilegeChecker;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.TAP_HO_SO;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NGUOIDUNG_NGHIEMTHU;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUOIDUNG_NGHIEMTHU;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUOIDUNG_NGHIEMTHU;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUOIDUNG_NGHIEMTHU;

public class Control_NGHIEMTHU_CANBO {
	private Connection conn;
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

	public Control_NGHIEMTHU_CANBO(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public boolean setNGUOIDUNG_NGHIEMTHU(String ten_TAI_KHOAN, GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU,
			int value_HinhThucNhanCongviec_NguoiDungNhanViec, Date NGAY_THAM_GIA) throws SQLException {
		return getInserter().setNGUOIDUNG_NGHIEMTHU(ten_TAI_KHOAN, get_GIAIDOAN_NGHIEMTHU,
				value_HinhThucNhanCongviec_NguoiDungNhanViec, NGAY_THAM_GIA);
	}

	public NGUOIDUNG_NGHIEMTHU getNGUOIDUNG_NGHIEMTHU(String ten_TAI_KHOAN, GIAI_DOAN_NGHIEM_THU ngth)
			throws SQLException {
		return getSelecter().getNGUOIDUNG_NGHIEMTHU(ten_TAI_KHOAN, ngth);
	}

	public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
		return getSelecter().get_NGUOIDUNG_Thamgia_Phanviec(gdnt);
	}

	public ArrayList<NGUOIDUNG_NGHIEMTHU> get_AllNGUOIDUNG_NGHIEMTHU(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
		return getSelecter().get_AllNGUOIDUNG_NGHIEMTHU(gdnt);
	}

	public int update_TAPHOSO(NGUOIDUNG_NGHIEMTHU ndnt, TAP_HO_SO ths) throws SQLException {
		return getUpdater().update_TAPHOSO(ndnt, ths);
	}

	public boolean deleteNGUOIDUNG_GIAI_DOAN_NGHIEM_THU(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
			throws SQLException {
		return getDeleter().deleteNGUOIDUNG_GIAI_DOAN_NGHIEM_THU(ten_TAI_KHOAN, ma_GIAI_DOAN_CONG_VIEC);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getINSERT_Privilege();
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
		public boolean setNGUOIDUNG_NGHIEMTHU(String ten_TAI_KHOAN, GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU,
				int value_HinhThucNhanCongviec_NguoiDungNhanViec, Date NGAY_THAM_GIA) throws SQLException {
			if (conn != null & isPrivilegeADD()) {
				String query = (new query_Insert_NGUOIDUNG_NGHIEMTHU()).getString_ThemNguoidungThamgiaNghiemthu(
						ten_TAI_KHOAN, get_GIAIDOAN_NGHIEMTHU, value_HinhThucNhanCongviec_NguoiDungNhanViec,
						NGAY_THAM_GIA);
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

	private class Select extends REAactivity {
		public NGUOIDUNG_NGHIEMTHU getNGUOIDUNG_NGHIEMTHU(String ten_TAI_KHOAN, GIAI_DOAN_NGHIEM_THU ngth)
				throws SQLException {
			if (conn != null & isPrivilegeREA()) {
				NGUOIDUNG_NGHIEMTHU ndth = null;
				String query = (new query_Select_NGUOIDUNG_NGHIEMTHU())
						.getString_NguoidungNghiemthuCongviec(ten_TAI_KHOAN, ngth);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					ndth = new Control_DAO_Build().get_NGUOIDUNG_NGHIEMTHU(rs);
				}
				rs.close();
				st.close();
				return ndth;
			}
			return null;
		}

		public ArrayList<NGUOIDUNG> get_NGUOIDUNG_Thamgia_Phanviec(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
			if (conn != null & isPrivilegeREA()) {
				ArrayList<NGUOIDUNG> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_NGHIEMTHU()).getString_NguoidungThamgiaNghiemthu(gdnt);
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

		public ArrayList<NGUOIDUNG_NGHIEMTHU> get_AllNGUOIDUNG_NGHIEMTHU(GIAI_DOAN_NGHIEM_THU gdnt)
				throws SQLException {
			if (conn != null & isPrivilegeREA()) {
				ArrayList<NGUOIDUNG_NGHIEMTHU> result = new ArrayList<>();
				String query = (new query_Select_NGUOIDUNG_NGHIEMTHU()).getString_Tatca_NguoidungNghiemthu(gdnt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NGUOIDUNG_NGHIEMTHU ndth = new Control_DAO_Build().get_NGUOIDUNG_NGHIEMTHU(rs);
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
		public int update_TAPHOSO(NGUOIDUNG_NGHIEMTHU ndnt, TAP_HO_SO ths) throws SQLException {
			if (conn != null & isPrivilegeEDI()) {
				String query = (new query_Update_NGUOIDUNG_NGHIEMTHU()).getString_CapnhatHoso(ndnt, ths);
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
		public boolean deleteNGUOIDUNG_GIAI_DOAN_NGHIEM_THU(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
				throws SQLException {
			if (conn != null & isPrivilegeDEL()) {
				String query = (new query_Delete_NGUOIDUNG_NGHIEMTHU()).getString_XoaNguoidungNghiemthu(ten_TAI_KHOAN,
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
