package projetIDE.gui.controler;

import projetIDE.model.ast.*;
import java.awt.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.*;


public class TreeCellRendererIde extends DefaultTreeCellRenderer {
	Icon packageIcon;
	Icon privateClassIcon;
	Icon protectedClassIcon;
	Icon publicClassIcon;
	Icon videClassIcon;
	Icon privateMethodIcon;
	Icon protectedMethodIcon;
	Icon publicMethodIcon;
	Icon videMethodIcon;
	Icon racineIcon;
	Icon privateAttributIcon;
	Icon protectedAttributIcon;
	Icon publicAttributIcon;
	Icon videAttributIcon;
	Icon privateInterfaceIcon;
	Icon protectedInterfaceIcon;
	Icon publicInterfaceIcon;
	Icon videInterfaceIcon;
	Icon privateConstructeurIcon;
        Icon protectedConstructeurIcon;
        Icon publicConstructeurIcon;
	Icon videConstructeurIcon;
    
    public TreeCellRendererIde(Icon packageIcon, Icon privateClassIcon, Icon protectedClassIcon, Icon publicClassIcon, Icon videClassIcon, Icon privateMethodIcon, Icon protectedMethodIcon, Icon publicMethodIcon, Icon videMethodIcon, Icon racineIcon, Icon privateAttributIcon, Icon protectedAttributIcon, Icon publicAttributIcon, Icon videAttributIcon, Icon privateInterfaceIcon, Icon protectedInterfaceIcon, Icon publicInterfaceIcon, Icon videInterfaceIcon, Icon privateConstructeurIcon, Icon protectedConstructeurIcon, Icon publicConstructeurIcon, Icon videConstructeurIcon) {
	    super();
	    this.packageIcon = packageIcon;
	    this.privateClassIcon = privateClassIcon;
	    this.protectedClassIcon = protectedClassIcon;
	    this.publicClassIcon = publicClassIcon;
	    this.videClassIcon = videClassIcon;
	    this.privateMethodIcon = privateMethodIcon;
	    this.protectedMethodIcon = protectedMethodIcon;
	    this.publicMethodIcon = publicMethodIcon;
	    this.videMethodIcon = videMethodIcon;
	    this.racineIcon = racineIcon;
	    this.privateAttributIcon = privateAttributIcon;
	    this.protectedAttributIcon = protectedAttributIcon;
	    this.publicAttributIcon = publicAttributIcon;
	    this.videAttributIcon = videAttributIcon;
	    this.privateInterfaceIcon = privateInterfaceIcon;
	    this.protectedInterfaceIcon = protectedInterfaceIcon;
	    this.publicInterfaceIcon = publicInterfaceIcon;
	    this.videInterfaceIcon = videInterfaceIcon;
	    this.privateConstructeurIcon = privateConstructeurIcon;
            this.protectedConstructeurIcon = protectedConstructeurIcon;
            this.publicConstructeurIcon = publicConstructeurIcon;
	    this.videConstructeurIcon = videConstructeurIcon;
	}
    
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
	    super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	    if(isThisNode(value, "package")) {
		setIcon(packageIcon);
		setToolTipText("Type : Paquetage Nom : "+((SimpleNode)value).name_token);
	    } else if(isThisNode(value, "class", "private")) {
		setIcon(privateClassIcon);
		setToolTipText("Type : Class Nom : "+((SimpleNode)value).name_token+" Visibiltee : Private");
	    } else if(isThisNode(value, "class", "protected")) {
		setIcon(protectedClassIcon);
		setToolTipText("Type : Class Nom : "+((SimpleNode)value).name_token+" Visibilitee : Protected");
	    } else if(isThisNode(value, "class", "public")) {
		setIcon(publicClassIcon);
		setToolTipText("Type : Class\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Public");
	    } else if(isThisNode(value, "class", "vide")) {
		setIcon(videClassIcon);
		setToolTipText("Type : Class\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Aucune");
	    } else if(isThisNode(value, "methode", "private")) {
		setIcon(privateMethodIcon);
		setToolTipText("Type : Methode\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Private");
	    } else if(isThisNode(value, "methode", "protected")) {
		setIcon(protectedMethodIcon);
		setToolTipText("Type : Methode\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Protected");
	    } else if(isThisNode(value, "methode", "public")) {
		setIcon(publicMethodIcon);
		setToolTipText("Type : Methode\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Public");
	    } else if(isThisNode(value, "methode", "vide")) {
		setIcon(videMethodIcon);
		setToolTipText("Type : Methode\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Aucune");
	    } else if(isThisNode(value, "Racine")) {
		setIcon(racineIcon);
		setToolTipText("Type : Racine");
	    } else if(isThisNode(value, "attribut", "private")) {
		setIcon(privateAttributIcon);
		setToolTipText("Type : Attribut\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Private");
	    } else if(isThisNode(value, "attribut", "protected")) {
		setIcon(protectedAttributIcon);
		setToolTipText("Type : Attribut\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Protected");
	    } else if(isThisNode(value, "attribut", "public")) {
		setIcon(publicAttributIcon);
		setToolTipText("Type : Attribut\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Public");
	    } else if(isThisNode(value, "attribut", "vide")) {
		setIcon(videAttributIcon);
		setToolTipText("Type : Attribut\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Aucune");
	    } else if(isThisNode(value, "interface", "private")) {
		setIcon(privateInterfaceIcon);
		setToolTipText("Type : Interface\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Private");
	    } else if(isThisNode(value, "interface", "protected")) {
		setIcon(protectedInterfaceIcon);
		setToolTipText("Type : Interface\nNom : "+((SimpleNode)value).name_token+" Visibilitee: Protected");
	    } else if(isThisNode(value, "interface", "public")) {
		setIcon(publicInterfaceIcon);
		setToolTipText("Type : Interface\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Public");
	    } else if(isThisNode(value, "interface", "vide")) {
		setIcon(videInterfaceIcon);
		setToolTipText("Type : Interface\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Aucune");
	    } else if(isThisNode(value, "constructeur", "private")) {
                setIcon(privateConstructeurIcon);
		setToolTipText("Type : Constructeur\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Private");
            } else if(isThisNode(value, "constructeur", "protected")) {
                setIcon(protectedConstructeurIcon);
		setToolTipText("Type : Constructeur\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Protected");
            } else if(isThisNode(value, "constructeur", "public")) {
                setIcon(publicConstructeurIcon);
		setToolTipText("Type : Constructeur\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Public");
	    } else if(isThisNode(value, "constructeur", "vide")) {
                setIcon(videConstructeurIcon);
		setToolTipText("Type : Constructeur\nNom : "+((SimpleNode)value).name_token+" Visibilitee : Aucune");
	    } else {

	    }
	    
	    return this;
	}

        //faire tous les cas avec visibilitee


        private boolean isThisNode(Object value, String type, String visibility) {
            SimpleNode node = (SimpleNode) value;
            String typeNode = (node.nodeType_token).toString();
            String visibilityNode = (node.visibility_token).toString();
            if (typeNode.indexOf(type) >= 0 && visibilityNode.indexOf(visibility) >= 0) {
                return true;
            }

            return false;
        }

        private boolean isThisNode(Object value, String type) {
            SimpleNode node = (SimpleNode) value;
            String typeNode = (node.nodeType_token).toString();
            if (typeNode.indexOf(type) >= 0) {
                return true;
            }
	    return false;
	}
}
