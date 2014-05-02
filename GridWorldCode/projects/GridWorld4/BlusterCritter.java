/********************************************************
 * 4. Create a class BlusterCritter that extends Critter.
 * A BlusterCritter looks at all of the neighbors within
 * two steps of its current location. If there are c or more
 * critters, the BlusterCritter's color darkens (color values decrease).
 * Here, c is a value that indicates the courage of the critter.
 * It should be set in the constructor.
 ********************************************************/

import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class BlusterCritter extends Critter {
    private int courage;
    
    public BlusterCritter( int c ) {
	super();
	courage = c;
    } //end constructor

    //getting the actors
    public ArrayList<Actor> getActors() {
	ArrayList<Actor> actors = new ArrayList<Actor>();
	Location loc = getLocation();
	for ( int r=loc.getRow() - 2; r<=loc.getRow() + 2; r++ ) {
	    for ( int c=loc.getCol() - 2; c<=loc.getCol() + 2; c++) {
		Location l = new Location( r, c );
		if ( getGrid().isValid( l ) ) {
		    Actor a = getGrid().get( l );
		    if ( a != null && a != this ) { //exclude this from the list!
			actors.add( a );
		    }
		}
	    }
	}
	return actors;
    } //end method getActors()

    public void darken() { //by 5
	Color c = getColor();
	int RED = c.getRed();
	int GREEN = c.getGreen();
	int BLUE = c.getBlue();
	
	if (RED >= 5) RED-=5;
	if (GREEN >= 5) GREEN-=5;
	if (BLUE >= 5) BLUE-=5;
	setColor(new Color(RED, GREEN, BLUE));
    } //end method darken()

     public void lighten() { //by 5
	Color c = getColor();
	int RED = c.getRed();
	int GREEN = c.getGreen();
	int BLUE = c.getBlue();
	
	if (RED <= 250) RED+=5;
	if (GREEN <= 250) GREEN+=5;
	if (BLUE <= 250) BLUE+=5;
	setColor(new Color(RED, GREEN, BLUE));
    } //end method lighten()

    public void processActors( ArrayList<Actor> actors ) {
	int x = 0;
	for ( Actor a : actors ) {
	    if ( a instanceof Critter ) count++;
	}
	if ( count < courage ) lighten();
	else darken();
    } //end method processActors()
    
} //end class BlusterCritter