import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		int numOfExpressions = 10;
		ArrayList<Expression> expressions = initExpressions(numOfExpressions);

		String msg = "";
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		for(int i=0; i<expressions.size(); i++) {
			Expression ex = expressions.get(i);
			boolean isEquals = containsEquals(expressions, i);
			
			msg += ex.toString() + " = " + df.format(ex.calculate()) + " Has equal expressions: " + String.valueOf(isEquals) + "\n";
		};
		
		JOptionPane.showConfirmDialog(null, msg, "Calculator Example", JOptionPane.DEFAULT_OPTION);
	}
	
	private static ArrayList<Expression> initExpressions(int numOfExpressions) {
		ArrayList<Expression> expressions = new ArrayList<Expression>();
		
		Random r = new Random();
		int rangeOperandMax = 5;
		int rangeOperandMin = 1;
		int rangeMin = 0;
		int rangeMax = 10;
		
		// Fill 10 Expressions
		for(int i = 0; i < numOfExpressions; i++) {
			int operands = rangeOperandMin + r.nextInt(rangeOperandMax - rangeOperandMin + 1);
			
			// Use a random number of operands between 1 and 5
			int j=1;
			double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			randomValue = Double.parseDouble(String.format("%.2f", randomValue));
			
			Expression ex = new AtomicExpression(randomValue);
			
			while(j<operands) {
				randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				randomValue = Double.parseDouble(String.format("%.2f", randomValue));

				boolean randomOperandValue = r.nextBoolean(); 
				
				Expression temp = ex;

				
				if(randomOperandValue) {
					ex = new AdditionExpression(temp, new AtomicExpression(randomValue));
				} else {
					ex = new SubtractionExpression(temp, new AtomicExpression(randomValue));					
				}
				
				j++;
			}
			
			expressions.add(ex);
		}
		
		return expressions;
	}
	
	private static boolean containsEquals(ArrayList<Expression> expressions, int exIndex) {
		for(int i=0; i<expressions.size(); i++) {
			if(exIndex != i && expressions.get(i).equals(expressions.get(exIndex))) {
				return true;
			}
		}
		
		return false;
	}

}
