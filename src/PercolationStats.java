
/**
 * 多次测试获取结果
 * 
 * @author 40741
 *
 * @date 2017年9月30日
 */
public class PercolationStats {
    
    private Percolation p;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
            p = new Percolation(n);
    }
    // sample mean of percolation threshold
    public double mean() {
        return -1;
    } 
    // sample standard deviation of percolation threshold
    public double stddev() {
        return -1;
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return -1;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return -1;
    }  
    // test client (described below)
    public static void main(String[] args) {}  
}
