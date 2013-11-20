package projetIDE.gui.controler.actions;

import projetIDE.model.*;

import javax.swing.*;
import java.awt.event.*;


public class StopAction extends AbstractAction {
    
    private ExecuteConsoleModel console;
    
    public StopAction(String name, Icon icon, ExecuteConsoleModel console) {
	super(name,icon);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl K"));
	putValue(Action.SHORT_DESCRIPTION,"Stopper l'execution"); 
	this.console=console;
    }
    
    public void actionPerformed(ActionEvent e){
	console.kill();
    }
}

