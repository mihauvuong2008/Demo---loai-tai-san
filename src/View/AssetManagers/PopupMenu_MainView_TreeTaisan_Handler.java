package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import DAO.TAISAN;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.NGUOIDUNG;
import View.AssetManagers.CongViec.Baoduong.Taodot_Baoduong;
import View.AssetManagers.CongViec.Giamtaisan.TaoDotGiam;
import View.AssetManagers.CongViec.Suachua.Taodot_Suachua;
import View.AssetManagers.CongViec.TangTaiSan.TaoDotTangtaisan;
import View.AssetManagers.Taisan.Phuongtiengiaothong.EditPhuongtienGiaothong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.Phuongtien_Giaothong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong.LichBaoduong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem.LichDangkiem;
import View.AssetManagers.Taisan.SuaThongTinTaisan.Edit_TaiSan;
import View.AssetManagers.Taisan.XemTaiSan.View_Taisan;
import View.AssetManagers.excel_XuatDulieu.ExportExcelData_Phuongtiengiaothong;
import View.AssetManagers.excel_XuatDulieu.ExportExcelData_Taisan;

public class PopupMenu_MainView_TreeTaisan_Handler {

	private final NGUOIDUNG user;
	private Display display;
	private Shell shell;

	public PopupMenu_MainView_TreeTaisan_Handler(Display display, Shell shell, NGUOIDUNG user) {
		this.user = user;
		this.display = display;
		this.shell = shell;
	}

	public void OpenForm_Edit_TaiSan(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		Edit_TaiSan vt = new Edit_TaiSan(display, user, key);
		vt.open();
	}

	public void OpenForm_View_TaiSan(Integer mA_TAISAN) throws SQLException {
		View_Taisan vt = new View_Taisan(display.getShells()[0], SWT.DIALOG_TRIM, user, mA_TAISAN);
		vt.open();
	}

	public void OpenForm_Insert_PHUONGTIEN_GIAOTHONG(TAISAN t) throws SQLException {
		Phuongtien_Giaothong pg = new Phuongtien_Giaothong(shell, SWT.DIALOG_TRIM, user, t);
		pg.open();
	}

	public void OpenForm_Edit_PHUONGTIEN_GIAOTHONG(TAISAN t) throws SQLException {
		EditPhuongtienGiaothong ep = new EditPhuongtienGiaothong(shell, SWT.DIALOG_TRIM, user, t);
		ep.open();
	}

	public void OpenForm_Export_PHUONGTIEN_GIAOTHONG(TAISAN[] items) throws SQLException {
		ExportExcelData_Phuongtiengiaothong ep = new ExportExcelData_Phuongtiengiaothong(shell, SWT.DIALOG_TRIM, user,
				items);
		ep.open();
	}

	public void OpenForm_Export_Taisan(ArrayList<TAISAN> data) throws SQLException {
		ExportExcelData_Taisan et = new ExportExcelData_Taisan(shell, SWT.DIALOG_TRIM, user, data);
		et.open();
	}

	public void OpenForm_HosoMuasam(DOT_THUCHIEN_TANG_TAISAN dtt_last) throws SQLException {
		TaoDotTangtaisan xdt = new TaoDotTangtaisan(shell, SWT.DIALOG_TRIM, user, dtt_last);
		xdt.open();
	}

	public void OpenForm_View_Hoso_Chuyengiao_TaiSan_Noibo(TAISAN t) {

	}

	public void OpenForm_Thanhly_TaiSan_list(ArrayList<TAISAN> taisan_list) throws SQLException {
		TaoDotGiam xdg = new TaoDotGiam(shell, SWT.DIALOG_TRIM, user, taisan_list);
		xdg.open();
	}

	public void OpenForm_Tool_Baoduong_Phuongtien_Giaothong(ArrayList<TAISAN> data) throws SQLException {
		Taodot_Baoduong sb = new Taodot_Baoduong(shell, SWT.DIALOG_TRIM, user, data);
		sb.open();
	}

	public void OpenForm_Tool_Suachua_Taisan(ArrayList<TAISAN> data) throws SQLException {
		Taodot_Suachua suachua = new Taodot_Suachua(shell, SWT.DIALOG_TRIM, user, data);
		suachua.open();
		// TODO Auto-generated method stub

	}

	public void OpenForm_LichBaoduong_PHUONGTIEN_GIAOTHONG(int Oto_xemay) throws SQLException {
		LichBaoduong lbd = new LichBaoduong(shell, SWT.DIALOG_TRIM, user);
		lbd.open();
	}

	public void OpenForm_LichDangKiem_PhuongtienGiaothong(int Oto_Xemay) throws SQLException {
		LichDangkiem ldk = new LichDangkiem(shell, SWT.DIALOG_TRIM, user);
		ldk.open();
	}

}
