package projetIDE.model; 

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;

import java.awt.*;

import static java.awt.Color.*;

import java.util.*;

/**
   Objet text developpé pour obtenir une coloration syntaxique; par consequant il encapsule une table d'ensemble clé valeur qui charge des styles pour un ensemble de mots donnés en fonction d'une 
   propriété dans les régles du langage (pour ce projet le langage est le Java).
   @version 1.0
   @since 12 / 1 / 2006 
 */
public class TextModel extends DefaultStyledDocument {
    
    private Hashtable<String,SimpleAttributeSet> styles;
    private Colorer colorer;
    private Object doclock;
    private DocumentReader documentReader;
    public boolean isSave;
    public String absolutePathname;
    public UndoManager undo; 
    
    
    /**
       
    
    */
    public TextModel(String absolutePathname, String str){
	super();
	styles = new Hashtable<String,SimpleAttributeSet>();
	initStyles();
	doclock = new Object();	
	documentReader = new DocumentReader(this);
	colorer = new Colorer(doclock,this,documentReader);
	try 
	    {
		
		if(str.length()>0)
		    insertString(0,str,new SimpleAttributeSet());
	    }
	catch(BadLocationException e)
	    {
		e.printStackTrace();
	    }
	
	colorer.start();
	isSave=false;
	this.absolutePathname=absolutePathname;
	undo = new UndoManager();
	addUndoableEditListener(undo);
    }

    /**
       
    */
    public UndoManager getUndo(){
	return undo;
    }
    
    
	
     /**
     
     
     */
    public void setSave(boolean save){
	this.isSave=save;
    }

    /**
       
    
    */
    public boolean isSave(){
	return this.isSave;
    }
    
    /**
       
    
    */
    public String getAbsolutePath(){
	return this.absolutePathname;
    }
    
    /**
       
    
    */
    public void setAbsolutePath(String absolutePathname){
	this.absolutePathname=absolutePathname;
	
    }
    
    /**
       
    
    */
    public SimpleAttributeSet getHashStyle(String styleName){
        return styles.get(styleName);
    }
    
    
    /**
      
    
    */
    public void colorAll(){
	color(0, getLength());
    }

    /**
   
    
    */
    public void color(int position,int adjustment){
        colorer.color(position, adjustment);
    }

    /**
     
  
    */
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	synchronized (doclock){
	    super.insertString(offs, str, a);
	    color(offs, str.length());
	    documentReader.update(offs, str.length());
	}
    }
    /**
     
  
    */
    public void remove(int offs, int len) throws BadLocationException {
	synchronized (doclock){
	    super.remove(offs, len);
	    color(offs, -len);
	    documentReader.update(offs, -len);
	}
    }
    
    /**
     
  
    */    
    private void initStyles(){
      
	SimpleAttributeSet style;
	
	style = new SimpleAttributeSet(); 
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, false);
	styles.put("body", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.blue);
        StyleConstants.setBold(style, true);
	styles.put("tag", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.blue);
        StyleConstants.setBold(style, false);
	styles.put("endtag", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, false);
	styles.put("reference", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
        StyleConstants.setBold(style, true);
	styles.put("name", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
        StyleConstants.setBold(style, false);
	styles.put("value", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
        StyleConstants.setBackground(style, Color.white);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
	styles.put("text", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.blue);
        StyleConstants.setBold(style, false);
	styles.put("reservedWord", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, false);
	styles.put("identifier", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
        StyleConstants.setBackground(style, Color.white);
        StyleConstants.setForeground(style, new Color(0xB03060)/*Color.maroon*/);
        StyleConstants.setBold(style, false);
	styles.put("literal", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
        StyleConstants.setBackground(style, Color.white);
        StyleConstants.setForeground(style, new Color(0x000080)/*Color.navy*/);
        StyleConstants.setBold(style, false);
	styles.put("separator", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
	styles.put("operator", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.green.darker());
        StyleConstants.setBold(style, false);
	styles.put("comment", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, new Color(0xA020F0).darker()/*Color.purple*/);
        StyleConstants.setBold(style, false);
	styles.put("preprocessor", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, false);
	styles.put("whitespace", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.red);
        StyleConstants.setBold(style, false);
	styles.put("error", style);

        style = new SimpleAttributeSet();
        StyleConstants.setFontFamily(style, "Monospaced");
        StyleConstants.setFontSize(style, 12);
	StyleConstants.setForeground(style, Color.orange);
        StyleConstants.setBold(style, false);
	styles.put("unknown", style);
    }

}
