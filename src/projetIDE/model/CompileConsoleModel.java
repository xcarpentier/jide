package projetIDE.model; 

import java.lang.*;
import java.util.*;
import java.io.*;

/**
   Permet de compiler un programme source java et d'executer le bytecode java (fichier x.class). 
   Previent les observers lorsqu'il y a une compilation ou une execution, et leurs envoie les messages correspondant.
   @author Carpentier Xavier & Saint Maxin Steeve
   @since 24 / 1 / 2006
   @version 1.0
*/
public class CompileConsoleModel extends Observable {
    
    /**
       Compile le fichier de chemin absolu <code>pathname</code> et spécifie les messages (erreur à la compilation ou non) aux observers.
       @param pathname chemin absolu du fichier à compiler.
    */
    public void compilJava(String pathname){
	try  
	    {
		String message="",tmp=null;
		Process p = new ProcessBuilder("javac",pathname).start();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		while((tmp=b.readLine()) != null){
		    message+=tmp+"\n";
		}
		setChanged();
		notifyObservers(message);
	    }
	catch(IOException e) 
	    {
		e.printStackTrace();
	    }
    }
    
    
    
    /**
       Test l'efficacite de la classe <code>ConsoleModel</code>
       @param args prend le nom du fichier java en premier argument, et le byte code ensuite, par exemple: "java ConsoleModel HelloWorld.java HelloWorld".
    */
//     public static void main(String[] args) {
// 	ConsoleModel c = new ConsoleModel();
// 	//	System.out.println(args[0]);
// 	c.compilJava("HelloWorld.java");
// 	c.execJava(".","HelloWorld");
//     }
}

