package Control.NGUONTANG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Control.ControlTool.Control_Tool;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.NGUONTANG;
import DAO.BUILD.OUT.Control_DAO_Build;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_NGUONTANG;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_NGUONTANG;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_NGUONTANG;
import DAO.BUILD.QUERY.UPDATE_LIB.query_Update_NGUONTANG;

public class Control_NGUONTANG {
	private final Connection conn;

	public Control_NGUONTANG(NGUOIDUNG user) {
		conn = user.getConn();
	}

	public NGUONTANG get_NguonTangTaisan(int Ma_NguonTang) throws SQLException {
		return new Select().get_NguonTangTaisan(Ma_NguonTang);
	}

	public ArrayList<NGUONTANG> get_All_NguonTangTaisan(String string) throws SQLException {
		return new Select().get_All_NguonTangTaisan(string);
	}

	public int getNextKey() {
		if (conn != null)
			return new Control_Tool(conn).nextKey_TABLE("NGUONGTANG");
		return -1;
	}

	public Integer Insert_NGUONTANG(NGUONTANG nt) throws SQLException {
		return new Insert().Insert_NGUONTANG(nt);
	}

	public Integer update_NGUONTANG(NGUONTANG nt) throws SQLException {
		return new Update().update_NGUONTANG(nt);
	}

	public boolean deteleNGUONTANG(NGUONTANG nt) throws SQLException {
		return new Delete().deteleNGUONTANG(nt);
	}

	public NGUONTANG get_NguonTangTaisan_FromTaiSan(int ma_TAISAN) throws SQLException {
		return new Select().get_NguonTangTaisan_FromTaiSan(ma_TAISAN);
	}

	public NGUONTANG get_NguonTang(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
		return new Select().get_NguonTang(dtt);
	}

	class Insert {
		public int Insert_NGUONTANG(NGUONTANG nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Insert_NGUONTANG()).getString_ThemNguontang(nt);
				if (query == null)
					return -1;
				int nextkey = getNextKey();
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nextkey;
			}
			return -1;
		}

	}

	class Select {
		public NGUONTANG get_NguonTangTaisan(int Ma_NguonTang) throws SQLException {
			if (conn != null) {
				NGUONTANG nt = null;
				String query = (new query_Select_NGUONTANG()).getString_NguontangTaisan(Ma_NguonTang);
				if (query == null)
					return null;
				nt = new NGUONTANG();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONTANG(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}

		public ArrayList<NGUONTANG> get_All_NguonTangTaisan(String string) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NGUONTANG()).getString_Tatca_Nguongtang(string);
				if (query == null)
					return null;
				ArrayList<NGUONTANG> result = new ArrayList<>();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					NGUONTANG nt = (new Control_DAO_Build()).get_NGUONTANG(rs);
					result.add(nt);
				}
				rs.close();
				st.close();
				return result;
			}
			return null;
		}

		public NGUONTANG get_NguonTangTaisan_FromTaiSan(int ma_TAISAN) throws SQLException {
			if (conn != null) {
				NGUONTANG nt = null;
				String query = (new query_Select_NGUONTANG()).getString_Nguontang_Taisan(ma_TAISAN);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONTANG(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}

		public NGUONTANG get_NguonTang(DOT_THUCHIEN_TANG_TAISAN dtt) throws SQLException {
			if (conn != null) {
				String query = (new query_Select_NGUONTANG()).getString_Nguontang_DottangTaisan(dtt);
				if (query == null)
					return null;
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				NGUONTANG nt = null;
				while (rs.next()) {
					nt = (new Control_DAO_Build()).get_NGUONTANG(rs);
				}
				rs.close();
				st.close();
				return nt;
			}
			return null;
		}
	}

	class Update {
		public Integer update_NGUONTANG(NGUONTANG nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Update_NGUONTANG()).getString_Capnhat_Nguontang(nt);
				if (query == null)
					return null;
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return nt.getMA_NGUONTANG();
			}
			return -1;
		}
	}

	class Delete {
		public boolean deteleNGUONTANG(NGUONTANG nt) throws SQLException {
			if (conn != null) {
				String query = (new query_Delete_NGUONTANG()).getString_XoaNguontang(nt);
				if (query == null)
					return false;
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}
}
