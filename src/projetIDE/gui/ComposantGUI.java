package projetIDE.gui;

import projetIDE.gui.controler.actions.*;

import javax.swing.*;

public class ComposantGUI {
    
    public ActionMap actions;
    
    public ComposantGUI(ListModel list, JTabbedPane tabbedpane, JComponent window_global){
	actions = window_global.getActionMap();
	InputMap imap = window_global.getInputMap(JComponent.WHEN_FOCUSED);
    }

    public ActionMap getActionMap(){
	return actions;
    }

}
