package DAO;

import java.io.InputStream;
import java.util.Date;

public class FILESCAN {
	private int image_id;
	private InputStream image;
	private String PathOfImage;
	private long image_size;
	private String image_name;
	private Date Ngaytao;
	private int Stt;
	private int MA_VANBAN;

	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public String getPathOfImage() {
		return PathOfImage;
	}

	public void setPathOfImage(String pathOfImage) {
		PathOfImage = pathOfImage;
	}

	public long getImage_size() {
		return image_size;
	}

	public void setImage_size(long image_size) {
		this.image_size = image_size;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public int getMA_VANBAN() {
		return MA_VANBAN;
	}

	public void setMA_VANBAN(int mA_VANBAN) {
		MA_VANBAN = mA_VANBAN;
	}

	public Date getNgaytao() {
		return Ngaytao;
	}

	public void setNgaytao(Date ngaytao) {
		Ngaytao = ngaytao;
	}

	public int getStt() {
		return Stt;
	}

	public void setStt(int stt) {
		Stt = stt;
	}
}
