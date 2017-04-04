package Control.DOT_THUCHIEN_GIAM_TAISAN;

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
import Control.ROLE.PrivilegeChecker;
import Control.SYSTEM_LOG.Control_SYSTEM_LOG;
import Control.SYSTEM_LOG.Log_Library;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONGIAM;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_THUCHIEN_GIAM_TAISAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_THUCHIEN_GIAM_TAISAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_GIAM_TAISAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_THUCHIEN_GIAM_TAISAN;

public class Control_DOT_THUCHIEN_GIAM_TAISAN {
	private Control_SYSTEM_LOG cs;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final PrivilegeChecker pvc;
	private final Control_DEXUAT cdx;
	private final Control_QUATRINH_DEXUAT_THUCHIEN cqdt;

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

	public Control_DOT_THUCHIEN_GIAM_TAISAN(NGUOIDUNG user) {
		conn = user.getConn();
		cs = new Control_SYSTEM_LOG(user);
		pvc = user.getPrivilegeChecker();
		cdx = new Control_DEXUAT(user);
		cqdt = new Control_QUATRINH_DEXUAT_THUCHIEN(user);
	}

	public Date get_NGAYTHUCHIEN_GIAMTAISAN(DOT_THUCHIEN_GIAM_TAISAN dg) throws SQLException {
		Date rs = getSelecter().get_NGAYTHUCHIEN_GIAMTAISAN(dg);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_NgaythuchienThanhlyTaisan(dg));
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_All_DotGiamTaisan() throws SQLException {
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> rs = getSelecter().get_All_DotGiamTaisan();
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_DotThanhlyTaisan());
		return rs;
	}

	public int InsertDOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dg, NGUONGIAM ng) throws SQLException {
		int rs = getInserter().InsertDOT_THUCHIEN_GIAM_TAISAN(dg, ng);
		if (rs >= 0)
			cs.insertLog(new Log_Library().getString_Them_DotThanhlyTaisan(dg));
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DangThucHien_DotGiamTaisan() throws SQLException {
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> rs = getSelecter().get_DangThucHien_DotGiamTaisan();
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Danhsach_DotThanhlyTaisan_Dangthuchien());
		return rs;
	}

	public DOT_THUCHIEN_GIAM_TAISAN get_DOT_THUCHIEN_GIAM_TAISAN(int ma_DOTGIAM) throws SQLException {
		DOT_THUCHIEN_GIAM_TAISAN rs = getSelecter().get_DOT_THUCHIEN_GIAM_TAISAN(ma_DOTGIAM);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_DotThanhlyTaisan(ma_DOTGIAM));
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DOT_THUCHIEN_GIAM_TAISAN_ChuaHoanthanh() throws SQLException {
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> rs = getSelecter().get_DOT_THUCHIEN_GIAM_TAISAN_ChuaHoanthanh();
		if (rs != null)
			cs.insertLog(new Log_Library().getString_DOT_THUCHIEN_GIAM_TAISAN_ChuaHoanthanh());
		return rs;
	}

	public boolean delete_DOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		boolean rs = getDeleter().delete_DOT_THUCHIEN_GIAM_TAISAN(dgt);
		if (rs)
			cs.insertLog(new Log_Library().getString_XoaDOT_THUCHIEN_GIAM_TAISAN(dgt));
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DOT_THUCHIEN_GIAM_TAISAN_list(Date begin, Date end)
			throws SQLException {
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> rs = getSelecter().get_DOT_THUCHIEN_GIAM_TAISAN_list(begin, end);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_DOT_THUCHIEN_GIAM_TAISAN(begin, end));
		return rs;
	}

	public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_All_DotGiamTaisan(Date date, Date date2, String text)
			throws SQLException {
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> rs = getSelecter().get_All_DotGiamTaisan(date, date2, text);
		if (rs != null)
			cs.insertLog(new Log_Library().getString_Xem_Danhsach_DOT_THUCHIEN_GIAM_TAISAN(date, date2));
		return rs;
	}

	public boolean update_DOT_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		boolean rs = getUpdater().update_DOT_GIAM_TAISAN(dgt);
		if (rs)
			cs.insertLog(new Log_Library().getString_Capnhat_DOT_THUCHIEN_GIAM_TAISAN(dgt));
		return rs;
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
		public int getNextKey() {
			if (conn != null)
				return (new Control_Tool(conn)).nextKey_TABLE("DOT_THUCHIEN_GIAM_TAISAN");
			return -1;
		}

		public int InsertDOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dg, NGUONGIAM ng) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_Insert_DotGiamTaisan_With_NguonGiam(dg, ng);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}

	}

	private class Select extends REAactivity {

		public Date get_NGAYTHUCHIEN_GIAMTAISAN(DOT_THUCHIEN_GIAM_TAISAN dg) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_NgayBatdau_GiaiDoanThuchien_GiamTaisan(dg.getMA_DOT_GIAM());
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					return rs.getTimestamp("THOI_DIEM_BAT_DAU");
				}
				rs.close();
				st.close();
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_All_DotGiamTaisan() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN()).getString_Tatca_DotGiamTaisan();
				if (query == null)
					return null;
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DangThucHien_DotGiamTaisan() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dexuat1 = get_ChuaHoanthanh_DE_XUAT();
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> thuchien1 = get_ChuaHoanthanh_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> thuchien2 = get_ChuaTao_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				if (dexuat1 != null)
					result.addAll(dexuat1);
				if (thuchien1 != null)
					for (DOT_THUCHIEN_GIAM_TAISAN e : thuchien1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_GIAM() == e.getMA_DOT_GIAM()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (thuchien2 != null)
					for (DOT_THUCHIEN_GIAM_TAISAN e : thuchien2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_GIAM() == e.getMA_DOT_GIAM()) {
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

		private ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_ChuaTao_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_DotGiamTaisan_ChuaTao_GiaidoanThuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_ChuaHoanthanh_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_DotGiamTaisan_ChuaHoanthanh_GiaidoanThuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_ChuaHoanthanh_DE_XUAT() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				Statement st;
				ResultSet rs;
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				// đợt gia?m chưa hoàn thành đề xuất
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_DotGiamTaisan_ChuaHoanthanh_GiaidoanDexuat();
				if (query == null)
					return null;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dgt = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dgt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public DOT_THUCHIEN_GIAM_TAISAN get_DOT_THUCHIEN_GIAM_TAISAN(int ma_DOTGIAM) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN()).getString_DotGiamTaisan(ma_DOTGIAM);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				DOT_THUCHIEN_GIAM_TAISAN dgt = null;
				while (rs.next()) {
					dgt = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
				}
				rs.close();
				st.close();
				return dgt;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DOT_THUCHIEN_GIAM_TAISAN_list(Date begin, Date end)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_Danhsach_DotGiamTaisan_Bieudo(begin, end);
				if (query == null)
					return null;
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_All_DotGiamTaisan(Date date, Date date2, String text)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN()).getString_Select_All_Dot_giam(date, date2,
						text);
				if (query == null)
					return null;
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
					result.add(dts);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_GIAM_TAISAN> get_DOT_THUCHIEN_GIAM_TAISAN_ChuaHoanthanh() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_GIAM_TAISAN())
						.getString_Danhsach_DotGiamTaisan_ChuaHoanthanh();
				if (query == null)
					return null;
				ArrayList<DOT_THUCHIEN_GIAM_TAISAN> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_GIAM_TAISAN dts = (new Control_DAO_Build()).get_DOT_THUCHIEN_GIAM_TAISAN(rs);
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
		public boolean update_DOT_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_GIAM_TAISAN()).getString_Capnhat_DotGiamTaisan(dgt);
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
		public boolean delete_DOT_THUCHIEN_GIAM_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (dgt == null)
					return false;
				DE_XUAT d = cdx.get_DEXUAT(dgt);
				cdx.delete_DEXUAT(d);
				cqdt.delete_QUATRINH_DEXUAT_THUCHIEN(dgt.getMA_QUATRINH_DEXUAT_THUCHIEN());
				String query = (new query_Delete_DOT_THUCHIEN_GIAM_TAISAN()).getString_Delete_Dot_GiamTaisan(dgt);
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
