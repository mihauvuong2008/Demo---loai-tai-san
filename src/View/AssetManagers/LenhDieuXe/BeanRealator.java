package View.AssetManagers.LenhDieuXe;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Control.NGUOIDUNG.Control_NGUOIDUNG;
import Control.PHUONGTIEN_GIAOTHONG.Control_PHUONGTIEN_GIAOTHONG;
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import DAO.PHUONGTIEN_GIAOTHONG;

public class BeanRealator {
	private String ma_lenh_dieu_xe;// integer (20) not null auto_increment,
	private String bien_so;// varchar (20) not null,
	private String ten_canbo;// varchar(20) not null,
	private String diem_xuatphat;// varchar(200),
	private String diem_den;// varchar(200),
	private String ngay_di;// date ,
	private String ngay_ve;// date,
	private String so_km_hientai;// integer (30) not null,
	private String quang_duong_dukien;// integer (10),
	private String phieu_Nhienlieu_duoccap;// integer (10),
	private String diadiem_giodon;// varchar(200),
	private String noidung_chuyendi;// varchar(400),

	public BeanRealator(LENH_DIEU_XE l, NGUOIDUNG user) throws SQLException {
		if (l != null && user != null) {
			Control_PHUONGTIEN_GIAOTHONG cp = new Control_PHUONGTIEN_GIAOTHONG(user);
			Control_NGUOIDUNG cn = new Control_NGUOIDUNG(user);

			ma_lenh_dieu_xe = String.valueOf(l.getMA_LENH_DIEU_XE());
			PHUONGTIEN_GIAOTHONG p = cp.get_PHUONGTIEN_GIAOTHONG(l.getMA_PHUONGTIEN_GIAOTHONG());
			if (p != null) {
				bien_so = p.getBIENSO();
			} else {
				bien_so = "Trống";
			}
			NGUOIDUNG cb = cn.get_NGUOIDUNG(l.getTEN_TAI_KHOAN());
			if (cb != null) {
				ten_canbo = cb.getTEN_CAN_BO();
			} else {
				ten_canbo = "Trống";
			}
			diem_xuatphat = l.getDIEM_XUATPHAT();
			diem_den = l.getDIEM_DEN();

			String NGAY_DI = new SimpleDateFormat("dd-MM-yyyy").format(l.getNGAY_DI());
			String NGAY_VE = new SimpleDateFormat("dd-MM-yyyy").format(l.getNGAY_VE());
			ngay_di = NGAY_DI;
			ngay_ve = NGAY_VE;
			so_km_hientai = String.valueOf(l.getSO_KM_HIENTAI());
			quang_duong_dukien = String.valueOf(l.getQUANG_DUONG_DUKIEN());
			phieu_Nhienlieu_duoccap = String.valueOf(l.getPHIEU_NHIENLIEU_DUOCCAP());
			diadiem_giodon = l.getDIADIEM_GIODON();
			noidung_chuyendi = l.getNOIDUNG_CHUYENDI();
		}
	}

	public String getMa_lenh_dieu_xe() {
		return ma_lenh_dieu_xe;
	}

	public void setMa_lenh_dieu_xe(String ma_lenh_dieu_xe) {
		this.ma_lenh_dieu_xe = ma_lenh_dieu_xe;
	}

	public String getBien_so() {
		return bien_so;
	}

	public void setBien_so(String bien_so) {
		this.bien_so = bien_so;
	}

	public String getTen_canbo() {
		return ten_canbo;
	}

	public void setTen_canbo(String ten_canbo) {
		this.ten_canbo = ten_canbo;
	}

	public String getDiem_xuatphat() {
		return diem_xuatphat;
	}

	public void setDiem_xuatphat(String diem_xuatphat) {
		this.diem_xuatphat = diem_xuatphat;
	}

	public String getDiem_den() {
		return diem_den;
	}

	public void setDiem_den(String diem_den) {
		this.diem_den = diem_den;
	}

	public String getNgay_di() {
		return ngay_di;
	}

	public void setNgay_di(String ngay_di) {
		this.ngay_di = ngay_di;
	}

	public String getNgay_ve() {
		return ngay_ve;
	}

	public void setNgay_ve(String ngay_ve) {
		this.ngay_ve = ngay_ve;
	}

	public String getSo_km_hientai() {
		return so_km_hientai;
	}

	public void setSo_km_hientai(String so_km_hientai) {
		this.so_km_hientai = so_km_hientai;
	}

	public String getQuang_duong_dukien() {
		return quang_duong_dukien;
	}

	public void setQuang_duong_dukien(String quang_duong_dukien) {
		this.quang_duong_dukien = quang_duong_dukien;
	}

	public String getPhieu_xang_duoccap() {
		return phieu_Nhienlieu_duoccap;
	}

	public void setPhieu_xang_duoccap(String phieu_xang_duoccap) {
		this.phieu_Nhienlieu_duoccap = phieu_xang_duoccap;
	}

	public String getDiadiem_giodon() {
		return diadiem_giodon;
	}

	public void setDiadiem_giodon(String diadiem_giodon) {
		this.diadiem_giodon = diadiem_giodon;
	}

	public String getNoidung_chuyendi() {
		return noidung_chuyendi;
	}

	public void setNoidung_chuyendi(String noidung_chuyendi) {
		this.noidung_chuyendi = noidung_chuyendi;
	}

}
