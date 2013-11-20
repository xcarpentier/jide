package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SaveAllAction extends UtilAction {
    
    
    public SaveAllAction(String name, Icon icon,ListModel listmodel,JTabbedPane tabbedpane) {
	super(name,icon,listmodel,tabbedpane);
    }
    
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    ArrayList<String> list = new ArrayList<String>(((ListModelIde)listSources).getList()); 
	    for(String filename : list){
		if(validName(filename)){
		    save(getDocument(filename),new File(((TextModel)getDocument(filename)).getAbsolutePath()));
		}else{
		    save(getDocument(filename),changedName(filename));
		}
	    }
	}else{
	    //message d'erreur...
	}
    }
}

