package DAO.BUILD.QUERY.SELECT_LIB;

import DAO.LOAI_XE;
import View.MarkItem.Fill_ItemData;

public class query_Select_LOAI_XE {

	public String getString_Loaixe(int ma_LOAI_XE) {
		try {
			return "SELECT * from LOAI_XE WHERE MA_LOAI_XE = '" + ma_LOAI_XE + "' ;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaLoaixe() {
		try {
			return "SELECT * from LOAI_XE;";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaXemay() {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT * from LOAI_XE WHERE OTO_XEMAY='" + f.getInt_Xemay() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_TatcaOto() {
		try {
			Fill_ItemData f = new Fill_ItemData();
			return "SELECT * from LOAI_XE WHERE OTO_XEMAY='" + f.getInt_Oto() + "';";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_DateList() {
		try {
			return "SELECT * from LOAI_XE";
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_KyhanBaoduong(LOAI_XE lx) {
		try {
			return "SELECT * from LOAI_XE WHERE MA_LOAI_XE ='" + lx.getMA_LOAI_XE() + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
