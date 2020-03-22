import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main{

    static JFrame frmMain;
    static Container pane;

    static CalendarApp pnlCalendar;

    
    public static void main (String args[]){

        
        frmMain = new JFrame ("Calnedar");
        frmMain.setSize(500, 600);
        pane = frmMain.getContentPane();
        pane.setLayout(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnlCalendar = new CalendarApp();
         pane.add(pnlCalendar);
        frmMain.setResizable(false);
        frmMain.setVisible(true);   
    }
    
    
    
    
}
