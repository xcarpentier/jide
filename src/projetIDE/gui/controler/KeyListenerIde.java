package projetIDE.gui.controler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import projetIDE.model.*;


public class KeyListenerIde extends KeyAdapter {
    
    private JTextArea text;
    private OutputStream out;
    private Thread waiting;
    private ExecuteConsoleModel model;
    private String tmp;
    
    public KeyListenerIde(JTextArea text,ExecuteConsoleModel model) {
	this.text=text;
	this.model=model;
	tmp="";
    }
    
    public void keyPressed(KeyEvent e){
        char carac = e.getKeyChar();
       	int ascii_cod = (int)carac;
	out = model.getOutputStream();
	PrintStream ps = new PrintStream(out);
	BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(out));;
	waiting = model.getWaitExecution();
	//try
	//  {
		if(waiting.isAlive()){
		    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && tmp.length()>0){
			String t = text.getText();
			String t2 = t.substring(0,t.length()-1);
			tmp = tmp.substring(0,tmp.length()-1);
			text.setText(t2);
		    } 
		    if(((ascii_cod>=32 && ascii_cod<=128) || carac == '\n') && e.getKeyCode() != KeyEvent.VK_DELETE){
			if(carac=='\n'){
			    //  buf.write(tmp,0,tmp.length());
			    // 			    buf.close();
			    // 			    //buf.flush();
			    // 			    buf = new BufferedWriter(new OutputStreamWriter(out));
			    ps.println(tmp);
			    ps.flush();
			    tmp="";
			    text.append(carac+"");
			} else {
			    tmp+=carac+"";
			    text.append(carac+"");
			}
		    }
		    
		}
		// }
		//catch(IOException io)
		//  {
		//io.printStackTrace();
		//text.append("\n   *///ERREUR IDE///*   \n");
		//  }
    }   
}
