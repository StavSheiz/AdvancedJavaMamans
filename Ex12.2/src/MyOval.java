import java.awt.Color;
import java.awt.Graphics;

public class MyOval extends MyBoundedShape {

	public MyOval(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
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
	
//	@Override
//	protected MyShape clone() {
//		return new MyOval(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.getColor(), this.getIsFilled());
//	}

}
