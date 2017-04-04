package DAO;

import java.util.Date;

public class VANBAN {
	private int MA_VANBAN;
	private String SO_VANBAN;
	private Date NGAY_BAN_HANH;
	private String CO_QUAN_BAN_HANH;
	private String TRICH_YEU;
	private int MA_TAPHOSO;

	public int getMA_VANBAN() {
		return MA_VANBAN;
	}

	public void setMA_VANBAN(int mA_VANBAN) {
		MA_VANBAN = mA_VANBAN;
	}

	public String getSO_VANBAN() {
		return SO_VANBAN;
	}

	public void setSO_VANBAN(String sO_VANBAN) {
		SO_VANBAN = sO_VANBAN;
	}

	public Date getNGAY_BAN_HANH() {
		return NGAY_BAN_HANH;
	}

	public void setNGAY_BAN_HANH(Date nGAY_BAN_HANH) {
		NGAY_BAN_HANH = nGAY_BAN_HANH;
	}

	public String getCO_QUAN_BAN_HANH() {
		return CO_QUAN_BAN_HANH;
	}

	public void setCO_QUAN_BAN_HANH(String cO_QUAN_BAN_HANH) {
		CO_QUAN_BAN_HANH = cO_QUAN_BAN_HANH;
	}

	public String getTRICH_YEU() {
		return TRICH_YEU;
	}

	public void setTRICH_YEU(String tRICH_YEU) {
		TRICH_YEU = tRICH_YEU;
	}

	public int getMA_TAPHOSO() {
		return MA_TAPHOSO;
	}

	public void setMA_TAPHOSO(int mA_TAPHOSO) {
		MA_TAPHOSO = mA_TAPHOSO;
	}
}
