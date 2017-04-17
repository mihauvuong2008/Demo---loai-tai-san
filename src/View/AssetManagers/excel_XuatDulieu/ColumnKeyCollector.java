package View.AssetManagers.excel_XuatDulieu;

import java.util.ArrayList;

public class ColumnKeyCollector {
	ArrayList<String> columnCollector;

	public ColumnKeyCollector() {
		columnCollector = new ArrayList<>();
	}

	public void addColumn(String column) {
		columnCollector.add(column);
	}

	public final ArrayList<String> getColumnCollector() {
		return columnCollector;
	}

	public final void setColumnCollector(ArrayList<String> columnCollector) {
		this.columnCollector = columnCollector;
	}

	public class PTGT_keyColumn {
		static public final String MA_PHUONGTIEN_GIAOTHONG = "MA_PHUONGTIEN_GIAOTHONG";
		static public final String TEN_PHUONGTIEN_GIAOTHONG = "TEN_PHUONGTIEN_GIAOTHONG";
		static public final String LOAI_PHUONGTIEN_GIAOTHONG = "LOAI_PHUONGTIEN_GIAOTHONG";
		static public final String MA_LOAI_XE = "MA_LOAI_XE";
		static public final String BIENSO = "BIENSO";
		static public final String SOKHUNG = "SOKHUNG";
		static public final String SOMAY = "SOMAY";
		static public final String SO_KM_XE = "SO_KM_XE";
		static public final String THOIHAN_DANGKIEM = "THOIHAN_DANGKIEM";
		static public final String MA_TAISAN = "MA_TAISAN";// mã kế toán
	}

	public class TAISAN_keyColumn {
		static public final String MA_TAISAN = "MA_TAISAN";
		static public final String TEN_TAISAN = "TEN_TAISAN";
		static public final String MODEL = "MODEL";
		static public final String SERI = "SERI";
		static public final String NGAY_SU_DUNG = "NGAY_SU_DUNG";
		static public final String XUAT_XU = "XUAT_XU";
		static public final String BAO_HANH = "BAO_HANH";
		static public final String TINH_TRANG = "TINH_TRANG";
		static public final String TRANG_THAI = "TRANG_THAI";
		static public final String DON_VI_TINH = "DON_VI_TINH";
		static public final String SOLUONG = "SOLUONG";
		static public final String NGUYEN_GIA = "NGUYEN_GIA";
		static public final String MA_TANSAN_KETOAN = "MA_TANSAN_KETOAN";
		static public final String MA_NHOMTAISAN_CAP_V = "MA_NHOMTAISAN_CAP_V";
		static public final String MA_LOAITAISAN_CAP_III = "MA_LOAITAISAN_CAP_III";
		static public final String MA_DON_VI_SU_DUNG = "MA_DON_VI_SU_DUNG";
		static public final String MA_DON_VI_QUAN_LY = "MA_DON_VI_QUAN_LY";
		static public final String GHI_CHU = "GHI_CHU";
	}
}
