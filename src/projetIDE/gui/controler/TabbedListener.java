package projetIDE.gui.controler;

import projetIDE.model.ast.*;
import projetIDE.model.*;
import projetIDE.gui.*;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.io.*;

public class TabbedListener implements ChangeListener {
    
    private JTabbedPane tabbed;
    private JTextPane current_text;
    private JPanel tree;
    private ListModel model;
    private String source;
    
    public TabbedListener(JTabbedPane tabbed,JPanel tree,ListModel model){
	this.tabbed=tabbed;
	this.tree=tree;
	this.model=model;
    }
    
    public void stateChanged(ChangeEvent e){
	ListModelIde list = (ListModelIde)model;
	if(list.getSize()>0) {
	    JTabbedPane tabbedpane = (JTabbedPane)e.getSource();
	    if(tabbedpane.getTabCount()>0) {
		JScrollPane p = (JScrollPane)tabbedpane.getSelectedComponent().getAccessibleContext().getAccessibleChild(0);
		JPanel intermediate = (JPanel)p.getViewport().getView();
		current_text = (JTextPane)intermediate.getComponent(1);
		source = current_text.getText();
		if(new TestCompil().test(source) && source!=null){
		    if(source.length()>0){
			Thread init = new Thread(){
				public void run(){
				    ((TreeGUI)tree).initTree(new AbstractSyntaxTreeModel(new StringReader(source)));
				}
			    };
			init.run();
		    }
		}
		current_text.addKeyListener(new KeyListenerTab());
	    }
	} else {
	    ((TreeGUI)tree).removeTree();
	}

    }
    
    class KeyListenerTab extends KeyAdapter {
	
	public void keyPressed(KeyEvent e){
	    if(e.getKeyCode()==KeyEvent.VK_INSERT){
		if(current_text.getCaretColor().equals(Color.RED)){
		    current_text.setCaretColor(Color.BLACK);
		}else{
		    current_text.setCaretColor(Color.RED);
		}
	    }
	    
	    if(e.getKeyChar()=='}' || e.getKeyChar()==';' || e.getKeyChar()=='\n' || e.getKeyChar()==' ') {
		source = current_text.getText();
		if(new TestCompil().test(source)) {
		    Thread init = new Thread(){
			    public void run(){
				((TreeGUI)tree).initTree(new AbstractSyntaxTreeModel(new StringReader(source)));
			    }
			};
		    init.run();
		}
	    } 
	}
    }
    
    
}

