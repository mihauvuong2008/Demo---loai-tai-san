package View.AssetManagers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import Application.Setting.Setting;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.TAISAN;
import View.AssetManagers.DanhMuc_Lienhe_Dichvu.DanhmucLienhe;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong.TieuchuanBaoduong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem.KyhanDangkiem;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichDangkiem.LichDangkiem;
import View.AssetManagers.CongViec.Baoduong.Taodot_Baoduong;
import View.AssetManagers.CongViec.Chart.Chart;
import View.AssetManagers.CongViec.CongViecCuatoi.CongViecCuaToi;
import View.AssetManagers.CongViec.CongviecDahoanthanh.QuanLyCongviec;
import View.AssetManagers.CongViec.CongviecDangthuchien.GiaoViec;
import View.AssetManagers.CongViec.Giamtaisan.TaoDotGiam;
import View.AssetManagers.CongViec.Suachua.Taodot_Suachua;
import View.AssetManagers.CongViec.TangTaiSan.TaoDotTangtaisan;
import View.AssetManagers.Hoso.HosoLuutru;
import View.AssetManagers.LenhDieuXe.LenhDieuxe;
import View.AssetManagers.LenhDieuXe.LichsuDieuXe.LichsuDieuXe;
import View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo.Xem_Dot_Chuyengiao_Noibo;
import View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong.LichBaoduong;
import View.AssetManagers.Taisan.Phuongtiengiaothong.Thongke.Thongke_PhuongtienGiaothong;
import View.AssetManagers.ThongBao.ThongbaoChuadoc;
import View.AssetManagers.ThuvienDexuat.ThuvienDexuat;
import View.AssetManagers.ThongBao.ThongBao;
import View.AssetManagers.TimKiem.TimKiem;

public class Tool_Handler {

	private final NGUOIDUNG user;
	Display display;
	Shell mainFormShell;

	public Tool_Handler(NGUOIDUNG user, Display display, Shell mainFormShell) {
		this.user = user;
		this.display = display;
		this.mainFormShell = mainFormShell;
	}

	public void OpenForm_Tool_TangTaiSan() throws SQLException {
		// _1_TaoDeXuat td = new _1_TaoDeXuat(display, user);
		// td.open();
		TaoDotTangtaisan xdt = new TaoDotTangtaisan(mainFormShell, SWT.DIALOG_TRIM, user);
		xdt.open();
	}

	public void OpenForm_Tool_QuanlyHoso() throws SQLException {
		HosoLuutru tchs = new HosoLuutru(display, user);
		tchs.open();
	}

	public void OpenForm_Tool_GiamTaiSan() throws SQLException {
		// _1_Dexuat dg = new _1_Dexuat(display, user);
		// dg.open();
		TaoDotGiam xdg = new TaoDotGiam(mainFormShell, SWT.DIALOG_TRIM, user, new ArrayList<>());
		xdg.open();
	}

	public void OpenForm_Tool_Suachua() throws SQLException {
		Taodot_Suachua suachua = new Taodot_Suachua(mainFormShell, SWT.DIALOG_TRIM, user, new ArrayList<>());
		suachua.open();
	}

	public void OpenForm_Tool_CongViecCuaToi() throws SQLException {
		CongViecCuaToi cv = new CongViecCuaToi(display, user);
		cv.open();
	}

	public void OpenForm_Tool_CongViec_Dangthuchien() throws SQLException {
		GiaoViec ql = new GiaoViec(user);
		ql.open();
	}

	public void OpenForm_Tool_HopthuLuu() {
		ThongBao hdnd = new ThongBao(display, user);
		hdnd.open();
	}

	public void OpenForm_Tool_TimKiem() {
		TimKiem tk = new TimKiem(display);
		tk.open();
	}

	public void OpenForm_Tool_Quanly_CongViec() throws SQLException {
		QuanLyCongviec qc = new QuanLyCongviec(display, user);
		qc.open();

	}

	public void OpenForm_Tool_LenhDieuXe() throws SQLException {
		LenhDieuxe l = new LenhDieuxe(mainFormShell, SWT.DIALOG_TRIM, user, null);
		l.open();
	}

	public void OpenForm_Tool_Lichsu_Dieuxe() throws SQLException {
		LichsuDieuXe ld = new LichsuDieuXe(display, user);
		ld.open();
	}

	public void OpenForm_Tool_ThongKe_PHUONGTIEN_GIAOTHONG() throws SQLException {
		Thongke_PhuongtienGiaothong tpg = new Thongke_PhuongtienGiaothong(display, user);
		tpg.open();
	}

	public void OpenForm_Tool_Baoduong_Phuongtien_Giaothong(ArrayList<TAISAN> data) throws SQLException {
		Taodot_Baoduong sb = new Taodot_Baoduong(mainFormShell, SWT.DIALOG_TRIM, user, data);
		sb.open();
	}

	public void OpenForm_Tool_Thongbao_Chuadoc() throws SQLException {
		ThongbaoChuadoc dnnv = new ThongbaoChuadoc(mainFormShell, SWT.NONE, user);
		dnnv.open();
	}

	public void OpenForm_Tool_Chart() {
		Chart c = new Chart(mainFormShell, SWT.DIALOG_TRIM, user);
		c.open();

	}

	public void OpenForm_Tool_LichBaoduong() throws SQLException {
		LichBaoduong lbd = new LichBaoduong(mainFormShell, SWT.DIALOG_TRIM, user);
		lbd.open();

	}

	public void OpenForm_Tool_ThuvienDexuat() throws SQLException {
		ThuvienDexuat tvdx = new ThuvienDexuat(mainFormShell, SWT.DIALOG_TRIM, user);
		tvdx.open();

	}

	public void OpenForm_Tool_Danhmuc_Lienhe() {
		DanhmucLienhe dmlh = new DanhmucLienhe(mainFormShell, SWT.DIALOG_TRIM, user);
		dmlh.open();
	}

	public void OpenForm_Tool_TieuchuanBaoduong() throws SQLException {
		TieuchuanBaoduong tcbd = new TieuchuanBaoduong(mainFormShell, SWT.DIALOG_TRIM, user);
		tcbd.open();
	}

	public void OpenForm_Tool_Lich_Dang_Kiem() throws SQLException {
		LichDangkiem ldk = new LichDangkiem(mainFormShell, SWT.DIALOG_TRIM, user);
		ldk.open();
	}

	public void OpenForm_Tool_Ky_han_Dang_kiem() throws SQLException {
		KyhanDangkiem khdk = new KyhanDangkiem(mainFormShell, SWT.DIALOG_TRIM, user);
		khdk.open();
	}

	public void OpenForm_Tool_LenhDieuXe(PHUONGTIEN_GIAOTHONG ptgt) throws SQLException {
		LenhDieuxe l = new LenhDieuxe(mainFormShell, SWT.DIALOG_TRIM, user, ptgt);
		l.open();
	}

	public void OpenForm_setting() {
		Setting s = new Setting(mainFormShell, SWT.DIALOG_TRIM);
		s.open();
	}

	public void OpenForm_Tool_XemDot_Bangiao_Taisan_Noibo() throws SQLException {
		Xem_Dot_Chuyengiao_Noibo xdcn = new Xem_Dot_Chuyengiao_Noibo(mainFormShell, SWT.DIALOG_TRIM, user);
		xdcn.open();
	}

}
