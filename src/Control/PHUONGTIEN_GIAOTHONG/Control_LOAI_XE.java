package Control.PHUONGTIEN_GIAOTHONG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.ROLE.PrivilegeChecker;
import DAO.Kyhan_Baoduong;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_LOAI_XE;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_LOAI_XE;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_LOAI_XE;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_LOAI_XE;

public class Control_LOAI_XE {
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

	public Control_LOAI_XE(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public LOAI_XE get_LOAI_XE(int ma_LOAI_XE) throws SQLException {
		return getSelecter().get_LOAI_XE(ma_LOAI_XE);
	}

	public ArrayList<Integer> get_DateList() throws SQLException {
		return getSelecter().get_DateList();
	}

	public ArrayList<LOAI_XE> get_AllLOAI_XE() throws SQLException {
		return getSelecter().get_AllLOAI_XE();
	}

	public ArrayList<LOAI_XE> get_AllLOAI_XE_XEMAY() throws SQLException {
		return getSelecter().get_AllLOAI_XE_XEMAY();
	}

	public ArrayList<LOAI_XE> get_AllLOAI_XE_OTO() throws SQLException {
		return getSelecter().get_AllLOAI_XE_OTO();
	}

	public Kyhan_Baoduong getKyhanBaoduong(LOAI_XE lx) throws SQLException {
		return getSelecter().get_Kyhan_Baoduong(lx);
	}

	public int insert_LOAI_XE(LOAI_XE i) throws SQLException {
		return getInserter().insert_LOAI_XE(i);
	}

	public void remove_LOAI_XE(LOAI_XE i) throws SQLException {
		getDeleter().remove_LOAI_XE(i);
	}

	public void update_LOAI_XE(LOAI_XE r) throws SQLException {
		getUpdater().update_LOAI_XE(r);
	}

	public void update_TieuchuanBaoduong_LOAI_XE(LOAI_XE r) throws SQLException {
		getUpdater().update_TieuchuanBaoduong_LOAI_XE(r);
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

		public int getNextKey() {
			if (conn != null)
				return new Control_Tool(conn).nextKey_TABLE("LOAI_XE");
			return -1;
		}

		public int insert_LOAI_XE(LOAI_XE i) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_LOAI_XE()).getString_ThemLoaixe(i);
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
		public Kyhan_Baoduong get_Kyhan_Baoduong(LOAI_XE lx) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				Kyhan_Baoduong result = new Kyhan_Baoduong();
				String query = (new query_Select_LOAI_XE()).getString_KyhanBaoduong(lx);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_Kyhan_Baoduong(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<Integer> get_DateList() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_LOAI_XE()).getString_DateList();
				if (query == null)
					return null;
				ArrayList<Integer> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					buildResult(result, (new Control_DAO_Build()).get_Kyhan_Baoduong(rs));
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private void buildResult(ArrayList<Integer> result, Kyhan_Baoduong get_Hinhthuc_Baoduong) {

			Boolean b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayNhot() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayNhot());
			b = true;
			for (Integer integer : result) {

				if (get_Hinhthuc_Baoduong.getThayLocNhot() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayLocNhot());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayLocnhienlieu() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayLocnhienlieu());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayLocgio() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayLocgio());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayLocgioGianlanh() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayLocgioGianlanh());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayDauVisai() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayDauVisai());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayDautroluclai() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayDautroluclai());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayDauphanh_Daulyhop() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayDauphanh_Daulyhop());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getThayDauhopso() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getThayDauhopso());
			b = true;
			for (Integer integer : result) {
				if (get_Hinhthuc_Baoduong.getBaoduongkhac() == integer) {
					b = false;
					break;
				}
			}
			if (b)
				result.add(get_Hinhthuc_Baoduong.getBaoduongkhac());

		}

		public LOAI_XE get_LOAI_XE(int ma_LOAI_XE) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				LOAI_XE p = null;
				String query = (new query_Select_LOAI_XE()).getString_Loaixe(ma_LOAI_XE);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					p = (new Control_DAO_Build()).get_LOAI_XE(rs);
				}
				rs.close();
				st.close();
				return p;
			}
			return null;
		}

		public ArrayList<LOAI_XE> get_AllLOAI_XE() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LOAI_XE> result = new ArrayList<>();
				String query = (new query_Select_LOAI_XE()).getString_TatcaLoaixe();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LOAI_XE p = (new Control_DAO_Build()).get_LOAI_XE(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LOAI_XE> get_AllLOAI_XE_XEMAY() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LOAI_XE> result = new ArrayList<>();
				String query = (new query_Select_LOAI_XE()).getString_TatcaXemay();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LOAI_XE p = (new Control_DAO_Build()).get_LOAI_XE(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LOAI_XE> get_AllLOAI_XE_OTO() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LOAI_XE> result = new ArrayList<>();
				String query = (new query_Select_LOAI_XE()).getString_TatcaOto();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LOAI_XE p = (new Control_DAO_Build()).get_LOAI_XE(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		private void update_TieuchuanBaoduong_LOAI_XE(LOAI_XE r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_LOAI_XE()).getString_CapnhatTieuchuanBaoduong(r);
				if (query == null)
					return;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
			}
		}

		public void update_LOAI_XE(LOAI_XE r) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_LOAI_XE()).getString_CapnhatLoaixe(r);
				if (query == null)
					return;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
			}
		}
	}

	private class Delete extends DELactivity {
		public void remove_LOAI_XE(LOAI_XE i) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_LOAI_XE()).getString_Xoa(i);
				if (query == null)
					return;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
			}
		}

	}

}
