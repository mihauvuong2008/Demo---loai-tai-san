package View.AssetManagers.Hoso;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Relator {
	public void getRelator(ArrayList<BeanRealator> vanban) throws JRException {
		InputStream arg = Relator.class.getResourceAsStream("/Report/report2.jrxml");
		JasperReport repot = JasperCompileManager.compileReport(arg);
		JRBeanCollectionDataSource itemImageBean = new JRBeanCollectionDataSource(vanban.get(0).getData());
		Map<String, Object> paramater = new HashMap<String, Object>();
		paramater.put("ItemdataSource", itemImageBean);
		JasperPrint print = JasperFillManager.fillReport(repot, paramater, getDataSource(vanban));
		// JasperViewer.viewReport(print, false);
		JasperViewer jv = new JasperViewer(print, false);
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File("./img/document-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jv.setIconImage(im);
		jv.setTitle("Xuất file văn bản");
		jv.setVisible(true);
	}

	private static JRDataSource getDataSource(ArrayList<BeanRealator> vanban) {
		BeanRealator vanban_ = vanban.get(0);
		Collection<BeanRealator> coll = new ArrayList<BeanRealator>();
		coll.add(vanban_);
		return new JRBeanCollectionDataSource(coll);
	}

}
