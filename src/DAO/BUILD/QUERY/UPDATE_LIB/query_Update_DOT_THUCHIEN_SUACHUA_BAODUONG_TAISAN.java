package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.Row_PTTSthamgia;
import View.MarkItem.Fill_ItemData;

public class query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN {

	public String getString_Update_HinhthucBaoduong_PhuongtienThamgiaBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb,
			Row_PTTSthamgia rp) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			String result = "UPDATE DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN SET THAY_NHOT = '"
					+ f.getInt_ThayNhot(rp.getHtbd().isThayNhot()) + "', THAY_LOC_NHOT ='"
					+ f.getInt_ThayLocNhot(rp.getHtbd().isThayLocNhot()) + "', THAY_LOC_GIO ='"
					+ f.getInt_ThayLocgio(rp.getHtbd().isThayLocgio()) + "', THAY_LOC_NHIEN_LIEU ='"
					+ f.getInt_ThayLocnhienlieu(rp.getHtbd().isThayLocnhienlieu()) + "', THAY_DAU_PHANH_DAU_LY_HOP='"
					+ f.getInt_ThayDauphanh_Daulyhop(rp.getHtbd().isThayDauphanh_Daulyhop()) + "', THAY_DAU_HOP_SO ='"
					+ f.getInt_ThayDauphanh_Daulyhop(rp.getHtbd().isThayDauhopso()) + "', THAY_DAU_VI_SAI ='"
					+ f.getInt_ThayDauVisai(rp.getHtbd().isThayDauVisai()) + "', THAY_LOC_GIO_GIAN_LANH ='"
					+ f.getInt_ThayLocgioGianlanh(rp.getHtbd().isThayLocgioGianlanh()) + "', THAY_DAU_TRO_LUC_LAI='"
					+ f.getInt_ThayDautroluclai(rp.getHtbd().isThayDautroluclai()) + "', BAO_DUONG_KHAC= '"
					+ f.getInt_Baoduongkhac(rp.getHtbd().isBaoduongkhac()) + "' WHERE MA_TAISAN='" + rp.getMA_TAI_SAN()
					+ "' AND MA_DOT_THUCHIEN_SUACHUA_BAODUONG ='" + dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "' ;";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
