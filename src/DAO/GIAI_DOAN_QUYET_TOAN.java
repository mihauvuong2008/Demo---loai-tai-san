package DAO;

import java.util.Date;

public class GIAI_DOAN_QUYET_TOAN {
	private int MA_GIAI_DOAN_QUYET_TOAN;
	private Date THOI_DIEM_TIEP_NHAN;
	private int THOI_GIAN_DU_KIEN_HOAN_THANH;
	private Date THOI_GIAN_KET_THUC;
	private String GHI_CHU;
	private int MA_QUATRINH_NGHIEMTHU_QUYETTOAN;

	public int getMA_GIAI_DOAN_QUYET_TOAN() {
		return MA_GIAI_DOAN_QUYET_TOAN;
	}

	public void setMA_GIAI_DOAN_QUYET_TOAN(int mA_GIAI_DOAN_QUYET_TOAN) {
		MA_GIAI_DOAN_QUYET_TOAN = mA_GIAI_DOAN_QUYET_TOAN;
	}

	public Date getTHOI_DIEM_TIEP_NHAN() {
		return THOI_DIEM_TIEP_NHAN;
	}

	public void setTHOI_DIEM_TIEP_NHAN(Date tHOI_DIEM_TIEP_NHAN) {
		THOI_DIEM_TIEP_NHAN = tHOI_DIEM_TIEP_NHAN;
	}

	public int getTHOI_GIAN_DU_KIEN_HOAN_THANH() {
		return THOI_GIAN_DU_KIEN_HOAN_THANH;
	}

	public void setTHOI_GIAN_DU_KIEN_HOAN_THANH(int tHOI_GIAN_DU_KIEN_HOAN_THANH) {
		THOI_GIAN_DU_KIEN_HOAN_THANH = tHOI_GIAN_DU_KIEN_HOAN_THANH;
	}

	public Date getTHOI_GIAN_KET_THUC() {
		return THOI_GIAN_KET_THUC;
	}

	public void setTHOI_GIAN_KET_THUC(Date tHOI_GIAN_KET_THUC) {
		THOI_GIAN_KET_THUC = tHOI_GIAN_KET_THUC;
	}

	public String getGHI_CHU() {
		return GHI_CHU;
	}

	public void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
	}

	public int getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() {
		return MA_QUATRINH_NGHIEMTHU_QUYETTOAN;
	}

	public void setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(int mA_QUATRINH_NGHIEMTHU_QUYETTOAN) {
		MA_QUATRINH_NGHIEMTHU_QUYETTOAN = mA_QUATRINH_NGHIEMTHU_QUYETTOAN;
	}

}
