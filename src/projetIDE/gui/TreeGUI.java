package projetIDE.gui;

import projetIDE.gui.controler.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TreeGUI extends JPanel {
    
    public JTree tree;
    
    public TreeGUI(){
	super(new BorderLayout());
	add(new JScrollPane(this.tree), BorderLayout.CENTER);
    }
    
    public void initTree(TreeModel model){
	removeAll();
	tree = new JTree(model);
	tree.setRootVisible(false); 
	tree.expandPath(tree.getAnchorSelectionPath());
	tree.setScrollsOnExpand(true);
        ToolTipManager.sharedInstance().registerComponent(tree);
	setImageIcones(tree);
	add(new JScrollPane(tree), BorderLayout.CENTER);
	tree.revalidate();
	revalidate();
    }
    
    public void removeTree(){
	removeAll();
	tree = new JTree();
	tree.revalidate();
	revalidate();
    }

    public static void setImageIcones(JTree t) {
        ImageIcon packageIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/package3.gif"));

	//icones class
	ImageIcon privateClassIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/classPrivate.gif"));
	ImageIcon protectedClassIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/classProtected.gif"));
	ImageIcon publicClassIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/class.gif"));
	ImageIcon videClassIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/class.gif"));

	//icones methode
	ImageIcon privateMethodIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/methodePrivate.gif"));
	ImageIcon protectedMethodIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/methodeProtected.gif"));
	ImageIcon publicMethodIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/methode.gif"));
	ImageIcon videMethodIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/methode.gif"));

	//icone racine
	ImageIcon racineIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/racine.gif"));
	
	//icones attribut
	ImageIcon privateAttributIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/attributPrivate.gif"));
	ImageIcon protectedAttributIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/attributProtected.gif"));
	ImageIcon publicAttributIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/attribut.gif"));
	ImageIcon videAttributIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/attribut.gif"));
	
	//	icones interface
	ImageIcon privateInterfaceIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/interfacePrivate.gif"));
	ImageIcon protectedInterfaceIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/interfaceProtected.gif"));
	ImageIcon publicInterfaceIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/interface.gif"));
	ImageIcon videInterfaceIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/interface.gif"));

	//icones constructeur
	ImageIcon privateConstructeurIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/constructorPrivate.gif"));
        ImageIcon protectedConstructeurIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/constructorProtected.gif"));
        ImageIcon publicConstructeurIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/constructor.gif"));
	ImageIcon videConstructeurIcon = new ImageIcon(TreeGUI.class.getResource("/resources/icons/constructor.gif"));

	
	if (packageIcon != null && privateClassIcon != null && protectedClassIcon != null && publicClassIcon != null && videClassIcon != null && privateMethodIcon != null &&
	    protectedMethodIcon != null && publicMethodIcon != null && videMethodIcon != null && racineIcon != null && privateAttributIcon != null && protectedAttributIcon != null &&
	    publicAttributIcon != null  && videAttributIcon != null && privateInterfaceIcon != null && protectedInterfaceIcon != null && publicInterfaceIcon != null && videInterfaceIcon != null && 
	    privateConstructeurIcon != null && protectedConstructeurIcon != null && publicConstructeurIcon != null && videConstructeurIcon != null) {

	    t.setCellRenderer(new TreeCellRendererIde(packageIcon, privateClassIcon, protectedClassIcon, publicClassIcon, videClassIcon, privateMethodIcon, 
						      protectedMethodIcon, publicMethodIcon, videMethodIcon, racineIcon, privateAttributIcon, protectedAttributIcon, 
						      publicAttributIcon, videAttributIcon, privateInterfaceIcon, protectedInterfaceIcon, publicInterfaceIcon, 
						      videInterfaceIcon, privateConstructeurIcon, protectedConstructeurIcon, publicConstructeurIcon, videConstructeurIcon));
	} else {
	    System.out.println("image non trouvee");
	}
    }
}
