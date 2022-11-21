/*

There are n balloons and n bullets and each balloon is assigned with a particular number (point). Whenever a particular balloon is shot the no of points increases by 
1. the multiplication of point assigned to balloon on left and that of right side.
2. point assigned to left if no right exists
3. point assigned to right if no left exists.
4. the point assigned to itself if no other balloon exists.
You have to output the maximum no of points possible.
Input
1 2 3 4
Output
20.

 */



import java.util.*;
class BurstBalloons
{
	public static int maxCoinsBottomUpDP(int[] nums)
	{
		int[][] T = new int[nums.length][nums.length];
		for(int len=1; len <= nums.length; len++){
			for(int i=0; i <= nums.length-len; i++){
				int j = i+len-1;
				for(int k=i; k<=j; k++){ // for every balloon between i and j
					// leftValue/rightValue is initially -1. If there is element on left/right of k then left/right value will take that value.
					int leftValue = -1;
					int rightValue = -1;
					if(i != 0)
						leftValue = nums[i-1];
					if(j != nums.length-1)
						rightValue = nums[j+1];
					// before is initially 0. If k is i then before will stay 0 otherwise it gets value T[i][k-1].
					// after is similarly 0 initially. If k is j then after will stay 0 otherwise will get value T[k+1][j].
					int before = 0;
					int after = 0;
					if(i != k)
						before = T[i][k-1];
					if(j != k)
						after = T[k+1][j];
					if(leftValue > 0 && rightValue > 0)
						T[i][j] = Math.max(leftValue*rightValue + before + after, T[i][j]);
					else if(leftValue > 0 && rightValue < 0)
						T[i][j] = Math.max(leftValue + before + after, T[i][j]);
					else if(leftValue < 0 && rightValue > 0)
						T[i][j] = Math.max(rightValue + before + after, T[i][j]);
					else                                             // if there is no balloon to left and right of k then use points of balloon at k
						T[i][j] = Math.max(nums[k]+before+after, T[i][j]);
				}
			}
		}
		return T[0][nums.length-1];
	}
	public static void main (String[] args) 
	{
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] nums = new int[n];
		for(int i=0; i<n; i++)
			nums[i] = s.nextInt();
		System.out.println("Maximum coins that can be earned are : "+maxCoinsBottomUpDP(nums));
	}
}
/* 
Sample Input 1:
4
1 2 3 4
Sample Input 2:
5
3 10 1 2 5
Sample Input 3:
7
12 48 28 21 67 75 85
Sample Input 4:
8
245 108 162 400 274 358 366 166
Sample Input 5:
10
866 919 840 944 761 895 701 912 848 799

*/