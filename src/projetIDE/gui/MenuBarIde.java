package projetIDE.gui;

import projetIDE.gui.controler.*;
import projetIDE.model.*;
import projetIDE.gui.controler.actions.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;  

public class MenuBarIde extends JMenuBar {
    
    /* pour mémoire */     
    String[] HELP = {"new","open","save","save as","save all","print","close","close all","quit","undo","redo",
		     "cut","copy","past","search","replace","select all","delete","compile","run","police","help","about"};


    private static final String[] MENU_BAR = {"file","edit","format","tools","help"};
    private JMenu[] menu;
    private JMenuItem[] item;
    
    public MenuBarIde(ActionMap map){
	Object[] keys = map.keys();
	JMenu file = new JMenu("File");
	file.add(new JMenuItem(map.get("new_file")));
	file.add(new JMenuItem(map.get("open")));
	file.add(new JSeparator());
	file.add(new JMenuItem(map.get("print")));
	file.add(new JSeparator());
	file.add(new JMenuItem(map.get("save")));
	file.add(new JMenuItem(map.get("saveas")));
	file.add(new JMenuItem(map.get("saveall")));
	file.add(new JSeparator());
	file.add(new JMenuItem(map.get("close")));
	file.add(new JMenuItem(map.get("closeall")));
	file.add(new JSeparator());
	file.add(new JMenuItem(map.get("quit")));
	add(file);

	JMenu edit =  new JMenu("Edit");
	edit.add(new JMenuItem(map.get("copy")));
	edit.add(new JMenuItem(map.get("cut")));
	edit.add(new JMenuItem(map.get("paste")));
	edit.add(new JSeparator());
	edit.add(new JMenuItem(map.get("select")));
	edit.add(new JMenuItem(map.get("searchreplace")));
	edit.add(new JSeparator());
	edit.add(new JMenuItem(map.get("undo")));
	edit.add(new JMenuItem(map.get("redo")));
	add(edit);

	JMenu tools = new JMenu("Tools");
	tools.add(new JMenuItem(map.get("compil")));
	tools.add(new JMenuItem(map.get("execute")));
	tools.add(new JMenuItem(map.get("stopexecution")));
	tools.add(new JMenuItem(map.get("valid")));
	add(tools);

	JMenu about = new JMenu("About");
	about.add(new JMenuItem(map.get("about")));
	add(about);
    }
    
    
    
    // Le menu deroulant Fichier
    
    
    // 	item = new JMenuItem("Nouveau");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
    
    
    // 	item = new JMenuItem("Ouvrir");
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	file.addSeparator();
    
    // 	item = new JMenuItem("Enregistrer");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
    
    // 	item = new JMenuItem("Enregistrer-sous...");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    
    
    // 	item = new JMenuItem("Tout enregistrer");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	file.addSeparator();
    
    // 	item = new JMenuItem("Imprimer");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	file.addSeparator();
    
    // 	item = new JMenuItem("Fermer");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
    
    // 	item = new JMenuItem("Fermer tout");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	file.addSeparator();
    
    // 	item = new JMenuItem("Quitter");
    // 	file.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
    
    
    
    // 	// Le menu deroulant Edition
    // 	item = new JMenuItem("Annuler",'A');
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
    // 	item = new JMenuItem("Refaire");
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	edit.addSeparator();
    
    // 	item = new JMenuItem("Couper");
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
    // 	item = new JMenuItem("Copier");
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
    // 	item = new JMenuItem("Coller");
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
    // 	edit.addSeparator();
    
    // 	item = new JMenuItem("Rechercher");
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item = new JMenuItem("Remplacer",'R');
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	edit.addSeparator();
    
    // 	item = new JMenuItem("Tout selectionner",'T');
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	edit.addSeparator();
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
    
    // 	item = new JMenuItem("Supprimer",'S');
    // 	edit.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
    
    // 	// Le menu deroulant Format
    // 	item = new JMenuItem("Police");
    // 	format.add(item);
    // 	item.addActionListener(menuListener);
    
    // 	// Le menu deroulant Aide 
    // 	item = new JMenuItem("Aide");
    // 	help.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));
    // 	help.addSeparator();
    
    // 	item = new JMenuItem("A propos de...");
    // 	help.add(item);
    // 	item.addActionListener(menuListener);
    // 	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
    
    
    
}
