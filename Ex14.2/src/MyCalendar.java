import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MyCalendar {

	public MyCalendar() {
		this.appointments = new HashMap<String, ArrayList<String>>();
	}
	
	private HashMap<String, ArrayList<String>> appointments;
	
	public void addAppointment(MyDate date, String text) {
		ArrayList<String> dateAppointments = this.appointments.get(date.toString());
		if(dateAppointments == null) {
			dateAppointments = new ArrayList<String>();
		}
		
		dateAppointments.add(text);
		this.appointments.put(date.toString(), dateAppointments);
	}
	
	public ArrayList<String> getAppointments(MyDate date) {
		return this.appointments.get(date.toString());
	}

}
