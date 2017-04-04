package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.Row_PTTSthamgia;
import DAO.TAISAN;
import View.MarkItem.Fill_ItemData;

public class query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN {

	public String getString_Insert_Phuongtien_Thamgia_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, Row_PTTSthamgia rp) {
		try {
			Fill_ItemData f = new Fill_ItemData();
			String result = "INSERT INTO DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN ( MA_TAISAN, MA_DOT_THUCHIEN_SUACHUA_BAODUONG, THAY_NHOT, THAY_LOC_NHOT, THAY_LOC_GIO, THAY_LOC_NHIEN_LIEU, THAY_DAU_PHANH_DAU_LY_HOP, THAY_DAU_HOP_SO, THAY_DAU_VI_SAI, THAY_LOC_GIO_GIAN_LANH, THAY_DAU_TRO_LUC_LAI, BAO_DUONG_KHAC ) VALUES( '"
					+ rp.getMA_TAI_SAN() + "','" + dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "', '"
					+ f.getInt_ThayNhot(rp.getHtbd().isThayNhot()) + "', '"
					+ f.getInt_ThayLocNhot(rp.getHtbd().isThayLocNhot()) + "', '"
					+ f.getInt_ThayLocgio(rp.getHtbd().isThayLocgio()) + "','"
					+ f.getInt_ThayLocnhienlieu(rp.getHtbd().isThayLocnhienlieu()) + "','"
					+ f.getInt_ThayDauphanh_Daulyhop(rp.getHtbd().isThayDauphanh_Daulyhop()) + "','"
					+ f.getInt_ThayDauphanh_Daulyhop(rp.getHtbd().isThayDauhopso()) + "','"
					+ f.getInt_ThayDauVisai(rp.getHtbd().isThayDauVisai()) + "','"
					+ f.getInt_ThayLocgioGianlanh(rp.getHtbd().isThayLocgioGianlanh()) + "','"
					+ f.getInt_ThayDautroluclai(rp.getHtbd().isThayDautroluclai()) + "','"
					+ f.getInt_Baoduongkhac(rp.getHtbd().isBaoduongkhac()) + "' );";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Insert_Taisan_Thamgia_Suachua(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb, TAISAN ts) {
		try {
			String result = "INSERT INTO DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN ( MA_TAISAN, MA_DOT_THUCHIEN_SUACHUA_BAODUONG) VALUES( '"
					+ ts.getMA_TAISAN() + "','" + vIEW_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "' );";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
