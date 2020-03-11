
public class AdditionExpression extends CompoundExpression {

	public AdditionExpression(Expression ex1, Expression ex2) {
		super(ex1, ex2);
	}
	
	@Override
	public double calculate() {
		return this.ex1.calculate() + this.ex2.calculate();
	}
	
	@Override
	public String toString() {
		return this.ex1.toString() + " + " + this.ex2.toString();
	}

}
