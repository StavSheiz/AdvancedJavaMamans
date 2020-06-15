import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements MouseListener, ActionListener {

	public MyFrame() {
		super("hello");
		
		setLayout(new FlowLayout());		
		
		myLabel = new JLabel("label");
		myButton = new JButton("button");
		myTextField = new JTextField("text");
		
		myLabel.setOpaque(true);
	
		myTextField.addActionListener(this);
		myButton.addActionListener(this);
		myLabel.addMouseListener(this);
		
		add(myLabel);
		add(myButton);
		add(myTextField);
				
		setSize(300,200);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JLabel myLabel;
	private JButton myButton;
	private JTextField myTextField;


	@Override
	public void mouseClicked(MouseEvent e) {

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == myButton) {
			myLabel.setBackground(Color.RED);
		} else if (e.getSource() == myTextField) {
			myLabel.setText(((JTextField)e.getSource()).getText());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == myLabel) {
			myLabel.setBackground(Color.GREEN);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}



