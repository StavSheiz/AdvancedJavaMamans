import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TemperatureChartApp {

	public TemperatureChartApp(ArrayList<YearTemperatureData> data) {
		this.yearsData = data;
		this.frame = new JFrame("Monthly Temperatures");
		this.skeleton = new BarChartSkeleton();
	
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(560, 500);      
		this.frame.setLocationRelativeTo(null);  
	}
	
	public void start() {
		this.frame = new JFrame("Bar Chart");
		
        JButton button = new JButton("Pick Year");
        button.setBounds(50,50,90, 50);  

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	YearTemperatureData data = SelectYear();
            	
            	if(data != null) {
            		skeleton.setTitle(data.getYearName());
            		skeleton.setBars(formatBarData(data));
            	}
            	
            }
        });
        
        JPanel p = new JPanel();
        p.add(button);
        p.setBounds(10, 10, 80, 40);

		frame.getContentPane().add(p, BorderLayout.NORTH);	
		frame.getContentPane().add(this.skeleton, BorderLayout.CENTER);		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private ArrayList<YearTemperatureData> yearsData;
	private JFrame frame;
	private BarChartSkeleton skeleton;
	
	private YearTemperatureData SelectYear() {
		YearTemperatureData year = null;
		String message = "Enter a year";
		
		while(year == null) {
			String yearString = (String)JOptionPane.showInputDialog(
		               frame,
		               message, 
		               "Monthly Temperatures",            
		               JOptionPane.PLAIN_MESSAGE,
		               null,            
		               null, 
		               null
		    );
			
			if(yearString == null) {
				return null;
			}
			
			for(YearTemperatureData yearData : this.yearsData) {
				if(yearData.getYearName().equals(yearString)) {
					year = yearData;
				}
			}
			
			if(year == null) {
				message = "Invalid year, Enter a year";
			}
		}
		
		return year;
	}
	
	private ArrayList<BarData> formatBarData(YearTemperatureData data) {
		ArrayList<BarData> barData = new ArrayList<BarData>();
		ArrayList<MonthTemperatureData> tempsData = data.getMonthsData();

		int maxValue = tempsData.get(0).getTemperature();
		int minValue = tempsData.get(0).getTemperature();
		ArrayList<Integer> maxIndexes = new ArrayList<Integer>();
		ArrayList<Integer> minIndexes = new ArrayList<Integer>();
		minIndexes.add(0);
		maxIndexes.add(0);
		
		for(int i = 0; i < 12; i++) {
			MonthTemperatureData temp = tempsData.get(i);
			barData.add(new BarData(temp.getTemperature(), Color.GRAY, temp.getMonth().name()));
			
			if(temp.getTemperature() > maxValue) {
				maxIndexes.clear();
				maxIndexes.add(i);
				maxValue = temp.getTemperature();
			} else if(temp.getTemperature() == maxValue) {
				maxIndexes.add(i);
			}
			
			if(temp.getTemperature() < minValue) {
				minIndexes.clear();
				minIndexes.add(i);
				minValue = temp.getTemperature();
			} else if(temp.getTemperature() == minValue) {
				minIndexes.add(i);
			}
		}
		
		for(int i : maxIndexes) {
			barData.get(i).setColor(Color.RED);
		}
		
		for(int i : minIndexes) {
			barData.get(i).setColor(Color.BLUE);
		}
		
		return barData;
	}
}
