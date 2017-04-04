package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DAO.CHUKY_DANGKIEM;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_ImportCHUKY_DANGKIEM {

	private Workbook workbook;

	public ExcelReader_ImportCHUKY_DANGKIEM(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<CHUKY_DANGKIEM> getData() {
		ArrayList<CHUKY_DANGKIEM> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];

		for (int row = 0; row < sheet.getRows(); row++) {
			for (int col = 0; col < sheet.getColumns(); col++) {
				System.out.print(" (" + col + " ) - " + sheet.getCell(col, row).getContents() + " - ");
			}
			if (row > 0) {
				CHUKY_DANGKIEM lx = new CHUKY_DANGKIEM();
				lx.setTEN_KYHAN(sheet.getCell(0, row).getContents().trim());
				try {
					lx.setCHU_KY_DAU(Integer.valueOf(sheet.getCell(1, row).getContents().trim()));
				} catch (Exception e) {
					lx.setCHU_KY_DAU(0);
				}
				try {
					lx.setCHU_KY(Integer.valueOf(sheet.getCell(2, row).getContents().trim()));
				} catch (Exception e) {
					lx.setCHU_KY(0);
				}
				result.add(lx);
			}
		}
		return result;
	}

}
