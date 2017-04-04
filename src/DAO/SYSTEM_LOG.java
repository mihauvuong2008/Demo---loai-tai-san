package DAO;

import java.util.Date;

public class SYSTEM_LOG {
	int LOG_ID;
	String TEN_TAI_KHOAN;
	String NOIDUNG;
	Date THOIGIAN;

	public int getLOG_ID() {
		return LOG_ID;
	}

	public void setLOG_ID(int lOG_ID) {
		LOG_ID = lOG_ID;
	}

	public String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	public String getNOIDUNG() {
		return NOIDUNG;
	}

	public void setNOIDUNG(String nOIDUNG) {
		NOIDUNG = nOIDUNG;
	}

	public Date getTHOIGIAN() {
		return THOIGIAN;
	}

	public void setTHOIGIAN(Date tHOIGIAN) {
		THOIGIAN = tHOIGIAN;
	}
}
