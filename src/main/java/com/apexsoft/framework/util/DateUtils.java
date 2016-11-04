package com.apexsoft.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm z";

	public static Date getDate(String dateInString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date date = null;
		try {
			date = dateFormat.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param dateInString "yyyy-MM-dd HH:mm (z)"
	 * @param timeZoneId
	 * @return
	 */
	public static String convertTimeZone(String dateInString, String timeZoneId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			Date date = dateFormat.parse(dateInString);

			dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateInString;
	}

	/**
	 * @param dateInString "yyyy-MM-dd HH:mm (z)"
	 * @param format
	 * @return
	 */
	public static String convertFormat(String dateInString, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			Date date = dateFormat.parse(dateInString);

			SimpleDateFormat outDateFormat = new SimpleDateFormat(format);
			outDateFormat.setTimeZone(dateFormat.getTimeZone());
			return outDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateInString;
	}

	/**
	 * @param dateInString "yyyy-MM-dd HH:mm (z)"
	 * @param timeZoneId
	 * @param format
	 * @return
	 */
	public static String convertTimeZoneAndFormat(String dateInString, String timeZoneId, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			Date date = dateFormat.parse(dateInString);

			SimpleDateFormat outDateFormat = new SimpleDateFormat(format);
			outDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
			return outDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateInString;
	}
}
