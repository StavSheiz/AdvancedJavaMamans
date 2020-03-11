import java.awt.Color;
import java.awt.Graphics;

public abstract class BoundedShape extends Shape {

	public BoundedShape(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
		super(x1, y1, x2, y2, color);
		this.isFilled = isFilled;
	}
	
	private boolean isFilled;
	
	public boolean getIsFilled() {
		return this.isFilled;
	}
	
	public void setIsFilled(boolean value) {
		this.isFilled = value;
	}
	
	@Override
	public boolean equals(Object other) {
		return other.getClass().equals(this.getClass()) && 
				this.getX2() == ((BoundedShape)other).getX2() && 
				this.getY2() == ((BoundedShape)other).getY2();
	}
	
	public abstract void draw(Graphics g);

}
