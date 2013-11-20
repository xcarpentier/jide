package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;
import projetIDE.gui.controler.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ExecuteAction extends UtilAction {
    
    private ExecuteConsoleModel console;
    private JTabbedPane view;
    private JTextArea text;

    public ExecuteAction(String name, Icon icon, ListModel model,JTabbedPane tabbedpane, ExecuteConsoleModel console, JTabbedPane view) {
	super(name,icon,model,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl E"));
	putValue(Action.SHORT_DESCRIPTION,"Executer"); 
	this.console=console;
	this.view=view;
	JScrollPane p = (JScrollPane)view.getComponentAt(1).getAccessibleContext().getAccessibleChild(0);
	text = (JTextArea)p.getViewport().getView();
	text.addKeyListener(new KeyListenerIde(text,console));
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    view.setSelectedIndex(1); 
	    String name = getCurrentName();
	    String pathname = ((TextModel)getDocument(name)).getAbsolutePath();
	    console.execution(pathname,name);
	} else {
	    // message d'erreur
	    attention_msg();
	}
	
    }
}
