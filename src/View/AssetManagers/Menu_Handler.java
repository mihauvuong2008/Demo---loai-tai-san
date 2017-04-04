package View.AssetManagers;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import DAO.NGUOIDUNG;
import View.AssetManagers.CongViec.TangTaiSan._1_TaoDeXuat;
import View.AssetManagers.DanhMuc_LoaiTaisan.QuanlyLoaiTaisan;
import View.AssetManagers.DanhMuc_LoaiTaisan.YesNoBox_ChuyenLoaiTaisan;
import View.AssetManagers.DanhMuc_NhomTaisan.QuanlyNhomtaisan;
import View.AssetManagers.DanhMuc_NhomTaisan.YesNoBox_ChuyenNhomTaisan;
import View.AssetManagers.DanhMuc_Phongban.QuanLy_PhongBan;
import View.AssetManagers.HoatdongNguoidung.HoatDongNguoidung;
import View.AssetManagers.NguonGiam.QuanlyNguonGiam;
import View.AssetManagers.NguonSuachua_Baoduong.Quanly_NguonSuachua_Baoduong;
import View.AssetManagers.NguonTang.QuanLy_NguonTang;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_Loaitaisan;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_NhomTaisan;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_PTGT;
import View.AssetManagers.NhapDulieu_Excel.ImportExcel_PTTS;
import View.AssetManagers.Role.QuanlyQuyenhan;
import View.AssetManagers.Taikhoan.QuanlyTaikhoan;
import View.AssetManagers.Taikhoan.TaikhoanHienhanh;
import View.AssetManagers.Taisan.ChuyenGiaoTaiSanNoibo._1_HinhThuc_Chuyen_DuLieu_Noibo;
import View.AssetManagers.Taisan.Phuongtiengiaothong.Loaixe_DinhmucNhienlieu.Loaixe_DinhmucNhienlieu;

public class Menu_Handler {
	private final NGUOIDUNG user;

	public Menu_Handler(NGUOIDUNG user) {
		this.user = user;
	}

	public void OpenForm_TinhHinhPhuongTienTaiSan() {
		// TODO Auto-generated method stub

	}

	public void Openform_TinhHinhSuaChuaBaoDuong() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_PhanViecDaGiao() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_GiamSatHoatDongNguoiDung() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_DeNghiNghiemVu() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_DanhGiaDuLieuHeThong() {
		// TODO Auto-generated method stub

	}

	public void OpenFrom_TinhHinhCapNhatDuLieu() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_QuanLyDanhMuc(int DANHMUC) {
		// TODO Auto-generated method stub

	}

	public void OpenForm_TangTaiSan() throws SQLException {
		// TODO Auto-generated method stub
		_1_TaoDeXuat dx = new _1_TaoDeXuat(null, user);
		dx.open();
	}

	public void OpenForm_GiamTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_CapNhatThongTinTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_CapNhatHoSoTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_QuanLyHoSoTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_ThemPhanViec() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_DanhSachPhanViec() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_ThongKePhanViec() {
		// TODO Auto-generated method stub

	}

	public void CapNhatHoSoDulieu() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoTangTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoGiamTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoChuyenGiaoTaiSanNoiBo() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoSuaChua_BaoDuong_NangCapTaiSan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoThanhToan() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_TangGanDay() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_GiamGanDay() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_CongViecDangTrienKhai() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_LichSuSuaChuaBaoDuong() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_HoSoCapNhatGanDay() {
		// TODO Auto-generated method stub

	}

	public void OpenForm_TaiKhoanHienHanh() throws SQLException {
		// TODO Auto-generated method stub
		TaikhoanHienhanh th = new TaikhoanHienhanh(null, user, true);
		th.open();
	}

	public void OpenForm_ChuyenGiaoTaiSan_NoiBo(String[] split, Integer MAPHONGBAN) {
		String[] DS_MA_TAISAN = split;
		_1_HinhThuc_Chuyen_DuLieu_Noibo hcd = new _1_HinhThuc_Chuyen_DuLieu_Noibo(null, user, MAPHONGBAN, DS_MA_TAISAN);
		hcd.open();
	}

	public void OpForm_NguonTang() throws SQLException {
		// TODO Auto-generated method stub
		QuanLy_NguonTang q = new QuanLy_NguonTang(null, user);
		q.open();
	}

	public void OpenForm_QuanlyLoaiTaisan(Display display) {
	}

	public void OpenForm_QuanLyNhomTaisan(Display display) {
	}

	public void OpenForm_QuanlyPhannhomTaisan(Display display) {
	}

	public void OpenForm_QuanLyDanhMuc_Donvitinh(Display display) {
		// TODO Auto-generated method stub

	}

	public void OpenForm_QuanLyDanhMuc_Xuatxu(Display display) {
		// TODO Auto-generated method stub

	}

	public void OpenForm_ChuyenNhomTaisan(Display display, String[] MaTaisan, int mA_NhomTaisan) {
		YesNoBox_ChuyenNhomTaisan yncn = new YesNoBox_ChuyenNhomTaisan(display, MaTaisan, mA_NhomTaisan, user);
		yncn.open();
	}

	public void OpenForm_ChuyenLoaiTaisan(Display display, String[] split, int mA_LoaiTaisan) {
		YesNoBox_ChuyenLoaiTaisan yncn = new YesNoBox_ChuyenLoaiTaisan(display, split, mA_LoaiTaisan, user);
		yncn.open();
	}

	public void OpenForm_TaiKhoanCuatoi() throws SQLException {
		TaikhoanHienhanh th = new TaikhoanHienhanh(null, user, false);
		th.open();
	}

	public void OpenForm_Tool_Excel_NhapPTTS(Display display) throws SQLException {
		ImportExcel_PTTS ieptts = new ImportExcel_PTTS(display, user);
		ieptts.open();
	}

	public void OpenForm_Tool_Excel_NhapPhuongtienGiaothong(Display display) throws SQLException {
		ImportExcel_PTGT ie = new ImportExcel_PTGT(display, user);
		ie.open();
	}

	public void OpenForm_Tool_QuanlyLoaiTaisan(Display display) throws SQLException {
		QuanlyLoaiTaisan ql = new QuanlyLoaiTaisan(display, user);
		ql.open();
	}

	public void OpenForm_Tool_QuanLyNhomTaisan(Display display) throws SQLException {
		QuanlyNhomtaisan qn = new QuanlyNhomtaisan(display, user);
		qn.open();
	}

	public void OpenForm_Tool_Excel_Import_NhomTaisan(Display display) {
		ImportExcel_NhomTaisan in = new ImportExcel_NhomTaisan(display, user);
		in.open();
	}

	public void OpenForm_Tool_Excel_Import_LoaiTaisan(Display display) {
		ImportExcel_Loaitaisan il = new ImportExcel_Loaitaisan(display, user);
		il.open();
	}

	public void OpenForm_Tool_QuanLy_DanhMuc_PhongBan(Display display) throws SQLException {
		QuanLy_PhongBan qp = new QuanLy_PhongBan(display, user);
		qp.open();
	}

	public void OpenForm_QuanlyTaikhoanNguoidung(Display display) throws SQLException {
		QuanlyTaikhoan cltk = new QuanlyTaikhoan(display, user);
		cltk.open();

	}

	public void OpenForm_QuanlyQuyenhan(Display display) throws SQLException {
		QuanlyQuyenhan qlqh = new QuanlyQuyenhan(display, user);
		qlqh.open();

	}

	public void OpenForm_DinhmucNhienlieu(Display display) throws SQLException {
		Loaixe_DinhmucNhienlieu dmnl = new Loaixe_DinhmucNhienlieu(display, user);
		dmnl.open();

	}

	public void OpenForm_LogUser(Display display) throws SQLException {
		HoatDongNguoidung hdnd = new HoatDongNguoidung(display.getShells()[0], SWT.DIALOG_TRIM, user);
		hdnd.open();
	}

	public void OpenForm_Danhmuc_Suachua_Baoduong(Shell shell) throws SQLException {
		Quanly_NguonSuachua_Baoduong qsbd = new Quanly_NguonSuachua_Baoduong(shell, SWT.DIALOG_TRIM, user);
		qsbd.open();
	}

	public void OpenForm_Danhmuc_Nguongiam(Shell shell) throws SQLException {
		QuanlyNguonGiam qng = new QuanlyNguonGiam(shell, SWT.DIALOG_TRIM, user);
		qng.open();
	}

}
