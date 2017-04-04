package View.AssetManagers.CongViec.Chart.PIEChart;

import java.awt.Frame;
import java.awt.SystemColor;
import java.sql.SQLException;

import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import Controler.Controler;
import DAO.DE_XUAT;
import DAO.DOT_THUCHIEN_GIAM_TAISAN;
import DAO.DOT_THUCHIEN_SUACHUA_BAODUONG;
import DAO.DOT_THUCHIEN_TANG_TAISAN;
import DAO.GIAI_DOAN_NGHIEM_THU;
import DAO.GIAI_DOAN_QUYET_TOAN;
import DAO.GIAI_DOAN_THUC_HIEN;
import DAO.NGUOIDUNG;
import View.DateTime.MyDateFormat;

public class PieChart {

	private Frame frame;
	private ChartPanel chartPanel;
	private final MyDateFormat mdf = new MyDateFormat();
	private final Controler controler;

	public PieChart(NGUOIDUNG user) {
		controler = new Controler(user);
	}

	public void Draw(Composite composite_PieChart, Object congviec) throws SQLException {
		// composite_PieChart.setBackground(new Color(null, 1, 1, 1));
		if (frame == null)
			frame = SWT_AWT.new_Frame(composite_PieChart);
		else {
			frame.repaint();
		}
		// chart.setBackgroundPaint(SystemColor.white);
		// PiePlot3D p = (PiePlot3D) chart.getPlot();
		if (chartPanel == null)
			chartPanel = new ChartPanel(getChart(congviec));
		else {
			chartPanel.setChart(getChart(congviec));
		}
		// chartPanel.setForeground(SystemColor.activeCaption);
		// chartPanel.setBackground(SystemColor.white);
		// frame.setBackground(SystemColor.white);
		frame.add(chartPanel);
	}

	private JFreeChart getChart(Object congviec) throws SQLException {
		DefaultPieDataset pdataset = creatDataset(congviec);
		String title = "";
		if (congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			DOT_THUCHIEN_SUACHUA_BAODUONG cv = (DOT_THUCHIEN_SUACHUA_BAODUONG) congviec;
			title = cv.getTEN_DOT_THUCHIEN_SUACHUA_BAODUONG();
		} else if (congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
			DOT_THUCHIEN_TANG_TAISAN cv = (DOT_THUCHIEN_TANG_TAISAN) congviec;
			title = cv.getTEN_DOT_TANG();
		} else if (congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			DOT_THUCHIEN_GIAM_TAISAN cv = (DOT_THUCHIEN_GIAM_TAISAN) congviec;
			title = cv.getTEN_DOT_GIAM();
		}
		JFreeChart chart = createPieChart(pdataset, title);

		return chart;
	}

	private DefaultPieDataset creatDataset(Object congviec) throws SQLException {
		DefaultPieDataset pdataset = new DefaultPieDataset();
		DE_XUAT dx = null;
		GIAI_DOAN_THUC_HIEN gdth = null;
		GIAI_DOAN_NGHIEM_THU gdnt = null;
		GIAI_DOAN_QUYET_TOAN gdqt = null;
		if (congviec instanceof DOT_THUCHIEN_SUACHUA_BAODUONG) {
			DOT_THUCHIEN_SUACHUA_BAODUONG cv = (DOT_THUCHIEN_SUACHUA_BAODUONG) congviec;
			dx = controler.getControl_DEXUAT().get_DEXUAT(cv);
			gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(cv);
			gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(cv);
			gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(cv);

		} else if (congviec instanceof DOT_THUCHIEN_TANG_TAISAN) {
			DOT_THUCHIEN_TANG_TAISAN cv = (DOT_THUCHIEN_TANG_TAISAN) congviec;
			dx = controler.getControl_DEXUAT().get_DEXUAT(cv);
			gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(cv);
			gdnt = controler.getControl_NGHIEMTHU().get_GIAIDOAN_NGHIEMTHU(cv);
			gdqt = controler.getControl_QUYETTOAN().get_GIAIDOAN_QUYETTOAN(cv);

		} else if (congviec instanceof DOT_THUCHIEN_GIAM_TAISAN) {
			DOT_THUCHIEN_GIAM_TAISAN cv = (DOT_THUCHIEN_GIAM_TAISAN) congviec;
			dx = controler.getControl_DEXUAT().get_DEXUAT(cv);
			gdth = controler.getControl_THUCHIEN().get_GIAIDOAN_THUCHIEN(cv);
		}
		if (dx != null) {
			int nhandx = mdf.getTongNgaythuchien(dx.getTHOI_DIEM_BAT_DAU(), dx.getTHOI_DIEM_CHUYEN_GIAO());
			pdataset.setValue("Quá trình xử lý đề xuất: " + nhandx + " ngày", nhandx);
		}
		if (gdth != null) {
			int chuyendx = mdf.getTongNgaythuchien(dx.getTHOI_DIEM_CHUYEN_GIAO(), dx.getTHOI_DIEM_HOAN_THANH());
			int thuchien = mdf.getTongNgaythuchien(gdth.getTHOI_DIEM_BAT_DAU(), gdth.getTHOI_DIEM_CHUYEN_GIAO());
			pdataset.setValue("Chuyển giao Đề xuất: " + chuyendx + " ngày", chuyendx);
			pdataset.setValue("Quá trình Thực hiện công việc: " + thuchien + " ngày", thuchien);
		}
		if (gdnt != null) {
			int chuyenTH = mdf.getTongNgaythuchien(gdth.getTHOI_DIEM_CHUYEN_GIAO(), gdth.getTHOI_DIEM_HOAN_THANH());
			int nghiemthu = mdf.getTongNgaythuchien(gdnt.getTHOI_DIEM_TIEP_NHAN(), gdnt.getTHOI_DIEM_CHUYEN_GIAO());
			pdataset.setValue("Chuyển giao phần việc Thực hiện: " + chuyenTH + " ngày", chuyenTH);
			pdataset.setValue("Quá trình Nghiệm thu công việc: " + nghiemthu + " ngày", nghiemthu);
		}
		if (gdqt != null) {
			int chuyenNT = mdf.getTongNgaythuchien(gdnt.getTHOI_DIEM_CHUYEN_GIAO(), gdnt.getTHOI_DIEM_KET_THUC());
			int quyettoan = mdf.getTongNgaythuchien(gdqt.getTHOI_DIEM_TIEP_NHAN(), gdqt.getTHOI_GIAN_KET_THUC());
			pdataset.setValue("Chuyển giao phần việc Nghiệm thu: " + chuyenNT + " ngày", chuyenNT);
			pdataset.setValue("Quá trình Quyết toán công việc: " + quyettoan + " ngày", quyettoan);
		}
		return pdataset;
	}

	private JFreeChart createPieChart(DefaultPieDataset pdataset, String title) {
		final JFreeChart chart = ChartFactory.createPieChart3D(title, // chart
																		// title
				pdataset, // data
				true, // include legend
				true, false);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		plot.setBackgroundPaint(SystemColor.white);
		plot.setOutlineVisible(false);
		return chart;
	}
}
