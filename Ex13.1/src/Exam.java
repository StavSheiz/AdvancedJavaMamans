import java.util.ArrayList;

public class Exam {

	public Exam() {
		this.questions = new ArrayList<Question>();
		this.answers = new ArrayList<String>();
	}
	
	private ArrayList<Question> questions;
	private ArrayList<String> answers;
	
	public ArrayList<Question> getQuestions() {
		return this.questions;
	}
	
	public String getAnswer(int index) {
		return this.answers.get(index);
	}
	
	public double checkExam() {
		int countCorrect = 0;
		
		for(int i = 0; i < this.questions.size(); i++) {
			String answer = this.answers.get(i);
			
			if(answer != null && this.questions.get(i).isCorrect(answer)) {
				countCorrect++;
			}
		}
		
		return 1.0*countCorrect/this.questions.size();
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
		this.answers.add(null);
	}
	
	public void setAnswer(int index, String answer) {
		this.answers.set(index, answer);
	}
	
	public void resetAnswers() {
		this.answers.replaceAll(answer -> null);
	}

}
