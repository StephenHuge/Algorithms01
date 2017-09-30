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
    
    private int virtualUpRoot;
    private int virtualDownRoot;
    
    private WeightedQuickUnionUF w;
    /*
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if(n <= 0) throw new java.lang.IllegalArgumentException();
        
        grid = new boolean[n][n];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
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
        
        if(row == 1)             // 第一行有一个虚结点，是为了后面简化判断是否渗析而设置的
            w.union(position, virtualUpRoot);
        else if(row == grid.length)                      
            w.union(position, virtualDownRoot);
        if(row != 1 && isOpen(row - 1, col))    // 是否处于上边界             
            w.union(position, up);   
        if((row != grid.length) && isOpen(row + 1, col)) // 是否处于下边界 
            w.union(position, down);
        if(col != 1 && isOpen(row, col - 1)) // 是否处于左边界             
            w.union(position, left);
        if((col != grid.length) && isOpen(row, col + 1)) // 是否处于右边界 
            w.union(position, right);
    }
    
    /*
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if(row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
        return grid[row - 1][col - 1];
    } 
    /*
     * is site (row, col) full (means that site gets water)?
     */
    public boolean isFull(int row, int col) {
        if(row < 1 || row > grid[0].length || col < 1 || col > grid.length) 
            throw new java.lang.IllegalArgumentException();
        if(isOpen(row, col) && row == 1)    return true;    // 最上面一行默认有水
        int position = (row - 1) * grid.length + col - 1;   // 当前节点
        if(w.connected(position, virtualUpRoot))            // 如果跟Up虚结点有连接则返回true   
            return true;
        return false;
    } 
    public int numberOfOpenSites() {
        return openedNum;
    }     
    /*
     *  does the system percolate?
     */
    public boolean percolates() {
        return w.connected(virtualUpRoot, virtualDownRoot);
    }             
    /*
     * test client (optional)
     */
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        
        for(int i = 1; i <= p.grid.length; i++) {
            for(int j = 1; j <= p.grid.length; j++) {
                p.open(i, j);
                System.out.println(i + " - " + j + ": " + p.grid[i - 1][j - 1]);
            }
        }
        System.out.println(p.numberOfOpenSites());
    } 
}
