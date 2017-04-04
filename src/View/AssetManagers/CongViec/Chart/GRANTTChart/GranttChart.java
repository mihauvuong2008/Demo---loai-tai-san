package View.AssetManagers.CongViec.Chart.GRANTTChart;

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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;

import DAO.NGUOIDUNG;

public class GranttChart {
	private final NGUOIDUNG user;
	private Frame frame_BieuDoCongviec_Grantt;
	private ChartPanel cp;

	public GranttChart(NGUOIDUNG user) {
		this.user = user;
	}

	public void Draw(Composite composite_BieuDo_Grantt, int oPTION) throws SQLException {
		creatFrame(composite_BieuDo_Grantt);
		JFreeChart chart_Congviec = createJFreeChart(oPTION);
		addChartPanel_To_Frame(chart_Congviec);
	}

	private JFreeChart createJFreeChart(int oPTION) throws SQLException {
		IntervalCategoryDataset dataset_DulieuCongviec = createDataset(oPTION);
		JFreeChart chart_Congviec = createGanttChart(dataset_DulieuCongviec, "Quá trình triển khai");
		return chart_Congviec;
	}

	private void creatFrame(Composite composite_BieuDo_Grantt) {
		if (frame_BieuDoCongviec_Grantt == null)
			frame_BieuDoCongviec_Grantt = SWT_AWT.new_Frame(composite_BieuDo_Grantt);
		else {
			frame_BieuDoCongviec_Grantt.repaint();
		}
	}

	private void addChartPanel_To_Frame(JFreeChart chart_Congviec) {
		if (cp == null)
			cp = new ChartPanel(chart_Congviec);
		else {
			cp.setChart(chart_Congviec);
		}
		cp.setRangeZoomable(false);
		cp.setMouseZoomable(true);
		cp.setForeground(java.awt.Color.BLUE);
		if (frame_BieuDoCongviec_Grantt.getComponentCount() <= 0)
			frame_BieuDoCongviec_Grantt.add(cp);
		frame_BieuDoCongviec_Grantt.repaint();
	}

	public void reDraw() {
		if (frame_BieuDoCongviec_Grantt != null)
			frame_BieuDoCongviec_Grantt.repaint();
	}

	private JFreeChart createGanttChart(IntervalCategoryDataset dataset, String title) {
		final JFreeChart chart = ChartFactory.createGanttChart(null/* title */,
				"Công việc" /* domain axis label */,
				"Thời gian" /* range axis label */, dataset /* data */,
				true /* include legend */, true /* tooltips */, false /* urls */
		);
		// chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// set background (set outline: setOutlineVisi...)
		plot.setBackgroundPaint(SystemColor.white);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		// plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0f);
		final CategoryItemRenderer renderer = plot.getRenderer();
		// chọn màu cho các task
		renderer.setSeriesPaint(0, SystemColor.RED);
		renderer.setSeriesPaint(1, SystemColor.green);
		renderer.setSeriesPaint(2, SystemColor.blue);
		// add the chart to a panel...
		return chart;
	}

	@SuppressWarnings("unused")
	private static Date date(final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;
	}

	private IntervalCategoryDataset createDataset(int oPTION) throws SQLException {
		final TaskSeriesCollection collection = new TaskSeriesCollection();
		Build_Task bt = new Build_Task(user);
		ArrayList<Object> dataset = bt.CongViec_ChuaHoanthanh(oPTION);
		collection.add(bt.getTHUCHIEN_Task(dataset));
		collection.add(bt.getNGHIEMTHU_Task(dataset));
		collection.add(bt.getQUYETTOAN_Task(dataset));
		System.out.println("coll: " + dataset.size());
		return collection;
	}

	protected void Hightlight_Chart(String key, Object object_Chart) {
		// TODO Auto-generated method stub
	}
}
