import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

public abstract class Shape implements Cloneable {

	public Shape(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
	}
	
	private int x1, x2, y1, y2;
	private Color color;
	
	public int getX1() {
		return this.x1;
	}
	
	public void setX1(int value) {
		this.x1 = value;
	}
	
	public int getX2() {
		return this.x2;
	}
	
	public void setX2(int value) {
		this.x2 = value;
	}
	
	public int getY1() {
		return this.y1;
	}
	
	public void setY1(int value) {
		this.y1 = value;
	}
	
	public int getY2() {
		return this.y2;
	}
	
	public void setY2(int value) {
		this.y2 = value;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color value) {
		this.color = value;
	}
	
	public abstract void draw(Graphics g);
	
	@Override
	protected abstract Shape clone();

}
