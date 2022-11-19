/* 
Given an NxM (N rows and M columns) integer matrix with non-negative values (0..MAX_INT inclusive). What is the maximum sum from going top left (0, 0) to bottom right (N-1, M-1)? 
The condition is that when you're at the point (p, q), you can only move to either right (p, q+1) or down (p+1, q).
*/

import java.util.*;

class MaxCostPath {
	
	public static int max(int a, int b){
		return a >= b ? a:b;
	}
	
	public static int maxSum(int n, int m, int[][] matrix)     // Bottom up DP based approach.
	{
		int[][] cost = new int[n][m];
		cost[0][0] = matrix[0][0];
		for(int i=1; i<m; i++)  // filling first row
			cost[0][i] = matrix[0][i]+cost[0][i-1];
		for(int i=1; i<n; i++) // filling first column
			cost[i][0] = matrix[i][0]+cost[i-1][0];
			
		for(int i=1; i<n; i++)
		  for(int j=1; j<m; j++)
		    cost[i][j]= matrix[i][j] + max(cost[i-1][j], cost[i][j-1]);  // DP step
		    
		return cost[n-1][m-1];
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] matrix = new int[n][m];
		
		for(int i=0; i<n; i++) 
			for(int j=0; j<m; j++) 
				matrix[i][j] = sc.nextInt();
				
		int maxCost = maxSum(n, m, matrix);
		System.out.println("Maximum cost is : "+maxCost);
	}
}
