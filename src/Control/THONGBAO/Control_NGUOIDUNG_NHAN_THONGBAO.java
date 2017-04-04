package Control.THONGBAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NHAN_THONGBAO;
import DAO.THONGBAO;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUOIDUNG_NHAN_THONGBAO;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUOIDUNG_NHAN_THONGBAO;

public class Control_NGUOIDUNG_NHAN_THONGBAO {
	private final Connection conn;

	public Control_NGUOIDUNG_NHAN_THONGBAO(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public boolean insert_NGUOIDUNG_NHAN_THONGBAO(NGUOIDUNG_NHAN_THONGBAO nntb) throws SQLException {
		return new Insert().insert_NGUOIDUNG_NHAN_THONGBAO(nntb);
	}

	public boolean set_NgayDocThongbao(THONGBAO tb, NGUOIDUNG user2, Date date) throws SQLException {
		return new Update().set_NgayDocThongbao(tb, user2, date);
	}

	class Insert {
		public boolean insert_NGUOIDUNG_NHAN_THONGBAO(NGUOIDUNG_NHAN_THONGBAO nntb) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NGUOIDUNG_NHAN_THONGBAO())
						.getString_Them_Nguoidung_Nhan_Thongbao(nntb);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}

	}

	class Select {
	}

	class Update {
		public boolean set_NgayDocThongbao(THONGBAO tb, NGUOIDUNG user2, Date date) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NGUOIDUNG_NHAN_THONGBAO()).getString_Capnhat_NgaydocThongbao(tb, user2,
						date);
				if (query == null)
					return false;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}

	class Delete {
	}
}
