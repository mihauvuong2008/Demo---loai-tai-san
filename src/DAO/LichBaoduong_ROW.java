package DAO;

public class LichBaoduong_ROW {
	TAISAN taisan;
	PHUONGTIEN_GIAOTHONG phuongtienGiaothong;
	LOAI_XE loaixe;
	DOT_THUCHIEN_SUACHUA_BAODUONG dotBaoduong;
	Kyhan_Baoduong htbd;

	public final TAISAN getTaisan() {
		return taisan;
	}

	public final void setTaisan(TAISAN taisan) {
		this.taisan = taisan;
	}

	public final PHUONGTIEN_GIAOTHONG getPhuongtienGiaothong() {
		return phuongtienGiaothong;
	}

	public final void setPhuongtienGiaothong(PHUONGTIEN_GIAOTHONG phuongtienGiaothong) {
		this.phuongtienGiaothong = phuongtienGiaothong;
	}

	public final LOAI_XE getLoaixe() {
		return loaixe;
	}

	public final void setLoaixe(LOAI_XE loaixe) {
		this.loaixe = loaixe;
	}

	public final DOT_THUCHIEN_SUACHUA_BAODUONG getDotBaoduong() {
		return dotBaoduong;
	}

	public final void setDotBaoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dotBaoduong) {
		this.dotBaoduong = dotBaoduong;
	}

	public final Kyhan_Baoduong getHtbd() {
		return htbd;
	}

	public final void setHtbd(Kyhan_Baoduong htbd) {
		this.htbd = htbd;
	}
}
