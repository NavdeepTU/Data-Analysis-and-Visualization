/*
 
Farmer John has built a new long barn, with N (2 <= N <= 100,000) stalls. The stalls are located along a straight line at positions x1,...,xN (0 <= xi <= 1,000,000,000).

His C (2 <= C <= N) cows don't like this barn layout and become aggressive towards each other once put into a stall. To prevent the cows from hurting each other, 
FJ wants to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. What is the largest minimum distance?
Input
t – the number of test cases, then t test cases follows.
* Line 1: Two space-separated integers: N and C
* Lines 2..N+1: Line i+1 contains an integer stall location, xi
Output
For each test case output one integer: the largest minimum distance.
Example
Input:
1
5 3
1
2
8
4
9
Output:
3
Output details:
FJ can put his 3 cows in the stalls at positions 1, 4 and 8,
resulting in a minimum distance of 3.

 */


import java.util.*;
class AggresiveCows
{
	public static boolean isPossible(int[] arr,int mid, int cows)
	{
		// last stall..jis par cow rakhi hai
		int last = arr[0];
		// number of placed cows
		int count = 1;
		for(int i=1; i<arr.length; i++)  // now place #cows at remaining stalls
		{
			if(arr[i]-last >= mid)
			{
				count++;
				last = arr[i];
			}
			if(count == cows)
				return true;
		}
		return false;
	}
	public static int getMaxDistance(int[] arr, int cows)
	{
		if(arr.length == 0)
			return 0;
		Arrays.sort(arr);   // first sort the input array in ascending order
		int low = 1;  // minimum distance between any 2 cows is 1
		int high = arr[arr.length-1]-1; // largest distance between any 2 cows is last position of the stall-1;
		int ans = 0;
		while(low <= high)  // using binary search to find the the largest minimum distance
		{
			int mid = (high+low)/2;
			if(isPossible(arr, mid, cows))
			{
				ans = mid;
				low = mid+1;  // this operation gives us the largest minimum distance
			}
			else
				high = mid-1;
		}
		return ans;
	}
	public static void main (String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int i=0; i<t; i++)
		{
			int n = sc.nextInt();
			int c = sc.nextInt();
			int[] arr = new int[n];
			for(int j=0; j<n; j++)
				arr[j] = sc.nextInt();
			System.out.println(getMaxDistance(arr, c));
		}
	}
}
/*
Sample input :
1
5 3
1
2
8
4
9

Output: 3
*/