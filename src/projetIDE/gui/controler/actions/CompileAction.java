package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CompileAction extends UtilAction {
    
    private CompileConsoleModel console;
    private JTabbedPane view;    
    
    public CompileAction(String name, Icon icon,ListModel listSources,JTabbedPane tabbedpane,CompileConsoleModel console, JTabbedPane view){
	super(name,icon,listSources,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl B"));
	putValue(Action.SHORT_DESCRIPTION,"Compiler le fichier courant"); 

	
	this.console=console;
	this.view=view;
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    view.setSelectedIndex(0); 
	    String filename = getCurrentName();
	    String absolutePath = ((TextModel)getDocument(filename)).getAbsolutePath();
	    if(validName(filename)){
		File file = new File(absolutePath);
		save(getDocument(filename),file);
		console.compilJava(file.getAbsolutePath());
	    }else{
		File file = changedName(filename);
		save(getDocument(filename),file);
		console.compilJava(file.getAbsolutePath());
	    }
	} else {
	    // message d'erreur !!!
	    attention_msg();
	}
    }

    
}
