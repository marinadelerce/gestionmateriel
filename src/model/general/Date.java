package model.general;

public class Date {
	private int hour;
	private int day;
	private int month;
	private int year;
	
	public Date(){
		hour = 0;
		day = 0;
		month = 0;
		year = 0;
	}
	
	public Date(int hour, int day, int month, int year){
		this.hour = hour;
		this.day=day;
		this.month=month;
		this.year=year;
	}
	
	public boolean isPosterior(Date date){
		if (year>date.year) return true;
		if (year<date.year) return false;
		if (month>date.month) return true;
		if (month<date.month) return false;
		if (day>date.day) return true;
		if (day<date.day) return false;
		if (hour>date.hour) return  true;
		if (hour<date.hour) return false;
		return false;
	}
	
	public boolean isPrevious(Date date){
		if (isPosterior(date) || equals(date)) return false;
		else return true;
	}
	
	public void nextHour(){
		hour++;
		if (hour>=24){
			hour=0;
			day++;
		}
		if (day>30){
			day=1;
			month++;
		}
		if (month>12){
			month=1;
			year++;
		}
	}
	
	
	public int getHour() {
		return hour;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Date)) return false;
		Date d = (Date) obj;
		if (year == d.year && month == d.month && day == d.day && hour == d.hour) return true;
		else return false;
	}
	
	@Override
	public Object clone(){
		return new Date(hour, day, month, year);
	}
}
