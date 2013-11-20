package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;
import projetIDE.model.ast.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ValidAction extends UtilAction {
    
    private CompileConsoleModel console;
    private JTabbedPane view;    
    private boolean first = true;
    private Analyseur javaParser;
    
    public ValidAction(String name, Icon icon,ListModel listSources,JTabbedPane tabbedpane,CompileConsoleModel console, JTabbedPane view){
	super(name,icon,listSources,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl Y"));
	putValue(Action.SHORT_DESCRIPTION,"Validation du code source"); 
	this.console=console;
	this.view=view;
    }
    
    public void actionPerformed(ActionEvent e){
	JScrollPane p = (JScrollPane)view.getSelectedComponent().getAccessibleContext().getAccessibleChild(0);
	JTextArea area = (JTextArea)p.getViewport().getView();
	area.setText("");
	if(testTabbed()){
	    view.setSelectedIndex(0); 
	    String text = getText().getText();
	    StringReader reader = new StringReader(text);
	    try 
	    {
		javaParser.ReInit(reader);
		javaParser.CompilationUnit();
		area.setText("Source valide");
	    }
	    catch(Exception e2)
		{
		    area.setText(e2.getMessage());
		}
	} else {
	    // message d'erreur !!!
	}
    }

    
}
