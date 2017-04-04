package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.NGUONSUACHUA_BAODUONG;

public class query_Update_DOT_THUCHIEN_SUACHUA_BAODUONG {

	public String getString_Capnhat_GiaidoanQuyettoan(DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			String result = "UPDATE DOT_THUCHIEN_SUACHUA_BAODUONG  SET MA_QUATRINH_NGHIEMTHU_QUYETTOAN='"
					+ dsb.getMA_QUATRINH_NGHIEMTHU_QUYETTOAN() + "' WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG='"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_DotSuachua_Baoduong(DOT_THUCHIEN_SUACHUA_BAODUONG vIEW_dsb) {
		try {
			String result = "UPDATE DOT_THUCHIEN_SUACHUA_BAODUONG  SET  TEN_DOT_THUCHIEN_SUACHUA_BAODUONG ='"
					+ vIEW_dsb.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() + "', LOAI_PHUONG_TIEN='"
					+ vIEW_dsb.getLOAI_PHUONG_TIEN() + "', MUC_KINH_PHI = '" + vIEW_dsb.getMUC_KINH_PHI() + "', MO_TA='"
					+ vIEW_dsb.getMO_TA() + "' "
					+ (vIEW_dsb.getMA_NGUONSUACHUA_BAODUONG() == 0 ? ""
							: ", MA_NGUONSUACHUA_BAODUONG ='" + vIEW_dsb.getMA_NGUONSUACHUA_BAODUONG() + "'")
					+ " WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG='" + vIEW_dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG()
					+ "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public String getString_Capnhat_NguonSuachua_Baoduong(NGUONSUACHUA_BAODUONG nsb,
			DOT_THUCHIEN_SUACHUA_BAODUONG dsb) {
		try {
			String result = "UPDATE DOT_THUCHIEN_SUACHUA_BAODUONG  SET  MA_NGUONSUACHUA_BAODUONG ='"
					+ nsb.getMA_NGUONSUACHUA_BAODUONG() + "' WHERE MA_DOT_THUCHIEN_SUACHUA_BAODUONG='"
					+ dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + "';";
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
