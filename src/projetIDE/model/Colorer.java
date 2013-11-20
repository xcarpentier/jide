/**
 * Run the Syntax Highlighting as a separate thread.
 * Things that need to be colored are messaged to the
 * thread and put in a list.
 */
package projetIDE.model;

import projetIDE.model.*;

import javax.swing.text.*;

import java.io.*;
import java.util.*;

import com.Ostermiller.Syntax.Lexer.*;


public class Colorer extends Thread {
    
    
    /**
     * Keep a list of places in the file that it is safe to restart the
     * highlighting.  This happens whenever the lexer reports that it has
     * returned to its initial state.  Since this list needs to be sorted
     * and we need to be able to retrieve ranges from it, it is stored in a
     * balanced tree.
     */
    private TreeSet iniPositions;
    
    /**
     * As we go through and remove invalid positions we will also be finding
     * new valid positions. 
     * Since the position list cannot be deleted from and written to at the same
     * time, we will keep a list of the new positions and simply add it to the
     * list of positions once all the old positions have been removed.
     */
    private HashSet newPositions;
    
    
    /**
     * Vector that stores the communication between the two threads.
     */
    private volatile Vector v;
    
    
    /**
     * The amount of change that has occurred before the place in the
     * document that we are currently highlighting (lastPosition).
     */
    private volatile int change;
    
    /**
     * The last position colored
     */
    private volatile int lastPosition;
    
    private volatile boolean asleep;
    
    /**
     * When accessing the vector, we need to create a critical section.
     * we will synchronize on this object to ensure that we don't get
     * unsafe thread behavior.
     */
    private Object lock;
    
    private Object doclock;
    private TextModel document;
    private Lexer syntaxLexer;
    private DocumentReader documentReader;
    
    public Colorer(Object doclock, TextModel document, DocumentReader documentReader){
	this.doclock=doclock;
	this.document=document;
	syntaxLexer = new JavaLexer(this.documentReader);
	this.documentReader=documentReader;
	iniPositions = new TreeSet(new DocPositionComparator());
	newPositions = new HashSet();
	v = new Vector();
	change = 0;
	lastPosition = -1;
	asleep = false;
	lock = new Object();
    }
    
    
    /**
     * A simple wrapper representing something that needs to be colored.
     * Placed into an object so that it can be stored in a Vector.
     */
    private class RecolorEvent {
	
	public int position;
	public int adjustment;
	
	public RecolorEvent(int position, int adjustment)
	{
	    this.position = position;
	    this.adjustment = adjustment;
	}
    }
    
    /**
     * Tell the Syntax Highlighting thread to take another look at this
     * section of the document.  It will process this as a FIFO.
     * This method should be done inside a doclock.
     */
    public void color(int position, int adjustment){
	// figure out if this adjustment effects the current run.
	// if it does, then adjust the place in the document
	// that gets highlighted.
	if (position < lastPosition)
	    {
		if (lastPosition < position - adjustment)
		    {
			change -= lastPosition - position;
		    } 
		else 
		    {
			change += adjustment;
		    }
	    }
	synchronized(lock)
	    {
		v.add(new RecolorEvent(position, adjustment));
		if (asleep)
		    {
			this.interrupt();
		    }
	    }
    }
    
    /**
     * The colorer runs forever and may sleep for long
     * periods of time.  It should be interrupted every
     * time there is something for it to do.
     */
    public void run(){
	int position = -1;
	int adjustment = 0;
	// if we just finish, we can't go to sleep until we
	// ensure there is nothing else for us to do.
	// use try again to keep track of this.
	boolean tryAgain = false;
	for (;;){  // forever
	    synchronized(lock)
		{
		    if (v.size() > 0)
			{
			    RecolorEvent re = (RecolorEvent)(v.elementAt(0));
			    v.removeElementAt(0);
			    position = re.position;
			    adjustment = re.adjustment;
			} 
		    else 
			{
			    tryAgain = false;
			    position = -1;
			    adjustment = 0;
			}
		}
	    if (position != -1)
		{
		    SortedSet workingSet;
		    Iterator workingIt;
		    DocPosition startRequest = new DocPosition(position);
		    DocPosition endRequest = new DocPosition(position + ((adjustment>=0)?adjustment:-adjustment));
		    DocPosition  dp;
		    DocPosition dpStart = null;
		    DocPosition dpEnd = null;
		    
		    // find the starting position.  We must start at least one
		    // token before the current position
		    try 
			{
			    // all the good positions before
			    workingSet = iniPositions.headSet(startRequest);
			    // the last of the stuff before
			    dpStart = ((DocPosition)workingSet.last());
			} 
		    catch (NoSuchElementException x)
			{
			    // if there were no good positions before the requested start,
			    // we can always start at the very beginning.
			    //x.printStackTrace();
			    dpStart = new DocPosition(0);
			    
			}
		    
		    // if stuff was removed, take any removed positions off the list.
		    if (adjustment < 0)
			{
			    workingSet = iniPositions.subSet(startRequest, endRequest);
			    workingIt = workingSet.iterator();
			    dpStart = new DocPosition(0);
			    while (workingIt.hasNext())
				{
				    workingIt.next();
				    workingIt.remove();
				}
			}
		    
		    
		    // adjust the positions of everything after the insertion/removal.
		    workingSet = iniPositions.tailSet(startRequest);
		    workingIt = workingSet.iterator();
		    while (workingIt.hasNext())
			{
			    ((DocPosition)workingIt.next()).adjustPosition(adjustment);
			}
		    
		    // now go through and highlight as much as needed
		    workingSet = iniPositions.tailSet(dpStart);
		    workingIt = workingSet.iterator();
		    dp = null;
		    if (workingIt.hasNext())
			{
			    dp = (DocPosition)workingIt.next();
			}
		    try 
			{
			    Token token;
			    boolean done = false;
			    dpEnd = dpStart;
			    synchronized (doclock)
				{
				    // we are playing some games with the lexer for efficiency.
				    // we could just create a new lexer each time here, but instead,
				    // we will just reset it so that it thinks it is starting at the
				    // beginning of the document but reporting a funny start position.
				    // Reseting the lexer causes the close() method on the reader
				    // to be called but because the close() method has no effect on the
				    // DocumentReader, we can do this.
				    syntaxLexer.reset(documentReader, 0, dpStart.getPosition(), 0);
				    // After the lexer has been set up, scroll the reader so that it
				    // is in the correct spot as well.
				    documentReader.seek(dpStart.getPosition());
				    // we will highlight tokens until we reach a good stopping place.
				    // the first obvious stopping place is the end of the document.
				    // the lexer will return null at the end of the document and wee
				    // need to stop there.
				    token = syntaxLexer.getNextToken();
				}
			    newPositions.add(dpStart);
			    while (!done && token != null)
				{
				    // this is the actual command that colors the stuff.
				    // Color stuff with the description of the style matched
				    // to the hash table that has been set up ahead of time.
				    synchronized (doclock)
					{
					    if (token.getCharEnd() <= document.getLength())
						{
						    document.setCharacterAttributes(
										    token.getCharBegin() + change,
										    token.getCharEnd()-token.getCharBegin(),
										    document.getHashStyle(token.getDescription()),
										    true
										    );                                                                  
						    // record the position of the last bit of text that we colored
						    dpEnd = new DocPosition(token.getCharEnd());
						}
					    lastPosition = (token.getCharEnd() + change);
					}
				    // The other more complicated reason for doing no more highlighting
				    // is that all the colors are the same from here on out anyway.
				    // We can detect this by seeing if the place that the lexer returned
				    // to the initial state last time we highlighted is the same as the
				    // place that returned to the initial state this time.
				    // As long as that place is after the last changed text, everything
				    // from there on is fine already.
				    if (token.getState() == Token.INITIAL_STATE)
					{
					    //System.out.println(t);
					    // look at all the positions from last time that are less than or
					    // equal to the current position
					    while (dp != null && dp.getPosition() <= token.getCharEnd())
						{
						    if (dp.getPosition() == token.getCharEnd() && dp.getPosition() >= endRequest.getPosition())
							{
							    // we have found a state that is the same
							    done = true;
							    dp = null;
							} 
						    else if (workingIt.hasNext())
							{
							    // didn't find it, try again.
							    dp = (DocPosition)workingIt.next();
							} 
						    else 
							{
							    // didn't find it, and there is no more info from last
							    // time.  This means that we will just continue
							    // until the end of the document.
							    dp = null;
							}
						}
					    // so that we can do this check next time, record all the
					    // initial states from this time.
					    newPositions.add(dpEnd);
					}
				    synchronized (doclock)
					{
					    token = syntaxLexer.getNextToken();
					}
				}
			    
			    // remove all the old initial positions from the place where
			    // we started doing the highlighting right up through the last
			    // bit of text we touched.
			    workingIt = iniPositions.subSet(dpStart, dpEnd).iterator();
			    while (workingIt.hasNext())
				{
				    workingIt.next();
				    workingIt.remove();
				}
			    
			    // Remove all the positions that are after the end of the file.:
			    workingIt = iniPositions.tailSet(new DocPosition(document.getLength())).iterator();
			    while (workingIt.hasNext())
				{
				    workingIt.next();
				    workingIt.remove();
				}
			    
			    // and put the new initial positions that we have found on the list.
			    iniPositions.addAll(newPositions);
			    newPositions.clear();
			    
			    
			    
			    
			    /*workingIt = iniPositions.iterator();
			      while (workingIt.hasNext()){
			      System.out.println(workingIt.next());
			      }
			      
			      System.out.println("Started: " + dpStart.getPosition() + " Ended: " + dpEnd.getPosition());*/
			}
		    catch (IOException x)
			{
			    //x.printStackTrace();
			}
		    synchronized (doclock)
			{
			    lastPosition = -1;
			    change = 0;
			}
		    // since we did something, we should check that there is
		    // nothing else to do before going back to sleep.
		    tryAgain = true;
		}                
	    asleep = true;
	    if (!tryAgain)
		{
                    try 
			{
			    sleep (0xdddddd);
			}
		    catch (InterruptedException x)
			{
			    //x.printStackTrace();   
			}
                    
                }
	    asleep = false;
	}
     }
}

