package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.LOAI_XE;

public class query_Update_LOAI_XE {

	public String getString_CapnhatLoaixe(LOAI_XE r) {
		try {
			return "UPDATE LOAI_XE SET TEN_DONG_XE  ='" + r.getTEN_DONG_XE() + "', HANG_SAN_XUAT='"
					+ r.getHANG_SAN_XUAT() + "', OTO_XEMAY='" + r.getOTO_XEMAY() + "', DINH_MUC_XANG_DAU='"
					+ r.getDINH_MUC_XANG_DAU() + "'   " + "WHERE MA_LOAI_XE = '" + r.getMA_LOAI_XE() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_CapnhatTieuchuanBaoduong(LOAI_XE r) {
		try {
			return "UPDATE LOAI_XE SET TEN_DONG_XE  ='" + r.getTEN_DONG_XE() + "', HANG_SAN_XUAT='"
					+ r.getHANG_SAN_XUAT() + "', OTO_XEMAY='" + r.getOTO_XEMAY() + "', DINH_MUC_XANG_DAU='"
					+ r.getDINH_MUC_XANG_DAU() + "', THAY_NHOT ='" + r.getTHAY_NHOT() + "', THAY_LOC_NHOT ='"
					+ r.getTHAY_LOC_NHOT() + "', THAY_LOC_GIO ='" + r.getTHAY_LOC_GIO() + "', THAY_LOC_NHIEN_LIEU ='"
					+ r.getTHAY_LOC_NHIEN_LIEU() + "', THAY_DAU_PHANH_DAU_LY_HOP ='" + r.getTHAY_DAU_PHANH_DAU_LY_HOP()
					+ "', THAY_DAU_HOP_SO ='" + r.getTHAY_DAU_HOP_SO() + "', THAY_DAU_VI_SAI ='"
					+ r.getTHAY_DAU_VI_SAI() + "', THAY_LOC_GIO_GIAN_LANH ='" + r.getTHAY_LOC_GIO_GIAN_LANH()
					+ "', THAY_DAU_TRO_LUC_LAI ='" + r.getTHAY_DAU_TRO_LUC_LAI() + "', BAO_DUONG_KHAC ='"
					+ r.getBAO_DUONG_KHAC() + "'  " + "WHERE MA_LOAI_XE = '" + r.getMA_LOAI_XE() + "';";
		} catch (Exception e) {
			return null;
		}
	}

}
