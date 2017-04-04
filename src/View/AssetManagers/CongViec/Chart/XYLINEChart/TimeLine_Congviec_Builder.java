package View.AssetManagers.CongViec.Chart.XYLINEChart;

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

public class TimeLine_Congviec_Builder {
	private final Controler controler;

	public TimeLine_Congviec_Builder(NGUOIDUNG user) {
		controler = new Controler(user);
	}

	public ArrayList<TIMELINE_CONGVIEC> getCurrent_Task() {
		return null;
	}

	public ArrayList<TIMELINE_CONGVIEC> getHistory_Task(Date Begin, Date End) throws SQLException {
		ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl = controler.getControl_DOT_THUCHIEN_SUACHUA_BAODUONG()
				.get_DOT_THUCHIEN_SUACHUA_BAODUONG_list(Begin, End);
		ArrayList<DOT_THUCHIEN_TANG_TAISAN> dttl = controler.getControl_DOT_THUCHIEN_TANG_TAISAN()
				.get_DOT_THUCHIEN_TANG_TAISAN_list(Begin, End);
		ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl = controler.getControl_DOT_THUCHIEN_GIAM_TAISAN()
				.get_DOT_THUCHIEN_GIAM_TAISAN_list(Begin, End);

		ArrayList<TIMELINE_CONGVIEC> tcl_dsb = build_TimeLineDSB(dsbl);
		ArrayList<TIMELINE_CONGVIEC> tcl_dtt = build_TimeLineDTT(dttl);
		ArrayList<TIMELINE_CONGVIEC> tcl_dgt = build_TimeLineDGT(dgtl);
		ArrayList<TIMELINE_CONGVIEC> result = new ArrayList<>();
		result.addAll(tcl_dsb);
		result.addAll(tcl_dtt);
		result.addAll(tcl_dgt);
		return (result);
	}

	private ArrayList<TIMELINE_CONGVIEC> build_TimeLineDSB(ArrayList<DOT_THUCHIEN_SUACHUA_BAODUONG> dsbl)
			throws SQLException {
		ArrayList<TIMELINE_CONGVIEC> result = new ArrayList<>();
		if (dsbl != null)
			for (DOT_THUCHIEN_SUACHUA_BAODUONG dsb : dsbl) {
				TIMELINE_CONGVIEC tc = new TIMELINE_CONGVIEC();
				tc.setLoaiCongviec(dsb);
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dsb);
				TIMELINE_DEXUAT tldx = getTIMELINE_DEXUAT(dx);
				tc.setDEXUAT(tldx);

				GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dsb);
				TIMELINE_THUCHIEN tlth = getTIMELINE_THUCHIEN(th);
				tc.setTHUCHIEN(tlth);

				GIAI_DOAN_NGHIEM_THU nt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dsb);
				TIMELINE_NGHIEMTHU tlnt = getTIMELINE_NGHIEMTHU(nt);
				tc.setNGHIEMTHU(tlnt);

				GIAI_DOAN_QUYET_TOAN qt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dsb);
				TIMELINE_QUYETTOAN tlqt = getTIMELINE_QUYETTOAN(qt);
				tc.setQUYETTOAN(tlqt);
				result.add(tc);
			}
		return result;
	}

	private ArrayList<TIMELINE_CONGVIEC> build_TimeLineDTT(ArrayList<DOT_THUCHIEN_TANG_TAISAN> dgtl)
			throws SQLException {
		ArrayList<TIMELINE_CONGVIEC> result = new ArrayList<>();
		if (dgtl != null)
			for (DOT_THUCHIEN_TANG_TAISAN dtt : dgtl) {
				TIMELINE_CONGVIEC tc = new TIMELINE_CONGVIEC();
				tc.setLoaiCongviec(dtt);
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dtt);
				TIMELINE_DEXUAT tldx = getTIMELINE_DEXUAT(dx);
				tc.setDEXUAT(tldx);

				GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dtt);
				TIMELINE_THUCHIEN tlth = getTIMELINE_THUCHIEN(th);
				tc.setTHUCHIEN(tlth);

				GIAI_DOAN_NGHIEM_THU nt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(dtt);
				TIMELINE_NGHIEMTHU tlnt = getTIMELINE_NGHIEMTHU(nt);
				tc.setNGHIEMTHU(tlnt);

				GIAI_DOAN_QUYET_TOAN qt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(dtt);
				TIMELINE_QUYETTOAN tlqt = getTIMELINE_QUYETTOAN(qt);
				tc.setQUYETTOAN(tlqt);
				result.add(tc);
			}
		return result;
	}

	private ArrayList<TIMELINE_CONGVIEC> build_TimeLineDGT(ArrayList<DOT_THUCHIEN_GIAM_TAISAN> dgtl)
			throws SQLException {
		ArrayList<TIMELINE_CONGVIEC> result = new ArrayList<>();
		if (dgtl != null)
			for (DOT_THUCHIEN_GIAM_TAISAN dgt : dgtl) {
				TIMELINE_CONGVIEC tc = new TIMELINE_CONGVIEC();
				tc.setLoaiCongviec(dgt);
				DE_XUAT dx = controler.getControl_DEXUAT().get_DEXUAT(dgt);
				TIMELINE_DEXUAT tldx = getTIMELINE_DEXUAT(dx);
				tc.setDEXUAT(tldx);

				GIAI_DOAN_THUC_HIEN th = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(dgt);
				TIMELINE_THUCHIEN tlth = getTIMELINE_THUCHIEN(th);
				tc.setTHUCHIEN(tlth);

				result.add(tc);
			}
		return result;
	}

	TIMELINE_DEXUAT getTIMELINE_DEXUAT(DE_XUAT dx) {
		TIMELINE_DEXUAT tldx = new TIMELINE_DEXUAT();
		tldx.setNGAY_BATDAU(dx.getTHOI_DIEM_BAT_DAU());
		tldx.setNGAY_CHUYENGIAO(dx.getTHOI_DIEM_CHUYEN_GIAO());
		tldx.setNGAY_KETTHUC(dx.getTHOI_DIEM_HOAN_THANH());
		return tldx;
	}

	TIMELINE_THUCHIEN getTIMELINE_THUCHIEN(GIAI_DOAN_THUC_HIEN th) {
		TIMELINE_THUCHIEN tlth = new TIMELINE_THUCHIEN();
		tlth.setNGAY_BATDAU(th.getTHOI_DIEM_BAT_DAU());
		tlth.setNGAY_CHUYENGIAO(th.getTHOI_DIEM_CHUYEN_GIAO());
		tlth.setNGAY_KETTHUC(th.getTHOI_DIEM_HOAN_THANH());
		return tlth;
	}

	TIMELINE_QUYETTOAN getTIMELINE_QUYETTOAN(GIAI_DOAN_QUYET_TOAN qt) {
		TIMELINE_QUYETTOAN tlqt = new TIMELINE_QUYETTOAN();
		tlqt.setNGAY_BATDAU(qt.getTHOI_DIEM_TIEP_NHAN());
		tlqt.setNGAY_KETTHUC(qt.getTHOI_GIAN_KET_THUC());
		return tlqt;
	}

	TIMELINE_NGHIEMTHU getTIMELINE_NGHIEMTHU(GIAI_DOAN_NGHIEM_THU nt) {
		TIMELINE_NGHIEMTHU tlnt = new TIMELINE_NGHIEMTHU();
		tlnt.setNGAY_BATDAU(nt.getTHOI_DIEM_TIEP_NHAN());
		tlnt.setNGAY_CHUYENGIAO(nt.getTHOI_DIEM_CHUYEN_GIAO());
		tlnt.setNGAY_KETTHUC(nt.getTHOI_DIEM_KET_THUC());
		return tlnt;
	}
}
