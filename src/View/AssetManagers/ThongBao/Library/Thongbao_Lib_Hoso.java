package View.AssetManagers.ThongBao.Library;

import java.sql.SQLException;

import Controler.Controler;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import View.AssetManagers.ThongBao.GuiThongbao.CongviecCuaToi.Thongbao_CongviecCuaToi;

public class Thongbao_Lib_Hoso {
	private final NGUOIDUNG user;
	@SuppressWarnings("unused")
	private final Controler controler;

	public Thongbao_Lib_Hoso(NGUOIDUNG user) {
		this.user = user;
		controler = new Controler(user);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật hồ sơ Công việc: [BẢO DƯỠNG PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);

	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Cập nhật hồ sơ Công việc: [SỬA CHỮA PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Tổ chức - Thực hiện",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật hồ sơ Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);

	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_THUCHIEN_GIAMTAISAN(DOT_THUCHIEN_GIAM_TAISAN congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật hồ sơ Công việc: [THANH LĐ, BÀN GIAO PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật hồ sơ Công việc: [BẢO DƯỠNG PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Cập nhật hồ sơ Công việc: [SỬA CHỮA PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_NGHIEMTHU_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật hồ sơ Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật hồ sơ Công việc: [BẢO DƯỠNG PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Cập nhật hồ sơ Công việc: [SỬA CHỮA PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatHoSo_QUYETTOAN_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật hồ sơ Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật Ghi chú Công việc: [BẢO DƯỠNG PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật Ghi chú Công việc: [SỬA CHỮA PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật Ghi chú Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);

	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_THUCHIEN_GIAMTAISAN(DOT_THUCHIEN_GIAM_TAISAN congviec,
			GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Cập nhật Ghi chú Công việc: [THANH LĐ, BÀN GIAO PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã Cập nhật hồ sơ Tổ chức - Thực hiện", congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [BẢO DƯỠNG PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [SỬA CHỮA PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
						congviec, phanviec);

	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_NGHIEMTHU_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Nghiệm thu - Bàn giao",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_BAODUONG(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [BẢO DƯỠNG PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_SUACHUA(DOT_THUCHIEN_SUACHUA_BAODUONG congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [SỬA CHỮA PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
						congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CapnhatGhichu_QUYETTOAN_TANGTAISAN(DOT_THUCHIEN_TANG_TAISAN congviec,
			GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Cập nhật Ghi chú Công việc: [MUA SẮM, THANH LĐ PTTS: "
								+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
								+ user.getTEN_CAN_BO() + " Đã Cập nhật hồ sơ Quyết toán - Thanh lý hợp đồng",
						congviec, phanviec);
	}

}
