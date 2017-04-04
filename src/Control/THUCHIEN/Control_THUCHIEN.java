package Control.THUCHIEN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_GIAI_DOAN_THUC_HIEN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_GIAI_DOAN_THUC_HIEN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_GIAI_DOAN_THUC_HIEN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_GIAI_DOAN_THUC_HIEN;
import DAO.CONGVIEC_PHANVIEC;

public class Control_THUCHIEN {
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

	public Control_THUCHIEN(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("GIAI_DOAN_THUC_HIEN");
		return -1;
	}

	public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getSelecter().get_GIAIDOAN_THUCHIEN(dsb);
	}

	public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getSelecter().get_GIAIDOAN_THUCHIEN(dtt);
	}

	public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		return getSelecter().get_GIAIDOAN_THUCHIEN(dgt);
	}

	public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getInserter().create_GIAI_DOAN_THUCHIEN(dsb);
	}

	public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getInserter().create_GIAI_DOAN_THUCHIEN(dtt);
	}

	public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		return getInserter().create_GIAI_DOAN_THUCHIEN(dgt);
	}

	public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(int ma_PHANVIEC) throws SQLException {
		return getSelecter().get_GIAIDOAN_THUCHIEN(ma_PHANVIEC);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangThucHien_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangThucHien_TANG_TAISAN(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangThucHien_GIAM_TAISAN(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaThucHien_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaThucHien_TANG_TAISAN(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaThucHien_GIAM_TAISAN(nd);
	}

	public boolean update_Ghichu(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, String text) throws SQLException {
		return getUpdater().update_Ghichu(get_GIAIDOAN_THUCHIEN, text);
	}

	public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, Date tHISDAY)
			throws SQLException {
		return getUpdater().update_ThoiDiemBatdauCongviec(get_GIAIDOAN_THUCHIEN, tHISDAY);
	}

	public boolean set_NGAYCHUYENGIAO_PHANVIEC(GIAI_DOAN_THUC_HIEN giai_DOAN_THUC_HIEN, Date tHISDAY)
			throws SQLException {
		return getUpdater().set_NGAYCHUYENGIAO_PHANVIEC(giai_DOAN_THUC_HIEN, tHISDAY);
	}

	public boolean update_ThoiDiemKetthucCongviec(GIAI_DOAN_THUC_HIEN gdth, Date tHISDAY) throws SQLException {
		return getUpdater().update_ThoiDiemKetthucCongviec(gdth, tHISDAY);
	}

	public boolean update_DukienThuchien(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, int dukienThuchien)
			throws SQLException {
		return getUpdater().update_DukienThuchien(get_GIAIDOAN_THUCHIEN, dukienThuchien);
	}

	public boolean Update_Giaidoan_Thuchien(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
		return getUpdater().Update_Giaidoan_Thuchien(gdth);
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
		public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_THUC_HIEN())
						.getString_Taomoi_GiaidoanThuchien_SuachuaBaoduong(dsb);
				int getNextKey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return getNextKey;
			}
			return -1;
		}

		public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_THUC_HIEN())
						.getString_Taomoi_GiaidoanThuchien_TangTaisan(dtt);
				int getNextKey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return getNextKey;
			}
			return -1;
		}

		public int create_GIAI_DOAN_THUCHIEN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_THUC_HIEN())
						.getString_Taomoi_GiaidoanThuchien_GiamTaisan(dgt);
				int getNextKey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return getNextKey;
			}
			return -1;
		}
	}

	private class Select extends REAactivity {
		public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN()).getString_GiaidoanThuchien_SuachuaBaoduong(dsb);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_THUC_HIEN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN()).getString_GiaidoanThuchien_TangTaisan(dtt);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_THUC_HIEN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN()).getString_GiaidoanThuchien_GiamTaisan(dgt);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_THUC_HIEN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN(int ma_PHANVIEC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN()).getString_GiaidoanThuchien(ma_PHANVIEC);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_THUC_HIEN result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DangThuchien_SuachuaBaoduong(nd);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					e.setCONGVIEC(dsb);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
					e.setPHANVIEC(gdth);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DangThuchien_TangTaisan(nd);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_TANG_TAISAN dtt = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					e.setCONGVIEC(dtt);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);

					e.setPHANVIEC(gdth);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangThucHien_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DangThuchien_GiamTaisan(nd);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_GIAM_TAISAN dgt = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					e.setCONGVIEC(dgt);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
					e.setPHANVIEC(gdth);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DaThuchien_SuachuaBaoduong(nd);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					e.setCONGVIEC(dsb);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
					e.setPHANVIEC(gdth);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DaThuchien_TangTaisan(nd);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();

					DOT_THUCHIEN_TANG_TAISAN dtt = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					e.setCONGVIEC(dtt);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
					e.setPHANVIEC(gdth);

					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaThucHien_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = new ArrayList<>();
				String query = (new query_Select_GIAI_DOAN_THUC_HIEN())
						.getString_Congviec_Phanviec_DaThuchien_GiamTaisan(nd);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();
					DOT_THUCHIEN_GIAM_TAISAN dgt = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					e.setCONGVIEC(dgt);

					GIAI_DOAN_THUC_HIEN gdth = (new Control_DAO_Build()).get_GIAI_DOAN_THUC_HIEN(rs);
					e.setPHANVIEC(gdth);

					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_DukienThuchien(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, int dukienThuchien)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN())
						.getString_Capnhat_Dukien_GiaidoanThuchien(get_GIAIDOAN_THUCHIEN, dukienThuchien);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean Update_Giaidoan_Thuchien(GIAI_DOAN_THUC_HIEN gdth) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN()).getString_Update_Giaidoan_Thuchien(gdth);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_ThoiDiemKetthucCongviec(GIAI_DOAN_THUC_HIEN gdth, Date tHISDAY) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN())
						.getString_Capnhat_Thoidiem_Ketthuc_GiaidoanThuchien(gdth, tHISDAY);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean set_NGAYCHUYENGIAO_PHANVIEC(GIAI_DOAN_THUC_HIEN giai_DOAN_THUC_HIEN, Date tHISDAY)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN())
						.getString_Capnhat_Thoidiem_Chuyengiao_GiaidoanThuchien(giai_DOAN_THUC_HIEN, tHISDAY);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;

		}

		public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, Date tHISDAY)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN())
						.getString_Capnhat_Thoidiem_Batdau_GiaidoanThuchien(get_GIAIDOAN_THUCHIEN, tHISDAY);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;

		}

		public boolean update_Ghichu(GIAI_DOAN_THUC_HIEN get_GIAIDOAN_THUCHIEN, String text) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_THUC_HIEN()).getString_Capnhat_Ghichu(get_GIAIDOAN_THUCHIEN,
						text);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}

	private class Delete extends DELactivity {
		public Boolean deleteNGUOIDUNG_GIAI_DOAN_THUC_HIEN(String ten_TAI_KHOAN, int ma_GIAI_DOAN_CONG_VIEC)
				throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_GIAI_DOAN_THUC_HIEN())
						.getString_Xoa_GiaidoanThuchien_Canbo(ten_TAI_KHOAN, ma_GIAI_DOAN_CONG_VIEC);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;

		}
	}

}
