package DAO;

import java.util.Date;

public class HOSO_DANHAN {
	private int MA_HOSO_DANHAN;
	private Date NGAY_NHAN;
	private String TAIKHOAN_NHAN;
	private String TAIKHOAN_GUI;
	private int MA_TAPHOSO;

	/**
	 * @return the mA_HOSO_DANHAN
	 */
	public final int getMA_HOSO_DANHAN() {
		return MA_HOSO_DANHAN;
	}

	/**
	 * @param mA_HOSO_DANHAN
	 *            the mA_HOSO_DANHAN to set
	 */
	public final void setMA_HOSO_DANHAN(int mA_HOSO_DANHAN) {
		MA_HOSO_DANHAN = mA_HOSO_DANHAN;
	}

	/**
	 * @return the nGAY_NHAN
	 */
	public final Date getNGAY_NHAN() {
		return NGAY_NHAN;
	}

	/**
	 * @param nGAY_NHAN
	 *            the nGAY_NHAN to set
	 */
	public final void setNGAY_NHAN(Date nGAY_NHAN) {
		NGAY_NHAN = nGAY_NHAN;
	}

	/**
	 * @return the tAIKHOAN_NHAN
	 */
	public final String getTAIKHOAN_NHAN() {
		return TAIKHOAN_NHAN;
	}

	/**
	 * @param tAIKHOAN_NHAN
	 *            the tAIKHOAN_NHAN to set
	 */
	public final void setTAIKHOAN_NHAN(String tAIKHOAN_NHAN) {
		TAIKHOAN_NHAN = tAIKHOAN_NHAN;
	}

	/**
	 * @return the tAIKHOAN_GUI
	 */
	public final String getTAIKHOAN_GUI() {
		return TAIKHOAN_GUI;
	}

	/**
	 * @param tAIKHOAN_GUI
	 *            the tAIKHOAN_GUI to set
	 */
	public final void setTAIKHOAN_GUI(String tAIKHOAN_GUI) {
		TAIKHOAN_GUI = tAIKHOAN_GUI;
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
