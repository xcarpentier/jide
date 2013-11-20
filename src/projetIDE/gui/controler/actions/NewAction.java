package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NewAction extends AbstractAction {
    
    private ListModel model;
    public int nb_SansNom; 
    
    public NewAction(String name, Icon icon, ListModel model) {
	super(name,icon);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl N"));
	putValue(Action.SHORT_DESCRIPTION,"Nouveau"); 
	this.model = model;
	nb_SansNom=1;
    }
    
    public void actionPerformed(ActionEvent e){
	try
	    {
		String sans_nom = "Sans nom "+nb_SansNom;
		ListModelIde sources =(ListModelIde)model;
		Object doclock = new Object();	
		TextModel text = new TextModel(sans_nom,"");
		sources.setDocument(sans_nom,text);
		nb_SansNom++;
	    }
	catch(NullPointerException npe)
	    {
		npe.printStackTrace();
	    }
	catch(NoSuchElementException nsee)
	    {
		nsee.printStackTrace();
	    }
    }
}
