package View.AssetManagers.Hoso;

import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Controler.Controler;
import DAO.FILESCAN;
import DAO.NGUOIDUNG;
import DAO.VANBAN;
import View.DateTime.MyDateFormat;

public class BeanRealator {
	private String socongvan;
	private String coquanbanhanh;
	private String ngaythangVanban;
	private String trichyeu;
	private String macongvan;
	private List<ImageBean> data = new ArrayList<ImageBean>();

	public class ImageBean {
		Image data;

		public ImageBean(Image data) {
			this.data = data;
		}

		public Image getData() {
			return data;
		}

		public void setData(Image data) {
			this.data = data;
		}
	}

	public BeanRealator(VANBAN vb, NGUOIDUNG user) throws SQLException, IOException {
		if (user != null) {
			socongvan = vb.getSO_VANBAN();
			coquanbanhanh = vb.getCO_QUAN_BAN_HANH();
			ngaythangVanban = new MyDateFormat().getViewStringDate(vb.getNGAY_BAN_HANH());
			trichyeu = vb.getTRICH_YEU();
			macongvan = vb.getMA_VANBAN() + "";
			ArrayList<FILESCAN> datal = new Controler(user).getControl_FILESCAN().get_IMAGE_l(vb);
			if (datal != null) {
				for (int i = 0; i < datal.size(); i++) {
					data.add(new ImageBean(ImageIO.read(datal.get(i).getImage())));
				}
			}
		}
	}

	public final String getSocongvan() {
		return socongvan;
	}

	public final String getCoquanbanhanh() {
		return coquanbanhanh;
	}

	public final String getNgaythangVanban() {
		return ngaythangVanban;
	}

	public final String getTrichyeu() {
		return trichyeu;
	}

	public final String getMacongvan() {
		return macongvan;
	}

	public final List<ImageBean> getData() {
		return data;
	}

}
