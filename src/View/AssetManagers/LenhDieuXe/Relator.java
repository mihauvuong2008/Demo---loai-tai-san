package View.AssetManagers.LenhDieuXe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

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
	public void getRelator(ArrayList<BeanRealator> ldx) throws JRException {
		InputStream arg = Relator.class.getResourceAsStream("/Report/report3.jrxml");
		JasperReport repot = JasperCompileManager.compileReport(arg);
		JasperPrint print = JasperFillManager.fillReport(repot, null, getDataSource(ldx));
		JasperViewer jv = new JasperViewer(print, false);
		// JasperViewer.viewReport(print, false);
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File("./img/document-icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jv.setIconImage(im);
		jv.setTitle("Xuất Lệnh điều xe");
		jv.setVisible(true);
	}

	private static JRDataSource getDataSource(ArrayList<BeanRealator> ldx) {
		BeanRealator ldx_ = ldx.get(0);
		Collection<BeanRealator> coll = new ArrayList<BeanRealator>();
		coll.add(ldx_);
		return new JRBeanCollectionDataSource(coll);
	}

}
