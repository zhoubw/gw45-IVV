/********************************************************
 * 5. Create a class QuickCrab that extends CrabCritter. A
 * QuickCrab processes actors the same way a CrabCritter does.
 * A QuickCrab moves to one of the two locations, randomly
 * selected, that are two spaces to its right or left, if 
 * that location and the intervening location are both empty.
 * Otherwise, a QuickCrab moves like a CrabCritter.
 ********************************************************/

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
    
    public QuickCrab() {
	setColor(Color.RED);
    } //end constructor

    public void addTwoAwayLoc( ArrayList<Location> locs, int direction ) {
	Grid gr = getGrid();
	Location loc = getLocation();
	Location temp = loc.getAdjacentDirection( direction );
	if ( gr.isValid( temp ) && gr.get( temp ) == null ) {
	    Location loc2 = temp.getAdjacentDirection( direction ); //jumps fwd again
	    if ( gr.isValid( loc2 ) && gr.get( loc2 ) == null ) locs.add( loc2 );
	}
    } //end method addTwoAwayLoc


    //get 2 locations right and left that are empty
    public ArrayList<Location> getMoveLocations() {
	ArrayList<Location> locs = new ArrayList<Location>();
	Grid gr = getGrid();
	addTwoAwayLoc( locs, getDirection() + Location.LEFT );
	addTwoAwayLoc( locs, getDirection() + Location.RIGHT );
	if ( locs.size() == 0 ) return super.getMoveLocations();
	return locs;
    } //end method getMoveLocations()
    
} //end class QuickCrab