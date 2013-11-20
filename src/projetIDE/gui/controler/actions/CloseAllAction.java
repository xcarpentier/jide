package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CloseAllAction extends UtilAction {
    
    public CloseAllAction(String name, Icon icon,ListModel listSources,JTabbedPane tabbed) {
	super(name,icon,listSources,tabbed);
	putValue(Action.SHORT_DESCRIPTION,"fermer tout les fichiers"); 

    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    ArrayList<String> list = new ArrayList<String>(((ListModelIde)listSources).getList()); 
	    for(String filename : list) {
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
		} else if(response.equals(Response.NO)) {
		    ((ListModelIde)listSources).removeDocument(filename);
		} else {
		    //ne rien faire
		}
	    }
	} else {
	    //message d'erreur...
	    attention_msg();
	}
    }
}
