import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		ArrayList<YearTemperatureData> data = getYearlyData();
		TemperatureChartApp app = new TemperatureChartApp(data);
		
		app.start();
	}
	
	private static ArrayList<YearTemperatureData> getYearlyData() {
		ArrayList<YearTemperatureData> yearsData = new ArrayList<YearTemperatureData>();
		
		for(int i = 2010; i<2020; i++) {
			YearTemperatureData year = new YearTemperatureData(i, getMonthsData()); 
		
			yearsData.add(year);
		}
		
		return yearsData;
	}
	
	private static ArrayList<MonthTemperatureData> getMonthsData() {
		ArrayList<MonthTemperatureData> data = new ArrayList<MonthTemperatureData>();
		Random r = new Random();
		
		for(Month m : Month.values()) {
			data.add(new MonthTemperatureData(m, r.nextInt(70) - 20)); // Random temperature between -20 and 50 
		}
		
		return data;
	}

}
