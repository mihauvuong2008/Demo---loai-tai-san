package DAO.BUILD.QUERY.INSERT_LIB;

import DAO.HOSO_DANHAN;
import View.DateTime.MyDateFormat;

public class query_Insert_HOSO_DANHAN {
	MyDateFormat mdf = new MyDateFormat();

	public String getString_insert_HOSO_DANHAN(HOSO_DANHAN r) {
		String date = r.getNGAY_NHAN() == null ? " null " : " '" + mdf.getSQLStringDate(r.getNGAY_NHAN()) + "' ";
		return "INSERT INTO HOSO_DANHAN (NGAY_NHAN, TAIKHOAN_NHAN, TAIKHOAN_GUI, MA_TAPHOSO) VALUES (" + date + ", '"
				+ r.getTAIKHOAN_NHAN() + "', '" + r.getTAIKHOAN_GUI() + "', '" + r.getMA_TAPHOSO() + "')";
	}

}
