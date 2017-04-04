package Control.LENH_DIEU_XE;

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
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LENH_DIEU_XE;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_LENH_DIEU_XE;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LENH_DIEU_XE;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LENH_DIEU_XE;

public class Control_LENH_DIEU_XE {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final Control_SYSTEM_LOG cs;
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

	public Control_LENH_DIEU_XE(NGUOIDUNG user) {
		conn = user.getConn();
		cs = new Control_SYSTEM_LOG(user);
		pvc = user.getPrivilegeChecker();
	}

	public int getNetxt_Key() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("LENH_DIEU_XE");
		return -1;
	}

	public ArrayList<LENH_DIEU_XE> getAll_LENH_DIEU_XE() throws SQLException {
		cs.insertLog(new Log_Library().getString_XemToanboLenhDieuxe());
		return getSelecter().getAll_LENH_DIEU_XE();
	}

	public ArrayList<LENH_DIEU_XE> get_List_LENH_DIEU_XE(PHONGBAN dv, Date date_Batdau, Date date_Ketthuc,
			boolean huyLenh) throws SQLException {
		cs.insertLog(new Log_Library().getString_Xem_Danhsach_LenhDieuxe__Phongban_Batdau_Ketthuc(dv, date_Batdau,
				date_Ketthuc));
		return getSelecter().get_Data_LENH_DIEU_XE(dv, date_Batdau, date_Ketthuc, huyLenh);
	}

	public ArrayList<LENH_DIEU_XE> get_List_LENH_DIEU_XE(PHUONGTIEN_GIAOTHONG pt, Date date_Batdau, Date date_Ketthuc)
			throws SQLException {
		cs.insertLog(new Log_Library().getString_Xem_Danhsach_LenhDieuxe__PhuongtienGiaothong_Batdau_Ketthuc(pt,
				date_Batdau, date_Ketthuc));
		return getSelecter().get_Data_LENH_DIEU_XE(pt, date_Batdau, date_Ketthuc);
	}

	public Date get_NgayDautien_DIEUXE() throws SQLException {
		cs.insertLog(new Log_Library().getString_get_NgayDautien_DIEUXE());
		return getSelecter().get_NgayDautien_DIEUXE();
	}

	public Date get_NgayCuoicung_DIEUXE() throws SQLException {
		cs.insertLog(new Log_Library().getString_get_NgayCuoicung_DIEUXE());
		return getSelecter().get_NgayCuoicung_DIEUXE();
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_Oto_Dasudung(PHONGBAN dv, Date date_Batdau, Date date_Ketthuc)
			throws SQLException {
		cs.insertLog(
				new Log_Library().getString_get_Oto_Dasudung__Phongban_Batdau_Ketthuc(dv, date_Batdau, date_Ketthuc));
		return getSelecter().get_Oto_Dasudung(dv, date_Batdau, date_Ketthuc);
	}

	public LENH_DIEU_XE get_LENHDIEUXE_DAUKY(PHUONGTIEN_GIAOTHONG pt, Date date_Batdau) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_LENHDIEUXE_DAUKY__PhuongtienGiaothong_Date(pt, date_Batdau));
		return getSelecter().get_LENHDIEUXE_DAUKY(pt, date_Batdau);
	}

	public LENH_DIEU_XE get_LENHDIEUXE_CUOIKY(PHUONGTIEN_GIAOTHONG pt, Date date_Ketthuc) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_LENHDIEUXE_CUOIKY__PhuongtienGiaothong_Date(pt, date_Ketthuc));
		return getSelecter().get_LENHDIEUXE_CUOIKY(pt, date_Ketthuc);
	}

	public LENH_DIEU_XE get_LENH_DIEU_XE_AFTER(LENH_DIEU_XE last) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_LENHDIEUXE_AFTER(last));
		return getSelecter().get_LENH_DIEU_XE_AFTER(last);
	}

	public ArrayList<String> get_Recent_DiemXuatphat(int i) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_DiemxuatphatGanday(i));
		return getSelecter().get_Recent_DiemXuatphat(i);
	}

	public ArrayList<String> get_Recent_DiemDen(int i) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_DiemdenGanday(i));
		return getSelecter().get_Recent_DiemDen(i);
	}

	public LENH_DIEU_XE get_LENHDIEUXE_Gannhat(PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_LENHDIEUXE_Gannhat(ptgt));
		return getSelecter().get_LENHDIEUXE_Gannhat(ptgt);
	}

	public LENH_DIEU_XE get_LENH_DIEU_XE(int ma_LENH_DIEU_XE) throws SQLException {
		cs.insertLog(new Log_Library().getString_get_LENHDIEUXE(ma_LENH_DIEU_XE));
		return getSelecter().get_LENH_DIEU_XE(ma_LENH_DIEU_XE);
	}

	public int insert_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
		cs.insertLog(new Log_Library().getString_Them_LENHDIEUXE(l.getMA_PHUONGTIEN_GIAOTHONG()));
		return getInserter().insert_LENH_DIEU_XE(l);
	}

	public Integer update_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
		cs.insertLog(new Log_Library().getString_Capnhat_LENHDIEUXE(l.getMA_LENH_DIEU_XE()));
		return getUpdater().update_LENH_DIEU_XE(l);
	}

	public boolean detele_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
		cs.insertLog(new Log_Library().getString_Xoa_LENHDIEUXE(l.getMA_LENH_DIEU_XE()));
		return getDeleter().detele_LENH_DIEU_XE(l);
	}

	public boolean set_Huylenh(LENH_DIEU_XE l, Boolean Huy) throws SQLException {
		cs.insertLog(new Log_Library().getString_Huy_LENHDIEUXE(l.getMA_LENH_DIEU_XE()));
		return getUpdater().set_Huylenh(l, Huy);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_THONGTIN_TAISAN().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public int insert_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_LENH_DIEU_XE()).getString_ThemLenhDieuxe(l);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return getNetxt_Key();
			}
			return -1;
		}
	}

	private class Select extends REAactivity {
		public ArrayList<LENH_DIEU_XE> getAll_LENH_DIEU_XE() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LENH_DIEU_XE> result;
				String query = (new query_Select_LENH_DIEU_XE()).getString_TatcaLenhDieuxe_Cohieuluc();
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LENH_DIEU_XE l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
					result.add(l);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LENH_DIEU_XE> get_Data_LENH_DIEU_XE(PHONGBAN dv, Date date_Batdau, Date date_Ketthuc,
				boolean huyLenh) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LENH_DIEU_XE> result;
				String query = (new query_Select_LENH_DIEU_XE())
						.getString_LenhDieuxe_Theo_Phongban_Thoidiembatdau_ThoidiemKetthuc_Conhieuluc(dv, date_Batdau,
								date_Ketthuc, huyLenh);

				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LENH_DIEU_XE l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
					result.add(l);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;

		}

		public ArrayList<LENH_DIEU_XE> get_Data_LENH_DIEU_XE(PHUONGTIEN_GIAOTHONG pt, Date date_Batdau,
				Date date_Ketthuc) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				if (date_Batdau != null && date_Ketthuc != null && pt != null) {
					ArrayList<LENH_DIEU_XE> result;
					String query = null;
					query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe_cua_Phuongtiengiaothong(pt,
							date_Batdau, date_Ketthuc);
					result = new ArrayList<>();
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(query);

					while (rs.next()) {
						LENH_DIEU_XE l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
						result.add(l);
					}
					rs.close();
					st.close();
					return result;
				}
			}
			return null;

		}

		public Date get_NgayDautien_DIEUXE() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				Date d = null;
				String query = (new query_Select_LENH_DIEU_XE()).getString_NgayDautienDieuXe();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					d = rs.getTimestamp(1);
				}
				rs.close();
				st.close();
				return d;
			}
			return null;
		}

		public Date get_NgayCuoicung_DIEUXE() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				Date d = null;
				String query = (new query_Select_LENH_DIEU_XE()).getString_CuoicungtienDieuXe();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					d = rs.getTimestamp(1);
				}
				rs.close();
				st.close();
				return d;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_Oto_Dasudung(PHONGBAN dv, Date date_Batdau, Date date_Ketthuc)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_LENH_DIEU_XE()).getString_Oto_Dasudung(dv, date_Batdau, date_Ketthuc);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG PtGt = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(PtGt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public LENH_DIEU_XE get_LENHDIEUXE_DAUKY(PHUONGTIEN_GIAOTHONG l2, Date date_Batdau) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe_DaukyThongke(l2, date_Batdau);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				LENH_DIEU_XE l = null;
				while (rs.next()) {
					l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
					break;// lay row dau tien
				}
				rs.close();
				st.close();
				return l;
			}
			return null;
		}

		public LENH_DIEU_XE get_LENHDIEUXE_CUOIKY(PHUONGTIEN_GIAOTHONG l2, Date date_Ketthuc) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe_CuoikyThongke(l2, date_Ketthuc);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				LENH_DIEU_XE l = null;
				while (rs.next()) {
					l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
				}
				rs.close();
				st.close();
				return l;
			}
			return null;
		}

		public LENH_DIEU_XE get_LENH_DIEU_XE_AFTER(LENH_DIEU_XE last) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe_After(last);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				LENH_DIEU_XE l = null;
				while (rs.next()) {
					l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
				}
				rs.close();
				st.close();
				return l;
			}
			return null;
		}

		public ArrayList<String> get_Recent_DiemXuatphat(int i) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<String> result = new ArrayList<>();
				String query = (new query_Select_LENH_DIEU_XE()).getString_DiemXuatphat_Ganday(i);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result.add(rs.getString("DIEM_XUATPHAT"));
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<String> get_Recent_DiemDen(int i) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<String> result = new ArrayList<>();
				String query = (new query_Select_LENH_DIEU_XE()).getString_DiemdenGanday(i);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result.add(rs.getString("DIEM_DEN"));
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public LENH_DIEU_XE get_LENHDIEUXE_Gannhat(PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				LENH_DIEU_XE l = null;
				String query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe_Gannhat(ptgt);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
				}
				rs.close();
				st.close();
				return l;
			}
			return null;
		}

		public LENH_DIEU_XE get_LENH_DIEU_XE(int ma_LENH_DIEU_XE) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				LENH_DIEU_XE l = null;
				String query = (new query_Select_LENH_DIEU_XE()).getString_Lenhdieuxe(ma_LENH_DIEU_XE);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					l = (new Control_DAO_Build()).get_LENH_DIEU_XE(rs);
				}
				rs.close();
				st.close();
				return l;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public Integer update_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_LENH_DIEU_XE()).getString_Capnhat_Lenhdieuxe(l);
				System.out.println(query);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return l.getMA_LENH_DIEU_XE();
			}
			return -1;
		}

		public boolean set_Huylenh(LENH_DIEU_XE l, Boolean Huy) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_LENH_DIEU_XE()).getString_Huylenh_Laplailenh(l, Huy);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	private class Delete extends DELactivity {
		public boolean detele_LENH_DIEU_XE(LENH_DIEU_XE l) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_LENH_DIEU_XE()).getString_Xoa_LenhDieuxe(l);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

}
