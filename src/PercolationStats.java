import edu.princeton.cs.algs4.StdStats;

/**
 * 多次测试获取结果
 * 
 * @author 40741
 *
 * @date 2017年9月30日
 */
public class PercolationStats {

	private Percolation p;

	/**
	 * array storing all the openSites in experiments
	 */
	private int[] openSites;
	/*
	 * equlas to sum / trials , get average nodes count of percolate incident
	 */
	private double avgSum;   
	/*
	 *  the sample standard deviation which measures the sharpness of the threshold
	 */
	private double stdDeviation;	 

	/*
	 *  experiment times
	 */
	private int trials;
	/*
	 * perform trials independent experiments on an n-by-n grid
	 */
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
		this.trials = trials;
		
		openSites = new int[trials];
		calculate(n, trials);
		log();
	}

    /**
     * calculate the answer
     */
    private void calculate(int n, int trials) {
        for (int i = 0; i < trials; i++) {    // for trials times experiments 
		    p = new Percolation(n);
		    int random, row, col, prclteTimes = 0;  
		    for (int j = 0; j < n * n; j++) { // start n * n times open until this experiment percolates
		        random = (int) (Math.random() * (n * n - 1) + 1);    // get a random number to open, range is [1, n * n] 
		        col = random % n;     
		        row = col == 0 ? random / n : random / n + 1;
		        col = col == 0 ? n : col;
		        p.open(row, col);
		        if(p.percolates())  {     // this model percolates 
		            prclteTimes = p.numberOfOpenSites();
		            openSites[i] = prclteTimes;   // record openSites of this model
		            break;   
		        }
            }
        }
    }
	
	private void log() {
	    System.out.println("mean\t\t\t = " + mean());
        System.out.println("stddev\t\t\t = " + stddev() );
        System.out.println("95% confidence interval\t = [" + confidenceLo() + ", " + confidenceHi() + "]");
	}
	/**
	 * get experiment times 
	 */
	private int getTrials() {
	    return trials;
	}
	/*
	 * sample mean of percolation threshold
	 */
	public double mean() {
	    avgSum = StdStats.mean(openSites) / getTrials();
		return avgSum;
	} 
	/*
	 * sample standard deviation of percolation threshold
	 */
	public double stddev() {
	    stdDeviation = StdStats.stddev(openSites);
		return stdDeviation;
	}
	/*
	 * low  endpoint of 95% confidence interval
	 */
	public double confidenceLo() {
	    double cl = mean() - (1.96 * stddev()) / getTrials(); 
		return  cl;
	}
	/*
	 * high endpoint of 95% confidence interval
	 */
	public double confidenceHi() {
	    double ch = mean() + (1.96 * stddev()) / getTrials(); 
        return  ch;
	}  
	/*
	 * test client (described below)
	 */
	public static void main(String[] args) {
	    int n = 200;
	    int trials = 100;
	    PercolationStats ps = new PercolationStats(n, trials);
	}  
}
