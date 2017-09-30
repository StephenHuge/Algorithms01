
/**
 * 多次测试获取结果
 * 
 * @author 40741
 *
 * @date 2017年9月30日
 */
public class PercolationStats {

	private Percolation p;

	//    private int sum;	// count of nodes that percolates in various experiments
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
		
		for (int i = 0; i < trials; i++) {    // for trials times experiments 
		    p = new Percolation(n);
		    
		    int random, row, col, prclteTimes = 0;  
		    for (int j = 0; j < n * n; j++) { // start n * n times open until this experiment percolates
		        random = (int) (Math.random() * (n * n - 1));    // get a random number to open 
//		        System.out.println("random: " + random);
		        row = random / 10 + 1;    col = random % 10 + 1;
		        p.open(row, col);
		        prclteTimes++;
		        if(p.percolates())  break;
            }
		    avgSum += prclteTimes;
		    System.out.println("times of open operation before percolate: " + prclteTimes);
        }
		avgSum = avgSum / trials;
		System.out.println("Average times open operation before percolate: " + avgSum);
	}
	private int getTrials() {
	    return trials;
	}
	/*
	 * sample mean of percolation threshold
	 */
	public double mean() {
		return avgSum;
	} 
	/*
	 * sample standard deviation of percolation threshold
	 */
	public double stddev() {
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
	    int n = 10;
	    int trials = 100;
	    PercolationStats ps = new PercolationStats(n, trials);
	    System.out.println("test: " + n + " by " + n + " grid, for " + trials + " times.");
	}  
}
