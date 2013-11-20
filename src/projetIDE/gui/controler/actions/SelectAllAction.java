package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SelectAllAction extends UtilAction {
    
    public SelectAllAction(String name, Icon icon, JTabbedPane tabbedpane){
	super(name,icon,tabbedpane);
    }
    
    public void actionPerformed(ActionEvent e){
	JTextPane text = getText();
	text.setCaretPosition(0);
	text.selectAll();
    }
}
