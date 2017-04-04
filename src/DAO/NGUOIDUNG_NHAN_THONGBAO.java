package DAO;

import java.util.Date;

public class NGUOIDUNG_NHAN_THONGBAO {
	int MA_THONGBAO;
	String TEN_TAI_KHOAN;
	Date NGAY_DOC_THONGBAO;

	public int getMA_THONGBAO() {
		return MA_THONGBAO;
	}

	public void setMA_THONGBAO(int mA_THONGBAO) {
		MA_THONGBAO = mA_THONGBAO;
	}

	public String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	public Date getNGAY_DOC_THONGBAO() {
		return NGAY_DOC_THONGBAO;
	}

	public void setNGAY_DOC_THONGBAO(Date nGAY_DOC_THONGBAO) {
		NGAY_DOC_THONGBAO = nGAY_DOC_THONGBAO;
	}
}
