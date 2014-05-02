//Barak Zhou
//https://github.com/Merkuri/gw45-IVV
//APCS PD 8
//HW #34
//2014-04-29

//Exercises: Part 4

/********************************************************
 * 1. Modify the processActors method in ChameleonCritter
 * so that if the list of actors to process is empty, the
 * color of the ChameleonCritter will darken (like a flower).
 ********************************************************/

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class ChameleonCritter extends Critter {
    
    protected static final double DARKENING_FACTOR = 0.05; //same as Flower
    
    public void processActors ( ArrayList<Actor> actors ) {
	int n = actors.size();
	if ( n == 0 ) {
	    //darken like a flower
	    //alter the color values, then set color
	    Color c = getColor();
	    int RED = (int) ( c.getRed() * ( 1 - DARKENING_FACTOR ) );
	    int GREEN = (int) ( c.getGreen() * ( 1 - DARKENING_FACTOR ) );
	    int BLUE = (int) ( c.getBlue() * ( 1 - DARKENING_FACTOR ) );
	    setColor( new Color( RED, GREEN, BLUE ));
	}
	int r = (int) ( Math.random() * n );
	Actor other = actors.get(r);
	setColor( other.getColor() );
    } //end method processActors()

    public void makeMove( Location loc ) {
	setDirection( getLocation().getDirectionToward( loc ) );
	super.makeMove( loc );
    } //end method makeMove() 

} //end class ChameleonCritter