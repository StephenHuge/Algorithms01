import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * implement class Percolation 
 * 
 * @author HJS
 * 
 * @date 2017-9-29
 * 
 */
public class Percolation {
    
    private boolean[][] grid;
    
    private static final boolean OPENED = true;
    
    private int openedNum = 0;
    
    private final int virtualUpRoot;
    private final int virtualDownRoot;
    
    private final WeightedQuickUnionUF w;
    /*
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        
        grid = new boolean[n][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = false;
            }
        }
        
        w = new WeightedQuickUnionUF(n * n + 2);
        virtualUpRoot = n * n;
        virtualDownRoot = n * n + 1;
    }
    /*
     *  open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = OPENED;
            openedNum++;
        }
        int position = (row - 1) * grid.length + col - 1;
        int up = (row - 2) * grid.length + col - 1;
        int down = row * grid.length + col - 1;
        int left = (row - 1) * grid.length + (col - 2);
        int right = (row - 1) * grid.length + col;
        
        if (row == 1)             // there is a virtual root for row 1, for simplify the codes
            w.union(position, virtualUpRoot);
        if(row == grid.length) {
            w.union(virtualDownRoot, position);
        }
        if (row != 1 && isOpen(row - 1, col))    // whether in the up edge
            w.union(position, up);   
        if ((row != grid.length) && isOpen(row + 1, col)) // whether in the down edge
            w.union(position, down);
        if (col != 1 && isOpen(row, col - 1)) // whether in the left edge             
            w.union(position, left);
        if ((col != grid.length) && isOpen(row, col + 1)) // whether in the right edge 
            w.union(position, right);
    }
    /*
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
       validate(row, col);
        return grid[row - 1][col - 1];
    } 
    /*
     * is site (row, col) full (means that site gets water)?
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        int position = (row - 1) * grid.length + col - 1;   // current site
        // if connected with virtual up root, then return true
        if (isOpen(row, col) && w.connected(position, virtualUpRoot)) 
                return true;
        return false;
    } 
    /*
     *  does the system percolate?
     */
    public boolean percolates() {
        return w.connected(virtualUpRoot, virtualDownRoot);
    }
    
    private void validate(int row, int col) {
        if (row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
    }
    public int numberOfOpenSites() {
        return openedNum;
    }     
    /*
     * test client (optional)
     */
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        
        for (int i = 1; i <= p.grid.length; i++) {
            for (int j = 1; j <= p.grid.length; j++) {
                p.open(i, j);
                p.isFull(i, j);
                System.out.println(i + " - " + j + ": " + p.grid[i - 1][j - 1]);
            }
        }
        System.out.println(p.numberOfOpenSites());
    } 
}
