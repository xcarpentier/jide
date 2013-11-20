package projetIDE.gui;
 
import projetIDE.model.*;
import projetIDE.gui.controler.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.event.*;

public class ListGUI extends JPanel implements Observer {
    
    private JList list;
    private ListSelectionListener listener;
    private ListModelIde model;
    private JTabbedPane tabbed;

    public ListGUI(ListModel model,JTabbedPane tabbedpane){
	super(new BorderLayout());
	this.tabbed=tabbedpane;
	initList(model);
	this.model=(ListModelIde)model;
	this.model.addObserver(this);
	add(new JScrollPane(list),BorderLayout.CENTER);
    }
    
    private void initList(ListModel model){
	list = new JList(model);
	listener = new ListListenerIde(tabbed,list);
	list.addListSelectionListener(listener);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
    }
    
    public void update(Observable o, Object arg){
	removeAll();
	initList((ListModel)o);
	add(new JScrollPane(list),BorderLayout.CENTER);
	list.revalidate();
	revalidate();
    }
}
