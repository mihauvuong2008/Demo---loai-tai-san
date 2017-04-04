package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DAO.LOAITAISAN_CAP_I;
import DAO.LOAITAISAN_CAP_II;
import DAO.LOAITAISAN_CAP_III;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_LoaiTaisan {
	private Workbook workbook;

	public ExcelReader_LoaiTaisan(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<Object> getData() {
		ArrayList<Object> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];
		int Keylv1 = 0;
		int Keylv2 = 0;
		int Keylv3 = 0;
		for (int row = 0; row < sheet.getRows(); row++) {
			Cell cell = sheet.getCell(0, row);
			if (cell.getContents().equals("1")) {
				Keylv1++;
				LOAITAISAN_CAP_I n = new LOAITAISAN_CAP_I();
				n.setMA_LOAITAISAN_CAP_I(Keylv1);
				n.setTEN_LOAITAISAN_CAP_I(sheet.getCell(1, row).getContents());
				result.add(n);
				System.out.println(sheet.getCell(1, row).getContents());
			} else if (cell.getContents().equals("2")) {
				Keylv2++;
				LOAITAISAN_CAP_II n = new LOAITAISAN_CAP_II();
				n.setMA_LOAITAISAN_CAP_II(Keylv2);
				n.setTEN_LOAITAISAN_CAP_II(sheet.getCell(1, row).getContents());
				n.setMA_LOAITAISAN_CAP_I(Keylv1);
				result.add(n);
			} else if (cell.getContents().equals("3")) {
				Keylv3++;
				LOAITAISAN_CAP_III n = new LOAITAISAN_CAP_III();
				n.setMA_LOAITAISAN_CAP_III(Keylv3);
				n.setTEN_LOAITAISAN_CAP_III(sheet.getCell(1, row).getContents());
				n.setMA_LOAITAISAN_CAP_II(Keylv2);
				result.add(n);
			}
		}
		return result;
	}
}
