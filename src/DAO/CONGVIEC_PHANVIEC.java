package DAO;

import java.util.Date;

public class CONGVIEC_PHANVIEC {
	private Object CONGVIEC;
	private Object PHANVIEC;

	public Object getCONGVIEC() {
		return CONGVIEC;
	}

	public void setCONGVIEC(Object cONGVIEC) {
		CONGVIEC = cONGVIEC;
	}

	public Object getPHANVIEC() {
		return PHANVIEC;
	}

	public void setPHANVIEC(Object pHANVIEC) {
		PHANVIEC = pHANVIEC;
	}

	public int getLOAI_CONGVIEC() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return 1;
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return 2;
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return 3;
		} else {
			return 0;
		}
	}

	public int getMA_CONGVIEC() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMA_DOT_THUCHIEN_SUACHUA_BAODUONG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMA_DOT_TANG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getMA_DOT_GIAM();
		} else {
			return 0;
		}
	}

	public String getTEN_CONGVIEC() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getTEN_DOT_TANG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getTEN_DOT_GIAM();
		} else {
			return null;
		}
	}

	public int getLY_DO_TANG() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return 0;
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getLY_DO_TANG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getLY_DO_GIAM();
		} else {
			return 0;
		}
	}

	public int getMUC_KINH_PHI() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMUC_KINH_PHI();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMUC_KINH_PHI();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return 0;
		} else {
			return 0;
		}
	}

	public int getSUACHUA_BAODUONG() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getSUACHUA_BAODUONG();
		} else {
			return 0;
		}
	}

	public String getMO_TA() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMO_TA();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMO_TA();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getMO_TA();
		} else {
			return null;
		}
	}

	public int getMA_QUATRINH_DEXUAT_THUCHIEN() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMA_QUATRINH_DEXUAT_THUCHIEN();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMA_QUATRINH_DEXUAT_THUCHIEN();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getMA_QUATRINH_DEXUAT_THUCHIEN();
		} else {
			return 0;
		}
	}

	public int getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMA_QUATRINH_NGHIEMTHU_QUYETTOAN();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return 0;
		} else {
			return 0;
		}
	}

	public int getMA_NGUON_CONGVIEC() {
		if (CONGVIEC instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			return ((DOT_THUCHIEN_SUACHUA_BAODUONG) CONGVIEC).getMA_NGUONSUACHUA_BAODUONG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_TANG_TAISAN) {
			return ((DOT_THUCHIEN_TANG_TAISAN) CONGVIEC).getMA_NGUONTANG();
		} else if (CONGVIEC instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			return ((DOT_THUCHIEN_GIAM_TAISAN) CONGVIEC).getMA_NGUONGIAM();
		} else {
			return 0;
		}
	}

	public int getLOAI_PHANVIEC() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return 1;
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return 2;
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return 3;
		} else {
			return 0;
		}
	}

	public int getMA_PHANVIEC() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getMA_GIAI_DOAN_THUC_HIEN();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getMA_GIAI_DOAN_NGHIEM_THU();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return ((GIAI_DOAN_QUYET_TOAN) PHANVIEC).getMA_GIAI_DOAN_QUYET_TOAN();
		} else {
			return 0;
		}
	}

	public Date getTHOI_DIEM_BAT_DAU() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getTHOI_DIEM_BAT_DAU();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getTHOI_DIEM_TIEP_NHAN();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return ((GIAI_DOAN_QUYET_TOAN) PHANVIEC).getTHOI_DIEM_TIEP_NHAN();
		} else {
			return null;
		}
	}

	public int getTHOI_GIAN_DU_KIEN_HOAN_THANH() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getTHOI_GIAN_DU_KIEN_HOAN_THANH();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getTHOI_GIAN_DU_KIEN_HOAN_THANH();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return ((GIAI_DOAN_QUYET_TOAN) PHANVIEC).getTHOI_GIAN_DU_KIEN_HOAN_THANH();
		} else {
			return 0;
		}
	}

	public Date getTHOI_DIEM_CHUYEN_GIAO() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getTHOI_DIEM_CHUYEN_GIAO();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getTHOI_DIEM_CHUYEN_GIAO();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return null;
		} else {
			return null;
		}
	}

	public Date getTHOI_DIEM_HOAN_THANH() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getTHOI_DIEM_HOAN_THANH();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getTHOI_DIEM_KET_THUC();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return ((GIAI_DOAN_QUYET_TOAN) PHANVIEC).getTHOI_GIAN_KET_THUC();
		} else {
			return null;
		}
	}

	public String getGHI_CHU() {
		if (PHANVIEC instanceof GIAI_DOAN_THUC_HIEN) {
			return ((GIAI_DOAN_THUC_HIEN) PHANVIEC).getGHI_CHU();
		} else if (PHANVIEC instanceof GIAI_DOAN_NGHIEM_THU) {
			return ((GIAI_DOAN_NGHIEM_THU) PHANVIEC).getGHI_CHU();
		} else if (PHANVIEC instanceof GIAI_DOAN_QUYET_TOAN) {
			return ((GIAI_DOAN_QUYET_TOAN) PHANVIEC).getGHI_CHU();
		} else {
			return null;
		}
	}

}
