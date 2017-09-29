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
    
    private boolean[][] grid;
    
    private static final boolean OPENED = true;
    
//    private static final boolean NOT_OPENED = false;
    
    private int openedNum = 0;
    
    private int virtualUpRoot = 0;
    private int virtualDownRoot = 0;
    
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
        if(row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
        if(!isOpen(row, col)) {
            grid[row - 1][col - 1] = OPENED;
            openedNum++;
        }

        int position = (row - 1) * grid.length + col - 1;
        int up = (row - 2) * grid.length + col - 1;
        int down = row * grid.length + col - 1;
        int left = (row - 1) * grid.length + (col - 2);
        int right = (row - 1) * grid.length + col;
        
        if(row != 1 && isOpen(row - 1, col))                 w.union(position, up);   // 是否处于上边界
        if((row != grid.length) && isOpen(row + 1, col)) w.union(position, down); // 是否处于下边界
        if(col != 1 && isOpen(row, col - 1))                 w.union(position, left); // 是否处于左边界
        if((col != grid.length) && isOpen(row, col + 1)) w.union(position, right);// 是否处于右边界
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
        return grid[row - 1][col - 1];
    } 
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if(row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
        return false;
    } 
    public int numberOfOpenSites() {
        return openedNum;
    }     
    // does the system percolate?
    public boolean percolates() {
        return false;
    }             
    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        for(int i = 1; i <= p.grid.length; i++) {
            for(int j = 1; j <= p.grid.length; j++) {
                p.open(i, j);
                System.out.println(i + " - " + j + ": " + p.grid[i - 1][j - 1]);
            }
        }
    } 
}
