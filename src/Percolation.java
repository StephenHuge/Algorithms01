import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * implement class Percolation 
 * 
 * @author HJS
 * 
 * @date 2017-10-2
 * 
 */
public class Percolation {
    
    private final boolean[][] sites;    // model that we use for class Percolation 
    private final WeightedQuickUnionUF ufP;     // for percolates() method, there are (n * n + 2) sites
    private final WeightedQuickUnionUF ufF;     // for isFull() method, there are (n * n + 1) sites
    
    private final int virtualUpRootP;    // virtual up root for ufP 
    private final int virtualDownRootP;  // virtual down root for ufP
    
    private final int virtualUpRootF;   // virtual up root for ufF, ufF has no virtual down root
    
    private int openedCount;        // record count of opened sites 
    
    public Percolation(int n)                
    {
        if(n <= 0) 
            throw new java.lang.IllegalArgumentException();
        sites = new boolean[n][n];  // create n-by-n grid, with all sites blocked
        
        // init all variables
        ufP = new WeightedQuickUnionUF(n * n + 2);
        virtualUpRootP = n * n;
        virtualDownRootP = n * n + 1;
        
        ufF = new WeightedQuickUnionUF(n * n + 1);
        virtualUpRootF = n * n;
        
        openedCount = 0;
    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        validate(row, col);
        if (!isOpen(row, col)) 
        {
            sites[row - 1][col - 1] = true; // open the site
            openedCount++;
        }
        spread(row, col);       // connect sites beside it which are opened
    }
    private void spread(int row, int col) 
    {
        validate(row, col);
        
        int position = (row - 1) * sites.length + col - 1;
        int up = (row - 2) * sites.length + col - 1;        // site above
        int down = row * sites.length + col - 1;            // site below
        int left = (row - 1) * sites.length + (col - 2);    // site in the left
        int right = (row - 1) * sites.length + col;         // site in the right
        if (row == 1) 
        {
            ufP.union(position, virtualUpRootP);
            ufF.union(position, virtualUpRootF); 
        }
        if (row == sites.length) 
        {
            ufP.union(position, virtualDownRootP);
        }
        if (row != 1 && isOpen(row - 1, col))   // whether in the up edge 
        { 
            ufP.union(position, up);
            ufF.union(position, up);
        }           
        if (row != sites.length && isOpen(row + 1, col))   // whether in the down edge
        {
            ufP.union(position, down);
            ufF.union(position, down);
        }
        if (col != 1 && isOpen(row, col - 1))  // whether in the left edge
        {
            ufP.union(position, left);
            ufF.union(position, left);
        }
        if (col != sites.length && isOpen(row, col + 1))  // whether in the left edge
        {
            ufP.union(position, right);
            ufF.union(position, right);
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        validate(row, col);
        return sites[row - 1][col - 1];
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        validate(row, col);
        int position = (row - 1) * sites.length + col - 1;
        return ufF.connected(position, virtualUpRootF);
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return openedCount;
    }
    public boolean percolates()              // does the system percolate?
    {
        return ufP.connected(virtualUpRootP, virtualDownRootP);
    }
    
    private void validate(int row, int col) 
    {
        if (row < 1 || row > sites.length || col < 1 || col > sites.length)
            throw new java.lang.IllegalArgumentException();
    }
    public static void main(String[] args)   // test client (optional)
    {
        // UncommentedEmptyMethodBody
    }
}
