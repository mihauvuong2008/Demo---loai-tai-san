package View.AssetManagers.Taisan.Phuongtiengiaothong.LichBaoduong;

public class HinhthucBaoduong {
	private final int index;
	private final String title;
	private String SQL_NAME;
	private int Data;

	public HinhthucBaoduong(int index, String title) {
		this.index = index;
		this.title = title;
	}

	public final int getData() {
		return Data;
	}

	public final void setData(int data) {
		Data = data;
	}

	public final int getIndex() {
		return index;
	}

	public final String getTitle() {
		return title;
	}

	public final String getSQL_NAME() {
		return SQL_NAME;
	}

	public final void setSQL_NAME(String sQL_NAME) {
		SQL_NAME = sQL_NAME;
	}

}
