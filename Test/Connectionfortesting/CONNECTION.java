package Connectionfortesting;

import java.sql.Connection;
import java.sql.DriverManager;

import Control.ROLE.PrivilegeChecker;
import DAO.NGUOIDUNG;

public class CONNECTION {
	public NGUOIDUNG getUser() {
		NGUOIDUNG rs = new NGUOIDUNG();
		rs.setTEN_TAI_KHOAN("tester01");
		rs.setConn(getConn());
		rs.setPrivilegeChecker(new PrivilegeChecker(rs));
		return rs;
	}

	public Connection getConn() {
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyptts?useSSL=true", "root", "638402");
		} catch (Exception e) {
			conn = null;
		}
		return conn;
	}
}
