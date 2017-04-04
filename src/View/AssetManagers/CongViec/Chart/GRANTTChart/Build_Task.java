package View.AssetManagers.CongViec.Chart.GRANTTChart;

import java.sql.SQLException;
import java.util.ArrayList;

import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;

import Controler.Controler;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;

public class Build_Task {
	private final Controler controler;

	public Build_Task(NGUOIDUNG user) {
		controler = new Controler(user);
	}

	public ArrayList<Object> CongViec_ChuaHoanthanh(int oPTION) throws SQLException {

		ArrayList<Object> result = new ArrayList<>();
		if (oPTION == 1 || oPTION == 3 || oPTION == 5 || oPTION == 7) {
			ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
					.get_DangThucHien_Suachua_Baoduong();
			if (dsbl != null)
				result.addAll(dsbl);
		}
		if (oPTION == 2 || oPTION == 3 || oPTION == 6 || oPTION == 7) {
			ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
					.get_DangThucHien_DotTangTaisan();
			if (dttl != null)
				result.addAll(dttl);
		}
		if (oPTION == 4 || oPTION == 5 || oPTION == 6 || oPTION == 7) {
			ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
					.get_DangThucHien_DotGiamTaisan();
			if (dgtl != null)
				result.addAll(dgtl);
		}

		return result;
	};

	public TaskSeries getTHUCHIEN_Task(ArrayList<Object> dataSet) throws SQLException {
		final TaskSeries Thuchien = new TaskSeries("THỰC HIỆN");
		if (dataSet == null)
			return Thuchien;
		for (Object o : dataSet) {
			String Tencongviec = "";
			if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) o;
				Tencongviec = "Sửa chữa - Bảo dưỡng: " + dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG();
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_BAT_DAU() != null && gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
						Thuchien.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_TANG_TAISAN) {
				DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) o;
				Tencongviec = "Mua sắm - Tiếp nhận: " + dtt.getMA_DOT_TANG();
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_BAT_DAU() != null && gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
						Thuchien.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				DOT_THUCHIEN_GIAM_TAISAN dgt = (DOT_THUCHIEN_GIAM_TAISAN) o;
				Tencongviec = "Thanh lý - Bàn giao: " + dgt.getMA_DOT_GIAM();
				GIAI_DOAN_THUC_HIEN gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_BAT_DAU() != null && gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
						Thuchien.add(t);
					}
			}
		}
		return Thuchien;
	};

	public TaskSeries getNGHIEMTHU_Task(ArrayList<Object> dataSet) throws SQLException {
		final TaskSeries Nghiemthu = new TaskSeries("NGHIỆM THU");
		if (dataSet == null)
			return Nghiemthu;
		for (Object o : dataSet) {
			String Tencongviec = "";
			if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) o;
				Tencongviec = "Sửa chữa - Bảo dưỡng: " + dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG();
				GIAI_DOAN_NGHIEM_THU gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_TIEP_NHAN() != null && gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_TIEP_NHAN(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
						Nghiemthu.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_TANG_TAISAN) {
				DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) o;
				Tencongviec = "Mua sắm - Tiếp nhận: " + dtt.getMA_DOT_TANG();
				GIAI_DOAN_NGHIEM_THU gdth = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_TIEP_NHAN() != null && gdth.getTHOI_DIEM_CHUYEN_GIAO() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_TIEP_NHAN(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
						Nghiemthu.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				// Do nothing
			}
		}
		return Nghiemthu;
	};

	public TaskSeries getQUYETTOAN_Task(ArrayList<Object> dataSet) throws SQLException {
		final TaskSeries Quyettoan = new TaskSeries("QUYẾT TOÁN");
		if (dataSet == null)
			return Quyettoan;
		for (Object o : dataSet) {
			String Tencongviec = "";
			if (o instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
				DOT_THUCHIEN_SUACHUA_BAODUONG dsb = (DOT_THUCHIEN_SUACHUA_BAODUONG) o;
				Tencongviec = "Sửa chữa - Bảo dưỡng: " + dsb.getMA_DOT_THUCHIEN_SUACHUA_BAODUONG();
				GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_TIEP_NHAN() != null && gdth.getTHOI_GIAN_KET_THUC() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_TIEP_NHAN(), gdth.getTHOI_GIAN_KET_THUC());
						Quyettoan.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_TANG_TAISAN) {
				DOT_THUCHIEN_TANG_TAISAN dtt = (DOT_THUCHIEN_TANG_TAISAN) o;
				Tencongviec = "Mua sắm - Tiếp nhận: " + dtt.getMA_DOT_TANG();
				GIAI_DOAN_QUYET_TOAN gdth = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
				if (gdth != null)
					if (gdth.getTHOI_DIEM_TIEP_NHAN() != null && gdth.getTHOI_GIAN_KET_THUC() != null) {
						Task t = new Task(Tencongviec, gdth.getTHOI_DIEM_TIEP_NHAN(), gdth.getTHOI_GIAN_KET_THUC());
						Quyettoan.add(t);
					}
			} else if (o instanceof DOT_THUCHIEN_GIAM_TAISAN) {
				// Nothing do hú hu
			}
		}
		return Quyettoan;
	};
}
