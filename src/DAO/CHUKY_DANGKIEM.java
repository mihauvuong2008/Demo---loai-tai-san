package DAO;

public class CHUKY_DANGKIEM {
	private int MA_KYHAN_DANGKIEM;// INTEGER(10) NOT NULL AUTO_INCREMENT,
	private String TEN_KYHAN;// VARCHAR(100),
	private int CHU_KY_DAU;// INTEGER,
	private int CHU_KY;// INTEGER,

	public final int getMA_KYHAN_DANGKIEM() {
		return MA_KYHAN_DANGKIEM;
	}

	public final void setMA_KYHAN_DANGKIEM(int mA_KYHAN_DANGKIEM) {
		MA_KYHAN_DANGKIEM = mA_KYHAN_DANGKIEM;
	}

	public final String getTEN_KYHAN() {
		return TEN_KYHAN;
	}

	public final void setTEN_KYHAN(String tEN_KYHAN) {
		TEN_KYHAN = tEN_KYHAN;
	}

	public final int getCHU_KY_DAU() {
		return CHU_KY_DAU;
	}

	public final void setCHU_KY_DAU(int cHU_KY_DAU) {
		CHU_KY_DAU = cHU_KY_DAU;
	}

	public final int getCHU_KY() {
		return CHU_KY;
	}

	public final void setCHU_KY(int cHU_KY) {
		CHU_KY = cHU_KY;
	}

}
