package DAO;

import java.util.ArrayList;

public class HOSO_ROW {
	private TAPHOSO taphoso;
	private ArrayList<VANBAN> vanbanList;
	private String TEN_TAI_KHOAN;

	public final TAPHOSO getTaphoso() {
		return taphoso;
	}

	public final void setTaphoso(TAPHOSO ths) {
		this.taphoso = ths;
	}

	public ArrayList<VANBAN> getVanbanList() {
		return vanbanList;
	}

	public void setVanbanList(ArrayList<VANBAN> vbl) {
		this.vanbanList = vbl;
	}

	public final String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public final void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

}
