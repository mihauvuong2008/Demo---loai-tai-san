package View.AssetManagers.ThongBao.Library;

import java.sql.SQLException;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import View.AssetManagers.ThongBao.GuiThongbao.CongviecCuaToi.Thongbao_CongviecCuaToi;

public class ThongBao_Lib_Congviec {
	private static NGUOIDUNG user;
	private final Controler controler;

	public ThongBao_Lib_Congviec(NGUOIDUNG user) {
		ThongBao_Lib_Congviec.user = user;
		controler = new Controler(user);
	}

	Object getPhanViecTruoc(Object Congviec, Object Phanviec) throws SQLException {
		if (Phanviec instanceof GIAI_DOAN_THUC_HIEN) {
			if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				return controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
				return controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_TANG_TAISAN) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				return controler.getControl_DEXUAT().get_DEXUAT((DOT_THUCHIEN_GIAM_TAISAN) Congviec);
			}
		} else if (Phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
			if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				return controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
				return controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_TANG_TAISAN) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				return controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN((DOT_THUCHIEN_GIAM_TAISAN) Congviec);
			}
		} else if (Phanviec instanceof GIAI_DOAN_QUYET_TOAN) {
			if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				return controler.getControl_NGHIEMTHU()
						.get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
				return controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_TANG_TAISAN) Congviec);
			}
		}
		return null;
	}

	Object getPhanViecTieptheo(Object Congviec, Object Phanviec) throws SQLException {
		if (Phanviec instanceof GIAI_DOAN_THUC_HIEN) {
			if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				return controler.getControl_NGHIEMTHU()
						.get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
				return controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU((DOT_THUCHIEN_TANG_TAISAN) Congviec);
			}
		} else if (Phanviec instanceof GIAI_DOAN_NGHIEM_THU) {
			if (Congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				return controler.getControl_QUYETTOAN()
						.get_GIAIDOAN_QUYETTOAN((DOT_THUCHIEN_SUACHUA_BAODUONG) Congviec);
			} else if (Congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
				return controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN((DOT_THUCHIEN_TANG_TAISAN) Congviec);
			}
		}
		return null;
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
						congviec, phanviec);
		DE_XUAT gdnt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (gdnt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Giai đoạn ĐĐ xuất. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
							congviec, gdnt);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [SỬA CHỮA PTTS - " + congviec.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() + " (Mã: "
						+ congviec.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + ")]\n" + user.getTEN_CAN_BO()
						+ " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		DE_XUAT gdnt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (gdnt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Hoàn thành Giai đoạn ĐĐ xuất. Công việc: [SỬA CHỮA PTTS - "
							+ congviec.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG() + " (Mã: "
							+ congviec.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG() + ")]\n" + user.getTEN_CAN_BO()
							+ " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
					congviec, gdnt);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		DE_XUAT dx = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (dx != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Giai đoạn ĐĐ xuất. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
							congviec, dx);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
			DOT_THUCHIEN_GIAM_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [THANH LĐ, BÀN GIAO PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		DE_XUAT dx = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (dx != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Giai đoạn ĐĐ xuất. Công việc: [THANH LĐ, BÀN GIAO PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Tổ chức - Thực hiện",
							congviec, dx);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);
		GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Tổ chức - thực hiện. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu - Bàn giao",
							congviec, gdth);

	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);

		GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Tổ chức - thực hiện. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu - Bàn giao",
							congviec, gdth);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_THUC_HIEN gdth = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Tổ chức - thực hiện. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Nghiệm thu Bàn giao",
							congviec, gdth);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Nghiệm thu. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
							congviec, gdth);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);

		GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Nghiệm thu. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
							congviec, gdth);
	}

	public void create_THONGBAO_Congvieccuatoi_TIEPNHAN_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);

		GIAI_DOAN_NGHIEM_THU gdth = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (gdth != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Hoàn thành Nghiệm thu. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã tiếp nhận phần việc Quyết toán - Thanh lý hợp đồng",
							congviec, gdth);
	}

	/// aaaaaaaaaaaaaaaaaaaa
	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
						congviec, phanviec);
		DE_XUAT pvt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
						congviec, phanviec);
		DE_XUAT pvt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN  PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		DE_XUAT pvt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Trả lại. Công việc: [MUA SẮM, TIẾP NHẬN  PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
			DOT_THUCHIEN_GIAM_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [THANH LĐ, CHUYỂN GIAO PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		DE_XUAT pvt = (DE_XUAT) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Trả lại. Công việc: [THANH LĐ, CHUYỂN GIAO PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);
		GIAI_DOAN_THUC_HIEN pvt = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);
		GIAI_DOAN_THUC_HIEN pvt = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_THUC_HIEN pvt = (GIAI_DOAN_THUC_HIEN) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Trả lại. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
					"Trả lại. Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
							+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
					congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_TRALAI_GIAI_DOAN_QUYET_TOAN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTruoc(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Trả lại. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Trả lại phần việc Quyết toán - Thanh lý hợp đồng",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc hoàn tất: [BẢO DƯỠNG PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Hoàn thành Quyết toán - Thanh lý hợp đồng",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc hoàn tất: [SỬA CHỮA PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Hoàn thành Quyết toán, Thanh lý hợp đồng",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_QUYET_TOAN_Hoantat_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_QUYET_TOAN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia("Công việc hoàn tất: [MUA SẮM, TIẾP NHẬN PTTS: "
						+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ " Đã Hoàn thành Quyết toán, Thanh lý hợp đồng", congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
						congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
						congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_Hoanthanh_Congviec_DOT_THUCHIEN_GIAM_TAISAN(
			DOT_THUCHIEN_GIAM_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [THANH LĐ, BÀN GIAO PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Hoàn thành Tổ chức - Thực hiện",
				congviec, phanviec);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user))
				.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
						"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
								+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
						congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Chuyển giao. Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức Thực hiện",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_DUNGCHUYENGIAO_GIAI_DOAN_THUC_HIEN_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_THUC_HIEN phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức - Thực hiện",
				congviec, phanviec);
		GIAI_DOAN_NGHIEM_THU pvt = (GIAI_DOAN_NGHIEM_THU) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [MUA SẮM, TIẾP NHẬN PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Tổ chức - Thực hiện",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_BAODUONG(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [BẢO DƯỠNG PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [BẢO DƯỠNG PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_SUACHUA(
			DOT_THUCHIEN_SUACHUA_BAODUONG congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [SỬA CHỮA PTTS - " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
						+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [SỬA CHỮA PTTS - "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

	public void create_THONGBAO_Congvieccuatoi_CHUACHUYENGIAO_GIAI_DOAN_NGHIEM_THU_Congviec_DOT_THUCHIEN_TANG_TAISAN(
			DOT_THUCHIEN_TANG_TAISAN congviec, GIAI_DOAN_NGHIEM_THU phanviec) throws SQLException {
		(new Thongbao_CongviecCuaToi(user)).GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
				"Công việc: [MUA SẮM, TIẾP NHẬN PTTS: " + (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec)
						+ ")]\n" + user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
				congviec, phanviec);
		GIAI_DOAN_QUYET_TOAN pvt = (GIAI_DOAN_QUYET_TOAN) getPhanViecTieptheo(congviec, phanviec);
		if (pvt != null)
			(new Thongbao_CongviecCuaToi(user))
					.GuiThongbao_PhanviecCuatoi_Cho_CanboThamgia(
							"Dừng Chuyển giao. Công việc: [MUA SẮM, TIẾP NHẬN PTTS: "
									+ (new Thongbao_CongviecCuaToi(user)).TenCongviec(congviec) + ")]\n"
									+ user.getTEN_CAN_BO() + " Đã DỪNG Chuyển giao phần việc Nghiệm thu - Bàn giao",
							congviec, pvt);
	}

}
