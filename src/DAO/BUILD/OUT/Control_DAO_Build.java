package DAO.BUILD.OUT;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CHUKY_DANGKIEM;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_DANGKIEM;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.Hinhthuc_Baoduong;
import DAO.KY_HAN_THONGKE_XANG_DAU;
import DAO.Kyhan_Baoduong;
import DAO.LENH_DIEU_XE;
import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import DAO.LOAI_XE;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.NGUONGIAM;
import DAO.NGUONSUACHUA_BAODUONG;
import DAO.NGUONTANG;
import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import DAO.NHOM_DONVI_THAMGIA_THONGKE;
import DAO.PHONGBAN;
import DAO.PHUKIEN;
import DAO.PHUONGTIEN_GIAOTHONG;
import DAO.ROLE;
import DAO.Row_PTTSthamgia;
import DAO.SYSTEM_LOG;
import DAO.TAISAN;
import DAO.THONGBAO;
import View.DateTime.MyDateFormat;

public class Control_DAO_Build {

	private final MyDateFormat mdf = new MyDateFormat();

	public DE_XUAT get_DE_XUAT(ResultSet rs) throws SQLException {
		DE_XUAT dx = new DE_XUAT();
		dx.setMA_DE_XUAT(rs.getInt("MA_DE_XUAT"));
		dx.setSODEXUAT(rs.getString("SODEXUAT"));
		dx.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
		dx.setNGAYTHANG_VANBAN(rs.getTimestamp("NGAYTHANG_VANBAN"));
		dx.setMA_PHONGBAN(rs.getInt("MA_PHONGBAN"));
		dx.setTHOI_DIEM_BAT_DAU(rs.getTimestamp("THOI_DIEM_BAT_DAU"));
		dx.setTHOI_DIEM_CHUYEN_GIAO(rs.getTimestamp("THOI_DIEM_CHUYEN_GIAO"));
		dx.setTHOI_DIEM_HOAN_THANH(rs.getTimestamp("THOI_DIEM_HOAN_THANH"));
		dx.setGHI_CHU(rs.getString("GHI_CHU"));
		dx.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		return dx;
	}

	public DOT_THUCHIEN_GIAM_TAISAN get_DOT_THUCHIEN_GIAM_TAISAN(ResultSet rs) throws SQLException {
		DOT_THUCHIEN_GIAM_TAISAN dts = new DOT_THUCHIEN_GIAM_TAISAN();
		dts.setMA_DOT_GIAM(rs.getInt("MA_DOT_GIAM"));
		dts.setTEN_DOT_GIAM(rs.getString("TEN_DOT_GIAM"));
		dts.setLY_DO_GIAM(rs.getInt("LY_DO_GIAM"));
		dts.setMO_TA(rs.getString("MO_TA"));
		dts.setMA_NGUONGIAM(rs.getInt("MA_NGUONGIAM"));
		dts.setMA_QUATRINH_DEXUAT_THUCHIEN(rs.getInt("MA_QUATRINH_DEXUAT_THUCHIEN"));
		return dts;
	}

	public DOT_THUCHIEN_SUACHUA_BAODUONG get_DOT_THUCHIEN_SUACHUA_BAODUONG(ResultSet rs) throws SQLException {
		DOT_THUCHIEN_SUACHUA_BAODUONG dsb = new DOT_THUCHIEN_SUACHUA_BAODUONG();
		dsb.setMA_DOT_THUCHIEN_SUACHUA_BAODUONG(rs.getInt("MA_DOT_THUCHIEN_SUACHUA_BAODUONG"));
		dsb.setTEN_DOT_THUCHIEN_SUACHUA_BAODUONG(rs.getString("TEN_DOT_THUCHIEN_SUACHUA_BAODUONG"));
		dsb.setLOAI_PHUONG_TIEN(rs.getInt("LOAI_PHUONG_TIEN"));
		dsb.setMUC_KINH_PHI(rs.getInt("MUC_KINH_PHI"));
		dsb.setSUACHUA_BAODUONG(rs.getInt("SUACHUA_BAODUONG"));
		dsb.setMO_TA(rs.getString("MO_TA"));
		dsb.setMA_QUATRINH_DEXUAT_THUCHIEN(rs.getInt("MA_QUATRINH_DEXUAT_THUCHIEN"));
		dsb.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(rs.getInt("MA_QUATRINH_NGHIEMTHU_QUYETTOAN"));
		dsb.setMA_NGUONSUACHUA_BAODUONG(rs.getInt("MA_NGUONSUACHUA_BAODUONG"));
		return dsb;
	}

	public DOT_THUCHIEN_TANG_TAISAN get_DOT_THUCHIEN_TANG_TAISAN(ResultSet rs) throws SQLException {
		DOT_THUCHIEN_TANG_TAISAN dts = new DOT_THUCHIEN_TANG_TAISAN();
		dts.setMA_DOT_TANG(rs.getInt("MA_DOT_TANG"));
		dts.setTEN_DOT_TANG(rs.getString("TEN_DOT_TANG"));
		dts.setLY_DO_TANG(rs.getInt("LY_DO_TANG"));
		dts.setMUC_KINH_PHI(rs.getInt("MUC_KINH_PHI"));
		dts.setMO_TA(rs.getString("MO_TA"));
		dts.setMA_NGUONTANG(rs.getInt("MA_NGUONTANG"));
		dts.setMA_QUATRINH_DEXUAT_THUCHIEN(rs.getInt("MA_QUATRINH_DEXUAT_THUCHIEN"));
		dts.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(rs.getInt("MA_QUATRINH_NGHIEMTHU_QUYETTOAN"));
		return dts;
	}

	public LENH_DIEU_XE get_LENH_DIEU_XE(ResultSet rs) throws SQLException {
		LENH_DIEU_XE l = new LENH_DIEU_XE();
		l.setMA_LENH_DIEU_XE(rs.getInt("MA_LENH_DIEU_XE"));
		l.setMA_PHUONGTIEN_GIAOTHONG(rs.getString("MA_PHUONGTIEN_GIAOTHONG"));
		l.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		l.setDIEM_XUATPHAT(rs.getString("DIEM_XUATPHAT"));
		l.setDIEM_DEN(rs.getString("DIEM_DEN"));
		l.setNGAY_DI(rs.getTimestamp("NGAY_DI"));
		l.setNGAY_VE(rs.getTimestamp("NGAY_VE"));
		l.setSO_KM_HIENTAI(rs.getInt("SO_KM_HIENTAI"));
		l.setQUANG_DUONG_DUKIEN(rs.getInt("QUANG_DUONG_DUKIEN"));
		l.setPHIEU_NHIENLIEU_DUOCCAP(rs.getInt("PHIEU_NHIENLIEU_DUOCCAP"));
		l.setPHIEU_NHIENLIEU_MUANGOAI(rs.getInt("PHIEU_NHIENLIEU_MUANGOAI"));
		l.setDIADIEM_GIODON(rs.getString("DIADIEM_GIODON"));
		l.setNOIDUNG_CHUYENDI(rs.getString("NOIDUNG_CHUYENDI"));
		l.setNGUOI_TAO_LENH(rs.getString("NGUOI_TAO_LENH"));
		l.setTON_NHIENLIEU_HIENTAI(rs.getInt("TON_NHIENLIEU_HIENTAI"));
		l.setHUY_LENH(rs.getInt("HUY_LENH"));
		return l;
	}

	public NGUOIDUNG_NGHIEMTHU get_NGUOIDUNG_NGHIEMTHU(ResultSet rs) throws SQLException {
		NGUOIDUNG_NGHIEMTHU ndth = new NGUOIDUNG_NGHIEMTHU();
		ndth.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		ndth.setMA_GIAI_DOAN_NGHIEM_THU(rs.getInt("MA_GIAI_DOAN_NGHIEM_THU"));
		ndth.setGIAO_NHANVIEC(rs.getInt("GIAO_NHANVIEC"));
		ndth.setNGAY_THAM_GIA(rs.getTimestamp("NGAY_THAM_GIA"));
		ndth.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
		return ndth;
	}

	public GIAI_DOAN_NGHIEM_THU get_GIAI_DOAN_NGHIEM_THU(ResultSet rs) throws SQLException {
		GIAI_DOAN_NGHIEM_THU gdnt = new GIAI_DOAN_NGHIEM_THU();
		gdnt.setMA_GIAI_DOAN_NGHIEM_THU(rs.getInt("MA_GIAI_DOAN_NGHIEM_THU"));
		gdnt.setTHOI_DIEM_TIEP_NHAN(rs.getTimestamp("THOI_DIEM_TIEP_NHAN"));
		gdnt.setTHOI_GIAN_DU_KIEN_HOAN_THANH(rs.getInt("THOI_GIAN_DU_KIEN_HOAN_THANH"));
		gdnt.setTHOI_DIEM_KET_THUC(rs.getTimestamp("THOI_DIEM_KET_THUC"));
		gdnt.setTHOI_DIEM_CHUYEN_GIAO(rs.getTimestamp("THOI_DIEM_CHUYEN_GIAO"));
		gdnt.setGHI_CHU(rs.getString("GHI_CHU"));
		gdnt.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(rs.getTimestamp("MA_QUATRINH_NGHIEMTHU_QUYETTOAN"));
		return gdnt;
	}

	public NGUOIDUNG get_NGUOIDUNG_TINY(ResultSet rs) throws SQLException {
		NGUOIDUNG user = new NGUOIDUNG();
		user.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		user.setMAT_KHAU(rs.getString("MAT_KHAU"));
		return user;
	}

	public NGUOIDUNG get_NGUOIDUNG(ResultSet rs) throws SQLException {
		NGUOIDUNG nd = new NGUOIDUNG();
		nd.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		nd.setTEN_CAN_BO(rs.getString("TEN_CAN_BO"));
		nd.setGIOI_THIEU(rs.getString("GIOI_THIEU"));
		nd.setMA_PHONGBAN(rs.getInt("MA_PHONGBAN"));
		return nd;
	}

	public NGUOIDUNG get_NGUOIDUNG_With_PASSWORD(ResultSet rs) throws SQLException {
		NGUOIDUNG nd = new NGUOIDUNG();
		nd.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		nd.setMAT_KHAU(rs.getString("MAT_KHAU"));
		nd.setTEN_CAN_BO(rs.getString("TEN_CAN_BO"));
		nd.setGIOI_THIEU(rs.getString("GIOI_THIEU"));
		nd.setMA_PHONGBAN(rs.getInt("MA_PHONGBAN"));
		return nd;
	}

	public ROLE get_ROLE(ResultSet rs_) throws SQLException {
		ROLE quyen = new ROLE();
		quyen.setMA_QUYEN(rs_.getInt("MA_QUYEN"));
		quyen.setTEN_QUYEN(rs_.getString("TEN_QUYEN"));
		quyen.setMO_TA(rs_.getString("MO_TA"));
		quyen.setTHEM_NGUOIDUNG(rs_.getInt("THEM_NGUOIDUNG"));
		quyen.setPHAN_QUYEN_NGUOIDUNG(rs_.getInt("PHAN_QUYEN_NGUOIDUNG"));
		quyen.setXEM_THONGTIN_NGUOIDUNG(rs_.getInt("XEM_THONGTIN_NGUOIDUNG"));
		quyen.setXOA_NGUOIDUNG(rs_.getInt("XOA_NGUOIDUNG"));
		quyen.setTHEM_THONGTIN_TAISAN((rs_.getInt("THEM_THONGTIN_TAISAN")));
		quyen.setXEM_THONGTIN_TAISAN(rs_.getInt("XEM_THONGTIN_TAISAN"));
		quyen.setSUA_THONGTIN_TAISAN(rs_.getInt("SUA_THONGTIN_TAISAN"));
		quyen.setXOA_THONGTIN_TAISAN(rs_.getInt("XOA_THONGTIN_TAISAN"));
		quyen.setTHEM_CONGVIEC(rs_.getInt("THEM_CONGVIEC"));
		quyen.setXEM_THONGTIN_CONGVIEC(rs_.getInt("XEM_THONGTIN_CONGVIEC"));
		quyen.setSUA_THONGTIN_CONGVIEC(rs_.getInt("SUA_THONGTIN_CONGVIEC"));
		quyen.setXOA_CONGVIEC(rs_.getInt("XOA_CONGVIEC"));
		quyen.setTHEM_HOSO(rs_.getInt("THEM_HOSO"));
		quyen.setXEM_THONGTIN_HOSO(rs_.getInt("XEM_THONGTIN_HOSO"));
		quyen.setSUA_THONGTIN_HOSO(rs_.getInt("SUA_THONGTIN_HOSO"));
		quyen.setXOA_HOSO(rs_.getInt("XOA_HOSO"));
		return quyen;
	}

	public NGUONGIAM get_NGUONGIAM(ResultSet rs) throws SQLException {
		NGUONGIAM nt = new NGUONGIAM();
		nt.setMA_NGUONGIAM(rs.getInt("MA_NGUONGIAM"));
		nt.setTEN_NGUONGIAM(rs.getString("TEN_NGUONGIAM"));
		nt.setGIOI_THIEU(rs.getString("GIOI_THIEU"));
		nt.setLIEN_HE(rs.getString("LIEN_HE"));
		// TODO Auto-generated method stub
		return nt;
	}

	public NGUONSUACHUA_BAODUONG get_NGUONSUACHUA_BAODUONG(ResultSet rs) throws SQLException {
		NGUONSUACHUA_BAODUONG nt = new NGUONSUACHUA_BAODUONG();
		nt.setMA_NGUONSUACHUA_BAODUONG(rs.getInt("MA_NGUONSUACHUA_BAODUONG"));
		nt.setTEN_NGUONSUACHUA_BAODUONG(rs.getString("TEN_NGUONSUACHUA_BAODUONG"));
		nt.setGIOI_THIEU(rs.getString("GIOI_THIEU"));
		nt.setLIEN_HE(rs.getString("LIEN_HE"));
		return nt;
	}

	public NGUONTANG get_NGUONTANG(ResultSet rs) throws SQLException {
		NGUONTANG nt = new NGUONTANG();
		nt.setMA_NGUONTANG(rs.getInt("MA_NGUONTANG"));
		nt.setTEN_NGUONTANG(rs.getString("TEN_NGUONTANG"));
		nt.setGIOI_THIEU(rs.getString("GIOI_THIEU"));
		nt.setLIEN_HE(rs.getString("LIEN_HE"));
		return nt;
	}

	public NHOMTAISAN_CAP_I get_NHOMTAISAN_CAP_I(ResultSet rs) throws SQLException {
		NHOMTAISAN_CAP_I lt = new NHOMTAISAN_CAP_I();
		lt.setMA_NHOMTAISAN_CAP_I(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_I")));
		lt.setTEN_NHOMTAISAN_CAP_I(rs.getString("TEN_NHOMTAISAN_CAP_I"));
		return lt;
	}

	public NHOMTAISAN_CAP_II get_NHOMTAISAN_CAP_II(ResultSet rs) throws SQLException {
		NHOMTAISAN_CAP_II lt = new NHOMTAISAN_CAP_II();
		lt.setMA_NHOMTAISAN_CAP_II(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_II")));
		lt.setTEN_NHOMTAISAN_CAP_II(rs.getString("TEN_NHOMTAISAN_CAP_II"));
		lt.setMA_NHOMTAISAN_CAP_I(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_I")));
		return lt;
	}

	public NHOMTAISAN_CAP_III get_NHOMTAISAN_CAP_III(ResultSet rs) throws SQLException {
		NHOMTAISAN_CAP_III lt = new NHOMTAISAN_CAP_III();
		lt.setMA_NHOMTAISAN_CAP_III(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_III")));
		lt.setTEN_NHOMTAISAN_CAP_III(rs.getString("TEN_NHOMTAISAN_CAP_III"));
		lt.setMA_NHOMTAISAN_CAP_II(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_II")));
		return lt;
	}

	public NHOMTAISAN_CAP_IV get_NHOMTAISAN_CAP_IV(ResultSet rs) throws SQLException {
		NHOMTAISAN_CAP_IV lt = new NHOMTAISAN_CAP_IV();
		lt.setMA_NHOMTAISAN_CAP_IV(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_IV")));
		lt.setTEN_NHOMTAISAN_CAP_IV(rs.getString("TEN_NHOMTAISAN_CAP_IV"));
		lt.setMA_NHOMTAISAN_CAP_III(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_III")));
		return lt;
	}

	public NHOMTAISAN_CAP_V get_NHOMTAISAN_CAP_V(ResultSet rs) throws SQLException {
		NHOMTAISAN_CAP_V lt = new NHOMTAISAN_CAP_V();
		lt.setMA_NHOMTAISAN_CAP_V(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_V")));
		lt.setTEN_NHOMTAISAN_CAP_V(rs.getString("TEN_NHOMTAISAN_CAP_V"));
		lt.setMA_NHOMTAISAN_CAP_IV(Integer.valueOf(rs.getInt("MA_NHOMTAISAN_CAP_IV")));
		return lt;
	}

	public PHONGBAN get_PHONGBAN(ResultSet rs) throws SQLException {
		PHONGBAN donvi_Quanly = new PHONGBAN();
		donvi_Quanly.setMA_PHONGBAN(rs.getInt("MA_PHONGBAN"));
		donvi_Quanly.setTEN_PHONGBAN(rs.getString("TEN_PHONGBAN"));
		donvi_Quanly.setGIOI_THIEU_PHONGBAN(rs.getString("GIOI_THIEU_PHONGBAN"));
		return donvi_Quanly;
	}

	public LOAI_XE get_LOAI_XE(ResultSet rs) throws SQLException {
		LOAI_XE p = new LOAI_XE();
		p.setMA_LOAI_XE(rs.getInt("MA_LOAI_XE"));
		p.setTEN_DONG_XE(rs.getString("TEN_DONG_XE"));
		p.setOTO_XEMAY(rs.getInt("OTO_XEMAY"));
		p.setHANG_SAN_XUAT(rs.getString("HANG_SAN_XUAT"));
		p.setDINH_MUC_XANG_DAU(rs.getDouble("DINH_MUC_XANG_DAU"));
		return p;
	}

	public PHUONGTIEN_GIAOTHONG get_PHUONGTIEN_GIAOTHONG(ResultSet rs) throws SQLException {
		PHUONGTIEN_GIAOTHONG result = new PHUONGTIEN_GIAOTHONG();
		result.setMA_PHUONGTIEN_GIAOTHONG(rs.getString("MA_PHUONGTIEN_GIAOTHONG"));
		result.setTEN_PHUONGTIEN_GIAOTHONG(rs.getString("TEN_PHUONGTIEN_GIAOTHONG"));
		result.setLOAI_PHUONGTIEN_GIAOTHONG(rs.getInt("LOAI_PHUONGTIEN_GIAOTHONG"));
		result.setMA_LOAI_XE(rs.getInt("MA_LOAI_XE"));
		result.setBIENSO(rs.getString("BIENSO"));
		result.setSOKHUNG(rs.getString("SOKHUNG"));
		result.setSOMAY(rs.getString("SOMAY"));
		result.setSO_KM_XE(rs.getInt("SO_KM_XE"));
		result.setTHOIHAN_DANGKIEM(rs.getTimestamp("THOIHAN_DANGKIEM"));
		result.setMA_TAISAN(rs.getInt("MA_TAISAN"));
		result.setXANG_DAU(rs.getInt("XANG_DAU"));
		result.setMA_KYHAN_DANGKIEM(rs.getInt("MA_KYHAN_DANGKIEM"));
		return result;
	}

	public NGUOIDUNG_QUYETTOAN get_NGUOIDUNG_QUYETTOAN(ResultSet rs) throws SQLException {
		NGUOIDUNG_QUYETTOAN ndth = new NGUOIDUNG_QUYETTOAN();
		ndth.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		ndth.setMA_GIAI_DOAN_QUYET_TOAN(rs.getInt("MA_GIAI_DOAN_QUYET_TOAN"));
		ndth.setGIAO_NHANVIEC(rs.getInt("GIAO_NHANVIEC"));
		ndth.setNGAY_THAM_GIA(rs.getTimestamp("NGAY_THAM_GIA"));
		ndth.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
		return ndth;
	}

	public GIAI_DOAN_QUYET_TOAN get_GIAI_DOAN_QUYET_TOAN(ResultSet rs) throws SQLException {
		GIAI_DOAN_QUYET_TOAN result = new GIAI_DOAN_QUYET_TOAN();
		result.setMA_GIAI_DOAN_QUYET_TOAN(rs.getInt("MA_GIAI_DOAN_QUYET_TOAN"));
		result.setTHOI_DIEM_TIEP_NHAN(rs.getTimestamp("THOI_DIEM_TIEP_NHAN"));
		result.setTHOI_GIAN_DU_KIEN_HOAN_THANH(rs.getInt("THOI_GIAN_DU_KIEN_HOAN_THANH"));
		result.setTHOI_GIAN_KET_THUC(rs.getTimestamp("THOI_GIAN_KET_THUC"));
		result.setGHI_CHU(rs.getString("GHI_CHU"));
		result.setMA_QUATRINH_NGHIEMTHU_QUYETTOAN(rs.getInt("MA_QUATRINH_NGHIEMTHU_QUYETTOAN"));
		return result;
	}

	public TAISAN get_TAISAN_FLASH_ACCESS_ANDIMPORT_DV_SDUNG(ResultSet rs) throws SQLException {
		TAISAN t = new TAISAN();
		t.setMA_TAISAN(rs.getInt(1));
		t.setTEN_TAISAN((rs.getString(2)));
		t.setMODEL(rs.getString(3));
		t.setSERI(rs.getString(4));
		t.setNGAY_SU_DUNG(rs.getTimestamp(5));
		t.setMA_DON_VI_SU_DUNG(rs.getInt(6));
		PHONGBAN donvi_Sudung = new PHONGBAN();
		donvi_Sudung.setTEN_PHONGBAN(rs.getString(7));
		t.setDonvi_Sudung(donvi_Sudung);
		return t;
	}

	public TAISAN get_TAISAN_FULL(ResultSet rs) throws SQLException {
		TAISAN t = new TAISAN();
		t.setMA_TAISAN(rs.getInt("MA_TAISAN"));
		t.setTEN_TAISAN((rs.getString("TEN_TAISAN")));
		t.setMODEL(rs.getString("MODEL"));
		t.setSERI(rs.getString("Seri"));
		t.setNGAY_SU_DUNG(rs.getTimestamp("Ngay_Su_Dung"));
		t.setXUAT_XU(rs.getString("XUAT_XU"));
		t.setBAO_HANH(rs.getString("BAO_HANH"));
		t.setTINH_TRANG(rs.getInt("TINH_TRANG"));
		t.setTRANG_THAI(rs.getInt("TRANG_THAI"));
		t.setDON_VI_TINH(rs.getString("DON_VI_TINH"));
		t.setNGUYEN_GIA(rs.getLong("NGUYEN_GIA"));
		t.setGHI_CHU(rs.getString("GHI_CHU"));
		t.setMA_TANSAN_KETOAN(rs.getString("MA_TANSAN_KETOAN"));
		t.setMA_NHOMTAISAN_CAP_V(rs.getInt("MA_NHOMTAISAN_CAP_V"));
		t.setMA_LOAITAISAN_CAP_III(rs.getInt("MA_LOAITAISAN_CAP_III"));
		t.setMA_DON_VI_SU_DUNG(rs.getInt("MA_DON_VI_SU_DUNG"));
		t.setMA_DON_VI_QUAN_LY(rs.getInt("MA_DON_VI_QUAN_LY"));
		return t;
	}

	public TAISAN get_TAISAN_TINY(ResultSet rs) throws SQLException {
		TAISAN t = new TAISAN();
		t.setMA_TAISAN(rs.getInt("MA_TAISAN"));
		t.setTEN_TAISAN(rs.getString("TEN_TAISAN"));
		t.setNGAY_SU_DUNG(rs.getTimestamp("NGAY_SU_DUNG"));
		return t;
	}

	public PHUKIEN get_PHUKIEN_FLASH_ACCESS(ResultSet rs2) throws SQLException {
		PHUKIEN pk = new PHUKIEN();
		pk.setMA_PHUKIEN(rs2.getInt(1));
		pk.setTEN_PHUKIEN(rs2.getString(2));
		pk.setMODEL(rs2.getString(3));
		pk.setSERI(rs2.getString(4));
		return pk;
	}

	public PHUKIEN get_PHUKIEN_FULL(ResultSet rs) throws SQLException {
		PHUKIEN p = new PHUKIEN();
		p.setMA_PHUKIEN(rs.getInt("MA_PHUKIEN"));
		p.setTEN_PHUKIEN(rs.getString("TEN_PHUKIEN"));
		p.setMODEL(rs.getString("MODEL"));
		p.setSERI(rs.getString("SERI"));
		p.setNGUYEN_GIA(rs.getInt("NGUYEN_GIA"));
		p.setMA_TAISAN(rs.getInt("MA_TAISAN"));
		return p;
	}

	public THONGBAO get_THONGBAO(ResultSet rs) throws SQLException {
		THONGBAO tb = new THONGBAO();
		tb.setMA_THONGBAO(rs.getInt("THONGBAO.MA_THONGBAO"));
		tb.setLOAI_THONGBAO(rs.getInt("THONGBAO.LOAI_THONGBAO"));
		tb.setTEN_TAI_KHOAN_GUI(rs.getString("THONGBAO.TEN_TAI_KHOAN_GUI"));
		tb.setNGUON_TACDONG(rs.getString("THONGBAO.NGUON_TACDONG"));
		tb.setNGUON_BITACDONG(rs.getString("THONGBAO.NGUON_BITACDONG"));
		tb.setNOIDUNG_THONGBAO(rs.getString("THONGBAO.NOIDUNG_THONGBAO"));
		tb.setNGAY_THONGBAO(rs.getTimestamp("THONGBAO.NGAY_THONGBAO"));
		return tb;
	}

	public NGUOIDUNG_THUCHIEN get_NGUOIDUNG_THUCHIEN(ResultSet rs) throws SQLException {
		NGUOIDUNG_THUCHIEN ndth = new NGUOIDUNG_THUCHIEN();
		ndth.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		ndth.setMA_GIAI_DOAN_THUC_HIEN(rs.getInt("MA_GIAI_DOAN_THUC_HIEN"));
		ndth.setGIAO_NHANVIEC(rs.getInt("GIAO_NHANVIEC"));
		ndth.setNGAY_THAM_GIA(rs.getTimestamp("NGAY_THAM_GIA"));
		ndth.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
		return ndth;
	}

	public GIAI_DOAN_THUC_HIEN get_GIAI_DOAN_THUC_HIEN(ResultSet rs) throws SQLException {
		GIAI_DOAN_THUC_HIEN result = new GIAI_DOAN_THUC_HIEN();
		result.setMA_GIAI_DOAN_THUC_HIEN(rs.getInt("MA_GIAI_DOAN_THUC_HIEN"));
		result.setTHOI_DIEM_BAT_DAU(rs.getTimestamp("THOI_DIEM_BAT_DAU"));
		result.setTHOI_GIAN_DU_KIEN_HOAN_THANH(rs.getInt("THOI_GIAN_DU_KIEN_HOAN_THANH"));
		result.setTHOI_DIEM_HOAN_THANH(rs.getTimestamp("THOI_DIEM_HOAN_THANH"));
		result.setTHOI_DIEM_CHUYEN_GIAO(rs.getTimestamp("THOI_DIEM_CHUYEN_GIAO"));
		result.setGHI_CHU(rs.getString("GHI_CHU"));
		result.setMA_QUATRINH_DEXUAT_THUCHIEN(rs.getInt("MA_QUATRINH_DEXUAT_THUCHIEN"));
		return result;
	}

	public Hinhthuc_Baoduong get_Hinhthuc_Baoduong(ResultSet rs) throws SQLException {
		Hinhthuc_Baoduong htbd = new Hinhthuc_Baoduong();
		htbd.setThayNhot(rs.getBoolean("THAY_NHOT"));
		htbd.setThayLocNhot(rs.getBoolean("THAY_LOC_NHOT"));
		htbd.setThayLocgio(rs.getBoolean("THAY_LOC_GIO"));
		htbd.setThayLocnhienlieu(rs.getBoolean("THAY_LOC_NHIEN_LIEU"));
		htbd.setThayDauphanh_Daulyhop(rs.getBoolean("THAY_DAU_PHANH_DAU_LY_HOP"));
		htbd.setThayDauhopso(rs.getBoolean("THAY_DAU_HOP_SO"));
		htbd.setThayDauVisai(rs.getBoolean("THAY_DAU_VI_SAI"));
		htbd.setThayLocgioGianlanh(rs.getBoolean("THAY_LOC_GIO_GIAN_LANH"));
		htbd.setThayDautroluclai(rs.getBoolean("THAY_DAU_TRO_LUC_LAI"));
		htbd.setBaoduongkhac(rs.getBoolean("BAO_DUONG_KHAC"));
		return htbd;
	}

	public Kyhan_Baoduong get_Kyhan_Baoduong(ResultSet rs) throws SQLException {
		Kyhan_Baoduong htbd = new Kyhan_Baoduong();
		htbd.setThayNhot(rs.getInt("THAY_NHOT"));
		htbd.setThayLocNhot(rs.getInt("THAY_LOC_NHOT"));
		htbd.setThayLocgio(rs.getInt("THAY_LOC_GIO"));
		htbd.setThayLocnhienlieu(rs.getInt("THAY_LOC_NHIEN_LIEU"));
		htbd.setThayDauphanh_Daulyhop(rs.getInt("THAY_DAU_PHANH_DAU_LY_HOP"));
		htbd.setThayDauhopso(rs.getInt("THAY_DAU_HOP_SO"));
		htbd.setThayDauVisai(rs.getInt("THAY_DAU_VI_SAI"));
		htbd.setThayLocgioGianlanh(rs.getInt("THAY_LOC_GIO_GIAN_LANH"));
		htbd.setThayDautroluclai(rs.getInt("THAY_DAU_TRO_LUC_LAI"));
		htbd.setBaoduongkhac(rs.getInt("BAO_DUONG_KHAC"));
		return htbd;
	}

	public Row_PTTSthamgia get_Row_PTTSthamgia(ResultSet rs, PHUONGTIEN_GIAOTHONG p, Hinhthuc_Baoduong htbd)
			throws SQLException {
		Row_PTTSthamgia rp = new Row_PTTSthamgia();
		rp.setPtgt(p);
		rp.setHtbd(htbd);
		rp.setMA_TAI_SAN(String.valueOf(rs.getInt("TAISAN.MA_TAISAN")));
		rp.setTEN_TAI_SAN(rs.getString("TAISAN.TEN_TAISAN"));
		rp.setNGAY_SU_DUNG(mdf.getViewStringDate(rs.getTimestamp("TAISAN.NGAY_SU_DUNG")));
		return rp;
	}

	public KY_HAN_THONGKE_XANG_DAU get_KY_HAN_THONGKE_XANG_DAU(ResultSet rs) throws SQLException {
		KY_HAN_THONGKE_XANG_DAU khxd = new KY_HAN_THONGKE_XANG_DAU();
		khxd.setMA_KYHAN(rs.getInt("MA_KYHAN"));
		khxd.setTEN_KYHAN(rs.getString("TEN_KYHAN"));
		khxd.setNGAY_BAT_DAU(rs.getTimestamp("NGAY_BAT_DAU"));
		khxd.setNGAY_KET_THUC(rs.getTimestamp("NGAY_KET_THUC"));
		khxd.setGHI_CHU(rs.getString("GHI_CHU"));
		return khxd;
	}

	public NHOM_DONVI_THAMGIA_THONGKE get_NHOM_DONVI_THAMGIA_THONGKE(ResultSet rs) throws SQLException {
		NHOM_DONVI_THAMGIA_THONGKE nhom = new NHOM_DONVI_THAMGIA_THONGKE();
		nhom.setMA_NHOM_DONVI_THAMGIA_THONGKE(rs.getInt("MA_NHOM_DONVI_THAMGIA_THONGKE"));
		nhom.setTEN_NHOM_DONVI_THAMGIA_THONGKE(rs.getString("TEN_NHOM_DONVI_THAMGIA_THONGKE"));
		nhom.setMA_KYHAN(rs.getInt("MA_KYHAN"));
		return nhom;
	}

	public LOAITAISAN_CAP_I get_LOAITAISAN_CAP_I(ResultSet rs) throws SQLException {
		LOAITAISAN_CAP_I lt = new LOAITAISAN_CAP_I();
		lt.setMA_LOAITAISAN_CAP_I(Integer.valueOf(rs.getInt("MA_LOAITAISAN_CAP_I")));
		lt.setTEN_LOAITAISAN_CAP_I(rs.getString("TEN_LOAITAISAN_CAP_I"));
		return lt;
	}

	public LOAITAISAN_CAP_II get_LOAITAISAN_CAP_II(ResultSet rs) throws SQLException {
		LOAITAISAN_CAP_II lt = new LOAITAISAN_CAP_II();
		lt.setMA_LOAITAISAN_CAP_II(Integer.valueOf(rs.getInt("MA_LOAITAISAN_CAP_II")));
		lt.setTEN_LOAITAISAN_CAP_II(rs.getString("TEN_LOAITAISAN_CAP_II"));
		lt.setMA_LOAITAISAN_CAP_I(Integer.valueOf(rs.getInt("MA_LOAITAISAN_CAP_I")));
		return lt;
	}

	public LOAITAISAN_CAP_III get_LOAITAISAN_CAP_III(ResultSet rs) throws SQLException {
		LOAITAISAN_CAP_III lt = new LOAITAISAN_CAP_III();
		lt.setMA_LOAITAISAN_CAP_III(rs.getInt("MA_LOAITAISAN_CAP_III"));
		lt.setTEN_LOAITAISAN_CAP_III(rs.getString("TEN_LOAITAISAN_CAP_III"));
		lt.setMA_LOAITAISAN_CAP_II(rs.getInt("MA_LOAITAISAN_CAP_II"));
		return lt;
	}

	public SYSTEM_LOG get_SYSTEM_LOG(ResultSet rs) throws SQLException {
		SYSTEM_LOG result = new SYSTEM_LOG();
		result.setLOG_ID(rs.getInt("LOG_ID"));
		result.setTEN_TAI_KHOAN(rs.getString("TEN_TAI_KHOAN"));
		result.setNOIDUNG(rs.getString("NOIDUNG"));
		result.setTHOIGIAN(rs.getTimestamp("THOIGIAN"));
		return result;
	}

	public CHUKY_DANGKIEM get_CHUKY_DANGKIEM(ResultSet rs) throws SQLException {
		CHUKY_DANGKIEM result = new CHUKY_DANGKIEM();
		result.setMA_KYHAN_DANGKIEM(rs.getInt("MA_KYHAN_DANGKIEM"));
		result.setTEN_KYHAN(rs.getString("TEN_KYHAN"));
		result.setCHU_KY_DAU(rs.getInt("CHU_KY_DAU"));
		result.setCHU_KY(rs.getInt("CHU_KY"));
		return result;
	}

	public DOT_THUCHIEN_DANGKIEM get_DOT_THUCHIEN_DANGKIEM(ResultSet rs) throws SQLException {
		DOT_THUCHIEN_DANGKIEM result = new DOT_THUCHIEN_DANGKIEM();
		result.setMA_DOT_THUCHIEN_DANGKIEM(rs.getInt("MA_DOT_THUCHIEN_DANGKIEM"));
		result.setNGAY_THUCHIEN(rs.getTimestamp("NGAY_THUCHIEN"));
		result.setMA_TAPHOSO(rs.getInt("MA_TAPHOSO"));
		result.setMA_PHUONGTIEN_GIAOTHONG(rs.getString("MA_PHUONGTIEN_GIAOTHONG"));
		result.setGHI_CHU(rs.getString("GHI_CHU"));
		return result;
	}

}
