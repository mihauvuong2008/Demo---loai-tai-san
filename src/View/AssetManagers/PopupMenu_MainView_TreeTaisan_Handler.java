package View.AssetManagers;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import DAO.TAISAN;
import DAO.NGUOIDUNG;
import View.AssetManagers.Taisan.Phuongtiengiaothong.EditPhuongtienGiaothong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.Phuongtien_Giaothong;
import View.AssetManagers.Taisan.SuaThongTinTaisan.Edit_TaiSan;
import View.AssetManagers.Taisan.XemTaiSan.View_Taisan;

public class PopupMenu_MainView_TreeTaisan_Handler {

	private final NGUOIDUNG user;

	public PopupMenu_MainView_TreeTaisan_Handler(NGUOIDUNG user) {
		this.user = user;
	}

	public void OpenForm_Edit_TaiSan(Display display, Integer key) throws SQLException {
		// TODO Auto-generated method stub
		Edit_TaiSan vt = new Edit_TaiSan(display, user, key);
		vt.open();
	}

	public void OpenForm_View_TaiSan(Display display, Integer mA_TAISAN) throws SQLException {
		View_Taisan vt = new View_Taisan(display.getShells()[0], SWT.DIALOG_TRIM, user, mA_TAISAN);
		vt.open();
	}

	public void OpenForm_Insert_PHUONGTIEN_GIAOTHONG(Display display, TAISAN t) throws SQLException {
		Phuongtien_Giaothong pg = new Phuongtien_Giaothong(display, user, t);
		pg.open();
	}

	public void OpenForm_Edit_PHUONGTIEN_GIAOTHONG(Shell shell, TAISAN t) throws SQLException {
		EditPhuongtienGiaothong ep = new EditPhuongtienGiaothong(shell, SWT.DIALOG_TRIM, user, t);
		ep.open();
	}

}
