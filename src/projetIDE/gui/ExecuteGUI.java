package projetIDE.gui;

import projetIDE.model.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;

public class ExecuteGUI extends JPanel implements Observer {
    
    private JTextArea message;
    private String last_execution;
    
    public ExecuteGUI(ExecuteConsoleModel model){
	super(new BorderLayout());
	model.addObserver(this);
	last_execution = "";
       	message = new JTextArea();
	message.setEditable(false); 
	JScrollPane scroll = new JScrollPane(message);
	add(scroll,BorderLayout.CENTER);
    }
    
    public void update(Observable o, Object arg){
	String execution = (String)arg;
	if(last_execution.length()>12 && last_execution.substring(0,12).equals("exit value :")){
	    message.setText("");
	}
	message.append(execution);
	last_execution=execution;
    }
}
