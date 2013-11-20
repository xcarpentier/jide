package projetIDE.gui;

import projetIDE.model.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.BorderLayout;

import static java.awt.BorderLayout.*;

/**
   Regroupe les élements graphique du projet pour créer l'interface homme-machine.
   @since 17 / 1 / 2006
   @author Saint-Maxin Steeve & Carpentier Xavier
*/
public class PanelGroup extends JPanel {
    /**
       Prend en paramètre les différents éléments graphiques à afficher sur l'interface graphique.
       @param menu élément graphique qui affiche les boutons d'interaction, regroupe des boutons, instance de <code>MenuBarIde</code>.
       @param list élément graphique qui liste les fichiers ouvert instance de <code>ListGUI</code>.
       @param tree élément graphique qui permet de visualiser les signatures de méthode de la classe courante sous la forme d'un arbre.
       @param console élément graphique qui permet de visualiser les messages d'erreurs du programme source en cours d'implementation.
    */
    public PanelGroup(JPanel menu, JPanel list, JPanel tree, JTabbedPane console, JTabbedPane tabbedText, ActionMap map){
	super(new BorderLayout());
	
	setActionMap(map);
	
	JTabbedPane list_tree = new JTabbedPane();
	list_tree.addTab("tree",tree);
	list_tree.addTab("list",list);
				
	JSplitPane list_tree_source = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, list_tree, tabbedText);
	JSplitPane list_tree_source_console = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, list_tree_source, console);
	list_tree_source.setResizeWeight(0.0005);
	list_tree_source_console.setResizeWeight(0.7);
	list_tree_source.setOneTouchExpandable(false);
	list_tree_source_console.setOneTouchExpandable(false);
        
	add(menu,WEST);
	add(list_tree_source_console,CENTER);	
    }
}
