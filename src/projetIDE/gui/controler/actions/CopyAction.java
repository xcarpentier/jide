package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CopyAction extends UtilAction {
    
    public CopyAction(String name, Icon icon,JTabbedPane tabbedpane) {
	super(name,icon,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl C"));
	putValue(Action.SHORT_DESCRIPTION,"Copier"); 

    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    JTextPane text = getText();
	    text.copy();
	    text.select(0,0);
 	} else {
	    attention_msg();
	}

    }
}
