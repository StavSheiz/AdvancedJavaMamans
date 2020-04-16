import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Main {
	
	private static final int MAX_NUMBER = 200;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DrawPanel panel = new DrawPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);      
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		ArrayList<MyShape> firstShapes = initShapes(2);
		
		ArrayList<MyShape> secondShapes = clone(firstShapes);
		changeAllShapes(secondShapes, 10, Color.GREEN, false);
		
		panel.addShapes(firstShapes);
		panel.addShapes(secondShapes);
	}
	
	public static void changeAllShapes(ArrayList<MyShape> shapes, int pointMovement, Color color, boolean isFilled) {
		shapes.forEach(shape -> {
			shape.setX1(shape.getX1() + pointMovement);
			shape.setY1(shape.getY1() + pointMovement);
			shape.setColor(color);
			
			if(MyBoundedShape.class.isAssignableFrom(shape.getClass())) {
				((MyBoundedShape)shape).setIsFilled(isFilled);
			}
		});
	}

	public static ArrayList<MyShape> initShapes(int amountForKind) {
		ArrayList<MyShape> shapes = new ArrayList<MyShape>();
		Random r = new Random();
		
		for(int i = 0; i < amountForKind; i++) {
			MyLine line = new MyLine(getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					Color.RED);
			
			MyOval oval = new MyOval(getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					Color.RED, 
					true);
			
			MyRectangle rect = new MyRectangle(getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					getRandomNumber(r), 
					Color.RED, 
					true);
			
			shapes.add(line);
			shapes.add(oval);
			shapes.add(rect);
		}
		
		return shapes;
	}
	
	private static int getRandomNumber(Random r) {
		return r.nextInt(MAX_NUMBER + 1);
	}
	
	private static ArrayList<MyShape> clone(ArrayList<MyShape> shapes) {
		ArrayList<MyShape> clonedShapes = new ArrayList<MyShape>();
		
		shapes.forEach(shape -> {
			try {
				clonedShapes.add(shape.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		});
		
		return clonedShapes;
	}

}
