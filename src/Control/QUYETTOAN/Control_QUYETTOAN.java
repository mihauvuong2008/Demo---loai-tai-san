package Control.QUYETTOAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_GIAI_DOAN_QUYET_TOAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_GIAI_DOAN_QUYET_TOAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_GIAI_DOAN_QUYET_TOAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_GIAI_DOAN_QUYET_TOAN;
import DAO.CONGVIEC_PHANVIEC;

/**
 * Người theo hương hoa mây mù giăng lối
 * 
 * Làn sương khói phôi phai đưa bước ai xa rồi
 * 
 * Đơn côi mình ta vấn vương hồi ức trong men say chiều mưa buồn
 * 
 * Ngăn giọt lệ ngừng khiến khóe mi sầu bi
 * 
 * Đường xưa nơi cố nhân từ giã biệt li, cánh hoa rụng rơi
 * 
 * Phận duyên mong manh rẽ lối trong mơ ngày tương phùng.
 * 
 * [ĐK:]Ohhhh, tiếng khóc cuốn theo làn gió bay
 * 
 * Thuyền ai qua sông lỡ quên vớt ánh trăng tàn nơi này
 * 
 * Trống vắng bóng ai dần hao gầy.
 * 
 * Ehhhhhh, lòng ta xin nguyện khắc ghi trong tim tình nồng mê say
 * 
 * Mặc cho tóc mây vương lên đôi môi cay
 * 
 * Bâng khuâng mình ta lạc trôi giữa đời
 * 
 * Ta lạc trôi giữa trời.
 */
@SuppressWarnings("unused")
public class Control_QUYETTOAN {
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

	public Control_QUYETTOAN(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return new Select().get_GIAIDOAN_QUYETTOAN(dtt);
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("GIAI_DOAN_QUYET_TOAN");
		return -1;
	}

	public int create_GIAI_DOAN_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getInserter().create_GIAI_DOAN_QUYETTOAN(dtt);
	}

	public int create_GIAI_DOAN_QUYETTOAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getInserter().create_GIAI_DOAN_QUYETTOAN(dsb);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangQuyettoan_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangQuyettoan_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangQuyettoan_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangQuyettoan_TANG_TAISAN(nd);
	}

	public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getSelecter().get_GIAIDOAN_QUYETTOAN(dsb);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaQuyettoan_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaQuyettoan_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaQuyettoan_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaQuyettoan_TANG_TAISAN(nd);
	}

	public boolean update_Ghichu(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, String text) throws SQLException {
		return getUpdater().update_Ghichu(get_GIAIDOAN_QUYETTOAN, text);
	}

	public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(int ma_PHANVIEC) throws SQLException {
		return getSelecter().get_GIAIDOAN_QUYETTOAN(ma_PHANVIEC);
	}

	public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, Date tHISDAY)
			throws SQLException {
		return getUpdater().update_ThoiDiemBatdauCongviec(get_GIAIDOAN_QUYETTOAN, tHISDAY);
	}

	public boolean set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC(GIAI_DOAN_QUYET_TOAN giai_DOAN_QUYET_TOAN, Date tHISDAY)
			throws SQLException {
		return getUpdater().set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC(giai_DOAN_QUYET_TOAN, tHISDAY);
	}

	public boolean update_DukienThuchien(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, int dukienThuchien)
			throws SQLException {
		return getUpdater().update_DukienThuchien(get_GIAIDOAN_QUYETTOAN, dukienThuchien);
	}

	public boolean update_Giaidoan_Quyettoan(GIAI_DOAN_QUYET_TOAN gdqt) throws SQLException {
		return getUpdater().update_Giaidoan_Quyettoan(gdqt);
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
		public int create_GIAI_DOAN_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_QUYET_TOAN())
						.getString_ThemGiaidoanQuyettoan_TangTaisan(dtt);
				if (query == null)
					return -1;
				int nextkey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}

		public int create_GIAI_DOAN_QUYETTOAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_QUYET_TOAN())
						.getString_ThemGiaidoanQuyettoan_SuachuaBaoduong(dsb);
				if (query == null)
					return -1;
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
		public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN()).getString_GiaidoanQuyettoan_Tangtaisan(dtt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_QUYET_TOAN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangQuyettoan_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN())
						.getString_CongviecPhanviec_DangQUYETTOAN_SuachuaBaoduong(nd);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					e.setCONGVIEC(dsb);

					GIAI_DOAN_QUYET_TOAN gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangQuyettoan_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN())
						.getString_CongviecPhanviec_DangQUYETTOAN_TangTaisan(nd);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();
					DOT_THUCHIEN_TANG_TAISAN dtt = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					e.setCONGVIEC(dtt);
					GIAI_DOAN_QUYET_TOAN gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN())
						.getString_GiaidoanQuyettoan_SuachuaBaoduong(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_QUYET_TOAN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaQuyettoan_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN())
						.getString_CongviecPhanviec_DaQUYETTOAN_SuachuaBaoduong(nd);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					e.setCONGVIEC(dsb);
					GIAI_DOAN_QUYET_TOAN gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaQuyettoan_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN())
						.getString_CongviecPhanviec_DaQUYETTOAN_TangTaisan(nd);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = new CONGVIEC_PHANVIEC();
					DOT_THUCHIEN_TANG_TAISAN dtt = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					e.setCONGVIEC(dtt);
					GIAI_DOAN_QUYET_TOAN gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
					e.setPHANVIEC(gdnt);

					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN(int ma_PHANVIEC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_QUYET_TOAN()).getString_GiaidoanQuyettoan(ma_PHANVIEC);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_QUYET_TOAN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_QUYET_TOAN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_Ghichu(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, String text) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_QUYET_TOAN()).getString_CapnhatGhichu(get_GIAIDOAN_QUYETTOAN,
						text);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, Date tHISDAY)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_QUYET_TOAN())
						.getString_Capnhat_ThoidiembatdauPhanviec(get_GIAIDOAN_QUYETTOAN, tHISDAY);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean set_NGAYCHUYENGIAO_PHANVIEC_KETTHUC_CONGVIEC(GIAI_DOAN_QUYET_TOAN giai_DOAN_QUYET_TOAN,
				Date tHISDAY) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_QUYET_TOAN())
						.getString_Capnhat_NgaychuyengiaoPhanviec_KetthucCongviec(giai_DOAN_QUYET_TOAN, tHISDAY);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_DukienThuchien(GIAI_DOAN_QUYET_TOAN get_GIAIDOAN_QUYETTOAN, int dukienThuchien)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_QUYET_TOAN())
						.getString_Capnhat_DukienThuchienPhanviec(get_GIAIDOAN_QUYETTOAN, dukienThuchien);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_Giaidoan_Quyettoan(GIAI_DOAN_QUYET_TOAN gdqt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_QUYET_TOAN()).getString_Update_Giaidoan_Quyettoan(gdqt);
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

	}

}
