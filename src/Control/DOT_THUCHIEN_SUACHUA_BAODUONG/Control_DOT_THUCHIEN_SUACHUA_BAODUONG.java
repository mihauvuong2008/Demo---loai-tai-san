package Control.DOT_THUCHIEN_SUACHUA_BAODUONG;

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
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.NGUOIDUNG;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.QUATRINH_NGHIEMTHU_QUYETTOAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG;

public class Control_DOT_THUCHIEN_SUACHUA_BAODUONG {
	private Connection conn;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final PrivilegeChecker pvc;
	Control_DEXUAT cdx;
	Control_QUATRINH_DEXUAT_THUCHIEN cqdt;
	Control_QUATRINH_NGHIEMTHU_QUYETTOAN cqnq;

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
				return (new Control_Tool(conn)).nextKey_TABLE("DOT_THUCHIEN_SUACHUA_BAODUONG");
			return -1;
		}

		public int InsertDOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, QUATRINH_DEXUAT_THUCHIEN qdt,
				QUATRINH_NGHIEMTHU_QUYETTOAN qnq) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Insert_Dot_Suachua_Baoduong(dsb, qdt, qnq);
				if (query == null)
					return -1;
				int nextkey = getNextKey();
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
		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Integer maTaiSan)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Suachua_Baoduong_cua_Taisan(maTaiSan);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					dsbl.add(dsb);
				}
				rs.close();
				st.close();
				return dsbl;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_All_DotSuachua_BaoduongTaisan(Date date, Date date2,
				String text) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_All_Dot_Suachua_Baoduong(date, date2, text);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					dsbl.add(dsb);
				}
				rs.close();
				st.close();
				return dsbl;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_Baoduongkhac_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_Baoduongkhac_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauTroluclai(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayDauTroluclai_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocgioGianlanh_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayLocgioGianlanh_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauhopso_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayDauhopso_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauvisai_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayDauvisai_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauphanh_Daulyhop_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayDauphanh_Daulyhop_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocNhienlieu_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayLocNhienlieu_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocgio_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayLocgio_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocnhot_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_ThayLocnhot_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayNhot_Gannhat(int ma_TAISAN)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Select_Dot_Baoduong_Thaynhot_Gannhat(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(int ma_PHONGBAN,
				int loaiPTGT) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Danhsach_Baoduong_PhuongTienGThong_cua_Phongban(ma_PHONGBAN, loaiPTGT);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					dsbl.add(dsb);
				}
				rs.close();
				st.close();
				return dsbl;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DangThucHien_Suachua_Baoduong() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dexuat1 = get_ChuaHoanthanh_DE_XUAT();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> thuchien1 = get_ChuaHoanthanh_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> thuchien2 = get_ChuaTao_GIAIDOAN_THUCHIEN();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> nghiemthu1 = get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> nghiemthu2 = get_ChuaTao_GIAIDOAN_NGHIEMTHU();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> quyettoan1 = get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> quyettoan2 = get_ChuaTao_GIAIDOAN_QUYETTOAN();
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				if (dexuat1 != null)
					result.addAll(dexuat1);
				if (thuchien1 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : thuchien1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (thuchien2 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : thuchien2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (nghiemthu1 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : nghiemthu1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (nghiemthu2 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : nghiemthu2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (quyettoan1 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : quyettoan1) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
								flag = false;
							}
						}
						if (flag) {
							result.add(e);
						}
					}
				if (quyettoan2 != null)
					for (DOT_THUCHIEN_SUACHUA_BAODUONG e : quyettoan2) {
						boolean flag = true;
						int size = result.size();
						for (int i = 0; i < size; i++) {
							if (result.get(i).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() == e
									.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()) {
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

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaTao_GIAIDOAN_QUYETTOAN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaTao_Quyettoan();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaHoanthanh_GIAIDOAN_QUYETTOAN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaHoanthanh_Quyettoan();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaTao_GIAIDOAN_NGHIEMTHU() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				// chưa thực hiện đề xuất
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaTao_Nghiemthu();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaHoanthanh_GIAIDOAN_NGHIEMTHU() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaHoanthanh_Nghiemthu();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaTao_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaTao_Thuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaHoanthanh_GIAIDOAN_THUCHIEN() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaHoanthanh_Thuchien();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		private ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_ChuaHoanthanh_DE_XUAT() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				// đợt tăng chưa hoàn thành đề xuất
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotSuachua_Baoduong_ChuaHoanthanh_Dexuat();
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_SUACHUA_BAODUONG(int ma_CONGVIEC) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG()).getString_DotSuachua(ma_CONGVIEC);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_SUACHUA_BAODUONG(DE_XUAT dx) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_DotthuchienSuachua_Baoduong(dx);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = null;
				while (rs.next()) {
					dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
				}
				rs.close();
				st.close();
				return dsb;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Date begin, Date end)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Danhsach_DotSuachuaBaoduong_Bieudo(begin, end);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Date begin, Date end,
				int ma_TAISAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Danhsach_DotSuachuaBaoduong_Ganday(begin, end, ma_TAISAN);
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_ChuaHoanthanh()
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> result = new ArrayList<>();
				String query = (new query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Danhsach_DotSuachuaBaoduong_ChuaHoanthanh();
				if (query == null)
					return null;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (new Control_DAO_Build()).get_DOT_THUCHIEN_SUACHUA_BAODUONG(rs);
					result.add(dsb);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Capnhat_GiaidoanQuyettoan(dsb);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_Nguon_Suachua_Baoduong(NGUONSUACHUA_BAODUONG nsb, DOT_THUCHIEN_SUACHUA_BAODUONG dsb)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Capnhat_NguonSuachua_Baoduong(nsb, dsb);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb)
				throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				if (vIEW_dsb == null)
					return false;
				String query = (new query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_Capnhat_DotSuachua_Baoduong(vIEW_dsb);
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
		public boolean delete_DOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				if (dsb == null)
					return false;
				DE_XUAT d = cdx.get_DEXUAT(dsb);
				cdx.delete_DEXUAT(d);
				cqdt.delete_QUATRINH_DEXUAT_THUCHIEN(dsb.getMA_QUATRINH_DEXUAT_THUCHIEN());
				cqnq.delete_QUATRINH_NGHIEMTHU_QUYETTOAN(dsb.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN());
				String query = (new query_Delete_DOT_THUCHIEN_SUACHUA_BAODUONG())
						.getString_delete_DotSuachua_Baoduong(dsb);
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

	public Control_DOT_THUCHIEN_SUACHUA_BAODUONG(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
		cdx = new Control_DEXUAT(user);
		cqdt = new Control_QUATRINH_DEXUAT_THUCHIEN(user);
		cqnq = new Control_QUATRINH_NGHIEMTHU_QUYETTOAN(user);
	}

	public int InsertDOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, QUATRINH_DEXUAT_THUCHIEN qdt,
			QUATRINH_NGHIEMTHU_QUYETTOAN qnq) throws SQLException {
		return getInserter().InsertDOT_THUCHIEN_SUACHUA_BAODUONG(dsb, qdt, qnq);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Integer maTaiSan)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(maTaiSan);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_Thaynhot_Gannhat(int ma_TAISAN) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayNhot_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocnhot_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayLocnhot_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocgio_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayLocgio_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocNhienlieu_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayLocNhienlieu_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauphanh_Daulyhop_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayDauphanh_Daulyhop_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauvisai_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayDauvisai_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauhopso_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayDauhopso_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayLocgioGianlanh_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayLocgioGianlanh_Gannhat(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_ThayDauTroluclai(int ma_TAISAN) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_ThayDauTroluclai(ma_TAISAN);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_BAODUONG_Baoduongkhac_Gannhat(int ma_TAISAN)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_BAODUONG_Baoduongkhac_Gannhat(ma_TAISAN);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(int ma_PHONGBAN,
			int loaiPTGT) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(ma_PHONGBAN, loaiPTGT);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DangThucHien_Suachua_Baoduong() throws SQLException {
		return getSelecter().get_DangThucHien_Suachua_Baoduong();
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_SUACHUA_BAODUONG(int ma_CONGVIEC) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG(ma_CONGVIEC);
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_SUACHUA_BAODUONG(DE_XUAT dx) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG(dx);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Date begin, Date end)
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(begin, end);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Date begin, Date end,
			int ma_TAISAN) throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(begin, end, ma_TAISAN);
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_DOT_THUCHIEN_SUACHUA_BAODUONG_ChuaHoanthanh()
			throws SQLException {
		return getSelecter().get_DOT_THUCHIEN_SUACHUA_BAODUONG_ChuaHoanthanh();
	}

	public ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> get_All_DotSuachua_BaoduongTaisan(Date date, Date date2,
			String text) throws SQLException {
		return getSelecter().get_All_DotSuachua_BaoduongTaisan(date, date2, text);
	}

	public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(
			DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getUpdater().update_DOT_THUCHIEN_SUACHUA_BAODUONG_Update_QUATRINH_NGHIEMTHU_QUYETTOAN(dsb);

	}

	public boolean update_DOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb) throws SQLException {
		return getUpdater().update_DOT_THUCHIEN_SUACHUA_BAODUONG(vIEW_dsb);
	}

	public boolean update_Nguon_Suachua_Baoduong(NGUONSUACHUA_BAODUONG nsb, DOT_THUCHIEN_SUACHUA_BAODUONG dsb)
			throws SQLException {
		return getUpdater().update_Nguon_Suachua_Baoduong(nsb, dsb);
	}

	public boolean delete_DOT_THUCHIEN_SUACHUA_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getDeleter().delete_DOT_THUCHIEN_SUACHUA_BAODUONG(dsb);
	}

}
