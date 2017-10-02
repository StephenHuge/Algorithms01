import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * implement class PercolationStats
 *  
 * @author HJS
 *
 * @date 2017Äê10ÔÂ2ÈÕ
 */
public class PercolationStats {
    
    private static final double CONFIDENCE_95 = 1.96;
    private Percolation p;
    private double[] openSites;
    private final int exTimes;
    
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        validate(n, trials);
        openSites = new double[trials];
        exTimes = trials;
        
        calculate(n, trials);
    }
    private void calculate(int n, int trials) {
        for (int i = 0; i < trials; i++) 
        {
            p = new Percolation(n);
            for (; ;) 
            {
                int row = StdRandom.uniform(n) + 1; // a random integer uniformly in [1, n + 1)
                int col = StdRandom.uniform(n) + 1; // a random integer uniformly in [0, n + 1)
                
                p.open(row, col);
                if (p.percolates()) 
                {
                    int prclteTimes = p.numberOfOpenSites();
                    
                    // record possibility percolates mean of this model
                    openSites[i] = 1.0 * prclteTimes / (n * n); 
                    break;
                }
            }
        }
    }
    
    public double mean()                          // sample mean of percolation threshold
    {
        double mean = StdStats.mean(openSites);
        return mean;
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        double stddev = StdStats.stddev(openSites);
        return stddev;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        double cl = mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(exTimes); 
        return  cl;
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        double ch = mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(exTimes); 
        return  ch;
    }
    
    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0)
        {
            throw new java.lang.IllegalArgumentException();
        } 
    }
    private static void log(PercolationStats ps) {
        System.out.println("mean\t\t\t = " + ps.mean());
        System.out.println("stddev\t\t\t = " + ps.stddev());
        System.out.println("95% confidence interval\t = [" + String.format("%6f", ps.confidenceLo()) 
                    + ", " + String.format("%6f", ps.confidenceLo()) + "]");
    }
    public static void main(String[] args)        // test client (described below)
    {
      int n = Integer.parseInt(args[0]);  int trials = Integer.parseInt(args[1]);
//      int n = 50;  int trials = 20;
      PercolationStats ps = new PercolationStats(n, trials);
      log(ps);
    }
}
