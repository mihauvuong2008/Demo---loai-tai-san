package Control.ControlTool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Control_Tool {

	private Connection conn;

	public Control_Tool(Connection conn) {
		this.conn = conn;
	}

	public int nextKey_TABLE(String TABLE_NAME) {
		String query = "SHOW TABLE STATUS WHERE Name = '" + TABLE_NAME + "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			int nextid = 1;
			while (rs.next()) {
				nextid = rs.getInt("Auto_increment");
			}
			rs.close();
			st.close();
			return nextid;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
