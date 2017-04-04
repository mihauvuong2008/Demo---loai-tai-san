package Application.Setting;

import java.util.ArrayList;

public class userSetting {
	private boolean centerScreen = true;
	private boolean savePassword = false;
	private ArrayList<Object> lastSession;

	public void loadSetting() {
	}

	public final boolean isCenterScreen() {
		return centerScreen;
	}

	public final boolean isSavePassword() {
		return savePassword;
	}

	public final ArrayList<Object> getLastSession() {
		return lastSession;
	}
}
