package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SaveAction extends UtilAction {
    
    public SaveAction(String name, Icon icon,ListModel listmodel,JTabbedPane tabbedpane) {
	super(name,icon,listmodel,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl S"));
	putValue(Action.SHORT_DESCRIPTION,"Sauvegarder"); 

    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    String filename = getCurrentName();
	    if(validName(filename)){
		save(getDocument(filename),new File(((TextModel)getDocument(filename)).getAbsolutePath()));
	    }else{
		save(getDocument(filename),changedName(filename));
	    }
	} else {
	    // message d'erreur !!!
	}

	    
   }
}

