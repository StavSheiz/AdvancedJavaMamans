import java.awt.Color;
import java.awt.Graphics;

public class SmoothRectangle extends BoundedShape {

	public SmoothRectangle(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
		super(x1, y1, x2, y2, color, isFilled);
	}
	
	private static final int RADIUS = 20;

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRoundRect(this.getX1(), this.getY1(), this.getX2(), this.getY2(), SmoothRectangle.RADIUS, SmoothRectangle.RADIUS);
		
		if(this.getIsFilled()) {
			g.fillRoundRect(this.getX1(), this.getY1(), this.getX2(), this.getY2(), SmoothRectangle.RADIUS, SmoothRectangle.RADIUS);
		}
	}

	@Override
	protected Shape clone() {
		return new SmoothRectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.getColor(), this.getIsFilled());
	}

}
