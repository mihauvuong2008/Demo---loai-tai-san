package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;

public class query_Select_GIAI_DOAN_THUC_HIEN {

	public String getString_GiaidoanThuchien_SuachuaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			return "SELECT * " + " FROM GIAI_DOAN_THUC_HIEN" + " WHERE MA_QUATRINH_DEXUAT_THUCHIEN = '"
					+ dsb.getMA_QUATRINH_DEXUAT_THUCHIEN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_GiaidoanThuchien_TangTaisan(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			return "SELECT * " + " FROM GIAI_DOAN_THUC_HIEN" + " WHERE MA_QUATRINH_DEXUAT_THUCHIEN = '"
					+ dtt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "'; ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_GiaidoanThuchien_GiamTaisan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "SELECT * " + " FROM GIAI_DOAN_THUC_HIEN" + " WHERE MA_QUATRINH_DEXUAT_THUCHIEN = '"
					+ dgt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "' ";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DangThuchien_SuachuaBaoduong(NGUOIDUNG nd) {
		try {
			return "SELECT * " + " FROM DOT_THUCHIEN_SUACHUA_BAODUONG ,GIAI_DOAN_THUC_HIEN"
					+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
					+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
					+ nd.getTEN_TAI_KHOAN() + "'"
					+ " WHERE THOI_DIEM_HOAN_THANH IS NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DangThuchien_TangTaisan(NGUOIDUNG nd) {
		try {
			return "SELECT * " + " FROM DOT_THUCHIEN_TANG_TAISAN ,GIAI_DOAN_THUC_HIEN"
					+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
					+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
					+ nd.getTEN_TAI_KHOAN() + "'"
					+ " WHERE THOI_DIEM_HOAN_THANH IS NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DangThuchien_GiamTaisan(NGUOIDUNG nd) {
		try {
			return "SELECT * " + " FROM DOT_THUCHIEN_GIAM_TAISAN ,GIAI_DOAN_THUC_HIEN"
					+ " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
					+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
					+ nd.getTEN_TAI_KHOAN() + "'"
					+ " WHERE THOI_DIEM_HOAN_THANH IS NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DaThuchien_SuachuaBaoduong(NGUOIDUNG nd) {
		try {
			String userPart = nd == null ? ""
					: " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
							+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
							+ nd.getTEN_TAI_KHOAN() + "'";
			return "SELECT * " + " FROM DOT_THUCHIEN_SUACHUA_BAODUONG ,GIAI_DOAN_THUC_HIEN " + userPart
					+ " WHERE THOI_DIEM_HOAN_THANH IS NOT NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_SUACHUA_BAODUONG.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DaThuchien_TangTaisan(NGUOIDUNG nd) {
		try {
			String userPart = nd == null ? ""
					: " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
							+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
							+ nd.getTEN_TAI_KHOAN() + "'";
			return "SELECT * " + " FROM DOT_THUCHIEN_TANG_TAISAN ,GIAI_DOAN_THUC_HIEN " + userPart
					+ " WHERE THOI_DIEM_HOAN_THANH IS NOT NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_TANG_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Congviec_Phanviec_DaThuchien_GiamTaisan(NGUOIDUNG nd) {
		try {
			String userPart = nd == null ? ""
					: " INNER JOIN GIAI_DOAN_THUC_HIEN_CAN_BO"
							+ " ON (GIAI_DOAN_THUC_HIEN_CAN_BO.MA_GIAI_DOAN_THUC_HIEN= GIAI_DOAN_THUC_HIEN.MA_GIAI_DOAN_THUC_HIEN ) AND TEN_TAI_KHOAN='"
							+ nd.getTEN_TAI_KHOAN() + "'";
			return "SELECT * " + " FROM DOT_THUCHIEN_GIAM_TAISAN ,GIAI_DOAN_THUC_HIEN" + userPart
					+ " WHERE THOI_DIEM_HOAN_THANH IS NOT NULL AND GIAI_DOAN_THUC_HIEN.MA_QUATRINH_DEXUAT_THUCHIEN = DOT_THUCHIEN_GIAM_TAISAN.MA_QUATRINH_DEXUAT_THUCHIEN;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_GiaidoanThuchien(int ma_PHANVIEC) {
		try {
			return "SELECT * FROM GIAI_DOAN_THUC_HIEN  WHERE MA_GIAI_DOAN_THUC_HIEN = '" + ma_PHANVIEC + "' ";
		} catch (Exception e) {
			return null;
		}
	}

}
