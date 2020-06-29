import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener, Runnable {

	public MyPanel() {
		hit = 0;
		miss = 0;
		squareCount = 0;
		rand = new Random();
		
		addMouseListener(this);
	}
	
	private static final int SQUARE_SIZE = 10;
	private static final int SCREEN_HEIGHT = 400;
	private static final int SCREEN_WIDTH = 400;
	private static final int x = 3000;
	private static final int n = 5;
	
	private int squareX;
	private int squareY;
	private int hit;
	private int miss;
	private int squareCount;
	private boolean isActive;
	private Random rand;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ExecutorService exe = Executors.newCachedThreadPool();
		
		frame.setSize(SCREEN_HEIGHT,SCREEN_WIDTH);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		MyPanel panel = new MyPanel();
		frame.add(panel);
		frame.setVisible(true);
		
		exe.execute(panel);
	}
	
	private void printResults() {
		String message = "results: " + (2*hit-miss);
		JOptionPane.showConfirmDialog(null, message, "results", JOptionPane.OK_OPTION);
	}
	
	protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);       
        
        g.setColor(Color.RED);
        g.drawRect(squareX, squareY, SQUARE_SIZE, SQUARE_SIZE);
        g.fillRect(squareX, squareY, SQUARE_SIZE, SQUARE_SIZE);
    }


	@Override
	public void mouseClicked(MouseEvent arg0) {
		int px = arg0.getX();
		int py = arg0.getY();
		
		if(px>squareX && px<squareX+SQUARE_SIZE && py>squareY && py<squareY+SQUARE_SIZE) {
			hit++;
		} else {
			miss++;
		}
		
		isActive = false;
	}
	
	@Override
	public void run() {
		while(squareCount < n) {
			    squareX = rand.nextInt(SCREEN_WIDTH-SQUARE_SIZE);
			    squareY = rand.nextInt(SCREEN_HEIGHT-SQUARE_SIZE);
			    squareCount++;
			    isActive = true;
			    repaint();
			    
			    try {
					Thread.sleep(x);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		printResults();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





}
