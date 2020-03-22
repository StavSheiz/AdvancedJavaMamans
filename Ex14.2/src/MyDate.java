
public class MyDate {

	public MyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	private int year;
	private int month;
	private int day;
	
	private int getYear() {
		return this.year;
	}
	
	private int getMonth() {
		return this.month;
	}
	
	private int getDay() {
		return this.day;
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof MyDate && 
				((MyDate)other).year == this.year && 
				((MyDate)other).month == this.month && 
				((MyDate)other).day == this.day; 
	}
	
	public String toString() {
		return String.valueOf(this.day) + "/" + String.valueOf(this.month + 1) + "/" + String.valueOf(this.year);
	}

}
