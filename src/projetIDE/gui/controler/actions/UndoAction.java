package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.undo.*;

public class UndoAction extends UtilAction {
    
    public UndoAction(String name, Icon icon,ListModel listSources, JTabbedPane tabbedpane) {
	super(name,icon,listSources,tabbedpane);
    }
    
    public void actionPerformed(ActionEvent e){
	TextModel text = (TextModel)getDocument(getCurrentName());
	UndoManager undomanager = text.getUndo();
	if(undomanager.canUndo()){
	    try
		{
		    undomanager.undo();
		    undomanager.undo();
		}
	    catch(CannotUndoException c)
		{}
	}
    }
}
