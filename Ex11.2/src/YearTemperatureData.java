import java.util.ArrayList;

public class YearTemperatureData {

	public YearTemperatureData(int year, ArrayList<MonthTemperatureData> monthsData) {
		this.year = year;
		this.monthsData = monthsData;
	}
	
	private ArrayList<MonthTemperatureData> monthsData;
	private int year;
	
	public String getYearName() {
		return Integer.toString(this.year);
	}
	
	public ArrayList<MonthTemperatureData> getMonthsData() {
		return this.monthsData;
	}

}
