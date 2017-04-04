package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_ImportPTTS_getDONVI_SUDUNG {

	private Workbook workbook;

	public ExcelReader_ImportPTTS_getDONVI_SUDUNG(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<String> getData() {
		ArrayList<String> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];

		for (int row = 0; row < sheet.getRows(); row++) {
			String line = sheet.getCell(1, row).getContents();
			result.add(line.trim());
		}
		return result;
	}
}
