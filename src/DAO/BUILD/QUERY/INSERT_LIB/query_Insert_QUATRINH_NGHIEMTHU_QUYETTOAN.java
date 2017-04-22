package DAO.BUILD.QUERY.INSERT_LIB;

public class query_Insert_QUATRINH_NGHIEMTHU_QUYETTOAN {

	public String getString_ThemQuatrinhNghiemthuQuyettoan(int loaicongviec) {
		try {
			return "INSERT INTO QUATRINH_NGHIEMTHU_QUYETTOAN (LOAI_CONGVIEC) VALUES( '" + loaicongviec + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
