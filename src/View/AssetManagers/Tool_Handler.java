package View.AssetManagers;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Application.Setting.Setting;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import View.AssetManagers.DanhMuc_Lienhe_Dichvu.DanhmucLienhe;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong.TieuchuanBaoduong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem.KyhanDangkiem;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem.LichDangkiem;
import View.AssetManagers.CongViec.Baoduong._1_NhapDeXuat;
import View.AssetManagers.CongViec.Chart.Chart;
import View.AssetManagers.CongViec.CongViecCuatoi.CongViecCuaToi;
import View.AssetManagers.CongViec.CongviecDahoanthanh.QuanLyCongviec;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.CongViec.Giamtaisan._1_Dexuat;
import View.AssetManagers.CongViec.Suachua._1_NhapDeXuat_Suachua;
import View.AssetManagers.CongViec.TangTaiSan._1_TaoDeXuat;
import View.AssetManagers.Hoso.HosoLuutru;
import View.AssetManagers.LenhDieuXe.LenhDieuxe;
import View.AssetManagers.LenhDieuXe.LichsuDieuXe.LichsuDieuXe;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong.LichBaoduong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke.Thongke_PhuongtienGiaothong;
import View.AssetManagers.ThongBao.ThongbaoChuadoc;
import View.AssetManagers.ThuvienDexuat.ThuvienDexuat;
import View.AssetManagers.ThongBao.ThongBao;
import View.AssetManagers.TimKiem.TimKiem;

public class Tool_Handler {

	private final NGUOIDUNG user;

	public Tool_Handler(NGUOIDUNG user) {
		this.user = user;
	}

	public void OpenForm_Tool_TangTaiSan(Display display, Shell mainFormShell) throws SQLException {
		_1_TaoDeXuat td = new _1_TaoDeXuat(display, user);
		td.open();

	}

	public void OpenForm_Tool_QuanlyHoso(Display display) throws SQLException {
		HosoLuutru tchs = new HosoLuutru(display, user);
		tchs.open();
	}

	public void OpenForm_Tool_GiamTaiSan(Display display) throws SQLException {
		_1_Dexuat dg = new _1_Dexuat(display, user);
		dg.open();
	}

	public void OpenForm_Tool_Suachua(Display display) throws SQLException {
		_1_NhapDeXuat_Suachua td = new _1_NhapDeXuat_Suachua(display, user);
		td.open();
	}

	public void OpenForm_Tool_CongViecCuaToi(Display display) throws SQLException {
		CongViecCuaToi cv = new CongViecCuaToi(display, user);
		cv.open();
	}

	public void OpenForm_Tool_CongViec_Dangthuchien() throws SQLException {
		GiaoViec ql = new GiaoViec(user);
		ql.open();
	}

	public void OpenForm_Tool_HopthuLuu(Display display) {
		ThongBao hdnd = new ThongBao(display, user);
		hdnd.open();
	}

	public void OpenForm_Tool_TimKiem(Display display) {
		TimKiem tk = new TimKiem(display);
		tk.open();
	}

	public void OpenForm_Tool_Quanly_CongViec(Display display) throws SQLException {
		QuanLyCongviec qc = new QuanLyCongviec(display, user);
		qc.open();

	}

	public void OpenForm_Tool_LenhDieuXe(Display display) throws SQLException {
		LenhDieuxe l = new LenhDieuxe(display, user, null);
		l.open();
	}

	public void OpenForm_Tool_Lichsu_Dieuxe(Display display) throws SQLException {
		LichsuDieuXe ld = new LichsuDieuXe(display, user);
		ld.open();
	}

	public void OpenForm_Tool_ThongKe_PHUONGTIEN_GIAOTHONG(Display display) throws SQLException {
		Thongke_PhuongtienGiaothong tpg = new Thongke_PhuongtienGiaothong(display, user);
		tpg.open();
	}

	public void OpenForm_Tool_Baoduong_Phuongtien_Giaothong(Display display) throws SQLException {

		_1_NhapDeXuat td = new _1_NhapDeXuat(display, user);
		td.open();
	}

	public void OpenForm_Tool_Thongbao_Chuadoc(Shell shell) throws SQLException {
		ThongbaoChuadoc dnnv = new ThongbaoChuadoc(shell, SWT.NONE, user);
		dnnv.open();
	}

	public void OpenForm_Tool_Chart(Display current) {
		Chart c = new Chart(current.getShells()[0], SWT.DIALOG_TRIM, user);
		c.open();

	}

	public void OpenForm_Tool_LichBaoduong(Shell shell, Display current) throws SQLException {
		LichBaoduong lbd = new LichBaoduong(shell, SWT.DIALOG_TRIM, user);
		lbd.open();

	}

	public void OpenForm_Tool_ThuvienDexuat(Shell shell, Display current) throws SQLException {
		ThuvienDexuat tvdx = new ThuvienDexuat(shell, SWT.DIALOG_TRIM, user);
		tvdx.open();

	}

	public void OpenForm_Tool_Baoduong_Phuongtien_Giaothong(Shell shell) {
		DanhmucLienhe dmlh = new DanhmucLienhe(shell, SWT.DIALOG_TRIM, user);
		dmlh.open();
	}

	public void OpenForm_Tool_TieuchuanBaoduong(Shell shell) throws SQLException {
		TieuchuanBaoduong tcbd = new TieuchuanBaoduong(shell, SWT.DIALOG_TRIM, user);
		tcbd.open();
	}

	public void OpenForm_Tool_Lich_Dang_Kiem(Shell shell) throws SQLException {
		LichDangkiem ldk = new LichDangkiem(shell, SWT.DIALOG_TRIM, user);
		ldk.open();
	}

	public void OpenForm_Tool_Ky_han_Dang_kiem(Shell shell) throws SQLException {
		KyhanDangkiem khdk = new KyhanDangkiem(shell, SWT.DIALOG_TRIM, user);
		khdk.open();
	}

	public void OpenForm_Tool_LenhDieuXe(Display display, PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
		LenhDieuxe l = new LenhDieuxe(display, user, ptgt);
		l.open();
	}

	public void OpenForm_setting(Shell shell) {
		Setting s = new Setting(shell, SWT.DIALOG_TRIM);
		s.open();
	}

}
