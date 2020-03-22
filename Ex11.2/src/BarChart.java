import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BarChart extends JPanel {
	
	public BarChart() {
	}
	
	private ArrayList<BarData> bars =
            new ArrayList<BarData>();
	private String title = "";
		
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
		this.setOpaque(false);
		super.setOpaque(false);
		g.drawString(this.title, getWidth()/2, 40);
		
		for(int k = this.bars.size(); k > 0; k--) {
			BarData data = this.bars.get(k-1);
			int y,x,width,height;
			
			if(data.getValue() >= 0) {
				y = getHeight() - data.getValue()*BarChartSkeleton.Y_GRID_FACTOR - BarChartSkeleton.Y_GRID_BUFFER - (-BarChartSkeleton.MIN_VALUE)*BarChartSkeleton.Y_GRID_FACTOR;
				x = getWidth() - (12-k+1)*BarChartSkeleton.X_GRID_FACTOR - BarChartSkeleton.X_GRID_BUFFER;
				width = BarChartSkeleton.X_GRID_FACTOR - 2;
				height = data.getValue()*BarChartSkeleton.Y_GRID_FACTOR;
			} else {
				height = (-1*data.getValue())*BarChartSkeleton.Y_GRID_FACTOR;
				y = getHeight() - data.getValue()*BarChartSkeleton.Y_GRID_FACTOR - BarChartSkeleton.Y_GRID_BUFFER - 
						((-BarChartSkeleton.MIN_VALUE)*BarChartSkeleton.Y_GRID_FACTOR + height);
				x = getWidth() - (12-k+1)*BarChartSkeleton.X_GRID_FACTOR - BarChartSkeleton.X_GRID_BUFFER;
				width = BarChartSkeleton.X_GRID_FACTOR - 2;
			}
			
			g.setColor(data.getColor());
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}
	}

}
