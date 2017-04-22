package DAO;

import java.util.ArrayList;

public class HOSO_ROW {
	private TAPHOSO taphoso;
	private ArrayList<VANBAN> vanbanList;

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

}
