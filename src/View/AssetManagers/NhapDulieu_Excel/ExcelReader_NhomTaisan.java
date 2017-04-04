package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DAO.NHOMTAISAN_CAP_I;
import DAO.NHOMTAISAN_CAP_II;
import DAO.NHOMTAISAN_CAP_III;
import DAO.NHOMTAISAN_CAP_IV;
import DAO.NHOMTAISAN_CAP_V;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_NhomTaisan {
	private Workbook workbook;

	public ExcelReader_NhomTaisan(String fileName) throws BiffException, IOException {
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
		int Keylv4 = 0;
		int Keylv5 = 0;
		for (int row = 0; row < sheet.getRows(); row++) {
			Cell cell = sheet.getCell(0, row);
			if (cell.getContents().equals("1")) {
				Keylv1++;
				NHOMTAISAN_CAP_I n = new NHOMTAISAN_CAP_I();
				n.setMA_NHOMTAISAN_CAP_I(Keylv1);
				n.setTEN_NHOMTAISAN_CAP_I(sheet.getCell(1, row).getContents());
				System.out.println(sheet.getCell(0, row).getContents() + " " + sheet.getCell(1, row).getContents());
				result.add(n);
			} else if (cell.getContents().equals("2")) {
				Keylv2++;
				NHOMTAISAN_CAP_II n = new NHOMTAISAN_CAP_II();
				n.setMA_NHOMTAISAN_CAP_II(Keylv2);
				n.setTEN_NHOMTAISAN_CAP_II(sheet.getCell(1, row).getContents());
				n.setMA_NHOMTAISAN_CAP_I(Keylv1);
				result.add(n);
			} else if (cell.getContents().equals("3")) {
				Keylv3++;
				NHOMTAISAN_CAP_III n = new NHOMTAISAN_CAP_III();
				n.setMA_NHOMTAISAN_CAP_III(Keylv3);
				n.setTEN_NHOMTAISAN_CAP_III(sheet.getCell(1, row).getContents());
				n.setMA_NHOMTAISAN_CAP_II(Keylv2);
				result.add(n);
			} else if (cell.getContents().equals("4")) {
				Keylv4++;
				NHOMTAISAN_CAP_IV n = new NHOMTAISAN_CAP_IV();
				n.setMA_NHOMTAISAN_CAP_IV(Keylv4);
				n.setTEN_NHOMTAISAN_CAP_IV(sheet.getCell(1, row).getContents());
				n.setMA_NHOMTAISAN_CAP_III(Keylv3);
				result.add(n);
			} else if (cell.getContents().equals("5")) {
				Keylv5++;
				NHOMTAISAN_CAP_V n = new NHOMTAISAN_CAP_V();
				n.setMA_NHOMTAISAN_CAP_V(Keylv5);
				n.setTEN_NHOMTAISAN_CAP_V(sheet.getCell(1, row).getContents());
				n.setTHOIGIAN_SUDUNG(Integer.valueOf(sheet.getCell(2, row).getContents()));
				n.setTILE_HAOMON(Double.valueOf(sheet.getCell(3, row).getContents()));
				n.setMA_NHOMTAISAN_CAP_IV(Keylv4);
				result.add(n);
			}
		}
		return result;
	}

}
