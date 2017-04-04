package DAO;

public class Row_PTTSthamgia {
	private PHUONGTIEN_GIAOTHONG ptgt;
	private Hinhthuc_Baoduong htbd;
	private String TEN_TAI_SAN;
	private String NGAY_SU_DUNG;
	private String MA_TAI_SAN;

	public PHUONGTIEN_GIAOTHONG getPtgt() {
		return ptgt;
	}

	public void setPtgt(PHUONGTIEN_GIAOTHONG ptgt) {
		this.ptgt = ptgt;
	}

	public Hinhthuc_Baoduong getHtbd() {
		return htbd;
	}

	public void setHtbd(Hinhthuc_Baoduong htbd) {
		this.htbd = htbd;
	}

	public String getTEN_TAI_SAN() {
		return TEN_TAI_SAN;
	}

	public void setTEN_TAI_SAN(String tEN_TAI_SAN) {
		TEN_TAI_SAN = tEN_TAI_SAN;
	}

	public String getNGAY_SU_DUNG() {
		return NGAY_SU_DUNG;
	}

	public void setNGAY_SU_DUNG(String nGAY_SU_DUNG) {
		NGAY_SU_DUNG = nGAY_SU_DUNG;
	}

	public String getMA_TAI_SAN() {
		return MA_TAI_SAN;
	}

	public void setMA_TAI_SAN(String mA_TAI_SAN) {
		MA_TAI_SAN = mA_TAI_SAN;
	}

}
