

public class Percolation {
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0) throw new java.lang.IllegalArgumentException();
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {}   
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return false;
    } 
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
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
    public static void main(String[] args) {} 
}
