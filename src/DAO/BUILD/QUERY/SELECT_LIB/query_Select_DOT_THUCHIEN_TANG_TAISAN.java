package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import View.DateTime.MyDateFormat;

public class query_Select_DOT_THUCHIEN_TANG_TAISAN {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_DotTangtaisan_Gannhat(int mataisan) {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN as dtt "
					+ " INNER JOIN TAISAN_DOT_THUCHIEN_TANG_TAISAN as tdtt " + " ON dtt.MA_DOT_TANG = tdtt.MA_DOT_TANG "
					+ " INNER JOIN TAISAN as ts " + " ON tdtt.MA_TAISAN = ts.MA_TAISAN "
					+ " INNER JOIN QUATRINH_NGHIEMTHU_QUYETTOAN as qntqt "
					+ " ON qntqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt "
					+ " ON gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN= qntqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ " WHERE ts.MA_TAISAN = '" + mataisan
					+ "' AND gdnt.THOI_DIEM_KET_THUC IS NOT NULL ORDER BY  gdnt.THOI_DIEM_KET_THUC DESC  LIMIT 1 ;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaDotTangtaisan() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN ; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaHoanthanh_Dexuat() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN  INNER JOIN (SELECT MA_QUATRINH_DEXUAT_THUCHIEN, QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT FROM QUATRINH_DEXUAT_THUCHIEN INNER JOIN (SELECT MA_DE_XUAT FROM DE_XUAT where THOI_DIEM_HOAN_THANH IS NULL) as t ON QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT = t.MA_DE_XUAT) as t1 ON DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN = t1.MA_QUATRINH_DEXUAT_THUCHIEN;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaHoanthanh_Thuchien() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN INNER JOIN GIAI_DOAN_THUC_HIEN ON DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN=GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN AND GIAI_DOAN_THUC_HIEN.THOI_DIEM_HOAN_THANH is null";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaTao_Thuchien() {
		try {
			String result = "SELECT * FROM   DOT_THUCHIEN_TANG_TAISAN WHERE  NOT EXISTS (SELECT 1 FROM GIAI_DOAN_THUC_HIEN WHERE  GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaHoanthanh_Nghiemthu() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN INNER JOIN GIAI_DOAN_NGHIEM_THU ON DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=GIAI_DOAN_NGHIEM_THU.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND GIAI_DOAN_NGHIEM_THU.THOI_DIEM_KET_THUC is null";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaTao_Nghiemthu() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN WHERE  NOT EXISTS (SELECT 1 FROM GIAI_DOAN_NGHIEM_THU WHERE  GIAI_DOAN_NGHIEM_THU.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaHoanthanh_Quyettoan() {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN INNER JOIN GIAI_DOAN_QUYET_TOAN ON (DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=GIAI_DOAN_QUYET_TOAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND GIAI_DOAN_QUYET_TOAN.THOI_GIAN_KET_THUC is null)";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan_ChuaTao_Quyettoan() {
		try {
			String result = "SELECT * FROM   DOT_THUCHIEN_TANG_TAISAN WHERE  NOT EXISTS "
					+ "(SELECT 1 FROM GIAI_DOAN_QUYET_TOAN WHERE  GIAI_DOAN_QUYET_TOAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotTangTaisan(int ma_CONGVIEC) {
		try {
			String result = "SELECT DOT_THUCHIEN_TANG_TAISAN.MA_DOT_TANG, TEN_DOT_TANG, LY_DO_TANG, MUC_KINH_PHI,  MO_TA,  MA_QUATRINH_DEXUAT_THUCHIEN, MA_QUATRINH_NGHIEMTHU_QUYETTOAN, MA_NGUONTANG FROM DOT_THUCHIEN_TANG_TAISAN WHERE   MA_DOT_TANG='"
					+ ma_CONGVIEC + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_DotTangTaisan_Bieudo(Date begin, Date end) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN dtt " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN qdt"
					+ " ON dtt.MA_QUATRINH_DEXUAT_THUCHIEN = qdt.MA_QUATRINH_DEXUAT_THUCHIEN"
					+ " INNER JOIN DE_XUAT dx " + " ON dx.MA_DE_XUAT = qdt.MA_DE_XUAT "
					+ " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
					+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ "WHERE  dx.THOI_DIEM_BAT_DAU>='" + mdf.getSQLStringDate(begin)
					+ "' AND gdqt.THOI_GIAN_KET_THUC<='" + mdf.getSQLStringDate(end) + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_DotTangTaisan_ChuaHoanthanh() {
		try {
			return "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN dtt " + " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
					+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dtt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ "WHERE gdqt.THOI_GIAN_KET_THUC IS NULL ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_All_DotTangTaisan(Date date, Date date2, String text) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_TANG_TAISAN as dsb " + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON (dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN ) "
					+ " INNER JOIN DE_XUAT as dx " + " ON (dx.MA_DE_XUAT = qtdxth.MA_DE_XUAT AND dx.THOI_DIEM_BAT_DAU>'"
					+ mdf.getSQLStringDate(date) + "')" + " INNER JOIN GIAI_DOAN_QUYET_TOAN as gdqt "
					+ " ON (dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND gdqt.THOI_GIAN_KET_THUC <'"
					+ mdf.getSQLStringDate(date2) + "') WHERE MA_DOT_TANG LIKE '%" + text
					+ "%'  OR TEN_DOT_TANG LIKE '%" + text + "%'";
		} catch (Exception e) {
			return null;
		}

	}

}
