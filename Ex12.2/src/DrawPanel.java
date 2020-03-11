import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	public DrawPanel() {
		shapes = new ArrayList<MyShape>();
	}
	
	private ArrayList<MyShape> shapes;
	
	public void addShapes(ArrayList<MyShape> shapes) {	
		this.shapes.addAll(shapes);
		this.repaint();
	}
	
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        this.setBackground(Color.WHITE);
        
        this.shapes.forEach(shape -> {
        	shape.draw(g);
        });
    }

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}
}
