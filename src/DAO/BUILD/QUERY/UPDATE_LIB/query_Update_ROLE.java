package DAO.BUILD.QUERY.UPDATE_LIB;

import DAO.ROLE;

public class query_Update_ROLE {

	public String getString_Capnhat_ROLE(ROLE r) {

		try {
			return "UPDATE ROLE SET TEN_QUYEN  ='" + r.getTEN_QUYEN() + "', MO_TA='" + r.getMO_TA()
					+ "', THEM_NGUOIDUNG='" + r.getTHEM_NGUOIDUNG() + "', PHAN_QUYEN_NGUOIDUNG='"
					+ r.getPHAN_QUYEN_NGUOIDUNG() + "', XEM_THONGTIN_NGUOIDUNG='" + r.getXEM_THONGTIN_NGUOIDUNG()
					+ "', XOA_NGUOIDUNG='" + r.getXOA_NGUOIDUNG() + "', THEM_THONGTIN_TAISAN='"
					+ r.getTHEM_THONGTIN_TAISAN() + "', XEM_THONGTIN_TAISAN='" + r.getXEM_THONGTIN_TAISAN()
					+ "', SUA_THONGTIN_TAISAN='" + r.getSUA_THONGTIN_TAISAN() + "', XOA_THONGTIN_TAISAN='"
					+ r.getXOA_THONGTIN_TAISAN() + "', THEM_CONGVIEC='" + r.getTHEM_CONGVIEC()
					+ "', XEM_THONGTIN_CONGVIEC='" + r.getXEM_THONGTIN_CONGVIEC() + "', SUA_THONGTIN_CONGVIEC='"
					+ r.getSUA_THONGTIN_CONGVIEC() + "', XOA_CONGVIEC='" + r.getXOA_CONGVIEC() + "', THEM_HOSO='"
					+ r.getTHEM_HOSO() + "', XEM_THONGTIN_HOSO='" + r.getXEM_THONGTIN_HOSO() + "', SUA_THONGTIN_HOSO='"
					+ r.getSUA_THONGTIN_HOSO() + "' , XOA_HOSO='" + r.getXOA_HOSO() + "'  where MA_QUYEN='"
					+ r.getMA_QUYEN() + "'";
		} catch (Exception e) {
			return null;
		}
	}

}
