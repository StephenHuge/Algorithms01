package my.algorithms.hello;

import edu.princeton.cs.algs4.StdDraw;

public class StdDrawTest1 {
    public static void main(String[] args) {
        int N = 100;
        StdDraw.setXscale(0, N);    // set X axis' length
        StdDraw.setYscale(0, N * N);// set Y axis' length
        StdDraw.setPenRadius(.01);  // set pen's weight
        
        for(int i = 0; i < N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
    }
}
