package Application.Setting;

public class HostSetting {
	private String ip;
	private int port;
	private boolean ssl;
	private int TimeDelay;
	private String Password;
	private String password_session;

	public HostSetting(String ip, int port, boolean ssl, int TimeDelay, String Password, String password_session) {
		this.ip = ip;
		this.port = port;
		this.ssl = ssl;
		this.TimeDelay = TimeDelay;
		this.Password = Password;
		this.password_session = password_session;
	}

	public final String getIp() {
		return ip;
	}

	public final int getPort() {
		return port;
	}

	public final boolean isSsl() {
		return ssl;
	}

	public final int getTimeDelay() {
		return TimeDelay;
	}

	public final String getPassword() {
		return Password;
	}

	public final void setIp(String ip) {
		this.ip = ip;
	}

	public final void setPort(int port) {
		this.port = port;
	}

	public final void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public final void setTimeDelay(int timeDelay) {
		TimeDelay = timeDelay;
	}

	public final void setPassword(String password) {
		Password = password;
	}

	public final String getInitVector() {
		return password_session;
	}

	public final void setInitVector(String initVector) {
		this.password_session = initVector;
	}
}
