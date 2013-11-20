package projetIDE.gui.controler;

import  projetIDE.gui.controler.actions.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class WindowListenerIde extends WindowAdapter {
    
    private ActionMap map;
    
    public WindowListenerIde(ActionMap map){
    	super();
	this.map=map;
	
    }
    
    public void windowClosing(WindowEvent e) {
	Action action = map.get("quit");
	action.actionPerformed(new ActionEvent(new Object(),0,""));
	map.put("quit",action);
	
    }
   
}
