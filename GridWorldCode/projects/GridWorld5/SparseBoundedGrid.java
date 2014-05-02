//SparseBoundedGrid: 
// get(): O(1).
// put(): O(1) if dimensions are within the actual size.
// put(): O(dimensions^2) if otherwise, because the dimensions need to be traversed
// to make new ones.
import info.gridworld.grid.*;
import java.util.ArrayList;

public class SparseBoundedGrid<T> extends AbstractGrid<T> {
    
    private SparseGridNode[] occupants; //array of rows?!
    private int numRows;
    private int numCols;

    public SparseBoundedGrid( int rows, int cols ) {
	if ( rows <= 0 ) throw new IllegalArgumentException("Invalid rows");
	if ( cols <= 0 ) throw new IllegalArgumentException("Invalid cols");
	
	numRows = rows;
	numCols = cols;
	occupants = new SparseGridNode[rows];
    } //end constructor

    /*********accessors**********/
    public int getNumRows() {return numRows();}
    public int getNumCols() {return numCols();}

    public T get( Location loc ) {
	if ( !isValid( loc ) ) throw new IllegalArgumentException("loc is not valid");
	SparseGridNode n = occupants[ loc.getRow() ]; //get the row
	while ( n != null ) { //find the thing you want
	    if ( loc.getCol() == n.getColumn() ) return (E)n.getOccupant();
	    else n = n.getNext();
	}
    } //end method get()
    /*********accessors**********/

    /*********mutators**********/
    public E put( Location loc, E obj ) {
	if ( !isValid( loc ) ) throw new IllegalArgumentException("loc is not valid");
	if ( obj == null ) throw new IllegalArgumentException("object is null");
	
	E oldOccupant = remove(loc); //for some reason I have to return this
	//get row, then put element at front
	SparseGridNode n = occupants[ loc.getRow() ];
	occupants[ loc.getRow() ] = new SparseGridNode( obj, loc.getCol(), n);
	return oldOccupant;
    } //end method put()

    public E remove( Location loc ) {
	if ( !isValid( loc ) ) throw new IllegalArgumentException("loc is not valid");
	E obj = get( loc );

	if ( obj == null ) return null; //loc was empty
	
	SparseGridNode n = occupants[ loc.getRow() ];
	if ( n != null ) {
	    if ( n.getColumn() == loc.getCol() ) occupants[ loc.getRow() ] = n.getNext();
	    else {
		//r: searches in loc.getCol()
		//n: stays behind to remove occupant
		SparseGridNode r = n.getNext();
		while ( r != null && r.getColumn() != loc.getCol() ) {
		    n = n.getNext();
		    r = r.getNext();
		}
		if (r != null) n.setNext(r.getNext());
	    }
	}
	return obj;
	
    } //end method remove()

    /*********mutators**********/

    public boolean isValid( Location loc ) {
	return ( 0 <= loc.getRow() &&
		 loc.getRow() < getNumRows() &&
		 0 <= loc.getCol() &&
		 loc.getCol() < getNumCols() );
    } //end method isValid()

    public ArrayList<Location> getOccupiedLocations() {
	ArrayList<Location> locations = new ArrayList<Location>();
	
	//check for locs if empty
	for ( int i=0; i<getNumRows(); i++ ) {
	    SparseGridNode n = occupants[ i ];
	    //go through the whole row
	    while ( n != null ) {
		Location loc = new Location( r, p.getColumn() );
		locations.add( loc );
		n = n.getNext();
	    }
	} 
	return locations;
    } //end method getOccupiedLocations()

} //end class SparseBoundedGrid
