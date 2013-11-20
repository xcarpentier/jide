package projetIDE.gui.controler;

import projetIDE.gui.*;

import java.awt.event.*;
import javax.swing.*;

public class SearchButtonListener implements ActionListener {
    
    private SearchGui fenetre;
    
    public SearchButtonListener(SearchGui fenetre){
	this.fenetre=fenetre;
    }
    
    public void actionPerformed(ActionEvent e){
	String s = e.getActionCommand();
	if(s.equals("search")){
	    
	} else if(s.equals("search all")) {
	    
	} else if(s.equals("replace")) {
	    
	} else {
	    
	}
	
    }
    
    
} 
