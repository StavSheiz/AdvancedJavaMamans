import java.util.ArrayList;
import java.util.Collections;

public class Question {

	public Question() {
		this.answers = new ArrayList<String>();
	}

	private String question;
	private String correctAnswer;
	private ArrayList<String> answers;
	
	public void setQuestion(String value) {
		this.question = value;
	}
	
	public void setCorrectAnswer(String value) {
		this.correctAnswer = value;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void addAnswer(String answer) {
		this.answers.add(answer);
	}
	
	public ArrayList<String> getShuffeldAnswers() {
		Collections.shuffle(this.answers);
		
		return this.answers;
	}
	
	public boolean isCorrect(String answer) {
		return this.correctAnswer.equals(answer);
	}
}
