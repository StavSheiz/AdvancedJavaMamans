
public class MonthTemperatureData {

	public MonthTemperatureData(Month month, int temperature) {
		this.month = month;
		this.temperature = temperature;
	}
	
	private Month month;
	private int temperature;
	
	public Month getMonth() {
		return this.month;
	}

	public int getTemperature() {
		return this.temperature;
	}
}
