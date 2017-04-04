package View.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.DateTime;

public class MyDateFormat {
	SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf_ymdhhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf_dmy = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf_dmyhhmmss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	SimpleDateFormat sdf_hhmmss = new SimpleDateFormat("hh:mm:ss");
	SimpleDateFormat sdf_yyyy = new SimpleDateFormat("yyyy");

	public final SimpleDateFormat getSdf_dmy() {
		return sdf_dmy;
	}

	public final void setSdf_dmy(SimpleDateFormat sdf_dmy) {
		this.sdf_dmy = sdf_dmy;
	}

	public String getSQLStringDate(Date date) {
		return sdf_ymd.format(date);
	}

	public String getSQLStringDateTime(Date date) {
		return sdf_ymdhhmmss.format(date);
	}

	public String getViewStringDate(Date date) {
		return sdf_dmy.format(date);
	}

	public String getViewStringDateTime(Date date) {
		return sdf_dmyhhmmss.format(date);
	}

	public String getViewStringTime(Date date) {
		return sdf_hhmmss.format(date);
	}

	public Date getDateFromSQLString(String SQLString) throws ParseException {
		return sdf_ymd.parse(SQLString);
	}

	public Date getDateTimeFromSQLString(String SQLString) throws ParseException {
		return sdf_ymdhhmmss.parse(SQLString);
	}

	public Date getDateFromVIEWString(String VIEWString) throws ParseException {
		return sdf_dmy.parse(VIEWString);
	}

	public Date getDateTimeFromVIEWString(String VIEWString) throws ParseException {
		return sdf_dmyhhmmss.parse(VIEWString);
	}

	public Date getDate(DateTime dateTime) {
		if (dateTime != null) {
			return date(dateTime.getDay(), dateTime.getMonth(), dateTime.getYear());
		}
		return null;
	}

	public Date date(final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		final Date result = calendar.getTime();
		return result;

	}

	public Date date(final int hour, final int minus, final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minus, 0);
		final Date result = calendar.getTime();
		return result;

	}

	public int getTongNgaythuchien(Date Begin, Date End) {
		if (Begin == null || End == null) {
			return 0;
		}
		long tmp = (End.getTime() - Begin.getTime()) / (1000 * 60 * 60 * 24);
		return (int) tmp;
	}

	public int getDay(Date ngay_SU_DUNG) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay_SU_DUNG);
		return calendar.get(Calendar.DATE);
	}

	public int getMonth(Date ngay_SU_DUNG) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay_SU_DUNG);
		return calendar.get(Calendar.MONTH);
	}

	public int getYear(Date ngay_SU_DUNG) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay_SU_DUNG);
		return calendar.get(Calendar.YEAR);
	}

	public String getViewStringDateTime_hma_dmy(Date ngay_THONGBAO) {
		return new SimpleDateFormat("hh:mm a, dd-MM-yyyy").format(ngay_THONGBAO);
	}

	public Date getDate_FromStringYear(String contents) throws ParseException {
		return sdf_yyyy.parse(contents);
	}

	public String getString_FromDateYear(Date date) throws ParseException {
		return sdf_yyyy.format(date);
	}

	public String getViewStringDateTime_hm_dmy(Date thoigian) {
		return new SimpleDateFormat("hh:mm dd-MM-yyyy").format(thoigian);
	}

	public Date addDate(Date date, int day) {
		Date result;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		result = cal.getTime();
		return result;
	}

	public Date getDate(DateTime dateTime_time, DateTime dateTime_Ngay) {
		if (dateTime_Ngay != null) {
			return date(dateTime_time.getHours(), dateTime_time.getMinutes(), dateTime_Ngay.getDay(),
					dateTime_Ngay.getMonth(), dateTime_Ngay.getYear());
		}
		return null;
	}

	public Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
