package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.undo.*;

public class RedoAction extends UtilAction {
    
    public RedoAction(String name, Icon icon,ListModel listSources, JTabbedPane tabbedpane) {
	super(name,icon,listSources,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl R"));
	putValue(Action.SHORT_DESCRIPTION,"Refaire"); 
	
    }
    
    public void actionPerformed(ActionEvent e){
	TextModel text = (TextModel)getDocument(getCurrentName());
	UndoManager redomanager = text.getUndo();
	if(redomanager.canRedo()){
	    try
		{
		    redomanager.redo();
		    redomanager.redo();
		}
	    catch(CannotUndoException c)
		{}
	}
    }
}
