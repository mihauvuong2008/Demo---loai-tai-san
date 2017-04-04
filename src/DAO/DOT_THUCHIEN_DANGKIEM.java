package DAO;

import java.util.Date;

public class DOT_THUCHIEN_DANGKIEM {
	private int MA_DOT_THUCHIEN_DANGKIEM;// INTEGER(10) NOT NULL AUTO_INCREMENT,
	private Date NGAY_THUCHIEN;// DATE,
	private int MA_TAPHOSO;// INTEGER,
	private String MA_PHUONGTIEN_GIAOTHONG;// INTEGER,
	private String GHI_CHU;// VARCHAR(400),

	public final int getMA_DOT_THUCHIEN_DANGKIEM() {
		return MA_DOT_THUCHIEN_DANGKIEM;
	}

	public final void setMA_DOT_THUCHIEN_DANGKIEM(int mA_DOT_THUCHIEN_DANGKIEM) {
		MA_DOT_THUCHIEN_DANGKIEM = mA_DOT_THUCHIEN_DANGKIEM;
	}

	public final Date getNGAY_THUCHIEN() {
		return NGAY_THUCHIEN;
	}

	public final void setNGAY_THUCHIEN(Date nGAY_THUCHIEN) {
		NGAY_THUCHIEN = nGAY_THUCHIEN;
	}

	public final int getMA_TAPHOSO() {
		return MA_TAPHOSO;
	}

	public final void setMA_TAPHOSO(int mA_TAPHOSO) {
		MA_TAPHOSO = mA_TAPHOSO;
	}

	public final String getMA_PHUONGTIEN_GIAOTHONG() {
		return MA_PHUONGTIEN_GIAOTHONG;
	}

	public final void setMA_PHUONGTIEN_GIAOTHONG(String mA_PHUONGTIEN_GIAOTHONG) {
		MA_PHUONGTIEN_GIAOTHONG = mA_PHUONGTIEN_GIAOTHONG;
	}

	public final String getGHI_CHU() {
		return GHI_CHU;
	}

	public final void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
	}

}
