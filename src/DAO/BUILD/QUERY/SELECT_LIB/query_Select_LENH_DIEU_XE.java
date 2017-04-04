package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import DAO.LENH_DIEU_XE;
import DAO.PHONGBAN;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.DateTime.MyDateFormat;

public class query_Select_LENH_DIEU_XE {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_TatcaLenhDieuxe_Cohieuluc() {
		try {
			String result = "SELECT  * FROM LENH_DIEU_XE WHERE HUY_LENH='1'; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_LenhDieuxe_Theo_Phongban_Thoidiembatdau_ThoidiemKetthuc_Conhieuluc(PHONGBAN dv,
			Date date_Batdau, Date date_Ketthuc, boolean huyLenh) {
		try {
			int HuyLenh = 0;
			if (!huyLenh) {
				HuyLenh = 1;
			}
			String result = null;
			if (dv != null) {
				if (date_Batdau != null && date_Ketthuc != null) {
					result = "SELECT * FROM LENH_DIEU_XE INNER JOIN ( SELECT MA_PHUONGTIEN_GIAOTHONG"
							+ " FROM PHUONGTIEN_GIAOTHONG" + " INNER JOIN TAISAN"
							+ " ON TAISAN.MA_TAISAN=PHUONGTIEN_GIAOTHONG.MA_TAISAN AND TAISAN.MA_DON_VI_SU_DUNG = '"
							+ dv.getMA_PHONGBAN() + "') as t"
							+ " ON t.MA_PHUONGTIEN_GIAOTHONG = LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG"
							+ " WHERE (NGAY_DI BETWEEN '" + mdf.getSQLStringDate(date_Batdau) + "' AND '"
							+ mdf.getSQLStringDate(date_Ketthuc) + "') AND HUY_LENH='" + HuyLenh + "' ;";
				} else {
					result = "SELECT * FROM LENH_DIEU_XE, PHUONGTIEN_GIAOTHONG, TAISAN WHERE"
							+ " PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG=LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG "
							+ "AND TAISAN.MA_TAISAN = PHUONGTIEN_GIAOTHONG.MA_TAISAN AND  TAISAN.MA_DON_VI_SU_DUNG ='"
							+ dv.getMA_PHONGBAN() + "' AND HUY_LENH='" + HuyLenh + "' ;";
				}
			} else {
				if (date_Batdau != null && date_Ketthuc != null) {
					result = "SELECT * FROM LENH_DIEU_XE WHERE (NGAY_DI BETWEEN '" + mdf.getSQLStringDate(date_Batdau)
							+ "' AND '" + mdf.getSQLStringDate(date_Ketthuc) + "')  AND HUY_LENH='" + HuyLenh + "';";
				} else {
					result = "SELECT * FROM LENH_DIEU_XE WHERE HUY_LENH='" + HuyLenh + "';";
				}
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe_cua_Phuongtiengiaothong(PHUONGTIEN_GIAOTHONG pt, Date date_Batdau,
			Date date_Ketthuc) {
		try {
			String result = "SELECT *  FROM LENH_DIEU_XE WHERE (NGAY_DI BETWEEN '" + mdf.getSQLStringDate(date_Batdau)
					+ "' AND '" + mdf.getSQLStringDate(date_Ketthuc) + "') AND MA_PHUONGTIEN_GIAOTHONG='"
					+ pt.getMA_PHUONGTIEN_GIAOTHONG() + "'  AND HUY_LENH='1';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_NgayDautienDieuXe() {
		try {
			String result = "SELECT  MIN(NGAY_DI) FROM LENH_DIEU_XE ; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_CuoicungtienDieuXe() {
		try {
			String result = "SELECT  MAX(NGAY_DI) FROM LENH_DIEU_XE ; ";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Oto_Dasudung(PHONGBAN dv, Date date_Batdau, Date date_Ketthuc) {
		try {

			String result = null;
			if (dv != null) {
				if (date_Batdau != null && date_Ketthuc != null) {
					result = "SELECT * FROM PHUONGTIEN_GIAOTHONG INNER JOIN ( SELECT  LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG "
							+ " FROM LENH_DIEU_XE INNER JOIN ( SELECT MA_PHUONGTIEN_GIAOTHONG"
							+ " FROM PHUONGTIEN_GIAOTHONG" + " INNER JOIN TAISAN"
							+ " ON TAISAN.MA_TAISAN=PHUONGTIEN_GIAOTHONG.MA_TAISAN AND TAISAN.MA_DON_VI_SU_DUNG = '"
							+ dv.getMA_PHONGBAN() + "' ) as t"
							+ " ON t.MA_PHUONGTIEN_GIAOTHONG = LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG"
							+ " WHERE (NGAY_DI BETWEEN '" + mdf.getSQLStringDate(date_Batdau) + "' AND '"
							+ mdf.getSQLStringDate(date_Ketthuc)
							+ "') AND HUY_LENH='1' GROUP BY LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG) as t2 ON PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG = t2.MA_PHUONGTIEN_GIAOTHONG;";
				} else {
					result = "SELECT * FROM PHUONGTIEN_GIAOTHONG INNER JOIN( SELECT LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG FROM LENH_DIEU_XE, PHUONGTIEN_GIAOTHONG, TAISAN WHERE"
							+ " PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG=LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG "
							+ "AND TAISAN.MA_TAISAN = PHUONGTIEN_GIAOTHONG.MA_TAISAN AND  TAISAN.MA_DON_VI_SU_DUNG ='"
							+ dv.getMA_PHONGBAN()
							+ "' AND HUY_LENH='1' GROUP BY LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG) as t2 ON PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG = t2.MA_PHUONGTIEN_GIAOTHONG;";
				}
			} else {
				if (date_Batdau != null && date_Ketthuc != null) {
					result = "SELECT * FROM PHUONGTIEN_GIAOTHONG INNER JOIN( SELECT  LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG FROM LENH_DIEU_XE WHERE (NGAY_DI BETWEEN '"
							+ mdf.getSQLStringDate(date_Batdau) + "' AND '" + mdf.getSQLStringDate(date_Ketthuc)
							+ "') AND HUY_LENH='1'  GROUP BY LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG) as t2 ON PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG = t2.MA_PHUONGTIEN_GIAOTHONG;";
				} else {
					result = "SELECT *  FROM PHUONGTIEN_GIAOTHONG INNER JOIN( SELECT LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG  FROM LENH_DIEU_XE WHERE HUY_LENH='1' GROUP BY LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG)"
							+ " as t2 ON PHUONGTIEN_GIAOTHONG.MA_PHUONGTIEN_GIAOTHONG = t2.MA_PHUONGTIEN_GIAOTHONG;";
				}
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe_DaukyThongke(PHUONGTIEN_GIAOTHONG l2, Date date_Batdau) {
		try {
			String result = "SELECT * FROM LENH_DIEU_XE WHERE HUY_LENH='1'  AND MA_PHUONGTIEN_GIAOTHONG = '"
					+ l2.getMA_PHUONGTIEN_GIAOTHONG()
					+ "' AND NGAY_DI IN (SELECT MIN(NGAY_DI) FROM LENH_DIEU_XE WHERE NGAY_DI >='"
					+ mdf.getSQLStringDate(date_Batdau) + "' AND HUY_LENH='1'  AND MA_PHUONGTIEN_GIAOTHONG = '"
					+ l2.getMA_PHUONGTIEN_GIAOTHONG() + "' );";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe_CuoikyThongke(PHUONGTIEN_GIAOTHONG l2, Date date_Ketthuc) {
		try {
			String result = "SELECT * FROM LENH_DIEU_XE WHERE HUY_LENH='1'  AND MA_PHUONGTIEN_GIAOTHONG = '"
					+ l2.getMA_PHUONGTIEN_GIAOTHONG()
					+ "' AND NGAY_DI IN (SELECT MAX(NGAY_DI) FROM LENH_DIEU_XE WHERE NGAY_DI <='"
					+ mdf.getSQLStringDate(date_Ketthuc) + "' AND HUY_LENH='1'  AND MA_PHUONGTIEN_GIAOTHONG = '"
					+ l2.getMA_PHUONGTIEN_GIAOTHONG() + "') ;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DiemXuatphat_Ganday(int i) {
		try {
			String result = "SELECT  DIEM_XUATPHAT FROM LENH_DIEU_XE WHERE HUY_LENH='1' AND DIEM_XUATPHAT NOT LIKE '' GROUP BY DIEM_XUATPHAT ORDER BY  MA_LENH_DIEU_XE DESC LIMIT "
					+ i + ";";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DiemdenGanday(int i) {
		try {
			String result = "SELECT  DIEM_DEN FROM LENH_DIEU_XE WHERE HUY_LENH='1' AND DIEM_DEN NOT LIKE ''   GROUP BY DIEM_DEN ORDER BY  MA_LENH_DIEU_XE DESC  LIMIT "
					+ i + ";";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe_Gannhat(PHUONGTIEN_GIAOTHONG ptgt) {
		try {
			String result = "SELECT *  FROM LENH_DIEU_XE WHERE" + " LENH_DIEU_XE.MA_PHUONGTIEN_GIAOTHONG ='"
					+ ptgt.getMA_PHUONGTIEN_GIAOTHONG()
					+ "'   AND HUY_LENH='1' ORDER BY  MA_LENH_DIEU_XE DESC  LIMIT 1 ;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe(int ma_LENH_DIEU_XE) {
		try {
			String result = "SELECT * FROM LENH_DIEU_XE WHERE" + " MA_LENH_DIEU_XE ='" + ma_LENH_DIEU_XE + "' ;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Lenhdieuxe_After(LENH_DIEU_XE last) {
		try {
			String result = "SELECT * FROM LENH_DIEU_XE WHERE NGAY_VE IN (SELECT MAX(NGAY_VE) FROM LENH_DIEU_XE WHERE NGAY_VE >'"
					+ mdf.getSQLStringDateTime(last.getNGAY_VE()) + "' AND HUY_LENH='1' AND MA_PHUONGTIEN_GIAOTHONG='"
					+ last.getMA_PHUONGTIEN_GIAOTHONG() + "')";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
