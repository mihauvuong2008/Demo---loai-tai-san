package View.Filter;

import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;

public class Taisan_Filter {
	TAISAN t;
	String MA_TAISAN = "";
	String TEN_TAISAN = "";
	String MODEL = "";
	String SERI = "";
	String GHI_CHU = "";
	String LOAI_PHUONGTIEN_GIAOTHONG = "";// INTEGER(5) NOT NULL,
	String BIENSO = "";// VARCHAR(40) NOT NULL,
	String SOKHUNG = "";// VARCHAR(40) NOT NULL,
	String SOMAY = "";// VARCHAR(40) NOT NULL,

	double Value = 0;

	public Taisan_Filter(TAISAN t, String Pattern) {
		this.t = t;
		MA_TAISAN = String.valueOf(t.getMA_TAISAN());
		TEN_TAISAN = t.getTEN_TAISAN() == null ? "" : t.getTEN_TAISAN().equals("null") ? "" : t.getTEN_TAISAN();
		MODEL = t.getMODEL() == null ? "" : t.getMODEL().equals("null") ? "" : t.getMODEL();
		SERI = t.getSERI() == null ? "" : t.getSERI().equals("null") ? "" : t.getSERI();
		GHI_CHU = t.getGHI_CHU() == null ? "" : t.getGHI_CHU().equals("null") ? "" : t.getGHI_CHU();
		if (t.getPhuongtien_Giaothong() != null) {
			PHUONGTIEN_GIAOTHONG p = t.getPhuongtien_Giaothong();
			LOAI_PHUONGTIEN_GIAOTHONG = (p.getLOAI_PHUONGTIEN_GIAOTHONG() == 1) ? "O to" : "Xe may";
			BIENSO = p.getBIENSO() == null ? "" : p.getBIENSO();
			SOKHUNG = p.getSOKHUNG() == null ? "" : p.getSOKHUNG();
			SOMAY = p.getSOMAY() == null ? "" : p.getSOMAY();
		}
		Value = new Valuer(getData(), Pattern).getValue();
	}

	String getData() {
		return (MA_TAISAN + " " + TEN_TAISAN + " " + MODEL + " " + SERI + " "
				+ GHI_CHU /* + " " + LOAI_PHUONGTIEN_GIAOTHONG */
				+ " " + BIENSO + " " + SOKHUNG + " " + SOMAY);
	}

	public void setT(TAISAN t) {
		this.t = t;
	}

	public double getValue() {
		return Value;
	}

}
