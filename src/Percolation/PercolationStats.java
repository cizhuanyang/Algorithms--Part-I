package Percolation;
import java.util.Random;

import edu.princeton.cs.algs4.*;

public class PercolationStats {
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi; 
	public PercolationStats(int N,int T) {
		int numofopen=0;
		double[] percolationThreshold=new double[T];
		if (N<0) {
			throw new IllegalArgumentException("N must be at least 1");
		}
		if (T<0) {
			throw new IllegalArgumentException("N must be at least 1");
		}
		for(int i=0;i<T;i++) {
			Percolation percolation=new Percolation(N);		
			while(!percolation.percolates()) {
				int row;
				int col;
				Random random=new Random();
				do {
					row=1+random.nextInt(N);
					col=1+random.nextInt(N);
				}while(percolation.isOpen(row, col));
				percolation.open(row, col);
				numofopen++;
			}
			percolationThreshold[i]=numofopen/(double)(N*N);
			numofopen=0;
		}
		mean=StdStats.mean(percolationThreshold);
		stddev=StdStats.stddev(percolationThreshold);
		double confidenceFraction=(1.96*stddev())/Math.sqrt(T);
		confidenceLo=mean-confidenceFraction;
		confidenceHi=mean+confidenceFraction;
	}
	public double mean() {
		return mean;
	}
	public double stddev() {
		return stddev;
	}
	public double confidenceLo() {
		return confidenceLo;
	}
	public double confidenceHi() {
		return confidenceHi;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=Integer.parseInt(args[0]);
		int T=Integer.parseInt(args[1]);
		PercolationStats percolationStats=new PercolationStats(N, T);
		StdOut.println("mean =  "+percolationStats.mean());
		StdOut.println("confidenceLo =  "+percolationStats.confidenceLo());
		StdOut.println("confidenceHi =  "+percolationStats.confidenceHi);
		System.out.println("test1");
	}

}
