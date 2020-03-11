import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {
		ExamReader reader = new ExamReader();
		
		try {
			Exam exam1 = reader.readExam("src/exam1.txt");
//			//Exam exam2 = reader.readExam("src/exam2.txt");
//			//Exam exam3 = reader.readExam("src/exam3.txt");
			
			ExamGUI quiz = new ExamGUI(exam1);
			quiz.play();	        
		} catch (NoSuchElementException e) {
			JOptionPane.showConfirmDialog(null, "File format is invalid.", "Error", JOptionPane.DEFAULT_OPTION);
		} catch (FileNotFoundException e) {
			JOptionPane.showConfirmDialog(null, "File not found.", "Error", JOptionPane.DEFAULT_OPTION);
		}
		

		
	}

}
