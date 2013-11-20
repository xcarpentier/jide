package projetIDE.model;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.*;
import java.nio.*;

/**
   Encapsule des <a href="/home/carpentier/projet_/doc/projetIDE/model/TextModel.html">TextModel</a href> dans une Map.
   L'implementation de l'interface ListModel permet l'utilisation comme mod�le d' une JList. 
   ListModelIde est utilis�e � cette effet par l'objet <a href="/home/carpentier/projet/doc/projetIDE/gui/ListGUI.html"> ListGUI </a href>, 
   qui affiche graphiquement les fichiers source java ouvert.
   @see javax.swing.ListModel
   @see java.util.Map
   @see java.util.ArrayList
   @see javax.swing.JList
   @version 1.0
   @since 11 / 1 / 2006.
*/
public class ListModelIde extends Observable implements ListModel {
    
    private Map<String,StyledDocument> styledDocument;
    private List<String> nameFile;
    private List<ListDataListener> dataListener;    

    /**
     *Construit l'objet avec les structures de donnee : LinkedHashMap et ArrayList.
     */
    public ListModelIde(){
	styledDocument = new LinkedHashMap<String,StyledDocument>();
	nameFile = new ArrayList<String>();
	dataListener = new ArrayList<ListDataListener>();
    }
    
    
    //*** 
    //***Implementation des m�thodes de ListModel : 
    //***
    
    /**
     *Ajoute des �couteurs a la liste qui seront informer � chaque fois qu'un changement apparait dans la structure de donn�e.
     *@param l ListDataListener qui est rajouter � la liste.
     */
    public void addListDataListener(ListDataListener l) {
	dataListener.add(l);
       
    }
    
    
    /**
     *Retire l de la liste des �couteur. 
     *@param l ListDataListener qui est retranch� � la liste.
     */
    public void removeListDataListener(ListDataListener l) {
	dataListener.remove(l);
    }
    
    /**
     *Retourne le nom du fichier qui se trouve � index 
     *@param index indice de l'objet � retourner.
     *@return nom du fichier qui se trouve � index.
     *
     */
    public Object getElementAt(int index){ 
	return nameFile.get(index);
    }
	
    /**
     *Retourne le nombre de <a href="/home/carpentier/projet/doc/projetIDE/model/TextModel.html">TextModel</a href> pr�sent dans la liste.
     *@return nombre de <a href="/home/carpentier/projet/doc/projetIDE/model/TextModel.html">TextModel</a href>.
     */
    public int getSize() {
	return styledDocument.size();
    }
  
    
    /**
     *Retourne le document dont le nom de fichier est key.
     *@param key nom du fichier.
     *@return document dont le nom de fichier est key.
     */
    public StyledDocument getDocument(String key){
	return styledDocument.get(key);
    }
    
    /**
     *Stocke le nouveau document et pr�vient les observers qu'il y a eu un changement dans la liste.
     *@param key nom du nouveau fichier source avec quoi le document est cr��
     *@param document document qui est stock� � l'indice key
     */
    public void setDocument(String key, StyledDocument document){
	styledDocument.put(key,document);
	nameFile.add(key);
	setChanged();
	notifyObservers(key);
    }
    
    /**
     *Elimine de la liste le document dont le nom de fichier et key et pr�vient les observers qu'il y a eu un changement dans la liste.
     *@param key nom du fichier du document.
     */
    public void removeDocument(String key){
	styledDocument.remove(key);
	nameFile.remove(key);
	setChanged();
	notifyObservers("remove"+key);
    }

    public List<String> getList(){ 
	return nameFile;
    }

}

