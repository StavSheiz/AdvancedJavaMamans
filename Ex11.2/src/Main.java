import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		ArrayList<YearData> data = getYearlyData();
		YearlyTemperatures app = new YearlyTemperatures(data);
		
		app.start();
	}
	
	private static ArrayList<YearData> getYearlyData() {
		ArrayList<YearData> yearsData = new ArrayList<YearData>();
		
		for(int i = 2010; i<2020; i++) {
			YearData year = new YearData(i, getMonthsData()); 
		
			yearsData.add(year);
		}
		
		return yearsData;
	}
	
	private static ArrayList<TemperatureData> getMonthsData() {
		ArrayList<TemperatureData> data = new ArrayList<TemperatureData>();
		Random r = new Random();
		
		for(Month m : Month.values()) {
			data.add(new TemperatureData(m, r.nextInt(70) - 20)); // Random temperature between -20 and 50 
		}
		
		return data;
	}

}
