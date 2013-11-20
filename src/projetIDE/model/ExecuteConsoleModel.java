package projetIDE.model; 

import java.lang.*;
import java.util.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.*;

/**
   Permet de excuter le bytecode d'un programme source java (fichier Class.class). 
   Previent les observers lorsqu'il y a une compilation ou une execution, et leurs envoie les messages correspondant.
   @author Carpentier Xavier & Saint Maxin Steeve
   @since 13 / 2 / 2006
   @version 1.0
*/
public class ExecuteConsoleModel extends Observable {
        
    //private Process process;
    private OutputStream out;
    private Thread waiting;
    private Process process;
    
    /**
       Execute le fichier de chemin absolu <code>pathname</code> et spécifie les message d'erreurs ou les affichages sur la sortie standard.
       @param pathname  chemin absolu du fichier à executer.
    */
    public void execution(String pathname,String filename) {
	String path = getPath(pathname);
	String name = getName(filename);
	Thread process = new ExecuteProcess(path,name);
	process.start();
    }
    
    
    private String getName(String filename){
	// faire un test pour voir si le programme est dans un package (AST permet de trouver le nom du package).
	int i = filename.lastIndexOf('.');
	if (i > 0 &&  i < filename.length() - 1){
	    return filename.substring(0,i);
	}
	return "";
	
    }
    
    private String getPath(String pathname){
	int i = pathname.lastIndexOf(System.getProperty("file.separator"));
	if (i > 0 &&  i < pathname.length() - 1) {
	    return pathname.substring(0,i);
	}
	return "";
    }
    
    public OutputStream getOutputStream() {
	return this.out; //process.getOutputStream();
    }
    
    public Thread getWaitExecution() {
	return waiting;
    }
    
    public boolean isAlive(){
	return waiting.isAlive();
    }
    
    public void kill() {
	if(waiting!=null){
	    if(waiting.isAlive()){
		process.destroy();
		 setChanged();
		 notifyObservers("exit value : 0 \n");
	    }
	}
    }
    

    /**
     * Classe d'attente du processus lancé par l'utilisateur de JIDE.
     **/
    class WaitExecution extends Thread {
	private Process p;
	
	public WaitExecution(Process p){
	    this.p=p;
	}
	
	public void run(){
	    try
		{
		    int exitValue = p.waitFor();
		}
	    catch(InterruptedException in)
		{
		    in.printStackTrace();
		}
	}
    }
    

    /**
     * Classe de déclenchement du processus de l'utilisateur de JIDE.
     **/
    class ExecuteProcess extends Thread {
	private String name;
	private String path;
	
	public ExecuteProcess(String path,String name){
	    this.path=path;
	    this.name=name;
	}
	
	public void run(){
	    try
		{
		    String classpath = "$CLASSPATH:"+path;
		    process = new ProcessBuilder("java","-classpath",classpath,name).start();
		    out = process.getOutputStream();
		    waiting = new WaitExecution(process);
		    waiting.start();
		    BufferedReader buf_in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    BufferedReader buf_err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		    String tmp = null;
		    while(waiting.isAlive())
			{
			    while((tmp=buf_in.readLine())!=null){
				setChanged();
				notifyObservers(tmp+"\n");
			    }
			    
			    while((tmp=buf_err.readLine())!=null){
				setChanged();
				notifyObservers(tmp+"\n");
			    }
			}
		    setChanged();
		    notifyObservers("exit value : "+process.exitValue()+" \n");
		}
	    catch(IOException ioe)
		{
                  
		}   
	}
    }
       
}

