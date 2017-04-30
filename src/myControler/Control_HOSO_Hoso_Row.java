package myControler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import DAO.HOSO_DAGUI;
import DAO.HOSO_DANHAN;
import DAO.HOSO_USER;
import DAO.NGUOIDUNG;
import DAO.NGUOINHAN_HOSO_DAGUI;
import DAO.TAPHOSO;
import DAO.VANBAN;
import DAO.BUILD.OUT.Control_DAO_Build;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;

public class Control_HOSO_Hoso_Row {

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

	public Control_HOSO_Hoso_Row(NGUOIDUNG user) {
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

		public ArrayList<HOSO_USER> get_BangiaonoiboData(Date start, Date end, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT *  FROM TAPHOSO as ths " + " INNER JOIN DOT_BANGIAO_TAISAN_NOIBO as dbtn "
						+ " ON ths.MA_TAPHOSO = dbtn.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("dbtn.TEN_TAI_KHOAN"));

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

		public ArrayList<HOSO_USER> get_Suachua_BaoduongData(Date start, Date end, int suachua_baoduong,
				String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Nghiemthu = rs.getString("gdntcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Quyettoan = rs.getString("gdqtcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Nghiemthu.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Nghiemthu;
					} else if (!TEN_TAI_KHOAN_Quyettoan.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Quyettoan;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_Suachua_BaoduongData(Date start, Date end, int suachua_baoduong,
				String ten_TAI_KHOAN, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
						+ "' AND (dx.TEN_TAI_KHOAN='" + ten_TAI_KHOAN + "' OR gdthcb.TEN_TAI_KHOAN='" + ten_TAI_KHOAN
						+ "' OR gdntcb.TEN_TAI_KHOAN = '" + ten_TAI_KHOAN + "' OR gdqtcb.TEN_TAI_KHOAN='"
						+ ten_TAI_KHOAN + "') AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%"
						+ searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Nghiemthu = rs.getString("gdntcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Quyettoan = rs.getString("gdqtcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Nghiemthu.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Nghiemthu;
					} else if (!TEN_TAI_KHOAN_Quyettoan.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Quyettoan;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_MuasamData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Nghiemthu = rs.getString("gdntcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Quyettoan = rs.getString("gdqtcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Nghiemthu.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Nghiemthu;
					} else if (!TEN_TAI_KHOAN_Quyettoan.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Quyettoan;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_MuasamData(Date start, Date end, String ten_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
						+ mdf.getSQLStringDate(end) + "' AND (dx.TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
						+ "' OR gdthcb.TEN_TAI_KHOAN ='" + ten_TAI_KHOAN + "' OR gdntcb.TEN_TAI_KHOAN = '"
						+ ten_TAI_KHOAN + "' OR gdqtcb.TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
						+ "')  AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Nghiemthu = rs.getString("gdntcb.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Quyettoan = rs.getString("gdqtcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Nghiemthu.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Nghiemthu;
					} else if (!TEN_TAI_KHOAN_Quyettoan.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Quyettoan;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_ThanhlyData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_ThanhlyData(Date start, Date end, String ten_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
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
						+ mdf.getSQLStringDate(end) + "' AND (dx.TEN_TAI_KHOAN='" + ten_TAI_KHOAN
						+ "' OR gdthcb.TEN_TAI_KHOAN='" + ten_TAI_KHOAN + "') AND (ths.MA_TAPHOSO LIKE '%"
						+ searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%"
						+ searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					String TEN_TAI_KHOAN = null;
					String TEN_TAI_KHOAN_Dexuat = rs.getString("dx.TEN_TAI_KHOAN");
					String TEN_TAI_KHOAN_Thuchien = rs.getString("gdthcb.TEN_TAI_KHOAN");
					if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					} else if (!TEN_TAI_KHOAN_Dexuat.equals("null")) {
						TEN_TAI_KHOAN = TEN_TAI_KHOAN_Thuchien;
					}
					hsr.setTEN_TAI_KHOAN(TEN_TAI_KHOAN);

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

		public ArrayList<HOSO_USER> get_DangkiemData(Date start, Date end, String searchString) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , ddk.TEN_TAI_KHOAN  FROM TAPHOSO as ths "
						+ " INNER JOIN DOT_THUCHIEN_DANGKIEM as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND (ths.MA_TAPHOSO LIKE '%" + searchString + "%' OR TEN_TAPHOSO LIKE '%" + searchString
						+ "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString + "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("ddk.TEN_TAI_KHOAN"));
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

		public ArrayList<HOSO_USER> get_DangkiemData(Date start, Date end, String ten_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , ddk.TEN_TAI_KHOAN  FROM TAPHOSO as ths "
						+ " INNER JOIN DOT_THUCHIEN_DANGKIEM as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND ddk.TEN_TAI_KHOAN = '" + ten_TAI_KHOAN + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("ddk.TEN_TAI_KHOAN"));
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

		public ArrayList<HOSO_USER> get_TailenData(Date start, Date end, String TEN_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , ddk.TEN_TAI_KHOAN  FROM TAPHOSO as ths " + " INNER JOIN HOSO_TAILEN as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND ddk.TEN_TAI_KHOAN = '" + TEN_TAI_KHOAN + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("ddk.TEN_TAI_KHOAN"));
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

		public ArrayList<HOSO_USER> get_DaguiData(Date start, Date end, String tEN_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , ddk.TEN_TAI_KHOAN  FROM TAPHOSO as ths " + " RIGHT JOIN HOSO_DAGUI as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND ddk.TEN_TAI_KHOAN = '" + tEN_TAI_KHOAN + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					HOSO_DAGUI hdg = cdb.get_HOSO_DAGUI(rs);
					hsr.setTaphoso(ths);
					hsr.setHoso_Dagui(hdg);
					hsr.setTEN_TAI_KHOAN(rs.getString("ddk.TEN_TAI_KHOAN"));
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

		public ArrayList<HOSO_USER> get_DanhanData(Date start, Date end, String ten_TAI_KHOAN, String searchString)
				throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , ddk.TAIKHOAN_NHAN  FROM TAPHOSO as ths " + " RIGHT JOIN HOSO_DANHAN as ddk "
						+ " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO WHERE NGAY_TAO_TAPHOSO >= '"
						+ mdf.getSQLStringDate(start) + "' AND NGAY_TAO_TAPHOSO <='" + mdf.getSQLStringDate(end)
						+ "' AND ddk.TAIKHOAN_NHAN = '" + ten_TAI_KHOAN + "' AND (ths.MA_TAPHOSO LIKE '%" + searchString
						+ "%' OR TEN_TAPHOSO LIKE '%" + searchString + "%' OR GIOITHIEU_TAPHOSO LIKE '%" + searchString
						+ "%' ) ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					HOSO_DANHAN hdn = cdb.get_HOSO_DANHAN(rs);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("ddk.TAIKHOAN_NHAN"));
					hsr.setHoso_Danhan(hdn);
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

		public ArrayList<HOSO_USER> get_VanbanguidenData(String ten_TAI_KHOAN) throws SQLException {
			if (conn != null && isPrivilegeREA()) {
				ArrayList<HOSO_USER> result = new ArrayList<>();
				String query = "SELECT * , nhd.TEN_TAI_KHOAN, nhd.MA_HOSO_DAGUI, nhd.DA_DOC FROM TAPHOSO as ths "
						+ " RIGHT JOIN HOSO_DAGUI as ddk " + " ON ths.MA_TAPHOSO = ddk.MA_TAPHOSO "
						+ " INNER JOIN NGUOINHAN_HOSO_DAGUI as nhd "
						+ " ON nhd.MA_HOSO_DAGUI = ddk.MA_HOSO_DAGUI WHERE  nhd.TEN_TAI_KHOAN = '" + ten_TAI_KHOAN
						+ "' AND (DA_DOC = 'FALSE' OR DA_DOC IS NULL   )";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				Control_DAO_Build cdb = new Control_DAO_Build();
				while (rs.next()) {
					HOSO_USER hsr = new HOSO_USER();
					TAPHOSO ths = cdb.get_TAPHOSO(rs);
					NGUOINHAN_HOSO_DAGUI nhd = cdb.getNGUOINHAN_HOSO_DAGUI(rs);
					HOSO_DAGUI hd = cdb.get_HOSO_DAGUI(rs);
					hsr.setNguoiNhanHosoDagui(nhd);
					hsr.setHoso_Dagui(hd);
					hsr.setTaphoso(ths);
					hsr.setTEN_TAI_KHOAN(rs.getString("nhd.TEN_TAI_KHOAN"));
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

	public ArrayList<HOSO_USER> get_AllData(Date start, Date end, String searchString) throws SQLException {
		ArrayList<HOSO_USER> rs = new ArrayList<>();
		addElement(rs, getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Baoduong(),
				searchString));
		addElement(rs,
				getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Suachua(), searchString));
		addElement(rs, getSelecter().get_MuasamData(start, end, searchString));
		addElement(rs, getSelecter().get_ThanhlyData(start, end, searchString));
		addElement(rs, getSelecter().get_DangkiemData(start, end, searchString));
		return rs;
	}

	public ArrayList<HOSO_USER> get_AllData_HosocuaToi(Date start, Date end, String ten_TAI_KHOAN, String searchString)
			throws SQLException {
		ArrayList<HOSO_USER> rs = new ArrayList<>();
		addElement(rs, getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Baoduong(),
				ten_TAI_KHOAN, searchString));
		addElement(rs, getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Suachua(),
				ten_TAI_KHOAN, searchString));
		addElement(rs, getSelecter().get_MuasamData(start, end, ten_TAI_KHOAN, searchString));
		addElement(rs, getSelecter().get_ThanhlyData(start, end, ten_TAI_KHOAN, searchString));
		addElement(rs, getSelecter().get_DangkiemData(start, end, ten_TAI_KHOAN, searchString));
		return rs;
	}

	private void addElement(ArrayList<HOSO_USER> par1, ArrayList<HOSO_USER> par2) {
		if (par1 == null)
			return;
		if (par2 != null)
			par1.addAll(par2);
	}

	public ArrayList<HOSO_USER> get_BangiaonoiboData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_BangiaonoiboData(start, end, searchString);
	}

	public ArrayList<HOSO_USER> get_BaoduongData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Baoduong(), searchString);
	}

	public ArrayList<HOSO_USER> get_SuachuaData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_Suachua_BaoduongData(start, end, new Fill_ItemData().getInt_Suachua(), searchString);
	}

	public ArrayList<HOSO_USER> get_MuasamData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_MuasamData(start, end, searchString);
	}

	public ArrayList<HOSO_USER> get_ThanhlyData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_ThanhlyData(start, end, searchString);
	}

	public ArrayList<HOSO_USER> get_DangkiemData(Date start, Date end, String searchString) throws SQLException {
		return getSelecter().get_DangkiemData(start, end, searchString);
	}

	public ArrayList<HOSO_USER> get_TailenData(Date start, Date end, String TEN_TAI_KHOAN, String searchString)
			throws SQLException {
		return getSelecter().get_TailenData(start, end, TEN_TAI_KHOAN, searchString);
	}

	public ArrayList<HOSO_USER> get_DaguiData(Date start, Date end, String TEN_TAI_KHOAN, String searchString)
			throws SQLException {
		return getSelecter().get_DaguiData(start, end, TEN_TAI_KHOAN, searchString);
	}

	public ArrayList<HOSO_USER> get_DanhanData(Date start, Date end, String ten_TAI_KHOAN, String searchString)
			throws SQLException {
		return getSelecter().get_DanhanData(start, end, ten_TAI_KHOAN, searchString);
	}

	public ArrayList<HOSO_USER> get_VanbanguidenData(String ten_TAI_KHOAN) throws SQLException {
		return getSelecter().get_VanbanguidenData(ten_TAI_KHOAN);
	}

}
