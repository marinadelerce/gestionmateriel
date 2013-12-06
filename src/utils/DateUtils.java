package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
	/**
	 * Convert date.
	 *
	 * @param date the date
	 * @return the gregorian calendar
	 * @throws ParseException the parse exception
	 */
	public static GregorianCalendar convertDate(String date) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date oldDate = format.parse(date);
		GregorianCalendar newDate = new GregorianCalendar();
		newDate.setTime(oldDate);
		
		return newDate;
	}
	
	public static String dateToString(GregorianCalendar date){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dateDate = date.getTime();
		 
		return dateFormat.format(dateDate);
	}
	
}
