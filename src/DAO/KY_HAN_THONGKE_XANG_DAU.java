package DAO;

import java.util.Date;

public class KY_HAN_THONGKE_XANG_DAU {
	private int MA_KYHAN;
	private String TEN_KYHAN;
	private Date NGAY_BAT_DAU;
	private Date NGAY_KET_THUC;
	private String GHI_CHU;

	public int getMA_KYHAN() {
		return MA_KYHAN;
	}

	public void setMA_KYHAN(int mA_KYHAN) {
		MA_KYHAN = mA_KYHAN;
	}

	public String getTEN_KYHAN() {
		return TEN_KYHAN;
	}

	public void setTEN_KYHAN(String tEN_KYHAN) {
		TEN_KYHAN = tEN_KYHAN;
	}

	public Date getNGAY_BAT_DAU() {
		return NGAY_BAT_DAU;
	}

	public void setNGAY_BAT_DAU(Date nGAY_BAT_DAU) {
		NGAY_BAT_DAU = nGAY_BAT_DAU;
	}

	public Date getNGAY_KET_THUC() {
		return NGAY_KET_THUC;
	}

	public void setNGAY_KET_THUC(Date nGAY_KET_THUC) {
		NGAY_KET_THUC = nGAY_KET_THUC;
	}

	public String getGHI_CHU() {
		return GHI_CHU;
	}

	public void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
	}
}
