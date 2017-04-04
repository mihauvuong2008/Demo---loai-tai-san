package DAO.BUILD.QUERY.SELECT_LIB;

import java.util.Date;

import DAO.NGUOIDUNG;
import View.DateTime.MyDateFormat;
import View.MarkItem.Fill_ItemData;

public class query_Select_THONGBAO {

	private final MyDateFormat mdf = new MyDateFormat();

	public String getString_Thongbao_Lenhdieuxe(NGUOIDUNG user, Date date, Date date2) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_LenhDieuxe() + "'  AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "'  AND NGAY_THONGBAO > '" + mdf.getSQLStringDate(date)
					+ "' AND NGAY_THONGBAO < '" + mdf.getSQLStringDate(date2) + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_SuachuaBaoduong(NGUOIDUNG user, Date date, Date date2) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND (LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Suachua() + "' OR LOAI_THONGBAO = '" + f.getInt_LOAI_THONGBAO_Baoduong()
					+ "' )  AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '" + user.getTEN_TAI_KHOAN()
					+ "' AND NGAY_THONGBAO > '" + mdf.getSQLStringDate(date) + "' AND NGAY_THONGBAO < '"
					+ mdf.getSQLStringDate(date2) + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_Muasam_Thanhly(NGUOIDUNG user, Date date, Date date2) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND (LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Muasam() + "' OR LOAI_THONGBAO='" + f.getInt_LOAI_THONGBAO_Thanhly()
					+ "' ) AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '" + user.getTEN_TAI_KHOAN()
					+ "' AND NGAY_THONGBAO > '" + mdf.getSQLStringDate(date) + "' AND NGAY_THONGBAO < '"
					+ mdf.getSQLStringDate(date2) + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_Nguoidung(NGUOIDUNG user, Date date, Date date2) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Nguoidung() + "' AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "' AND NGAY_THONGBAO > '" + mdf.getSQLStringDate(date)
					+ "' AND NGAY_THONGBAO < '" + mdf.getSQLStringDate(date2) + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_ThongbaoKhac(NGUOIDUNG user, Date date, Date date2) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_ThongbaoKhac() + "' AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "' AND NGAY_THONGBAO > '" + mdf.getSQLStringDate(date)
					+ "' AND NGAY_THONGBAO < '" + mdf.getSQLStringDate(date2) + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_Lenhdieuxe_ChuaDoc(NGUOIDUNG user) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_LenhDieuxe()
					+ "' AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO = null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_SuachuaBaoduong_ChuaDoc(NGUOIDUNG user) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND (LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Suachua() + "' OR LOAI_THONGBAO = '" + f.getInt_LOAI_THONGBAO_Baoduong()
					+ "' )AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO is null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_Muasam_Thanhly_ChuaDoc(NGUOIDUNG user) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND (LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Muasam() + "' OR LOAI_THONGBAO='" + f.getInt_LOAI_THONGBAO_Thanhly()
					+ "' ) AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO is null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_Nguoidung_ChuaDoc(NGUOIDUNG user) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_Nguoidung()
					+ "' AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO is null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Thongbao_ThongbaoKhac_ChuaDoc(NGUOIDUNG user) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT THONGBAO.MA_THONGBAO , THONGBAO.LOAI_THONGBAO, THONGBAO.TEN_TAI_KHOAN_GUI, "
					+ "THONGBAO.NGUON_TACDONG, THONGBAO.NGUON_BITACDONG, THONGBAO.NOIDUNG_THONGBAO, THONGBAO.NGAY_THONGBAO "
					+ "FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO AND LOAI_THONGBAO = '"
					+ f.getInt_LOAI_THONGBAO_ThongbaoKhac()
					+ "' AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO is null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_bool_Thongbao_chuadoc(NGUOIDUNG user) {
		try {
			return "SELECT THONGBAO.MA_THONGBAO  FROM THONGBAO, NGUOIDUNG_NHAN_THONGBAO WHERE NGUOIDUNG_NHAN_THONGBAO.MA_THONGBAO = THONGBAO.MA_THONGBAO   AND NGUOIDUNG_NHAN_THONGBAO.NGAY_DOC_THONGBAO is null AND NGUOIDUNG_NHAN_THONGBAO.TEN_TAI_KHOAN = '"
					+ user.getTEN_TAI_KHOAN() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
