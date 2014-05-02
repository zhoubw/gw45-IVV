/********************************************************
 * 6. Kings don't just make things commit suicide if they
 * can't get out of the way. We call those dictators.
 ********************************************************/

import info.gridworld.actor.Actor; 
import info.gridworld.actor.Critter; 
import info.gridworld.grid.Grid; 
import info.gridworld.grid.Location; 
 
import java.awt.Color; 
import java.util.ArrayList;

public class KingCrab extends CrabCritter {
    public KingCrab() {
	setColor( Color.YELLOW ); //regal gold
    } //end constructor

    public int distanceFrom( Location L1, Location L2 ) {
	int x1 = L1.getRow();
	int x2 = L2.getRow();
	int y1 = L1.getCol();
	int y2 = L2.getCol();
	double dist = Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ) + 0.5; //flooring purposes; its on
	//a multiple choice question!
	return (int) Math.floor( dist );
    } //end method distanceFrom()

    public boolean moveOneMoreAway( Actor a ) {
	ArrayList locs = getGrid().getEmptyAdjacentLocations( a.getLocation() );
	for ( Location loc : locs ) {
	    if ( distanceFrom( getLocation(), loc ) > 1 ) {
		a.moveTo( loc );
		return true;
	    }
	}
	return false;
    } //end method moveOneMoreAway()

    public void processActors( ArrayList<Actor> actors ) {
	for ( Actor a : actors ) {
	    if ( !moveOneMoreAway( a ) ) {
		a.removeSelfFromGrid();
	    }
	}
    } //end method processActors()

} //end class CrabCritter