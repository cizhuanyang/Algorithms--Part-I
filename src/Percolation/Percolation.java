package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * @author FrouFrou
 * this is programming assignment for algs4 week 1 
 */
public class Percolation {
	private WeightedQuickUnionUF weightedQuickUnionUF;
	private WeightedQuickUnionUF weightedQuickUnionUFIsFull;
	private int gridsize;
	private int[][] grid;
	private int virtualTopSite;
	private int virtualBottomSite;
	public Percolation(int n) {
		if(n<0)
			throw new IllegalArgumentException("n must be at least 1");
		gridsize=n;
		grid=new int[n][n];
		weightedQuickUnionUF=new WeightedQuickUnionUF(n*n+2);
		weightedQuickUnionUFIsFull=new WeightedQuickUnionUF(n*n+1);
		virtualTopSite=0;
		virtualBottomSite=n*n+1;
	}
	public void open(int row,int col) {
		int index=getIndexInQuickFindStructure(row, col);
		if(!isOpen(row, col)) {			
			if(row==1) {
				weightedQuickUnionUF.union(virtualTopSite,index);
				weightedQuickUnionUFIsFull.union(virtualTopSite, index);
			}
			if(row==gridsize) {
				weightedQuickUnionUF.union(virtualBottomSite, index);
			}
		}
		connectIfOpen(index,row-1, col);
		connectIfOpen(index,row+1, col);
		connectIfOpen(index,row, col-1);
		connectIfOpen(index,row, col+1);
		grid[row-1][col-1]=1;
	}
	public boolean isOpen(int row,int col) {
		return grid[row-1][col-1]==1;
	}
	public boolean isFull(int row,int col) {
		return weightedQuickUnionUFIsFull.connected(virtualTopSite, getIndexInQuickFindStructure(row, col));
	}
	public boolean percolates() {
		return weightedQuickUnionUF.connected(virtualTopSite, virtualBottomSite);
	}
	private void connectIfOpen(int index,int row,int col) {
		try {
			if(isOpen(row, col)) {
				int indexforconnect=getIndexInQuickFindStructure(row, col);
				weightedQuickUnionUF.union(indexforconnect,index);
				weightedQuickUnionUFIsFull.union(indexforconnect, index);
			}
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			
		}
		
	}
	private int getIndexInQuickFindStructure(int row,int col) {
		return (row-1)*gridsize+col;
	}
}
