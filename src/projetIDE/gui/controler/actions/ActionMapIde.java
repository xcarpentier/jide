package projetIDE.gui.controler.actions;

import projetIDE.model.*;

import javax.swing.*;
import java.awt.*;

public class ActionMapIde extends ActionMap {

    private ListModel model;
    private JTabbedPane tabbedpane;
    private CompileConsoleModel console;
    private ExecuteConsoleModel execute;
    private JTabbedPane viewconsole;

    public ActionMapIde(ListModel model, JTabbedPane tabbedpane, CompileConsoleModel console, ExecuteConsoleModel execute, JTabbedPane viewconsole) {
	super();
	this.model=model;
	this.tabbedpane=tabbedpane;
	this.console=console;
	this.execute=execute;
	this.viewconsole=viewconsole;
    }
    
    public void init(){
	put("new_file", new NewAction("new",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/new.gif")),model));
 	put("about",new AboutAction("About", new ImageIcon(ActionMapIde.class.getResource("/resources/icons/about.gif"))));
 	put("copy",new CopyAction("copy",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/copy.gif")),tabbedpane));
 	put("cut",new CutAction("cut",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/cut.gif")),tabbedpane));
 	put("paste",new PasteAction("paste",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/paste.gif")),tabbedpane));
 	put("searchreplace",new SearchReplaceAction("search",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/searchreplace.gif")),tabbedpane));
 	put("print", new PrintAction("print",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/print.gif")),model,tabbedpane));
 	put("redo", new RedoAction("redo",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/redo.gif")),model,tabbedpane));
 	put("undo", new UndoAction("undo",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/undo.gif")),model,tabbedpane));
	put("save", new SaveAction("save",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/save.gif")),model,tabbedpane));
	put("saveall",new SaveAllAction("saveall",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/saveall.gif")),model,tabbedpane));
	put("saveas", new SaveAsAction("saveas",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/saveas.gif")),model,tabbedpane));
	put("close", new CloseAction("close",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/close.gif")),model,tabbedpane));
	put("closeall", new CloseAllAction("closeall",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/closeall.gif")),model,tabbedpane));
	put("compil", new CompileAction("compile",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/compil.gif")),model,tabbedpane,console, viewconsole)); 
	put("valid", new ValidAction("valider",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/valid.gif")),model,tabbedpane,console, viewconsole)); 
	put("execute", new ExecuteAction("execute",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/execute.gif")),model,tabbedpane,execute, viewconsole));
	put("select", new SelectAllAction("select all",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/select.gif")),tabbedpane));
	put("quit", new QuitAction("quit",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/quit.gif")),model,tabbedpane));
	put("open", new OpenAction("open",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/open.gif")),model));
	put("stopexecution", new StopAction("Stop",new ImageIcon(ActionMapIde.class.getResource("/resources/icons/stopexec.gif")), execute));

    }

}
