package projetIDE.gui;

import projetIDE.gui.controler.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class SearchGui extends JFrame {

    private final double DEFAULT_WIDTH;
    private final double DEFAULT_HEIGHT;
    private final double DEFAULT_HEIGHT_SCREEN;
    private final double DEFAULT_WIDTH_SCREEN;
    
    public static JComboBox search;
    public static JComboBox replace;
    public static JCheckBox mot_entier;
    public static JCheckBox casse;
    public static JCheckBox haut;
    public static JCheckBox all_doc;

    public SearchGui() {
	super("Search & Replace");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
        DEFAULT_WIDTH = screenSize.getWidth()/2.2;
        DEFAULT_HEIGHT = screenSize.getHeight()/4.7;
        DEFAULT_WIDTH_SCREEN = screenSize.getWidth();
        DEFAULT_HEIGHT_SCREEN = screenSize.getHeight();
	
	setPreferredSize(new Dimension((int)DEFAULT_WIDTH,(int)DEFAULT_HEIGHT));
	int x =(int)((DEFAULT_WIDTH_SCREEN - DEFAULT_WIDTH)/1.2);
        int y =(int)((DEFAULT_HEIGHT_SCREEN - DEFAULT_HEIGHT)/2.5);
        setLocation(x,y);
	setResizable(false);
	
	GridBagLayout layout = new GridBagLayout();
	
	setLayout(layout);
	
	JPanel options = new JPanel(new GridLayout(4,1));

	JLabel lbl_search = new JLabel("search:");
	JLabel lbl_replace = new JLabel("replace:");
	
	search = new JComboBox();
	replace = new JComboBox();
	search.setEditable(true);
	replace.setEditable(true);
	
	JButton searchSimple = new JButton("search");
	JButton searchAll = new JButton("search all");
	JButton replaceSimple = new JButton("replace");
	JButton replaceAll = new JButton("replace all");
	
	ActionListener listener = new SearchButtonListener(this);
	searchSimple.addActionListener(listener);
	searchAll.addActionListener(listener);
	replaceSimple.addActionListener(listener);
	replaceAll.addActionListener(listener);
	
	mot_entier = new JCheckBox("uniquement les mots en entiers");
	casse = new JCheckBox("respecter la casse");
	haut = new JCheckBox("vers le haut");
	all_doc = new JCheckBox("tous le document");
	
	options.add(mot_entier);
	options.add(casse);
	options.add(haut);
	options.add(all_doc);
	options.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Options"));
	
	add(lbl_search,new GBC(0,0).setAnchor(GBC.WEST).setInsets(4).setWeight(250,250).setInsets(6,4,4,4));
	add(search,new GBC(1,0).setFill(GBC.HORIZONTAL).setWeight(250,300).setInsets(6,4,4,4));
	add(searchSimple,new GBC(2,0).setFill(GBC.HORIZONTAL).setWeight(250,250).setInsets(6,4,4,4));
	add(searchAll,new GBC(3,0).setFill(GBC.HORIZONTAL).setWeight(250,250).setInsets(6,4,4,4));
	add(lbl_replace,new GBC(0,1).setAnchor(GBC.WEST).setInsets(4).setWeight(250,250).setInsets(4,4,4,4));
	add(replace,new GBC(1,1).setFill(GBC.HORIZONTAL).setWeight(250,250).setInsets(4,4,4,4));
	add(replaceSimple,new GBC(2,1).setFill(GBC.HORIZONTAL).setWeight(250,250).setInsets(4,4,4,4));
	add(replaceAll,new GBC(3,1).setFill(GBC.HORIZONTAL).setWeight(250,250).setInsets(4,4,4,4));
	add(options,new GBC(0,2,4,1).setFill(GBC.BOTH).setWeight(250,250).setInsets(3,3,3,3));
	setAlwaysOnTop(true);
	
        pack();
        setVisible(true);
    }
    
    

    
    public static void main(String args[]){
	new SearchGui();
    }
}
