package projetIDE.model.ast;

import java.io.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.List;
import java.util.*;
import javax.swing.*;
import java.awt.*;


public class AbstractSyntaxTreeModel implements TreeModel {
    
    /** Attributs */
    protected JavaParser javaParser;
    protected SimpleNode root;
    private static boolean first = true;

    public List listener;
    
    /** Constructeur */
    public AbstractSyntaxTreeModel(Reader reader) {
	listener = new ArrayList();
	try 
	    {
		if(first){
		    javaParser = new JavaParser(reader);
		    first=false;
		    root = javaParser.CompilationUnit();
		} else {
		    javaParser.ReInit(reader);
		    root = javaParser.CompilationUnit();
		}

	}
	catch(ParseException e2) {
	    e2.printStackTrace();
	}
    }

    /** Methode de l'interface TreeModel */
    public void addTreeModelListener(TreeModelListener l) {
	listener.add(l);
    } 

    public Object getChild(Object parent, int index) {
	Node par = (Node)parent;
	int i;
	if(par instanceof ASTCompilationUnit) {
	    if(index != 0) {
		if(par.jjtGetChild(0) instanceof ASTPackageDeclaration) {
		    i=1;
		    while(!(par.jjtGetChild(i) instanceof ASTTypeDeclaration)) { i+=1; }
		    return par.jjtGetChild(i);
		} else {
		    i=0;
		    while(!(par.jjtGetChild(i) instanceof ASTTypeDeclaration)) { i+=1; }
		    return par.jjtGetChild(i);
		}
	    } else {
		if(par.jjtGetChild(0) instanceof ASTPackageDeclaration) {
		    return par.jjtGetChild(0);
		} else {
		    i=0;
		    while(!(par.jjtGetChild(i) instanceof ASTTypeDeclaration)) { i+=1; }
		    return par.jjtGetChild(i);
		}
	    }
	} else if(par instanceof ASTTypeDeclaration) {
	    if(par.jjtGetChild(0) instanceof ASTClassDeclaration) {
		return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetChild(index);
	    } else if(par.jjtGetChild(0) instanceof ASTInterfaceDeclaration) {
		return getUnmodifiedInterfaceDeclaration(par.jjtGetChild(0)).jjtGetChild(index);
	    } else {
		return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetChild(index);
	    } 
	    
	}else if((((SimpleNode)par).nodeType_token).equalsTo("class")) {
	    return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetChild(index);
	}
	return ((Node)parent).jjtGetChild(index);
    }

    private Node getClassBody(Node parent){
	int nbFils = parent.jjtGetNumChildren();
	for(int i = 0; i < nbFils; i+=1) {
	    if(parent.jjtGetChild(i) instanceof ASTClassBody) {
		return parent.jjtGetChild(i);
	    }
	}
	return null;
    }

    private Node getUnmodifiedInterfaceDeclaration(Node parent) {
	int nbFils = parent.jjtGetNumChildren();
	//System.out.println("Nombre de fils: "+nbFils);
	for(int i = 0; i < nbFils; i+=1) {
	    if(parent.jjtGetChild(i) instanceof ASTUnmodifiedInterfaceDeclaration) {
		return parent.jjtGetChild(i);
	    }
	}
	return null;
    } 

    public int getChildCount(Object parent) {
	Node par = (Node)parent;
	if(par instanceof ASTCompilationUnit){
		if(par.jjtGetChild(0) instanceof ASTPackageDeclaration) {
			return 2;
		}
	    	return 1;
	} else if(par instanceof ASTPackageDeclaration ){
	    return 0;
	} else if(par instanceof ASTTypeDeclaration){
	    //return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetNumChildren();
	    if(par.jjtGetChild(0) instanceof ASTClassDeclaration) {
		return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetNumChildren();
	    } else if(par.jjtGetChild(0) instanceof ASTInterfaceDeclaration) {
		return getUnmodifiedInterfaceDeclaration(par.jjtGetChild(0)).jjtGetNumChildren();
	    } else {
		return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetNumChildren();
	    }
	} else if(par instanceof ASTClassBodyDeclaration || par instanceof ASTInterfaceMemberDeclaration) {
	    if((((SimpleNode)par).nodeType_token).equalsTo("class")) {
		return getClassBody(par.jjtGetChild(0).jjtGetChild(0)).jjtGetNumChildren();
            }
	    return 0;
	    }
	return ((Node)parent).jjtGetNumChildren();
    }

    public int getIndexOfChild(Object parent, Object o) {
	Node child = (Node)o; 
	int length = getChildCount(parent);
	for(int i=0;i<length;i++){
	    if(child == (Node)getChild(parent,i)){
		return i;
	    }
	}
	return -1;
    }

    public Object getRoot() {
	return this.root;
    }

    public boolean isLeaf(Object node) {
	Node par = (Node) node;
	if(par instanceof ASTCompilationUnit){
	    return false;
	} else if(par instanceof ASTPackageDeclaration ){
	    return true;
	} else if(par instanceof ASTClassBodyDeclaration || par instanceof ASTInterfaceMemberDeclaration){
	     if((((SimpleNode)par).nodeType_token).equalsTo("class")) {
		return false;
	    }
	    return true;
	}
	return ((Node)node).jjtGetNumChildren() == 0;
    }

    public void removeTreeModelListener(TreeModelListener l) {
	listener.remove(listener.indexOf(l));
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
	
    }
    
}
