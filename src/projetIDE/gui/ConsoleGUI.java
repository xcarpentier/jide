package projetIDE.gui;

import projetIDE.model.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class ConsoleGUI extends JTabbedPane {
    
    public ConsoleGUI(CompileConsoleModel compilation, ExecuteConsoleModel execution){
	add("compilation",new CompileGUI(compilation));
	add("execution",new ExecuteGUI(execution));
    }
}
