package DAO;

import java.util.ArrayList;

public class HOSO_USER {
	private TAPHOSO taphoso;
	private ArrayList<VANBAN> vanbanList;
	private String NGUOITAO_HOSO;
	private NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui;
	private HOSO_DAGUI Hoso_Dagui;
	private HOSO_DANHAN Hoso_Danhan;

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
		return NGUOITAO_HOSO;
	}

	public final void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		NGUOITAO_HOSO = tEN_TAI_KHOAN;
	}

	public final NGUOINHAN_HOSO_DAGUI getNguoiNhanHosoDagui() {
		return nguoiNhanHosoDagui;
	}

	public final void setNguoiNhanHosoDagui(NGUOINHAN_HOSO_DAGUI nguoiNhanHosoDagui) {
		this.nguoiNhanHosoDagui = nguoiNhanHosoDagui;
	}

	public final HOSO_DAGUI getHoso_Dagui() {
		return Hoso_Dagui;
	}

	public final void setHoso_Dagui(HOSO_DAGUI hoso_Dagui) {
		Hoso_Dagui = hoso_Dagui;
	}

	public final HOSO_DANHAN getHoso_Danhan() {
		return Hoso_Danhan;
	}

	public final void setHoso_Danhan(HOSO_DANHAN hoso_Danhan) {
		Hoso_Danhan = hoso_Danhan;
	}

}
