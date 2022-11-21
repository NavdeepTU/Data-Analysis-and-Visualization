/*
You are given N unique numbers a1<a2<a3<...an. Find out the count of all possible binary search trees that can be constructed using these numbers. 
For example with 3 elements 1,2,3, there are 5 possible BST and for 1,2,3,4 there are 14 BST.
 */


import java.util.*;
class UniqueBST
{
	public static int countBST(int n)
	{
		int[] dp = new int[n+1];
		dp[0] = 1;  // only 1 unique BST is possible with 0
		dp[1] = 1;  // only 1 unique BST is possible with 0, 1
		
		for(int i=2; i<n+1; i++) //upto n
		{
			for(int j=1; j<=i; j++)  // This loop makes j as root and computes # of unique BST's for n=i
			{
				dp[i] += dp[j-1]*dp[i-j];
			}
		}
		return dp[n];
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(countBST(n)); // To count number of BT multiply countBST(n) with n!
	}
}
/*
Sample Input 1 : 
3
Output : 5
Sample Input 2 : 
4
Output : 14
*/