package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import DAO.DE_XUAT;
import View.DateTime.MyDateFormat;

public class query_Select_DOT_THUCHIEN_SUACHUA_BAODUONG {
	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Select_Dot_Suachua_Baoduong_cua_Taisan(Integer maTaiSan) {
		try {
			String result = "SELECT DOT_THUCHIEN_SUACHUA_BAODUONG.MA_DOT_THUCHIEN_SUACHUA_BAODUONG, TEN_DOT_THUCHIEN_SUACHUA_BAODUONG, LOAI_PHUONG_TIEN, MUC_KINH_PHI, SUACHUA_BAODUONG,  MO_TA, DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN, MA_QUATRINH_NGHIEMTHU_QUYETTOAN, MA_NGUONSUACHUA_BAODUONG FROM DOT_THUCHIEN_SUACHUA_BAODUONG, (SELECT MA_DOT_THUCHIEN_SUACHUA_BAODUONG FROM DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN WHERE MA_TAISAN = '"
					+ maTaiSan
					+ "') as t WHERE  t.MA_DOT_THUCHIEN_SUACHUA_BAODUONG=DOT_THUCHIEN_SUACHUA_BAODUONG.MA_DOT_THUCHIEN_SUACHUA_BAODUONG;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_Baoduong_PhuongTienGThong_cua_Phongban(int ma_PHONGBAN, int loaiPTGT) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt "
					+ " ON dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG "
					+ " INNER JOIN TAISAN as ts " + " ON (dsbt.MA_TAISAN = ts.MA_TAISAN AND ts.MA_DON_VI_SU_DUNG = '"
					+ ma_PHONGBAN + "') " + " INNER JOIN PHUONGTIEN_GIAOTHONG as ptgt "
					+ " ON (ptgt.MA_TAISAN = ts.MA_TAISAN AND ptgt.LOAI_PHUONGTIEN_GIAOTHONG = '" + loaiPTGT + "') ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaTao_Quyettoan() {
		try {
			String result = "SELECT * " + "FROM   DOT_THUCHIEN_SUACHUA_BAODUONG " + "WHERE  NOT EXISTS (SELECT 1 "
					+ "FROM GIAI_DOAN_QUYET_TOAN "
					+ "WHERE  GIAI_DOAN_QUYET_TOAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_NGHIEMTHU_QUYETTOAN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaHoanthanh_Quyettoan() {
		try {
			String result = "SELECT * " + "FROM DOT_THUCHIEN_SUACHUA_BAODUONG  " + "INNER JOIN GIAI_DOAN_QUYET_TOAN "
					+ "ON (DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=GIAI_DOAN_QUYET_TOAN.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ " AND GIAI_DOAN_QUYET_TOAN.THOI_GIAN_KET_THUC IS null);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaTao_Nghiemthu() {
		try {
			String result = "SELECT * " + "FROM DOT_THUCHIEN_SUACHUA_BAODUONG " + "WHERE  NOT EXISTS (SELECT 1 "
					+ "FROM GIAI_DOAN_NGHIEM_THU "
					+ "WHERE  GIAI_DOAN_NGHIEM_THU.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_NGHIEMTHU_QUYETTOAN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaHoanthanh_Nghiemthu() {
		try {
			String result = "SELECT * " + " FROM DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_NGHIEM_THU"
					+ " ON (DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_NGHIEMTHU_QUYETTOAN=GIAI_DOAN_NGHIEM_THU.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND GIAI_DOAN_NGHIEM_THU.THOI_DIEM_KET_THUC IS null);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaTao_Thuchien() {
		try {
			String result = "SELECT * " + " FROM   DOT_THUCHIEN_SUACHUA_BAODUONG" + " WHERE  NOT EXISTS (SELECT 1"
					+ " FROM GIAI_DOAN_THUC_HIEN"
					+ " WHERE  GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaHoanthanh_Thuchien() {
		try {
			String result = "SELECT * " + " FROM DOT_THUCHIEN_SUACHUA_BAODUONG" + " INNER JOIN GIAI_DOAN_THUC_HIEN"
					+ " ON (DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN=GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN"
					+ " AND GIAI_DOAN_THUC_HIEN.THOI_DIEM_HOAN_THANH is null);";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua_Baoduong_ChuaHoanthanh_Dexuat() {
		try {
			String result = "SELECT * " + "FROM DOT_THUCHIEN_SUACHUA_BAODUONG "
					+ " INNER JOIN (SELECT MA_QUATRINH_DEXUAT_THUCHIEN" + " FROM QUATRINH_DEXUAT_THUCHIEN"
					+ " INNER JOIN (SELECT MA_DE_XUAT" + " FROM DE_XUAT" + " where THOI_DIEM_HOAN_THANH is null) as t"
					+ " ON QUATRINH_DEXUAT_THUCHIEN.MA_DE_XUAT = t.MA_DE_XUAT) as t1"
					+ " ON DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN = t1.MA_QUATRINH_DEXUAT_THUCHIEN;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotSuachua(int ma_CONGVIEC) {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG WHERE   MA_DOT_THUCHIEN_SUACHUA_BAODUONG='"
					+ ma_CONGVIEC + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DotthuchienSuachua_Baoduong(DE_XUAT dx) {
		try {
			String result = "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG "
					+ " INNER JOIN (SELECT MA_QUATRINH_DEXUAT_THUCHIEN FROM QUATRINH_DEXUAT_THUCHIEN WHERE MA_DE_XUAT = '"
					+ dx.getMA_DE_XUAT()
					+ "'	)as t ON t.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_DotSuachuaBaoduong_Bieudo(Date begin, Date end) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG dsb" + " INNER JOIN QUATRINH_DEXUAT_THUCHIEN qdth "
					+ " ON dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qdth.MA_QUATRINH_DEXUAT_THUCHIEN "
					+ " INNER JOIN DE_XUAT dx " + " ON dx.MA_DE_XUAT = qdth.MA_DE_XUAT "
					+ " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
					+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ " WHERE  dx.THOI_DIEM_BAT_DAU>='" + mdf.getSQLStringDate(begin)
					+ "' AND gdqt.THOI_GIAN_KET_THUC<='" + mdf.getSQLStringDate(end) + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_DotSuachuaBaoduong_ChuaHoanthanh() {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG dsb" + " INNER JOIN GIAI_DOAN_QUYET_TOAN gdqt "
					+ " ON gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN "
					+ " WHERE  gdqt.THOI_GIAN_KET_THUC IS NULL";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_Thaynhot_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_NHOT = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_NHOT = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayLocnhot_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_NHOT = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_NHOT = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayLocgio_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_GIO = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_GIO = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayLocNhienlieu_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_NHIEN_LIEU = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_NHIEN_LIEU = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayDauphanh_Daulyhop_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_PHANH_DAU_LY_HOP = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_PHANH_DAU_LY_HOP = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayDauvisai_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_HOP_SO = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_HOP_SO = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayDauhopso_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_VI_SAI = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_VI_SAI = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayLocgioGianlanh_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_GIO_GIAN_LANH = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_LOC_GIO_GIAN_LANH = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_ThayDauTroluclai_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_TRO_LUC_LAI = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND THAY_DAU_TRO_LUC_LAI = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_Dot_Baoduong_Baoduongkhac_Gannhat(int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND BAO_DUONG_KHAC = true)" + " INNER JOIN GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " WHERE (gdnt.THOI_DIEM_KET_THUC IN(SELECT MAX(gdnt.THOI_DIEM_KET_THUC ) FROM GIAI_DOAN_NGHIEM_THU as gdnt"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " ON dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdnt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN"
					+ " INNER JOIN DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN as dsbt"
					+ " ON (dsb.MA_DOT_THUCHIEN_SUACHUA_BAODUONG = dsbt.MA_DOT_THUCHIEN_SUACHUA_BAODUONG AND dsbt.MA_TAISAN = "
					+ ma_TAISAN + " AND BAO_DUONG_KHAC = true)))";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Select_All_Dot_Suachua_Baoduong(Date date, Date date2, String text) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON (dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN ) "
					+ " INNER JOIN DE_XUAT as dx " + " ON (dx.MA_DE_XUAT = qtdxth.MA_DE_XUAT AND dx.THOI_DIEM_BAT_DAU>'"
					+ mdf.getSQLStringDate(date) + "')" + " INNER JOIN GIAI_DOAN_QUYET_TOAN as gdqt "
					+ " ON (dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND gdqt.THOI_GIAN_KET_THUC <'"
					+ mdf.getSQLStringDate(date2) + "') WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG LIKE '%" + text
					+ "%'  OR TEN_DOT_THUCHIEN_SUACHUA_BAODUONG LIKE '%" + text + "%'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Danhsach_DotSuachuaBaoduong_Ganday(Date begin, Date end, int ma_TAISAN) {
		try {
			return "SELECT * FROM DOT_THUCHIEN_SUACHUA_BAODUONG as dsb "
					+ " INNER JOIN QUATRINH_DEXUAT_THUCHIEN as qtdxth "
					+ " ON (dsb.MA_QUATRINH_DEXUAT_THUCHIEN = qtdxth.MA_QUATRINH_DEXUAT_THUCHIEN ) "
					+ " INNER JOIN DE_XUAT as dx "
					+ " ON (dx.MA_DE_XUAT = qtdxth.MA_DE_XUAT AND dx.THOI_DIEM_BAT_DAU >='"
					+ mdf.getSQLStringDate(begin) + "')" + " INNER JOIN GIAI_DOAN_QUYET_TOAN as gdqt "
					+ " ON (dsb.MA_QUATRINH_NGHIEMTHU_QUYETTOAN = gdqt.MA_QUATRINH_NGHIEMTHU_QUYETTOAN AND gdqt.THOI_GIAN_KET_THUC <='"
					+ mdf.getSQLStringDate(end)
					+ "') WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG IN (SELECT MA_DOT_THUCHIEN_SUACHUA_BAODUONG FROM DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN WHERE MA_TAISAN = '"
					+ ma_TAISAN + "' )";
		} catch (Exception e) {
			return null;
		}
	}

}
