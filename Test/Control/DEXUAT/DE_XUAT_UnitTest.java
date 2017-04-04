package Control.DEXUAT;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;
import Connectionfortesting.CONNECTION;
import DAO.DE_XUAT;
import DAO.NGUOIDUNG;

public class DE_XUAT_UnitTest {
	NGUOIDUNG user = new CONNECTION().getUser();

	@Test
	public void TestCase1() {
		DAO.DE_XUAT dx = null;
		Control_DEXUAT cd = new Control_DEXUAT(user);
		int rs = 0;
		try {
			rs = cd.insert_DEXUAT(dx);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1048) {
				System.out.println("Kiểm tra thông số Đề xuất nhập vào");
			}
			System.out.println(e.getErrorCode());
		}
		assertNotNull(rs);
	}

	@Test
	public void TestCase2() {
		DAO.DE_XUAT dx = new DE_XUAT();
		Control_DEXUAT cd = new Control_DEXUAT(user);
		int rs = 0;
		try {
			rs = cd.insert_DEXUAT(dx);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1048) {
				System.out.println("Kiểm tra thông số Đề xuất nhập vào");
			}
			System.out.println(e.getErrorCode());
		}
		assertNotNull(rs);
	}

}
