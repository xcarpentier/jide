package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;

public class SearchReplaceAction extends UtilAction {
    
    public SearchReplaceAction(String name, Icon icon, JTabbedPane tabbedpane) {
	super(name,icon,tabbedpane);
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    
	    JTextPane text = getText();
	    new SearchGui();
	} else {
	    //message d'erreur
	}
    }
    
}
