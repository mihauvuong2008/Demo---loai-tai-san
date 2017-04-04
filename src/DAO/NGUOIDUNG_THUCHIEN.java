package DAO;

import java.util.Date;

public class NGUOIDUNG_THUCHIEN {
	private int MA_GIAI_DOAN_THUC_HIEN;
	private String TEN_TAI_KHOAN;
	private int GIAO_NHANVIEC;
	private Date NGAY_THAM_GIA;
	private int MA_TAPHOSO;

	public int getMA_GIAI_DOAN_THUC_HIEN() {
		return MA_GIAI_DOAN_THUC_HIEN;
	}

	public void setMA_GIAI_DOAN_THUC_HIEN(int mA_GIAI_DOAN_THUC_HIEN) {
		MA_GIAI_DOAN_THUC_HIEN = mA_GIAI_DOAN_THUC_HIEN;
	}

	public String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	public int getGIAO_NHANVIEC() {
		return GIAO_NHANVIEC;
	}

	public void setGIAO_NHANVIEC(int gIAO_NHANVIEC) {
		GIAO_NHANVIEC = gIAO_NHANVIEC;
	}

	public Date getNGAY_THAM_GIA() {
		return NGAY_THAM_GIA;
	}

	public void setNGAY_THAM_GIA(Date nGAY_THAM_GIA) {
		NGAY_THAM_GIA = nGAY_THAM_GIA;
	}

	public int getMA_TAPHOSO() {
		return MA_TAPHOSO;
	}

	public void setMA_TAPHOSO(int mA_TAPHOSO) {
		MA_TAPHOSO = mA_TAPHOSO;
	}
}
