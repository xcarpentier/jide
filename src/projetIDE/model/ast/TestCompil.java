package projetIDE.model.ast;

import java.io.*;

public class TestCompil {
    
    private static boolean first = true;
    private Analyseur parser;

    public boolean test(String text) {
	try 
	    {
		if(first){
		    parser = new Analyseur(new StringReader(text));
		    first=false;
		    parser.CompilationUnit();
		    return true;
		} else {
		    parser.ReInit(new StringReader(text));
		    parser.CompilationUnit();
		    return true;
		}
	    }
	catch(ParseException e)
	    {
		return false;
	    }
    }
}
