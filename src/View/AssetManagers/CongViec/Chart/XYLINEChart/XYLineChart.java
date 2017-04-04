package View.AssetManagers.CongViec.Chart.XYLINEChart;

import java.awt.Color;
import java.awt.Frame;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import DAO.NGUOIDUNG;
import View.DateTime.MyDateFormat;

public class XYLineChart {
	private final NGUOIDUNG user;
	private ChartPanel chartPanel;
	private Frame frame_BieuDoCongviec_Grantt;

	public XYLineChart(NGUOIDUNG user) {
		this.user = user;
	}

	public void Draw(Composite composite_Xychart, Date Begin, Date End) throws SQLException {
		createFrame(composite_Xychart);
		JFreeChart chart = createChart(Begin, End);
		addChartPanel_To_Frame(chart);
	}

	private void addChartPanel_To_Frame(JFreeChart chart) {
		if (chartPanel == null)
			chartPanel = new ChartPanel(chart);
		else {
			chartPanel.setChart(chart);
		}
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		frame_BieuDoCongviec_Grantt.add(chartPanel);
		frame_BieuDoCongviec_Grantt.repaint();
	}

	private JFreeChart createChart(Date Begin, Date End) throws SQLException {

		XYDataset dataset = getDataset(Begin, End);
		JFreeChart chart = ChartFactory.createXYLineChart(null, "Thời gian", "Số lượng công việc", dataset,
				PlotOrientation.VERTICAL, true, false, false);
		/* setupChartPlot */
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(SystemColor.white);
		plot.setForegroundAlpha(1f);
		plot.setNoDataMessage("No data to display");
		/* setupShape */
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesShapesVisible(1, true);
		renderer.setSeriesLinesVisible(2, true);
		renderer.setSeriesShapesVisible(2, true);
		renderer.setSeriesLinesVisible(3, true);
		renderer.setSeriesShapesVisible(3, true);
		renderer.setSeriesPaint(3, Color.cyan);
		renderer.setSeriesLinesVisible(4, true);
		renderer.setSeriesShapesVisible(4, true);
		renderer.setSeriesPaint(4, Color.magenta);
		renderer.setSeriesLinesVisible(5, true);
		renderer.setSeriesShapesVisible(5, true);
		renderer.setBaseShapesVisible(true);
		renderer.setBaseShapesFilled(true);
		plot.setRenderer(renderer);
		DateAxis axis = new DateAxis();// định dạng trục thời gian
		axis.setDateFormatOverride(new MyDateFormat().getSdf_dmy());
		axis.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 10));
		plot.setDomainAxis(axis);
		axis.setAutoTickUnitSelection(false);
		axis.setVerticalTickLabels(true);
		return chart;
	}

	private void createFrame(Composite composite_Xychart) {
		if (frame_BieuDoCongviec_Grantt == null)
			frame_BieuDoCongviec_Grantt = SWT_AWT.new_Frame(composite_Xychart);
		else {
			frame_BieuDoCongviec_Grantt.repaint();
		}
	}

	private XYDataset getDataset(Date Begin, Date End) throws SQLException {

		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		ArrayList<TIMELINE_CONGVIEC> tcl = new TimeLine_Congviec_Builder(user).getHistory_Task(Begin, End);

		TimeSeries CONGVIEC = new TimeSeries("Công việc");
		TimeSeries DEXUAT = new TimeSeries("Tiếp nhận Đề xuất công việc");
		TimeSeries THUCHIEN = new TimeSeries("Tổ chức thực hiện");
		TimeSeries NGHIEMTHU = new TimeSeries("Kiểm tra, Nhiệm thu, Bàn giao");
		TimeSeries QUYETTOAN = new TimeSeries("Quyết toán, Thanh lý hợp đồng");
		long diff = Math.abs(End.getTime() - Begin.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		int Space = (int) diffDays;
		if (tcl != null) {
			Date ThisDate = Begin;
			for (int i = 0; i < Space; i++) {
				int Congviec = 0;
				int Dexuat = 0;
				int Thuchien = 0;
				int Nghiemthu = 0;
				int Quyettoan = 0;
				for (TIMELINE_CONGVIEC tc : tcl) {
					if (tc.getDEXUAT() != null) {
						if (tc.getQUYETTOAN() != null) {
							if ((!ThisDate.before(tc.getDEXUAT().getNGAY_BATDAU()))
									&& (!(tc.getQUYETTOAN().getNGAY_KETTHUC()).before(ThisDate)))
								Congviec += 1;
						} else {
							if ((!ThisDate.before(tc.getDEXUAT().getNGAY_BATDAU()))
									&& (!(tc.getTHUCHIEN().getNGAY_CHUYENGIAO()).before(ThisDate)))
								Congviec += 1;
						}
					}
					if (tc.getDEXUAT() != null) {
						if ((!ThisDate.before(tc.getDEXUAT().getNGAY_BATDAU()))
								&& (!(tc.getDEXUAT().getNGAY_CHUYENGIAO()).before(ThisDate))) {
							Dexuat += 1;
						}
					}
					if (tc.getTHUCHIEN() != null)
						if ((!ThisDate.before(tc.getTHUCHIEN().getNGAY_BATDAU()))
								&& (!(tc.getTHUCHIEN().getNGAY_CHUYENGIAO().before(ThisDate)))) {
							Thuchien += 1;
						}
					if (tc.getNGHIEMTHU() != null)
						if ((!ThisDate.before(tc.getNGHIEMTHU().getNGAY_BATDAU()))
								&& (!(tc.getNGHIEMTHU().getNGAY_CHUYENGIAO()).before(ThisDate)))
							Nghiemthu += 1;
					if (tc.getQUYETTOAN() != null)
						if (!ThisDate.before(tc.getQUYETTOAN().getNGAY_BATDAU())
								&& (!(tc.getQUYETTOAN().getNGAY_KETTHUC()).before(ThisDate)))
							Quyettoan += 1;
				}
				CONGVIEC.add(new Day(ThisDate), Congviec);
				DEXUAT.add(new Day(ThisDate), Dexuat);
				THUCHIEN.add(new Day(ThisDate), Thuchien);
				NGHIEMTHU.add(new Day(ThisDate), Nghiemthu);
				QUYETTOAN.add(new Day(ThisDate), Quyettoan);
				ThisDate = addDate(ThisDate, 1);
			}
		}
		dataSet.addSeries(CONGVIEC);
		dataSet.addSeries(DEXUAT);
		dataSet.addSeries(THUCHIEN);
		dataSet.addSeries(NGHIEMTHU);
		dataSet.addSeries(QUYETTOAN);
		return dataSet;
	}

	Date addDate(Date thisDate, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
}
