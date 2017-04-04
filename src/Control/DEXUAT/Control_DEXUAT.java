package Control.DEXUAT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import Control.SYSTEM_LOG.Control_SYSTEM_LOG;
import Control.SYSTEM_LOG.Log_Library;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DEXUAT;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DEXUAT;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DEXUAT;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DEXUAT;

public class Control_DEXUAT {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final PrivilegeChecker pvc;
	private final Control_SYSTEM_LOG cs;

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

	// giới thiệu: class truy vấn và cập nhật các thông tin của đối tượng Đề
	// xuất
	public Control_DEXUAT(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
		cs = new Control_SYSTEM_LOG(user);
	}

	public int getNextKey() {
		return getInserter().getNextKey();
	}

	// Them 1 đề xuất:
	public int insert_DEXUAT(DE_XUAT dx) throws SQLException, NullPointerException {
		int key = getInserter().insert_DEXUAT(dx);
		if (key > 0)
			cs.insertLog(new Log_Library().getString_ThemDexuat(key));
		return key;
	}

	// lấy đề xuất của một công việc mua sắm tài sản:
	public DE_XUAT get_DEXUAT(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		DE_XUAT rs = getSelecter().get_DEXUAT(dtt);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Dexuat(rs.getMA_DE_XUAT()));
		return rs;
	}

	// lấy đề xuất của một công việc sửa chữa - bảo dưỡng tài sản:
	public DE_XUAT get_DEXUAT(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		DE_XUAT rs = getSelecter().get_DEXUAT(dsb);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Dexuat(rs.getMA_DE_XUAT()));
		return rs;
	}

	// lấy đề xuất của một công việc bàn giao tài sản:
	public DE_XUAT get_DEXUAT(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		DE_XUAT rs = getSelecter().get_DEXUAT(dgt);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Dexuat(rs.getMA_DE_XUAT()));
		return rs;
	}

	public ArrayList<DE_XUAT> get_All_Dexuat_Suachua_Baoduong(Date begin, Date end, String text_pattern)
			throws SQLException {
		ArrayList<DE_XUAT> rs = getSelecter().get_All_Dexuat_Suachua_Baoduong(begin, end, text_pattern);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_Dexuat_Suachua_Baoduong(begin, end));
		return rs;
	}

	public ArrayList<DE_XUAT> get_All_Dexuat_Muasam(Date begin, Date end, String text_pattern) throws SQLException {
		ArrayList<DE_XUAT> rs = getSelecter().get_All_Dexuat_Muasam(begin, end, text_pattern);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_Dexuat_Muasam(begin, end));
		return rs;
	}

	public ArrayList<DE_XUAT> get_All_Dexuat_Thanhly(Date begin, Date end, String text_pattern) throws SQLException {
		ArrayList<DE_XUAT> rs = getSelecter().get_All_Dexuat_Thanhly(begin, end, text_pattern);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_Dexuat_Thanhly(begin, end));
		return rs;
	}

	// xóa đề xuất
	public boolean delete_DEXUAT(DE_XUAT dx) throws SQLException {
		boolean rs = getDeleter().delete_DEXUAT(dx);
		if (rs)
			cs.insertLog(new Log_Library().getString_Xoa_Dexuat(dx.getMA_DE_XUAT()));
		return rs;
	}

	public boolean update_TapHoso(DE_XUAT selected, int ma_NewTapHoso) throws SQLException {
		boolean rs = getUpdater().update_TapHoso(selected, ma_NewTapHoso);
		if (rs)
			cs.insertLog(new Log_Library().getString_Capnhat_Hoso_Dexuat(selected.getMA_DE_XUAT(), ma_NewTapHoso));
		return rs;
	}

	public boolean update_Dexuat(DE_XUAT dx) throws SQLException {
		boolean rs = getUpdater().update_Dexuat(dx);
		if (rs)
			cs.insertLog(new Log_Library().getString_Capnhat_Dexuat(dx.getMA_DE_XUAT()));
		return rs;
	}

	// cập nhật thông tin ngày kết thúc phần việc lập đề xuất, giao việc
	public boolean update_ThoiDiemKetthucCongviec(DE_XUAT dx, Date tHISDAY) throws SQLException {
		boolean rs = getUpdater().update_ThoiDiemKetthucCongviec(dx, tHISDAY);
		if (rs)
			cs.insertLog(new Log_Library().getString_Capnhat_ThoidiemKetthuc_Dexuat(dx.getMA_DE_XUAT(), tHISDAY));
		return rs;
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			if (pvc == null)
				return false;
			return pvc.getPrivilegeQUANLY_CONGVIEC().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		// Lấy khóa tiếp theo của Ba?ng (auto increment)
		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("DE_XUAT");
			return -1;
		}

		// Them 1 đề xuất:
		public int insert_DEXUAT(DE_XUAT dx) throws SQLException, NullPointerException {
			if (conn != null && isPrivilegeADD()) {
				int nextKey = getNextKey();
				String query = (new query_Insert_DEXUAT()).get_String_InsertDexuat(dx);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextKey;
			}
			return -1;
		}
	}

	private class Select extends REAactivity {
		// lấy đề xuất của một công việc mua sắm tài sản:
		public DE_XUAT get_DEXUAT(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DEXUAT()).getString_Select_Dexuat_MuasamTaisan(dtt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				DE_XUAT dx = null;
				while (rs.next()) {
					dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
				}
				rs.close();
				st.close();
				return dx;
			}
			return null;
		}

		public DE_XUAT get_DEXUAT(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DEXUAT()).getString_Select_Dexuat_Suachua_Baoduong_PTTS(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				DE_XUAT dx = null;
				while (rs.next()) {
					dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
				}
				rs.close();
				st.close();
				return dx;
			}
			return null;
		}

		// lấy đề xuất của một công việc bàn giao tài sản:
		public DE_XUAT get_DEXUAT(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DEXUAT()).getString_Select_Dexuat_ThanhlyTaisan(dgt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				DE_XUAT dx = null;
				while (rs.next()) {
					dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
				}
				rs.close();
				st.close();
				return dx;
			}
			return null;
		}

		public ArrayList<DE_XUAT> get_All_Dexuat_Suachua_Baoduong(Date begin, Date end, String text_pattern)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DE_XUAT> result = new ArrayList<>();
				String query = (new query_Select_DEXUAT()).getString_Select_AllDexuat_Suachua_Baoduong(begin, end,
						text_pattern);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DE_XUAT dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
					result.add(dx);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DE_XUAT> get_All_Dexuat_Muasam(Date begin, Date end, String text_pattern) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DE_XUAT> result = new ArrayList<>();
				String query = (new query_Select_DEXUAT()).getString_Select_AllDexuat_Muasam(begin, end, text_pattern);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DE_XUAT dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
					result.add(dx);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DE_XUAT> get_All_Dexuat_Thanhly(Date begin, Date end, String text_pattern)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DE_XUAT> result = new ArrayList<>();
				String query = (new query_Select_DEXUAT()).getString_Select_AllDexuat_Thanhly(begin, end, text_pattern);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DE_XUAT dx = (new Control_DAO_Build()).get_DE_XUAT(rs);
					result.add(dx);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_TapHoso(DE_XUAT selected, int ma_NewTapHoso) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DEXUAT()).getString_Update_TapHoso(selected, ma_NewTapHoso);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_Dexuat(DE_XUAT dx) throws SQLException {
			if (conn != null & isPrivilegeEDI()) {
				String query = (new query_Update_DEXUAT()).getString_Update_Dexuat(dx);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		// cập nhật thông tin ngày kết thúc phần việc lập đề xuất, giao việc
		public boolean update_ThoiDiemKetthucCongviec(DE_XUAT dx, Date tHISDAY) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DEXUAT()).getString_Update_ThoidiemKetthucCongviecDexuat(dx, tHISDAY);
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

	private class Delete extends DELactivity {
		// xóa đề xuất
		public boolean delete_DEXUAT(DE_XUAT dx) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_DEXUAT()).getString_XoaDexuat(dx);
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
