import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle extends MyBoundedShape {

	public MyRectangle(int x1, int y1, int x2, int y2, Color color, boolean isFilled) {
		super(x1, y1, x2, y2, color, isFilled);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());
		
		if(this.getIsFilled()) {
			g.fillRect(this.getX1(), this.getY1(), this.getX2(), this.getY2());
		}
	}

//	@Override
//	protected MyShape clone() {
//		return new MyRectangle(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.getColor(), this.getIsFilled());
//	}

}
