package projetIDE.gui.controler.actions;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class Filtre extends FileFilter {
  
    public boolean accept(File f) 
    {
	if(f.isDirectory()) 
	    return true;
	String extension = getExtension(f);
	if(extension.equals("java"))
	    return true; 
	return false;
    }
    
    public String getDescription() {
	return "Fichiers sources Java";
    }
    
    private String getExtension(File f) {
	String s = f.getName();
	int i = s.lastIndexOf('.');
	if (i > 0 &&  i < s.length() - 1) 
	    return s.substring(i+1).toLowerCase();
	return "";
    } 
}
