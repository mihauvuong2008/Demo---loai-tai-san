package Control.ROLE;

import java.sql.SQLException;

import Controler.Controler;
import DAO.NGUOIDUNG;
import View.AssetManagers.AppMessage.DefaultBoxMessage;

public class PrivilegeChecker {
	private final NGUOIDUNG user;
	private final Controler controler;
	private QUANLY_NGUOIDUNG QUANLY_NGUOIDUNG;
	private QUANLY_THONGTIN_TAISAN QUANLY_THONGTIN_TAISAN;
	private QUANLY_CONGVIEC QUANLY_CONGVIEC;
	private QUANLY_HOSO QUANLY_HOSO;

	public PrivilegeChecker(NGUOIDUNG user) {
		this.user = user;
		controler = new Controler(user);
	}

	public final QUANLY_NGUOIDUNG getPrivilegeQUANLY_NGUOIDUNG() {
		if (QUANLY_NGUOIDUNG == null)
			QUANLY_NGUOIDUNG = new QUANLY_NGUOIDUNG();
		return QUANLY_NGUOIDUNG;
	}

	public final QUANLY_THONGTIN_TAISAN getPrivilegeQUANLY_THONGTIN_TAISAN() {
		if (QUANLY_THONGTIN_TAISAN == null)
			QUANLY_THONGTIN_TAISAN = new QUANLY_THONGTIN_TAISAN();
		return QUANLY_THONGTIN_TAISAN;
	}

	public final QUANLY_CONGVIEC getPrivilegeQUANLY_CONGVIEC() {
		if (QUANLY_CONGVIEC == null)
			QUANLY_CONGVIEC = new QUANLY_CONGVIEC();
		return QUANLY_CONGVIEC;
	}

	public final QUANLY_HOSO getPrivilegeQUANLY_HOSO() {
		if (QUANLY_HOSO == null)
			QUANLY_HOSO = new QUANLY_HOSO();
		return QUANLY_HOSO;
	}

	public class QUANLY_NGUOIDUNG {
		boolean showMess1 = true;
		boolean showMess2 = true;
		boolean showMess3 = true;
		boolean showMess4 = true;

		public boolean getINSERT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getTHEM_NGUOIDUNG() == 0 ? false : true)) {
				return true;
			}
			if (showMess1) {
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Thêm dữ liệu - Người dùng - Role");
			}
			showMess1 = false;
			return false;
		}

		public boolean getSELECT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXEM_THONGTIN_NGUOIDUNG() == 0 ? false : true)) {
				return true;
			}
			if (showMess2)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Truy cập dữ liệu - Người dùng - Role");
			showMess2 = false;
			return false;
		}

		public boolean getUPDATE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getPHAN_QUYEN_NGUOIDUNG() == 0 ? false : true)) {
				return true;
			}
			if (showMess3)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Thay đổi dữ liệu - Người dùng - Role");
			showMess3 = false;
			return false;
		}

		public boolean getDELETE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXOA_NGUOIDUNG() == 0 ? false : true)) {
				return true;
			}
			if (showMess4)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Xóa dữ liệu - Người dùng - Role");
			showMess4 = false;
			return false;
		}
	}

	public class QUANLY_THONGTIN_TAISAN {
		boolean showMess1 = true;
		boolean showMess2 = true;
		boolean showMess3 = true;
		boolean showMess4 = true;

		public boolean getINSERT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getTHEM_THONGTIN_TAISAN() == 0 ? false : true)) {
				return true;
			}
			if (showMess1)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Thêm dữ liệu - Phương tiện tài sản");
			showMess1 = false;
			return false;
		}

		public boolean getSELECT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXEM_THONGTIN_TAISAN() == 0 ? false : true)) {
				return true;
			}
			if (showMess2)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Truy cập dữ liệu - Phương tiện tài sản");
			showMess2 = false;
			return false;
		}

		public boolean getUPDATE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getSUA_THONGTIN_TAISAN() == 0 ? false : true)) {
				return true;
			}
			if (showMess3)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Cập nhật dữ liệu - Phương tiện tài sản");
			showMess3 = false;
			return false;
		}

		public boolean getDELETE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXOA_THONGTIN_TAISAN() == 0 ? false : true)) {
				return true;
			}
			if (showMess4)
				new DefaultBoxMessage().Notification("Giới hạn quyền",
						"Giới hạn Quyền Xóa dữ liệu - Phương tiện tài sản");
			showMess4 = false;
			return false;
		}
	}

	public class QUANLY_CONGVIEC {
		boolean showMess1 = true;
		boolean showMess2 = true;
		boolean showMess3 = true;
		boolean showMess4 = true;

		public boolean getINSERT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getTHEM_CONGVIEC() == 0 ? false : true)) {
				return true;
			}
			if (showMess1)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Thêm dữ liệu - Công việc");
			showMess1 = false;
			return false;
		}

		public boolean getSELECT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXEM_THONGTIN_CONGVIEC() == 0 ? false : true)) {
				return true;
			}
			if (showMess2)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Truy cập dữ liệu - Công việc");
			showMess2 = false;
			return false;
		}

		public boolean getUPDATE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getSUA_THONGTIN_CONGVIEC() == 0 ? false : true)) {
				return true;
			}
			if (showMess3)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Cập nhật dữ liệu - Công việc");
			showMess3 = false;
			return false;
		}

		public boolean getDELETE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXOA_CONGVIEC() == 0 ? false : true)) {
				return true;
			}
			if (showMess4)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Xóa dữ liệu - Công việc");
			showMess4 = false;
			return false;
		}
	}

	public class QUANLY_HOSO {
		boolean showMess1 = true;
		boolean showMess2 = true;
		boolean showMess3 = true;
		boolean showMess4 = true;

		public boolean getINSERT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getTHEM_HOSO() == 0 ? false : true)) {
				return true;
			}
			if (showMess1)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Thêm dữ liệu - Hồ sơ");
			showMess1 = false;
			return false;
		}

		public boolean getSELECT_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXEM_THONGTIN_HOSO() == 0 ? false : true)) {
				return true;
			}
			if (showMess2)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Truy cập dữ liệu - Hồ sơ");
			showMess2 = false;
			return false;
		}

		public boolean getUPDATE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getSUA_THONGTIN_HOSO() == 0 ? false : true)) {
				return true;
			}
			if (showMess3)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Cập nhật dữ liệu - Hồ sơ");
			showMess3 = false;
			return false;
		}

		public boolean getDELETE_Privilege() throws SQLException {
			if ((controler.getControl_Role_User().getROLE_User(user).getXOA_HOSO() == 0 ? false : true)) {
				return true;
			}
			if (showMess4)
				new DefaultBoxMessage().Notification("Giới hạn quyền", "Giới hạn Quyền Xóa dữ liệu - Hồ sơ");
			showMess4 = false;
			return false;
		}
	}
}
