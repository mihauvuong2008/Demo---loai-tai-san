package DAO;

import java.util.ArrayList;
import java.util.Date;

public class HOSO_ROW {
	private int MA_TAPHOSO;
	private String TEN_TAPHOSO;
	private String GIOITHIEU_TAPHOSO;
	private Date NGAY_TAO_TAPHOSO;
	private ArrayList<VANBAN> vbl;

	public int getMA_TAPHOSO() {
		return MA_TAPHOSO;
	}

	public void setMA_TAPHOSO(int mA_TAPHOSO) {
		MA_TAPHOSO = mA_TAPHOSO;
	}

	public String getTEN_TAPHOSO() {
		return TEN_TAPHOSO;
	}

	public void setTEN_TAPHOSO(String tEN_TAPHOSO) {
		TEN_TAPHOSO = tEN_TAPHOSO;
	}

	public String getGIOITHIEU_TAPHOSO() {
		return GIOITHIEU_TAPHOSO;
	}

	public void setGIOITHIEU_TAPHOSO(String gIOITHIEU_TAPHOSO) {
		GIOITHIEU_TAPHOSO = gIOITHIEU_TAPHOSO;
	}

	public Date getNGAY_TAO_TAPHOSO() {
		return NGAY_TAO_TAPHOSO;
	}

	public void setNGAY_TAO_TAPHOSO(Date nGAY_TAO_TAPHOSO) {
		NGAY_TAO_TAPHOSO = nGAY_TAO_TAPHOSO;
	}

	public ArrayList<VANBAN> getVbl() {
		return vbl;
	}

	public void setVbl(ArrayList<VANBAN> vbl) {
		this.vbl = vbl;
	}

}
