package projetIDE.gui.controler;

import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;

import projetIDE.model.*;
import projetIDE.gui.*;

public class TextListenerIde implements DocumentListener { 
    
    LineCountGUI line_text;
    
    public TextListenerIde(JPanel line_text){
	this.line_text =(LineCountGUI) line_text;
    } 
    
    
    public void changedUpdate(DocumentEvent e){}
    
    public void insertUpdate(DocumentEvent e){
 	line_text.initTextLine();
	TextModel text = (TextModel)e.getDocument();
	text.setSave(false);
    }
    
    public void removeUpdate(DocumentEvent e){
	line_text.initTextLine();
	TextModel text = (TextModel)e.getDocument();
	text.setSave(false);

    } 
    
}
