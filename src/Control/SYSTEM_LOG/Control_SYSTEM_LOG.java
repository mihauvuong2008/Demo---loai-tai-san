package Control.SYSTEM_LOG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Control.ControlTool.Control_Tool;
import Controler.Controler;
import DAO.NGUOIDUNG;
import DAO.SYSTEM_LOG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_SYSTEM_LOG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_SYSTEM_LOG;

public class Control_SYSTEM_LOG {

	private final NGUOIDUNG user;
	private final Connection conn;

	public Control_SYSTEM_LOG(NGUOIDUNG user) {
		this.user = user;
		conn = user.getConn();
	}

	public ArrayList<SYSTEM_LOG> getLOG(Date begin, Date end) throws SQLException {
		return new Select().getLOG(begin, end);
	}

	public int insertLog(String message) throws SQLException {
		return new Insert().insertLog(message);
	}

	public int getNextKey() {
		if (conn != null)
			return (new Control_Tool(conn)).nextKey_TABLE("SYSTEM_LOG");
		return -1;
	}

	class Insert {
		public int insertLog(String message) throws SQLException {
			if (conn != null) {
				String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Controler(user).getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
				int nextKey = getNextKey();
				String query = (new query_Insert_SYSTEM_LOG()).getString_INSERT_INTO_SYSTEM_LOG(user, message, Date);
				if (query == null)
					return -1;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextKey;
			}
			return 0;
		}

	}

	class Select {
		public ArrayList<SYSTEM_LOG> getLOG(Date begin, Date end) throws SQLException {
			if (conn != null) {
				ArrayList<SYSTEM_LOG> result = new ArrayList<>();
				String query = new query_Select_SYSTEM_LOG().getString_All_SYSTEM_LOG(begin, end);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					SYSTEM_LOG sl = (new Control_DAO_Build()).get_SYSTEM_LOG(rs);
					result.add(sl);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}
	}

	class Update {
	}

	class Delete {
	}
}
