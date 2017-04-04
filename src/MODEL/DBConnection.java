package MODEL;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Application.Setting.CipherPassword;
import Application.Setting.HostSetting;
import CONFIG.ConfigReader;
import CONFIG.keyy;

public class DBConnection {
	private Connection conn;

	public DBConnection() {
	}

	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ConfigReader CR = new ConfigReader(Paths.get("./config/user/user.xml").toString(),
					Paths.get("./config/host/host.xml").toString());
			HostSetting hs = CR.getHostSetting();
			CipherPassword cp = new CipherPassword();
			String password = cp.decrypt(new keyy().getKey(), hs.getInitVector(), hs.getPassword());
			conn = DriverManager.getConnection("jdbc:mysql://" + hs.getIp() + ":" + hs.getPort() + "/quanlyptts?useSSL="
					+ String.valueOf(hs.isSsl()) + "", "root", password);
		} catch (Exception e) {
			conn = null;
		}
		return conn;
	}

	public void CloseConn() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
