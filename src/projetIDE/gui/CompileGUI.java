package projetIDE.gui;

import projetIDE.model.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class CompileGUI extends JPanel implements Observer {
    
    private JTextArea message;
    
    public CompileGUI(CompileConsoleModel model){
	super(new BorderLayout());
	model.addObserver(this);
	message=new JTextArea();
	message.setEditable(false);
	JScrollPane scroll = new JScrollPane(message);
	add(scroll,BorderLayout.CENTER);
    }
    
    public void update(Observable o, Object arg){
	String compilation = (String)arg;
	if(compilation.length()>0){
	    message.setText(compilation);
	} else {
	    message.setText("Aucune erreur à la compilation.");
	}    
    }
}
