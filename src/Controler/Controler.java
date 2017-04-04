package Controler;

import java.sql.SQLException;

import Control.CHUKY_DANGKIEM.Control_CHUKY_DANGKIEM;
import Control.DATETIME_SERVER.Control_DATETIME_FROM_SERVER;
import Control.DEXUAT.Control_DEXUAT;
import Control.DOT_THUCHIEN_DANGKIEM.Control_DOT_THUCHIEN_DANGKIEM;
import Control.DOT_THUCHIEN_GIAM_TAISAN.Control_DOT_THUCHIEN_GIAM_TAISAN;
import Control.DOT_THUCHIEN_GIAM_TAISAN.Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN;
import Control.DOT_THUCHIEN_SUACHUA_BAODUONG.Control_DOT_THUCHIEN_SUACHUA_BAODUONG;
import Control.DOT_THUCHIEN_SUACHUA_BAODUONG.Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
import Control.DOT_THUCHIEN_TANG_TAISAN.Control_DOT_THUCHIEN_TANG_TAISAN;
import Control.DOT_THUCHIEN_TANG_TAISAN.Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN;
import Control.HO_SO.Control_FILESCAN;
import Control.HO_SO.Control_Hoso_Row;
import Control.HO_SO.Control_TAPHOSO;
import Control.HO_SO.Control_VANBAN;
import Control.KY_HAN_THONGKE_XANG_DAU.Control_KY_HAN_THONGKE_XANG_DAU;
import Control.KY_HAN_THONGKE_XANG_DAU.Control_NHOM_DONVI_THAMGIA_THONGKE;
import Control.LENH_DIEU_XE.Control_LENH_DIEU_XE;
import Control.LOAITAISAN.Control_LOAITAISAN_CAP_I;
import Control.LOAITAISAN.Control_LOAITAISAN_CAP_II;
import Control.LOAITAISAN.Control_LOAITAISAN_CAP_III;
import Control.NGHIEMTHU.Control_NGHIEMTHU;
import Control.NGHIEMTHU.Control_NGHIEMTHU_CANBO;
import Control.NGUOIDUNG.Control_NGUOIDUNG;
import Control.NGUONGIAM.Control_NGUONGIAM;
import Control.NGUONSUACHUA_BAODUONG.Control_NGUONSUACHUA_BAODUONG;
import Control.NGUONTANG.Control_NGUONTANG;
import Control.NHOMTAISAN.Control_NHOMTAISAN_CAP_I;
import Control.NHOMTAISAN.Control_NHOMTAISAN_CAP_II;
import Control.NHOMTAISAN.Control_NHOMTAISAN_CAP_III;
import Control.NHOMTAISAN.Control_NHOMTAISAN_CAP_IV;
import Control.NHOMTAISAN.Control_NHOMTAISAN_CAP_V;
import Control.PHONGBAN.Control_PHONGBAN;
import Control.PHUONGTIEN_GIAOTHONG.Control_LOAI_XE;
import Control.PHUONGTIEN_GIAOTHONG.Control_PHUONGTIEN_GIAOTHONG;
import Control.QUATRINH_DEXUAT_THUCHIEN.Control_QUATRINH_DEXUAT_THUCHIEN;
import Control.QUATRINH_NGHIEMTHU_QUYETTOAN.Control_QUATRINH_NGHIEMTHU_QUYETTOAN;
import Control.QUYETTOAN.Control_QUYETTOAN;
import Control.QUYETTOAN.Control_QUYETTOAN_CANBO;
import Control.ROLE.Control_Role;
import Control.ROLE.Control_Role_User;
import Control.SYSTEM_LOG.Control_SYSTEM_LOG;
import Control.TAISAN.Control_TAISAN;
import Control.THONGBAO.Control_NGUOIDUNG_NHAN_THONGBAO;
import Control.THONGBAO.Control_THONGBAO;
import Control.THUCHIEN.Control_THUCHIEN;
import Control.THUCHIEN.Control_THUCHIEN_CANBO;
import DAO.LENH_DIEU_XE;
import DAO.NGUOIDUNG;
import View.AssetManagers.LenhDieuXe.BeanRealator;
import View.AssetManagers.LenhDieuXe.Relator;

public class Controler {

	private final NGUOIDUNG user;

	private final Control_DATETIME_FROM_SERVER Control_DATETIME_FROM_SERVER;
	private final Control_DEXUAT Control_DEXUAT;
	private final Control_DOT_THUCHIEN_GIAM_TAISAN Control_DOT_THUCHIEN_GIAM_TAISAN;
	private final Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN;
	private final Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
	private final Control_DOT_THUCHIEN_SUACHUA_BAODUONG Control_DOT_THUCHIEN_SUACHUA_BAODUONG;
	private final Control_DOT_THUCHIEN_TANG_TAISAN Control_DOT_THUCHIEN_TANG_TAISAN;
	private final Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN;
	private final Control_FILESCAN Control_FILESCAN;
	private final Control_Hoso_Row Control_Hoso_Row;
	private final Control_TAPHOSO Control_TAPHOSO;
	private final Control_VANBAN Control_VANBAN;
	private final Control_KY_HAN_THONGKE_XANG_DAU Control_KY_HAN_THONGKE_XANG_DAU;
	private final Control_NHOM_DONVI_THAMGIA_THONGKE Control_NHOM_DONVI_THAMGIA_THONGKE;
	private final Control_LENH_DIEU_XE Control_LENH_DIEU_XE;
	@SuppressWarnings("unused")
	private BeanRealator BeanRealator;
	private final Relator Relator;
	private final Control_LOAITAISAN_CAP_I Control_LOAITAISAN_CAP_I;
	private final Control_LOAITAISAN_CAP_II Control_LOAITAISAN_CAP_II;
	private final Control_LOAITAISAN_CAP_III Control_LOAITAISAN_CAP_III;
	private final Control_NGHIEMTHU Control_NGHIEMTHU;
	private final Control_NGHIEMTHU_CANBO Control_NGHIEMTHU_CANBO;
	private final Control_NGUOIDUNG Control_NGUOIDUNG;
	private final Control_NGUONGIAM Control_NGUONGIAM;
	private final Control_NGUONSUACHUA_BAODUONG Control_NGUONSUACHUA_BAODUONG;
	private final Control_NGUONTANG Control_NGUONTANG;
	private final Control_NHOMTAISAN_CAP_I Control_NHOMTAISAN_CAP_I;
	private final Control_NHOMTAISAN_CAP_II Control_NHOMTAISAN_CAP_II;
	private final Control_NHOMTAISAN_CAP_III Control_NHOMTAISAN_CAP_III;
	private final Control_NHOMTAISAN_CAP_IV Control_NHOMTAISAN_CAP_IV;
	private final Control_NHOMTAISAN_CAP_V Control_NHOMTAISAN_CAP_V;
	private final Control_PHONGBAN Control_PHONGBAN;
	private final Control_LOAI_XE Control_LOAI_XE;
	private final Control_PHUONGTIEN_GIAOTHONG Control_PHUONGTIEN_GIAOTHONG;
	private final Control_QUATRINH_DEXUAT_THUCHIEN Control_QUATRINH_DEXUAT_THUCHIEN;
	private final Control_QUATRINH_NGHIEMTHU_QUYETTOAN Control_QUATRINH_NGHIEMTHU_QUYETTOAN;
	private final Control_QUYETTOAN_CANBO Control_QUYETTOAN_CANBO;
	private final Control_QUYETTOAN Control_QUYETTOAN;
	private final Control_Role_User Control_Role_User;
	private final Control_Role Control_Role;
	private final Control_SYSTEM_LOG Control_SYSTEM_LOG;
	private final Control_TAISAN Control_TAISAN;
	private final Control_NGUOIDUNG_NHAN_THONGBAO Control_NGUOIDUNG_NHAN_THONGBAO;
	private final Control_THONGBAO Control_THONGBAO;
	private final Control_THUCHIEN_CANBO Control_THUCHIEN_CANBO;
	private final Control_THUCHIEN Control_THUCHIEN;
	private final Control_CHUKY_DANGKIEM Control_CHUKY_DANGKIEM;
	private final Control_DOT_THUCHIEN_DANGKIEM Control_DOT_THUCHIEN_DANGKIEM;

	public Controler(NGUOIDUNG user) {
		super();
		this.user = user;
		Control_DATETIME_FROM_SERVER = new Control_DATETIME_FROM_SERVER(user);
		Control_DEXUAT = new Control_DEXUAT(user);
		Control_DOT_THUCHIEN_GIAM_TAISAN = new Control_DOT_THUCHIEN_GIAM_TAISAN(user);
		Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN = new Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN(user);
		Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN = new Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN(user);
		Control_DOT_THUCHIEN_SUACHUA_BAODUONG = new Control_DOT_THUCHIEN_SUACHUA_BAODUONG(user);
		Control_DOT_THUCHIEN_TANG_TAISAN = new Control_DOT_THUCHIEN_TANG_TAISAN(user);
		Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN = new Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN(user);
		Control_FILESCAN = new Control_FILESCAN(user);
		Control_Hoso_Row = new Control_Hoso_Row(user);
		Control_TAPHOSO = new Control_TAPHOSO(user);
		Control_VANBAN = new Control_VANBAN(user);
		Control_KY_HAN_THONGKE_XANG_DAU = new Control_KY_HAN_THONGKE_XANG_DAU(user);
		Control_NHOM_DONVI_THAMGIA_THONGKE = new Control_NHOM_DONVI_THAMGIA_THONGKE(user);
		Control_LENH_DIEU_XE = new Control_LENH_DIEU_XE(user);
		Relator = new Relator();
		Control_LOAITAISAN_CAP_I = new Control_LOAITAISAN_CAP_I(user);
		Control_LOAITAISAN_CAP_II = new Control_LOAITAISAN_CAP_II(user);
		Control_LOAITAISAN_CAP_III = new Control_LOAITAISAN_CAP_III(user);
		Control_NGHIEMTHU = new Control_NGHIEMTHU(user);
		Control_NGHIEMTHU_CANBO = new Control_NGHIEMTHU_CANBO(user);
		Control_NGUOIDUNG = new Control_NGUOIDUNG(user);
		Control_NGUONGIAM = new Control_NGUONGIAM(user);
		Control_NGUONSUACHUA_BAODUONG = new Control_NGUONSUACHUA_BAODUONG(user);
		Control_NGUONTANG = new Control_NGUONTANG(user);
		Control_NHOMTAISAN_CAP_I = new Control_NHOMTAISAN_CAP_I(user);
		Control_NHOMTAISAN_CAP_II = new Control_NHOMTAISAN_CAP_II(user);
		Control_NHOMTAISAN_CAP_III = new Control_NHOMTAISAN_CAP_III(user);
		Control_NHOMTAISAN_CAP_IV = new Control_NHOMTAISAN_CAP_IV(user);
		Control_NHOMTAISAN_CAP_V = new Control_NHOMTAISAN_CAP_V(user);
		Control_PHONGBAN = new Control_PHONGBAN(user);
		Control_LOAI_XE = new Control_LOAI_XE(user);
		Control_PHUONGTIEN_GIAOTHONG = new Control_PHUONGTIEN_GIAOTHONG(user);
		Control_QUATRINH_DEXUAT_THUCHIEN = new Control_QUATRINH_DEXUAT_THUCHIEN(user);
		Control_QUATRINH_NGHIEMTHU_QUYETTOAN = new Control_QUATRINH_NGHIEMTHU_QUYETTOAN(user);
		Control_QUYETTOAN_CANBO = new Control_QUYETTOAN_CANBO(user);
		Control_QUYETTOAN = new Control_QUYETTOAN(user);
		Control_Role_User = new Control_Role_User(user);
		Control_Role = new Control_Role(user);
		Control_SYSTEM_LOG = new Control_SYSTEM_LOG(user);
		Control_TAISAN = new Control_TAISAN(user);
		Control_NGUOIDUNG_NHAN_THONGBAO = new Control_NGUOIDUNG_NHAN_THONGBAO(user);
		Control_THONGBAO = new Control_THONGBAO(user);
		Control_THUCHIEN_CANBO = new Control_THUCHIEN_CANBO(user);
		Control_THUCHIEN = new Control_THUCHIEN(user);
		Control_CHUKY_DANGKIEM = new Control_CHUKY_DANGKIEM(user);
		Control_DOT_THUCHIEN_DANGKIEM = new Control_DOT_THUCHIEN_DANGKIEM(user);
	}

	public final NGUOIDUNG getUser() {
		return user;
	}

	public final Control_DATETIME_FROM_SERVER getControl_DATETIME_FROM_SERVER() {
		return Control_DATETIME_FROM_SERVER;
	}

	public final Control_DEXUAT getControl_DEXUAT() {
		return Control_DEXUAT;
	}

	public final Control_DOT_THUCHIEN_GIAM_TAISAN getControl_DOT_THUCHIEN_GIAM_TAISAN() {
		return Control_DOT_THUCHIEN_GIAM_TAISAN;
	}

	public final Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN getControl_TAISAN_DOT_THUCHIEN_GIAM_TAISAN() {
		return Control_TAISAN_DOT_THUCHIEN_GIAM_TAISAN;
	}

	public final Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN getControl_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN() {
		return Control_DOT_THUCHIEN_SUACHUA_BAODUONG_TAISAN;
	}

	public final Control_DOT_THUCHIEN_SUACHUA_BAODUONG getControl_DOT_THUCHIEN_SUACHUA_BAODUONG() {
		return Control_DOT_THUCHIEN_SUACHUA_BAODUONG;
	}

	public final Control_DOT_THUCHIEN_TANG_TAISAN getControl_DOT_THUCHIEN_TANG_TAISAN() {
		return Control_DOT_THUCHIEN_TANG_TAISAN;
	}

	public final Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN getControl_TAISAN_DOT_THUCHIEN_TANG_TAISAN() {
		return Control_TAISAN_DOT_THUCHIEN_TANG_TAISAN;
	}

	public final Control_FILESCAN getControl_FILESCAN() {
		return Control_FILESCAN;
	}

	public final Control_Hoso_Row getControl_Hoso_Row() {
		return Control_Hoso_Row;
	}

	public final Control_TAPHOSO getControl_TAPHOSO() {
		return Control_TAPHOSO;
	}

	public final Control_VANBAN getControl_VANBAN() {
		return Control_VANBAN;
	}

	public final Control_KY_HAN_THONGKE_XANG_DAU getControl_KY_HAN_THONGKE_XANG_DAU() {
		return Control_KY_HAN_THONGKE_XANG_DAU;
	}

	public final Control_NHOM_DONVI_THAMGIA_THONGKE getControl_NHOM_DONVI_THAMGIA_THONGKE() {
		return Control_NHOM_DONVI_THAMGIA_THONGKE;
	}

	public final Control_LENH_DIEU_XE getControl_LENH_DIEU_XE() {
		return Control_LENH_DIEU_XE;
	}

	public final BeanRealator getBeanRealator(LENH_DIEU_XE l, NGUOIDUNG user) throws SQLException {
		return new BeanRealator(l, user);
	}

	public final Relator getRelator() {
		return Relator;
	}

	public final Control_LOAITAISAN_CAP_I getControl_LOAITAISAN_CAP_I() {
		return Control_LOAITAISAN_CAP_I;
	}

	public final Control_LOAITAISAN_CAP_II getControl_LOAITAISAN_CAP_II() {
		return Control_LOAITAISAN_CAP_II;
	}

	public final Control_LOAITAISAN_CAP_III getControl_LOAITAISAN_CAP_III() {
		return Control_LOAITAISAN_CAP_III;
	}

	public final Control_NGHIEMTHU getControl_NGHIEMTHU() {
		return Control_NGHIEMTHU;
	}

	public final Control_NGHIEMTHU_CANBO getControl_NGHIEMTHU_CANBO() {
		return Control_NGHIEMTHU_CANBO;
	}

	public final Control_NGUOIDUNG getControl_NGUOIDUNG() {
		return Control_NGUOIDUNG;
	}

	public final Control_NGUONGIAM getControl_NGUONGIAM() {
		return Control_NGUONGIAM;
	}

	public final Control_NGUONSUACHUA_BAODUONG getControl_NGUONSUACHUA_BAODUONG() {
		return Control_NGUONSUACHUA_BAODUONG;
	}

	public final Control_NGUONTANG getControl_NGUONTANG() {
		return Control_NGUONTANG;
	}

	public final Control_NHOMTAISAN_CAP_I getControl_NHOMTAISAN_CAP_I() {
		return Control_NHOMTAISAN_CAP_I;
	}

	public final Control_NHOMTAISAN_CAP_II getControl_NHOMTAISAN_CAP_II() {
		return Control_NHOMTAISAN_CAP_II;
	}

	public final Control_NHOMTAISAN_CAP_III getControl_NHOMTAISAN_CAP_III() {
		return Control_NHOMTAISAN_CAP_III;
	}

	public final Control_NHOMTAISAN_CAP_IV getControl_NHOMTAISAN_CAP_IV() {
		return Control_NHOMTAISAN_CAP_IV;
	}

	public final Control_NHOMTAISAN_CAP_V getControl_NHOMTAISAN_CAP_V() {
		return Control_NHOMTAISAN_CAP_V;
	}

	public final Control_PHONGBAN getControl_PHONGBAN() {
		return Control_PHONGBAN;
	}

	public final Control_LOAI_XE getControl_LOAI_XE() {
		return Control_LOAI_XE;
	}

	public final Control_PHUONGTIEN_GIAOTHONG getControl_PHUONGTIEN_GIAOTHONG() {
		return Control_PHUONGTIEN_GIAOTHONG;
	}

	public final Control_QUATRINH_DEXUAT_THUCHIEN getControl_QUATRINH_DEXUAT_THUCHIEN() {
		return Control_QUATRINH_DEXUAT_THUCHIEN;
	}

	public final Control_QUATRINH_NGHIEMTHU_QUYETTOAN getControl_QUATRINH_NGHIEMTHU_QUYETTOAN() {
		return Control_QUATRINH_NGHIEMTHU_QUYETTOAN;
	}

	public final Control_QUYETTOAN_CANBO getControl_QUYETTOAN_CANBO() {
		return Control_QUYETTOAN_CANBO;
	}

	public final Control_QUYETTOAN getControl_QUYETTOAN() {
		return Control_QUYETTOAN;
	}

	public final Control_Role_User getControl_Role_User() {
		return Control_Role_User;
	}

	public final Control_Role getControl_Role() {
		return Control_Role;
	}

	public final Control_SYSTEM_LOG getControl_SYSTEM_LOG() {
		return Control_SYSTEM_LOG;
	}

	public final Control_TAISAN getControl_TAISAN() {
		return Control_TAISAN;
	}

	public final Control_NGUOIDUNG_NHAN_THONGBAO getControl_NGUOIDUNG_NHAN_THONGBAO() {
		return Control_NGUOIDUNG_NHAN_THONGBAO;
	}

	public final Control_THONGBAO getControl_THONGBAO() {
		return Control_THONGBAO;
	}

	public final Control_THUCHIEN_CANBO getControl_THUCHIEN_CANBO() {
		return Control_THUCHIEN_CANBO;
	}

	public final Control_THUCHIEN getControl_THUCHIEN() {
		return Control_THUCHIEN;
	}

	public Control_CHUKY_DANGKIEM getControl_CHUKY_DANGKIEM() {
		return Control_CHUKY_DANGKIEM;
	}

	public Control_DOT_THUCHIEN_DANGKIEM getControl_DOT_THUCHIEN_DANGKIEM() {
		return Control_DOT_THUCHIEN_DANGKIEM;
	}

}
