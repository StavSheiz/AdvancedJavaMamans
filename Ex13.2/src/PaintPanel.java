import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener{

	public PaintPanel() {
		this.shapes = new ArrayList<Shape>();
        this.setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
	}
	
	private Color currentColor;
	private boolean isFill;
	private ArrayList<Shape> shapes;
    private boolean isDragging;
    private int pressLocX,pressLocY;
    private ENUMShapes currentShapeType;
    private Shape currentShape;

	public void setCurrentColor(Color value) {
		this.currentColor = value;
	}
	
	public void setIsFill(boolean value) {
		this.isFill = value;
	}
	
	public void setCurrentShape(ENUMShapes value) {
		this.currentShapeType = value;
	}
	
	public void resetShapes() {
		this.shapes.clear();
		this.repaint();
	}
	
	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        
        this.shapes.forEach(shape -> {
        	shape.draw(g);
        });
        
        if(this.currentShape != null) { 
        	this.currentShape.draw(g);
    	};
    }

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (this.currentShapeType) {
		case Line: {
			this.currentShape = this.createLine(e);
			break;
		}
		case Oval: {
			this.currentShape = this.createOval(e);
			break;
		}
		case Rectangle: {
			this.currentShape = this.createRectangle(e);
			break;
		}
		case SmoothRectangle: {
			this.currentShape = this.createSmoothRectangle(e);
			break;
		}
		default:
			break;
		}
		
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.isDragging)
			return; // If user presses another mouse button while dragging, ignore.
		
		this.pressLocX = e.getX();
		this.pressLocY = e.getY();
		this.isDragging = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (!this.isDragging)
            return; // Nothing to do because the user isn't drawing.
		
        this.isDragging = false;
		this.shapes.add(this.currentShape);
		this.currentShape = null;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}
	
	private Line createLine(MouseEvent e) {
		return new Line(this.pressLocX, this.pressLocY, e.getX(), e.getY(), this.currentColor);
	}
	
	private Oval createOval(MouseEvent e) {
		int[] topLeftPoint = this.getTopLeftPoint(this.pressLocX, this.pressLocY, e.getX(), e.getY());
		return new Oval(
				topLeftPoint[0], 
				topLeftPoint[1], 
				Math.abs(this.pressLocX - e.getX()), 
				Math.abs(this.pressLocY - e.getY()), 
				this.currentColor, 
				this.isFill);
	}
	
	private Rectangle createRectangle(MouseEvent e) {
		int[] topLeftPoint = this.getTopLeftPoint(this.pressLocX, this.pressLocY, e.getX(), e.getY());
		return new Rectangle(
				topLeftPoint[0], 
				topLeftPoint[1], 
				Math.abs(this.pressLocX - e.getX()), 
				Math.abs(this.pressLocY - e.getY()), 
				this.currentColor, 
				this.isFill);
	}
	
	private SmoothRectangle createSmoothRectangle(MouseEvent e) {
		int[] topLeftPoint = this.getTopLeftPoint(this.pressLocX, this.pressLocY, e.getX(), e.getY());
		return new SmoothRectangle(
				topLeftPoint[0], 
				topLeftPoint[1], 
				Math.abs(this.pressLocX - e.getX()), 
				Math.abs(this.pressLocY - e.getY()), 
				this.currentColor, 
				this.isFill);
	}
	
	private int[] getTopLeftPoint(int x1, int y1, int x2, int y2) {
		int[] point = new int[2];
		
		point[0] = x1 < x2 ? x1 : x2;
		point[1] = y1 < y2 ? y1 : y2;
		
		return point;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
