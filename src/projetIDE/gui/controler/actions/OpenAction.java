package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*; 


public class OpenAction extends AbstractAction {
    
    private ListModel model;
    
    public OpenAction(String name,Icon icon,ListModel model) {
	super(name,icon);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl O"));
	putValue(Action.SHORT_DESCRIPTION,"Ouvrir"); 
	this.model=model;
    }
    
    public void actionPerformed(ActionEvent e){
	try
	    {	
		ListModelIde model_ide = (ListModelIde)model; 
		JFileChooser filechooser = new JFileChooser();//essayer de trouvé comment faire un filechooser commun ???(pour action Save et Open)
		filechooser.setFileFilter(new Filtre()); 
		if((filechooser.showOpenDialog(new JFrame()))==JFileChooser.APPROVE_OPTION) { 
		    String absolutePath = filechooser.getSelectedFile().getAbsolutePath();
		    BufferedReader buffer = new  BufferedReader(new FileReader(absolutePath));
		    String str="",tmp=null;
		    while((tmp = buffer.readLine()) != null)
			str+=tmp+"\n";
		    TextModel text = new TextModel(absolutePath,str);
		    model_ide.setDocument(filechooser.getSelectedFile().getName(),text);
		}
	    }
	catch(IOException io)
	    {
		io.printStackTrace();
	    }
	catch(NullPointerException npe)
	    {
		npe.printStackTrace();
	    }
	catch(NoSuchElementException nsee)
	    {
		nsee.printStackTrace();
	    }
    }
}
