package View.AssetManagers.NhapDulieu_Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class ExcelReader_ImportPTGT {

	private Workbook workbook;

	public ExcelReader_ImportPTGT(String fileName) throws BiffException, IOException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		workbook = Workbook.getWorkbook(new File(fileName), ws);
	}

	private Sheet[] getSheet() {
		return workbook.getSheets();
	}

	public ArrayList<ExcelRow_PTGT> getData() {
		ArrayList<ExcelRow_PTGT> result = new ArrayList<>();
		Sheet sheet = getSheet()[0];

		for (int row = 0; row < sheet.getRows(); row++) {
			for (int col = 0; col < sheet.getColumns(); col++) {
				System.out.print(" (" + col + " ) - " + sheet.getCell(col, row).getContents() + " - ");
			}
			System.out.println();
			if (row > 1) {
				if (!(sheet.getCell(7, row).getContents().equals(null)
						|| sheet.getCell(7, row).getContents().equals(""))) {
					ExcelRow_PTGT ep = new ExcelRow_PTGT();
					ep.setTenPTGT(sheet.getCell(1, row).getContents().trim());
					ep.setBienso(sheet.getCell(2, row).getContents().trim());
					ep.setNamSudung(Integer.valueOf(sheet.getCell(3, row).getContents().trim()));
					ep.setSokhung(sheet.getCell(4, row).getContents().trim());
					ep.setSomay(sheet.getCell(5, row).getContents().trim());
					ep.setMaTaisanLienket(sheet.getCell(7, row).getContents().trim());
					result.add(ep);
				}
			}
		}
		return result;
	}

	public class ExcelRow_PTGT {
		String TenPTGT;
		String Bienso;
		int NamSudung;
		String Sokhung;
		String Somay;
		String MaTaisanLienket;

		public final String getTenPTGT() {
			return TenPTGT;
		}

		public final void setTenPTGT(String tenPTGT) {
			TenPTGT = tenPTGT;
		}

		public final String getBienso() {
			return Bienso;
		}

		public final void setBienso(String bienso) {
			Bienso = bienso;
		}

		public final int getNamSudung() {
			return NamSudung;
		}

		public final void setNamSudung(int namSudung) {
			NamSudung = namSudung;
		}

		public final String getSokhung() {
			return Sokhung;
		}

		public final void setSokhung(String sokhung) {
			Sokhung = sokhung;
		}

		public final String getSomay() {
			return Somay;
		}

		public final void setSomay(String somay) {
			Somay = somay;
		}

		public final String getMaTaisanLienket() {
			return MaTaisanLienket;
		}

		public final void setMaTaisanLienket(String maTaisanLienket) {
			MaTaisanLienket = maTaisanLienket;
		}

	}
}
