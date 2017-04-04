package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.QUATRINH_DEXUAT_THUCHIEN;
import DAO.QUATRINH_NGHIEMTHU_QUYETTOAN;

public class query_Insert_DOT_THUCHIEN_SUACHUA_BAODUONG {

	public String getString_Insert_Dot_Suachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG dsb, QUATRINH_DEXUAT_THUCHIEN qdt,
			QUATRINH_NGHIEMTHU_QUYETTOAN qnq) {
		try {
			String MA_QUATRINH_DEXUAT_THUCHIEN = (qdt == null) ? ("null")
					: ("'" + qdt.getMA_QUATRINH_DEXUAT_THUCHIEN() + "'");
			String MA_QUATRINH_NGHIEMTHU_QUYETTOAN = (qnq == null) ? ("null")
					: ("'" + qnq.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() + "'");
			String result = "INSERT INTO DOT_THUCHIEN_SUACHUA_BAODUONG "
					+ "( TEN_DOT_THUCHIEN_SUACHUA_BAODUONG ,LOAI_PHUONG_TIEN, SUACHUA_BAODUONG, MO_TA, MA_QUATRINH_DEXUAT_THUCHIEN,"
					+ " MA_QUATRINH_NGHIEMTHU_QUYETTOAN, MA_NGUONSUACHUA_BAODUONG ) VALUES" + "( '"
					+ dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() + "', '" + dsb.getLOAI_PHUONG_TIEN() + "', '"
					+ dsb.getSUACHUA_BAODUONG() + "' , '" + dsb.getMO_TA() + "', " + MA_QUATRINH_DEXUAT_THUCHIEN + ", "
					+ MA_QUATRINH_NGHIEMTHU_QUYETTOAN + ", " + (dsb.getMA_NGUONSUACHUA_BAODUONG() <= 0 ? " null"
							: " '" + dsb.getMA_NGUONSUACHUA_BAODUONG() + "'")
					+ " );";
			return result;
		} catch (Exception e) {
			return null;
		}

	}

}
