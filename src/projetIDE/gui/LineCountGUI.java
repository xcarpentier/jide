package projetIDE.gui;

import projetIDE.model.*;
import projetIDE.gui.controler.*;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class LineCountGUI extends JPanel {
    
    private JTextPane text; 
    private JTextArea text_line;
    private static boolean first = true;

    public LineCountGUI(JTextPane text){
	super(new BorderLayout());
	Document model = text.getDocument();
	model.addDocumentListener(new TextListenerIde(this));
	this.text=text;
	initTextLine();
    }
    
    public void initTextLine() {
	Thread Line = new Thread() { 
		public void run(){
		    try
			{
			    if(first){
				removeAll();
			    }
			    text_line=new JTextArea();
			    text_line.setBackground(new Color(0xBBDCEE)); 
			    text_line.setSelectionColor(new Color(0xBBDCEE));
			    text_line.setSelectedTextColor(Color.BLACK);
			    text_line.setForeground(Color.BLACK);
			    text_line.setFont(new Font(null, Font.BOLD, 12));
			    text_line.setMargin(new Insets(6,10,6,6));
			    text_line.setEditable(false);
			    BufferedReader buf = new BufferedReader(new StringReader(text.getText()));
			    String tmp;
			    int nb_line=0;
			    while((tmp = buf.readLine())!=null)
				{
				    nb_line++;
				    text_line.append(nb_line+"\n");
				}
			    //if(first)
			    //text_line.setCaretPosition(0);
			}
		    catch(IOException e)
			{
			    e.printStackTrace();
			}
		}
	    };
	Line.run();
	if(first)
	    text_line.setCaretPosition(0);
	first=false;
	text_line.revalidate();
	revalidate();
	add(text_line,BorderLayout.CENTER);
    }
}
