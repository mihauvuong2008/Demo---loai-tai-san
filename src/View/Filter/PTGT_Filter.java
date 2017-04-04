package View.Filter;

import java.sql.SQLException;

import Control.PHUONGTIEN_GIAOTHONG.Control_LOAI_XE;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;

public class PTGT_Filter {
	TAISAN t;
	String TEN_PHUONGTIEN_GIAOTHONG = "";// INTEGER(5) NOT NULL,
	String BIENSO = "";// VARCHAR(40) NOT NULL,
	String SOKHUNG = "";// VARCHAR(40) NOT NULL,
	String SOMAY = "";// VARCHAR(40) NOT NULL,
	String LOAIXE = "";// VARCHAR(40) NOT NULL,

	double Value = 0;

	public PTGT_Filter(TAISAN t, String Pattern, NGUOIDUNG user, boolean tenphuongtien, boolean bienso2, boolean dongxe,
			boolean sokhung2, boolean somay2) throws SQLException {
		this.t = t;
		if (t.getPhuongtien_Giaothong() != null) {
			PHUONGTIEN_GIAOTHONG p = t.getPhuongtien_Giaothong();
			TEN_PHUONGTIEN_GIAOTHONG = t.getTEN_TAISAN() + " " + p.getTEN_PHUONGTIEN_GIAOTHONG();
			BIENSO = p.getBIENSO() == null ? "" : p.getBIENSO();
			SOKHUNG = p.getSOKHUNG() == null ? "" : p.getSOKHUNG();
			SOMAY = p.getSOMAY() == null ? "" : p.getSOMAY();
			LOAI_XE lx = new Control_LOAI_XE(user).get_LOAI_XE(p.getMA_LOAI_XE());
			LOAIXE = lx.getHANG_SAN_XUAT() + " " + lx.getTEN_DONG_XE();
		}
		Value = new Valuer(getData(tenphuongtien, bienso2, dongxe, sokhung2, somay2), Pattern).getValue();
	}

	String getData(boolean tenphuongtien, boolean bienso2, boolean dongxe, boolean sokhung2, boolean somay2) {
		return ((tenphuongtien ? TEN_PHUONGTIEN_GIAOTHONG
				: "")/*
						 * + " " + LOAI_PHUONGTIEN_GIAOTHONG
						 */
				+ " " + (bienso2 ? BIENSO : "") + " " + (sokhung2 ? SOKHUNG : "") + " " + (somay2 ? SOMAY : "") + " "
				+ (dongxe ? LOAIXE : ""));
	}

	public void setT(TAISAN t) {
		this.t = t;
	}

	public double getValue() {
		return Value;
	}

}
