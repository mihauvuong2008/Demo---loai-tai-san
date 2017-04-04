package DAO;

import java.util.Date;

public class THONGBAO {
	int MA_THONGBAO;
	int LOAI_THONGBAO;
	String TEN_TAI_KHOAN_GUI;
	String NGUON_TACDONG;
	String NGUON_BITACDONG;
	String NOIDUNG_THONGBAO;
	Date NGAY_THONGBAO;

	public int getMA_THONGBAO() {
		return MA_THONGBAO;
	}

	public void setMA_THONGBAO(int mA_THONGBAO) {
		MA_THONGBAO = mA_THONGBAO;
	}

	public int getLOAI_THONGBAO() {
		return LOAI_THONGBAO;
	}

	public void setLOAI_THONGBAO(int lOAI_THONGBAO) {
		LOAI_THONGBAO = lOAI_THONGBAO;
	}

	public String getTEN_TAI_KHOAN_GUI() {
		return TEN_TAI_KHOAN_GUI;
	}

	public void setTEN_TAI_KHOAN_GUI(String tEN_TAI_KHOAN_GUI) {
		TEN_TAI_KHOAN_GUI = tEN_TAI_KHOAN_GUI;
	}

	public String getNGUON_TACDONG() {
		return NGUON_TACDONG;
	}

	public void setNGUON_TACDONG(String nGUON_TACDONG) {
		NGUON_TACDONG = nGUON_TACDONG;
	}

	public String getNGUON_BITACDONG() {
		return NGUON_BITACDONG;
	}

	public void setNGUON_BITACDONG(String nGUON_BITACDONG) {
		NGUON_BITACDONG = nGUON_BITACDONG;
	}

	public String getNOIDUNG_THONGBAO() {
		return NOIDUNG_THONGBAO;
	}

	public void setNOIDUNG_THONGBAO(String nOIDUNG_THONGBAO) {
		NOIDUNG_THONGBAO = nOIDUNG_THONGBAO;
	}

	public Date getNGAY_THONGBAO() {
		return NGAY_THONGBAO;
	}

	public void setNGAY_THONGBAO(Date nGAY_THONGBAO) {
		NGAY_THONGBAO = nGAY_THONGBAO;
	}
}
