import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AtomicExpression extends Expression {

	public AtomicExpression(double value) {
		this.value = value;
	}
	
	private double value;
	
	@Override
	public double calculate() {
		return this.value;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return String.valueOf(df.format(this.calculate()));
	}

}
