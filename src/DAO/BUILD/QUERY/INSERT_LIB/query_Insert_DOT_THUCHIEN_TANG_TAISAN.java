package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUONTANG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.QUATRINH_NGHIEMTHU_QUYETTOAN;

public class query_Insert_DOT_THUCHIEN_TANG_TAISAN {

	public String getString_Insert_DotTangTaisan(DOT_THUCHIEN_TANG_TAISAN dt, QUATRINH_DEXUAT_THUCHIEN qdt,
			QUATRINH_NGHIEMTHU_QUYETTOAN qnq, NGUONTANG nt) {
		try {
			String a = (qdt == null) ? ("null") : ("'" + qdt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "'");
			String b = (qnq == null) ? ("null") : ("'" + qnq.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() + "'");
			String c = (nt == null) ? ("null") : ("'" + nt.getMA_NGUONTANG() + "'");
			String result = "INSERT INTO DOT_THUCHIEN_TANG_TAISAN "
					+ "( TEN_DOT_TANG, LY_DO_TANG, MUC_KINH_PHI, MO_TA, MA_QUATRINH_DEXUAT_THUCHIEN,"
					+ " MA_QUATRINH_NGHIEMTHU_QUYETTOAN, MA_NGUONTANG ) VALUES" + "( '" + dt.getTEN_DOT_TANG() + "','"
					+ dt.getLY_DO_TANG() + "','" + dt.getMUC_KINH_PHI() + "' , '" + dt.getMO_TA() + "', " + a.toString()
					+ ", " + b.toString() + "," + c.toString() + " );";
			return result;
		} catch (Exception e) {
			return null;
		}

	}

}
