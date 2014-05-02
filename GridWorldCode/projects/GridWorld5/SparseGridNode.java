public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;

    public SparseGridNode ( Object occupant, int col, SparseGridNode next ) {
	this.occupant = occupant;
	this.col = col;
	this.next = next;
    }

    public Object getOccupant() {
	return occupant;
    }
    
    public int getCol() {
	return col;
    }

    public SparseGridNode getNext() {
	return next;
    }

    public SparseGridNode setNext( SparseGridNode n ) {
	next = n;
	return n;
    }

    public int setCol( int c ) {
	col = c;
	return c;
    }

    public Object setOccupant( Object o ) {
	occupant = o;
	return o;
    }
    
}