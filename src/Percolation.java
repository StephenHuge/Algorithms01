import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 实现一个Percolation类
 * 
 * @author HJS
 * 
 * @date 2017年9月29日
 * 
 */
public class Percolation {
    
    private static final boolean OPENED = true;
    
    private static final boolean NOT_OPENED = false;
    
    private boolean[][] grid;
    
    private WeightedQuickUnionUF w;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0) throw new java.lang.IllegalArgumentException();
        grid = new boolean[n][n];
        w = new WeightedQuickUnionUF(n * n);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                grid[i][j] = false;
            }
        }
        
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if(row < 0 || row > grid[0].length - 1 || col < 0 || col > grid.length - 1) 
            throw new java.lang.IllegalArgumentException();
        if(!isOpen(row, col)) grid[row][col] = OPENED;

        int position = row * grid.length + col;
        int up = (row - 1) * grid.length + col;
        int down = (row + 1) * grid.length + col;
        int left = row * grid.length + (col - 1);
        int right = row * grid.length + (col + 1);
        
        if(row != 0 && isOpen(row - 1, col))           w.union(position, up);   // 是否处于上边界
        if((row != grid.length - 1) && isOpen(row + 1, col)) w.union(position, down); // 是否处于下边界
        if(col != 0 && isOpen(row, col - 1))           w.union(position, left);  // 是否处于左边界
        if((col != grid.length - 1) && isOpen(row, col + 1)) w.union(position, right);// 是否处于右边界
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row < 0 || row > grid[0].length -1 || col < 0 || col > grid.length - 1) 
            throw new java.lang.IllegalArgumentException();
        return grid[row][col];
    } 
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if(row < 0 || row > grid[0].length - 1 || col < 0 || col > grid.length - 1) 
            throw new java.lang.IllegalArgumentException();
        return false;
    } 
    public int numberOfOpenSites() {
        return -1;
    }     
    // does the system percolate?
    public boolean percolates() {
        return false;
    }             
    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        for(int i = 0; i < p.grid.length; i++) {
            for(int j = 0; j < p.grid.length; j++) {
                p.open(i, j);
                System.out.println(i + " - " + j + ": " + p.grid[i][j]);
            }
        }
    } 
}
