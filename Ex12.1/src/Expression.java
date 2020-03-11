
public abstract class Expression {

	public Expression() {
	}
	
	public abstract double calculate();
	
	public boolean equals(Object obj) {
		return obj != null && 
				Expression.class.isAssignableFrom(obj.getClass()) && 
				this.calculate() == ((Expression)obj).calculate();
	}

}
