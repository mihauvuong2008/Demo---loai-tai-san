package DAO;

import java.util.Date;

public class HOSO_DAGUI {
	private int MA_HOSO_DAGUI;
	private Date NGAY_GUI;
	private String TEN_TAI_KHOAN;
	private int MA_TAPHOSO;

	public final int getMA_HOSO_DAGUI() {
		return MA_HOSO_DAGUI;
	}

	public final void setMA_HOSO_DAGUI(int mA_HOSO_DAGUI) {
		MA_HOSO_DAGUI = mA_HOSO_DAGUI;
	}

	/**
	 * @return the nGAY_GUI
	 */
	public final Date getNGAY_GUI() {
		return NGAY_GUI;
	}

	/**
	 * @param nGAY_GUI
	 *            the nGAY_GUI to set
	 */
	public final void setNGAY_GUI(Date nGAY_GUI) {
		NGAY_GUI = nGAY_GUI;
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
