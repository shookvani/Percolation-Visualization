public class Percolation {
//percolates function, numbering
    // creates n-by-n grid, with all sites initially blocked'
    
    private boolean[] opened;
    private WeightedQuickUnionUF grid;
    private int side;
    private int virtualTop;
    private int virtualBottom;
    private int openSites=0;
    
    
    public Percolation(int n) {
        side=n;
        opened = new boolean[n*n];
        grid = new WeightedQuickUnionUF(n*n+2);
        virtualTop=n*n;
        virtualBottom=n*n+1;
        
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index=getIndex(row,col);
        if (!opened[index]) {
            openSites+=1;
       
            //open site
            opened[index] = true;
            //left site
            if ((row-1)>=0 && isOpen(row-1, col)) {

                grid.union(index, getIndex(row-1,col)); 

            }

            //right site
            if ((row+1)<side && isOpen(row+1, col)) {

                grid.union(index, getIndex(row+1,col)); 

            }

            //lower site
            if ((col-1)>=0 && isOpen(row, col-1)) {

                grid.union(index, getIndex(row,col-1)); 

            }

            //upper site
            if ((col+1)<side && isOpen(row, col+1)) {

                grid.union(index, getIndex(row,col+1)); 

            }
            if (index<side) {
                grid.union(index, virtualTop);
            }
            if(index>=side*side-side) {
                grid.union(index, virtualBottom);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return opened[getIndex(row,col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return grid.connected(getIndex(row,col),virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
       return openSites;
    }

    // does the system percolate?
    public boolean percolates() {    
        return grid.connected(virtualTop, virtualBottom); 
    
        
    }
    
    private int getIndex(int row, int col) {
        return (row)*side+col;
        
    }

    // unit testing (required)
    //public static void main(String[] args)

}