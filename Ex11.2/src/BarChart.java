import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BarChart extends JPanel {
	
	private ArrayList<BarData> bars =
            new ArrayList<BarData>();
	private String title = "";
	
	private final int Y_GRID_BUFFER = 30;
	private final int Y_GRID_FACTOR = 8;
	private final int Y_GRID_SCALE = 5;

	private final int X_GRID_FACTOR = 60;
	private final int X_GRID_BUFFER = 40;
	
	private final int MAX_VALUE = 50;
	private final int MIN_VALUE = -20;
		
	public void setBars(ArrayList<BarData> data)
	{
		bars = data;	
		
		this.revalidate();
		this.repaint();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.setBackground(Color.WHITE);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground());
		
		g.drawString(this.title, getWidth()/2, 40);
		
		for(int i=0; i <= MAX_VALUE - MIN_VALUE; i++) {
			if(i%Y_GRID_SCALE == 0) {
				int y = getHeight() - i*Y_GRID_FACTOR - Y_GRID_BUFFER;
				int x = 10;
				int line = i + MIN_VALUE;
				
				g.drawString(Integer.toString(line), x, y);
				g.drawLine(x + 20, y, getWidth() - x, y);
			}
		}
		
		for(int j = 12; j > 0; j--) {
			int y = getHeight() - 10;
			int x = getWidth() - (12-j)*X_GRID_FACTOR - X_GRID_FACTOR/2 - X_GRID_BUFFER;
			
			g.drawString(Integer.toString(j), x, y);
		}
		
		
		for(int k = this.bars.size(); k > 0; k--) {
			BarData data = this.bars.get(k-1);
			int y,x,width,height;
			
			if(data.getValue() >= 0) {
				y = getHeight() - data.getValue()*Y_GRID_FACTOR - Y_GRID_BUFFER - (-MIN_VALUE)*Y_GRID_FACTOR;
				x = getWidth() - (12-k+1)*X_GRID_FACTOR - X_GRID_BUFFER;
				width = X_GRID_FACTOR - 2;
				height = data.getValue()*Y_GRID_FACTOR;
			} else {
				height = (-1*data.getValue())*Y_GRID_FACTOR;
				y = getHeight() - data.getValue()*Y_GRID_FACTOR - Y_GRID_BUFFER - 
						((-MIN_VALUE)*Y_GRID_FACTOR + height);
				x = getWidth() - (12-k+1)*X_GRID_FACTOR - X_GRID_BUFFER;
				width = X_GRID_FACTOR - 2;
			}
			
			g.setColor(data.getColor());
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);

			

		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 700);
	}

}
