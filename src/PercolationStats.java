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
	 * array storing all the openSites in experiments
	 */
	private int[] openSites;
	/**
	 * the number n
	 */
	private final int range;
	/*
	 *  experiment times
	 */
	private final int trials;
	/*
	 * perform trials independent experiments on an n-by-n grid
	 */
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
		this.range = n;
		this.trials = trials;
		openSites = new int[trials];
		calculate(n, trials);
	}

    /**
     * calculate the answer
     */
    private void calculate(int n, int trials) {
        Percolation p;
        for (int i = 0; i < trials; i++) {    // for trials times experiments 
            p = new Percolation(n);
		    int random, row, col, prclteTimes = 0;  
		    for (int j = 0; j < getPower2(n); j++) { // start n * n times open until this experiment percolates
		        random = StdRandom.uniform(getPower2(n) - 1) + 1;    // get a random number to open, range is [1, n * n] 
		        col = random % n;     
		        row = col == 0 ? random / n : random / n + 1;
		        col = col == 0 ? n : col;
		        p.open(row, col);
		        if (p.percolates())  {     // this model percolates 
		            prclteTimes = p.numberOfOpenSites();
		            openSites[i] = prclteTimes;   // record openSites of this model
		            break;   
		        }
            }
        }
    }
	
	private static void log(PercolationStats ps) {
	    System.out.println("mean\t\t\t = " + ps.mean());
        System.out.println("stddev\t\t\t = " + ps.stddev() );
        System.out.println("95% confidence interval\t = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
	private int getPower2(int num) {
	    return num * num;
	}
	/**
	 * get experiment times 
	 */
	private int getTrials() {
	    return trials;
	}
	private int getRange() {
	    return range;
	}
	/*
	 * sample mean of percolation threshold
	 */
	public double mean() {
	    double avgSum = StdStats.mean(openSites) / getPower2(getRange());
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
	private final double CONFIDENCE_95 = 1.96;
	public double confidenceLo() {
	    double cl = mean() - (CONFIDENCE_95 * stddev()) / getTrials(); 
		return  cl;
	}
	/*
	 * high endpoint of 95% confidence interval
	 */
	public double confidenceHi() {
	    double ch = mean() + (CONFIDENCE_95 * stddev()) / getTrials(); 
        return  ch;
	}  
	/*
	 * test client (described below)
	 */
	public static void main(String[] args) {
	    int n = 200;
	    int trials = 100;
	    PercolationStats ps = new PercolationStats(n, trials);
	    log(ps);
	}  
}
