import java.util.ArrayList;

public class YearData {

	public YearData(int year, ArrayList<TemperatureData> monthsData) {
		this.year = year;
		this.monthsData = monthsData;
	}
	
	private ArrayList<TemperatureData> monthsData;
	private int year;
	
	public String getYearName() {
		return Integer.toString(this.year);
	}
	
	public ArrayList<TemperatureData> getMonthsData() {
		return this.monthsData;
	}

}
