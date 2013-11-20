package projetIDE.gui.controler;


import javax.swing.*;
import javax.swing.event.*;

public class ListListenerIde implements ListSelectionListener {
    
    public JTabbedPane tabbed;
    public JList list;

    public ListListenerIde(JTabbedPane tabbed, JList list){
	this.tabbed=tabbed;
	this.list=list;
    }
    
    public void	valueChanged(ListSelectionEvent e){
	String name = (String)list.getSelectedValue();
	tabbed.setSelectedIndex(tabbed.indexOfTab(name)); 
    }
} 

