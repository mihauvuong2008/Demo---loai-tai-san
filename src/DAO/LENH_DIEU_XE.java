package DAO;

import java.util.Date;

public class LENH_DIEU_XE {

	private int MA_LENH_DIEU_XE;// INTEGER (20) NOT NULL AUTO_INCREMENT,
	private String MA_PHUONGTIEN_GIAOTHONG;// VARCHAR (20) NOT NULL,
	private String TEN_TAI_KHOAN;// VARCHAR(20) NOT NULL,
	private String DIEM_XUATPHAT;// VARCHAR(200),
	private String DIEM_DEN;// VARCHAR(200),
	private Date NGAY_DI;// DATE ,
	private Date NGAY_VE;// DATE,
	private int SO_KM_HIENTAI;// INTEGER (30) NOT NULL,
	private int TON_NHIENLIEU_HIENTAI;// INTEGER,
	private int QUANG_DUONG_DUKIEN;// INTEGER (10),
	private int PHIEU_NHIENLIEU_DUOCCAP;// INTEGER (10),
	private int PHIEU_NHIENLIEU_MUANGOAI;// INTEGER (10),
	private String DIADIEM_GIODON;// VARCHAR(200),
	private String NOIDUNG_CHUYENDI;// VARCHAR(400),
	private String NGUOI_TAO_LENH;
	private int HUY_LENH = 1;

	public int getMA_LENH_DIEU_XE() {
		return MA_LENH_DIEU_XE;
	}

	public void setMA_LENH_DIEU_XE(int mA_LENH_DIEU_XE) {
		MA_LENH_DIEU_XE = mA_LENH_DIEU_XE;
	}

	public String getMA_PHUONGTIEN_GIAOTHONG() {
		return MA_PHUONGTIEN_GIAOTHONG;
	}

	public void setMA_PHUONGTIEN_GIAOTHONG(String mA_PHUONGTIEN_GIAOTHONG) {
		MA_PHUONGTIEN_GIAOTHONG = mA_PHUONGTIEN_GIAOTHONG;
	}

	public String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	public String getDIEM_XUATPHAT() {
		return DIEM_XUATPHAT;
	}

	public void setDIEM_XUATPHAT(String dIEM_XUATPHAT) {
		DIEM_XUATPHAT = dIEM_XUATPHAT;
	}

	public String getDIEM_DEN() {
		return DIEM_DEN;
	}

	public void setDIEM_DEN(String dIEM_DEN) {
		DIEM_DEN = dIEM_DEN;
	}

	public Date getNGAY_DI() {
		return NGAY_DI;
	}

	public void setNGAY_DI(Date nGAY_DI) {
		NGAY_DI = nGAY_DI;
	}

	public Date getNGAY_VE() {
		return NGAY_VE;
	}

	public void setNGAY_VE(Date nGAY_VE) {
		NGAY_VE = nGAY_VE;
	}

	public int getSO_KM_HIENTAI() {
		return SO_KM_HIENTAI;
	}

	public void setSO_KM_HIENTAI(int sO_KM_HIENTAI) {
		SO_KM_HIENTAI = sO_KM_HIENTAI;
	}

	public int getTON_NHIENLIEU_HIENTAI() {
		return TON_NHIENLIEU_HIENTAI;
	}

	public void setTON_NHIENLIEU_HIENTAI(int tON_NHIENLIEU_HIENTAI) {
		TON_NHIENLIEU_HIENTAI = tON_NHIENLIEU_HIENTAI;
	}

	public int getQUANG_DUONG_DUKIEN() {
		return QUANG_DUONG_DUKIEN;
	}

	public void setQUANG_DUONG_DUKIEN(int qUANG_DUONG_DUKIEN) {
		QUANG_DUONG_DUKIEN = qUANG_DUONG_DUKIEN;
	}

	public final int getPHIEU_NHIENLIEU_MUANGOAI() {
		return PHIEU_NHIENLIEU_MUANGOAI;
	}

	public final void setPHIEU_NHIENLIEU_MUANGOAI(int pHIEU_NHIENLIEU_MUANGOAI) {
		PHIEU_NHIENLIEU_MUANGOAI = pHIEU_NHIENLIEU_MUANGOAI;
	}

	public int getPHIEU_NHIENLIEU_DUOCCAP() {
		return PHIEU_NHIENLIEU_DUOCCAP;
	}

	public void setPHIEU_NHIENLIEU_DUOCCAP(int pHIEU_NHIENLIEU_DUOCCAP) {
		PHIEU_NHIENLIEU_DUOCCAP = pHIEU_NHIENLIEU_DUOCCAP;
	}

	public String getDIADIEM_GIODON() {
		return DIADIEM_GIODON;
	}

	public void setDIADIEM_GIODON(String dIADIEM_GIODON) {
		DIADIEM_GIODON = dIADIEM_GIODON;
	}

	public String getNOIDUNG_CHUYENDI() {
		return NOIDUNG_CHUYENDI;
	}

	public void setNOIDUNG_CHUYENDI(String nOIDUNG_CHUYENDI) {
		NOIDUNG_CHUYENDI = nOIDUNG_CHUYENDI;
	}

	public String getNGUOI_TAO_LENH() {
		return NGUOI_TAO_LENH;
	}

	public void setNGUOI_TAO_LENH(String nGUOI_TAO_LENH) {
		NGUOI_TAO_LENH = nGUOI_TAO_LENH;
	}

	public int getHUY_LENH() {
		return HUY_LENH;
	}

	public void setHUY_LENH(int hUY_LENH) {
		HUY_LENH = hUY_LENH;
	}

}
