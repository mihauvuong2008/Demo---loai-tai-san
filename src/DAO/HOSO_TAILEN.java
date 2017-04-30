package DAO;

import java.util.Date;

public class HOSO_TAILEN {
	private int MA_HOSO_TAILEN;
	private String TEN_TAI_KHOAN;
	private int MA_TAPHOSO;

	public final int getMA_HOSO_TAILEN() {
		return MA_HOSO_TAILEN;
	}

	public final void setMA_HOSO_TAILEN(int mA_HOSO_TAILEN) {
		MA_HOSO_TAILEN = mA_HOSO_TAILEN;
	}

	/**
	 * @return the tEN_TAI_KHOAN
	 */
	public final String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	/**
	 * @param tEN_TAI_KHOAN
	 *            the tEN_TAI_KHOAN to set
	 */
	public final void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	/**
	 * @return the mA_TAPHOSO
	 */
	public final int getMA_TAPHOSO() {
		return MA_TAPHOSO;
	}

	/**
	 * @param mA_TAPHOSO
	 *            the mA_TAPHOSO to set
	 */
	public final void setMA_TAPHOSO(int mA_TAPHOSO) {
		MA_TAPHOSO = mA_TAPHOSO;
	}

}
