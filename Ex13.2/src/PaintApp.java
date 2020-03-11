import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintApp extends JFrame {

	public PaintApp() throws HeadlessException {
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		this.setResizable(false);
	    this.setSize(800,800);
	    this.setLocation(100,100);
	    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		JPanel buttonsPanel = new JPanel();
		
		JPanel shapesPanel = this.createShapesPanel();
		JPanel colorsPanel = this.createColorsJPanel();
		JPanel fillPanel = this.createFillJPanel();
		JPanel utilsPanel = this.createUtilsJPanel();
		
		buttonsPanel.add(shapesPanel);
		buttonsPanel.add(colorsPanel);
		buttonsPanel.add(fillPanel);
		buttonsPanel.add(utilsPanel);
		
	    this.getContentPane().add(buttonsPanel);
	    
		this.paintPanel = new PaintPanel();

	    this.paintPanel.setCurrentColor(PaintApp.colors.get("Black"));
	    this.paintPanel.setCurrentShape(ENUMShapes.Line);
	    this.paintPanel.setIsFill(false);
	    
	    this.getContentPane().add(this.paintPanel);
	    
	    this.setVisible(true);
	}
	
	private final static HashMap<String, Color> colors = new HashMap<String, Color>(){{ 
		put("Black", Color.BLACK);
		put("Red", Color.RED);
		put("Blue", Color.BLUE);
		put("Yellow", Color.YELLOW);
		put("Green", Color.GREEN);
		put("Pink", Color.PINK);
	}};

	private PaintPanel paintPanel;
	
	private JPanel createColorsJPanel() {
	   JPanel btnPanel = new JPanel();
	   Iterator it = PaintApp.colors.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        String btnText = (String) pair.getKey();
	        JButton colorBtn = new JButton(btnText);
	        
	        colorBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 paintPanel.setCurrentColor((Color) pair.getValue());
	            }
	        });
	        
	        btnPanel.add(colorBtn);
	    }
	    
	    return btnPanel;
	}
	
	private JPanel createFillJPanel() {
		JPanel fillPanel = new JPanel();
		
		JButton fillBtn = new JButton("Fill On");
		fillBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 paintPanel.setIsFill(true);
            }
        });
		
		JButton noFillBtn = new JButton("Fill Off");
		noFillBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	 	paintPanel.setIsFill(false);
            }
        });
		
		fillPanel.add(noFillBtn);
		fillPanel.add(fillBtn);
		
		return fillPanel;
	}
	
	private JPanel createUtilsJPanel() {
		JPanel utilsPanel = new JPanel();
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 paintPanel.resetShapes();
            }
        });
		
		utilsPanel.add(clearBtn);
		
		return utilsPanel;
	}
	
	private JPanel createShapesPanel() {
		JPanel shapesPanel = new JPanel();
		
		JButton lineBtn = new JButton(ENUMShapes.Line.name());
		lineBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 paintPanel.setCurrentShape(ENUMShapes.Line);
            }
        });
		
		JButton ovalBtn = new JButton(ENUMShapes.Oval.name());
		ovalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	 paintPanel.setCurrentShape(ENUMShapes.Oval);
            }
        });
		
		JButton rectangleBtn = new JButton(ENUMShapes.Rectangle.name());
		rectangleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	 paintPanel.setCurrentShape(ENUMShapes.Rectangle);
            }
        });
		
		JButton smoothRectangleBtn = new JButton(ENUMShapes.SmoothRectangle.name());
		smoothRectangleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
           	 paintPanel.setCurrentShape(ENUMShapes.SmoothRectangle);
            }
        });
		
		shapesPanel.add(lineBtn);
		shapesPanel.add(ovalBtn);
		shapesPanel.add(rectangleBtn);
		shapesPanel.add(smoothRectangleBtn);
		
		return shapesPanel;
	}

}
