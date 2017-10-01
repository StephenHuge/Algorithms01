import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * get various experiments solution
 * 
 * @author 40741
 *
 * @date 2017-9-30
 */
public class PercolationStats {
    
    /**
     * a constant for calculation of 95% confidence interval 
     */
    private static final double CONFIDENCE_95 = 1.96;

    /**
     * array storing all the openSites in experiments
     */
    private double[] openSites;
    /*
     *  experiment times
     */
    private final int exTimes;
    /*
     * perform trials independent experiments on an n-by-n grid
     */
    public PercolationStats(int n, int trials) {
        validate(n, trials);
        this.exTimes = trials;
        openSites = new double[trials];
        calculate(n, trials);
    }

    /**
     * validate input
     */
    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
    }
    /**
     * calculate the answer
     */
    private void calculate(int n, int trials) {
        Percolation p;
        for (int i = 0; i < trials; i++) {    // for trials times experiments 
            p = new Percolation(n);
            int row, col, prclteTimes = 0;  
            for (int j = 0; j < getPower2(n); j++) { // start n * n times open until this experiment percolates
                row = StdRandom.uniform(n) + 1; // get a random number for row [1, n + 1)
                col = StdRandom.uniform(n) + 1; // get a random number for col [1, n + 1)
//                System.out.println("random:" + row + " - " + col);
                p.open(row, col);
                if (p.percolates())  {     // this model percolates 
                    prclteTimes = p.numberOfOpenSites();
                    openSites[i] = 1.0 * prclteTimes / getPower2(n);   // record possibility percolates mean of this model
                    break;   
                }
            }
        }
    }
    
    private static void log(PercolationStats ps) {
        System.out.println("mean\t\t\t = " + String.format("%6f", ps.mean()));
        System.out.println("stddev\t\t\t = " + ps.stddev());
        System.out.println("95% confidence interval\t = [" + String.format("%6f", ps.confidenceLo()) 
                    + ", " + String.format("%6f", ps.confidenceLo()) + "]");
    }
    private int getPower2(int num) {
        return num * num;
    }
    /**
     * get experiment times 
     */
    private int getExTimes() {
        return exTimes;
    }
    /*
     * sample mean of percolation threshold
     */
    public double mean() {
        double avgSum = StdStats.mean(openSites);
        return avgSum;
    } 

    /*
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        double stdDeviation = StdStats.stddev(openSites);
        return stdDeviation;
    }
    /*
     * low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        double cl = mean() - (CONFIDENCE_95 * stddev()) / getExTimes(); 
        return  cl;
    }
    /*
     * high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        double ch = mean() + (CONFIDENCE_95 * stddev()) / getExTimes(); 
        return  ch;
    }  
    /*
     * test client (described below)
     */
    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);  int trials = Integer.parseInt(args[1]);
        int n = 5;  int trials = 10000;
        PercolationStats ps = new PercolationStats(n, trials);
        log(ps);
    }  
}
