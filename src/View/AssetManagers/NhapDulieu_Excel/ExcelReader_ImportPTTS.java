package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import DAO.TAISAN;
import View.DateTime.MyDateFormat;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_ImportPTTS {
	private Workbook workbook;
	private MyDateFormat mdf = new MyDateFormat();

	public ExcelReader_ImportPTTS(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<TAISAN> getData() {
		ArrayList<TAISAN> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];

		for (int row = 0; row < sheet.getRows(); row++) {
			if (row > 8) {
				if (sheet.getCell(2, row).getContents().equals(""))
					break;
				TAISAN t = new TAISAN();
				t.setTEN_TAISAN(sheet.getCell(2, row).getContents().trim());
				t.setMA_TANSAN_KETOAN(sheet.getCell(4, row).getContents().trim());
				Date d = null;
				try {
					d = mdf.getDate_FromStringYear(sheet.getCell(6, row).getContents());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				t.setNGAY_SU_DUNG(d);
				t.setGHI_CHU(sheet.getCell(7, row).getContents().trim());
				int Sl = 0;
				try {
					Sl = Integer.parseInt(sheet.getCell(9, row).getContents().trim());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				t.setSOLUONG(Sl);
				long NguyenGia = 0;
				try {
					NguyenGia = Long.parseLong(sheet.getCell(10, row).getContents().trim());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				t.setNGUYEN_GIA(NguyenGia);
				result.add(t);
			}
		}
		return result;
	}
}
