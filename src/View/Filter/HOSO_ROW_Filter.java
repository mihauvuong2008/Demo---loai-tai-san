package View.Filter;

import java.sql.SQLException;

import DAO.HOSO_ROW;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;

public class HOSO_ROW_Filter {
	private HOSO_ROW t;
	private String TEN_TAPHOSO = "";// INTEGER(5) NOT NULL,
	private String GIOITHIEU_TAPHOSO = "";// VARCHAR(40) NOT NULL,
	private String NGAY_TAO_TAPHOSO = "";// VARCHAR(40) NOT NULL,
	private String SO_VANBAN = "";// VARCHAR(40) NOT NULL,
	private String NGAY_BAN_HANH = "";// VARCHAR(40) NOT NULL,
	private String CO_QUAN_BAN_HANH = "";// VARCHAR(40) NOT NULL,
	private String TRICH_YEU = "";// VARCHAR(40) NOT NULL,

	double Value = 0;

	public HOSO_ROW_Filter(HOSO_ROW t, String Pattern) throws SQLException {
		this.t = t;
		if (t != null) {
			TEN_TAPHOSO = t.getTEN_TAPHOSO();
			GIOITHIEU_TAPHOSO = t.getGIOITHIEU_TAPHOSO();
			NGAY_TAO_TAPHOSO = new MyDateFormat().getViewStringDate(t.getNGAY_TAO_TAPHOSO());
			if (t.getVbl() != null) {
				for (VANBAN vb : t.getVbl()) {
					SO_VANBAN = SO_VANBAN + " " + vb.getSO_VANBAN();
					NGAY_BAN_HANH = NGAY_BAN_HANH + " " + vb.getNGAY_BAN_HANH();
					CO_QUAN_BAN_HANH = CO_QUAN_BAN_HANH + " " + vb.getCO_QUAN_BAN_HANH();
					TRICH_YEU = TRICH_YEU + " " + vb.getTRICH_YEU();
				}
			}
			SO_VANBAN = SO_VANBAN.trim();
			NGAY_BAN_HANH = NGAY_BAN_HANH.trim();
			CO_QUAN_BAN_HANH = CO_QUAN_BAN_HANH.trim();
			TRICH_YEU = TRICH_YEU.trim();
		}
		Value = new Valuer(getData(), Pattern).getValue();
	}

	public final HOSO_ROW getT() {
		return t;
	}

	String getData() {
		return TEN_TAPHOSO + " " + GIOITHIEU_TAPHOSO + " " + NGAY_TAO_TAPHOSO + " " + SO_VANBAN + " " + NGAY_BAN_HANH
				+ " " + CO_QUAN_BAN_HANH + " " + TRICH_YEU;
	}

	public void setT(HOSO_ROW t) {
		this.t = t;
	}

	public double getValue() {
		return Value;
	}

}
