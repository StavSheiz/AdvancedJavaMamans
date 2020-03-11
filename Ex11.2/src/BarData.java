import java.awt.Color;

public class BarData {

	public BarData(int value, Color color, String label) {
		this.value = value;
		this.color = color;
		this.label = label;
	}
	
	private int value;
	private Color color;
	private String label;
	
	public int getValue() {
		return value;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color value) {
		this.color = value;
	}
	
	public String getLabel() {
		return this.label;
	}

}
