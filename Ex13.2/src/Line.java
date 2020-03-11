import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	public Line(int x1, int y1, int x2, int y2, Color color) {
		super(x1, y1, x2, y2, color);
	}
	
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
	}
	
	@Override
	public boolean equals(Object other) {
		return other.getClass().equals(this.getClass()) && this.getLength() == ((Line)other).getLength();
	}
	
	private double getLength() {
		return Math.sqrt(Math.abs(this.getX1()-this.getX2()) + Math.abs(this.getY1()-this.getY2()));
	}

	@Override
	protected Shape clone() {
		return new Line(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.getColor());
	}
}
