package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_THUCHIEN_TANG_TAISAN;

public class query_Update_DOT_THUCHIEN_TANG_TAISAN {

	public String getString_QuatrinhNghiemthu_Capnhat_Giaidoan_Quyettoan(DOT_THUCHIEN_TANG_TAISAN dtt,
			int mA_QUATRINH_NGHIEMTHU_QUYETTOAN) {
		try {
			String result = "UPDATE DOT_THUCHIEN_TANG_TAISAN  SET MA_QUATRINH_NGHIEMTHU_QUYETTOAN='"
					+ mA_QUATRINH_NGHIEMTHU_QUYETTOAN + "' WHERE MA_DOT_TANG='" + dtt.getMA_DOT_TANG() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_DotTangTaisan(DOT_THUCHIEN_TANG_TAISAN dtt) {
		try {
			String result = "UPDATE DOT_THUCHIEN_TANG_TAISAN  SET TEN_DOT_TANG='" + dtt.getTEN_DOT_TANG()
					+ "', LY_DO_TANG='" + dtt.getLY_DO_TANG() + "', MO_TA = '" + dtt.getMO_TA() + "', MA_NGUONTANG = '"
					+ dtt.getMA_DOT_TANG() + "' WHERE MA_DOT_TANG= '" + dtt.getMA_DOT_TANG() + "'";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
