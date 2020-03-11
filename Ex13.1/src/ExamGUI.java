import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.*;

public class ExamGUI extends JDialog {
	
    public ExamGUI(Exam exam){
        super();
        this.exam = exam;
        setModal(true);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
    }
    
    private Exam exam;
	private ArrayList<ButtonGroup> groups = new ArrayList<ButtonGroup>();
	private int command = 1;

    public void startQuiz(){
    	
    	while(command == 1) {
        	this.groups.clear();
        	this.getContentPane().removeAll();
            this.exam.resetAnswers();
            this.displayQuestions();
            this.displayAnswers();
    	}
    }
    
    public void play() {
    	this.startQuiz();
    	this.dispose();
    }
    
    private void displayAnswers() {
    	this.getContentPane().removeAll();

        double score = this.exam.checkExam();
        
        DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		        
        for (int i = 0; i<this.exam.getQuestions().size(); i++){
        	Question q = this.exam.getQuestions().get(i);

            JLabel questionText = new JLabel(q.getQuestion());
            
            this.getContentPane().add(questionText);
            
            for (String answer : q.getShuffeldAnswers()){
            	String answerMsg = answer;
            	
            	if(this.exam.getAnswer(i) != null && this.exam.getAnswer(i).equals(answer)) {
                	if(q.isCorrect(answer)) {
                		answerMsg += " - Correct";
                	} else {
                		answerMsg += " - Wrong";
                	}
            	}

                JLabel lblAnswer = new JLabel(answerMsg);
                this.getContentPane().add(lblAnswer);
            }
            
            this.getContentPane().add(new JSeparator());
        }
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	command = 0;
            	setVisible(false);
            }
        });
        
        JButton btnTryAgain = new JButton("Try Again");
        btnTryAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	command = 1;
            	setVisible(false);
            }
        });
        
        JLabel lblScore = new JLabel("Your score: "+df.format(score*100));
        
        this.getContentPane().add(lblScore);
        this.getContentPane().add(btnTryAgain);
        this.getContentPane().add(btnExit);
        
        pack();
        setVisible(true);
    }
    
    private void displayQuestions() {
        for (int i = 0; i<this.exam.getQuestions().size(); i++){
        	Question q = this.exam.getQuestions().get(i);
        	int index = i;
            ButtonGroup group = new ButtonGroup();
            JLabel questionText = new JLabel(q.getQuestion());
            
            this.getContentPane().add(questionText);
            
            for (String answer : q.getShuffeldAnswers()){
                JRadioButton radio = new JRadioButton(answer);
                radio.setActionCommand(answer);
                radio.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                		exam.setAnswer(index, answer);
                	}
                });
                group.add(radio);
                this.getContentPane().add(radio);
            }
            
            this.getContentPane().add(new JSeparator());
            this.groups.add(group);
        }	
        
        JButton btnOk = new JButton("Finish");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            }
        });
        
        this.getContentPane().add(btnOk);
        
        pack();
        setVisible(true);
    }
    
}