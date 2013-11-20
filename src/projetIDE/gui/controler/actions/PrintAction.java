package projetIDE.gui.controler.actions;

import projetIDE.model.*;
import projetIDE.gui.*;


import javax.swing.*;
import java.awt.print.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.awt.*;

public class PrintAction extends UtilAction {
    
    public PrintAction(String name, Icon icon, ListModel listSources, JTabbedPane tabbedpane) {
	super(name,icon,listSources,tabbedpane);
	putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke("ctrl P"));
	putValue(Action.SHORT_DESCRIPTION,"Imprimer"); 
	
    }
    
    public void actionPerformed(ActionEvent e){
	if(testTabbed()) {
	    PrinterJob printjob = PrinterJob.getPrinterJob();
	    printjob.setJobName(getCurrentName());
	    if(!printjob.printDialog(new HashPrintRequestAttributeSet())){
		System.out.println("erreur print");
		//ne rien faire
	    } else {
		try
		    {
			printjob.print();
		    }
		catch(PrinterException pr)
		    {
			pr.printStackTrace();
		    }
	    }
	} else {
	    //message d'erreur car il n'y a pas de fichier ouvert 
	    attention_msg();
	}
	
    }
}
