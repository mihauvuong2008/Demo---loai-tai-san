package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUONGIAM;

public class query_Insert_DOT_THUCHIEN_GIAM_TAISAN {

	public String getString_Insert_DotGiamTaisan_With_NguonGiam(DOT_THUCHIEN_GIAM_TAISAN dg, NGUONGIAM ng) {
		try {
			String maDotgiam = (dg == null) ? ("null") : ("'" + dg.getMA_QUATRINH_DEXUAT_THUCHIEN() + "'");
			String maNguongiam = (ng == null) ? ("null") : ("'" + ng.getMA_NGUONGIAM() + "'");
			String result = "INSERT INTO DOT_THUCHIEN_GIAM_TAISAN "
					+ "(  TEN_DOT_GIAM, LY_DO_GIAM, MO_TA, MA_QUATRINH_DEXUAT_THUCHIEN," + " MA_NGUONGIAM ) VALUES"
					+ "( '" + dg.getTEN_DOT_GIAM() + "','" + dg.getLY_DO_GIAM() + "','" + dg.getMO_TA() + "', "
					+ maDotgiam + ", " + maNguongiam + " );";

			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
