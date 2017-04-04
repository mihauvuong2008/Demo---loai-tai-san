package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.PHONGBAN;
import View.DateTime.MyDateFormat;

public class query_Select_PHUONGTIEN_GIAOTHONG {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_PhuongtinGiaothong(String mA_PHUONGTIEN_GIAOTHONG) {
		try {
			return "SELECT * FROM PHUONGTIEN_GIAOTHONG" + " WHERE MA_PHUONGTIEN_GIAOTHONG = '" + mA_PHUONGTIEN_GIAOTHONG
					+ "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_PhuongtinGiaothong_From_Taisan(int mA_TAISAN) {
		try {
			return "SELECT * FROM PHUONGTIEN_GIAOTHONG" + " WHERE MA_TAISAN = '" + mA_TAISAN + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaOto() {
		try {
			return "SELECT * FROM PHUONGTIEN_GIAOTHONG WHERE LOAI_PHUONGTIEN_GIAOTHONG='1' ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Tatca() {
		try {
			return "SELECT * FROM PHUONGTIEN_GIAOTHONG; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_PhuongtienGiaoThong_cuaPhongban_Suachua(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, PHONGBAN dv) {
		try {
			return "SELECT * from PHUONGTIEN_GIAOTHONG INNER JOIN (SELECT TAISAN.MA_TAISAN "
					+ "FROM DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN, TAISAN, PHONGBAN "
					+ "WHERE DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN.MA_TAISAN = TAISAN.MA_TAISAN AND DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = '"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "' "
					+ "AND TAISAN.MA_DON_VI_SU_DUNG = PHONGBAN.MA_PHONGBAN AND PHONGBAN.MA_PHONGBAN = '"
					+ dv.getMA_PHONGBAN() + "') as t" + " ON PHUONGTIEN_GIAOTHONG.MA_TAISAN = t.MA_TAISAN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_PhuongtienGiaothong_cua_Phongban_Oto_Xemay(int loaiPTGT, PHONGBAN dv) {
		try {
			return "SELECT * from PHUONGTIEN_GIAOTHONG INNER JOIN (SELECT TAISAN.MA_TAISAN, TAISAN.MA_DON_VI_SU_DUNG "
					+ "FROM TAISAN " + "WHERE TAISAN.MA_DON_VI_SU_DUNG = '" + dv.getMA_PHONGBAN() + "') as t"
					+ " ON PHUONGTIEN_GIAOTHONG.MA_TAISAN = t.MA_TAISAN"
					+ " WHERE PHUONGTIEN_GIAOTHONG.LOAI_PHUONGTIEN_GIAOTHONG ='" + loaiPTGT + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_Thaynhot(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_NHOT = TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_NHOT= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayLocnhot(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_LOC_NHOT= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_LOC_NHOT= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayLocgio(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_LOC_GIO= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_LOC_GIO= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayLocNhienlieu(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_LOC_NHIEN_LIEU= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_LOC_NHIEN_LIEU= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayDauphanh_Daulyhop(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_DAU_PHANH_DAU_LY_HOP= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_DAU_PHANH_DAU_LY_HOP= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayDauhopso(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_DAU_HOP_SO= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_DAU_HOP_SO= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayDauvisai(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_DAU_VI_SAI= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_DAU_VI_SAI= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayLocgioGianlanh(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_LOC_GIO_GIAN_LANH= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_LOC_GIO_GIAN_LANH= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_ThayDauTroluclai(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.THAY_DAU_TRO_LUC_LAI= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.THAY_DAU_TRO_LUC_LAI= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC>= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";

		} catch (Exception e) {
			return null;
		}
	}

	public String getString_getLichbaoduong_Baoduongkhac(PHONGBAN phongban_Selected, Date date) {
		try {
			String batdau = (date == null) ? "null" : mdf.getSQLStringDate(date);
			return " SELECT * FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg "
					+ " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN LOAI_XE as lx"
					+ " ON lx.MA_LOAI_XE = pg.MA_LOAI_XE "
					+ /* tai san chua bao duong */" WHERE "
					+ ((phongban_Selected == null) ? " "
							: "  t.MA_DON_VI_SU_DUNG='" + phongban_Selected.getMA_PHONGBAN() + "' AND ")
					+ "((t.MA_TAISAN NOT IN (SELECT t.MA_TAISAN   FROM TAISAN as t"
					+ " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (t.MA_TAISAN = dsbt.MA_TAISAN AND dsbt.BAO_DUONG_KHAC= TRUE)"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb"
					+ " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG))"
					/* tai san da bao duong trong thoi han */ + " OR (t.MA_TAISAN IN (SELECT t.MA_TAISAN FROM TAISAN as t" + " INNER JOIN PHUONGTIEN_GIAOTHONG as pg" + " ON t.MA_TAISAN = pg.MA_TAISAN" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt" + " ON (dsbt.MA_TAISAN = t.MA_TAISAN AND dsbt.BAO_DUONG_KHAC= TRUE)" + " INNER JOIN  DOT_THUCHIEN_SUACHUA_BAODUONG as dsb" + " ON dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt" + " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN" + " WHERE " + ((date == null) ? "" : " (gdnt.THOI_DIEM_KET_THUC >= '" + batdau + "') AND ") + " gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt" + " WHERE gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN) )))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaOto(PHONGBAN phongbanSelected2, String text) {
		try {
			return "SELECT * FROM PHUONGTIEN_GIAOTHONG as pg " + " INNER JOIN TAISAN as ts "
					+ " ON (pg.MA_TAISAN = ts.MA_TAISAN  "
					+ (phongbanSelected2 == null ? ")"
							: "AND ts.MA_DON_VI_SU_DUNG = '" + phongbanSelected2.getMA_PHONGBAN() + "')")
					+ " WHERE LOAI_PHUONGTIEN_GIAOTHONG='1' AND (pg.BIENSO LIKE '%" + text
					+ "%' OR pg.MA_PHUONGTIEN_GIAOTHONG LIKE '%" + text + "%')";
		} catch (Exception e) {
			return null;
		}
	}

}
