package Control.PHUONGTIEN_GIAOTHONG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.DOT_THUCHIEN_SUACHUA_BAODUONG.Control_DOT_THUCHIEN_SUACHUA_BAODUONG;
import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.LichBaoduong_ROW;
import DAO.NGUOIDUNG;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_PHUONGTIEN_GIAOTHONG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_PHUONGTIEN_GIAOTHONG;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_PHUONGTIEN_GIAOTHONG;

public class Control_PHUONGTIEN_GIAOTHONG {
	private final Connection conn;
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	public final PrivilegeChecker pvc;
	public Control_DOT_THUCHIEN_SUACHUA_BAODUONG cdsb;

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
		public int insert_PHUONGTIEN_GIAOTHONG(PHUONGTIEN_GIAOTHONG p) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_PHUONGTIEN_GIAOTHONG()).getString_ThemPhuongtienGiaothong(p);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return 0;
			}
			return -1;
		}
	}

	private class Select extends REAactivity {
		public PHUONGTIEN_GIAOTHONG get_PHUONGTIEN_GIAOTHONG(String MA_PHUONGTIEN_GIAOTHONG) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_PhuongtinGiaothong(MA_PHUONGTIEN_GIAOTHONG);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				PHUONGTIEN_GIAOTHONG result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public PHUONGTIEN_GIAOTHONG get_PHUONGTIEN_GIAOTHONG_FromTaisan(int MA_TAISAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_PhuongtinGiaothong_From_Taisan(MA_TAISAN);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				PHUONGTIEN_GIAOTHONG result = null;
				while (rs.next()) {
					result = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_Oto() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG()).getString_TatcaOto();
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_Oto(PHONGBAN phongbanSelected2, String text)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG()).getString_TatcaOto(phongbanSelected2, text);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_PHUONGTIEN_GIAOTHONG() throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG()).getString_Tatca();
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_PHUONGTIEN_GIAOTHONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, PHONGBAN dv)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_PhuongtienGiaoThong_cuaPhongban_Suachua(dsb, dv);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<PHUONGTIEN_GIAOTHONG> get_PHUONGTIEN_GIAOTHONG(int loaiPTGT, PHONGBAN dv) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<PHUONGTIEN_GIAOTHONG> result;
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_PhuongtienGiaothong_cua_Phongban_Oto_Xemay(loaiPTGT, dv);
				result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					PHUONGTIEN_GIAOTHONG p = (new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs);
					result.add(p);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public int get_LoaiNhienlieu(String ma_PHUONGTIEN_GIAOTHONG) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				String query = (new query_Update_PHUONGTIEN_GIAOTHONG())
						.getString_Capnhat_LoaiNhienlieu(ma_PHUONGTIEN_GIAOTHONG);
				int result = 0;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					result = (rs.getInt("XANG_DAU"));
				}
				rs.close();
				st.close();
				return result;
			}
			return 0;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_Thaynhot(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_Thaynhot(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong((cdsb.get_DOT_THUCHIEN_BAODUONG_Thaynhot_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocnhot(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayLocnhot(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayLocnhot_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocgio(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayLocgio(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayLocgio_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocNhienlieu(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayLocNhienlieu(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayLocNhienlieu_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauphanh_Daulyhop(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayDauphanh_Daulyhop(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong((cdsb
							.get_DOT_THUCHIEN_BAODUONG_ThayDauphanh_Daulyhop_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauhopso(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayDauhopso(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayDauhopso_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauvisai(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayDauvisai(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayDauvisai_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocgioGianlanh(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayLocgioGianlanh(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_ThayLocgioGianlanh_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauTroluclai(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_ThayDauTroluclai(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong((cdsb.get_DOT_THUCHIEN_BAODUONG_ThayDauTroluclai(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<LichBaoduong_ROW> getLichbaoduong_Baoduongkhac(PHONGBAN phongban_Selected, Date date)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<LichBaoduong_ROW> result = new ArrayList<>();
				String query = (new query_Select_PHUONGTIEN_GIAOTHONG())
						.getString_getLichbaoduong_Baoduongkhac(phongban_Selected, date);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					LichBaoduong_ROW lr = new LichBaoduong_ROW();
					lr.setLoaixe((new Control_DAO_Build()).get_LOAI_XE(rs));
					lr.setHtbd(new Control_DAO_Build().get_Kyhan_Baoduong(rs));
					lr.setPhuongtienGiaothong((new Control_DAO_Build()).get_PHUONGTIEN_GIAOTHONG(rs));
					lr.setTaisan((new Control_DAO_Build()).get_TAISAN_TINY(rs));
					lr.setDotBaoduong(
							(cdsb.get_DOT_THUCHIEN_BAODUONG_Baoduongkhac_Gannhat(lr.getTaisan().getMA_TAISAN())));
					result.add(lr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
		public String update_soKmXe(String maptgt, Integer valueOf) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_PHUONGTIEN_GIAOTHONG()).getString_Capnhat_SoKm(maptgt, valueOf);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return maptgt;
			}
			return null;
		}

		public boolean update_PHUONGTIEN_GIAOTHONG(PHUONGTIEN_GIAOTHONG phuongtien_Giaothong) throws SQLException {
			if (conn != null && isPrivilegeEDI()) {
				String query = (new query_Update_PHUONGTIEN_GIAOTHONG())
						.getString_update_PHUONGTIEN_GIAOTHONG(phuongtien_Giaothong);
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
	}

	public Control_PHUONGTIEN_GIAOTHONG(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
		cdsb = new Control_DOT_THUCHIEN_SUACHUA_BAODUONG(user);
	}

	public PHUONGTIEN_GIAOTHONG get_PHUONGTIEN_GIAOTHONG(String MA_PHUONGTIEN_GIAOTHONG) throws SQLException {
		return getSelecter().get_PHUONGTIEN_GIAOTHONG(MA_PHUONGTIEN_GIAOTHONG);
	}

	public PHUONGTIEN_GIAOTHONG get_PHUONGTIEN_GIAOTHONG_FromTaisan(int MA_TAISAN) throws SQLException {
		return getSelecter().get_PHUONGTIEN_GIAOTHONG_FromTaisan(MA_TAISAN);
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_Oto() throws SQLException {
		return getSelecter().get_All_Oto();
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_Oto(PHONGBAN phongbanSelected2, String text) throws SQLException {
		return getSelecter().get_All_Oto(phongbanSelected2, text);
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_All_PHUONGTIEN_GIAOTHONG() throws SQLException {
		return getSelecter().get_All_PHUONGTIEN_GIAOTHONG();
	}

	public int insert_PHUONGTIEN_GIAOTHONG(PHUONGTIEN_GIAOTHONG p) throws SQLException {
		return getInserter().insert_PHUONGTIEN_GIAOTHONG(p);
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_PHUONGTIEN_GIAOTHONG(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, PHONGBAN dv)
			throws SQLException {
		return getSelecter().get_PHUONGTIEN_GIAOTHONG(dsb, dv);
	}

	public ArrayList<PHUONGTIEN_GIAOTHONG> get_PHUONGTIEN_GIAOTHONG(int loaiPTGT, PHONGBAN dv) throws SQLException {
		return getSelecter().get_PHUONGTIEN_GIAOTHONG(loaiPTGT, dv);
	}

	public String update_soKmXe(String maptgt, Integer valueOf) throws SQLException {
		return getUpdater().update_soKmXe(maptgt, valueOf);
	}

	public int get_LoaiNhienlieu(String ma_PHUONGTIEN_GIAOTHONG) throws SQLException {
		return getSelecter().get_LoaiNhienlieu(ma_PHUONGTIEN_GIAOTHONG);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_Thaynhot(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_Thaynhot(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocnhot(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayLocnhot(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocgio(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayLocgio(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocNhienlieu(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayLocNhienlieu(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauphanh_Daulyhop(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayDauphanh_Daulyhop(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauhopso(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayDauhopso(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauvisai(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayDauvisai(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayLocgioGianlanh(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayLocgioGianlanh(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayDauTroluclai(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_ThayDauTroluclai(phongban_Selected, date);
	}

	public ArrayList<LichBaoduong_ROW> getLichbaoduong_ThayBaoduongkhac(PHONGBAN phongban_Selected, Date date)
			throws SQLException {
		return getSelecter().getLichbaoduong_Baoduongkhac(phongban_Selected, date);
	}

	public boolean update_PHUONGTIEN_GIAOTHONG(PHUONGTIEN_GIAOTHONG phuongtien_Giaothong) throws SQLException {
		return getUpdater().update_PHUONGTIEN_GIAOTHONG(phuongtien_Giaothong);
	}

}
