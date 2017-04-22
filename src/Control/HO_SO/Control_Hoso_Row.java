package Control.HO_SO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Control.ROLE.PrivilegeChecker;
import DAO.HOSO_ROW;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;
import DAO.VANBAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;

public class Control_Hoso_Row {

	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private final Connection conn;
	private final MyDateFormat mdf = new MyDateFormat();
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

	public Control_Hoso_Row(NGUOIDUNG user) {
		conn = user.getConn();
		pvc = user.getPrivilegeChecker();
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
	}

	private class Select extends REAactivity {
		public ArrayList<HOSO_ROW> get_AllData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '" + mdf.getSQLStringDate(start)
						+ "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end) + "' AND (MA_TAPHOSO LIKE '%"
						+ searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%"
						+ searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";

					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<HOSO_ROW> get_BangiaonoiboData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_BANGIAO_TAISAN_NOIBO as dbtn "
						+ " ON ths.MA_TAPHOSO = dbtn.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);

					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<HOSO_ROW> get_Suachua_BaoduongData(Date start, Date end, int suachua_baoduong,
				String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
						+ " ON TRUE " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qdxth "
						+ " ON qdxth.MA_QUATRINH_DEXUAT_THUCHIEN = dsb.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN DE_XUAT as dx " + " ON dx.MA_DE_XUAT = qdxth.MA_DE_XUAT "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN as gdth "
						+ " ON gdth.MA_QUATRINH_DEXUAT_THUCHIEN = dsb.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO gdthcb "
						+ " ON gdthcb.MA_GIAI_DOAN_THUC_HIEN = gdth.MA_GIAI_DOAN_THUC_HIEN "
						+ " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt "
						+ " ON gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
						+ " INNER JOIN GIAI_DOAN_NGHIEM_THU_CAN_BO as gdntcb "
						+ " ON gdntcb.MA_GIAI_DOAN_NGHIEM_THU = gdnt.MA_GIAI_DOAN_NGHIEM_THU "
						+ " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
						+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
						+ " INNER JOIN GIAI_DOAN_QUYET_TOAN_CAN_BO as gdqtcb "
						+ " ON gdqtcb.MA_GIAI_DOAN_QUYET_TOAN =gdqt.MA_GIAI_DOAN_QUYET_TOAN "
						+ " WHERE ( ths.MA_TAPHOSO = dx.MA_TAPHOSO " + " OR ths.MA_TAPHOSO = gdthcb.MA_TAPHOSO "
						+ " OR ths.MA_TAPHOSO = gdntcb.MA_TAPHOSO " + " OR ths.MA_TAPHOSO = gdqtcb.MA_TAPHOSO ) "
						+ " AND dsb.SUACHUA_BAODUONG = '" + suachua_baoduong + "' AND NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);

					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<HOSO_ROW> get_MuasamData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_THUCHIEN_TANG_TAISAN as dtt "
						+ " ON TRUE " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qdxth "
						+ " ON qdxth.MA_QUATRINH_DEXUAT_THUCHIEN = dtt.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN DE_XUAT as dx " + " ON dx.MA_DE_XUAT = qdxth.MA_DE_XUAT "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN as gdth "
						+ " ON gdth.MA_QUATRINH_DEXUAT_THUCHIEN = dtt.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO gdthcb "
						+ " ON gdthcb.MA_GIAI_DOAN_THUC_HIEN = gdth.MA_GIAI_DOAN_THUC_HIEN "
						+ " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt "
						+ " ON gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
						+ " INNER JOIN GIAI_DOAN_NGHIEM_THU_CAN_BO as gdntcb "
						+ " ON gdntcb.MA_GIAI_DOAN_NGHIEM_THU = gdnt.MA_GIAI_DOAN_NGHIEM_THU "
						+ " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
						+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
						+ " INNER JOIN GIAI_DOAN_QUYET_TOAN_CAN_BO as gdqtcb "
						+ " ON gdqtcb.MA_GIAI_DOAN_QUYET_TOAN =gdqt.MA_GIAI_DOAN_QUYET_TOAN "
						+ " WHERE ( ths.MA_TAPHOSO = dx.MA_TAPHOSO " + " OR ths.MA_TAPHOSO = gdthcb.MA_TAPHOSO "
						+ " OR ths.MA_TAPHOSO = gdntcb.MA_TAPHOSO " + " OR ths.MA_TAPHOSO = gdqtcb.MA_TAPHOSO ) "
						+ "  AND NGAY_TAO_TAPHOSO >= '" + mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='"
						+ mdf.getSQLStringDate(end) + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);

					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<HOSO_ROW> get_ThanhlyData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_THUCHIEN_GIAM_TAISAN as dgt "
						+ " ON TRUE " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qdxth "
						+ " ON qdxth.MA_QUATRINH_DEXUAT_THUCHIEN = dgt.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN DE_XUAT as dx " + " ON dx.MA_DE_XUAT = qdxth.MA_DE_XUAT "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN as gdth "
						+ " ON gdth.MA_QUATRINH_DEXUAT_THUCHIEN = dgt.MA_QUATRINH_DEXUAT_THUCHIEN "
						+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO gdthcb "
						+ " ON gdthcb.MA_GIAI_DOAN_THUC_HIEN = gdth.MA_GIAI_DOAN_THUC_HIEN "
						+ " WHERE ( ths.MA_TAPHOSO = dx.MA_TAPHOSO " + " OR ths.MA_TAPHOSO = gdthcb.MA_TAPHOSO) "
						+ "  AND NGAY_TAO_TAPHOSO >= '" + mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='"
						+ mdf.getSQLStringDate(end) + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);

					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public ArrayList<HOSO_ROW> get_DangkiemData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_ROW> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_THUCHIEN_DANGKIEM as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_ROW hsr = new HOSO_ROW();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);

					String query2 = "SELECT * FROM VANBAN WHERE  MA_TAPHOSO='" + hsr.getTaphoso().getMA_TAPHOSO() + "'";
					Statement st2 = conn.createStatement();
					ResultSet rs2 = st2.executeQuery(query2);
					ArrayList<VANBAN> vbl = new ArrayList<>();
					while (rs2.next()) {
						VANBAN vb = cdb.get_VANBAN(rs2);
						vbl.add(vb);
					}
					hsr.setVanbanList(vbl);
					st2.close();
					rs2.close();
					result.add(hsr);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	private class Update extends EDIactivity {
	}

	private class Delete extends DELactivity {
	}

	public ArrayList<HOSO_ROW> get_AllData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_AllData(start, end, searchString);
	}

	public ArrayList<HOSO_ROW> get_BangiaonoiboData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_BangiaonoiboData(start, end, searchString);
	}

	public ArrayList<HOSO_ROW> get_BaoduongData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Baoduong(), searchString);
	}

	public ArrayList<HOSO_ROW> get_SuachuaData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Suachua(), searchString);
	}

	public ArrayList<HOSO_ROW> get_MuasamData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_MuasamData(start, end, searchString);
	}

	public ArrayList<HOSO_ROW> get_ThanhlyData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_ThanhlyData(start, end, searchString);
	}

	public ArrayList<HOSO_ROW> get_DangkiemData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_DangkiemData(start, end, searchString);
	}
}
