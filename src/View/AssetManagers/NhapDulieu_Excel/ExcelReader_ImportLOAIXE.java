package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import DAO.LOAI_XE;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_ImportLOAIXE {

	private Workbook workbook;

	public ExcelReader_ImportLOAIXE(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<LOAI_XE> getData() {
		ArrayList<LOAI_XE> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];

		for (int row = 0; row < sheet.getRows(); row++) {
			for (int col = 0; col < sheet.getColumns(); col++) {
				System.out.print(" (" + col + " ) - " + sheet.getCell(col, row).getContents() + " - ");
			}
			System.out.println();
			if (row > 0) {
				LOAI_XE lx = new LOAI_XE();
				lx.setHANG_SAN_XUAT(sheet.getCell(0, row).getContents());
				lx.setTEN_DONG_XE(sheet.getCell(1, row).getContents());
				lx.setOTO_XEMAY(Integer.valueOf(sheet.getCell(2, row).getContents()));
				lx.setDINH_MUC_XANG_DAU(Double.valueOf(sheet.getCell(3, row).getContents()));
				result.add(lx);
			}
		}
		return result;
	}

}
