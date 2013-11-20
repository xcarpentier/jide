package projetIDE.gui.controler.actions;

import projetIDE.gui.*;

import javax.swing.JDialog;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AboutAction extends AbstractAction {
    
    private AboutDialog dialog;
    
    public AboutAction(String name, Icon icon) {
	super(name,icon);
	dialog = new AboutDialog();
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl T"));
	putValue(Action.SHORT_DESCRIPTION,"à propos de"); 
	
    }
    
    public void actionPerformed(ActionEvent e){
	dialog = new AboutDialog();
	dialog.setVisible(true); 
    }
    
    class AboutDialog extends JDialog {
	
	public AboutDialog(){
	    super(new JFrame(),"à propos de",true);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double w = screenSize.getWidth();
	    double h = screenSize.getHeight();
	    int x = 463;
	    int y = 206;
	    setLocation((int)(w-x)/2,(int)(h-y)/2);
	    setResizable(false);
	    //	    setSize((int)x,(int)y);
	    
	    setLayout(new GridBagLayout());
	    String message = "<HTML> <BODY ALIGN=\"CENTER\">"+
		"<TABLE ALIGN=\"CENTER\" BORDER=\"1\" WIDTH=\"100%\" BGCOLOR=\"#BBDCEE\"> <TR><TD><H1 ALIGN=\"CENTER\" ><B>J I D E</B></H1></TD></TR>"+
		"<TR><TD ALIGN=\"CENTER\">version 0.1</TD></TR><TR><TD ALIGN=\"CENTER\">Since 2006/03/1</TD></TR>"+
		"<TR><TD ALIGN=\"CENTER\">Authors : Carpentier Xavier & Steeve Saint-Maxin</TD></TR>"+
		"<TR><TD ALIGN=\"CENTER>\">Dans le cadre du projets EUDIP de 2éme année de l'iut A de Lille 1 </TD></TR>";
	    JEditorPane text = new JEditorPane("text/html",message);
	    text.setEditable(false);
	    add(text,new GBC(0,0,3,1).setAnchor(GBC.CENTER).setFill(GBC.HORIZONTAL).setWeight(100,100).setInsets(0));
	    AbstractAction action = new AbstractAction("OK") {
		    public void actionPerformed(ActionEvent e){
			setVisible(false);
		    }
		};
	    action.putValue(Action.MNEMONIC_KEY,new Integer(KeyEvent.VK_O));
	    JButton ok = new JButton(action);
	    add(ok,new GBC(2,2).setAnchor(GBC.CENTER).setInsets(1).setWeight(0,0));
	    pack();
	}
    }
      
    public static void main(String[] args){
	Action a = new AboutAction("",new ImageIcon());
	a.actionPerformed(new ActionEvent(new Object(),0,""));
	
    }
}
