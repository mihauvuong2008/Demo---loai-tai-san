package DAO.BUILD.QUERY.INSERT_LIB;

public class query_Insert_QUATRINH_NGHIEMTHU_QUYETTOAN {

	public String getString_ThemQuatrinhNghiemthuQuyettoan(String string) {
		try {
			return "INSERT INTO QUATRINH_NGHIEMTHU_QUYETTOAN (TEN_QUA_TRINH) VALUES( '" + string + "');";
		} catch (Exception e) {
			return null;
		}
	}

}
