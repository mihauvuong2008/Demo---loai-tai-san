package View.AssetManagers.excel_XuatDulieu;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelWriter_ExportPTGT {

	private WritableWorkbook writeWorkbook;

	public ExcelWriter_ExportPTGT(String fileName) throws BiffException, IOException, WriteException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("Cp1252");
		writeWorkbook = Workbook.createWorkbook(new File(fileName));
		writeWorkbook.createSheet("PhuongtienGiaothong", 0);
	}

	private WritableSheet getSheet() {
		return writeWorkbook.getSheet("PhuongtienGiaothong");
	}

	class ExcelOutputMeta {
		public static final int FONT_ALIGNMENT_LEFT = 1;
		public static final int FONT_ALIGNMENT_RIGHT = 2;
		public static final int FONT_ALIGNMENT_CENTER = 3;
		public static final int FONT_ALIGNMENT_FILL = 4;
		public static final int FONT_ALIGNMENT_GENERAL = 5;
		public static final int FONT_ALIGNMENT_JUSTIFY = 6;
	}

	public WritableCellFormat getAlignment(int stepValue, WritableCellFormat format) throws WriteException {
		if (stepValue != ExcelOutputMeta.FONT_ALIGNMENT_LEFT) {
			switch (stepValue) {
			case ExcelOutputMeta.FONT_ALIGNMENT_RIGHT:
				format.setAlignment(jxl.format.Alignment.RIGHT);
				break;
			case ExcelOutputMeta.FONT_ALIGNMENT_CENTER:
				format.setAlignment(jxl.format.Alignment.CENTRE);
				break;
			case ExcelOutputMeta.FONT_ALIGNMENT_FILL:
				format.setAlignment(jxl.format.Alignment.FILL);
				break;
			case ExcelOutputMeta.FONT_ALIGNMENT_GENERAL:
				format.setAlignment(jxl.format.Alignment.GENERAL);
				break;
			case ExcelOutputMeta.FONT_ALIGNMENT_JUSTIFY:
				format.setAlignment(jxl.format.Alignment.JUSTIFY);
				break;
			default:
				break;
			}
		}
		return format;
	}

	public void setData(ColumnKeyCollector ckc, excel_row er) throws RowsExceededException, WriteException {
		addHeader("STT", 0, 0);
		for (int i = 0; i < ckc.columnCollector.size(); i++) {
			addHeader(ckc.columnCollector.get(i), i + 1, 0);
		}
		int x = 1;
		for (Map.Entry<String, Column_Collec> r : er.row.entrySet()) {
			addLabel(x + "", 0, x);
			int i = 1;
			for (String s : ckc.getColumnCollector()) {
				addLabel(((String) r.getValue().getColumn(s)), i, x);
				i++;
			}
			x++;
		}
	}

	private void addHeader(String string, int c, int r) throws RowsExceededException, WriteException {
		// Create cell font and format
		WritableFont cellFont = new WritableFont(WritableFont.TIMES, 10);
		cellFont.setColour(Colour.BLACK);
		cellFont.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setBackground(Colour.GRAY_25);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		getSheet().setRowView(0, 320);
		getSheet().addCell((WritableCell) new Label(c, r, string, cellFormat));

	}

	private void addLabel(String string, int c, int r) throws RowsExceededException, WriteException {
		// Create cell font and format
		WritableFont cellFont = new WritableFont(WritableFont.TIMES, 11);
		cellFont.setColour(Colour.BLACK);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setBackground(Colour.WHITE);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		getSheet().setRowView(r, 300);
		getSheet().addCell((WritableCell) new Label(c, r, string, cellFormat));

	}

	public void Write() {

	}

	public void close() throws WriteException, IOException {
		if (writeWorkbook != null) {
			writeWorkbook.write();
			writeWorkbook.close();
		}
	}

}
