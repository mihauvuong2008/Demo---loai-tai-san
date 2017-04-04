package View.AssetManagers.CongViec.Chart.XYLINEChart;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;

public class TIMELINE_CONGVIEC {
	private int LoaiCongviec;
	private String TenCongviec;
	private TIMELINE_DEXUAT DEXUAT;
	private TIMELINE_THUCHIEN THUCHIEN;
	private TIMELINE_NGHIEMTHU NGHIEMTHU;
	private TIMELINE_QUYETTOAN QUYETTOAN;

	public int getLoaiCongviec() {
		return LoaiCongviec;
	}

	public void setLoaiCongviec(Object CONGVIEC) {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			LoaiCongviec = 1;
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			LoaiCongviec = 2;
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			LoaiCongviec = 3;
		} else {
			LoaiCongviec = 0;
		}
	}

	public String getTenCongviec() {
		return TenCongviec;
	}

	public void setTenCongviec(String tenCongviec) {
		TenCongviec = tenCongviec;
	}

	public TIMELINE_DEXUAT getDEXUAT() {
		return DEXUAT;
	}

	public void setDEXUAT(TIMELINE_DEXUAT dEXUAT) {
		DEXUAT = dEXUAT;
	}

	public TIMELINE_THUCHIEN getTHUCHIEN() {
		return THUCHIEN;
	}

	public void setTHUCHIEN(TIMELINE_THUCHIEN tHUCHIEN) {
		THUCHIEN = tHUCHIEN;
	}

	public TIMELINE_NGHIEMTHU getNGHIEMTHU() {
		return NGHIEMTHU;
	}

	public void setNGHIEMTHU(TIMELINE_NGHIEMTHU nGHIEMTHU) {
		NGHIEMTHU = nGHIEMTHU;
	}

	public TIMELINE_QUYETTOAN getQUYETTOAN() {
		return QUYETTOAN;
	}

	public void setQUYETTOAN(TIMELINE_QUYETTOAN qUYETTOAN) {
		QUYETTOAN = qUYETTOAN;
	}
}
