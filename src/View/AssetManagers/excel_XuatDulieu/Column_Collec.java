package View.AssetManagers.excel_XuatDulieu;

import java.util.HashMap;

public class Column_Collec {
	HashMap<String, String> row;

	public Column_Collec() {
		row = new HashMap<>();
	}

	public void addColumn(String key, String data) {
		row.put(key, data);
	}

	public String getColumn(String key) {
		return row.get(key);
	}

	public String delColumn(String key) {
		return row.remove(key);
	}

	public int ColumnCount() {
		return row.size();
	}
}
