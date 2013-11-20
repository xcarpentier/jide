package projetIDE.gui;

import projetIDE.gui.controler.actions.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToolBarIde extends JPanel {
   
    public ToolBarIde(ActionMap map){
        Object[] keys = map.keys();
	JToolBar toolbar = new JToolBar("JIDE's toolbar",SwingConstants.VERTICAL);
	toolbar.setFloatable(false); 
	toolbar.addSeparator();
	toolbar.add(map.get("new_file"));
	toolbar.add(map.get("open"));


	toolbar.addSeparator();
	toolbar.add(map.get("copy"));
	toolbar.add(map.get("cut"));
	toolbar.add(map.get("paste"));


	toolbar.addSeparator();
	toolbar.add(map.get("select"));
	toolbar.add(map.get("searchreplace"));


	toolbar.addSeparator();
	toolbar.add(map.get("undo"));
	toolbar.add(map.get("redo"));


	toolbar.addSeparator();
	toolbar.add(map.get("compil"));
	toolbar.add(map.get("execute"));
	toolbar.add(map.get("stopexecution"));
	toolbar.add(map.get("valid"));
	
	
	toolbar.addSeparator();
	toolbar.add(map.get("about"));

		    
	add(toolbar);
    }
}
