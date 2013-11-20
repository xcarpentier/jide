package projetIDE.gui;

import javax.swing.*;
import java.awt.*;
import projetIDE.gui.controler.actions.*;

public class WindowIde extends JFrame {
    private final double DEFAULT_WIDTH_IDE;
    private final double DEFAULT_HEIGHT_IDE;
    private final double DEFAULT_HEIGHT_SCREEN;
    private final double DEFAULT_WIDTH_SCREEN;
    
    
    public WindowIde(){
	super("JIDE");
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	DEFAULT_WIDTH_IDE = screenSize.getWidth()/1.3;
	DEFAULT_HEIGHT_IDE = screenSize.getHeight()/1.3;
	DEFAULT_WIDTH_SCREEN = screenSize.getWidth();
	DEFAULT_HEIGHT_SCREEN = screenSize.getHeight();
	
	Dimension frame_dimension = new Dimension((int)DEFAULT_WIDTH_IDE,(int)DEFAULT_HEIGHT_IDE);
	setIconImage(new ImageIcon(WindowIde.class.getResource("/resources/icons/logo.gif")).getImage());
	setPreferredSize(frame_dimension);
	int x =(int)(DEFAULT_WIDTH_SCREEN - DEFAULT_WIDTH_IDE)/2;
	int y =(int)(DEFAULT_HEIGHT_SCREEN - DEFAULT_HEIGHT_IDE)/2;
	setLocation(x,y);	
	setMaximizedBounds(new Rectangle(screenSize));
	setResizable(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	setVisible(true);
    }
}
  
