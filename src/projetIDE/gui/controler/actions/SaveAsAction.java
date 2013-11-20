package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SaveAsAction extends UtilAction {
    
    public SaveAsAction(String name, Icon icon,ListModel listmodel,JTabbedPane tabbedpane) {
	super(name,icon,listmodel,tabbedpane);
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    String old_name = getCurrentName();
	    save(getDocument(old_name),changedName(old_name));
	}else{
	    //message d'erreur...
	}
    }

}
