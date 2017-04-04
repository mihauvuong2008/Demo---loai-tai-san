package DAO;

import java.util.ArrayList;
import java.util.Date;

public class TAISAN {

	private int MA_TAISAN;
	private String TEN_TAISAN;
	private String MODEL;
	private String SERI;
	private Date NGAY_SU_DUNG;
	private String XUAT_XU;
	private String BAO_HANH;
	private int TINH_TRANG;
	private int TRANG_THAI;
	private String DON_VI_TINH;
	private int SOLUONG;
	private long NGUYEN_GIA;
	private String GHI_CHU;
	private String MA_TANSAN_KETOAN;
	private int MA_NHOMTAISAN_CAP_V;
	private int MA_LOAITAISAN_CAP_III;
	private int MA_DON_VI_SU_DUNG;
	private int MA_DON_VI_QUAN_LY;
	private PHONGBAN Donvi_Sudung;
	private PHONGBAN Donvi_Quanly;
	private ArrayList<PHUKIEN> PhukienList;
	private PHUONGTIEN_GIAOTHONG Phuongtien_Giaothong;

	public int getMA_TAISAN() {
		return MA_TAISAN;
	}

	public void setMA_TAISAN(int mA_TAISAN) {
		MA_TAISAN = mA_TAISAN;
	}

	public String getTEN_TAISAN() {
		return TEN_TAISAN;
	}

	public void setTEN_TAISAN(String tEN_TAISAN) {
		TEN_TAISAN = tEN_TAISAN;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}

	public String getSERI() {
		return SERI;
	}

	public void setSERI(String sERI) {
		SERI = sERI;
	}

	public Date getNGAY_SU_DUNG() {
		return NGAY_SU_DUNG;
	}

	public void setNGAY_SU_DUNG(Date nGAY_SU_DUNG) {
		NGAY_SU_DUNG = nGAY_SU_DUNG;
	}

	public String getXUAT_XU() {
		return XUAT_XU;
	}

	public void setXUAT_XU(String xUAT_XU) {
		XUAT_XU = xUAT_XU;
	}

	public String getBAO_HANH() {
		return BAO_HANH;
	}

	public void setBAO_HANH(String bAO_HANH) {
		BAO_HANH = bAO_HANH;
	}

	public int getTINH_TRANG() {
		return TINH_TRANG;
	}

	public void setTINH_TRANG(int tINH_TRANG) {
		TINH_TRANG = tINH_TRANG;
	}

	public int getTRANG_THAI() {
		return TRANG_THAI;
	}

	public void setTRANG_THAI(int tRANG_THAI) {
		TRANG_THAI = tRANG_THAI;
	}

	public String getDON_VI_TINH() {
		return DON_VI_TINH;
	}

	public void setDON_VI_TINH(String dON_VI_TINH) {
		DON_VI_TINH = dON_VI_TINH;
	}

	public int getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(int l) {
		SOLUONG = l;
	}

	public long getNGUYEN_GIA() {
		return NGUYEN_GIA;
	}

	public void setNGUYEN_GIA(long l) {
		NGUYEN_GIA = l;
	}

	public String getGHI_CHU() {
		return GHI_CHU;
	}

	public void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
	}

	public String getMA_TANSAN_KETOAN() {
		return MA_TANSAN_KETOAN;
	}

	public void setMA_TANSAN_KETOAN(String mA_TANSAN_KETOAN) {
		MA_TANSAN_KETOAN = mA_TANSAN_KETOAN;
	}

	public int getMA_NHOMTAISAN_CAP_V() {
		return MA_NHOMTAISAN_CAP_V;
	}

	public void setMA_NHOMTAISAN_CAP_V(int mA_NHOMTAISAN_CAP_V) {
		MA_NHOMTAISAN_CAP_V = mA_NHOMTAISAN_CAP_V;
	}

	public int getMA_LOAITAISAN_CAP_III() {
		return MA_LOAITAISAN_CAP_III;
	}

	public void setMA_LOAITAISAN_CAP_III(int mA_LOAITAISAN_CAP_III) {
		MA_LOAITAISAN_CAP_III = mA_LOAITAISAN_CAP_III;
	}

	public int getMA_DON_VI_SU_DUNG() {
		return MA_DON_VI_SU_DUNG;
	}

	public void setMA_DON_VI_SU_DUNG(int mA_DON_VI_SU_DUNG) {
		MA_DON_VI_SU_DUNG = mA_DON_VI_SU_DUNG;
	}

	public int getMA_DON_VI_QUAN_LY() {
		return MA_DON_VI_QUAN_LY;
	}

	public void setMA_DON_VI_QUAN_LY(int mA_DON_VI_QUAN_LY) {
		MA_DON_VI_QUAN_LY = mA_DON_VI_QUAN_LY;
	}

	public PHONGBAN getDonvi_Sudung() {
		return Donvi_Sudung;
	}

	public void setDonvi_Sudung(PHONGBAN donvi_Sudung) {
		Donvi_Sudung = donvi_Sudung;
	}

	public PHONGBAN getDonvi_Quanly() {
		return Donvi_Quanly;
	}

	public void setDonvi_Quanly(PHONGBAN donvi_Quanly) {
		Donvi_Quanly = donvi_Quanly;
	}

	public ArrayList<PHUKIEN> getPhukienList() {
		return PhukienList;
	}

	public void setPhukienList(ArrayList<PHUKIEN> phukienList) {
		PhukienList = phukienList;
	}

	public PHUONGTIEN_GIAOTHONG getPhuongtien_Giaothong() {
		return Phuongtien_Giaothong;
	}

	public void setPhuongtien_Giaothong(PHUONGTIEN_GIAOTHONG phuongtien_Giaothong) {
		Phuongtien_Giaothong = phuongtien_Giaothong;
	}

}
