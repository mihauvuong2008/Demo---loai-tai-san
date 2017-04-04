package View.AssetManagers.ThongBao.GuiThongbao.CongviecCuaToi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import DAO.NGUOIDUNG_NGHIEMTHU;
import DAO.NGUOIDUNG_NHAN_THONGBAO;
import DAO.NGUOIDUNG_QUYETTOAN;
import DAO.NGUOIDUNG_THUCHIEN;
import DAO.THONGBAO;
import View.MarkItem.Fill_ItemData;

public class Thongbao_CongviecCuaToi {
	private static NGUOIDUNG user;
	private Fill_ItemData f;
	private final Controler controler;

	public Thongbao_CongviecCuaToi(NGUOIDUNG user) {
		Thongbao_CongviecCuaToi.user = user;
		f = new Fill_ItemData();
		controler = new Controler(user);
	}

	public THONGBAO thongbao_Template(Object Congviec) {
		THONGBAO tb = new THONGBAO();
		tb.setTEN_TAI_KHOAN_GUI("SYSTEM");
		tb.setNGUON_TACDONG(user.getTEN_TAI_KHOAN());
		if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			DOT_THUCHIEN_SUACHUA_BAODUONG c = (DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec;
			if (c.getSUACHUA_BAODUONG() == 1) {
				tb.setLOAI_THONGBAO(f.getInt_LOAI_THONGBAO_Baoduong());
			} else if (c.getSUACHUA_BAODUONG() == 2) {
				tb.setLOAI_THONGBAO(f.getInt_LOAI_THONGBAO_Suachua());
			}
		} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
			tb.setLOAI_THONGBAO(f.getInt_LOAI_THONGBAO_Muasam());
		} else if (Congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			tb.setLOAI_THONGBAO(f.getInt_LOAI_THONGBAO_Thanhly());
		}
		Date d = controler.getControl_DATETIME_FROM_SERVER().get_CURRENT_DATETIME();
		tb.setNGAY_THONGBAO(d);
		return tb;
	}

	public void Gui_Thongbao(int MaThongbao, String TenTaikhoan) throws SQLException {
		NGUOIDUNG_NHAN_THONGBAO nntb = new NGUOIDUNG_NHAN_THONGBAO();
		nntb.setMA_THONGBAO(MaThongbao);
		nntb.setTEN_TAI_KHOAN(TenTaikhoan);
		controler.getControl_NGUOIDUNG_NHAN_THONGBAO().insert_NGUOIDUNG_NHAN_THONGBAO(nntb);
	}

	private void Gui_Thongbao_PhanviecCuatoi(int MaThongbao, DE_XUAT dx) throws SQLException {
		Gui_Thongbao(MaThongbao, dx.getTEN_TAI_KHOAN());
	}

	public void Gui_Thongbao_PhanviecCuatoi(int MaThongbao, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_THUCHIEN> ndl = controler.getControl_THUCHIEN_CANBO().get_AllNGUOIDUNG_THUCHIEN(phanviec);
		for (NGUOIDUNG_THUCHIEN nth : ndl) {
			Gui_Thongbao(MaThongbao, nth.getTEN_TAI_KHOAN());
		}
	}

	public void Gui_Thongbao_PhanviecCuatoi(int MaThongbao, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_NGHIEMTHU> ndl = controler.getControl_NGHIEMTHU_CANBO()
				.get_AllNGUOIDUNG_NGHIEMTHU(phanviec);
		for (NGUOIDUNG_NGHIEMTHU nth : ndl) {
			Gui_Thongbao(MaThongbao, nth.getTEN_TAI_KHOAN());
		}
	}

	public void Gui_Thongbao_PhanviecCuatoi(int MaThongbao, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		ArrayList<NGUOIDUNG_QUYETTOAN> ndl = controler.getControl_QUYETTOAN_CANBO()
				.get_AllNGUOIDUNG_QUYETTOAN(phanviec);
		for (NGUOIDUNG_QUYETTOAN nth : ndl) {
			Gui_Thongbao(MaThongbao, nth.getTEN_TAI_KHOAN());
		}
	}

	public void GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(String message, Object Congviec, DE_XUAT dx)
			throws SQLException {
		THONGBAO tb = thongbao_Template(Congviec);
		tb.setNOIDUNG_THONGBAO(message);
		int MaThongbao = controler.getControl_THONGBAO().insert_THONGBAO(tb);
		Gui_Thongbao_PhanviecCuatoi(MaThongbao, dx);
	}

	public void GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(String message, Object Congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		THONGBAO tb = thongbao_Template(Congviec);
		tb.setNOIDUNG_THONGBAO(message);
		int MaThongbao = controler.getControl_THONGBAO().insert_THONGBAO(tb);
		Gui_Thongbao_PhanviecCuatoi(MaThongbao, phanviec);
	}

	public void GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(String message, Object Congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		THONGBAO tb = thongbao_Template(Congviec);
		tb.setNOIDUNG_THONGBAO(message);
		int MaThongbao = controler.getControl_THONGBAO().insert_THONGBAO(tb);
		Gui_Thongbao_PhanviecCuatoi(MaThongbao, phanviec);
	}

	public void GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(String message, Object Congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		THONGBAO tb = thongbao_Template(Congviec);
		tb.setNOIDUNG_THONGBAO(message);
		int MaThongbao = controler.getControl_THONGBAO().insert_THONGBAO(tb);
		Gui_Thongbao_PhanviecCuatoi(MaThongbao, phanviec);
	}

	public String TenCongviec(DOT_THUCHIEN_SUACHUA_BAODUONG congviec) {
		return congviec.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() + " (Mã: "
				+ congviec.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG();
	}

	public String TenCongviec(DOT_THUCHIEN_TANG_TAISAN congviec) {
		return congviec.getTEN_DOT_TANG() + " (Mã: " + congviec.getMA_DOT_TANG();
	}

	public String TenCongviec(DOT_THUCHIEN_GIAM_TAISAN congviec) {
		return congviec.getTEN_DOT_GIAM() + " (Mã: " + congviec.getMA_DOT_GIAM();
	}

}
