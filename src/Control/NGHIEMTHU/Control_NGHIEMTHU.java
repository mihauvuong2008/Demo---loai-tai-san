package Control.NGHIEMTHU;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.CONGVIEC_PHANVIEC;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_GIAI_DOAN_NGHIEM_THU;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_GIAI_DOAN_NGHIEM_THU;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_GIAI_DOAN_NGHIEM_THU;

public class Control_NGHIEMTHU {
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

	public Control_NGHIEMTHU(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("GIAI_DOAN_NGHIEM_THU");
		return -1;
	}

	public int create_GIAI_DOAN_NGHIEMTHU(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getInserter().create_GIAI_DOAN_NGHIEMTHU(dtt);
	}

	public int create_GIAI_DOAN_NGHIEMTHU(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getInserter().create_GIAI_DOAN_NGHIEMTHU(dsb);
	}

	public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getSelecter().get_GIAIDOAN_NGHIEMTHU(dtt);
	}

	public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getSelecter().get_GIAIDOAN_NGHIEMTHU(dsb);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangNghiemthu_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangNghiemthu_TANG_TAISAN(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DangNghiemthu_GIAM_TAISAN(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaNghiemthu_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaNghiemthu_SUACHUA_BAODUONG(nd);
	}

	public ArrayList<CONGVIEC_PHANVIEC> get_DaNghiemthu_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
		return getSelecter().get_DaNghiemthu_TANG_TAISAN(nd);
	}

	public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(int ma_PHANVIEC) throws SQLException {
		return getSelecter().get_GIAIDOAN_NGHIEMTHU(ma_PHANVIEC);
	}

	public boolean update_Ghichu(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, String text) throws SQLException {
		return getUpdater().update_Ghichu(get_GIAIDOAN_NGHIEMTHU, text);
	}

	public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY)
			throws SQLException {
		return getUpdater().update_ThoiDiemBatdauCongviec(get_GIAIDOAN_NGHIEMTHU, tHISDAY);
	}

	public boolean set_NGAYCHUYENGIAO_PHANVIEC(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY)
			throws SQLException {
		return getUpdater().set_NGAYCHUYENGIAO_PHANVIEC(get_GIAIDOAN_NGHIEMTHU, tHISDAY);
	}

	public boolean update_ThoiDiemKetthucPhanviec(GIAI_DOAN_NGHIEM_THU gdnt, Date tHISDAY) throws SQLException {
		return getUpdater().update_ThoiDiemKetthucPhanviec(gdnt, tHISDAY);
	}

	public boolean update_DukienThuchien(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, int dukienThuchien)
			throws SQLException {
		return getUpdater().update_DukienThuchien(get_GIAIDOAN_NGHIEMTHU, dukienThuchien);
	}

	public boolean Update_Giaidoan_Nghiemthu(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
		return getUpdater().Update_Giaidoan_Nghiemthu(gdnt);
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
		public int create_GIAI_DOAN_NGHIEMTHU(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_NGHIEM_THU())
						.getString_TaoGiaidoanNghiemthu_MuasamTaisan(dtt);
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

		public int create_GIAI_DOAN_NGHIEMTHU(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_GIAI_DOAN_NGHIEM_THU())
						.getString_TaoGiaidoanNghiemthu_SuachuaBaoduong(dsb);
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
		public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_GiaidoanNghiemthu_tuDotTangTaisan(dtt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_NGHIEM_THU result = null;
				while (rs.next()) {
					if (rs.getString("MA_GIAI_DOAN_NGHIEM_THU") == null)
						break;
					result = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);

				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				if (dsb == null)
					return null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_GiaidoanNghiemthu_tuDotSuachuaBaoduong(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_NGHIEM_THU result = null;
				while (rs.next()) {
					// if (rs.getString("MA_GIAI_DOAN_NGHIEM_THU") == null)
					// break;
					result = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);

				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_Congviec_Phanviec_DangNGHIEMTHU_suachuaBaoduong(nd);
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
					GIAI_DOAN_NGHIEM_THU gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_CongviecPhanviec_DangNGHIEMTHU_TangTaisan(nd);
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
					GIAI_DOAN_NGHIEM_THU gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DangNghiemthu_GIAM_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_CongviecPhanviec_DangNGHIEMTHU_GiamTaisan(nd);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					CONGVIEC_PHANVIEC e = null;
					e = new CONGVIEC_PHANVIEC();
					DOT_THUCHIEN_GIAM_TAISAN dgt = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					e.setCONGVIEC(dgt);
					GIAI_DOAN_NGHIEM_THU gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaNghiemthu_SUACHUA_BAODUONG(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_CongviecPhanviec_DaNGHIEMTHU_SuachuaBaoduong(nd);
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
					GIAI_DOAN_NGHIEM_THU gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
					e.setPHANVIEC(gdnt);
					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<CONGVIEC_PHANVIEC> get_DaNghiemthu_TANG_TAISAN(NGUOIDUNG nd) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<CONGVIEC_PHANVIEC> result = null;
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU())
						.getString_CongviecPhanviec_DaNGHIEMTHU_TangTaisan(nd);
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
					GIAI_DOAN_NGHIEM_THU gdnt = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
					e.setPHANVIEC(gdnt);

					result.add(e);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU(int ma_PHANVIEC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_GIAI_DOAN_NGHIEM_THU()).getString_GiaidoanNghiemthu(ma_PHANVIEC);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				GIAI_DOAN_NGHIEM_THU result = null;
				while (rs.next()) {
					if (rs.getString("MA_GIAI_DOAN_NGHIEM_THU") == null)
						break;
					result = (new Control_DAO_Build()).get_GIAI_DOAN_NGHIEM_THU(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public boolean update_Ghichu(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, String text) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU())
						.getString_Capnhat_Ghichu(get_GIAIDOAN_NGHIEMTHU, text);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_ThoiDiemBatdauCongviec(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU())
						.getString_Capnhat_ThoidiemBatdauCongviec(get_GIAIDOAN_NGHIEMTHU, tHISDAY);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean set_NGAYCHUYENGIAO_PHANVIEC(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, Date tHISDAY)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU())
						.getString_Capnhat_NgayChuyengiaoPhanviec(get_GIAIDOAN_NGHIEMTHU, tHISDAY);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_ThoiDiemKetthucPhanviec(GIAI_DOAN_NGHIEM_THU gdnt, Date tHISDAY) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU()).getString_Capnhat_ThoidiemKetthucPhanviec(gdnt,
						tHISDAY);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_DukienThuchien(GIAI_DOAN_NGHIEM_THU get_GIAIDOAN_NGHIEMTHU, int dukienThuchien)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU())
						.getString_Capnhat_DukienThoiganHoanthanh(get_GIAIDOAN_NGHIEMTHU, dukienThuchien);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean Update_Giaidoan_Nghiemthu(GIAI_DOAN_NGHIEM_THU gdnt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_GIAI_DOAN_NGHIEM_THU()).getString_Update_Giaidoan_Nghiemthu(gdnt);
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
