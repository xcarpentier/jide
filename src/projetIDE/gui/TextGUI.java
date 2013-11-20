package projetIDE.gui; 

import projetIDE.model.*;
import projetIDE.gui.controler.*;

import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;

public class TextGUI extends JPanel {
    
    private JTextPane text;
    
    public TextGUI(StyledDocument model){ 
	super(new BorderLayout());
	text = new JTextPane(model);
	text.setCaretPosition(0);
	text.setMargin(new Insets(5,10,5,5));
	text.setSelectedTextColor(Color.white);
	text.setSelectionColor(Color.blue.brighter());
	JPanel text_line = new LineCountGUI(text);
	text.setFocusable(true);
	JPanel group = new JPanel(new BorderLayout());
	group.add(text_line,BorderLayout.WEST);
	group.add(text,BorderLayout.CENTER);
	JScrollPane scrollPane = new JScrollPane(group,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	setPreferredSize(new Dimension(450,110));
	add(scrollPane, BorderLayout.CENTER);
    }
    
    public JTextPane getText(){
	return text;
    } 
}

