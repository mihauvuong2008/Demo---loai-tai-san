package View.AssetManagers.excel_XuatDulieu;

import java.util.HashMap;

public class excel_row {
	HashMap<String, Column_Collec> row;

	public excel_row() {
		row = new HashMap<>();
	}

	public void addRow(String key, Column_Collec cc) {
		row.put(key, cc);
	}

	public Column_Collec getRow(String key) {
		return row.get(key);
	}

	public void removeRow(String key) {
		row.remove(key);
	}

}
