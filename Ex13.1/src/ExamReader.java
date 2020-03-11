import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExamReader {

	public ExamReader() {}
	
	private Scanner scanner;
	
	public Exam readExam(String fileName) throws FileNotFoundException, NoSuchElementException {
		System.out.println(new File(fileName).getAbsolutePath());

		this.scanner = new Scanner(new File(fileName));
		
		Exam exam = new Exam();
		
		while(scanner.hasNext()) {
			String question = scanner.nextLine();
			String correctAnswer = scanner.nextLine();
			String answer1 = scanner.nextLine();
			String answer2 = scanner.nextLine();
			String answer3 = scanner.nextLine();
			
			Question q = new Question();
			q.setQuestion(question);
			q.setCorrectAnswer(correctAnswer);
			q.addAnswer(correctAnswer);
			q.addAnswer(answer1);
			q.addAnswer(answer2);
			q.addAnswer(answer3);
			
			exam.addQuestion(q);
		}
		
		scanner.close();
		
		return exam;
	}

}
