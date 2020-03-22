import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalendarApp extends JPanel {

	public CalendarApp() {
		super(null);
		this.calendar = new MyCalendar();
		
		this.initComponents();
        this.addListeners();
        this.setBounds();
        this.initCalendar();
        this.initCalendarTable();
        
        refreshCalendar (currentMonth, this.currentYear);
    }
	
    private DefaultTableModel mtblCalendar;
    private JScrollPane stblCalendar;
    private JTextField tbTextBox;
    private int currentYear, currentMonth, currentDay, shownYear, shownMonth;
    private JLabel lblMonth, lblYear, lblText;
    private JButton btnPrev, btnNext, btnAccept;
    private JTable tblCalendar;
    private JComboBox cmbYear;
    
    private MyCalendar calendar;
	
	private void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        
        //Allow/disallow buttons
        this.btnPrev.setEnabled(true);
        this.btnNext.setEnabled(true);
        if (month == 0 && year <= this.currentYear-10){this.btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= this.currentYear+100){this.btnNext.setEnabled(false);} //Too late
        this.lblMonth.setText(months[month] + " " + year); //Refresh the month label (at the top)
        this.lblMonth.setBounds(240-this.lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
        
        //Clear tables
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                this.mtblCalendar.setValueAt(null, i, j);
                //tblCalendar.getCellRenderer(i, j);
            }
        }
        
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Draw calendar
        for (int i=1; i<=nod; i++){
            int row = (i+som-2)/7;
            int column  =  (i+som-2)%7;
            this.mtblCalendar.setValueAt(i, row, column);
        }
        
        //Apply renderers
        this.tblCalendar.setDefaultRenderer(this.tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
	
	private void addListeners() {
        this.btnPrev.addActionListener(new btnPrev_Action());
        this.btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
        this.btnAccept.addActionListener(new btnOk_Action());
	}
	
	private void initComponents() {
        this.lblMonth = new JLabel ("January");
        this.lblYear = new JLabel ("Change year:");
        cmbYear = new JComboBox();
        this.btnPrev = new JButton ("<");
        this.btnNext = new JButton (">");
        this.tbTextBox = new JTextField();
        this.lblText = new JLabel ("Appointment text:");
        this.btnAccept = new JButton("Ok");
        
        this.mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        this.tblCalendar = new JTable(this.mtblCalendar);
        this.stblCalendar = new JScrollPane(this.tblCalendar);
        
        this.add(this.lblMonth);
        this.add(this.lblYear);
        this.add(cmbYear);
        this.add(this.btnPrev);
        this.add(this.btnNext);
        this.add(this.btnAccept);
        this.add(this.stblCalendar);
        this.add(this.lblText);
        this.add(this.tbTextBox);
	}
	
	private void setBounds() {
        this.setBounds(0, 0, 488, 550);

        this.lblMonth.setBounds(160-this.lblMonth.getPreferredSize().width/2, 25, 100, 25);
        this.lblYear.setBounds(10, 390, 80, 20);
        cmbYear.setBounds(150, 390, 80, 20);
        this.btnPrev.setBounds(10, 25, 50, 25);
        this.btnNext.setBounds(428, 25, 50, 25);
        this.btnAccept.setBounds(370, 420, 50, 20);
        this.stblCalendar.setBounds(10, 50, 468, 326);
        this.lblText.setBounds(10, 420, 100 ,20);
        this.tbTextBox.setBounds(150, 420, 200, 20);
	}
	
	private void initCalendar() {
        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        this.currentDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        this.currentMonth = cal.get(GregorianCalendar.MONTH); //Get month
        this.currentYear = cal.get(GregorianCalendar.YEAR); //Get year
        this.shownMonth = this.currentMonth; //Match month and year
        this.shownYear = this.currentYear;
	}
	
	private void initCalendarTable() {
		//Add headers
        String[] headers = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}; //All headers
        for (int i=0; i<7; i++){
            this.mtblCalendar.addColumn(headers[i]);
        }
        
        this.tblCalendar.getParent().setBackground(this.tblCalendar.getBackground()); //Set background
        
        //No resize/reorder
        this.tblCalendar.getTableHeader().setResizingAllowed(false);
        this.tblCalendar.getTableHeader().setReorderingAllowed(false);
        
        //Single cell selection
        this.tblCalendar.setColumnSelectionAllowed(true);
        this.tblCalendar.setRowSelectionAllowed(true);
        this.tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tblCalendar.setCellSelectionEnabled(true);
        
        //Set row/column count
        this.tblCalendar.setRowHeight(50);
        this.mtblCalendar.setColumnCount(7);
        this.mtblCalendar.setRowCount(6);
        
        //Populate table
        for (int i=this.currentYear-100; i<=this.currentYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }
        
        this.tblCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblCalendar.rowAtPoint(evt.getPoint());
                int col = tblCalendar.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                	Object cellValue = tblCalendar.getValueAt(row, col);
                	
                	if(cellValue != null) {
                    	MyDate date = new MyDate(shownYear, shownMonth, (int)cellValue);
                    	ArrayList<String> appointments = calendar.getAppointments(date);
                    	
                    	if(appointments != null) {
                    		String msg = "";
                    		for(String appointment : appointments) {
                    			msg += appointment + "\n";
                    		}
                    		
                			int result = JOptionPane.showConfirmDialog(null, msg, "Appointmnets for " + date.toString() , JOptionPane.DEFAULT_OPTION);

                    	}
                	}
                }
            }
        });
	}
	
	class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
        	this.removeAll();
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 5 || column == 6){ //Week-end
                setBackground(new Color(255, 220, 220));
            }
            else{ //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == currentDay && shownMonth == currentMonth && shownYear == currentYear){ //Today
                    setBackground(new Color(220, 255, 220));
                }
                
                MyDate date = new MyDate(shownYear, shownMonth, Integer.parseInt(value.toString()));
                ArrayList<String> appointments = calendar.getAppointments(date);
                
                if(appointments != null) {
                	JPanel panel = new JPanel();
                	panel.setOpaque(false);
                	int counter = 0;
                    for(String appointment : appointments) {
                    	JLabel label = new JLabel(appointment);
                    	label.setBackground(Color.YELLOW);
                    	label.setOpaque(true);
                    	label.setBounds(0, 12*counter, 50, 10);
                    	counter++;
                        panel.add(label);
                    }
                    
                	panel.setBounds(20, 0, 50,  12*counter);

                    this.add(panel, BorderLayout.EAST);
                }
            }
            
            if(table.isCellSelected(row, column)) {
            	setBackground(new Color(220, 220, 255));
            }
            


            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (shownMonth == 0){ //Back one year
                shownMonth = 11;
                shownYear -= 1;
            }
            else{ //Back one month
                shownMonth -= 1;
            }
            refreshCalendar(shownMonth, shownYear);
        }
    }
    class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (shownMonth == 11){ //Foward one year
                shownMonth = 0;
                shownYear += 1;
            }
            else{ //Foward one month
                shownMonth += 1;
            }
            refreshCalendar(shownMonth, shownYear);
        }
    }
    class cmbYear_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                shownYear = Integer.parseInt(b);
                refreshCalendar(shownMonth, shownYear);
            }
        }
    }
    
    class btnOk_Action implements ActionListener {
    	public void actionPerformed (ActionEvent e) {
    		int selectedRow = tblCalendar.getSelectedRow();
    		int selectedColumn = tblCalendar.getSelectedColumn();
    		Object cellValue;
    		if(selectedColumn != -1 && selectedRow != -1 && (cellValue = tblCalendar.getValueAt(selectedRow, selectedColumn)) != null) {
    			MyDate date = new MyDate(shownYear, shownMonth, (int)cellValue);
    			String text = tbTextBox.getText();
    			
    			calendar.addAppointment(date, text);
    			JOptionPane.showConfirmDialog(null, "Appointment Added", "Success", JOptionPane.DEFAULT_OPTION);
    			refreshCalendar(shownMonth, shownYear);
    		} else {
    			JOptionPane.showConfirmDialog(null, "No Date picked, pick a date to add an appointment.", "Error", JOptionPane.DEFAULT_OPTION);
    		}
    	}
    }

}
