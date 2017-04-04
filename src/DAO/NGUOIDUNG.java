package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import Control.ROLE.PrivilegeChecker;

public class NGUOIDUNG {
	private String TEN_TAI_KHOAN;
	private String MAT_KHAU;
	private String TEN_CAN_BO;
	private String GIOI_THIEU;
	private int MA_PHONGBAN;
	PrivilegeChecker privilegeChecker;
	private ArrayList<ROLE> rolelist;
	Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getTEN_TAI_KHOAN() {
		return TEN_TAI_KHOAN;
	}

	public void setTEN_TAI_KHOAN(String tEN_TAI_KHOAN) {
		TEN_TAI_KHOAN = tEN_TAI_KHOAN;
	}

	public String getMAT_KHAU() {
		return MAT_KHAU;
	}

	public void setMAT_KHAU(String mAT_KHAU) {
		MAT_KHAU = mAT_KHAU;
	}

	public String getTEN_CAN_BO() {
		return TEN_CAN_BO;
	}

	public void setTEN_CAN_BO(String tEN_CAN_BO) {
		TEN_CAN_BO = tEN_CAN_BO;
	}

	public String getGIOI_THIEU() {
		return GIOI_THIEU;
	}

	public void setGIOI_THIEU(String gIOI_THIEU) {
		GIOI_THIEU = gIOI_THIEU;
	}

	public int getMA_PHONGBAN() {
		return MA_PHONGBAN;
	}

	public void setMA_PHONGBAN(int mA_PHONGBAN) {
		MA_PHONGBAN = mA_PHONGBAN;
	}

	public ArrayList<ROLE> getRolelist() {
		return rolelist;
	}

	public void setRolelist(ArrayList<ROLE> rolelist) {
		this.rolelist = rolelist;
	}

	public final PrivilegeChecker getPrivilegeChecker() {
		return privilegeChecker;
	}

	public final void setPrivilegeChecker(PrivilegeChecker privilegeChecker) {
		this.privilegeChecker = privilegeChecker;
	}

}
