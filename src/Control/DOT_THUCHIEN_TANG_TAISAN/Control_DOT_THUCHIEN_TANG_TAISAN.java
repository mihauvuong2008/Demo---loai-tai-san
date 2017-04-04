package Control.DOT_THUCHIEN_TANG_TAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Control.DEXUAT.Control_DEXUAT;
import Control.QUATRINH_DEXUAT_THUCHIEN.Control_QUATRINH_DEXUAT_THUCHIEN;
import Control.QUATRINH_NGHIEMTHU_QUYETTOAN.Control_QUATRINH_NGHIEMTHU_QUYETTOAN;
import Control.ROLE.PrivilegeChecker;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.QUATRINH_NGHIEMTHU_QUYETTOAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_THUCHIEN_TANG_TAISAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_THUCHIEN_TANG_TAISAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_TANG_TAISAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_THUCHIEN_TANG_TAISAN;

public class Control_DOT_THUCHIEN_TANG_TAISAN {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	public Control_DEXUAT cdx;
	public Control_QUATRINH_DEXUAT_THUCHIEN cqdt;
	public Control_QUATRINH_NGHIEMTHU_QUYETTOAN cnqt;
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

	public Control_DOT_THUCHIEN_TANG_TAISAN(NGUOIDUNG user) {
		conn = user.getConn();
		cdx = new Control_DEXUAT(user);
		cqdt = new Control_QUATRINH_DEXUAT_THUCHIEN(user);
		cnqt = new Control_QUATRINH_NGHIEMTHU_QUYETTOAN(user);
		pvc = user.getPrivilegeChecker();
	}

	public DOT_THUCHIEN_TANG_TAISAN get_DotTangTaisan_Gannhat(int Mataisan) throws SQLException {
		return getSelecter().get_DotTangTaisan_Gannhat(Mataisan);
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_All_DotTangTaisan() throws SQLException {
		return getSelecter().get_All_DotTangTaisan();
	}

	public int InsertDOT_THUCHIEN_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt, QUATRINH_DEXUAT_THUCHIEN qdt,
			QUATRINH_NGHIEMTHU_QUYETTOAN qnq, NGUONTANG nt) throws SQLException {
		return getInserter().InsertDOT_THUCHIEN_TANG_TAISAN(dt, qdt, qnq, nt);

	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_DE_XUAT() throws SQLException {
		return getSelecter().get_ChuaHoanthanh_DE_XUAT();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_THUCHIEN() throws SQLException {
		return getSelecter().get_ChuaHoanthanh_GIAIDOAN_THUCHIEN();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_THUCHIEN() throws SQLException {
		return getSelecter().get_ChuaTao_GIAIDOAN_THUCHIEN();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU() throws SQLException {
		return getSelecter().get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_NGHIEMTHU() throws SQLException {
		return getSelecter().get_ChuaTao_GIAIDOAN_NGHIEMTHU();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN() throws SQLException {
		return getSelecter().get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_QUYETTOAN() throws SQLException {
		return getSelecter().get_ChuaTao_GIAIDOAN_QUYETTOAN();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DangThucHien_DotTangTaisan() throws SQLException {
		return getSelecter().get_DangThucHien_DotTangTaisan();
	}

	public DOT_THUCHIEN_TANG_TAISAN get_DOT_THUCHIEN_TANG_TAISAN(int ma_CONGVIEC) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_TANG_TAISAN(ma_CONGVIEC);
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DOT_THUCHIEN_TANG_TAISAN_list(Date begin, Date end)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_TANG_TAISAN_list(begin, end);
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DOT_THUCHIEN_TANG_TAISAN_ChuaHoanthanh() throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_TANG_TAISAN_ChuaHoanthanh();
	}

	public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_All_DotTangTaisan(Date date, Date date2, String text)
			throws SQLException {
		return getSelecter().get_All_DotTangTaisan(date, date2, text);
	}

	public boolean delete_DOT_THUCHIEN_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt) throws SQLException {
		return getDeleter().delete_DOT_THUCHIEN_TANG_TAISAN(dt);
	}

	public boolean update_DOT_TANG_TAISAN_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt,
			int mA_QUATRINH_NGHIEMTHU_QUYETTOAN) throws SQLException {
		return getUpdater().update_DOT_TANG_TAISAN_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(dtt,
				mA_QUATRINH_NGHIEMTHU_QUYETTOAN);
	}

	public boolean update_DOT_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getUpdater().update_DOT_TANG_TAISAN(dtt);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_HOSO().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {

		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("DOT_THUCHIEN_TANG_TAISAN");
			return -1;
		}

		public int InsertDOT_THUCHIEN_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt, QUATRINH_DEXUAT_THUCHIEN qdt,
				QUATRINH_NGHIEMTHU_QUYETTOAN qnq, NGUONTANG nt) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_DOT_THUCHIEN_TANG_TAISAN()).getString_Insert_DotTangTaisan(dt, qdt,
						qnq, nt);
				if (query == null)
					return -1;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;

		}

	}

	private class Select extends REAactivity {
		public DOT_THUCHIEN_TANG_TAISAN get_DotTangTaisan_Gannhat(int Mataisan) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_TANG_TAISAN dts = null;
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN()).getString_DotTangtaisan_Gannhat(Mataisan);
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					return dts;
				}
				rs.close();
				st.close();
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_All_DotTangTaisan() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN()).getString_TatcaDotTangtaisan();
				if (query == null)
					return null;
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_DE_XUAT() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// đợt tăng chưa hoàn thành đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaHoanthanh_Dexuat();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaHoanthanh_Thuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN()).getString_DotTangTaisan_ChuaTao_Thuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaHoanthanh_Nghiemthu();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_NGHIEMTHU() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();

				Statement st;
				ResultSet rs;
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaTao_Nghiemthu();
				if (query == null)
					return null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaHoanthanh_Quyettoan();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_ChuaTao_GIAIDOAN_QUYETTOAN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_DotTangTaisan_ChuaTao_Quyettoan();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DangThucHien_DotTangTaisan() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> dexuat1 = get_ChuaHoanthanh_DE_XUAT();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> thuchien1 = get_ChuaHoanthanh_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> thuchien2 = get_ChuaTao_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> nghiemthu1 = get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> nghiemthu2 = get_ChuaTao_GIAIDOAN_NGHIEMTHU();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> quyettoan1 = get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> quyettoan2 = get_ChuaTao_GIAIDOAN_QUYETTOAN();
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				if (dexuat1 != null)
					result.addAll(dexuat1);
				if (thuchien1 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : thuchien1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (thuchien2 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : thuchien2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (nghiemthu1 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : nghiemthu1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (nghiemthu2 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : nghiemthu2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (quyettoan1 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : quyettoan1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (quyettoan2 != null)
					for (DOT_THUCHIEN_TANG_TAISAN e : quyettoan2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_TANG() == e.getMA_DOT_TANG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				return result;
			}
			return null;
		}

		public DOT_THUCHIEN_TANG_TAISAN get_DOT_THUCHIEN_TANG_TAISAN(int ma_CONGVIEC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN()).getString_DotTangTaisan(ma_CONGVIEC);
				DOT_THUCHIEN_TANG_TAISAN dts = null;
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
				}
				rs.close();
				st.close();
				return dts;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DOT_THUCHIEN_TANG_TAISAN_list(Date begin, Date end)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();

				Statement st;
				ResultSet rs;
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_Danhsach_DotTangTaisan_Bieudo(begin, end);
				if (query == null)
					return null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_All_DotTangTaisan(Date date, Date date2, String text)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN()).getString_All_DotTangTaisan(date, date2,
						text);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_TANG_TAISAN> get_DOT_THUCHIEN_TANG_TAISAN_ChuaHoanthanh() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_TANG_TAISAN> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN())
						.getString_Danhsach_DotTangTaisan_ChuaHoanthanh();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_TANG_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_TANG_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_DOT_TANG_TAISAN_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(DOT_THUCHIEN_TANG_TAISAN dtt,
				int mA_QUATRINH_NGHIEMTHU_QUYETTOAN) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_TANG_TAISAN())
						.getString_QuatrinhNghiemthu_Capnhat_Giaidoan_Quyettoan(dtt, mA_QUATRINH_NGHIEMTHU_QUYETTOAN);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_DOT_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_TANG_TAISAN()).getString_Capnhat_DotTangTaisan(dtt);
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
		public boolean delete_DOT_THUCHIEN_TANG_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (dt == null)
					return false;
				DE_XUAT d = cdx.get_DEXUAT(dt);
				cdx.delete_DEXUAT(d);
				cqdt.delete_QUATRINH_DEXUAT_THUCHIEN(dt.getMA_QUATRINH_DEXUAT_THUCHIEN());
				cnqt.delete_QUATRINH_NGHIEMTHU_QUYETTOAN(dt.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN());
				String query = (new query_Delete_DOT_THUCHIEN_TANG_TAISAN()).getString_Delete_DotTangTaisan(dt);
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
