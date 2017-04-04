package login;

import java.sql.SQLException;

import DAO.NGUOIDUNG;
import View.AssetManagers.MainForm;

public class circumstance {
	private static NGUOIDUNG u;

	public circumstance(NGUOIDUNG u) {
		circumstance.u = u;
	}

	public void ViewFormAfterLogin() throws SQLException {

		int User_Role = get_Role_User(u);
		if (User_Role == 1) {//

			MainForm m = new MainForm(u);
			m.open();
		} else if (User_Role == 2) { // LÃ£nh Ä‘áº¡o Ä�á»™i

		} else if (User_Role == 3) {// VÄƒn ThÆ°

		} else if (User_Role == 4) {// CB quáº£n lÃ½

		} else if (User_Role == 5) {// CB thanh quyáº¿t toÃ¡n

		} else {
			// do nothing
		}
		System.out.println("aa");
	}

	private int get_Role_User(NGUOIDUNG u2) {
		// TODO Auto-generated method stub
		return 1;
	}

}
