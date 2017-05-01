package View.AssetManagers.Hoso;

import java.sql.SQLException;

import Controler.Controler;
import DAO.HOSO_TAILEN;
import DAO.NGUOIDUNG;
import DAO.TAPHOSO;

public class TapHoso_Creator {
	NGUOIDUNG user;
	Controler controler;

	public TapHoso_Creator(NGUOIDUNG user) {
		this.user = user;
		controler = new Controler(user);
	}

	public void TailenHosoCuatoi(int key) throws SQLException {
		Controler controler = new Controler(user);
		HOSO_TAILEN hsct = getHOSO_CUATOI(user, key);
		if (key <= 0)
			return;
		controler.getControl_HOSO_CUATOI().insert_HOSO_CUATOI(hsct);
	}

	private HOSO_TAILEN getHOSO_CUATOI(NGUOIDUNG user, int key) {
		HOSO_TAILEN rs = new HOSO_TAILEN();
		rs.setTEN_TAI_KHOAN(user.getTEN_TAI_KHOAN());
		rs.setMA_TAPHOSO(key);
		return rs;
	}

	public TAPHOSO getTaphoso(String tenTaphoso, String Gioithieu) {
		TAPHOSO rs = new TAPHOSO();
		rs.setTEN_TAPHOSO(tenTaphoso);
		rs.setGIOITHIEU_TAPHOSO(Gioithieu);
		rs.setNGAY_TAO_TAPHOSO(controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME());
		return rs;
	}
}
