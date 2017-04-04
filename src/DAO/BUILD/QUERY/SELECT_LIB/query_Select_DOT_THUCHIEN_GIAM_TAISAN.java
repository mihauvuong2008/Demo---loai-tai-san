package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import View.DateTime.MyDateFormat;

public class query_Select_DOT_THUCHIEN_GIAM_TAISAN {

	private static final Log log = LogFactory.getLog(query_Select_DOT_THUCHIEN_GIAM_TAISAN.class);
	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_NgayBatdau_GiaiDoanDexuat_GiamTaisan(int ma_DOT_GIAM) {
		try {
			String result = "SELECT THOI_DIEM_BAT_DAU  FROM GIAI_DOAN_DE_XUAT, DOT_THUCHIEN_GIAM_TAISAN, QUATRINH_DEXUAT_THUCHIEN  "
					+ " WHERE GIAI_DOAN_DE_XUAT.MA_QUATRINH_DEXUAT_THUCHIEN = QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ "AND DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN = QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN AND "
					+ "DOT_THUCHIEN_GIAM_TAISAN.MA_DOT_GIAM = '" + ma_DOT_GIAM + "'";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_NgayBatdau_GiaiDoanThuchien_GiamTaisan(int ma_DOT_GIAM) {
		try {
			String result = "SELECT THOI_DIEM_BAT_DAU  FROM GIAI_DOAN_THUC_HIEN, DOT_THUCHIEN_GIAM_TAISAN, QUATRINH_DEXUAT_THUCHIEN  "
					+ "WHERE GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ "AND DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN = QUATRINH_DEXUAT_THUCHIEN.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ "AND DOT_THUCHIEN_GIAM_TAISAN.MA_DOT_GIAM = '" + ma_DOT_GIAM + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Tatca_DotGiamTaisan() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN; ";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_DotGiamTaisan_ChuaTao_GiaidoanThuchien() {
		try {
			String result = "SELECT * FROM   DOT_THUCHIEN_GIAM_TAISAN WHERE  NOT EXISTS (SELECT 1 FROM GIAI_DOAN_THUC_HIEN WHERE  GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN);";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_DotGiamTaisan_ChuaHoanthanh_GiaidoanThuchien() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN INNER JOIN GIAI_DOAN_THUC_HIEN ON DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN=GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN AND GIAI_DOAN_THUC_HIEN.THOI_DIEM_HOAN_THANH is null";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_DotGiamTaisan_ChuaHoanthanh_GiaidoanDexuat() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN  INNER JOIN (SELECT MA_QUATRINH_DEXUAT_THUCHIEN, QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT FROM QUATRINH_DEXUAT_THUCHIEN INNER JOIN (SELECT MA_DE_XUAT FROM DE_XUAT where THOI_DIEM_HOAN_THANH IS NULL) as t ON QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT = t.MA_DE_XUAT) as t1 ON DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN = t1.MA_QUATRINH_DEXUAT_THUCHIEN;";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_DotGiamTaisan(int ma_DOTGIAM) {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN WHERE   MA_DOT_GIAM='" + ma_DOTGIAM + "';";
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Danhsach_DotGiamTaisan_Bieudo(Date begin, Date end) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN dgt" + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN qdth"
					+ "	ON qdth.MA_QUATRINH_DEXUAT_THUCHIEN = dgt.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " INNER JOIN DE_XUAT dx" + " ON dx.MA_DE_XUAT = qdth.MA_DE_XUAT"
					+ " INNER JOIN GIAI_DOAN_THUC_HIEN gdth "
					+ " ON gdth.MA_QUATRINH_DEXUAT_THUCHIEN = dgt.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " WHERE  dx.THOI_DIEM_BAT_DAU>='" + mdf.getSQLStringDate(begin)
					+ "' AND gdth.THOI_DIEM_CHUYEN_GIAO<='" + mdf.getSQLStringDate(end)
					+ "' AND gdth.THOI_DIEM_CHUYEN_GIAO IS NOT NULL";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Danhsach_DotGiamTaisan_ChuaHoanthanh() {
		try {
			return "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN dgt" + " INNER JOIN GIAI_DOAN_THUC_HIEN gdth "
					+ " ON gdth.MA_QUATRINH_DEXUAT_THUCHIEN = dgt.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " WHERE gdth.THOI_DIEM_CHUYEN_GIAO IS NULL  ";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String getString_Select_All_Dot_giam(Date date, Date date2, String text) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_GIAM_TAISAN as dsb " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON (dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN ) "
					+ " INNER JOIN DE_XUAT as dx " + " ON (dx.MA_DE_XUAT = qtdxth.MA_DE_XUAT AND dx.THOI_DIEM_BAT_DAU>'"
					+ mdf.getSQLStringDate(date) + "')" + " INNER JOIN GIAI_DOAN_THUC_HIEN as gdqt "
					+ " ON (dsb.MA_QUATRINH_DEXUAT_THUCHIEN = gdqt.MA_QUATRINH_DEXUAT_THUCHIEN AND gdqt.THOI_DIEM_HOAN_THANH <'"
					+ mdf.getSQLStringDate(date2) + "') WHERE MA_DOT_GIAM LIKE '%" + text + "%' OR TEN_DOT_GIAM LIKE '%"
					+ text + "%'";
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
