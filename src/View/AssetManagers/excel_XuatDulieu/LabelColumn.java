package View.AssetManagers.excel_XuatDulieu;

public class LabelColumn {
	private String Name;
	private String Data;

	public LabelColumn(String string, String string2) {
		this.Name = string;
		this.Data = string2;
	}

	public final String getName() {
		return Name;
	}

	public final void setName(String name) {
		Name = name;
	}

	public final String getData() {
		return Data;
	}

	public final void setData(String data) {
		Data = data;
	}

}