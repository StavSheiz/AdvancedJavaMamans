import java.awt.Color;
import java.awt.Graphics;

public class Oval extends BoundedShape {

	public Oval(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
		super(x1, y1, x2, y2, color, isFilled);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());
		
		if(this.getIsFilled()) {
			g.fillOval(this.getX1(), this.getY1(), this.getX2(), this.getY2());
		}
	}
	
	@Override
	protected Shape clone() {
		return new Oval(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.getColor(), this.getIsFilled());
	}

}
