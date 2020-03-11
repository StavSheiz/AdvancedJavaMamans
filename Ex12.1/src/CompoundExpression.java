
public abstract class CompoundExpression extends Expression {

	public CompoundExpression(Expression ex1, Expression ex2) {
		this.ex1 = ex1;
		this.ex2 = ex2;
	}
	
	protected Expression ex1;
	protected Expression ex2;

}
