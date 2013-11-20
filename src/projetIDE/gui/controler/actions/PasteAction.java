package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class PasteAction extends UtilAction {
    
    public PasteAction(String name, Icon icon, JTabbedPane tabbedpane) {
	super(name,icon,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl V"));
	putValue(Action.SHORT_DESCRIPTION,"Coller"); 
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    JTextPane text = getText();
	    text.paste();
	} else {
	    attention_msg();
	}  
    }
}
