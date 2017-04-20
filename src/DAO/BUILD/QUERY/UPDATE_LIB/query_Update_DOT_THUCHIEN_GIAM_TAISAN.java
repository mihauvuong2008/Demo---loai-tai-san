package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;

public class query_Update_DOT_THUCHIEN_GIAM_TAISAN {

	public String getString_Capnhat_DotGiamTaisan(DOT_THUCHIEN_GIAM_TAISAN dgt) {
		try {
			return "UPDATE DOT_THUCHIEN_GIAM_TAISAN  SET TEN_DOT_GIAM='" + dgt.getTEN_DOT_GIAM() + "', LY_DO_GIAM='"
					+ dgt.getLY_DO_GIAM() + "', MO_TA = '" + dgt.getMO_TA() + "' "
					+ (dgt.getMA_NGUONGIAM() == 0 ? "" : ", MA_NGUONGIAM ='" + dgt.getMA_NGUONGIAM() + "'")
					+ " WHERE MA_DOT_GIAM= '" + dgt.getMA_DOT_GIAM() + "'";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_QuatrinhNghiemthu_Capnhat_Giaidoan_Dexuat_Thuchien(DOT_THUCHIEN_GIAM_TAISAN dgt,
			int ma_Quatrinh_Dexuat_thuchien) {
		try {
			String result = "UPDATE DOT_THUCHIEN_GIAM_TAISAN  SET MA_QUATRINH_DEXUAT_THUCHIEN='"
					+ ma_Quatrinh_Dexuat_thuchien + "' WHERE MA_DOT_GIAM='" + dgt.getMA_DOT_GIAM() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
