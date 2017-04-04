package Control.DOT_THUCHIEN_GIAM_TAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ROLE.PrivilegeChecker;
import Control.SYSTEM_LOG.Control_SYSTEM_LOG;
import Control.SYSTEM_LOG.Log_Library;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.NGUOIDUNG;
import DAO.TAISAN;
import DAO.BUILD.QUERY.DELETE_LIB.query_Delete_TAISAN_DOT_THUCHIEN_GIAM_TAISAN;
import DAO.BUILD.QUERY.INSERT_LIB.query_Insert_TAISAN_DOT_THUCHIEN_GIAM_TAISAN;

public class Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
	private Control_SYSTEM_LOG cs;
	private final Connection conn;
	private final PrivilegeChecker pvc;

	public final Insert getInserter() {
		if (inserter == null)
			inserter = new Insert();
		return inserter;
	}

	public final Select getSelecter() {
		if (selecter == null)
			selecter = new Select();
		return selecter;
	}

	public final Update getUpdater() {
		if (updater == null)
			updater = new Update();
		return updater;
	}

	public final Delete getDeleter() {
		if (deleter == null)
			deleter = new Delete();
		return deleter;
	}

	public Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN(NGUOIDUNG user) {
		this.conn = user.getConn();
		cs = new Control_SYSTEM_LOG(user);
		pvc = user.getPrivilegeChecker();
	}

	public boolean set_DOTGIAMTAISAN_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dg, TAISAN t) throws SQLException {
		boolean rs = getInserter().set_DOTGIAMTAISAN_TAISAN(dg, t);
		if (rs)
			cs.insertLog(new Log_Library().getString_CapnhatTaisan_DotThanhlyTaisan(dg, t));
		return rs;
	}

	public boolean delete_TAISAN_DOT_GIAM_TAISAN(TAISAN t, DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
		return getDeleter().delete_TAISAN_DOT_GIAM_TAISAN(t, dgt);
	}

	abstract class ADDactivity {

		public final boolean isPrivilegeADD() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getINSERT_Privilege();
		}
	}

	abstract class REAactivity {

		public final boolean isPrivilegeREA() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getSELECT_Privilege();
		}
	}

	abstract class EDIactivity {

		public final boolean isPrivilegeEDI() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getUPDATE_Privilege();
		}
	}

	abstract class DELactivity {

		public final boolean isPrivilegeDEL() throws SQLException {
			return pvc.getPrivilegeQUANLY_CONGVIEC().getDELETE_Privilege();
		}
	}

	private class Insert extends ADDactivity {
		public boolean set_DOTGIAMTAISAN_TAISAN(DOT_THUCHIEN_GIAM_TAISAN dgt, TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Insert_TAISAN_DOT_THUCHIEN_GIAM_TAISAN()).getString_Them_TAISAN_GIAM_TAISAN(t,
						dgt);
				PreparedStatement prs;
				prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;

			}
			return false;
		}
	}

	private class Select extends REAactivity {
	}

	private class Update extends EDIactivity {
	}

	private class Delete extends DELactivity {
		public boolean delete_TAISAN_DOT_GIAM_TAISAN(TAISAN t, DOT_THUCHIEN_GIAM_TAISAN dgt) throws SQLException {
			if (conn != null && isPrivilegeDEL()) {
				String query = (new query_Delete_TAISAN_DOT_THUCHIEN_GIAM_TAISAN()).getString_Xoa_TAISAN_GIAM_TAISAN(t,
						dgt);
				PreparedStatement prs = conn.prepareStatement(query);
				prs.executeUpdate();
				prs.close();
				return true;
			}
			return false;
		}
	}
}
