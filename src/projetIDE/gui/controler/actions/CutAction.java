package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CutAction extends UtilAction {
    
    public CutAction(String name, Icon icon, JTabbedPane tabbedpane) {
	super(name,icon,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl X"));
	putValue(Action.SHORT_DESCRIPTION,"Couper");
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()){
	    JTextPane text = getText();
	    text.cut();
	    text.select(0,0);
	} else {
	    attention_msg();
	}
    }
}

