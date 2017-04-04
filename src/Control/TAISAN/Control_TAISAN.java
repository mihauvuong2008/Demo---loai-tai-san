package Control.TAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import Control.PHONGBAN.Control_PHONGBAN;
import Control.PHUONGTIEN_GIAOTHONG.Control_PHUONGTIEN_GIAOTHONG;
import Control.ROLE.PrivilegeChecker;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_TAISAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_TAISAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_TAISAN;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_TAISAN;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.NGUOIDUNG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;

public class Control_TAISAN {

	private final Connection conn;

	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	public final PrivilegeChecker pvc;
	public Control_PHONGBAN cdv;
	public Control_PHUONGTIEN_GIAOTHONG cpg;

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
		public int insert_TAISAN(TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				int nextkey = getNextKey();
				String query = (new query_Insert_TAISAN()).getString_Them_Taisan(t);
				if (query == null)
					return -1;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				if (t.getPhukienList() != null && nextkey != 0)
					for (PHUKIEN p : t.getPhukienList()) {
						p.setMA_TAISAN(nextkey);
						String query2 = (new query_Insert_TAISAN()).getString_Them_Phukien(p);
						prs = conn.prepareStatement(query2);
						prs.executeUpdate();
					}
				prs.close();
				return nextkey;
			}
			return -1;
		}

		public boolean insert_PHUKIEN(PHUKIEN pk) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_TAISAN()).getString_Them_Phukien(pk);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	private class Select extends REAactivity {
		public ArrayList<TAISAN> Data_TaiSan_Mainform_LoaiTaisan(PHONGBAN dv, LOAITAISAN_CAP_III p, LOAITAISAN_CAP_II n,
				LOAITAISAN_CAP_I l) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_FLASH_ACCESS_MAINFORM_LoaiTaisan(dv, p, n, l);
				if (query == null)
					return null;
				ArrayList<TAISAN> Data_TaiSan_List = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FLASH_ACCESS_ANDIMPORT_DV_SDUNG(rs);
					String SubQuery_Phukien = (new query_Select_TAISAN()).getString_get_Phukien_Short(t);
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(SubQuery_Phukien);
					ArrayList<PHUKIEN> pkl = new ArrayList<>();
					while (rs2.next()) {
						PHUKIEN pk = (new Control_DAO_Build()).get_PHUKIEN_FLASH_ACCESS(rs2);
						pkl.add(pk);
					}
					rs2.close();
					st2.close();
					t.setPhukienList(pkl);
					Data_TaiSan_List.add(t);
				}
				rs.close();
				st.close();
				return Data_TaiSan_List;
			}
			return null;
		}

		public ArrayList<TAISAN> Data_TaiSan_Mainform_NhomTaisan(PHONGBAN dv, NHOMTAISAN_CAP_V lv5,
				NHOMTAISAN_CAP_IV lv4, NHOMTAISAN_CAP_III lv3, NHOMTAISAN_CAP_II lv2, NHOMTAISAN_CAP_I lv1)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_FLASH_ACCESS_MAINFORM_NhomTaisan(dv, lv5, lv4, lv3,
						lv2, lv1);
				if (query == null)
					return null;
				ArrayList<TAISAN> Data_TaiSan_List = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FLASH_ACCESS_ANDIMPORT_DV_SDUNG(rs);
					String SubQuery_Phukien = (new query_Select_TAISAN()).getString_get_Phukien_Short(t);
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(SubQuery_Phukien);
					ArrayList<PHUKIEN> pkl = new ArrayList<>();
					while (rs2.next()) {
						PHUKIEN pk = (new Control_DAO_Build()).get_PHUKIEN_FLASH_ACCESS(rs2);
						pkl.add(pk);
					}
					rs2.close();
					st2.close();
					t.setPhukienList(pkl);
					Data_TaiSan_List.add(t);
				}
				rs.close();
				st.close();
				return Data_TaiSan_List;
			}
			return null;
		}

		public ArrayList<PHUKIEN> get_DataPhuKien(TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUKIEN> PhuKien_List = new ArrayList<>();
				String query = (new query_Select_TAISAN()).getString_get_Phukien_FULL(t);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					PHUKIEN p = (new Control_DAO_Build()).get_PHUKIEN_FULL(rs);
					if (p.getMA_PHUKIEN() != 0)
						PhuKien_List.add(p);
				}
				rs.close();
				st.close();
				return PhuKien_List;
			}
			return null;
		}

		public TAISAN get_Taisan(Integer key) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_TAISAN_Full(key);
				if (query == null)
					return null;
				TAISAN t = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					t = (new Control_DAO_Build()).get_TAISAN_FULL(rs);
					PHONGBAN donvi_Sundung = cdv.get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
					t.setDonvi_Sudung(donvi_Sundung);
					PHONGBAN donvi_Quanly = cdv.get_PHONGBAN(t.getMA_DON_VI_QUAN_LY());
					t.setDonvi_Quanly(donvi_Quanly);
					ArrayList<PHUKIEN> pklist = get_DataPhuKien(t);
					t.setPhukienList(pklist);
					break;
				}
				rs.close();
				st.close();
				return t;
			}
			return null;
		}

		public TAISAN get_Taisan_MaLienKet(String maTaisanLienket) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_TAISAN_From_MaLienket(maTaisanLienket);
				if (query == null)
					return null;
				TAISAN t = null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					t = (new Control_DAO_Build()).get_TAISAN_FULL(rs);
					break;
				}
				rs.close();
				st.close();
				return t;
			}
			return null;
		}

		public ArrayList<TAISAN> get_Taisan_Timkiem(String text_tentaisan, PHONGBAN p) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_TAISAN_Full(text_tentaisan, p);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ArrayList<TAISAN> result = new ArrayList<>();
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FULL(rs);
					PHONGBAN donvi_Sundung = cdv.get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
					t.setDonvi_Sudung(donvi_Sundung);
					PHONGBAN donvi_Quanly = cdv.get_PHONGBAN(t.getMA_DON_VI_QUAN_LY());
					t.setDonvi_Quanly(donvi_Quanly);
					ArrayList<PHUKIEN> pklist = get_DataPhuKien(t);
					t.setPhukienList(pklist);
					result.add(t);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public int getcountTaisan(String ColumnName) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_Tong_SoluongHang_theoColumn(ColumnName);
				if (query == null)
					return -1;
				Statement st;
				ResultSet rs;
				st = conn.createStatement();
				rs = st.executeQuery(query);
				int count = 0;
				while (rs.next()) {
					count = Integer.valueOf(rs.getString("COUNT(*)"));
				}
				rs.close();
				st.close();
				return count;
			}
			return -1;
		}

		public String[] get_RandomTaisan(String ColumnName, int num) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				int i = getcountTaisan(ColumnName);
				if (num < i) {
					i = num;
				}
				String item[] = new String[i];
				String query = (new query_Select_TAISAN()).getString_Random_Dulieu_Column(ColumnName, num);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				int x = 0;
				while (rs.next()) {
					item[x] = rs.getString(ColumnName);
					x++;
				}
				rs.close();
				st.close();
				return item;
			}
			return null;
		}

		public ArrayList<TAISAN> get_Taisan_Oto(PHONGBAN dv) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<TAISAN> result;
				String query = (new query_Select_TAISAN()).getString_Taisan_Oto(dv);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_TINY(rs);
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					t.setPhuongtien_Giaothong(p);
					result.add(t);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<TAISAN> get_Taisan_Xemay(PHONGBAN dv) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<TAISAN> result;
				String query = (new query_Select_TAISAN()).getString_Taisan_Xemey(dv);
				if (query == null)
					return null;
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_TINY(rs);
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					t.setPhuongtien_Giaothong(p);
					result.add(t);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public TAISAN get_Taisan(PHUONGTIEN_GIAOTHONG pg) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_Taisan(pg);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				TAISAN t = new TAISAN();
				while (rs.next()) {
					t = (new Control_DAO_Build()).get_TAISAN_TINY(rs);
					PHUONGTIEN_GIAOTHONG p = cpg.get_PHUONGTIEN_GIAOTHONG(rs.getString("MA_PHUONGTIEN_GIAOTHONG"));
					t.setPhuongtien_Giaothong(p);
				}
				rs.close();
				st.close();
				return t;
			}
			return null;
		}

		public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_Taisan_DotGiamTaisan(dgt);
				if (query == null)
					return null;
				ArrayList<TAISAN> Data_TaiSan_List = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FLASH_ACCESS_ANDIMPORT_DV_SDUNG(rs);
					String SubQuery_Phukien = (new query_Select_TAISAN()).getString_get_Phukien_Short(t);
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(SubQuery_Phukien);
					ArrayList<PHUKIEN> pkl = new ArrayList<>();
					while (rs2.next()) {
						PHUKIEN pk = (new Control_DAO_Build()).get_PHUKIEN_FLASH_ACCESS(rs2);
						pkl.add(pk);
					}
					rs2.close();
					st2.close();
					t.setPhukienList(pkl);
					Data_TaiSan_List.add(t);
				}
				rs.close();
				st.close();
				return Data_TaiSan_List;
			}
			return null;
		}

		public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<TAISAN> result = new ArrayList<>();
				String query = (new query_Select_TAISAN()).getString_Taisan_SuachuaBaoduong(dsb);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FULL(rs);
					PHONGBAN donvi_Sudung = cdv.get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
					t.setDonvi_Sudung(donvi_Sudung);
					PHONGBAN donvi_Quanly = cdv.get_PHONGBAN(t.getMA_DON_VI_QUAN_LY());
					t.setDonvi_Quanly(donvi_Quanly);
					ArrayList<PHUKIEN> pklist = get_DataPhuKien(t);
					t.setPhukienList(pklist);
					result.add(t);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_TAISAN()).getString_FlASH_ACCESS_DotTangTaisan(dtt);
				if (query == null)
					return null;
				ArrayList<TAISAN> Data_TaiSan_List = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FLASH_ACCESS_ANDIMPORT_DV_SDUNG(rs);
					String SubQuery_Phukien = (new query_Select_TAISAN()).getString_get_Phukien_FULL(t);

					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(SubQuery_Phukien);

					ArrayList<PHUKIEN> pkl = new ArrayList<>();
					while (rs2.next()) {
						PHUKIEN pk = (new Control_DAO_Build()).get_PHUKIEN_FLASH_ACCESS(rs2);
						pkl.add(pk);
					}
					rs2.close();
					st2.close();
					t.setPhukienList(pkl);

					Data_TaiSan_List.add(t);
				}
				rs.close();
				st.close();
				return Data_TaiSan_List;
			}
			return null;
		}

		public ArrayList<TAISAN> get_TAINSAN_FULL_INFO(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<TAISAN> result = new ArrayList<>();
				String query = (new query_Select_TAISAN()).getString_Taisan_TangTaisan(dtt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					TAISAN t = (new Control_DAO_Build()).get_TAISAN_FULL(rs);

					PHONGBAN donvi_Sundung = cdv.get_PHONGBAN(t.getMA_DON_VI_SU_DUNG());
					t.setDonvi_Sudung(donvi_Sundung);

					PHONGBAN donvi_Quanly = cdv.get_PHONGBAN(t.getMA_DON_VI_QUAN_LY());
					t.setDonvi_Quanly(donvi_Quanly);

					ArrayList<PHUKIEN> pklist = get_DataPhuKien(t);
					t.setPhukienList(pklist);
					result.add(t);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

	}

	private class Update extends EDIactivity {
		public void Update_Donvi_Sudung(String key, Integer mAPHONGBAN) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_TAISAN()).getString_Capnhat_Donvi_Sudung(key, mAPHONGBAN);
				if (query == null)
					return;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
			}
		}

		public boolean Update_Taisan(TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_TAISAN()).getString_Capnhat_Thongtin_Taisan(t);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean Update_Phukien(PHUKIEN pk) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_TAISAN()).getString_Capnhat_Phukien(pk);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean Update_LoaiTaisan(int key, int mA_NhomTaisan) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_TAISAN()).getString_Capnhat_Taisan_LoaiTaisan(key, mA_NhomTaisan);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean Update_NhomTaisan(int key, int mA_NhomTaisan) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_TAISAN()).getString_Taisan_NhomTaisan(key, mA_NhomTaisan);
				if (query == null)
					return false;
				System.out.println(query);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	private class Delete extends DELactivity {
		public boolean delete_TAISAN(TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeDEL() && (t != null)) {
				PreparedStatement prs;
				if (t.getPhukienList() != null) {
					String query = (new query_Delete_TAISAN()).getString_Xoa_Phukien(t);
					if (query == null)
						return false;
					prs = conn.prepareStatement(query);
					prs.executeUpdate();
				}
				String query = (new query_Delete_TAISAN()).getString_Xoa_Taisan(t);
				if (query == null)
					return false;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean delete_All_DOTTANGTAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				PreparedStatement prs;
				String query = (new query_Delete_TAISAN()).getString_Xoa_Taisan_Muasam(dtt);
				if (query == null)
					return false;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

		public boolean delete_PHUKIEN(PHUKIEN pk) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				PreparedStatement prs;
				String query = (new query_Delete_TAISAN()).getString_Xoa_Phukien(pk);
				if (query == null)
					return false;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}

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

	public Control_TAISAN(NGUOIDUNG user) {
		conn = user.getConn();
		cdv = new Control_PHONGBAN(user);
		cpg = new Control_PHUONGTIEN_GIAOTHONG(user);
		pvc = user.getPrivilegeChecker();
	}

	public ArrayList<TAISAN> Data_TaiSan_Mainform_LoaiTaisan(PHONGBAN dv, LOAITAISAN_CAP_III p, LOAITAISAN_CAP_II n,
			LOAITAISAN_CAP_I l) throws SQLException {
		return getSelecter().Data_TaiSan_Mainform_LoaiTaisan(dv, p, n, l);
	}

	public ArrayList<TAISAN> Data_TaiSan_Mainform_NhomTaisan(PHONGBAN dv, NHOMTAISAN_CAP_V lv5, NHOMTAISAN_CAP_IV lv4,
			NHOMTAISAN_CAP_III lv3, NHOMTAISAN_CAP_II lv2, NHOMTAISAN_CAP_I lv1) throws SQLException {
		return getSelecter().Data_TaiSan_Mainform_NhomTaisan(dv, lv5, lv4, lv3, lv2, lv1);
	}

	public ArrayList<PHUKIEN> get_DataPhuKien(TAISAN t) throws SQLException {
		return getSelecter().get_DataPhuKien(t);
	}

	public TAISAN get_Taisan(Integer key) throws SQLException {
		return getSelecter().get_Taisan(key);
	}

	public TAISAN getFlashTaisan_MaLienKet(String maTaisanLienket) throws SQLException {
		return getSelecter().get_Taisan_MaLienKet(maTaisanLienket);
	}

	public ArrayList<TAISAN> get_Taisan_Timkiem(String text, PHONGBAN p) throws SQLException {
		return getSelecter().get_Taisan_Timkiem(text, p);
	}

	public int getcountTaisan(String ColumnName) throws SQLException {
		return getSelecter().getcountTaisan(ColumnName);
	}

	public String[] get_RandomTaisan(String ColumnName, int num) throws SQLException {
		return getSelecter().get_RandomTaisan(ColumnName, num);
	}

	public void Update_Donvi_Sudung(String key, Integer mAPHONGBAN) throws SQLException {
		getUpdater().Update_Donvi_Sudung(key, mAPHONGBAN);
	}

	public boolean Update_Taisan(TAISAN t) throws SQLException {
		return getUpdater().Update_Taisan(t);
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("TAISAN");
		return -1;
	}

	public Integer insert_TAISAN(TAISAN t) throws SQLException {
		return getInserter().insert_TAISAN(t);
	}

	public boolean delete_TAISAN(TAISAN t) throws SQLException {
		return getDeleter().delete_TAISAN(t);
	}

	public ArrayList<TAISAN> get_Taisan_Oto(PHONGBAN dv) throws SQLException {
		return getSelecter().get_Taisan_Oto(dv);
	}

	public ArrayList<TAISAN> get_Taisan_Xemay(PHONGBAN dv) throws SQLException {
		return getSelecter().get_Taisan_Xemay(dv);
	}

	public TAISAN get_Taisan(PHUONGTIEN_GIAOTHONG pg) throws SQLException {
		return getSelecter().get_Taisan(pg);
	}

	public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		return getSelecter().get_TAISAN(dgt);
	}

	public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) throws SQLException {
		return getSelecter().get_TAISAN(dsb);
	}

	public ArrayList<TAISAN> get_TAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getSelecter().get_TAISAN(dtt);
	}

	public ArrayList<TAISAN> get_TAINSAN_FULL_INFO(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getSelecter().get_TAINSAN_FULL_INFO(dtt);
	}

	public boolean delete_All_DOTTANGTAISAN(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return getDeleter().delete_All_DOTTANGTAISAN(dtt);
	}

	public boolean delete_PHUKIEN(PHUKIEN pk) throws SQLException {
		return getDeleter().delete_PHUKIEN(pk);
	}

	public boolean insert_PHUKIEN(PHUKIEN pk) throws SQLException {
		return getInserter().insert_PHUKIEN(pk);
	}

	public boolean Update_Phukien(PHUKIEN pk) throws SQLException {
		return getUpdater().Update_Phukien(pk);
	}

	public boolean Update_LoaiTaisan(int key, int mA_NhomTaisan) throws SQLException {
		return getUpdater().Update_LoaiTaisan(key, mA_NhomTaisan);
	}

	public boolean Update_NhomTaisan(int key, int mA_NhomTaisan) throws SQLException {
		return getUpdater().Update_NhomTaisan(key, mA_NhomTaisan);
	}

}
