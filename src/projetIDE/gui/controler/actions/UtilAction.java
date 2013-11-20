package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;

public abstract class UtilAction extends AbstractAction {
    
    protected ListModel listSources;
    protected JTabbedPane tabbedpane;
    protected enum Response {YES,NO,CANCEL};
    
    public UtilAction(String name,Icon icon,ListModel listSources,JTabbedPane tabbedpane) {
	super(name,icon);
	this.tabbedpane=tabbedpane;
	this.listSources=listSources;
    }
    
    public UtilAction(String name, Icon icon,JTabbedPane tabbedpane) {
	super(name,icon);
	this.tabbedpane=tabbedpane;
    }
    
    protected void save(Document document,File source){
	TextModel text = (TextModel)document;
	if(!text.isSave()){
	    try
		{
		    PrintWriter pw = new PrintWriter(new FileWriter(source),true);
		    BufferedReader buf = new BufferedReader(new StringReader(text.getText(0,text.getLength())));
		    String tmp;
		    while((tmp = buf.readLine())!=null)
			pw.println(tmp);
		    text.setSave(true);
		}
	    catch(IOException ioe)
		{
		    ioe.printStackTrace();
		}
	    catch(BadLocationException ble)
		{
		    ble.printStackTrace();
		}
	}
    }

    protected Response save(String filename) {
	int selection = JOptionPane.showConfirmDialog(new JFrame(),"Voulez vous enregistrer le fichier "+filename, "Enregistrer "+filename+" ?",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	if(selection == JOptionPane.YES_OPTION){
	    return Response.YES;
	} else if (selection == JOptionPane.NO_OPTION){
	    return Response.NO;
	}
	return Response.CANCEL;
    }
    
    protected void attention_msg(){
	JOptionPane.showMessageDialog(new JFrame(),"Aucun fichier n'est ouvert", "Attention",JOptionPane.WARNING_MESSAGE);
    }
    
    protected boolean validName(String filename){
	if(filename.length()<9) return true;
	return !(filename.substring(0,9).equals("Sans nom "));
    }
    
    public StyledDocument getCurrentDocument(){
	return ((ListModelIde)listSources).getDocument(getCurrentName());
    }
    
    public StyledDocument getDocument(String filename){
	return ((ListModelIde)listSources).getDocument(filename); 
    }
  
    protected File changedName(String filename){
	JFileChooser filechooser = new JFileChooser();
	filechooser.setDialogTitle(filename); 
	filechooser.setFileFilter(new Filtre()); 
	if((filechooser.showSaveDialog(new JFrame("<"+filename+">"+" Enregistrer sous")))==JFileChooser.APPROVE_OPTION){
	    File sources = filechooser.getSelectedFile();
	    ((TextModel)((ListModelIde)listSources).getDocument(filename)).setAbsolutePath(sources.getAbsolutePath());
	    StyledDocument doc = getDocument(filename);
	    ((ListModelIde)listSources).removeDocument(filename);
	    ((ListModelIde)listSources).setDocument(sources.getName(),doc);

	    return sources;
	}
	return new File(filename);
    }
    
    protected boolean testTabbed(){
	return tabbedpane.getTabCount()!=0;
    }

    protected String getCurrentName(){
	return tabbedpane.getTitleAt(tabbedpane.getSelectedIndex());
    }

    protected JTextPane getText(){
	JScrollPane p = (JScrollPane)tabbedpane.getSelectedComponent().getAccessibleContext().getAccessibleChild(0);
	JPanel panel = (JPanel)p.getViewport().getView();
	return (JTextPane)panel.getComponent(1);
    }
    
    

    public static void main(String[] args){
	try
	    {
		Document a = new DefaultStyledDocument();
		a.insertString(0,"public class HelloWorld extends Object {\n"+
			       "    public static void main(String[] args){\n"+
			       "        System.out.println(\"Hello World\");\n"+
			       "    }\n"+
			       "  }\n"
			       ,new SimpleAttributeSet());
		/* mettre la méthode save en static pour faire le test */
		//save("/tmp/truc.bidule",a); 		
	    }
	catch(BadLocationException ble)
	    {
		ble.printStackTrace();
	    }
	

    }
}
