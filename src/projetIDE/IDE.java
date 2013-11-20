package projetIDE; 

import projetIDE.gui.*;
import projetIDE.model.*;
import projetIDE.model.ast.*;
import projetIDE.gui.controler.*;
import projetIDE.gui.controler.actions.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
   Class principale du projetIDE. 
   Auteur: Saint-Maxin Steeve et Carpentier Xavier.
   
   @author Saint-Maxin Steeve et Carpentier Xavier
   @version 1.0
   @since 24 / 1 / 2006
*/
public class IDE {
    /**
     *       Methode principale.
     * @param args inutile ici.
     */
    public static void main(String[] args) {
	
	/*  Creation des models (il ne devrait y avoir qu'un seul modéle ?) */
	
	
	ListModel       list_m     = new ListModelIde(); // Contient les documents (model de ListGUI)
	CompileConsoleModel    console_m  = new CompileConsoleModel(); // Encapsule les message à la compilation (model de ConsoleGUI)
	ExecuteConsoleModel  execute_m = new ExecuteConsoleModel(); // message à l'execution
	
	
	/* Creation des vues */
	JPanel    tree      = new TreeGUI();
	JTabbedPane    console_v = new ConsoleGUI(console_m,execute_m); // Affiche les message d'erreur à la compilation 
	JTabbedPane    tabtext   = new TabbedPaneTextGUI(list_m); 
	JPanel    list_v    = new ListGUI(list_m,tabtext); // Affiche une liste des fichiers ouverts
	/* Creation des controleurs */
	
	
	/* Creation des action avec l'utilisateur*/
	ActionMapIde map = new ActionMapIde(list_m,tabtext,console_m,execute_m,console_v);
	map.init();
	
	
	//DocumentListener text_c    = new TextListenerIde(); 
	WindowListener   fenetre_c = new WindowListenerIde(map);
	TabbedListener   tabbed_c  = new TabbedListener(tabtext,tree,list_m);
	tabtext.addChangeListener(tabbed_c);
	
	/* Creation de la fenetre englobante */
	
	PanelGroup group = new PanelGroup(new ToolBarIde(map),list_v,tree,console_v,tabtext,map);
	JFrame fenetre = new WindowIde();
	fenetre.addWindowListener(fenetre_c);
	fenetre.setJMenuBar(new MenuBarIde(map));
	fenetre.add(group);
	fenetre.pack();
	fenetre.setVisible(true);
    }
}
