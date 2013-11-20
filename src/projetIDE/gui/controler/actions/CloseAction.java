package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CloseAction extends UtilAction {
    
    public CloseAction(String name, Icon icon,ListModel listSources,JTabbedPane tabbedpane){
	super(name,icon,listSources,tabbedpane);
	putValue(Action.SHORT_DESCRIPTION,"fermer le fichier courrant"); 

    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    String filename = getCurrentName();
	    Response response = save(filename);  
	    if(response.equals(Response.YES)){
		if(validName(filename)){
		    save(getDocument(filename),new File(((TextModel)getDocument(filename)).getAbsolutePath())); 
		    ((ListModelIde)listSources).removeDocument(filename);
		}else{
		    File file = changedName(filename);
		    save(getDocument(filename),file);
		    ((ListModelIde)listSources).removeDocument(file.getName());
		}
	    } else if(response.equals(Response.NO)){
		((ListModelIde)listSources).removeDocument(filename);
	    } else {
		//ne rien faire
	    }
	} else {
	    attention_msg();
	}
	
	
    }
}
