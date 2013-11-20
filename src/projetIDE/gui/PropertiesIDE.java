package projetIDE.gui;

import java.util.*;
import java.io.*;

public class PropertiesIDE extends Hashtable<String,Properties> {
    
    public PropertiesIDE(){
	// 	try
	// 	    {
	String separator = System.getProperty("file.separator");
	InputStream in = PropertiesIDE.class.getResourceAsStream("META-INF.properties");
	//		FileInputStream in = new FileInputStream("resources"+separator+"META-INF.properties");
	// Properties meta_properties = new Properties();
	// 		meta_properties.load(in);
	
	
	// 		Properties default_text_setting = new Properties();
	// 		default_text_setting.put("font","Monospaced");
	// 		default_text_setting.put("size","10");
	//...
	
	
	// Properties text = new Properties(default_text_setting);
	// 		in = new FileInputStream("resources"+separator+meta_properties.getProperty("1"));
	// 		text.load(in);
	// 		put("text",text);
	// 		
	
	// 		Properties icon = new Properties();
	// 		in = new FileInputStream("resources"+separator+meta_properties.getProperty("2"));
	// 		icon.load(in);
	
	
	// 	    }
	// 	catch(IOException e)
	// 	    {
	// 		e.printStackTrace();
	// 	    }
	
    }
    
    

}
