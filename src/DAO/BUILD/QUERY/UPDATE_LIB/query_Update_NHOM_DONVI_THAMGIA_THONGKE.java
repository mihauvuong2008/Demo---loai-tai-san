package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.NHOM_DONVI_THAMGIA_THONGKE;

public class query_Update_NHOM_DONVI_THAMGIA_THONGKE {

	public String getString_Capnhat_NhomDonviThamgiaThongKe(NHOM_DONVI_THAMGIA_THONGKE r) {
		try {
			String result = "UPDATE NHOM_DONVI_THAMGIA_THONGKE SET TEN_NHOM_DONVI_THAMGIA_THONGKE  ='"
					+ r.getTEN_NHOM_DONVI_THAMGIA_THONGKE() + "'  where MA_QUYEN='"
					+ r.getMA_NHOM_DONVI_THAMGIA_THONGKE() + "'";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
