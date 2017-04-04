package Control.DOT_THUCHIEN_TANG_TAISAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Control.ROLE.PrivilegeChecker;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import DAO.TAISAN;
import DAO.BUILD.QUERY.SELECT_LIB.query_Select_DOT_THUCHIEN_TANG_TAISAN_TAISAN;

public class Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN {
	private Insert inserter;
	private Select selecter;
	private Update updater;
	private Delete deleter;
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

	public Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN(NGUOIDUNG user) {
		this.conn = user.getConn();
		pvc = user.getPrivilegeChecker();
	}

	public boolean set_DOTTANGTAISAN_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt, TAISAN t) throws SQLException {
		return getInserter().set_DOTTANGTAISAN_TAISAN(dt, t);
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
		public boolean set_DOTTANGTAISAN_TAISAN(DOT_THUCHIEN_TANG_TAISAN dt, TAISAN t) throws SQLException {
			if (conn != null && isPrivilegeADD()) {
				String query = (new query_Select_DOT_THUCHIEN_TANG_TAISAN_TAISAN())
						.getString_set_DOTTANGTAISAN_TAISAN(dt, t);
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

	private class Select extends REAactivity {
	}

	private class Update extends EDIactivity {
	}

	private class Delete extends DELactivity {
	}
}
