package projetIDE.gui;

import projetIDE.model.*;

import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.BorderLayout;

/**
   Vue sur l'ensemble des fichiers sources courant. Observe la liste agréger à <code>ListModelIde</code> et se modifie en fonction de celle ci.
   Agrégation d'un objet <code>JTabbedPane</code> qui représente la liste des fichiers sources par onglet.
   @author Carpentier Xavier & Saint-Maxin Steeve
   @since 20 / 1 / 2006
   @version 1.0
*/
public class TabbedPaneTextGUI extends JTabbedPane implements Observer {
    
    
    public TabbedPaneTextGUI(ListModel model){
	super();
	((ListModelIde)model).addObserver(this);
    }
    
    public void update(Observable o, Object arg) {
    	ListModelIde model = (ListModelIde)o;  
	String name = (String)arg;
	if(name.substring(0,6).equals("remove")){
	    removeTabAt(indexOfTab(name.substring(6)));
	}else{
	    int tabcount = getTabCount();
	    if(tabcount==0){
		addTab((String)arg,new TextGUI(model.getDocument(name)));
	    }else{
		insertTab((String)arg,null,new TextGUI(model.getDocument(name)),null,tabcount);
		setSelectedIndex(getTabCount()-1);
	    }
    	}
    }
    
}
