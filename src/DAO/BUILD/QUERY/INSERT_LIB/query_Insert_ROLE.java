package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.ROLE;

public class query_Insert_ROLE {

	public String getString_ThemROLE(ROLE r) {
		try {
			return "INSERT INTO ROLE (TEN_QUYEN, MO_TA, THEM_NGUOIDUNG,  PHAN_QUYEN_NGUOIDUNG, XEM_THONGTIN_NGUOIDUNG, XOA_NGUOIDUNG, THEM_THONGTIN_TAISAN, XEM_THONGTIN_TAISAN, SUA_THONGTIN_TAISAN, XOA_THONGTIN_TAISAN, THEM_CONGVIEC, XEM_THONGTIN_CONGVIEC, SUA_THONGTIN_CONGVIEC, XOA_CONGVIEC,  THEM_HOSO, XEM_THONGTIN_HOSO,  SUA_THONGTIN_HOSO, XOA_HOSO ) VALUES( '"
					+ r.getTEN_QUYEN() + "', '" + r.getMO_TA() + "', '" + r.getTHEM_NGUOIDUNG() + "', '"
					+ r.getPHAN_QUYEN_NGUOIDUNG() + "', '" + r.getXEM_THONGTIN_NGUOIDUNG() + "', '"
					+ r.getXOA_NGUOIDUNG() + "', '" + r.getTHEM_THONGTIN_TAISAN() + "', '" + r.getXEM_THONGTIN_TAISAN()
					+ "', '" + r.getSUA_THONGTIN_TAISAN() + "', '" + r.getXOA_THONGTIN_TAISAN() + "', '"
					+ r.getTHEM_CONGVIEC() + "', '" + r.getXEM_THONGTIN_CONGVIEC() + "', '"
					+ r.getSUA_THONGTIN_CONGVIEC() + "', '" + r.getXOA_CONGVIEC() + "', '" + r.getTHEM_HOSO() + "', '"
					+ r.getXEM_THONGTIN_HOSO() + "', '" + r.getSUA_THONGTIN_HOSO() + "', '" + r.getXOA_HOSO() + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
