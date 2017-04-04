package Control.DATETIME_SERVER;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import DAO.NGUOIDUNG;

public class Control_DATETIME_FROM_SERVER {

	private NGUOIDUNG user;
	private Connection conn;

	public Control_DATETIME_FROM_SERVER(NGUOIDUNG user) {
		this.user = user;
		conn = user.getConn();
	}

	public Date get_CURRENT_DATETIME() {
		if (user != null) {
			String query = "SELECT NOW() FROM DUAL;";
			Statement st;
			ResultSet rs;
			Date tb = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					tb = rs.getTimestamp("NOW()");
				}
				rs.close();
				st.close();
				return tb;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
